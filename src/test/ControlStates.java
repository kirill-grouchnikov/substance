package test;

import java.awt.FlowLayout;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.pushingpixels.substance.api.SubstanceConstants;
import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import org.pushingpixels.substance.api.skin.BusinessBlackSteelSkin;

public class ControlStates extends JFrame {
	public ControlStates() {
		super("Control states");

        this.setLayout(new FlowLayout());
        JButton defaultButton = new JButton("active");
        JButton button = new JButton("default");
        JButton disabledButton = new JButton("disabled");
        disabledButton.setEnabled(false);
        this.getRootPane().setDefaultButton(defaultButton);

        this.add(defaultButton);
        this.add(button);
        this.add(disabledButton);

        this.setVisible(true);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setIconImage(new BufferedImage(1, 1, BufferedImage.TYPE_4BYTE_ABGR));
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame.setDefaultLookAndFeelDecorated(true);
				UIManager.put(SubstanceLookAndFeel.FOCUS_KIND,
						SubstanceConstants.FocusKind.NONE);
				SubstanceLookAndFeel.setSkin(new BusinessBlackSteelSkin());
				new ControlStates().setVisible(true);
			}
		});
	}

}
