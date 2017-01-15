package command.elements;

import model.Model;

import model.elements.GraphicElement;

import command.Command;

/**
 * Command to commit the new state of a graphic element, happening when the user
 * finishes an edit operation. It is actually not more than a no-op, as the live
 * preview feature of the graphic element ensures that the actual graphic
 * element always contains the freshest state. Cancelling is then implemented by
 * taking a snapshot before the edit operation and applying it if the user
 * cancels.
 * 
 * @author geomaster
 *
 */
public class GraphicElementEditCommitCommand extends Command {
	/**
	 * Element to act upon.
	 */
	private GraphicElement element;

	/**
	 * Constructor.
	 * 
	 * @param model
	 *            Model
	 * @param element
	 *            Element to act upon
	 */
	public GraphicElementEditCommitCommand(Model model, GraphicElement element) {
		this.model = model;
		this.element = element;
	}

	/**
	 * Notifies the element that an edit operation has happened and to update
	 * associated views etc.
	 */
	@Override
	public void doCommand() {
		element.notifyShapesChanged();
	}
}
