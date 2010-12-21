package test.check.command;

import java.awt.Component;

import javax.swing.JScrollPane;

/**
 * A configure command that disables the viewport component specified component.
 * 
 * @author Kirill Grouchnikov
 */
public class DisableViewportCommand implements ConfigurationCommand<Component> {
	/*
	 * (non-Javadoc)
	 * 
	 * @see test.check.ConfigurationCommand#invoke(java.lang.Object)
	 */
	public void configure(Component ab) {
		if (ab instanceof JScrollPane) {
			JScrollPane jsp = (JScrollPane) ab;
			jsp.getViewport().getView().setEnabled(false);
		}
	}
}