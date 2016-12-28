package command;

public class Invoker {
	public static Invoker instance = null;

	private Invoker() {
	}

	public static Invoker getInstance() {
		if (instance == null) {
			instance = new Invoker();
			// init
		}
		return instance;
	}

	public void executeCommand(Command command) {
		command.execute();
	}
}
