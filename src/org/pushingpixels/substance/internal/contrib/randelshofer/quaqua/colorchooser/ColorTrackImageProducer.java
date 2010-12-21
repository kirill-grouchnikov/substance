/*
 * @(#)ColorTrackImageProducer.java  1.1  2005-08-28
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

package org.pushingpixels.substance.internal.contrib.randelshofer.quaqua.colorchooser;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
/**
 * ColorTrackImageProducer creates the image for the track of a 
 * color slider.
 *
 * @see ColorSliderUI
 *
 * @author  Werner Randelshofer
 * @version 1.1 2005-08-28 Support for vertical color track implemented. 
 * Passing now a null pixel array to super constructor to avoid unnecessary 
 * memory allocation. Method markAsDirty() added.
 * <br>1.0.1 2005-04-18 Fixed a minor shift in the color range.
 * <br>1.0  29 March 2005  Created.
 */
public class ColorTrackImageProducer extends MemoryImageSource {
    private int[] pixels;
    private int w, h;
    private float[] baseComponents;
    private int component;
    private int trackBuffer;
    private ColorSliderModel colorizer = new RGBColorSliderModel();
    private boolean isDirty = true;
    private int componentIndex = 0;
    private boolean isHorizontal;
    
    /** Creates a new instance. */
    public ColorTrackImageProducer(int w, int h, int trackBuffer, boolean isHorizontal) {
        super(w, h, null, 0, w);
        pixels = new int[w*h];
        this.w = w;
        this.h = h;
        // trackBuffer must be even
        this.trackBuffer = (trackBuffer % 2 == 1) ? trackBuffer - 1 : trackBuffer;
        this.componentIndex = componentIndex;
        this.isHorizontal = isHorizontal;
        newPixels(pixels, ColorModel.getRGBdefault(), 0, w);
        setAnimated(true);
    }
    
    public int getWidth() {
        return w;
    }
    public int getHeight() {
        return h;
    }
    
    public void markAsDirty() {
        isDirty = true;
    }
    
    public boolean needsGeneration() {
        return isDirty;
    }
    
    public void regenerateColorTrack() {
        if (isDirty) {
            generateColorTrack();
        }
    }
    
    public void generateColorTrack() {
        if (isHorizontal) {
            generateHorizontalColorTrack();
        } else {
            generateVerticalColorTrack();
        }
        newPixels();
        isDirty = false;
    }
    
    private void generateHorizontalColorTrack() {
        int offset = trackBuffer / 2;
        for (int x = 0, n = w - trackBuffer - 1; x <= n; x++) {
            pixels[x + offset] = colorizer.getInterpolatedRGB(componentIndex, x / (float) n);
        }
        for (int x=0; x < offset; x++) {
            pixels[x] = pixels[offset];
            pixels[w - x - 1] = pixels[w - offset - 1];
        }
        for (int y=w, n = w*h; y < n; y+=w) {
            System.arraycopy(pixels, 0, pixels, y, w);
        }
    }
    private void generateVerticalColorTrack() {
        int offset = trackBuffer / 2;
        for (int y = 0, n = h - trackBuffer - 1; y <= n; y++) {
            pixels[(y + offset) * w] = colorizer.getInterpolatedRGB(componentIndex, 1 - y / (float) n);
        }
        for (int y=0; y < offset; y++) {
            pixels[y * w] = pixels[offset * w];
            pixels[(h - y - 1) * w] = pixels[(h - offset - 1) * w];
        }
        for (int x=1; x < w; x++) {
            for (int y=0, n = w*h; y < n; y+=w) {
                pixels[x + y] = pixels[x - 1 + y];
            }
        }
    }
    
    public void setBaseComponents(BoundedRangeModel[] components) {
        isDirty = true;
        //isDirty = isDirty || colorizer.needsRegeneration(this.baseRGB, baseRGB);
        //this.baseRGB = baseRGB;
        for (int i=0; i < components.length; i++) {
            baseComponents[i] = components[i].getValue() / (float) components[i].getMaximum();
        }
    }
    
    public void setColorSliderModel(ColorSliderModel colorizer) {
        this.colorizer = colorizer;
        isDirty = true;
    }
    public void setColorComponentIndex(int index) {
        this.componentIndex = index;
        isDirty = true;
    }
    public void componentChanged(int index) {
        isDirty |= this.componentIndex != index;
    }
}
