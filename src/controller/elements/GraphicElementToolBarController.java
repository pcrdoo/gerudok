package controller.elements;

import model.elements.GraphicElement;
import model.elements.GraphicElementToolBarAction;
import command.elements.GraphicElementCutCommand;
import command.elements.GraphicElementCopyCommand;
import command.elements.GraphicElementPasteCommand;
import state.GraphicElementEllipseState;
import state.GraphicElementMoveState;
import state.GraphicElementRectangleState;
import state.GraphicElementRemoveState;
import state.GraphicElementSelectState;
import state.GraphicElementLassoSelectState;
import state.GraphicElementStateManager;
import state.GraphicElementTriangleState;

import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;

import command.elements.GraphicElementInvoker;

import view.elements.GraphicCanvasView;
import view.elements.GraphicElementToolBarView;

/**
 * Controller for the toolbar in the graphic element edit dialog.
 * 
 * @author geomaster
 *
 */
public class GraphicElementToolBarController {
	/**
	 * Toolbar view.
	 */
	private GraphicElementToolBarView view;

	/**
	 * State manager for the graphic element edit dialog.
	 */
	private GraphicElementStateManager stateManager;

	/**
	 * Graphic element that is being edited.
	 */
	private GraphicElement element;

	/**
	 * Editor view.
	 */
	private GraphicCanvasView canvas;

	/**
	 * Constructor.
	 * 
	 * @param element
	 *            Element that is being edited
	 * @param view
	 *            Toolbar view this controller should control
	 * @param stateManager
	 *            State manager
	 * @param canvas
	 *            Editor view
	 */
	public GraphicElementToolBarController(GraphicElement element, GraphicElementToolBarView view,
			GraphicElementStateManager stateManager, GraphicCanvasView canvas) {
		this.view = view;
		this.stateManager = stateManager;
		this.canvas = canvas;
		this.element = element;

		stateManager.setState(new GraphicElementSelectState(canvas));

		view.registerActionListener(GraphicElementToolBarAction.SELECT, new SelectActionListener());
		view.registerActionListener(GraphicElementToolBarAction.LASSO_SELECT, new LassoSelectActionListener());
		view.registerActionListener(GraphicElementToolBarAction.UNDO, new UndoActionListener());
		view.registerActionListener(GraphicElementToolBarAction.REDO, new RedoActionListener());
		view.registerActionListener(GraphicElementToolBarAction.CUT, new CutActionListener());
		view.registerActionListener(GraphicElementToolBarAction.COPY, new CopyActionListener());
		view.registerActionListener(GraphicElementToolBarAction.PASTE, new PasteActionListener());
		view.registerActionListener(GraphicElementToolBarAction.RECTANGLE, new RectangleActionListener());
		view.registerActionListener(GraphicElementToolBarAction.ELLIPSE, new EllipseActionListener());
		view.registerActionListener(GraphicElementToolBarAction.TRIANGLE, new TriangleActionListener());
		view.registerActionListener(GraphicElementToolBarAction.MOVE, new MoveActionListener());
		view.registerActionListener(GraphicElementToolBarAction.REMOVE, new RemoveActionListener());

		view.setUndoEnabled(GraphicElementInvoker.getInstance().canUndo());
		view.setRedoEnabled(GraphicElementInvoker.getInstance().canRedo());
		GraphicElementInvoker.getInstance().registerUndoRedoStatusListener(new UndoRedoStatusListener());
	}

