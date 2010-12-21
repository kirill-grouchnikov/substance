package test.check.decoration;

import java.awt.*;

import javax.swing.*;

import org.pushingpixels.substance.api.DecorationAreaType;
import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import org.pushingpixels.substance.api.skin.BusinessBlackSteelSkin;

import test.check.SubstanceSkinComboSelector;

public class DecorationLists extends JFrame {
	private static class ListPanel extends JPanel {
		public ListPanel(String caption, DecorationAreaType decorationAreaType,
				boolean isEnabled) {
			super(new BorderLayout());
			SubstanceLookAndFeel.setDecorationType(this, decorationAreaType);

			JLabel captionLabel = new JLabel(" " + caption);
			Font font = captionLabel.getFont();
			captionLabel.setFont(font.deriveFont(Font.BOLD));
			this.add(captionLabel, BorderLayout.NORTH);
			JList list = new JList(new Object[] { "entry1", "entry2", "entry3",
					"entry4" });
			list.setEnabled(isEnabled);
			this.add(list, BorderLayout.CENTER);
		}
	}

	public DecorationLists() {
		super("Lists in decoration areas");

		JPanel listsPanel = new JPanel(new GridLayout(2, 4));

		listsPanel.add(new ListPanel("Enabled in HEADER",
				DecorationAreaType.HEADER, true));
		listsPanel.add(new ListPanel("Disabled in HEADER",
				DecorationAreaType.HEADER, false));
		listsPanel.add(new ListPanel("Enabled in NONE",
				DecorationAreaType.NONE, true));
		listsPanel.add(new ListPanel("Disabled in NONE",
				DecorationAreaType.NONE, false));
		listsPanel.add(new ListPanel("Enabled in GENERAL",
				DecorationAreaType.GENERAL, true));
		listsPanel.add(new ListPanel("Disabled in GENERAL",
				DecorationAreaType.GENERAL, false));
		listsPanel.add(new ListPanel("Enabled in FOOTER",
				DecorationAreaType.FOOTER, true));
		listsPanel.add(new ListPanel("Disabled in FOOTER",
				DecorationAreaType.FOOTER, false));

		this.setLayout(new BorderLayout());
		this.add(listsPanel, BorderLayout.CENTER);

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
				SubstanceLookAndFeel.setSkin(new BusinessBlackSteelSkin());
				new DecorationLists().setVisible(true);
			}
		});
	}
}
