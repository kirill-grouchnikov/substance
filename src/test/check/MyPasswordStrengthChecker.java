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
package test.check;

import org.pushingpixels.lafwidget.text.PasswordStrengthChecker;
import org.pushingpixels.lafwidget.utils.LafConstants.PasswordStrength;

/**
 * A custom password strength checker for the test application.
 * 
 * @author Kirill Grouchnikov
 */
public class MyPasswordStrengthChecker implements PasswordStrengthChecker {
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pushingpixels.lafwidget.text.PasswordStrengthChecker#getStrength(char[])
	 */
	public PasswordStrength getStrength(char[] password) {
		if (password == null)
			return PasswordStrength.WEAK;
		int length = password.length;
		if (length < 3)
			return PasswordStrength.WEAK;
		if (length < 6)
			return PasswordStrength.MEDIUM;
		return PasswordStrength.STRONG;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pushingpixels.lafwidget.text.PasswordStrengthChecker#getDescription(org.pushingpixels.lafwidget.utils.LafConstants.PasswordStrength)
	 */
	public String getDescription(PasswordStrength strength) {
		if (strength == PasswordStrength.WEAK)
			return "<html>This password is <b>way</b> too weak</html>";
		if (strength == PasswordStrength.MEDIUM)
			return "<html>Come on, you can do<br> a little better than that</html>";
		if (strength == PasswordStrength.STRONG)
			return "OK";
		return null;
	}
}