package command.elements;

import model.elements.GraphicElement;
import java.util.List;
import java.util.ListIterator;
import java.util.HashSet;

public class GraphicElementCompositeCommand extends GraphicElementCommand {
	private List<? extends GraphicElementCommand> commands;
	
	public GraphicElementCompositeCommand(GraphicElement element, List<? extends GraphicElementCommand> commands)
	{
		super(element);
		this.commands = commands;
	}
	
	@Override
	public void doCommand()
	{
		ListIterator<? extends GraphicElementCommand> it = commands.listIterator();
		while (it.hasNext()) {
			it.next().doCommand();
		}
	}
	
	@Override
	public void undoCommand() throws Exception
	{
		ListIterator<? extends GraphicElementCommand> it = commands.listIterator(commands.size());
		while (it.hasPrevious()) {
			GraphicElementCommand c = it.previous();
			if (c.isUndoable()) {
				c.undoCommand();
			}
		}
	}
	
	@Override
	public String getCommandName()
	{
		// Get only the unique names of commands
		HashSet<String> commandNames = new HashSet<>();
		for (GraphicElementCommand c: commands) {
			commandNames.add(c.getCommandName());
		}
		
		// Like "move shape(s); add shape(s); remove shape(s)"
		return String.join("; ", commandNames);
	}
}
