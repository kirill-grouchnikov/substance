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
package org.pushingpixels.substance.internal.utils.menu;

import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.Area;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.ButtonModel;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.ButtonUI;
import javax.swing.plaf.basic.BasicHTML;
import javax.swing.text.View;

import org.pushingpixels.lafwidget.LafWidgetUtilities;
import org.pushingpixels.substance.api.ComponentState;
import org.pushingpixels.substance.api.ComponentStateFacet;
import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import org.pushingpixels.substance.api.SubstanceConstants.MenuGutterFillKind;
import org.pushingpixels.substance.internal.animation.StateTransitionTracker;
import org.pushingpixels.substance.internal.animation.TransitionAwareUI;
import org.pushingpixels.substance.internal.utils.SubstanceColorSchemeUtilities;
import org.pushingpixels.substance.internal.utils.SubstanceCoreUtilities;
import org.pushingpixels.substance.internal.utils.SubstanceTextUtilities;

/**
 * Menu-related utilities.
 * 
 * @author Kirill Grouchnikov
 */
public class MenuUtilities {
	/**
	 * Key to store the layout metrics. The value should be an instance of
	 * {@link MenuLayoutMetrics}.
	 */
	private static final String LAYOUT_METRICS = "substancelaf.internal.menus.layoutMetrics";

	/**
	 * Key to store the gutter X location. The value should be an instance of
	 * {@link Integer}.
	 */
	private static final String GUTTER_X = "substancelaf.internal.menus.gutterX";

	public static final String LAYOUT_INFO = "substancelaf.internal.menus.layoutInfo";

	/**
	 * Listener to track changes in the menu items. Once any property has been
	 * changed, the popup layout metrics on the menu item and its parent popup
	 * menu are cleared.
	 * 
	 * @author Kirill Grouchnikov
	 */
	public static class MenuPropertyListener implements PropertyChangeListener {
		/**
		 * Associated menu item.
		 */
		private JMenuItem menuItem;

		/**
		 * Runnable instance to clean the layout metrics.
		 */
		private Runnable cleanLayoutMetricsRunnable;

		/**
		 * Creates a new listener.
		 * 
		 * @param menuItem
		 *            Menu item.
		 */
		public MenuPropertyListener(final JMenuItem menuItem) {
			this.menuItem = menuItem;
			this.cleanLayoutMetricsRunnable = new Runnable() {
				public void run() {
					MenuUtilities.cleanPopupLayoutMetrics(menuItem);
				}
			};
		}

		/**
		 * Installs the property change listener on the associated menu item.
		 */
		public void install() {
			this.menuItem.addPropertyChangeListener(this);
		}

		/**
		 * Uninstalls the property change listener from the associated menu
		 * item.
		 */
		public void uninstall() {
			this.menuItem.removePropertyChangeListener(this);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @seejava.beans.PropertyChangeListener#propertyChange(java.beans.
		 * PropertyChangeEvent)
		 */
		public void propertyChange(PropertyChangeEvent evt) {
			if (!evt.getPropertyName().equals(MenuUtilities.LAYOUT_METRICS)) {
				SwingUtilities.invokeLater(cleanLayoutMetricsRunnable);
			}
		}
	}

	/**
	 * Layout information for a single menu item.
	 * 
	 * @author Kirill Grouchnikov
	 */
	public static class MenuLayoutInfo {
		/**
		 * View rectangle.
		 */
		public Rectangle viewRect;

		/**
		 * Icon rectangle.
		 */
		public Rectangle iconRect;

		/**
		 * Check icon rectangle.
		 */
		public Rectangle checkIconRect;

		/**
		 * Text rectangle.
		 */
		public Rectangle textRect;

		/**
		 * Accelerator rectangle.
		 */
		public Rectangle acceleratorRect;

		/**
		 * Arrow icon rectangle.
		 */
		public Rectangle arrowIconRect;

		/**
		 * Menu item text.
		 */
		public String text;
	}

	/**
	 * Layout metrics for a single popup menu. All menu items in a popup menu
	 * share the same metrics so that different parts (icons, check icons,
	 * texts, accelerator texts and arrow icons) are vertically aligned.
	 * 
	 * @author Kirill Grouchnikov
	 */
	public static class MenuLayoutMetrics {
		/**
		 * Maximum width of icons of the menu items of this popup menu.
		 */
		public int maxIconWidth;

		/**
		 * Maximum width of check icons of the menu items of this popup menu.
		 */
		public int maxCheckIconWidth;

		/**
		 * Maximum width of texts of the menu items of this popup menu.
		 */
		public int maxTextWidth;

