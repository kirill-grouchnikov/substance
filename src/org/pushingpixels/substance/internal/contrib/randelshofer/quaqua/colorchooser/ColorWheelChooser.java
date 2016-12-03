/*
 * @(#)ColorWheelChooser.java  1.1  2006-04-23
 *
 * Copyright (c) 2005-2006 Werner Randelshofer
 * Staldenmattweg 2, Immensee, CH-6405, Switzerland.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Werner Randelshofer. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Werner Randelshofer.
 */

package org.pushingpixels.substance.internal.contrib.randelshofer.quaqua.colorchooser;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.Icon;
import javax.swing.UIManager;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.UIResource;

/**
 * A HSB color chooser, which displays a hue/saturation color wheel, and a
 * brightness slider.
 *
 * @author Werner Randelshofer
 * @version 1.1 2006-04-23 Retrieve labels from UIManager. <br>
 * 			1.0.2 2005-11-07 Get "labels" resource bundle from UIManager. <br>
 * 			1.0.1 2005-09-11 Get icon from UIManager. <br>
 * 			1.0 August 27, 2005 Created.
 */
public class ColorWheelChooser extends AbstractColorChooserPanel implements UIResource {
	private ColorWheel colorWheel;
	private HSBColorSliderModel ccModel = new HSBColorSliderModel();

	/**
	 * Creates a new instance.
	 */
	public ColorWheelChooser() {
		initComponents();

		int textSliderGap = UIManager.getInt("ColorChooser.textSliderGap");
		if (textSliderGap != 0) {
			BorderLayout layout = (BorderLayout) getLayout();
			layout.setHgap(textSliderGap);
		}

		colorWheel = new ColorWheel();
		add(colorWheel);

		ccModel.configureColorSlider(2, brightnessSlider);
		colorWheel.setModel(ccModel);

		ccModel.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent evt) {
				setColorToModel(ccModel.getColor());
			}
		});
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	private void initComponents() {// GEN-BEGIN:initComponents
		brightnessSlider = new javax.swing.JSlider();

		setLayout(new java.awt.BorderLayout());

		brightnessSlider.setMajorTickSpacing(50);
		brightnessSlider.setOrientation(javax.swing.JSlider.VERTICAL);
		brightnessSlider.setPaintTicks(true);
		add(brightnessSlider, java.awt.BorderLayout.EAST);

	}// GEN-END:initComponents

	protected void buildChooser() {
	}

	public String getDisplayName() {
		return UIManager.getString("ColorChooser.colorWheel");
	}

	public javax.swing.Icon getLargeDisplayIcon() {
		return UIManager.getIcon("ColorChooser.colorWheelIcon");
	}

	public Icon getSmallDisplayIcon() {
		return getLargeDisplayIcon();
	}

	public void updateChooser() {
		ccModel.setColor(getColorFromModel());
	}

	public void setColorToModel(Color color) {
		getColorSelectionModel().setSelectedColor(color);
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JSlider brightnessSlider;
	// End of variables declaration//GEN-END:variables

}
