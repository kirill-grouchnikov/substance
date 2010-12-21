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
package org.pushingpixels.substance.internal.ui;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.*;
import javax.swing.plaf.basic.BasicBorders;
import javax.swing.plaf.basic.BasicTextFieldUI;

import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import org.pushingpixels.substance.internal.animation.StateTransitionTracker;
import org.pushingpixels.substance.internal.animation.TransitionAwareUI;
import org.pushingpixels.substance.internal.utils.*;
import org.pushingpixels.substance.internal.utils.border.SubstanceTextComponentBorder;
import org.pushingpixels.trident.swing.SwingRepaintCallback;

/**
 * UI for text fields in <b>Substance</b> look and feel.
 * 
 * @author Kirill Grouchnikov
 */
public class SubstanceTextFieldUI extends BasicTextFieldUI implements
		TransitionAwareUI {
	protected StateTransitionTracker stateTransitionTracker;

	/**
	 * The associated text field.
	 */
	protected JTextField textField;

	/**
	 * Property change listener.
	 */
	protected PropertyChangeListener substancePropertyChangeListener;

	/**
	 * Listener for transition animations.
	 */
	private RolloverTextControlListener substanceRolloverListener;

	/**
	 * Surrogate button model for tracking the state transitions.
	 */
	private ButtonModel transitionModel;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.plaf.ComponentUI#createUI(javax.swing.JComponent)
	 */
	public static ComponentUI createUI(JComponent comp) {
		SubstanceCoreUtilities.testComponentCreationThreadingViolation(comp);
		return new SubstanceTextFieldUI(comp);
	}

	/**
	 * Simple constructor.
	 * 
	 * @param c
	 *            Component (text field).
	 */
	public SubstanceTextFieldUI(JComponent c) {
		super();
		this.textField = (JTextField) c;

		this.transitionModel = new DefaultButtonModel();
		this.transitionModel.setArmed(false);
		this.transitionModel.setSelected(false);
		this.transitionModel.setPressed(false);
		this.transitionModel.setRollover(false);
		this.transitionModel.setEnabled(this.textField.isEnabled());

		this.stateTransitionTracker = new StateTransitionTracker(
				this.textField, this.transitionModel);
		this.stateTransitionTracker
				.setRepaintCallback(new StateTransitionTracker.RepaintCallback() {
					@Override
					public SwingRepaintCallback getRepaintCallback() {
						return SubstanceCoreUtilities
								.getTextComponentRepaintCallback(textField);
					}
				});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.swing.plaf.basic.BasicTextUI#paintBackground(java.awt.Graphics)
	 */
	@Override
	protected void paintBackground(Graphics g) {
		SubstanceTextUtilities.paintTextCompBackground(g, this.textField);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.plaf.basic.BasicTextUI#installListeners()
	 */
	@Override
	protected void installListeners() {
		super.installListeners();

		this.substanceRolloverListener = new RolloverTextControlListener(
				this.textField, this, this.transitionModel);
		this.substanceRolloverListener.registerListeners();

		this.stateTransitionTracker.registerModelListeners();
		this.stateTransitionTracker.registerFocusListeners();

		this.substancePropertyChangeListener = new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if ("font".equals(evt.getPropertyName())) {
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							// remember the caret location - issue 404
							int caretPos = textField.getCaretPosition();
							textField.updateUI();
							textField.setCaretPosition(caretPos);
							Container parent = textField.getParent();
							if (parent != null) {
								parent.invalidate();
								parent.validate();
							}
						}
					});
				}

				if ("enabled".equals(evt.getPropertyName())) {
					transitionModel.setEnabled(textField.isEnabled());
				}
			}
		};
		this.textField
				.addPropertyChangeListener(this.substancePropertyChangeListener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.plaf.basic.BasicTextUI#uninstallListeners()
	 */
	@Override
	protected void uninstallListeners() {
		this.stateTransitionTracker.unregisterModelListeners();
		this.stateTransitionTracker.unregisterFocusListeners();

		this.textField
				.removePropertyChangeListener(this.substancePropertyChangeListener);
		this.substancePropertyChangeListener = null;

		this.substanceRolloverListener.unregisterListeners();
		this.substanceRolloverListener = null;

		// this.textField.removeFocusListener(this.substanceFocusListener);
		// this.substanceFocusListener = null;

		super.uninstallListeners();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.plaf.basic.BasicTextUI#installDefaults()
	 */
	@Override
	protected void installDefaults() {
		super.installDefaults();
		Border b = this.textField.getBorder();
		if (b == null || b instanceof UIResource) {
			Border newB = new BorderUIResource.CompoundBorderUIResource(
					new SubstanceTextComponentBorder(SubstanceSizeUtils
							.getTextBorderInsets(SubstanceSizeUtils
									.getComponentFontSize(this.textField))),
					new BasicBorders.MarginBorder());
			this.textField.setBorder(newB);
		}

		// support for per-window skins
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				if (textField == null)
					return;
				Color foregr = textField.getForeground();
				if ((foregr == null) || (foregr instanceof UIResource)) {
					textField
							.setForeground(SubstanceColorUtilities
									.getForegroundColor(SubstanceLookAndFeel
											.getCurrentSkin(textField)
											.getEnabledColorScheme(
													SubstanceLookAndFeel
															.getDecorationType(textField))));
				}
			}
		});
	}

	@Override
	public boolean isInside(MouseEvent me) {
		if (!SubstanceLookAndFeel.isCurrentLookAndFeel()) {
			return false;
		}
		Shape contour = SubstanceOutlineUtilities.getBaseOutline(
				this.textField, 2.0f * SubstanceSizeUtils
						.getClassicButtonCornerRadius(SubstanceSizeUtils
								.getComponentFontSize(this.textField)), null);
		return contour.contains(me.getPoint());
	}

	@Override
	public StateTransitionTracker getTransitionTracker() {
		return this.stateTransitionTracker;
	}
}
