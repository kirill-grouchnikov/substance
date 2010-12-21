package test.check;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import org.pushingpixels.substance.api.skin.SubstanceBusinessBlackSteelLookAndFeel;

public class AnimateProgressBar extends JFrame {
	public AnimateProgressBar() {
		this.setLayout(new BorderLayout());
		final JProgressBar jpb = new JProgressBar(0, 15);
		this.add(jpb, BorderLayout.CENTER);

		JPanel controls = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton forw = new JButton("Increment");
		forw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jpb.setValue(jpb.getValue() + 1);
			}
		});
		controls.add(forw);
		this.add(controls, BorderLayout.SOUTH);

		this.setSize(300, 100);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) throws Exception {
		UIManager.setLookAndFeel(new SubstanceBusinessBlackSteelLookAndFeel());
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new AnimateProgressBar().setVisible(true);
			}
		});
	}

}
