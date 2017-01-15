package command.elements;

/**
 * Exception thrown when undoing a particular command is not implemented.
 * 
 * @author geomaster
 *
 */
public class UndoUnsupportedException extends Exception {
	/**
	 * Name of the command the exception relates to.
	 */
	private String commandName;

	/**
	 * Constructor.
	 * 
	 * @param commandName
	 *            Name of the command that caused the exception
	 * @param message
	 *            Exception message
	 */
	public UndoUnsupportedException(String commandName, String message) {
		super(message);
		this.commandName = commandName;
	}

	/**
	 * Gets the name of the offending command
	 * 
	 * @return Name of the command that caused the exception
	 */
	public String getCommandName() {
		return commandName;
	}
}
