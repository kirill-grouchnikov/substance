package test.check.command;

/**
 * Chain command - applies a chain of configure commands on a component.
 * 
 * @author Kirill Grouchnikov
 * @param <T>
 *            Component class.
 */
public class ChainCommand<T> implements ConfigurationCommand<T> {
	/**
	 * Command chain.
	 */
	private ConfigurationCommand<T>[] commands;

	/**
	 * Creates the chain command.
	 * 
	 * @param commands
	 *            Command chain.
	 */
	public ChainCommand(ConfigurationCommand<T>... commands) {
		super();
		this.commands = commands;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see test.check.ConfigurationCommand#invoke(java.lang.Object)
	 */
	public void configure(T component) {
		for (ConfigurationCommand<T> cmd : this.commands)
			cmd.configure(component);
	}
}
