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

import javax.swing.JProgressBar;
import javax.swing.JRootPane;
import javax.swing.text.JTextComponent;

/**
 * Defies a single facet of core and custom {@link ComponentState}s. See
 * Javadocs of the {@link ComponentState} class for more information on state
 * facets.
 * 
 * <p>
 * This class is experimental API and is likely to change in the next few
 * releases.
 * </p>
 */
public final class ComponentStateFacet {
	int value;

	String name;

	/**
	 * Facet that describes the enabled bit.
	 */
	public static final ComponentStateFacet ENABLE = new ComponentStateFacet(
			"enable", 0);

	/**
	 * Facet that describes the rollover bit.
	 */
	public static final ComponentStateFacet ROLLOVER = new ComponentStateFacet(
			"rollover", 10);

	/**
	 * Facet that describes the selection bit.
	 */
	public static final ComponentStateFacet SELECTION = new ComponentStateFacet(
			"selection", 10);

	/**
	 * Facet that describes the press bit.
	 */
	public static final ComponentStateFacet PRESS = new ComponentStateFacet(
			"press", 50);

	/**
	 * Facet that describes the arm bit. This is relevant for menu items.
	 */
	public static final ComponentStateFacet ARM = new ComponentStateFacet(
			"arm", 10);

	/**
	 * Facet that describes the default bit. This is relevant for buttons which
	 * can be set as default with the
	 * {@link JRootPane#setDefaultButton(javax.swing.JButton)} API.
	 */
	public static final ComponentStateFacet DEFAULT = new ComponentStateFacet(
			"default", 500);

	/**
	 * Facet that describes the determinate bit. This is relevant for
	 * {@link JProgressBar} control and its
	 * {@link JProgressBar#setIndeterminate(boolean)} API.
	 */
	public static final ComponentStateFacet DETERMINATE = new ComponentStateFacet(
			"determinate", 10);

	/**
	 * Facet that describes the editable bit. This is relevant for
	 * {@link JTextComponent} derived controls and its
	 * {@link JTextComponent#setEditable(boolean)} API.
	 */
	public static final ComponentStateFacet EDITABLE = new ComponentStateFacet(
			"editable", 50);

	/**
	 * Creates a new facet.
	 * 
	 * @param name
	 *            Facet name.
	 * @param value
	 *            Facet value. This is used in the matching algorithm described
	 *            in the javadocs of {@link ComponentState}. The larger the
	 *            value, the more importance is given to the specific facet.
	 */
	public ComponentStateFacet(String name, int value) {
		this.name = name;
		if (value < 0) {
			throw new IllegalArgumentException(
					"Facet value must be non-negative");
		}
		this.value = value;
	}

	@Override
	public String toString() {
		return this.name + ":" + this.value;
	}
}
