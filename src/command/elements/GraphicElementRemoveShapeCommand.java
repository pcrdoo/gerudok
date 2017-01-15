package command.elements;

import model.elements.GraphicElement;
import model.elements.GraphicShape;

import java.util.List;

public class GraphicElementRemoveShapeCommand extends GraphicElementCommand {
	private GraphicShape shape;
	private GraphicElement element;
	
	public GraphicElementRemoveShapeCommand(GraphicElement element, GraphicShape shape)
	{
		super(element);
		this.element = element;
		this.shape = shape;
	}
	
	@Override
	public void doCommand()
	{
		element.getShapes().remove(findShape(shape));
		element.notifyShapesChanged();
	}
	
	@Override
	public void undoCommand()
	{
		element.getShapes().add(shape);
		element.notifyShapesChanged();
	}
	
	@Override
	public String getCommandName()
	{
		return "remove shape(s)";
	}
}
