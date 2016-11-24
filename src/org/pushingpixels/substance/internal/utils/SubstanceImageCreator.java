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
package org.pushingpixels.substance.internal.utils;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LinearGradientPaint;
import java.awt.MultipleGradientPaint.CycleMethod;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Set;

import javax.swing.AbstractButton;
import javax.swing.CellRendererPane;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.text.JTextComponent;

import org.pushingpixels.lafwidget.LafWidgetUtilities;
import org.pushingpixels.lafwidget.contrib.intellij.UIUtil;
import org.pushingpixels.lafwidget.icon.HiDpiAwareIcon;
import org.pushingpixels.lafwidget.icon.HiDpiAwareIconUiResource;
import org.pushingpixels.lafwidget.utils.ColorFilter;
import org.pushingpixels.substance.api.ComponentState;
import org.pushingpixels.substance.api.SubstanceColorScheme;
import org.pushingpixels.substance.api.SubstanceConstants.Side;
import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import org.pushingpixels.substance.api.painter.border.FlatBorderPainter;
import org.pushingpixels.substance.api.painter.border.SubstanceBorderPainter;
import org.pushingpixels.substance.api.painter.fill.SubstanceFillPainter;
import org.pushingpixels.substance.api.watermark.SubstanceWatermark;
import org.pushingpixels.substance.internal.colorscheme.ShiftColorScheme;
import org.pushingpixels.substance.internal.painter.SimplisticFillPainter;
import org.pushingpixels.substance.internal.svg.Locked;
import org.pushingpixels.substance.internal.svg.System_search;
import org.pushingpixels.substance.internal.utils.filters.ColorSchemeFilter;
import org.pushingpixels.substance.internal.utils.filters.GrayscaleFilter;
import org.pushingpixels.substance.internal.utils.filters.TranslucentFilter;

/**
 * Provides utility functions for creating various images for <b>Substance </b>
 * look and feel. This class is <b>for internal use only</b>.
 * 
 * @author Kirill Grouchnikov
 */
public final class SubstanceImageCreator {
	/**
	 * Custom fill painter for filling the checkmarks of checkboxes and radio
	 * buttons.
	 * 
	 * @author Kirill Grouchnikov
	 */
	public static class SimplisticSoftBorderReverseFillPainter extends
			SimplisticFillPainter {
		/**
		 * Singleton instance.
		 */
		public static final SubstanceFillPainter INSTANCE = new SimplisticSoftBorderReverseFillPainter();

		/**
		 * Private constructor.
		 */
		private SimplisticSoftBorderReverseFillPainter() {
		}

		@Override
		public String getDisplayName() {
			return "Simplistic Soft Border Reverse";
		}

		@Override
		public Color getTopFillColor(SubstanceColorScheme fillScheme) {
			return super.getBottomFillColor(fillScheme);
		}

		@Override
		public Color getBottomFillColor(SubstanceColorScheme fillScheme) {
			return super.getTopFillColor(fillScheme);
		}
	}

	/**
	 * Paints border instance of specified dimensions and status.
	 * 
	 * @param c
	 *            Component.
	 * @param graphics
	 *            Graphics context.
	 * @param x
	 *            Component left X (in graphics context).
	 * @param y
	 *            Component top Y (in graphics context).
	 * @param width
	 *            Border width.
	 * @param height
	 *            Border height.
	 * @param radius
	 *            Border radius.
	 * @param borderScheme1
	 *            First border color scheme.
	 * @param borderScheme2
	 *            Second border color scheme.
	 * @param cyclePos
	 *            Cycle position for interpolating the border color schemes.
	 */
	public static void paintBorder(Component c, Graphics2D graphics, int x,
			float y, float width, float height, float radius,
			SubstanceColorScheme borderScheme) {

		SubstanceBorderPainter borderPainter = SubstanceCoreUtilities
				.getBorderPainter(c);
		graphics.translate(x, y);
		int componentFontSize = SubstanceSizeUtils.getComponentFontSize(c);
		float borderDelta = SubstanceSizeUtils.getBorderStrokeWidth(componentFontSize) / 2.0f;
		Shape contour = SubstanceOutlineUtilities.getBaseOutline(width, height,
				radius, null, borderDelta);
		float borderThickness = SubstanceSizeUtils.getBorderStrokeWidth(componentFontSize);
		boolean skipInnerBorder = (c instanceof JTextComponent)
				|| ((SwingUtilities.getAncestorOfClass(CellRendererPane.class,
						c) != null) && (SwingUtilities.getAncestorOfClass(
						JFileChooser.class, c) != null));
		GeneralPath contourInner = skipInnerBorder ? null
				: SubstanceOutlineUtilities.getBaseOutline(width, height,
						radius - borderThickness, null, borderThickness + borderDelta);
		borderPainter.paintBorder(graphics, c, width, height, contour,
				contourInner, borderScheme);
		graphics.translate(-x, -y);
	}

	/**
	 * Paints border instance of specified dimensions and status.
	 * 
	 * @param c
	 *            Component.
	 * @param graphics
	 *            Graphics context.
	 * @param x
	 *            Component left X (in graphics context).
	 * @param y
	 *            Component top Y (in graphics context).
	 * @param width
	 *            Border width.
	 * @param height
	 *            Border height.
	 * @param radius
	 *            Border radius.
	 * @param borderScheme
	 *            Border color scheme.
	 */
	public static void paintTextComponentBorder(JComponent c,
			Graphics graphics, int x, int y, int width, int height,
			float radius, SubstanceColorScheme borderScheme) {

		int componentFontSize = SubstanceSizeUtils.getComponentFontSize(c);
		float borderDelta = SubstanceSizeUtils
				.getBorderStrokeWidth(componentFontSize) / 2.0f;
		Set<Side> sides = SubstanceCoreUtilities.getSides(c, 
				SubstanceLookAndFeel.BUTTON_SIDE_PROPERTY);
		Shape contour = SubstanceOutlineUtilities.getBaseOutline(
				width, height, radius, sides, borderDelta);
		float borderThickness = SubstanceSizeUtils.getBorderStrokeWidth(componentFontSize);
		GeneralPath contourInner = SubstanceOutlineUtilities.getBaseOutline(
				width, height, radius - borderThickness, sides, 
				borderThickness + borderDelta);

		Graphics2D g2d = (Graphics2D) graphics.create();
		g2d.translate(x, y);

		ComponentState stateForOuterBorder = c.isEnabled() ? ComponentState.ENABLED
				: ComponentState.DISABLED_UNSELECTED;
		Color lightColor = SubstanceColorUtilities.getDefaultBackgroundColor(c,
				stateForOuterBorder);

		if (stateForOuterBorder.isDisabled()) {
			float alpha = SubstanceColorSchemeUtilities.getAlpha(c,
					stateForOuterBorder);
			lightColor = SubstanceColorUtilities.getAlphaColor(lightColor,
					(int) (255 * alpha));
		}

		Color outerColor = SubstanceColorUtilities
				.getOuterTextComponentBorderColor(lightColor);
		float[] hsb = Color.RGBtoHSB(lightColor.getRed(),
				lightColor.getGreen(), lightColor.getBlue(), null);
		// hsb[1] = hsb[1] * 0.5f;
		// System.out.println(lightColor + " -> " + hsb[1] + ":" + hsb[2]);
		double bottomInnerBlend = 0.85;
		double topInnerBlend = 0.8;
		if (hsb[2] < 0.3f) {
			bottomInnerBlend = 0.6;
			topInnerBlend = 0.95;
		} else if (hsb[2] < 0.5f) {
			bottomInnerBlend = 0.8;
		} else if (hsb[2] < 0.75f) {
			bottomInnerBlend = 0.7;
		} else {
		}
		Color darkColor = borderScheme.getDarkColor();

		Color topInnerColor = SubstanceColorUtilities.getInterpolatedColor(
				darkColor, lightColor, topInnerBlend);
		Color bottomInnerColor = SubstanceColorUtilities.getInterpolatedColor(
				lightColor, darkColor, bottomInnerBlend);

		// darkColor = SubstanceColorUtilities.getAlphaColor(darkColor, 196);

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
				RenderingHints.VALUE_STROKE_PURE);

		float strokeWidth = SubstanceSizeUtils
				.getBorderStrokeWidth(SubstanceSizeUtils
						.getComponentFontSize(c));
		g2d.setStroke(new BasicStroke(strokeWidth, BasicStroke.CAP_BUTT,
				BasicStroke.JOIN_ROUND));

		g2d.setPaint(new GradientPaint(0, 0, outerColor, 0, height, outerColor));
		// g2d.setColor(Color.red);
		g2d.draw(contour);
		g2d.setPaint(new GradientPaint(0, 0, topInnerColor, 0, height,
				bottomInnerColor));
		// g2d.setColor(Color.green);
		g2d.draw(contourInner);

		// System.out.println("Outer : " + outerColor + "[" + lightColor
		// + "] from " + borderScheme.getDisplayName());

