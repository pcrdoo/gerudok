package state;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class GraphicElementStateManager implements MouseMotionListener, MouseListener {
	private GraphicElementState currentState = null;
	
	public GraphicElementStateManager()
	{
		
	}
	
	public void setState(GraphicElementState newState)
	{
		if (currentState != null) {
			currentState.leave();
		}
		if (newState != null) {
			newState.enter();
		}
		currentState = newState;
	}
	
	@Override
	public void mouseClicked(MouseEvent e)
	{
		if (currentState != null) {
			currentState.mouseClick(e);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) { }
	
	@Override
	public void mouseExited(MouseEvent e) { }
	
	@Override
	public void mouseReleased(MouseEvent e)
	{
		if (currentState != null) {
			currentState.mouseUp(e);
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e)
	{
		if (currentState != null) {
			currentState.mouseDown(e);
		}
	}
	
	@Override
	public void mouseMoved(MouseEvent e)
	{
		if (currentState != null) {
			currentState.mouseMove(e);
		}
	}

	@Override
	public void mouseDragged(MouseEvent e)
	{ 
		if (currentState != null) {
			currentState.mouseDrag(e);
		}
	}
}
