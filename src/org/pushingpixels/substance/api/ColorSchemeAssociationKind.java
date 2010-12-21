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

import java.util.*;

import javax.swing.JCheckBox;
import javax.swing.JTabbedPane;

/**
 * Allows associating different color schemes to different visual parts of UI
 * components. For example, the {@link JCheckBox} has three different visual
 * areas:
 * <ul>
 * <li>Border - assciated with {@link #BORDER}</li>
 * <li>Fill - associated with {@link #FILL}</li>
 * <li>Check mark - associated with {@link #MARK}</li>
 * </ul>
 * 
 * Applications can create custom instances of this class to further refine the
 * control over the painting. In this case, the custom UI delegates must be
 * created to use these new association kinds.
 * 
 * @author Kirill Grouchnikov
 * @since version 5.1
 */
public class ColorSchemeAssociationKind {
	/**
	 * All known association kind values.
	 */
	private static Set<ColorSchemeAssociationKind> values = new HashSet<ColorSchemeAssociationKind>();

	/**
	 * Name for this association kind.
	 */
	private String name;

	/**
	 * Fallback for this association kind. This is used when no color scheme is
	 * associated with this kind. For example, {@link #TAB_BORDER} specifies
	 * that its fallback is {@link #BORDER}. When the {@link JTabbedPane} UI
	 * delegate is painting the tabs, it will try to use the color scheme
	 * associated with {@link #TAB_BORDER}. If none was registered, it will fall
	 * back to use the color scheme associated with {@link #BORDER}, and if that
	 * is not registered as well, will use the color scheme associated with
	 * {@link #FILL}.
	 */
	private ColorSchemeAssociationKind fallback;

	/**
	 * Creates a new association kind.
	 * 
	 * @param name
	 *            Association kind name.
	 * @param fallback
	 *            Fallback association kind. This is used when no color scheme
	 *            is associated with this kind. For example, {@link #TAB_BORDER}
	 *            specifies that its fallback is {@link #BORDER}. When the
	 *            {@link JTabbedPane} UI delegate is painting the tabs, it will
	 *            try to use the color scheme associated with
	 *            {@link #TAB_BORDER}. If none was registered, it will fall back
	 *            to use the color scheme associated with {@link #BORDER}, and
	 *            if that is not registered as well, will use the color scheme
	 *            associated with {@link #FILL}.
	 */
	public ColorSchemeAssociationKind(String name,
			ColorSchemeAssociationKind fallback) {
		this.name = name;
		this.fallback = fallback;
		values.add(this);
	}

	@Override
	public String toString() {
		return this.name;
	}

	/**
	 * The default visual area that is used for the inner part of most controls.
	 */
	public static final ColorSchemeAssociationKind FILL = new ColorSchemeAssociationKind(
			"fill", null);

	/**
	 * Visual area of separators.
	 */
	public static final ColorSchemeAssociationKind SEPARATOR = new ColorSchemeAssociationKind(
			"separator", FILL);

	/**
	 * Fill visual area of the tabs.
	 */
	public static final ColorSchemeAssociationKind TAB = new ColorSchemeAssociationKind(
			"tab", FILL);

	/**
	 * Border visual area of non-tab controls.
	 */
	public static final ColorSchemeAssociationKind BORDER = new ColorSchemeAssociationKind(
			"border", FILL);

	/**
	 * Visual area of marks. Used for painting check marks of checkboxes and
	 * radio buttons, as well as arrow icons of combo boxes, spinners and more.
	 */
	public static final ColorSchemeAssociationKind MARK = new ColorSchemeAssociationKind(
			"mark", BORDER);

	/**
	 * Border visual area of the tabs.
	 */
	public static final ColorSchemeAssociationKind TAB_BORDER = new ColorSchemeAssociationKind(
			"tabBorder", BORDER);

	/**
	 * Highlight visual areas for lists, tables, trees and menus.
	 */
	public static final ColorSchemeAssociationKind HIGHLIGHT = new ColorSchemeAssociationKind(
			"highlight", FILL);

	/**
	 * Highlight visual areas for text components.
	 */
	public static final ColorSchemeAssociationKind TEXT_HIGHLIGHT = new ColorSchemeAssociationKind(
			"textHighlight", HIGHLIGHT);

	/**
	 * Border visual areas for highlighted regions of lists, tables, trees and
	 * menus.
	 */
	public static final ColorSchemeAssociationKind HIGHLIGHT_BORDER = new ColorSchemeAssociationKind(
			"highlightBorder", BORDER);

	/**
	 * Visual area of marks in highlighted regions of lists, tables, trees and
	 * menus.
	 */
	public static final ColorSchemeAssociationKind HIGHLIGHT_MARK = new ColorSchemeAssociationKind(
			"highlightMark", MARK);

	/**
	 * Returns all available association kinds.
	 * 
	 * @return All available association kinds.
	 */
	public static Set<ColorSchemeAssociationKind> values() {
		return Collections.unmodifiableSet(values);
	}

	/**
	 * Returns the fallback for this association kind.
	 * 
	 * @return The fallback for this association kind.
	 */
	public ColorSchemeAssociationKind getFallback() {
		return fallback;
	}
}