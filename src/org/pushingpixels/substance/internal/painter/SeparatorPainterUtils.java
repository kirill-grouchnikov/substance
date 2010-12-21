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
import java.util.Collection;

import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import org.pushingpixels.substance.api.*;
import org.pushingpixels.substance.internal.utils.*;

/**
 * Contains utility methods related to painting separators. This class is for
 * internal use only.
 * 
 * @author Kirill Grouchnikov
 */
public class SeparatorPainterUtils {
	/**
	 * Cached images of separators.
	 */
	private static LazyResettableHashMap<BufferedImage> cached = new LazyResettableHashMap<BufferedImage>(
			"SeparatorPainterUtils");

	/**
	 * Paints a separator.
	 * 
	 * @param c
	 *            Component.
	 * @param graphics
	 *            Graphics context.
	 * @param width
	 *            Separator width.
	 * @param height
	 *            Separator height.
	 * @param orientation
	 *            Separator orientation.
	 */
	public static void paintSeparator(Component c, Graphics graphics,
			int width, int height, int orientation) {
		paintSeparator(c, graphics, width, height, orientation, true, 10);
	}

	/**
	 * Paints a separator.
	 * 
	 * @param c
	 *            Component.
	 * @param graphics
	 *            Graphics context.
	 * @param scheme
	 *            Color scheme.
	 * @param width
	 *            Separator width.
	 * @param height
	 *            Separator height.
	 * @param orientation
	 *            Separator orientation.
	 * @param hasShadow
	 *            If <code>true</code>, the separator painting will have shadow.
	 * @param maxGradLength
	 *            Specifies the maximum pixel length of "ramp" portions of the
	 *            separator. The ramp portions are located on separator ends and
	 *            allow providing a faded appearance on those ends.
	 */
	public static void paintSeparator(Component c, Graphics graphics,
			int width, int height, int orientation, boolean hasShadow,
			int maxGradLength) {
		paintSeparator(c, graphics, width, height, orientation, hasShadow,
				maxGradLength, maxGradLength, false);
	}

	/**
	 * Paints a separator.
	 * 
	 * @param c
	 *            Component.
	 * @param g
	 *            Graphics context.
	 * @param width
	 *            Separator width.
	 * @param height
	 *            Separator height.
	 * @param orientation
	 *            Separator orientation.
	 * @param hasShadow
	 *            If <code>true</code>, the separator painting will have shadow.
	 * @param maxGradLengthStart
	 *            Specifies the maximum pixel length of the starting "ramp"
	 *            portion of the separator. The starting ramp portion is located
	 *            on top / left separator end and allows providing a faded
	 *            appearance on that end.
	 * @param maxGradLengthEnd
	 *            Specifies the maximum pixel length of the ending "ramp"
	 *            portion of the separator. The ending ramp portion is located
	 *            on bottom / right separator end and allows providing a faded
	 *            appearance on that end.
	 * @param toEnforceAlphaColors
	 *            If <code>true</code>, the fade sequences will always use alpha
	 *            colors. This may affect the performance.
	 */
	public static void paintSeparator(Component c, Graphics g, int width,
			int height, int orientation, boolean hasShadow,
			int maxGradLengthStart, int maxGradLengthEnd,
			boolean toEnforceAlphaColors) {
		SubstanceColorScheme compScheme = SubstanceColorSchemeUtilities
				.getColorScheme(c, ColorSchemeAssociationKind.SEPARATOR,
						ComponentState.ENABLED);
		paintSeparator(c, g, compScheme, width, height, orientation, hasShadow,
				maxGradLengthStart, maxGradLengthEnd, toEnforceAlphaColors);
	}

