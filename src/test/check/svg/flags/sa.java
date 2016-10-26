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
public class sa implements Icon, UIResource, IsResizable, IsHiDpiAware {
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
g.transform(new AffineTransform(0.9375f, 0.0f, 0.0f, 0.9375f, 80.0f, 0.0f));
// _0_0
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_0
paint = new Color(25, 157, 0, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(-128.0, 0.0);
((GeneralPath)shape).lineTo(640.0, 0.0);
((GeneralPath)shape).lineTo(640.0, 512.0);
((GeneralPath)shape).lineTo(-128.0, 512.0);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_0);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_1 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_1
paint = new Color(255, 255, 255, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(65.54, 145.13);
((GeneralPath)shape).curveTo(64.653, 157.091, 63.59, 178.141, 73.753, 180.3);
((GeneralPath)shape).curveTo(86.046, 181.48201, 79.267, 159.494, 83.716995, 155.507);
((GeneralPath)shape).curveTo(84.558, 153.537, 86.10899, 153.52701, 86.23699, 156.01201);
((GeneralPath)shape).lineTo(86.23699, 174.662);
((GeneralPath)shape).curveTo(86.12499, 180.728, 90.11199, 182.514, 93.20899, 183.767);
((GeneralPath)shape).curveTo(96.43299, 183.518, 98.58499, 183.62599, 99.84799, 186.762);
((GeneralPath)shape).lineTo(101.35999, 219.023);
((GeneralPath)shape).curveTo(101.35999, 219.023, 108.83599, 221.163, 109.19199, 200.877);
((GeneralPath)shape).curveTo(109.548996, 188.967, 106.812, 178.997, 108.41699, 176.679);
((GeneralPath)shape).curveTo(108.47299, 174.40201, 111.38099, 174.266, 113.396996, 175.376);
((GeneralPath)shape).curveTo(116.61099, 177.64201, 118.04199, 180.44101, 123.035995, 179.322);
((GeneralPath)shape).curveTo(130.634, 177.22801, 135.202, 173.53201, 135.314, 167.69801);
((GeneralPath)shape).curveTo(134.87, 162.15302, 134.248, 156.60802, 131.846, 151.06302);
((GeneralPath)shape).curveTo(132.181, 150.05402, 130.379, 147.44202, 130.713, 146.43301);
((GeneralPath)shape).curveTo(132.079, 148.57202, 134.157, 148.39401, 134.629, 146.43301);
((GeneralPath)shape).curveTo(133.336, 142.17201, 131.329, 138.08902, 128.076, 136.32101);
((GeneralPath)shape).curveTo(125.388, 133.95302, 121.453, 134.43701, 120.012, 139.37701);
((GeneralPath)shape).curveTo(119.345, 145.06902, 122.064, 151.83202, 126.209, 157.34502);
((GeneralPath)shape).curveTo(127.089, 159.49902, 128.326, 163.07802, 127.782, 166.30101);
((GeneralPath)shape).curveTo(125.576996, 167.56001, 123.370995, 167.035, 121.522995, 165.08401);
((GeneralPath)shape).curveTo(121.522995, 165.08401, 115.475, 160.54802, 115.475, 159.54001);
((GeneralPath)shape).curveTo(117.08, 149.264, 115.831, 148.09901, 114.942, 145.24701);
((GeneralPath)shape).curveTo(114.32, 141.31001, 112.452, 140.048, 110.939, 137.35901);
((GeneralPath)shape).curveTo(109.426, 135.755, 107.379005, 135.755, 106.401, 137.35901);
((GeneralPath)shape).curveTo(103.727, 141.994, 104.974, 151.94202, 106.905, 156.39902);
((GeneralPath)shape).curveTo(108.301, 160.49702, 110.433, 163.06902, 109.424995, 163.06902);
((GeneralPath)shape).curveTo(108.59599, 165.38501, 106.881, 164.85002, 105.632996, 162.17702);
((GeneralPath)shape).curveTo(103.851, 156.65202, 103.493996, 148.40802, 103.493996, 144.69702);
((GeneralPath)shape).curveTo(102.96, 140.09402, 102.371994, 130.27702, 99.342995, 127.78302);
((GeneralPath)shape).curveTo(97.494995, 125.26702, 94.756, 126.49402, 93.798996, 128.79102);
((GeneralPath)shape).curveTo(93.6, 133.35901, 93.578995, 137.92601, 94.093994, 142.13602);
((GeneralPath)shape).curveTo(96.171, 149.51901, 96.824, 156.01202, 97.83199, 163.57301);
((GeneralPath)shape).curveTo(98.11199, 173.70102, 91.97599, 167.96701, 92.256, 162.94601);
((GeneralPath)shape).curveTo(93.671, 156.42401, 93.30399, 146.15802, 92.046, 143.55602);
((GeneralPath)shape).curveTo(91.049995, 140.95401, 89.873, 140.31201, 87.449, 140.74002);
((GeneralPath)shape).curveTo(85.523994, 140.62302, 80.571, 146.03102, 79.18, 155.00201);
((GeneralPath)shape).curveTo(79.18, 155.00201, 77.994, 159.62001, 77.489, 163.72002);
((GeneralPath)shape).curveTo(76.809, 168.35301, 73.758995, 171.62302, 71.618996, 163.06802);
((GeneralPath)shape).curveTo(69.770996, 156.85103, 68.634995, 141.54402, 65.538994, 145.12903);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_1);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_2 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_2
paint = new Color(255, 255, 255, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(98.944, 194.21);
((GeneralPath)shape).curveTo(88.096, 199.513, 77.604996, 204.46, 66.934006, 209.585);
((GeneralPath)shape).curveTo(67.324005, 202.32901, 82.15301, 189.231, 92.26501, 189.04701);
((GeneralPath)shape).curveTo(98.84701, 189.23102, 97.19301, 191.59702, 98.94401, 194.21101);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_2);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_3 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_3
paint = new Color(255, 255, 255, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(93.352, 204.24);
((GeneralPath)shape).curveTo(76.481995, 247.727, 132.857, 253.78601, 139.157, 206.02101);
((GeneralPath)shape).curveTo(139.75, 204.061, 142.127, 202.10101, 142.543, 205.30801);
((GeneralPath)shape).curveTo(141.236, 248.55602, 98.937, 251.52802, 91.749, 237.92201);
((GeneralPath)shape).curveTo(89.968, 234.71501, 89.432, 227.58601, 89.254, 223.30801);
((GeneralPath)shape).curveTo(88.184, 214.81401, 83.729996, 218.08101, 83.017, 226.516);
((GeneralPath)shape).curveTo(82.303, 231.209, 82.481995, 232.518, 82.481995, 237.03201);
((GeneralPath)shape).curveTo(84.739, 271.191, 139.21799, 256.518, 148.069, 228.29901);
((GeneralPath)shape).curveTo(152.762, 212.67401, 147.296, 201.15001, 149.85, 201.20901);
((GeneralPath)shape).curveTo(155.257, 207.03102, 162.8, 201.98102, 164.464, 199.96101);
((GeneralPath)shape).curveTo(165.17801, 198.95102, 166.961, 198.29901, 168.20801, 199.60501);
((GeneralPath)shape).curveTo(172.425, 202.63701, 179.85301, 201.20801, 181.397, 195.86201);
((GeneralPath)shape).curveTo(182.289, 190.63402, 183.002, 185.22801, 183.18001, 179.64502);
((GeneralPath)shape).curveTo(179.733, 180.71503, 177.18001, 181.42603, 176.942, 182.85301);
((GeneralPath)shape).lineTo(176.229, 187.48601);
((GeneralPath)shape).curveTo(175.933, 188.972, 172.96, 189.03001, 172.843, 187.12901);
((GeneralPath)shape).curveTo(171.536, 181.18802, 166.13, 180.41602, 162.862, 189.62502);
((GeneralPath)shape).curveTo(160.663, 191.40701, 156.684, 191.764, 156.267, 189.09102);
((GeneralPath)shape).curveTo(156.801, 182.91202, 154.306, 182.08002, 149.317, 184.99101);
((GeneralPath)shape).curveTo(147.712, 172.753, 146.10901, 161.05101, 144.504, 148.81201);
((GeneralPath)shape).curveTo(146.584, 148.75201, 148.486, 150.296, 150.388, 147.92001);
((GeneralPath)shape).curveTo(148.306, 141.44601, 143.911, 128.19702, 141.474, 127.24602);
((GeneralPath)shape).curveTo(140.286, 125.821014, 139.274, 126.71202, 137.732, 127.068016);
((GeneralPath)shape).curveTo(135.118, 127.90002, 132.68199, 130.15802, 133.45299, 134.55402);
((GeneralPath)shape).curveTo(136.54298, 153.32602, 138.56299, 167.64401, 141.65298, 186.41702);
((GeneralPath)shape).curveTo(142.12698, 188.61502, 140.28598, 191.52402, 137.90999, 191.22702);
((GeneralPath)shape).curveTo(133.87, 188.49503, 132.86299, 182.97102, 125.969986, 183.20702);
((GeneralPath)shape).curveTo(120.97899, 183.26701, 115.27399, 188.67302, 114.56299, 193.90302);
((GeneralPath)shape).curveTo(113.73099, 198.05902, 113.43299, 202.57301, 114.56099, 206.19902);
((GeneralPath)shape).curveTo(118.06799, 210.41602, 122.28599, 210.00201, 125.96899, 209.05103);
((GeneralPath)shape).curveTo(128.999, 207.80403, 131.49298, 204.77403, 132.56299, 205.48602);
((GeneralPath)shape).curveTo(133.277, 206.37802, 132.73999, 216.35602, 118.303986, 224.02103);
((GeneralPath)shape).curveTo(109.57198, 227.94102, 102.61999, 228.83403, 98.87898, 221.70403);
((GeneralPath)shape).curveTo(96.56198, 217.24803, 99.056984, 200.31703, 93.35198, 204.23903);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_3);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_4 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_4
paint = new Color(255, 255, 255, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(164.91, 160.03);
((GeneralPath)shape).curveTo(168.296, 158.782, 184.339, 140.426, 184.339, 140.426);
((GeneralPath)shape).curveTo(183.50601, 139.713, 182.763, 139.178, 181.931, 138.465);
((GeneralPath)shape).curveTo(181.039, 137.693, 181.129, 136.92099, 181.931, 136.148);
((GeneralPath)shape).curveTo(185.911, 133.832, 184.634, 128.752, 182.555, 126.436);
((GeneralPath)shape).curveTo(179.109, 124.89, 176.109, 125.395996, 173.90999, 126.523994);
((GeneralPath)shape).curveTo(171.11798, 129.198, 170.46599, 133.474, 172.665, 136.148);
((GeneralPath)shape).curveTo(174.805, 137.15799, 176.94199, 139.327, 175.515, 140.515);
((GeneralPath)shape).curveTo(168.952, 147.525, 150.98, 159.615, 153.06, 160.03);
((GeneralPath)shape).curveTo(153.504, 160.624, 164.555, 160.594, 164.91, 160.03);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(68.036, 225.0);
((GeneralPath)shape).curveTo(62.020004, 234.584, 61.496002, 248.903, 64.816, 253.172);
((GeneralPath)shape).curveTo(66.58, 255.189, 69.478004, 256.072, 71.622, 255.441);
((GeneralPath)shape).curveTo(75.402, 253.801, 77.056, 246.14299, 76.159004, 243.34099);
((GeneralPath)shape).curveTo(74.897, 241.36699, 73.90401, 241.05399, 72.644005, 242.734);
((GeneralPath)shape).curveTo(69.984, 248.13399, 68.880005, 244.429, 68.647, 241.411);
((GeneralPath)shape).curveTo(68.239006, 235.68799, 68.778, 230.41699, 69.399, 226.243);
((GeneralPath)shape).curveTo(70.061005, 221.965, 69.389, 223.273, 68.036, 225.0);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(325.12, 209.65);
((GeneralPath)shape).curveTo(319.303, 197.129, 311.25, 184.756, 308.688, 180.00299);
((GeneralPath)shape).curveTo(306.12598, 175.24799, 286.783, 147.176, 283.93597, 144.01898);
((GeneralPath)shape).curveTo(277.65198, 136.55199, 294.13998, 147.12898, 281.84998, 132.30298);
((GeneralPath)shape).curveTo(277.16397, 128.28297, 276.89197, 128.03798, 273.003, 124.75798);
((GeneralPath)shape).curveTo(271.041, 123.36598, 266.25098, 120.82298, 265.40298, 125.03798);
((GeneralPath)shape).curveTo(264.97598, 128.75497, 265.206, 130.76997, 265.83, 133.86497);
((GeneralPath)shape).curveTo(266.31, 135.92798, 269.31497, 139.38298, 270.793, 141.38498);
((GeneralPath)shape).curveTo(290.418, 167.76498, 307.82, 194.40498, 324.601, 227.89998);
((GeneralPath)shape).curveTo(327.251, 226.63998, 326.67102, 211.74498, 325.121, 209.64998);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_4);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_5 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_5
paint = new Color(255, 255, 255, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(299.59, 251.54);
((GeneralPath)shape).curveTo(298.44598, 252.82399, 302.414, 258.31598, 307.57, 258.31);
((GeneralPath)shape).curveTo(316.194, 257.31, 323.785, 252.466, 330.814, 239.715);
((GeneralPath)shape).curveTo(332.694, 236.741, 335.998, 230.381, 336.094, 225.448);
((GeneralPath)shape).curveTo(336.75198, 196.523, 334.647, 174.015, 330.313, 153.108);
((GeneralPath)shape).curveTo(330.03497, 151.072, 330.20398, 148.67801, 330.54898, 148.06801);
((GeneralPath)shape).curveTo(331.10797, 147.401, 333.00098, 148.07101, 334.00598, 146.42401);
((GeneralPath)shape).curveTo(335.47998, 144.919, 330.09198, 132.45401, 327.02, 127.653015);
((GeneralPath)shape).curveTo(325.93, 125.50902, 325.55, 124.07702, 323.744, 127.905014);
((GeneralPath)shape).curveTo(321.845, 131.01501, 320.56998, 136.44301, 320.719, 141.51501);
((GeneralPath)shape).curveTo(324.833, 169.99802, 326.097, 194.91402, 328.785, 223.39702);
((GeneralPath)shape).curveTo(329.005, 226.15102, 328.599, 230.15402, 326.768, 231.75002);
((GeneralPath)shape).curveTo(319.996, 238.82202, 310.22, 247.52701, 299.59, 251.54001);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(416.08, 251.39);
((GeneralPath)shape).curveTo(409.891, 254.967, 409.88397, 259.082, 414.888, 259.231);
((GeneralPath)shape).curveTo(423.511, 258.22998, 433.701, 257.51398, 440.728, 246.902);
((GeneralPath)shape).curveTo(442.609, 243.928, 444.843, 235.887, 444.94, 230.954);
((GeneralPath)shape).curveTo(445.59702, 202.02899, 444.562, 180.439, 440.228, 159.534);
((GeneralPath)shape).curveTo(439.951, 157.497, 439.05, 152.81, 439.39398, 152.19899);
((GeneralPath)shape).curveTo(439.95297, 150.76698, 442.76398, 152.355, 443.76898, 150.70699);
((GeneralPath)shape).curveTo(445.24197, 149.20299, 436.49097, 137.95999, 433.41898, 133.15898);
((GeneralPath)shape).curveTo(432.32898, 131.01598, 431.94897, 129.58398, 430.14398, 133.41098);
((GeneralPath)shape).curveTo(428.244, 136.52197, 427.581, 142.10298, 428.34097, 147.02197);
((GeneralPath)shape).curveTo(432.91397, 177.94997, 436.31796, 201.18497, 437.01996, 228.59697);
((GeneralPath)shape).curveTo(436.62595, 231.19897, 436.52795, 232.60197, 435.30795, 235.87997);
((GeneralPath)shape).curveTo(432.60895, 239.33998, 429.61896, 243.66498, 426.81494, 245.75598);
((GeneralPath)shape).curveTo(424.01193, 247.84598, 418.02994, 249.84198, 416.07996, 251.39098);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_5);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_6 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_6
paint = new Color(255, 255, 255, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(420.72, 223.66);
((GeneralPath)shape).curveTo(420.649, 216.427, 420.822, 210.181, 420.584, 204.787);
((GeneralPath)shape).curveTo(420.346, 199.392, 419.393, 194.99901, 417.526, 191.17);
((GeneralPath)shape).curveTo(415.759, 187.064, 416.856, 183.765, 416.026, 179.39099);
((GeneralPath)shape).curveTo(415.197, 175.019, 415.401, 168.471, 414.148, 163.28299);
((GeneralPath)shape).curveTo(413.80502, 161.25699, 412.752, 154.76799, 413.078, 154.146);
((GeneralPath)shape).curveTo(413.588, 152.698, 415.534, 154.191, 416.484, 152.513);
((GeneralPath)shape).curveTo(417.907, 150.961, 411.547, 134.505, 408.321, 129.806);
((GeneralPath)shape).curveTo(407.161, 127.699, 405.05402, 128.421, 402.457, 131.846);
((GeneralPath)shape).curveTo(400.047, 134.101, 400.941, 139.24199, 401.861, 144.13199);
((GeneralPath)shape).curveTo(408.04898, 176.42299, 412.664, 205.65, 411.65698, 236.38899);
((GeneralPath)shape).curveTo(411.348, 239.00299, 420.784, 228.594, 420.71997, 223.65999);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(375.0, 183.61);
((GeneralPath)shape).curveTo(371.108, 183.539, 362.956, 176.036, 360.577, 171.64);
((GeneralPath)shape).curveTo(359.678, 169.119, 360.255, 166.659, 361.047, 165.218);
((GeneralPath)shape).curveTo(362.488, 164.281, 364.706, 163.228, 366.363, 164.238);
((GeneralPath)shape).curveTo(366.363, 164.238, 368.082, 166.64201, 367.749, 166.94801);
((GeneralPath)shape).curveTo(369.874, 167.96602, 370.776, 167.38002, 370.992, 166.516);
((GeneralPath)shape).curveTo(371.137, 165.002, 370.364, 164.1, 370.355, 162.43301);
((GeneralPath)shape).curveTo(371.256, 157.91301, 376.423, 157.21, 378.36902, 160.09302);
((GeneralPath)shape).curveTo(379.79303, 161.85002, 380.31503, 165.58502, 380.531, 168.10602);
((GeneralPath)shape).curveTo(380.508, 169.39401, 378.422, 167.88301, 377.238, 168.19302);
((GeneralPath)shape).curveTo(376.052, 168.50302, 375.767, 169.87102, 375.67502, 171.10802);
((GeneralPath)shape).curveTo(375.46002, 174.38602, 375.07202, 179.64601, 375.00003, 183.60902);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(303.17, 231.68);
((GeneralPath)shape).curveTo(304.242, 221.85199, 302.77402, 204.349, 302.67603, 198.54999);
((GeneralPath)shape).curveTo(302.282, 184.84999, 300.045, 158.38799, 298.98, 153.96698);
((GeneralPath)shape).curveTo(297.78, 145.63397, 302.407, 154.88098, 301.76602, 150.03699);
((GeneralPath)shape).curveTo(300.26602, 141.71599, 295.65002, 136.07498, 290.22403, 128.45299);
((GeneralPath)shape).curveTo(288.47403, 125.972984, 288.53403, 125.46799, 285.83304, 129.06099);
((GeneralPath)shape).curveTo(282.84305, 135.84099, 285.42303, 140.50598, 286.19604, 145.78699);
((GeneralPath)shape).curveTo(290.10904, 162.99298, 292.39206, 178.82399, 293.45505, 194.47398);
((GeneralPath)shape).curveTo(294.51804, 210.12398, 294.84506, 227.04599, 293.87405, 243.52899);
((GeneralPath)shape).curveTo(296.80606, 243.644, 301.52106, 238.78499, 303.17004, 231.67899);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_6);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_7 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_7
paint = new Color(255, 255, 255, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(433.97, 215.94);
((GeneralPath)shape).curveTo(427.107, 204.425, 416.751, 191.953, 413.984, 187.31601);
((GeneralPath)shape).curveTo(411.21802, 182.67801, 387.818, 152.48601, 384.836, 149.45601);
((GeneralPath)shape).curveTo(376.276, 140.46301, 388.762, 147.992, 383.197, 141.046);
((GeneralPath)shape).curveTo(378.49, 135.878, 377.117, 134.25601, 373.089, 131.14801);
((GeneralPath)shape).curveTo(371.069, 129.84302, 369.845, 127.35001, 369.181, 131.598);
((GeneralPath)shape).curveTo(368.917, 135.33, 368.641, 139.64801, 368.892, 142.793);
((GeneralPath)shape).curveTo(368.878, 144.54199, 370.699, 147.827, 372.262, 149.763);
((GeneralPath)shape).curveTo(393.017, 175.263, 415.655, 201.3, 433.879, 234.03299);
((GeneralPath)shape).curveTo(436.472, 232.65799, 435.61, 217.96599, 433.97, 215.93999);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_7);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_8 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_8
paint = new Color(27, 164, 0, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(122.59, 194.69);
((GeneralPath)shape).curveTo(122.09599, 195.556, 120.995995, 196.67801, 121.365, 197.839);
((GeneralPath)shape).curveTo(122.134995, 198.88301, 122.751, 199.095, 124.034996, 199.15201);
((GeneralPath)shape).curveTo(125.148994, 199.15201, 126.704994, 199.41501, 127.03999, 198.75801);
((GeneralPath)shape).curveTo(127.63999, 198.098, 128.09499, 196.74901, 127.59599, 195.477);
((GeneralPath)shape).curveTo(126.43599, 192.57701, 123.19599, 193.657, 122.59, 194.69);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_8);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_9 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_9
paint = new Color(255, 255, 255, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(354.17, 362.54);
((GeneralPath)shape).curveTo(363.34802, 362.87802, 369.355, 362.959, 377.518, 363.93002);
((GeneralPath)shape).curveTo(377.518, 363.93002, 384.558, 363.23502, 387.071, 362.855);
((GeneralPath)shape).curveTo(397.69702, 361.841, 398.16702, 378.031, 398.16702, 378.031);
((GeneralPath)shape).curveTo(398.05103, 387.526, 394.38602, 388.023, 389.70703, 389.026);
((GeneralPath)shape).curveTo(387.03403, 389.366, 385.62903, 387.425, 384.22403, 385.358);
((GeneralPath)shape).curveTo(382.46103, 386.096, 380.06403, 386.202, 377.16003, 385.799);
((GeneralPath)shape).curveTo(373.33502, 385.55902, 369.51004, 385.575, 365.68604, 385.336);
((GeneralPath)shape).curveTo(361.62305, 384.978, 359.46204, 385.759, 355.39804, 385.401);
((GeneralPath)shape).curveTo(354.56104, 386.716, 353.47305, 388.537, 350.98804, 387.951);
((GeneralPath)shape).curveTo(348.92004, 387.722, 346.47104, 381.918, 347.19205, 377.50497);
((GeneralPath)shape).curveTo(348.68506, 374.34097, 348.27505, 375.35898, 348.11905, 373.968);
((GeneralPath)shape).curveTo(310.59406, 373.012, 272.70905, 371.339, 235.89905, 371.818);
((GeneralPath)shape).curveTo(207.09904, 371.93698, 178.65504, 373.132, 150.21205, 374.328);
((GeneralPath)shape).curveTo(135.03505, 374.088, 123.443054, 371.698, 115.43605, 359.986);
((GeneralPath)shape).curveTo(116.15305, 359.986, 154.15605, 362.137, 165.27106, 361.41998);
((GeneralPath)shape).curveTo(185.82605, 361.18, 204.58806, 359.508, 225.50206, 358.90997);
((GeneralPath)shape).curveTo(266.73206, 359.62698, 307.60504, 359.62796, 348.83206, 362.49496);
((GeneralPath)shape).curveTo(344.88806, 359.79697, 344.74707, 353.42395, 350.81705, 351.86597);
((GeneralPath)shape).curveTo(351.33304, 351.51096, 351.59906, 355.03497, 352.50705, 354.96896);
((GeneralPath)shape).curveTo(357.36404, 354.60495, 355.23404, 361.18295, 354.16605, 362.53897);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(188.64, 135.28);
((GeneralPath)shape).curveTo(182.392, 153.13899, 192.22, 172.674, 199.034, 170.772);
((GeneralPath)shape).curveTo(203.95, 172.807, 207.08, 163.455, 209.093, 153.209);
((GeneralPath)shape).curveTo(210.47, 150.333, 211.51001, 150.027, 212.217, 151.505);
((GeneralPath)shape).curveTo(212.036, 165.13, 213.196, 168.14801, 216.69899, 172.284);
((GeneralPath)shape).curveTo(224.51299, 178.313, 230.97699, 173.054, 231.484, 172.54599);
((GeneralPath)shape).lineTo(237.568, 166.46199);
((GeneralPath)shape).curveTo(238.922, 165.03699, 240.72499, 164.954, 242.638, 166.20898);
((GeneralPath)shape).curveTo(244.497, 167.89899, 244.23601, 170.81699, 248.215, 172.84099);
((GeneralPath)shape).curveTo(251.565, 174.18098, 258.725, 173.15099, 260.385, 170.26498);
((GeneralPath)shape).curveTo(262.617, 166.43898, 263.153, 165.12498, 264.187, 163.67398);
((GeneralPath)shape).curveTo(265.78, 161.55298, 268.497, 162.49599, 268.497, 163.16599);
((GeneralPath)shape).curveTo(268.243, 164.34898, 266.64902, 165.53299, 267.737, 167.66298);
((GeneralPath)shape).curveTo(269.631, 169.08398, 270.069, 168.16998, 271.19, 167.85397);
((GeneralPath)shape).curveTo(275.154, 165.95897, 278.13, 157.33597, 278.13, 157.33597);
((GeneralPath)shape).curveTo(278.306, 154.12798, 276.508, 154.39297, 275.342, 155.05397);
((GeneralPath)shape).curveTo(273.821, 155.98396, 273.72202, 156.28098, 272.2, 157.21097);
((GeneralPath)shape).curveTo(270.26202, 157.49896, 266.502, 158.78397, 264.643, 155.90598);
((GeneralPath)shape).curveTo(262.745, 152.44498, 262.719, 147.61598, 261.268, 144.12598);
((GeneralPath)shape).curveTo(261.268, 143.87198, 258.75, 138.62798, 261.094, 138.29198);
((GeneralPath)shape).curveTo(262.276, 138.51198, 264.8, 139.17998, 265.201, 137.05498);
((GeneralPath)shape).curveTo(266.442, 134.98297, 262.54498, 129.11697, 259.878, 126.15398);
((GeneralPath)shape).curveTo(257.563, 123.61198, 254.355, 123.30598, 251.258, 125.90098);
((GeneralPath)shape).curveTo(249.09, 127.89598, 249.402, 130.12498, 248.976, 132.23798);
((GeneralPath)shape).curveTo(248.424, 134.66397, 248.54199, 137.64897, 251.005, 140.85797);
((GeneralPath)shape).curveTo(253.169, 145.12497, 257.118, 150.62097, 255.821, 158.34897);
((GeneralPath)shape).curveTo(255.821, 158.34897, 253.515, 162.00197, 249.496, 161.52397);
((GeneralPath)shape).curveTo(247.82, 161.15897, 245.104, 160.44797, 243.653, 149.72997);
((GeneralPath)shape).curveTo(242.554, 141.61797, 243.912, 130.26697, 240.469, 124.94597);
((GeneralPath)shape).curveTo(239.22499, 121.73297, 238.317, 118.62997, 235.286, 124.12597);
((GeneralPath)shape).curveTo(234.472, 126.283966, 230.97699, 129.55898, 233.512, 136.29497);
((GeneralPath)shape).curveTo(235.586, 140.56497, 236.43, 147.51497, 235.489, 155.24896);
((GeneralPath)shape).curveTo(234.052, 157.44597, 233.733, 158.18996, 231.849, 160.38797);
((GeneralPath)shape).curveTo(229.203, 163.23297, 226.331, 162.50697, 224.132, 161.44397);
((GeneralPath)shape).curveTo(222.07701, 160.05898, 220.468, 159.34297, 219.53, 154.94296);
((GeneralPath)shape).curveTo(219.7, 147.92996, 220.09, 136.44896, 218.808, 134.01396);
((GeneralPath)shape).curveTo(216.918, 130.24196, 213.802, 131.60596, 212.47, 132.74596);
((GeneralPath)shape).curveTo(206.073, 138.59496, 202.913, 148.46396, 200.981, 156.32295);
((GeneralPath)shape).curveTo(199.207, 162.04996, 197.319, 160.40895, 195.991, 158.09795);
((GeneralPath)shape).curveTo(192.761, 155.06995, 192.541, 131.38794, 188.64, 135.28195);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_9);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_10 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_10
paint = new Color(255, 255, 255, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(207.45, 174.1);
((GeneralPath)shape).curveTo(210.287, 172.09001, 208.961, 170.686, 213.204, 174.92801);
((GeneralPath)shape).curveTo(218.51599, 184.01001, 221.931, 195.77301, 222.443, 206.19402);
((GeneralPath)shape).curveTo(222.219, 208.76201, 224.02899, 210.38701, 224.857, 209.82901);
((GeneralPath)shape).curveTo(225.349, 203.8, 240.01, 195.40001, 253.453, 194.17201);
((GeneralPath)shape).curveTo(255.509, 193.725, 254.509, 189.78502, 254.84401, 187.77602);
((GeneralPath)shape).curveTo(254.03801, 180.31302, 259.038, 173.51802, 266.04602, 172.97601);
((GeneralPath)shape).curveTo(275.584, 174.38602, 278.75903, 179.47801, 278.92, 187.251);
((GeneralPath)shape).curveTo(277.89, 202.173, 262.345, 204.703, 253.61101, 205.893);
((GeneralPath)shape).curveTo(252.27101, 206.40001, 251.71301, 207.018, 253.61101, 207.74901);
((GeneralPath)shape).lineTo(290.206, 207.91501);
((GeneralPath)shape).lineTo(292.073, 208.99301);
((GeneralPath)shape).curveTo(292.297, 209.86601, 291.54, 209.13802, 290.095, 211.51701);
((GeneralPath)shape).curveTo(288.651, 213.89601, 286.52, 219.38202, 286.41, 223.04501);
((GeneralPath)shape).curveTo(275.50702, 226.55101, 264.23, 228.087, 252.768, 229.473);
((GeneralPath)shape).curveTo(248.78601, 231.487, 246.81201, 234.17001, 247.63, 237.19);
((GeneralPath)shape).curveTo(248.97, 240.54001, 257.79, 243.886, 257.79, 244.045);
((GeneralPath)shape).curveTo(259.465, 245.095, 261.44702, 247.547, 257.316, 252.56999);
((GeneralPath)shape).curveTo(239.466, 251.78499, 225.63301, 244.18799, 220.84401, 233.46599);
((GeneralPath)shape).curveTo(219.40102, 232.34698, 217.84702, 233.45999, 216.85101, 234.90898);
((GeneralPath)shape).curveTo(209.88202, 243.89297, 203.02501, 251.98299, 191.143, 256.27798);
((GeneralPath)shape).curveTo(184.057, 258.04797, 176.804, 255.19098, 173.378, 250.55098);
((GeneralPath)shape).curveTo(171.085, 247.90997, 171.17201, 244.99397, 170.33, 244.36198);
((GeneralPath)shape).curveTo(166.5, 246.05598, 133.545, 260.059, 137.722, 253.53598);
((GeneralPath)shape).curveTo(145.742, 244.95097, 159.628, 238.64598, 171.89099, 230.17297);
((GeneralPath)shape).curveTo(172.77599, 227.33397, 174.385, 217.73097, 179.228, 214.60297);
((GeneralPath)shape).curveTo(179.508, 214.62596, 178.461, 220.24297, 178.566, 222.61096);
((GeneralPath)shape).curveTo(178.62099, 224.55496, 178.42299, 225.31696, 178.849, 224.81696);
((GeneralPath)shape).curveTo(179.676, 224.28896, 194.556, 212.59695, 195.706, 209.01196);
((GeneralPath)shape).curveTo(197.15599, 206.95596, 196.14099, 201.74896, 196.14099, 201.59096);
((GeneralPath)shape).curveTo(193.351, 194.39896, 189.43599, 193.78796, 187.98499, 190.21495);
((GeneralPath)shape).curveTo(186.67899, 185.46796, 187.26999, 180.04996, 189.98299, 178.53995);
((GeneralPath)shape).curveTo(192.39299, 176.35295, 195.24599, 176.62294, 197.87799, 179.01295);
((GeneralPath)shape).curveTo(200.88399, 181.70496, 203.553, 186.96596, 204.325, 190.88495);
((GeneralPath)shape).curveTo(203.80899, 192.43495, 200.392, 189.85495, 199.207, 190.62495);
((GeneralPath)shape).curveTo(201.30501, 192.79695, 202.284, 195.30295, 203.042, 198.36896);
((GeneralPath)shape).curveTo(204.98001, 206.57796, 204.389, 209.76895, 202.438, 215.07697);
((GeneralPath)shape).curveTo(195.832, 228.96696, 187.389, 233.11197, 180.002, 238.25797);
((GeneralPath)shape).curveTo(179.805, 238.32797, 179.674, 241.78296, 182.452, 243.65097);
((GeneralPath)shape).curveTo(183.40999, 244.65697, 187.263, 245.17297, 191.79399, 243.72098);
((GeneralPath)shape).curveTo(200.549, 238.94698, 209.637, 230.15198, 214.14899, 220.35397);
((GeneralPath)shape).curveTo(215.45898, 212.94298, 213.64299, 205.08397, 211.71498, 198.22998);
((GeneralPath)shape).curveTo(208.81198, 191.53198, 205.39297, 181.96799, 205.39297, 181.80998);
((GeneralPath)shape).curveTo(205.27997, 177.63298, 205.61998, 176.18398, 207.45198, 174.09998);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(111.64, 135.47);
((GeneralPath)shape).curveTo(115.852, 137.479, 123.770996, 136.62401, 123.436, 129.832);
((GeneralPath)shape).curveTo(123.436, 129.232, 123.283, 127.2, 123.223, 126.649);
((GeneralPath)shape).curveTo(122.363, 124.647, 120.018, 125.142, 119.486, 127.209);
((GeneralPath)shape).curveTo(119.319, 127.886, 119.784, 128.989, 119.171, 129.329);
((GeneralPath)shape).curveTo(118.819, 129.68399, 117.473, 129.47499, 117.529, 127.59899);
((GeneralPath)shape).curveTo(117.529, 127.00099, 117.089, 126.35899, 116.823, 125.97899);
((GeneralPath)shape).curveTo(116.557, 125.804985, 116.388, 125.75699, 115.906, 125.75699);
((GeneralPath)shape).curveTo(115.318, 125.77999, 115.327995, 125.93299, 115.006, 126.42699);
((GeneralPath)shape).curveTo(114.868996, 126.928986, 114.681, 127.41998, 114.681, 127.98999);
((GeneralPath)shape).curveTo(114.607, 128.65799, 114.355, 128.896, 113.864, 128.995);
((GeneralPath)shape).curveTo(113.314995, 128.995, 113.438995, 129.055, 112.993996, 128.77199);
((GeneralPath)shape).curveTo(112.729996, 128.482, 112.399994, 128.372, 112.399994, 127.87899);
((GeneralPath)shape).curveTo(112.399994, 127.36799, 112.284, 126.54099, 112.13, 126.20399);
((GeneralPath)shape).curveTo(111.896996, 125.89599, 111.521996, 125.75399, 111.1, 125.64499);
((GeneralPath)shape).curveTo(108.799995, 125.65299, 108.64, 128.277, 108.772995, 129.273);
((GeneralPath)shape).curveTo(108.6, 129.461, 108.503, 134.168, 111.63999, 135.47);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_10);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_11 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_11
paint = new Color(255, 255, 255, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(235.11, 187.73);
((GeneralPath)shape).curveTo(239.322, 189.739, 249.348, 188.583, 246.906, 182.092);
((GeneralPath)shape).curveTo(246.906, 181.49199, 246.753, 179.459, 246.69301, 178.909);
((GeneralPath)shape).curveTo(245.83301, 176.907, 243.488, 177.402, 242.95601, 179.469);
((GeneralPath)shape).curveTo(242.789, 180.146, 243.25401, 181.249, 242.641, 181.58899);
((GeneralPath)shape).curveTo(242.289, 181.94398, 240.94301, 181.73499, 240.99901, 179.859);
((GeneralPath)shape).curveTo(240.99901, 179.26099, 240.559, 178.61899, 240.29301, 178.239);
((GeneralPath)shape).curveTo(240.02701, 178.065, 239.85802, 178.017, 239.376, 178.017);
((GeneralPath)shape).curveTo(238.78801, 178.04, 238.798, 178.193, 238.47601, 178.687);
((GeneralPath)shape).curveTo(238.33902, 179.189, 238.15102, 179.68, 238.15102, 180.25);
((GeneralPath)shape).curveTo(238.07701, 180.918, 237.82501, 181.156, 237.33401, 181.255);
((GeneralPath)shape).curveTo(236.78502, 181.255, 236.90901, 181.315, 236.46402, 181.032);
((GeneralPath)shape).curveTo(236.20001, 180.742, 235.87003, 180.632, 235.87003, 180.13899);
((GeneralPath)shape).curveTo(235.87003, 179.62799, 235.75403, 178.801, 235.60002, 178.46399);
((GeneralPath)shape).curveTo(235.36702, 178.15599, 234.99202, 178.01399, 234.57002, 177.90498);
((GeneralPath)shape).curveTo(232.27002, 177.91298, 232.11002, 180.53699, 232.24303, 181.53299);
((GeneralPath)shape).curveTo(232.07002, 181.721, 231.97302, 186.428, 235.11003, 187.73);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(307.11, 166.1);
((GeneralPath)shape).curveTo(311.322, 168.10901, 319.241, 167.25401, 318.90598, 160.462);
((GeneralPath)shape).curveTo(318.90598, 159.862, 318.75296, 157.83, 318.69296, 157.279);
((GeneralPath)shape).curveTo(317.83298, 155.27701, 315.48798, 155.772, 314.95596, 157.839);
((GeneralPath)shape).curveTo(314.78897, 158.516, 315.25397, 159.619, 314.64096, 159.959);
((GeneralPath)shape).curveTo(314.28897, 160.314, 312.94296, 160.105, 312.99896, 158.229);
((GeneralPath)shape).curveTo(312.99896, 157.631, 312.55896, 156.989, 312.29297, 156.60901);
((GeneralPath)shape).curveTo(312.02698, 156.43501, 311.85797, 156.38701, 311.37598, 156.38701);
((GeneralPath)shape).curveTo(310.78796, 156.41, 310.79797, 156.563, 310.47598, 157.057);
((GeneralPath)shape).curveTo(310.339, 157.559, 310.15, 158.05, 310.15, 158.62001);
((GeneralPath)shape).curveTo(310.077, 159.28801, 309.82498, 159.52602, 309.33398, 159.62502);
((GeneralPath)shape).curveTo(308.78497, 159.62502, 308.909, 159.68501, 308.464, 159.40201);
((GeneralPath)shape).curveTo(308.19998, 159.11201, 307.869, 159.00201, 307.869, 158.509);
((GeneralPath)shape).curveTo(307.869, 157.998, 307.754, 157.171, 307.59998, 156.834);
((GeneralPath)shape).curveTo(307.36697, 156.526, 306.99197, 156.384, 306.56998, 156.275);
((GeneralPath)shape).curveTo(304.27, 156.28299, 304.11, 158.907, 304.24298, 159.903);
((GeneralPath)shape).curveTo(304.06998, 160.091, 303.973, 164.798, 307.11, 166.1);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(344.4, 220.43);
((GeneralPath)shape).curveTo(337.06, 228.70299, 340.296, 242.385, 341.95398, 245.333);
((GeneralPath)shape).curveTo(344.374, 250.17499, 346.32297, 253.28, 351.03198, 255.67499);
((GeneralPath)shape).curveTo(355.322, 258.832, 358.66397, 256.85898, 360.50598, 254.648);
((GeneralPath)shape).curveTo(364.82297, 250.17499, 364.874, 238.754, 366.9, 236.491);
((GeneralPath)shape).curveTo(368.322, 232.334, 371.9, 233.04399, 373.637, 234.886);
((GeneralPath)shape).curveTo(375.32, 237.306, 377.30298, 238.866, 379.777, 240.184);
((GeneralPath)shape).curveTo(383.802, 243.73601, 388.61002, 244.384, 393.34702, 241.14801);
((GeneralPath)shape).curveTo(396.583, 239.33202, 398.687, 236.98601, 400.582, 232.32901);
((GeneralPath)shape).curveTo(402.688, 226.699, 401.514, 200.68102, 401.093, 185.26102);
((GeneralPath)shape).curveTo(400.931, 184.05101, 396.909, 164.05602, 396.909, 163.83302);
((GeneralPath)shape).curveTo(396.909, 163.60902, 396.37698, 153.62802, 395.935, 151.25003);
((GeneralPath)shape).curveTo(395.857, 150.28503, 395.618, 150.00803, 396.629, 150.13004);
((GeneralPath)shape).curveTo(397.702, 151.03304, 397.849, 151.09004, 398.519, 151.38704);
((GeneralPath)shape).curveTo(399.601, 151.58504, 400.57, 149.74203, 399.91702, 148.04704);
((GeneralPath)shape).lineTo(389.86902, 129.51404);
((GeneralPath)shape).curveTo(389.062, 128.72003, 388.02103, 127.84804, 386.742, 129.73804);
((GeneralPath)shape).curveTo(385.52002, 130.80904, 384.219, 132.74803, 384.259, 135.24104);
((GeneralPath)shape).curveTo(384.556, 139.63304, 385.329, 144.10104, 385.625, 148.49405);
((GeneralPath)shape).lineTo(389.645, 171.04605);
((GeneralPath)shape).curveTo(390.91098, 187.12206, 391.228, 200.27905, 392.49298, 216.35504);
((GeneralPath)shape).curveTo(392.31598, 223.16205, 390.19998, 229.09705, 388.21497, 229.95303);
((GeneralPath)shape).curveTo(388.21497, 229.95303, 385.19498, 231.70303, 383.16995, 229.77003);
((GeneralPath)shape).curveTo(381.69696, 229.17903, 375.80194, 219.94504, 375.80194, 219.94504);
((GeneralPath)shape).curveTo(372.78793, 217.18204, 370.80093, 217.97203, 368.65594, 219.94504);
((GeneralPath)shape).curveTo(362.74194, 225.65604, 360.06595, 236.33904, 356.04694, 243.70804);
((GeneralPath)shape).curveTo(355.01093, 245.35304, 352.08194, 246.76004, 348.83694, 243.58804);
((GeneralPath)shape).curveTo(340.59494, 232.33005, 345.42593, 216.31104, 344.39993, 220.43004);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(309.0, 126.67);
((GeneralPath)shape).curveTo(312.773, 128.25, 315.435, 135.893, 314.567, 139.625);
((GeneralPath)shape).curveTo(313.81598, 144.242, 311.81497, 149.228, 310.37598, 148.579);
((GeneralPath)shape).curveTo(308.81198, 147.999, 311.442, 143.989, 309.93896, 139.78299);
((GeneralPath)shape).curveTo(309.10397, 137.04498, 303.95496, 132.04199, 304.49698, 130.56699);
((GeneralPath)shape).curveTo(303.434, 127.47699, 306.68597, 126.124985, 308.99997, 126.66998);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_11);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_12 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_12
paint = new Color(255, 255, 255, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(356.55, 224.96);
((GeneralPath)shape).curveTo(357.344, 215.781, 356.004, 210.184, 355.766, 204.789);
((GeneralPath)shape).curveTo(355.52798, 199.394, 349.664, 158.23001, 348.475, 154.14801);
((GeneralPath)shape).curveTo(347.04, 146.43001, 354.17502, 153.113, 353.39502, 148.62401);
((GeneralPath)shape).curveTo(350.92703, 142.964, 344.78403, 134.72401, 342.855, 129.80801);
((GeneralPath)shape).curveTo(341.695, 127.70201, 342.182, 125.82801, 339.584, 129.25401);
((GeneralPath)shape).curveTo(337.17502, 137.13002, 336.33902, 143.56801, 337.259, 148.45901);
((GeneralPath)shape).curveTo(343.446, 180.74902, 349.792, 207.59901, 348.785, 238.33801);
((GeneralPath)shape).curveTo(351.719, 238.35802, 355.101, 231.62401, 356.55002, 224.96101);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(421.02, 139.68);
((GeneralPath)shape).curveTo(424.46, 141.39, 426.47498, 150.969, 426.095, 153.706);
((GeneralPath)shape).curveTo(425.411, 158.70499, 423.585, 164.103, 422.275, 163.4);
((GeneralPath)shape).curveTo(420.849, 162.77199, 422.56, 155.987, 421.875, 153.877);
((GeneralPath)shape).curveTo(421.115, 150.913, 416.421, 145.497, 416.915, 143.9);
((GeneralPath)shape).curveTo(415.945, 140.555, 418.91, 139.09099, 421.02002, 139.68);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(165.35, 207.6);
((GeneralPath)shape).curveTo(168.64, 208.856, 170.57, 215.895, 170.20601, 217.90701);
((GeneralPath)shape).curveTo(169.55, 221.57901, 167.80301, 225.54602, 166.55, 225.03001);
((GeneralPath)shape).curveTo(165.186, 224.56801, 166.824, 219.58202, 166.169, 218.03201);
((GeneralPath)shape).curveTo(165.891, 214.27202, 161.324, 212.32501, 161.419, 210.70102);
((GeneralPath)shape).curveTo(160.567, 207.71503, 163.33, 207.16602, 165.35, 207.60101);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_12);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_13 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_13
paint = new Color(27, 157, 0, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(244.86, 218.17);
((GeneralPath)shape).curveTo(249.107, 218.44, 251.23, 221.772, 247.251, 223.17);
((GeneralPath)shape).curveTo(243.32701, 224.513, 239.556, 225.563, 239.53801, 231.234);
((GeneralPath)shape).curveTo(240.99, 239.13599, 237.548, 236.424, 235.494, 235.351);
((GeneralPath)shape).curveTo(233.075, 233.614, 226.28601, 229.431, 225.31801, 220.401);
((GeneralPath)shape).curveTo(225.173, 218.246, 226.85301, 216.432, 229.55801, 216.445);
((GeneralPath)shape).curveTo(233.63301, 217.552, 239.64502, 217.63301, 244.85901, 218.17001);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_13);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_14 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_14
paint = new Color(255, 255, 255, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(77.399, 124.39);
((GeneralPath)shape).curveTo(82.254005, 125.854, 82.541, 132.99, 82.183, 135.076);
((GeneralPath)shape).curveTo(81.535995, 138.884, 79.816, 142.996, 78.581, 142.46);
((GeneralPath)shape).curveTo(77.237, 141.98201, 78.528, 136.813, 77.883, 135.205);
((GeneralPath)shape).curveTo(77.163, 132.947, 73.061005, 128.821, 73.526, 127.605);
((GeneralPath)shape).curveTo(72.613, 125.056, 75.409004, 123.94, 77.399, 124.39001);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(173.28, 158.03);
((GeneralPath)shape).curveTo(169.555, 160.045, 168.11, 166.05, 170.43, 169.548);
((GeneralPath)shape).curveTo(172.596, 172.628, 176.017, 171.48601, 176.47299, 171.48601);
((GeneralPath)shape).curveTo(180.12299, 171.94301, 182.288, 164.64401, 182.288, 164.64401);
((GeneralPath)shape).curveTo(182.288, 164.64401, 182.403, 162.59201, 178.069, 166.47002);
((GeneralPath)shape).curveTo(176.245, 166.81201, 176.017, 166.12701, 175.561, 165.10002);
((GeneralPath)shape).curveTo(175.181, 163.20003, 175.257, 161.30002, 176.13101, 159.39902);
((GeneralPath)shape).curveTo(176.77701, 157.57501, 175.37001, 156.77602, 173.28001, 158.03001);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(201.22, 121.63);
((GeneralPath)shape).curveTo(199.348, 122.884995, 195.622, 126.744995, 195.508, 131.193);
((GeneralPath)shape).curveTo(195.39499, 133.702, 194.928, 133.694, 196.56999, 135.291);
((GeneralPath)shape).curveTo(197.75699, 137.003, 198.954, 136.849, 201.34999, 135.594);
((GeneralPath)shape).curveTo(202.726, 134.57999, 203.191, 133.907, 203.65498, 132.207);
((GeneralPath)shape).curveTo(204.22499, 129.357, 200.64099, 133.559, 200.18498, 130.387);
((GeneralPath)shape).curveTo(199.38698, 127.440994, 201.68898, 126.23499, 203.85498, 123.384995);
((GeneralPath)shape).curveTo(203.92699, 121.43199, 203.88498, 120.048996, 201.21999, 121.63);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(223.73, 125.63);
((GeneralPath)shape).curveTo(222.92299, 127.411995, 221.956, 136.72299, 222.11699, 136.72299);
((GeneralPath)shape).curveTo(221.47299, 139.497, 225.01999, 140.68399, 226.633, 137.118);
((GeneralPath)shape).curveTo(229.052, 130.581, 229.052, 127.808, 229.213, 125.034);
((GeneralPath)shape).curveTo(228.45999, 120.81, 225.612, 120.94199, 223.73, 125.63);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(365.65, 197.85);
((GeneralPath)shape).curveTo(366.134, 197.36601, 385.64798, 183.49701, 385.64798, 183.49701);
((GeneralPath)shape).curveTo(387.637, 182.798, 387.206, 190.647, 386.29297, 190.593);
((GeneralPath)shape).curveTo(386.66898, 192.151, 367.04797, 205.483, 365.64896, 204.946);
((GeneralPath)shape).curveTo(364.68195, 205.645, 363.71396, 199.571, 365.64896, 197.85);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(383.44, 197.73);
((GeneralPath)shape).curveTo(386.88, 199.44, 388.25, 209.50299, 387.87, 212.23999);
((GeneralPath)shape).curveTo(387.992, 217.562, 384.555, 221.82999, 383.24298, 221.12799);
((GeneralPath)shape).curveTo(381.817, 220.49998, 383.36798, 214.521, 382.68298, 212.411);
((GeneralPath)shape).curveTo(381.92297, 209.44699, 379.00198, 203.86899, 379.495, 202.272);
((GeneralPath)shape).curveTo(378.526, 198.927, 381.33, 197.141, 383.44, 197.73001);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(267.37, 241.07);
((GeneralPath)shape).curveTo(268.727, 239.08601, 272.91998, 236.231, 273.01498, 236.231);
((GeneralPath)shape).curveTo(274.94897, 235.263, 276.839, 236.99, 276.72498, 236.876);
((GeneralPath)shape).curveTo(277.046, 238.81201, 275.49997, 240.613, 275.985, 243.194);
((GeneralPath)shape).curveTo(276.40698, 244.234, 276.71698, 245.389, 278.623, 244.949);
((GeneralPath)shape).curveTo(281.722, 242.511, 284.593, 242.354, 287.69098, 242.199);
((GeneralPath)shape).curveTo(290.06497, 242.34201, 290.15897, 246.362, 288.658, 246.392);
((GeneralPath)shape).curveTo(282.93698, 247.635, 280.373, 249.16699, 276.288, 250.726);
((GeneralPath)shape).curveTo(274.352, 251.855, 272.692, 250.423, 272.692, 250.262);
((GeneralPath)shape).curveTo(272.692, 250.10098, 271.56, 249.16399, 272.34998, 246.59999);
((GeneralPath)shape).curveTo(272.49, 244.54399, 271.66296, 243.41699, 269.94998, 243.65);
((GeneralPath)shape).curveTo(268.65997, 244.34799, 267.524, 244.81, 266.87897, 243.327);
((GeneralPath)shape).curveTo(266.62497, 242.232, 266.54196, 241.69899, 267.36996, 241.06999);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(403.97, 246.49);
((GeneralPath)shape).curveTo(404.806, 247.554, 405.34702, 248.539, 403.903, 250.287);
((GeneralPath)shape).curveTo(402.53403, 251.541, 401.57, 252.23201, 400.20203, 253.48601);
((GeneralPath)shape).curveTo(399.55603, 254.58801, 399.14203, 256.26, 401.11902, 256.793);
((GeneralPath)shape).curveTo(404.769, 257.819, 413.20703, 252.345, 413.20703, 252.231);
((GeneralPath)shape).curveTo(414.57602, 251.205, 414.12003, 249.266, 414.00504, 249.266);
((GeneralPath)shape).curveTo(413.20703, 248.354, 411.40903, 248.89601, 410.20004, 248.748);
((GeneralPath)shape).curveTo(409.62405, 248.748, 407.73505, 248.462, 408.63403, 246.79001);
((GeneralPath)shape).curveTo(409.38403, 245.75002, 409.65305, 245.113, 410.16205, 243.83101);
((GeneralPath)shape).curveTo(410.73206, 242.57701, 410.24203, 241.74101, 408.18906, 241.05602);
((GeneralPath)shape).curveTo(406.09906, 240.67601, 405.26306, 240.86601, 402.94406, 241.05602);
((GeneralPath)shape).curveTo(401.69006, 241.32202, 401.26205, 241.88301, 401.03406, 243.40302);
((GeneralPath)shape).curveTo(401.12405, 245.70901, 402.52606, 245.57802, 403.97006, 246.49002);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_14);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_15 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_15
paint = new Color(37, 159, 0, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(268.117, 189.743);
((GeneralPath)shape).curveTo(267.582, 190.668, 265.773, 190.623, 264.077, 189.64299);
((GeneralPath)shape).curveTo(262.38098, 188.66298, 261.439, 187.116, 261.974, 186.18999);
((GeneralPath)shape).curveTo(262.509, 185.26398, 264.318, 185.30998, 266.014, 186.29199);
((GeneralPath)shape).curveTo(267.71002, 187.274, 268.652, 188.818, 268.117, 189.743);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(179.045, 136.149);
((GeneralPath)shape).curveTo(178.03099, 136.397, 176.706, 135.493, 176.08499, 134.13);
((GeneralPath)shape).curveTo(175.46399, 132.76701, 175.784, 131.461, 176.797, 131.213);
((GeneralPath)shape).curveTo(177.81, 130.965, 179.136, 131.868, 179.757, 133.231);
((GeneralPath)shape).curveTo(180.378, 134.59401, 180.059, 135.901, 179.045, 136.149);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_15);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_16 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_16
paint = new Color(32, 144, 0, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(355.24, 374.97);
((GeneralPath)shape).curveTo(364.591, 375.426, 373.37698, 375.076, 382.728, 375.533);
((GeneralPath)shape).curveTo(384.422, 376.973, 383.212, 380.508, 382.083, 380.255);
((GeneralPath)shape).curveTo(379.043, 380.178, 377.29102, 380.103, 374.25, 380.026);
((GeneralPath)shape).curveTo(374.146, 377.048, 366.544, 377.536, 366.763, 380.121);
((GeneralPath)shape).curveTo(362.658, 380.615, 358.956, 379.978, 354.851, 379.826);
((GeneralPath)shape).curveTo(353.63702, 378.31598, 353.793, 375.594, 355.24103, 374.97);
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
        return 1;
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
	public sa() {
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

