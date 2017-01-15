package state;

import model.elements.GraphicRectangleShape;
import model.elements.Point;
import model.elements.Rectangle;
import view.elements.GraphicCanvasView;

import java.awt.event.MouseEvent;

import command.elements.GraphicElementAddShapeCommand;
import command.elements.GraphicElementInvoker;
import model.elements.GraphicElement;

/**
 * Rectangle tool state.
 * 
 * @author geomaster
 *
 */
public class GraphicElementRectangleState extends GraphicElementState {
	/**
	 * Point where the mouse was held down.
	 */
	private Point startPoint = new Point(0, 0);

	/**
	 * Element to act upon.
	 */
	private GraphicElement element;

	/**
	 * Rectangle shape to be added.
	 */
	private GraphicRectangleShape shape = null;

	/**
	 * Constructor
	 * 
	 * @param element
	 *            Element to act upon
	 * @param view
	 *            Editor view
	 */
	public GraphicElementRectangleState(GraphicElement element, GraphicCanvasView view) {
		super(view);
		this.element = element;
	}

	/**
	 * Updates the rectangle's parameters and repaints the view.
	 * 
	 * @param e
	 *            Mouse event
	 */
	@Override
	public void mouseDrag(MouseEvent e) {
		Rectangle rect = shape.getRectangle();
		Point origin = new Point(Math.min(startPoint.getX(), e.getX()), Math.min(startPoint.getY(), e.getY()));
		rect.setOrigin(origin);
		rect.setWidth(Math.abs(e.getX() - startPoint.getX()));
		rect.setHeight(Math.abs(e.getY() - startPoint.getY()));

		view.repaint();
	}

	/**
	 * Creates a new rectangle and adds it to the folder's temporary shapes.
	 * 
	 * @param e
	 *            Mouse event
	 */
	@Override
	public void mouseDown(MouseEvent e) {
		startPoint = new Point(e.getX(), e.getY());
		shape = new GraphicRectangleShape(new Rectangle(startPoint, 0, 0));
		view.getTempShapes().add(shape);

		view.repaint();
	}

	/**
	 * Invokes the command to add the rectangle to the element.
	 */
	@Override
	public void mouseUp(MouseEvent e) {
		view.getTempShapes().clear();
		GraphicElementInvoker.getInstance().executeCommand(new GraphicElementAddShapeCommand(element, shape));
	}
}
