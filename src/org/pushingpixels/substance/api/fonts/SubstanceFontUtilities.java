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
package org.pushingpixels.substance.api.fonts;

import java.awt.Font;
import java.security.AccessController;
import java.security.PrivilegedAction;

import javax.swing.UIDefaults;
import javax.swing.plaf.FontUIResource;

import org.pushingpixels.lafwidget.utils.LookUtils;
import org.pushingpixels.substance.internal.fonts.*;
import org.pushingpixels.substance.internal.utils.SubstanceSizeUtils;

/**
 * Font-related utilities.
 * 
 * @author Kirill Grouchnikov
 */
public class SubstanceFontUtilities {
	/**
	 * Font set implementation for Substance. This is used to make the window
	 * title font bold.
	 * 
	 * @author Kirill Grouchnikov
	 */
	private static class SubstanceFontSet implements FontSet {
		/**
		 * The default system font set.
		 */
		private FontSet systemFontSet;

		/**
		 * Creates a new font set for Substance.
		 * 
		 * @param systemFontSet
		 *            The default system font set.
		 */
		public SubstanceFontSet(FontSet systemFontSet) {
			this.systemFontSet = systemFontSet;
		}

		/**
		 * Returns Substance-specific font resource.
		 * 
		 * @param systemFont
		 *            The default system font.
		 * @return Substance-specific font resource.
		 */
		private FontUIResource getSubstanceFont(FontUIResource systemFont) {
			return systemFont;
		}

		/**
		 * Returns Substance-specific font resource.
		 * 
		 * @param systemFont
		 *            The default system font.
		 * @param toBoldify
		 *            If <code>true</code>, the original font (the first
		 *            parameter) is boldified.
		 * @param extraFontSize
		 *            Extra font size in pixels.
		 * @return Substance-specific font resource.
		 */
		private FontUIResource getSubstanceFont(FontUIResource systemFont,
				boolean toBoldify, int extraFontSize) {
			boolean isOrigItalic = systemFont.isItalic();
			int newStyle = systemFont.getStyle();
			if (toBoldify) {
				if (isOrigItalic)
					newStyle = Font.ITALIC + Font.BOLD;
				else
					newStyle = Font.BOLD;
			}
			return new FontUIResource(systemFont.deriveFont(
					(float) (systemFont.getSize() + extraFontSize)).deriveFont(
					newStyle));
			// return new FontUIResource(systemFont.getFontName(), newStyle,
			// systemFont.getSize() + extraFontSize);
		}

		public FontUIResource getControlFont() {
			return this.getSubstanceFont(this.systemFontSet.getControlFont());
		}

		public FontUIResource getMenuFont() {
			return this.getSubstanceFont(this.systemFontSet.getMenuFont());
		}

		public FontUIResource getMessageFont() {
			return this.getSubstanceFont(this.systemFontSet.getMessageFont());
		}

		public FontUIResource getSmallFont() {
			return this.getSubstanceFont(this.systemFontSet.getSmallFont(),
					false, 1);
		}

		public FontUIResource getTitleFont() {
			return this.getSubstanceFont(this.systemFontSet.getTitleFont());
		}

		public FontUIResource getWindowTitleFont() {
			return this.getSubstanceFont(this.systemFontSet
					.getWindowTitleFont(), true, 1);
		}
	}

	/**
	 * Returns the default platform-specific font policy.
	 * 
	 * @return Default platform-specific font policy.
	 */
	public static FontPolicy getDefaultFontPolicy() {
		// boolean toWrapPolicy = !LookUtils.IS_OS_MAC;
		FontPolicy defaultPolicy = FontPolicies.getDefaultPlasticPolicy();
		boolean isKDE = false;
		// boolean isGnome = false;
		try {
			isKDE = DefaultKDEFontPolicy.isKDERunning();
		} catch (Throwable t) {
			// security access - too bad for KDE desktops.
		}
		if (LookUtils.IS_OS_WINDOWS) {
			defaultPolicy = FontPolicies.getDefaultWindowsPolicy();
		} else {
			if (LookUtils.IS_OS_MAC) {
				defaultPolicy = new DefaultMacFontPolicy();
			} else {
				if (isKDE) {
					// new in version 4.2
					defaultPolicy = new DefaultKDEFontPolicy();
				} else {
					try {
						String desktop = AccessController
								.doPrivileged(new PrivilegedAction<String>() {
									public String run() {
										return System
												.getProperty("sun.desktop");
									}
								});
						if ("gnome".equals(desktop)) {
							// new in version 4.1
							defaultPolicy = new DefaultGnomeFontPolicy();
							// isGnome = true;
						}
					} catch (Throwable t) {
						// security access - too bad for Gnome desktops.
					}
				}
			}
		}

		// System.out.println("System " + System.getProperty("os.name")
		// + ", policy " + defaultPolicy.getClass().getName());
		//
		SubstanceSizeUtils.resetPointsToPixelsRatio(defaultPolicy);
		final FontPolicy fontPolicy = FontPolicies
				.customSettingsPolicy(defaultPolicy);
		if (LookUtils.IS_OS_MAC || isKDE)// || isGnome)
			return fontPolicy;
		return new FontPolicy() {
			public FontSet getFontSet(String lafName, UIDefaults table) {
				FontSet baseResult = fontPolicy.getFontSet(lafName, table);
				FontSet substanceFontSet = new SubstanceFontSet(baseResult);
				return substanceFontSet;
			}
		};
	}

	/**
	 * Returns scaled platform-specific font policy.
	 * 
	 * @param scaleFactor
	 *            Scale factor. Should be positive.
	 * @return Scaled platform-specific font policy.
	 */
	public static FontPolicy getScaledFontPolicy(final float scaleFactor) {
		final FontSet substanceCoreFontSet = SubstanceFontUtilities
				.getDefaultFontPolicy().getFontSet("Substance", null);
		// Create the scaled font set
		FontPolicy newFontPolicy = new FontPolicy() {
			public FontSet getFontSet(String lafName, UIDefaults table) {
				return new ScaledFontSet(substanceCoreFontSet, scaleFactor);
			}
		};
		return newFontPolicy;
	}
}
