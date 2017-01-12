package controller;
import view.GraphicCanvasView;
import model.GraphicShape;
import java.awt.event.MouseEvent;
import model.Point;

public class GraphicElementRemoveState extends GraphicElementState {
	
	public GraphicElementRemoveState(GraphicCanvasView view)
	{
		super(view);
	}
	
	@Override
	public void enter()
	{
		for (GraphicShape s : view.getSelection()) {
			view.getShapes().remove(s);
		}
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
		view.getShapes().remove(s);
	}
}
