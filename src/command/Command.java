package command;

import model.Model;

/**
 * Abstract command, superclass for other commands.
 * 
 * @author Nikola Jovanovic
 *
 */
public abstract class Command {
	/**
	 * The main model.
	 */
	protected Model model;

	/**
	 * Defines the behavior of the command.
	 */
	public abstract void doCommand();
}
