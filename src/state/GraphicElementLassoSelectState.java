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

/**
 * Lasso selection state.
 * 
 * @author geomaster
 *
 */
public class GraphicElementLassoSelectState extends GraphicElementState {
	/**
	 * Rectangle describing the current lasso selection bounds.
	 */
	private Rectangle rectangle;

	/**
	 * Point where mouse dragging has started.
	 */
	private Point startPoint;

	/**
	 * Initial (before dragging) selected state of all shapes.
	 */
	private Map<GraphicShape, Boolean> initialState = new HashMap<>();

	/**
	 * Element to act upon.
	 */
	private GraphicElement element;

	/**
	 * Constructor.
	 * 
	 * @param element
	 *            Element to act upon
	 * @param view
	 *            Editor view
	 */
	public GraphicElementLassoSelectState(GraphicElement element, GraphicCanvasView view) {
		super(view);
		this.element = element;
	}

	/**
	 * Starts a new lasso selection and records the current selected state of
	 * all shapes.
	 * 
	 * @param e
	 *            Mouse event
	 */
	@Override
	public void mouseDown(MouseEvent e) {
		startPoint = new Point(e.getX(), e.getY());
		rectangle = new Rectangle(startPoint, 0, 0);
		view.setLassoRectangle(rectangle);

		for (GraphicShape s : element.getShapes()) {
			initialState.put(s, false);
		}

		for (GraphicShape s : view.getSelection()) {
			initialState.put(s, true);
		}
	}

	/**
	 * Updates the lasso selection bounds and selects/deselects elements based
	 * on if they are inside or not.
	 * 
	 * @param e
	 *            Mouse event
	 */
	@Override
	public void mouseDrag(MouseEvent e) {
		rectangle.setOrigin(startPoint.clone());
		rectangle.setWidth(e.getX() - startPoint.getX());
		rectangle.setHeight(e.getY() - startPoint.getY());
		rectangle.normalize();

		Set<GraphicShape> underLasso = view.shapesUnderLasso();
		for (Map.Entry<GraphicShape, Boolean> entry : initialState.entrySet()) {
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

	/**
	 * Removes the lasso rectangle from the editor view.
	 */
	@Override
	public void mouseUp(MouseEvent e) {
		view.setLassoRectangle(null);
	}
}
