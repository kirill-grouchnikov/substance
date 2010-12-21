/*
 * Copyright (c) 2005-2010 Substance Kirill Grouchnikov. All Rights Reserved.
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
package tools.jitterbug;

import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.dnd.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.InputStream;

import javax.swing.*;

import org.pushingpixels.substance.api.SubstanceColorScheme;
import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import org.pushingpixels.substance.api.skin.BusinessSkin;
import org.pushingpixels.substance.internal.utils.SubstanceColorSchemeUtilities;

import tools.common.JImageComponent;
import tools.jitterbug.StateChangeEvent.StateChangeType;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

public class JitterbugEditor extends JFrame implements ClipboardOwner {
	private static final String APP_TITLE = "Jitterbug color scheme editor";
	private JColorSchemeList colorSchemeList;
	private JColorSchemeComponent colorSchemeComp;
	private JHsvGraph hsvGraph;

	private class JitterbugLogo implements Icon {
		@Override
		public int getIconHeight() {
			return 16;
		}

		@Override
		public int getIconWidth() {
			return 16;
		}

		@Override
		public void paintIcon(Component c, Graphics g, int x, int y) {
			Graphics2D g2d = (Graphics2D) g.create();
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			g2d.translate(x, y);

			double coef1 = (double) getIconWidth()
					/ (double) substance.getOrigWidth();
			double coef2 = (double) getIconHeight()
					/ (double) substance.getOrigHeight();
			double coef = Math.min(coef1, coef2);
			g2d.scale(coef, coef);
			g2d.translate(substance.getOrigX(), substance.getOrigY());
			substance.paint(g2d);
			g2d.dispose();
		}
	}

	protected class JitterbugDropHandler extends DropTargetAdapter {
		@Override
		public void drop(DropTargetDropEvent dtde) {
			Transferable t = dtde.getTransferable();
			if (t.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
				try {
					dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
					java.util.List files = (java.util.List) t
							.getTransferData(DataFlavor.javaFileListFlavor);
					File f = (File) files.get(0);
					System.out.println("Reading from " + f.getAbsolutePath());
					colorSchemeList.setColorSchemeList(f);
					colorSchemeComp.clearContent();
					dtde.dropComplete(true);
					JitterbugEditor.this.setTitle(APP_TITLE + " - "
							+ f.getAbsolutePath());
					return;
				} catch (Exception exc) {
					exc.printStackTrace();
				}
			}
			// if (t.isDataFlavorSupported(DataFlavor.stringFlavor)) {
			// try {
			// dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
			// String content = (String) t
			// .getTransferData(DataFlavor.stringFlavor);
			// colorSchemeComp.setContent(new ByteArrayInputStream(content
			// .getBytes()));
			// dtde.dropComplete(true);
			// return;
			// } catch (Exception exc) {
			// exc.printStackTrace();
			// }
			// }
		}

		@Override
		public void dragEnter(DropTargetDragEvent dtde) {
			for (DataFlavor df : dtde.getCurrentDataFlavors()) {
				Class<?> repClass = df.getDefaultRepresentationClass();
				boolean canDrop = InputStream.class.isAssignableFrom(repClass);
				if (canDrop) {
					dtde.acceptDrag(DnDConstants.ACTION_COPY_OR_MOVE);
					return;
				}
			}
		}
	}

	public JitterbugEditor() {
		super();

		BufferedImage iconImage = GraphicsEnvironment
				.getLocalGraphicsEnvironment().getDefaultScreenDevice()
				.getDefaultConfiguration().createCompatibleImage(16, 16,
						Transparency.TRANSLUCENT);
		new JitterbugLogo().paintIcon(this, iconImage.getGraphics(), 0, 0);
		this.setIconImage(iconImage);

		FormLayout leftPanelLayout = new FormLayout("fill:pref",
				"fill:pref, fill:pref, fill:pref:grow, fill:pref");
		DefaultFormBuilder leftPanelBuilder = new DefaultFormBuilder(
				leftPanelLayout);

		this.colorSchemeList = new JColorSchemeList();
		this.colorSchemeList.setDropTarget(new DropTarget(this,
				new JitterbugDropHandler()));
		leftPanelBuilder.append(this.colorSchemeList);

		this.colorSchemeComp = new JColorSchemeComponent();
		this.colorSchemeComp.setEnabled(false);
		this.colorSchemeComp.setDropTarget(new DropTarget(this,
				new JitterbugDropHandler()));
		leftPanelBuilder.append(this.colorSchemeComp);

		this.hsvGraph = new JHsvGraph();
		this.hsvGraph.setDropTarget(new DropTarget(this,
				new JitterbugDropHandler()));
		leftPanelBuilder.append(this.hsvGraph);

		JPanel controlsPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));

		final JButton saveButton = new JButton("save");
		saveButton.setIcon(new ImageIcon(JitterbugEditor.class.getClassLoader()
				.getResource("tools/jitterbug/page_save.png")));
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				colorSchemeList.save();
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						saveButton.setEnabled(false);
					}
				});
			}
		});
		saveButton.setEnabled(false);
		controlsPanel.add(saveButton);

		JButton saveAsButton = new JButton("save as...");
		saveAsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				colorSchemeList.saveAs();
				updateMainWindowTitle(colorSchemeList.isModified());
			}
		});
		controlsPanel.add(saveAsButton);

		JButton newButton = new JButton("new");
		newButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				colorSchemeList.setColorSchemeList(null);
				colorSchemeComp.clearContent();
				updateMainWindowTitle(colorSchemeList.isModified());
			}
		});

		controlsPanel.add(Box.createHorizontalStrut(20));
		controlsPanel.add(newButton);

		leftPanelBuilder.append(controlsPanel);

		JPanel leftPanel = leftPanelBuilder.getPanel();
		// wire drag and drop
		wireDragAndDrop(leftPanel);

		// wire color scheme selection in the list to the
		// color scheme component
		this.colorSchemeList.addPropertyChangeListener("selectedColorScheme",
				new PropertyChangeListener() {
					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						SubstanceColorScheme newSelection = (SubstanceColorScheme) evt
								.getNewValue();
						if (newSelection != null)
							colorSchemeComp.setContent(newSelection);
						else
							colorSchemeComp.clearContent();
					}
				});
		// track color modifications of the currently selected
		// color scheme
		this.colorSchemeComp.addStateChangeListener(new StateChangeListener() {
			@Override
			public void stateChanged(StateChangeEvent event) {
				if (event.getStateChangeType() == StateChangeType.MODIFIED) {
					// let the color scheme list know that there was change
					colorSchemeList.setModified(true);
				}
				// update the HSV graph component
				if (colorSchemeComp.isDefined()) {
					Color[] colors = new Color[] {
							colorSchemeComp.getUltraLightColor(),
							colorSchemeComp.getExtraLightColor(),
							colorSchemeComp.getLightColor(),
							colorSchemeComp.getMidColor(),
							colorSchemeComp.getDarkColor(),
							colorSchemeComp.getUltraDarkColor() };
					hsvGraph.setColors(colors);
				} else {
					hsvGraph.setColors(null);
				}

				if (event.getStateChangeType() == StateChangeType.MODIFIED) {
					if (colorSchemeComp.isDefined()) {
						boolean isLight = colorSchemeComp.isLight();
						Color ultraDark = colorSchemeComp.getUltraDarkColor();
						Color dark = colorSchemeComp.getDarkColor();
						Color mid = colorSchemeComp.getMidColor();
						Color light = colorSchemeComp.getLightColor();
						Color extraLight = colorSchemeComp.getExtraLightColor();
						Color ultraLight = colorSchemeComp.getUltraLightColor();
						Color foreground = colorSchemeComp.getForegroundColor();
						String name = colorSchemeComp.getDisplayName();

						Color[] colors = new Color[] { ultraLight, extraLight,
								light, mid, dark, ultraDark, foreground };
						SubstanceColorScheme scheme = isLight ? SubstanceColorSchemeUtilities
								.getLightColorScheme(name, colors)
								: SubstanceColorSchemeUtilities
										.getDarkColorScheme(name, colors);
						colorSchemeList.updateColorScheme(scheme);
					}
				}
			}
		});
		// track modification changes on the scheme list and any scheme in it
		this.colorSchemeList.addPropertyChangeListener("modified",
				new PropertyChangeListener() {
					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						boolean isModified = (Boolean) evt.getNewValue();
						getRootPane().putClientProperty(
								SubstanceLookAndFeel.WINDOW_MODIFIED,
								Boolean.valueOf(isModified));

						// update the main frame title
						updateMainWindowTitle(isModified);

						File currFile = colorSchemeList.getCurrentFile();
						saveButton.setEnabled(currFile != null);
					}
				});

		this.add(leftPanel, BorderLayout.WEST);

		JPanel mainPanel = new JPanel(new BorderLayout());
		JImageComponent imageComp = new JImageComponent(true);
		imageComp
				.setLegend(new String[] {
						"Image panel. Use one of the following to show an image:",
						"\t* Right-click to paste an image from the clipboard",
						"\t* Drag and drop an image file from local disk or another app",
						"\t* Drag and drop a URL pointing to an image" });

		imageComp.addPropertyChangeListener("selectedColor",
				new PropertyChangeListener() {
					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						Color selectedImageColor = (Color) evt.getNewValue();
						JColorComponent selectedColorComp = colorSchemeComp
								.getSelectedColorComponent();
						if (selectedColorComp != null) {
							selectedColorComp
									.setColor(selectedImageColor, true);
						}
					}
				});

		mainPanel.add(imageComp, BorderLayout.CENTER);

		this.add(mainPanel, BorderLayout.CENTER);

		this.setSize(800, 700);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// do we need to save the modified scheme list?
				if (colorSchemeList.checkModifiedStateAndSaveIfNecessary()) {
					dispose();
				}
			}
		});

		this.updateMainWindowTitle(false);
	}

	private void wireDragAndDrop(Component comp) {
		comp.setDropTarget(new DropTarget(this, new JitterbugDropHandler()));
		if (comp instanceof Container) {
			Container cont = (Container) comp;
			for (int i = 0; i < cont.getComponentCount(); i++) {
				wireDragAndDrop(cont.getComponent(i));
			}
		}
	}

	@Override
	public void lostOwnership(Clipboard clipboard, Transferable contents) {
	}

	private void updateMainWindowTitle(boolean isModified) {
		File schemesFile = colorSchemeList.getCurrentFile();
		String title = APP_TITLE + " - ";
		if (isModified) {
			title += "* ";
		}
		if (schemesFile != null) {
			title += schemesFile.getAbsolutePath();
		} else {
			title += "Unsaved";
		}
		setTitle(title);
	}

	public static void main(String[] args) {
		JDialog.setDefaultLookAndFeelDecorated(true);
		JFrame.setDefaultLookAndFeelDecorated(true);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				SubstanceLookAndFeel.setSkin(new BusinessSkin());
				new JitterbugEditor().setVisible(true);
			}
		});
	}

}
