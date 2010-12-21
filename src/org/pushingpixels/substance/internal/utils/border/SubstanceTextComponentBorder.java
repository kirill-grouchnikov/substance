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
package org.pushingpixels.substance.internal.utils.border;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.border.Border;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.UIResource;
import javax.swing.text.JTextComponent;

import org.pushingpixels.substance.api.*;
import org.pushingpixels.substance.internal.animation.StateTransitionTracker;
import org.pushingpixels.substance.internal.animation.TransitionAwareUI;
import org.pushingpixels.substance.internal.utils.*;

/**
 * Text component border for the <b>Substance</b> look and feel.
 * 
 * @author Kirill Grouchnikov
 */
public class SubstanceTextComponentBorder implements Border, UIResource {
	/**
	 * Insets of <code>this</code> border.
	 */
	protected Insets myInsets;

	/**
	 * Cache of small border images.
	 */
	private static LazyResettableHashMap<BufferedImage> smallImageCache = new LazyResettableHashMap<BufferedImage>(
			"SubstanceTextComponentBorder");

	/**
	 * Creates a new border with the specified insets.
	 * 
	 * @param insets
	 *            Insets.
	 */
	public SubstanceTextComponentBorder(Insets insets) {
		this.myInsets = new Insets(insets.top, insets.left, insets.bottom,
				insets.right);
	}

