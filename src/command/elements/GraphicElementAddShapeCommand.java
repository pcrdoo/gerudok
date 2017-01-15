package command.elements;

import model.elements.GraphicElement;
import model.elements.GraphicShape;

/**
 * Command which adds a given shape to a graphic element.
 * 
 * @author geomaster
 *
 */
public class GraphicElementAddShapeCommand extends GraphicElementCommand {
	/**
	 * Shape to add.
	 */
	private GraphicShape shape;

	/**
	 * Constructor.
	 * 
	 * @param element
	 *            Element to add the shape to
	 * @param shape
	 *            Shape to add
	 */
	public GraphicElementAddShapeCommand(GraphicElement element, GraphicShape shape) {
		super(element);
		this.element = element;
		this.shape = shape;
	}

	/**
	 * Gets the shape that is to be added.
	 * 
	 * @return Shape to be added to the element
	 */
	public GraphicShape getShape() {
		return shape;
	}

	/**
	 * Adds the shape to the element.
	 */
	@Override
	public void doCommand() {
		element.getShapes().add(shape);
		element.notifyShapesChanged();
	}

	/**
	 * Removes the added shape from the element.
	 */
	@Override
	public void undoCommand() {
		element.getShapes().remove(findShape(shape));
		element.notifyShapesChanged();
	}

	/**
	 * Gets the command name.
	 * 
	 * @return Command name
	 */
	@Override
	public String getCommandName() {
		return "add shape(s)";
	}
}
