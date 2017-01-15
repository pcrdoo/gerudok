package state;

import model.elements.GraphicEllipseShape;
import model.elements.Point;
import model.elements.Rectangle;
import view.elements.GraphicCanvasView;

import java.awt.event.MouseEvent;

import command.elements.GraphicElementInvoker;
import command.elements.GraphicElementAddShapeCommand;
import model.elements.GraphicElement;

/**
 * Ellipse tool state.
 * 
 * @author geomaster
 *
 */
public class GraphicElementEllipseState extends GraphicElementState {
	/**
	 * Point where dragging was started.
	 */
	private Point startPoint = new Point(0, 0);

	/**
	 * Element to act on.
	 */
	private GraphicElement element;

	/**
	 * Ellipse shape that is being drawn.
	 */
	private GraphicEllipseShape shape = null;

	/**
	 * Constructor.
	 * 
	 * @param element
	 *            Element to act on
	 * @param view
	 *            Editor view
	 */
	public GraphicElementEllipseState(GraphicElement element, GraphicCanvasView view) {
		super(view);
		this.element = element;
	}

	/**
	 * Updates the editor view with the new dimensions of the ellipse.
	 * 
	 * @param e
	 *            Mouse event
	 */
	@Override
	public void mouseDrag(MouseEvent e) {
		Rectangle rect = shape.getBounds();
		Point origin = new Point(Math.min(startPoint.getX(), e.getX()), Math.min(startPoint.getY(), e.getY()));
		rect.setOrigin(origin);
		rect.setWidth(Math.abs(e.getX() - startPoint.getX()));
		rect.setHeight(Math.abs(e.getY() - startPoint.getY()));

		view.repaint();
	}

	/**
	 * Starts a new ellipse and adds it to the editor view.
	 * 
	 * @param e
	 *            Mouse event
	 */
	@Override
	public void mouseDown(MouseEvent e) {
		startPoint = new Point(e.getX(), e.getY());
		shape = new GraphicEllipseShape(new Rectangle(startPoint, 0, 0));
		view.getTempShapes().add(shape);

		view.repaint();
	}

	/**
	 * Invokes a command to add the drawn ellipse to the element's shapes.
	 * 
	 * @param e
	 *            Mouse event
	 */
	@Override
	public void mouseUp(MouseEvent e) {
		view.getTempShapes().clear();
		GraphicElementInvoker.getInstance().executeCommand(new GraphicElementAddShapeCommand(element, shape));
	}
}
