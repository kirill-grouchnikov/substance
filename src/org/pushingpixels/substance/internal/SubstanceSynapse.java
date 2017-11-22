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
package org.pushingpixels.substance.internal;

import org.pushingpixels.substance.internal.widget.text.LockBorderWidget;

public class SubstanceSynapse {
    /**
     * Client property name for specifying that the {@link LockBorderWidget} should put a lock icon.
     */
    public final static String HAS_LOCK_ICON = "substance.internal.widget.hasLockIcon";

    /**
     * Client property name for specifying the preview painter for a component.
     */
    public final static String COMPONENT_PREVIEW_PAINTER = "substance.internal.widget.componentPreviewPainter";

    /**
     * Client property name for specifying password strength checker for a password field.
     */
    public final static String PASSWORD_STRENGTH_CHECKER = "substance.internal.widget.passwordStrengthChecker";

    /**
     * Client property name for specifying that the text component contents should be selected on
     * focus gain.
     */
    public final static String TEXT_SELECT_ON_FOCUS = "substance.internal.widget.textSelectAllOnFocus";

    /**
     * Client property name for specifying that the text component contents should flip selection on
     * ESCAPE key press.
     */
    public final static String TEXT_FLIP_SELECT_ON_ESCAPE = "substance.internal.widget.textFlipSelectOnEscape";

    /**
     * Client property name for specifying that the text component should have the edit context menu
     * (with Cut / Copy / Paste / ... menu items).
     */
    public final static String TEXT_EDIT_CONTEXT_MENU = "substance.internal.widget.textEditContextMenu";

    /**
     * Client property name for specifying that the tree component should have automatic drag and
     * drop support.
     */
    public final static String TREE_AUTO_DND_SUPPORT = "substance.widget.treeAutoDnDSupport";

    /**
     * Client property name for specifying that a scroll pane should have auto-scroll support
     * invoked on mouse button click that triggers popups.
     */
    public final static String AUTO_SCROLL = "substance.widget.scroll.auto";

}
