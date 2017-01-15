package view.elements;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseListener;

import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import model.Model;
import model.elements.GraphicElement;
import view.ElementView;

public class GraphicElementView extends ElementView {
	private GraphicElement graphicElement;
	private GraphicCanvasView canvas;
	
	public GraphicElementView(Model model, GraphicElement graphicElement) {
		super(model, graphicElement);
		
		this.graphicElement = graphicElement;
		canvas = new GraphicCanvasView(graphicElement);

		canvas.setMinimumSize(new Dimension(500, 30));
		add(canvas);
		for (MouseListener l: deferredListeners) {
			canvas.addMouseListener(l);
		}
	}
	
	public void onEditNotification(Object obj) {
		canvas.repaint();
	}
	
	public GraphicElement getGraphicElement() {
		return (GraphicElement) getElementContainer();
	}
}
