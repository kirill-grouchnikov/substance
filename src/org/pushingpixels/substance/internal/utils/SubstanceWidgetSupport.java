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

import javax.swing.*;
import javax.swing.JInternalFrame.JDesktopIcon;

import org.pushingpixels.lafwidget.LafWidgetSupport;
import org.pushingpixels.lafwidget.utils.LafConstants.PasswordStrength;
import org.pushingpixels.substance.api.*;
import org.pushingpixels.substance.api.SubstanceConstants.SubstanceWidgetType;
import org.pushingpixels.substance.internal.ui.SubstanceDesktopIconUI;
import org.pushingpixels.substance.internal.ui.SubstanceTabbedPaneUI;

/**
 * Support for <a href="https://laf-widget.dev.java.net">laf-widget</a> layer.
 * This class is <b>for internal use only</b>.
 * 
 * @author Kirill Grouchnikov
 */
public class SubstanceWidgetSupport extends LafWidgetSupport {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.pushingpixels.lafwidget.LafWidgetSupport#getComponentForHover(javax
	 * .swing .JInternalFrame.JDesktopIcon)
	 */
	@Override
	public JComponent getComponentForHover(JDesktopIcon desktopIcon) {
		SubstanceDesktopIconUI ui = (SubstanceDesktopIconUI) desktopIcon
				.getUI();
		return ui.getComponentForHover();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.pushingpixels.lafwidget.LafWidgetSupport#toInstallMenuSearch(javax
	 * .swing. JMenuBar)
	 */
	@Override
	public boolean toInstallMenuSearch(JMenuBar menuBar) {
		// if the menu search widget has not been allowed,
		// return false
		if (!SubstanceWidgetManager.getInstance().isAllowed(
				SwingUtilities.getRootPane(menuBar),
				SubstanceWidgetType.MENU_SEARCH))
			return false;
		// don't install on menu bar of title panes
		if (menuBar instanceof SubstanceTitlePane.SubstanceMenuBar)
			return false;
		return super.toInstallMenuSearch(menuBar);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pushingpixels.lafwidget.LafWidgetSupport#getSearchIcon(int,
	 * java.awt.ComponentOrientation)
	 */
	@Override
	public Icon getSearchIcon(int dimension,
			ComponentOrientation componentOrientation) {
		return SubstanceImageCreator.getSearchIcon(dimension,
				SubstanceColorSchemeUtilities.getColorScheme(null,
						ComponentState.DEFAULT), componentOrientation
						.isLeftToRight());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pushingpixels.lafwidget.LafWidgetSupport#getArrowIcon(int)
	 */
	@Override
	public Icon getArrowIcon(int orientation) {
		return SubstanceImageCreator.getArrowIcon(SubstanceSizeUtils
				.getControlFontSize(), orientation,
				SubstanceColorSchemeUtilities.getColorScheme(null,
						ComponentState.DEFAULT));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pushingpixels.lafwidget.LafWidgetSupport#getNumberIcon(int)
	 */
	@Override
	public Icon getNumberIcon(int number) {
		SubstanceColorScheme colorScheme = SubstanceLookAndFeel.getCurrentSkin(
				null).getActiveColorScheme(DecorationAreaType.HEADER);
		return SubstanceImageCreator.getHexaMarker(number, colorScheme);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.pushingpixels.lafwidget.LafWidgetSupport#markButtonAsFlat(javax.swing
	 * .AbstractButton)
	 */
	@Override
	public void markButtonAsFlat(AbstractButton button) {
		button.putClientProperty(SubstanceLookAndFeel.FLAT_PROPERTY,
				Boolean.TRUE);
		button.setOpaque(false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.pushingpixels.lafwidget.LafWidgetSupport#getRolloverTabIndex(javax
	 * .swing. JTabbedPane)
	 */
	@Override
	public int getRolloverTabIndex(JTabbedPane tabbedPane) {
		SubstanceTabbedPaneUI ui = (SubstanceTabbedPaneUI) tabbedPane.getUI();
		return ui.getRolloverTabIndex();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.pushingpixels.lafwidget.LafWidgetSupport#setTabAreaInsets(javax.swing
	 * .JTabbedPane , java.awt.Insets)
	 */
	@Override
	public void setTabAreaInsets(JTabbedPane tabbedPane, Insets tabAreaInsets) {
		SubstanceTabbedPaneUI ui = (SubstanceTabbedPaneUI) tabbedPane.getUI();
		ui.setTabAreaInsets(tabAreaInsets);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.pushingpixels.lafwidget.LafWidgetSupport#getTabAreaInsets(javax.swing
	 * .JTabbedPane )
	 */
	@Override
	public Insets getTabAreaInsets(JTabbedPane tabbedPane) {
		SubstanceTabbedPaneUI ui = (SubstanceTabbedPaneUI) tabbedPane.getUI();
		return ui.getTabAreaInsets();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.pushingpixels.lafwidget.LafWidgetSupport#getTabRectangle(javax.swing
	 * .JTabbedPane , int)
	 */
	@Override
	public Rectangle getTabRectangle(JTabbedPane tabbedPane, int tabIndex) {
		SubstanceTabbedPaneUI ui = (SubstanceTabbedPaneUI) tabbedPane.getUI();
		return ui.getTabRectangle(tabIndex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.pushingpixels.lafwidget.LafWidgetSupport#paintPasswordStrengthMarker
	 * (java .awt.Graphics, int, int, int, int,
	 * org.pushingpixels.lafwidget.utils.LafConstants.PasswordStrength)
	 */
	@Override
	public void paintPasswordStrengthMarker(Graphics g, int x, int y,
			int width, int height, PasswordStrength pStrength) {
		Graphics2D g2 = (Graphics2D) g.create();

		SubstanceColorScheme colorScheme = null;

		if (pStrength == PasswordStrength.WEAK)
			colorScheme = SubstanceColorSchemeUtilities.ORANGE;
		if (pStrength == PasswordStrength.MEDIUM)
			colorScheme = SubstanceColorSchemeUtilities.YELLOW;
		if (pStrength == PasswordStrength.STRONG)
			colorScheme = SubstanceColorSchemeUtilities.GREEN;

		if (colorScheme != null) {
			SubstanceImageCreator.paintRectangularBackground(null, g, x, y,
					width, height, colorScheme, 0.5f, false);
		}

		g2.dispose();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.pushingpixels.lafwidget.LafWidgetSupport#hasLockIcon(java.awt.Component
	 * )
	 */
	@Override
	public boolean hasLockIcon(Component comp) {
		if (!SubstanceCoreUtilities.toShowExtraWidgets(comp))
			return false;
		return super.hasLockIcon(comp);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.pushingpixels.lafwidget.LafWidgetSupport#getLockIcon(java.awt.Component
	 * )
	 */
	@Override
	public Icon getLockIcon(Component c) {
		return SubstanceImageCreator.makeTransparent(null,
				SubstanceImageCreator.getSmallLockIcon(
						SubstanceColorSchemeUtilities.getColorScheme(null,
								ComponentState.ENABLED), c), 0.3);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.pushingpixels.lafwidget.LafWidgetSupport#toInstallExtraElements(java
	 * .awt. Component)
	 */
	@Override
	public boolean toInstallExtraElements(Component comp) {
		return SubstanceCoreUtilities.toShowExtraWidgets(comp);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pushingpixels.lafwidget.LafWidgetSupport#getLookupIconSize()
	 */
	@Override
	public int getLookupIconSize() {
		int result = 2 + SubstanceSizeUtils.getControlFontSize();
		if (result % 2 != 0)
			result++;
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pushingpixels.lafwidget.LafWidgetSupport#getLookupButtonSize()
	 */
	@Override
	public int getLookupButtonSize() {
		return 4 + SubstanceSizeUtils.getControlFontSize();
	}
}
