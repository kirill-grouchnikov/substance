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
package org.pushingpixels.substance.internal.painter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.EnumSet;
import java.util.Set;

import javax.swing.CellRendererPane;

import org.pushingpixels.lafwidget.LafWidgetUtilities;
import org.pushingpixels.substance.api.SubstanceColorScheme;
import org.pushingpixels.substance.api.SubstanceConstants.Side;
import org.pushingpixels.substance.api.painter.border.SubstanceBorderPainter;
import org.pushingpixels.substance.api.painter.highlight.SubstanceHighlightPainter;
import org.pushingpixels.substance.internal.utils.*;

/**
 * Contains utility methods related to highlight painters. This class is for
 * internal use only.
 * 
 * @author Kirill Grouchnikov
 */
public class HighlightPainterUtils {
	/**
	 * Cache for small objects.
	 */
	protected final static LazyResettableHashMap<BufferedImage> smallCache = new LazyResettableHashMap<BufferedImage>(
			"SubstanceHighlightUtils");

	/**
	 * Paints the highlight for the specified component.
	 * 
	 * @param g
	 *            Graphic context.
	 * @param rendererPane
	 *            Renderer pane. Can be <code>null</code>.
	 * @param c
	 *            Component.
	 * @param rect
	 *            Rectangle to highlight.
	 * @param borderAlpha
	 *            Border alpha.
	 * @param openSides
	 *            The sides specified in this set will not be painted. Can be
	 *            <code>null</code> or empty.
	 * @param fillScheme
	 *            The fill color scheme.
	 * @param borderScheme
	 *            The border color scheme.
	 */
	public static void paintHighlight(Graphics g,
			CellRendererPane rendererPane, Component c, Rectangle rect,
			float borderAlpha, Set<Side> openSides,
			SubstanceColorScheme fillScheme, SubstanceColorScheme borderScheme) {
		// fix for bug 65
		if ((rect.width <= 0) || (rect.height <= 0))
			return;

		Component compForQuerying = (rendererPane != null) ? rendererPane : c;
		SubstanceHighlightPainter highlightPainter = SubstanceCoreUtilities
				.getSkin(compForQuerying).getHighlightPainter();
		SubstanceBorderPainter highlightBorderPainter = SubstanceCoreUtilities
				.getHighlightBorderPainter(compForQuerying);
		Graphics2D g2d = (Graphics2D) g.create(rect.x, rect.y, rect.width,
				rect.height);

		if (openSides == null) {
			openSides = EnumSet.noneOf(Side.class);
		}
		if (rect.width * rect.height < 100000) {
			String openKey = "";
			for (Side oSide : openSides) {
				openKey += oSide.name() + "-";
			}

			HashMapKey key = SubstanceCoreUtilities.getHashKey(highlightPainter
					.getDisplayName(), highlightBorderPainter.getDisplayName(),
					rect.width, rect.height, fillScheme.getDisplayName(),
					borderScheme.getDisplayName(), borderAlpha, openKey);
			BufferedImage result = smallCache.get(key);
			if (result == null) {
				result = createHighlighterImage(c, rect, borderAlpha,
						openSides, fillScheme, borderScheme, highlightPainter,
						highlightBorderPainter);
				smallCache.put(key, result);
			}
			g2d.drawImage(result, 0, 0, null);
		}
	}

	private static BufferedImage createHighlighterImage(Component c,
			Rectangle rect, float borderAlpha, Set<Side> openSides,
			SubstanceColorScheme currScheme,
			SubstanceColorScheme currBorderScheme,
			SubstanceHighlightPainter highlightPainter,
			SubstanceBorderPainter highlightBorderPainter) {
		BufferedImage result = SubstanceCoreUtilities.getBlankImage(rect.width,
				rect.height);
		Graphics2D resGraphics = result.createGraphics();
		highlightPainter.paintHighlight(resGraphics, c, rect.width,
				rect.height, currScheme);
		paintHighlightBorder(resGraphics, c, rect.width, rect.height,
				borderAlpha, openSides, highlightBorderPainter,
				currBorderScheme);
		resGraphics.dispose();
		return result;
	}

	/**
	 * Paints the highlight border for the specified component.
	 * 
	 * @param graphics
	 *            Graphic context.
	 * @param comp
	 *            Component.
	 * @param width
	 *            Border width.
	 * @param height
	 *            Border width.
	 * @param borderAlpha
	 *            Border alpha.
	 * @param openSides
	 *            The sides specified in this set will not be painted. Can be
	 *            <code>null</code> or empty.
	 * @param highlightBorderPainter
	 *            Border painter for the highlights.
	 * @param borderColorScheme
	 *            The border color scheme.
	 */
	private static void paintHighlightBorder(Graphics2D graphics,
			Component comp, int width, int height, float borderAlpha,
			Set<Side> openSides, SubstanceBorderPainter highlightBorderPainter,
			SubstanceColorScheme borderColorScheme) {
		if (borderAlpha <= 0.0f)
			return;

		int openDelta = 3 + (int) (Math.ceil(3.0 * SubstanceSizeUtils
				.getBorderStrokeWidth(SubstanceSizeUtils
						.getComponentFontSize(comp))));
		int deltaLeft = openSides.contains(Side.LEFT) ? openDelta : 0;
		int deltaRight = openSides.contains(Side.RIGHT) ? openDelta : 0;
		int deltaTop = openSides.contains(Side.TOP) ? openDelta : 0;
		int deltaBottom = openSides.contains(Side.BOTTOM) ? openDelta : 0;

		int borderDelta = (int) Math.floor(SubstanceSizeUtils
				.getBorderStrokeWidth(SubstanceSizeUtils
						.getComponentFontSize(comp)) / 2.0);
		Shape contour = new Rectangle(borderDelta, borderDelta, width
				+ deltaLeft + deltaRight - 2 * borderDelta - 1, height
				+ deltaTop + deltaBottom - 2 * borderDelta - 1);

		Graphics2D g2d = (Graphics2D) graphics.create();
		g2d.translate(-deltaLeft, -deltaTop);
		g2d.setComposite(LafWidgetUtilities.getAlphaComposite(null,
				borderAlpha, graphics));
		int borderThickness = (int) SubstanceSizeUtils
				.getBorderStrokeWidth(SubstanceSizeUtils
						.getComponentFontSize(comp));
		Shape contourInner = new Rectangle(borderDelta + borderThickness,
				borderDelta + borderThickness, width + deltaLeft + deltaRight
						- 2 * borderDelta - 2 * borderThickness - 1, height
						+ deltaTop + deltaBottom - 2 * borderDelta - 2
						* borderThickness - 1);

		highlightBorderPainter.paintBorder(g2d, comp, width + deltaLeft
				+ deltaRight, height + deltaTop + deltaBottom, contour,
				contourInner, borderColorScheme);
		g2d.dispose();
	}

	/**
	 * Returns the memory usage string.
	 * 
	 * @return Memory usage string.
	 */
	public static String getMemoryUsage() {
		StringBuffer sb = new StringBuffer();
		sb.append("SubstanceHighlightUtils: \n");
		sb.append("\t" + smallCache.size() + " smalls");
		return sb.toString();
	}
}
