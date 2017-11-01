/*
 * Copyright (c) 2005-2017 Substance Kirill Grouchnikov. All Rights Reserved.
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

/**
 * Animation facet.
 * 
 * @author Kirill Grouchnikov.
 */
public final class AnimationFacet {
	/**
	 * Animation facet display name.
	 */
	protected String displayName;

	/**
	 * Creates a new animation facet.
	 * 
	 * @param displayName
	 *            Display name for the animation facet.
	 * @param isDefaultAllowed
	 *            Indicates whether this animation facet is allowed by default.
	 */
	public AnimationFacet(String displayName, boolean isDefaultAllowed) {
		this.displayName = displayName;
		if (isDefaultAllowed) {
			AnimationConfigurationManager.getInstance().allowAnimations(this);
		}
	}

	/**
	 * Arming a component.
	 */
	public static final AnimationFacet ARM = new AnimationFacet(
			"lafwidgets.core.arm", true);

	/**
	 * Pressing a component.
	 */
	public static final AnimationFacet PRESS = new AnimationFacet(
			"lafwidgets.core.press", true);

	/**
	 * Focusing a component.
	 */
	public static final AnimationFacet FOCUS = new AnimationFacet(
			"lafwidgets.core.focus", true);

	/**
	 * <p>
	 * Focus loop animation. Disabled by default, use
	 * {@link AnimationConfigurationManager#allowAnimations(AnimationFacet)} to
	 * enable.
	 * </p>
	 * 
	 * @since version 3.0
	 */
	public static final AnimationFacet FOCUS_LOOP_ANIMATION = new AnimationFacet(
			"lafwidgets.core.focusLoopAnimation", false);

	/**
	 * Rollover a component.
	 */
	public static final AnimationFacet ROLLOVER = new AnimationFacet(
			"lafwidgets.core.rollover", true);

	/**
	 * Selecting a component.
	 */
	public static final AnimationFacet SELECTION = new AnimationFacet(
			"lafwidgets.core.selection", true);

	/**
	 * <i>Ghosting image</i> effects on button icons when the button is
	 * rolled-over. Disabled by default, use
	 * {@link AnimationConfigurationManager#allowAnimations(AnimationFacet)} to
	 * enable.
	 */
	public static final AnimationFacet GHOSTING_ICON_ROLLOVER = new AnimationFacet(
			"lafwidgets.core.ghosting.iconRollover", false);

	/**
	 * <i>Ghosting image</i> effects on buttons when the button is pressed.
	 * Disabled by default, use
	 * {@link AnimationConfigurationManager#allowAnimations(AnimationFacet)} to
	 * enable.
	 */
	public static final AnimationFacet GHOSTING_BUTTON_PRESS = new AnimationFacet(
			"lafwidgets.core.ghosting.buttonPress", false);

	/**
	 * Glow effect on icons when the relevant control is rolled over. Disabled
	 * by default, use
	 * {@link AnimationConfigurationManager#allowAnimations(AnimationFacet)} to
	 * enable.
	 */
	public static final AnimationFacet ICON_GLOW = new AnimationFacet(
			"lafwidgets.core.iconGlow", false);

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.displayName;
	}
}