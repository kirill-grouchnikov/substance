package test.check.command;

import java.awt.Component;


/**
 * A configure command that disables the specified component.
 * 
 * @author Kirill Grouchnikov
 */
public class DisableCommand implements ConfigurationCommand<Component> {
	/*
	 * (non-Javadoc)
	 * 
	 * @see test.check.ConfigurationCommand#invoke(java.lang.Object)
	 */
	public void configure(Component ab) {
		ab.setEnabled(false);
	}
}