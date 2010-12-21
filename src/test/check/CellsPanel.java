package test.check;

import java.awt.*;
import java.util.Enumeration;

import javax.swing.*;
import javax.swing.tree.*;

import org.pushingpixels.substance.api.DecorationAreaType;
import org.pushingpixels.substance.api.SubstanceLookAndFeel;

import test.check.command.*;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

public class CellsPanel extends JPanel implements Deferrable {
	private boolean isInitialized;

	@Override
	public boolean isInitialized() {
		return this.isInitialized;
	}

	public CellsPanel() {
	}
	
	@Override
	public synchronized void initialize() {
		FormLayout lmCells = new FormLayout(
				"right:pref, 10dlu, fill:pref:grow(1), 4dlu,"
						+ "fill:pref:grow(1), 4dlu, fill:pref:grow(1), "
						+ "4dlu, fill:pref:grow(1)", "");
		lmCells.setColumnGroups(new int[][] { { 3, 5, 7, 9 } });
		DefaultFormBuilder builderCells = new DefaultFormBuilder(lmCells,
				new ScrollablePanel());
		builderCells.setDefaultDialogBorder();

		builderCells.append("");
		builderCells.append(new JLabel("NONE"), new JLabel("GENERAL"));
		builderCells.append(new JLabel("HEADER"), new JLabel("FOOTER"));

		builderCells.appendSeparator("Lists");
		addControlRow(builderCells, "List", new CreationCommand<Component>() {
			public Component create() {
				JList list = new JList(new Object[] { "entry1", "entry2",
						"entry3" });
				return list;
			}
		}, null);
		addControlRow(builderCells, "List watermark",
				new CreationCommand<Component>() {
					public Component create() {
						JList list = new JList(new Object[] { "entry1",
								"entry2", "entry3" });
						return list;
					}
				}, new ClientPropertyCommand(
						SubstanceLookAndFeel.WATERMARK_VISIBLE, Boolean.TRUE));
		addControlRow(builderCells, "List disabled",
				new CreationCommand<Component>() {
					public Component create() {
						JList list = new JList(new Object[] { "entry1",
								"entry2", "entry3" });
						return list;
					}
				}, new DisableCommand());

		CreationCommand<Component> tableCreationCmd = new CreationCommand<Component>() {
			public Component create() {
				final JTable table = new JTable(new Object[][] {
						{ "entry11", "entry12", "entry13" },
						{ "entry21", "entry22", "entry23" },
						{ "entry31", "entry32", "entry33" } }, new Object[] {
						"Column1", "Column2", "Column3" });
				table.setName("Table ");
				JScrollPane tableScroll = new JScrollPane(table) {
					@Override
					public void setBackground(Color bg) {
						super.setBackground(bg);
						table.setBackground(bg);
						table.getTableHeader().setBackground(bg);
					}

					@Override
					public void setForeground(Color fg) {
						super.setForeground(fg);
						table.setForeground(fg);
						table.getTableHeader().setForeground(fg);
					}
				};
				Dimension prefTable = table.getPreferredSize();
				tableScroll.setPreferredSize(new Dimension(prefTable.width,
						prefTable.height + 25));
				return tableScroll;
			}
		};
		builderCells.appendSeparator("Tables");
		addControlRow(builderCells, "Table", tableCreationCmd, null);
		addControlRow(builderCells, "Table watermark", tableCreationCmd,
				new ClientPropertyCommand(
						SubstanceLookAndFeel.WATERMARK_VISIBLE, Boolean.TRUE));
		addControlRow(builderCells, "Table disabled", tableCreationCmd,
				new DisableViewportCommand());

		CreationCommand<Component> treeCreationCmd = new CreationCommand<Component>() {
			public void expandAll(JTree tree, boolean expand) {
				TreeNode root = (TreeNode) tree.getModel().getRoot();

				// Traverse tree from root
				expandAll(tree, new TreePath(root), expand);
			}

			private void expandAll(JTree tree, TreePath parent, boolean expand) {
				// Traverse children
				TreeNode node = (TreeNode) parent.getLastPathComponent();
				if (node.getChildCount() >= 0) {
					for (Enumeration e = node.children(); e.hasMoreElements();) {
						TreeNode n = (TreeNode) e.nextElement();
						TreePath path = parent.pathByAddingChild(n);
						expandAll(tree, path, expand);
					}
				}

				// Expansion or collapse must be done bottom-up
				if (expand) {
					tree.expandPath(parent);
				} else {
					tree.collapsePath(parent);
				}
			}

			public Component create() {
				DefaultMutableTreeNode root = new DefaultMutableTreeNode("root");
				DefaultMutableTreeNode son1 = new DefaultMutableTreeNode("son1");
				DefaultMutableTreeNode son2 = new DefaultMutableTreeNode("son2");
				DefaultMutableTreeNode son3 = new DefaultMutableTreeNode("son3");
				DefaultMutableTreeNode gson11 = new DefaultMutableTreeNode(
						"gson11");
				DefaultMutableTreeNode gson12 = new DefaultMutableTreeNode(
						"gson12");
				DefaultMutableTreeNode gson21 = new DefaultMutableTreeNode(
						"gson21");
				DefaultMutableTreeNode gson22 = new DefaultMutableTreeNode(
						"gson22");
				DefaultMutableTreeNode gson31 = new DefaultMutableTreeNode(
						"gson31");
				DefaultMutableTreeNode gson32 = new DefaultMutableTreeNode(
						"gson32");
				DefaultMutableTreeNode ggson111 = new DefaultMutableTreeNode(
						"ggson111");
				DefaultMutableTreeNode ggson112 = new DefaultMutableTreeNode(
						"ggson112");
				DefaultMutableTreeNode ggson113 = new DefaultMutableTreeNode(
						"ggson113");

				gson11.add(ggson111);
				gson11.add(ggson112);
				gson11.add(ggson113);
				son1.add(gson11);
				son1.add(gson12);
				son2.add(gson21);
				son2.add(gson22);
				son3.add(gson31);
				son3.add(gson32);
				root.add(son1);
				root.add(son2);
				root.add(son3);

				JTree tree = new JTree(root);
				expandAll(tree, true);
				return tree;
			}
		};
		builderCells.appendSeparator("Trees");
		addControlRow(builderCells, "Tree", treeCreationCmd, null);
		addControlRow(builderCells, "Tree watermark", treeCreationCmd,
				new ClientPropertyCommand(
						SubstanceLookAndFeel.WATERMARK_VISIBLE, Boolean.TRUE));
		addControlRow(builderCells, "Tree disabled", treeCreationCmd,
				new DisableCommand());

		JPanel panelCells = builderCells.getPanel();
		JScrollPane jspCells = new JScrollPane(panelCells);
		panelCells.setOpaque(false);
		jspCells.setOpaque(false);
		jspCells.getViewport().setOpaque(false);

		this.setLayout(new BorderLayout());
		this.add(jspCells);
		
		this.isInitialized = true;
	}

