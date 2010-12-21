package test.check.command;

import java.awt.Component;

import javax.swing.AbstractButton;
import javax.swing.JButton;


/**
 * A configure command that selects the specified component.
 * 
 * @author Kirill Grouchnikov
 */
public class SelectCommand implements ConfigurationCommand<Component> {
	/*
	 * (non-Javadoc)
	 * 
	 * @see test.check.ConfigurationCommand#invoke(java.lang.Object)
	 */
	public void configure(Component ab) {
		if (ab instanceof JButton)
			return;
		if (ab instanceof AbstractButton)
			((AbstractButton) ab).setSelected(true);
	}
}