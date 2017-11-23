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

import org.pushingpixels.substance.api.colorscheme.SubstanceColorScheme;
import org.pushingpixels.substance.api.hidpi.HiDpiAwareIcon;
import org.pushingpixels.substance.internal.svg.autoscroll_all;
import org.pushingpixels.substance.internal.svg.autoscroll_h;
import org.pushingpixels.substance.internal.svg.autoscroll_v;
import org.pushingpixels.substance.internal.svg.ic_add_circle_black_24px;
import org.pushingpixels.substance.internal.svg.ic_adjust_black_24px;
import org.pushingpixels.substance.internal.svg.ic_album_black_24px;
import org.pushingpixels.substance.internal.svg.ic_arrow_upward_black_24px;
import org.pushingpixels.substance.internal.svg.ic_brightness_high_black_24px;
import org.pushingpixels.substance.internal.svg.ic_computer_black_24px;
import org.pushingpixels.substance.internal.svg.ic_content_copy_black_24px;
import org.pushingpixels.substance.internal.svg.ic_content_cut_black_24px;
import org.pushingpixels.substance.internal.svg.ic_content_paste_black_24px;
import org.pushingpixels.substance.internal.svg.ic_create_new_folder_black_24px;
import org.pushingpixels.substance.internal.svg.ic_delete_black_24px;
import org.pushingpixels.substance.internal.svg.ic_error_black_24px;
import org.pushingpixels.substance.internal.svg.ic_folder_open_black_24px;
import org.pushingpixels.substance.internal.svg.ic_grid_on_black_24px;
import org.pushingpixels.substance.internal.svg.ic_help_black_24px;
import org.pushingpixels.substance.internal.svg.ic_home_black_24px;
import org.pushingpixels.substance.internal.svg.ic_info_black_24px;
import org.pushingpixels.substance.internal.svg.ic_insert_drive_file_black_24px;
import org.pushingpixels.substance.internal.svg.ic_lock_outline_black_24px;
import org.pushingpixels.substance.internal.svg.ic_menu_black_24px;
import org.pushingpixels.substance.internal.svg.ic_mode_edit_black_24px;
import org.pushingpixels.substance.internal.svg.ic_palette_black_24px;
import org.pushingpixels.substance.internal.svg.ic_refresh_black_24px;
import org.pushingpixels.substance.internal.svg.ic_remove_circle_black_24px;
import org.pushingpixels.substance.internal.svg.ic_save_black_24px;
import org.pushingpixels.substance.internal.svg.ic_select_all_black_24px;
import org.pushingpixels.substance.internal.svg.ic_storage_black_24px;
import org.pushingpixels.substance.internal.svg.ic_view_list_black_24px;
import org.pushingpixels.substance.internal.svg.ic_warning_black_24px;

/**
 * Default icon pack interface for <b>Substance</b> look and feel. This class is part of officially
 * supported API.<br>
 * 
 * @author Kirill Grouchnikov
 * @since version 8.0
 */
public class SubstanceDefaultIconPack implements SubstanceIconPack {
    private HiDpiAwareIcon getOptionPaneIcon(HiDpiAwareIcon base,
            SubstanceColorScheme colorScheme) {
        return base.colorize(colorScheme.getMidColor());
    }

