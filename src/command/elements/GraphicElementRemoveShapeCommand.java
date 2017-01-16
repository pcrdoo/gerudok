package command.elements;

import model.elements.GraphicElement;
import model.elements.GraphicShape;

/**
 * Command which removes a shape from a graphic element.
 * 
 * @author geomaster
 *
 */
public class GraphicElementRemoveShapeCommand extends GraphicElementCommand {
	/**
	 * Shape to remove.
	 */
	private GraphicShape shape;

	/**
	 * Element to remove the shape from.
	 */
	private GraphicElement element;

	/**
	 * Constructor.
	 * 
	 * @param element
	 *            Element to remove the shape from
	 * @param shape
	 *            Shape to remove
	 */
	public GraphicElementRemoveShapeCommand(GraphicElement element, GraphicShape shape) {
		super(element);
		this.element = element;
		this.shape = shape;
	}

	/**
	 * Removes the shape from the element.
	 */
	@Override
	public void doCommand() {
		element.getShapes().remove(findShape(shape));
		element.notifyShapesChanged();
	}

	/**
	 * Adds the shape back into the element.
	 */
	@Override
	public void undoCommand() {
		element.getShapes().add(shape);
		element.notifyShapesChanged();
	}

	/**
	 * Gets the command name.
	 * 
	 * @return Command name
	 */
	@Override
	public String getCommandName() {
		return "remove shape(s)";
	}
}
