/*
 * @(#)MultiIcon.java  1.0.1  2006-02-14
 *
 * Copyright (c) 2005 Werner Randelshofer
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
 * An icon which paints one out of multiple icons depending on the state
 * of the component.
 * MultiIcon can lazily create the icons from a tiled image.
 *
 * @author  Werner Randelshofer
 * @version 1.0.1 2006-02-14 Created tileCount icons from image.
 * <br>1.0 October 17, 2005 Created.
 */
public abstract class MultiIcon implements Icon {
    /**
     * The icons from which we choose from.
     * This variable is null, if we are using a tiled image as our base.
     */
    protected Icon[] icons;
    
    /** Holds the icon pictures in a single image. This variable is used only
     *until we create the icons array. Then it is set to null.
     */
    private Image tiledImage;
    /**
     * The number of icons in the tiledImage.
     */
    private int tileCount;
    /**
     * Whether the tiledImage needs to be tiled horizontaly or vertically
     * to get the icons out of it.
     */
    private boolean isTiledHorizontaly;
    
    
    /**
     * Creates a new instance from an array of icons.
     * All icons must have the same dimensions.
     * If an icon is null, an icon is derived for the state from the
     * other icons.
     */
    public MultiIcon(Icon[] icons) {
        this.icons = icons;
        generateMissingIcons();
    }

    /**
     * Creates a new instance from an array of images.
     * All icons must have the same dimensions.
     * If an icon is null, an icon is derived for the state from the
     * other icons.
     */
    public MultiIcon(Image[] images) {
        this.icons = new Icon[images.length];
        for (int i=0, n = icons.length; i < n; i++) {
            if (images[i] != null) {
                icons[i] = new ImageIcon(images[i]);
            }
        }
        generateMissingIcons();
    }
    
    /**
     * Creates a new instance.
     * The icon representations are created lazily from the tiled image.
     */
    public MultiIcon(Image tiledImage, int tileCount, boolean isTiledHorizontaly) {
        this.tiledImage = tiledImage;
        this.tileCount = tileCount;
        this.isTiledHorizontaly = isTiledHorizontaly;
    }
    
    
    public int getIconHeight() {
        generateIconsFromTiledImage();
        return icons[0].getIconHeight();
    }
    
    public int getIconWidth() {
        generateIconsFromTiledImage();
        return icons[0].getIconWidth();
    }
    
    public void paintIcon(java.awt.Component c, java.awt.Graphics g, int x, int y) {
        generateIconsFromTiledImage();
        Icon icon = getIcon(c);
        if (icon != null) {
            icon.paintIcon(c, g, x, y);
        }
    }
    
    private void generateIconsFromTiledImage() {
        if (icons == null) {
            icons = new Icon[tileCount];
            Image[] images = Images.split(tiledImage, tileCount, isTiledHorizontaly);
            for (int i=0, n = Math.min(images.length, icons.length); i < n; i++) {
                if (images[i] != null) {
                    icons[i] = new ImageIcon(images[i]);
                }
            }
            generateMissingIcons();
            tiledImage = null;
        }
    }
    
    protected abstract Icon getIcon(Component c);
    protected abstract void generateMissingIcons();
}
