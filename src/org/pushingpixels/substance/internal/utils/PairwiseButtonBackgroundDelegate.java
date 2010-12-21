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
package org.pushingpixels.substance.internal.utils;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

import javax.swing.AbstractButton;

import org.pushingpixels.lafwidget.LafWidgetUtilities;
import org.pushingpixels.substance.api.ColorSchemeAssociationKind;
import org.pushingpixels.substance.api.ComponentState;
import org.pushingpixels.substance.api.SubstanceColorScheme;
import org.pushingpixels.substance.api.SubstanceConstants;
import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import org.pushingpixels.substance.api.SubstanceConstants.Side;
import org.pushingpixels.substance.api.painter.border.SubstanceBorderPainter;
import org.pushingpixels.substance.api.painter.fill.MatteFillPainter;
import org.pushingpixels.substance.api.painter.fill.SubstanceFillPainter;
import org.pushingpixels.substance.api.shaper.RectangularButtonShaper;
import org.pushingpixels.substance.api.shaper.SubstanceButtonShaper;
import org.pushingpixels.substance.internal.animation.StateTransitionTracker;
import org.pushingpixels.substance.internal.animation.TransitionAwareUI;

/**
 * Delegate class for painting backgrounds of buttons in <b>Substance </b> look
 * and feel. This class is <b>for internal use only</b>.
 * 
 * @author Kirill Grouchnikov
 */
public class PairwiseButtonBackgroundDelegate {
	/**
	 * Cache for background images for pairwise backgrounds. Each time
	 * {@link #getPairwiseBackground(AbstractButton, int, int, Side)} is called,
	 * it checks <code>this</code> map to see if it already contains such
	 * background. If so, the background from the map is returned.
	 */
	private static LazyResettableHashMap<BufferedImage> pairwiseBackgrounds = new LazyResettableHashMap<BufferedImage>(
			"PairwiseButtonBackgroundDelegate");

	/**
	 * Paints background image for the specified button in button pair (such as
	 * scrollbar arrows, for example).
	 * 
	 * @param g
	 *            Graphics context.
	 * @param button
	 *            Button.
	 * @param painter
	 *            Gradient painter.
	 * @param width
	 *            Button width.
	 * @param height
	 *            Button height.
	 * @param side
	 *            Button orientation.
	 * @param toIgnoreOpenSides
	 *            If <code>true</code>, the open side setting (controlled by the
	 *            {@link SubstanceLookAndFeel#BUTTON_OPEN_SIDE_PROPERTY} is
	 *            ignored.
	 */
	public static void updatePairwiseBackground(Graphics g,
			AbstractButton button, int width, int height,
			SubstanceConstants.Side side, boolean toIgnoreOpenSides) {
		if (SubstanceCoreUtilities.isButtonNeverPainted(button))
			return;

		SubstanceButtonShaper shaper = SubstanceCoreUtilities
				.getButtonShaper(button);

		TransitionAwareUI transitionAwareUI = (TransitionAwareUI) button
				.getUI();
		StateTransitionTracker stateTransitionTracker = transitionAwareUI
				.getTransitionTracker();
		StateTransitionTracker.ModelStateInfo modelStateInfo = stateTransitionTracker
				.getModelStateInfo();

		ComponentState currState = modelStateInfo.getCurrModelState();
		SubstanceColorScheme baseFillScheme = SubstanceColorSchemeUtilities
				.getColorScheme(button, currState);
		SubstanceColorScheme baseBorderScheme = SubstanceColorSchemeUtilities
				.getColorScheme(button, ColorSchemeAssociationKind.BORDER,
						currState);

		SubstanceFillPainter fillPainter = SubstanceCoreUtilities
				.isSpinnerButton(button) ? MatteFillPainter.INSTANCE
				: SubstanceImageCreator.SimplisticSoftBorderReverseFillPainter.INSTANCE;

		BufferedImage baseLayer = getPairwiseFullAlphaBackground(button,
				fillPainter, shaper, width, height, side, baseFillScheme,
				baseBorderScheme, toIgnoreOpenSides);
		BufferedImage fullOpacity = null;

		Map<ComponentState, StateTransitionTracker.StateContributionInfo> activeStates = modelStateInfo
				.getStateContributionMap();

		if (currState.isDisabled() || (activeStates.size() == 1)) {
			fullOpacity = baseLayer;
		} else {
			fullOpacity = SubstanceCoreUtilities.getBlankImage(baseLayer
					.getWidth(), baseLayer.getHeight());
			Graphics2D g2fullOpacity = fullOpacity.createGraphics();

			// draw the base layer
			g2fullOpacity.drawImage(baseLayer, 0, 0, null);

			for (Map.Entry<ComponentState, StateTransitionTracker.StateContributionInfo> activeEntry : activeStates
					.entrySet()) {
				ComponentState activeState = activeEntry.getKey();
				if (activeState == currState)
					continue;

				float contribution = activeEntry.getValue().getContribution();
				if (contribution == 0.0f)
					continue;

				SubstanceColorScheme fillScheme = SubstanceColorSchemeUtilities
						.getColorScheme(button, activeState);
				SubstanceColorScheme borderScheme = SubstanceColorSchemeUtilities
						.getColorScheme(button,
								ColorSchemeAssociationKind.BORDER, activeState);
				BufferedImage layer = getPairwiseFullAlphaBackground(button,
						fillPainter, shaper, width, height, side, fillScheme,
						borderScheme, toIgnoreOpenSides);

				g2fullOpacity.setComposite(AlphaComposite.SrcOver
						.derive(contribution));
				g2fullOpacity.drawImage(layer, 0, 0, null);
			}

			g2fullOpacity.dispose();
		}

		boolean isFlat = SubstanceCoreUtilities.hasFlatAppearance(button);
		boolean isSpecial = isFlat || !button.isEnabled();
		float extraAlpha = 1.0f;

		if (isSpecial) {
			if (isFlat) {
				// Special handling of flat buttons
				extraAlpha = 0.0f;
				for (Map.Entry<ComponentState, StateTransitionTracker.StateContributionInfo> activeEntry : activeStates
						.entrySet()) {
					ComponentState activeState = activeEntry.getKey();
					if (activeState.isDisabled())
						continue;
					if (activeState == ComponentState.ENABLED)
						continue;
					extraAlpha += activeEntry.getValue().getContribution();
				}
			} else {
				if (!button.isEnabled()) {
					extraAlpha = SubstanceColorSchemeUtilities.getAlpha(button,
							currState);
				}
			}
		}
		if (extraAlpha > 0.0f) {
			Graphics2D graphics = (Graphics2D) g.create();
			graphics.setComposite(LafWidgetUtilities.getAlphaComposite(button,
					extraAlpha, g));
			graphics.drawImage(fullOpacity, 0, 0, null);
			graphics.dispose();
		}
	}

