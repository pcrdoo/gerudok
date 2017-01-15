package command.elements;

import model.Model;
import model.elements.GraphicElement;
import model.elements.GraphicShape;

import java.util.*;

import command.Command;

/**
 * Command to revert the state of a graphic element, happening when the user
 * cancels an edit operation.
 * 
 * @author geomaster
 *
 */
public class GraphicElementEditCancelCommand extends Command {
	/**
	 * Element to act upon.
	 */
	private GraphicElement element;

	/**
	 * List of shapes in the element before the edit operation.
	 */
	private List<GraphicShape> oldShapes;

	/**
	 * Constructor.
	 * 
	 * @param model
	 *            Model
	 * @param element
	 *            Element to act upon
	 * @param oldShapes
	 *            List of shapes in the element before the edit operation
	 */
	public GraphicElementEditCancelCommand(Model model, GraphicElement element, List<GraphicShape> oldShapes) {
		this.oldShapes = oldShapes;
		this.element = element;
		this.model = model;
	}

	/**
	 * Reverts the element to the previous state.
	 */
	@Override
	public void doCommand() {
		element.setShapes(oldShapes);
	}
}
