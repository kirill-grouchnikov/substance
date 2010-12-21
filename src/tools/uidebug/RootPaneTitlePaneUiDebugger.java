package tools.uidebug;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.TransferHandler;
import javax.swing.UIManager;

import org.pushingpixels.lafwidget.LafWidgetAdapter;
import org.pushingpixels.lafwidget.animation.AnimationConfigurationManager;
import org.pushingpixels.lafwidget.animation.effects.GhostPaintingUtils;
import org.pushingpixels.substance.api.ColorSchemeTransform;
import org.pushingpixels.substance.api.ComponentState;
import org.pushingpixels.substance.api.SubstanceColorScheme;
import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import org.pushingpixels.substance.api.SubstanceSkin;
import org.pushingpixels.substance.api.SubstanceConstants.FocusKind;
import org.pushingpixels.substance.internal.utils.LazyResettableHashMap;
import org.pushingpixels.substance.internal.utils.SubstanceCoreUtilities;

public class RootPaneTitlePaneUiDebugger extends LafWidgetAdapter<JRootPane> {
	protected MouseListener substanceDebugUiListener;

	protected JComponent titlePane;

	@Override
	public boolean requiresCustomLafSupport() {
		return false;
	}

	@Override
	public void installUI() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				if (!(UIManager.getLookAndFeel() instanceof SubstanceLookAndFeel))
					return;