	/**
	 * Paints a separator.
	 * 
	 * @param c
	 *            Component.
	 * @param g
	 *            Graphics context.
	 * @param scheme
	 *            Color scheme.
	 * @param width
	 *            Separator width.
	 * @param height
	 *            Separator height.
	 * @param orientation
	 *            Separator orientation.
	 * @param hasShadow
	 *            If <code>true</code>, the separator painting will have shadow.
	 * @param maxGradLengthStart
	 *            Specifies the maximum pixel length of the starting "ramp"
	 *            portion of the separator. The starting ramp portion is located
	 *            on top / left separator end and allows providing a faded
	 *            appearance on that end.
	 * @param maxGradLengthEnd
	 *            Specifies the maximum pixel length of the ending "ramp"
	 *            portion of the separator. The ending ramp portion is located
	 *            on bottom / right separator end and allows providing a faded
	 *            appearance on that end.
	 * @param toEnforceAlphaColors
	 *            If <code>true</code>, the fade sequences will always use alpha
	 *            colors. This may affect the performance.
	 */
	public static void paintSeparator(Component c, Graphics g,
			SubstanceColorScheme scheme, int width, int height,
			int orientation, boolean hasShadow, int maxGradLengthStart,
			int maxGradLengthEnd, boolean toEnforceAlphaColors) {

		DecorationAreaType decorationAreaType = SubstanceLookAndFeel
				.getDecorationType(c);
		SubstanceSkin skin = SubstanceCoreUtilities.getSkin(c);
		// use alpha colors when the control is in a painted decoration area
		// (where skin can use different background colors) or in a decoration
		// area that has overlays.
		boolean toUseAlphaColors = ((decorationAreaType == null) || (decorationAreaType == DecorationAreaType.NONE)) ? false
				: skin.isRegisteredAsDecorationArea(decorationAreaType)
						|| (skin.getOverlayPainters(decorationAreaType).size() > 0);
		toUseAlphaColors = toUseAlphaColors || toEnforceAlphaColors;

		Color backgrFill = SubstanceColorUtilities.getBackgroundFillColor(c);
		int fontSize = SubstanceSizeUtils.getComponentFontSize(c);
		float borderStrokeWidth = SubstanceSizeUtils
				.getBorderStrokeWidth(fontSize);
		if ((orientation == JSeparator.HORIZONTAL) && (height == 0)) {
			height = (int) Math.ceil(2.0 * borderStrokeWidth);
		}
		if ((orientation == JSeparator.VERTICAL) && (width == 0)) {
			width = (int) Math.ceil(2.0 * borderStrokeWidth);
		}

		if ((width == 0) || (height == 0))
			return;

		HashMapKey key = SubstanceCoreUtilities.getHashKey(fontSize, scheme
				.getDisplayName(), width, height, orientation, hasShadow,
				maxGradLengthStart, maxGradLengthEnd, toUseAlphaColors,
				backgrFill.getRGB());

		BufferedImage singleLine = cached.get(key);
		if (singleLine == null) {
			singleLine = SubstanceCoreUtilities.getBlankImage(width, height);
			Graphics2D graphics = singleLine.createGraphics();

			Color foreLight = getSeparatorLightColor(scheme);
			Color foreDark = getSeparatorDarkColor(scheme);
			Color back = getSeparatorShadowColor(scheme);

			Color foreLight12 = toUseAlphaColors ? SubstanceColorUtilities
					.getAlphaColor(foreLight, 32) : SubstanceColorUtilities
					.getInterpolatedColor(foreLight, backgrFill, 0.12);
			Color foreDark95 = toUseAlphaColors ? SubstanceColorUtilities
					.getAlphaColor(foreDark, 240) : SubstanceColorUtilities
					.getInterpolatedColor(foreDark, backgrFill, 0.95);
			Color back12 = toUseAlphaColors ? SubstanceColorUtilities
					.getAlphaColor(back, 32) : SubstanceColorUtilities
					.getInterpolatedColor(back, backgrFill, 0.12);
			Color back95 = toUseAlphaColors ? SubstanceColorUtilities
					.getAlphaColor(back, 240) : SubstanceColorUtilities
					.getInterpolatedColor(back, backgrFill, 0.95);
			graphics.setStroke(new BasicStroke(borderStrokeWidth,
					BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND));
			if (orientation == JSeparator.VERTICAL) {
				int gradStart = Math.min(maxGradLengthStart, height / 2);
				int gradEnd = Math.min(maxGradLengthEnd, height / 2);
				graphics.translate(Math.max(0, width / 2 - 1), 0);
				graphics.setPaint(new GradientPaint(0, 0, foreLight12, 0,
						gradStart, foreDark95));
				graphics.drawLine(0, 0, 0, gradStart);
				graphics.setColor(foreDark95);
				graphics.drawLine(0, gradStart, 0, height - gradEnd);
				graphics.setPaint(new GradientPaint(0, height - gradEnd,
						foreDark95, 0, height, foreLight12));
				graphics.drawLine(0, height - gradEnd, 0, height);

				if (hasShadow) {
					int offset = (int) borderStrokeWidth;
					graphics.setPaint(new GradientPaint(offset, 0, back12,
							offset, gradStart, back95));
					graphics.drawLine(offset, 0, offset, gradStart);
					graphics.setColor(back95);
					graphics.drawLine(offset, gradStart, offset, height
							- gradEnd);
					graphics.setPaint(new GradientPaint(offset, height
							- gradEnd, back95, offset, height, back12));
					graphics.drawLine(offset, height - gradEnd, offset, height);
				}
			} else {
				// HORIZONTAL
				int gradStart = Math.min(maxGradLengthStart, width / 2);
				int gradEnd = Math.min(maxGradLengthEnd, width / 2);
				graphics.translate(0, Math.max(0, height / 2 - 1));
				graphics.setPaint(new GradientPaint(0, 0, foreLight12,
						gradStart, 0, foreDark95));
				graphics.drawLine(0, 0, gradStart, 0);
				graphics.setColor(foreDark95);
				graphics.drawLine(gradStart, 0, width - gradEnd, 0);
				graphics.setPaint(new GradientPaint(width - gradEnd, 0,
						foreDark95, width, 0, foreLight12));
				graphics.drawLine(width - gradEnd, 0, width, 0);

				if (hasShadow) {
					int offset = (int) borderStrokeWidth;
					graphics.setPaint(new GradientPaint(0, offset, back12,
							gradStart, offset, back95));
					graphics.drawLine(0, offset, gradStart, offset);
					graphics.setColor(back95);
					graphics.drawLine(gradStart, offset, width - gradEnd,
							offset);
					graphics.setPaint(new GradientPaint(width - gradEnd,
							offset, back95, width, offset, back12));
					graphics.drawLine(width - gradEnd, offset, width, offset);
				}
			}
			graphics.dispose();
			cached.put(key, singleLine);
		}
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.drawImage(singleLine, 0, 0, null);
		g2d.dispose();
	}

