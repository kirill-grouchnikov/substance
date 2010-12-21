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
package test.samples.substance.clientprop;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import org.pushingpixels.substance.api.SubstanceSkin;
import org.pushingpixels.substance.api.skin.*;

/**
 * Test application that shows the use of the
 * {@link SubstanceLookAndFeel#SKIN_PROPERTY} client property.
 * 
 * @author Kirill Grouchnikov
 * @see SubstanceLookAndFeel#SKIN_PROPERTY
 */
public class SkinProperty extends JFrame {
	/**
	 * Creates the main frame for <code>this</code> sample.
	 */
	public SkinProperty() {
		super("Per-window skins");

		this.setLayout(new FlowLayout());

		JButton autumnSkin = new JButton("Autumn skin");
		autumnSkin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						openSampleFrame(new AutumnSkin());
					}
				});
			}
		});
		this.add(autumnSkin);

		JButton ravenGraphiteSkin = new JButton("Graphite skin");
		ravenGraphiteSkin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						openSampleFrame(new GraphiteSkin());
					}
				});
			}
		});
		this.add(ravenGraphiteSkin);
		this.setSize(400, 200);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Opens a sample frame under the specified skin.
	 * 
	 * @param skin
	 *            Skin.
	 */
	private void openSampleFrame(SubstanceSkin skin) {
		JFrame sampleFrame = new JFrame(skin.getDisplayName());
		sampleFrame.setLayout(new FlowLayout());
		JButton defaultButton = new JButton("active");
		JButton button = new JButton("default");
		JButton disabledButton = new JButton("disabled");
		disabledButton.setEnabled(false);
		sampleFrame.getRootPane().setDefaultButton(defaultButton);

		sampleFrame.add(defaultButton);
		sampleFrame.add(button);
		sampleFrame.add(disabledButton);

		sampleFrame.setVisible(true);
		sampleFrame.pack();
		sampleFrame.setLocationRelativeTo(null);
		sampleFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		sampleFrame.getRootPane().putClientProperty(
				SubstanceLookAndFeel.SKIN_PROPERTY, skin);
		SwingUtilities.updateComponentTreeUI(sampleFrame);
		sampleFrame.repaint();
	}

	/**
	 * The main method for <code>this</code> sample. The arguments are ignored.
	 * 
	 * @param args
	 *            Ignored.
	 */
	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				SubstanceLookAndFeel.setSkin(new BusinessBlackSteelSkin());
				new SkinProperty().setVisible(true);
			}
		});
	}
}
