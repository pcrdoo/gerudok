package command.elements;
import java.util.List;

import command.Command;

import model.elements.GraphicElement;
import model.elements.GraphicShape;

public abstract class GraphicElementCommand extends Command {
	protected GraphicElement element;
	
	protected GraphicElementCommand(GraphicElement element)
	{
		this.element = element;
	}
	
	protected String getCommandName()
	{
		return "unknown";
	}
	
	public void undoCommand() throws Exception
	{
		// Default implementation is going to throw, as someone up the chain from the Invoker should catch exceptions
		// raised from do/undoCommand
		throw new UndoUnsupportedException(getCommandName(), "The '" + getCommandName() + "' command hasn't implemented undo functionality.");
	}
	
	public boolean isUndoable()
	{
		// Returns true by default, to discourage bad practice of not implementing undoCommand() ;)
		return true;
	}
	
	protected GraphicShape findShape(GraphicShape shape)
	{
		// Object.equals() is not overriden in GraphicShape as that would entail
		// an override of hashCode(), which is unnecessary
		List<GraphicShape> shapes = element.getShapes();
		for (int i = 0; i < shapes.size(); i++) {
			if (shape.equalShape(shapes.get(i))) {
				return shapes.get(i);
			}
		}
		
		return null;
	}
}