	public static Color getSeparatorShadowColor(SubstanceColorScheme scheme) {
		return scheme.isDark() ? scheme.getDarkColor() : scheme
				.getUltraLightColor();
	}

	public static Color getSeparatorDarkColor(SubstanceColorScheme scheme) {
		return scheme.isDark() ? scheme.getExtraLightColor()
				: SubstanceColorUtilities.getInterpolatedColor(scheme
						.getMidColor(), scheme.getDarkColor(), 0.4);
	}

	public static Color getSeparatorLightColor(SubstanceColorScheme scheme) {
		return scheme.isDark() ? scheme.getLightColor()
				: SubstanceColorUtilities.getInterpolatedColor(scheme
						.getLightColor(), scheme.getDarkColor(), 0.8);
	}

	/**
	 * Paints vertical separator lines.
	 * 
	 * @param g
	 *            Graphics context.
	 * @param c
	 *            Component.
	 * @param scheme
	 *            Color scheme for painting the vertical separator lines.
	 * @param y
	 *            The top Y coordinate of the lines.
	 * @param x
	 *            The X coordinates of the lines.
	 * @param height
	 *            The height of the lines.
	 * @param fadeStartFraction
	 *            The start fraction of the fade out sequence.
	 */
	public static void paintVerticalLines(Graphics g, Component c,
			SubstanceColorScheme scheme, int y, Collection<Integer> x,
			int height, float fadeStartFraction) {
		int componentFontSize = SubstanceSizeUtils.getComponentFontSize(c);
		Color backgrFill = SubstanceColorUtilities.getBackgroundFillColor(c);

		HashMapKey key = SubstanceCoreUtilities.getHashKey(componentFontSize,
				scheme.getDisplayName(), 0, height, SwingConstants.VERTICAL,
				true, 0.0, fadeStartFraction, backgrFill.getRGB());

		float borderStrokeWidth = SubstanceSizeUtils
				.getBorderStrokeWidth(componentFontSize);
		int offset = (int) borderStrokeWidth;
		BufferedImage singleLine = cached.get(key);
		if (singleLine == null) {
			singleLine = SubstanceCoreUtilities.getBlankImage(Math.max(2,
					(int) Math.ceil(2.0 * borderStrokeWidth)), height);
			Graphics2D graphics = singleLine.createGraphics();

			Color foreLight = getSeparatorLightColor(scheme);
			Color foreDark = getSeparatorDarkColor(scheme);
			Color back = getSeparatorShadowColor(scheme);

			graphics.setStroke(new BasicStroke(borderStrokeWidth,
					BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND));
			Color foreLight12 = SubstanceColorUtilities.getInterpolatedColor(
					foreLight, backgrFill, 0.12);
			Color foreDark95 = SubstanceColorUtilities.getInterpolatedColor(
					foreDark, backgrFill, 0.95);
			Color back12 = SubstanceColorUtilities.getInterpolatedColor(back,
					backgrFill, 0.12);
			Color back95 = SubstanceColorUtilities.getInterpolatedColor(back,
					backgrFill, 0.95);

			LinearGradientPaint forePaint = new LinearGradientPaint(0, 0, 0,
					height, new float[] { 0.0f, fadeStartFraction, 1.0f },
					new Color[] { foreDark95, foreDark95, foreLight12 });
			graphics.setPaint(forePaint);
			graphics.translate(borderStrokeWidth / 2, 0);
			graphics.drawLine(0, 0, 0, height);

			LinearGradientPaint backPaint = new LinearGradientPaint(0, 0, 0,
					height, new float[] { 0.0f, fadeStartFraction, 1.0f },
					new Color[] { back95, back95, back12 });
			graphics.setPaint(backPaint);
			graphics.drawLine(offset, 0, offset, height);

			graphics.dispose();
			cached.put(key, singleLine);
		}

		Graphics2D g2d = (Graphics2D) g.create();
		for (int lineX : x) {
			g2d.drawImage(singleLine, lineX - offset / 2, y, null);
		}
		g2d.dispose();
	}

