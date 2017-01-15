package state;

import java.awt.event.MouseEvent;

import model.elements.GraphicShape;
import model.elements.Point;
import view.elements.GraphicCanvasView;
import model.elements.GraphicElement;
import command.elements.GraphicElementInvoker;
import command.elements.GraphicElementMoveShapeCommand;
import command.elements.GraphicElementCompositeCommand;

import java.util.List;
import java.util.ArrayList;

/**
 * Move tool state.
 * 
 * @author geomaster
 *
 */
public class GraphicElementMoveState extends GraphicElementState {
	/**
	 * Last point where the mouse was dragged.
	 */
	private Point lastPoint = null;

	/**
	 * Element to act upon.
	 */
	private GraphicElement element;

	/**
	 * List of commands that move all shapes in the selection.
	 */
	private List<GraphicElementMoveShapeCommand> commands = new ArrayList<>();

	/**
	 * Composite command that moves all shapes in the selection.
	 */
	private GraphicElementCompositeCommand compositeCommand = null;

	/**
	 * Constructor.
	 * 
	 * @param element
	 *            Element to act upon
	 * @param view
	 *            Editor view
	 */
	public GraphicElementMoveState(GraphicElement element, GraphicCanvasView view) {
		super(view);
		this.element = element;
	}

	/**
	 * Initializes a composite command based on the current selection.
	 */
	@Override
	public void enter() {
		for (GraphicShape s : view.getSelection()) {
			GraphicElementMoveShapeCommand c = new GraphicElementMoveShapeCommand(element, s, s.getPosition());
			c.captureCurrentState();
			commands.add(c);
		}
		compositeCommand = new GraphicElementCompositeCommand(element, commands);
	}

	/**
	 * Highlights the shape under the cursor.
	 * 
	 * @param e
	 *            Mouse event
	 */
	@Override
	public void mouseMove(MouseEvent e) {
		view.highlight(view.shapeUnderCursor(e));
	}

	/**
	 * Performs the translation of the shape(s) and updates the commands with
	 * the new positions.
	 * 
	 * @param e
	 *            Mouse event
	 */
	@Override
	public void mouseDrag(MouseEvent e) {
		for (GraphicShape s : view.getSelection()) {
			s.translate(e.getX() - lastPoint.getX(), e.getY() - lastPoint.getY());
		}

		for (GraphicElementMoveShapeCommand c : commands) {
			c.setNewPosition(c.getShape().getPosition());
		}

		view.repaint();
		lastPoint = new Point(e.getX(), e.getY());
	}

	/**
	 * Invokes the command to change the position of all affected shapes.
	 * 
	 * @param e
	 *            Mouse event
	 */
	@Override
	public void mouseUp(MouseEvent e) {
		if (compositeCommand != null) {
			GraphicElementInvoker.getInstance().executeCommand(compositeCommand);
			compositeCommand = null;
		}
	}

	/**
	 * Picks the shape under the cursor (unless it is part of the current
	 * selection, in which case the whole selection will be moved) and
	 * initializes the composite command to move only that one.
	 * 
	 * @param e
	 *            Mouse event
	 */
	@Override
	public void mouseDown(MouseEvent e) {
		GraphicShape s = view.shapeUnderCursor(e);
		lastPoint = new Point(e.getX(), e.getY());

		if (s != null && !view.getSelection().contains(s)) {
			view.deselectAll();
			view.select(s);

			GraphicElementMoveShapeCommand c = new GraphicElementMoveShapeCommand(element, s, s.getPosition());
			c.captureCurrentState();
			commands.add(c);

			commands = new ArrayList<>();
			compositeCommand = new GraphicElementCompositeCommand(element, commands);
		} else {
			enter();
		}
	}
}
