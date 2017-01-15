package command;

public class Invoker {
	protected static Invoker instance = null;

	protected Invoker() {
	}

	public static Invoker getInstance() {
		if (instance == null) {
			instance = new Invoker();
			// init
		}
		return instance;
	}

	public void executeCommand(Command command) {
		command.doCommand();
	}
}