	/**
	 * Listener for the selection of the 'select' tool in the toolbar.
	 * 
	 * @author geomaster
	 */
	class SelectActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			/**
			 * Sets the current state to the selection state.
			 */
			stateManager.setState(new GraphicElementSelectState(canvas));
		}
	}

	/**
	 * Listener for the selection of the 'lasso select' tool in the toolbar.
	 * 
	 * @author geomaster
	 */
	class LassoSelectActionListener implements ActionListener {
		/**
		 * Sets the current state to the lasso selection state.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			stateManager.setState(new GraphicElementLassoSelectState(element, canvas));
		}
	}

	/**
	 * Listener for the selection of the 'undo' action in the toolbar.
	 * 
	 * @author geomaster
	 */
	class UndoActionListener implements ActionListener {
		/**
		 * Undoes the last command via the invoker.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				GraphicElementInvoker.getInstance().undoCommand();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Undo returned an error: " + ex.getMessage());
			}
		}
	}

	/**
	 * Listener for the selection of the 'redo' action in the toolbar.
	 * 
	 * @author geomaster
	 */
	class RedoActionListener implements ActionListener {
		@Override
		/**
		 * Redoes the last command via the invoker.
		 */
		public void actionPerformed(ActionEvent e) {
			try {
				GraphicElementInvoker.getInstance().redoCommand();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Redo returned an error: " + ex.getMessage());
			}
		}
	}

	/**
	 * Listener for the selection of the 'rectangle' tool in the toolbar.
	 * 
	 * @author geomaster
	 */
	class RectangleActionListener implements ActionListener {
		@Override
		/**
		 * Sets the current state to the rectangle state.
		 */
		public void actionPerformed(ActionEvent e) {
			stateManager.setState(new GraphicElementRectangleState(element, canvas));
		}
	}

	/**
	 * Listener for the selection of the 'ellipse' tool in the toolbar.
	 * 
	 * @author geomaster
	 */
	class EllipseActionListener implements ActionListener {
		@Override
		/**
		 * Sets the current state to the ellipse state.
		 */
		public void actionPerformed(ActionEvent e) {
			stateManager.setState(new GraphicElementEllipseState(element, canvas));
		}
	}

	/**
	 * Listener for the selection of the 'triangle' tool in the toolbar.
	 * 
	 * @author geomaster
	 */
	class TriangleActionListener implements ActionListener {
		@Override
		/**
		 * Sets the current state to the triangle state.
		 */
		public void actionPerformed(ActionEvent e) {
			stateManager.setState(new GraphicElementTriangleState(element, canvas));
		}
	}

	/**
	 * Listener for the selection of the 'move' tool in the toolbar.
	 * 
	 * @author geomaster
	 */
	class MoveActionListener implements ActionListener {
		@Override
		/**
		 * Sets the current state to the move state.
		 */
		public void actionPerformed(ActionEvent e) {
			stateManager.setState(new GraphicElementMoveState(element, canvas));
		}
	}

	/**
	 * Listener for the selection of the 'remove' tool in the toolbar.
	 * 
	 * @author geomaster
	 */
	class RemoveActionListener implements ActionListener {
		@Override
		/**
		 * Sets the current state to the remove state.
		 */
		public void actionPerformed(ActionEvent e) {
			stateManager.setState(new GraphicElementRemoveState(element, canvas));
		}
	}

	/**
	 * Listener for changes in undo/redo enabled status.
	 * 
	 * @author geomaster
	 */
	class UndoRedoStatusListener implements command.elements.UndoRedoStatusListener {
		@Override
		/**
		 * Called when the undo status has changed. Enables/disables the undo
		 * button in the toolbar.
		 * 
		 * @param undoStatus
		 *            Whether undo is now enabled
		 */
		public void undoStatusChanged(boolean undoStatus) {
			view.setUndoEnabled(undoStatus);
		}

		/**
		 * Called when the redo status has changed. Enabled/disables the redo
		 * button in the toolbar.
		 * 
		 * @param redoStatus
		 *            Whether redo is now enabled
		 */
		@Override
		public void redoStatusChanged(boolean redoStatus) {
			view.setRedoEnabled(redoStatus);
		}
	}

	/**
	 * Listener for the selection of the 'cut' action in the toolbar.
	 * 
	 * @author geomaster
	 */
	class CutActionListener implements ActionListener {
		/**
		 * Runs the 'cut' command via the invoker.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			GraphicElementInvoker.getInstance().executeCommand(new GraphicElementCutCommand(element, canvas));
		}
	}

	/**
	 * Listener for the selection of the 'copy' action in the toolbar.
	 * 
	 * @author geomaster
	 */
	class CopyActionListener implements ActionListener {
		/**
		 * Runs the 'copy' command via the invoker.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			GraphicElementInvoker.getInstance().executeCommand(new GraphicElementCopyCommand(element, canvas));
		}
	}

	/**
	 * Listener for the selection of the 'paste' action in the toolbar.
	 * 
	 * @author geomaster
	 */
	class PasteActionListener implements ActionListener {
		/**
		 * Runs the 'paste' command via the invoker.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			GraphicElementInvoker.getInstance().executeCommand(new GraphicElementPasteCommand(element, canvas));
		}
	}
}
