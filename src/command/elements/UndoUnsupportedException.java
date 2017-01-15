package command.elements;

public class UndoUnsupportedException extends Exception {
	private String commandName;
	
	public UndoUnsupportedException(String commandName, String message) {
		super(message);
		this.commandName = commandName;
	}
	
	public String getCommandName()
	{
		return commandName;
	}
}
