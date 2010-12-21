/*
 * @(#)ButtonStateIcon.java  3.1  2005-12-08
 *
 * Copyright (c) 2003-2005 Werner Randelshofer
 * Staldenmattweg 2, Immensee, CH-6405, Switzerland.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Werner Randelshofer. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Werner Randelshofer.
 */

package org.pushingpixels.substance.internal.contrib.randelshofer.quaqua;

import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.*;

import org.pushingpixels.substance.internal.contrib.randelshofer.quaqua.util.*;

/**
 * An Icon with different visuals reflecting the state of the AbstractButton
 * on which it draws on.
 *
 * @author  Werner Randelshofer
 * @version 3.1 2005-12-08 Draw pressed state if model is armed, instead of
 * drawing pressed state if model is armed and pressed.
 * <br>3.0 2005-10-17 Changed superclass to MultiIcon.
 * <br>2.0.1 2005-10-02 Used Enabled image for Pressed-Unarmed state.
 * <br>2.0.1 2005-09-11 generateMissing icons can now deal with only one
 * provided icon image.
 * <br>2.0 2005-03-19 Reworked.
 * <br>1.0 October 5, 2003 Create..
 */
public class ButtonStateIcon extends MultiIcon {
    private final static int E = 0;
    private final static int EP = 1;
    private final static int ES = 2;
    private final static int EPS = 3;
    private final static int D = 4;
    private final static int DS = 5;
    private final static int I = 6;
    private final static int IS = 7;
    private final static int DI = 8;
    private final static int DIS = 9;
    
    /**
     * Creates a new instance.
     * All icons must have the same dimensions.
     * If an icon is null, an icon is derived for the state from the
     * other icons.
     */
    public ButtonStateIcon(Icon e, Icon ep, Icon es, Icon eps, Icon d, Icon ds) {
        super(new Icon[] {e, ep, es, eps, d, ds});
    }
    /**
     * Creates a new instance.
     * All icons must have the same dimensions.
     *
     * The array indices are used to represente the following states:
     * [0] Enabled
     * [1] Enabled Pressed
     * [2] Enabled Selected
     * [3] Enabled Pressed Selected
     * [4] Disabled
     * [5] Disabled Selected
     * [6] Enabled Inactive
     * [7] Enabled Inactive Selected
     * [8] Disabled Inactive
     * [9] Disabled Inactive Selected
     *
     * If an array element is null, an icon is derived for the state from the
     * other icons.
     */
    public ButtonStateIcon(Image[] images) {
        super(images);
    }
    /**
     * Creates a new instance.
     * All icons must have the same dimensions.
     * If an icon is null, nothing is drawn for this state.
     */
    public ButtonStateIcon(Icon[] icons) {
        super(icons);
    }
    
    /**
     * Creates a new instance.
     * The icon representations are created lazily from the image.
     */
    public ButtonStateIcon(Image tiledImage, int tileCount, boolean isTiledHorizontally) {
        super(tiledImage, tileCount, isTiledHorizontally);
    }
    
    
    protected Icon getIcon(Component c) {
        Icon icon;
        boolean isActive = QuaquaUtilities.isOnActiveWindow(c);
        
        if (c instanceof AbstractButton) {
            ButtonModel model = ((AbstractButton) c).getModel();
            if (isActive) {
                if (model.isEnabled()) {
                    if (/*model.isPressed() && */model.isArmed()) {
                        if (model.isSelected()) {
                            icon = icons[EPS];
                        } else {
                            icon = icons[EP];
                        }
                    } else if (model.isSelected()) {
                        icon = icons[ES];
                    } else {
                        icon = icons[E];
                    }
                } else {
                    if (model.isSelected()) {
                        icon = icons[DS];
                    } else {
                        icon = icons[D];
                    }
                }
            } else {
                if (model.isEnabled()) {
                    if (model.isSelected()) {
                        icon = icons[IS];
                    } else {
                        icon = icons[I];
                    }
                } else {
                    if (model.isSelected()) {
                        icon = icons[DIS];
                    } else {
                        icon = icons[DI];
                    }
                }
            }
        } else {
            if (isActive) {
                if (c.isEnabled()) {
                    icon = icons[E];
                } else {
                    icon = icons[D];
                }
            } else {
                if (c.isEnabled()) {
                    icon = icons[I];
                } else {
                    icon = icons[DI];
                }
            }
        }
        return icon;
    }
    
    protected void generateMissingIcons() {
        if (icons.length != 10) {
            Icon[] helper = icons;
            icons = new Icon[10];
            System.arraycopy(helper, 0, icons, 0, Math.min(helper.length, icons.length));
        }
        
        if (icons[EP] == null) {
            icons[EP] = icons[E];
        }
        if (icons[ES] == null) {
            icons[ES] = icons[EP];
        }
        if (icons[EPS] == null) {
            icons[EPS] = icons[EP];
        }
        if (icons[D] == null) {
            icons[D] = icons[E];
        }
        if (icons[DS] == null) {
            icons[DS] = icons[ES];
        }
        if (icons[I] == null) {
            icons[I] = icons[E];
        }
        if (icons[IS] == null) {
            icons[IS] = icons[ES];
        }
        if (icons[DI] == null) {
            icons[DI] = icons[D];
        }
        if (icons[DIS] == null) {
            icons[DIS] = icons[DS];
        }
    }
}
