package test.check.command;

import java.awt.Color;
import java.awt.Component;


/**
 * A configure command that sets the specified foreground color on the
 * specified component.
 * 
 * @author Kirill Grouchnikov
 */
public class ForegroundColorCommand implements
		ConfigurationCommand<Component> {
	/**
	 * Color to set.
	 */
	private Color color;

	/**
	 * Creates a foreground color configuration command.
	 * 
	 * @param color
	 *            Foreground color to set.
	 */
	public ForegroundColorCommand(Color color) {
		this.color = color;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see test.check.ConfigurationCommand#invoke(java.lang.Object)
	 */
	public void configure(Component ab) {
		ab.setForeground(this.color);
	}
}