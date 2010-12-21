/*
 * Copyright (c) 2005-2008 Substance Kirill Grouchnikov. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  o Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  o Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 *  o Neither the name of Substance Kirill Grouchnikov nor the names of
 *    its contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package test.check;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.*;

import javax.swing.*;
import javax.swing.JInternalFrame.JDesktopIcon;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import org.pushingpixels.substance.api.*;
import org.pushingpixels.substance.api.skin.SkinChangeListener;

import test.SubstanceLogo;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

/**
 * Test application panel for testing {@link JDesktopPane},
 * {@link JInternalFrame} and {@link JDesktopIcon} components.
 * 
 * @author Kirill Grouchnikov
 */
public class DesktopPanel extends ControllablePanel {
	/**
	 * The desktop pane.
	 */
	private JDesktopPane jdp;

	/**
	 * Counter for creating the internal frames.
	 */
	private int count = 0;

	/**
	 * A set of disposed internal frames.
	 */
	private Set<JInternalFrame> disposed = new HashSet<JInternalFrame>();

	/**
	 * Creates the desktop panel.
	 */
	public DesktopPanel() {
		this.setLayout(new BorderLayout());
		jdp = new JDesktopPane();
		this.add(jdp, BorderLayout.CENTER);

		FormLayout lm = new FormLayout("right:pref, 4dlu, fill:pref:grow", "");
		DefaultFormBuilder builder = new DefaultFormBuilder(lm,
				new ScrollablePanel());
		builder.appendSeparator("New sample frame");

		JButton sample = new JButton("Add");
		sample.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						JInternalFrame jif = new SampleInternalFrame() {
							// @Override
							// public void paint(Graphics g) {
							// long start = System.nanoTime();
							// super.paint(g);
							// long end = System.nanoTime();
							// System.err.println("paint done in " + (end -
							// start));
							// }
						};
						jif.setBounds(0, 0, 300, 200);
						jdp.add(jif);
						jif.setVisible(true);
						jif.setComponentOrientation(jdp
								.getComponentOrientation());
					}
				});
			}
		});
		builder.append("Add sample", sample);

		builder.appendSeparator("New custom frame");
		final JCheckBox makeZero = new JCheckBox("Has zero bounds");
		builder.append("Custom settings", makeZero);

		final JCheckBox isClosable = new JCheckBox("Is closable");
		isClosable.setSelected(true);
		builder.append("", isClosable);

		final JCheckBox isMaximizable = new JCheckBox("Is maximizable");
		isMaximizable.setSelected(true);
		builder.append("", isMaximizable);

		final JCheckBox isIconifiable = new JCheckBox("Is iconifiable");
		isIconifiable.setSelected(true);
		builder.append("", isIconifiable);

		final JCheckBox isResizable = new JCheckBox("Is resizable");
		isResizable.setSelected(true);
		builder.append("", isResizable);

		JButton bt = new JButton("Add");
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String title = "[" + count + "]Internal title ";
				int c = (int) (20 * Math.random());
				for (int i = 0; i < c; i++) {
					title += "0";
				}
				final JInternalFrame jif = new JInternalFrame(title);
				jif.setFrameIcon(SubstanceLogo.getLogoIcon(SubstanceLookAndFeel
						.getCurrentSkin(jif.getRootPane()).getColorScheme(
								DecorationAreaType.SECONDARY_TITLE_PANE,
								ColorSchemeAssociationKind.FILL,
								ComponentState.ENABLED)));
				SubstanceLookAndFeel
						.registerSkinChangeListener(new SkinChangeListener() {
							public void skinChanged() {
								jif
										.setFrameIcon(SubstanceLogo
												.getLogoIcon(SubstanceLookAndFeel
														.getCurrentSkin(
																jif
																		.getRootPane())
														.getColorScheme(
																DecorationAreaType.SECONDARY_TITLE_PANE,
																ColorSchemeAssociationKind.FILL,
																ComponentState.ENABLED)));
							}
						});
				jif.setLayout(new BorderLayout());
				JPanel controls = new JPanel(new FlowLayout());
				int comps = 5 + (int) (10 * Math.random());
				for (int i = 0; i < comps; i++) {
					double r = Math.random();
					if (r < 0.1) {
						controls.add(new JButton("button" + i));
					} else {
						if (r < 0.2) {
							controls.add(new JLabel("label" + i));
						} else {
							if (r < 0.3) {
								controls.add(new JRadioButton("radio" + i));
							} else {
								if (r < 0.4) {
									controls.add(new JCheckBox("check" + i));
								} else {
									if (r < 0.5) {
										controls.add(new JToggleButton("toggle"
												+ i));
									} else {
										if (r < 0.6) {
											controls
													.add(new JComboBox(
															new Object[] { "combo"
																	+ i }));
										} else {
											if (r < 0.7) {
												controls.add(new JTextField(
														"text field" + i));
											} else {
												if (r < 0.8) {
													controls
															.add(new JPasswordField(
																	"password"
																			+ i));
												} else {
													if (r < 0.9) {
														controls
																.add(new JSpinner());
													} else {
														controls
																.add(new JList(
																		new Object[] { "list"
																				+ i }));
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}

				jif.add(controls, BorderLayout.CENTER);
				jif.setClosable(isClosable.isSelected());
				jif.setMaximizable(isMaximizable.isSelected());
				jif.setIconifiable(isIconifiable.isSelected());
				jif.setResizable(isResizable.isSelected());

				JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
				final JCheckBox isModified = new JCheckBox("modified");
				isModified.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						jif.getRootPane().putClientProperty(
								SubstanceLookAndFeel.WINDOW_MODIFIED,
								Boolean.valueOf(isModified.isSelected()));
					}
				});
				buttons.add(isModified);

				JButton changeTitleButton = new JButton("Change title");
				changeTitleButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String random = "abcdefghijklmnopqrstuvwxyz     ";
						int length = (int) (50 * Math.random());
						String title = "";
						while (length > 0) {
							title += random
									.charAt((int) (random.length() * Math
											.random()));
							length--;
						}
						jif.setTitle(title);
					}
				});
				buttons.add(changeTitleButton);

				JButton setNullTitlePane = new JButton("Remove title pane");
				setNullTitlePane.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						SwingUtilities.invokeLater(new Runnable() {
							public void run() {
								((BasicInternalFrameUI) jif.getUI())
										.setNorthPane(null);
								jif.revalidate();
							}
						});
					}
				});
				buttons.add(setNullTitlePane);

				jif.add(buttons, BorderLayout.SOUTH);

				count++;
				int width = 50 + (int) (400 * Math.random());
				int height = 50 + (int) (200 * Math.random());
				jif.setBounds(20 * (count % 10), 20 * (count % 10), width,
						height);
				if (makeZero.isSelected())
					jif.setBounds(0, 0, 0, 0);
				jif.setBackground(new Color(128 + (int) (128 * Math.random()),
						128 + (int) (128 * Math.random()),
						128 + (int) (128 * Math.random())));
				// jif.setClosable(true);
				// jif.setMaximizable(true);
				// jif.setResizable(true);
				// jif.setIconifiable(true);
				jif.setComponentOrientation(jdp.getComponentOrientation());
				jdp.add(jif, 1);

				JMenuBar jmb = new JMenuBar();
				jmb.add(SampleMenuFactory.getSkinMenu());

				JMenu jm1 = new JMenu("Menu1");
				jm1.setMnemonic('1');
				int mcount = 0;
				for (LinkedList<JMenuItem> miList : SampleMenuFactory
						.getTestMenuItems()) {
					if (mcount > 0) {
						if (mcount % 2 == 0)
							jm1.addSeparator();
						else
							jm1.add(new JSeparator());
					}
					for (JMenuItem menuItem : miList) {
						jm1.add(menuItem);
					}
					mcount++;
				}
				jmb.add(jm1);
				jif.setJMenuBar(jmb);

				jif.show();
			}
		});

		builder.append("Add custom", bt);

		builder.appendSeparator("Operations");

		JButton minAll = new JButton("Execute");
		minAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (JInternalFrame jif : jdp.getAllFrames()) {
					try {
						jif.setIcon(true);
					} catch (PropertyVetoException pve) {
					}
				}
			}
		});
		builder.append("Minimize all", minAll);

		JButton closeAll = new JButton("Execute");
		closeAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (JInternalFrame jif : jdp.getAllFrames()) {
					try {
						jif.setClosed(true);
					} catch (PropertyVetoException pve) {
					}
				}
			}
		});
		builder.append("Close all", closeAll);

		JButton disposeAll = new JButton("Execute");
		disposeAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (JInternalFrame jif : jdp.getAllFrames()) {
					disposed.add(jif);
					jif.dispose();
				}
			}
		});
		builder.append("Dispose all", disposeAll);

		JButton reshowAll = new JButton("Execute");
		reshowAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (JInternalFrame jif : disposed) {
					jif.setVisible(true);
				}
			}
		});
		builder.append("Reshow all", reshowAll);

		this.controlPanel = builder.getPanel();

		this.setPreferredSize(new Dimension(400, 400));
		this.setSize(this.getPreferredSize());
		this.setMinimumSize(this.getPreferredSize());
	}
}