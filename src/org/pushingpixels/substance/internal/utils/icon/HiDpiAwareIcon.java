package org.pushingpixels.substance.internal.utils.icon;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.Icon;

import org.pushingpixels.substance.internal.contrib.intellij.JBHiDPIScaledImage;
import org.pushingpixels.substance.internal.contrib.intellij.UIUtil;

public class HiDpiAwareIcon implements Icon, IsHiDpiAware {
	private final int factor;
	private final boolean isHiDpiAwareSource;
	
	private BufferedImage imageSource;
	private Icon iconSource;
	
	public HiDpiAwareIcon(BufferedImage image) {
		this.imageSource = image;

		factor = UIUtil.isRetina() ? 2 : 1;
		isHiDpiAwareSource = image instanceof JBHiDPIScaledImage;
	}
	
	public HiDpiAwareIcon(Icon icon) {
		this.iconSource = icon;

		factor = UIUtil.isRetina() ? 2 : 1;
		isHiDpiAwareSource = (icon instanceof IsHiDpiAware) && 
				((IsHiDpiAware) icon).isHiDpiAware();
	}
	
	@Override
	public boolean isHiDpiAware() {
		return this.isHiDpiAwareSource;
	}

	@Override
	public synchronized void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.translate(x, y);
		if (this.isHiDpiAwareSource) {
			g2d.scale(1.0f / this.factor, 1.0f / this.factor);
		}
		if (this.imageSource != null) {
			g2d.drawImage(this.imageSource, 0, 0, null);
		} else if (this.iconSource != null) {
			this.iconSource.paintIcon(c, g2d, 0, 0);
		}
		g2d.dispose();
	}
	
	@Override
	public int getIconWidth() {
		if (this.imageSource != null) {
			return this.imageSource.getWidth() / this.factor;
		}
		if (this.iconSource != null) {
			return this.iconSource.getIconWidth() / this.factor;
		}
		return 0;
	}
	
	@Override
	public int getIconHeight() {
		if (this.imageSource != null) {
			return this.imageSource.getHeight() / this.factor;
		}
		if (this.iconSource != null) {
			return this.iconSource.getIconHeight() / this.factor;
		}
		return 0;
	}
	
	public int getFactor() {
		return this.factor;
	}
}
