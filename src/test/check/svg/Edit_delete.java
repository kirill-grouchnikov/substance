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
public class Edit_delete implements Icon, UIResource, IsResizable, IsHiDpiAware {
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
g.setComposite(AlphaComposite.getInstance(3, 0.5268817f * origAlpha));
AffineTransform defaultTransform__0_0_0 = g.getTransform();
g.transform(new AffineTransform(1.225000023841858f, 0.0f, 0.0f, 0.40833398699760437f, -5.221223831176758f, 25.176219940185547f));
// _0_0_0
paint = new RadialGradientPaint(new Point2D.Double(23.85714340209961, 40.0), 17.142857f, new Point2D.Double(23.85714340209961, 40.0), new float[] {0.0f,1.0f}, new Color[] {new Color(0, 0, 0, 255),new Color(0, 0, 0, 0)}, MultipleGradientPaint.CycleMethod.NO_CYCLE, MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(1.0f, 0.0f, 0.0f, 0.5f, 1.6357419162661008E-14f, 20.0f));
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
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_1 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_1
paint = new RadialGradientPaint(new Point2D.Double(20.935379028320312, 12.592706680297852), 19.967958f, new Point2D.Double(20.935379028320312, 12.592706680297852), new float[] {0.0f,1.0f}, new Color[] {new Color(239, 41, 41, 255),new Color(204, 0, 0, 255)}, MultipleGradientPaint.CycleMethod.NO_CYCLE, MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(-1.2628710269927979f, 0.02796553075313568f, -0.036067161709070206f, -1.6296559572219849f, 47.36661911010742f, 36.49787139892578f));
shape = new GeneralPath();
((GeneralPath)shape).moveTo(24.035816, 3.5720837);
((GeneralPath)shape).curveTo(13.289574, 3.5720837, 4.567857, 12.294146, 4.567858, 23.040834);
((GeneralPath)shape).curveTo(4.567858, 33.78752, 13.289575, 42.509583, 24.035816, 42.509583);
((GeneralPath)shape).curveTo(34.78206, 42.509583, 43.503777, 33.78752, 43.503777, 23.040834);
((GeneralPath)shape).curveTo(43.503777, 12.294147, 34.78206, 3.5720837, 24.035816, 3.5720837);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(24.004517, 8.478333);
((GeneralPath)shape).curveTo(32.049892, 8.478333, 38.589836, 14.990984, 38.589836, 23.009584);
((GeneralPath)shape).curveTo(38.589836, 25.981869, 37.657974, 28.737373, 36.117218, 31.040834);
((GeneralPath)shape).lineTo(15.960683, 10.915834);
((GeneralPath)shape).curveTo(18.27268, 9.381393, 21.022552, 8.478333, 24.004517, 8.478333);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(12.267404, 14.447084);
((GeneralPath)shape).lineTo(32.580437, 34.728333);
((GeneralPath)shape).curveTo(30.166683, 36.490826, 27.221539, 37.540833, 24.004517, 37.540833);
((GeneralPath)shape).curveTo(15.959141, 37.540833, 9.419198, 31.028185, 9.419198, 23.009584);
((GeneralPath)shape).curveTo(9.419198, 19.80327, 10.497961, 16.851742, 12.267404, 14.447084);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
paint = new Color(164, 0, 0, 255);
stroke = new BasicStroke(0.9999998f,1,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(24.035816, 3.5720837);
((GeneralPath)shape).curveTo(13.289574, 3.5720837, 4.567857, 12.294146, 4.567858, 23.040834);
((GeneralPath)shape).curveTo(4.567858, 33.78752, 13.289575, 42.509583, 24.035816, 42.509583);
((GeneralPath)shape).curveTo(34.78206, 42.509583, 43.503777, 33.78752, 43.503777, 23.040834);
((GeneralPath)shape).curveTo(43.503777, 12.294147, 34.78206, 3.5720837, 24.035816, 3.5720837);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(24.004517, 8.478333);
((GeneralPath)shape).curveTo(32.049892, 8.478333, 38.589836, 14.990984, 38.589836, 23.009584);
((GeneralPath)shape).curveTo(38.589836, 25.981869, 37.657974, 28.737373, 36.117218, 31.040834);
((GeneralPath)shape).lineTo(15.960683, 10.915834);
((GeneralPath)shape).curveTo(18.27268, 9.381393, 21.022552, 8.478333, 24.004517, 8.478333);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(12.267404, 14.447084);
((GeneralPath)shape).lineTo(32.580437, 34.728333);
((GeneralPath)shape).curveTo(30.166683, 36.490826, 27.221539, 37.540833, 24.004517, 37.540833);
((GeneralPath)shape).curveTo(15.959141, 37.540833, 9.419198, 31.028185, 9.419198, 23.009584);
((GeneralPath)shape).curveTo(9.419198, 19.80327, 10.497961, 16.851742, 12.267404, 14.447084);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
g.setTransform(defaultTransform__0_0_1);
g.setComposite(AlphaComposite.getInstance(3, 0.55376345f * origAlpha));
AffineTransform defaultTransform__0_0_2 = g.getTransform();
g.transform(new AffineTransform(1.0075759887695312f, 0.0f, 0.0f, 1.0191570520401f, -4.5681939125061035f, -4.726047992706299f));
// _0_0_2
paint = new Color(255, 255, 255, 255);
stroke = new BasicStroke(0.986826f,1,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(46.714287, 27.214285);
((GeneralPath)shape).curveTo(46.714287, 37.234306, 38.495514, 45.35714, 28.357143, 45.35714);
((GeneralPath)shape).curveTo(18.218773, 45.35714, 10.0, 37.234306, 10.0, 27.214285);
((GeneralPath)shape).curveTo(10.0, 17.194262, 18.218773, 9.071428, 28.357143, 9.071428);
((GeneralPath)shape).curveTo(38.495514, 9.071428, 46.714287, 17.194262, 46.714287, 27.214285);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
g.setTransform(defaultTransform__0_0_2);
g.setComposite(AlphaComposite.getInstance(3, 0.4784946f * origAlpha));
AffineTransform defaultTransform__0_0_3 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_3
paint = new Color(255, 255, 255, 255);
stroke = new BasicStroke(1.0f,1,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(15.075203, 11.366727);
((GeneralPath)shape).lineTo(35.646633, 31.938156);
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
g.setTransform(defaultTransform__0_0_3);
g.setComposite(AlphaComposite.getInstance(3, 0.3064516f * origAlpha));
AffineTransform defaultTransform__0_0_4 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_4
paint = new Color(255, 255, 255, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(4.432346, 19.795298);
((GeneralPath)shape).curveTo(4.456155, 21.985773, 9.837108, 20.461966, 9.860917, 21.652441);
((GeneralPath)shape).curveTo(9.813298, 19.842916, 11.837108, 15.961965, 12.289489, 15.152441);
((GeneralPath)shape).lineTo(20.932346, 23.509584);
((GeneralPath)shape).curveTo(20.884727, 21.771488, 27.122824, 23.390537, 27.003777, 21.509584);
((GeneralPath)shape).lineTo(16.718061, 10.866727);
((GeneralPath)shape).curveTo(18.24187, 10.081013, 21.837109, 8.795298, 24.075205, 8.938155);
((GeneralPath)shape).curveTo(32.313297, 9.152441, 38.051395, 16.795298, 38.075203, 22.009584);
((GeneralPath)shape).lineTo(37.503777, 27.009584);
((GeneralPath)shape).curveTo(37.384727, 28.866728, 43.33711, 26.795298, 43.146633, 28.295298);
((GeneralPath)shape).curveTo(43.551395, 27.152441, 44.027584, 24.795298, 43.932346, 22.081013);
((GeneralPath)shape).curveTo(43.884727, 12.438155, 35.76568, 3.3667266, 24.003775, 3.1524405);
((GeneralPath)shape).curveTo(15.420441, 3.2833936, 8.497822, 8.182202, 6.128774, 14.66137);
((GeneralPath)shape).curveTo(5.993301, 14.69483, 4.658536, 16.842916, 4.432346, 19.795298);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
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
        return 4;
    }

    /**
     * Returns the Y of the bounding box of the original SVG image.
     * 
     * @return The Y of the bounding box of the original SVG image.
     */
    public static int getOrigY() {
        return 4;
    }

	/**
	 * Returns the width of the bounding box of the original SVG image.
	 * 
	 * @return The width of the bounding box of the original SVG image.
	 */
	public static int getOrigWidth() {
		return 42;
	}

	/**
	 * Returns the height of the bounding box of the original SVG image.
	 * 
	 * @return The height of the bounding box of the original SVG image.
	 */
	public static int getOrigHeight() {
		return 42;
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
	public Edit_delete() {
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

