package state;

import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

/**
 * Manager of the state for the graphic element edit dialog. Keeps track of the
 * current state and manages transitions between states.
 * 
 * @author geomaster
 *
 */
public class GraphicElementStateManager implements MouseMotionListener, MouseListener {
	/**
	 * Current state.
	 */
	private GraphicElementState currentState = null;

	/**
	 * Sets a new state as current.
	 * 
	 * @param newState
	 *            New current state
	 */
	public void setState(GraphicElementState newState) {
		if (currentState != null) {
			currentState.leave();
		}
		if (newState != null) {
			newState.enter();
		}
		currentState = newState;
	}

	/**
	 * Called when the mouse is clicked.
	 * 
	 * @param e
	 *            Mouse event
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if (currentState != null) {
			currentState.mouseClick(e);
		}
	}

	/**
	 * Called when the mouse enters the associated view.
	 * 
	 * @param e
	 *            Mouse event
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
	}

	/**
	 * Called when the mouse exits the associated view.
	 * 
	 * @param e
	 *            Mouse event
	 */
	@Override
	public void mouseExited(MouseEvent e) {
	}

	/**
	 * Called when the mouse button is released.
	 * 
	 * @param e
	 *            Mouse event
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		if (currentState != null) {
			currentState.mouseUp(e);
		}
	}

	/**
	 * Called when the mouse button is held down.
	 * 
	 * @param e
	 *            Mouse event
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		if (currentState != null) {
			currentState.mouseDown(e);
		}
	}

	/**
	 * Called when the mouse moves over the associated view.
	 * 
	 * @param e
	 *            Mouse event
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		if (currentState != null) {
			currentState.mouseMove(e);
		}
	}

	/**
	 * Called when the mouse is dragged over the associated view.
	 * 
	 * @param e
	 *            Mouse event
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		if (currentState != null) {
			currentState.mouseDrag(e);
		}
	}
}
