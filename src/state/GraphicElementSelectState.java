package state;

import java.awt.event.MouseEvent;

import model.elements.GraphicShape;
import view.elements.GraphicCanvasView;

/**
 * Selection tool state.
 * 
 * @author geomaster
 *
 */
public class GraphicElementSelectState extends GraphicElementState {
	/**
	 * Constructor.
	 * 
	 * @param view
	 *            Editor view
	 */
	public GraphicElementSelectState(GraphicCanvasView view) {
		super(view);
	}

	/**
	 * Deselects all elements.
	 */
	@Override
	public void enter() {
		view.deselectAll();
	}

	/**
	 * Highlights the element under the cursor.
	 * 
	 * @param e
	 *            Mouse event
	 */
	@Override
	public void mouseMove(MouseEvent e) {
		view.highlight(view.shapeUnderCursor(e));
	}

	/**
	 * Toggles the selected state of the element under the cursor, or if there's
	 * none, deselects all elements.
	 * 
	 * @param e
	 *            Mouse event
	 */
	@Override
	public void mouseClick(MouseEvent e) {
		GraphicShape shape = view.shapeUnderCursor(e);
		if (shape != null) {
			view.toggleSelected(shape);
		} else {
			view.deselectAll();
		}
	}
}