	/**
	 * Paints border instance for the specified component.
	 * 
	 * @param c
	 *            The component.
	 * @param g
	 *            Graphics context.
	 * @param x
	 *            Component left X (in graphics context).
	 * @param y
	 *            Component top Y (in graphics context).
	 * @param width
	 *            Component width.
	 * @param height
	 *            Component height.
	 * @param isEnabled
	 *            Component enabled status.
	 * @param hasFocus
	 *            Component focus ownership status.
	 * @param alpha
	 *            Alpha value.
	 */
	private void paintBorder(JComponent c, Graphics g, int x, int y, int width,
			int height, boolean isEnabled, boolean hasFocus) {
		// failsafe for LAF change
		if (!SubstanceLookAndFeel.isCurrentLookAndFeel()) {
			return;
		}

		if ((width <= 0) || (height <= 0))
			return;

		Graphics2D graphics = (Graphics2D) g.create();

		// float cyclePos = 1.0f;

		float radius = 2.0f * SubstanceSizeUtils
				.getClassicButtonCornerRadius(SubstanceSizeUtils
						.getComponentFontSize(c));

		JTextComponent componentForTransitions = SubstanceCoreUtilities
				.getTextComponentForTransitions(c);

		if (componentForTransitions != null) {
			ComponentUI ui = componentForTransitions.getUI();
			if (ui instanceof TransitionAwareUI) {
				TransitionAwareUI trackable = (TransitionAwareUI) ui;
				StateTransitionTracker stateTransitionTracker = trackable
						.getTransitionTracker();
				StateTransitionTracker.ModelStateInfo modelStateInfo = stateTransitionTracker
						.getModelStateInfo();
				Map<ComponentState, StateTransitionTracker.StateContributionInfo> activeStates = modelStateInfo
						.getStateContributionMap();
				ComponentState currState = modelStateInfo.getCurrModelState();
				if (currState.isDisabled())
					currState = ComponentState.DISABLED_SELECTED;

				if (width * height < 100000) {
					SubstanceColorScheme baseBorderScheme = SubstanceColorSchemeUtilities
							.getColorScheme(componentForTransitions,
									ColorSchemeAssociationKind.BORDER,
									currState);

					HashMapKey baseKey = SubstanceCoreUtilities.getHashKey(
							SubstanceSizeUtils.getComponentFontSize(c), width,
							height, radius, baseBorderScheme.getDisplayName());
					BufferedImage baseLayer = smallImageCache.get(baseKey);
					float baseAlpha = SubstanceColorSchemeUtilities.getAlpha(c,
							currState);

					if (baseLayer == null) {
						baseLayer = SubstanceCoreUtilities.getBlankImage(width,
								height);
						Graphics2D g2d = baseLayer.createGraphics();
						SubstanceImageCreator.paintTextComponentBorder(c, g2d,
								0, 0, width, height, radius, baseBorderScheme);
						g2d.dispose();
						smallImageCache.put(baseKey, baseLayer);
					}

					graphics.setComposite(AlphaComposite.SrcOver
							.derive(baseAlpha));
					graphics.drawImage(baseLayer, x, y, null);

					if (!currState.isDisabled() && (activeStates.size() > 1)) {
						for (Map.Entry<ComponentState, StateTransitionTracker.StateContributionInfo> activeEntry : activeStates
								.entrySet()) {
							ComponentState activeState = activeEntry.getKey();
							if (activeState == currState)
								continue;

							float contribution = activeEntry.getValue()
									.getContribution();
							if (contribution == 0.0f)
								continue;

							SubstanceColorScheme borderScheme = SubstanceColorSchemeUtilities
									.getColorScheme(componentForTransitions,
											ColorSchemeAssociationKind.BORDER,
											activeState);

							HashMapKey key = SubstanceCoreUtilities.getHashKey(
									SubstanceSizeUtils.getComponentFontSize(c),
									width, height, radius, borderScheme
											.getDisplayName());
							BufferedImage layer = smallImageCache.get(key);
							float alpha = SubstanceColorSchemeUtilities
									.getAlpha(c, activeState);

							if (layer == null) {
								layer = SubstanceCoreUtilities.getBlankImage(
										width, height);
								Graphics2D g2d = layer.createGraphics();
								SubstanceImageCreator.paintTextComponentBorder(
										c, g2d, 0, 0, width, height, radius,
										borderScheme);
								g2d.dispose();
								smallImageCache.put(key, layer);
							}

							graphics.setComposite(AlphaComposite.SrcOver
									.derive(alpha * contribution));
							graphics.drawImage(layer, x, y, null);
						}
					}

				} else {
					// for borders larger than 100000 pixels, use simple
					// painting
					graphics.translate(x, y);

					SubstanceColorScheme baseBorderScheme = SubstanceColorSchemeUtilities
							.getColorScheme(componentForTransitions,
									ColorSchemeAssociationKind.BORDER,
									currState);
					float baseAlpha = SubstanceColorSchemeUtilities.getAlpha(c,
							currState);
					graphics.setComposite(AlphaComposite.SrcOver
							.derive(baseAlpha));
					SubstanceImageCreator.paintSimpleBorder(c, graphics, width,
							height, baseBorderScheme);

					if (!currState.isDisabled() && (activeStates.size() > 1)) {
						for (Map.Entry<ComponentState, StateTransitionTracker.StateContributionInfo> activeEntry : activeStates
								.entrySet()) {
							ComponentState activeState = activeEntry.getKey();
							if (activeState == currState)
								continue;

							float contribution = activeEntry.getValue()
									.getContribution();
							if (contribution == 0.0f)
								continue;

							SubstanceColorScheme borderScheme = SubstanceColorSchemeUtilities
									.getColorScheme(componentForTransitions,
											ColorSchemeAssociationKind.BORDER,
											activeState);
							float alpha = SubstanceColorSchemeUtilities
									.getAlpha(c, activeState);
							graphics.setComposite(AlphaComposite.SrcOver
									.derive(alpha * contribution));
							SubstanceImageCreator.paintSimpleBorder(c,
									graphics, width, height, borderScheme);
						}
					}
				}
			}
		} else {
			ComponentState currState = isEnabled ? ComponentState.ENABLED
					: ComponentState.DISABLED_UNSELECTED;
			SubstanceColorScheme borderColorScheme = SubstanceColorSchemeUtilities
					.getColorScheme(c, ColorSchemeAssociationKind.BORDER,
							currState);
			if (width * height < 100000) {
				HashMapKey hashKey = SubstanceCoreUtilities.getHashKey(
						SubstanceSizeUtils.getComponentFontSize(c), width,
						height, radius, borderColorScheme.getDisplayName());
				BufferedImage result = smallImageCache.get(hashKey);
				if (result == null) {
					result = SubstanceCoreUtilities
							.getBlankImage(width, height);
					Graphics2D g2d = result.createGraphics();
					SubstanceImageCreator.paintTextComponentBorder(c, g2d, 0,
							0, width, height, radius, borderColorScheme);
					g2d.dispose();
					smallImageCache.put(hashKey, result);
				}
				graphics.drawImage(result, x, y, null);
			} else {
				// for borders larger than 100000 pixels, use simple
				// painting
				graphics.translate(x, y);
				SubstanceImageCreator.paintSimpleBorder(c, graphics, width,
						height, borderColorScheme);
			}
		}

		graphics.dispose();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.border.Border#paintBorder(java.awt.Component,
	 * java.awt.Graphics, int, int, int, int)
	 */
	public void paintBorder(Component c, Graphics g, int x, int y, int width,
			int height) {
		paintBorder((JComponent) c, g, x, y, width, height, c.isEnabled(), c
				.hasFocus());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.border.Border#getBorderInsets(java.awt.Component)
	 */
	public Insets getBorderInsets(Component c) {
		return this.myInsets;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.border.Border#isBorderOpaque()
	 */
	public boolean isBorderOpaque() {
		return false;
	}
}