		/**
		 * Maximum width of accelerator texts of the menu items of this popup
		 * menu.
		 */
		public int maxAcceleratorWidth;

		/**
		 * Maximum width of arrow icons of the menu items of this popup menu.
		 */
		public int maxArrowIconWidth;

		/**
		 * Maximum gap between icon and text of the menu items of this popup
		 * menu.
		 */
		public int maxIconTextGap;
	}

	/**
	 * Returns the layout info for the specified menu item.
	 * 
	 * @param menuItem
	 *            Menu item.
	 * @param acceleratorFont
	 *            Font for the accelerator text.
	 * @param checkIcon
	 *            Check icon.
	 * @param arrowIcon
	 *            Arrow icon.
	 * @param defaultTextIconGap
	 *            Gap between the icon and the text.
	 * @return Layout info for the specified menu item.
	 */
	public static MenuLayoutInfo getMenuLayoutInfo(boolean forPainting,
			JMenuItem menuItem, Font acceleratorFont, Icon checkIcon,
			Icon arrowIcon, int defaultTextIconGap) {

		Insets i = menuItem.getInsets();

		Rectangle iconRect = new Rectangle(0, 0, 0, 0);
		Rectangle textRect = new Rectangle(0, 0, 0, 0);
		Rectangle acceleratorRect = new Rectangle(0, 0, 0, 0);
		Rectangle checkIconRect = new Rectangle(0, 0, 0, 0);
		Rectangle arrowIconRect = new Rectangle(0, 0, 0, 0);
		Rectangle viewRect = new Rectangle(Short.MAX_VALUE, Short.MAX_VALUE);

		if (forPainting) {
			// fix for issue 379 - setting the available
			// bounds only during the painting.
			int menuWidth = menuItem.getWidth();
			int menuHeight = menuItem.getHeight();
			if ((menuWidth > 0) && (menuHeight > 0))
				viewRect.setBounds(0, 0, menuWidth, menuHeight);

			viewRect.x += i.left;
			viewRect.y += i.top;
			viewRect.width -= (i.right + viewRect.x);
			viewRect.height -= (i.bottom + viewRect.y);
		}

		FontMetrics fm = menuItem.getFontMetrics(menuItem.getFont());
		FontMetrics fmAccel = menuItem.getFontMetrics(acceleratorFont);

		// get Accelerator text
		KeyStroke accelerator = menuItem.getAccelerator();
		String acceleratorText = "";
		if (accelerator != null) {
			int modifiers = accelerator.getModifiers();
			if (modifiers > 0) {
				acceleratorText = KeyEvent.getKeyModifiersText(modifiers);
				// acceleratorText += "-";
				acceleratorText += UIManager
						.getString("MenuItem.acceleratorDelimiter");
			}

			int keyCode = accelerator.getKeyCode();
			if (keyCode != 0) {
				acceleratorText += KeyEvent.getKeyText(keyCode);
			} else {
				acceleratorText += accelerator.getKeyChar();
			}
		}

		// layout the text and icon
		String text = layoutMenuItem(menuItem, fm, menuItem.getText(), fmAccel,
				acceleratorText, menuItem.getIcon(), checkIcon, arrowIcon,
				menuItem.getVerticalAlignment(), menuItem
						.getHorizontalAlignment(), menuItem
						.getVerticalTextPosition(), menuItem
						.getHorizontalTextPosition(), viewRect, iconRect,
				textRect, acceleratorRect, checkIconRect, arrowIconRect,
				menuItem.getText() == null ? 0 : defaultTextIconGap,
				defaultTextIconGap);

		MenuLayoutInfo mlInfo = new MenuLayoutInfo();
		mlInfo.checkIconRect = checkIconRect;
		mlInfo.iconRect = iconRect;
		mlInfo.textRect = textRect;
		mlInfo.viewRect = viewRect;
		mlInfo.acceleratorRect = acceleratorRect;
		mlInfo.arrowIconRect = arrowIconRect;
		mlInfo.text = text;

		return mlInfo;
	}

