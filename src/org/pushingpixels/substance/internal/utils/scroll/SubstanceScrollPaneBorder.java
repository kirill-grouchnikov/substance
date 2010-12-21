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
package org.pushingpixels.substance.internal.utils.scroll;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.UIResource;

import org.pushingpixels.substance.api.*;
import org.pushingpixels.substance.api.painter.border.StandardBorderPainter;
import org.pushingpixels.substance.internal.painter.SimplisticSoftBorderPainter;
import org.pushingpixels.substance.internal.utils.SubstanceColorSchemeUtilities;
import org.pushingpixels.substance.internal.utils.SubstanceSizeUtils;

/**
 * Default border on {@link JScrollPane}s. Provides continuous appearance of the
 * border + scroll bars.
 * 
 * @author Kirill Grouchnikov
 */
public class SubstanceScrollPaneBorder implements Border, UIResource {
	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.border.Border#getBorderInsets(java.awt.Component)
	 */
	public Insets getBorderInsets(Component c) {
		float borderStrokeWidth = SubstanceSizeUtils
				.getBorderStrokeWidth(SubstanceSizeUtils
						.getComponentFontSize(c));
		int ins = (int) borderStrokeWidth;
		return new Insets(ins, ins, ins, ins);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.border.Border#isBorderOpaque()
	 */
	public boolean isBorderOpaque() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.border.Border#paintBorder(java.awt.Component,
	 * java.awt.Graphics, int, int, int, int)
	 */
	public void paintBorder(Component c, Graphics g, int x, int y, int width,
			int height) {
		if (!(c instanceof JScrollPane)) {
			// Applications (such as NetBeans RCP) may incorrectly assume
			// that scroll pane border specified by the ""ScrollPane.border"
			// UIManager key by a look-and-feel can be installed on any
			// component. In case this component is not JScrollPane, do
			// nothing.
			return;
		}

		JScrollPane scrollPane = (JScrollPane) c;
		JScrollBar vertical = scrollPane.getVerticalScrollBar();
		JScrollBar horizontal = scrollPane.getHorizontalScrollBar();
		JViewport columnHeader = scrollPane.getColumnHeader();

		StandardBorderPainter painter = new SimplisticSoftBorderPainter();

		SubstanceColorScheme scheme = SubstanceColorSchemeUtilities
				.getColorScheme(c, ColorSchemeAssociationKind.BORDER, c
						.isEnabled() ? ComponentState.ENABLED
						: ComponentState.DISABLED_UNSELECTED);

		float borderStrokeWidth = SubstanceSizeUtils
				.getBorderStrokeWidth(SubstanceSizeUtils
						.getComponentFontSize(c));
		int borderDelta = (int) Math.floor(SubstanceSizeUtils
				.getBorderStrokeWidth(SubstanceSizeUtils
						.getComponentFontSize(c)) / 2.0);
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setStroke(new BasicStroke(borderStrokeWidth,
				BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		x += borderDelta;
		y += borderDelta;
		width -= 2 * borderDelta;
		height -= 2 * borderDelta;

		boolean horizontalVisible = (horizontal != null)
				&& horizontal.isVisible();
		boolean verticalVisible = (vertical != null) && vertical.isVisible();
		boolean hasRowHeader = (scrollPane.getRowHeader() != null);

		if (scrollPane.getComponentOrientation().isLeftToRight()) {
			// top portion
			g2d.setColor(painter.getTopBorderColor(scheme));
			if (verticalVisible && (columnHeader == null)) {
				g2d.drawLine(x, y, x + width - vertical.getWidth(), y);
			} else {
				g2d.drawLine(x, y, x + width, y);
			}

			// left portion
			g2d.setColor(painter.getTopBorderColor(scheme));
			if (horizontalVisible && !hasRowHeader) {
				g2d.drawLine(x, y, x, y + height - horizontal.getHeight());
			} else {
				g2d.drawLine(x, y, x, y + height);
			}

			// bottom portion
			g2d.setColor(painter.getBottomBorderColor(scheme));
			if (horizontalVisible) {
				if (hasRowHeader) {
					g2d.drawLine(x, y + height - 1, x
							+ scrollPane.getRowHeader().getSize().width, y
							+ height - 1);
				}
			} else {
				if (verticalVisible) {
					g2d.drawLine(x, y + height - 1, x + width
							- vertical.getWidth(), y + height - 1);
				} else {
					g2d.drawLine(x, y + height - 1, x + width, y + height - 1);
				}
			}

			// right portion
			g2d.setColor(painter.getBottomBorderColor(scheme));
			if (verticalVisible) {
				// g.drawLine(x + width - 1, y + vertical.getHeight(), x + width
				// - 1, y + height);

				if (columnHeader != null) {
					g2d.drawLine(x + width - 1, y, x + width - 1, y
							+ columnHeader.getHeight());
				}
			} else {
				if (horizontalVisible)
					g2d.drawLine(x + width - 1, y, x + width - 1, y + height
							- horizontal.getHeight());
				else
					g2d.drawLine(x + width - 1, y, x + width - 1, y + height);
			}
		} else {
			// top portion
			g2d.setColor(painter.getTopBorderColor(scheme));
			if (verticalVisible && (columnHeader == null)) {
				g2d.drawLine(x + vertical.getWidth(), y, x + width, y);
			} else {
				g2d.drawLine(x, y, x + width, y);
			}

			// left portion
			g2d.setColor(painter.getBottomBorderColor(scheme));
			if (verticalVisible) {
				// g.drawLine(x, y, x, y + height - horizontal.getHeight());
				if (columnHeader != null) {
					g2d.drawLine(x, y, x, y + columnHeader.getHeight());
				}
			} else {
				if (horizontalVisible) {
					g2d.drawLine(x, y, x, y + height - horizontal.getHeight());
				} else {
					g2d.drawLine(x, y, x, y + height - 1);
				}
			}

			// bottom portion
			g2d.setColor(painter.getBottomBorderColor(scheme));
			if (horizontalVisible) {
				if (hasRowHeader) {
					g2d.drawLine(x + width
							- scrollPane.getRowHeader().getSize().width, y
							+ height - 1, x + width - 1, y + height - 1);
				}
			} else {
				if (verticalVisible) {
					g2d.drawLine(x + vertical.getWidth(), y + height - 1, x
							+ width, y + height - 1);
				} else {
					g2d.drawLine(x, y + height - 1, x + width, y + height - 1);
				}
			}

			// right portion
			g2d.setColor(painter.getTopBorderColor(scheme));
			if (horizontalVisible && !hasRowHeader) {
				g2d.drawLine(x + width - 1, y, x + width - 1, y + height
						- horizontal.getHeight());
			} else {
				g2d.drawLine(x + width - 1, y, x + width - 1, y + height);
			}
		}
		g2d.dispose();
	}
}
