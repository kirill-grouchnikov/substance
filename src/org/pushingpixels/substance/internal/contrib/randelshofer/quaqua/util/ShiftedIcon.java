/*
 * @(#)ShiftedIcon.java  1.0  May 12, 2006
 *
 * Copyright (c) 2006 Werner Randelshofer
 * Staldenmattweg 2, CH-6405 Immensee, Switzerland
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Werner Randelshofer. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Werner Randelshofer.
 */

package org.pushingpixels.substance.internal.contrib.randelshofer.quaqua.util;

import java.awt.*;
import javax.swing.*;
/**
 * ShiftedIcon renders a target icon at a different location and can return
 * different width and height values than the target.
 *
 * @author Werner Randelshofer.
 * @version 1.0 May 12, 2006 Created.
 */
public class ShiftedIcon implements Icon {
    private Icon target;
    private Rectangle shift;
    
    /** Creates a new instance. */
    public ShiftedIcon(Icon target, Point shift) {
        this.target = target;
        this.shift = new Rectangle(
                shift.x, shift.y, 
                target.getIconWidth(), 
                target.getIconHeight()
                );
    }
    public ShiftedIcon(Icon target, Rectangle shiftAndSize) {
        this.target = target;
        this.shift = shiftAndSize;
    }

    public void paintIcon(Component c, Graphics g, int x, int y) {
        target.paintIcon(c, g, x + shift.x, y + shift.y);
    }

    public int getIconWidth() {
        return shift.width;
    }

    public int getIconHeight() {
        return shift.height;
    }
    
}
