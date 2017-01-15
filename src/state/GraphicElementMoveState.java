package state;
import java.awt.event.MouseEvent;

import model.elements.GraphicShape;
import model.elements.Point;
import view.elements.GraphicCanvasView;
import model.elements.GraphicElement;
import command.elements.GraphicElementInvoker;
import command.elements.GraphicElementMoveShapeCommand;
import command.elements.GraphicElementCompositeCommand;

import java.util.List;
import java.util.ArrayList;

public class GraphicElementMoveState extends GraphicElementState {
	private Point lastPoint = null;
	private GraphicElement element;
	private List<GraphicElementMoveShapeCommand> commands = new ArrayList<>();
	private GraphicElementCompositeCommand compositeCommand = null;
	
	public GraphicElementMoveState(GraphicElement element, GraphicCanvasView view)
	{
		super(view);
		this.element = element;
	}
	
	@Override
	public void enter()
	{
		for (GraphicShape s: view.getSelection()) {
			GraphicElementMoveShapeCommand c = new GraphicElementMoveShapeCommand(element, s, s.getPosition());
			c.captureCurrentState();
			commands.add(c);
		}
		compositeCommand = new GraphicElementCompositeCommand(element, commands);
	}
	
	@Override
	public void mouseMove(MouseEvent e)
	{
		view.highlight(view.shapeUnderCursor(e));
	}
	
	@Override
	public void mouseDrag(MouseEvent e)
	{
		for (GraphicShape s: view.getSelection()) {
			s.translate(e.getX() - lastPoint.getX(), e.getY() - lastPoint.getY());
		}
		
		for (GraphicElementMoveShapeCommand c: commands) {
			c.setNewPosition(c.getShape().getPosition());
		}
		
		view.repaint();
		lastPoint = new Point(e.getX(), e.getY());
	}
	
	@Override
	public void mouseUp(MouseEvent e)
	{
		if (compositeCommand != null) {
			GraphicElementInvoker.getInstance().executeCommand(compositeCommand);
			compositeCommand = null;
		}
	}
	
	@Override
	public void mouseDown(MouseEvent e)
	{
		GraphicShape s = view.shapeUnderCursor(e);
		lastPoint = new Point(e.getX(), e.getY());
		if (s != null) {
			if (!view.getSelection().contains(s)) {
				view.deselectAll();
				view.select(s);
				
				GraphicElementMoveShapeCommand c = new GraphicElementMoveShapeCommand(element, s, s.getPosition());
				c.captureCurrentState();
				commands = new ArrayList<>();
				commands.add(c);
				compositeCommand = new GraphicElementCompositeCommand(element, commands);
			}
		}
	}
}
