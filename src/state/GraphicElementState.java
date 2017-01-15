package state;

import java.awt.event.MouseEvent;

import view.elements.GraphicCanvasView;

/**
 * State of the graphic element edit dialog, representing a modal mouse-based
 * tool of which only one can be selected as active at one time.
 * 
 * @author geomaster
 *
 */
public class GraphicElementState {
	/**
	 * Editor view.
	 */
	protected GraphicCanvasView view;

	/**
	 * Constructor.
	 * 
	 * @param view
	 *            Editor view
	 */
	public GraphicElementState(GraphicCanvasView view) {
		this.view = view;
	}

	/**
	 * Called when the state is entered i.e. the tool chosen active.
	 */
	public void enter() {
	}

	/**
	 * Called when the state is left i.e. a different tool chosen active.
	 */
	public void leave() {
	}

	/**
	 * Called when the mouse is clicked and this state is active.
	 * 
	 * @param e
	 *            Mouse event
	 */
	public void mouseClick(MouseEvent e) {
	}

	/**
	 * Called when the mouse is dragged and this state is active
	 * 
	 * @param e
	 *            Mouse event
	 */
	public void mouseDrag(MouseEvent e) {
	}

	/**
	 * Called when the mouse button is held and this state is active
	 * 
	 * @param e
	 *            Mouse event
	 */
	public void mouseDown(MouseEvent e) {
	}

	/**
	 * Called when the mouse button is released and this state is active
	 * 
	 * @param e
	 *            Mouse event
	 */
	public void mouseUp(MouseEvent e) {
	}

	/**
	 * Called when the mouse is moved and this state is active
	 * 
	 * @param e
	 *            Mouse event
	 */
	public void mouseMove(MouseEvent e) {
	}
}
