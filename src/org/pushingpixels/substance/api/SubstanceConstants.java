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
package org.pushingpixels.substance.api;

import java.awt.*;
import java.awt.geom.GeneralPath;

import javax.swing.*;

import org.pushingpixels.substance.api.shaper.SubstanceButtonShaper;
import org.pushingpixels.substance.internal.animation.TransitionAwareUI;
import org.pushingpixels.substance.internal.utils.*;

/**
 * <b>Substance</b> constants.
 * 
 * @author Kirill Grouchnikov
 */
public class SubstanceConstants {
	/**
	 * Enumerates available sides.
	 * 
	 * @author Kirill Grouchnikov
	 * @see SubstanceLookAndFeel#BUTTON_OPEN_SIDE_PROPERTY
	 * @see SubstanceLookAndFeel#BUTTON_SIDE_PROPERTY
	 */
	public static enum Side {
		/**
		 * Left side.
		 */
		LEFT,

		/**
		 * Right side.
		 */
		RIGHT,

		/**
		 * Top side.
		 */
		TOP,

		/**
		 * Bottom side.
		 */
		BOTTOM;
	}

	/**
	 * Enumerates focus indication kinds.
	 * 
	 * @author Kirill Grouchnikov
	 * @see SubstanceLookAndFeel#FOCUS_KIND
	 */
	public enum FocusKind {
		/**
		 * No focus indication.
		 */
		NONE {
			@Override
			public void paintFocus(Component mainComp, Component focusedComp,
					TransitionAwareUI transitionAwareUI, Graphics2D graphics,
					Shape focusShape, Rectangle textRect, int extraPadding) {
			}
		},

