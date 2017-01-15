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

/**
 * View representing the canvas on which graphic shapes can be drawn, and also
 * overlays needed for editing grapphic elements.
 * 
 * @author geomaster
 *
 */
public class GraphicCanvasView extends JComponent implements GObserver {
	/**
	 * Associated element.
	 */
	private GraphicElement element;

	/**
	 * Current lasso rectangle to draw, or null if none.
	 */
	private Rectangle lassoRectangle = null;

	/**
	 * Temporary shapes to draw in addition to the element's ones. Used for
	 * displaying in-progress elements that are being added without actually
	 * adding them to the element until they are finalized.
	 */
	private List<GraphicShape> tempShapes = new ArrayList<>();

	/**
	 * Set of shapes which should be displayed translucently because they are a
	 * part of a cut-paste operation.
	 */
	private Set<GraphicShape> cutShapes = new HashSet<>();

	/**
	 * Set of selected shapes.
	 */
	private Set<GraphicShape> selection = new HashSet<>();

	/**
	 * Currently highlighted shape, or null if there's none.
	 */
	private GraphicShape highlightedShape = null;

	/**
	 * Constructor.
	 * 
	 * @param element
	 *            Element whose shapes to draw
	 */
	public GraphicCanvasView(GraphicElement element) {
		super();
		this.element = element;
		element.addObserver(this);
	}

	/**
	 * Handle notifications. Only handles the ELEMENT_EEDIT notification, by
	 * repainting itself.
	 * 
	 * @param notification
	 *            Notification type
	 * @param obj
	 *            Custom data
	 */
	@Override
	public void update(GNotification notification, Object obj) {
		if (notification == GNotification.ELEMENT_EDIT) {
			repaint();
			pruneRemovedSelectedElements();
		}
	}

	/**
	 * Removes shapes which are no longer part of the element (that were
	 * removed) from the selection.
	 */
	private void pruneRemovedSelectedElements() {
		List<GraphicShape> toRemove = new ArrayList<>();
		for (GraphicShape s : selection) {
			if (!element.getShapes().contains(s)) {
				toRemove.add(s);
			}
		}

		for (GraphicShape s : toRemove) {
			selection.remove(s);
		}
	}

	/**
	 * Paints the shapes and additional content.
	 * 
	 * @param g
	 *            Graphics to work with
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		for (GraphicShape s : element.getShapes()) {
			paintShape(s, g2d, cutShapes.contains(s));
		}

		for (GraphicShape s : tempShapes) {
			paintShape(s, g2d, false);
		}

		for (GraphicShape s : selection) {
			paintSelection(s.getBoundingRectangle(), g2d);
		}

		paintLassoRectangle(g2d);
	}

	/**
	 * Sets the current lasso rectangle preview.
	 * 
	 * @param lassoRectangle
	 *            New lasso rectangle preview to display, or null to hide
	 */
	public void setLassoRectangle(Rectangle lassoRectangle) {
		this.lassoRectangle = lassoRectangle;
		repaint();
	}

	/**
	 * Gets the current lasso rectangle preview.
	 * 
	 * @return Lasso rectangle
	 */
	public Rectangle getLassoRectangle() {
		return lassoRectangle;
	}

	/**
	 * Adds a shape to the set of selected shapes.
	 * 
	 * @param shape
	 *            Shape to select
	 */
	public void select(GraphicShape shape) {
		selection.add(shape);
		repaint();
	}

	/**
	 * Removes a shape from the set of selected shapes.
	 * 
	 * @param shape
	 *            Shape to deselect
	 */
	public void deselect(GraphicShape shape) {
		selection.remove(shape);
		repaint();
	}

	/**
	 * Deselects all shapes.
	 */
	public void deselectAll() {
		selection.clear();
		repaint();
	}

	/**
	 * Adds the current selection to the set of cut shapes, displaying them
	 * translucently until the cut-paste operation is finished or aborts.
	 */
	public void cutSelection() {
		for (GraphicShape s : selection) {
			cutShapes.add(s);
		}

		repaint();
	}

	/**
	 * Removes all shapes from the set of cut shapes, displaying all shapes
	 * normally again.
	 */
	public void clearCutShapes() {
		cutShapes.clear();
		repaint();
	}

	/**
	 * Toggles the selection status of a shape. If the shape was selected, it is
	 * deselected and vice-versa.
	 * 
	 * @param shape
	 *            Shape to toggle selection for
	 */
	public void toggleSelected(GraphicShape shape) {
		if (selection.contains(shape)) {
			deselect(shape);
		} else {
			select(shape);
		}
	}

	/**
	 * Highlights a shape.
	 * 
	 * @param shape
	 *            Shape to highlight, or null to highlight nothing
	 */
	public void highlight(GraphicShape shape) {
		if (highlightedShape != shape) {
			highlightedShape = shape;
			repaint();
		}
	}