	/**
	 * Paints horizontal separator lines.
	 * 
	 * @param g
	 *            Graphics context.
	 * @param c
	 *            Component.
	 * @param scheme
	 *            Color scheme for painting the horizontal separator lines.
	 * @param x
	 *            The left X coordinate of the lines.
	 * @param y
	 *            The Y coordinates of the lines.
	 * @param width
	 *            The width of the lines.
	 * @param fadeStartFraction
	 *            The start fraction of the fade out sequence.
	 * @param isLtr
	 *            If <code>true</code>, the lines are left-to-right and the fade
	 *            out is on the right side. Otherwise, the fade out is on the
	 *            left side.
	 */
	public static void paintHorizontalLines(Graphics g, Component c,
			SubstanceColorScheme scheme, int x, Collection<Integer> y,
			int width, float fadeStartFraction, boolean isLtr) {
		int componentFontSize = SubstanceSizeUtils.getComponentFontSize(c);
		Color backgrFill = SubstanceColorUtilities.getBackgroundFillColor(c);

		HashMapKey key = SubstanceCoreUtilities.getHashKey(componentFontSize,
				scheme.getDisplayName(), width, 0, SwingConstants.VERTICAL,
				true, 0.0, fadeStartFraction, isLtr, backgrFill.getRGB());

		float borderStrokeWidth = SubstanceSizeUtils
				.getBorderStrokeWidth(componentFontSize);
		int offset = (int) borderStrokeWidth;
		BufferedImage singleLine = cached.get(key);
		if (singleLine == null) {
			singleLine = SubstanceCoreUtilities.getBlankImage(width, Math.max(
					2, (int) Math.ceil(2.0 * borderStrokeWidth)));
			Graphics2D graphics = singleLine.createGraphics();

			Color foreLight = getSeparatorLightColor(scheme);
			Color foreDark = getSeparatorDarkColor(scheme);
			Color back = getSeparatorShadowColor(scheme);

			graphics.setStroke(new BasicStroke(borderStrokeWidth,
					BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND));
			Color foreLight12 = SubstanceColorUtilities.getInterpolatedColor(
					foreLight, backgrFill, 0.12);
			Color foreDark95 = SubstanceColorUtilities.getInterpolatedColor(
					foreDark, backgrFill, 0.95);
			Color back12 = SubstanceColorUtilities.getInterpolatedColor(back,
					backgrFill, 0.12);
			Color back95 = SubstanceColorUtilities.getInterpolatedColor(back,
					backgrFill, 0.95);

			LinearGradientPaint forePaint = new LinearGradientPaint(0, 0,
					width, 0, new float[] { 0.0f, fadeStartFraction, 1.0f },
					new Color[] { isLtr ? foreDark95 : foreLight12, foreDark95,
							isLtr ? foreLight12 : foreDark95 });
			graphics.setPaint(forePaint);
			graphics.drawLine(0, 0, width, 0);

			LinearGradientPaint backPaint = new LinearGradientPaint(0, 9,
					width, 0, new float[] { 0.0f, fadeStartFraction, 1.0f },
					new Color[] { isLtr ? back95 : back12, back95,
							isLtr ? back12 : back95 });
			graphics.setPaint(backPaint);
			graphics.drawLine(0, offset, width, offset);

			graphics.dispose();
			cached.put(key, singleLine);
		}

		Graphics2D g2d = (Graphics2D) g.create();
		for (int lineY : y) {
			g2d.drawImage(singleLine, x, lineY - offset / 2, null);
		}
		g2d.dispose();
	}
}
