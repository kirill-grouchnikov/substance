package test.check.svg.flags;

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
public class ca implements Icon, UIResource, IsResizable, IsHiDpiAware {
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
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, -0.0f, -0.0f));
// _0
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0 = g.getTransform();
g.transform(new AffineTransform(0.9375f, 0.0f, 0.0f, 0.9375f, 74.11799621582031f, 0.0f));
// _0_0
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0
paint = new Color(255, 255, 255, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(81.137, 0.0);
((GeneralPath)shape).lineTo(443.413, 0.0);
((GeneralPath)shape).lineTo(443.413, 512.0);
((GeneralPath)shape).lineTo(81.137, 512.0);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_0);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_1 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_1
paint = new Color(191, 10, 48, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(-100.0, 0.0);
((GeneralPath)shape).lineTo(81.138, 0.0);
((GeneralPath)shape).lineTo(81.138, 512.0);
((GeneralPath)shape).lineTo(-100.0, 512.0);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(443.41302, 0.0);
((GeneralPath)shape).lineTo(624.55, 0.0);
((GeneralPath)shape).lineTo(624.55, 512.0);
((GeneralPath)shape).lineTo(443.414, 512.0);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(135.31, 247.41);
((GeneralPath)shape).lineTo(121.243, 252.218);
((GeneralPath)shape).lineTo(186.699, 309.664);
((GeneralPath)shape).curveTo(191.649, 324.428, 184.979, 328.78, 180.729, 336.524);
((GeneralPath)shape).lineTo(251.789, 327.504);
((GeneralPath)shape).lineTo(249.939, 399.016);
((GeneralPath)shape).lineTo(264.65698, 398.593);
((GeneralPath)shape).lineTo(261.447, 327.675);
((GeneralPath)shape).lineTo(332.577, 336.107);
((GeneralPath)shape).curveTo(328.175, 326.81, 324.257, 321.874, 328.33, 307.009);
((GeneralPath)shape).lineTo(393.744, 252.58301);
((GeneralPath)shape).lineTo(382.297, 248.43901);
((GeneralPath)shape).curveTo(372.937, 241.21701, 386.341, 213.65501, 388.363, 196.26102);
((GeneralPath)shape).curveTo(388.363, 196.26102, 350.168, 209.39601, 347.665, 202.52301);
((GeneralPath)shape).lineTo(337.93802, 183.83801);
((GeneralPath)shape).lineTo(303.191, 222.00801);
((GeneralPath)shape).curveTo(299.39502, 222.91801, 297.778, 221.408, 296.88702, 218.20001);
((GeneralPath)shape).lineTo(312.94003, 138.43402);
((GeneralPath)shape).lineTo(287.52002, 152.73102);
((GeneralPath)shape).curveTo(285.39203, 153.64102, 283.264, 152.85602, 281.86203, 150.37602);
((GeneralPath)shape).lineTo(257.41202, 101.316025);
((GeneralPath)shape).lineTo(232.20203, 152.26602);
((GeneralPath)shape).curveTo(230.30203, 154.09203, 228.39903, 154.30302, 226.82002, 153.06203);
((GeneralPath)shape).lineTo(202.61603, 139.48402);
((GeneralPath)shape).lineTo(217.14603, 218.62701);
((GeneralPath)shape).curveTo(215.99002, 221.76701, 213.22203, 222.65201, 209.96603, 220.95102);
((GeneralPath)shape).lineTo(176.75003, 183.21402);
((GeneralPath)shape).curveTo(172.40503, 190.17603, 169.46004, 201.55002, 163.71703, 204.09901);
((GeneralPath)shape).curveTo(157.97302, 206.48601, 138.73703, 199.27602, 125.844025, 196.46202);
((GeneralPath)shape).curveTo(130.24803, 212.35703, 144.02002, 238.76402, 135.30403, 247.41902);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_1);
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
        return 0;
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
		return 640;
	}

	/**
	 * Returns the height of the bounding box of the original SVG image.
	 * 
	 * @return The height of the bounding box of the original SVG image.
	 */
	public static int getOrigHeight() {
		return 480;
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
	public ca() {
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
}

