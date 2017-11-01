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

import javax.swing.JComponent;
import javax.swing.JPasswordField;
import javax.swing.UIManager;

import org.pushingpixels.substance.api.painter.preview.DefaultPreviewPainter;
import org.pushingpixels.substance.api.painter.preview.PreviewPainter;
import org.pushingpixels.substance.api.password.PasswordStrengthChecker;
import org.pushingpixels.substance.internal.widget.text.LockBorderWidget;

/**
 * Interface for Substance widgets (behavioral traits).
 * 
 * @author Kirill Grouchnikov
 */
public abstract class SubstanceWidget<T extends JComponent> {
	/**
	 * <p>
	 * Client property name for specifying that the {@link LockBorderWidget}
	 * should put a lock icon. This property can be set either on a single
	 * component or globally on {@link UIManager}. The value in both cases
	 * should be either {@link Boolean#TRUE} or {@link Boolean#FALSE}.
	 * </p>
	 * 
	 * @since 3.2
	 */
	public final static String HAS_LOCK_ICON = "substance.widget.hasLockIcon";

	/**
	 * <p>
	 * Client property name for specifying the preview painter for a component.
	 * This property can be set either on a component or globally on
	 * {@link UIManager}. The value in both cases should be an instance of
	 * {@link PreviewPainter}. Default implementation is available in the
	 * {@link DefaultPreviewPainter}.
	 * </p>
	 * 
	 * <p>
	 * Here is an example of a scroll pane with default preview painter
	 * installed on the internal component:
	 * </p>
	 * 
	 * <code>
	 * JPanel myPanel = new JPanel();<br>
	 * myPanel.putClientProperty(SubstanceWidget.PANE_PREVIEW_PAINTER,<br>
	 * &nbsp;&nbsp;new DefaultPreviewPainter());<br>
	 * JScrollPane jsp = new JScrollPane(myPanel);
	 * </code>
	 */
	public final static String COMPONENT_PREVIEW_PAINTER = "substance.widget.componentPreviewPainter";

	/**
	 * <p>
	 * Client property name for specifying password strength checker for a
	 * password field. The value should be an instance of
	 * {@link PasswordStrengthChecker}, otherwise will be ignored. This property
	 * must be set on a specific {@link JPasswordField}. Here is an example:
	 * </p>
	 * 
	 * <code>
	 * &nbsp;&nbsp;JPasswordField jpf = new JPasswordField("password", 10);<br>
	 * &nbsp;&nbsp;jpf.putClientProperty(SubstanceWidget.PASSWORD_STRENGTH_CHECKER,<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;new PasswordStrengthChecker() {<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;public PasswordStrength getStrength(char[] password) {<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;if (password == null)<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;return PasswordStrength.WEAK;<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;int length = password.length;<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;if (length < 3)<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;return PasswordStrength.WEAK;<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;if (length < 6)<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;return PasswordStrength.MEDIUM;<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;return PasswordStrength.STRONG;<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;}<br>
	 * <br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;public String getDescription(PasswordStrength strength) {<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;if (strength == PasswordStrength.WEAK)<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;return "&lt;html&gt;This password is &lt;b&gt;way&lt;/b&gt; too weak&lt;/html&gt;";<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;if (strength == PasswordStrength.MEDIUM)<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;return "&lt;html&gt;Come on, you can do&lt;br&gt; a little better than that&lt;/html&gt;";<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;if (strength == PasswordStrength.STRONG)<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;return "OK";<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;return null;<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;}<br>
	 * &nbsp;&nbsp;});
	 * </code>
	 */
	public final static String PASSWORD_STRENGTH_CHECKER = "substance.widget.passwordStrengthChecker";

	/**
	 * <p>
	 * Client property name for specifying that the text component contents
	 * should be selected on focus gain. This property can be set either on a
	 * single text component or globally on {@link UIManager}. The value in both
	 * cases should be either {@link Boolean#TRUE} or {@link Boolean#FALSE}.
	 * </p>
	 * 
	 * <p>
	 * Here is an example of globally set property (all text components that
	 * don't specify {@link Boolean#FALSE} as a client property will have the
	 * "select all on focus gain" behaviour):
	 * </p>
	 * 
	 * <code>
	 * &nbsp;&nbsp;UIManager.put(SubstanceWidget.TEXT_SELECT_ON_FOCUS, Boolean.TRUE);
	 * </code>
	 */
	public final static String TEXT_SELECT_ON_FOCUS = "substance.widget.textSelectAllOnFocus";

