package test;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import org.pushingpixels.substance.api.SubstanceColorScheme;

import test.check.substance;

public class SubstanceLogo implements Icon {
	private SubstanceLogo() {
	}

	@Override
	public int getIconHeight() {
		return 16;
	}

	@Override
	public int getIconWidth() {
		return 16;
	}

	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.translate(x, y);

		double coef1 = getIconWidth() / (double) substance.getOrigWidth();
		double coef2 = getIconHeight() / (double) substance.getOrigHeight();
		double coef = Math.min(coef1, coef2);
		g2d.scale(coef, coef);
		g2d.translate(substance.getOrigX(), substance.getOrigY());
		substance.paint(g2d);
		g2d.dispose();
	}

	public static Icon getLogoIcon(SubstanceColorScheme scheme) {
		return new ImageIcon(getLogoImage(scheme));
	}

	public static BufferedImage getLogoImage(SubstanceColorScheme scheme) {
		SubstanceLogo origLogo = new SubstanceLogo();

		GraphicsEnvironment e = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		GraphicsDevice d = e.getDefaultScreenDevice();
		GraphicsConfiguration c = d.getDefaultConfiguration();
		BufferedImage result = c.createCompatibleImage(origLogo.getIconWidth(),
				origLogo.getIconHeight(), Transparency.TRANSLUCENT);

		origLogo.paintIcon(null, result.getGraphics(), 0, 0);

		Color newFg = scheme.getForegroundColor();
		for (int i = 0; i < result.getWidth(); i++) {
			for (int j = 0; j < result.getHeight(); j++) {
				int argb = result.getRGB(i, j);
				int transp = (argb >>> 24) & 0xFF;

				result.setRGB(i, j, (transp << 24) | (newFg.getRed() << 16)
						| (newFg.getGreen() << 8) | newFg.getBlue());
			}
		}
		return result;
	}
}
