/*
 * Copyright (c) 2005-2010 Substance Kirill Grouchnikov. All Rights Reserved.
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
package org.pushingpixels.substance.api.painter.overlay;

import java.awt.*;

import javax.swing.SwingUtilities;

import org.pushingpixels.substance.api.*;
import org.pushingpixels.substance.api.painter.SubstancePainterUtils;
import org.pushingpixels.substance.internal.utils.SubstanceSizeUtils;

/**
 * Overlay painter that paints a bezel line at the top edge of the relevant
 * decoration area. This class is part of officially supported API.
 * 
 * @author Kirill Grouchnikov
 * @since version 5.3
 */
public final class TopBezelOverlayPainter implements SubstanceOverlayPainter {
	/**
	 * Used to compute the color of the top line painted by this overlay
	 * painter.
	 */
	ColorSchemeSingleColorQuery colorSchemeQueryTop;

	/**
	 * Used to compute the color of the bottom line painted by this overlay
	 * painter.
	 */
	ColorSchemeSingleColorQuery colorSchemeQueryBottom;

	/**
	 * Creates a new overlay painter that paints a bezel line at the top edge of
	 * the relevant decoration area
	 * 
	 * @param colorSchemeQueryTop
	 *            Used to compute the color of the top line painted by this
	 *            overlay painter.
	 * @param colorSchemeQueryBottom
	 *            Used to compute the color of the top line painted by this
	 *            overlay painter.
	 */
	public TopBezelOverlayPainter(
			ColorSchemeSingleColorQuery colorSchemeQueryTop,
			ColorSchemeSingleColorQuery colorSchemeQueryBottom) {
		this.colorSchemeQueryTop = colorSchemeQueryTop;
		this.colorSchemeQueryBottom = colorSchemeQueryBottom;
	}

	@Override
	public void paintOverlay(Graphics2D graphics, Component comp,
			DecorationAreaType decorationAreaType, int width, int height,
			SubstanceSkin skin) {
		Component topMostWithSameDecorationAreaType = SubstancePainterUtils
				.getTopMostParentWithDecorationAreaType(comp,
						decorationAreaType);

		Point inTopMost = SwingUtilities.convertPoint(comp, new Point(0, 0),
				topMostWithSameDecorationAreaType);
		int dy = inTopMost.y;

		int fontSize = SubstanceSizeUtils.getComponentFontSize(comp);
		float borderStrokeWidth = SubstanceSizeUtils
				.getBorderStrokeWidth(fontSize);
		graphics.setStroke(new BasicStroke(borderStrokeWidth));

		SubstanceColorScheme colorScheme = skin
				.getBackgroundColorScheme(decorationAreaType);
		// skin.getColorScheme(comp,
		// ColorSchemeAssociationKind.SEPARATOR, ComponentState.DEFAULT);
		graphics.setColor(this.colorSchemeQueryTop.query(colorScheme));
		graphics.drawLine(0, (int) (borderStrokeWidth) - dy - 1, width,
				(int) (borderStrokeWidth) - dy - 1);

		graphics.setColor(this.colorSchemeQueryBottom.query(colorScheme));
		graphics.drawLine(0, (int) (2 * borderStrokeWidth) - dy - 1, width,
				(int) (2 * borderStrokeWidth) - dy - 1);
	}

	@Override
	public String getDisplayName() {
		return "Top Line Shadow";
	}

}
