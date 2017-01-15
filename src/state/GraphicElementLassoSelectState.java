package state;
import model.elements.GraphicElement;
import model.elements.Point;
import model.elements.GraphicShape;
import model.elements.Rectangle;
import view.elements.GraphicCanvasView;

import java.awt.event.MouseEvent;

import java.util.Set;
import java.util.Map;
import java.util.HashMap;

public class GraphicElementLassoSelectState extends GraphicElementState {
	private Rectangle rectangle;
	private Point startPoint;
	private Map<GraphicShape, Boolean> initialState = new HashMap<>();
	private GraphicElement element;
	
	public GraphicElementLassoSelectState(GraphicElement element, GraphicCanvasView view)
	{
		super(view);
		this.element = element;
	}
	
	@Override
	public void mouseDown(MouseEvent e)
	{
		startPoint = new Point(e.getX(), e.getY());
		rectangle = new Rectangle(startPoint, 0, 0);
		view.setLassoRectangle(rectangle);
		
		for (GraphicShape s: element.getShapes()) {
			initialState.put(s, false);
		}
		
		for (GraphicShape s: view.getSelection()) {
			initialState.put(s, true);
		}
	}
	
	@Override
	public void mouseDrag(MouseEvent e)
	{
		rectangle.setOrigin(startPoint.clone());
		rectangle.setWidth(e.getX() - startPoint.getX());
		rectangle.setHeight(e.getY() - startPoint.getY());
		rectangle.normalize();
		
		Set<GraphicShape> underLasso = view.shapesUnderLasso();
		for (Map.Entry<GraphicShape, Boolean> entry: initialState.entrySet()) {
			boolean select = false;
			// Either was initially selected, or is under lasso
			if (underLasso.contains(entry.getKey()) || entry.getValue()) {
				select = true;
			}
			
			if (select) {
				view.select(entry.getKey());
			} else {
				view.deselect(entry.getKey());
			}
		}
		
		view.repaint();
	}
	
	@Override
	public void mouseUp(MouseEvent e)
	{
		view.setLassoRectangle(null);
	}
}
