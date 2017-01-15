package state;

import java.awt.event.MouseEvent;

import model.elements.GraphicElement;
import model.elements.GraphicShape;
import view.elements.GraphicCanvasView;

import command.elements.GraphicElementInvoker;
import command.elements.GraphicElementRemoveShapeCommand;
import command.elements.GraphicElementCompositeCommand;
import java.util.ArrayList;

/**
 * Shape remove tool state.
 * 
 * @author geomaster
 *
 */
public class GraphicElementRemoveState extends GraphicElementState {
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
	public GraphicElementRemoveState(GraphicElement element, GraphicCanvasView view) {
		super(view);
		this.element = element;
	}

	/**
	 * Invokes a composite command to remove all currently selected shapes from
	 * the element.
	 */
	@Override
	public void enter() {
		ArrayList<GraphicElementRemoveShapeCommand> removeAll = new ArrayList<>();
		for (GraphicShape s : view.getSelection()) {
			removeAll.add(new GraphicElementRemoveShapeCommand(element, s));
		}
		GraphicElementInvoker.getInstance().executeCommand(new GraphicElementCompositeCommand(element, removeAll));
		view.deselectAll();
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
	 * Invokes a command to remove the shape under the cursor.
	 * 
	 * @param e
	 *            Mouse event
	 */
	@Override
	public void mouseClick(MouseEvent e) {
		GraphicShape s = view.shapeUnderCursor(e);
		if (s != null) {
			GraphicElementInvoker.getInstance().executeCommand(new GraphicElementRemoveShapeCommand(element, s));
		}
	}
}
