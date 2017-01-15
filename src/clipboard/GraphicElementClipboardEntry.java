package clipboard;
import java.util.Set;
import model.elements.GraphicShape;

public class GraphicElementClipboardEntry {
	private Set<GraphicShape> shapes;
	int pastedCount = 0;
	
	public GraphicElementClipboardEntry(Set<GraphicShape> shapes)
	{
		this.shapes = shapes;
	}
	
	public void onPasted()
	{
		pastedCount++;
	}
	
	public Set<GraphicShape> getShapes()
	{
		return shapes;
	}
	
	public int getPastedCount()
	{
		return pastedCount;
	}
}
