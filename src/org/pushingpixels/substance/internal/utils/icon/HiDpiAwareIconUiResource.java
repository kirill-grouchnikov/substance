package org.pushingpixels.substance.internal.utils.icon;

import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.plaf.UIResource;

public class HiDpiAwareIconUiResource extends HiDpiAwareIcon implements UIResource {
	public HiDpiAwareIconUiResource(BufferedImage image) {
		super(image);
	}
	
	public HiDpiAwareIconUiResource(Icon icon) {
		super(icon);
	}
}
