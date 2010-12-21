/**
 * 
 */
package test.check;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.UIManager;

import org.pushingpixels.lafwidget.LafWidgetUtilities;
import org.pushingpixels.substance.api.SubstanceLookAndFeel;

final class CheckeredPanel extends ScrollablePanel {
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D graphics = (Graphics2D) g.create();
		graphics.setComposite(LafWidgetUtilities.getAlphaComposite(this));

		int w = this.getWidth();
		int h = this.getHeight();

		int cols = 1 + w / 10;
		int rows = 1 + h / 10;
		if ((UIManager.getLookAndFeel() instanceof SubstanceLookAndFeel)
				&& SubstanceLookAndFeel.getCurrentSkin(this)
						.getActiveColorScheme(
								SubstanceLookAndFeel.getDecorationType(this))
						.isDark())
			graphics.setColor(Color.black);
		else
			graphics.setColor(Color.white);
		graphics.fillRect(0, 0, w, h);
		for (int i = 0; i < cols; i++) {
			for (int j = 0; j < rows; j++) {
				if (((i + j) % 2) == 0) {
					float val = (i + j) / 100.f;
					val -= Math.floor(val);
					boolean isDark = (UIManager.getLookAndFeel() instanceof SubstanceLookAndFeel) ? SubstanceLookAndFeel
							.getCurrentSkin(this).getActiveColorScheme(
									SubstanceLookAndFeel
											.getDecorationType(this)).isDark()
							: false;
					float brightness = isDark ? 0.1f : 0.9f;
					float saturation = 0.2f;
					graphics.setColor(new Color(Color.HSBtoRGB(val, saturation,
							brightness)));
					graphics.fillRect(i * 10, j * 10, 10, 10);
				}
			}
		}

		graphics.setColor(Color.gray);
		graphics.setFont(new Font("Arial", Font.PLAIN, 11));
		rows = 1 + h / 25;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < w / 25; j++) {
				graphics.drawString("" + (i + j), j * 25, 25 * i);
			}
		}
		graphics.dispose();
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(1200, 800);
	}
}