package test;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

import org.pushingpixels.lafwidget.icon.HiDpiAwareIcon;
import org.pushingpixels.substance.api.SubstanceColorScheme;

import test.check.substance;

public class SubstanceLogo {
	public static HiDpiAwareIcon getLogoIcon(SubstanceColorScheme scheme) {
		// Step 1 - create a new instance of the transcoded Substance logo and 
		// set its base size to 16x16
		substance icon = new substance();
		icon.setDimension(new Dimension(16, 16));
		// Step 2 - convert it to a hi-dpi aware icon
		HiDpiAwareIcon hiDpiAwareIcon = new HiDpiAwareIcon(icon);
		// Step 3 - return the colorized version of the icon
		return hiDpiAwareIcon.colorize(scheme.getForegroundColor());
	}
	
	public static BufferedImage getLogoImage(SubstanceColorScheme scheme) {
		return getLogoIcon(scheme).toImage();
	}
}