    @Override
    public HiDpiAwareIcon getOptionPaneInformationIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme) {
        HiDpiAwareIcon base = ic_info_black_24px.of(preferredSize, preferredSize);
        return getOptionPaneIcon(base, preferredIconColorScheme);
    }

    @Override
    public HiDpiAwareIcon getOptionPaneWarningIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme) {
        HiDpiAwareIcon base = ic_warning_black_24px.of(preferredSize, preferredSize);
        return getOptionPaneIcon(base, preferredIconColorScheme);
    }

    @Override
    public HiDpiAwareIcon getOptionPaneErrorIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme) {
        HiDpiAwareIcon base = ic_error_black_24px.of(preferredSize, preferredSize);
        return getOptionPaneIcon(base, preferredIconColorScheme);
    }

    @Override
    public HiDpiAwareIcon getOptionPaneQuestionIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme) {
        HiDpiAwareIcon base = ic_help_black_24px.of(preferredSize, preferredSize);
        return getOptionPaneIcon(base, preferredIconColorScheme);
    }

    @Override
    public HiDpiAwareIcon getFileChooserNewFolderIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme) {
        HiDpiAwareIcon base = ic_create_new_folder_black_24px.of(preferredSize, preferredSize);
        return base.colorize(preferredIconColorScheme);
    }

    @Override
    public HiDpiAwareIcon getFileChooserUpFolderIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme) {
        HiDpiAwareIcon base = ic_arrow_upward_black_24px.of(preferredSize, preferredSize);
        return base.colorize(preferredIconColorScheme);
    }

    @Override
    public HiDpiAwareIcon getFileChooserHomeFolderIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme) {
        HiDpiAwareIcon base = ic_home_black_24px.of(preferredSize, preferredSize);
        return base.colorize(preferredIconColorScheme);
    }

    @Override
    public HiDpiAwareIcon getFileChooserListViewIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme) {
        HiDpiAwareIcon base = ic_view_list_black_24px.of(preferredSize, preferredSize);
        return base.colorize(preferredIconColorScheme);
    }

    @Override
    public HiDpiAwareIcon getFileChooserDetailsViewIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme) {
        HiDpiAwareIcon base = ic_insert_drive_file_black_24px.of(preferredSize, preferredSize);
        return base.colorize(preferredIconColorScheme);
    }

    @Override
    public HiDpiAwareIcon getFileChooserViewMenuIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme) {
        HiDpiAwareIcon base = ic_menu_black_24px.of(preferredSize, preferredSize);
        return base.colorize(preferredIconColorScheme);
    }

    @Override
    public HiDpiAwareIcon getFileChooserComputerIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme) {
        HiDpiAwareIcon base = ic_computer_black_24px.of(preferredSize, preferredSize);
        return base.colorize(preferredIconColorScheme);
    }

    @Override
    public HiDpiAwareIcon getFileChooserDirectoryIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme) {
        HiDpiAwareIcon base = ic_folder_open_black_24px.of(preferredSize, preferredSize);
        return base.colorize(preferredIconColorScheme);
    }

    @Override
    public HiDpiAwareIcon getFileChooserFileIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme) {
        HiDpiAwareIcon base = ic_insert_drive_file_black_24px.of(preferredSize, preferredSize);
        return base.colorize(preferredIconColorScheme);
    }

    @Override
    public HiDpiAwareIcon getFileChooserFloppyDriveIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme) {
        HiDpiAwareIcon base = ic_save_black_24px.of(preferredSize, preferredSize);
        return base.colorize(preferredIconColorScheme);
    }

    @Override
    public HiDpiAwareIcon getFileChooserHardDriveIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme) {
        HiDpiAwareIcon base = ic_storage_black_24px.of(preferredSize, preferredSize);
        return base.colorize(preferredIconColorScheme);
    }

    @Override
    public HiDpiAwareIcon getLockIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme) {
        HiDpiAwareIcon base = ic_lock_outline_black_24px.of(preferredSize, preferredSize);
        return base.colorize(preferredIconColorScheme,
                preferredIconColorScheme.isDark() ? 0.6f : -0.1f);
    }

    @Override
    public HiDpiAwareIcon getInspectIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme) {
        HiDpiAwareIcon base = ic_adjust_black_24px.of(preferredSize, preferredSize);
        return base.colorize(preferredIconColorScheme.getForegroundColor(), 0.625f);
    }

    @Override
    public HiDpiAwareIcon getRefreshIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme) {
        HiDpiAwareIcon base = ic_refresh_black_24px.of(preferredSize, preferredSize);
        return base.colorize(preferredIconColorScheme.getForegroundColor());
    }

    @Override
    public HiDpiAwareIcon getAllowedIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme) {
        HiDpiAwareIcon base = ic_add_circle_black_24px.of(preferredSize, preferredSize);
        return base.colorize(preferredIconColorScheme.getForegroundColor());
    }

    @Override
    public HiDpiAwareIcon getNotAllowedIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme) {
        HiDpiAwareIcon base = ic_remove_circle_black_24px.of(preferredSize, preferredSize);
        return base.colorize(preferredIconColorScheme.getDarkColor());
    }

    @Override
    public HiDpiAwareIcon getTextCopyActionIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme) {
        HiDpiAwareIcon base = ic_content_copy_black_24px.of(preferredSize, preferredSize);
        return base.colorize(preferredIconColorScheme.getForegroundColor());
    }

    @Override
    public HiDpiAwareIcon getTextCutActionIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme) {
        HiDpiAwareIcon base = ic_content_cut_black_24px.of(preferredSize, preferredSize);
        return base.colorize(preferredIconColorScheme.getForegroundColor());
    }

    @Override
    public HiDpiAwareIcon getTextPasteActionIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme) {
        HiDpiAwareIcon base = ic_content_paste_black_24px.of(preferredSize, preferredSize);
        return base.colorize(preferredIconColorScheme.getForegroundColor());
    }

    @Override
    public HiDpiAwareIcon getTextDeleteActionIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme) {
        HiDpiAwareIcon base = ic_delete_black_24px.of(preferredSize, preferredSize);
        return base.colorize(preferredIconColorScheme.getForegroundColor());
    }

    @Override
    public HiDpiAwareIcon getTextSelectAllActionIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme) {
        HiDpiAwareIcon base = ic_select_all_black_24px.of(preferredSize, preferredSize);
        return base.colorize(preferredIconColorScheme.getForegroundColor());
    }

    @Override
    public HiDpiAwareIcon getColorChooserColorPalettesIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme) {
        HiDpiAwareIcon base = ic_palette_black_24px.of(preferredSize, preferredSize);
        return base.colorize(preferredIconColorScheme.getForegroundColor());
    }

    @Override
    public HiDpiAwareIcon getColorChooserColorSlidersIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme) {
        HiDpiAwareIcon base = ic_menu_black_24px.of(preferredSize, preferredSize);
        return base.colorize(preferredIconColorScheme.getForegroundColor());
    }

    @Override
    public HiDpiAwareIcon getColorChooserColorSwatchesIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme) {
        HiDpiAwareIcon base = ic_grid_on_black_24px.of(preferredSize, preferredSize);
        return base.colorize(preferredIconColorScheme.getForegroundColor());
    }

    @Override
    public HiDpiAwareIcon getColorChooserColorWheelIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme) {
        HiDpiAwareIcon base = ic_album_black_24px.of(preferredSize, preferredSize);
        return base.colorize(preferredIconColorScheme.getForegroundColor());
    }

    @Override
    public HiDpiAwareIcon getColorChooserCrayonsIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme) {
        HiDpiAwareIcon base = ic_mode_edit_black_24px.of(preferredSize, preferredSize);
        return base.colorize(preferredIconColorScheme.getForegroundColor());
    }

    @Override
    public HiDpiAwareIcon getColorChooserImagePalettesIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme) {
        HiDpiAwareIcon base = ic_brightness_high_black_24px.of(preferredSize, preferredSize);
        return base.colorize(preferredIconColorScheme.getForegroundColor());
    }

    @Override
    public HiDpiAwareIcon getScrollVerticalIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme) {
        HiDpiAwareIcon base = autoscroll_v.of(preferredSize, preferredSize);
        return base.colorize(preferredIconColorScheme.getForegroundColor());
    }

    @Override
    public HiDpiAwareIcon getScrollHorizontalIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme) {
        HiDpiAwareIcon base = autoscroll_h.of(preferredSize, preferredSize);
        return base.colorize(preferredIconColorScheme.getForegroundColor());
    }

    @Override
    public HiDpiAwareIcon getScrollAllIcon(int preferredSize,
            SubstanceColorScheme preferredIconColorScheme) {
        HiDpiAwareIcon base = autoscroll_all.of(preferredSize, preferredSize);
        return base.colorize(preferredIconColorScheme.getForegroundColor());
    }
}