	/**
	 * <p>
	 * Client property name for specifying that the text component contents
	 * should flip selection on ESCAPE key press. This property can be set on a
	 * single text component. The value should be either {@link Boolean#TRUE} or
	 * {@link Boolean#FALSE}.
	 * </p>
	 * 
	 * <p>
	 * Here is an example of this property set on this specific text field:
	 * </p>
	 * 
	 * <code>
	 * &nbsp;&nbsp;myTextField.put(SubstanceWidget.TEXT_FLIP_SELECT_ON_ESCAPE, Boolean.TRUE);
	 * </code>
	 */
	public final static String TEXT_FLIP_SELECT_ON_ESCAPE = "substance.widget.textFlipSelectOnEscape";

	/**
	 * <p>
	 * Client property name for specifying that the text component should have
	 * the edit context menu (with Cut / Copy / Paste / ... menu items). This
	 * property can be set either on a single text component or globally on
	 * {@link UIManager}. The value in both cases should be either
	 * {@link Boolean#TRUE} or {@link Boolean#FALSE}.
	 * </p>
	 * 
	 * <p>
	 * Here is an example of globally set property (all text components that
	 * don't specify {@link Boolean#FALSE} as a client property will have the
	 * the edit context menu):
	 * </p>
	 * 
	 * <code>
	 * &nbsp;&nbsp;UIManager.put(SubstanceWidget.TEXT_EDIT_CONTEXT_MENU, Boolean.TRUE);
	 * </code>
	 */
	public final static String TEXT_EDIT_CONTEXT_MENU = "substance.widget.textEditContextMenu";

	/**
	 * Client property name for specifying that the tree component should have
	 * automatic drag and drop support. This property can be set either on a
	 * single tree or globally on {@link UIManager}. The value in both cases
	 * should be either {@link Boolean#TRUE} or {@link Boolean#FALSE}.<br>
	 * <br>
	 * Here is an example of globally set property (all trees that don't specify
	 * {@link Boolean#FALSE} as a client property will have the automatic drag
	 * and drop support): <br>
	 * <br>
	 * <code>
	 * &nbsp;&nbsp;UIManager.put(SubstanceWidget.TREE_AUTO_DND_SUPPORT, Boolean.TRUE);
	 * </code>
	 */
	public final static String TREE_AUTO_DND_SUPPORT = "substance.widget.treeAutoDnDSupport";

	/**
	 * <p>
	 * Client property name for specifying that a scroll pane should have
	 * auto-scroll support invoked on middle-mouse button click. This property
	 * can be installed on a single scroll pane or globally on {@link UIManager}
	 * , and the value should be either {@link Boolean#TRUE} or
	 * {@link Boolean#FALSE}.
	 * </p>
	 * <p>
	 * Here is an example of a scroll bar that has auto-scroll installed.
	 * </p>
	 * <code>
	 * JScrollPane scrollPane = ...<br>
	 * scrollPane.putClientProperty(SubstanceWidget.AUTO_SCROLL, Boolean.TRUE);
	 * </code>
	 * 
	 * @since version 3.4
	 */
	public final static String AUTO_SCROLL = "substance.widget.scroll.auto";

	/**
     * Associated component.
     */
    protected T jcomp;

	/**
	 * Associates a component with <code>this</code> widget.
	 * 
	 * @param jcomp
	 *            Component.
	 */
	public void setComponent(T jcomp) {
        this.jcomp = jcomp;
    }

	/**
	 * Installs UI on the associated component.
	 */
	public void installUI() {
	}

	/**
	 * Installs default settings for the associated component.
	 */
	public void installDefaults() {
    }

	/**
	 * Installs listeners for the associated component.
	 */
	public void installListeners() {
    }

	/**
	 * Installs components for the associated component.
	 */
	public void installComponents() {
    }

	/**
	 * Uninstalls UI on the associated component.
	 */
	public void uninstallUI() {
    }

	/**
	 * Uninstalls default settings for the associated component.
	 */
	public void uninstallDefaults() {
    }

	/**
	 * Uninstalls listeners for the associated component.
	 */
	public void uninstallListeners() {
    }

	/**
	 * Uninstalls components for the associated component.
	 */
	public void uninstallComponents() {
    }
}
