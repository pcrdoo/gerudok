package controller;
import model.GraphicElementToolBarAction;
import model.Model;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import view.GraphicElementToolBarView;
import view.GraphicCanvasView;

public class GraphicElementToolBarController {
	private Model model;
	private GraphicElementToolBarView view;
	private GraphicElementStateManager stateManager;
	private GraphicCanvasView canvas;
	
	public GraphicElementToolBarController(Model model, GraphicElementToolBarView view, GraphicElementStateManager stateManager, GraphicCanvasView canvas)
	{
		this.model = model;
		this.view = view;
		this.stateManager = stateManager;
		this.canvas = canvas;
		
		stateManager.setState(new GraphicElementSelectState(canvas));
		
		view.registerActionListener(GraphicElementToolBarAction.SELECT, new SelectActionListener());
		view.registerActionListener(GraphicElementToolBarAction.RECTANGLE, new RectangleActionListener());
		view.registerActionListener(GraphicElementToolBarAction.ELLIPSE, new EllipseActionListener());
		view.registerActionListener(GraphicElementToolBarAction.TRIANGLE, new TriangleActionListener());
		view.registerActionListener(GraphicElementToolBarAction.MOVE, new MoveActionListener());
		view.registerActionListener(GraphicElementToolBarAction.REMOVE, new RemoveActionListener());
	}
	
	class SelectActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			stateManager.setState(new GraphicElementSelectState(canvas));
		} 
	}
	
	class RectangleActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			stateManager.setState(new GraphicElementRectangleState(canvas));
		} 
	}
	
	class EllipseActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			stateManager.setState(new GraphicElementEllipseState(canvas));
		} 
	}
	
	class TriangleActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			stateManager.setState(new GraphicElementTriangleState(canvas));
		} 
	}
	
	class MoveActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			stateManager.setState(new GraphicElementMoveState(canvas));
		} 
	}
	
	class RemoveActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			stateManager.setState(new GraphicElementRemoveState(canvas));
		} 
	}
}
