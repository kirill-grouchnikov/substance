package org.pushingpixels.substance.internal.svg;

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
public class Help_browser implements Icon, UIResource, IsResizable, IsHiDpiAware {
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
g.setComposite(AlphaComposite.getInstance(3, 0.6306818f * origAlpha));
AffineTransform defaultTransform__0_0_0 = g.getTransform();
g.transform(new AffineTransform(1.1738029718399048f, 0.0f, 0.0f, 0.6000000238418579f, -5.004403114318848f, 20.325000762939453f));
// _0_0_0
paint = new RadialGradientPaint(new Point2D.Double(25.125, 36.75), 15.75f, new Point2D.Double(25.125, 36.75), new float[] {0.0f,1.0f}, new Color[] {new Color(0, 0, 0, 255),new Color(0, 0, 0, 0)}, MultipleGradientPaint.CycleMethod.NO_CYCLE, MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(1.0f, 0.0f, 0.0f, 0.5952379703521729f, 3.369686058403963E-16f, 14.875f));
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
g.transform(new AffineTransform(0.9384419918060303f, 0.0f, 0.0f, 0.9386799931526184f, 1.564074993133545f, 1.6339060068130493f));
// _0_0_1
paint = new RadialGradientPaint(new Point2D.Double(26.544321060180664, 28.458724975585938), 22.376116f, new Point2D.Double(26.544321060180664, 28.458724975585938), new float[] {0.0f,1.0f}, new Color[] {new Color(156, 188, 222, 255),new Color(32, 74, 135, 255)}, MultipleGradientPaint.CycleMethod.NO_CYCLE, MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(1.238342046737671f, 0.005954845808446407f, -0.006507761776447296f, 1.3512719869613647f, -6.992513179779053f, -9.744841575622559f));
shape = new GeneralPath();
((GeneralPath)shape).moveTo(45.785164, 23.825787);
((GeneralPath)shape).curveTo(45.785164, 35.90763, 35.990894, 45.701904, 23.909048, 45.701904);
((GeneralPath)shape).curveTo(11.827203, 45.701904, 2.0329323, 35.90763, 2.0329323, 23.825787);
((GeneralPath)shape).curveTo(2.0329323, 11.743941, 11.827203, 1.9496708, 23.909048, 1.9496708);
((GeneralPath)shape).curveTo(35.990894, 1.9496708, 45.785164, 11.743941, 45.785164, 23.825787);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
paint = new Color(32, 74, 135, 255);
stroke = new BasicStroke(1.0f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(45.785164, 23.825787);
((GeneralPath)shape).curveTo(45.785164, 35.90763, 35.990894, 45.701904, 23.909048, 45.701904);
((GeneralPath)shape).curveTo(11.827203, 45.701904, 2.0329323, 35.90763, 2.0329323, 23.825787);
((GeneralPath)shape).curveTo(2.0329323, 11.743941, 11.827203, 1.9496708, 23.909048, 1.9496708);
((GeneralPath)shape).curveTo(35.990894, 1.9496708, 45.785164, 11.743941, 45.785164, 23.825787);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
g.setTransform(defaultTransform__0_0_1);
g.setComposite(AlphaComposite.getInstance(3, 0.96022725f * origAlpha));
AffineTransform defaultTransform__0_0_2 = g.getTransform();
g.transform(new AffineTransform(0.8551030158996582f, 0.0f, 0.0f, 0.8552129864692688f, 3.555288076400757f, 3.625019073486328f));
// _0_0_2
paint = new Color(255, 255, 255, 255);
stroke = new BasicStroke(3.0307744f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(45.785164, 23.825787);
((GeneralPath)shape).curveTo(45.785164, 35.90763, 35.990894, 45.701904, 23.909048, 45.701904);
((GeneralPath)shape).curveTo(11.827203, 45.701904, 2.0329323, 35.90763, 2.0329323, 23.825787);
((GeneralPath)shape).curveTo(2.0329323, 11.743941, 11.827203, 1.9496708, 23.909048, 1.9496708);
((GeneralPath)shape).curveTo(35.990894, 1.9496708, 45.785164, 11.743941, 45.785164, 23.825787);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
g.setTransform(defaultTransform__0_0_2);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_3 = g.getTransform();
g.transform(new AffineTransform(0.8498950004577637f, 0.0f, 0.0f, 0.8352050185203552f, 41.72980880737305f, 8.548327445983887f));
// _0_0_3
paint = new RadialGradientPaint(new Point2D.Double(-19.51563835144043, 16.855663299560547), 8.753643f, new Point2D.Double(-19.51563835144043, 16.855663299560547), new float[] {0.0f,1.0f}, new Color[] {new Color(255, 255, 255, 255),new Color(184, 184, 184, 255)}, MultipleGradientPaint.CycleMethod.NO_CYCLE, MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(4.445991039276123f, -8.852599147408013E-16f, 1.3672169501077291E-15f, 6.866499900817871f, 67.2507095336914f, -104.66790008544922f));
shape = new GeneralPath();
((GeneralPath)shape).moveTo(-20.25, 5.875);
((GeneralPath)shape).curveTo(-21.30902, 5.875026, -22.397636, 5.9982357, -23.53125, 6.21875);
((GeneralPath)shape).curveTo(-24.664175, 6.4391785, -25.911411, 6.756263, -27.28125, 7.21875);
((GeneralPath)shape).curveTo(-27.291632, 7.21754, -27.302118, 7.21754, -27.3125, 7.21875);
((GeneralPath)shape).curveTo(-27.324562, 7.227379, -27.335121, 7.237937, -27.34375, 7.25);
((GeneralPath)shape).curveTo(-27.355812, 7.258629, -27.366371, 7.269187, -27.375, 7.28125);
((GeneralPath)shape).curveTo(-27.37621, 7.2916317, -27.37621, 7.302119, -27.375, 7.3125);
((GeneralPath)shape).curveTo(-27.37621, 7.3228817, -27.37621, 7.333369, -27.375, 7.34375);
((GeneralPath)shape).lineTo(-27.375, 12.5);
((GeneralPath)shape).curveTo(-27.37621, 12.510382, -27.37621, 12.520868, -27.375, 12.53125);
((GeneralPath)shape).curveTo(-27.37621, 12.541632, -27.37621, 12.552118, -27.375, 12.5625);
((GeneralPath)shape).curveTo(-27.366371, 12.574563, -27.355812, 12.585121, -27.34375, 12.59375);
((GeneralPath)shape).curveTo(-27.335121, 12.605813, -27.324562, 12.616371, -27.3125, 12.625);
((GeneralPath)shape).curveTo(-27.302118, 12.62621, -27.291632, 12.62621, -27.28125, 12.625);
((GeneralPath)shape).curveTo(-27.270868, 12.62621, -27.260382, 12.62621, -27.25, 12.625);
((GeneralPath)shape).curveTo(-27.239618, 12.62621, -27.229132, 12.62621, -27.21875, 12.625);
((GeneralPath)shape).curveTo(-27.208368, 12.62621, -27.197882, 12.62621, -27.1875, 12.625);
((GeneralPath)shape).curveTo(-26.045061, 11.905957, -24.954147, 11.357862, -23.90625, 11.0);
((GeneralPath)shape).curveTo(-22.858109, 10.631244, -21.863134, 10.437521, -20.96875, 10.4375);
((GeneralPath)shape).curveTo(-20.019531, 10.437521, -19.323826, 10.648045, -18.8125, 11.0625);
((GeneralPath)shape).curveTo(-18.303778, 11.46459, -18.031261, 12.04554, -18.03125, 12.78125);
((GeneralPath)shape).curveTo(-18.03126, 13.261907, -18.175438, 13.73266, -18.46875, 14.21875);
((GeneralPath)shape).curveTo(-18.751741, 14.705766, -19.209015, 15.249245, -19.84375, 15.8125);
((GeneralPath)shape).lineTo(-20.9375, 16.75);
((GeneralPath)shape).curveTo(-22.13896, 17.83049, -22.926743, 18.741022, -23.3125, 19.46875);
((GeneralPath)shape).curveTo(-23.695614, 20.180197, -23.875006, 20.988073, -23.875, 21.90625);
((GeneralPath)shape).lineTo(-23.875, 22.71875);
((GeneralPath)shape).curveTo(-23.87621, 22.729132, -23.87621, 22.739618, -23.875, 22.75);
((GeneralPath)shape).curveTo(-23.87621, 22.760382, -23.87621, 22.770868, -23.875, 22.78125);
((GeneralPath)shape).curveTo(-23.866371, 22.793312, -23.855812, 22.803871, -23.84375, 22.8125);
((GeneralPath)shape).curveTo(-23.835121, 22.824562, -23.824562, 22.835121, -23.8125, 22.84375);
((GeneralPath)shape).curveTo(-23.802118, 22.84496, -23.791632, 22.84496, -23.78125, 22.84375);
((GeneralPath)shape).curveTo(-23.770868, 22.84496, -23.760382, 22.84496, -23.75, 22.84375);
((GeneralPath)shape).lineTo(-17.65625, 22.84375);
((GeneralPath)shape).curveTo(-17.645868, 22.84496, -17.635382, 22.84496, -17.625, 22.84375);
((GeneralPath)shape).curveTo(-17.614618, 22.84496, -17.604132, 22.84496, -17.59375, 22.84375);
((GeneralPath)shape).curveTo(-17.581688, 22.835121, -17.571129, 22.824562, -17.5625, 22.8125);
((GeneralPath)shape).curveTo(-17.550438, 22.803871, -17.539879, 22.793312, -17.53125, 22.78125);
((GeneralPath)shape).curveTo(-17.53004, 22.770868, -17.53004, 22.760382, -17.53125, 22.75);
((GeneralPath)shape).curveTo(-17.53004, 22.739618, -17.53004, 22.729132, -17.53125, 22.71875);
((GeneralPath)shape).lineTo(-17.53125, 21.96875);
((GeneralPath)shape).curveTo(-17.531261, 21.500553, -17.38288, 21.075901, -17.15625, 20.6875);
((GeneralPath)shape).curveTo(-16.933954, 20.296215, -16.448177, 19.73714, -15.6875, 19.0625);
((GeneralPath)shape).lineTo(-14.625, 18.125);
((GeneralPath)shape).curveTo(-13.558412, 17.14269, -12.794341, 16.240347, -12.34375, 15.375);
((GeneralPath)shape).curveTo(-11.894481, 14.500954, -11.656268, 13.50158, -11.65625, 12.40625);
((GeneralPath)shape).curveTo(-11.656268, 10.279985, -12.400019, 8.672222, -13.875, 7.5625);
((GeneralPath)shape).curveTo(-15.350197, 6.441475, -17.48124, 5.875026, -20.25, 5.875);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(-23.8125, 25.03125);
((GeneralPath)shape).curveTo(-23.824562, 25.039879, -23.835121, 25.050438, -23.84375, 25.0625);
((GeneralPath)shape).curveTo(-23.855812, 25.071129, -23.866371, 25.081688, -23.875, 25.09375);
((GeneralPath)shape).curveTo(-23.87621, 25.104132, -23.87621, 25.114618, -23.875, 25.125);
((GeneralPath)shape).curveTo(-23.87621, 25.135382, -23.87621, 25.145868, -23.875, 25.15625);
((GeneralPath)shape).lineTo(-23.875, 31.0);
((GeneralPath)shape).curveTo(-23.87621, 31.010382, -23.87621, 31.020868, -23.875, 31.03125);
((GeneralPath)shape).curveTo(-23.87621, 31.041632, -23.87621, 31.052118, -23.875, 31.0625);
((GeneralPath)shape).curveTo(-23.866371, 31.074562, -23.855812, 31.085121, -23.84375, 31.09375);
((GeneralPath)shape).curveTo(-23.835121, 31.105812, -23.824562, 31.116371, -23.8125, 31.125);
((GeneralPath)shape).curveTo(-23.802118, 31.12621, -23.791632, 31.12621, -23.78125, 31.125);
((GeneralPath)shape).curveTo(-23.770868, 31.12621, -23.760382, 31.12621, -23.75, 31.125);
((GeneralPath)shape).lineTo(-17.65625, 31.125);
((GeneralPath)shape).curveTo(-17.645868, 31.12621, -17.635382, 31.12621, -17.625, 31.125);
((GeneralPath)shape).curveTo(-17.614618, 31.12621, -17.604132, 31.12621, -17.59375, 31.125);
((GeneralPath)shape).curveTo(-17.581688, 31.116371, -17.571129, 31.105812, -17.5625, 31.09375);
((GeneralPath)shape).curveTo(-17.550438, 31.085121, -17.539879, 31.074562, -17.53125, 31.0625);
((GeneralPath)shape).curveTo(-17.53004, 31.052118, -17.53004, 31.041632, -17.53125, 31.03125);
((GeneralPath)shape).curveTo(-17.53004, 31.020868, -17.53004, 31.010382, -17.53125, 31.0);
((GeneralPath)shape).lineTo(-17.53125, 25.15625);
((GeneralPath)shape).curveTo(-17.53004, 25.145868, -17.53004, 25.135382, -17.53125, 25.125);
((GeneralPath)shape).curveTo(-17.53004, 25.114618, -17.53004, 25.104132, -17.53125, 25.09375);
((GeneralPath)shape).curveTo(-17.539879, 25.081688, -17.550438, 25.071129, -17.5625, 25.0625);
((GeneralPath)shape).curveTo(-17.571129, 25.050438, -17.581688, 25.039879, -17.59375, 25.03125);
((GeneralPath)shape).curveTo(-17.604132, 25.03004, -17.614618, 25.03004, -17.625, 25.03125);
((GeneralPath)shape).curveTo(-17.635382, 25.03004, -17.645868, 25.03004, -17.65625, 25.03125);
((GeneralPath)shape).lineTo(-23.75, 25.03125);
((GeneralPath)shape).curveTo(-23.760382, 25.03004, -23.770868, 25.03004, -23.78125, 25.03125);
((GeneralPath)shape).curveTo(-23.791632, 25.03004, -23.802118, 25.03004, -23.8125, 25.03125);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
paint = new Color(255, 255, 255, 200);
stroke = new BasicStroke(1.0994728f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(-20.25, 5.875);
((GeneralPath)shape).curveTo(-21.30902, 5.875026, -22.397636, 5.9982357, -23.53125, 6.21875);
((GeneralPath)shape).curveTo(-24.664175, 6.4391785, -25.911411, 6.756263, -27.28125, 7.21875);
((GeneralPath)shape).curveTo(-27.291632, 7.21754, -27.302118, 7.21754, -27.3125, 7.21875);
((GeneralPath)shape).curveTo(-27.324562, 7.227379, -27.335121, 7.237937, -27.34375, 7.25);
((GeneralPath)shape).curveTo(-27.355812, 7.258629, -27.366371, 7.269187, -27.375, 7.28125);
((GeneralPath)shape).curveTo(-27.37621, 7.2916317, -27.37621, 7.302119, -27.375, 7.3125);
((GeneralPath)shape).curveTo(-27.37621, 7.3228817, -27.37621, 7.333369, -27.375, 7.34375);
((GeneralPath)shape).lineTo(-27.375, 12.5);
((GeneralPath)shape).curveTo(-27.37621, 12.510382, -27.37621, 12.520868, -27.375, 12.53125);
((GeneralPath)shape).curveTo(-27.37621, 12.541632, -27.37621, 12.552118, -27.375, 12.5625);
((GeneralPath)shape).curveTo(-27.366371, 12.574563, -27.355812, 12.585121, -27.34375, 12.59375);
((GeneralPath)shape).curveTo(-27.335121, 12.605813, -27.324562, 12.616371, -27.3125, 12.625);
((GeneralPath)shape).curveTo(-27.302118, 12.62621, -27.291632, 12.62621, -27.28125, 12.625);
((GeneralPath)shape).curveTo(-27.270868, 12.62621, -27.260382, 12.62621, -27.25, 12.625);
((GeneralPath)shape).curveTo(-27.239618, 12.62621, -27.229132, 12.62621, -27.21875, 12.625);
((GeneralPath)shape).curveTo(-27.208368, 12.62621, -27.197882, 12.62621, -27.1875, 12.625);
((GeneralPath)shape).curveTo(-26.045061, 11.905957, -24.954147, 11.357862, -23.90625, 11.0);
((GeneralPath)shape).curveTo(-22.858109, 10.631244, -21.863134, 10.437521, -20.96875, 10.4375);
((GeneralPath)shape).curveTo(-20.019531, 10.437521, -19.323826, 10.648045, -18.8125, 11.0625);
((GeneralPath)shape).curveTo(-18.303778, 11.46459, -18.031261, 12.04554, -18.03125, 12.78125);
((GeneralPath)shape).curveTo(-18.03126, 13.261907, -18.175438, 13.73266, -18.46875, 14.21875);
((GeneralPath)shape).curveTo(-18.751741, 14.705766, -19.209015, 15.249245, -19.84375, 15.8125);
((GeneralPath)shape).lineTo(-20.9375, 16.75);
((GeneralPath)shape).curveTo(-22.13896, 17.83049, -22.926743, 18.741022, -23.3125, 19.46875);
((GeneralPath)shape).curveTo(-23.695614, 20.180197, -23.875006, 20.988073, -23.875, 21.90625);
((GeneralPath)shape).lineTo(-23.875, 22.71875);
((GeneralPath)shape).curveTo(-23.87621, 22.729132, -23.87621, 22.739618, -23.875, 22.75);
((GeneralPath)shape).curveTo(-23.87621, 22.760382, -23.87621, 22.770868, -23.875, 22.78125);
((GeneralPath)shape).curveTo(-23.866371, 22.793312, -23.855812, 22.803871, -23.84375, 22.8125);
((GeneralPath)shape).curveTo(-23.835121, 22.824562, -23.824562, 22.835121, -23.8125, 22.84375);
((GeneralPath)shape).curveTo(-23.802118, 22.84496, -23.791632, 22.84496, -23.78125, 22.84375);
((GeneralPath)shape).curveTo(-23.770868, 22.84496, -23.760382, 22.84496, -23.75, 22.84375);
((GeneralPath)shape).lineTo(-17.65625, 22.84375);
((GeneralPath)shape).curveTo(-17.645868, 22.84496, -17.635382, 22.84496, -17.625, 22.84375);
((GeneralPath)shape).curveTo(-17.614618, 22.84496, -17.604132, 22.84496, -17.59375, 22.84375);
((GeneralPath)shape).curveTo(-17.581688, 22.835121, -17.571129, 22.824562, -17.5625, 22.8125);
((GeneralPath)shape).curveTo(-17.550438, 22.803871, -17.539879, 22.793312, -17.53125, 22.78125);
((GeneralPath)shape).curveTo(-17.53004, 22.770868, -17.53004, 22.760382, -17.53125, 22.75);
((GeneralPath)shape).curveTo(-17.53004, 22.739618, -17.53004, 22.729132, -17.53125, 22.71875);
((GeneralPath)shape).lineTo(-17.53125, 21.96875);
((GeneralPath)shape).curveTo(-17.531261, 21.500553, -17.38288, 21.075901, -17.15625, 20.6875);
((GeneralPath)shape).curveTo(-16.933954, 20.296215, -16.448177, 19.73714, -15.6875, 19.0625);
((GeneralPath)shape).lineTo(-14.625, 18.125);
((GeneralPath)shape).curveTo(-13.558412, 17.14269, -12.794341, 16.240347, -12.34375, 15.375);
((GeneralPath)shape).curveTo(-11.894481, 14.500954, -11.656268, 13.50158, -11.65625, 12.40625);
((GeneralPath)shape).curveTo(-11.656268, 10.279985, -12.400019, 8.672222, -13.875, 7.5625);
((GeneralPath)shape).curveTo(-15.350197, 6.441475, -17.48124, 5.875026, -20.25, 5.875);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(-23.8125, 25.03125);
((GeneralPath)shape).curveTo(-23.824562, 25.039879, -23.835121, 25.050438, -23.84375, 25.0625);
((GeneralPath)shape).curveTo(-23.855812, 25.071129, -23.866371, 25.081688, -23.875, 25.09375);
((GeneralPath)shape).curveTo(-23.87621, 25.104132, -23.87621, 25.114618, -23.875, 25.125);
((GeneralPath)shape).curveTo(-23.87621, 25.135382, -23.87621, 25.145868, -23.875, 25.15625);
((GeneralPath)shape).lineTo(-23.875, 31.0);
((GeneralPath)shape).curveTo(-23.87621, 31.010382, -23.87621, 31.020868, -23.875, 31.03125);
((GeneralPath)shape).curveTo(-23.87621, 31.041632, -23.87621, 31.052118, -23.875, 31.0625);
((GeneralPath)shape).curveTo(-23.866371, 31.074562, -23.855812, 31.085121, -23.84375, 31.09375);
((GeneralPath)shape).curveTo(-23.835121, 31.105812, -23.824562, 31.116371, -23.8125, 31.125);
((GeneralPath)shape).curveTo(-23.802118, 31.12621, -23.791632, 31.12621, -23.78125, 31.125);
((GeneralPath)shape).curveTo(-23.770868, 31.12621, -23.760382, 31.12621, -23.75, 31.125);
((GeneralPath)shape).lineTo(-17.65625, 31.125);
((GeneralPath)shape).curveTo(-17.645868, 31.12621, -17.635382, 31.12621, -17.625, 31.125);
((GeneralPath)shape).curveTo(-17.614618, 31.12621, -17.604132, 31.12621, -17.59375, 31.125);
((GeneralPath)shape).curveTo(-17.581688, 31.116371, -17.571129, 31.105812, -17.5625, 31.09375);
((GeneralPath)shape).curveTo(-17.550438, 31.085121, -17.539879, 31.074562, -17.53125, 31.0625);
((GeneralPath)shape).curveTo(-17.53004, 31.052118, -17.53004, 31.041632, -17.53125, 31.03125);
((GeneralPath)shape).curveTo(-17.53004, 31.020868, -17.53004, 31.010382, -17.53125, 31.0);
((GeneralPath)shape).lineTo(-17.53125, 25.15625);
((GeneralPath)shape).curveTo(-17.53004, 25.145868, -17.53004, 25.135382, -17.53125, 25.125);
((GeneralPath)shape).curveTo(-17.53004, 25.114618, -17.53004, 25.104132, -17.53125, 25.09375);
((GeneralPath)shape).curveTo(-17.539879, 25.081688, -17.550438, 25.071129, -17.5625, 25.0625);
((GeneralPath)shape).curveTo(-17.571129, 25.050438, -17.581688, 25.039879, -17.59375, 25.03125);
((GeneralPath)shape).curveTo(-17.604132, 25.03004, -17.614618, 25.03004, -17.625, 25.03125);
((GeneralPath)shape).curveTo(-17.635382, 25.03004, -17.645868, 25.03004, -17.65625, 25.03125);
((GeneralPath)shape).lineTo(-23.75, 25.03125);
((GeneralPath)shape).curveTo(-23.760382, 25.03004, -23.770868, 25.03004, -23.78125, 25.03125);
((GeneralPath)shape).curveTo(-23.791632, 25.03004, -23.802118, 25.03004, -23.8125, 25.03125);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
g.setTransform(defaultTransform__0_0_3);
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
        return 3;
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
		return 46;
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
	public Help_browser() {
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

