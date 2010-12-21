package test.check.command;

import java.awt.Component;

/**
 * A configure command that enables the specified component.
 * 
 * @author Kirill Grouchnikov
 */
public class EnableCommand implements ConfigurationCommand<Component> {
	/*
	 * (non-Javadoc)
	 * 
	 * @see test.check.ConfigurationCommand#invoke(java.lang.Object)
	 */
	public void configure(Component comp) {
		comp.setEnabled(true);
	}
}