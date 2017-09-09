package test;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;

public class Walkthrough extends JFrame {
    public Walkthrough() {
        super("Sample app");
        this.setLayout(new FlowLayout());
        this.add(new JButton("button"));
        this.add(new JCheckBox("check"));
        this.add(new JLabel("label"));

        this.setIconImage(new BufferedImage(1, 1, BufferedImage.TYPE_4BYTE_ABGR));
        this.setSize(new Dimension(250, 80));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(new MetalLookAndFeel());
                } catch (Throwable t) {
                }
                // SubstanceLookAndFeel.setSkin(new BusinessSkin());
                Walkthrough w = new Walkthrough();
                w.setVisible(true);
            }
        });
    }
}