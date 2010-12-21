/*
 * @(#)PaletteEntryCellRenderer.java  1.0  19 septembre 2005
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
import javax.swing.*;
import javax.swing.border.*;
/**
 * PaletteEntryCellRenderer.
 *
 * @author  Werner Randelshofer
 * @version 1.0 19 septembre 2005 Created.
 */
public class PaletteEntryCellRenderer extends DefaultListCellRenderer {
    /** The following colors are used to draw the marker that marks the
     * closest color in the palette.
     * The "closest color" is used, when the palette does not contain an
     * exact match to the currently selected color in the color chooser.
     * The marker is used to help the user finding the closest color in the
     * palette.
     */
    private Color closestMarker1 = new Color(0xe6e6e6);
    private Color closestMarker2 = new Color(0xededed);
    private Color closestMarker3 = new Color(0xf0f0f0);
    
    
    static class ColorIcon implements Icon {
        private Color color = Color.black;
        
        public void setColor(Color c) {
            this.color = c;
        }
        public Color getColor() {
            return color;
        }
        
        public int getIconHeight() {
            return 15;
        }
        
        public int getIconWidth() {
            return 25;
        }
        
        public void paintIcon(Component c, Graphics g, int x, int y) {
            g.setColor(getColor());
            g.fillRect(x + 1, y + 1, getIconWidth() - 2, getIconHeight() - 2);
            g.setColor(getColor().darker());
            g.drawRect(x, y, getIconWidth() - 1, getIconHeight() - 1);
        }
    }
    
    
    private ColorIcon icon;
    private boolean isClosestColor;
    
    /**
     * Creates a new instance.
     */
    public PaletteEntryCellRenderer() {
        icon = new ColorIcon();
        setIcon(icon);
        setOpaque(false);
    }
    
    public Component getListCellRendererComponent(
    JList list,
    Object value,
    int index,
    boolean isSelected,
    boolean cellHasFocus) {
        
        setComponentOrientation(list.getComponentOrientation());
        if (isSelected) {
            setBackground(UIManager.getColor("ColorChooser.listSelectionBackground"));
            setForeground(UIManager.getColor("ColorChooser.listSelectionForeground"));
            isClosestColor = false;
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
            PaletteListModel model = (PaletteListModel) list.getModel();
            isClosestColor = model.getClosestIndex() == index;
        }
        
        setEnabled(list.isEnabled());
        setFont(list.getFont());
        //setBorder((cellHasFocus) ? UIManager.getBorder("List.focusCellHighlightBorder") : noFocusBorder);
        
        PaletteEntry entry = (PaletteEntry) value;
        icon.setColor(entry.getColor());
        setText(entry.getName());
        
        return this;
    }
    
    public void paintComponent(Graphics g) {
        int width = getWidth();
        int height = getHeight();
        g.setColor(getBackground());
        g.fillRect(0,0,width,height);
        if (isClosestColor) {
            g.setColor(closestMarker1);
            g.fillRect(0,0,width,2);
            g.fillRect(0,height - 2,width,2);
            g.setColor(closestMarker2);
            g.fillRect(0,2,width,1);
            g.fillRect(0,height - 3,width,1);
            g.setColor(closestMarker3);
            g.fillRect(0,3,width,1);
            g.fillRect(0,height - 4,width,1);
        }
        super.paintComponent(g);
    }
}
