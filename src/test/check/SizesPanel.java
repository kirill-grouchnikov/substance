package test.check;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;

import org.pushingpixels.lafwidget.LafWidget;
import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import org.pushingpixels.substance.api.renderers.SubstanceDefaultListCellRenderer;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

public class SizesPanel extends JPanel {
	private static interface Creator {
		public JComponent create(int fontSize);
	}

	private static class Mapping {
		public String caption;

		public Creator creator;

		public Mapping(String caption, Creator creator) {
			super();
			this.caption = caption;
			this.creator = creator;
		}
	}

	private List<Mapping> model;

	private JScrollPane central;

	public SizesPanel() {
		this.model = new LinkedList<Mapping>();
		Font base = new Font("Tahoma", Font.PLAIN, 11);
		if (UIManager.getLookAndFeel() instanceof SubstanceLookAndFeel) {
			base = SubstanceLookAndFeel.getFontPolicy().getFontSet("Substance",
					null).getControlFont();
		}
		final Font baseFinal = base;
		this.model.add(new Mapping("buttons", new Creator() {
			public JComponent create(int fontSize) {
				JButton result = new JButton("size " + fontSize);
				result.setFont(baseFinal.deriveFont((float) fontSize));
				return result;
			}
		}));
		this.model.add(new Mapping("toggle buttons", new Creator() {
			public JComponent create(int fontSize) {
				JToggleButton result = new JToggleButton("size " + fontSize);
				result.setFont(baseFinal.deriveFont((float) fontSize));
				return result;
			}
		}));
		this.model.add(new Mapping("check boxes", new Creator() {
			public JComponent create(int fontSize) {
				JCheckBox result = new JCheckBox("size " + fontSize);
				result.setFont(baseFinal.deriveFont((float) fontSize));
				return result;
			}
		}));
		this.model.add(new Mapping("radio buttons", new Creator() {
			public JComponent create(int fontSize) {
				JRadioButton result = new JRadioButton("size " + fontSize);
				result.setFont(baseFinal.deriveFont((float) fontSize));
				return result;
			}
		}));
		this.model.add(new Mapping("combo boxes", new Creator() {
			public JComponent create(int fontSize) {
				JComboBox result = new JComboBox(new Object[] { "size "
						+ fontSize });
				result.setFont(baseFinal.deriveFont((float) fontSize));
				return result;
			}
		}));
		this.model.add(new Mapping("editable combo boxes", new Creator() {
			public JComponent create(int fontSize) {
				JComboBox result = new JComboBox(new Object[] { "size "
						+ fontSize });
				result.setEditable(true);
				result.setFont(baseFinal.deriveFont((float) fontSize));
				return result;
			}
		}));
		this.model.add(new Mapping("spinners", new Creator() {
			public JComponent create(int fontSize) {
				JSpinner result = new JSpinner(new SpinnerNumberModel(fontSize,
						fontSize - 10, fontSize + 10, 1));
				result.setFont(baseFinal.deriveFont((float) fontSize));
				return result;
			}
		}));
		this.model.add(new Mapping("text fields", new Creator() {
			public JComponent create(int fontSize) {
				JTextField result = new JTextField("size " + fontSize);
				result.setFont(baseFinal.deriveFont((float) fontSize));
				return result;
			}
		}));
		this.model.add(new Mapping("locked text fields", new Creator() {
			public JComponent create(int fontSize) {
				JTextField result = new JTextField("size " + fontSize);
				result.setEditable(false);
				result.putClientProperty(LafWidget.HAS_LOCK_ICON, Boolean.TRUE);
				result.setFont(baseFinal.deriveFont((float) fontSize));
				return result;
			}
		}));
		this.model.add(new Mapping("password fields", new Creator() {
			public JComponent create(int fontSize) {
				JPasswordField result = new JPasswordField("size " + fontSize);
				result.setFont(baseFinal.deriveFont((float) fontSize));
				return result;
			}
		}));
		// this.model.add(new Mapping("scroll bars", new Creator() {
		// public JComponent create(int fontSize) {
		// JScrollBar result = new JScrollBar(JScrollBar.HORIZONTAL);
		// result.setFont(baseFinal.deriveFont((float) fontSize));
		// return result;
		// }
		// }));
		this.model.add(new Mapping("progress bars", new Creator() {
			public JComponent create(int fontSize) {
				JProgressBar result = new JProgressBar(JProgressBar.HORIZONTAL);
				result.setMinimum(0);
				result.setMaximum(100);
				result.setValue(60);
				result.setFont(baseFinal.deriveFont((float) fontSize));
				return result;
			}
		}));
		this.model.add(new Mapping("progress bars 2", new Creator() {
			public JComponent create(int fontSize) {
				JProgressBar result = new JProgressBar(JProgressBar.HORIZONTAL);
				result.setMinimum(0);
				result.setMaximum(100);
				result.setValue(60);
				result.setStringPainted(true);
				result.setFont(baseFinal.deriveFont((float) fontSize));
				return result;
			}
		}));
		this.model.add(new Mapping("sliders", new Creator() {
			public JComponent create(int fontSize) {
				JSlider result = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
				result.setFont(baseFinal.deriveFont((float) fontSize));
				return result;
			}
		}));
		this.model.add(new Mapping("sliders 2", new Creator() {
			public JComponent create(int fontSize) {
				JSlider result = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
				result.setMajorTickSpacing(20);
				result.setMinorTickSpacing(5);
				result.setPaintLabels(true);
				result.setFont(baseFinal.deriveFont((float) fontSize));
				return result;
			}
		}));
		this.model.add(new Mapping("sliders 3", new Creator() {
			public JComponent create(int fontSize) {
				JSlider result = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
				result.setMajorTickSpacing(20);
				result.setMinorTickSpacing(5);
				result.setPaintTicks(true);
				result.setFont(baseFinal.deriveFont((float) fontSize));
				return result;
			}
		}));
		this.model.add(new Mapping("sliders 4", new Creator() {
			public JComponent create(int fontSize) {
				JSlider result = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
				result.setMajorTickSpacing(20);
				result.setMinorTickSpacing(5);
				result.setPaintTicks(true);
				result.setPaintLabels(true);
				result.setFont(baseFinal.deriveFont((float) fontSize));
				return result;
			}
		}));
		this.model.add(new Mapping("trees", new Creator() {
			public JComponent create(int fontSize) {
				DefaultMutableTreeNode root = new DefaultMutableTreeNode(
						"size " + fontSize);
				DefaultMutableTreeNode son1 = new DefaultMutableTreeNode("son1");
				DefaultMutableTreeNode gson11 = new DefaultMutableTreeNode(
						"gson11");
				son1.add(gson11);
				root.add(son1);
				JTree result = new JTree(root);
				// result.setRootVisible(false);
				// result.setShowsRootHandles(true);
				result.setFont(baseFinal.deriveFont((float) fontSize));
				return result;
			}
		}));
		this.model.add(new Mapping("lists", new Creator() {
			public JComponent create(int fontSize) {
				JList result = new JList(new Object[] { "item1", "item2" });
				result.setFont(baseFinal.deriveFont((float) fontSize));
				return result;
			}
		}));
		this.model.add(new Mapping("tables", new Creator() {
			public JComponent create(int fontSize) {
				DefaultTableModel model = new DefaultTableModel() {
					@Override
					public int getRowCount() {
						return 2;
					}

					@Override
					public int getColumnCount() {
						return 3;
					}

					@Override
					public Object getValueAt(int row, int column) {
						return row + ":" + column;
					}

					@Override
					public boolean isCellEditable(int row, int column) {
						return false;
					}

					@Override
					public String getColumnName(int column) {
						return "Column " + column;
					}
				};
				JTable result = new JTable(model);
				result.setFont(baseFinal.deriveFont((float) fontSize));
				return result;
			}
		}));

		final JList list = new JList(new AbstractListModel() {
			public Object getElementAt(int index) {
				return model.get(index);
			}

			public int getSize() {
				return model.size();
			}
		});
		if (UIManager.getLookAndFeel() instanceof SubstanceLookAndFeel) {
			list.setCellRenderer(new SubstanceDefaultListCellRenderer() {
				@Override
				public Component getListCellRendererComponent(JList list,
						Object value, int index, boolean isSelected,
						boolean cellHasFocus) {
					return super.getListCellRendererComponent(list,
							((Mapping) value).caption, index, isSelected,
							cellHasFocus);
				}
			});
		}
		this.setLayout(new BorderLayout());
		this.add(new JScrollPane(list), BorderLayout.WEST);

		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		list.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent e) {
						SwingUtilities.invokeLater(new Runnable() {
							public void run() {
								if (central != null)
									remove(central);
								central = null;

								int selIndex = list.getSelectedIndex();
								if (selIndex >= 0) {
									Mapping sel = (Mapping) list
											.getSelectedValue();
									FormLayout lm = new FormLayout(
											"right:pref, 4dlu, left:pref:grow",
											"");
									DefaultFormBuilder builder = new DefaultFormBuilder(
											lm, new ScrollablePanel());
									builder.setDefaultDialogBorder();
									for (int fontSize = 11; fontSize < 25; fontSize++) {
										builder.append(fontSize + " pixels",
												sel.creator.create(fontSize));
									}
									builder.append("72 pixels", sel.creator
											.create(72));
									central = new JScrollPane(
											builder.getPanel(),
											JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
											JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
									add(central, BorderLayout.CENTER);
									doLayout();
									revalidate();
								}
							}
						});
					}
				});
	}
}