		/**
		 * Focus indication around the text.
		 */
		TEXT {
			@Override
			public void paintFocus(Component mainComp, Component focusedComp,
					TransitionAwareUI transitionAwareUI, Graphics2D graphics,
					Shape focusShape, Rectangle textRect, int extraPadding) {
				if (textRect == null)
					return;
				if ((textRect.width == 0) || (textRect.height == 0))
					return;

				int fontSize = SubstanceSizeUtils
						.getComponentFontSize(mainComp);
				float dashLength = getDashLength(fontSize);
				float dashGap = getDashGap(fontSize);
				float dashPhase = (dashLength + dashGap)
						* (1.0f - transitionAwareUI.getTransitionTracker()
								.getFocusLoopPosition());

				graphics.setStroke(new BasicStroke(SubstanceSizeUtils
						.getFocusStrokeWidth(fontSize), BasicStroke.CAP_BUTT,
						BasicStroke.JOIN_ROUND, 0.0f, new float[] { dashLength,
								dashGap }, dashPhase));

				int delta = ((mainComp instanceof JComboBox) || (mainComp instanceof JSpinner)) ? 0
						: 1;
				GeneralPath contour = SubstanceOutlineUtilities.getBaseOutline(
						textRect.width + 2 * delta, textRect.height,
						SubstanceSizeUtils
								.getClassicButtonCornerRadius(fontSize), null);

				graphics.translate(textRect.x - delta, textRect.y);
				graphics.draw(contour);
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * org.pushingpixels.substance.utils.SubstanceConstants.FocusKind
			 * #isAnimated ()
			 */
			@Override
			public boolean isAnimated() {
				return true;
			}
		},

		/**
		 * Focus indication around the whole component.
		 */
		ALL {
			@Override
			public void paintFocus(Component mainComp, Component focusedComp,
					TransitionAwareUI transitionAwareUI, Graphics2D graphics,
					Shape focusShape, Rectangle textRect, int extraPadding) {
				if ((focusShape == null)
						&& ((mainComp instanceof AbstractButton)
								&& !(mainComp instanceof JCheckBox) && !(mainComp instanceof JRadioButton))) {
					SubstanceButtonShaper shaper = SubstanceCoreUtilities
							.getButtonShaper(mainComp);
					if (shaper == null)
						return;

					int fontSize = SubstanceSizeUtils
							.getComponentFontSize(mainComp);
					float dashLength = getDashLength(fontSize);
					float dashGap = getDashGap(fontSize);
					float dashPhase = (dashLength + dashGap)
							* (1.0f - transitionAwareUI.getTransitionTracker()
									.getFocusLoopPosition());
					graphics.setStroke(new BasicStroke(SubstanceSizeUtils
							.getFocusStrokeWidth(fontSize),
							BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 0.0f,
							new float[] { dashLength, dashGap }, dashPhase));

					Shape contour = shaper.getButtonOutline(
							(AbstractButton) mainComp, null, mainComp
									.getWidth(), mainComp.getHeight(), false);
					graphics.draw(contour);
					// }
				} else {
					// graphics.translate(textRect.x - 1, textRect.y - 1);
					graphics.translate(1, 1);
					Shape contour = (focusShape != null) ? focusShape
							: SubstanceOutlineUtilities
									.getBaseOutline(
											mainComp.getWidth() - 2,
											mainComp.getHeight() - 2,
											SubstanceSizeUtils
													.getClassicButtonCornerRadius(SubstanceSizeUtils
															.getComponentFontSize(mainComp)),
											null);

					int fontSize = SubstanceSizeUtils
							.getComponentFontSize(mainComp);
					float dashLength = getDashLength(fontSize);
					float dashGap = getDashGap(fontSize);
					float dashPhase = (dashLength + dashGap)
							* (1.0f - transitionAwareUI.getTransitionTracker()
									.getFocusLoopPosition());
					graphics.setStroke(new BasicStroke(SubstanceSizeUtils
							.getFocusStrokeWidth(fontSize),
							BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 0.0f,
							new float[] { dashLength, dashGap }, dashPhase));
					graphics.draw(contour);
				}
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * org.pushingpixels.substance.utils.SubstanceConstants.FocusKind
			 * #isAnimated ()
			 */
			@Override
			public boolean isAnimated() {
				return true;
			}
		},

		/**
		 * Focus indication around the whole component, but moved 1 pixel inside
		 * the component.
		 */
		ALL_INNER {
			@Override
			public void paintFocus(Component mainComp, Component focusedComp,
					TransitionAwareUI transitionAwareUI, Graphics2D graphics,
					Shape focusShape, Rectangle textRect, int extraPadding) {

				if ((focusShape == null)
						&& ((mainComp instanceof AbstractButton)
								&& !(mainComp instanceof JCheckBox) && !(mainComp instanceof JRadioButton))) {
					SubstanceButtonShaper shaper = SubstanceCoreUtilities
							.getButtonShaper(mainComp);
					if (shaper == null)
						return;

					if (shaper.isProportionate()) {
						int fontSize = SubstanceSizeUtils
								.getComponentFontSize(mainComp);
						float dashLength = getDashLength(fontSize);
						float dashGap = getDashGap(fontSize);
						float dashPhase = (dashLength + dashGap)
								* (1.0f - transitionAwareUI
										.getTransitionTracker()
										.getFocusLoopPosition());
						float focusStrokeWidth = SubstanceSizeUtils
								.getFocusStrokeWidth(fontSize);
						graphics.setStroke(new BasicStroke(focusStrokeWidth,
								BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND,
								0.0f, new float[] { dashLength, dashGap },
								dashPhase));
						int insetsPix = extraPadding;
						Insets insets = new Insets(insetsPix, insetsPix,
								insetsPix, insetsPix);

						Shape contour = shaper.getButtonOutline(
								(AbstractButton) mainComp, insets, mainComp
										.getWidth(), mainComp.getHeight(),
								false);
						graphics.draw(contour);
					}
				} else {
					graphics.translate(extraPadding / 2, extraPadding / 2);
					int fontSize = SubstanceSizeUtils
							.getComponentFontSize(mainComp);
					Shape contour = (focusShape != null) ? focusShape
							: SubstanceOutlineUtilities.getBaseOutline(mainComp
									.getWidth()
									- extraPadding, mainComp.getHeight()
									- extraPadding, SubstanceSizeUtils
									.getClassicButtonCornerRadius(fontSize),
									null);

					float dashLength = getDashLength(fontSize);
					float dashGap = getDashGap(fontSize);
					float dashPhase = (dashLength + dashGap)
							* (1.0f - transitionAwareUI.getTransitionTracker()
									.getFocusLoopPosition());

					graphics.setStroke(new BasicStroke(SubstanceSizeUtils
							.getFocusStrokeWidth(fontSize),
							BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 0.0f,
							new float[] { dashLength, dashGap }, dashPhase));
					graphics.draw(contour);
				}
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * org.pushingpixels.substance.utils.SubstanceConstants.FocusKind
			 * #isAnimated ()
			 */
			@Override
			public boolean isAnimated() {
				return true;
			}
		},

		/**
		 * Focus indication around the whole component, but moved 1 pixel inside
		 * the component.
		 */
		ALL_STRONG_INNER {
			@Override
			public void paintFocus(Component mainComp, Component focusedComp,
					TransitionAwareUI transitionAwareUI, Graphics2D graphics,
					Shape focusShape, Rectangle textRect, int extraPadding) {
				if ((focusShape == null)
						&& ((mainComp instanceof AbstractButton)
								&& !(mainComp instanceof JCheckBox) && !(mainComp instanceof JRadioButton))) {
					SubstanceButtonShaper shaper = SubstanceCoreUtilities
							.getButtonShaper(mainComp);
					if (shaper == null)
						return;

					if (shaper.isProportionate()) {
						Insets insets = new Insets(extraPadding, extraPadding,
								extraPadding, extraPadding);

						Shape contour = shaper.getButtonOutline(
								(AbstractButton) mainComp, insets, mainComp
										.getWidth(), mainComp.getHeight(),
								false);
						graphics.draw(contour);
					}
				} else {
					graphics.translate(extraPadding / 2, extraPadding / 2);
					Shape contour = (focusShape != null) ? focusShape
							: SubstanceOutlineUtilities
									.getBaseOutline(
											mainComp.getWidth() - extraPadding,
											mainComp.getHeight() - extraPadding,
											SubstanceSizeUtils
													.getClassicButtonCornerRadius(SubstanceSizeUtils
															.getComponentFontSize(mainComp)),
											null);

					graphics.draw(contour);
				}
			}
		},

		/**
		 * Focus indication under the component text.
		 */
		UNDERLINE {
			@Override
			public void paintFocus(Component mainComp, Component focusedComp,
					TransitionAwareUI transitionAwareUI, Graphics2D graphics,
					Shape focusShape, Rectangle textRect, int extraPadding) {
				if (textRect == null)
					return;

				int fontSize = SubstanceSizeUtils
						.getComponentFontSize(mainComp);
				float dashLength = getDashLength(fontSize);
				float dashGap = getDashGap(fontSize);
				float dashPhase = (dashLength + dashGap)
						* (1.0f - transitionAwareUI.getTransitionTracker()
								.getFocusLoopPosition());

				graphics.setStroke(new BasicStroke(SubstanceSizeUtils
						.getFocusStrokeWidth(fontSize), BasicStroke.CAP_BUTT,
						BasicStroke.JOIN_ROUND, 0.0f, new float[] { dashLength,
								dashGap }, dashPhase));

				graphics.translate(textRect.x - 1, textRect.y);
				graphics.drawLine(0, textRect.height - 1, textRect.width,
						textRect.height - 1);
				graphics.dispose();
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * org.pushingpixels.substance.utils.SubstanceConstants.FocusKind
			 * #isAnimated ()
			 */
			@Override
			public boolean isAnimated() {
				return true;
			}
		},

		/**
		 * Strong focus indication under the component text.
		 */
		STRONG_UNDERLINE {
			@Override
			public void paintFocus(Component mainComp, Component focusedComp,
					TransitionAwareUI transitionAwareUI, Graphics2D graphics,
					Shape focusShape, Rectangle textRect, int extraPadding) {
				if (textRect == null)
					return;

				graphics.translate(textRect.x - 1, textRect.y);
				graphics.drawLine(0, textRect.height - 1, textRect.width,
						textRect.height - 1);
			}
		};

		/**
		 * Paints the focus ring on the specified component.
		 * 
		 * @param mainComp
		 *            The main component for the focus painting.
		 * @param focusedComp
		 *            The actual component that has the focus. For example, the
		 *            main component can be a {@link JSpinner}, while the
		 *            focused component is a text field inside the the spinner
		 *            editor.
		 * @param graphics
		 *            Graphics context.
		 * @param focusShape
		 *            Focus shape. May be <code>null</code> - in this case, the
		 *            bounds of <code>mainComp</code> will be used.
		 * @param textRect
		 *            Text rectangle (if relevant).
		 * @param extraPadding
		 *            Extra padding between the component bounds and the focus
		 *            ring painting.
		 */
		public abstract void paintFocus(Component mainComp,
				Component focusedComp, TransitionAwareUI transitionAwareUI,
				Graphics2D graphics, Shape focusShape, Rectangle textRect,
				int extraPadding);

		/**
		 * Returns DPI-aware dash length for dash-based focus painting.
		 * 
		 * @param fontSize
		 *            The font size of the component for focus painting.
		 * @return DPI-aware dash length for dash-based focus painting.
		 */
		protected static float getDashLength(int fontSize) {
			return 2.0f + SubstanceSizeUtils.getExtraPadding(fontSize);
		}

		/**
		 * Returns DPI-aware dash gap for dash-based focus painting.
		 * 
		 * @param fontSize
		 *            The font size of the component for focus painting.
		 * @return DPI-aware dash gap for dash-based focus painting.
		 */
		protected static float getDashGap(int fontSize) {
			return getDashLength(fontSize) / 2.0f;
		}

		/**
		 * Returns indication whether <code>this</code> focus kind can be
		 * animated. For example, focus rings painted with solid lines are
		 * generally static.
		 * 
		 * @return <code>true</code> if <code>this</code> focus kind can be
		 *         animated, <code>false</code> otherwise.
		 */
		public boolean isAnimated() {
			return false;
		}
	}

	/**
	 * Enumerates of image-based watermarks kinds.
	 * 
	 * @author Kirill Grouchnikov
	 * @see SubstanceLookAndFeel#setImageWatermarkKind(org.pushingpixels.substance.utils.
	 *      SubstanceConstants.ImageWatermarkKind)
	 * @see SubstanceLookAndFeel#getImageWatermarkKind()
	 */
	public enum ImageWatermarkKind {
		/**
		 * The default behaviour. The image is centered in the screen and scaled
		 * down if necessary.
		 */
		SCREEN_CENTER_SCALE,

		/**
		 * The image is tiled starting from the screen top-left corner and not
		 * scaled.
		 */
		SCREEN_TILE,

		/**
		 * The image is anchored to the top-left corner of the application frame
		 * and not scaled.
		 */
		APP_ANCHOR,

		/**
		 * The image is anchored to the center of the application frame and not
		 * scaled.
		 */
		APP_CENTER,

		/**
		 * The image is tiled starting from the top-left corner of the
		 * application frame and not scaled.
		 */
		APP_TILE
	}

	/**
	 * Enumerates possible modes of closing tabs.
	 * 
	 * @author Kirill Grouchnikov
	 * @see SubstanceLookAndFeel#TABBED_PANE_CLOSE_CALLBACK
	 */
	public enum TabCloseKind {
		/**
		 * Indicates that no tabs should be closed.
		 */
		NONE,

		/**
		 * Indicates that the specified tab should be closed.
		 */
		THIS,

		/**
		 * Indicates that all tabs should be closed.
		 */
		ALL,

		/**
		 * Indicates that all tabs except the specified should be closed.
		 */
		ALL_BUT_THIS
	}

	/**
	 * Enumerates possible button policies for scroll panes.
	 * 
	 * @author Kirill Grouchnikov
	 * @see SubstanceLookAndFeel#SCROLL_PANE_BUTTONS_POLICY
	 */
	public enum ScrollPaneButtonPolicyKind {
		/**
		 * The <code>empty</code> button policy - no buttons.
		 */
		NONE,

		/**
		 * The <code>opposite</code> (default) button policy - the decrease
		 * button is on one side of the scroll bar, and the increase button is
		 * on the other side of the scroll bar.
		 */
		OPPOSITE,

		/**
		 * The <code>adjacent</code> button policy - both the decrease button
		 * and the increase button are on the same side of the scroll bar
		 * adjacent to each other (like on Mac).
		 */
		ADJACENT,

		/**
		 * The <code>multiple</code> button policy - there are two decrease
		 * buttons on the opposite side of the scroll bar and the increase
		 * button is adjacent to the second decrease button. This combines the
		 * {@link #OPPOSITE} and the {@link #ADJACENT} policies together.
		 */
		MULTIPLE,

		/**
		 * The <code>multiple both</code> button policy - there are two pairs of
		 * decrease-increase buttons on the opposite sides of the scroll bar.
		 * This extends the {@link #MULTIPLE} policy.
		 */
		MULTIPLE_BOTH
	}

	/**
	 * Enumerates possible values for menu gutter fill kind.
	 * 
	 * @author Kirill Grouchnikov
	 * @see SubstanceLookAndFeel#MENU_GUTTER_FILL_KIND
	 */
	public enum MenuGutterFillKind {
		/**
		 * The <code>none</code> fill kind - draws no background in the menu
		 * gutter.
		 */
		NONE,

		/**
		 * The <code>soft fill</code> fill kind - draws light fill background in
		 * the menu gutter.
		 */
		SOFT_FILL,

		/**
		 * The <code>hard fill</code> fill kind - draws darker fill background
		 * in the menu gutter.
		 */
		HARD_FILL,

		/**
		 * The <code>soft</code> fill kind - draws gradient ranging from darker
		 * to light in the menu gutter.
		 */
		SOFT,

		/**
		 * The <code>hard</code> (default) fill kind - draws gradient ranging
		 * from darker to light in the menu gutter.
		 */
		HARD
	}

	/**
	 * Tab content pane border kind.
	 * 
	 * @author Kirill Grouchnikov
	 * @since version 4.1
	 */
	public enum TabContentPaneBorderKind {
		/**
		 * The content pane has full border on all sides plus an additional line
		 * along the tab placement side (as in Firefox 2.0, Internet Explorer
		 * 7.0 and Nimbus). This is the default kind starting from version 4.1.
		 */
		DOUBLE_FULL,

		/**
		 * The content pane has full single border on all sides. This has been
		 * the default kind prior to version 4.1.
		 */
		SINGLE_FULL,

		/**
		 * The content pane has double border along the tab placement side.
		 */
		DOUBLE_PLACEMENT,

		/**
		 * The content pane has single border along the tab placement side.
		 */
		SINGLE_PLACEMENT
	}

	/**
	 * Enumerates configurable Substance-specific widget types for
	 * {@link SubstanceLookAndFeel#setWidgetVisible(javax.swing.JRootPane, boolean, SubstanceWidgetType...)}
	 * API.
	 * 
	 * @author Kirill Grouchnikov
	 */
	public enum SubstanceWidgetType {
		/**
		 * Menu search widget.
		 */
		MENU_SEARCH,

		/**
		 * Title pane heap status widget.
		 */
		TITLE_PANE_HEAP_STATUS
	}
}
