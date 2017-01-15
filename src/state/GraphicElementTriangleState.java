package state;

import model.elements.GraphicElement;
import model.elements.GraphicTriangleShape;
import model.elements.Point;
import model.elements.Rectangle;
import view.elements.GraphicCanvasView;

import java.awt.event.MouseEvent;

import command.elements.GraphicElementAddShapeCommand;
import command.elements.GraphicElementInvoker;

/**
 * Triangle tool state.
 * 
 * @author geomaster
 *
 */
public class GraphicElementTriangleState extends GraphicElementState {
	/**
	 * Point where dragging was started.
	 */
	private Point startPoint = new Point(0, 0);

	/**
	 * Element to act on.
	 */
	private GraphicElement element;

	/**
	 * Triangle shape that is being drawn.
	 */
	private GraphicTriangleShape shape = null;

	/**
	 * Constructor.
	 * 
	 * @param element
	 *            Element to act on
	 * @param view
	 *            Editor view
	 */
	public GraphicElementTriangleState(GraphicElement element, GraphicCanvasView view) {
		super(view);
		this.element = element;
	}

	/**
	 * Updates the editor view with the new dimensions of the triangle.
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
	 * Starts a new triangle and adds it to the editor view.
	 * 
	 * @param e
	 *            Mouse event
	 */
	@Override
	public void mouseDown(MouseEvent e) {
		startPoint = new Point(e.getX(), e.getY());
		shape = new GraphicTriangleShape(new Rectangle(startPoint, 0, 0));
		view.getTempShapes().add(shape);

		view.repaint();
	}

	/**
	 * Invokes a command to add the drawn triangle to the element's shapes.
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