	/**
	 * Adds a row of components configured with the specified configuration
	 * command.
	 * 
	 * @param builder
	 *            Form builder.
	 * @param label
	 *            Text to set.
	 * @param creationCmd
	 *            Creation command.
	 * @param configurationCmd
	 *            Configuration command to apply.
	 */
	private void addControlRow(DefaultFormBuilder builder, String label,
			CreationCommand<Component> creationCmd,
			ConfigurationCommand<Component> configurationCmd) {

		Component[] row = new Component[4];
		row[0] = creationCmd.create();
		SubstanceLookAndFeel.setDecorationType(((JComponent) row[0]),
				DecorationAreaType.NONE);
		row[0].setName(row[0].getName() + ": NONE");

		row[1] = creationCmd.create();
		SubstanceLookAndFeel.setDecorationType(((JComponent) row[1]),
				DecorationAreaType.GENERAL);
		row[1].setName(row[1].getName() + ": GENERAL");

		row[2] = creationCmd.create();
		SubstanceLookAndFeel.setDecorationType(((JComponent) row[2]),
				DecorationAreaType.HEADER);
		row[2].setName(row[2].getName() + ": HEADER");

		row[3] = creationCmd.create();
		SubstanceLookAndFeel.setDecorationType(((JComponent) row[3]),
				DecorationAreaType.FOOTER);
		row[3].setName(row[3].getName() + ": FOOTER");

		if (configurationCmd != null) {
			for (Component comp : row) {
				configurationCmd.configure(comp);
			}
		}

		JLabel jl = new JLabel(label);
		builder.append(jl);
		for (Component comp : row)
			builder.append(comp);
	}

}
