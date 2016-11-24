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
package org.pushingpixels.substance.internal.utils.border;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;

import javax.swing.border.Border;
import javax.swing.plaf.UIResource;

import org.pushingpixels.lafwidget.contrib.intellij.UIUtil;
import org.pushingpixels.substance.api.ComponentState;
import org.pushingpixels.substance.api.SubstanceColorScheme;
import org.pushingpixels.substance.internal.utils.SubstanceColorSchemeUtilities;
import org.pushingpixels.substance.internal.utils.SubstanceColorUtilities;
import org.pushingpixels.substance.internal.utils.SubstanceSizeUtils;

public class SubstancePopupMenuBorder implements Border, UIResource {
	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		boolean isRetina = UIUtil.isRetina();
		SubstanceColorScheme fillScheme = SubstanceColorSchemeUtilities
				.getColorScheme(c, ComponentState.ENABLED);

		Graphics2D g2d = (Graphics2D) g.create();
		int componentFontSize = SubstanceSizeUtils.getComponentFontSize(c);
		float borderThickness = SubstanceSizeUtils.getBorderStrokeWidth(componentFontSize);
		float borderDelta = borderThickness / 2.0f;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
				RenderingHints.VALUE_STROKE_PURE);
		g2d.setStroke(new BasicStroke(borderThickness, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND));
		g2d.translate(x, y);
		Color outline1 = SubstanceColorUtilities.getInterpolatedColor(
				fillScheme.getUltraDarkColor(), fillScheme.getDarkColor(), 0.5f);
		g2d.setColor(outline1);
		g2d.draw(new Rectangle2D.Float(borderDelta, borderDelta, width - borderThickness,
				height - borderThickness));
		if (isRetina) {
			Color outline2 = SubstanceColorUtilities.getInterpolatedColor(
					outline1, fillScheme.getMidColor(), 0.7f);
			g2d.setColor(outline2);
			g2d.draw(new Rectangle2D.Float(borderDelta + borderThickness, 
					borderDelta + borderThickness, 
					width - 3 * borderThickness, 
					height - 3 * borderThickness));
		}
	}

	@Override
	public Insets getBorderInsets(Component c) {
		return new Insets(1, 1, 1, 1);
	}

	@Override
	public boolean isBorderOpaque() {
		return true;
	}

}
