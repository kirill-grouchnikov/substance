package tools.jitterbug;

import java.awt.*;
import java.awt.geom.*;

/**
 * This class has been automatically generated using <a
 * href="https://flamingo.dev.java.net">Flamingo SVG transcoder</a>.
 */
public class substance {
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
g.transform(new AffineTransform(1.3333326578140259f, 0.0f, 0.0f, 1.3333326578140259f, 5.900062114960747E-4f, -0.0f));
// _0
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_0 = g.getTransform();
g.transform(new AffineTransform(-0.752575781317554f, 0.6585056517405703f, -0.6585056517405703f, -0.752575781317554f, 310.2049865722656f, 167.59800720214844f));
// _0_0_0
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_0_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0
paint = new Color(0, 0, 0, 255);
stroke = new BasicStroke(20.0f,1,1,3.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(0.0, 139.41);
((GeneralPath)shape).curveTo(23.64, 156.92, 46.62, 183.6, 74.01, 184.91);
((GeneralPath)shape).curveTo(95.69, 185.94, 120.12, 171.07, 142.34, 167.23);
((GeneralPath)shape).curveTo(158.16, 164.5, 172.85, 167.36, 184.27, 175.05);
((GeneralPath)shape).curveTo(195.56, 182.66, 203.65, 195.0, 201.61, 205.97);
((GeneralPath)shape).curveTo(200.21, 213.5, 194.06, 220.39, 186.75, 222.32);
((GeneralPath)shape).curveTo(176.88, 224.94, 164.91, 218.53, 157.54, 209.84);
((GeneralPath)shape).curveTo(148.63, 199.34, 146.44, 185.49, 147.06, 170.49);
((GeneralPath)shape).curveTo(147.72, 154.46, 151.57, 137.12, 150.12, 118.98);
((GeneralPath)shape).curveTo(148.86, 103.29, 143.64, 86.99, 134.79, 74.2);
((GeneralPath)shape).curveTo(120.67, 53.81, 97.33, 42.33, 75.36, 45.25);
((GeneralPath)shape).curveTo(63.41, 46.84, 51.87, 52.69, 44.12, 61.07);
((GeneralPath)shape).curveTo(31.55, 74.67, 28.96, 94.96, 39.07, 110.9);
((GeneralPath)shape).curveTo(50.18, 128.41, 76.62, 140.68, 95.67, 139.41);
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
g.setTransform(defaultTransform__0_0_0_0);
g.setTransform(defaultTransform__0_0_0);
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
        return -5;
    }

    /**
     * Returns the Y of the bounding box of the original SVG image.
     * 
     * @return The Y of the bounding box of the original SVG image.
     */
    public static int getOrigY() {
        return -21;
    }

    /**
     * Returns the width of the bounding box of the original SVG image.
     * 
     * @return The width of the bounding box of the original SVG image.
     */
    public static int getOrigWidth() {
        return 403;
    }

    /**
     * Returns the height of the bounding box of the original SVG image.
     * 
     * @return The height of the bounding box of the original SVG image.
     */
    public static int getOrigHeight() {
        return 400;
    }
}

