/*
 * @(#)QuaquaIconFactory.java  3.2 2007-01-05
 *
 * Copyright (c) 2005-2006 Werner Randelshofer
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

import java.net.*;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import javax.swing.plaf.*;

import org.pushingpixels.substance.internal.contrib.randelshofer.quaqua.util.*;

//import javax.imageio.*;
//import javax.imageio.stream.*;
import java.io.*;
import java.util.*;

/**
 * QuaquaIconFactory.
 *
 * @author  Werner Randelshofer, Christopher Atlan
 * @version 3.2 2007-01-05 Issue #1: Changed LazyOptionPaneIcon to load image
 * asynchronously before paintIcon is invoked.
 * <br>3.1 2006-12-24 by Karl von Randow: Use Images class to create artwork.
 * <br>3.0.2 2006-11-01 Use Graphics2D.drawImage() to scale application
 * image icon instead of using Image.getScaledInstance(). 
 * <br>3.0.1 2006-05-14 Application icon was unnecessarily created multiple
 * times. 
 * <br>3.0 2006-05-12 Added support for file icon images. Renamed some
 * methods.
 * <br>2.1 2006-02-14 Added method createFrameButtonStateIcon.
 * <br>2.0 2006-02-12 Added methods createApplicationIcon, compose,
 * createOptionPaneIcon. These methods were contributed by Christopher Atlan.
 * <br>1.0 December 4, 2005 Created.
 */
public class QuaquaIconFactory {
    private static BufferedImage applicationImage;
    
    
    /**
     * Prevent instance creation.
     */
    private QuaquaIconFactory() {
    }
    
    public static URL getResource(String location) {
        URL url = QuaquaIconFactory.class.getResource(location);
        if (url == null) {
            throw new InternalError("image resource missing: "+location);
        }
        return url;
    }
    
    public static Image createImage(String location) {
        return createImage(QuaquaIconFactory.class, location);
    }
    public static Image createImage(Class baseClass, String location) {
        return Images.createImage(baseClass.getResource(location));
    }
    public static Image createBufferedImage(String location) {
        return Images.toBufferedImage(createImage(location));
    }
    
    public static Icon[] createIcons(String location, int count, boolean horizontal) {
        Icon[] icons = new Icon[count];
        
        BufferedImage[] images = Images.split(
                (Image) createImage(location),
                count, horizontal
                );
        
        for (int i=0; i < count; i++) {
            icons[i] = new IconUIResource(new ImageIcon(images[i]));
        }
        return icons;
    }
    
    public static Icon createIcon(String location, int count, boolean horizontal, int index) {
        return createIcons(location, count, horizontal)[index];
    }
    
    
    public static Icon createButtonStateIcon(String location, int states) {
        return new ButtonStateIcon(
                (Image) createImage(location),
                states, true
                );
    }
    public static Icon createButtonStateIcon(String location, int states, Point shift) {
        return new ShiftedIcon(
                new ButtonStateIcon(
                (Image) createImage(location),
                states, true
                ),
                shift
                );
    }
    public static Icon createButtonStateIcon(String location, int states, Rectangle shift) {
        return new ShiftedIcon(
                new ButtonStateIcon(
                (Image) createImage(location),
                states, true
                ),
                shift
                );
    }
    
    public static Icon createIcon(Class baseClass, String location) {
        return new ImageIcon(createImage(baseClass, location));
    }
    public static Icon createIcon(Class baseClass, String location, Point shift) {
        return new ShiftedIcon(
                new ImageIcon(createImage(baseClass, location)),
                shift
                );
    }
    public static Icon createIcon(Class baseClass, String location, Rectangle shiftAndSize) {
        return new ShiftedIcon(
                new ImageIcon(createImage(baseClass, location)),
                shiftAndSize
                );
    }
    
}
