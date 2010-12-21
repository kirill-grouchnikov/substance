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
package org.pushingpixels.substance.internal.utils.border;

import java.awt.*;

import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.AbstractBorder;
import javax.swing.plaf.UIResource;

import org.pushingpixels.substance.api.*;
import org.pushingpixels.substance.internal.utils.*;

/**
 * Border for toolbar.
 * 
 * @author Kirill Grouchnikov
 */
public class SubstanceToolBarBorder extends AbstractBorder implements
		UIResource {
	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.border.Border#paintBorder(java.awt.Component,
	 * java.awt.Graphics, int, int, int, int)
	 */
	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
		// failsafe for LAF change
		if (!SubstanceLookAndFeel.isCurrentLookAndFeel())
			return;

		Graphics2D graphics = (Graphics2D) g;
		graphics.translate(x, y);

		if (((JToolBar) c).isFloatable()) {
			SubstanceColorScheme scheme = SubstanceColorSchemeUtilities
					.getColorScheme(c, ColorSchemeAssociationKind.SEPARATOR,
							ComponentState.ENABLED);
			int dragBumpsWidth = (int) (0.75 * SubstanceSizeUtils
					.getToolBarDragInset(SubstanceSizeUtils
							.getComponentFontSize(c)));
			if (((JToolBar) c).getOrientation() == SwingConstants.HORIZONTAL) {
				// fix for defect 3 on NB module
				int height = c.getHeight() - 4;
				if (height > 0) {
					if (c.getComponentOrientation().isLeftToRight()) {
						graphics.drawImage(SubstanceImageCreator.getDragImage(
								c, scheme, dragBumpsWidth, height, 2), 2, 1,
								null);
					} else {
						graphics.drawImage(SubstanceImageCreator.getDragImage(
								c, scheme, dragBumpsWidth, height, 2), c
								.getBounds().width
								- dragBumpsWidth - 2, 1, null);
					}
				}
			} else // vertical
			{
				// fix for defect 3 on NB module
				int width = c.getWidth() - 4;
				if (width > 0) {
					graphics.drawImage(SubstanceImageCreator.getDragImage(c,
							scheme, width, dragBumpsWidth, 2), 2, 2, null);
				}
			}
		}

		graphics.translate(-x, -y);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.border.Border#getBorderInsets(java.awt.Component)
	 */
	@Override
	public Insets getBorderInsets(Component c) {
		return this.getBorderInsets(c, new Insets(0, 0, 0, 0));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.swing.border.AbstractBorder#getBorderInsets(java.awt.Component,
	 * java.awt.Insets)
	 */
	@Override
	public Insets getBorderInsets(Component c, Insets newInsets) {
		Insets defaultInsets = SubstanceSizeUtils
				.getToolBarInsets(SubstanceSizeUtils.getComponentFontSize(c));
		newInsets.set(defaultInsets.top, defaultInsets.left,
				defaultInsets.bottom, defaultInsets.right);

		JToolBar toolbar = (JToolBar) c;
		if (toolbar.isFloatable()) {
			int dragInset = SubstanceSizeUtils
					.getToolBarDragInset(SubstanceSizeUtils
							.getComponentFontSize(c));
			if (toolbar.getOrientation() == SwingConstants.HORIZONTAL) {
				if (toolbar.getComponentOrientation().isLeftToRight()) {
					newInsets.left = dragInset;
				} else {
					newInsets.right = dragInset;
				}
			} else {// vertical
				newInsets.top = dragInset;
			}
		}

		Insets margin = toolbar.getMargin();

		if (margin != null) {
			newInsets.left += margin.left;
			newInsets.top += margin.top;
			newInsets.right += margin.right;
			newInsets.bottom += margin.bottom;
		}

		return newInsets;
	}
}
