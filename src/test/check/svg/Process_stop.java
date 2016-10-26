package test.check.svg;

import java.awt.*;
import java.awt.geom.*;

import javax.swing.Icon;
import javax.swing.plaf.UIResource;

import org.pushingpixels.substance.api.icon.IsHiDpiAware;
import org.pushingpixels.substance.api.icon.IsResizable;

/**
 * This class has been automatically generated using <a
 * href="https://flamingo.dev.java.net">Flamingo SVG transcoder</a>.
 */
public class Process_stop implements Icon, UIResource, IsResizable, IsHiDpiAware {
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
g.setComposite(AlphaComposite.getInstance(3, 0.63068f * origAlpha));
AffineTransform defaultTransform__0_0_0 = g.getTransform();
g.transform(new AffineTransform(1.173799991607666f, 0.0f, 0.0f, 0.6000000238418579f, -5.265900135040283f, 19.575000762939453f));
// _0_0_0
paint = new RadialGradientPaint(new Point2D.Double(25.125, 36.75), 15.75f, new Point2D.Double(25.125, 36.75), new float[] {0.0f,1.0f}, new Color[] {new Color(0, 0, 0, 255),new Color(0, 0, 0, 0)}, MultipleGradientPaint.CycleMethod.NO_CYCLE, MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(1.0f, 0.0f, 0.0f, 0.5952399969100952f, -2.300700091662053E-15f, 14.875f));
shape = new GeneralPath();
((GeneralPath)shape).moveTo(40.875, 36.75);
((GeneralPath)shape).curveTo(40.875, 41.92767, 33.823486, 46.125, 25.125, 46.125);
((GeneralPath)shape).curveTo(16.426516, 46.125, 9.375, 41.92767, 9.375, 36.75);
((GeneralPath)shape).curveTo(9.375, 31.57233, 16.426516, 27.375, 25.125, 27.375);
((GeneralPath)shape).curveTo(33.823486, 27.375, 40.875, 31.57233, 40.875, 36.75);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_0);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_1 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_1
paint = new LinearGradientPaint(new Point2D.Double(23.996000289916992, 20.104999542236328), new Point2D.Double(41.04800033569336, 37.959999084472656), new float[] {0.0f,1.0f}, new Color[] {new Color(204, 0, 0, 255),new Color(179, 0, 0, 255)}, MultipleGradientPaint.CycleMethod.NO_CYCLE, MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, -2.0f));
shape = new GeneralPath();
((GeneralPath)shape).moveTo(15.591, 0.49192);
((GeneralPath)shape).lineTo(32.676, 0.49192);
((GeneralPath)shape).lineTo(45.497997, 13.58592);
((GeneralPath)shape).lineTo(45.497997, 31.47992);
((GeneralPath)shape).lineTo(32.849, 43.496918);
((GeneralPath)shape).lineTo(15.418999, 43.496918);
((GeneralPath)shape).lineTo(2.4939985, 30.657917);
((GeneralPath)shape).lineTo(2.4943986, 13.463917);
((GeneralPath)shape).lineTo(15.591398, 0.49191666);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
paint = new Color(134, 0, 0, 255);
stroke = new BasicStroke(1.0f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(15.591, 0.49192);
((GeneralPath)shape).lineTo(32.676, 0.49192);
((GeneralPath)shape).lineTo(45.497997, 13.58592);
((GeneralPath)shape).lineTo(45.497997, 31.47992);
((GeneralPath)shape).lineTo(32.849, 43.496918);
((GeneralPath)shape).lineTo(15.418999, 43.496918);
((GeneralPath)shape).lineTo(2.4939985, 30.657917);
((GeneralPath)shape).lineTo(2.4943986, 13.463917);
((GeneralPath)shape).lineTo(15.591398, 0.49191666);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
g.setTransform(defaultTransform__0_0_1);
g.setComposite(AlphaComposite.getInstance(3, 0.81319f * origAlpha));
AffineTransform defaultTransform__0_0_2 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_2
paint = new LinearGradientPaint(new Point2D.Double(15.737000465393066, 12.503999710083008), new Point2D.Double(53.56999969482422, 47.374000549316406), new float[] {0.0f,1.0f}, new Color[] {new Color(255, 139, 139, 255),new Color(236, 27, 27, 255)}, MultipleGradientPaint.CycleMethod.NO_CYCLE, MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, -2.0f));
stroke = new BasicStroke(1.0f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(16.021, 1.5003);
((GeneralPath)shape).lineTo(32.249, 1.5003);
((GeneralPath)shape).lineTo(44.496002, 13.923301);
((GeneralPath)shape).lineTo(44.496002, 31.0373);
((GeneralPath)shape).lineTo(32.638, 42.4883);
((GeneralPath)shape).lineTo(15.870001, 42.4883);
((GeneralPath)shape).lineTo(3.5090008, 30.209301);
((GeneralPath)shape).lineTo(3.5091007, 13.8463);
((GeneralPath)shape).lineTo(16.021101, 1.5003004);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
g.setTransform(defaultTransform__0_0_2);
g.setComposite(AlphaComposite.getInstance(3, 0.28977f * origAlpha));
AffineTransform defaultTransform__0_0_3 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_3
paint = new RadialGradientPaint(new Point2D.Double(16.75, 10.666000366210938), 21.25f, new Point2D.Double(16.75, 10.666000366210938), new float[] {0.0f,1.0f}, new Color[] {new Color(255, 255, 255, 255),new Color(255, 255, 255, 0)}, MultipleGradientPaint.CycleMethod.NO_CYCLE, MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(4.15500020980835f, -2.9792000645288686E-24f, 3.2556999533285856E-24f, 3.198699951171875f, -52.84600067138672f, -23.509000778198242f));
shape = new GeneralPath();
((GeneralPath)shape).moveTo(15.688, 0.75);
((GeneralPath)shape).lineTo(2.75, 13.562);
((GeneralPath)shape).lineTo(2.75, 30.562);
((GeneralPath)shape).lineTo(5.6875, 33.469);
((GeneralPath)shape).curveTo(22.4495, 33.526, 22.1655, 20.45, 45.2495, 21.594002);
((GeneralPath)shape).lineTo(45.2495, 13.688002);
((GeneralPath)shape).lineTo(32.5615, 0.7500019);
((GeneralPath)shape).lineTo(15.6875, 0.7500019);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_3);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_4 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_4
paint = new RadialGradientPaint(new Point2D.Double(24.302000045776367, 33.301998138427734), 12.302f, new Point2D.Double(24.302000045776367, 33.301998138427734), new float[] {0.0f,1.0f}, new Color[] {new Color(255, 255, 255, 255),new Color(219, 219, 219, 255)}, MultipleGradientPaint.CycleMethod.NO_CYCLE, MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(1.694000005722046f, -5.775700085738748E-16f, 5.775700085738748E-16f, 1.694000005722046f, -16.864999771118164f, -25.111000061035156f));
shape = new GeneralPath();
((GeneralPath)shape).moveTo(16.767, 10.5);
((GeneralPath)shape).lineTo(12.5, 14.767);
((GeneralPath)shape).lineTo(20.035, 22.302);
((GeneralPath)shape).lineTo(12.5, 29.837);
((GeneralPath)shape).lineTo(16.767, 34.105);
((GeneralPath)shape).lineTo(24.302, 26.569);
((GeneralPath)shape).lineTo(31.837, 34.105);
((GeneralPath)shape).lineTo(36.105, 29.837);
((GeneralPath)shape).lineTo(28.569, 22.302);
((GeneralPath)shape).lineTo(36.105, 14.767);
((GeneralPath)shape).lineTo(31.837, 10.5);
((GeneralPath)shape).lineTo(24.302, 18.035);
((GeneralPath)shape).lineTo(16.767, 10.5);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
paint = new LinearGradientPaint(new Point2D.Double(21.75, 15.802000045776367), new Point2D.Double(24.302000045776367, 35.051998138427734), new float[] {0.0f,1.0f}, new Color[] {new Color(255, 2, 2, 255),new Color(255, 155, 155, 255)}, MultipleGradientPaint.CycleMethod.NO_CYCLE, MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, -2.0f));
stroke = new BasicStroke(1.0f,1,1,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(16.767, 10.5);
((GeneralPath)shape).lineTo(12.5, 14.767);
((GeneralPath)shape).lineTo(20.035, 22.302);
((GeneralPath)shape).lineTo(12.5, 29.837);
((GeneralPath)shape).lineTo(16.767, 34.105);
((GeneralPath)shape).lineTo(24.302, 26.569);
((GeneralPath)shape).lineTo(31.837, 34.105);
((GeneralPath)shape).lineTo(36.105, 29.837);
((GeneralPath)shape).lineTo(28.569, 22.302);
((GeneralPath)shape).lineTo(36.105, 14.767);
((GeneralPath)shape).lineTo(31.837, 10.5);
((GeneralPath)shape).lineTo(24.302, 18.035);
((GeneralPath)shape).lineTo(16.767, 10.5);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
g.setTransform(defaultTransform__0_0_4);
g.setTransform(defaultTransform__0_0);
g.setTransform(defaultTransform__0);
g.setTransform(defaultTransform_);

	}

    /**
     * Returns the X of the bounding box of the original SVG image.
     * 
     * @return The X of the bounding box of the original SVG image.
     */
    public static int getOrigX() {
        return 2;
    }

    /**
     * Returns the Y of the bounding box of the original SVG image.
     * 
     * @return The Y of the bounding box of the original SVG image.
     */
    public static int getOrigY() {
        return 0;
    }

	/**
	 * Returns the width of the bounding box of the original SVG image.
	 * 
	 * @return The width of the bounding box of the original SVG image.
	 */
	public static int getOrigWidth() {
		return 45;
	}

	/**
	 * Returns the height of the bounding box of the original SVG image.
	 * 
	 * @return The height of the bounding box of the original SVG image.
	 */
	public static int getOrigHeight() {
		return 48;
	}

	/**
	 * The current width of this resizable icon.
	 */
	int width;

	/**
	 * The current height of this resizable icon.
	 */
	int height;

	/**
	 * Creates a new transcoded SVG image.
	 */
	public Process_stop() {
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
		g2d.scale(coef, coef);
		paint(g2d);
		g2d.dispose();
	}
}

