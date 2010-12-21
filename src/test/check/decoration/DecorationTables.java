package test.check.decoration;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.pushingpixels.substance.api.DecorationAreaType;
import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import org.pushingpixels.substance.api.skin.MistSilverSkin;

import test.check.SubstanceSkinComboSelector;

public class DecorationTables extends JFrame {
	private static class TablePanel extends JPanel {
		public TablePanel(String caption,
				DecorationAreaType decorationAreaType, boolean isEnabled) {
			super(new BorderLayout());
			SubstanceLookAndFeel.setDecorationType(this, decorationAreaType);

			JLabel captionLabel = new JLabel(" " + caption);
			Font font = captionLabel.getFont();
			captionLabel.setFont(font.deriveFont(Font.BOLD));
			this.add(captionLabel, BorderLayout.NORTH);
			JTable table = new JTable(new Object[][] { { "cell00", "cell01" },
					{ "cell10", "cell11" }, { "cell20", "cell21" },
					{ "cell30", "cell31" } }, new Object[] { "col1", "col2" });
			table.setEnabled(isEnabled);
			JScrollPane scroll = new JScrollPane(table);
			this.add(scroll, BorderLayout.CENTER);
			this.setBorder(new EmptyBorder(0, 1, 0, 1));
		}
	}

	public DecorationTables() {
		super("Tables in decoration areas");

		JPanel tablePanel = new JPanel(new GridLayout(2, 4));

		tablePanel.add(new TablePanel("Enabled in HEADER",
				DecorationAreaType.HEADER, true));
		tablePanel.add(new TablePanel("Disabled in HEADER",
				DecorationAreaType.HEADER, false));
		tablePanel.add(new TablePanel("Enabled in NONE",
				DecorationAreaType.NONE, true));
		tablePanel.add(new TablePanel("Disabled in NONE",
				DecorationAreaType.NONE, false));
		tablePanel.add(new TablePanel("Enabled in GENERAL",
				DecorationAreaType.GENERAL, true));
		tablePanel.add(new TablePanel("Disabled in GENERAL",
				DecorationAreaType.GENERAL, false));
		tablePanel.add(new TablePanel("Enabled in FOOTER",
				DecorationAreaType.FOOTER, true));
		tablePanel.add(new TablePanel("Disabled in FOOTER",
				DecorationAreaType.FOOTER, false));

		this.setLayout(new BorderLayout());
		this.add(tablePanel, BorderLayout.CENTER);

		JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		controlPanel.add(new SubstanceSkinComboSelector());

		this.add(controlPanel, BorderLayout.SOUTH);

		this.setSize(500, 400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				SubstanceLookAndFeel.setSkin(new MistSilverSkin());
				new DecorationTables().setVisible(true);
			}
		});
	}
}
