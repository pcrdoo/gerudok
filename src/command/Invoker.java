package command;

/**
 * Creates and invokes all commands as per command pattern.
 * 
 * @author Random
 *
 */
public class Invoker {
	/**
	 * Singleton instance.
	 */
	protected static Invoker instance = null;

	/**
	 * Inaccessible constructor that prevents instantiation.
	 */
	protected Invoker() {
	}

	/**
	 * @return returns invoker instance
	 */
	public static Invoker getInstance() {
		if (instance == null) {
			instance = new Invoker();
			// init
		}
		return instance;
	}

	/**
	 * @param command
	 *            the command to be executed
	 */
	public void executeCommand(Command command) {
		command.doCommand();
	}
}
