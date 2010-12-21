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

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicOptionPaneUI;

import org.pushingpixels.lafwidget.animation.AnimationConfigurationManager;
import org.pushingpixels.lafwidget.animation.AnimationFacet;
import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import org.pushingpixels.substance.internal.animation.IconGlowTracker;
import org.pushingpixels.substance.internal.painter.BackgroundPaintingUtils;
import org.pushingpixels.substance.internal.utils.SubstanceCoreUtilities;
import org.pushingpixels.substance.internal.utils.icon.GlowingIcon;

/**
 * UI for option panes in <b>Substance</b> look and feel.
 * 
 * @author Kirill Grouchnikov
 */
public class SubstanceOptionPaneUI extends BasicOptionPaneUI {
	static {
		AnimationConfigurationManager.getInstance().allowAnimations(
				AnimationFacet.ICON_GLOW, OptionPaneLabel.class);
	}

	/**
	 * Label extension class. Due to defect 250, the option pane icon animation
	 * (glowing icon) should repaint only the icon itself and not the entire
	 * option pane. While the {@link AnimationConfigurationManager} API provides
	 * an option to enable animations on the specific component, it's better to
	 * enable it on the component class (to make the lookups faster). So, when
	 * the option pane icon label is created (in addIcon method), we use this
	 * class.
	 * 
	 * @author Kirill Grouchnikov
	 */
	protected static class OptionPaneLabel extends JLabel {
	}

	/**
	 * Icon label.
	 */
	private OptionPaneLabel substanceIconLabel;

	private IconGlowTracker iconGlowTracker;

	/**
	 * Creates a new SubstanceOptionPaneUI instance.
	 */
	public static ComponentUI createUI(JComponent comp) {
		SubstanceCoreUtilities.testComponentCreationThreadingViolation(comp);
		return new SubstanceOptionPaneUI();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.plaf.ComponentUI#paint(java.awt.Graphics,
	 * javax.swing.JComponent)
	 */
	@Override
	public void paint(Graphics g, JComponent c) {
		BackgroundPaintingUtils.updateIfOpaque(g, c);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.plaf.basic.BasicOptionPaneUI#addIcon(java.awt.Container)
	 */
	@Override
	protected void addIcon(Container top) {
		Icon sideIcon = (optionPane == null ? null : optionPane.getIcon());

		if (sideIcon == null && optionPane != null)
			sideIcon = super.getIconForType(optionPane.getMessageType());

		if (sideIcon != null) {
			if (!SubstanceLookAndFeel.isToUseConstantThemesOnDialogs()) {
				sideIcon = SubstanceCoreUtilities.getThemedIcon(null, sideIcon);
			}

			this.substanceIconLabel = new OptionPaneLabel();
			this.iconGlowTracker = new IconGlowTracker(substanceIconLabel);
			this.substanceIconLabel.setIcon(new GlowingIcon(sideIcon,
					this.iconGlowTracker));

			this.substanceIconLabel.setName("OptionPane.iconLabel");
			this.substanceIconLabel.setVerticalAlignment(SwingConstants.TOP);
			top.add(this.substanceIconLabel, BorderLayout.BEFORE_LINE_BEGINS);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.plaf.basic.BasicOptionPaneUI#getIconForType(int)
	 */
	@Override
	protected Icon getIconForType(int messageType) {
		switch (messageType) {
		case JOptionPane.ERROR_MESSAGE:
			return SubstanceCoreUtilities
					.getIcon("resource/32/dialog-error.png");
		case JOptionPane.INFORMATION_MESSAGE:
			return SubstanceCoreUtilities
					.getIcon("resource/32/dialog-information.png");
		case JOptionPane.WARNING_MESSAGE:
			return SubstanceCoreUtilities
					.getIcon("resource/32/dialog-warning.png");
		case JOptionPane.QUESTION_MESSAGE:
			return SubstanceCoreUtilities
					.getIcon("resource/32/help-browser.png");
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.plaf.basic.BasicOptionPaneUI#installComponents()
	 */
	@Override
	protected void installComponents() {
		super.installComponents();
		// fix for defect 265 - check that the label is not null
		// before activating the loop.
		if (this.substanceIconLabel != null) {
			// Make the icon glow for three cycles. There's no need to
			// explicitly cancel the animation when the option pane is closed
			// before the animation is over - when the three cycles are up,
			// the animation will be removed by the tracker.
			if (!this.iconGlowTracker.isPlaying()) {
				this.iconGlowTracker.play(3);
			}
		}
	}
}
