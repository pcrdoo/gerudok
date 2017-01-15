package state;
import java.awt.event.MouseEvent;

import view.elements.GraphicCanvasView;

public class GraphicElementState {
	protected GraphicCanvasView view;
	
	public GraphicElementState(GraphicCanvasView view)
	{
		this.view = view;
	}
	
	public void enter() { }
	public void leave() { }
	public void mouseClick(MouseEvent e) { }
	public void mouseDrag(MouseEvent e) { }
	public void mouseDown(MouseEvent e) { }
	public void mouseUp(MouseEvent e) { }
	public void mouseMove(MouseEvent e) { }
}
