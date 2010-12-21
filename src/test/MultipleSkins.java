/*
 * Copyright (c) 2005-2008 Substance Kirill Grouchnikov. All Rights Reserved.
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
package test;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import org.pushingpixels.substance.api.SubstanceSkin;
import org.pushingpixels.substance.api.skin.*;

import test.check.SampleFrame;

public class MultipleSkins {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				SubstanceLookAndFeel.setSkin(new BusinessBlackSteelSkin());
				JFrame.setDefaultLookAndFeelDecorated(true);

				GraphicsEnvironment ge = GraphicsEnvironment
						.getLocalGraphicsEnvironment();
				GraphicsDevice gd = ge.getDefaultScreenDevice();
				GraphicsConfiguration gc = gd.getDefaultConfiguration();
				Rectangle screenBounds = gc.getBounds();
				screenBounds.x = 0;
				screenBounds.y = 0;
				Insets screenInsets = Toolkit.getDefaultToolkit()
						.getScreenInsets(gc);
				Rectangle maxBounds = new Rectangle(screenBounds.x
						+ screenInsets.left, screenBounds.y + screenInsets.top,
						screenBounds.width
								- ((screenInsets.left + screenInsets.right)),
						screenBounds.height
								- ((screenInsets.top + screenInsets.bottom)));

				SubstanceSkin[][] skinGrid = new SubstanceSkin[][] {
						{ new AutumnSkin(), new BusinessSkin(),
								new BusinessBlueSteelSkin(),
								new BusinessBlackSteelSkin() },
						{ new NebulaSkin(), new CremeSkin(),
								new CremeCoffeeSkin(), new ModerateSkin() },
						{ new OfficeSilver2007Skin(), new SaharaSkin(),
								new MistAquaSkin(), new NebulaBrickWallSkin() },
						{ new RavenSkin(), new GraphiteSkin(),
								new GraphiteGlassSkin(), new MagellanSkin() } };

				int x = maxBounds.x;
				for (int i = 0; i < 4; i++) {
					int y = maxBounds.y;
					for (int j = 0; j < 3; j++) {
						SampleFrame skinFrame = new SampleFrame();
						skinFrame.getRootPane().putClientProperty(
								SubstanceLookAndFeel.SKIN_PROPERTY,
								skinGrid[i][j]);
						SwingUtilities.updateComponentTreeUI(skinFrame);
						skinFrame.setBounds(x, y, maxBounds.width / 4,
								maxBounds.height / 3);
						skinFrame.setVisible(true);
						skinFrame
								.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						y += maxBounds.height / 3;
					}
					x += maxBounds.width / 4;
				}

			}
		});
	}

}
