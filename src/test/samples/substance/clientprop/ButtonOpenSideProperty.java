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
import java.util.EnumSet;

import javax.swing.*;

import org.pushingpixels.substance.api.SubstanceConstants;
import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import org.pushingpixels.substance.api.SubstanceConstants.Side;
import org.pushingpixels.substance.api.skin.BusinessBlackSteelSkin;

/**
 * Test application that shows the use of the
 * {@link SubstanceLookAndFeel#BUTTON_OPEN_SIDE_PROPERTY} client property.
 * 
 * @author Kirill Grouchnikov
 * @see SubstanceLookAndFeel#BUTTON_OPEN_SIDE_PROPERTY
 */
public class ButtonOpenSideProperty extends JFrame {
	/**
	 * Creates the main frame for <code>this</code> sample.
	 */
	public ButtonOpenSideProperty() {
		super("Buttons with open sides");

		this.setLayout(new FlowLayout());

		JButton buttonA = new JButton("left only");
		// mark button to have open and straight left side
		// using side constant
		buttonA.putClientProperty(SubstanceLookAndFeel.BUTTON_SIDE_PROPERTY,
				SubstanceConstants.Side.LEFT);
		buttonA.putClientProperty(
				SubstanceLookAndFeel.BUTTON_OPEN_SIDE_PROPERTY,
				SubstanceConstants.Side.LEFT);

		JButton buttonB = new JButton("right only");
		// mark button to have open and straight right side using side constant
		buttonB.putClientProperty(SubstanceLookAndFeel.BUTTON_SIDE_PROPERTY,
				SubstanceConstants.Side.RIGHT);
		buttonB.putClientProperty(
				SubstanceLookAndFeel.BUTTON_OPEN_SIDE_PROPERTY,
				SubstanceConstants.Side.RIGHT);

		JButton buttonC = new JButton("left+top");
		// mark button to have open and straight left and top sides
		// using set of side constants
		EnumSet<Side> leftTopSides = EnumSet.of(SubstanceConstants.Side.LEFT,
				SubstanceConstants.Side.TOP);
		buttonC.putClientProperty(SubstanceLookAndFeel.BUTTON_SIDE_PROPERTY,
				leftTopSides);
		buttonC.putClientProperty(
				SubstanceLookAndFeel.BUTTON_OPEN_SIDE_PROPERTY, leftTopSides);

		JButton buttonD = new JButton("right+bottom");
		// mark button to have open and straight right and bottom sides
		// using set of side constants
		EnumSet<Side> rightBottomSides = EnumSet.of(
				SubstanceConstants.Side.RIGHT, SubstanceConstants.Side.BOTTOM);
		buttonD.putClientProperty(SubstanceLookAndFeel.BUTTON_SIDE_PROPERTY,
				rightBottomSides);
		buttonD.putClientProperty(
				SubstanceLookAndFeel.BUTTON_OPEN_SIDE_PROPERTY,
				rightBottomSides);

		this.add(buttonA);
		this.add(buttonB);
		this.add(buttonC);
		this.add(buttonD);

		this.setSize(400, 200);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
				new ButtonOpenSideProperty().setVisible(true);
			}
		});
	}
}
