package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseListener;

import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import model.Model;
import model.GraphicElement;

public class GraphicElementView extends ElementView {
	private GraphicElement graphicElement;
	private GraphicCanvasView canvas;
	
	public GraphicElementView(Model model, GraphicElement graphicElement) {
		super(model, graphicElement);
		
		this.graphicElement = graphicElement;
		canvas = new GraphicCanvasView();
	    
		add(canvas);
		for (MouseListener l: deferredListeners) {
			canvas.addMouseListener(l);
		}
		canvas.setShapes(graphicElement.getShapes());
	}
	
	public void onEditNotification(Object obj) {
		canvas.setShapes(graphicElement.getShapes());
	}
	
	public GraphicElement getGraphicElement() {
		return (GraphicElement) getElementContainer();
	}
}
