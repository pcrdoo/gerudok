package view;
import javax.swing.JComponent;
import java.util.*;
import model.GraphicShapeType;
import model.GraphicShape;
import model.GraphicRectangleShape;
import model.GraphicEllipseShape;
import model.GraphicTriangleShape;
import model.Point;
import model.Rectangle;

import java.awt.event.MouseEvent;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.BasicStroke;

public class GraphicCanvasView extends JComponent {
	private List<GraphicShape> shapes = new ArrayList<>();
	private Set<GraphicShape> selectedShapes = new HashSet<>();
	private GraphicShape highlightedShape = null;
	
	public GraphicCanvasView()
	{
		super();
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		for (GraphicShape s : shapes) {
			paintShape(s, g2d);
		}
		
		for (GraphicShape s : selectedShapes) {
			paintSelection(s.getBoundingRectangle(), g2d);
		}
	}
	
	public void select(GraphicShape shape)
	{
		selectedShapes.add(shape);
		repaint();
	}
	
	public void deselect(GraphicShape shape)
	{
		selectedShapes.remove(shape);
		repaint();
	}
	
	public void deselectAll()
	{
		selectedShapes.clear();
		repaint();
	}
	
	public void toggleSelected(GraphicShape shape)
	{
		if (selectedShapes.contains(shape)) {
			deselect(shape);
		} else {
			select(shape);
		}
	}
	
	public void highlight(GraphicShape shape)
	{
		if (highlightedShape != shape) {
			highlightedShape = shape;
			repaint();
		}
	}
	
	public GraphicShape shapeUnderCursor(MouseEvent e)
	{
		Point p = new Point(e.getX(), e.getY());
		for (GraphicShape s : shapes) {
			if (s.pointInside(p)) {
				return s;
			}
		}
		
		return null;
	}
	
	public Set<GraphicShape> getSelection()
	{
		return selectedShapes;
	}
	
	private void paintShape(GraphicShape shape, Graphics2D g)
	{
		if (shape == highlightedShape) {
			g.setColor(Color.red);
			g.setStroke(new BasicStroke(2));
		} else {
			g.setColor(Color.gray);
			g.setStroke(new BasicStroke(2));
		}
		
		switch (shape.getType()) {
		case ELLIPSE:
			paintEllipse((GraphicEllipseShape) shape, g);
			break;
			
		case TRIANGLE:
			paintTriangle((GraphicTriangleShape) shape, g);
			break;
				
		case RECTANGLE:
			paintRectangle((GraphicRectangleShape) shape, g);
			break;
		}
	}
	
	private void paintRectangle(GraphicRectangleShape rect, Graphics2D g)
	{
		Rectangle r = rect.getRectangle();
		g.drawRect(r.getOrigin().getX(), r.getOrigin().getY(), r.getWidth(), r.getHeight());
	}
	
	private void paintEllipse(GraphicEllipseShape ellipse, Graphics2D g)
	{
		Rectangle r = ellipse.getBounds();
		g.drawArc(r.getOrigin().getX(), r.getOrigin().getY(), r.getWidth(), r.getHeight(), 0, 360);
	}
	
	private void paintTriangle(GraphicTriangleShape tri, Graphics2D g)
	{
		Rectangle r = tri.getBounds();
		Point o = r.getOrigin();
		int w = r.getWidth();
		int h = r.getHeight();
		int x = o.getX();
		int y = o.getY();
		
		g.drawLine(x, y + h, x + w, y + h);
		g.drawLine(x + w, y + h, x + w / 2, y);
		g.drawLine(x + w / 2, y, x, y + h);
	}
	
	private void paintSelection(Rectangle boundingRect, Graphics2D g)
	{
		g.setColor(Color.red);
		final float[] dashing = { 5.0f };
		g.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 5.0f, dashing, 0.0f));
		g.drawRect(boundingRect.getOrigin().getX() - 4, boundingRect.getOrigin().getY() - 4, boundingRect.getWidth() + 8, boundingRect.getHeight() + 8);
	}
	
	public void setShapes(List<GraphicShape> shapes)
	{
		this.shapes = shapes;
		repaint();
	}
	
	public List<GraphicShape> getShapes()
	{
		return shapes;
	}
}
