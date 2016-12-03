/*
 * @(#)QuaquaUtilities.java  3.1  2006-09-04
 *
 * Copyright (c) 2003-2006 Werner Randelshofer
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

import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.FocusTraversalPolicy;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Insets;
import java.awt.MediaTracker;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.TexturePaint;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.Window;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicGraphicsUtils;
import javax.swing.text.JTextComponent;

/**
 * Utility class for the Quaqua LAF.
 *
 * @author Werner Randelshofer, Staldenmattweg 2, CH-6405 Immensee, Switzerland
 * @version 3.1 2006-09-04 Added method compositeRequestFocus.
 * <br>3.0.5 2006-08-20 Method endGraphics must not set
 * KEY_TEXT_ANTIALIASING to null.
 * <br>3.0.4 2006-02-19 Catch Throwable in method setWindowAlpha instead
 * of catching NoSuchMethodException.
 * <br>3.0.3 2006-01-08 Don't set Window alpha, when running on
 * Java 1.4.2_05 on Mac OS X 10.3.5. Because this only has the effect of turning
 * the background color of the Window to white.
 * <br>3.0.2 2005-12-10 Method isOnActiveWindow() did not reliably
 * return true.
 * <br>3.0.1 2005-11-12 Fixed NPE in method repaint border.
 * <br>3.0 2005-09-24 Removed all reflection helper methods. Moved Sheet
 * helper methods out into class Sheets.
 * <br>2.6 2005-09-17 Method isOnFocusedWindow returns true, if
 * the window returns false on "getFocusableWindowState".
 * <br>2.5 2005-03-13 Renamed method isFrameActive to isOnActiveFrame.
 * <br>2.4 2004-12-28 Method createBufferdImage added. Method
 * isOnActiveWindow() renamed to isFrameActive().
 * <br>2.3 2004-12-14 Method getUI added.
 * <br>2.2.1 2004-12-01 Methods setDragEnabled and getDragEnabled never
 * worked because the attempted to get method objects on the wrong class.
 * <br>2.2 2004-09-19 Refined algorithm of method isFrameActive.
 * <br>2.1 2004-07-04 Methods repaintBorder, beginFont, endFont and
 * isFocused added.
 * <br>2.0 2004-04-27 Renamed from QuaquaGraphicUtils to QuaquaUtilities.
 * Added method isFrameActive(Component).
 * <br>1.1.1 2003-10-08 Diagnostic output to System.out removed.
 * <br>1.1 2003-10-05 Methods getModifiersText and getModifiersUnicode
 * added.
 * <br>1.0 2003-07-19 Created.
 */
public class QuaquaUtilities extends BasicGraphicsUtils implements SwingConstants {
    /** Prevent instance creation. */
    private QuaquaUtilities() {
    }
    
    /*
     * Convenience function for determining ComponentOrientation.  Helps us
     * avoid having Munge directives throughout the code.
     */
    public static boolean isLeftToRight( Component c ) {
        return c.getComponentOrientation().isLeftToRight();
    }
    
    /**
     * Draw a string with the graphics <code>g</code> at location
     * (<code>x</code>, <code>y</code>)
     * just like <code>g.drawString</code> would.
     * The character at index <code>underlinedIndex</code>
     * in text will be underlined. If <code>index</code> is beyond the
     * bounds of <code>text</code> (including < 0), nothing will be
     * underlined.
     *
     * @param g Graphics to draw with
     * @param text String to draw
     * @param underlinedIndex Index of character in text to underline
     * @param x x coordinate to draw at
     * @param y y coordinate to draw at
     * @since 1.4
     */
    public static void drawStringUnderlineCharAt(Graphics g, String text,
            int underlinedIndex, int x,int y) {
        g.drawString(text,x,y);
        if (underlinedIndex >= 0 && underlinedIndex < text.length() ) {
            FontMetrics fm = g.getFontMetrics();
            int underlineRectX = x + fm.stringWidth(text.substring(0,underlinedIndex));
            int underlineRectY = y;
            int underlineRectWidth = fm.charWidth(text.charAt(underlinedIndex));
            int underlineRectHeight = 1;
            g.fillRect(underlineRectX, underlineRectY + fm.getDescent() - 1,
                    underlineRectWidth, underlineRectHeight);
        }
    }
    /**
     * Returns index of the first occurrence of <code>mnemonic</code>
     * within string <code>text</code>. Matching algorithm is not
     * case-sensitive.
     *
     * @param text The text to search through, may be null
     * @param mnemonic The mnemonic to find the character for.
     * @return index into the string if exists, otherwise -1
     */
    static int findDisplayedMnemonicIndex(String text, int mnemonic) {
        if (text == null || mnemonic == '\0') {
            return -1;
        }
        
        char uc = Character.toUpperCase((char)mnemonic);
        char lc = Character.toLowerCase((char)mnemonic);
        
        int uci = text.indexOf(uc);
        int lci = text.indexOf(lc);
        
        if (uci == -1) {
            return lci;
        } else if(lci == -1) {
            return uci;
        } else {
            return (lci < uci) ? lci : uci;
        }
    }
    
