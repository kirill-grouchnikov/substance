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
package org.pushingpixels.substance.api.painter;

import java.awt.Component;
import java.awt.Point;

import javax.swing.*;

import org.pushingpixels.substance.api.DecorationAreaType;
import org.pushingpixels.substance.internal.painter.DecorationPainterUtils;
import org.pushingpixels.substance.internal.utils.SubstanceCoreUtilities;

public class SubstancePainterUtils {
	public static Point getOffsetInRootPaneCoords(Component comp) {
		JRootPane rootPane = SwingUtilities.getRootPane(comp);
		int dx = 0;
		int dy = 0;
		JComponent titlePane = null;

		if (rootPane != null) {
			titlePane = SubstanceCoreUtilities.getTitlePane(rootPane);

			if (titlePane != null) {
				if (comp.isShowing() && titlePane.isShowing()) {
					dx += (comp.getLocationOnScreen().x - titlePane
							.getLocationOnScreen().x);
					dy += (comp.getLocationOnScreen().y - titlePane
							.getLocationOnScreen().y);
				} else {
					// have to traverse the hierarchy
					Component c = comp;
					dx = 0;
					dy = 0;
					while (c != rootPane) {
						dx += c.getX();
						dy += c.getY();
						c = c.getParent();
					}
					c = titlePane;
					if ((c != null) && (c.getParent() != null)) {
						while (c != rootPane) {
							dx -= c.getX();
							dy -= c.getY();
							c = c.getParent();
						}
					}
				}
			}
		}

		return new Point(dx, dy);
	}

	public static Component getTopMostParentWithDecorationAreaType(
			Component comp, DecorationAreaType type) {
		Component c = comp;
		Component topMostWithSameDecorationAreaType = c;
		while (c != null) {
			if (DecorationPainterUtils.getImmediateDecorationType(c) == type) {
				topMostWithSameDecorationAreaType = c;
			}
			c = c.getParent();
		}
		return topMostWithSameDecorationAreaType;
	}
}
