package test.check.command;

import java.awt.Component;
import java.beans.PropertyVetoException;

import javax.swing.JInternalFrame;

/**
 * A configure command that minimizes internal frames.
 * 
 * @author Kirill Grouchnikov
 */
public class MinimizeInternalFrameCommand implements
		ConfigurationCommand<Component> {
	/*
	 * (non-Javadoc)
	 * 
	 * @see test.check.ConfigurationCommand#invoke(java.lang.Object)
	 */
	public void configure(Component ab) {
		if (ab instanceof JInternalFrame) {
			JInternalFrame jif = (JInternalFrame) ab;
			try {
				jif.setIcon(true);
			} catch (PropertyVetoException pve) {
			}
		}
	}
}