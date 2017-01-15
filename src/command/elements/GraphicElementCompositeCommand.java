package command.elements;

import model.elements.GraphicElement;
import java.util.List;
import java.util.ListIterator;
import java.util.HashSet;

/**
 * GraphicElementCommand representing the composition of zero or more
 * GraphicElementCommands, which are undone and redone as a single unit.
 * 
 * @author geomaster
 *
 */
public class GraphicElementCompositeCommand extends GraphicElementCommand {
	/**
	 * List of containing commands.
	 */
	private List<? extends GraphicElementCommand> commands;

	/**
	 * Constructor.
	 * 
	 * @param element
	 *            Element which is being acted upon
	 * @param commands
	 *            List of commands to compose
	 */
	public GraphicElementCompositeCommand(GraphicElement element, List<? extends GraphicElementCommand> commands) {
		super(element);
		this.commands = commands;
	}

	/**
	 * Runs all commands, in increasing order.
	 */
	@Override
	public void doCommand() {
		ListIterator<? extends GraphicElementCommand> it = commands.listIterator();
		while (it.hasNext()) {
			it.next().doCommand();
		}
	}

	/**
	 * Reverts all commands, in reverse order.
	 */
	@Override
	public void undoCommand() throws Exception {
		ListIterator<? extends GraphicElementCommand> it = commands.listIterator(commands.size());
		while (it.hasPrevious()) {
			GraphicElementCommand c = it.previous();
			if (c.isUndoable()) {
				c.undoCommand();
			}
		}
	}

	/**
	 * Gets the command name. The command name is a composition of the
	 * containing command names, with duplicates removed.
	 * 
	 * @return Command name
	 */
	@Override
	public String getCommandName() {
		// Get only the unique names of commands
		HashSet<String> commandNames = new HashSet<>();
		for (GraphicElementCommand c : commands) {
			commandNames.add(c.getCommandName());
		}

		// Like "move shape(s); add shape(s); remove shape(s)"
		return String.join("; ", commandNames);
	}
}
