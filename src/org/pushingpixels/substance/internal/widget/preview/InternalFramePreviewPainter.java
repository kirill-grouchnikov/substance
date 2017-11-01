package org.pushingpixels.substance.internal.widget.preview;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.WeakHashMap;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import org.pushingpixels.substance.api.painter.preview.DefaultPreviewPainter;
import org.pushingpixels.substance.internal.utils.WidgetUtilities;

public class InternalFramePreviewPainter extends DefaultPreviewPainter {
	/**
	 * Snapshot map.
	 */
	private static WeakHashMap snapshots = new WeakHashMap();

	public static void refreshSnaphost(JInternalFrame frame) {
		if (!frame.isShowing())
			return;
		// Draw the current state of the internal frame to a
		// temp image (w/o border and decorations). It would be nice
		// to use Robot, but this frame may be partially obscured,
		// so we take our chances that the frame will be properly
		// drawn by the user code.
		int frameWidth = frame.getWidth();
		int frameHeight = frame.getHeight();

		int dx = 0;
		int dy = 0;
		// Now we need to remove the border and the title pane :)
		Border internalFrameBorder = UIManager
				.getBorder("InternalFrame.border");
		Insets borderInsets = internalFrameBorder.getBorderInsets(frame);
		dx += borderInsets.left;
		dy += borderInsets.top;
		frameWidth -= (borderInsets.left + borderInsets.right);
		frameHeight -= (borderInsets.top + borderInsets.bottom);

		BasicInternalFrameUI frameUI = (BasicInternalFrameUI) frame.getUI();
		JComponent frameTitlePane = frameUI.getNorthPane();

		if (frameTitlePane != null) {
			dy += frameTitlePane.getHeight();
			frameHeight -= frameTitlePane.getHeight();
		}

		// fix for defect 112 - checking frame height and width
		if ((frameWidth > 0) && (frameHeight > 0)) {
			// draw frame (note the canvas translation)
			BufferedImage tempCanvas = new BufferedImage(frameWidth,
					frameHeight, BufferedImage.TYPE_INT_ARGB);
			Graphics tempCanvasGraphics = tempCanvas.getGraphics();
			tempCanvasGraphics.translate(-dx, -dy);
			frame.paint(tempCanvasGraphics);

			int maxWidth = UIManager.getInt("DesktopIcon.width");
			int maxHeight = maxWidth;

			// check if need to scale down
			double coef = Math.min((double) maxWidth / (double) frameWidth,
					(double) maxHeight / (double) frameHeight);
			if (coef < 1.0) {
				int sdWidth = (int) (coef * frameWidth);
				// int sdHeight = (int) (coef * frameHeight);
				// BufferedImage scaledDown = new BufferedImage(sdWidth,
				// sdHeight,
				// BufferedImage.TYPE_INT_ARGB);
				// Graphics g = scaledDown.getGraphics();
				// g.drawImage(tempCanvas, 0, 0, sdWidth, sdHeight, 0, 0,
				// frameWidth, frameHeight, null);
				BufferedImage scaledDown = WidgetUtilities.createThumbnail(
						tempCanvas, sdWidth);
				// System.out.println("Putting " + frame.hashCode() + "
				// -> " + scaledDown.hashCode());
				snapshots.put(frame, scaledDown);
			} else {
				// System.out.println("Putting " + frame.hashCode() + "
				// -> " + snapshot.hashCode());
				snapshots.put(frame, tempCanvas);
			}
		}
	}

	public void previewComponent(Container parent, Component component,
			int componentIndex, Graphics g, int x, int y, int w, int h) {
		BufferedImage preview = (BufferedImage) snapshots.get(component);
		if (preview != null) {
			g.drawImage(preview, x, y, null);
		}
	}

	public Dimension getPreviewWindowDimension(Container parent,
			Component component, int componentIndex) {
		return new Dimension(UIManager.getInt("DesktopIcon.width"), UIManager
				.getInt("DesktopIcon.width"));
	}
}