    /**
     * Returns true if the component is on a Dialog or a Frame, which is active,
     * or if it is on a Window, which is focused.
     * Always returns true, if the component has no parent window.
     */
    public static boolean isOnActiveWindow(Component c) {
        // In the RootPaneUI, we set a client property on the whole component
        // tree, if the ancestor Frame gets activated or deactivated.
        if (c instanceof JComponent) {
            Boolean value = (Boolean) ((JComponent) c).getClientProperty("Frame.active");
            // Unfortunately, the value is not always reliable.
            // Therefore we can only do a short circuit, if the value is true.
            if (value != null && value.booleanValue()) {
                return true;
                //return value.booleanValue();
            }
        }
        /*
        // This is how I would have implemented the code, if Quaqua would
        // not be required to work an a Java 1.3 VM.
        Window window = SwingUtilities.getWindowAncestor(c);
        if (window == null) {
            return true;
        } else if ((window instanceof Frame) || (window instanceof Dialog)) {
            return window.isActive();
        } else {
            if (window.getFocusableWindowState()) {
                return window.isFocused();
            } else {
                return true;
            }
        }
         */
        
        // If we missed the client property or if it was false, we have to
        // figure out the activation state on our own.
        // The following code works from Java 1.3 onwards.
        Window window = SwingUtilities.getWindowAncestor(c);
        boolean isOnActiveWindow;
        if (window == null) {
            isOnActiveWindow = true;
        } else if ((window instanceof Frame) || (window instanceof Dialog)) {
            isOnActiveWindow = window.isActive();
        } else {
            if (window.getFocusableWindowState()) {
                isOnActiveWindow = window.isFocused();
            } else {
                isOnActiveWindow = true;
            }
        }
        
        // In case the activation property is true, we fix the value of the
        // client property, so that we can do a short circuit next time.
        if (isOnActiveWindow && (c instanceof JComponent)) {
            ((JComponent) c).putClientProperty("Frame.active", new Boolean(isOnActiveWindow));
        }
        return isOnActiveWindow;
    }
    
    /**
     * Returns a Mac OS X specific String describing the modifier key(s),
     * such as "Shift", or "Ctrl+Shift".
     *
     * @return string a text description of the combination of modifier
     *                keys that were held down during the event
     */
    public static String getKeyModifiersText(int modifiers, boolean leftToRight) {
        return getKeyModifiersUnicode(modifiers, leftToRight);
    }
    static String getKeyModifiersUnicode(int modifiers, boolean leftToRight) {
        char[] cs = new char[4];
        int count = 0;
        if (leftToRight) {
            if ((modifiers & InputEvent.CTRL_MASK) != 0)
                cs[count++] = '\u2303'; // Unicode: UP ARROWHEAD
            if ((modifiers & (InputEvent.ALT_MASK | InputEvent.ALT_GRAPH_MASK)) != 0)
                cs[count++] = '\u2325'; // Unicode: OPTION KEY
            if ((modifiers & InputEvent.SHIFT_MASK) != 0)
                cs[count++] = '\u21e7'; // Unicode: UPWARDS WHITE ARROW
            if ((modifiers & InputEvent.META_MASK) != 0)
                cs[count++] = '\u2318'; // Unicode: PLACE OF INTEREST SIGN
        } else {
            if ((modifiers & InputEvent.META_MASK) != 0)
                cs[count++] = '\u2318'; // Unicode: PLACE OF INTEREST SIGN
            if ((modifiers & InputEvent.SHIFT_MASK) != 0)
                cs[count++] = '\u21e7'; // Unicode: UPWARDS WHITE ARROW
            if ((modifiers & (InputEvent.ALT_MASK | InputEvent.ALT_GRAPH_MASK)) != 0)
                cs[count++] = '\u2325'; // Unicode: OPTION KEY
            if ((modifiers & InputEvent.CTRL_MASK) != 0)
                cs[count++] = '\u2303'; // Unicode: UP ARROWHEAD
        }
        return new String(cs, 0, count);
    }
    
