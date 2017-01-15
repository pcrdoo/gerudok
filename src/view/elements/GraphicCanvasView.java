package view.elements;
import javax.swing.JComponent;
import java.util.*;

import model.elements.GraphicElement;
import model.elements.GraphicEllipseShape;
import model.elements.GraphicRectangleShape;
import model.elements.GraphicShape;
import model.elements.GraphicShapeType;
import model.elements.GraphicTriangleShape;
import model.elements.Point;
import model.elements.Rectangle;
import gerudok_observer.GObserver;
import gerudok_observer.GNotification;

import java.awt.event.MouseEvent;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.BasicStroke;

public class GraphicCanvasView extends JComponent implements GObserver {
	private GraphicElement element;
	private Rectangle lassoRectangle = null;
	private List<GraphicShape> tempShapes = new ArrayList<>();
	private List<GraphicShape> cutShapes = new ArrayList<>();
	private Set<GraphicShape> selection = new HashSet<>();

	private GraphicShape highlightedShape = null;
	
	public GraphicCanvasView(GraphicElement element)
	{
		super();
		this.element = element;
		element.addObserver(this);
	}
	
	@Override
	public void update(GNotification notification, Object obj) {
		if (notification == GNotification.ELEMENT_EDIT) {
			repaint();
			pruneRemovedSelectedElements();
		}
	}
	
	private void pruneRemovedSelectedElements()
	{
		List<GraphicShape> toRemove = new ArrayList<>();
		for (GraphicShape s: selection) {
			if (!element.getShapes().contains(s)) {
				toRemove.add(s);
			}
		}
		
		for (GraphicShape s: toRemove) {
			selection.remove(s);
		}
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		for (GraphicShape s: element.getShapes()) {
			paintShape(s, g2d, cutShapes.contains(s));
		}
		
		for (GraphicShape s: tempShapes) {
			paintShape(s, g2d, false);
		}
		
		for (GraphicShape s: selection) {
			paintSelection(s.getBoundingRectangle(), g2d);
		}
		
		paintLassoRectangle(g2d);
	}
	
	public void setLassoRectangle(Rectangle lassoRectangle)
	{
		this.lassoRectangle = lassoRectangle;
		repaint();
	}
	
	public Rectangle getLassoRectangle()
	{
		return lassoRectangle;
	}
	
	public void select(GraphicShape shape)
	{
		selection.add(shape);
		repaint();
	}
	
	public void deselect(GraphicShape shape)
	{
		selection.remove(shape);
		repaint();
	}
	
	public void deselectAll()
	{
		selection.clear();
		repaint();
	}
	
	public void cutSelection()
	{
		for (GraphicShape s: selection) {
			cutShapes.add(s);
		}
		
		repaint();
	}
	
	public void clearCutShapes()
	{
		cutShapes.clear();
		repaint();
	}
	
	public void toggleSelected(GraphicShape shape)
	{
		if (selection.contains(shape)) {
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
		for (GraphicShape s : element.getShapes()) {
			if (s.pointInside(p)) {
				return s;
			}
		}
		
		return null;
	}
	
	public Set<GraphicShape> shapesUnderLasso()
	{
		Set<GraphicShape> set = new HashSet<>();
		for (GraphicShape s: element.getShapes()) {
			if (s.getBoundingRectangle().isInside(lassoRectangle)) {
				set.add(s);
			}
		}
		
		return set;
	}
	
	public Set<GraphicShape> getSelection()
	{
		return selection;
	}
	
	public void setSelection(Set<GraphicShape> selection)
	{
		this.selection = selection;
		repaint();
	}
	
	private void paintShape(GraphicShape shape, Graphics2D g, boolean isCut)
	{
		if (!isCut) {
			if (shape == highlightedShape) {
				g.setColor(Color.red);
			} else {
				g.setColor(Color.gray);
			}
		} else {
			g.setColor(Color.lightGray);
		}

		g.setStroke(new BasicStroke(2));
		
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

	private void paintLassoRectangle(Graphics2D g)
	{
		if (lassoRectangle != null) {
			g.setColor(Color.darkGray);
			final float[] dashing = { 1.0f };
			g.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 5.0f, dashing, 0.0f));
			g.drawRect(lassoRectangle.getOrigin().getX(), lassoRectangle.getOrigin().getY(), lassoRectangle.getWidth(), lassoRectangle.getHeight());			
		}
	}
	public void setElement(GraphicElement element)
	{
		this.element = element;
		repaint();
	}
	
	public GraphicElement getElement()
	{
		return element;
	}
	
	public void setTempShapes(List<GraphicShape> tempShapes)
	{
		this.tempShapes = tempShapes;
		repaint();
	}
	
	public List<GraphicShape> getTempShapes()
	{
		return tempShapes;
	}
}
