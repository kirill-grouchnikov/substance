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

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.Border;

import org.pushingpixels.lafwidget.LafWidgetUtilities;
import org.pushingpixels.lafwidget.animation.AnimationConfigurationManager;
import org.pushingpixels.lafwidget.animation.AnimationFacet;
import org.pushingpixels.substance.api.*;
import org.pushingpixels.substance.api.shaper.ClassicButtonShaper;
import org.pushingpixels.substance.internal.animation.StateTransitionTracker;
import org.pushingpixels.substance.internal.animation.TransitionAwareUI;
import org.pushingpixels.substance.internal.utils.border.SubstanceButtonBorder;

/**
 * Spinner button in <b>Substance</b> look and feel.
 * 
 * @author Kirill Grouchnikov
 */
public class SubstanceSpinnerButton extends JButton implements Sideable,
		SubstanceInternalArrowButton {
	static {
		AnimationConfigurationManager.getInstance().disallowAnimations(
				AnimationFacet.GHOSTING_BUTTON_PRESS,
				SubstanceSpinnerButton.class);
		AnimationConfigurationManager.getInstance().disallowAnimations(
				AnimationFacet.GHOSTING_ICON_ROLLOVER,
				SubstanceSpinnerButton.class);
	}

	/**
	 * Button orientation.
	 */
	private int orientation;

	private abstract static class SpinnerButtonBorder extends
			SubstanceButtonBorder {
		public SpinnerButtonBorder(Class<?> buttonShaperClass) {
			super(buttonShaperClass);
		}
	}

	/**
	 * Simple constructor.
	 * 
	 * @param spinner
	 *            The owner spinner.
	 * @param orientation
	 *            The orientation of the spinner icon arrow.
	 */
	public SubstanceSpinnerButton(JSpinner spinner, final int orientation) {
		this.setEnabled(spinner.isEnabled());
		this.setFocusable(false);
		this.setRequestFocusEnabled(false);
		this.setMargin(new Insets(0, 0, 0, 2));
		this.setBorder(new SpinnerButtonBorder(ClassicButtonShaper.class) {
			public Insets getBorderInsets(Component c) {
				int extraPadding = SubstanceSizeUtils
						.getExtraPadding(SubstanceSizeUtils
								.getComponentFontSize(c));
				// Bring the icons closer together instead of
				// having them centered in the spinner buttons
				int delta = SubstanceSizeUtils.getAdjustedSize(
						SubstanceSizeUtils.getComponentFontSize(c), 3, 3, 1,
						false);
				int deltaTop = (orientation == SwingConstants.NORTH) ? delta
						: 0;
				int deltaBottom = (orientation == SwingConstants.NORTH) ? 0
						: delta;
				return new Insets(extraPadding + deltaTop, extraPadding,
						extraPadding + deltaBottom, extraPadding);
			}
		});
		this.orientation = orientation;

		this.setOpaque(false);
		this.setBorderPainted(false);
		this.putClientProperty(SubstanceLookAndFeel.FLAT_PROPERTY, Boolean.TRUE);
	}

	@Override
	public void setBorder(Border border) {
		if (border instanceof SpinnerButtonBorder) {
			super.setBorder(border);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.Component#isFocusable()
	 */
	@Override
	public boolean isFocusable() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pushingpixels.substance.utils.Sideable#getSide()
	 */
	public SubstanceConstants.Side getSide() {
		switch (this.orientation) {
		case SwingConstants.NORTH:
			return SubstanceConstants.Side.BOTTOM;
		case SwingConstants.WEST:
			return SubstanceConstants.Side.RIGHT;
		case SwingConstants.SOUTH:
			return SubstanceConstants.Side.TOP;
		case SwingConstants.EAST:
			return SubstanceConstants.Side.LEFT;
		default:
			return null;
		}
	}

	@Override
	protected void paintBorder(Graphics g) {
		if (SubstanceCoreUtilities.isButtonNeverPainted(this)) {
			return;
		}

		TransitionAwareUI transitionAwareUI = (TransitionAwareUI) this.getUI();
		StateTransitionTracker stateTransitionTracker = transitionAwareUI
				.getTransitionTracker();
		StateTransitionTracker.ModelStateInfo modelStateInfo = stateTransitionTracker
				.getModelStateInfo();
		Map<ComponentState, StateTransitionTracker.StateContributionInfo> activeStates = modelStateInfo
				.getStateContributionMap();

		ComponentState currState = modelStateInfo.getCurrModelState();
		float extraAlpha = stateTransitionTracker.getActiveStrength();

		if (currState == ComponentState.DISABLED_UNSELECTED)
			extraAlpha = 0.0f;

		if (extraAlpha == 0.0f)
			return;

		boolean isNextButton = "Spinner.nextButton".equals(this.getName());

		int componentFontSize = SubstanceSizeUtils.getComponentFontSize(this);
		int borderDelta = (int) Math.floor(1.5 * SubstanceSizeUtils
				.getBorderStrokeWidth(componentFontSize));
		float radius = Math.max(0, 2.0f
				* SubstanceSizeUtils
						.getClassicButtonCornerRadius(componentFontSize)
				- borderDelta);

		int width = getWidth();
		int height = getHeight();

		JSpinner parent = (JSpinner) this.getParent();
		BufferedImage offscreen = SubstanceCoreUtilities.getBlankImage(width,
				height);
		Graphics2D g2offscreen = offscreen.createGraphics();
		int offsetX = this.getX();
		int offsetY = this.getY();
		SubstanceColorScheme baseBorderScheme = SubstanceColorSchemeUtilities
				.getColorScheme(this, ColorSchemeAssociationKind.BORDER,
						currState);

		if (isNextButton) {
			SubstanceImageCreator.paintTextComponentBorder(this, g2offscreen,
					0, 0, width, 1 * height, radius, baseBorderScheme);
			g2offscreen.translate(-offsetX, -offsetY);
			SubstanceImageCreator.paintTextComponentBorder(parent, g2offscreen,
					0, 0, parent.getWidth(), parent.getHeight(), radius,
					baseBorderScheme);
			g2offscreen.translate(offsetX, offsetY);

			for (Map.Entry<ComponentState, StateTransitionTracker.StateContributionInfo> activeEntry : activeStates
					.entrySet()) {
				ComponentState activeState = activeEntry.getKey();
				if (activeState == currState)
					continue;

				float contribution = activeEntry.getValue().getContribution();
				if (contribution == 0.0f)
					continue;

				g2offscreen.setComposite(AlphaComposite.SrcOver
						.derive(contribution));
				SubstanceColorScheme borderScheme = SubstanceColorSchemeUtilities
						.getColorScheme(this,
								ColorSchemeAssociationKind.BORDER, activeState);

				SubstanceImageCreator.paintTextComponentBorder(this,
						g2offscreen, 0, 0, width, 1 * height, radius,
						borderScheme);
				g2offscreen.translate(-offsetX, -offsetY);
				SubstanceImageCreator.paintTextComponentBorder(parent,
						g2offscreen, 0, 0, parent.getWidth(), parent
								.getHeight(), radius, borderScheme);
				g2offscreen.translate(offsetX, offsetY);
			}
		} else {
			SubstanceImageCreator.paintTextComponentBorder(this, g2offscreen,
					0, 0, width, 1 * height, radius, baseBorderScheme);
			g2offscreen.translate(-offsetX, -offsetY);
			SubstanceImageCreator.paintTextComponentBorder(parent, g2offscreen,
					0, 0, parent.getWidth(), parent.getHeight(), radius,
					baseBorderScheme);
			g2offscreen.translate(offsetX, offsetY);

			for (Map.Entry<ComponentState, StateTransitionTracker.StateContributionInfo> activeEntry : activeStates
					.entrySet()) {
				ComponentState activeState = activeEntry.getKey();
				if (activeState == currState)
					continue;

				float contribution = activeEntry.getValue().getContribution();
				if (contribution == 0.0f)
					continue;

				g2offscreen.setComposite(AlphaComposite.SrcOver
						.derive(contribution));
				SubstanceColorScheme borderScheme = SubstanceColorSchemeUtilities
						.getColorScheme(this,
								ColorSchemeAssociationKind.BORDER, activeState);

				SubstanceImageCreator.paintTextComponentBorder(this,
						g2offscreen, 0, 0, width, 1 * height, radius,
						borderScheme);
				g2offscreen.translate(-offsetX, -offsetY);
				SubstanceImageCreator.paintTextComponentBorder(parent,
						g2offscreen, 0, 0, parent.getWidth(), parent
								.getHeight(), radius, borderScheme);
				g2offscreen.translate(offsetX, offsetY);
			}
		}
		g2offscreen.dispose();

		// System.out.println(prevState + ":" + currState + ":" + extraAlpha);
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setComposite(LafWidgetUtilities.getAlphaComposite(this, extraAlpha,
				g));
		g2d.drawImage(offscreen, 0, 0, null);
		g2d.dispose();
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();

		int componentFontSize = SubstanceSizeUtils.getComponentFontSize(this);
		int width = getWidth();
		int height = getHeight();
		int clipDelta = (int) SubstanceSizeUtils
				.getBorderStrokeWidth(componentFontSize);

		if (this.getComponentOrientation().isLeftToRight()) {
			g2d.clipRect(clipDelta, 0, width - clipDelta, height);
		} else {
			g2d.clipRect(0, 0, width - clipDelta, height);
		}
		super.paint(g2d);
		g2d.dispose();
	}
}
