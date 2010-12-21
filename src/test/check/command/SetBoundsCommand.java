package test.check.command;

import java.awt.Component;
import java.awt.Rectangle;

/**
 * A configure command that sets bounds for the specified component.
 * 
 * @author Kirill Grouchnikov
 */
public class SetBoundsCommand implements ConfigurationCommand<Component> {
	/**
	 * Component bounds to set.
	 */
	private Rectangle bounds;

	/**
	 * Creates a new configuration command.
	 * 
	 * @param bounds
	 *            Component bounds to set.
	 */
	public SetBoundsCommand(Rectangle bounds) {
		this.bounds = bounds;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see test.check.ConfigurationCommand#invoke(java.lang.Object)
	 */
	public void configure(Component comp) {
		comp.setBounds(this.bounds);
	}
}