    public static void repaintBorder(JComponent component) {
        JComponent c = component;
        Border border = null;
        Container container = component.getParent();
        if (container instanceof JViewport) {
            c = (JComponent) container.getParent();
            if (c != null) {
                border = c.getBorder();
            }
        }
        if (border == null) {
            border = component.getBorder();
            c = component;
        }
        if (border != null && c != null) {
            int w = c.getWidth();
            int h = c.getHeight();
            Insets insets = c.getInsets();
            c.repaint(0, 0, w, insets.top);
            c.repaint(0, 0, insets.left, h);
            c.repaint(0, h - insets.bottom, w, insets.bottom);
            c.repaint(w - insets.right, 0, insets.right, h);
        }
    }
    public static final Object beginGraphics(Graphics2D graphics2d) {
        Object object = graphics2d.getRenderingHint(RenderingHints
                .KEY_TEXT_ANTIALIASING);
        graphics2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        return object;
    }
    
    public static final void endGraphics(Graphics2D graphics2d, Object oldHints) {
        if (oldHints != null) {
            graphics2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                    oldHints);
        }
    }
    public static final boolean isFocused(Component component) {
        if (QuaquaUtilities.isOnActiveWindow(component)) {
            Component c = component;
            if (c instanceof JComponent) {
                if (c instanceof JScrollPane) {
                    JViewport viewport = ((JScrollPane) component).getViewport();
                    if (viewport != null) {
                        c = viewport.getView();
                    }
                }
                if (c instanceof JTextComponent
                        && !((JTextComponent) c).isEditable()) {
                    return false;
                }
                return c != null
                        && (((JComponent) c).hasFocus() || ((JComponent) c).getClientProperty("Quaqua.drawFocusBorder") == Boolean.TRUE);
            }
        }
        return false;
    }
    
    static boolean isHeadless() {
    	return GraphicsEnvironment.isHeadless();
    }
    
    /**
     * Invoked when the user attempts an invalid operation,
     * such as pasting into an uneditable <code>JTextField</code>
     * that has focus. The default implementation beeps. Subclasses
     * that wish different behavior should override this and provide
     * the additional feedback.
     *
     * @param component Component the error occured in, may be null
     *			indicating the error condition is not directly
     *			associated with a <code>Component</code>.
     */
    static void provideErrorFeedback(Component component) {
        Toolkit toolkit = null;
        if (component != null) {
            toolkit = component.getToolkit();
        } else {
            toolkit = Toolkit.getDefaultToolkit();
        }
        toolkit.beep();
    } // provideErrorFeedback()
    
    public static BufferedImage createBufferedImage(URL location) {
        Image image = Toolkit.getDefaultToolkit().createImage(location);
        BufferedImage buf;
        if (image instanceof BufferedImage) {
            buf = (BufferedImage) image;
        } else {
            loadImage(image);
            //buf = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
            buf = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration().createCompatibleImage(image.getWidth(null), image.getHeight(null), Transparency.OPAQUE);
            
            Graphics g = buf.getGraphics();
            g.drawImage(image, 0, 0, null);
            g.dispose();
            image.flush();
        }
        return buf;
    }
    public static TexturePaint createTexturePaint(URL location) {
        BufferedImage texture = createBufferedImage(location);
        TexturePaint paint = new TexturePaint(texture, new Rectangle(0, 0, texture.getWidth(), texture.getHeight()));
        return paint;
    }
    
    
    /**
     * Loads the image, returning only when the image is loaded.
     * @param image the image
     */
    private static void loadImage(Image image) {
        Component component = new Component() {};
        MediaTracker tracker = new MediaTracker(component);
        synchronized (tracker) {
            int id = 0;
            
            tracker.addImage(image, id);
            try {
                tracker.waitForID(id, 0);
            } catch (InterruptedException e) {
                System.out.println("INTERRUPTED while loading Image");
            }
            int loadStatus = tracker.statusID(id, false);
            tracker.removeImage(image, id);
        }
    }
    
 
    public static void configureGraphics(Graphics gr) {
        Graphics2D g = (Graphics2D) gr;
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
    }
    
    /** Copied from BasicLookAndFeel.
     */
    public static Component compositeRequestFocus(Component component) {
		if (component instanceof Container) {
			Container container = (Container) component;
			if (container.isFocusCycleRoot()) {
				FocusTraversalPolicy policy = container.getFocusTraversalPolicy();
				Component comp = policy.getDefaultComponent(container);
				if (comp != null) {
					comp.requestFocus();
					return comp;
				}
			}
			Container rootAncestor = container.getFocusCycleRootAncestor();
			if (rootAncestor != null) {
				FocusTraversalPolicy policy = rootAncestor.getFocusTraversalPolicy();
				Component comp = policy.getComponentAfter(rootAncestor, container);

				if (comp != null && SwingUtilities.isDescendingFrom(comp, container)) {
					comp.requestFocus();
					return comp;
				}
			}
		}
        if (component.isFocusable()) {
            component.requestFocus();
            return component;
        }
        return null;
    }
}