	/**
	 * Compute and return the location of the icons origin, the location of
	 * origin of the text baseline, and a possibly clipped version of the
	 * compound labels string. Locations are computed relative to the viewRect
	 * rectangle.
	 */
	private static String layoutMenuItem(JMenuItem menuItem, FontMetrics fm,
			String text, FontMetrics fmAccel, String acceleratorText,
			Icon icon, Icon checkIcon, Icon arrowIcon, int verticalAlignment,
			int horizontalAlignment, int verticalTextPosition,
			int horizontalTextPosition, Rectangle viewRect, Rectangle iconRect,
			Rectangle textRect, Rectangle acceleratorRect,
			Rectangle checkIconRect, Rectangle arrowIconRect, int textIconGap,
			int menuItemGap) {

		SwingUtilities.layoutCompoundLabel(menuItem, fm, text, icon,
				verticalAlignment, horizontalAlignment, verticalTextPosition,
				horizontalTextPosition, viewRect, iconRect, textRect,
				textIconGap);

		/*
		 * Initialize the acceelratorText bounds rectangle textRect. If a null
		 * or and empty String was specified we substitute "" here and use
		 * 0,0,0,0 for acceleratorTextRect.
		 */
		if ((acceleratorText == null) || acceleratorText.equals("")) {
			acceleratorRect.width = acceleratorRect.height = 0;
			acceleratorText = "";
		} else {
			acceleratorRect.width = fmAccel.stringWidth(acceleratorText);
			acceleratorRect.height = fmAccel.getHeight();
		}

		/*
		 * Initialize the checkIcon bounds rectangle's width & height.
		 */

		if (useCheckAndArrow(menuItem)) {
			if (checkIcon != null) {
				checkIconRect.width = checkIcon.getIconWidth();
				checkIconRect.height = checkIcon.getIconHeight();
			} else {
				checkIconRect.width = checkIconRect.height = 0;
			}

			/*
			 * Initialize the arrowIcon bounds rectangle width & height.
			 */

			if (arrowIcon != null) {
				arrowIconRect.width = arrowIcon.getIconWidth();
				arrowIconRect.height = arrowIcon.getIconHeight();
			} else {
				arrowIconRect.width = arrowIconRect.height = 0;
			}
		}

		Rectangle labelRect = iconRect.union(textRect);
		if (menuItem.getComponentOrientation().isLeftToRight()) {
			textRect.x += menuItemGap;
			iconRect.x += menuItemGap;

			// Position the Accelerator text rect
			acceleratorRect.x = viewRect.x + viewRect.width
					- arrowIconRect.width - menuItemGap - acceleratorRect.width;

			// Position the Check and Arrow Icons
			if (useCheckAndArrow(menuItem)) {
				checkIconRect.x = viewRect.x + menuItemGap;
				textRect.x += menuItemGap + checkIconRect.width;
				iconRect.x += menuItemGap + checkIconRect.width;
				arrowIconRect.x = viewRect.x + viewRect.width - menuItemGap
						- arrowIconRect.width;
			}
		} else {
			textRect.x -= menuItemGap;
			iconRect.x -= menuItemGap;

			// Position the Accelerator text rect
			acceleratorRect.x = viewRect.x + arrowIconRect.width + menuItemGap;

			// Position the Check and Arrow Icons
			if (useCheckAndArrow(menuItem)) {
				checkIconRect.x = viewRect.x + viewRect.width - menuItemGap
						- checkIconRect.width;
				textRect.x -= menuItemGap + checkIconRect.width;
				iconRect.x -= menuItemGap + checkIconRect.width;
				arrowIconRect.x = viewRect.x + menuItemGap;
			}
		}

		// Align the accelertor text and the check and arrow icons vertically
		// with the center of the label rect.
		acceleratorRect.y = labelRect.y + (labelRect.height / 2)
				- (acceleratorRect.height / 2);
		if (useCheckAndArrow(menuItem)) {
			arrowIconRect.y = labelRect.y + (labelRect.height / 2)
					- (arrowIconRect.height / 2);
			checkIconRect.y = labelRect.y + (labelRect.height / 2)
					- (checkIconRect.height / 2);
		}

		/*
		 * System.out.println("Layout: text="+menuItem.getText()+"\n\tv="
		 * +viewRect+"\n\tc="+checkIconRect+"\n\ti="
		 * +iconRect+"\n\tt="+textRect+"\n\tacc="
		 * +acceleratorRect+"\n\ta="+arrowIconRect+"\n");
		 */

		return text;
	}

	/*
	 * Returns false if the component is a JMenu and it is a top level menu (on
	 * the menubar).
	 */
	private static boolean useCheckAndArrow(JMenuItem menuItem) {
		boolean b = true;
		if ((menuItem instanceof JMenu)
				&& (((JMenu) menuItem).isTopLevelMenu())) {
			b = false;
		}
		return b;
	}

