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
public class Dialog_information implements Icon, UIResource, IsResizable, IsHiDpiAware {
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
g.transform(new AffineTransform(0.02083333395421505f, 0.0f, 0.0f, 0.02083333395421505f, -0.0f, -0.0f));
// _0
g.setComposite(AlphaComposite.getInstance(3, 0.8f * origAlpha));
AffineTransform defaultTransform__0_0 = g.getTransform();
g.transform(new AffineTransform(1.1971999406814575f, 0.0f, 0.0f, 1.0986000299453735f, -6.201600074768066f, -3.2095000743865967f));
// _0_0
paint = new RadialGradientPaint(new Point2D.Double(14.772000312805176, 74.20999908447266), 7.829f, new Point2D.Double(14.772000312805176, 74.20999908447266), new float[] {0.0f,0.55172f,1.0f}, new Color[] {new Color(0, 0, 0, 131),new Color(0, 0, 0, 37),new Color(0, 0, 0, 0)}, MultipleGradientPaint.CycleMethod.NO_CYCLE, MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(1.764299988746643f, 0.0f, 0.0f, 0.5667999982833862f, 0.0f, 0.0f));
shape = new GeneralPath();
((GeneralPath)shape).moveTo(39.9, 42.1);
((GeneralPath)shape).curveTo(39.9, 44.530052, 33.72153, 46.5, 26.100002, 46.5);
((GeneralPath)shape).curveTo(18.478472, 46.5, 12.300001, 44.530052, 12.300001, 42.1);
((GeneralPath)shape).curveTo(12.300001, 39.669945, 18.478472, 37.699997, 26.1, 37.699997);
((GeneralPath)shape).curveTo(33.72153, 37.699997, 39.9, 39.669945, 39.9, 42.1);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_1 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f));
// _0_1
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_1_0 = g.getTransform();
g.transform(new AffineTransform(1.0757999420166016f, 0.0f, 0.0f, 0.9374899864196777f, -2.551300048828125f, 3.0471999645233154f));
// _0_1_0
paint = new LinearGradientPaint(new Point2D.Double(23.124000549316406, 43.165000915527344), new Point2D.Double(26.47800064086914, 43.165000915527344), new float[] {0.005618f,0.030121f,0.083666f,0.1422f,0.2074f,0.2846f,0.4045f,0.4962f,0.6057f,0.7245f,0.8497f,0.9789f,1.0f}, new Color[] {new Color(104, 104, 104, 255),new Color(119, 119, 119, 255),new Color(146, 146, 146, 255),new Color(167, 167, 167, 255),new Color(182, 182, 182, 255),new Color(190, 190, 190, 255),new Color(193, 193, 193, 255),new Color(188, 188, 188, 255),new Color(173, 173, 173, 255),new Color(149, 149, 149, 255),new Color(116, 116, 116, 255),new Color(73, 73, 73, 255),new Color(65, 65, 65, 255)}, MultipleGradientPaint.CycleMethod.NO_CYCLE, MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(1.6390999555587769f, 0.0f, 0.0f, 1.6390999555587769f, -15.970000267028809f, -29.79400062561035f));
shape = new GeneralPath();
((GeneralPath)shape).moveTo(21.9, 38.9);
((GeneralPath)shape).lineTo(21.9, 40.4);
((GeneralPath)shape).curveTo(21.9, 41.800003, 23.199999, 43.100002, 24.699999, 43.100002);
((GeneralPath)shape).curveTo(26.199999, 43.100002, 27.499998, 41.800003, 27.499998, 40.4);
((GeneralPath)shape).lineTo(27.499998, 38.9);
((GeneralPath)shape).lineTo(21.899998, 38.9);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
paint = new Color(86, 86, 86, 255);
stroke = new BasicStroke(1.0f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(21.9, 38.9);
((GeneralPath)shape).lineTo(21.9, 40.4);
((GeneralPath)shape).curveTo(21.9, 41.800003, 23.199999, 43.100002, 24.699999, 43.100002);
((GeneralPath)shape).curveTo(26.199999, 43.100002, 27.499998, 41.800003, 27.499998, 40.4);
((GeneralPath)shape).lineTo(27.499998, 38.9);
((GeneralPath)shape).lineTo(21.899998, 38.9);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
g.setTransform(defaultTransform__0_1_0);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_1_1 = g.getTransform();
g.transform(new AffineTransform(0.9890699982643127f, 0.0f, 0.0f, 0.9935600161552429f, -0.40874001383781433f, 0.00792049989104271f));
// _0_1_1
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_1_1_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_1_1_0
paint = new Color(174, 174, 87, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(24.5, 27.7);
((GeneralPath)shape).curveTo(21.2, 27.7, 17.5, 28.6, 19.5, 30.5);
((GeneralPath)shape).curveTo(19.0, 30.7, 18.3, 31.1, 18.4, 32.2);
((GeneralPath)shape).curveTo(18.4, 32.7, 19.0, 33.100002, 19.8, 33.3);
((GeneralPath)shape).curveTo(18.9, 34.0, 18.3, 34.6, 18.4, 35.3);
((GeneralPath)shape).curveTo(18.4, 35.8, 19.0, 36.2, 19.8, 36.399998);
((GeneralPath)shape).curveTo(18.9, 37.1, 18.3, 37.8, 18.4, 38.399998);
((GeneralPath)shape).curveTo(18.4, 39.3, 20.5, 40.399998, 24.8, 40.199997);
((GeneralPath)shape).curveTo(28.0, 40.199997, 30.8, 39.6, 31.0, 38.399998);
((GeneralPath)shape).curveTo(31.1, 37.899998, 30.7, 37.499996, 30.1, 37.1);
((GeneralPath)shape).curveTo(30.5, 36.6, 30.9, 36.1, 30.800001, 35.699997);
((GeneralPath)shape).curveTo(30.800001, 35.199997, 30.2, 34.799995, 29.400002, 34.499996);
((GeneralPath)shape).curveTo(30.300001, 33.899998, 30.900002, 33.199997, 30.800001, 32.599995);
((GeneralPath)shape).curveTo(30.800001, 31.999994, 30.2, 31.699995, 29.400002, 31.499994);
((GeneralPath)shape).curveTo(30.300001, 30.799994, 30.900002, 30.099995, 30.800001, 29.499994);
((GeneralPath)shape).curveTo(30.800001, 28.799994, 27.6, 27.699995, 24.5, 27.699995);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
paint = new LinearGradientPaint(new Point2D.Double(24.613000869750977, 31.145999908447266), new Point2D.Double(24.613000869750977, 26.739999771118164), new float[] {0.0f,1.0f}, new Color[] {new Color(76, 76, 40, 255),new Color(76, 76, 40, 0)}, MultipleGradientPaint.CycleMethod.NO_CYCLE, MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(1.0026999711990356f, 0.0f, 0.0f, 0.9973499774932861f, 0.0f, 0.0f));
stroke = new BasicStroke(2.0175f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(24.5, 27.7);
((GeneralPath)shape).curveTo(21.2, 27.7, 17.5, 28.6, 19.5, 30.5);
((GeneralPath)shape).curveTo(19.0, 30.7, 18.3, 31.1, 18.4, 32.2);
((GeneralPath)shape).curveTo(18.4, 32.7, 19.0, 33.100002, 19.8, 33.3);
((GeneralPath)shape).curveTo(18.9, 34.0, 18.3, 34.6, 18.4, 35.3);
((GeneralPath)shape).curveTo(18.4, 35.8, 19.0, 36.2, 19.8, 36.399998);
((GeneralPath)shape).curveTo(18.9, 37.1, 18.3, 37.8, 18.4, 38.399998);
((GeneralPath)shape).curveTo(18.4, 39.3, 20.5, 40.399998, 24.8, 40.199997);
((GeneralPath)shape).curveTo(28.0, 40.199997, 30.8, 39.6, 31.0, 38.399998);
((GeneralPath)shape).curveTo(31.1, 37.899998, 30.7, 37.499996, 30.1, 37.1);
((GeneralPath)shape).curveTo(30.5, 36.6, 30.9, 36.1, 30.800001, 35.699997);
((GeneralPath)shape).curveTo(30.800001, 35.199997, 30.2, 34.799995, 29.400002, 34.499996);
((GeneralPath)shape).curveTo(30.300001, 33.899998, 30.900002, 33.199997, 30.800001, 32.599995);
((GeneralPath)shape).curveTo(30.800001, 31.999994, 30.2, 31.699995, 29.400002, 31.499994);
((GeneralPath)shape).curveTo(30.300001, 30.799994, 30.900002, 30.099995, 30.800001, 29.499994);
((GeneralPath)shape).curveTo(30.800001, 28.799994, 27.6, 27.699995, 24.5, 27.699995);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
g.setTransform(defaultTransform__0_1_1_0);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_1_1_1 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_1_1_1
paint = new LinearGradientPaint(new Point2D.Double(-22.874000549316406, 38.67599868774414), new Point2D.Double(-4.3907999992370605, 38.67599868774414), new float[] {0.005618f,0.020787f,0.066001f,0.1148f,0.1677f,0.2265f,0.2963f,0.4045f,0.5239f,0.6666f,0.8211f,0.9832f,1.0f}, new Color[] {new Color(163, 163, 73, 255),new Color(172, 172, 84, 255),new Color(193, 193, 114, 255),new Color(212, 214, 142, 255),new Color(226, 228, 166, 255),new Color(237, 240, 184, 255),new Color(243, 246, 195, 255),new Color(245, 248, 199, 255),new Color(238, 240, 190, 255),new Color(219, 221, 169, 255),new Color(190, 189, 136, 255),new Color(152, 149, 100, 255),new Color(148, 145, 96, 255)}, MultipleGradientPaint.CycleMethod.NO_CYCLE, MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(0.5666199922561646f, 0.029890000820159912f, -0.11856000125408173f, 0.6565399765968323f, 36.185001373291016f, 20.08300018310547f));
shape = new GeneralPath();
((GeneralPath)shape).moveTo(30.9, 38.3);
((GeneralPath)shape).curveTo(30.699999, 39.5, 27.6, 40.2, 22.599998, 40.0);
((GeneralPath)shape).curveTo(19.499998, 39.8, 19.3, 38.9, 19.499998, 37.7);
((GeneralPath)shape).curveTo(19.699997, 36.5, 22.499998, 35.7, 25.599998, 35.8);
((GeneralPath)shape).curveTo(28.8, 36.0, 31.099998, 37.1, 30.899998, 38.3);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
paint = new LinearGradientPaint(new Point2D.Double(-10.480999946594238, 39.034000396728516), new Point2D.Double(-23.85099983215332, 39.143001556396484), new float[] {0.0f,0.26471f,0.63235f,1.0f}, new Color[] {new Color(146, 148, 112, 255),new Color(252, 255, 193, 255),new Color(243, 245, 186, 255),new Color(146, 148, 112, 255)}, MultipleGradientPaint.CycleMethod.NO_CYCLE, MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(0.5666199922561646f, 0.029890000820159912f, -0.11856000125408173f, 0.6565399765968323f, 36.185001373291016f, 20.08300018310547f));
stroke = new BasicStroke(0.089063f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(30.9, 38.3);
((GeneralPath)shape).curveTo(30.699999, 39.5, 27.6, 40.2, 22.599998, 40.0);
((GeneralPath)shape).curveTo(19.499998, 39.8, 19.3, 38.9, 19.499998, 37.7);
((GeneralPath)shape).curveTo(19.699997, 36.5, 22.499998, 35.7, 25.599998, 35.8);
((GeneralPath)shape).curveTo(28.8, 36.0, 31.099998, 37.1, 30.899998, 38.3);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
g.setTransform(defaultTransform__0_1_1_1);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_1_1_2 = g.getTransform();
g.transform(new AffineTransform(0.6027399897575378f, -0.1286199986934662f, 0.06428399682044983f, 0.7607899904251099f, 31.1200008392334f, 14.491000175476074f));
// _0_1_1_2
paint = new LinearGradientPaint(new Point2D.Double(-22.874000549316406, 38.67599868774414), new Point2D.Double(-4.3907999992370605, 38.67599868774414), new float[] {0.005618f,0.020787f,0.066001f,0.1148f,0.1677f,0.2265f,0.2963f,0.4045f,0.5239f,0.6666f,0.8211f,0.9832f,1.0f}, new Color[] {new Color(163, 163, 73, 255),new Color(172, 172, 84, 255),new Color(193, 193, 114, 255),new Color(212, 214, 142, 255),new Color(226, 228, 166, 255),new Color(237, 240, 184, 255),new Color(243, 246, 195, 255),new Color(245, 248, 199, 255),new Color(238, 240, 190, 255),new Color(219, 221, 169, 255),new Color(190, 189, 136, 255),new Color(152, 149, 100, 255),new Color(148, 145, 96, 255)}, MultipleGradientPaint.CycleMethod.NO_CYCLE, MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(1.0263999700546265f, 0.0f, 0.0f, 0.9742299914360046f, 0.0f, 0.0f));
shape = new GeneralPath();
((GeneralPath)shape).moveTo(-3.5, 27.2);
((GeneralPath)shape).curveTo(-3.5, 29.031387, -8.133853, 30.51602, -13.85, 30.51602);
((GeneralPath)shape).curveTo(-19.566147, 30.51602, -24.2, 29.031387, -24.2, 27.2);
((GeneralPath)shape).curveTo(-24.2, 25.368614, -19.566147, 23.883982, -13.85, 23.883982);
((GeneralPath)shape).curveTo(-8.133853, 23.883982, -3.5, 25.368614, -3.5, 27.2);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
paint = new LinearGradientPaint(new Point2D.Double(-10.480999946594238, 39.034000396728516), new Point2D.Double(-23.85099983215332, 39.143001556396484), new float[] {0.0f,0.26471f,0.63235f,1.0f}, new Color[] {new Color(146, 148, 112, 255),new Color(252, 255, 193, 255),new Color(243, 245, 186, 255),new Color(146, 148, 112, 255)}, MultipleGradientPaint.CycleMethod.NO_CYCLE, MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(1.0263999700546265f, 0.0f, 0.0f, 0.9742299914360046f, 0.0f, 0.0f));
stroke = new BasicStroke(0.13035f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(-3.5, 27.2);
((GeneralPath)shape).curveTo(-3.5, 29.031387, -8.133853, 30.51602, -13.85, 30.51602);
((GeneralPath)shape).curveTo(-19.566147, 30.51602, -24.2, 29.031387, -24.2, 27.2);
((GeneralPath)shape).curveTo(-24.2, 25.368614, -19.566147, 23.883982, -13.85, 23.883982);
((GeneralPath)shape).curveTo(-8.133853, 23.883982, -3.5, 25.368614, -3.5, 27.2);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
g.setTransform(defaultTransform__0_1_1_2);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_1_1_3 = g.getTransform();
g.transform(new AffineTransform(0.6027399897575378f, -0.1286199986934662f, 0.06428399682044983f, 0.7607899904251099f, 31.1200008392334f, 11.395999908447266f));
// _0_1_1_3
paint = new LinearGradientPaint(new Point2D.Double(-22.874000549316406, 38.67599868774414), new Point2D.Double(-4.3907999992370605, 38.67599868774414), new float[] {0.005618f,0.020787f,0.066001f,0.1148f,0.1677f,0.2265f,0.2963f,0.4045f,0.5239f,0.6666f,0.8211f,0.9832f,1.0f}, new Color[] {new Color(163, 163, 73, 255),new Color(172, 172, 84, 255),new Color(193, 193, 114, 255),new Color(212, 214, 142, 255),new Color(226, 228, 166, 255),new Color(237, 240, 184, 255),new Color(243, 246, 195, 255),new Color(245, 248, 199, 255),new Color(238, 240, 190, 255),new Color(219, 221, 169, 255),new Color(190, 189, 136, 255),new Color(152, 149, 100, 255),new Color(148, 145, 96, 255)}, MultipleGradientPaint.CycleMethod.NO_CYCLE, MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(1.0263999700546265f, 0.0f, 0.0f, 0.9742299914360046f, 0.0f, 0.0f));
shape = new GeneralPath();
((GeneralPath)shape).moveTo(-3.5, 27.2);
((GeneralPath)shape).curveTo(-3.5, 29.031387, -8.133853, 30.51602, -13.85, 30.51602);
((GeneralPath)shape).curveTo(-19.566147, 30.51602, -24.2, 29.031387, -24.2, 27.2);
((GeneralPath)shape).curveTo(-24.2, 25.368614, -19.566147, 23.883982, -13.85, 23.883982);
((GeneralPath)shape).curveTo(-8.133853, 23.883982, -3.5, 25.368614, -3.5, 27.2);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
paint = new LinearGradientPaint(new Point2D.Double(-10.480999946594238, 39.034000396728516), new Point2D.Double(-23.85099983215332, 39.143001556396484), new float[] {0.0f,0.26471f,0.63235f,1.0f}, new Color[] {new Color(146, 148, 112, 255),new Color(252, 255, 193, 255),new Color(243, 245, 186, 255),new Color(146, 148, 112, 255)}, MultipleGradientPaint.CycleMethod.NO_CYCLE, MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(1.0263999700546265f, 0.0f, 0.0f, 0.9742299914360046f, 0.0f, 0.0f));
stroke = new BasicStroke(0.13035f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(-3.5, 27.2);
((GeneralPath)shape).curveTo(-3.5, 29.031387, -8.133853, 30.51602, -13.85, 30.51602);
((GeneralPath)shape).curveTo(-19.566147, 30.51602, -24.2, 29.031387, -24.2, 27.2);
((GeneralPath)shape).curveTo(-24.2, 25.368614, -19.566147, 23.883982, -13.85, 23.883982);
((GeneralPath)shape).curveTo(-8.133853, 23.883982, -3.5, 25.368614, -3.5, 27.2);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
g.setTransform(defaultTransform__0_1_1_3);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_1_1_4 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_1_1_4
paint = new LinearGradientPaint(new Point2D.Double(-22.874000549316406, 38.67599868774414), new Point2D.Double(-4.3907999992370605, 38.67599868774414), new float[] {0.005618f,0.020787f,0.066001f,0.1148f,0.1677f,0.2265f,0.2963f,0.4045f,0.5239f,0.6666f,0.8211f,0.9832f,1.0f}, new Color[] {new Color(163, 163, 73, 255),new Color(172, 172, 84, 255),new Color(193, 193, 114, 255),new Color(212, 214, 142, 255),new Color(226, 228, 166, 255),new Color(237, 240, 184, 255),new Color(243, 246, 195, 255),new Color(245, 248, 199, 255),new Color(238, 240, 190, 255),new Color(219, 221, 169, 255),new Color(190, 189, 136, 255),new Color(152, 149, 100, 255),new Color(148, 145, 96, 255)}, MultipleGradientPaint.CycleMethod.NO_CYCLE, MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(0.6186800003051758f, -0.13202999532222748f, 0.06262700259685516f, 0.7411800026893616f, 31.1200008392334f, 8.300399780273438f));
shape = new GeneralPath();
((GeneralPath)shape).moveTo(30.7, 29.6);
((GeneralPath)shape).curveTo(30.7, 31.0, 28.2, 32.6, 24.7, 33.3);
((GeneralPath)shape).curveTo(21.300001, 34.0, 18.400002, 33.5, 18.300001, 32.1);
((GeneralPath)shape).curveTo(18.2, 30.8, 20.500002, 29.199999, 24.0, 28.999998);
((GeneralPath)shape).curveTo(27.4, 28.799997, 30.7, 28.899998, 30.7, 29.599998);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
paint = new LinearGradientPaint(new Point2D.Double(-10.480999946594238, 39.034000396728516), new Point2D.Double(-23.85099983215332, 39.143001556396484), new float[] {0.0f,0.26471f,0.63235f,1.0f}, new Color[] {new Color(146, 148, 112, 255),new Color(252, 255, 193, 255),new Color(243, 245, 186, 255),new Color(146, 148, 112, 255)}, MultipleGradientPaint.CycleMethod.NO_CYCLE, MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(0.6186800003051758f, -0.13202999532222748f, 0.06262700259685516f, 0.7411800026893616f, 31.1200008392334f, 8.300399780273438f));
stroke = new BasicStroke(0.089063f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(30.7, 29.6);
((GeneralPath)shape).curveTo(30.7, 31.0, 28.2, 32.6, 24.7, 33.3);
((GeneralPath)shape).curveTo(21.300001, 34.0, 18.400002, 33.5, 18.300001, 32.1);
((GeneralPath)shape).curveTo(18.2, 30.8, 20.500002, 29.199999, 24.0, 28.999998);
((GeneralPath)shape).curveTo(27.4, 28.799997, 30.7, 28.899998, 30.7, 29.599998);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
g.setTransform(defaultTransform__0_1_1_4);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_1_1_5 = g.getTransform();
g.transform(new AffineTransform(0.33546000719070435f, 0.0f, 0.0f, 0.33546000719070435f, 11.746999740600586f, 27.22599983215332f));
// _0_1_1_5
paint = new Color(255, 255, 255, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(31.0, 22.4);
((GeneralPath)shape).curveTo(31.228405, 23.69713, 30.66363, 25.005863, 29.563164, 25.729527);
((GeneralPath)shape).curveTo(28.462698, 26.453192, 27.037302, 26.453192, 25.936836, 25.729527);
((GeneralPath)shape).curveTo(24.83637, 25.005863, 24.271595, 23.69713, 24.5, 22.4);
((GeneralPath)shape).curveTo(24.271595, 21.102869, 24.83637, 19.794136, 25.936836, 19.070473);
((GeneralPath)shape).curveTo(27.037302, 18.346807, 28.462698, 18.346807, 29.563164, 19.070473);
((GeneralPath)shape).curveTo(30.66363, 19.794136, 31.228405, 21.102869, 31.0, 22.4);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_1_1_5);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_1_1_6 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_1_1_6
paint = new Color(0, 0, 0, 60);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(19.3, 33.4);
((GeneralPath)shape).curveTo(22.699999, 33.9, 26.3, 33.300003, 29.199999, 31.500002);
((GeneralPath)shape).curveTo(29.999998, 30.900002, 30.099998, 30.300001, 30.499998, 29.900002);
((GeneralPath)shape).curveTo(29.099998, 31.000002, 25.499998, 34.0, 19.3, 33.4);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_1_1_6);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_1_1_7 = g.getTransform();
g.transform(new AffineTransform(0.33546000719070435f, 0.0f, 0.0f, 0.33546000719070435f, 11.746999740600586f, 30.233999252319336f));
// _0_1_1_7
paint = new Color(255, 255, 255, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(31.0, 22.4);
((GeneralPath)shape).curveTo(31.228405, 23.69713, 30.66363, 25.005863, 29.563164, 25.729527);
((GeneralPath)shape).curveTo(28.462698, 26.453192, 27.037302, 26.453192, 25.936836, 25.729527);
((GeneralPath)shape).curveTo(24.83637, 25.005863, 24.271595, 23.69713, 24.5, 22.4);
((GeneralPath)shape).curveTo(24.271595, 21.102869, 24.83637, 19.794136, 25.936836, 19.070473);
((GeneralPath)shape).curveTo(27.037302, 18.346807, 28.462698, 18.346807, 29.563164, 19.070473);
((GeneralPath)shape).curveTo(30.66363, 19.794136, 31.228405, 21.102869, 31.0, 22.4);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_1_1_7);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_1_1_8 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_1_1_8
paint = new Color(0, 0, 0, 60);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(19.5, 39.5);
((GeneralPath)shape).curveTo(22.9, 40.0, 26.4, 39.5, 29.3, 37.6);
((GeneralPath)shape).curveTo(30.199999, 37.1, 30.3, 36.5, 30.599998, 36.0);
((GeneralPath)shape).curveTo(29.199999, 37.1, 25.599998, 40.2, 19.499998, 39.5);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_1_1_8);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_1_1_9 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_1_1_9
paint = new Color(0, 0, 0, 60);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(19.5, 36.4);
((GeneralPath)shape).curveTo(22.9, 36.9, 26.5, 36.4, 29.4, 34.5);
((GeneralPath)shape).curveTo(30.199999, 34.0, 30.3, 33.4, 30.699999, 32.9);
((GeneralPath)shape).curveTo(29.199999, 34.0, 25.599998, 37.100002, 19.5, 36.4);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_1_1_9);
g.setTransform(defaultTransform__0_1_1);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_1_2 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, -0.9887999892234802f, 0.0f));
// _0_1_2
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_1_2_0 = g.getTransform();
g.transform(new AffineTransform(0.9544399976730347f, 0.0f, 0.0f, 0.989870011806488f, 1.4332000017166138f, 0.6398800015449524f));
// _0_1_2_0
paint = new LinearGradientPaint(new Point2D.Double(17.8799991607666, 55.362998962402344), new Point2D.Double(11.906000137329102, 54.862998962402344), new float[] {0.0f,1.0f}, new Color[] {new Color(214, 215, 165, 255),new Color(142, 143, 109, 255)}, MultipleGradientPaint.CycleMethod.NO_CYCLE, MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(1.6033999919891357f, 0.0f, 0.0f, 0.5493999719619751f, 0.6141700148582458f, 0.024498000741004944f));
shape = new GeneralPath();
((GeneralPath)shape).moveTo(18.9, 29.6);
((GeneralPath)shape).curveTo(18.9, 28.800001, 20.4, 27.9, 24.4, 27.9);
((GeneralPath)shape).curveTo(28.1, 28.0, 30.5, 28.8, 30.5, 30.1);
((GeneralPath)shape).curveTo(30.5, 31.300001, 27.3, 32.2, 23.9, 32.0);
((GeneralPath)shape).curveTo(20.4, 31.8, 18.9, 30.9, 18.9, 29.6);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
paint = new LinearGradientPaint(new Point2D.Double(-29.006999969482422, -29.798999786376953), new Point2D.Double(-37.64099884033203, -29.597999572753906), new float[] {0.0f,0.26471f,0.63235f,1.0f}, new Color[] {new Color(146, 148, 112, 255),new Color(96, 97, 74, 255),new Color(243, 245, 186, 255),new Color(146, 148, 112, 255)}, MultipleGradientPaint.CycleMethod.NO_CYCLE, MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(-0.9057300090789795f, -0.0438620001077652f, 0.18951000273227692f, -0.9634400010108948f, 0.6141700148582458f, 0.024498000741004944f));
stroke = new BasicStroke(0.090833f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(18.9, 29.6);
((GeneralPath)shape).curveTo(18.9, 28.800001, 20.4, 27.9, 24.4, 27.9);
((GeneralPath)shape).curveTo(28.1, 28.0, 30.5, 28.8, 30.5, 30.1);
((GeneralPath)shape).curveTo(30.5, 31.300001, 27.3, 32.2, 23.9, 32.0);
((GeneralPath)shape).curveTo(20.4, 31.8, 18.9, 30.9, 18.9, 29.6);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
g.setTransform(defaultTransform__0_1_2_0);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_1_2_1 = g.getTransform();
g.transform(new AffineTransform(0.9544399976730347f, 0.0f, 0.0f, 0.989870011806488f, 1.4332000017166138f, 0.6398800015449524f));
// _0_1_2_1
paint = new RadialGradientPaint(new Point2D.Double(68.13800048828125, 29.868999481201172), 33.934f, new Point2D.Double(68.13800048828125, 29.868999481201172), new float[] {0.0f,0.882f,1.0f}, new Color[] {new Color(255, 255, 255, 45),new Color(112, 154, 200, 255),new Color(111, 150, 221, 255)}, MultipleGradientPaint.CycleMethod.NO_CYCLE, MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(0.551289975643158f, 1.2655999691426272E-16f, -1.3557000548821492E-16f, 0.7660300135612488f, -10.487000465393066f, 3.5143001079559326f));
shape = new GeneralPath();
((GeneralPath)shape).moveTo(24.7, 0.9);
((GeneralPath)shape).curveTo(16.900002, 0.9, 10.500001, 6.8, 10.500001, 14.2);
((GeneralPath)shape).curveTo(10.500001, 21.7, 16.2, 22.599998, 16.2, 25.4);
((GeneralPath)shape).curveTo(16.2, 28.6, 19.6, 32.3, 25.1, 32.2);
((GeneralPath)shape).curveTo(31.0, 32.0, 33.5, 28.800001, 33.5, 25.400002);
((GeneralPath)shape).curveTo(33.5, 22.400002, 38.9, 22.300001, 38.9, 14.200002);
((GeneralPath)shape).curveTo(38.9, 6.8000016, 32.5, 0.9000015, 24.7, 0.9000015);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
paint = new Color(97, 100, 113, 255);
stroke = new BasicStroke(1.016f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(24.7, 0.9);
((GeneralPath)shape).curveTo(16.900002, 0.9, 10.500001, 6.8, 10.500001, 14.2);
((GeneralPath)shape).curveTo(10.500001, 21.7, 16.2, 22.599998, 16.2, 25.4);
((GeneralPath)shape).curveTo(16.2, 28.6, 19.6, 32.3, 25.1, 32.2);
((GeneralPath)shape).curveTo(31.0, 32.0, 33.5, 28.800001, 33.5, 25.400002);
((GeneralPath)shape).curveTo(33.5, 22.400002, 38.9, 22.300001, 38.9, 14.200002);
((GeneralPath)shape).curveTo(38.9, 6.8000016, 32.5, 0.9000015, 24.7, 0.9000015);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
g.setTransform(defaultTransform__0_1_2_1);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_1_2_2 = g.getTransform();
g.transform(new AffineTransform(0.9544399976730347f, 0.0f, 0.0f, 0.989870011806488f, 1.4332000017166138f, 0.6398800015449524f));
// _0_1_2_2
paint = new LinearGradientPaint(new Point2D.Double(37.939998626708984, 16.652000427246094), new Point2D.Double(-5.251699924468994, 3.8557000160217285), new float[] {0.0f,1.0f}, new Color[] {new Color(241, 243, 255, 255),new Color(241, 243, 255, 0)}, MultipleGradientPaint.CycleMethod.NO_CYCLE, MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(0.8941299915313721f, 0.0f, 0.0f, 0.985230028629303f, 1.5160000324249268f, 0.024498000741004944f));
stroke = new BasicStroke(0.94686f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(24.7, 1.9);
((GeneralPath)shape).curveTo(17.400002, 1.9, 11.500001, 7.5, 11.500001, 14.4);
((GeneralPath)shape).curveTo(11.500001, 21.4, 16.800001, 22.2, 16.800001, 24.8);
((GeneralPath)shape).curveTo(16.800001, 27.8, 20.000002, 31.199999, 25.100002, 31.099998);
((GeneralPath)shape).curveTo(30.600002, 30.999998, 32.9, 27.999998, 32.9, 24.8);
((GeneralPath)shape).curveTo(32.9, 22.0, 37.9, 21.9, 37.9, 14.4);
((GeneralPath)shape).curveTo(37.9, 7.4999995, 32.0, 1.8999996, 24.7, 1.8999996);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
g.setTransform(defaultTransform__0_1_2_2);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_1_2_3 = g.getTransform();
g.transform(new AffineTransform(0.9375f, 0.0f, 0.0f, 0.9269400238990784f, 0.5692200064659119f, 0.25176000595092773f));
// _0_1_2_3
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_1_2_3_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_1_2_3_0
paint = new LinearGradientPaint(new Point2D.Double(30.6200008392334, 10.314000129699707), new Point2D.Double(32.16600036621094, 18.163000106811523), new float[] {0.0f,0.41176f,1.0f}, new Color[] {new Color(255, 255, 255, 255),new Color(255, 255, 255, 113),new Color(0, 0, 0, 122)}, MultipleGradientPaint.CycleMethod.NO_CYCLE, MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(-0.6292999982833862f, 0.0f, 0.0f, 1.5891000032424927f, 50.6879997253418f, 3.8043999671936035f));
shape = new GeneralPath();
((GeneralPath)shape).moveTo(31.9, 19.2);
((GeneralPath)shape).curveTo(32.3, 19.300001, 32.5, 19.6, 32.4, 20.0);
((GeneralPath)shape).lineTo(28.500002, 31.5);
((GeneralPath)shape).curveTo(28.400002, 31.8, 28.100002, 32.0, 27.800001, 31.9);
((GeneralPath)shape).curveTo(27.5, 31.8, 27.300001, 31.5, 27.400002, 31.199999);
((GeneralPath)shape).lineTo(31.2, 19.599998);
((GeneralPath)shape).curveTo(31.300001, 19.3, 31.6, 19.099998, 31.900002, 19.199999);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_1_2_3_0);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_1_2_3_1 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_1_2_3_1
paint = new LinearGradientPaint(new Point2D.Double(30.6200008392334, 10.314000129699707), new Point2D.Double(32.16600036621094, 18.163000106811523), new float[] {0.0f,0.41176f,1.0f}, new Color[] {new Color(255, 255, 255, 255),new Color(255, 255, 255, 113),new Color(0, 0, 0, 122)}, MultipleGradientPaint.CycleMethod.NO_CYCLE, MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(0.6292999982833862f, 0.0f, 0.0f, 1.5891000032424927f, 1.4115999937057495f, 3.9293999671936035f));
shape = new GeneralPath();
((GeneralPath)shape).moveTo(20.2, 19.3);
((GeneralPath)shape).curveTo(19.800001, 19.5, 19.6, 19.8, 19.7, 20.099998);
((GeneralPath)shape).lineTo(23.6, 31.599998);
((GeneralPath)shape).curveTo(23.7, 31.999998, 24.0, 32.199997, 24.300001, 32.1);
((GeneralPath)shape).curveTo(24.600002, 32.0, 24.800001, 31.599998, 24.7, 31.3);
((GeneralPath)shape).lineTo(20.900002, 19.8);
((GeneralPath)shape).curveTo(20.800001, 19.5, 20.500002, 19.199999, 20.2, 19.3);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_1_2_3_1);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_1_2_3_2 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_1_2_3_2
paint = new Color(255, 255, 255, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(20.3, 19.3);
((GeneralPath)shape).curveTo(20.0, 19.3, 19.8, 19.5, 19.8, 19.8);
((GeneralPath)shape).curveTo(19.699999, 20.0, 19.9, 20.199999, 20.099998, 20.4);
((GeneralPath)shape).curveTo(20.099998, 20.4, 21.899998, 21.4, 24.3, 22.0);
((GeneralPath)shape).curveTo(26.699999, 22.5, 29.8, 22.6, 32.1, 20.3);
((GeneralPath)shape).curveTo(32.3, 20.199999, 32.399998, 19.9, 32.3, 19.699999);
((GeneralPath)shape).curveTo(32.3, 19.499998, 32.1, 19.3, 31.9, 19.3);
((GeneralPath)shape).curveTo(31.699999, 19.199999, 31.5, 19.3, 31.3, 19.5);
((GeneralPath)shape).curveTo(29.4, 21.4, 26.8, 21.4, 24.599998, 20.9);
((GeneralPath)shape).curveTo(22.399998, 20.4, 20.699999, 19.4, 20.699999, 19.4);
((GeneralPath)shape).curveTo(20.499998, 19.3, 20.4, 19.3, 20.3, 19.3);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
paint = new LinearGradientPaint(new Point2D.Double(14.63700008392334, 31.503999710083008), new Point2D.Double(9.364800453186035, 32.250999450683594), new float[] {0.0f,1.0f}, new Color[] {new Color(163, 163, 163, 255),new Color(181, 181, 181, 0)}, MultipleGradientPaint.CycleMethod.NO_CYCLE, MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(1.9851000308990479f, 0.0f, 0.0f, 0.5037599802017212f, 1.7865999937057495f, 4.5543999671936035f));
stroke = new BasicStroke(0.21455f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(20.3, 19.3);
((GeneralPath)shape).curveTo(20.0, 19.3, 19.8, 19.5, 19.8, 19.8);
((GeneralPath)shape).curveTo(19.699999, 20.0, 19.9, 20.199999, 20.099998, 20.4);
((GeneralPath)shape).curveTo(20.099998, 20.4, 21.899998, 21.4, 24.3, 22.0);
((GeneralPath)shape).curveTo(26.699999, 22.5, 29.8, 22.6, 32.1, 20.3);
((GeneralPath)shape).curveTo(32.3, 20.199999, 32.399998, 19.9, 32.3, 19.699999);
((GeneralPath)shape).curveTo(32.3, 19.499998, 32.1, 19.3, 31.9, 19.3);
((GeneralPath)shape).curveTo(31.699999, 19.199999, 31.5, 19.3, 31.3, 19.5);
((GeneralPath)shape).curveTo(29.4, 21.4, 26.8, 21.4, 24.599998, 20.9);
((GeneralPath)shape).curveTo(22.399998, 20.4, 20.699999, 19.4, 20.699999, 19.4);
((GeneralPath)shape).curveTo(20.499998, 19.3, 20.4, 19.3, 20.3, 19.3);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
g.setTransform(defaultTransform__0_1_2_3_2);
g.setTransform(defaultTransform__0_1_2_3);
g.setComposite(AlphaComposite.getInstance(3, 0.59777f * origAlpha));
AffineTransform defaultTransform__0_1_2_4 = g.getTransform();
g.transform(new AffineTransform(0.9544399976730347f, 0.0f, 0.0f, 0.989870011806488f, 1.4332000017166138f, 0.6398800015449524f));
// _0_1_2_4
paint = new LinearGradientPaint(new Point2D.Double(16.999000549316406, 10.060999870300293), new Point2D.Double(32.09700012207031, 36.72600173950195), new float[] {0.0f,1.0f}, new Color[] {new Color(255, 255, 255, 255),new Color(255, 255, 255, 0)}, MultipleGradientPaint.CycleMethod.NO_CYCLE, MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(1.1404999494552612f, 0.0f, 0.0f, 0.9259999990463257f, 0.27232998609542847f, -3.2472000122070312f));
shape = new GeneralPath();
((GeneralPath)shape).moveTo(25.0, 3.6);
((GeneralPath)shape).curveTo(18.7, 3.6, 13.7, 7.6, 13.7, 12.5);
((GeneralPath)shape).curveTo(13.7, 14.5, 14.599999, 16.3, 16.0, 17.7);
((GeneralPath)shape).curveTo(17.6, 18.400002, 19.2, 18.800001, 21.1, 18.800001);
((GeneralPath)shape).curveTo(27.400002, 18.800001, 32.5, 14.800001, 32.5, 9.800001);
((GeneralPath)shape).curveTo(32.5, 7.900001, 31.5, 6.1000013, 30.1, 4.6000013);
((GeneralPath)shape).curveTo(28.5, 4.0000014, 26.9, 3.6000013, 25.0, 3.6000013);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_1_2_4);
g.setTransform(defaultTransform__0_1_2);
g.setTransform(defaultTransform__0_1);
g.setTransform(defaultTransform__0);
g.setTransform(defaultTransform_);

	}

    /**
     * Returns the X of the bounding box of the original SVG image.
     * 
     * @return The X of the bounding box of the original SVG image.
     */
    public static int getOrigX() {
        return 1;
    }

    /**
     * Returns the Y of the bounding box of the original SVG image.
     * 
     * @return The Y of the bounding box of the original SVG image.
     */
    public static int getOrigY() {
        return 1;
    }

	/**
	 * Returns the width of the bounding box of the original SVG image.
	 * 
	 * @return The width of the bounding box of the original SVG image.
	 */
	public static int getOrigWidth() {
		return 1;
	}

	/**
	 * Returns the height of the bounding box of the original SVG image.
	 * 
	 * @return The height of the bounding box of the original SVG image.
	 */
	public static int getOrigHeight() {
		return 1;
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
	public Dialog_information() {
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

