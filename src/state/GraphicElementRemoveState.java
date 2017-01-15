package state;
import java.awt.event.MouseEvent;

import model.elements.GraphicElement;
import model.elements.GraphicShape;
import view.elements.GraphicCanvasView;

import command.elements.GraphicElementInvoker;
import command.elements.GraphicElementRemoveShapeCommand;
import command.elements.GraphicElementCompositeCommand;
import java.util.ArrayList;

public class GraphicElementRemoveState extends GraphicElementState {
	private GraphicElement element;
	
	public GraphicElementRemoveState(GraphicElement element, GraphicCanvasView view)
	{
		super(view);
		this.element = element;
	}
	
	@Override
	public void enter()
	{
		ArrayList<GraphicElementRemoveShapeCommand> removeAll = new ArrayList<>();
		for (GraphicShape s: view.getSelection()) {
			removeAll.add(new GraphicElementRemoveShapeCommand(element, s));
		}
		GraphicElementInvoker.getInstance().executeCommand(new GraphicElementCompositeCommand(element, removeAll));
		view.deselectAll();
	}
	
	@Override
	public void mouseMove(MouseEvent e)
	{
		view.highlight(view.shapeUnderCursor(e));
	}
	
	@Override
	public void mouseClick(MouseEvent e)
	{
		GraphicShape s = view.shapeUnderCursor(e);
		if (s != null) {
			GraphicElementInvoker.getInstance().executeCommand(new GraphicElementRemoveShapeCommand(element, s));
		}
	}
}