		g2d.dispose();
	}

	/**
	 * Retrieves check mark image.
	 * 
	 * @param button
	 *            Button for the check mark.
	 * @param dimension
	 *            Check mark dimension.
	 * @param isEnabled
	 *            Enabled status.
	 * @param scheme
	 *            Color scheme for the check mark.
	 * @param checkMarkVisibility
	 *            Checkmark visibility in 0.0-1.0 range.
	 * @return Check mark image.
	 */
	private static BufferedImage getCheckMark(int dimension, boolean isEnabled,
			SubstanceColorScheme scheme, float checkMarkVisibility) {
		BufferedImage result = SubstanceCoreUtilities.getBlankImage(dimension, dimension);

		// get graphics and set hints
		Graphics2D graphics = (Graphics2D) result.getGraphics();
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		// create curved checkbox path
		GeneralPath path = new GeneralPath();

		path.moveTo(0.25f * dimension, 0.5f * dimension);
		path.quadTo(0.37f * dimension, 0.6f * dimension, 0.47f * dimension, 0.8f * dimension);
		path.quadTo(0.55f * dimension, 0.5f * dimension, 0.85f * dimension, 0f);

		// compute the x-based clip for the visibility
		float xClipStart = 0.15f * dimension;
		float xClipEnd = 0.95f * dimension;
		float xClipRealEnd = xClipStart + (xClipEnd - xClipStart) * checkMarkVisibility;

		graphics.setClip(0, 0, (int) Math.ceil(xClipRealEnd), dimension);

		graphics.setColor(SubstanceColorUtilities.getMarkColor(scheme, isEnabled));
		Stroke stroke = new BasicStroke((float) 0.15 * dimension,
				BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
		graphics.setStroke(stroke);
		graphics.draw(path);

		return result;
	}

	/**
	 * Returns arrow icon for the specified parameters.
	 * 
	 * @param fontSize
	 *            Font size.
	 * @param direction
	 *            Arrow direction.
	 * @param colorScheme
	 *            Arrow icon color scheme.
	 * @return Arrow icon.
	 */
	public static HiDpiAwareIcon getArrowIcon(int fontSize, int direction,
			SubstanceColorScheme colorScheme) {
		float origWidth = SubstanceSizeUtils.getArrowIconWidth(fontSize);
		float origHeight = SubstanceSizeUtils.getArrowIconHeight(fontSize);
		float width = origWidth;
		float height = origHeight;
		if (direction == SwingConstants.CENTER)
			height *= 2;
		float strokeWidth = SubstanceSizeUtils.getArrowStrokeWidth(fontSize);
		HiDpiAwareIcon result = new HiDpiAwareIcon(getArrow(
				width, height, strokeWidth, direction, colorScheme));
		int finalWidth = (int) (Math.max(origWidth, origHeight) + 2);
		int finalHeight = (int) (Math.max(origWidth, height) + 2);
		result.setDimension(finalWidth, finalHeight);
		return result;
	}

	/**
	 * Retrieves arrow icon.
	 * 
	 * @param width
	 *            Arrow width.
	 * @param height
	 *            Arrow height.
	 * @param strokeWidth
	 *            Stroke width.
	 * @param direction
	 *            Arrow direction.
	 * @param scheme
	 *            Color scheme for the arrow.
	 * @return Arrow image.
	 * @see SwingConstants#NORTH
	 * @see SwingConstants#WEST
	 * @see SwingConstants#SOUTH
	 * @see SwingConstants#EAST
	 */
	public static HiDpiAwareIcon getArrowIcon(float width, float height,
			float strokeWidth, int direction, SubstanceColorScheme scheme) {
		return new HiDpiAwareIcon(getArrow(width, height, strokeWidth, direction,
				scheme));
	}

	/**
	 * Retrieves arrow image.
	 * 
	 * @param width
	 *            Arrow width.
	 * @param height
	 *            Arrow height.
	 * @param strokeWidth
	 *            Stroke width.
	 * @param direction
	 *            Arrow direction.
	 * @param scheme
	 *            Color scheme for the arrow.
	 * @return Arrow image.
	 * @see SwingConstants#NORTH
	 * @see SwingConstants#WEST
	 * @see SwingConstants#SOUTH
	 * @see SwingConstants#EAST
	 * @see SwingConstants#CENTER
	 */
	private static BufferedImage getArrow(float width, float height,
			float strokeWidth, int direction, SubstanceColorScheme scheme) {
		BufferedImage arrowImage = SubstanceCoreUtilities.getBlankImage(
				(int) width, (int) height);

		// System.out.println(width + ":" + height + ":" + strokeWidth);

		// get graphics and set hints
		Graphics2D graphics = (Graphics2D) arrowImage.getGraphics();
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		graphics.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
                RenderingHints.VALUE_STROKE_PURE);
//		graphics.setColor(Color.green);
//		graphics.fillRect(0, 0, (int) width, (int) height);

		Color arrowColor = SubstanceColorUtilities.getMarkColor(scheme, true);

		graphics.setColor(arrowColor);
		int cap = (width < 15) ? BasicStroke.CAP_BUTT : BasicStroke.CAP_ROUND;
		Stroke stroke = new BasicStroke(strokeWidth, cap, BasicStroke.JOIN_MITER);
		graphics.setStroke(stroke);

		if (direction == SwingConstants.CENTER) {
			float smallHeight = (height - strokeWidth) / 2;
			BufferedImage top = getArrow(width, smallHeight, strokeWidth,
					SwingConstants.NORTH, scheme);
			BufferedImage bottom = getArrow(width, smallHeight, strokeWidth,
					SwingConstants.SOUTH, scheme);
			int factor = UIUtil.isRetina() ? 2 : 1;
			graphics.drawImage(top, 0, 0, top.getWidth() / factor, top.getHeight() / factor, null);
			graphics.drawImage(bottom, 0, (int) (height / 2.0), 
					bottom.getWidth() / factor, bottom.getHeight() / factor, null);
			return arrowImage;
		} else {
			float cushion = strokeWidth / 2.0f;
			GeneralPath gp = new GeneralPath();
			gp.moveTo(cushion, cushion);
			gp.lineTo(0.5f * (width), height - 1 - cushion);
			gp.lineTo(width - cushion, cushion);
			graphics.draw(gp);

			int quadrantCounterClockwise = 0;
			switch (direction) {
			case SwingConstants.NORTH:
				quadrantCounterClockwise = 2;
				break;
			case SwingConstants.WEST:
				quadrantCounterClockwise = 1;
				break;
			case SwingConstants.SOUTH:
				quadrantCounterClockwise = 0;
				break;
			case SwingConstants.EAST:
				quadrantCounterClockwise = 3;
				break;
			}
			BufferedImage rotatedImage = SubstanceImageCreator.getRotated(
					arrowImage, quadrantCounterClockwise, false);

			return rotatedImage;
		}
	}

	/**
	 * Returns double arrow icon for the specified parameters.
	 * 
	 * @param fontSize
	 *            Font size.
	 * @param deltaWidth
	 *            Arrow width delta.
	 * @param deltaHeight
	 *            Arrow height delta.
	 * @param deltaStrokeWidth
	 *            Arrow stroke width delta.
	 * @param direction
	 *            Arrow direction.
	 * @param colorScheme
	 *            Color scheme for the arrow.
	 * @return Double arrow icon.
	 */
	public static HiDpiAwareIcon getDoubleArrowIconDelta(int fontSize, float deltaWidth,
			float deltaHeight, float deltaStrokeWidth, int direction,
			SubstanceColorScheme colorScheme) {
		float arrowWidth = SubstanceSizeUtils.getArrowIconWidth(fontSize);
		float arrowHeight = SubstanceSizeUtils.getArrowIconHeight(fontSize) + deltaHeight;
		float arrowStrokeWidth = SubstanceSizeUtils.getDoubleArrowStrokeWidth(fontSize)
				+ deltaStrokeWidth;
		return getDoubleArrowIcon(fontSize, arrowWidth, arrowHeight,
				arrowStrokeWidth, direction, colorScheme);
	}

	/**
	 * Retrieves arrow icon.
	 * 
	 * @param width
	 *            Arrow width.
	 * @param height
	 *            Arrow height.
	 * @param strokeWidth
	 *            Stroke width.
	 * @param direction
	 *            Arrow direction.
	 * @param colorScheme
	 *            Color scheme for the arrow.
	 * @return Arrow image.
	 * @see SwingConstants#NORTH
	 * @see SwingConstants#WEST
	 * @see SwingConstants#SOUTH
	 * @see SwingConstants#EAST
	 */
	public static HiDpiAwareIcon getDoubleArrowIcon(int fontSize, float width,
			float height, float strokeWidth, int direction,
			SubstanceColorScheme colorScheme) {
		int delta = 3 + 2 * SubstanceSizeUtils.getExtraPadding(fontSize) / 3;
		boolean toggle = (direction == SwingConstants.WEST)
				|| (direction == SwingConstants.EAST);
		if (toggle) {
			float temp = width;
			width = height;
			height = temp;
		}
		BufferedImage downArrowImage = SubstanceCoreUtilities.getBlankImage(
				(int) width, (int) height);

		BufferedImage singleArrow = getArrow(width,
				Math.max(1, height - delta), strokeWidth, SwingConstants.SOUTH,
				colorScheme);

		// get graphics and set hints
		Graphics2D graphics = (Graphics2D) downArrowImage.getGraphics();
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		int scaleFactor = UIUtil.isRetina() ? 2 : 1;
		int arrowHeight = singleArrow.getHeight();
		int arrowWidth = singleArrow.getWidth();
		int bottomOfSecondArrow = (int) (height);
		int topOfFirstArrow = bottomOfSecondArrow - arrowHeight / scaleFactor - delta;
		graphics.drawImage(singleArrow, 0, topOfFirstArrow, arrowWidth / scaleFactor,
				arrowHeight / scaleFactor, null);
		graphics.drawImage(singleArrow, 0, topOfFirstArrow + delta, arrowWidth / scaleFactor,
				arrowHeight / scaleFactor, null);

		int quadrantCounterClockwise = 0;
		switch (direction) {
		case SwingConstants.NORTH:
			quadrantCounterClockwise = 2;
			break;
		case SwingConstants.WEST:
			quadrantCounterClockwise = 1;
			break;
		case SwingConstants.SOUTH:
			quadrantCounterClockwise = 0;
			break;
		case SwingConstants.EAST:
			quadrantCounterClockwise = 3;
			break;
		}
		BufferedImage arrowImage = SubstanceImageCreator.getRotated(
				downArrowImage, quadrantCounterClockwise, false);

		return new HiDpiAwareIcon(arrowImage);
	}

	/**
	 * Returns rotated image.
	 * 
	 * @param bi
	 *            Image to rotate.
	 * @param quadrantClockwise
	 *            Amount of quadrants to rotate in clockwise direction. The
	 *            rotation angle is 90 times this value.
	 * @return Rotated image.
	 */
	public static BufferedImage getRotated(BufferedImage bi, int quadrantClockwise,
			boolean respectScaleFactorDuringRotation) {
		if (quadrantClockwise == 0) {
			return bi;
		}
		quadrantClockwise = quadrantClockwise % 4;
		int width = bi.getWidth();
		int height = bi.getHeight();
		if ((quadrantClockwise == 1) || (quadrantClockwise == 3)) {
			width = bi.getHeight();
			height = bi.getWidth();
		}
		int factor = UIUtil.isRetina() ? 2 : 1;
		BufferedImage biRot = SubstanceCoreUtilities.getBlankImage(width / factor,
				height / factor);
		AffineTransform at = null;
		int factorForRotation = respectScaleFactorDuringRotation ? factor : 1;
		switch (quadrantClockwise) {
		case 1:
			at = AffineTransform.getTranslateInstance(width / factorForRotation, 0);
			at.rotate(Math.PI / 2);
			break;
		case 2:
			at = AffineTransform.getTranslateInstance(width / factorForRotation, height / factorForRotation);
			at.rotate(Math.PI);
			break;
		case 3:
			at = AffineTransform.getTranslateInstance(0, height / factorForRotation);
			at.rotate(-Math.PI / 2);
		}
		Graphics2D rotg = biRot.createGraphics();
		rotg.scale(1.0f / factor, 1.0f / factor);
		if (at != null) {
			rotg.setTransform(at);
		}
		rotg.drawImage(bi, 0, 0, bi.getWidth(), bi.getHeight(), null);
		rotg.dispose();
		return biRot;
	}

	/**
	 * Translated the specified icon to grey scale.
	 * 
	 * @param icon
	 *            Icon.
	 * @return Greyscale version of the specified icon.
	 */
	public static Icon toGreyscale(Icon icon) {
		if (icon == null) {
			return null;
		}

		int width = icon.getIconWidth();
		int height = icon.getIconHeight();

		BufferedImage result = SubstanceCoreUtilities.getBlankImage(width,
				height);

		icon.paintIcon(null, result.getGraphics(), 0, 0);
		Icon resultIcon = new HiDpiAwareIcon(new GrayscaleFilter().filter(result, null));
//		System.out.println("Orig " + icon.getIconWidth() + "x" + icon.getIconHeight() + " -> " + 
//				resultIcon.getIconWidth() + "x" + resultIcon.getIconHeight());
		return resultIcon;
	}

	/**
	 * Makes the specified icon transparent.
	 * 
	 * @param c
	 *            Component.
	 * @param icon
	 *            Icon.
	 * @param alpha
	 *            The alpha of the resulting image. The closer this value is to
	 *            0.0, the more transparent resulting image will be.
	 * @return Transparent version of the specified icon.
	 */
	public static Icon makeTransparent(Component c, Icon icon, double alpha) {
		if (icon == null) {
			return null;
		}

		int width = icon.getIconWidth();
		int height = icon.getIconHeight();

		BufferedImage result = SubstanceCoreUtilities.getBlankImage(width, height);
		icon.paintIcon(c, result.getGraphics(), 0, 0);
		return new HiDpiAwareIcon(new TranslucentFilter(alpha).filter(result, null));
	}

	/**
	 * Retrieves radio button of the specified size that matches the specified
	 * parameters.
	 * 
	 * @param component
	 *            Component.
	 * @param dimension
	 *            Radio button dimension.
	 * @param componentState
	 *            Component state.
	 * @param offsetX
	 *            Offset on X axis - should be positive in order to see the
	 *            entire radio button.
	 * @param fillColorScheme
	 *            Color scheme for the inner fill.
	 * @param markColorScheme
	 *            Color scheme for the check mark.
	 * @param borderColorScheme
	 *            Color scheme for the border.
	 * @param checkMarkVisibility
	 *            Check mark visibility in 0.0-1.0 range.
	 * @return Radio button of the specified size that matches the specified
	 *         parameters.
	 */
	public static BufferedImage getRadioButton(JComponent component,
			SubstanceFillPainter fillPainter,
			SubstanceBorderPainter borderPainter, int dimension,
			ComponentState componentState, int offsetX,
			SubstanceColorScheme fillColorScheme,
			SubstanceColorScheme markColorScheme,
			SubstanceColorScheme borderColorScheme, float checkMarkVisibility,
			float alpha) {

		if (!componentState.isActive()) {
			fillPainter = SimplisticSoftBorderReverseFillPainter.INSTANCE;
		}

		float borderDelta = SubstanceSizeUtils.getBorderStrokeWidth(dimension);

		// float fDelta = borderThickness / 2.0f;
		Shape contourBorder = new Ellipse2D.Float(borderDelta / 2.0f, borderDelta / 2.0f, 
				dimension - borderDelta, dimension - borderDelta);

		BufferedImage offBackground = SubstanceCoreUtilities.getBlankImage(
				dimension + offsetX, dimension);
		Graphics2D graphics = (Graphics2D) offBackground.getGraphics().create();
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		graphics.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
                RenderingHints.VALUE_STROKE_PURE);
		
		graphics.setComposite(AlphaComposite.getInstance(
				AlphaComposite.SRC_OVER, alpha));

		graphics.translate(offsetX, 0);
		fillPainter.paintContourBackground(graphics, component, dimension,
				dimension, contourBorder, false, fillColorScheme, true);

		Shape contourInner = new Ellipse2D.Float(1.5f * borderDelta, 1.5f * borderDelta, 
				dimension - 3 * borderDelta, dimension - 3 * borderDelta);

		borderPainter.paintBorder(graphics, component, dimension, dimension,
				contourBorder, contourInner, borderColorScheme);
		graphics.setComposite(AlphaComposite.SrcOver);

		float rc = (dimension - (UIUtil.isRetina() ? 0.0f : 0.0f)) / 2.0f;
		float radius = dimension / 4.5f;

		Shape markOval = new Ellipse2D.Double(rc - radius, rc - radius,
				2 * radius, 2 * radius);
		if (checkMarkVisibility > 0.0) {
			// mark
			graphics.setComposite(AlphaComposite.getInstance(
					AlphaComposite.SRC_OVER, alpha * checkMarkVisibility));
			graphics.setColor(SubstanceColorUtilities.getMarkColor(
					markColorScheme, !componentState.isDisabled()));
			graphics.fill(markOval);
		} else {
			// draw ghost mark holder
			graphics.setComposite(AlphaComposite.getInstance(
					AlphaComposite.SRC_OVER, alpha * 0.3f));
			graphics.setPaint(new GradientPaint(rc + radius, rc - radius,
					fillColorScheme.getDarkColor(), rc - radius, rc + radius,
					fillColorScheme.getLightColor()));
			graphics.fill(markOval);
		}
		graphics.dispose();

		return offBackground;
	}

	/**
	 * Retrieves check box of the specified size that matches the specified
	 * component state.
	 * 
	 * @param button
	 *            Button for the check mark.
	 * @param dimension
	 *            Check box size.
	 * @param componentState
	 *            Component state.
	 * @param fillColorScheme
	 *            Color scheme for the inner fill.
	 * @param markColorScheme
	 *            Color scheme for the check mark.
	 * @param borderColorScheme
	 *            Color scheme for the border.
	 * @param checkMarkVisibility
	 *            Check mark visibility in 0.0-1.0 range.
	 * @param isCheckMarkFadingOut
	 *            if <code>true</code>, the value of
	 *            <code>interpolationCyclePos10</code> is used as the alpha
	 *            channel.
	 * @return Check box of the specified size that matches the specified
	 *         component state.
	 */
	public static BufferedImage getCheckBox(AbstractButton button,
			SubstanceFillPainter fillPainter,
			SubstanceBorderPainter borderPainter, int dimension,
			ComponentState componentState,
			SubstanceColorScheme fillColorScheme,
			SubstanceColorScheme markColorScheme,
			SubstanceColorScheme borderColorScheme, float checkMarkVisibility,
			boolean isCheckMarkFadingOut, float alpha) {

		int xOffset = SubstanceSizeUtils.getAdjustedSize(
				SubstanceSizeUtils.getComponentFontSize(button), 2, 9,
				1, false);
		int yOffset = xOffset + 1;
		int delta = xOffset;
		float cornerRadius = SubstanceSizeUtils
				.getClassicButtonCornerRadius(SubstanceSizeUtils
						.getComponentFontSize(button));
		if (dimension <= 10) {
			xOffset = 1;
			yOffset = 2;
			cornerRadius = 2;
		}

		int contourDim = dimension - delta;
		float borderDelta = SubstanceSizeUtils
				.getBorderStrokeWidth(SubstanceSizeUtils
						.getComponentFontSize(button)) / 2.0f;
		GeneralPath contour = SubstanceOutlineUtilities.getBaseOutline(
				contourDim, contourDim, cornerRadius, null, borderDelta);

		if (!componentState.isActive()) {
			fillPainter = SimplisticSoftBorderReverseFillPainter.INSTANCE;
		}

		BufferedImage offBackground = SubstanceCoreUtilities.getBlankImage(
				dimension, dimension);
		Graphics2D graphics = (Graphics2D) offBackground.getGraphics();
		graphics.setComposite(AlphaComposite.getInstance(
				AlphaComposite.SRC_OVER, alpha));

		graphics.translate(delta - 1, delta - 1);
		fillPainter.paintContourBackground(graphics, button, contourDim,
				contourDim, contour, false, fillColorScheme, true);

		float borderThickness = SubstanceSizeUtils.getBorderStrokeWidth(dimension);
		GeneralPath contourInner = SubstanceOutlineUtilities.getBaseOutline(
				contourDim, contourDim, cornerRadius - borderThickness, null,
				borderThickness + borderDelta);
		borderPainter.paintBorder(graphics, button, contourDim, contourDim,
				contour, contourInner, borderColorScheme);
		graphics.translate(-delta, 1 - delta);
		if (checkMarkVisibility > 0.0) {
			if (isCheckMarkFadingOut) {
				graphics.setComposite(AlphaComposite.getInstance(
						AlphaComposite.SRC_OVER, alpha * checkMarkVisibility));
				checkMarkVisibility = 1.0f;
			}

			BufferedImage checkMark = SubstanceImageCreator.getCheckMark(
					dimension - yOffset / 2, !componentState.isDisabled(),
					markColorScheme, checkMarkVisibility);
			int factor = UIUtil.isRetina() ? 2 : 1;
			graphics.drawImage(checkMark, 1 + 2 * xOffset / 3,
					(dimension < 14) ? 0 : -1, checkMark.getWidth() / factor, 
							checkMark.getHeight() / factor, null);
		}

		return offBackground;
	}

	/**
	 * Overlays light-colored echo below the specified image.
	 * 
	 * @param image
	 *            The input image.
	 * @param echoAlpha
	 *            Alpha channel for the echo image.
	 * @param offsetX
	 *            X offset of the echo.
	 * @param offsetY
	 *            Y offset of the echo.
	 * @return Image with overlayed echo.
	 */
	private static BufferedImage overlayEcho(BufferedImage image,
			float echoAlpha, Color echoColor, int offsetX, int offsetY) {
		int width = image.getWidth();
		int height = image.getHeight();

		offsetX = offsetY = 0;
		BufferedImage echo = new ColorFilter(echoColor).filter(image, null);
		int factor = UIUtil.isRetina() ? 2 : 1;
		int tweakedWidth = width / factor;
		int tweakedHeight = height / factor;
		BufferedImage result = SubstanceCoreUtilities.getBlankImage(tweakedWidth,
				tweakedHeight);
		Graphics2D graphics = (Graphics2D) result.getGraphics().create();
		graphics.setComposite(AlphaComposite.getInstance(
				AlphaComposite.SRC_OVER, 0.2f * echoAlpha * echoAlpha
						* echoAlpha));
		graphics.drawImage(echo, offsetX - 1, offsetY - 1, tweakedWidth, tweakedHeight, null);
		graphics.drawImage(echo, offsetX + 1, offsetY - 1, tweakedWidth, tweakedHeight, null);
		graphics.drawImage(echo, offsetX - 1, offsetY + 1, tweakedWidth, tweakedHeight, null);
		graphics.drawImage(echo, offsetX + 1, offsetY + 1, tweakedWidth, tweakedHeight, null);
		graphics.setComposite(AlphaComposite.getInstance(
				AlphaComposite.SRC_OVER, 0.7f * echoAlpha * echoAlpha
						* echoAlpha));
		graphics.drawImage(echo, offsetX, offsetY - 1, tweakedWidth, tweakedHeight, null);
		graphics.drawImage(echo, offsetX, offsetY + 1, tweakedWidth, tweakedHeight, null);
		graphics.drawImage(echo, offsetX - 1, offsetY, tweakedWidth, tweakedHeight, null);
		graphics.drawImage(echo, offsetX + 1, offsetY, tweakedWidth, tweakedHeight, null);

		graphics.setComposite(AlphaComposite.getInstance(
				AlphaComposite.SRC_OVER, 1.0f));
		graphics.drawImage(image, 0, 0, tweakedWidth, tweakedHeight, null);

		graphics.dispose();
		return result;
	}

	/**
	 * Returns <code>minimize</code> icon.
	 * 
	 * @param scheme
	 *            Color scheme for the icon.
	 * @return <code>Minimize</code> icon.
	 */
	public static HiDpiAwareIcon getMinimizeIcon(SubstanceColorScheme scheme,
			SubstanceColorScheme backgroundScheme) {
		int iSize = SubstanceSizeUtils.getTitlePaneIconSize();
		return getMinimizeIcon(iSize, scheme, backgroundScheme);
	}

	/**
	 * Returns <code>minimize</code> icon.
	 * 
	 * @param iSize
	 *            Icon dimension.
	 * @param scheme
	 *            Color scheme for the icon.
	 * @return <code>Minimize</code> icon.
	 */
	public static HiDpiAwareIcon getMinimizeIcon(int iSize, SubstanceColorScheme scheme,
			SubstanceColorScheme backgroundScheme) {
		BufferedImage image = SubstanceCoreUtilities
				.getBlankImage(iSize, iSize);
		Graphics2D graphics = (Graphics2D) image.createGraphics();
		int start = iSize / 4 - 2;
		int end = 3 * iSize / 4;
		int size = end - start - 3;
		Color color = SubstanceColorUtilities.getMarkColor(scheme, true);
		graphics.setColor(color);
		graphics.fillRect(start + 2, end - 1, size, 3);
		graphics.dispose();

		int fgStrength = SubstanceColorUtilities.getColorBrightness(color
				.getRGB());
		int fgNegativeStrength = SubstanceColorUtilities
				.getColorBrightness(SubstanceColorUtilities
						.getNegativeColor(color.getRGB()));
		int bgStrength = SubstanceColorUtilities
				.getColorBrightness(backgroundScheme.getLightColor().getRGB());
		boolean noEcho = (fgStrength > fgNegativeStrength)
				&& (fgStrength < bgStrength);

		Color echoColor = scheme.isDark() ? backgroundScheme.getUltraDarkColor() 
				: backgroundScheme.getUltraLightColor();
		return new HiDpiAwareIcon(SubstanceImageCreator.overlayEcho(image,
				noEcho ? 0 : SubstanceColorUtilities.getColorStrength(color),
				echoColor, 1, 1));
	}

	/**
	 * Returns <code>restore</code> icon.
	 * 
	 * @param scheme
	 *            Color scheme for the icon.
	 * @return <code>Restore</code> icon.
	 */
	public static HiDpiAwareIcon getRestoreIcon(SubstanceColorScheme scheme,
			SubstanceColorScheme backgroundScheme) {
		int iSize = SubstanceSizeUtils.getTitlePaneIconSize();
		BufferedImage image = SubstanceCoreUtilities.getBlankImage(iSize, iSize);
		Graphics2D graphics = (Graphics2D) image.createGraphics();
		int start = iSize / 4 - 1;
		int end = iSize - start;
		int smallSquareSize = end - start - 3;
		Color color = SubstanceColorUtilities.getMarkColor(scheme, true);
		graphics.setColor(color);

		// "Main" rectangle 
		int mainStartX = start;
		int mainStartY = end - smallSquareSize;
		// top (thicker)
		graphics.fillRect(mainStartX, mainStartY, smallSquareSize, 2);
		// left
		graphics.fillRect(mainStartX, mainStartY, 1, smallSquareSize);
		// right
		graphics.fillRect(mainStartX + smallSquareSize - 1, mainStartY, 1, smallSquareSize);
		// bottom
		graphics.fillRect(mainStartX, mainStartY + smallSquareSize - 1, smallSquareSize, 1);

		// "Secondary rectangle"
		int secondaryStartX = mainStartX + 3;
		int secondaryStartY = mainStartY - 3;
		// top (thicker)
		graphics.fillRect(secondaryStartX, secondaryStartY, smallSquareSize, 2);
		// right
		graphics.fillRect(secondaryStartX + smallSquareSize - 1, secondaryStartY, 1, smallSquareSize);
		// bottom (partial)
		graphics.fillRect(mainStartX + smallSquareSize + 1, secondaryStartY + smallSquareSize - 1, 
				2, 1);

		graphics.dispose();

		int fgStrength = SubstanceColorUtilities.getColorBrightness(color.getRGB());
		int fgNegativeStrength = SubstanceColorUtilities
				.getColorBrightness(SubstanceColorUtilities
						.getNegativeColor(color.getRGB()));
		int bgStrength = SubstanceColorUtilities
				.getColorBrightness(backgroundScheme.getLightColor().getRGB());
		boolean noEcho = (fgStrength > fgNegativeStrength)
				&& (fgStrength < bgStrength);

		Color echoColor = scheme.isDark() ? backgroundScheme.getUltraDarkColor() 
				: backgroundScheme.getUltraLightColor();
		return new HiDpiAwareIcon(SubstanceImageCreator.overlayEcho(image,
				noEcho ? 0 : SubstanceColorUtilities.getColorStrength(color),
				echoColor, 1, 1));
	}

	/**
	 * Returns <code>maximize</code> icon.
	 * 
	 * @param scheme
	 *            Color scheme for the icon.
	 * @return <code>Maximize</code> icon.
	 */
	public static HiDpiAwareIcon getMaximizeIcon(SubstanceColorScheme scheme,
			SubstanceColorScheme backgroundScheme) {
		int iSize = SubstanceSizeUtils.getTitlePaneIconSize();
		return getMaximizeIcon(iSize, scheme, backgroundScheme);
	}

	/**
	 * Returns <code>maximize</code> icon.
	 * 
	 * @param iSize
	 *            Icon dimension.
	 * @param scheme
	 *            Color scheme for the icon.
	 * @return <code>Maximize</code> icon.
	 */
	public static HiDpiAwareIcon getMaximizeIcon(int iSize, SubstanceColorScheme scheme,
			SubstanceColorScheme backgroundScheme) {
		BufferedImage image = SubstanceCoreUtilities.getBlankImage(iSize, iSize);
		Graphics2D graphics = (Graphics2D) image.createGraphics();
		int start = iSize / 4 - 1;
		int end = iSize - start;
		Color color = SubstanceColorUtilities.getMarkColor(scheme, true);
		graphics.setColor(color);
		// top (thicker)
		graphics.fillRect(start, start, end - start, 2);
		// left
		graphics.fillRect(start, start, 1, end - start);
		// right
		graphics.fillRect(end - 1, start, 1, end - start);
		// bottom
		graphics.fillRect(start, end - 1, end - start, 1);
		graphics.dispose();

		int fgStrength = SubstanceColorUtilities.getColorBrightness(color.getRGB());
		int fgNegativeStrength = SubstanceColorUtilities
				.getColorBrightness(SubstanceColorUtilities
						.getNegativeColor(color.getRGB()));
		int bgStrength = SubstanceColorUtilities
				.getColorBrightness(backgroundScheme.getLightColor().getRGB());
		boolean noEcho = (fgStrength > fgNegativeStrength)
				&& (fgStrength < bgStrength);
		noEcho = true;

		Color echoColor = scheme.isDark() ? backgroundScheme.getUltraDarkColor() 
				: backgroundScheme.getUltraLightColor();
		return new HiDpiAwareIcon(SubstanceImageCreator.overlayEcho(image,
				noEcho ? 0 : SubstanceColorUtilities.getColorStrength(color),
				echoColor, 1, 1));
	}

	/**
	 * Returns <code>close</code> icon.
	 * 
	 * @param scheme
	 *            Color scheme for the icon.
	 * @return <code>Close</code> icon.
	 */
	public static HiDpiAwareIcon getCloseIcon(SubstanceColorScheme scheme,
			SubstanceColorScheme backgroundScheme) {
		return SubstanceImageCreator.getCloseIcon(
				SubstanceSizeUtils.getTitlePaneIconSize(), scheme,
				backgroundScheme);
	}

	/**
	 * Returns <code>close</code> icon.
	 * 
	 * @param iSize
	 *            Icon dimension.
	 * @param colorScheme
	 *            Color scheme for the icon.
	 * @return <code>Close</code> icon.
	 */
	public static HiDpiAwareIcon getCloseIcon(int iSize,
			SubstanceColorScheme colorScheme,
			SubstanceColorScheme backgroundScheme) {
		BufferedImage image = SubstanceCoreUtilities.getBlankImage(iSize, iSize);
		Graphics2D graphics = (Graphics2D) image.createGraphics();
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		int start = iSize / 4;
		int end = iSize - start;

		// System.out.println(iSize + ":" + start + ":" + end);

		Stroke stroke = new BasicStroke(
				SubstanceSizeUtils.getCloseIconStrokeWidth(iSize),
				BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);

		graphics.setStroke(stroke);
		Color color = SubstanceColorUtilities.getMarkColor(colorScheme, true);
		graphics.setColor(color);
		graphics.drawLine(start, start, end, end);
		graphics.drawLine(start, end, end, start);
		graphics.dispose();

		int fgStrength = SubstanceColorUtilities.getColorBrightness(color.getRGB());
		int fgNegativeStrength = SubstanceColorUtilities.getColorBrightness(
				SubstanceColorUtilities.getNegativeColor(color.getRGB()));
		int bgStrength = SubstanceColorUtilities.getColorBrightness(
				backgroundScheme.getLightColor().getRGB());
		boolean noEcho = (fgStrength > fgNegativeStrength) && (fgStrength < bgStrength);

		Color echoColor = colorScheme.isDark() ? backgroundScheme.getUltraDarkColor()
				: backgroundScheme.getUltraLightColor();
		return new HiDpiAwareIcon(SubstanceImageCreator.overlayEcho(image,
				noEcho ? 0 : SubstanceColorUtilities.getColorStrength(color),
				echoColor, 1, 1));
	}

	/**
	 * Paints rectangular gradient background.
	 * 
	 * @param g
	 *            Graphic context.
	 * @param startX
	 *            Background starting X coord.
	 * @param startY
	 *            Background starting Y coord.
	 * @param width
	 *            Background width.
	 * @param height
	 *            Background height.
	 * @param colorScheme
	 *            Color scheme for the background.
	 * @param borderAlpha
	 *            Border alpha.
	 * @param isVertical
	 *            if <code>true</code>, the gradient will be vertical, if
	 *            <code>false</code>, the gradient will be horizontal.
	 */
	public static void paintRectangularBackground(Component c, Graphics g,
			int startX, int startY, int width, int height,
			SubstanceColorScheme colorScheme, float borderAlpha,
			boolean isVertical) {
		Graphics2D graphics = (Graphics2D) g.create();
		graphics.translate(startX, startY);

		if (!isVertical) {
			LinearGradientPaint paint = new LinearGradientPaint(0, 0, 0,
					height, new float[] { 0.0f, 0.4f, 0.5f, 1.0f },
					new Color[] { colorScheme.getUltraLightColor(),
							colorScheme.getLightColor(),
							colorScheme.getMidColor(),
							colorScheme.getUltraLightColor() },
					CycleMethod.REPEAT);
			graphics.setPaint(paint);
			graphics.fillRect(0, 0, width, height);
		} else {
			LinearGradientPaint paint = new LinearGradientPaint(0, 0, width, 0,
					new float[] { 0.0f, 0.4f, 0.5f, 1.0f }, new Color[] {
							colorScheme.getUltraLightColor(),
							colorScheme.getLightColor(),
							colorScheme.getMidColor(),
							colorScheme.getUltraLightColor() },
					CycleMethod.REPEAT);
			graphics.setPaint(paint);
			graphics.fillRect(0, 0, width, height);
		}

		if (borderAlpha > 0.0f) {
			Graphics2D g2d = (Graphics2D) graphics.create();
			g2d.setComposite(LafWidgetUtilities.getAlphaComposite(null,
					borderAlpha, graphics));

			paintSimpleBorderAliased(c, g2d, width, height, colorScheme);

			g2d.dispose();
		}
		graphics.dispose();
	}

	/**
	 * Paints simple border.
	 * 
	 * @param g2d
	 *            Graphics context.
	 * @param width
	 *            Border width.
	 * @param height
	 *            Border height.
	 * @param borderColorScheme
	 *            Border color scheme.
	 * @param cyclePos
	 *            Interpolation cycle.
	 */
	public static void paintSimpleBorder(Component c, Graphics2D g2d,
			float width, float height, SubstanceColorScheme borderColorScheme) {
		int componentFontSize = SubstanceSizeUtils.getComponentFontSize(c);
		// int borderDelta = (int) Math.floor(SubstanceSizeUtils
		// .getBorderStrokeWidth(componentFontSize) / 2.0);
		// int borderDelta2 = (int) Math.floor(SubstanceSizeUtils
		// .getBorderStrokeWidth(componentFontSize));
		float borderThickness = SubstanceSizeUtils.getBorderStrokeWidth(componentFontSize);

		g2d.setColor(SubstanceColorUtilities
				.getMidBorderColor(borderColorScheme));
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
				RenderingHints.VALUE_STROKE_PURE);
		int joinKind = BasicStroke.JOIN_ROUND;
		int capKind = BasicStroke.CAP_BUTT;
		g2d.setStroke(new BasicStroke(borderThickness, capKind, joinKind));
		g2d.draw(new Rectangle2D.Float((borderThickness - 1.0f) / 2.0f,
				(borderThickness - 1.0f) / 2.0f, width - 1
						- (borderThickness - 1.5f), height - 1
						- (borderThickness - 1.5f)));
		// g2d.drawRect(borderDelta, borderDelta, width - 1 - borderDelta2,
		// height
		// - 1 - borderDelta2);
	}

	public static void paintSimpleBorderAliased(Component c, Graphics2D g2d,
			int width, int height, SubstanceColorScheme colorScheme) {
		int componentFontSize = SubstanceSizeUtils.getComponentFontSize(c);
		// int borderDelta = (int) Math.floor(SubstanceSizeUtils
		// .getBorderStrokeWidth(componentFontSize) / 2.0);
		// int borderDelta2 = (int) Math.floor(SubstanceSizeUtils
		// .getBorderStrokeWidth(componentFontSize));
		float borderThickness = (float) Math.floor(SubstanceSizeUtils
				.getBorderStrokeWidth(componentFontSize));

		g2d.setColor(SubstanceColorUtilities.getMidBorderColor(colorScheme));
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_OFF);
		g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
				RenderingHints.VALUE_STROKE_PURE);
		int joinKind = BasicStroke.JOIN_MITER;
		int capKind = BasicStroke.CAP_SQUARE;
		g2d.setStroke(new BasicStroke(borderThickness, capKind, joinKind));
		g2d.draw(new Rectangle2D.Float(borderThickness / 2.0f,
				borderThickness / 2.0f, width - borderThickness, height
						- borderThickness));
	}

	/**
	 * Paints rectangular gradient background with spots and optional replicated
	 * stripe image.
	 * 
	 * @param g
	 *            Graphics context.
	 * @param startX
	 *            X start coordinate.
	 * @param startY
	 *            Y start coordinate.
	 * @param width
	 *            Background width.
	 * @param height
	 *            Background height.
	 * @param colorScheme
	 *            Color scheme for the background.
	 * @param stripeImage
	 *            Stripe image to replicate.
	 * @param stripeOffset
	 *            Offset of the first stripe replication.
	 * @param borderAlpha
	 *            Border alpha.
	 * @param isVertical
	 *            Indication of horizontal / vertical orientation.
	 */
	public static void paintRectangularStripedBackground(Component c,
			Graphics g, int startX, int startY, int width, int height,
			SubstanceColorScheme colorScheme, BufferedImage stripeImage,
			int stripeOffset, float borderAlpha, boolean isVertical) {
		Graphics2D graphics = (Graphics2D) g.create(startX, startY, width, height);
		int scaleFactor = UIUtil.isRetina() ? 2 : 1;
		if (!isVertical) {
			LinearGradientPaint paint = new LinearGradientPaint(0, 0, 0,
					height, new float[] { 0.0f, 0.2f, 0.5f, 0.8f, 1.0f },
					new Color[] { colorScheme.getDarkColor(),
							colorScheme.getLightColor(),
							colorScheme.getMidColor(),
							colorScheme.getLightColor(),
							colorScheme.getDarkColor() }, CycleMethod.REPEAT);
			graphics.setPaint(paint);
			graphics.fillRect(0, 0, width, height);

			if (stripeImage != null) {
				int stripeSize = stripeImage.getHeight();
				int stripeCount = scaleFactor * width / stripeSize;
				stripeOffset = stripeOffset % (2 * stripeSize * scaleFactor);
				for (int stripe = -2; stripe <= stripeCount; stripe += 2) {
					int stripePos = stripe * stripeSize / scaleFactor + stripeOffset;

					graphics.drawImage(stripeImage, stripePos, 0, 
							stripeImage.getWidth() / scaleFactor,
							stripeImage.getHeight() / scaleFactor, null);
				}
			}
		} else {
			LinearGradientPaint paint = new LinearGradientPaint(0, 0, width, 0,
					new float[] { 0.0f, 0.2f, 0.5f, 0.8f, 1.0f }, new Color[] {
							colorScheme.getDarkColor(),
							colorScheme.getLightColor(),
							colorScheme.getMidColor(),
							colorScheme.getLightColor(),
							colorScheme.getDarkColor() }, CycleMethod.REPEAT);
			graphics.setPaint(paint);
			graphics.fillRect(0, 0, width, height);

			if (stripeImage != null) {
				int stripeSize = stripeImage.getWidth();
				int stripeCount = scaleFactor * height / stripeSize;
				stripeOffset = stripeOffset % (2 * stripeSize * scaleFactor);
				for (int stripe = -2; stripe <= stripeCount; stripe += 2) {
					int stripePos = stripe * stripeSize / scaleFactor + stripeOffset;

					graphics.drawImage(stripeImage, 0, stripePos, 
							stripeImage.getWidth() / scaleFactor,
							stripeImage.getHeight() / scaleFactor, null);
				}
			}
		}

		if (borderAlpha > 0.0f) {
			Graphics2D g2d = (Graphics2D) graphics.create();
			g2d.setComposite(LafWidgetUtilities.getAlphaComposite(null,
					borderAlpha, graphics));

			paintSimpleBorderAliased(c, g2d, width, height, colorScheme);
			g2d.dispose();
		}
		graphics.dispose();
	}

	/**
	 * Returns diagonal stripe image.
	 * 
	 * @param baseSize
	 *            Stripe base in pixels.
	 * @param color
	 *            Stripe color.
	 * @return Diagonal stripe image.
	 */
	public static BufferedImage getStripe(int baseSize, Color color) {
		int width = (int) (1.8 * baseSize);
		int height = baseSize;
		BufferedImage intermediate = SubstanceCoreUtilities.getBlankImage(width, height);
		Graphics2D graphics = (Graphics2D) intermediate.getGraphics();

		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		Polygon polygon = new Polygon();
		polygon.addPoint(0, 0);
		polygon.addPoint(width - 1 - baseSize, 0);
		polygon.addPoint(width - 1, height - 1);
		polygon.addPoint(baseSize, height - 1);

		graphics.setColor(color);
		graphics.fillPolygon(polygon);
		graphics.drawPolygon(polygon);

		return intermediate;
	}

	/**
	 * Returns drag bumps image.
	 * 
	 * @param c
	 *            Component.
	 * @param colorScheme
	 *            Color scheme.
	 * @param alwaysUseActive
	 *            Indicates whether the active color scheme should always be
	 *            used.
	 * @param width
	 *            Drag bumps width.
	 * @param height
	 *            Drag bumps height.
	 * @param maxNumberOfStripes
	 *            The maximum number of bump stripes (rows or columns).
	 * @return Drag bumps image.
	 */
	public static BufferedImage getDragImage(Component c,
			SubstanceColorScheme colorScheme, int width, int height,
			int maxNumberOfStripes) {
		BufferedImage result = SubstanceCoreUtilities.getBlankImage(width,
				height);
		Graphics2D graphics = (Graphics2D) result.getGraphics();

		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		boolean isDark = colorScheme.isDark();
		Color back1 = isDark ? colorScheme.getLightColor()
				: SubstanceColorUtilities.getInterpolatedColor(
						colorScheme.getLightColor(),
						colorScheme.getDarkColor(), 0.8);
		Color back2 = isDark ? colorScheme.getExtraLightColor()
				: SubstanceColorUtilities.getInterpolatedColor(
						colorScheme.getMidColor(), colorScheme.getDarkColor(),
						0.4);
		Color fore = isDark ? colorScheme.getDarkColor() : colorScheme
				.getUltraLightColor();

		int componentFontSize = SubstanceSizeUtils.getComponentFontSize(c);
		int bumpDotDiameter = SubstanceSizeUtils
				.getDragBumpDiameter(componentFontSize);
		int bumpCellSize = (int) (1.5 * bumpDotDiameter + 1);
		int bumpRows = Math.max(1, height / bumpCellSize - 1);
		int bumpColumns = Math.max(1, (width - 2) / bumpCellSize);
		if (maxNumberOfStripes > 0) {
			if (height > width)
				bumpColumns = Math.min(bumpColumns, maxNumberOfStripes);
			else
				bumpRows = Math.min(bumpRows, maxNumberOfStripes);
		}

		int bumpRowOffset = (height - bumpCellSize * bumpRows) / 2;
		int bumpColOffset = 1 + (width - bumpCellSize * bumpColumns) / 2;

		for (int col = 0; col < bumpColumns; col++) {
			int cx = bumpColOffset + col * bumpCellSize;
			boolean isEvenCol = (col % 2 == 0);
			int offsetY = isEvenCol ? 0 : bumpDotDiameter;
			for (int row = 0; row < bumpRows; row++) {
				int cy = offsetY + bumpRowOffset + row * bumpCellSize;
				graphics.setColor(fore);
				graphics.fillOval(cx + 1, cy + 1, bumpDotDiameter,
						bumpDotDiameter);
				// graphics.setColor(back1);
				graphics.setPaint(new GradientPaint(cx, cy, back1, cx
						+ bumpDotDiameter - 1, cy + bumpDotDiameter - 1, back2));
				graphics.fillOval(cx, cy, bumpDotDiameter, bumpDotDiameter);
			}
		}
		return result;
	}

	/**
	 * Paints the bump dots on the split pane dividers.
	 * 
	 * @param g
	 *            Graphics context.
	 * @param divider
	 *            Split pane divider.
	 * @param x
	 *            X coordinate of the bump dots.
	 * @param y
	 *            Y coordinate of the bump dots.
	 * @param width
	 *            Width of the bump dots area.
	 * @param height
	 *            Height of the bump dots area.
	 * @param isHorizontal
	 *            Indicates whether the dots are horizontal.
	 * @param componentState
	 *            Split pane divider state.
	 * @param colorScheme1
	 *            First color scheme.
	 * @param colorScheme2
	 *            Second color scheme.
	 * @param interpolationCyclePos
	 *            Interpolation cycle.
	 */
	public static void paintSplitDividerBumpImage(Graphics g,
			SubstanceSplitPaneDivider divider, int x, int y, int width,
			int height, boolean isHorizontal, SubstanceColorScheme colorScheme) {
		Graphics2D graphics = (Graphics2D) g.create();
		graphics.translate(x, y);

		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		int componentFontSize = SubstanceSizeUtils
				.getComponentFontSize(divider);
		int bumpDotDiameter = SubstanceSizeUtils
				.getBigDragBumpDiameter(componentFontSize);
		int bumpCellSize = (int) (1.5 * bumpDotDiameter + 1);
		int bumpRows = isHorizontal ? 1 : Math.max(1, height / bumpCellSize - 1);
		int bumpColumns = isHorizontal ? Math.max(1, (width - 2) / bumpCellSize) : 1;

		int bumpRowOffset = (height - bumpCellSize * bumpRows) / 2;
		int bumpColOffset = 1 + (width - bumpCellSize * bumpColumns) / 2;

		BufferedImage singleDot = SubstanceCoreUtilities.getBlankImage(
				bumpDotDiameter, bumpDotDiameter);
		Graphics2D dotGraphics = (Graphics2D) singleDot.getGraphics();
		dotGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		dotGraphics.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
                RenderingHints.VALUE_STROKE_PURE);

		Color markColor = SubstanceColorUtilities.getMarkColor(colorScheme,
				divider.isEnabled());
		dotGraphics.setColor(markColor);
		dotGraphics.fillOval(0, 0, bumpDotDiameter, bumpDotDiameter);

		dotGraphics.setComposite(AlphaComposite.getInstance(
				AlphaComposite.SRC_OVER, 0.4f));
		SubstanceBorderPainter borderPainter = SubstanceCoreUtilities
				.getBorderPainter(divider);
		borderPainter.paintBorder(dotGraphics, divider, bumpDotDiameter, bumpDotDiameter,
				new Ellipse2D.Float(0, 0, bumpDotDiameter, bumpDotDiameter), null, colorScheme);

		graphics.setComposite(LafWidgetUtilities.getAlphaComposite(divider, 0.8f, g));
		int scaleFactor = UIUtil.isRetina() ? 2 : 1;
		for (int col = 0; col < bumpColumns; col++) {
			int cx = bumpColOffset + col * bumpCellSize;
			for (int row = 0; row < bumpRows; row++) {
				int cy = bumpRowOffset + row * bumpCellSize + (bumpCellSize - bumpDotDiameter) / 2;
				graphics.drawImage(singleDot, cx, cy, 
						singleDot.getWidth() / scaleFactor, 
						singleDot.getHeight() / scaleFactor, null);
			}
		}
		graphics.dispose();
	}

	/**
	 * Returns resize grip image.
	 * 
	 * @param c
	 *            Component.
	 * @param colorScheme
	 *            Color scheme.
	 * @param dimension
	 *            Resize grip width.
	 * @param isCrowded
	 *            Indicates whether the grips should be painted closely.
	 * @return Resize grip image.
	 */
	public static BufferedImage getResizeGripImage(Component c,
			SubstanceColorScheme colorScheme, int dimension, boolean isCrowded) {
		BufferedImage result = SubstanceCoreUtilities.getBlankImage(dimension,
				dimension);
		Graphics2D graphics = (Graphics2D) result.getGraphics();

		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		boolean isDark = colorScheme.isDark();
		Color back1 = isDark ? colorScheme.getLightColor()
				: SubstanceColorUtilities.getInterpolatedColor(
						colorScheme.getLightColor(),
						colorScheme.getDarkColor(), 0.8);
		Color back2 = isDark ? colorScheme.getExtraLightColor()
				: SubstanceColorUtilities.getInterpolatedColor(
						colorScheme.getMidColor(), colorScheme.getDarkColor(),
						0.4);
		Color fore = isDark ? colorScheme.getDarkColor() : colorScheme
				.getUltraLightColor();

		int bumpDotDiameter = SubstanceSizeUtils
				.getDragBumpDiameter(SubstanceSizeUtils.getComponentFontSize(c));
		int bumpCellSize = (int) (1.5 * bumpDotDiameter + 1);
		if (isCrowded)
			bumpCellSize--;
		int bumpLines = dimension / bumpCellSize;

		int bumpOffset = (dimension - bumpCellSize * bumpLines) / 2;

		for (int col = 0; col < bumpLines; col++) {
			int cx = bumpOffset + col * bumpCellSize;
			for (int row = (bumpLines - col - 1); row < bumpLines; row++) {
				int cy = bumpOffset + row * bumpCellSize;
				graphics.setColor(fore);
				graphics.fillOval(cx + 1, cy + 1, bumpDotDiameter, bumpDotDiameter);
				graphics.setPaint(new GradientPaint(cx, cy, back1, 
						cx + bumpDotDiameter - 1, cy + bumpDotDiameter - 1, back2));
				graphics.fillOval(cx, cy, bumpDotDiameter, bumpDotDiameter);
			}
		}
		return result;
	}

	/**
	 * Retrieves tree icon.
	 * 
	 * @param tree
	 *            Tree.
	 * @param fillScheme
	 *            Icon fill color scheme.
	 * @param borderScheme
	 *            Icon border color scheme.
	 * @param isCollapsed
	 *            Collapsed state.
	 * @return Tree icon.
	 */
	public static BufferedImage getTreeIcon(JTree tree,
			SubstanceColorScheme fillScheme, SubstanceColorScheme borderScheme,
			boolean isCollapsed) {
		int fontSize = SubstanceSizeUtils.getComponentFontSize(tree);
		int dim = SubstanceSizeUtils.getTreeIconSize(fontSize);
		BufferedImage result = SubstanceCoreUtilities.getBlankImage(dim, dim);
		Graphics2D graphics = (Graphics2D) result.getGraphics();

		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_OFF);

		Graphics2D g2 = (Graphics2D) graphics.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_OFF);
		SimplisticFillPainter gradPainter = new SimplisticSoftBorderReverseFillPainter();
		FlatBorderPainter fbp = new FlatBorderPainter();

		float borderDelta = SubstanceSizeUtils.getBorderStrokeWidth(fontSize) / 2.0f;
		Shape contour = SubstanceOutlineUtilities.getBaseOutline(dim, dim, 
				SubstanceSizeUtils.getClassicButtonCornerRadius(dim) / 1.5f,
				null, borderDelta);

		//g2.translate(0, 1);

		boolean isDark = fillScheme.isDark();

		fillScheme = new ShiftColorScheme(fillScheme, fillScheme.getExtraLightColor(), 0.7);

		gradPainter.paintContourBackground(g2, tree, dim, dim, contour,
				false, fillScheme, false);
		borderScheme = new ShiftColorScheme(borderScheme,
				isDark ? borderScheme.getUltraLightColor()
						: borderScheme.getLightColor(), 0.5);
		fbp.paintBorder(g2, tree, dim, dim, contour, null, borderScheme);

		//g2.translate(-1, -1);

		Color signColor = isDark ? borderScheme.getUltraLightColor().brighter()
				.brighter() : borderScheme.getUltraDarkColor();
		if ((tree != null) && !tree.isEnabled())
			signColor = borderScheme.getForegroundColor();
		g2.setColor(signColor);
		int mid = dim / 2;
		int length = 7 * dim / 12;
		g2.drawLine(mid - length / 2, dim / 2, mid + length / 2, dim / 2);
		if (isCollapsed) {
			g2.drawLine(dim / 2, mid - length / 2, dim / 2, mid + length / 2);
		}
		g2.dispose();

		return result;
	}

	/**
	 * Retrieves a single crayon of the specified color and dimensions for the
	 * crayon panel in color chooser.
	 * 
	 * @param mainColor
	 *            Crayon main color.
	 * @param width
	 *            Crayon width.
	 * @param height
	 *            Crayon height.
	 * @return Crayon image.
	 */
	private static BufferedImage getSingleCrayon(Color mainColor, int width,
			int height) {
		BufferedImage image = SubstanceCoreUtilities.getBlankImage(width,
				height);

		int baseTop = (int) (0.2 * height);

		Graphics2D graphics = (Graphics2D) image.getGraphics().create();
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		int r = mainColor.getRed();
		int g = mainColor.getGreen();
		int b = mainColor.getBlue();
		// light coefficient
		double lc = 0.8;
		int lr = (int) (r + (255 - r) * lc);
		int lg = (int) (g + (255 - g) * lc);
		int lb = (int) (b + (255 - b) * lc);
		// dark coefficient
		double dc = 0.05;
		int dr = (int) ((1.0 - dc) * r);
		int dg = (int) ((1.0 - dc) * g);
		int db = (int) ((1.0 - dc) * b);

		Color lightColor = new Color(lr, lg, lb);
		Color darkColor = new Color(dr, dg, db);

		LinearGradientPaint paint = new LinearGradientPaint(0, 0, width, 0,
				new float[] { 0.0f, 0.3f, 0.5f, 0.9f, 1.0f }, new Color[] {
						lightColor, darkColor, darkColor, lightColor,
						lightColor }, CycleMethod.REPEAT);
		graphics.setPaint(paint);
		graphics.fillRect(0, baseTop, width, height);

		int dbwr = lr;
		int dbwg = lg;
		int dbwb = lb;
		int lbwr = 128 + dr / 4;
		int lbwg = 128 + dg / 4;
		int lbwb = 128 + db / 4;

		Color lightStripeColor = new Color(lbwr, lbwg, lbwb);
		Color darkStripeColor = new Color(dbwr, dbwg, dbwb);

		int stripeTop = (int) (0.35 * height);
		int stripeHeight = (int) (0.04 * height);
		LinearGradientPaint stripePaint = new LinearGradientPaint(0, 0, width,
				0, new float[] { 0.0f, 0.3f, 0.5f, 0.9f, 1.0f }, new Color[] {
						lightStripeColor, darkStripeColor, darkStripeColor,
						lightStripeColor, lightStripeColor },
				CycleMethod.REPEAT);
		graphics.setPaint(stripePaint);
		graphics.fillRect(0, stripeTop, width, stripeHeight);

		graphics.setColor(lightStripeColor);
		graphics.drawRect(0, stripeTop, width - 1, stripeHeight);

		// create cap path
		GeneralPath capPath = new GeneralPath();
		capPath.moveTo(0.5f * width - 3, 4);
		capPath.quadTo(0.5f * width, 0, 0.5f * width + 3, 4);
		capPath.lineTo(width - 3, baseTop);
		capPath.lineTo(2, baseTop);
		capPath.lineTo(0.5f * width - 3, 4);

		graphics.setClip(capPath);

		graphics.setPaint(new GradientPaint(0, baseTop / 2, lightColor,
				(int) (0.6 * width), baseTop, mainColor));
		graphics.fillRect(0, 0, width / 2, baseTop);
		graphics.setPaint(new GradientPaint(width, baseTop / 2, lightColor,
				(int) (0.4 * width), baseTop, mainColor));
		graphics.fillRect(width / 2, 0, width / 2, baseTop);

		graphics.setStroke(new BasicStroke((float) 1.3, BasicStroke.CAP_ROUND,
				BasicStroke.JOIN_ROUND));

		graphics.setClip(null);
		graphics.setColor(new Color(64 + dr / 2, 64 + dg / 2, 64 + db / 2, 200));
		graphics.drawRect(0, baseTop, width - 1, height - baseTop - 1);
		graphics.draw(capPath);

		graphics.dispose();

		return image;
	}

	/**
	 * Crayon colors.
	 */
	private final static int[] crayonColors = { 0x800000, // Cayenne
			0x808000, // Asparagus
			0x008000, // Clover
			0x008080, // Teal
			0x000080, // Midnight
			0x800080, // Plum
			0x7f7f7f, // Tin
			0x808080, // Nickel

			0x804000, // Mocha
			0x408000, // Fern
			0x008040, // Moss
			0x004080, // Ocean
			0x400080, // Eggplant
			0x800040, // Maroon
			0x666666, // Steel
			0x999999, // Aluminium

			0xff0000, // Maraschino
			0xffff00, // Lemon
			0x00ff00, // Spring
			0x00ffff, // Turquoise
			0x0000ff, // Blueberry
			0xff00ff, // Magenta
			0x4c4c4c, // Iron
			0xb3b3b3, // Magnesium

			0xff8000, // Tangerine
			0x80ff00, // Lime
			0x00ff80, // Sea Foam
			0x0080ff, // Aqua
			0x8000ff, // Grape
			0xff0080, // Strawberry
			0x333333, // Tungsten
			0xcccccc, // Silver

			0xff6666, // Salmon
			0xffff66, // Banana
			0x66ff66, // Flora
			0x66ffff, // Ice
			0x6666ff, // Orchid
			0xff66ff, // Bubblegum
			0x191919, // Lead
			0xe6e6e6, // Mercury

			0xffcc66, // Cantaloupe
			0xccff66, // Honeydew
			0x66ffcc, // Spindrift
			0x66ccff, // Sky
			0xcc66ff, // Lavender
			0xff6fcf, // Carnation
			0x000000, // Licorice
			0xffffff, // Snow
	};

	/**
	 * Retrieves crayon X offset.
	 * 
	 * @param i
	 *            Crayon index.
	 * @return Crayon X offset.
	 */
	private static int crayonX(int i) {
		return (i % 8) * 22 + 4 + ((i / 8) % 2) * 11;
	}

	/**
	 * Retrieves crayon Y offset.
	 * 
	 * @param i
	 *            Crayon index.
	 * @return Crayon Y offset.
	 */
	private static int crayonY(int i) {
		return (i / 8) * 20 + 23;
	}

	/**
	 * Retrieves crayons image for the crayon panel of color chooser.
	 * 
	 * @return Crayons image.
	 */
	public static Image getCrayonsImage() {
		int iw = 195;
		int ih = 208;
		Image image = SubstanceCoreUtilities.getBlankImage(iw, ih);
		Graphics2D graphics = (Graphics2D) image.getGraphics().create();
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		graphics.setColor(new Color(240, 240, 240));
		graphics.fillRect(0, 0, iw, ih);

		int scaleFactor = UIUtil.isRetina() ? 2 : 1;
		for (int i = 0; i < SubstanceImageCreator.crayonColors.length; i++) {
			Color crayonColor = new Color(
					0xff000000 | SubstanceImageCreator.crayonColors[i]);
			BufferedImage crayonImage = SubstanceImageCreator.getSingleCrayon(
					crayonColor, 22, 120);
			graphics.drawImage(crayonImage, SubstanceImageCreator.crayonX(i),
					SubstanceImageCreator.crayonY(i), crayonImage.getWidth() / scaleFactor, 
					crayonImage.getHeight() / scaleFactor, null);
		}

		graphics.setColor(new Color(190, 190, 190));
		graphics.drawRoundRect(0, 1, iw - 1, ih - 2, 4, 4);

		graphics.dispose();
		return image;
	}

	/**
	 * Returns small icon representation of the specified integer value. The
	 * remainder of dividing the integer by 16 is translated to four circles
	 * arranged in 2*2 grid.
	 * 
	 * @param value
	 *            Integer value to represent.
	 * @param colorScheme
	 *            Icon color scheme.
	 * @return Icon representation of the specified integer value.
	 */
	public static HiDpiAwareIcon getHexaMarker(int value, SubstanceColorScheme colorScheme) {
		BufferedImage result = SubstanceCoreUtilities.getBlankImage(9, 9);

		value %= 16;
		Color offColor = null;
		Color onColor = null;
		if (colorScheme == null) {
			return new HiDpiAwareIcon(result);
		}
		boolean isDark = colorScheme.isDark();
		offColor = isDark ? SubstanceColorUtilities.getInterpolatedColor(
				colorScheme.getUltraLightColor(), Color.white, 0.7) : colorScheme.getMidColor().darker();
		onColor = isDark ? SubstanceColorUtilities.getInterpolatedColor(
					colorScheme.getUltraLightColor(), Color.white, 0.2)
				: colorScheme.getUltraDarkColor().darker();

		boolean bit1 = ((value & 0x1) != 0);
		boolean bit2 = ((value & 0x2) != 0);
		boolean bit3 = ((value & 0x4) != 0);
		boolean bit4 = ((value & 0x8) != 0);

		Graphics2D graphics = (Graphics2D) result.getGraphics().create();
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		graphics.setColor(bit1 ? onColor : offColor);
		graphics.fillOval(5, 5, 4, 4);
		graphics.setColor(bit2 ? onColor : offColor);
		graphics.fillOval(5, 0, 4, 4);
		graphics.setColor(bit3 ? onColor : offColor);
		graphics.fillOval(0, 5, 4, 4);
		graphics.setColor(bit4 ? onColor : offColor);
		graphics.fillOval(0, 0, 4, 4);

		graphics.dispose();
		return new HiDpiAwareIcon(result);
	}

	/**
	 * Returns search icon.
	 * 
	 * @param dimension
	 *            Icon dimension.
	 * @param colorScheme
	 *            Icon color scheme.
	 * @param leftToRight
	 *            LTR indication of the resulting icon.
	 * @return Search icon.
	 */
	private static BufferedImage createSearchIcon(int dimension,
			SubstanceColorScheme colorScheme, boolean leftToRight) {
		HiDpiAwareIcon hiDpiAwareIcon = new HiDpiAwareIcon(System_search.of(16, 16));
		
		Color foregroundColor = SubstanceColorUtilities.getForegroundColor(colorScheme);
		Color forColorization = SubstanceColorUtilities.getAlphaColor(foregroundColor, 160);
		return hiDpiAwareIcon.colorize(forColorization).toImage();
	}
	
	public static HiDpiAwareIcon getSearchIcon(JComponent c, int dimension,
			boolean leftToRight) {
		SubstanceColorScheme colorScheme = SubstanceColorSchemeUtilities
				.getColorScheme(c, ComponentState.ENABLED);
		return new HiDpiAwareIcon(createSearchIcon(dimension, colorScheme, leftToRight));
	}
	
	public static HiDpiAwareIcon getSearchIconUiResource(int dimension,
			SubstanceColorScheme colorScheme, boolean leftToRight) {
		return new HiDpiAwareIconUiResource(createSearchIcon(dimension, colorScheme, leftToRight));
	}

	/**
	 * Returns an icon that matches the specified watermark.
	 * 
	 * @param watermark
	 *            Watermark instance.
	 * @return Icon that matches the specified watermark.
	 */
	public static Icon getWatermarkIcon(SubstanceWatermark watermark) {
		int iSize = SubstanceSizeUtils.getTitlePaneIconSize();
		BufferedImage result = SubstanceCoreUtilities.getBlankImage(iSize,
				iSize);
		Graphics2D graphics = (Graphics2D) result.getGraphics().create();
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		watermark
				.previewWatermark(graphics,
						SubstanceColorSchemeUtilities.METALLIC_SKIN, 0, 0,
						iSize, iSize);
		graphics.dispose();
		return new HiDpiAwareIcon(result);
	}

	/**
	 * Returns a lock icon that matches the specified scheme.
	 * 
	 * @param scheme
	 *            Scheme instance.
	 * @return Lock icon that matches the specified scheme.
	 */
	public static Icon getSmallLockIcon(SubstanceColorScheme scheme, Component c) {
		int componentFontSize = SubstanceSizeUtils.getComponentFontSize(c);
		int extraPadding = SubstanceSizeUtils.getExtraPadding(componentFontSize);
		int size = 9 + 2 * extraPadding;

		BufferedImage result = SubstanceCoreUtilities.getBlankImage(size, size);
		Locked.of(size, size).paintIcon(c, result.getGraphics(), 0, 0);
		float brightnessFactor = scheme.isDark() ? 1.0f : 0.3f;
		ColorSchemeFilter filter = ColorSchemeFilter.getColorSchemeFilter(scheme, brightnessFactor);
		return new HiDpiAwareIcon(filter.filter(result, null));
	}

	/**
	 * Creates a new version of the specified icon that is rendered in the
	 * colors of the specified color scheme.
	 * 
	 * @param comp
	 *            Component.
	 * @param original
	 *            The original icon.
	 * @param colorScheme
	 *            Color scheme.
	 * @return Scheme-based version of the original icon.
	 */
	public static BufferedImage getColorSchemeImage(Component comp,
			Icon original, SubstanceColorScheme colorScheme,
			float originalBrightnessFactor) {
		int w = original.getIconWidth();
		int h = original.getIconHeight();
		BufferedImage origImage = SubstanceCoreUtilities.getBlankImage(w, h);
		Graphics2D g2d = (Graphics2D) origImage.getGraphics().create();
		original.paintIcon(comp, origImage.getGraphics(), 0, 0);
		g2d.dispose();

		BufferedImage result = getColorSchemeImage(origImage, colorScheme,
				originalBrightnessFactor);
		return result;
	}

	/**
	 * Creates a new version of the specified image that is rendered in the
	 * colors of the specified color scheme.
	 * 
	 * @param original
	 *            The original image.
	 * @param colorScheme
	 *            Color scheme.
	 * @param toSaturate
	 *            Indicates whether the resulting image should be saturated.
	 * @return Scheme-based version of the original icon.
	 */
	public static BufferedImage getColorSchemeImage(BufferedImage original,
			SubstanceColorScheme colorScheme, float originalBrightnessFactor) {
		return ColorSchemeFilter.getColorSchemeFilter(colorScheme,
				originalBrightnessFactor).filter(original, null);
	}
}
