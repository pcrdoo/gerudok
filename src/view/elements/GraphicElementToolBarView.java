package view.elements;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JToolBar;

import controller.elements.GraphicElementToolBarController;
import model.Model;
import model.elements.GraphicElement;
import model.elements.GraphicElementToolBarAction;
import state.GraphicElementStateManager;

import java.util.*;

/**
 * Toolbar for the graphic element edit dialog.
 * 
 * @author geomaster
 *
 */
public class GraphicElementToolBarView extends JToolBar {
	/**
	 * Version UID for serialization.
	 */
	final static long serialVersionUID = 1;
	
	/**
	 * Controller.
	 */
	GraphicElementToolBarController controller;

	/**
	 * Listeners registered for actions.
	 */
	Map<GraphicElementToolBarAction, ActionListener> listeners = new HashMap<>();

	/**
	 * Toolbar buttons.
	 */
	Map<GraphicElementToolBarAction, JButton> buttons = new HashMap<>();

	/**
	 * Constructor.
	 * 
	 * @param model
	 *            Model
	 * @param element
	 *            Element being edited
	 * @param stateManager
	 *            State manager for the edit dialog
	 * @param canvas
	 *            Editor view
	 */
	public GraphicElementToolBarView(Model model, GraphicElement element, GraphicElementStateManager stateManager,
			GraphicCanvasView canvas) {
		super();

		final String[] labels = { "Select", "Lasso Select", "Undo", "Redo", "Move", "Remove", "Cut", "Copy", "Paste",
				"Rectangle", "Ellipse", "Triangle" };

		final int[] separators = { 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0 };

		final GraphicElementToolBarAction[] actions = { GraphicElementToolBarAction.SELECT,
				GraphicElementToolBarAction.LASSO_SELECT, GraphicElementToolBarAction.UNDO,
				GraphicElementToolBarAction.REDO, GraphicElementToolBarAction.MOVE, GraphicElementToolBarAction.REMOVE,
				GraphicElementToolBarAction.CUT, GraphicElementToolBarAction.COPY, GraphicElementToolBarAction.PASTE,
				GraphicElementToolBarAction.RECTANGLE, GraphicElementToolBarAction.ELLIPSE,
				GraphicElementToolBarAction.TRIANGLE };

		final String[] filenames = { "select.png", "multiselect.png", "undo.png", "redo.png", "move.png", "delete.png",
				"cut.png", "copy.png", "paste.png", "rectangle.png", "ellipse.png", "triangle.png" };

		final String filenamePrefix = "src/res/graphic_edit_dialog/";

		for (int i = 0; i < labels.length; i++) {
			JButton button = new JButton();
			button.setToolTipText(labels[i]);
			button.setIcon(new ImageIcon(filenamePrefix + filenames[i]));

			final int j = i;
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (listeners.containsKey(actions[j])) {
						listeners.get(actions[j]).actionPerformed(e);
					}
				}
			});

			buttons.put(actions[i], button);
			add(button);

			if (separators[i] != 0) {
				addSeparator();
			}
		}

		controller = new GraphicElementToolBarController(element, this, stateManager, canvas);
	}

	/**
	 * Sets whether the undo button is enabled.
	 * 
	 * @param enabled
	 *            Whether to enable undo
	 */
	public void setUndoEnabled(boolean enabled) {
		buttons.get(GraphicElementToolBarAction.UNDO).setEnabled(enabled);
	}

	/**
	 * Sets whether the redo button is enabled.
	 * 
	 * @param enabled
	 *            Whether to enable redo
	 */
	public void setRedoEnabled(boolean enabled) {
		buttons.get(GraphicElementToolBarAction.REDO).setEnabled(enabled);
	}

	/**
	 * Registers a listener for a toolbar action.
	 * 
	 * @param action
	 *            Action to register the listener for
	 * @param listener
	 *            Listener to register
	 */
	public void registerActionListener(GraphicElementToolBarAction action, ActionListener listener) {
		listeners.put(action, listener);
	}

}
