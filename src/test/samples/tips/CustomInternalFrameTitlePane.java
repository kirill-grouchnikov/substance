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
package test.samples.tips;

import java.awt.*;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;

import org.pushingpixels.substance.api.*;
import org.pushingpixels.substance.api.painter.decoration.SubstanceDecorationPainter;
import org.pushingpixels.substance.api.skin.BusinessBlackSteelSkin;
import org.pushingpixels.substance.internal.ui.SubstanceInternalFrameUI;

public class CustomInternalFrameTitlePane extends JFrame {
	private static class MyInternalFrameTitlePane extends JComponent {
		@Override
		protected void paintComponent(Graphics g) {
			SubstanceSkin currentSkin = SubstanceLookAndFeel
					.getCurrentSkin(this);
			SubstanceDecorationPainter decoPainter = currentSkin
					.getDecorationPainter();
			Graphics2D g2d = (Graphics2D) g.create();
			decoPainter.paintDecorationArea(g2d, this,
					DecorationAreaType.SECONDARY_TITLE_PANE, getWidth(),
					getHeight(), currentSkin);
			g2d.dispose();
		}

		@Override
		public Dimension getPreferredSize() {
			return new Dimension(30, 80);
		}
	}

	public static class MyInternalFrameUI extends SubstanceInternalFrameUI {
		public MyInternalFrameUI(JInternalFrame jif) {
			super(jif);
		}

		public static ComponentUI createUI(JComponent comp) {
			return new MyInternalFrameUI((JInternalFrame) comp);
		}

		@Override
		protected JComponent createNorthPane(JInternalFrame w) {
			return new MyInternalFrameTitlePane();
		}
	}

	public CustomInternalFrameTitlePane() {
		super("Custom title pane");

		this.setLayout(new BorderLayout());
		JDesktopPane jdp = new JDesktopPane();
		this.add(jdp, BorderLayout.CENTER);

		JInternalFrame jif = new JInternalFrame("internal frame");
		jdp.add(jif);
		jif.setBounds(100, 100, 300, 200);
		jif.setVisible(true);

		this.setSize(500, 400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame.setDefaultLookAndFeelDecorated(true);
				SubstanceLookAndFeel.setSkin(new BusinessBlackSteelSkin());
				UIManager.put("InternalFrameUI", MyInternalFrameUI.class
						.getName());
				new CustomInternalFrameTitlePane().setVisible(true);
			}
		});
	}

}
