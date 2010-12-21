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
import org.pushingpixels.substance.internal.animation.StateTransitionTracker;
import org.pushingpixels.substance.internal.animation.TransitionAwareUI;

/**
 * Drop down button in <b>Substance</b> look and feel.
 * 
 * @author Kirill Grouchnikov
 */
public final class SubstanceDropDownButton extends JButton implements
		SubstanceInternalArrowButton {
	static {
		AnimationConfigurationManager.getInstance().disallowAnimations(
				AnimationFacet.GHOSTING_BUTTON_PRESS,
				SubstanceDropDownButton.class);
		AnimationConfigurationManager.getInstance().disallowAnimations(
				AnimationFacet.GHOSTING_ICON_ROLLOVER,
				SubstanceDropDownButton.class);
	}

	/**
	 * Simple constructor.
	 * 
	 * @param parent
	 *            The parent component.
	 */
	public SubstanceDropDownButton(JComponent parent) {
		super("");
		this.setModel(new DefaultButtonModel() {
			@Override
			public void setArmed(boolean armed) {
				super.setArmed(this.isPressed() || armed);
			}
		});
		this.setEnabled(parent.isEnabled());
		this.setFocusable(false);
		this.setRequestFocusEnabled(parent.isEnabled());

		int fontSize = SubstanceSizeUtils.getComponentFontSize(parent);
		int tbInset = SubstanceSizeUtils.getAdjustedSize(fontSize, 1, 2, 1,
				false);
		int lrInset = 0;
		this.setMargin(new Insets(tbInset, lrInset, tbInset, tbInset));

		this.setBorderPainted(false);
		this.putClientProperty(SubstanceLookAndFeel.FLAT_PROPERTY, Boolean.TRUE);
		this.setOpaque(false);
	}

	@Override
	public void setBorder(Border border) {
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

		int componentFontSize = SubstanceSizeUtils.getComponentFontSize(this);
		int borderDelta = (int) Math.floor(1.5 * SubstanceSizeUtils
				.getBorderStrokeWidth(componentFontSize));
		float radius = Math.max(0, 2.0f
				* SubstanceSizeUtils
						.getClassicButtonCornerRadius(componentFontSize)
				- borderDelta);

		int width = getWidth();
		int height = getHeight();

		int offsetX = this.getX();
		int offsetY = this.getY();
		JComponent parent = (JComponent) this.getParent();
		SubstanceColorScheme baseBorderScheme = SubstanceColorSchemeUtilities
				.getColorScheme(this, ColorSchemeAssociationKind.BORDER,
						currState);

		BufferedImage offscreen = SubstanceCoreUtilities.getBlankImage(width,
				height);
		Graphics2D g2offscreen = offscreen.createGraphics();

		SubstanceImageCreator.paintTextComponentBorder(this, g2offscreen, 0, 0,
				width, height, radius, baseBorderScheme);
		g2offscreen.translate(-offsetX, -offsetY);
		SubstanceImageCreator.paintTextComponentBorder(parent, g2offscreen, 0,
				0, parent.getWidth(), parent.getHeight(), radius,
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
					.getColorScheme(this, ColorSchemeAssociationKind.BORDER,
							activeState);

			SubstanceImageCreator.paintTextComponentBorder(this, g2offscreen,
					0, 0, width, height, radius, borderScheme);
			g2offscreen.translate(-offsetX, -offsetY);
			SubstanceImageCreator.paintTextComponentBorder(parent, g2offscreen,
					0, 0, parent.getWidth(), parent.getHeight(), radius,
					borderScheme);
			g2offscreen.translate(offsetX, offsetY);
		}
		g2offscreen.dispose();

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