	/**
	 * Paints the specified menu item.
	 * 
	 * @param g
	 *            Graphics context.
	 * @param menuItem
	 *            Menu item.
	 * @param checkIcon
	 *            Check icon.
	 * @param arrowIcon
	 *            Arrow icon.
	 * @param defaultTextIconGap
	 *            Gap between the icon and the text.
	 */
	public static void paintMenuItem(Graphics g, JMenuItem menuItem,
			Icon checkIcon, Icon arrowIcon, int defaultTextIconGap) {
		if (!SubstanceLookAndFeel.isCurrentLookAndFeel())
			return;

		Graphics2D g2d = (Graphics2D) g.create();

		// special handling of menu items covered by lightweight popups
		// for the defect 297
		if (Boolean.TRUE
				.equals(menuItem
						.getClientProperty(SubstanceCoreUtilities.IS_COVERED_BY_LIGHTWEIGHT_POPUPS))) {
			JRootPane rootPane = SwingUtilities.getRootPane(menuItem);
			if (rootPane != null) {
				Component[] popups = rootPane.getLayeredPane()
						.getComponentsInLayer(JLayeredPane.POPUP_LAYER);
				if (popups != null) {
					int popupIndexToStartWith = SubstanceCoreUtilities
							.getPopupParentIndexOf(menuItem, popups) - 1;
					Area clip = new Area(g2d.getClip());
					for (int i = popupIndexToStartWith; i >= 0; i--) {
						Component popup = popups[i];
						// convert the popup bounds to the menu item coordinate
						// system
						Rectangle popupArea = SwingUtilities.convertRectangle(
								rootPane.getLayeredPane(), popup.getBounds(),
								menuItem);
						// and subtract this area from the clip
						clip.subtract(new Area(popupArea));
					}
					// at this point we have the original clip minus
					// all the overlapping lightweight popups that are painted
					// after this menu item
					g2d.setClip(clip);
				}
			}
		}

		ButtonModel model = menuItem.getModel();
		SubstanceMenu menuUi = (SubstanceMenu) menuItem.getUI();

		Font f = menuItem.getFont();
		g2d.setFont(f);

		// get Accelerator text
		KeyStroke accelerator = menuItem.getAccelerator();
		String acceleratorText = "";
		if (accelerator != null) {
			int modifiers = accelerator.getModifiers();
			if (modifiers > 0) {
				acceleratorText = KeyEvent.getKeyModifiersText(modifiers);
				acceleratorText += UIManager
						.getString("MenuItem.acceleratorDelimiter");
			}

			int keyCode = accelerator.getKeyCode();
			if (keyCode != 0) {
				acceleratorText += KeyEvent.getKeyText(keyCode);
			} else {
				acceleratorText += accelerator.getKeyChar();
			}
		}
		Icon icon = menuItem.getIcon();
		// MenuLayoutInfo mli = MenuUtilities.getMenuLayoutInfo(menuItem, menuUi
		// .getAcceleratorFont(), menuUi.getCheckIcon(), menuUi
		// .getArrowIcon(), menuUi.getDefaultTextIconGap());

		MenuLayoutInfo mli = MenuUtilities.getMenuLayoutInfo(true, menuItem,
				menuUi.getAcceleratorFont(), menuUi.getCheckIcon(), menuUi
						.getArrowIcon(), menuUi.getDefaultTextIconGap());
		MenuLayoutMetrics popupMetrics = MenuUtilities.getPopupLayoutMetrics(
				menuItem, true);
		Insets i = menuItem.getInsets();
		if (popupMetrics != null) {
			MenuGutterFillKind gutterFillKind = SubstanceCoreUtilities
					.getMenuGutterFillKind();
			boolean needExtraIconTextGap = (gutterFillKind != null)
					&& (gutterFillKind != MenuGutterFillKind.NONE);
			int gap = popupMetrics.maxIconTextGap;
			if (menuItem.getComponentOrientation().isLeftToRight()) {
				int currX = i.left + gap / 2;
				if (checkIcon != null) {
					mli.checkIconRect = new Rectangle(currX, i.top, checkIcon
							.getIconWidth(), checkIcon.getIconHeight());
					int bump = (popupMetrics.maxCheckIconWidth - checkIcon
							.getIconWidth()) / 2;
					mli.checkIconRect.x += bump;
				}
				if (popupMetrics.maxCheckIconWidth > 0) {
					currX += (popupMetrics.maxCheckIconWidth + gap);
				}
				if (icon != null) {
					mli.iconRect = new Rectangle(currX, i.top, icon
							.getIconWidth(), icon.getIconHeight());
					int bump = (popupMetrics.maxIconWidth - icon.getIconWidth()) / 2;
					mli.iconRect.x += bump;
				}
				if (popupMetrics.maxIconWidth > 0) {
					currX += (popupMetrics.maxIconWidth + gap);
				}
				menuItem.putClientProperty(GUTTER_X, currX + gap / 2);
				if (needExtraIconTextGap)
					currX += gap;
				if (menuItem.getText() != null) {
					mli.textRect = new Rectangle(currX, mli.textRect.y,
							popupMetrics.maxTextWidth, mli.textRect.height);
					mli.text = menuItem.getText();
				}
				currX += (popupMetrics.maxTextWidth + gap);
				if (popupMetrics.maxAcceleratorWidth > 0) {
					currX += 5 * gap;
					// accelerator text is right-aligned
					mli.acceleratorRect = new Rectangle(currX
							+ popupMetrics.maxAcceleratorWidth
							- mli.acceleratorRect.width, mli.textRect.y,
							mli.acceleratorRect.width, mli.textRect.height);
				}
				if (popupMetrics.maxAcceleratorWidth > 0) {
					currX += (popupMetrics.maxAcceleratorWidth + gap);
				}

				if (arrowIcon != null) {
					mli.arrowIconRect = new Rectangle(currX, i.top,
							popupMetrics.maxArrowIconWidth, arrowIcon
									.getIconHeight());
				}

				Rectangle labelRect = new Rectangle(0, 0, menuItem.getWidth(),
						menuItem.getHeight());
				if (mli.textRect != null)
					labelRect = mli.textRect;
				if (mli.iconRect != null) {
					mli.iconRect.y = labelRect.y + (labelRect.height / 2)
							- (mli.iconRect.height / 2);
				}
				if (mli.arrowIconRect != null) {
					mli.arrowIconRect.y = labelRect.y + (labelRect.height / 2)
							- (mli.arrowIconRect.height / 2);
				}
				if (mli.checkIconRect != null) {
					mli.checkIconRect.y = labelRect.y + (labelRect.height / 2)
							- (mli.checkIconRect.height / 2);
				}
			} else {
				int currX = menuItem.getWidth() - i.right - gap / 2;
				if (checkIcon != null) {
					mli.checkIconRect = new Rectangle(currX
							- popupMetrics.maxCheckIconWidth, i.top, checkIcon
							.getIconWidth(), checkIcon.getIconHeight());
					int bump = (popupMetrics.maxCheckIconWidth - checkIcon
							.getIconWidth()) / 2;
					mli.checkIconRect.x += bump;
				}
				if (popupMetrics.maxCheckIconWidth > 0) {
					currX -= (popupMetrics.maxCheckIconWidth + gap);
				}
				if (icon != null) {
					// icons are center-aligned
					mli.iconRect = new Rectangle(currX
							- popupMetrics.maxIconWidth, i.top, icon
							.getIconWidth(), icon.getIconHeight());
					int bump = (popupMetrics.maxIconWidth - icon.getIconWidth()) / 2;
					mli.iconRect.x += bump;
				}
				if (popupMetrics.maxIconWidth > 0) {
					currX -= (popupMetrics.maxIconWidth + gap);
				}
				menuItem.putClientProperty(GUTTER_X, currX + gap / 2);
				if (needExtraIconTextGap)
					currX -= gap;
				if (menuItem.getText() != null) {
					// text is right-aligned
					mli.textRect = new Rectangle(currX - mli.textRect.width,
							mli.textRect.y, popupMetrics.maxTextWidth,
							mli.textRect.height);
					mli.text = menuItem.getText();
				}
				currX -= (popupMetrics.maxTextWidth + gap);
				if (popupMetrics.maxAcceleratorWidth > 0) {
					currX -= 5 * gap;
					// accelerator text is left-aligned
					mli.acceleratorRect = new Rectangle(currX
							- popupMetrics.maxAcceleratorWidth, mli.textRect.y,
							mli.acceleratorRect.width, mli.textRect.height);
				}
				if (popupMetrics.maxAcceleratorWidth > 0) {
					currX -= (popupMetrics.maxAcceleratorWidth + gap);
				}

				if (arrowIcon != null) {
					mli.arrowIconRect = new Rectangle(currX
							- popupMetrics.maxArrowIconWidth, i.top,
							popupMetrics.maxArrowIconWidth, arrowIcon
									.getIconHeight());
				}

				Rectangle labelRect = new Rectangle(0, 0, menuItem.getWidth(),
						menuItem.getHeight());
				if (mli.textRect != null)
					labelRect = mli.textRect;
				if (mli.iconRect != null) {
					mli.iconRect.y = labelRect.y + (labelRect.height / 2)
							- (mli.iconRect.height / 2);
				}
				if (mli.arrowIconRect != null) {
					mli.arrowIconRect.y = labelRect.y + (labelRect.height / 2)
							- (mli.arrowIconRect.height / 2);
				}
				if (mli.checkIconRect != null) {
					mli.checkIconRect.y = labelRect.y + (labelRect.height / 2)
							- (mli.checkIconRect.height / 2);
				}
			}
		}
		menuItem.putClientProperty(LAYOUT_INFO, mli);
		Component parent = menuItem.getParent();
		if (parent instanceof JPopupMenu) {
			((JPopupMenu) parent).putClientProperty(GUTTER_X, menuItem
					.getClientProperty(GUTTER_X));
		}

		// paint menu background
		paintBackground(g2d, menuItem);
		// paint menu highlight
		SubstanceMenuBackgroundDelegate.paintHighlights(g, menuItem, 0.5f);

		Graphics2D graphics = (Graphics2D) g2d.create();
		if (mli.text != null) {
			View v = (View) menuItem.getClientProperty(BasicHTML.propertyKey);
			if (v != null) {
				v.paint(graphics, mli.textRect);
			} else {
				SubstanceTextUtilities.paintText(graphics, menuItem,
						mli.textRect, mli.text, menuItem
								.getDisplayedMnemonicIndex());
			}
		}
		// Draw the Accelerator Text
		if (acceleratorText != null && !acceleratorText.equals("")) {
			SubstanceTextUtilities.paintText(graphics, menuItem,
					mli.acceleratorRect, acceleratorText, -1);
		}

		float textAlpha = SubstanceColorSchemeUtilities.getAlpha(menuItem,
				ComponentState.getState(menuItem.getModel(), menuItem, true));

		graphics.setComposite(LafWidgetUtilities.getAlphaComposite(menuItem,
				textAlpha, g2d));
		// Paint the Check
		if (checkIcon != null) {
			if (useCheckAndArrow(menuItem))
				checkIcon.paintIcon(menuItem, graphics, mli.checkIconRect.x,
						mli.checkIconRect.y);
		}

		// Paint the Icon
		if (icon != null) {
			if (!model.isEnabled()) {
				icon = menuItem.getDisabledIcon();
			} else if (model.isPressed() && model.isArmed()) {
				icon = menuItem.getPressedIcon();
				if (icon == null) {
					// Use default icon
					icon = menuItem.getIcon();
				}
			} else {
				icon = menuItem.getIcon();
			}

			if (icon != null) {
				boolean useThemed = SubstanceCoreUtilities
						.useThemedDefaultIcon(menuItem);

				if (!useThemed) {
					icon.paintIcon(menuItem, g2d, mli.iconRect.x,
							mli.iconRect.y);
				} else {
					Icon themed = SubstanceCoreUtilities.getThemedIcon(
							menuItem, icon);
					boolean useRegularVersion = model.isPressed()
							|| model.isSelected();
					if (useRegularVersion) {
						icon.paintIcon(menuItem, g2d, mli.iconRect.x,
								mli.iconRect.y);
					} else {
						TransitionAwareUI transitionAwareUI = (TransitionAwareUI) menuItem
								.getUI();
						StateTransitionTracker stateTransitionTracker = transitionAwareUI
								.getTransitionTracker();
						float rolloverAmount = Math
								.max(
										stateTransitionTracker
												.getFacetStrength(ComponentStateFacet.ROLLOVER),
										stateTransitionTracker
												.getFacetStrength(ComponentStateFacet.ARM));
						if (rolloverAmount > 0) {
							themed.paintIcon(menuItem, g2d, mli.iconRect.x,
									mli.iconRect.y);
							g2d.setComposite(LafWidgetUtilities
									.getAlphaComposite(menuItem,
											rolloverAmount, g));
							icon.paintIcon(menuItem, g2d, mli.iconRect.x,
									mli.iconRect.y);
							g2d.setComposite(LafWidgetUtilities
									.getAlphaComposite(menuItem, g));
						} else {
							// if (model.isRollover() || model.isArmed()) {
							// icon.paintIcon(menuItem, g2d, mli.iconRect.x,
							// mli.iconRect.y);
							// } else {
							themed.paintIcon(menuItem, g2d, mli.iconRect.x,
									mli.iconRect.y);
							// }
						}
					}
				}
			}
		}

		// Paint the Arrow
		if (arrowIcon != null) {
			if (useCheckAndArrow(menuItem))
				arrowIcon.paintIcon(menuItem, graphics, mli.arrowIconRect.x,
						mli.arrowIconRect.y);
		}
		graphics.dispose();

		// g2d.setColor(Color.red);
		// if (mli.iconRect != null)
		// g2d.draw(mli.iconRect);
		// if (mli.checkIconRect != null)
		// g2d.draw(mli.checkIconRect);
		// if (mli.textRect != null)
		// g2d.draw(mli.textRect);

		// g2d.setColor(Color.red);
		// g2d.drawRect(mli.checkIconRect.x, mli.checkIconRect.y,
		// mli.checkIconRect.width, mli.checkIconRect.height);
		// g2d.drawRect(mli.iconRect.x, mli.iconRect.y, mli.iconRect.width,
		// mli.iconRect.height);
		// g2d.drawRect(mli.textRect.x, mli.textRect.y, mli.textRect.width,
		// mli.textRect.height);
		// g2d.drawRect(mli.arrowIconRect.x, mli.arrowIconRect.y,
		// mli.arrowIconRect.width, mli.arrowIconRect.height);
		// g2d.drawRect(mli.acceleratorRect.x, mli.acceleratorRect.y,
		// mli.acceleratorRect.width, mli.acceleratorRect.height);

		g2d.dispose();
	}

