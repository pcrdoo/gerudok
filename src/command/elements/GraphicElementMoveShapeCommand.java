package command.elements;

import model.elements.GraphicElement;
import model.elements.GraphicShape;

import model.elements.Point;

/**
 * Command which moves a given shape to a new position.
 * 
 * @author geomaster
 *
 */
public class GraphicElementMoveShapeCommand extends GraphicElementCommand {
	/**
	 * Shape to move.
	 */
	private GraphicShape shape;

	/**
	 * New position for the shape.
	 */
	private Point newPosition;

	/**
	 * Old position of the shape (position to revert to on undo).
	 */
	private Point oldPosition;

	/**
	 * Graphic element the shape belongs to.
	 */
	private GraphicElement element;

	/**
	 * Constructor.
	 * 
	 * @param element
	 *            Graphic element to which the shape belongs to
	 * @param shape
	 *            Shape to move
	 * @param newPosition
	 *            New position of the shape
	 */
	public GraphicElementMoveShapeCommand(GraphicElement element, GraphicShape shape, Point newPosition) {
		super(element);
		this.element = element;
		this.newPosition = newPosition;
		this.shape = shape;
		captureCurrentState();
	}

	/**
	 * Records the current position of the shape as the 'previous state' i.e.
	 * state to revert to when undoing.
	 */
	public void captureCurrentState() {
		oldPosition = shape.getPosition().clone();
	}

	/**
	 * Set the new position for the shape.
	 * 
	 * @param newPosition
	 *            New position
	 */
	public void setNewPosition(Point newPosition) {
		this.newPosition = newPosition;
	}

	/**
	 * Gets the new position of the shape
	 * 
	 * @return Position to which the shape will be moved when executing this
	 *         command
	 */
	public Point getNewPosition() {
		return newPosition;
	}

	/**
	 * Gets the shape.
	 * 
	 * @return Shape that will be moved
	 */
	public GraphicShape getShape() {
		return shape;
	}

	/**
	 * Moves the shape to the new position.
	 */
	@Override
	public void doCommand() {
		shape.setPosition(newPosition);
		element.notifyShapesChanged();
	}

	/**
	 * Moves the shape back to the old position, as captured by
	 * captureCurrentState().
	 * 
	 * @see command.elements.GraphicElementMoveShapeCommand#captureCurrentState
	 */
	@Override
	public void undoCommand() {
		findShape(shape).setPosition(oldPosition);
		element.notifyShapesChanged();
	}

	/**
	 * Gets the command name.
	 * 
	 * @return Command name
	 */
	@Override
	public String getCommandName() {
		return "move shape(s)";
	}
}
