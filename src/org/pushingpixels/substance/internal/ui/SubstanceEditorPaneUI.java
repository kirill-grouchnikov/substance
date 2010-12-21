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
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicEditorPaneUI;

import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import org.pushingpixels.substance.internal.animation.StateTransitionTracker;
import org.pushingpixels.substance.internal.animation.TransitionAwareUI;
import org.pushingpixels.substance.internal.utils.*;

/**
 * UI for editor panes in <b>Substance</b> look and feel.
 * 
 * @author Kirill Grouchnikov
 */
public class SubstanceEditorPaneUI extends BasicEditorPaneUI implements
		TransitionAwareUI {
	protected StateTransitionTracker stateTransitionTracker;

	/**
	 * The associated editor pane.
	 */
	protected JEditorPane editorPane;

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
		return new SubstanceEditorPaneUI(comp);
	}

	/**
	 * Simple constructor.
	 * 
	 * @param c
	 *            Component (editor pane).
	 */
	public SubstanceEditorPaneUI(JComponent c) {
		super();
		this.editorPane = (JEditorPane) c;

		this.transitionModel = new DefaultButtonModel();
		this.transitionModel.setArmed(false);
		this.transitionModel.setSelected(false);
		this.transitionModel.setPressed(false);
		this.transitionModel.setRollover(false);
		this.transitionModel.setEnabled(this.editorPane.isEnabled());

		this.stateTransitionTracker = new StateTransitionTracker(
				this.editorPane, this.transitionModel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.plaf.basic.BasicTextUI#installListeners()
	 */
	@Override
	protected void installListeners() {
		super.installListeners();
		super.installListeners();

		this.substanceRolloverListener = new RolloverTextControlListener(
				this.editorPane, this, this.transitionModel);
		this.substanceRolloverListener.registerListeners();

		this.stateTransitionTracker.registerModelListeners();
		this.stateTransitionTracker.registerFocusListeners();

		this.substancePropertyChangeListener = new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if ("font".equals(evt.getPropertyName())) {
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							// remember the caret location - issue 404
							int caretPos = editorPane.getCaretPosition();
							editorPane.updateUI();
							editorPane.setCaretPosition(caretPos);
							Container parent = editorPane.getParent();
							if (parent != null) {
								parent.invalidate();
								parent.validate();
							}
						}
					});
				}

				if ("enabled".equals(evt.getPropertyName())) {
					transitionModel.setEnabled(editorPane.isEnabled());
				}
			}
		};
		this.editorPane
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

		this.editorPane
				.removePropertyChangeListener(this.substancePropertyChangeListener);
		this.substancePropertyChangeListener = null;

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

		// support for per-window skins
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				if (editorPane == null)
					return;
				Color foregr = editorPane.getForeground();
				if ((foregr == null) || (foregr instanceof UIResource)) {
					editorPane
							.setForeground(SubstanceColorUtilities
									.getForegroundColor(SubstanceLookAndFeel
											.getCurrentSkin(editorPane)
											.getEnabledColorScheme(
													SubstanceLookAndFeel
															.getDecorationType(editorPane))));
				}
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
		SubstanceTextUtilities.paintTextCompBackground(g, this.editorPane);
	}

	@Override
	public boolean isInside(MouseEvent me) {
		return true;
	}

	@Override
	public StateTransitionTracker getTransitionTracker() {
		return this.stateTransitionTracker;
	}
}