	/**
	 * Paints the background of the specified menu item.
	 * 
	 * @param g
	 *            Graphics context.
	 * @param menuItem
	 *            Menu item.
	 */
	private static void paintBackground(Graphics g, JMenuItem menuItem) {
		int textOffset = MenuUtilities.getTextOffset(menuItem, menuItem
				.getParent());
		SubstanceMenuBackgroundDelegate
				.paintBackground(g, menuItem, textOffset);
	}

	/**
	 * Returns the layout metrics of the specified popup menu.
	 * 
	 * @param popupMenu
	 *            Popup menu.
	 * @return Layout metrics of the specified popup menu.
	 */
	protected static MenuLayoutMetrics getMetrics(JPopupMenu popupMenu,
			boolean forPainting) {
		MenuLayoutMetrics metrics = new MenuLayoutMetrics();
		for (int i = 0; i < popupMenu.getComponentCount(); i++) {
			Component comp = popupMenu.getComponent(i);
			if (comp instanceof JMenuItem) {
				JMenuItem childMenuItem = (JMenuItem) comp;
				ButtonUI bui = childMenuItem.getUI();
				if (!(bui instanceof SubstanceMenu))
					continue;
				SubstanceMenu ui = (SubstanceMenu) bui;
				MenuLayoutInfo mli = MenuUtilities.getMenuLayoutInfo(
						forPainting, childMenuItem, ui.getAcceleratorFont(), ui
								.getCheckIcon(), ui.getArrowIcon(), ui
								.getDefaultTextIconGap());
				metrics.maxIconWidth = Math.max(metrics.maxIconWidth,
						mli.iconRect.width);
				metrics.maxCheckIconWidth = Math.max(metrics.maxCheckIconWidth,
						mli.checkIconRect.width);
				metrics.maxTextWidth = Math.max(metrics.maxTextWidth,
						mli.textRect.width);
				metrics.maxAcceleratorWidth = Math.max(
						metrics.maxAcceleratorWidth, mli.acceleratorRect.width);
				metrics.maxArrowIconWidth = Math.max(metrics.maxArrowIconWidth,
						mli.arrowIconRect.width);
				metrics.maxIconTextGap = Math.max(metrics.maxIconTextGap, ui
						.getDefaultTextIconGap());
			}
		}
		return metrics;
	}

