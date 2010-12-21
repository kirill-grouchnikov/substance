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
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicMenuUI;

import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import org.pushingpixels.substance.internal.animation.StateTransitionTracker;
import org.pushingpixels.substance.internal.animation.TransitionAwareUI;
import org.pushingpixels.substance.internal.utils.SubstanceCoreUtilities;
import org.pushingpixels.substance.internal.utils.SubstanceSizeUtils;
import org.pushingpixels.substance.internal.utils.icon.MenuArrowIcon;
import org.pushingpixels.substance.internal.utils.menu.MenuUtilities;
import org.pushingpixels.substance.internal.utils.menu.SubstanceMenu;
import org.pushingpixels.substance.internal.utils.menu.MenuUtilities.MenuPropertyListener;

/**
 * UI for menus in <b>Substance</b> look and feel.
 * 
 * @author Kirill Grouchnikov
 */
public class SubstanceMenuUI extends BasicMenuUI implements SubstanceMenu,
		TransitionAwareUI {
	/**
	 * For rollover effects - enhancement 93.
	 */
	protected MouseListener substanceMouseListener;

	protected StateTransitionTracker stateTransitionTracker;

	/**
	 * Listens on all changes to the underlying menu item.
	 */
	protected MenuPropertyListener substanceMenuPropertyListener;

	/**
	 * Property change listener. Listens on changes to
	 * {@link AbstractButton#MODEL_CHANGED_PROPERTY} property.
	 */
	protected PropertyChangeListener substancePropertyListener;

	/**
	 * For rollover effects - enhancement 93.
	 */
	protected FocusListener substanceFocusListener;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.plaf.ComponentUI#createUI(javax.swing.JComponent)
	 */
	public static ComponentUI createUI(JComponent comp) {
		SubstanceCoreUtilities.testComponentCreationThreadingViolation(comp);
		return new SubstanceMenuUI((JMenu) comp);
	}

	public SubstanceMenuUI(JMenu menuItem) {
		this.stateTransitionTracker = new StateTransitionTracker(menuItem,
				menuItem.getModel());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.plaf.basic.BasicMenuUI#installDefaults()
	 */
	@Override
	protected void installDefaults() {
		super.installDefaults();
		this.menuItem.setRolloverEnabled(true);
		// this.menuItem.setOpaque(false);

		this.arrowIcon = new MenuArrowIcon((JMenu) this.menuItem);

		this.defaultTextIconGap = SubstanceSizeUtils
				.getTextIconGap(SubstanceSizeUtils
						.getComponentFontSize(this.menuItem));

		this.menuItem.putClientProperty(SubstanceLookAndFeel.FLAT_PROPERTY,
				Boolean.TRUE);

		LookAndFeel.installProperty(menuItem, "opaque", Boolean.FALSE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.plaf.basic.BasicMenuUI#installListeners()
	 */
	@Override
	protected void installListeners() {
		super.installListeners();

		// Improving performance on big menus.
		this.substanceMenuPropertyListener = new MenuPropertyListener(
				this.menuItem);
		this.substanceMenuPropertyListener.install();

		this.stateTransitionTracker.registerModelListeners();

		// fix for enhancement 93 - rollover effects on menu items
		this.substanceMouseListener = new MouseAdapter() {
			// fix for defect 93 - no rollover effects on menu
			// items that are not in the selected path
			private boolean toRepaint() {
				MenuElement[] selectedMenuPath = MenuSelectionManager
						.defaultManager().getSelectedPath();
				for (MenuElement elem : selectedMenuPath) {
					if (elem == SubstanceMenuUI.this.menuItem) {
						return true;
					}
				}
				return (selectedMenuPath.length == 0);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				if (this.toRepaint()) {
					stateTransitionTracker.turnOffModelChangeTracking();
					menuItem.getModel().setRollover(true);
					stateTransitionTracker.onModelStateChanged();
					// fix for issue 371 - repaint the menu bar since the
					// menu is marked as flat
					Rectangle bounds = menuItem.getBounds();
					menuItem.getParent().repaint(bounds.x, bounds.y,
							bounds.width, bounds.height);
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (this.toRepaint()) {
					stateTransitionTracker.turnOffModelChangeTracking();
					menuItem.getModel().setRollover(false);
					stateTransitionTracker.onModelStateChanged();
					// fix for issue 371 - repaint the menu bar since the
					// menu is marked as flat
					Rectangle bounds = menuItem.getBounds();
					menuItem.getParent().repaint(bounds.x, bounds.y,
							bounds.width, bounds.height);
				}
			}
		};
		this.menuItem.addMouseListener(this.substanceMouseListener);
		this.substanceFocusListener = new FocusAdapter() {
			// fix for defect 93 - no rollover effects on menu
			// items that are not in the selected path
			private boolean toRepaint() {
				MenuElement[] selectedMenuPath = MenuSelectionManager
						.defaultManager().getSelectedPath();
				for (MenuElement elem : selectedMenuPath) {
					if (elem == SubstanceMenuUI.this.menuItem) {
						return true;
					}
				}
				return (selectedMenuPath.length == 0);
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (toRepaint()) {
					stateTransitionTracker.turnOffModelChangeTracking();
					menuItem.getModel().setRollover(false);
					stateTransitionTracker.onModelStateChanged();
					// fix for issue 371 - repaint the menu bar since the
					// menu is marked as flat
					Rectangle bounds = menuItem.getBounds();
					menuItem.getParent().repaint(bounds.x, bounds.y,
							bounds.width, bounds.height);
				}
			}
		};
		this.menuItem.addFocusListener(this.substanceFocusListener);

		this.substancePropertyListener = new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if (AbstractButton.MODEL_CHANGED_PROPERTY.equals(evt
						.getPropertyName())) {
					stateTransitionTracker.setModel((ButtonModel) evt
							.getNewValue());
				}
				if ("font".equals(evt.getPropertyName())) {
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							if (menuItem != null) {
								menuItem.updateUI();
							}
						}
					});
				}
			}
		};
		this.menuItem.addPropertyChangeListener(this.substancePropertyListener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.plaf.basic.BasicMenuUI#uninstallListeners()
	 */
	@Override
	protected void uninstallListeners() {
		super.uninstallListeners();

		// Improving performance on big menus.
		this.substanceMenuPropertyListener.uninstall();
		this.substanceMenuPropertyListener = null;

		// fix for enhancement 93 - rollover effects on menu items
		this.menuItem.removeMouseListener(this.substanceMouseListener);
		this.substanceMouseListener = null;
		this.menuItem.removeFocusListener(this.substanceFocusListener);
		this.substanceFocusListener = null;

		this.menuItem
				.removePropertyChangeListener(this.substancePropertyListener);
		this.substancePropertyListener = null;

		this.stateTransitionTracker.unregisterModelListeners();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pushingpixels.substance.SubstanceMenu#getAssociatedMenuItem()
	 */
	public JMenuItem getAssociatedMenuItem() {
		return this.menuItem;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pushingpixels.substance.SubstanceMenu#getAcceleratorFont()
	 */
	public Font getAcceleratorFont() {
		return this.acceleratorFont;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pushingpixels.substance.SubstanceMenu#getArrowIcon()
	 */
	public Icon getArrowIcon() {
		return this.arrowIcon;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pushingpixels.substance.SubstanceMenu#getCheckIcon()
	 */
	public Icon getCheckIcon() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pushingpixels.substance.SubstanceMenu#getDefaultTextIconGap()
	 */
	public int getDefaultTextIconGap() {
		return this.defaultTextIconGap;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.swing.plaf.basic.BasicMenuItemUI#getPreferredMenuItemSize(javax
	 * .swing.JComponent, javax.swing.Icon, javax.swing.Icon, int)
	 */
	@Override
	protected Dimension getPreferredMenuItemSize(JComponent c, Icon checkIcon,
			Icon arrowIcon, int defaultTextIconGap) {
		Dimension superDim = super.getPreferredMenuItemSize(c, checkIcon,
				arrowIcon, defaultTextIconGap);

		if (MenuUtilities.getPopupLayoutMetrics(menuItem, false) != null) {
			return new Dimension(MenuUtilities.getPreferredWidth(menuItem),
					superDim.height);
		}

		return superDim;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.swing.plaf.basic.BasicMenuItemUI#paintMenuItem(java.awt.Graphics,
	 * javax.swing.JComponent, javax.swing.Icon, javax.swing.Icon,
	 * java.awt.Color, java.awt.Color, int)
	 */
	@Override
	protected void paintMenuItem(Graphics g, JComponent c, Icon checkIcon,
			Icon arrowIcon, Color background, Color foreground,
			int defaultTextIconGap) {
		MenuUtilities.paintMenuItem(g, menuItem, checkIcon, arrowIcon,
				defaultTextIconGap);
	}

	@Override
	public StateTransitionTracker getTransitionTracker() {
		return this.stateTransitionTracker;
	}

	@Override
	public boolean isInside(MouseEvent me) {
		return true;
	}
}