				titlePane = SubstanceLookAndFeel
						.getTitlePaneComponent(SwingUtilities
								.getWindowAncestor(jcomp));
				if (titlePane != null) {
					substanceDebugUiListener = new MouseAdapter() {
						@Override
						public void mousePressed(MouseEvent e) {
							process(e);
						}

						@Override
						public void mouseReleased(MouseEvent e) {
							process(e);
						}

						protected void process(MouseEvent e) {
							if (e.isPopupTrigger()) {
								JPopupMenu popup = new JPopupMenu();
								JMenu cbMenu = new JMenu("Color blindness");
								JMenuItem protanopiaCurrent = new JMenuItem(
										"Protanopia current");
								protanopiaCurrent
										.addActionListener(new SkinChanger(
												new ColorSchemeTransform() {
													public SubstanceColorScheme transform(
															SubstanceColorScheme scheme) {
														return new ProtanopiaColorScheme(
																scheme);
													}
												}, "Protanopia current"));
								cbMenu.add(protanopiaCurrent);
								JMenuItem deuteranopiaCurrent = new JMenuItem(
										"Deuteranopia current");
								deuteranopiaCurrent
										.addActionListener(new SkinChanger(
												new ColorSchemeTransform() {
													public SubstanceColorScheme transform(
															SubstanceColorScheme scheme) {
														return new DeuteranopiaColorScheme(
																scheme);
													}
												}, "Deuteranopia current"));
								cbMenu.add(deuteranopiaCurrent);
								JMenuItem tritanopiaCurrent = new JMenuItem(
										"Tritanopia current");
								tritanopiaCurrent
										.addActionListener(new SkinChanger(
												new ColorSchemeTransform() {
													public SubstanceColorScheme transform(
															SubstanceColorScheme scheme) {
														return new TritanopiaColorScheme(
																scheme);
													}
												}, "Tritanopia current"));
								cbMenu.add(tritanopiaCurrent);

								cbMenu.addSeparator();

								JMenuItem restoreOriginal = new JMenuItem(
										"Restore original");
								if (SubstanceLookAndFeel.getCurrentSkin(null)
										.getColorScheme(null,
												ComponentState.ENABLED) instanceof ColorBlindColorScheme) {
									restoreOriginal
											.addActionListener(new SkinChanger(
													new ColorSchemeTransform() {
														public SubstanceColorScheme transform(
																SubstanceColorScheme scheme) {
															if (scheme instanceof ColorBlindColorScheme)
																return ((ColorBlindColorScheme) scheme)
																		.getOrigScheme();
															return scheme;
														}
													}, "Current"));
								} else {
									restoreOriginal.setEnabled(false);
								}
								cbMenu.add(restoreOriginal);

								popup.add(cbMenu);

								JMenu animMenu = new JMenu("Animation rate");
								JMenuItem debugNone = new JMenuItem("None");
								debugNone
										.addActionListener(new AnimationChanger(
												0));
								animMenu.add(debugNone);
								JMenuItem debugAnim = new JMenuItem(
										"Debug rate (extra slow)");
								debugAnim
										.addActionListener(new AnimationChanger(
												5000));
								animMenu.add(debugAnim);
								JMenuItem debugAnimFast = new JMenuItem(
										"Debug rate (faster)");
								debugAnimFast
										.addActionListener(new AnimationChanger(
												2500));
								animMenu.add(debugAnimFast);
								JMenuItem debugSlow = new JMenuItem("Slow rate");
								debugSlow
										.addActionListener(new AnimationChanger(
												1000));
								animMenu.add(debugSlow);
								JMenuItem debugRegular = new JMenuItem(
										"Regular rate");
								debugRegular
										.addActionListener(new AnimationChanger(
												250));
								animMenu.add(debugRegular);
								JMenuItem debugFast = new JMenuItem("Fast rate");
								debugFast
										.addActionListener(new AnimationChanger(
												100));
								animMenu.add(debugFast);

								popup.add(animMenu);

								JMenu focusMenu = new JMenu("Focus kind");
								for (FocusKind fKind : FocusKind.values()) {
									JMenuItem focusMenuItem = new JMenuItem(
											fKind.name().toLowerCase());
									focusMenuItem
											.addActionListener(new FocusKindChanger(
													fKind));
									focusMenu.add(focusMenuItem);
								}
								popup.add(focusMenu);

								JMenuItem dumpHierarchy = new JMenuItem(
										"Dump hierarchy");
								dumpHierarchy
										.addActionListener(new ActionListener() {
											public void actionPerformed(
													ActionEvent e) {
												dump(jcomp, 0);
											}
										});
								popup.add(dumpHierarchy);

								final JCheckBoxMenuItem ltrChange = new JCheckBoxMenuItem(
										"Is left-to-right");
								ltrChange.setSelected(jcomp
										.getComponentOrientation()
										.isLeftToRight());
								ltrChange
										.addActionListener(new ActionListener() {
											public void actionPerformed(
													ActionEvent e) {
												SwingUtilities
														.invokeLater(new Runnable() {
															public void run() {
																jcomp
																		.applyComponentOrientation(ltrChange
																				.isSelected() ? ComponentOrientation.LEFT_TO_RIGHT
																				: ComponentOrientation.RIGHT_TO_LEFT);
															}
														});
											}
										});
								popup.add(ltrChange);

								final JCheckBoxMenuItem useThemedIcons = new JCheckBoxMenuItem(
										"Use themed icons");
								useThemedIcons
										.setSelected(SubstanceCoreUtilities
												.useThemedDefaultIcon(null));
								useThemedIcons
										.addActionListener(new ActionListener() {
											public void actionPerformed(
													ActionEvent e) {
												SwingUtilities
														.invokeLater(new Runnable() {
															public void run() {
																UIManager
																		.put(
																				SubstanceLookAndFeel.USE_THEMED_DEFAULT_ICONS,
																				useThemedIcons
																						.isSelected() ? Boolean.TRUE
																						: null);
																jcomp.repaint();
															}
														});
											}
										});
								popup.add(useThemedIcons);

								final JCheckBoxMenuItem ghostDebugMode = new JCheckBoxMenuItem(
										"Ghost debug mode");
								ghostDebugMode
										.addActionListener(new ActionListener() {
											public void actionPerformed(
													ActionEvent e) {
												SwingUtilities
														.invokeLater(new Runnable() {
															public void run() {
																ghostDebugMode
																		.setEnabled(false);
																GhostPaintingUtils.MAX_ICON_GHOSTING_ALPHA = 0.8f;
																GhostPaintingUtils.MIN_ICON_GHOSTING_ALPHA = 0.6f;
																GhostPaintingUtils.MAX_PRESS_GHOSTING_ALPHA = 0.8f;
																GhostPaintingUtils.MIN_PRESS_GHOSTING_ALPHA = 0.6f;
																GhostPaintingUtils.DECAY_FACTOR = 0.7f;
															}
														});
											}
										});
								popup.add(ghostDebugMode);

								JMenuItem showCacheStats = new JMenuItem(
										"Show cache stats");
								showCacheStats
										.addActionListener(new ActionListener() {
											@Override
											public void actionPerformed(
													ActionEvent e) {
												SwingUtilities
														.invokeLater(new Runnable() {
															public void run() {
																final JTextArea textArea = new JTextArea();
																java.util.List<String> stats = LazyResettableHashMap
																		.getStats();
																if (stats != null) {
																	for (String stat : stats) {
																		textArea
																				.append(stat
																						+ "\n");
																	}
																}
																final JDialog dialog = new JDialog(
																		SwingUtilities
																				.getWindowAncestor(jcomp),
																		ModalityType.APPLICATION_MODAL);
																dialog
																		.setTitle("Substance cache stats");
																dialog
																		.setLayout(new BorderLayout());
																dialog
																		.add(
																				new JScrollPane(
																						textArea),
																				BorderLayout.CENTER);
																JButton dismiss = new JButton(
																		"Dismiss");
																dismiss
																		.addActionListener(new ActionListener() {
																			public void actionPerformed(
																					ActionEvent e) {
																				dialog
																						.dispose();
																			}
																		});
																JButton copyToClipboard = new JButton(
																		"Copy to clipboard");
																copyToClipboard
																		.addActionListener(new ActionListener() {
																			public void actionPerformed(
																					ActionEvent e) {
																				textArea
																						.selectAll();
																				TransferHandler
																						.getCopyAction()
																						.actionPerformed(
																								new ActionEvent(
																										textArea,
																										ActionEvent.ACTION_PERFORMED,
																										"Copy"));
																			}
																		});
																JPanel controls = new JPanel(
																		new FlowLayout(
																				FlowLayout.RIGHT));
																controls
																		.add(copyToClipboard);
																controls
																		.add(dismiss);
																dialog
																		.add(
																				controls,
																				BorderLayout.SOUTH);

																dialog.setSize(
																		500,
																		400);
																dialog
																		.setLocationRelativeTo(SwingUtilities
																				.getRootPane(jcomp));
																dialog
																		.setVisible(true);
															}
														});
											}
										});
								popup.add(showCacheStats);

								popup.show(titlePane, e.getX(), e.getY());
							}
						}
					};
					titlePane.addMouseListener(substanceDebugUiListener);
				}
			}
		});
	}

	@Override
	public void uninstallUI() {
		if (this.substanceDebugUiListener != null) {
			titlePane.removeMouseListener(this.substanceDebugUiListener);
			this.substanceDebugUiListener = null;
		}
	}

	protected static class SkinChanger implements ActionListener {
		protected ColorSchemeTransform transform;

		protected String name;

		public SkinChanger(ColorSchemeTransform transform, String name) {
			super();
			this.transform = transform;
			this.name = name;
		}

		public void actionPerformed(ActionEvent e) {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					SubstanceSkin newSkin = SubstanceLookAndFeel
							.getCurrentSkin(null).transform(transform, name);
					SubstanceLookAndFeel.setSkin(newSkin);
				}
			});
		}
	}

	protected static class AnimationChanger implements ActionListener {
		protected long newAnimationDuration;

		public AnimationChanger(long newAnimationDuration) {
			super();
			this.newAnimationDuration = newAnimationDuration;
		}

		public void actionPerformed(ActionEvent e) {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					AnimationConfigurationManager.getInstance()
							.setTimelineDuration(newAnimationDuration);
				}
			});
		}
	}

	protected static class FocusKindChanger implements ActionListener {
		protected FocusKind newFocusKind;

		public FocusKindChanger(FocusKind newFocusKind) {
			super();
			this.newFocusKind = newFocusKind;
		}

		public void actionPerformed(ActionEvent e) {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					UIManager
							.put(SubstanceLookAndFeel.FOCUS_KIND, newFocusKind);
				}
			});
		}
	}

	public static void dump(Component comp, int level) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < level; i++)
			sb.append("  ");
		sb.append(comp.toString());
		System.out.println(sb);
		if (comp instanceof Container) {
			Container cont = (Container) comp;
			for (int i = 0; i < cont.getComponentCount(); i++) {
				dump(cont.getComponent(i), level + 1);
			}
		}
	}
}
