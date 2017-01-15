package state;
import java.awt.event.MouseEvent;

import model.elements.GraphicShape;
import view.elements.GraphicCanvasView;

public class GraphicElementSelectState extends GraphicElementState {
	public GraphicElementSelectState(GraphicCanvasView view)
	{
		super(view);
	}
	
	@Override
	public void enter()
	{
		view.deselectAll();
	}
	
	@Override
	public void mouseMove(MouseEvent e)
	{
		view.highlight(view.shapeUnderCursor(e));
	}
	
	@Override
	public void mouseClick(MouseEvent e)
	{
		GraphicShape shape = view.shapeUnderCursor(e);
		if (shape != null) {
			view.toggleSelected(shape);
		} else {
			view.deselectAll();
		}
	}
}