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
package org.pushingpixels.substance.api.iconpack;

import org.pushingpixels.substance.api.SubstanceColorScheme;
import org.pushingpixels.substance.api.hidpi.HiDpiAwareIcon;

/**
 * Icon pack interface for <b>Substance</b> look and feel. This class is part of officially
 * supported API.<br>
 * <br>
 * 
 * Starting from version 8.0, applications using Substance can provide icon packs that match the
 * overall iconography language that is consistent across the entire app.
 * 
 * @author Kirill Grouchnikov
 * @since version 8.0
 */
public interface SubstanceIconPack {
    public HiDpiAwareIcon getOptionPaneInformationIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme);

    public HiDpiAwareIcon getOptionPaneWarningIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme);

    public HiDpiAwareIcon getOptionPaneErrorIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme);

    public HiDpiAwareIcon getOptionPaneQuestionIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme);
    
    public HiDpiAwareIcon getFileChooserNewFolderIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme);
    
    public HiDpiAwareIcon getFileChooserUpFolderIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme);
    
    public HiDpiAwareIcon getFileChooserHomeFolderIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme);
    
    public HiDpiAwareIcon getFileChooserListViewIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme);
    
    public HiDpiAwareIcon getFileChooserDetailsViewIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme);
    
    public HiDpiAwareIcon getFileChooserViewMenuIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme);
    
    public HiDpiAwareIcon getFileChooserComputerIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme);
    
    public HiDpiAwareIcon getFileChooserDirectoryIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme);
    
    public HiDpiAwareIcon getFileChooserFileIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme);
    
    public HiDpiAwareIcon getFileChooserFloppyDriveIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme);
    
    public HiDpiAwareIcon getFileChooserHardDriveIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme);
    
    public HiDpiAwareIcon getLockIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme);
    
    public HiDpiAwareIcon getInspectIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme);
    
    public HiDpiAwareIcon getRefreshIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme);
    
    public HiDpiAwareIcon getAllowedIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme);
    
    public HiDpiAwareIcon getNotAllowedIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme);
    
    public HiDpiAwareIcon getTextCopyActionIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme);
    
    public HiDpiAwareIcon getTextCutActionIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme);
    
    public HiDpiAwareIcon getTextPasteActionIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme);
    
    public HiDpiAwareIcon getTextDeleteActionIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme);
    
    public HiDpiAwareIcon getTextSelectAllActionIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme);
    
    public HiDpiAwareIcon getColorChooserColorPalettesIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme);
    
    public HiDpiAwareIcon getColorChooserColorSlidersIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme);
    
    public HiDpiAwareIcon getColorChooserColorSwatchesIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme);
    
    public HiDpiAwareIcon getColorChooserColorWheelIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme);
    
    public HiDpiAwareIcon getColorChooserCrayonsIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme);
    
    public HiDpiAwareIcon getColorChooserImagePalettesIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme);
    
    public HiDpiAwareIcon getScrollVerticalIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme);
    
    public HiDpiAwareIcon getScrollHorizontalIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme);
    
    public HiDpiAwareIcon getScrollAllIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme);
}
