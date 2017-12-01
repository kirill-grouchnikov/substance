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
package org.pushingpixels.substance.api.icon;

import org.pushingpixels.substance.api.SubstanceCortex;
import org.pushingpixels.substance.api.colorscheme.SubstanceColorScheme;

/**
 * Icon pack interface for <b>Substance</b> look and feel. This class is part of officially
 * supported API.
 * 
 * <p>
 * Starting from version 8.0, applications using Substance can use a custom icon pack with
 * {@link SubstanceCortex.GlobalScope#setIconPack(SubstanceIconPack)} to match the overall
 * iconography language that maintains visual consistency across the entire app.
 * 
 * @author Kirill Grouchnikov
 * @since version 8.0
 * @see {@link SubstanceCortex.GlobalScope#setIconPack(SubstanceIconPack)}
 */
public interface SubstanceIconPack {
    public SubstanceIcon getOptionPaneInformationIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme);

    public SubstanceIcon getOptionPaneWarningIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme);

    public SubstanceIcon getOptionPaneErrorIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme);

    public SubstanceIcon getOptionPaneQuestionIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme);

    public SubstanceIcon getFileChooserNewFolderIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme);

    public SubstanceIcon getFileChooserUpFolderIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme);

    public SubstanceIcon getFileChooserHomeFolderIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme);

    public SubstanceIcon getFileChooserListViewIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme);

    public SubstanceIcon getFileChooserDetailsViewIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme);

    public SubstanceIcon getFileChooserViewMenuIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme);

    public SubstanceIcon getFileChooserComputerIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme);

    public SubstanceIcon getFileChooserDirectoryIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme);

    public SubstanceIcon getFileChooserFileIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme);

    public SubstanceIcon getFileChooserFloppyDriveIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme);

    public SubstanceIcon getFileChooserHardDriveIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme);

    public SubstanceIcon getLockIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme);

    public SubstanceIcon getInspectIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme);

    public SubstanceIcon getRefreshIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme);

    public SubstanceIcon getAllowedIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme);

    public SubstanceIcon getNotAllowedIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme);

    public SubstanceIcon getTextCopyActionIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme);

    public SubstanceIcon getTextCutActionIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme);

    public SubstanceIcon getTextPasteActionIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme);

    public SubstanceIcon getTextDeleteActionIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme);

    public SubstanceIcon getTextSelectAllActionIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme);

    public SubstanceIcon getColorChooserColorPalettesIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme);

    public SubstanceIcon getColorChooserColorSlidersIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme);

    public SubstanceIcon getColorChooserColorSwatchesIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme);

    public SubstanceIcon getColorChooserColorWheelIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme);

    public SubstanceIcon getColorChooserCrayonsIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme);

    public SubstanceIcon getColorChooserImagePalettesIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme);

    public SubstanceIcon getScrollVerticalIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme);

    public SubstanceIcon getScrollHorizontalIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme);

    public SubstanceIcon getScrollAllIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme);
}
