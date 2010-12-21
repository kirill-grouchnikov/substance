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
package test.samples.lafwidget.clientprop;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingUtilities;

import org.pushingpixels.lafwidget.LafWidget;
import org.pushingpixels.lafwidget.text.PasswordStrengthChecker;
import org.pushingpixels.lafwidget.utils.LafConstants.PasswordStrength;
import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import org.pushingpixels.substance.api.skin.BusinessBlackSteelSkin;

/**
 * Test application that shows the use of the
 * {@link LafWidget#PASSWORD_STRENGTH_CHECKER} client property.
 * 
 * @author Kirill Grouchnikov
 * @see LafWidget#PASSWORD_STRENGTH_CHECKER
 */
public class PasswordStrengthCheckerProperty extends JFrame {
	/**
	 * Creates the main frame for <code>this</code> sample.
	 */
	public PasswordStrengthCheckerProperty() {
		super("Password strength checker");

		this.setLayout(new BorderLayout());
		final JPanel panel = new JPanel(new FlowLayout());
		this.add(panel, BorderLayout.CENTER);

		final JPasswordField jpf = new JPasswordField();
		jpf.setColumns(20);
		panel.add(new JLabel("Start typing password"));
		panel.add(jpf);

		JPanel controls = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		final JCheckBox hasPasswordStrengthChecker = new JCheckBox(
				"Has password strength checker");
		hasPasswordStrengthChecker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (hasPasswordStrengthChecker.isSelected()) {
					jpf.putClientProperty(LafWidget.PASSWORD_STRENGTH_CHECKER,
							new PasswordStrengthChecker() {
								public PasswordStrength getStrength(
										char[] password) {
									if (password == null)
										return PasswordStrength.WEAK;
									int length = password.length;
									if (length < 3)
										return PasswordStrength.WEAK;
									if (length < 6)
										return PasswordStrength.MEDIUM;
									return PasswordStrength.STRONG;
								}

								public String getDescription(
										PasswordStrength strength) {
									if (strength == PasswordStrength.WEAK)
										return "<html>This password is <b>way</b> too weak</html>";
									if (strength == PasswordStrength.MEDIUM)
										return "<html>Come on, you can do<br> a little better than that</html>";
									if (strength == PasswordStrength.STRONG)
										return "OK";
									return null;
								}
							});
				} else {
					jpf.putClientProperty(LafWidget.PASSWORD_STRENGTH_CHECKER,
							null);
				}
			}
		});

		controls.add(hasPasswordStrengthChecker);
		this.add(controls, BorderLayout.SOUTH);

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
				new PasswordStrengthCheckerProperty().setVisible(true);
			}
		});
	}
}
