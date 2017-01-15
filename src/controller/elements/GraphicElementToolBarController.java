package controller.elements;
import model.Model;

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

public class GraphicElementToolBarController {
	private Model model;
	private GraphicElementToolBarView view;
	private GraphicElementStateManager stateManager;
	private GraphicElement element;
	private GraphicCanvasView canvas;
	
	public GraphicElementToolBarController(Model model, GraphicElement element, GraphicElementToolBarView view, GraphicElementStateManager stateManager, GraphicCanvasView canvas)
	{
		this.model = model;
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
	
	class SelectActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			stateManager.setState(new GraphicElementSelectState(canvas));
		} 
	}
	
	class LassoSelectActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			stateManager.setState(new GraphicElementLassoSelectState(element, canvas));
		} 
	}
	
	class UndoActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				GraphicElementInvoker.getInstance().undoCommand();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Undo returned an error: " + ex.getMessage());
			}
		} 
	}
	
	class RedoActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				GraphicElementInvoker.getInstance().redoCommand();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Redo returned an error: " + ex.getMessage());
			}
		} 
	}
	
	class RectangleActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			stateManager.setState(new GraphicElementRectangleState(element, canvas));
		} 
	}
	
	class EllipseActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			stateManager.setState(new GraphicElementEllipseState(element, canvas));
		} 
	}
	
	class TriangleActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			stateManager.setState(new GraphicElementTriangleState(element, canvas));
		} 
	}
	
	class MoveActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			stateManager.setState(new GraphicElementMoveState(element, canvas));
		} 
	}
	
	class RemoveActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			stateManager.setState(new GraphicElementRemoveState(element, canvas));
		} 
	}
	
	class UndoRedoStatusListener implements command.elements.UndoRedoStatusListener {
		@Override
		public void undoStatusChanged(boolean undoStatus) {
			view.setUndoEnabled(undoStatus);
		}
		
		@Override
		public void redoStatusChanged(boolean redoStatus) {
			view.setRedoEnabled(redoStatus);
		}
	}
	
	class CutActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			GraphicElementInvoker.getInstance().executeCommand(new GraphicElementCutCommand(element, canvas));
		} 		
	}
	
	
	class CopyActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			GraphicElementInvoker.getInstance().executeCommand(new GraphicElementCopyCommand(element, canvas));
		}
	}
	
	
	class PasteActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			GraphicElementInvoker.getInstance().executeCommand(new GraphicElementPasteCommand(element, canvas));
		} 		
	}
}
