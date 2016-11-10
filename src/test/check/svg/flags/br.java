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
public class br implements Icon, UIResource, IsResizable, IsHiDpiAware {
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
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0
paint = new Color(34, 158, 69, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(0.0, 0.0);
((GeneralPath)shape).lineTo(640.0, 0.0);
((GeneralPath)shape).lineTo(640.0, 480.0);
((GeneralPath)shape).lineTo(0.0, 480.0);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_0);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_1 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_1
paint = new Color(248, 229, 9, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(321.406, 435.935);
((GeneralPath)shape).lineTo(622.88904, 240.265);
((GeneralPath)shape).lineTo(319.58102, 44.065002);
((GeneralPath)shape).lineTo(17.11, 240.734);
((GeneralPath)shape).lineTo(321.406, 435.934);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_1);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_2 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_2
paint = new Color(43, 73, 163, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(452.77, 240.005);
((GeneralPath)shape).curveTo(452.77, 310.333, 395.667, 367.345, 325.22598, 367.345);
((GeneralPath)shape).curveTo(254.78398, 367.345, 197.68198, 310.333, 197.68198, 240.005);
((GeneralPath)shape).curveTo(197.68198, 169.677, 254.78598, 112.66501, 325.22598, 112.66501);
((GeneralPath)shape).curveTo(395.66797, 112.66501, 452.771, 169.677, 452.771, 240.005);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_2);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_3 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_3
paint = new Color(255, 255, 239, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(283.3, 316.274);
((GeneralPath)shape).lineTo(279.357, 314.0);
((GeneralPath)shape).lineTo(275.264, 316.025);
((GeneralPath)shape).lineTo(276.181, 311.475);
((GeneralPath)shape).lineTo(273.019, 308.143);
((GeneralPath)shape).lineTo(277.539, 307.613);
((GeneralPath)shape).lineTo(279.663, 303.53302);
((GeneralPath)shape).lineTo(281.557, 307.75302);
((GeneralPath)shape).lineTo(286.017, 308.56302);
((GeneralPath)shape).lineTo(282.672, 311.69302);
((GeneralPath)shape).moveTo(368.77, 337.91702);
((GeneralPath)shape).lineTo(364.83, 335.64304);
((GeneralPath)shape).lineTo(360.73798, 337.66803);
((GeneralPath)shape).lineTo(361.65396, 333.11804);
((GeneralPath)shape).lineTo(358.49396, 329.78604);
((GeneralPath)shape).lineTo(363.01395, 329.25604);
((GeneralPath)shape).lineTo(365.13596, 325.17606);
((GeneralPath)shape).lineTo(367.02997, 329.39606);
((GeneralPath)shape).lineTo(371.48996, 330.20605);
((GeneralPath)shape).lineTo(368.14496, 333.33606);
((GeneralPath)shape).moveTo(331.92996, 303.34305);
((GeneralPath)shape).lineTo(328.52597, 301.37906);
((GeneralPath)shape).lineTo(324.98996, 303.12704);
((GeneralPath)shape).lineTo(325.78195, 299.19705);
((GeneralPath)shape).lineTo(323.05194, 296.31705);
((GeneralPath)shape).lineTo(326.95593, 295.86005);
((GeneralPath)shape).lineTo(328.78995, 292.33704);
((GeneralPath)shape).lineTo(330.42593, 295.98203);
((GeneralPath)shape).lineTo(334.27893, 296.68204);
((GeneralPath)shape).lineTo(331.38892, 299.38702);
((GeneralPath)shape).moveTo(418.2539, 290.91003);
((GeneralPath)shape).lineTo(414.9119, 288.98203);
((GeneralPath)shape).lineTo(411.4399, 290.7);
((GeneralPath)shape).lineTo(412.21692, 286.842);
((GeneralPath)shape).lineTo(409.53693, 284.015);
((GeneralPath)shape).lineTo(413.36993, 283.565);
((GeneralPath)shape).lineTo(415.16992, 280.105);
((GeneralPath)shape).lineTo(416.77692, 283.685);
((GeneralPath)shape).lineTo(420.5599, 284.371);
((GeneralPath)shape).lineTo(417.7229, 287.028);
((GeneralPath)shape).moveTo(330.37, 265.03);
((GeneralPath)shape).lineTo(326.43, 262.757);
((GeneralPath)shape).lineTo(322.337, 264.78198);
((GeneralPath)shape).lineTo(323.253, 260.232);
((GeneralPath)shape).lineTo(320.091, 256.9);
((GeneralPath)shape).lineTo(324.613, 256.37);
((GeneralPath)shape).lineTo(326.736, 252.29);
((GeneralPath)shape).lineTo(328.63, 256.50998);
((GeneralPath)shape).lineTo(333.09, 257.31998);
((GeneralPath)shape).lineTo(329.744, 260.44998);
((GeneralPath)shape).moveTo(225.13, 225.52);
((GeneralPath)shape).lineTo(221.19, 223.246);
((GeneralPath)shape).lineTo(217.09601, 225.271);
((GeneralPath)shape).lineTo(218.01201, 220.72299);
((GeneralPath)shape).lineTo(214.852, 217.39);
((GeneralPath)shape).lineTo(219.37201, 216.86);
((GeneralPath)shape).lineTo(221.494, 212.78);
((GeneralPath)shape).lineTo(223.388, 217.0);
((GeneralPath)shape).lineTo(227.848, 217.81);
((GeneralPath)shape).lineTo(224.503, 220.94);
((GeneralPath)shape).moveTo(237.78601, 278.08002);
((GeneralPath)shape).lineTo(233.84601, 275.80502);
((GeneralPath)shape).lineTo(229.75201, 277.83002);
((GeneralPath)shape).lineTo(230.66801, 273.282);
((GeneralPath)shape).lineTo(227.50801, 269.948);
((GeneralPath)shape).lineTo(232.02802, 269.418);
((GeneralPath)shape).lineTo(234.15102, 265.338);
((GeneralPath)shape).lineTo(236.04501, 269.558);
((GeneralPath)shape).lineTo(240.50502, 270.368);
((GeneralPath)shape).lineTo(237.16002, 273.5);
((GeneralPath)shape).moveTo(369.114, 206.17);
((GeneralPath)shape).lineTo(365.634, 204.163);
((GeneralPath)shape).lineTo(362.018, 205.95099);
((GeneralPath)shape).lineTo(362.828, 201.93399);
((GeneralPath)shape).lineTo(360.034, 198.98999);
((GeneralPath)shape).lineTo(364.02798, 198.51999);
((GeneralPath)shape).lineTo(365.90298, 194.91699);
((GeneralPath)shape).lineTo(367.576, 198.64499);
((GeneralPath)shape).lineTo(371.516, 199.35999);
((GeneralPath)shape).lineTo(368.561, 202.12599);
((GeneralPath)shape).moveTo(361.896, 240.366);
((GeneralPath)shape).lineTo(359.156, 238.784);
((GeneralPath)shape).lineTo(356.306, 240.192);
((GeneralPath)shape).lineTo(356.946, 237.028);
((GeneralPath)shape).lineTo(354.746, 234.708);
((GeneralPath)shape).lineTo(357.891, 234.34);
((GeneralPath)shape).lineTo(359.36798, 231.502);
((GeneralPath)shape).lineTo(360.68597, 234.438);
((GeneralPath)shape).lineTo(363.78897, 235.001);
((GeneralPath)shape).lineTo(361.46198, 237.181);
((GeneralPath)shape).moveTo(219.26297, 287.603);
((GeneralPath)shape).lineTo(216.63297, 286.085);
((GeneralPath)shape).lineTo(213.89897, 287.43698);
((GeneralPath)shape).lineTo(214.50897, 284.4);
((GeneralPath)shape).lineTo(212.39897, 282.175);
((GeneralPath)shape).lineTo(215.41898, 281.82098);
((GeneralPath)shape).lineTo(216.83498, 279.098);
((GeneralPath)shape).lineTo(218.09898, 281.916);
((GeneralPath)shape).lineTo(221.07698, 282.456);
((GeneralPath)shape).lineTo(218.84398, 284.546);
((GeneralPath)shape).moveTo(418.98398, 299.71);
((GeneralPath)shape).lineTo(416.83997, 298.57498);
((GeneralPath)shape).lineTo(414.61298, 299.585);
((GeneralPath)shape).lineTo(415.11298, 297.315);
((GeneralPath)shape).lineTo(413.39297, 295.64902);
((GeneralPath)shape).lineTo(415.85297, 295.384);
((GeneralPath)shape).lineTo(417.00696, 293.346);
((GeneralPath)shape).lineTo(418.03696, 295.454);
((GeneralPath)shape).lineTo(420.46295, 295.858);
((GeneralPath)shape).lineTo(418.64294, 297.421);
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_3);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_4 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_4
paint = new Color(255, 255, 239, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(219.263, 287.603);
((GeneralPath)shape).lineTo(216.633, 286.085);
((GeneralPath)shape).lineTo(213.899, 287.43698);
((GeneralPath)shape).lineTo(214.509, 284.4);
((GeneralPath)shape).lineTo(212.399, 282.175);
((GeneralPath)shape).lineTo(215.419, 281.82098);
((GeneralPath)shape).lineTo(216.835, 279.098);
((GeneralPath)shape).lineTo(218.09901, 281.916);
((GeneralPath)shape).lineTo(221.07701, 282.456);
((GeneralPath)shape).lineTo(218.84401, 284.546);
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_4);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_5 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_5
paint = new Color(255, 255, 239, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(219.263, 287.603);
((GeneralPath)shape).lineTo(216.633, 286.085);
((GeneralPath)shape).lineTo(213.899, 287.43698);
((GeneralPath)shape).lineTo(214.509, 284.4);
((GeneralPath)shape).lineTo(212.399, 282.175);
((GeneralPath)shape).lineTo(215.419, 281.82098);
((GeneralPath)shape).lineTo(216.835, 279.098);
((GeneralPath)shape).lineTo(218.09901, 281.916);
((GeneralPath)shape).lineTo(221.07701, 282.456);
((GeneralPath)shape).lineTo(218.84401, 284.546);
((GeneralPath)shape).moveTo(261.143, 287.594);
((GeneralPath)shape).lineTo(258.513, 286.074);
((GeneralPath)shape).lineTo(255.78, 287.427);
((GeneralPath)shape).lineTo(256.38998, 284.39);
((GeneralPath)shape).lineTo(254.27998, 282.165);
((GeneralPath)shape).lineTo(257.3, 281.811);
((GeneralPath)shape).lineTo(258.71597, 279.08902);
((GeneralPath)shape).lineTo(259.981, 281.906);
((GeneralPath)shape).lineTo(262.95898, 282.446);
((GeneralPath)shape).lineTo(260.72598, 284.536);
((GeneralPath)shape).moveTo(255.93999, 301.52502);
((GeneralPath)shape).lineTo(253.30998, 300.00702);
((GeneralPath)shape).lineTo(250.57599, 301.359);
((GeneralPath)shape).lineTo(251.18799, 298.321);
((GeneralPath)shape).lineTo(249.07799, 296.096);
((GeneralPath)shape).lineTo(252.09499, 295.742);
((GeneralPath)shape).lineTo(253.512, 293.018);
((GeneralPath)shape).lineTo(254.777, 295.835);
((GeneralPath)shape).lineTo(257.754, 296.375);
((GeneralPath)shape).lineTo(255.521, 298.465);
((GeneralPath)shape).moveTo(342.90198, 276.164);
((GeneralPath)shape).lineTo(340.27197, 274.644);
((GeneralPath)shape).lineTo(337.53897, 275.997);
((GeneralPath)shape).lineTo(338.14896, 272.961);
((GeneralPath)shape).lineTo(336.03897, 270.736);
((GeneralPath)shape).lineTo(339.05698, 270.383);
((GeneralPath)shape).lineTo(340.47397, 267.659);
((GeneralPath)shape).lineTo(341.73898, 270.47598);
((GeneralPath)shape).lineTo(344.71597, 271.016);
((GeneralPath)shape).lineTo(342.48297, 273.106);
((GeneralPath)shape).moveTo(317.38397, 276.154);
((GeneralPath)shape).lineTo(314.75397, 274.636);
((GeneralPath)shape).lineTo(312.01996, 275.98798);
((GeneralPath)shape).lineTo(312.63196, 272.951);
((GeneralPath)shape).lineTo(310.52197, 270.72598);
((GeneralPath)shape).lineTo(313.53998, 270.373);
((GeneralPath)shape).lineTo(314.95697, 267.649);
((GeneralPath)shape).lineTo(316.22098, 270.46597);
((GeneralPath)shape).lineTo(319.201, 271.00598);
((GeneralPath)shape).lineTo(316.96698, 273.09598);
((GeneralPath)shape).moveTo(248.16698, 267.25797);
((GeneralPath)shape).lineTo(246.51898, 266.30597);
((GeneralPath)shape).lineTo(244.80498, 267.15295);
((GeneralPath)shape).lineTo(245.18898, 265.25095);
((GeneralPath)shape).lineTo(243.86598, 263.85693);
((GeneralPath)shape).lineTo(245.75598, 263.63495);
((GeneralPath)shape).lineTo(246.64598, 261.92896);
((GeneralPath)shape).lineTo(247.43799, 263.69397);
((GeneralPath)shape).lineTo(249.30199, 264.03397);
((GeneralPath)shape).lineTo(247.902, 265.34396);
((GeneralPath)shape).moveTo(415.74, 310.72797);
((GeneralPath)shape).lineTo(413.11, 309.20996);
((GeneralPath)shape).lineTo(410.37698, 310.55997);
((GeneralPath)shape).lineTo(410.98898, 307.52496);
((GeneralPath)shape).lineTo(408.879, 305.29895);
((GeneralPath)shape).lineTo(411.896, 304.94495);
((GeneralPath)shape).lineTo(413.313, 302.22095);
((GeneralPath)shape).lineTo(414.577, 305.03793);
((GeneralPath)shape).lineTo(417.555, 305.57794);
((GeneralPath)shape).lineTo(415.322, 307.66794);
((GeneralPath)shape).moveTo(394.49, 313.51193);
((GeneralPath)shape).lineTo(392.31198, 312.25192);
((GeneralPath)shape).lineTo(390.04797, 313.37393);
((GeneralPath)shape).lineTo(390.55496, 310.85193);
((GeneralPath)shape).lineTo(388.80698, 309.00394);
((GeneralPath)shape).lineTo(391.30698, 308.70993);
((GeneralPath)shape).lineTo(392.481, 306.44794);
((GeneralPath)shape).lineTo(393.529, 308.78793);
((GeneralPath)shape).lineTo(395.995, 309.23795);
((GeneralPath)shape).lineTo(394.145, 310.97293);
((GeneralPath)shape).moveTo(404.516, 313.26993);
((GeneralPath)shape).lineTo(402.486, 312.09692);
((GeneralPath)shape).lineTo(400.378, 313.14093);
((GeneralPath)shape).lineTo(400.84998, 310.79694);
((GeneralPath)shape).lineTo(399.21997, 309.07895);
((GeneralPath)shape).lineTo(401.54996, 308.80496);
((GeneralPath)shape).lineTo(402.64294, 306.70197);
((GeneralPath)shape).lineTo(403.61896, 308.87897);
((GeneralPath)shape).lineTo(405.91495, 309.29596);
((GeneralPath)shape).lineTo(404.19196, 310.91095);
((GeneralPath)shape).moveTo(433.30194, 288.14996);
((GeneralPath)shape).lineTo(431.34695, 287.01996);
((GeneralPath)shape).lineTo(429.31696, 288.02597);
((GeneralPath)shape).lineTo(429.77097, 285.76898);
((GeneralPath)shape).lineTo(428.20398, 284.11398);
((GeneralPath)shape).lineTo(430.447, 283.852);
((GeneralPath)shape).lineTo(431.5, 281.828);
((GeneralPath)shape).lineTo(432.44, 283.92);
((GeneralPath)shape).lineTo(434.65, 284.32202);
((GeneralPath)shape).lineTo(432.992, 285.87503);
((GeneralPath)shape).moveTo(394.24, 327.69);
((GeneralPath)shape).lineTo(391.686, 326.295);
((GeneralPath)shape).lineTo(389.034, 327.535);
((GeneralPath)shape).lineTo(389.628, 324.749);
((GeneralPath)shape).lineTo(387.578, 322.706);
((GeneralPath)shape).lineTo(390.508, 322.38098);
((GeneralPath)shape).lineTo(391.884, 319.88098);
((GeneralPath)shape).lineTo(393.111, 322.46698);
((GeneralPath)shape).lineTo(396.001, 322.96298);
((GeneralPath)shape).lineTo(393.834, 324.883);
((GeneralPath)shape).moveTo(394.38303, 339.13);
((GeneralPath)shape).lineTo(392.05304, 337.73502);
((GeneralPath)shape).lineTo(389.63504, 338.975);
((GeneralPath)shape).lineTo(390.17703, 336.189);
((GeneralPath)shape).lineTo(388.30704, 334.145);
((GeneralPath)shape).lineTo(390.98004, 333.82098);
((GeneralPath)shape).lineTo(392.23505, 331.32098);
((GeneralPath)shape).lineTo(393.35504, 333.90698);
((GeneralPath)shape).lineTo(395.99005, 334.40298);
((GeneralPath)shape).lineTo(394.01306, 336.32098);
((GeneralPath)shape).moveTo(375.08405, 313.266);
((GeneralPath)shape).lineTo(373.12906, 312.136);
((GeneralPath)shape).lineTo(371.09705, 313.142);
((GeneralPath)shape).lineTo(371.55203, 310.885);
((GeneralPath)shape).lineTo(369.98404, 309.232);
((GeneralPath)shape).lineTo(372.22604, 308.969);
((GeneralPath)shape).lineTo(373.28003, 306.944);
((GeneralPath)shape).lineTo(374.22003, 309.037);
((GeneralPath)shape).lineTo(376.43304, 309.439);
((GeneralPath)shape).lineTo(374.77304, 310.99298);
((GeneralPath)shape).moveTo(356.99203, 313.266);
((GeneralPath)shape).lineTo(355.03802, 312.136);
((GeneralPath)shape).lineTo(353.00803, 313.142);
((GeneralPath)shape).lineTo(353.46204, 310.885);
((GeneralPath)shape).lineTo(351.89203, 309.232);
((GeneralPath)shape).lineTo(354.13602, 308.969);
((GeneralPath)shape).lineTo(355.18903, 306.944);
((GeneralPath)shape).lineTo(356.12903, 309.037);
((GeneralPath)shape).lineTo(358.33902, 309.439);
((GeneralPath)shape).lineTo(356.68103, 310.99298);
((GeneralPath)shape).moveTo(326.27304, 286.40298);
((GeneralPath)shape).lineTo(324.31805, 285.275);
((GeneralPath)shape).lineTo(322.28806, 286.279);
((GeneralPath)shape).lineTo(322.74207, 284.022);
((GeneralPath)shape).lineTo(321.17407, 282.368);
((GeneralPath)shape).lineTo(323.41708, 282.104);
((GeneralPath)shape).lineTo(324.4701, 280.08002);
((GeneralPath)shape).lineTo(325.4101, 282.174);
((GeneralPath)shape).lineTo(327.6221, 282.57602);
((GeneralPath)shape).lineTo(325.9621, 284.12903);
((GeneralPath)shape).moveTo(329.6961, 341.153);
((GeneralPath)shape).lineTo(328.0401, 340.19702);
((GeneralPath)shape).lineTo(326.3201, 341.04703);
((GeneralPath)shape).lineTo(326.7061, 339.13702);
((GeneralPath)shape).lineTo(325.3761, 337.73703);
((GeneralPath)shape).lineTo(327.2761, 337.51404);
((GeneralPath)shape).lineTo(328.1691, 335.79904);
((GeneralPath)shape).lineTo(328.9641, 337.57104);
((GeneralPath)shape).lineTo(330.8381, 337.91104);
((GeneralPath)shape).lineTo(329.4311, 339.22705);
((GeneralPath)shape).moveTo(283.30008, 252.59705);
((GeneralPath)shape).lineTo(279.3581, 250.32304);
((GeneralPath)shape).lineTo(275.2651, 252.34804);
((GeneralPath)shape).lineTo(276.1821, 247.80003);
((GeneralPath)shape).lineTo(273.0201, 244.46603);
((GeneralPath)shape).lineTo(277.5401, 243.93604);
((GeneralPath)shape).lineTo(279.6641, 239.85603);
((GeneralPath)shape).lineTo(281.5581, 244.07603);
((GeneralPath)shape).lineTo(286.0181, 244.88603);
((GeneralPath)shape).lineTo(282.6731, 248.01804);
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_5);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_6 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_6
paint = new Color(255, 255, 255, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(444.368, 285.817);
((GeneralPath)shape).curveTo(446.312, 280.73398, 448.81802, 273.067, 450.151, 266.03098);
((GeneralPath)shape).curveTo(382.409, 206.52298, 306.891, 176.03798, 211.47101, 182.31097);
((GeneralPath)shape).curveTo(208.04901, 188.86897, 205.311, 195.73398, 203.001, 203.16397);
((GeneralPath)shape).curveTo(316.06403, 192.37798, 398.937, 242.43398, 444.371, 285.81796);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_6);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_7 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_7
paint = new Color(48, 158, 58, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(413.914, 252.36);
((GeneralPath)shape).lineTo(416.334, 253.683);
((GeneralPath)shape).curveTo(415.954, 254.541, 415.854, 255.293, 416.02402, 255.933);
((GeneralPath)shape).curveTo(416.204, 256.578, 416.64902, 257.141, 417.359, 257.621);
((GeneralPath)shape).curveTo(418.109, 258.13602, 418.78302, 258.361, 419.375, 258.301);
((GeneralPath)shape).curveTo(419.975, 258.241, 420.42, 257.995, 420.71, 257.567);
((GeneralPath)shape).curveTo(420.88687, 257.31598, 420.9668, 257.0094, 420.935, 256.70398);
((GeneralPath)shape).curveTo(420.908, 256.404, 420.743, 256.04398, 420.44, 255.62898);
((GeneralPath)shape).curveTo(420.23, 255.34898, 419.72, 254.75598, 418.91, 253.85199);
((GeneralPath)shape).curveTo(417.87, 252.69199, 417.25, 251.71399, 417.05002, 250.91599);
((GeneralPath)shape).curveTo(416.77002, 249.79399, 416.94003, 248.77599, 417.56003, 247.85599);
((GeneralPath)shape).curveTo(417.96002, 247.26599, 418.49603, 246.82599, 419.17203, 246.538);
((GeneralPath)shape).curveTo(419.85803, 246.248, 420.60504, 246.183, 421.41202, 246.34);
((GeneralPath)shape).curveTo(422.22202, 246.497, 423.07602, 246.87999, 423.962, 247.483);
((GeneralPath)shape).curveTo(425.415, 248.47, 426.292, 249.531, 426.592, 250.667);
((GeneralPath)shape).curveTo(426.897, 251.80501, 426.709, 252.92001, 426.027, 254.01201);
((GeneralPath)shape).lineTo(423.62302, 252.52802);
((GeneralPath)shape).curveTo(423.923, 251.86302, 423.99802, 251.28801, 423.841, 250.80501);
((GeneralPath)shape).curveTo(423.694, 250.32, 423.29102, 249.85501, 422.631, 249.408);
((GeneralPath)shape).curveTo(421.95502, 248.948, 421.329, 248.726, 420.75702, 248.74501);
((GeneralPath)shape).curveTo(420.41028, 248.74373, 420.0871, 248.92041, 419.90103, 249.21301);
((GeneralPath)shape).curveTo(419.71503, 249.49, 419.67303, 249.80301, 419.77103, 250.156);
((GeneralPath)shape).curveTo(419.90103, 250.606, 420.43903, 251.349, 421.39603, 252.39);
((GeneralPath)shape).curveTo(422.34903, 253.43, 423.00003, 254.28, 423.34604, 254.937);
((GeneralPath)shape).curveTo(423.70105, 255.594, 423.86203, 256.277, 423.82803, 256.987);
((GeneralPath)shape).curveTo(423.80502, 257.693, 423.54404, 258.414, 423.05002, 259.147);
((GeneralPath)shape).curveTo(422.60403, 259.81027, 421.97433, 260.32913, 421.238, 260.64);
((GeneralPath)shape).curveTo(420.478, 260.97, 419.668, 261.052, 418.801, 260.88);
((GeneralPath)shape).curveTo(417.941, 260.703, 417.007, 260.273, 416.003, 259.59);
((GeneralPath)shape).curveTo(414.541, 258.598, 413.643, 257.497, 413.31598, 256.29);
((GeneralPath)shape).curveTo(412.994, 255.07701, 413.19098, 253.76701, 413.916, 252.365);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(402.436, 244.827);
((GeneralPath)shape).lineTo(404.908, 246.047);
((GeneralPath)shape).curveTo(404.563, 246.91899, 404.491, 247.675, 404.688, 248.30699);
((GeneralPath)shape).curveTo(404.896, 248.94398, 405.36, 249.48999, 406.08798, 249.94199);
((GeneralPath)shape).curveTo(406.86298, 250.42398, 407.54297, 250.62198, 408.13098, 250.53798);
((GeneralPath)shape).curveTo(408.731, 250.45198, 409.16797, 250.19199, 409.43698, 249.75198);
((GeneralPath)shape).curveTo(409.60498, 249.49515, 409.6726, 249.18549, 409.62698, 248.88199);
((GeneralPath)shape).curveTo(409.589, 248.57999, 409.409, 248.22699, 409.08698, 247.82399);
((GeneralPath)shape).curveTo(408.86697, 247.55199, 408.33698, 246.984, 407.49, 246.111);
((GeneralPath)shape).curveTo(406.40298, 244.99399, 405.744, 244.04099, 405.512, 243.25099);
((GeneralPath)shape).curveTo(405.189, 242.14099, 405.318, 241.118, 405.897, 240.174);
((GeneralPath)shape).curveTo(406.27054, 239.57106, 406.81302, 239.09119, 407.457, 238.79399);
((GeneralPath)shape).curveTo(408.131, 238.478, 408.877, 238.381, 409.687, 238.504);
((GeneralPath)shape).curveTo(410.505, 238.631, 411.372, 238.977, 412.282, 239.54399);
((GeneralPath)shape).curveTo(413.77402, 240.46999, 414.69, 241.49599, 415.035, 242.618);
((GeneralPath)shape).curveTo(415.385, 243.744, 415.245, 244.86499, 414.608, 245.983);
((GeneralPath)shape).lineTo(412.144, 244.598);
((GeneralPath)shape).curveTo(412.419, 243.92201, 412.471, 243.34601, 412.294, 242.87001);
((GeneralPath)shape).curveTo(412.126, 242.38802, 411.704, 241.94002, 411.03, 241.52);
((GeneralPath)shape).curveTo(410.333, 241.087, 409.7, 240.892, 409.13, 240.934);
((GeneralPath)shape).curveTo(408.76, 240.959, 408.483, 241.12901, 408.292, 241.438);
((GeneralPath)shape).curveTo(408.12, 241.72, 408.08798, 242.032, 408.202, 242.382);
((GeneralPath)shape).curveTo(408.347, 242.825, 408.916, 243.547, 409.912, 244.55);
((GeneralPath)shape).curveTo(410.90598, 245.55, 411.59198, 246.37201, 411.964, 247.015);
((GeneralPath)shape).curveTo(412.344, 247.655, 412.53198, 248.333, 412.52698, 249.04199);
((GeneralPath)shape).curveTo(412.53397, 249.74998, 412.3, 250.47899, 411.83698, 251.23499);
((GeneralPath)shape).curveTo(411.41608, 251.91393, 410.80853, 252.45724, 410.08698, 252.79999);
((GeneralPath)shape).curveTo(409.34097, 253.15999, 408.53098, 253.27399, 407.65997, 253.13599);
((GeneralPath)shape).curveTo(406.79498, 252.99599, 405.84497, 252.59999, 404.81198, 251.96098);
((GeneralPath)shape).curveTo(403.314, 251.02798, 402.374, 249.96498, 401.99698, 248.77098);
((GeneralPath)shape).curveTo(401.623, 247.57098, 401.76697, 246.25699, 402.43497, 244.82799);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(388.23, 241.01999);
((GeneralPath)shape).lineTo(395.506, 229.05399);
((GeneralPath)shape).lineTo(404.34302, 234.46999);
((GeneralPath)shape).lineTo(403.113, 236.49599);
((GeneralPath)shape).lineTo(396.683, 232.55399);
((GeneralPath)shape).lineTo(395.06802, 235.20598);
((GeneralPath)shape).lineTo(401.05103, 238.87398);
((GeneralPath)shape).lineTo(399.82602, 240.88898);
((GeneralPath)shape).lineTo(393.842, 237.22197);
((GeneralPath)shape).lineTo(391.86502, 240.47797);
((GeneralPath)shape).lineTo(398.52203, 244.55797);
((GeneralPath)shape).lineTo(397.29404, 246.57497);
((GeneralPath)shape).lineTo(388.23105, 241.01796);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(367.53802, 224.027);
((GeneralPath)shape).lineTo(368.618, 221.92699);
((GeneralPath)shape).lineTo(374.018, 224.72299);
((GeneralPath)shape).lineTo(371.47202, 229.685);
((GeneralPath)shape).curveTo(370.682, 229.923, 369.69202, 229.981, 368.49002, 229.855);
((GeneralPath)shape).curveTo(367.33267, 229.73546, 366.20776, 229.40108, 365.17303, 228.869);
((GeneralPath)shape).curveTo(363.87305, 228.196, 362.88303, 227.341, 362.19702, 226.297);
((GeneralPath)shape).curveTo(361.51904, 225.26993, 361.17856, 224.05685, 361.22302, 222.827);
((GeneralPath)shape).curveTo(361.26657, 221.53497, 361.6005, 220.26933, 362.2, 219.124);
((GeneralPath)shape).curveTo(362.864, 217.82599, 363.73, 216.814, 364.79, 216.084);
((GeneralPath)shape).curveTo(365.84702, 215.357, 367.04, 214.994, 368.36002, 214.994);
((GeneralPath)shape).curveTo(369.368, 214.992, 370.46402, 215.3, 371.65002, 215.91);
((GeneralPath)shape).curveTo(373.19202, 216.71, 374.22702, 217.657, 374.75403, 218.756);
((GeneralPath)shape).curveTo(375.29404, 219.85199, 375.39203, 221.036, 375.05203, 222.31099);
((GeneralPath)shape).lineTo(372.32404, 221.49098);
((GeneralPath)shape).curveTo(372.46405, 220.78899, 372.38104, 220.13498, 372.07404, 219.53398);
((GeneralPath)shape).curveTo(371.77805, 218.92798, 371.26804, 218.43898, 370.54703, 218.06398);
((GeneralPath)shape).curveTo(369.45004, 217.49698, 368.40103, 217.39398, 367.39203, 217.75899);
((GeneralPath)shape).curveTo(366.39203, 218.122, 365.54202, 218.98898, 364.83804, 220.359);
((GeneralPath)shape).curveTo(364.07803, 221.83899, 363.83304, 223.11899, 364.10803, 224.20099);
((GeneralPath)shape).curveTo(364.38504, 225.27399, 365.05203, 226.08699, 366.11603, 226.63799);
((GeneralPath)shape).curveTo(366.64, 226.90799, 367.21603, 227.07799, 367.84604, 227.14499);
((GeneralPath)shape).curveTo(368.48605, 227.21098, 369.06604, 227.19499, 369.59903, 227.09499);
((GeneralPath)shape).lineTo(370.40903, 225.51299);
((GeneralPath)shape).lineTo(367.53702, 224.02798);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(277.29602, 201.648);
((GeneralPath)shape).lineTo(279.33002, 187.78099);
((GeneralPath)shape).lineTo(283.502, 188.40099);
((GeneralPath)shape).lineTo(284.625, 198.22699);
((GeneralPath)shape).lineTo(288.485, 189.13399);
((GeneralPath)shape).lineTo(292.67297, 189.75198);
((GeneralPath)shape).lineTo(290.63998, 203.62198);
((GeneralPath)shape).lineTo(288.05, 203.23997);
((GeneralPath)shape).lineTo(289.65, 192.32198);
((GeneralPath)shape).lineTo(285.307, 202.83397);
((GeneralPath)shape).lineTo(282.622, 202.43597);
((GeneralPath)shape).lineTo(281.488, 191.11597);
((GeneralPath)shape).lineTo(279.888, 202.03096);
((GeneralPath)shape).lineTo(277.296, 201.64896);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(263.18802, 200.01);
((GeneralPath)shape).lineTo(264.493, 186.04999);
((GeneralPath)shape).lineTo(274.80002, 187.02399);
((GeneralPath)shape).lineTo(274.583, 189.38399);
((GeneralPath)shape).lineTo(267.08002, 188.678);
((GeneralPath)shape).lineTo(266.79, 191.773);
((GeneralPath)shape).lineTo(273.768, 192.43);
((GeneralPath)shape).lineTo(273.548, 194.782);
((GeneralPath)shape).lineTo(266.568, 194.124);
((GeneralPath)shape).lineTo(266.215, 197.924);
((GeneralPath)shape).lineTo(273.979, 198.65399);
((GeneralPath)shape).lineTo(273.759, 201.008);
((GeneralPath)shape).lineTo(263.187, 200.01);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_7);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_8 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_8
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_8_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_8_0
paint = new Color(48, 158, 58, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(216.5, 191.28);
((GeneralPath)shape).curveTo(216.54, 189.85, 216.784, 188.66, 217.236, 187.7);
((GeneralPath)shape).curveTo(217.57101, 186.99811, 218.02658, 186.36044, 218.58199, 185.816);
((GeneralPath)shape).curveTo(219.14798, 185.26399, 219.76198, 184.86, 220.42598, 184.60599);
((GeneralPath)shape).curveTo(221.30598, 184.25899, 222.31398, 184.10098, 223.44897, 184.13098);
((GeneralPath)shape).curveTo(225.50497, 184.19098, 227.13098, 184.87498, 228.32597, 186.18799);
((GeneralPath)shape).curveTo(229.53098, 187.50299, 230.10097, 189.30199, 230.03998, 191.583);
((GeneralPath)shape).curveTo(229.97998, 193.84299, 229.31998, 195.59999, 228.05798, 196.84698);
((GeneralPath)shape).curveTo(226.79799, 198.08699, 225.14398, 198.68098, 223.09499, 198.62398);
((GeneralPath)shape).curveTo(221.01799, 198.56798, 219.387, 197.88799, 218.19499, 196.58698);
((GeneralPath)shape).curveTo(217.00499, 195.27898, 216.43999, 193.50897, 216.50099, 191.27997);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_8_0);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_8_1 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_8_1
paint = new Color(247, 255, 255, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(219.414, 191.252);
((GeneralPath)shape).curveTo(219.371, 192.838, 219.704, 194.052, 220.411, 194.895);
((GeneralPath)shape).curveTo(221.11899, 195.73201, 222.036, 196.16501, 223.159, 196.195);
((GeneralPath)shape).curveTo(224.28099, 196.225, 225.21399, 195.845, 225.953, 195.057);
((GeneralPath)shape).curveTo(226.698, 194.26001, 227.093, 193.05, 227.13701, 191.42401);
((GeneralPath)shape).curveTo(227.18001, 189.81902, 226.86002, 188.61101, 226.177, 187.80202);
((GeneralPath)shape).curveTo(225.501, 186.99202, 224.582, 186.57202, 223.42, 186.54002);
((GeneralPath)shape).curveTo(222.258, 186.51003, 221.31, 186.88503, 220.577, 187.66803);
((GeneralPath)shape).curveTo(219.844, 188.44502, 219.457, 189.64003, 219.414, 191.25203);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_8_1);
g.setTransform(defaultTransform__0_0_8);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_9 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_9
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_9_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_9_0
paint = new Color(48, 158, 58, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(233.052, 198.51);
((GeneralPath)shape).lineTo(233.215, 184.493);
((GeneralPath)shape).lineTo(239.148, 184.563);
((GeneralPath)shape).curveTo(240.642, 184.58101, 241.722, 184.72, 242.392, 184.983);
((GeneralPath)shape).curveTo(243.069, 185.24, 243.606, 185.69301, 244.005, 186.343);
((GeneralPath)shape).curveTo(244.404, 186.993, 244.598, 187.728, 244.589, 188.558);
((GeneralPath)shape).curveTo(244.576, 189.61, 244.257, 190.476, 243.63301, 191.156);
((GeneralPath)shape).curveTo(243.01001, 191.83101, 242.08301, 192.251, 240.85602, 192.416);
((GeneralPath)shape).curveTo(241.46101, 192.779, 241.96002, 193.176, 242.34602, 193.609);
((GeneralPath)shape).curveTo(242.74303, 194.03899, 243.26903, 194.804, 243.93103, 195.902);
((GeneralPath)shape).lineTo(245.60403, 198.65599);
((GeneralPath)shape).lineTo(242.23204, 198.616);
((GeneralPath)shape).lineTo(240.23004, 195.54199);
((GeneralPath)shape).curveTo(239.52003, 194.44398, 239.03204, 193.754, 238.77003, 193.46999);
((GeneralPath)shape).curveTo(238.50504, 193.18, 238.22504, 192.98299, 237.92804, 192.87698);
((GeneralPath)shape).curveTo(237.63104, 192.76698, 237.15804, 192.70699, 236.51004, 192.69998);
((GeneralPath)shape).lineTo(235.94003, 192.69199);
((GeneralPath)shape).lineTo(235.87204, 198.54399);
((GeneralPath)shape).lineTo(233.05203, 198.51099);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_9_0);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_9_1 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_9_1
paint = new Color(255, 255, 255, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(235.976, 190.455);
((GeneralPath)shape).lineTo(238.062, 190.479);
((GeneralPath)shape).curveTo(239.415, 190.49501, 240.26, 190.449, 240.59799, 190.337);
((GeneralPath)shape).curveTo(240.935, 190.225, 241.20099, 190.03201, 241.394, 189.753);
((GeneralPath)shape).curveTo(241.587, 189.474, 241.687, 189.126, 241.694, 188.705);
((GeneralPath)shape).curveTo(241.698, 188.233, 241.576, 187.852, 241.324, 187.563);
((GeneralPath)shape).curveTo(241.08101, 187.267, 240.73001, 187.07701, 240.274, 186.996);
((GeneralPath)shape).curveTo(240.044, 186.962, 239.35901, 186.936, 238.217, 186.924);
((GeneralPath)shape).lineTo(236.017, 186.898);
((GeneralPath)shape).lineTo(235.977, 190.45299);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_9_1);
g.setTransform(defaultTransform__0_0_9);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_10 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_10
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_10_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_10_0
paint = new Color(48, 158, 58, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(249.003, 185.188);
((GeneralPath)shape).lineTo(254.15001, 185.448);
((GeneralPath)shape).curveTo(255.31001, 185.508, 256.19, 185.643, 256.79, 185.853);
((GeneralPath)shape).curveTo(257.5814, 186.12772, 258.28452, 186.60983, 258.82602, 187.249);
((GeneralPath)shape).curveTo(259.37903, 187.89499, 259.78403, 188.67499, 260.044, 189.58899);
((GeneralPath)shape).curveTo(260.30402, 190.49599, 260.4, 191.60399, 260.334, 192.915);
((GeneralPath)shape).curveTo(260.276, 194.068, 260.082, 195.053, 259.75403, 195.875);
((GeneralPath)shape).curveTo(259.35403, 196.875, 258.81604, 197.672, 258.13602, 198.271);
((GeneralPath)shape).curveTo(257.626, 198.724, 256.946, 199.06099, 256.10202, 199.287);
((GeneralPath)shape).curveTo(255.47002, 199.453, 254.63402, 199.509, 253.59203, 199.457);
((GeneralPath)shape).lineTo(248.29703, 199.187);
((GeneralPath)shape).lineTo(249.00302, 185.187);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_10_0);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_10_1 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_10_1
paint = new Color(255, 255, 255, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(251.706, 187.685);
((GeneralPath)shape).lineTo(251.23799, 196.959);
((GeneralPath)shape).lineTo(253.34099, 197.064);
((GeneralPath)shape).curveTo(254.12698, 197.106, 254.69798, 197.08899, 255.051, 197.01799);
((GeneralPath)shape).curveTo(255.511, 196.92499, 255.901, 196.74998, 256.211, 196.49199);
((GeneralPath)shape).curveTo(256.531, 196.232, 256.801, 195.79698, 257.021, 195.18199);
((GeneralPath)shape).curveTo(257.244, 194.562, 257.38098, 193.71199, 257.43698, 192.629);
((GeneralPath)shape).curveTo(257.49298, 191.546, 257.43698, 190.711, 257.27698, 190.122);
((GeneralPath)shape).curveTo(257.11697, 189.532, 256.873, 189.069, 256.54697, 188.72499);
((GeneralPath)shape).curveTo(256.21997, 188.383, 255.79697, 188.142, 255.27696, 188.00099);
((GeneralPath)shape).curveTo(254.88696, 187.89099, 254.11996, 187.808, 252.97096, 187.75099);
((GeneralPath)shape).lineTo(251.70695, 187.68399);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_10_1);
g.setTransform(defaultTransform__0_0_10);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_11 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_11
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_11_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_11_0
paint = new Color(48, 158, 58, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(317.63, 210.22);
((GeneralPath)shape).lineTo(320.89, 196.59);
((GeneralPath)shape).lineTo(325.29, 197.65);
((GeneralPath)shape).curveTo(326.956, 198.05199, 328.027, 198.38199, 328.5, 198.64);
((GeneralPath)shape).curveTo(329.23, 199.032, 329.774, 199.636, 330.134, 200.45);
((GeneralPath)shape).curveTo(330.494, 201.26, 330.544, 202.205, 330.286, 203.29);
((GeneralPath)shape).curveTo(330.086, 204.12599, 329.768, 204.79399, 329.328, 205.29);
((GeneralPath)shape).curveTo(328.89, 205.79, 328.396, 206.144, 327.848, 206.36);
((GeneralPath)shape).curveTo(327.30798, 206.572, 326.784, 206.67, 326.27798, 206.66);
((GeneralPath)shape).curveTo(325.593, 206.632, 324.628, 206.47, 323.38797, 206.17);
((GeneralPath)shape).lineTo(321.60196, 205.73799);
((GeneralPath)shape).lineTo(320.37195, 210.87999);
((GeneralPath)shape).lineTo(317.62894, 210.21999);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_11_0);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_11_1 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_11_1
paint = new Color(255, 255, 255, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(323.086, 199.552);
((GeneralPath)shape).lineTo(322.16, 203.42);
((GeneralPath)shape).lineTo(323.66, 203.782);
((GeneralPath)shape).curveTo(324.742, 204.04199, 325.48, 204.146, 325.878, 204.09);
((GeneralPath)shape).curveTo(326.65945, 203.993, 327.29385, 203.41196, 327.45898, 202.642);
((GeneralPath)shape).curveTo(327.57898, 202.146, 327.53198, 201.702, 327.31897, 201.312);
((GeneralPath)shape).curveTo(327.1089, 200.92107, 326.7711, 200.614, 326.36197, 200.442);
((GeneralPath)shape).curveTo(326.04996, 200.299, 325.40198, 200.11, 324.41196, 199.872);
((GeneralPath)shape).lineTo(323.08795, 199.55199);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_11_1);
g.setTransform(defaultTransform__0_0_11);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_12 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_12
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_12_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_12_0
paint = new Color(48, 158, 58, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(330.606, 214.106);
((GeneralPath)shape).lineTo(335.246, 200.886);
((GeneralPath)shape).lineTo(340.844, 202.866);
((GeneralPath)shape).curveTo(342.25198, 203.364, 343.231, 203.846, 343.781, 204.311);
((GeneralPath)shape).curveTo(344.341, 204.774, 344.704, 205.375, 344.874, 206.11801);
((GeneralPath)shape).curveTo(345.04398, 206.86102, 344.994, 207.62302, 344.718, 208.404);
((GeneralPath)shape).curveTo(344.37, 209.39601, 343.78998, 210.11401, 342.982, 210.557);
((GeneralPath)shape).curveTo(342.176, 210.99501, 341.165, 211.09401, 339.94998, 210.85501);
((GeneralPath)shape).curveTo(340.40698, 211.395, 340.75198, 211.93102, 340.97998, 212.46501);
((GeneralPath)shape).curveTo(341.218, 213.001, 341.46997, 213.895, 341.745, 215.14801);
((GeneralPath)shape).lineTo(342.449, 218.298);
((GeneralPath)shape).lineTo(339.269, 217.172);
((GeneralPath)shape).lineTo(338.35602, 213.616);
((GeneralPath)shape).curveTo(338.03403, 212.346, 337.794, 211.536, 337.63602, 211.181);
((GeneralPath)shape).curveTo(337.47803, 210.821, 337.27603, 210.543, 337.02902, 210.347);
((GeneralPath)shape).curveTo(336.78302, 210.145, 336.35602, 209.937, 335.743, 209.72);
((GeneralPath)shape).lineTo(335.207, 209.528);
((GeneralPath)shape).lineTo(333.269, 215.048);
((GeneralPath)shape).lineTo(330.609, 214.106);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_12_0);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_12_1 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_12_1
paint = new Color(255, 255, 255, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(335.938, 207.426);
((GeneralPath)shape).lineTo(337.905, 208.121);
((GeneralPath)shape).curveTo(339.181, 208.573, 339.995, 208.801, 340.35, 208.804);
((GeneralPath)shape).curveTo(340.70502, 208.809, 341.02002, 208.711, 341.293, 208.509);
((GeneralPath)shape).curveTo(341.565, 208.309, 341.771, 208.009, 341.909, 207.613);
((GeneralPath)shape).curveTo(342.064, 207.168, 342.07098, 206.768, 341.926, 206.41301);
((GeneralPath)shape).curveTo(341.791, 206.05301, 341.518, 205.76302, 341.113, 205.537);
((GeneralPath)shape).curveTo(340.907, 205.431, 340.26602, 205.187, 339.189, 204.807);
((GeneralPath)shape).lineTo(337.11398, 204.07101);
((GeneralPath)shape).lineTo(335.93698, 207.42702);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_12_1);
g.setTransform(defaultTransform__0_0_12);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_13 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_13
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_13_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_13_0
paint = new Color(48, 158, 58, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(347.01, 213.6);
((GeneralPath)shape).curveTo(347.43402, 212.237, 347.992, 211.156, 348.683, 210.36);
((GeneralPath)shape).curveTo(349.19516, 209.7735, 349.8073, 209.28256, 350.49103, 208.91);
((GeneralPath)shape).curveTo(351.18704, 208.533, 351.88803, 208.312, 352.59302, 208.24501);
((GeneralPath)shape).curveTo(353.53302, 208.15201, 354.54602, 208.27501, 355.631, 208.615);
((GeneralPath)shape).curveTo(357.596, 209.229, 358.975, 210.332, 359.77103, 211.923);
((GeneralPath)shape).curveTo(360.57404, 213.516, 360.63803, 215.403, 359.96103, 217.58101);
((GeneralPath)shape).curveTo(359.29102, 219.74301, 358.18103, 221.251, 356.63104, 222.10901);
((GeneralPath)shape).curveTo(355.08304, 222.96101, 353.32904, 223.07901, 351.37103, 222.466);
((GeneralPath)shape).curveTo(349.38904, 221.84601, 348.00104, 220.748, 347.20703, 219.172);
((GeneralPath)shape).curveTo(346.41403, 217.589, 346.34903, 215.732, 347.01102, 213.60199);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_13_0);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_13_1 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_13_1
paint = new Color(255, 255, 255, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(349.826, 214.385);
((GeneralPath)shape).curveTo(349.356, 215.899, 349.34598, 217.15799, 349.8, 218.163);
((GeneralPath)shape).curveTo(350.25497, 219.159, 351.02, 219.82599, 352.093, 220.163);
((GeneralPath)shape).curveTo(353.166, 220.497, 354.163, 220.386, 355.089, 219.827);
((GeneralPath)shape).curveTo(356.021, 219.265, 356.729, 218.207, 357.211, 216.655);
((GeneralPath)shape).curveTo(357.687, 215.12, 357.706, 213.872, 357.267, 212.905);
((GeneralPath)shape).curveTo(356.835, 211.943, 356.063, 211.287, 354.954, 210.941);
((GeneralPath)shape).curveTo(353.84402, 210.594, 352.83102, 210.698, 351.914, 211.25299);
((GeneralPath)shape).curveTo(350.999, 211.801, 350.30402, 212.84499, 349.824, 214.38599);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_13_1);
g.setTransform(defaultTransform__0_0_13);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_14 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_14
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_14_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_14_0
paint = new Color(48, 158, 58, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(374.305, 233.12);
((GeneralPath)shape).lineTo(380.72, 220.67);
((GeneralPath)shape).lineTo(385.99, 223.406);
((GeneralPath)shape).curveTo(387.31598, 224.09601, 388.22, 224.70601, 388.69998, 225.246);
((GeneralPath)shape).curveTo(389.18997, 225.778, 389.468, 226.426, 389.53497, 227.186);
((GeneralPath)shape).curveTo(389.60196, 227.94601, 389.44296, 228.69101, 389.06497, 229.42801);
((GeneralPath)shape).curveTo(388.58496, 230.36201, 387.91196, 230.992, 387.04797, 231.32);
((GeneralPath)shape).curveTo(386.188, 231.64201, 385.17596, 231.6, 384.00497, 231.192);
((GeneralPath)shape).curveTo(384.38297, 231.79001, 384.64996, 232.372, 384.80496, 232.932);
((GeneralPath)shape).curveTo(384.96295, 233.496, 385.09296, 234.416, 385.19196, 235.695);
((GeneralPath)shape).lineTo(385.45395, 238.91);
((GeneralPath)shape).lineTo(382.46094, 237.35501);
((GeneralPath)shape).lineTo(382.04593, 233.70702);
((GeneralPath)shape).curveTo(381.90094, 232.40302, 381.77594, 231.56702, 381.66794, 231.19502);
((GeneralPath)shape).curveTo(381.56293, 230.81802, 381.39795, 230.51302, 381.18094, 230.28502);
((GeneralPath)shape).curveTo(380.96695, 230.05202, 380.57095, 229.78502, 379.99493, 229.48701);
((GeneralPath)shape).lineTo(379.48795, 229.223);
((GeneralPath)shape).lineTo(376.81094, 234.42001);
((GeneralPath)shape).lineTo(374.30594, 233.12001);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_14_0);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_14_1 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_14_1
paint = new Color(255, 255, 255, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(380.503, 227.226);
((GeneralPath)shape).lineTo(382.356, 228.188);
((GeneralPath)shape).curveTo(383.556, 228.813, 384.33298, 229.15001, 384.68597, 229.20401);
((GeneralPath)shape).curveTo(385.03598, 229.25801, 385.36096, 229.20401, 385.65897, 229.042);
((GeneralPath)shape).curveTo(385.95496, 228.882, 386.19897, 228.61401, 386.39197, 228.23901);
((GeneralPath)shape).curveTo(386.60797, 227.81902, 386.66797, 227.42502, 386.57596, 227.05301);
((GeneralPath)shape).curveTo(386.48895, 226.67902, 386.26096, 226.35101, 385.89096, 226.07301);
((GeneralPath)shape).curveTo(385.70096, 225.93901, 385.10095, 225.60802, 384.08295, 225.08002);
((GeneralPath)shape).lineTo(382.13095, 224.06702);
((GeneralPath)shape).lineTo(380.50095, 227.22702);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_14_1);
g.setTransform(defaultTransform__0_0_14);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_15 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_15
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_15_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_15_0
paint = new Color(48, 158, 58, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(426.107, 258.704);
((GeneralPath)shape).curveTo(426.904, 257.521, 427.749, 256.648, 428.643, 256.084);
((GeneralPath)shape).curveTo(429.30212, 255.67279, 430.02856, 255.38098, 430.789, 255.22202);
((GeneralPath)shape).curveTo(431.51282, 255.06322, 432.2614, 255.05368, 432.989, 255.19402);
((GeneralPath)shape).curveTo(433.919, 255.37802, 434.85303, 255.79001, 435.794, 256.42902);
((GeneralPath)shape).curveTo(437.49802, 257.58502, 438.502, 259.04102, 438.808, 260.795);
((GeneralPath)shape).curveTo(439.118, 262.553, 438.635, 264.375, 437.36002, 266.26703);
((GeneralPath)shape).curveTo(436.09702, 268.14, 434.60202, 269.265, 432.872, 269.63702);
((GeneralPath)shape).curveTo(431.144, 270.002, 429.432, 269.609, 427.732, 268.45502);
((GeneralPath)shape).curveTo(426.014, 267.28702, 425.0, 265.833, 424.692, 264.09302);
((GeneralPath)shape).curveTo(424.38898, 262.34702, 424.86, 260.55002, 426.10498, 258.703);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_15_0);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_15_1 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_15_1
paint = new Color(255, 255, 255, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(428.578, 260.254);
((GeneralPath)shape).curveTo(427.69202, 261.57, 427.322, 262.772, 427.466, 263.86398);
((GeneralPath)shape).curveTo(427.616, 264.951, 428.156, 265.809, 429.086, 266.442);
((GeneralPath)shape).curveTo(430.018, 267.07397, 431.006, 267.257, 432.053, 266.99197);
((GeneralPath)shape).curveTo(433.108, 266.722, 434.09, 265.91498, 434.997, 264.567);
((GeneralPath)shape).curveTo(435.893, 263.237, 436.27002, 262.047, 436.127, 260.995);
((GeneralPath)shape).curveTo(435.989, 259.948, 435.43903, 259.097, 434.47702, 258.443);
((GeneralPath)shape).curveTo(433.515, 257.789, 432.515, 257.593, 431.47702, 257.86);
((GeneralPath)shape).curveTo(430.44403, 258.12, 429.47903, 258.91998, 428.57703, 260.254);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_15_1);
g.setTransform(defaultTransform__0_0_15);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_16 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_16
paint = new Color(48, 158, 58, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(301.824, 204.523);
((GeneralPath)shape).lineTo(304.072, 194.683);
((GeneralPath)shape).lineTo(311.34, 196.358);
((GeneralPath)shape).lineTo(310.962, 198.02);
((GeneralPath)shape).lineTo(305.67502, 196.80301);
((GeneralPath)shape).lineTo(305.17102, 198.983);
((GeneralPath)shape).lineTo(310.09702, 200.119);
((GeneralPath)shape).lineTo(309.71503, 201.774);
((GeneralPath)shape).lineTo(304.79703, 200.642);
((GeneralPath)shape).lineTo(304.183, 203.319);
((GeneralPath)shape).lineTo(309.65802, 204.579);
((GeneralPath)shape).lineTo(309.28003, 206.239);
((GeneralPath)shape).lineTo(301.82404, 204.522);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_16);
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
	public br() {
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

