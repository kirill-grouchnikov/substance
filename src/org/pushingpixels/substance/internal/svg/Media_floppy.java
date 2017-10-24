package org.pushingpixels.substance.internal.svg;

import java.awt.*;
import java.awt.geom.*;

import javax.swing.Icon;
import javax.swing.plaf.UIResource;

import org.pushingpixels.lafwidget.icon.IsHiDpiAware;
import org.pushingpixels.lafwidget.icon.IsResizable;

/**
 * This class has been automatically generated using <a
 * href="https://github.com/kirill-grouchnikov/flamingo">Flamingo SVG transcoder</a>.
 */
public class Media_floppy implements Icon, UIResource, IsResizable, IsHiDpiAware {
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
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0
g.setComposite(AlphaComposite.getInstance(3, 0.5056818f * origAlpha));
AffineTransform defaultTransform__0_0_0 = g.getTransform();
g.transform(new AffineTransform(0.9180319905281067f, 0.0f, 0.0f, 0.9812210202217102f, 1.6803280115127563f, 0.6478869915008545f));
// _0_0_0
paint = new RadialGradientPaint(new Point2D.Double(24.3125, 41.15625), 22.875f, new Point2D.Double(24.3125, 41.15625), new float[] {0.0f,1.0f}, new Color[] {new Color(0, 0, 0, 255),new Color(0, 0, 0, 0)}, MultipleGradientPaint.CycleMethod.NO_CYCLE, MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(1.0f, 0.0f, 0.0f, 0.2691259980201721f, -1.3522719537259335E-16f, 30.080049514770508f));
shape = new GeneralPath();
((GeneralPath)shape).moveTo(47.1875, 41.15625);
((GeneralPath)shape).curveTo(47.1875, 44.55625, 36.946014, 47.3125, 24.3125, 47.3125);
((GeneralPath)shape).curveTo(11.678987, 47.3125, 1.4375, 44.55625, 1.4375, 41.15625);
((GeneralPath)shape).curveTo(1.4375, 37.75625, 11.678987, 35.0, 24.3125, 35.0);
((GeneralPath)shape).curveTo(36.946014, 35.0, 47.1875, 37.75625, 47.1875, 41.15625);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_0);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_1 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_1
paint = new LinearGradientPaint(new Point2D.Double(40.88472366333008, 71.86913299560547), new Point2D.Double(16.879831314086914, -0.38931384682655334), new float[] {0.0f,1.0f}, new Color[] {new Color(30, 45, 105, 255),new Color(120, 167, 224, 255)}, MultipleGradientPaint.CycleMethod.NO_CYCLE, MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(0.9766119718551636f, 0.0f, 0.0f, 1.1397889852523804f, 0.5642150044441223f, -3.27115797996521f));
shape = new GeneralPath();
((GeneralPath)shape).moveTo(4.5577602, 3.5675797);
((GeneralPath)shape).lineTo(43.448063, 3.5675797);
((GeneralPath)shape).curveTo(44.037357, 3.5675797, 44.511772, 4.0419936, 44.511772, 4.6312885);
((GeneralPath)shape).lineTo(44.511772, 42.3965);
((GeneralPath)shape).curveTo(44.511772, 42.985794, 44.037357, 43.46021, 43.448063, 43.46021);
((GeneralPath)shape).lineTo(6.5577602, 43.46021);
((GeneralPath)shape).curveTo(6.5577602, 43.46021, 3.494052, 40.3965, 3.494052, 40.3965);
((GeneralPath)shape).lineTo(3.494052, 4.6312885);
((GeneralPath)shape).curveTo(3.494052, 4.0419936, 3.9684658, 3.5675797, 4.5577602, 3.5675797);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
paint = new Color(37, 55, 95, 255);
stroke = new BasicStroke(1.0000001f,1,1,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(4.5577602, 3.5675797);
((GeneralPath)shape).lineTo(43.448063, 3.5675797);
((GeneralPath)shape).curveTo(44.037357, 3.5675797, 44.511772, 4.0419936, 44.511772, 4.6312885);
((GeneralPath)shape).lineTo(44.511772, 42.3965);
((GeneralPath)shape).curveTo(44.511772, 42.985794, 44.037357, 43.46021, 43.448063, 43.46021);
((GeneralPath)shape).lineTo(6.5577602, 43.46021);
((GeneralPath)shape).curveTo(6.5577602, 43.46021, 3.494052, 40.3965, 3.494052, 40.3965);
((GeneralPath)shape).lineTo(3.494052, 4.6312885);
((GeneralPath)shape).curveTo(3.494052, 4.0419936, 3.9684658, 3.5675797, 4.5577602, 3.5675797);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
g.setTransform(defaultTransform__0_0_1);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_2 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_2
paint = new Color(255, 255, 255, 255);
shape = new Rectangle2D.Double(9.0, 4.0, 30.0, 23.0);
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_2);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_3 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_3
paint = new Color(211, 28, 0, 255);
shape = new RoundRectangle2D.Double(9.0, 4.0, 30.0, 4.0, 0.2524154484272003, 0.25241541862487793);
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_3);
g.setComposite(AlphaComposite.getInstance(3, 0.7386364f * origAlpha));
AffineTransform defaultTransform__0_0_4 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_4
paint = new Color(0, 0, 0, 255);
shape = new RoundRectangle2D.Double(6.0, 6.0, 2.0, 2.0, 0.2524154484272003, 0.25241541862487793);
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_4);
g.setComposite(AlphaComposite.getInstance(3, 0.13068181f * origAlpha));
AffineTransform defaultTransform__0_0_5 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_5
paint = new Color(0, 0, 0, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(11.0, 12.5);
((GeneralPath)shape).lineTo(37.0, 12.5);
g.setPaint(paint);
g.fill(shape);
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(1.0f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(11.0, 12.5);
((GeneralPath)shape).lineTo(37.0, 12.5);
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
g.setTransform(defaultTransform__0_0_5);
g.setComposite(AlphaComposite.getInstance(3, 0.13068181f * origAlpha));
AffineTransform defaultTransform__0_0_6 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_6
paint = new Color(0, 0, 0, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(11.0, 17.5);
((GeneralPath)shape).lineTo(37.0, 17.5);
g.setPaint(paint);
g.fill(shape);
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(1.0f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(11.0, 17.5);
((GeneralPath)shape).lineTo(37.0, 17.5);
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
g.setTransform(defaultTransform__0_0_6);
g.setComposite(AlphaComposite.getInstance(3, 0.13068181f * origAlpha));
AffineTransform defaultTransform__0_0_7 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_7
paint = new Color(0, 0, 0, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(11.0, 22.5);
((GeneralPath)shape).lineTo(37.0, 22.5);
g.setPaint(paint);
g.fill(shape);
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(1.0f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(11.0, 22.5);
((GeneralPath)shape).lineTo(37.0, 22.5);
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
g.setTransform(defaultTransform__0_0_7);
g.setComposite(AlphaComposite.getInstance(3, 0.59659094f * origAlpha));
AffineTransform defaultTransform__0_0_8 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_8
paint = new LinearGradientPaint(new Point2D.Double(13.783584594726562, -0.9967289566993713), new Point2D.Double(33.07471466064453, 55.70154571533203), new float[] {0.0f,1.0f}, new Color[] {new Color(255, 255, 255, 255),new Color(255, 255, 255, 0)}, MultipleGradientPaint.CycleMethod.NO_CYCLE, MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(0.9854320287704468f, 0.0f, 0.0f, 1.148179054260254f, 0.6410700082778931f, -2.93388295173645f));
stroke = new BasicStroke(1.0f,1,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(4.6189227, 4.5276647);
((GeneralPath)shape).lineTo(43.387405, 4.5276647);
((GeneralPath)shape).curveTo(43.45732, 4.5276647, 43.51361, 4.5839534, 43.51361, 4.6538725);
((GeneralPath)shape).lineTo(43.51361, 42.302113);
((GeneralPath)shape).curveTo(43.51361, 42.37203, 43.45732, 42.428318, 43.387405, 42.428318);
((GeneralPath)shape).lineTo(6.928282, 42.428318);
((GeneralPath)shape).curveTo(6.928282, 42.428318, 4.492715, 40.036945, 4.492715, 40.036945);
((GeneralPath)shape).lineTo(4.492715, 4.6538725);
((GeneralPath)shape).curveTo(4.492715, 4.5839534, 4.5490036, 4.5276647, 4.6189227, 4.5276647);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
g.setTransform(defaultTransform__0_0_8);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_9 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_9
paint = new LinearGradientPaint(new Point2D.Double(20.125, 21.84375), new Point2D.Double(28.5625, 42.46875), new float[] {0.0f,0.5f,1.0f}, new Color[] {new Color(133, 133, 133, 255),new Color(203, 203, 203, 255),new Color(107, 107, 107, 255)}, MultipleGradientPaint.CycleMethod.NO_CYCLE, MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(1.0676980018615723f, 0.0f, 0.0f, 1.1215319633483887f, -1.3689370155334473f, -5.574460029602051f));
shape = new GeneralPath();
((GeneralPath)shape).moveTo(14.113967, 28.562183);
((GeneralPath)shape).lineTo(33.863792, 28.562183);
((GeneralPath)shape).curveTo(34.751762, 28.562183, 35.466625, 29.313093, 35.466625, 30.245836);
((GeneralPath)shape).lineTo(35.466625, 43.447388);
((GeneralPath)shape).curveTo(35.466625, 43.447388, 12.511131, 43.447388, 12.511131, 43.447388);
((GeneralPath)shape).lineTo(12.511131, 30.245836);
((GeneralPath)shape).curveTo(12.511131, 29.313093, 13.225996, 28.562183, 14.113967, 28.562183);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
paint = new Color(82, 82, 82, 255);
stroke = new BasicStroke(0.99999946f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(14.113967, 28.562183);
((GeneralPath)shape).lineTo(33.863792, 28.562183);
((GeneralPath)shape).curveTo(34.751762, 28.562183, 35.466625, 29.313093, 35.466625, 30.245836);
((GeneralPath)shape).lineTo(35.466625, 43.447388);
((GeneralPath)shape).curveTo(35.466625, 43.447388, 12.511131, 43.447388, 12.511131, 43.447388);
((GeneralPath)shape).lineTo(12.511131, 30.245836);
((GeneralPath)shape).curveTo(12.511131, 29.313093, 13.225996, 28.562183, 14.113967, 28.562183);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
g.setTransform(defaultTransform__0_0_9);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_10 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_10
paint = new Color(73, 103, 162, 255);
shape = new RoundRectangle2D.Double(16.464279174804688, 30.456600189208984, 5.029752731323242, 10.065970420837402, 1.5024142265319824, 1.5024152994155884);
g.setPaint(paint);
g.fill(shape);
paint = new Color(82, 82, 82, 255);
stroke = new BasicStroke(0.9999996f,0,0,4.0f,null,0.0f);
shape = new RoundRectangle2D.Double(16.464279174804688, 30.456600189208984, 5.029752731323242, 10.065970420837402, 1.5024142265319824, 1.5024152994155884);
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
g.setTransform(defaultTransform__0_0_10);
g.setTransform(defaultTransform__0_0);
g.setTransform(defaultTransform__0);
g.setTransform(defaultTransform_);

	}

    /**
     * Returns the X of the bounding box of the original SVG image.
     * 
     * @return The X of the bounding box of the original SVG image.
     */
    public static double getOrigX() {
        return 2.994051933288574;
    }

    /**
     * Returns the Y of the bounding box of the original SVG image.
     * 
     * @return The Y of the bounding box of the original SVG image.
     */
    public static double getOrigY() {
        return 3.067579746246338;
    }

	/**
	 * Returns the width of the bounding box of the original SVG image.
	 * 
	 * @return The width of the bounding box of the original SVG image.
	 */
	public static double getOrigWidth() {
		return 42.01771926879883;
	}

	/**
	 * Returns the height of the bounding box of the original SVG image.
	 * 
	 * @return The height of the bounding box of the original SVG image.
	 */
	public static double getOrigHeight() {
		return 44.00432586669922;
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
	public Media_floppy() {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.Icon#paintIcon(java.awt.Component, java.awt.Graphics,
	 * int, int)
	 */
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
	
	public static Media_floppy of(int width, int height) {
	   Media_floppy result = new Media_floppy();
	   result.width = width;
	   result.height = height;
	   return result;
	}
}

