package test;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.pushingpixels.substance.api.skin.SubstanceBusinessLookAndFeel;

public class Fullscreen {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			try {
				UIManager.setLookAndFeel(new SubstanceBusinessLookAndFeel());
			} catch (Throwable t) {
			}
			GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
					.getDefaultScreenDevice();
			if (!gd.isFullScreenSupported()) {
				return;
			}
			final JFrame frame = new JFrame();
			frame.setBackground(Color.red);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.add(new JButton("sample"));
			frame.setLayout(new FlowLayout());
			frame.pack();
			// Trying to fix bug
			//frame.setUndecorated(false);
			gd.setFullScreenWindow(frame);
			frame.setVisible(true);
		});
	}
}