	/**
	 * Returns the layout metrics of the popup menu of the specified menu item.
	 * 
	 * @param menuItem
	 *            Menu item.
	 * @return Layout metrics of the popup menu of the specified menu item.
	 */
	public static MenuLayoutMetrics getPopupLayoutMetrics(JMenuItem menuItem,
			boolean forPainting) {
		Component comp = menuItem.getParent();
		if (comp instanceof JPopupMenu) {
			JPopupMenu popupMenu = (JPopupMenu) comp;
			return getPopupLayoutMetrics(popupMenu, forPainting);
		}
		// fix for issue 347 - menu item in menu bar is not
		// covered by the code above
		if (!(comp instanceof JMenu)) {
			ButtonUI bui = menuItem.getUI();
			if (bui instanceof SubstanceMenu) {
				SubstanceMenu ui = (SubstanceMenu) bui;

				MenuLayoutInfo mli = MenuUtilities.getMenuLayoutInfo(
						forPainting, menuItem, ui.getAcceleratorFont(), ui
								.getCheckIcon(), ui.getArrowIcon(), ui
								.getDefaultTextIconGap());
				MenuLayoutMetrics metrics = new MenuLayoutMetrics();
				metrics.maxIconWidth = mli.iconRect.width;
				metrics.maxCheckIconWidth = mli.checkIconRect.width;
				metrics.maxTextWidth = mli.textRect.width;
				metrics.maxAcceleratorWidth = mli.acceleratorRect.width;
				metrics.maxArrowIconWidth = mli.arrowIconRect.width;
				metrics.maxIconTextGap = ui.getDefaultTextIconGap();
				return metrics;
			}
		}

		// If here, the parent is not popup menu - this is a top-level menu
		// in a menu bar. No need to align the different rectangles
		return null;
	}

