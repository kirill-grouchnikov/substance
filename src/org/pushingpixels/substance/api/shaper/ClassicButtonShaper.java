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
package org.pushingpixels.substance.api.shaper;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.util.Set;

import javax.swing.*;
import javax.swing.border.Border;

import org.pushingpixels.substance.api.SubstanceConstants;
import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import org.pushingpixels.substance.internal.utils.*;
import org.pushingpixels.substance.internal.utils.border.SubstanceBorder;
import org.pushingpixels.substance.internal.utils.border.SubstanceButtonBorder;

/**
 * Button shaper that returns rectangular buttons with slightly rounded corners
 * (ala Windows XP). This class is part of officially supported API.
 * 
 * @author Kirill Grouchnikov
 */
public class ClassicButtonShaper implements SubstanceButtonShaper,
		RectangularButtonShaper {
	/**
	 * Cache of already computed contours.
	 */
	private final static LazyResettableHashMap<GeneralPath> contours = new LazyResettableHashMap<GeneralPath>(
			"ClassicButtonShaper");

	/**
	 * Reusable instance of this shaper.
	 */
	public static final ClassicButtonShaper INSTANCE = new ClassicButtonShaper();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.pushingpixels.substance.button.SubstanceButtonShaper#getDisplayName()
	 */
	public String getDisplayName() {
		return "Classic";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.pushingpixels.substance.shaper.SubstanceButtonShaper#getButtonOutline
	 * (javax .swing.AbstractButton, java.awt.Insets, int, int, boolean)
	 */
	@Override
	public GeneralPath getButtonOutline(AbstractButton button, Insets insets,
			int width, int height, boolean isInner) {
		Set<SubstanceConstants.Side> straightSides = SubstanceCoreUtilities
				.getSides(button, SubstanceLookAndFeel.BUTTON_SIDE_PROPERTY);

		float radius = this.getCornerRadius(button, insets);
		if (isInner) {
			radius -= (int) SubstanceSizeUtils
					.getBorderStrokeWidth(SubstanceSizeUtils
							.getComponentFontSize(button));
			if (radius < 0.0f)
				radius = 0.0f;
		}

		HashMapKey key = SubstanceCoreUtilities.getHashKey(width, height,
				straightSides, radius, insets);

		GeneralPath result = contours.get(key);
		if (result != null) {
			return result;
		}

		result = SubstanceOutlineUtilities.getBaseOutline(width, height,
				radius, straightSides, insets);
		contours.put(key, result);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.pushingpixels.substance.button.SubstanceButtonShaper#getButtonBorder
	 * (javax .swing.AbstractButton)
	 */
	public Border getButtonBorder(final AbstractButton button) {
		return new SubstanceButtonBorder(ClassicButtonShaper.class) {
			public Insets getBorderInsets(Component c) {
				int fontSize = SubstanceSizeUtils.getComponentFontSize(button);
				Insets buttonInsets = SubstanceSizeUtils
						.getButtonInsets(fontSize);
				int focusPadding = SubstanceSizeUtils
						.getFocusRingPadding(fontSize);
				int lrPadding = SubstanceCoreUtilities.hasText(button) ? SubstanceSizeUtils
						.getTextButtonLRPadding(fontSize)
						: 0;
				Set<SubstanceConstants.Side> openSides = SubstanceCoreUtilities
						.getSides(button,
								SubstanceLookAndFeel.BUTTON_OPEN_SIDE_PROPERTY);
				int left = lrPadding
						+ buttonInsets.left
						+ focusPadding
						+ ((openSides != null)
								&& openSides
										.contains(SubstanceConstants.Side.LEFT) ? -1
								: 0);
				int right = lrPadding
						+ buttonInsets.right
						+ focusPadding
						+ ((openSides != null)
								&& openSides
										.contains(SubstanceConstants.Side.RIGHT) ? -1
								: 0);
				int top = buttonInsets.top
						+ ((openSides != null)
								&& openSides
										.contains(SubstanceConstants.Side.TOP) ? -1
								: 0);
				int bottom = buttonInsets.bottom
						+ ((openSides != null)
								&& openSides
										.contains(SubstanceConstants.Side.BOTTOM) ? -1
								: 0);
				return new Insets(top, left, bottom, right);
			}
		};
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.pushingpixels.substance.button.SubstanceButtonShaper#getPreferredSize
	 * (javax .swing.AbstractButton, java.awt.Dimension)
	 */
	public Dimension getPreferredSize(AbstractButton button,
			Dimension uiPreferredSize) {
		Dimension result;
		boolean toTweakWidth = false;
		boolean toTweakHeight = false;

		Icon icon = button.getIcon();
		boolean hasIcon = SubstanceCoreUtilities.hasIcon(button);
		boolean hasText = SubstanceCoreUtilities.hasText(button);
		Insets margin = button.getMargin();

		result = uiPreferredSize;

		boolean hasNoMinSizeProperty = SubstanceCoreUtilities
				.hasNoMinSizeProperty(button);
		if ((!hasNoMinSizeProperty) && hasText) {
			int baseWidth = uiPreferredSize.width;
			baseWidth = Math.max(baseWidth, SubstanceSizeUtils
					.getMinButtonWidth(SubstanceSizeUtils
							.getComponentFontSize(button)));
			result = new Dimension(baseWidth, uiPreferredSize.height);
			int baseHeight = result.height;
			// int padding = SubstanceSizeUtils
			// .getButtonHeightPadding(SubstanceSizeUtils
			// .getComponentFontSize(button));
			// baseHeight += padding;
			// baseHeight = Math.max(baseHeight, SubstanceSizeUtils
			// .getMinButtonHeight(SubstanceSizeUtils
			// .getComponentFontSize(button)));
			result = new Dimension(result.width, baseHeight);
		} else {
			if (hasNoMinSizeProperty) {
				if (margin != null) {
					result = new Dimension(result.width + margin.left
							+ margin.right, result.height + margin.top
							+ margin.bottom);
				}
			}
		}

		int fontSize = SubstanceSizeUtils.getComponentFontSize(button);
		int extraPadding = SubstanceSizeUtils.getExtraPadding(fontSize);
		int focusPadding = SubstanceSizeUtils.getFocusRingPadding(fontSize);
		int iconPaddingWidth = 6 + 2 * extraPadding + 2 * focusPadding;
		int iconPaddingHeight = 6 + 2 * extraPadding;
		if (margin != null) {
			iconPaddingWidth = Math.max(iconPaddingWidth, margin.left
					+ margin.right);
			iconPaddingHeight = Math.max(iconPaddingHeight, margin.top
					+ margin.bottom);
		}
		if (hasIcon) {
			// check the icon height
			int iconHeight = icon.getIconHeight();
			if (iconHeight > (result.getHeight() - iconPaddingHeight)) {
				result = new Dimension(result.width, iconHeight);
				toTweakHeight = true;
			}
			int iconWidth = icon.getIconWidth();
			if (iconWidth > (result.getWidth() - iconPaddingWidth)) {
				result = new Dimension(iconWidth, result.height);
				toTweakWidth = true;
			}
		}
		// else {
		// if (hasText) {
		// result = new Dimension(result.width + 2 * extraPadding,
		// result.height);
		// }
		// }

		if (SubstanceCoreUtilities.isScrollBarButton(button)) {
			toTweakWidth = false;
			toTweakHeight = false;
		}

		if (toTweakWidth) {
			result = new Dimension(result.width + iconPaddingWidth,
					result.height);
		}
		if (toTweakHeight) {
			result = new Dimension(result.width, result.height
					+ iconPaddingHeight);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.pushingpixels.substance.button.SubstanceButtonShaper#isProportionate
	 * ()
	 */
	public boolean isProportionate() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.pushingpixels.substance.shaper.RectangularButtonShaper#getCornerRadius
	 * (javax .swing.JComponent, java.awt.Insets)
	 */
	@Override
	public float getCornerRadius(AbstractButton button, Insets insets) {
		float radius = SubstanceSizeUtils
				.getClassicButtonCornerRadius(SubstanceSizeUtils
						.getComponentFontSize(button));
		if (button instanceof SubstanceInternalArrowButton) {
			Border parentBorder = ((JComponent) button.getParent()).getBorder();
			if (parentBorder instanceof SubstanceBorder) {
				radius *= ((SubstanceBorder) parentBorder)
						.getRadiusScaleFactor();
			}
		}
		if (SubstanceCoreUtilities.isToolBarButton(button)) {
			radius = SubstanceCoreUtilities.getToolbarButtonCornerRadius(
					button, insets);
		}
		return radius;
	}
}