	/**
	 * Returns the shape that is under the cursor, given a mouse event.
	 * 
	 * @param e
	 *            Mouse event whose position to check against
	 * @return Shape under the cursor, or null if none
	 */
	public GraphicShape shapeUnderCursor(MouseEvent e) {
		Point p = new Point(e.getX(), e.getY());
		for (GraphicShape s : element.getShapes()) {
			if (s.pointInside(p)) {
				return s;
			}
		}

		return null;
	}

	/**
	 * Returns the set of shapes that are contained in the current lasso
	 * rectangle.
	 * 
	 * @return Set of shapes contained in the lasso rectangle
	 */
	public Set<GraphicShape> shapesUnderLasso() {
		Set<GraphicShape> set = new HashSet<>();
		if (lassoRectangle != null) {
			for (GraphicShape s : element.getShapes()) {
				if (s.getBoundingRectangle().isInside(lassoRectangle)) {
					set.add(s);
				}
			}
		}

		return set;
	}

	/**
	 * Gets the set of selected shapes.
	 * 
	 * @return Selected shapes
	 */
	public Set<GraphicShape> getSelection() {
		return selection;
	}

	/**
	 * Sets the set of selected shapes.
	 * 
	 * @param selection
	 *            New set of selected shapes
	 */
	public void setSelection(Set<GraphicShape> selection) {
		this.selection = selection;
		repaint();
	}

	/**
	 * Paints a given shape.
	 * 
	 * @param shape
	 *            Shape to paint
	 * @param g
	 *            Graphics context to use
	 * @param isCut
	 *            true if the shape should be rendered translucently (as if
	 *            during a cut-paste operation) or not
	 */
	private void paintShape(GraphicShape shape, Graphics2D g, boolean isCut) {
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

	/**
	 * Paints a rectangle shape.
	 * 
	 * @param rect
	 *            Shape to paint
	 * @param g
	 *            Graphics context to use
	 */
	private void paintRectangle(GraphicRectangleShape rect, Graphics2D g) {
		Rectangle r = rect.getRectangle();
		g.drawRect(r.getOrigin().getX(), r.getOrigin().getY(), r.getWidth(), r.getHeight());
	}

	/**
	 * Paints an ellipse shape.
	 * 
	 * @param ellipse
	 *            Shape to paint
	 * @param g
	 *            Graphics context to use
	 */
	private void paintEllipse(GraphicEllipseShape ellipse, Graphics2D g) {
		Rectangle r = ellipse.getBounds();
		g.drawArc(r.getOrigin().getX(), r.getOrigin().getY(), r.getWidth(), r.getHeight(), 0, 360);
	}

	/**
	 * Paints a triangle shape.
	 * 
	 * @param tri
	 *            Shape to paint
	 * @param g
	 *            Graphics context to use
	 */
	private void paintTriangle(GraphicTriangleShape tri, Graphics2D g) {
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

	/**
	 * Paints a dashed selection rectangle.
	 * 
	 * @param boundingRect
	 *            Bounding box of the selected element
	 * @param g
	 *            Graphics context to use
	 */
	private void paintSelection(Rectangle boundingRect, Graphics2D g) {
		g.setColor(Color.red);
		final float[] dashing = { 5.0f };
		g.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 5.0f, dashing, 0.0f));
		g.drawRect(boundingRect.getOrigin().getX() - 4, boundingRect.getOrigin().getY() - 4,
				boundingRect.getWidth() + 8, boundingRect.getHeight() + 8);
	}

	/**
	 * Paints a light dotted lasso rectangle preview.
	 * 
	 * @param g
	 *            Graphics context to use.
	 */
	private void paintLassoRectangle(Graphics2D g) {
		if (lassoRectangle != null) {
			g.setColor(Color.darkGray);
			final float[] dashing = { 1.0f };
			g.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 5.0f, dashing, 0.0f));
			g.drawRect(lassoRectangle.getOrigin().getX(), lassoRectangle.getOrigin().getY(), lassoRectangle.getWidth(),
					lassoRectangle.getHeight());
		}
	}

	/**
	 * Sets the element whose shapes to paint.
	 * 
	 * @param element
	 *            New element
	 */
	public void setElement(GraphicElement element) {
		this.element = element;
		repaint();
	}

	/**
	 * Gets the element whose shapes are being painted.
	 * 
	 * @return Element
	 */
	public GraphicElement getElement() {
		return element;
	}

	/**
	 * Sets the list of temporary shapes (shapes which are temporarily drawn in
	 * addition to the element's shapes).
	 * 
	 * @param tempShapes
	 *            New list of temporary shapes
	 */
	public void setTempShapes(List<GraphicShape> tempShapes) {
		this.tempShapes = tempShapes;
		repaint();
	}

	/**
	 * Gets the list of temporary shapes (shapes which are temporarily drawn in
	 * addition to the element's shapes).
	 * 
	 * @return List of temporary shapes
	 */

	public List<GraphicShape> getTempShapes() {
		return tempShapes;
	}
}
