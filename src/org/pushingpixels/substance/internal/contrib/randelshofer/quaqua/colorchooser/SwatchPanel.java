/*
 * @(#)SwatchPanel.java  1.0  30 March 2005
 *
 * Copyright (c) 2004 Werner Randelshofer
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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import javax.swing.UIManager;

/**
 * SwatchPanel.
 *
 * Code derived from javax.swing.colorchooser.DefaultSwatchChooserPanel.
 *
 * @author  Werner Randelshofer
 * @version 1.0  30 March 2005  Created.
 */
public class SwatchPanel extends javax.swing.JPanel {
    protected Color[] colors;
    protected Dimension swatchSize = new Dimension();
    protected Dimension defaultSwatchSize;
    protected Dimension numSwatches;
    protected Dimension gap;
    private final static Color gridColor = new Color(0xaaaaaa);
    
    /** Creates new form. */
    public SwatchPanel() {
        initComponents();
        
        initValues();
        initColors();
        setToolTipText(""); // register for events
        setOpaque(false);
        //setBackground(Color.white);
        setRequestFocusEnabled(false);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        
        setLayout(new java.awt.BorderLayout());
        
    }//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    
    
    
    public boolean isFocusTraversable() {
        return false;
    }
    
    protected void initValues() {
        defaultSwatchSize = UIManager.getDimension("ColorChooser.swatchesSwatchSize");
        swatchSize.width = defaultSwatchSize.width;
        swatchSize.height = defaultSwatchSize.height;
        gap = new Dimension(1, 1);
    }
    
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);
        if (width > getPreferredSize().width) {
            swatchSize.width = (width - numSwatches.width * gap.width) / numSwatches.width;
        } else {
            swatchSize.width = defaultSwatchSize.width;
        }
        if (height > getPreferredSize().height) {
            swatchSize.height = (height - numSwatches.height * gap.height) / numSwatches.height;
        } else {
            swatchSize.height = defaultSwatchSize.height;
        }
    }
    
    public void setColors(Color[] colors) {
        this.colors = colors;
    }
    public void setNumSwatches(int rows, int columns) {
        numSwatches = new Dimension(rows, columns);
    }
    
    public void paintComponent(Graphics g) {
        Dimension preferredSize = getSwatchesSize();
        int xoffset = (getWidth() - preferredSize.width) / 2;
        int yoffset = 0;// (getHeight() - preferredSize.height) / 2;
        
        for (int row = 0; row < numSwatches.height; row++) {
            for (int column = 0; column < numSwatches.width; column++) {
                Color cellColor = getColorForCell(column, row);
                g.setColor(cellColor);
                //int x = (numSwatches.width - column - 1) * (swatchSize.width + gap.width);
                int x = xoffset + column * (swatchSize.width + gap.width) + 1;
                int y = yoffset + row * (swatchSize.height + gap.height) + 1;
                g.fillRect( x, y, swatchSize.width, swatchSize.height);
                
                g.setColor(cellColor.darker());
                g.fillRect(x - 1, y - 1, swatchSize.width+1, 1);
                g.fillRect(x - 1, y, 1, swatchSize.height);
            }
        }
    }
    
    public Dimension getSwatchesSize() {
        int x = numSwatches.width * (swatchSize.width + gap.width);
        int y = numSwatches.height * (swatchSize.height + gap.height);
        return new Dimension( x, y );
    }
    
    public Dimension getPreferredSize() {
        int x = numSwatches.width * (defaultSwatchSize.width + gap.width);
        int y = numSwatches.height * (defaultSwatchSize.height + gap.height);
        return new Dimension( x, y );
    }

    protected void initColors() {
        
        
    }
    
    public String getToolTipText(MouseEvent e) {
        Color color = getColorForLocation(e.getX(), e.getY());
        return (color == null) ? null : color.getRed()+", "+ color.getGreen() + ", " + color.getBlue();
    }
    
    public Color getColorForLocation( int x, int y ) {
        Dimension preferredSize = getSwatchesSize();
        x -= (getWidth() - preferredSize.width) / 2;
        //y -= (getHeight() - preferredSize.height) / 2;
        int column;
        if ((!this.getComponentOrientation().isLeftToRight())) {
            column = numSwatches.width - x / (swatchSize.width + gap.width) - 1;
        } else {
            column = x / (swatchSize.width + gap.width);
        }
        int row = y / (swatchSize.height + gap.height);
        return getColorForCell(column, row);
    }
    
    private Color getColorForCell( int column, int row) {
        int index = (row * numSwatches.width) + column;
        return (index < colors.length) ? colors[index] : null;
    }
    
    
    
    
    
}
