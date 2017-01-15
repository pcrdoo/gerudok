package command.elements;

import model.elements.GraphicElement;
import model.elements.GraphicShape;

public class GraphicElementAddShapeCommand extends GraphicElementCommand {
	private GraphicShape shape;
	private GraphicElement element;
	
	public GraphicElementAddShapeCommand(GraphicElement element, GraphicShape shape)
	{
		super(element);
		this.element = element;
		this.shape = shape;
	}
	
	public GraphicShape getShape() {
		return shape;
	}
	
	@Override
	public void doCommand()
	{
		element.getShapes().add(shape);
		element.notifyShapesChanged();
	}
	
	@Override
	public void undoCommand()
	{
		element.getShapes().remove(findShape(shape));
		element.notifyShapesChanged();
	}
	
	@Override
	public String getCommandName()
	{
		return "add shape(s)";
	}
}
