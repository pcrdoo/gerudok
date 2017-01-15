package command.elements;

import model.elements.GraphicElement;
import model.elements.GraphicShape;

import java.util.List;
import model.elements.Point;

public class GraphicElementMoveShapeCommand extends GraphicElementCommand {
	private GraphicShape shape;
	private Point newPosition;
	private Point oldPosition;
	private GraphicElement element;
	
	public GraphicElementMoveShapeCommand(GraphicElement element, GraphicShape shape, Point newPosition)
	{
		super(element);
		this.element = element;
		this.newPosition = newPosition;
		this.oldPosition = null;
		this.shape = shape;
	}
	
	public void captureCurrentState()
	{
		oldPosition = shape.getPosition().clone();
	}
	
	public void setNewPosition(Point newPosition) {
		this.newPosition = newPosition;
	}
	
	public Point getNewPosition() {
		return newPosition;
	}
	
	public GraphicShape getShape() {
		return shape;
	}
	
	@Override
	public void doCommand()
	{
		shape.setPosition(newPosition);
		element.notifyShapesChanged();
	}
	
	@Override
	public void undoCommand()
	{
		findShape(shape).setPosition(oldPosition);
		element.notifyShapesChanged();
	}
	
	@Override
	public String getCommandName()
	{
		return "move shape(s)";
	}
}
