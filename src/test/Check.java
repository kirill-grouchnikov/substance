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
package test;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import java.util.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.FontUIResource;

import org.jdesktop.swingx.*;
import org.pushingpixels.lafwidget.LafWidget;
import org.pushingpixels.lafwidget.tabbed.DefaultTabPreviewPainter;
import org.pushingpixels.lafwidget.utils.LafConstants.TabOverviewKind;
import org.pushingpixels.substance.api.*;
import org.pushingpixels.substance.api.SubstanceConstants.Side;
import org.pushingpixels.substance.api.SubstanceConstants.TabCloseKind;
import org.pushingpixels.substance.api.fonts.FontSet;
import org.pushingpixels.substance.api.skin.SkinChangeListener;
import org.pushingpixels.substance.api.skin.SubstanceGeminiLookAndFeel;
import org.pushingpixels.substance.api.tabbed.*;

import test.check.*;
import test.check.statusbar.FontSizePanel;

public class Check extends JFrame {
	private JTabbedPane jtp;

	private MyMainTabPreviewPainter mainTabPreviewPainter;

	private static class WrapperFontSet implements FontSet {
		private int extra;

		private FontSet delegate;

		public WrapperFontSet(FontSet delegate, int extra) {
			super();
			this.delegate = delegate;
			this.extra = extra;
		}

		private FontUIResource getWrappedFont(FontUIResource systemFont) {
			return new FontUIResource(systemFont.getFontName(), systemFont
					.getStyle(), systemFont.getSize() + extra);
		}

		public FontUIResource getControlFont() {
			return getWrappedFont(delegate.getControlFont());
		}

		public FontUIResource getMenuFont() {
			return getWrappedFont(delegate.getMenuFont());
		}

		public FontUIResource getMessageFont() {
			return getWrappedFont(delegate.getMessageFont());
		}

		public FontUIResource getSmallFont() {
			return getWrappedFont(delegate.getSmallFont());
		}

		public FontUIResource getTitleFont() {
			return getWrappedFont(delegate.getTitleFont());
		}

		public FontUIResource getWindowTitleFont() {
			return getWrappedFont(delegate.getWindowTitleFont());
		}
	}

	private JXTaskPaneContainer taskPaneContainer;

	private JXTaskPane currentSpecificTaskPane;

	private JXTaskPane mainTaskPane;

	private JToolBar toolbar;

