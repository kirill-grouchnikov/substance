package test.check.command;

import java.awt.Color;
import java.awt.Component;


/**
 * A configure command that sets the specified background color on the
 * specified component.
 * 
 * @author Kirill Grouchnikov
 */
public class BackgroundColorCommand implements
		ConfigurationCommand<Component> {
	/**
	 * Color to set.
	 */
	private Color color;

	/**
	 * Creates a background color configuration command.
	 * 
	 * @param color
	 *            Background color to set.
	 */
	public BackgroundColorCommand(Color color) {
		this.color = color;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see test.check.ConfigurationCommand#invoke(java.lang.Object)
	 */
	public void configure(Component ab) {
		ab.setBackground(this.color);
	}
}