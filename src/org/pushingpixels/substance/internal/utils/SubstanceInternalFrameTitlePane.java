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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.JInternalFrame.JDesktopIcon;
import javax.swing.plaf.MenuBarUI;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;

import org.pushingpixels.lafwidget.LafWidgetUtilities;
import org.pushingpixels.substance.api.*;
import org.pushingpixels.substance.internal.colorscheme.ShiftColorScheme;
import org.pushingpixels.substance.internal.painter.BackgroundPaintingUtils;
import org.pushingpixels.substance.internal.ui.SubstanceButtonUI;
import org.pushingpixels.substance.internal.ui.SubstanceMenuBarUI;
import org.pushingpixels.substance.internal.utils.icon.SubstanceIconFactory;
import org.pushingpixels.substance.internal.utils.icon.TransitionAwareIcon;

/**
 * UI for internal frame title pane in <b>Substance </b> look and feel.
 * 
 * @author Kirill Grouchnikov
 */
public class SubstanceInternalFrameTitlePane extends
		BasicInternalFrameTitlePane {
	/**
	 * Listens on the changes to the internal frame title.
	 */
	protected PropertyChangeListener substancePropertyListener;

	/**
	 * Listens to the changes to the
	 * {@link SubstanceLookAndFeel#WINDOW_MODIFIED
	 * } property on the internal
	 * frame and its root pane.
	 */
	protected PropertyChangeListener substanceWinModifiedListener;

	/**
	 * Client property to mark an internal frame as being iconified.
	 */
	protected static final String ICONIFYING = "substance.internal.internalTitleFramePane.iconifying";

	/**
	 * Client property to mark a title pane as uninstalled.
	 */
	protected static final String UNINSTALLED = "substance.internal.internalTitleFramePane.uninstalled";

	// protected boolean wasClosable;

	/**
	 * Simple constructor.
	 * 
	 * @param f
	 *            Associated internal frame.
	 */
	public SubstanceInternalFrameTitlePane(JInternalFrame f) {
		super(f);
		this.setToolTipText(f.getTitle());
		SubstanceLookAndFeel.setDecorationType(this,
				DecorationAreaType.SECONDARY_TITLE_PANE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.plaf.basic.BasicInternalFrameTitlePane#installDefaults()
	 */
	@Override
	protected void installDefaults() {
		super.installDefaults();
		if (SubstanceLookAndFeel.isCurrentLookAndFeel()) {
			this.setForeground(SubstanceColorUtilities
					.getForegroundColor(SubstanceCoreUtilities.getSkin(
							this.frame).getActiveColorScheme(
							DecorationAreaType.SECONDARY_TITLE_PANE)));
		}
		// this.wasClosable = this.frame.isClosable();
	}

	// /*
	// * (non-Javadoc)
	// *
	// * @see
	// * javax.swing.plaf.basic.BasicInternalFrameTitlePane#uninstallDefaults()
	// */
	// @Override
	// protected void uninstallDefaults() {
	// super.uninstallDefaults();
	// if (this.wasClosable != this.frame.isClosable()) {
	// this.frame.setClosable(this.wasClosable);
	// }
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.swing.plaf.basic.BasicInternalFrameTitlePane#installListeners()
	 */
	@Override
	protected void installListeners() {
		super.installListeners();
		this.substancePropertyListener = new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if (JInternalFrame.TITLE_PROPERTY.equals(evt.getPropertyName())) {
					SubstanceInternalFrameTitlePane.this
							.setToolTipText((String) evt.getNewValue());
				}
				if ("JInternalFrame.messageType".equals(evt.getPropertyName())) {
					updateOptionPaneState();
					frame.repaint();
				}
			}
		};
		this.frame.addPropertyChangeListener(this.substancePropertyListener);

		// Property change listener for pulsating close button
		// when window has been marked as changed.
		this.substanceWinModifiedListener = new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if (SubstanceLookAndFeel.WINDOW_MODIFIED.equals(evt
						.getPropertyName())) {
					syncCloseButtonTooltip();
				}
			}
		};
		// Wire it on the root pane.
		this.frame.getRootPane().addPropertyChangeListener(
				this.substanceWinModifiedListener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.swing.plaf.basic.BasicInternalFrameTitlePane#uninstallListeners()
	 */
	@Override
	public void uninstallListeners() {
		this.frame.removePropertyChangeListener(this.substancePropertyListener);
		this.substancePropertyListener = null;

		this.frame.getRootPane().removePropertyChangeListener(
				this.substanceWinModifiedListener);
		this.substanceWinModifiedListener = null;

		super.uninstallListeners();
	}

	/**
	 * Uninstalls <code>this</code> title pane.
	 */
	public void uninstall() {
		if ((this.menuBar != null) && (this.menuBar.getMenuCount() > 0)) {
			MenuBarUI menuBarUI = this.menuBar.getUI();
			if (menuBarUI instanceof SubstanceMenuBarUI) {
				SubstanceMenuBarUI ui = (SubstanceMenuBarUI) menuBarUI;
				if (ui.getMenuBar() == this.menuBar)
					menuBarUI.uninstallUI(this.menuBar);
			}
			SubstanceCoreUtilities.uninstallMenu(this.menuBar.getMenu(0));
			this.remove(menuBar);
			// fix for issue 362 - remove the buttons so that we don't
			// have duplicate buttons on internal frames in reparented
			// desktop panes
			this.remove(maxButton);
			this.remove(closeButton);
			this.remove(iconButton);
		}
		this.uninstallListeners();
		this.putClientProperty(UNINSTALLED, Boolean.TRUE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.plaf.basic.BasicInternalFrameTitlePane#enableActions()
	 */
	@Override
	protected void enableActions() {
		super.enableActions();

		if (!this.frame.isIcon()) {
			if (this.maxButton != null)
				this.maxButton.setEnabled(this.maximizeAction.isEnabled()
						|| this.restoreAction.isEnabled());
			if (this.iconButton != null)
				this.iconButton.setEnabled(this.iconifyAction.isEnabled());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	public void paintComponent(Graphics g) {
		// if (this.isPalette) {
		// this.paintPalette(g);
		// return;
		// }
		Graphics2D graphics = (Graphics2D) g.create();
		// Desktop icon is translucent.
		final float coef = (this.getParent() instanceof JDesktopIcon) ? 0.6f
				: 1.0f;
		graphics.setComposite(LafWidgetUtilities.getAlphaComposite(this.frame,
				coef, g));

		boolean leftToRight = this.frame.getComponentOrientation()
				.isLeftToRight();

		int width = this.getWidth();
		int height = this.getHeight() + 2;

		SubstanceColorScheme scheme = SubstanceCoreUtilities
				.getSkin(this.frame).getEnabledColorScheme(
						DecorationAreaType.SECONDARY_TITLE_PANE);
		JInternalFrame hostFrame = (JInternalFrame) SwingUtilities
				.getAncestorOfClass(JInternalFrame.class, this);
		JComponent hostForColorization = hostFrame;
		if (hostFrame == null) {
			// try desktop icon
			JDesktopIcon desktopIcon = (JDesktopIcon) SwingUtilities
					.getAncestorOfClass(JDesktopIcon.class, this);
			if (desktopIcon != null)
				hostFrame = desktopIcon.getInternalFrame();
			hostForColorization = desktopIcon;
		}
		// if ((hostFrame != null) && SubstanceCoreUtilities.hasColorization(
		// this)) {
		Color backgr = hostFrame.getBackground();
		if (!(backgr instanceof UIResource)) {
			double colorization = SubstanceCoreUtilities
					.getColorizationFactor(hostForColorization);
			scheme = ShiftColorScheme.getShiftedScheme(scheme, backgr,
					colorization, null, 0.0);
		}
		// }
		String theTitle = this.frame.getTitle();

		// offset of border
		int xOffset = 0;
		int leftEnd;
		int rightEnd;

		if (leftToRight) {
			xOffset = 5;
			Icon icon = this.frame.getFrameIcon();
			if (icon != null) {
				xOffset += icon.getIconWidth() + 5;
			}

			leftEnd = (this.menuBar == null) ? 0
					: (this.menuBar.getWidth() + 5);
			xOffset += leftEnd;
			if (icon != null)
				leftEnd += (icon.getIconWidth() + 5);

			rightEnd = width - 5;

			// find the leftmost button for the right end
			AbstractButton leftmostButton = null;
			if (this.frame.isIconifiable()) {
				leftmostButton = this.iconButton;
			} else {
				if (this.frame.isMaximizable()) {
					leftmostButton = this.maxButton;
				} else {
					if (this.frame.isClosable()) {
						leftmostButton = this.closeButton;
					}
				}
			}

			if (leftmostButton != null) {
				Rectangle rect = leftmostButton.getBounds();
				rightEnd = rect.getBounds().x - 5;
			}
			if (theTitle != null) {
				FontMetrics fm = this.frame.getFontMetrics(graphics.getFont());
				int titleWidth = rightEnd - leftEnd;
				String clippedTitle = SubstanceCoreUtilities.clipString(fm,
						titleWidth, theTitle);
				// show tooltip with full title only if necessary
				if (theTitle.equals(clippedTitle))
					this.setToolTipText(null);
				else
					this.setToolTipText(theTitle);
				theTitle = clippedTitle;
			}
		} else {
			xOffset = width - 5;

			Icon icon = this.frame.getFrameIcon();
			if (icon != null) {
				xOffset -= (icon.getIconWidth() + 5);
			}

			rightEnd = (this.menuBar == null) ? xOffset : xOffset
					- this.menuBar.getWidth() - 5;

			// find the rightmost button for the left end
			AbstractButton rightmostButton = null;
			if (this.frame.isIconifiable()) {
				rightmostButton = this.iconButton;
			} else {
				if (this.frame.isMaximizable()) {
					rightmostButton = this.maxButton;
				} else {
					if (this.frame.isClosable()) {
						rightmostButton = this.closeButton;
					}
				}
			}

			leftEnd = 5;
			if (rightmostButton != null) {
				Rectangle rect = rightmostButton.getBounds();
				leftEnd = rect.getBounds().x + 5;
			}
			if (theTitle != null) {
				FontMetrics fm = this.frame.getFontMetrics(graphics.getFont());
				int titleWidth = rightEnd - leftEnd;
				String clippedTitle = SubstanceCoreUtilities.clipString(fm,
						titleWidth, theTitle);
				// show tooltip with full title only if necessary
				if (theTitle.equals(clippedTitle)) {
					this.setToolTipText(null);
				} else {
					this.setToolTipText(theTitle);
				}
				theTitle = clippedTitle;
				xOffset = rightEnd - fm.stringWidth(theTitle);
			}
		}

		BackgroundPaintingUtils.update(graphics,
				SubstanceInternalFrameTitlePane.this, false);
		// DecorationPainterUtils.paintDecorationBackground(graphics,
		// SubstanceInternalFrameTitlePane.this, false);

		// draw the title (if needed)
		if (theTitle != null) {
			JRootPane rootPane = this.getRootPane();
			FontMetrics fm = rootPane.getFontMetrics(graphics.getFont());
			int yOffset = ((height - fm.getHeight()) / 2) + fm.getAscent();

			SubstanceTextUtilities.paintTextWithDropShadow(this, graphics,
					SubstanceColorUtilities.getForegroundColor(scheme),
					theTitle, width, height, xOffset, yOffset);
		}

		Icon icon = this.frame.getFrameIcon();
		if (icon != null) {
			if (leftToRight) {
				int iconY = ((height / 2) - (icon.getIconHeight() / 2));
				icon.paintIcon(this.frame, graphics, 5, iconY);
			} else {
				int iconY = ((height / 2) - (icon.getIconHeight() / 2));
				icon.paintIcon(this.frame, graphics, width - 5
						- icon.getIconWidth(), iconY);
			}
		}

		graphics.dispose();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.plaf.basic.BasicInternalFrameTitlePane#setButtonIcons()
	 */
	@Override
	protected void setButtonIcons() {
		super.setButtonIcons();
		if (!SubstanceLookAndFeel.isCurrentLookAndFeel())
			return;

		Icon restoreIcon = new TransitionAwareIcon(this.maxButton,
				new TransitionAwareIcon.Delegate() {
					public Icon getColorSchemeIcon(SubstanceColorScheme scheme) {
						return SubstanceIconFactory
								.getTitlePaneIcon(
										SubstanceIconFactory.IconKind.RESTORE,
										scheme,
										SubstanceCoreUtilities
												.getSkin(
														SubstanceInternalFrameTitlePane.this)
												.getBackgroundColorScheme(
														DecorationAreaType.SECONDARY_TITLE_PANE));
					}
				}, "substance.internalFrame.restoreIcon");
		Icon maximizeIcon = new TransitionAwareIcon(this.maxButton,
				new TransitionAwareIcon.Delegate() {
					public Icon getColorSchemeIcon(SubstanceColorScheme scheme) {
						return SubstanceIconFactory
								.getTitlePaneIcon(
										SubstanceIconFactory.IconKind.MAXIMIZE,
										scheme,
										SubstanceCoreUtilities
												.getSkin(
														SubstanceInternalFrameTitlePane.this)
												.getBackgroundColorScheme(
														DecorationAreaType.SECONDARY_TITLE_PANE));
					}
				}, "substance.internalFrame.maxIcon");
		Icon minimizeIcon = new TransitionAwareIcon(this.iconButton,
				new TransitionAwareIcon.Delegate() {
					public Icon getColorSchemeIcon(SubstanceColorScheme scheme) {
						return SubstanceIconFactory
								.getTitlePaneIcon(
										SubstanceIconFactory.IconKind.MINIMIZE,
										scheme,
										SubstanceCoreUtilities
												.getSkin(
														SubstanceInternalFrameTitlePane.this)
												.getBackgroundColorScheme(
														DecorationAreaType.SECONDARY_TITLE_PANE));
					}
				}, "substance.internalFrame.minIcon");
		Icon closeIcon = new TransitionAwareIcon(this.closeButton,
				new TransitionAwareIcon.Delegate() {
					public Icon getColorSchemeIcon(SubstanceColorScheme scheme) {
						return SubstanceIconFactory
								.getTitlePaneIcon(
										SubstanceIconFactory.IconKind.CLOSE,
										scheme,
										SubstanceCoreUtilities
												.getSkin(
														SubstanceInternalFrameTitlePane.this)
												.getBackgroundColorScheme(
														DecorationAreaType.SECONDARY_TITLE_PANE));
					}
				}, "substance.internalFrame.closeIcon");
		if (this.frame.isIcon()) {
			this.iconButton.setIcon(restoreIcon);
			this.iconButton.setToolTipText(SubstanceCoreUtilities
					.getResourceBundle(frame).getString("SystemMenu.restore"));
			this.maxButton.setIcon(maximizeIcon);
			this.maxButton.setToolTipText(SubstanceCoreUtilities
					.getResourceBundle(frame).getString("SystemMenu.maximize"));
		} else {
			this.iconButton.setIcon(minimizeIcon);
			this.iconButton.setToolTipText(SubstanceCoreUtilities
					.getResourceBundle(frame).getString("SystemMenu.iconify"));
			if (this.frame.isMaximum()) {
				this.maxButton.setIcon(restoreIcon);
				this.maxButton.setToolTipText(SubstanceCoreUtilities
						.getResourceBundle(frame).getString(
								"SystemMenu.restore"));
			} else {
				this.maxButton.setIcon(maximizeIcon);
				this.maxButton.setToolTipText(SubstanceCoreUtilities
						.getResourceBundle(frame).getString(
								"SystemMenu.maximize"));
			}
		}
		if (closeIcon != null) {
			this.closeButton.setIcon(closeIcon);
			syncCloseButtonTooltip();
		}
	}

	/**
	 * Click correction listener that resets models of minimize and restore
	 * buttons on click (so that the rollover behaviour will be preserved
	 * correctly).
	 * 
	 * @author Kirill Grouchnikov.
	 */
	public static class ClickListener implements ActionListener {
		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent
		 * )
		 */
		public void actionPerformed(ActionEvent e) {
			AbstractButton src = (AbstractButton) e.getSource();
			ButtonModel model = src.getModel();
			model.setArmed(false);
			model.setPressed(false);
			model.setRollover(false);
			model.setSelected(false);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.plaf.basic.BasicInternalFrameTitlePane#createActions()
	 */
	@Override
	protected void createActions() {
		super.createActions();
		this.iconifyAction = new SubstanceIconifyAction();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.plaf.basic.BasicInternalFrameTitlePane#createButtons()
	 */
	@Override
	protected void createButtons() {
		iconButton = new SubstanceTitleButton(
				"InternalFrameTitlePane.iconifyButtonAccessibleName");
		iconButton.addActionListener(iconifyAction);

		maxButton = new SubstanceTitleButton(
				"InternalFrameTitlePane.maximizeButtonAccessibleName");
		maxButton.addActionListener(maximizeAction);

		closeButton = new SubstanceTitleButton(
				"InternalFrameTitlePane.closeButtonAccessibleName");
		closeButton.addActionListener(closeAction);

		setButtonIcons();

		for (ActionListener listener : this.iconButton.getActionListeners())
			if (listener instanceof ClickListener)
				return;
		this.iconButton.addActionListener(new ClickListener());
		for (ActionListener listener : this.maxButton.getActionListeners())
			if (listener instanceof ClickListener)
				return;
		this.maxButton.addActionListener(new ClickListener());
		this.iconButton.putClientProperty(SubstanceLookAndFeel.FLAT_PROPERTY,
				Boolean.TRUE);

		this.maxButton.putClientProperty(SubstanceLookAndFeel.FLAT_PROPERTY,
				Boolean.TRUE);

		this.closeButton.putClientProperty(
				SubstanceButtonUI.IS_TITLE_CLOSE_BUTTON, Boolean.TRUE);
		this.closeButton.putClientProperty(SubstanceLookAndFeel.FLAT_PROPERTY,
				Boolean.TRUE);

		this.enableActions();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.plaf.basic.BasicInternalFrameTitlePane#createLayout()
	 */
	@Override
	protected LayoutManager createLayout() {
		return new SubstanceTitlePaneLayout();
	}

	/**
	 * Synchronizes the tooltip of the close button.
	 */
	protected void syncCloseButtonTooltip() {
		if (SubstanceCoreUtilities.isInternalFrameModified(this.frame)) {
			this.closeButton.setToolTipText(SubstanceCoreUtilities
					.getResourceBundle(frame).getString("SystemMenu.close")
					+ " ["
					+ SubstanceCoreUtilities.getResourceBundle(frame)
							.getString("Tooltip.contentsNotSaved") + "]");
		} else {
			this.closeButton.setToolTipText(SubstanceCoreUtilities
					.getResourceBundle(frame).getString("SystemMenu.close"));
		}
		this.closeButton.repaint();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JComponent#removeNotify()
	 */
	@Override
	public void removeNotify() {
		super.removeNotify();

		// fix for defect 211 - internal frames that are iconified
		// programmatically should not uninstall the title panes.
		boolean isAlive = ((this.frame.isIcon() && !this.frame.isClosed()) || Boolean.TRUE
				.equals(frame.getClientProperty(ICONIFYING)));
		if (!isAlive) {
			this.uninstall();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JComponent#addNotify()
	 */
	@Override
	public void addNotify() {
		super.addNotify();
		if (Boolean.TRUE.equals(this.getClientProperty(UNINSTALLED))) {
			this.installTitlePane();
			// this.installListeners();
			this.putClientProperty(UNINSTALLED, null);
		}
	}

	/**
	 * Layout manager for this title pane.
	 * 
	 * @author Kirill Grouchnikov
	 */
	protected class SubstanceTitlePaneLayout extends TitlePaneLayout {
		@Override
		public void addLayoutComponent(String name, Component c) {
		}

		@Override
		public void removeLayoutComponent(Component c) {
		}

		@Override
		public Dimension preferredLayoutSize(Container c) {
			return minimumLayoutSize(c);
		}

		@Override
		public Dimension minimumLayoutSize(Container c) {
			// Compute width.
			int width = 30;
			if (frame.isClosable()) {
				width += 21;
			}
			if (frame.isMaximizable()) {
				width += 16 + (frame.isClosable() ? 10 : 4);
			}
			if (frame.isIconifiable()) {
				width += 16 + (frame.isMaximizable() ? 2
						: (frame.isClosable() ? 10 : 4));
			}
			FontMetrics fm = frame.getFontMetrics(getFont());
			String frameTitle = frame.getTitle();
			int title_w = frameTitle != null ? fm.stringWidth(frameTitle) : 0;
			int title_length = frameTitle != null ? frameTitle.length() : 0;

			if (title_length > 2) {
				int subtitle_w = fm.stringWidth(frame.getTitle()
						.substring(0, 2)
						+ "...");
				width += (title_w < subtitle_w) ? title_w : subtitle_w;
			} else {
				width += title_w;
			}

			// Compute height.
			int height = 0;
			// if (isPalette) {
			// height = paletteTitleHeight;
			// } else {
			int fontHeight = fm.getHeight();
			fontHeight += 7;
			Icon icon = frame.getFrameIcon();
			int iconHeight = 0;
			if (icon != null) {
				// SystemMenuBar forces the icon to be 16x16 or less.
				iconHeight = Math.min(icon.getIconHeight(), 16);
			}
			iconHeight += 5;
			height = Math.max(fontHeight, iconHeight);
			// }

			return new Dimension(width, height);
		}

		@Override
		public void layoutContainer(Container c) {
			boolean leftToRight = frame.getComponentOrientation()
					.isLeftToRight();

			int w = getWidth();
			int x = leftToRight ? w : 0;
			int y = 2;
			int spacing;

			// assumes all buttons have the same dimensions
			// these dimensions include the borders
			int buttonHeight = closeButton.getIcon().getIconHeight();
			int buttonWidth = closeButton.getIcon().getIconWidth();

			y = (getHeight() - buttonHeight) / 2;

			// Icon icon = frame.getFrameIcon();
			// int iconHeight = 0;
			// if (icon != null) {
			// iconHeight = icon.getIconHeight();
			// }
			// int xMenuBar = (leftToRight) ? 2 : w - 16 - 2;
			// menuBar.setBounds(xMenuBar, (getHeight() - iconHeight) / 2, 16,
			// 16);

			if (frame.isClosable()) {
				// if (isPalette) {
				// spacing = 3;
				// x += leftToRight ? -spacing - (buttonWidth + 2) : spacing;
				// closeButton.setBounds(x, y, buttonWidth + 2,
				// getHeight() - 4);
				// if (!leftToRight)
				// x += (buttonWidth + 2);
				// } else {
				spacing = 4;
				x += leftToRight ? -spacing - buttonWidth : spacing;
				closeButton.setBounds(x, y, buttonWidth, buttonHeight);
				if (!leftToRight)
					x += buttonWidth;
				// }
			}

			if (frame.isMaximizable()) {// && !isPalette) {
				spacing = frame.isClosable() ? 10 : 4;
				x += leftToRight ? -spacing - buttonWidth : spacing;
				maxButton.setBounds(x, y, buttonWidth, buttonHeight);
				if (!leftToRight)
					x += buttonWidth;
			}

			if (frame.isIconifiable()) {// && !isPalette) {
				spacing = frame.isMaximizable() ? 2 : (frame.isClosable() ? 10
						: 4);
				x += leftToRight ? -spacing - buttonWidth : spacing;
				iconButton.setBounds(x, y, buttonWidth, buttonHeight);
				if (!leftToRight)
					x += buttonWidth;
			}
			//
			// buttonsWidth = leftToRight ? w - x : x;
		}
	}

	/**
	 * Custom iconifying action.
	 * 
	 * @author Kirill Grouchnikov
	 */
	public class SubstanceIconifyAction extends IconifyAction {
		/**
		 * Creates an iconifying action.
		 */
		public SubstanceIconifyAction() {
			super();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			frame.putClientProperty(ICONIFYING, Boolean.TRUE);
			super.actionPerformed(e);
			frame.putClientProperty(ICONIFYING, null);
		}
	}

	/**
	 * Updates the state of internal frames used in {@link JOptionPane}s.
	 */
	private void updateOptionPaneState() {
		Object obj = frame.getClientProperty("JInternalFrame.messageType");

		if (obj == null) {
			// Don't change the closable state unless in an JOptionPane.
			return;
		}
		if (frame.isClosable()) {
			frame.setClosable(false);
		}
	}

	public AbstractButton getCloseButton() {
		return this.closeButton;
	}
}