	public static MenuLayoutMetrics getPopupLayoutMetrics(JPopupMenu popupMenu,
			boolean forPainting) {
		Object prop = popupMenu.getClientProperty(LAYOUT_METRICS);
		if (prop instanceof MenuLayoutMetrics) {
			return (MenuLayoutMetrics) prop;
		}
		MenuLayoutMetrics metrics = getMetrics(popupMenu, forPainting);
		popupMenu.putClientProperty(LAYOUT_METRICS, metrics);
		return metrics;
	}

	/**
	 * Cleans the layout metrics of the popup menu of the specified menu item.
	 * 
	 * @param menuItem
	 *            Menu item.
	 */
	private static void cleanPopupLayoutMetrics(JMenuItem menuItem) {
		Component comp = menuItem.getParent();
		if (comp instanceof JPopupMenu) {
			JPopupMenu popupMenu = (JPopupMenu) comp;
			cleanPopupLayoutMetrics(popupMenu);
		}
	}

	/**
	 * Cleans the layout metrics of the specified popup menu.
	 * 
	 * @param popupMenu
	 *            Popup menu.
	 */
	public static void cleanPopupLayoutMetrics(JPopupMenu popupMenu) {
		if (popupMenu != null) {
			popupMenu.putClientProperty(LAYOUT_METRICS, null);
		}
	}

