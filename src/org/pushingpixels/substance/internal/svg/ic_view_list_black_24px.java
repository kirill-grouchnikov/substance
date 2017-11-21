package org.pushingpixels.substance.internal.svg;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;

import org.pushingpixels.substance.api.hidpi.HiDpiAwareIcon;
import org.pushingpixels.substance.api.iconpack.SubstanceIcon;

/**
 * This class has been automatically generated using <a
 * href="https://github.com/kirill-grouchnikov/flamingo">Flamingo SVG transcoder</a>.
 */
public class ic_view_list_black_24px implements SubstanceIcon {
	/**
	 * Paints the transcoded SVG image on the specified graphics context. You
	 * can install a custom transformation on the graphics context to scale the
	 * image.
	 * 
	 * @param g
	 *            Graphics context.
	 */
    @SuppressWarnings("unused")
	public static void paint(Graphics2D g) {
        Shape shape = null;
        Paint paint = null;
        Stroke stroke = null;
         
        float origAlpha = 1.0f;
        Composite origComposite = ((Graphics2D)g).getComposite();
        if (origComposite instanceof AlphaComposite) {
            AlphaComposite origAlphaComposite = 
                (AlphaComposite)origComposite;
            if (origAlphaComposite.getRule() == AlphaComposite.SRC_OVER) {
                origAlpha = origAlphaComposite.getAlpha();
            }
        }
        
	    AffineTransform defaultTransform_ = g.getTransform();
// 
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, -0.0f, -0.0f));
// _0
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0
paint = new Color(0, 0, 0, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(4.0, 14.0);
((GeneralPath)shape).lineTo(8.0, 14.0);
((GeneralPath)shape).lineTo(8.0, 10.0);
((GeneralPath)shape).lineTo(4.0, 10.0);
((GeneralPath)shape).lineTo(4.0, 14.0);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(4.0, 19.0);
((GeneralPath)shape).lineTo(8.0, 19.0);
((GeneralPath)shape).lineTo(8.0, 15.0);
((GeneralPath)shape).lineTo(4.0, 15.0);
((GeneralPath)shape).lineTo(4.0, 19.0);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(4.0, 9.0);
((GeneralPath)shape).lineTo(8.0, 9.0);
((GeneralPath)shape).lineTo(8.0, 5.0);
((GeneralPath)shape).lineTo(4.0, 5.0);
((GeneralPath)shape).lineTo(4.0, 9.0);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(9.0, 14.0);
((GeneralPath)shape).lineTo(21.0, 14.0);
((GeneralPath)shape).lineTo(21.0, 10.0);
((GeneralPath)shape).lineTo(9.0, 10.0);
((GeneralPath)shape).lineTo(9.0, 14.0);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(9.0, 19.0);
((GeneralPath)shape).lineTo(21.0, 19.0);
((GeneralPath)shape).lineTo(21.0, 15.0);
((GeneralPath)shape).lineTo(9.0, 15.0);
((GeneralPath)shape).lineTo(9.0, 19.0);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(9.0, 5.0);
((GeneralPath)shape).lineTo(9.0, 9.0);
((GeneralPath)shape).lineTo(21.0, 9.0);
((GeneralPath)shape).lineTo(21.0, 5.0);
((GeneralPath)shape).lineTo(9.0, 5.0);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_1 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_1
g.setTransform(defaultTransform__0_1);
g.setTransform(defaultTransform__0);
g.setTransform(defaultTransform_);

	}

    /**
     * Returns the X of the bounding box of the original SVG image.
     * 
     * @return The X of the bounding box of the original SVG image.
     */
    public static double getOrigX() {
        return 4.0;
    }

    /**
     * Returns the Y of the bounding box of the original SVG image.
     * 
     * @return The Y of the bounding box of the original SVG image.
     */
    public static double getOrigY() {
        return 5.0;
    }

	/**
	 * Returns the width of the bounding box of the original SVG image.
	 * 
	 * @return The width of the bounding box of the original SVG image.
	 */
	public static double getOrigWidth() {
		return 17.0;
	}

	/**
	 * Returns the height of the bounding box of the original SVG image.
	 * 
	 * @return The height of the bounding box of the original SVG image.
	 */
	public static double getOrigHeight() {
		return 14.0;
	}

	/**
	 * The current width of this resizable icon.
	 */
	private int width;

	/**
	 * The current height of this resizable icon.
	 */
	private int height;

	/**
	 * Creates a new transcoded SVG image.
	 */
	public ic_view_list_black_24px() {
        this.width = (int) getOrigWidth();
        this.height = (int) getOrigHeight();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.Icon#getIconHeight()
	 */
    @Override
	public int getIconHeight() {
		return height;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.Icon#getIconWidth()
	 */
    @Override
	public int getIconWidth() {
		return width;
	}

	@Override
    public void setDimension(Dimension newDimension) {
        this.width = newDimension.width;
        this.height = newDimension.height;
    }
    
    @Override
    public boolean isHiDpiAware() {
        return true;
    }

    @Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.translate(x, y);

		double coef1 = (double) this.width / (double) getOrigWidth();
		double coef2 = (double) this.height / (double) getOrigHeight();
		double coef = Math.min(coef1, coef2);
        g2d.clipRect(0, 0, this.width, this.height);
		g2d.scale(coef, coef);
        g2d.translate(-getOrigX(), -getOrigY());
        if (coef1 != coef2) {
            if (coef1 < coef2) {
               int extraDy = (int) ((getOrigWidth() - getOrigHeight()) / 2.0);
               g2d.translate(0, extraDy);
            } else {
               int extraDx = (int) ((getOrigHeight() - getOrigWidth()) / 2.0);
               g2d.translate(extraDx, 0);
            }
        }
		paint(g2d);
		g2d.dispose();
	}
	
	public static HiDpiAwareIcon of(int width, int height) {
	   ic_view_list_black_24px base = new ic_view_list_black_24px();
	   base.width = width;
	   base.height = height;
	   return new HiDpiAwareIcon(base);
	}
}