	/**
	 * Retrieves background image for the specified button in button pair (such
	 * as scrollbar arrows, for example).
	 * 
	 * @param button
	 *            Button.
	 * @param kind
	 *            Color scheme kind.
	 * @param fillPainter
	 *            Gradient painter.
	 * @param width
	 *            Button width.
	 * @param height
	 *            Button height.
	 * @param side
	 *            Button orientation.
	 * @param cyclePos
	 *            Cycle position.
	 * @param colorScheme
	 *            The fill color scheme.
	 * @param borderScheme
	 *            The border color scheme.
	 * @param graphicsComposite
	 *            Composite to apply before painting the button.
	 * @param toIgnoreOpenSides
	 *            If <code>true</code>, the open side setting (controlled by the
	 *            {@link SubstanceLookAndFeel#BUTTON_OPEN_SIDE_PROPERTY} is
	 *            ignored.
	 * @return Button background image.
	 */
	private static BufferedImage getPairwiseFullAlphaBackground(
			AbstractButton button, SubstanceFillPainter fillPainter,
			SubstanceButtonShaper shaper, int width, int height,
			SubstanceConstants.Side side, SubstanceColorScheme colorScheme,
			SubstanceColorScheme borderScheme, boolean toIgnoreOpenSides) {
		if (SubstanceCoreUtilities.isButtonNeverPainted(button))
			return null;
		Set<Side> openSides = toIgnoreOpenSides ? EnumSet.noneOf(Side.class)
				: SubstanceCoreUtilities.getSides(button,
						SubstanceLookAndFeel.BUTTON_OPEN_SIDE_PROPERTY);
		// boolean noBorder = SubstanceCoreUtilities.isSpinnerButton(button);
		// && !button.getParent().isEnabled();
		boolean isBorderPainted = button.isBorderPainted();
		boolean isContentAreaFilled = button.isContentAreaFilled();

		// buttons will be rectangular to make two scrolls (horizontal
		// and vertical) touch the corners.
		// int radius = 0;

		float radius = 0.0f;
		if (SubstanceCoreUtilities.isSpinnerButton(button)
				&& shaper instanceof RectangularButtonShaper) {
			radius = ((RectangularButtonShaper) shaper).getCornerRadius(button,
					null);
		}

		HashMapKey key = SubstanceCoreUtilities.getHashKey(width, height, side,
				openSides, colorScheme.getDisplayName(), borderScheme
						.getDisplayName(), button.getClass().getName(),
				fillPainter.getDisplayName(), shaper.getDisplayName(),
				isBorderPainted, isContentAreaFilled, radius);
		// System.out.println("\tKey " + key);
		BufferedImage finalBackground = pairwiseBackgrounds.get(key);
		if (finalBackground == null) {
			// System.out.println("\tNot found");

			int deltaLeft = (openSides != null)
					&& openSides.contains(Side.LEFT) ? 3 : 0;
			int deltaRight = (openSides != null)
					&& openSides.contains(Side.RIGHT) ? 3 : 0;
			int deltaTop = (openSides != null) && openSides.contains(Side.TOP) ? 3
					: 0;
			int deltaBottom = (openSides != null)
					&& openSides.contains(Side.BOTTOM) ? 3 : 0;

			GeneralPath contour = null;

			SubstanceBorderPainter borderPainter = SubstanceCoreUtilities
					.getBorderPainter(button);

			int borderDelta = (int) Math.floor(SubstanceSizeUtils
					.getBorderStrokeWidth(SubstanceSizeUtils
							.getComponentFontSize(button)) / 2.0);
			finalBackground = SubstanceCoreUtilities.getBlankImage(width,
					height);
			Graphics2D finalGraphics = (Graphics2D) finalBackground
					.getGraphics();
			// finalGraphics.setColor(Color.red);
			// finalGraphics.fillRect(0, 0, width, height);
			finalGraphics.translate(-deltaLeft, -deltaTop);
			if (side != null) {
				switch (side) {
				case TOP:
				case BOTTOM:
					// rotate by 90% for better visuals
					contour = SubstanceOutlineUtilities.getBaseOutline(height
							+ deltaTop + deltaBottom, width + deltaLeft
							+ deltaRight, radius, null, borderDelta);

					int translateY = height;
					if (SubstanceCoreUtilities.isScrollButton(button)) {
						translateY += (1 + ((side == SubstanceConstants.Side.BOTTOM) ? 1
								: -2));
					}
					AffineTransform at = AffineTransform.getTranslateInstance(
							0, translateY);
					at.rotate(-Math.PI / 2);
					finalGraphics.setTransform(at);

					if (isContentAreaFilled) {
						fillPainter.paintContourBackground(finalGraphics,
								button, height + deltaTop + deltaBottom, width
										+ deltaLeft + deltaRight, contour,
								false, colorScheme, true);
					}
					if (isBorderPainted) {
						borderPainter.paintBorder(finalGraphics, button, height
								+ deltaTop + deltaBottom, width + deltaLeft
								+ deltaRight, contour, null, borderScheme);
					}
					break;
				case RIGHT:
				case LEFT:
					// arrow in horizontal bar
					contour = SubstanceOutlineUtilities.getBaseOutline(width
							+ deltaLeft + deltaRight, height + deltaTop
							+ deltaBottom, radius, null, borderDelta);

					if (isContentAreaFilled) {
						fillPainter.paintContourBackground(finalGraphics,
								button, width + deltaLeft + deltaRight, height
										+ deltaTop + deltaBottom, contour,
								false, colorScheme, true);
					}
					if (isBorderPainted) {
						borderPainter.paintBorder(finalGraphics, button, width
								+ deltaLeft + deltaRight, height + deltaTop
								+ deltaBottom, contour, null, borderScheme);
					}
					break;
				}
			} else {
				contour = SubstanceOutlineUtilities.getBaseOutline(width
						+ deltaLeft + deltaRight, height + deltaTop
						+ deltaBottom, radius, null, borderDelta);

				fillPainter.paintContourBackground(finalGraphics, button, width
						+ deltaLeft + deltaRight, height + deltaTop
						+ deltaBottom, contour, false, colorScheme, true);
				if (isBorderPainted) {
					borderPainter.paintBorder(finalGraphics, button, width
							+ deltaLeft + deltaRight, height + deltaTop
							+ deltaBottom, contour, null, borderScheme);
				}
			}

			// System.out.println("\tCreated new background " + width + ":" +
			// height);
			pairwiseBackgrounds.put(key, finalBackground);
		}
		return finalBackground;
	}
}