	/**
	 * Returns the preferred width of the specified menu item. The preferred
	 * width depends on the layout metrics of the entire popup menu of this menu
	 * item.
	 * 
	 * @param menuItem
	 *            Menu item.
	 * @return Preferred width of the specified menu item
	 */
	public static int getPreferredWidth(JMenuItem menuItem) {
		Insets ins = menuItem.getInsets();
		MenuLayoutMetrics popupMetrics = MenuUtilities.getPopupLayoutMetrics(
				menuItem, false);

		int width = popupMetrics.maxCheckIconWidth + popupMetrics.maxIconWidth
				+ popupMetrics.maxTextWidth + popupMetrics.maxAcceleratorWidth
				+ popupMetrics.maxArrowIconWidth + ins.left + ins.right;

		int gapsToAdd = 0;
		if (popupMetrics.maxCheckIconWidth > 0)
			gapsToAdd++;
		if (popupMetrics.maxIconWidth > 0)
			gapsToAdd++;
		if (popupMetrics.maxAcceleratorWidth > 0)
			gapsToAdd++;
		if (popupMetrics.maxArrowIconWidth > 0)
			gapsToAdd++;

		int gap = popupMetrics.maxIconTextGap;
		width += ((1 + gapsToAdd) * gap);

		if (popupMetrics.maxAcceleratorWidth > 0) {
			// at least one menu item has accelerator text
			width += 5 * gap;
		}

		MenuGutterFillKind gutterFillKind = SubstanceCoreUtilities
				.getMenuGutterFillKind();
		boolean needExtraIconTextGap = (gutterFillKind != null)
				&& (gutterFillKind != MenuGutterFillKind.NONE);
		if (needExtraIconTextGap)
			width += gap;

		return width;
	}

	/**
	 * Returns the text offset of the specified menu item.
	 * 
	 * @param menuItem
	 *            Menu item.
	 * @param menuItemParent
	 *            Menu item parent.
	 * @return Text offset of the specified menu item.
	 */
	public static int getTextOffset(JComponent menuItem,
			Component menuItemParent) {
		if (!(menuItemParent instanceof JPopupMenu)) {
			return 0;
		}

		Object itemProp = menuItem.getClientProperty(GUTTER_X);
		if (itemProp instanceof Integer) {
			return (Integer) itemProp;
		}

		JPopupMenu popupMenu = (JPopupMenu) menuItemParent;
		Object parentProp = popupMenu.getClientProperty(GUTTER_X);
		if (parentProp instanceof Integer) {
			return (Integer) parentProp;
		}
		return 0;
	}
}