	public Check() {
		super(
				"Substance test with very very very very very very very very very very very very very very long title");

		final ClassLoader cl = Thread.currentThread().getContextClassLoader();
		if (UIManager.getLookAndFeel() instanceof SubstanceLookAndFeel) {
			setIconImage(SubstanceLogo.getLogoImage(SubstanceLookAndFeel
					.getCurrentSkin(this.getRootPane()).getColorScheme(
							DecorationAreaType.PRIMARY_TITLE_PANE,
							ColorSchemeAssociationKind.FILL,
							ComponentState.ENABLED)));
		}
		SubstanceLookAndFeel
				.registerSkinChangeListener(new SkinChangeListener() {
					@Override
					public void skinChanged() {
						SwingUtilities.invokeLater(new Runnable() {
							@Override
							public void run() {
								setIconImage(SubstanceLogo
										.getLogoImage(SubstanceLookAndFeel
												.getCurrentSkin(
														Check.this
																.getRootPane())
												.getColorScheme(
														DecorationAreaType.PRIMARY_TITLE_PANE,
														ColorSchemeAssociationKind.FILL,
														ComponentState.ENABLED)));
							}
						});
					}
				});

		setLayout(new BorderLayout());

		jtp = new JTabbedPane();
		try {
			mainTabPreviewPainter = new MyMainTabPreviewPainter();
			jtp.putClientProperty(LafWidget.TABBED_PANE_PREVIEW_PAINTER,
					mainTabPreviewPainter);
		} catch (Throwable e) {
		}
		jtp.getModel().addChangeListener(new TabSwitchListener());

		final JXPanel jxPanel = new JXPanel(new BorderLayout());
		toolbar = getToolbar("", 22, true);
		jxPanel.add(toolbar, BorderLayout.NORTH);

		JXStatusBar statusBar = getStatusBar(jxPanel, jtp);
		this.add(statusBar, BorderLayout.SOUTH);

		taskPaneContainer = new JXTaskPaneContainer() {
			@Override
			public boolean getScrollableTracksViewportWidth() {
				return false;
			}
		};
		taskPaneContainer.setScrollableTracksViewportHeight(false);
		taskPaneContainer.setScrollableTracksViewportWidth(false);

		mainTaskPane = new JXTaskPane();
		mainTaskPane.setLayout(new BorderLayout());
		JPanel mainControlPanel = ControlPanelFactory.getMainControlPanel(this,
				jtp, mainTabPreviewPainter, toolbar);
		// mainControlPanel.setOpaque(false);
		mainTaskPane.add(mainControlPanel, BorderLayout.CENTER);
		mainTaskPane.setTitle("General settings");
		mainTaskPane.setIcon(getIcon("JFrameColor16"));
		mainTaskPane.setCollapsed(true);
		taskPaneContainer.add(mainTaskPane);

		JPanel dialogControlPanel = ControlPanelFactory
				.getDialogControlPanel(this);
		JXTaskPane dialogTaskPane = new JXTaskPane();
		dialogTaskPane.setLayout(new BorderLayout());
		dialogTaskPane.add(dialogControlPanel, BorderLayout.CENTER);
		dialogTaskPane.setTitle("Frames & Dialogs");
		dialogTaskPane.setIcon(getIcon("JDialogColor16"));
		dialogTaskPane.setCollapsed(true);
		// dialogTaskPane.setOpaque(false);
		taskPaneContainer.add(dialogTaskPane);

		currentSpecificTaskPane = null;

		final JScrollPane scrollPane = new JScrollPane(taskPaneContainer,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		SubstanceLookAndFeel.setDecorationType(scrollPane,
				DecorationAreaType.GENERAL);
		// scrollPane.setOpaque(false);
		// scrollPane.getViewport().setOpaque(false);

		JPanel mainPanel = new JPanel();
		mainPanel.add(scrollPane);
		mainPanel.add(jtp);
		mainPanel.setLayout(new LayoutManager() {
			public void addLayoutComponent(String name, Component comp) {
			}

			public Dimension minimumLayoutSize(Container parent) {
				Dimension min1 = scrollPane.getMinimumSize();
				Dimension min2 = jtp.getMinimumSize();
				return new Dimension(min1.width + min2.width, min1.height
						+ min2.height);
			}

			public void layoutContainer(Container parent) {
				// give 30% width to task pane container and
				// 70% width to the tabbed pane with controls.
				int width = parent.getWidth();
				int height = parent.getHeight();
				scrollPane.setBounds(0, 0, (int) (0.3 * width), height);
				jtp.setBounds((int) (0.3 * width), 0, width
						- (int) (0.3 * width), height);
			}

			public Dimension preferredLayoutSize(Container parent) {
				Dimension pref1 = scrollPane.getPreferredSize();
				Dimension pref2 = jtp.getPreferredSize();
				return new Dimension(pref1.width + pref2.width, pref1.height
						+ pref2.height);
			}

			public void removeLayoutComponent(Component comp) {
			}
		});
		jxPanel.add(mainPanel, BorderLayout.CENTER);

		this.add(jxPanel, BorderLayout.CENTER);

		setPreferredSize(new Dimension(400, 400));
		this.setSize(getPreferredSize());
		setMinimumSize(getPreferredSize());

		ButtonsPanel buttonsPanel = new ButtonsPanel();
		jtp.addTab("Buttons", getIcon("JButtonColor16"), buttonsPanel);
		getRootPane().setDefaultButton(buttonsPanel.defaultButton);

		jtp.addTab("Combo box", getIcon("JComboBoxColor16"), new CombosPanel());

		jtp.addTab("Scroll pane", getIcon("JScrollPaneColor16"),
				new ScrollPanel());

		TabCloseCallback closeCallback = new TabCloseCallback() {
			public TabCloseKind onAreaClick(JTabbedPane tabbedPane,
					int tabIndex, MouseEvent mouseEvent) {
				if (mouseEvent.getButton() != MouseEvent.BUTTON3)
					return TabCloseKind.NONE;
				if (mouseEvent.isShiftDown()) {
					return TabCloseKind.ALL;
				}
				return TabCloseKind.THIS;
			}

			public TabCloseKind onCloseButtonClick(JTabbedPane tabbedPane,
					int tabIndex, MouseEvent mouseEvent) {
				if (mouseEvent.isAltDown()) {
					return TabCloseKind.ALL_BUT_THIS;
				}
				if (mouseEvent.isShiftDown()) {
					return TabCloseKind.ALL;
				}
				return TabCloseKind.THIS;
			}

			public String getAreaTooltip(JTabbedPane tabbedPane, int tabIndex) {
				return null;
			}

			public String getCloseButtonTooltip(JTabbedPane tabbedPane,
					int tabIndex) {
				StringBuffer result = new StringBuffer();
				result.append("<html><body>");
				result.append("Mouse click closes <b>"
						+ tabbedPane.getTitleAt(tabIndex) + "</b> tab");
				result
						.append("<br><b>Alt</b>-Mouse click closes all tabs but <b>"
								+ tabbedPane.getTitleAt(tabIndex) + "</b> tab");
				result.append("<br><b>Shift</b>-Mouse click closes all tabs");
				result.append("</body></html>");
				return result.toString();
			}
		};

		try {
			TabPanel tp = new TabPanel();
			tp.jtp.putClientProperty(
					SubstanceLookAndFeel.TABBED_PANE_CLOSE_CALLBACK,
					closeCallback);
			jtp.addTab("Tabs", getIcon("JTabbedPaneColor16"), tp);
		} catch (NoClassDefFoundError ncdfe) {
		}

		jtp.addTab("Split", new SplitPanel());

		jtp.addTab("Desktop", getIcon("JDesktopPaneColor16"),
				new DesktopPanel());

		jtp.addTab("Text fields", getIcon("JTextPaneColor16"),
				new TextFieldsPanel());

		jtp.addTab("Table", getIcon("JTableColor16"), new TablePanel());

		try {
			jtp.addTab("List", getIcon("JListColor16"), new ListPanel());
		} catch (NoClassDefFoundError ncdfe) {
		}

		jtp.addTab("Slider", getIcon("JSliderColor16"), new SliderPanel());

		jtp.addTab("Progress bar", getIcon("JProgressBarColor16"),
				new ProgressBarPanel());

		jtp.addTab("Spinner", getIcon("JSpinnerColor16"), new SpinnerPanel());

		jtp.addTab("Tree", getIcon("JTreeColor16"), new TreePanel());

		jtp.addTab("File tree", getIcon("JTreeColor16"), new FileTreePanel());

		jtp.addTab("Cards", new CardPanel());

		JPanel verticalButtonPanel = new JPanel();
		verticalButtonPanel.setLayout(new GridLayout(1, 3));
		verticalButtonPanel.add(new JButton("Vert button 1"));
		verticalButtonPanel.add(new JButton("Vert button 2"));
		JPanel smallVerticalButtonPanel = new JPanel();
		smallVerticalButtonPanel.setLayout(new GridLayout(4, 4));
		for (int row = 0; row < 4; row++) {
			for (int col = 0; col < 4; col++) {
				JButton vertButton = new JButton("vert");
				vertButton.setToolTipText("Vertical button " + row + ":" + col);
				smallVerticalButtonPanel.add(vertButton);
			}
		}
		verticalButtonPanel.add(smallVerticalButtonPanel);
		jtp.addTab("V-Buttons", verticalButtonPanel);

		jtp.addTab("Colored", new ColoredControlsPanel());

		jtp.addTab("Colorized", new ColorizedControlsPanel());

		jtp.addTab("Cells", new CellsPanel());

		jtp.addTab("Sizes", new SizesPanel());

		jtp.addTab("H-Align", new HAlignmentPanel());

		jtp.addTab("V-Align", new VAlignmentPanel());

		// sample menu bar
		JMenuBar jmb = new JMenuBar();
		if (UIManager.getLookAndFeel() instanceof SubstanceLookAndFeel) {
			jmb.add(SampleMenuFactory.getSkinMenu());
			jmb.add(SampleMenuFactory.getTransformMenu());
		}
		JMenu coloredMenu = new JMenu("Colors");
		coloredMenu.setMnemonic('0');
		JMenuItem coloredMI = new JMenuItem("Italic red");
		coloredMI.setFont(coloredMI.getFont().deriveFont(Font.ITALIC));
		coloredMI.setForeground(Color.red);
		coloredMenu.add(coloredMI);
		JRadioButtonMenuItem coloredRBMI = new JRadioButtonMenuItem(
				"Bold green");
		coloredRBMI.setFont(coloredRBMI.getFont().deriveFont(Font.BOLD));
		coloredRBMI.setForeground(Color.green);
		coloredMenu.add(coloredRBMI);
		JCheckBoxMenuItem coloredCBMI = new JCheckBoxMenuItem("Big blue");
		coloredCBMI.setFont(coloredCBMI.getFont().deriveFont(32f));
		coloredCBMI.setForeground(Color.blue);
		coloredMenu.add(coloredCBMI);
		JMenu coloredM = new JMenu("Always big magenta");
		coloredM.setForeground(Color.magenta);
		coloredM.setFont(coloredM.getFont().deriveFont(24f));
		coloredMenu.add(coloredM);
		jmb.add(coloredMenu);

		JMenu testMenu = SampleMenuFactory.getTestMenu();
		jmb.add(testMenu);

		JMenu jm4 = new JMenu("Disabled");
		jm4.add(new JMenuItem("dummy4"));
		jm4.setMnemonic('4');
		jmb.add(jm4);
		jm4.setEnabled(false);

		// LAF changing
		jmb.add(SampleMenuFactory.getLookAndFeelMenu(this));
		setJMenuBar(jmb);

		TabCloseCallback closeCallbackMain = new TabCloseCallback() {
			public TabCloseKind onAreaClick(JTabbedPane tabbedPane,
					int tabIndex, MouseEvent mouseEvent) {
				if (mouseEvent.getButton() != MouseEvent.BUTTON2)
					return TabCloseKind.NONE;
				if (mouseEvent.isShiftDown()) {
					return TabCloseKind.ALL;
				}
				return TabCloseKind.THIS;
			}

			public TabCloseKind onCloseButtonClick(JTabbedPane tabbedPane,
					int tabIndex, MouseEvent mouseEvent) {
				if (mouseEvent.isAltDown()) {
					return TabCloseKind.ALL_BUT_THIS;
				}
				if (mouseEvent.isShiftDown()) {
					return TabCloseKind.ALL;
				}
				return TabCloseKind.THIS;
			}

			public String getAreaTooltip(JTabbedPane tabbedPane, int tabIndex) {
				return null;
			}

			public String getCloseButtonTooltip(JTabbedPane tabbedPane,
					int tabIndex) {
				StringBuffer result = new StringBuffer();
				result.append("<html><body>");
				result.append("Mouse click closes <b>"
						+ tabbedPane.getTitleAt(tabIndex) + "</b> tab");
				result
						.append("<br><b>Alt</b>-Mouse click closes all tabs but <b>"
								+ tabbedPane.getTitleAt(tabIndex) + "</b> tab");
				result.append("<br><b>Shift</b>-Mouse click closes all tabs");
				result.append("</body></html>");
				return result.toString();
			}
		};

		jtp.putClientProperty(SubstanceLookAndFeel.TABBED_PANE_CLOSE_CALLBACK,
				closeCallbackMain);
		SubstanceLookAndFeel
				.registerTabCloseChangeListener(new TabCloseListener() {
					public void tabClosed(JTabbedPane tabbedPane,
							Component tabComponent) {
						out("Closed tab");
					}

					public void tabClosing(JTabbedPane tabbedPane,
							Component tabComponent) {
						out("Closing tab");
					}
				});

		SubstanceLookAndFeel.registerTabCloseChangeListener(jtp,
				new VetoableTabCloseListener() {
					public void tabClosed(JTabbedPane tabbedPane,
							Component tabComponent) {
						out("Closed tab - specific");
					}

					public void tabClosing(JTabbedPane tabbedPane,
							Component tabComponent) {
						out("Closing tab - specific");
					}

					public boolean vetoTabClosing(JTabbedPane tabbedPane,
							Component tabComponent) {
						int userCloseAnswer = JOptionPane
								.showConfirmDialog(
										Check.this,
										"Are you sure you want to close '"
												+ tabbedPane
														.getTitleAt(tabbedPane
																.indexOfComponent(tabComponent))
												+ "' tab?", "Confirm dialog",
										JOptionPane.YES_NO_OPTION);
						return (userCloseAnswer == JOptionPane.NO_OPTION);
					}
				});

		SubstanceLookAndFeel.registerTabCloseChangeListener(jtp,
				new VetoableMultipleTabCloseListener() {
					public void tabsClosed(JTabbedPane tabbedPane,
							Set<Component> tabComponents) {
						out("Closed " + tabComponents.size()
								+ " tabs - specific");
					}

					public void tabsClosing(JTabbedPane tabbedPane,
							Set<Component> tabComponents) {
						out("Closing " + tabComponents.size()
								+ " tabs - specific");
					}

					public boolean vetoTabsClosing(JTabbedPane tabbedPane,
							Set<Component> tabComponents) {
						int userCloseAnswer = JOptionPane.showConfirmDialog(
								Check.this, "Are you sure you want to close "
										+ tabComponents.size() + " tabs?",
								"Confirm dialog", JOptionPane.YES_NO_OPTION);
						return (userCloseAnswer == JOptionPane.NO_OPTION);
					}
				});

		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				System.out.println("Size " + getSize());
			}
		});

	}

	protected static JXStatusBar getStatusBar(final JXPanel jxPanel,
			final JTabbedPane mainTabbedPane) {
		JXStatusBar statusBar = new JXStatusBar();

		try {
			ClassLoader cl = Thread.currentThread().getContextClassLoader();
			Enumeration<URL> urls = cl.getResources("META-INF/MANIFEST.MF");
			String substanceVer = null;
			String substanceBuildStamp = null;
			while (urls.hasMoreElements()) {
				InputStream is = urls.nextElement().openStream();
				BufferedReader br = new BufferedReader(
						new InputStreamReader(is));
				while (true) {
					String line = br.readLine();
					if (line == null)
						break;
					int firstColonIndex = line.indexOf(":");
					if (firstColonIndex < 0)
						continue;
					String name = line.substring(0, firstColonIndex).trim();
					String val = line.substring(firstColonIndex + 1).trim();
					if (name.compareTo("Substance-Version") == 0)
						substanceVer = val;
					if (name.compareTo("Substance-BuildStamp") == 0)
						substanceBuildStamp = val;
				}
				try {
					br.close();
				} catch (IOException ioe) {
				}
			}
			if (substanceVer != null) {
				JLabel statusLabel = new JLabel(substanceVer + " [built on "
						+ substanceBuildStamp + "]");
				JXStatusBar.Constraint cStatusLabel = new JXStatusBar.Constraint();
				cStatusLabel.setFixedWidth(300);
				statusBar.add(statusLabel, cStatusLabel);
			}
		} catch (IOException ioe) {
		}

		JXStatusBar.Constraint c2 = new JXStatusBar.Constraint(
				JXStatusBar.Constraint.ResizeBehavior.FILL);
		final JLabel tabLabel = new JLabel("");
		statusBar.add(tabLabel, c2);
		mainTabbedPane.getModel().addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int selectedIndex = mainTabbedPane.getSelectedIndex();
				if (selectedIndex < 0)
					tabLabel.setText("No selected tab");
				else
					tabLabel.setText("Tab "
							+ mainTabbedPane.getTitleAt(selectedIndex)
							+ " selected");
			}
		});

		JPanel fontSizePanel = FontSizePanel.getPanel();
		JXStatusBar.Constraint fontSizePanelConstraints = new JXStatusBar.Constraint();
		fontSizePanelConstraints.setFixedWidth(270);
		statusBar.add(fontSizePanel, fontSizePanelConstraints);

		JPanel alphaPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 0, 0));

		final JLabel alphaLabel = new JLabel("100%");
		final JSlider alphaSlider = new JSlider(0, 100, 100);
		alphaSlider.setFocusable(false);
		alphaSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int currValue = alphaSlider.getValue();
				alphaLabel.setText(currValue + "%");
				jxPanel.setAlpha(currValue / 100.0f);
			}
		});
		alphaSlider
				.setToolTipText("Changes the global opacity. Is not Substance-specific");
		alphaSlider.setPreferredSize(new Dimension(120, alphaSlider
				.getPreferredSize().height));

		alphaPanel.add(alphaLabel);
		alphaPanel.add(alphaSlider);

		JXStatusBar.Constraint alphaPanelConstraints = new JXStatusBar.Constraint();
		alphaPanelConstraints.setFixedWidth(160);
		statusBar.add(alphaPanel, alphaPanelConstraints);
		return statusBar;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				boolean hasLafSpecified = false;
				try {
					hasLafSpecified = (System.getProperty("swing.defaultlaf") != null);
				} catch (Throwable t) {
					// JNLP sandbox
				}

				// LAFAdapter.startWidget();

				try {
					if (!hasLafSpecified) {
						out(" CREATING LAF ");
						long time0 = System.currentTimeMillis();
						LookAndFeel laf = new SubstanceGeminiLookAndFeel();
						long time1 = System.currentTimeMillis();
						out(" LAF CREATED " + (time1 - time0));
						out(" SETTING LAF ");
						long time2 = System.currentTimeMillis();
						UIManager.setLookAndFeel(laf);
						long time3 = System.currentTimeMillis();
						out(" LAF SET " + (time3 - time2));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				SubstanceLookAndFeel.setToUseConstantThemesOnDialogs(true);
				UIManager
						.put(
								SubstanceLookAndFeel.TABBED_PANE_CLOSE_BUTTONS_PROPERTY,
								Boolean.TRUE);
				UIManager.put(SubstanceLookAndFeel.SHOW_EXTRA_WIDGETS,
						Boolean.TRUE);
				// try {
				JFrame.setDefaultLookAndFeelDecorated(true);
				JDialog.setDefaultLookAndFeelDecorated(true);

				long time2 = System.currentTimeMillis();

				// UIManager.put("Button.defaultButtonFollowsFocus",
				// Boolean.TRUE);

				Check c = new Check();
				c.addComponentListener(new ComponentAdapter() {
					@Override
					public void componentResized(ComponentEvent e) {
						super.componentResized(e);
						((JFrame) e.getComponent()).getRootPane().repaint();
					}
				});
				c.setPreferredSize(new Dimension(820, 560));
				c.setMinimumSize(new Dimension(150, 100));
				c.pack();
				Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
				// center the frame in the physical screen
				c.setLocation((d.width - c.getWidth()) / 2, (d.height - c
						.getHeight()) / 2);

				c.setVisible(true);
				c
						.setDefaultCloseOperation(System
								.getProperty("javawebstart.version") != null ? JFrame.EXIT_ON_CLOSE
								: JFrame.DISPOSE_ON_CLOSE);
				long time3 = System.currentTimeMillis();
				out("App " + (time3 - time2));

				// Thread cpuTracker = new Thread() {
				// @Override
				// public void run() {
				// OperatingSystemMXBean bean = ManagementFactory
				// .getOperatingSystemMXBean();
				// while (true) {
				// System.out.println(bean.getSystemLoadAverage());
				// try {
				// Thread.sleep(5000);
				// } catch (Throwable t) {
				// t.printStackTrace();
				// }
				// }
				// };
				// };
				// cpuTracker.setDaemon(true);
				// cpuTracker.start();
			}
		});
	}

	public static void out(Object obj) {
		try {
			System.out.println(obj);
		} catch (Exception exc) {
			// ignore - is thrown on Mac in WebStart (security access)
		}
	}

	public static Icon getIcon(String iconName) {
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		URL url = cl.getResource("test/check/icons/" + iconName + ".gif");
		if (url != null)
			return new ImageIcon(url);
		url = cl.getResource("test/check/icons/" + iconName + ".png");
		if (url != null)
			return new ImageIcon(url);
		return null;
	}

	public static JToolBar getToolbar(String label, int size, boolean hasStrings) {
		JToolBar toolBar = new JToolBar();

		JButton buttonCut = new JButton(hasStrings ? "cut" : null, getIcon(size
				+ "/edit-cut"));
		buttonCut.putClientProperty(
				SubstanceLookAndFeel.BUTTON_NO_MIN_SIZE_PROPERTY, Boolean.TRUE);
		toolBar.add(buttonCut);
		JButton buttonCopy = new JButton(hasStrings ? "copy" : null,
				getIcon(size + "/edit-copy"));
		buttonCopy.putClientProperty(
				SubstanceLookAndFeel.BUTTON_NO_MIN_SIZE_PROPERTY, Boolean.TRUE);
		buttonCopy.setEnabled(false);
		toolBar.add(buttonCopy);
		JButton buttonPaste = new JButton(getIcon(size + "/edit-paste"));
		toolBar.add(buttonPaste);
		JButton buttonSelectAll = new JButton(
				getIcon(size + "/edit-select-all"));
		toolBar.add(buttonSelectAll);
		JButton buttonDelete = new JButton(getIcon(size + "/edit-delete"));
		toolBar.add(buttonDelete);
		toolBar.addSeparator();

		// add an inner toolbar to check the painting of toolbar
		// gradient and drop shadows under different skins.
		JToolBar innerToolbar = new JToolBar(JToolBar.HORIZONTAL);
		innerToolbar.setFloatable(false);
		JToggleButton buttonFormatCenter = new JToggleButton(getIcon(size
				+ "/format-justify-center"));
		buttonFormatCenter.putClientProperty(
				SubstanceLookAndFeel.CORNER_RADIUS, Float.valueOf(5.0f));
		innerToolbar.add(buttonFormatCenter);
		JToggleButton buttonFormatLeft = new JToggleButton(getIcon(size
				+ "/format-justify-left"));
		innerToolbar.add(buttonFormatLeft);
		JToggleButton buttonFormatRight = new JToggleButton(getIcon(size
				+ "/format-justify-right"));
		innerToolbar.add(buttonFormatRight);
		JToggleButton buttonFormatFill = new JToggleButton(getIcon(size
				+ "/format-justify-fill"));
		buttonFormatFill.putClientProperty(SubstanceLookAndFeel.CORNER_RADIUS,
				Float.valueOf(0.0f));
		innerToolbar.add(buttonFormatFill);

		toolBar.add(innerToolbar);
		toolBar.addSeparator();

		if (size > 20) {
			JToolBar innerToolbar2 = new JToolBar(JToolBar.HORIZONTAL);
			innerToolbar2.setFloatable(false);

			JPanel innerPanel = new JPanel(
					new FlowLayout(FlowLayout.LEFT, 0, 0));
			innerToolbar2.add(innerPanel, BorderLayout.CENTER);

			final JToggleButton buttonStyleBold = new JToggleButton(
					getIcon(size + "/format-text-bold"));
			Set<Side> rightSide = EnumSet.of(Side.RIGHT);
			buttonStyleBold.putClientProperty(
					SubstanceLookAndFeel.BUTTON_OPEN_SIDE_PROPERTY, rightSide);
			buttonStyleBold.putClientProperty(
					SubstanceLookAndFeel.CORNER_RADIUS, Float.valueOf(3.0f));

			final JToggleButton buttonStyleItalic = new JToggleButton(
					getIcon(size + "/format-text-italic"));
			buttonStyleItalic.putClientProperty(
					SubstanceLookAndFeel.CORNER_RADIUS, Float.valueOf(0.0f));
			buttonStyleItalic.putClientProperty(
					SubstanceLookAndFeel.BUTTON_OPEN_SIDE_PROPERTY, rightSide);

			final JToggleButton buttonStyleUnderline = new JToggleButton(
					getIcon(size + "/format-text-underline"));
			buttonStyleUnderline.putClientProperty(
					SubstanceLookAndFeel.CORNER_RADIUS, Float.valueOf(0.0f));
			buttonStyleUnderline.putClientProperty(
					SubstanceLookAndFeel.BUTTON_OPEN_SIDE_PROPERTY, rightSide);

			final JToggleButton buttonStyleStrikethrough = new JToggleButton(
					getIcon(size + "/format-text-strikethrough"));
			buttonStyleStrikethrough.putClientProperty(
					SubstanceLookAndFeel.BUTTON_SIDE_PROPERTY, EnumSet
							.of(Side.LEFT));
			buttonStyleStrikethrough.putClientProperty(
					SubstanceLookAndFeel.CORNER_RADIUS, Float.valueOf(3.0f));
			buttonStyleBold.setSelected(true);

			innerPanel.add(buttonStyleBold);
			innerPanel.add(buttonStyleItalic);
			innerPanel.add(buttonStyleUnderline);
			innerPanel.add(buttonStyleStrikethrough);

			toolBar.add(innerToolbar2);
		}

		toolBar.add(Box.createGlue());
		JButton buttonExit = new JButton(getIcon(size + "/process-stop"));
		buttonExit.setToolTipText("Closes the test application");
		buttonExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		toolBar.add(buttonExit);

		return toolBar;
	}

	public void setSpecificTaskPane(JPanel contents, String title, Icon icon) {
		if (currentSpecificTaskPane != null) {
			taskPaneContainer.remove(currentSpecificTaskPane);
		}
		currentSpecificTaskPane = new JXTaskPane();
		currentSpecificTaskPane.setLayout(new BorderLayout());
		currentSpecificTaskPane.setTitle(title);
		currentSpecificTaskPane.setIcon(icon);
		// contents.setOpaque(false);
		SwingUtilities.updateComponentTreeUI(contents);
		// currentSpecificTaskPane.setOpaque(false);
		currentSpecificTaskPane.add(contents, BorderLayout.CENTER);

		// this.mainTaskPane.setExpanded(false);
		taskPaneContainer.add(currentSpecificTaskPane);
	}

	public void clearSpecificTaskPane() {
		if (currentSpecificTaskPane != null) {
			taskPaneContainer.remove(currentSpecificTaskPane);
		}
		currentSpecificTaskPane = null;
		// this.mainTaskPane.setExpanded(true);
	}

	public class TabSwitchListener implements ChangeListener {
		public void stateChanged(ChangeEvent e) {
			Component selected = jtp.getSelectedComponent();
			if (selected instanceof Deferrable) {
				Deferrable deferrable = (Deferrable) selected;
				if (!deferrable.isInitialized())
					deferrable.initialize();
			}

			if (selected instanceof Controllable) {
				JPanel controlPanel = ((Controllable) selected)
						.getControlPanel();
				if (controlPanel == null)
					clearSpecificTaskPane();
				else
					setSpecificTaskPane(controlPanel, jtp.getTitleAt(jtp
							.getSelectedIndex()), jtp.getIconAt(jtp
							.getSelectedIndex()));
			} else {
				clearSpecificTaskPane();
			}
		}
	}

	public static class MyMainTabPreviewPainter extends
			DefaultTabPreviewPainter {
		protected TabOverviewKind tabOverviewKind;

		public void setTabOverviewKind(TabOverviewKind tabOverviewKind) {
			this.tabOverviewKind = tabOverviewKind;
		}

		@Override
		public TabOverviewKind getOverviewKind(JTabbedPane tabPane) {
			if (tabOverviewKind == null)
				return super.getOverviewKind(tabPane);
			return tabOverviewKind;
		}
	}

	public JTabbedPane getMainTabbedPane() {
		return this.jtp;
	}
}
