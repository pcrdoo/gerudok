package controller;
import view.GraphicCanvasView;
import model.GraphicEllipseShape;
import model.Point;
import model.Rectangle;

import java.awt.event.MouseEvent;

public class GraphicElementEllipseState extends GraphicElementState {
	private Point startPoint = new Point(0, 0);
	private GraphicEllipseShape shape = null;
	
	public GraphicElementEllipseState(GraphicCanvasView view)
	{
		super(view);
	}
	
	@Override
	public void enter()
	{
	}
	
	@Override
	public void mouseDrag(MouseEvent e)
	{
		Rectangle rect = shape.getBounds();
		Point origin = new Point(Math.min(startPoint.getX(), e.getX()), Math.min(startPoint.getY(), e.getY()));
		rect.setOrigin(origin);
		rect.setWidth(Math.abs(e.getX() - startPoint.getX()));
		rect.setHeight(Math.abs(e.getY() - startPoint.getY()));
		
		view.repaint();
	}
	
	@Override
	public void mouseDown(MouseEvent e)
	{
		startPoint = new Point(e.getX(), e.getY());
		shape = new GraphicEllipseShape(new Rectangle(startPoint, 0, 0));
		view.getShapes().add(shape);

		view.repaint();
	}
	
	@Override
	public void mouseUp(MouseEvent e)
	{
	}
}
