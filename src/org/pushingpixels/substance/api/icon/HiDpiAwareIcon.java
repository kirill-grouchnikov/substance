/*
 * Copyright (c) 2005-2016 Substance Kirill Grouchnikov. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  o Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  o Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 *  o Neither the name of Substance Kirill Grouchnikov nor the names of
 *    its contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.pushingpixels.substance.api.icon;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.Icon;

import org.pushingpixels.substance.internal.contrib.intellij.JBHiDPIScaledImage;
import org.pushingpixels.substance.internal.contrib.intellij.UIUtil;
import org.pushingpixels.substance.internal.utils.SubstanceCoreUtilities;
import org.pushingpixels.substance.internal.utils.filters.ColorFilter;

public class HiDpiAwareIcon implements Icon, IsHiDpiAware {
	private final int factor;
	private final boolean isHiDpiAwareSource;
	
	private BufferedImage imageSource;
	private Icon iconSource;
	
	private int width;
	private int height;
	
	public HiDpiAwareIcon(BufferedImage image) {
		this.imageSource = image;
		this.factor = UIUtil.isRetina() ? 2 : 1;
		this.isHiDpiAwareSource = image instanceof JBHiDPIScaledImage;
		this.width = getInternalWidth();
		this.height = getInternalHeight();
	}
	
	public HiDpiAwareIcon(Icon icon) {
		this.iconSource = icon;
		this.factor = UIUtil.isRetina() ? 2 : 1;
		this.isHiDpiAwareSource = (icon instanceof IsHiDpiAware) && 
				((IsHiDpiAware) icon).isHiDpiAware();
		this.width = getInternalWidth();
		this.height = getInternalHeight();
	}
	
	public void setDimension(int width, int height) {
		if ((getIconWidth() > width) || (getIconHeight() > height)) {
			throw new IllegalArgumentException();
		}
		this.width = width;
		this.height = height;
	}
	
	@Override
	public boolean isHiDpiAware() {
		return this.isHiDpiAwareSource;
	}

	@Override
	public synchronized void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g2d = (Graphics2D) g.create();
		int dx = (this.width - this.getInternalWidth()) / 2;
		int dy = (this.height - this.getInternalHeight()) / 2;
//		g2d.setColor(Color.green);
//		g2d.fillRect(x, y, this.width, this.height);
		g2d.translate(x + dx, y + dy);
		if (this.imageSource != null) {
			g2d.drawImage(this.imageSource, 0, 0, this.imageSource.getWidth() / this.factor,
					this.imageSource.getHeight() / this.factor, null);
		} else if (this.iconSource != null) {
			this.iconSource.paintIcon(c, g2d, 0, 0);
		}
		g2d.dispose();
	}
	
	private int getInternalWidth() {
		if (this.imageSource != null) {
			return this.imageSource.getWidth() / this.factor;
		}
		if (this.iconSource != null) {
			return this.iconSource.getIconWidth() / (this.isHiDpiAwareSource ? 1 : this.factor);
		}
		return 0;
	}
	
	@Override
	public int getIconWidth() {
		return this.width;
	}
	
	private int getInternalHeight() {
		if (this.imageSource != null) {
			return this.imageSource.getHeight() / this.factor;
		}
		if (this.iconSource != null) {
			return this.iconSource.getIconHeight() / (this.isHiDpiAwareSource ? 1 : this.factor);
		}
		return 0;
	}
	
	@Override
	public int getIconHeight() {
		return this.height;
	}
	
	public int getFactor() {
		return this.factor;
	}
	
	public BufferedImage toImage() {
		BufferedImage result = SubstanceCoreUtilities.getBlankImage(
				this.getIconWidth(), this.getIconHeight());
		this.paintIcon(null, result.getGraphics(), 0, 0);
		return result;
	}
	
	public HiDpiAwareIcon colorize(Color color) {
		return new HiDpiAwareIcon(new ColorFilter(color).filter(this.toImage(), null));
	}
}
