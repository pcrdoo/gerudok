package command.elements;

import java.util.List;

import command.Command;

import model.elements.GraphicElement;
import model.elements.GraphicShape;

/**
 * A command specialized for graphic element related functionality, as that
 * subsystem supports undo and redo of commands.
 * 
 * @author geomaster
 *
 */
public abstract class GraphicElementCommand extends Command {
	/**
	 * Element the command acts upon
	 */
	protected GraphicElement element;

	/**
	 * Constructor.
	 * 
	 * @param element
	 *            Element that will be mutated by this command
	 */
	protected GraphicElementCommand(GraphicElement element) {
		this.element = element;
	}

	/**
	 * Gets the display name of the command.
	 * 
	 * @return User-friendly name for the command
	 */
	protected abstract String getCommandName();

	/**
	 * Reverts the action done by a command
	 * 
	 * @throws Exception
	 *             If undo isn't supported for this command
	 */
	public void undoCommand() throws Exception {
		// Default implementation is going to throw, as someone up the chain
		// from the Invoker should catch exceptions
		// raised from do/undoCommand
		throw new UndoUnsupportedException(getCommandName(),
				"The '" + getCommandName() + "' command hasn't implemented undo functionality.");
	}

	/**
	 * Returns whether or not the command can be undone.
	 * 
	 * @return true if the command is undoable and should count as part of the
	 *         undo history
	 */
	public boolean isUndoable() {
		// Returns true by default, to discourage bad practice of not
		// implementing undoCommand() ;)
		return true;
	}

	/**
	 * Returns whether or not the command can be redone.
	 * 
	 * @return true if the command is redoable and should count as part of the
	 *         redo stack
	 */
	public boolean isRedoable() {
		return isUndoable();
	}

	/**
	 * Helper. Searches for a shape in the graphic element. Needed because
	 * shapes can be cloned and manipulated, and we need to find the exact shape
	 * to apply any changes to.
	 * 
	 * @param shape
	 *            Shape to search for
	 * @return Shape which is part of the element and is equal by value to the
	 *         given shape, or null if there's none
	 */
	protected GraphicShape findShape(GraphicShape shape) {
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
