package clipboard;
import java.util.Set;
import java.util.HashSet;
import model.elements.GraphicShape;
import java.util.List;
import java.util.ArrayList;

public class GraphicElementClipboardEntry {
	private Set<GraphicShape> shapes;
	private List<GraphicElementClipboardEntryListener> listeners = new ArrayList<GraphicElementClipboardEntryListener>();
	private List<GraphicElementClipboardEntryListener> listenersToAdd = new ArrayList<>();
	private List<GraphicElementClipboardEntryListener> listenersToRemove = new ArrayList<>();
	private boolean inCriticalSection = false;
	
	int pastedCount = 0;
	
	private void enterCriticalSection() {
		inCriticalSection = true;
	}
	
	private void exitCriticalSection() {
		inCriticalSection = false;
		
		for (GraphicElementClipboardEntryListener listener: listenersToRemove) {
			listeners.remove(listener);
		}

		for (GraphicElementClipboardEntryListener listener: listenersToAdd) {
			listeners.add(listener);
		}
		
		listenersToRemove.clear();
		listenersToAdd.clear();
	}
	
	public GraphicElementClipboardEntry(Set<GraphicShape> shapes)
	{
		this.shapes = new HashSet<GraphicShape>();
		this.shapes.addAll(shapes);
	}
	
	public void registerEntryListener(GraphicElementClipboardEntryListener listener)
	{
		if (inCriticalSection) {
			listenersToAdd.add(listener);
		} else {
			listeners.add(listener);
		}
	}

	public void unregisterEntryListener(GraphicElementClipboardEntryListener listener)
	{
		if (inCriticalSection) {
			listenersToRemove.add(listener);
		} else {
			listeners.remove(listener);
		}
	}
	
	public void onPasted()
	{
		pastedCount++;
		enterCriticalSection();
		for (GraphicElementClipboardEntryListener listener: listeners) {
			listener.onEntryPasted();
		}
		exitCriticalSection();
	}
	
	public void onEvicted()
	{
		enterCriticalSection();
		for (GraphicElementClipboardEntryListener listener: listeners) {
			listener.onEntryEvicted();
		}
		exitCriticalSection();
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
