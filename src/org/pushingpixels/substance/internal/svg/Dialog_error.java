package org.pushingpixels.substance.internal.svg;

import java.awt.*;
import java.awt.geom.*;

import javax.swing.Icon;
import javax.swing.plaf.UIResource;

import org.pushingpixels.lafwidget.icon.IsHiDpiAware;
import org.pushingpixels.lafwidget.icon.IsResizable;

/**
 * This class has been automatically generated using <a
 * href="https://flamingo.dev.java.net">Flamingo SVG transcoder</a>.
 */
public class Dialog_error implements Icon, UIResource, IsResizable, IsHiDpiAware {
	/**
	 * Paints the transcoded SVG image on the specified graphics context. You
	 * can install a custom transformation on the graphics context to scale the
	 * image.
	 * 
	 * @param g
	 *            Graphics context.
	 */
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
g.setComposite(AlphaComposite.getInstance(3, 0.6f * origAlpha));
AffineTransform defaultTransform__0_0_0 = g.getTransform();
g.transform(new AffineTransform(1.0705549716949463f, 0.0f, 0.0f, 0.5249999761581421f, -0.8927549719810486f, 22.5f));
// _0_0_0
paint = new RadialGradientPaint(new Point2D.Double(23.85714340209961, 40.0), 17.142857f, new Point2D.Double(23.85714340209961, 40.0), new float[] {0.0f,1.0f}, new Color[] {new Color(0, 0, 0, 255),new Color(0, 0, 0, 0)}, MultipleGradientPaint.CycleMethod.NO_CYCLE, MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(1.0f, 0.0f, 0.0f, 0.5f, 1.8930479945655602E-14f, 20.0f));
shape = new GeneralPath();
((GeneralPath)shape).moveTo(41.0, 40.0);
((GeneralPath)shape).curveTo(41.0, 44.733868, 33.324883, 48.571426, 23.857143, 48.571426);
((GeneralPath)shape).curveTo(14.389405, 48.571426, 6.714287, 44.733868, 6.714287, 40.0);
((GeneralPath)shape).curveTo(6.714287, 35.266132, 14.389405, 31.428572, 23.857143, 31.428572);
((GeneralPath)shape).curveTo(33.324883, 31.428572, 41.0, 35.266132, 41.0, 40.0);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_0);
g.setTransform(defaultTransform__0_0);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_1 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_1
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_1_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_1_0
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_1_0_0 = g.getTransform();
g.transform(new AffineTransform(0.9204879999160767f, 0.0f, 0.0f, 0.9204879999160767f, 2.3685319423675537f, 0.9740800261497498f));
// _0_1_0_0
paint = new LinearGradientPaint(new Point2D.Double(36.91797637939453, 66.2880630493164), new Point2D.Double(19.071495056152344, 5.541010856628418), new float[] {0.0f,1.0f}, new Color[] {new Color(164, 0, 0, 255),new Color(255, 23, 23, 255)}, MultipleGradientPaint.CycleMethod.NO_CYCLE, MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
shape = new GeneralPath();
((GeneralPath)shape).moveTo(46.857143, 23.928572);
((GeneralPath)shape).curveTo(46.857143, 36.828365, 36.399796, 47.285713, 23.5, 47.285713);
((GeneralPath)shape).curveTo(10.600206, 47.285713, 0.1428566, 36.828365, 0.1428566, 23.928572);
((GeneralPath)shape).curveTo(0.1428566, 11.028778, 10.600206, 0.5714283, 23.5, 0.5714283);
((GeneralPath)shape).curveTo(36.399796, 0.5714283, 46.857143, 11.028778, 46.857143, 23.928572);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
paint = new Color(178, 0, 0, 255);
stroke = new BasicStroke(1.08638f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(46.857143, 23.928572);
((GeneralPath)shape).curveTo(46.857143, 36.828365, 36.399796, 47.285713, 23.5, 47.285713);
((GeneralPath)shape).curveTo(10.600206, 47.285713, 0.1428566, 36.828365, 0.1428566, 23.928572);
((GeneralPath)shape).curveTo(0.1428566, 11.028778, 10.600206, 0.5714283, 23.5, 0.5714283);
((GeneralPath)shape).curveTo(36.399796, 0.5714283, 46.857143, 11.028778, 46.857143, 23.928572);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
g.setTransform(defaultTransform__0_1_0_0);
g.setComposite(AlphaComposite.getInstance(3, 0.34659088f * origAlpha));
AffineTransform defaultTransform__0_1_0_1 = g.getTransform();
g.transform(new AffineTransform(0.8560929894447327f, 0.0f, 0.0f, 0.8560929894447327f, 1.818274974822998f, 0.19776900112628937f));
// _0_1_0_1
paint = new Color(204, 0, 0, 0);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(49.901535, 26.635273);
((GeneralPath)shape).curveTo(49.901535, 39.885204, 39.160343, 50.626396, 25.910412, 50.626396);
((GeneralPath)shape).curveTo(12.6604805, 50.626396, 1.9192886, 39.885204, 1.9192886, 26.635273);
((GeneralPath)shape).curveTo(1.9192886, 13.385342, 12.6604805, 2.6441498, 25.910412, 2.6441498);
((GeneralPath)shape).curveTo(39.160343, 2.6441498, 49.901535, 13.385342, 49.901535, 26.635273);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
paint = new LinearGradientPaint(new Point2D.Double(43.93581008911133, 53.83598327636719), new Point2D.Double(20.064685821533203, -8.562670707702637), new float[] {0.0f,1.0f}, new Color[] {new Color(255, 230, 155, 255),new Color(255, 255, 255, 255)}, MultipleGradientPaint.CycleMethod.NO_CYCLE, MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
stroke = new BasicStroke(1.1680961f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(49.901535, 26.635273);
((GeneralPath)shape).curveTo(49.901535, 39.885204, 39.160343, 50.626396, 25.910412, 50.626396);
((GeneralPath)shape).curveTo(12.6604805, 50.626396, 1.9192886, 39.885204, 1.9192886, 26.635273);
((GeneralPath)shape).curveTo(1.9192886, 13.385342, 12.6604805, 2.6441498, 25.910412, 2.6441498);
((GeneralPath)shape).curveTo(39.160343, 2.6441498, 49.901535, 13.385342, 49.901535, 26.635273);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
g.setTransform(defaultTransform__0_1_0_1);
g.setTransform(defaultTransform__0_1_0);
g.setTransform(defaultTransform__0_1);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_2 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_2
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_2_0 = g.getTransform();
g.transform(new AffineTransform(1.0058759450912476f, 0.0f, 0.0f, 1.1152009963989258f, -0.13804499804973602f, -2.3727080821990967f));
// _0_2_0
paint = new Color(239, 239, 239, 255);
shape = new Rectangle2D.Double(10.078821182250977, 19.164932250976562, 27.836435317993164, 7.1735944747924805);
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_2_0);
g.setTransform(defaultTransform__0_2);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_3 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_3
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_3_0 = g.getTransform();
g.transform(new AffineTransform(1.0029939413070679f, 0.0f, 0.0f, 1.0029939413070679f, -0.07185874134302139f, 0.019683560356497765f));
// _0_3_0
paint = new LinearGradientPaint(new Point2D.Double(21.993772506713867, 33.955299377441406), new Point2D.Double(20.917078018188477, 15.81460189819336), new float[] {0.0f,1.0f}, new Color[] {new Color(255, 254, 255, 85),new Color(255, 254, 255, 55)}, MultipleGradientPaint.CycleMethod.NO_CYCLE, MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
shape = new GeneralPath();
((GeneralPath)shape).moveTo(43.370686, 21.715487);
((GeneralPath)shape).curveTo(43.370686, 32.5461, 33.016357, 15.449178, 24.695948, 22.101873);
((GeneralPath)shape).curveTo(16.569626, 28.599384, 4.098984, 34.292423, 4.098984, 23.461805);
((GeneralPath)shape).curveTo(4.098984, 12.377753, 12.79438, 2.094803, 23.625, 2.094803);
((GeneralPath)shape).curveTo(34.45562, 2.094803, 43.370686, 10.884868, 43.370686, 21.715487);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_3_0);
g.setTransform(defaultTransform__0_3);
g.setTransform(defaultTransform__0);
g.setTransform(defaultTransform_);

	}

    /**
     * Returns the X of the bounding box of the original SVG image.
     * 
     * @return The X of the bounding box of the original SVG image.
     */
    public static int getOrigX() {
        return 3;
    }

    /**
     * Returns the Y of the bounding box of the original SVG image.
     * 
     * @return The Y of the bounding box of the original SVG image.
     */
    public static int getOrigY() {
        return 2;
    }

	/**
	 * Returns the width of the bounding box of the original SVG image.
	 * 
	 * @return The width of the bounding box of the original SVG image.
	 */
	public static int getOrigWidth() {
		return 44;
	}

	/**
	 * Returns the height of the bounding box of the original SVG image.
	 * 
	 * @return The height of the bounding box of the original SVG image.
	 */
	public static int getOrigHeight() {
		return 47;
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
	public Dialog_error() {
        this.width = getOrigWidth();
        this.height = getOrigHeight();
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
		paint(g2d);
		g2d.dispose();
	}
	
	public static Dialog_error of(int width, int height) {
	   Dialog_error result = new Dialog_error();
	   result.width = width;
	   result.height = height;
	   return result;
	}
}

