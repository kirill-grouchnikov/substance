package test.check.svg.flags;

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
public class us implements Icon, UIResource, IsResizable, IsHiDpiAware {
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
g.transform(new AffineTransform(0.9375f, 0.0f, 0.0f, 0.9375f, 0.0f, 0.0f));
// _0_0
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_0_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_0
paint = new Color(189, 61, 68, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(0.0, 0.0);
((GeneralPath)shape).lineTo(972.81, 0.0);
((GeneralPath)shape).lineTo(972.81, 39.385);
((GeneralPath)shape).lineTo(0.0, 39.385);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(0.0, 78.77);
((GeneralPath)shape).lineTo(972.81, 78.77);
((GeneralPath)shape).lineTo(972.81, 118.155);
((GeneralPath)shape).lineTo(0.0, 118.155);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(0.0, 157.54);
((GeneralPath)shape).lineTo(972.81, 157.54);
((GeneralPath)shape).lineTo(972.81, 196.92499);
((GeneralPath)shape).lineTo(0.0, 196.92499);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(0.0, 236.31);
((GeneralPath)shape).lineTo(972.81, 236.31);
((GeneralPath)shape).lineTo(972.81, 275.695);
((GeneralPath)shape).lineTo(0.0, 275.695);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(0.0, 315.08);
((GeneralPath)shape).lineTo(972.81, 315.08);
((GeneralPath)shape).lineTo(972.81, 354.465);
((GeneralPath)shape).lineTo(0.0, 354.465);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(0.0, 393.84998);
((GeneralPath)shape).lineTo(972.81, 393.84998);
((GeneralPath)shape).lineTo(972.81, 433.235);
((GeneralPath)shape).lineTo(0.0, 433.235);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(0.0, 472.61996);
((GeneralPath)shape).lineTo(972.81, 472.61996);
((GeneralPath)shape).lineTo(972.81, 512.00494);
((GeneralPath)shape).lineTo(0.0, 512.00494);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_0_0);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_0_1 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0_1
paint = new Color(255, 255, 255, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(0.0, 39.385);
((GeneralPath)shape).lineTo(972.81, 39.385);
((GeneralPath)shape).lineTo(972.81, 78.77);
((GeneralPath)shape).lineTo(0.0, 78.77);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(0.0, 118.155);
((GeneralPath)shape).lineTo(972.81, 118.155);
((GeneralPath)shape).lineTo(972.81, 157.54);
((GeneralPath)shape).lineTo(0.0, 157.54);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(0.0, 196.92499);
((GeneralPath)shape).lineTo(972.81, 196.92499);
((GeneralPath)shape).lineTo(972.81, 236.30998);
((GeneralPath)shape).lineTo(0.0, 236.30998);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(0.0, 275.69498);
((GeneralPath)shape).lineTo(972.81, 275.69498);
((GeneralPath)shape).lineTo(972.81, 315.08);
((GeneralPath)shape).lineTo(0.0, 315.08);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(0.0, 354.46497);
((GeneralPath)shape).lineTo(972.81, 354.46497);
((GeneralPath)shape).lineTo(972.81, 393.84998);
((GeneralPath)shape).lineTo(0.0, 393.84998);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(0.0, 433.23495);
((GeneralPath)shape).lineTo(972.81, 433.23495);
((GeneralPath)shape).lineTo(972.81, 472.61996);
((GeneralPath)shape).lineTo(0.0, 472.61996);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_0_1);
g.setTransform(defaultTransform__0_0_0);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_1 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_1
paint = new Color(25, 47, 93, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(0.0, 0.0);
((GeneralPath)shape).lineTo(389.12, 0.0);
((GeneralPath)shape).lineTo(389.12, 275.69);
((GeneralPath)shape).lineTo(0.0, 275.69);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_1);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_2 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_2
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_2_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_2_0
paint = new Color(255, 255, 255, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(32.427, 11.8);
((GeneralPath)shape).lineTo(35.967, 22.696);
((GeneralPath)shape).lineTo(47.425, 22.696);
((GeneralPath)shape).lineTo(38.155, 29.431);
((GeneralPath)shape).lineTo(41.696, 40.327);
((GeneralPath)shape).lineTo(32.426, 33.593);
((GeneralPath)shape).lineTo(23.157999, 40.327);
((GeneralPath)shape).lineTo(26.697998, 29.431);
((GeneralPath)shape).lineTo(17.428997, 22.696);
((GeneralPath)shape).lineTo(28.885998, 22.696);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(97.28, 11.8);
((GeneralPath)shape).lineTo(100.821, 22.696);
((GeneralPath)shape).lineTo(112.279, 22.696);
((GeneralPath)shape).lineTo(103.009, 29.431);
((GeneralPath)shape).lineTo(106.55, 40.327);
((GeneralPath)shape).lineTo(97.28, 33.593);
((GeneralPath)shape).lineTo(88.012, 40.327);
((GeneralPath)shape).lineTo(91.552, 29.431);
((GeneralPath)shape).lineTo(82.283005, 22.696);
((GeneralPath)shape).lineTo(93.74, 22.696);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(162.136, 11.8);
((GeneralPath)shape).lineTo(165.676, 22.696);
((GeneralPath)shape).lineTo(177.134, 22.696);
((GeneralPath)shape).lineTo(167.864, 29.431);
((GeneralPath)shape).lineTo(171.405, 40.327);
((GeneralPath)shape).lineTo(162.136, 33.593);
((GeneralPath)shape).lineTo(152.867, 40.327);
((GeneralPath)shape).lineTo(156.407, 29.431);
((GeneralPath)shape).lineTo(147.138, 22.696);
((GeneralPath)shape).lineTo(158.59601, 22.696);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(226.988, 11.8);
((GeneralPath)shape).lineTo(230.528, 22.696);
((GeneralPath)shape).lineTo(241.985, 22.696);
((GeneralPath)shape).lineTo(232.716, 29.431);
((GeneralPath)shape).lineTo(236.256, 40.327);
((GeneralPath)shape).lineTo(226.98799, 33.593);
((GeneralPath)shape).lineTo(217.71799, 40.327);
((GeneralPath)shape).lineTo(221.25899, 29.431);
((GeneralPath)shape).lineTo(211.98898, 22.696);
((GeneralPath)shape).lineTo(223.44699, 22.696);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(291.84302, 11.8);
((GeneralPath)shape).lineTo(295.38303, 22.696);
((GeneralPath)shape).lineTo(306.84103, 22.696);
((GeneralPath)shape).lineTo(297.57104, 29.431);
((GeneralPath)shape).lineTo(301.11203, 40.327);
((GeneralPath)shape).lineTo(291.84204, 33.593);
((GeneralPath)shape).lineTo(282.57404, 40.327);
((GeneralPath)shape).lineTo(286.11404, 29.431);
((GeneralPath)shape).lineTo(276.84503, 22.696);
((GeneralPath)shape).lineTo(288.30203, 22.696);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(356.69803, 11.8);
((GeneralPath)shape).lineTo(360.23804, 22.696);
((GeneralPath)shape).lineTo(371.69604, 22.696);
((GeneralPath)shape).lineTo(362.42606, 29.431);
((GeneralPath)shape).lineTo(365.96704, 40.327);
((GeneralPath)shape).lineTo(356.69803, 33.593);
((GeneralPath)shape).lineTo(347.42804, 40.327);
((GeneralPath)shape).lineTo(350.97003, 29.431);
((GeneralPath)shape).lineTo(341.70004, 22.696);
((GeneralPath)shape).lineTo(353.15805, 22.696);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(64.855, 39.37);
((GeneralPath)shape).lineTo(68.395004, 50.266);
((GeneralPath)shape).lineTo(79.853004, 50.266);
((GeneralPath)shape).lineTo(70.583, 57.0);
((GeneralPath)shape).lineTo(74.125, 67.897);
((GeneralPath)shape).lineTo(64.854996, 61.163002);
((GeneralPath)shape).lineTo(55.585995, 67.897);
((GeneralPath)shape).lineTo(59.126, 57.0);
((GeneralPath)shape).lineTo(49.857, 50.266);
((GeneralPath)shape).lineTo(61.315, 50.266);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(129.707, 39.37);
((GeneralPath)shape).lineTo(133.247, 50.266);
((GeneralPath)shape).lineTo(144.704, 50.266);
((GeneralPath)shape).lineTo(135.435, 57.0);
((GeneralPath)shape).lineTo(138.97499, 67.897);
((GeneralPath)shape).lineTo(129.70699, 61.163002);
((GeneralPath)shape).lineTo(120.43698, 67.897);
((GeneralPath)shape).lineTo(123.978, 57.0);
((GeneralPath)shape).lineTo(114.70799, 50.266);
((GeneralPath)shape).lineTo(126.16599, 50.266);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(194.56201, 39.37);
((GeneralPath)shape).lineTo(198.102, 50.266);
((GeneralPath)shape).lineTo(209.56, 50.266);
((GeneralPath)shape).lineTo(200.29, 57.0);
((GeneralPath)shape).lineTo(203.831, 67.897);
((GeneralPath)shape).lineTo(194.56099, 61.163002);
((GeneralPath)shape).lineTo(185.29298, 67.897);
((GeneralPath)shape).lineTo(188.833, 57.0);
((GeneralPath)shape).lineTo(179.564, 50.266);
((GeneralPath)shape).lineTo(191.021, 50.266);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(259.41702, 39.37);
((GeneralPath)shape).lineTo(262.95703, 50.266);
((GeneralPath)shape).lineTo(274.41504, 50.266);
((GeneralPath)shape).lineTo(265.145, 57.0);
((GeneralPath)shape).lineTo(268.68597, 67.897);
((GeneralPath)shape).lineTo(259.41696, 61.163002);
((GeneralPath)shape).lineTo(250.14696, 67.897);
((GeneralPath)shape).lineTo(253.69, 57.0);
((GeneralPath)shape).lineTo(244.42, 50.266);
((GeneralPath)shape).lineTo(255.87799, 50.266);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(324.269, 39.37);
((GeneralPath)shape).lineTo(327.80902, 50.266);
((GeneralPath)shape).lineTo(339.26602, 50.266);
((GeneralPath)shape).lineTo(329.997, 57.0);
((GeneralPath)shape).lineTo(333.53702, 67.897);
((GeneralPath)shape).lineTo(324.269, 61.163002);
((GeneralPath)shape).lineTo(314.99902, 67.897);
((GeneralPath)shape).lineTo(318.54, 57.0);
((GeneralPath)shape).lineTo(309.27002, 50.266);
((GeneralPath)shape).lineTo(320.72803, 50.266);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(32.427, 66.939);
((GeneralPath)shape).lineTo(35.967, 77.83501);
((GeneralPath)shape).lineTo(47.425, 77.83501);
((GeneralPath)shape).lineTo(38.155, 84.57001);
((GeneralPath)shape).lineTo(41.696, 95.466);
((GeneralPath)shape).lineTo(32.426, 88.732);
((GeneralPath)shape).lineTo(23.157999, 95.466);
((GeneralPath)shape).lineTo(26.697998, 84.57001);
((GeneralPath)shape).lineTo(17.428997, 77.83501);
((GeneralPath)shape).lineTo(28.885998, 77.83501);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(97.28, 66.939);
((GeneralPath)shape).lineTo(100.821, 77.83501);
((GeneralPath)shape).lineTo(112.279, 77.83501);
((GeneralPath)shape).lineTo(103.009, 84.57001);
((GeneralPath)shape).lineTo(106.55, 95.466);
((GeneralPath)shape).lineTo(97.28, 88.732);
((GeneralPath)shape).lineTo(88.012, 95.466);
((GeneralPath)shape).lineTo(91.552, 84.57001);
((GeneralPath)shape).lineTo(82.283005, 77.83501);
((GeneralPath)shape).lineTo(93.74, 77.83501);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(162.136, 66.939);
((GeneralPath)shape).lineTo(165.676, 77.83501);
((GeneralPath)shape).lineTo(177.134, 77.83501);
((GeneralPath)shape).lineTo(167.864, 84.57001);
((GeneralPath)shape).lineTo(171.405, 95.466);
((GeneralPath)shape).lineTo(162.136, 88.732);
((GeneralPath)shape).lineTo(152.867, 95.466);
((GeneralPath)shape).lineTo(156.407, 84.57001);
((GeneralPath)shape).lineTo(147.138, 77.83501);
((GeneralPath)shape).lineTo(158.59601, 77.83501);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(226.988, 66.939);
((GeneralPath)shape).lineTo(230.528, 77.83501);
((GeneralPath)shape).lineTo(241.985, 77.83501);
((GeneralPath)shape).lineTo(232.716, 84.57001);
((GeneralPath)shape).lineTo(236.256, 95.466);
((GeneralPath)shape).lineTo(226.98799, 88.732);
((GeneralPath)shape).lineTo(217.71799, 95.466);
((GeneralPath)shape).lineTo(221.25899, 84.57001);
((GeneralPath)shape).lineTo(211.98898, 77.83501);
((GeneralPath)shape).lineTo(223.44699, 77.83501);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(291.84302, 66.939);
((GeneralPath)shape).lineTo(295.38303, 77.83501);
((GeneralPath)shape).lineTo(306.84103, 77.83501);
((GeneralPath)shape).lineTo(297.57104, 84.57001);
((GeneralPath)shape).lineTo(301.11203, 95.466);
((GeneralPath)shape).lineTo(291.84204, 88.732);
((GeneralPath)shape).lineTo(282.57404, 95.466);
((GeneralPath)shape).lineTo(286.11404, 84.57001);
((GeneralPath)shape).lineTo(276.84503, 77.83501);
((GeneralPath)shape).lineTo(288.30203, 77.83501);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(356.69803, 66.939);
((GeneralPath)shape).lineTo(360.23804, 77.83501);
((GeneralPath)shape).lineTo(371.69604, 77.83501);
((GeneralPath)shape).lineTo(362.42606, 84.57001);
((GeneralPath)shape).lineTo(365.96704, 95.466);
((GeneralPath)shape).lineTo(356.69803, 88.732);
((GeneralPath)shape).lineTo(347.42804, 95.466);
((GeneralPath)shape).lineTo(350.97003, 84.57001);
((GeneralPath)shape).lineTo(341.70004, 77.83501);
((GeneralPath)shape).lineTo(353.15805, 77.83501);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(64.855, 94.508);
((GeneralPath)shape).lineTo(68.395004, 105.40501);
((GeneralPath)shape).lineTo(79.853004, 105.40501);
((GeneralPath)shape).lineTo(70.58301, 112.13901);
((GeneralPath)shape).lineTo(74.12501, 123.03601);
((GeneralPath)shape).lineTo(64.85501, 116.30201);
((GeneralPath)shape).lineTo(55.58601, 123.03601);
((GeneralPath)shape).lineTo(59.12601, 112.13901);
((GeneralPath)shape).lineTo(49.85701, 105.40501);
((GeneralPath)shape).lineTo(61.31501, 105.40501);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(129.707, 94.508);
((GeneralPath)shape).lineTo(133.247, 105.40501);
((GeneralPath)shape).lineTo(144.704, 105.40501);
((GeneralPath)shape).lineTo(135.435, 112.13901);
((GeneralPath)shape).lineTo(138.97499, 123.03601);
((GeneralPath)shape).lineTo(129.70699, 116.30201);
((GeneralPath)shape).lineTo(120.43698, 123.03601);
((GeneralPath)shape).lineTo(123.97798, 112.13901);
((GeneralPath)shape).lineTo(114.70798, 105.40501);
((GeneralPath)shape).lineTo(126.16598, 105.40501);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(194.56201, 94.508);
((GeneralPath)shape).lineTo(198.102, 105.40501);
((GeneralPath)shape).lineTo(209.56, 105.40501);
((GeneralPath)shape).lineTo(200.29, 112.13901);
((GeneralPath)shape).lineTo(203.831, 123.03601);
((GeneralPath)shape).lineTo(194.56099, 116.30201);
((GeneralPath)shape).lineTo(185.29298, 123.03601);
((GeneralPath)shape).lineTo(188.83298, 112.13901);
((GeneralPath)shape).lineTo(179.56398, 105.40501);
((GeneralPath)shape).lineTo(191.02098, 105.40501);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(259.41702, 94.508);
((GeneralPath)shape).lineTo(262.95703, 105.40501);
((GeneralPath)shape).lineTo(274.41504, 105.40501);
((GeneralPath)shape).lineTo(265.14505, 112.13901);
((GeneralPath)shape).lineTo(268.68604, 123.03601);
((GeneralPath)shape).lineTo(259.41702, 116.30201);
((GeneralPath)shape).lineTo(250.14702, 123.03601);
((GeneralPath)shape).lineTo(253.68903, 112.13901);
((GeneralPath)shape).lineTo(244.41902, 105.40501);
((GeneralPath)shape).lineTo(255.87701, 105.40501);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(324.269, 94.508);
((GeneralPath)shape).lineTo(327.80902, 105.40501);
((GeneralPath)shape).lineTo(339.26602, 105.40501);
((GeneralPath)shape).lineTo(329.997, 112.13901);
((GeneralPath)shape).lineTo(333.53702, 123.03601);
((GeneralPath)shape).lineTo(324.269, 116.30201);
((GeneralPath)shape).lineTo(314.99902, 123.03601);
((GeneralPath)shape).lineTo(318.54, 112.13901);
((GeneralPath)shape).lineTo(309.27002, 105.40501);
((GeneralPath)shape).lineTo(320.72803, 105.40501);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(32.427, 122.078);
((GeneralPath)shape).lineTo(35.967, 132.974);
((GeneralPath)shape).lineTo(47.425, 132.974);
((GeneralPath)shape).lineTo(38.155, 139.709);
((GeneralPath)shape).lineTo(41.696, 150.605);
((GeneralPath)shape).lineTo(32.426, 143.871);
((GeneralPath)shape).lineTo(23.157999, 150.605);
((GeneralPath)shape).lineTo(26.697998, 139.709);
((GeneralPath)shape).lineTo(17.428997, 132.974);
((GeneralPath)shape).lineTo(28.885998, 132.974);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(97.28, 122.078);
((GeneralPath)shape).lineTo(100.821, 132.974);
((GeneralPath)shape).lineTo(112.279, 132.974);
((GeneralPath)shape).lineTo(103.009, 139.709);
((GeneralPath)shape).lineTo(106.55, 150.605);
((GeneralPath)shape).lineTo(97.28, 143.871);
((GeneralPath)shape).lineTo(88.012, 150.605);
((GeneralPath)shape).lineTo(91.552, 139.709);
((GeneralPath)shape).lineTo(82.283005, 132.974);
((GeneralPath)shape).lineTo(93.74, 132.974);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(162.136, 122.078);
((GeneralPath)shape).lineTo(165.676, 132.974);
((GeneralPath)shape).lineTo(177.134, 132.974);
((GeneralPath)shape).lineTo(167.864, 139.709);
((GeneralPath)shape).lineTo(171.405, 150.605);
((GeneralPath)shape).lineTo(162.136, 143.871);
((GeneralPath)shape).lineTo(152.867, 150.605);
((GeneralPath)shape).lineTo(156.407, 139.709);
((GeneralPath)shape).lineTo(147.138, 132.974);
((GeneralPath)shape).lineTo(158.59601, 132.974);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(226.988, 122.078);
((GeneralPath)shape).lineTo(230.528, 132.974);
((GeneralPath)shape).lineTo(241.985, 132.974);
((GeneralPath)shape).lineTo(232.716, 139.709);
((GeneralPath)shape).lineTo(236.256, 150.605);
((GeneralPath)shape).lineTo(226.98799, 143.871);
((GeneralPath)shape).lineTo(217.71799, 150.605);
((GeneralPath)shape).lineTo(221.25899, 139.709);
((GeneralPath)shape).lineTo(211.98898, 132.974);
((GeneralPath)shape).lineTo(223.44699, 132.974);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(291.84302, 122.078);
((GeneralPath)shape).lineTo(295.38303, 132.974);
((GeneralPath)shape).lineTo(306.84103, 132.974);
((GeneralPath)shape).lineTo(297.57104, 139.709);
((GeneralPath)shape).lineTo(301.11203, 150.605);
((GeneralPath)shape).lineTo(291.84204, 143.871);
((GeneralPath)shape).lineTo(282.57404, 150.605);
((GeneralPath)shape).lineTo(286.11404, 139.709);
((GeneralPath)shape).lineTo(276.84503, 132.974);
((GeneralPath)shape).lineTo(288.30203, 132.974);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(356.69803, 122.078);
((GeneralPath)shape).lineTo(360.23804, 132.974);
((GeneralPath)shape).lineTo(371.69604, 132.974);
((GeneralPath)shape).lineTo(362.42606, 139.709);
((GeneralPath)shape).lineTo(365.96704, 150.605);
((GeneralPath)shape).lineTo(356.69803, 143.871);
((GeneralPath)shape).lineTo(347.42804, 150.605);
((GeneralPath)shape).lineTo(350.97003, 139.709);
((GeneralPath)shape).lineTo(341.70004, 132.974);
((GeneralPath)shape).lineTo(353.15805, 132.974);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(64.855, 149.647);
((GeneralPath)shape).lineTo(68.395004, 160.544);
((GeneralPath)shape).lineTo(79.853004, 160.544);
((GeneralPath)shape).lineTo(70.58301, 167.278);
((GeneralPath)shape).lineTo(74.12501, 178.175);
((GeneralPath)shape).lineTo(64.85501, 171.44101);
((GeneralPath)shape).lineTo(55.58601, 178.175);
((GeneralPath)shape).lineTo(59.12601, 167.278);
((GeneralPath)shape).lineTo(49.85701, 160.544);
((GeneralPath)shape).lineTo(61.31501, 160.544);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(129.707, 149.647);
((GeneralPath)shape).lineTo(133.247, 160.544);
((GeneralPath)shape).lineTo(144.704, 160.544);
((GeneralPath)shape).lineTo(135.435, 167.278);
((GeneralPath)shape).lineTo(138.97499, 178.175);
((GeneralPath)shape).lineTo(129.70699, 171.44101);
((GeneralPath)shape).lineTo(120.43698, 178.175);
((GeneralPath)shape).lineTo(123.97798, 167.278);
((GeneralPath)shape).lineTo(114.70798, 160.544);
((GeneralPath)shape).lineTo(126.16598, 160.544);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(194.56201, 149.647);
((GeneralPath)shape).lineTo(198.102, 160.544);
((GeneralPath)shape).lineTo(209.56, 160.544);
((GeneralPath)shape).lineTo(200.29, 167.278);
((GeneralPath)shape).lineTo(203.831, 178.175);
((GeneralPath)shape).lineTo(194.56099, 171.44101);
((GeneralPath)shape).lineTo(185.29298, 178.175);
((GeneralPath)shape).lineTo(188.83298, 167.278);
((GeneralPath)shape).lineTo(179.56398, 160.544);
((GeneralPath)shape).lineTo(191.02098, 160.544);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(259.41702, 149.647);
((GeneralPath)shape).lineTo(262.95703, 160.544);
((GeneralPath)shape).lineTo(274.41504, 160.544);
((GeneralPath)shape).lineTo(265.14505, 167.278);
((GeneralPath)shape).lineTo(268.68604, 178.175);
((GeneralPath)shape).lineTo(259.41702, 171.44101);
((GeneralPath)shape).lineTo(250.14702, 178.175);
((GeneralPath)shape).lineTo(253.68903, 167.278);
((GeneralPath)shape).lineTo(244.41902, 160.544);
((GeneralPath)shape).lineTo(255.87701, 160.544);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(324.269, 149.647);
((GeneralPath)shape).lineTo(327.80902, 160.544);
((GeneralPath)shape).lineTo(339.26602, 160.544);
((GeneralPath)shape).lineTo(329.997, 167.278);
((GeneralPath)shape).lineTo(333.53702, 178.175);
((GeneralPath)shape).lineTo(324.269, 171.44101);
((GeneralPath)shape).lineTo(314.99902, 178.175);
((GeneralPath)shape).lineTo(318.54, 167.278);
((GeneralPath)shape).lineTo(309.27002, 160.544);
((GeneralPath)shape).lineTo(320.72803, 160.544);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_2_0);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_2_1 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_2_1
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_2_1_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_2_1_0
paint = new Color(255, 255, 255, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(32.427, 177.217);
((GeneralPath)shape).lineTo(35.967, 188.11299);
((GeneralPath)shape).lineTo(47.425, 188.11299);
((GeneralPath)shape).lineTo(38.155, 194.84799);
((GeneralPath)shape).lineTo(41.696, 205.74399);
((GeneralPath)shape).lineTo(32.426, 199.01);
((GeneralPath)shape).lineTo(23.157999, 205.74399);
((GeneralPath)shape).lineTo(26.697998, 194.84799);
((GeneralPath)shape).lineTo(17.428997, 188.11299);
((GeneralPath)shape).lineTo(28.885998, 188.11299);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(97.28, 177.217);
((GeneralPath)shape).lineTo(100.821, 188.11299);
((GeneralPath)shape).lineTo(112.279, 188.11299);
((GeneralPath)shape).lineTo(103.009, 194.84799);
((GeneralPath)shape).lineTo(106.55, 205.74399);
((GeneralPath)shape).lineTo(97.28, 199.01);
((GeneralPath)shape).lineTo(88.012, 205.74399);
((GeneralPath)shape).lineTo(91.552, 194.84799);
((GeneralPath)shape).lineTo(82.283005, 188.11299);
((GeneralPath)shape).lineTo(93.74, 188.11299);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(162.136, 177.217);
((GeneralPath)shape).lineTo(165.676, 188.11299);
((GeneralPath)shape).lineTo(177.134, 188.11299);
((GeneralPath)shape).lineTo(167.864, 194.84799);
((GeneralPath)shape).lineTo(171.405, 205.74399);
((GeneralPath)shape).lineTo(162.136, 199.01);
((GeneralPath)shape).lineTo(152.867, 205.74399);
((GeneralPath)shape).lineTo(156.407, 194.84799);
((GeneralPath)shape).lineTo(147.138, 188.11299);
((GeneralPath)shape).lineTo(158.59601, 188.11299);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(226.988, 177.217);
((GeneralPath)shape).lineTo(230.528, 188.11299);
((GeneralPath)shape).lineTo(241.985, 188.11299);
((GeneralPath)shape).lineTo(232.716, 194.84799);
((GeneralPath)shape).lineTo(236.256, 205.74399);
((GeneralPath)shape).lineTo(226.98799, 199.01);
((GeneralPath)shape).lineTo(217.71799, 205.74399);
((GeneralPath)shape).lineTo(221.25899, 194.84799);
((GeneralPath)shape).lineTo(211.98898, 188.11299);
((GeneralPath)shape).lineTo(223.44699, 188.11299);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(291.84302, 177.217);
((GeneralPath)shape).lineTo(295.38303, 188.11299);
((GeneralPath)shape).lineTo(306.84103, 188.11299);
((GeneralPath)shape).lineTo(297.57104, 194.84799);
((GeneralPath)shape).lineTo(301.11203, 205.74399);
((GeneralPath)shape).lineTo(291.84204, 199.01);
((GeneralPath)shape).lineTo(282.57404, 205.74399);
((GeneralPath)shape).lineTo(286.11404, 194.84799);
((GeneralPath)shape).lineTo(276.84503, 188.11299);
((GeneralPath)shape).lineTo(288.30203, 188.11299);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(356.69803, 177.217);
((GeneralPath)shape).lineTo(360.23804, 188.11299);
((GeneralPath)shape).lineTo(371.69604, 188.11299);
((GeneralPath)shape).lineTo(362.42606, 194.84799);
((GeneralPath)shape).lineTo(365.96704, 205.74399);
((GeneralPath)shape).lineTo(356.69803, 199.01);
((GeneralPath)shape).lineTo(347.42804, 205.74399);
((GeneralPath)shape).lineTo(350.97003, 194.84799);
((GeneralPath)shape).lineTo(341.70004, 188.11299);
((GeneralPath)shape).lineTo(353.15805, 188.11299);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(64.855, 204.786);
((GeneralPath)shape).lineTo(68.395004, 215.683);
((GeneralPath)shape).lineTo(79.853004, 215.683);
((GeneralPath)shape).lineTo(70.58301, 222.41699);
((GeneralPath)shape).lineTo(74.12501, 233.314);
((GeneralPath)shape).lineTo(64.85501, 226.58);
((GeneralPath)shape).lineTo(55.58601, 233.314);
((GeneralPath)shape).lineTo(59.12601, 222.41699);
((GeneralPath)shape).lineTo(49.85701, 215.683);
((GeneralPath)shape).lineTo(61.31501, 215.683);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(129.707, 204.786);
((GeneralPath)shape).lineTo(133.247, 215.683);
((GeneralPath)shape).lineTo(144.704, 215.683);
((GeneralPath)shape).lineTo(135.435, 222.41699);
((GeneralPath)shape).lineTo(138.97499, 233.314);
((GeneralPath)shape).lineTo(129.70699, 226.58);
((GeneralPath)shape).lineTo(120.43698, 233.314);
((GeneralPath)shape).lineTo(123.97798, 222.41699);
((GeneralPath)shape).lineTo(114.70798, 215.683);
((GeneralPath)shape).lineTo(126.16598, 215.683);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(194.56201, 204.786);
((GeneralPath)shape).lineTo(198.102, 215.683);
((GeneralPath)shape).lineTo(209.56, 215.683);
((GeneralPath)shape).lineTo(200.29, 222.41699);
((GeneralPath)shape).lineTo(203.831, 233.314);
((GeneralPath)shape).lineTo(194.56099, 226.58);
((GeneralPath)shape).lineTo(185.29298, 233.314);
((GeneralPath)shape).lineTo(188.83298, 222.41699);
((GeneralPath)shape).lineTo(179.56398, 215.683);
((GeneralPath)shape).lineTo(191.02098, 215.683);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(259.41702, 204.786);
((GeneralPath)shape).lineTo(262.95703, 215.683);
((GeneralPath)shape).lineTo(274.41504, 215.683);
((GeneralPath)shape).lineTo(265.14505, 222.41699);
((GeneralPath)shape).lineTo(268.68604, 233.314);
((GeneralPath)shape).lineTo(259.41702, 226.58);
((GeneralPath)shape).lineTo(250.14702, 233.314);
((GeneralPath)shape).lineTo(253.68903, 222.41699);
((GeneralPath)shape).lineTo(244.41902, 215.683);
((GeneralPath)shape).lineTo(255.87701, 215.683);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(324.269, 204.786);
((GeneralPath)shape).lineTo(327.80902, 215.683);
((GeneralPath)shape).lineTo(339.26602, 215.683);
((GeneralPath)shape).lineTo(329.997, 222.41699);
((GeneralPath)shape).lineTo(333.53702, 233.314);
((GeneralPath)shape).lineTo(324.269, 226.58);
((GeneralPath)shape).lineTo(314.99902, 233.314);
((GeneralPath)shape).lineTo(318.54, 222.41699);
((GeneralPath)shape).lineTo(309.27002, 215.683);
((GeneralPath)shape).lineTo(320.72803, 215.683);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_2_1_0);
g.setTransform(defaultTransform__0_0_2_1);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_2_2 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_2_2
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_2_2_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_2_2_0
paint = new Color(255, 255, 255, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(32.427, 232.356);
((GeneralPath)shape).lineTo(35.967, 243.252);
((GeneralPath)shape).lineTo(47.425, 243.252);
((GeneralPath)shape).lineTo(38.155, 249.987);
((GeneralPath)shape).lineTo(41.696, 260.883);
((GeneralPath)shape).lineTo(32.426, 254.149);
((GeneralPath)shape).lineTo(23.157999, 260.883);
((GeneralPath)shape).lineTo(26.697998, 249.987);
((GeneralPath)shape).lineTo(17.428997, 243.252);
((GeneralPath)shape).lineTo(28.885998, 243.252);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(97.28, 232.356);
((GeneralPath)shape).lineTo(100.821, 243.252);
((GeneralPath)shape).lineTo(112.279, 243.252);
((GeneralPath)shape).lineTo(103.009, 249.987);
((GeneralPath)shape).lineTo(106.55, 260.883);
((GeneralPath)shape).lineTo(97.28, 254.149);
((GeneralPath)shape).lineTo(88.012, 260.883);
((GeneralPath)shape).lineTo(91.552, 249.987);
((GeneralPath)shape).lineTo(82.283005, 243.252);
((GeneralPath)shape).lineTo(93.74, 243.252);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(162.136, 232.356);
((GeneralPath)shape).lineTo(165.676, 243.252);
((GeneralPath)shape).lineTo(177.134, 243.252);
((GeneralPath)shape).lineTo(167.864, 249.987);
((GeneralPath)shape).lineTo(171.405, 260.883);
((GeneralPath)shape).lineTo(162.136, 254.149);
((GeneralPath)shape).lineTo(152.867, 260.883);
((GeneralPath)shape).lineTo(156.407, 249.987);
((GeneralPath)shape).lineTo(147.138, 243.252);
((GeneralPath)shape).lineTo(158.59601, 243.252);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(226.988, 232.356);
((GeneralPath)shape).lineTo(230.528, 243.252);
((GeneralPath)shape).lineTo(241.985, 243.252);
((GeneralPath)shape).lineTo(232.716, 249.987);
((GeneralPath)shape).lineTo(236.256, 260.883);
((GeneralPath)shape).lineTo(226.98799, 254.149);
((GeneralPath)shape).lineTo(217.71799, 260.883);
((GeneralPath)shape).lineTo(221.25899, 249.987);
((GeneralPath)shape).lineTo(211.98898, 243.252);
((GeneralPath)shape).lineTo(223.44699, 243.252);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(291.84302, 232.356);
((GeneralPath)shape).lineTo(295.38303, 243.252);
((GeneralPath)shape).lineTo(306.84103, 243.252);
((GeneralPath)shape).lineTo(297.57104, 249.987);
((GeneralPath)shape).lineTo(301.11203, 260.883);
((GeneralPath)shape).lineTo(291.84204, 254.149);
((GeneralPath)shape).lineTo(282.57404, 260.883);
((GeneralPath)shape).lineTo(286.11404, 249.987);
((GeneralPath)shape).lineTo(276.84503, 243.252);
((GeneralPath)shape).lineTo(288.30203, 243.252);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(356.69803, 232.356);
((GeneralPath)shape).lineTo(360.23804, 243.252);
((GeneralPath)shape).lineTo(371.69604, 243.252);
((GeneralPath)shape).lineTo(362.42606, 249.987);
((GeneralPath)shape).lineTo(365.96704, 260.883);
((GeneralPath)shape).lineTo(356.69803, 254.149);
((GeneralPath)shape).lineTo(347.42804, 260.883);
((GeneralPath)shape).lineTo(350.97003, 249.987);
((GeneralPath)shape).lineTo(341.70004, 243.252);
((GeneralPath)shape).lineTo(353.15805, 243.252);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_2_2_0);
g.setTransform(defaultTransform__0_0_2_2);
g.setTransform(defaultTransform__0_0_2);
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
	public us() {
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

