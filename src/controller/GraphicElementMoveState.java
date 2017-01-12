package controller;
import view.GraphicCanvasView;
import model.GraphicShape;
import java.awt.event.MouseEvent;
import model.Point;

public class GraphicElementMoveState extends GraphicElementState {
	private Point lastPoint = null;
	
	public GraphicElementMoveState(GraphicCanvasView view)
	{
		super(view);
	}
	
	@Override
	public void enter()
	{
	
	}
	
	@Override
	public void mouseMove(MouseEvent e)
	{
		view.highlight(view.shapeUnderCursor(e));
	}
	
	@Override
	public void mouseDrag(MouseEvent e)
	{
		for (GraphicShape s : view.getSelection()) {
			s.translate(e.getX() - lastPoint.getX(), e.getY() - lastPoint.getY());
		}
		
		view.repaint();
		
		lastPoint = new Point(e.getX(), e.getY());
	}
	
	@Override
	public void mouseDown(MouseEvent e)
	{
		GraphicShape s = view.shapeUnderCursor(e);
		lastPoint = new Point(e.getX(), e.getY());
		if (s != null) {
			if (!view.getSelection().contains(s)) {
				view.deselectAll();
				view.select(s);
			}
		}
	}
}
