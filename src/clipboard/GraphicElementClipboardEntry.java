package clipboard;

import java.util.Set;
import java.util.HashSet;
import model.elements.GraphicShape;
import java.util.List;
import java.util.ArrayList;

/**
 * Clipboard entry representing a set of shapes copied to the clipboard.
 * 
 * @author geomaster
 *
 */
public class GraphicElementClipboardEntry {
	/**
	 * Shapes contained in the entry.
	 */
	private Set<GraphicShape> shapes;

	/**
	 * List of currently registered listeners.
	 */
	private List<GraphicElementClipboardEntryListener> listeners = new ArrayList<GraphicElementClipboardEntryListener>();

	/**
	 * As we cannot add listeners while iterating over them to fire an event,
	 * this keeps tabs on listeners added in other listeners' on* methods, so
	 * they can be added when the iteration is finished.
	 */
	private List<GraphicElementClipboardEntryListener> listenersToAdd = new ArrayList<>();

	/**
	 * As we cannot remove listeners while iterating over them to fire an event,
	 * this keeps tabs on listeners removed in other listeners' on* methods, so
	 * they can be removed when the iteration is finished.
	 */
	private List<GraphicElementClipboardEntryListener> listenersToRemove = new ArrayList<>();

	/**
	 * Tracks whether we're inside a critical section, i.e. currently iterating
	 * over listeners as a result of an event firing.
	 */
	private boolean inCriticalSection = false;

	/**
	 * Number of times this entry has been pasted. Used to cascade elements when
	 * pasting.
	 */
	private int pastedCount = 0;

	/**
	 * Enters a critical section. Called when iterating over listeners in order
	 * to notify them of events starts.
	 */
	private void enterCriticalSection() {
		inCriticalSection = true;
	}

	/**
	 * Exits a critical section. Called when iterating over listeners in order
	 * to notify them of events ends. All listener register and listener
	 * unregister requests made during the critical section are applied here.
	 */
	private void exitCriticalSection() {
		inCriticalSection = false;

		for (GraphicElementClipboardEntryListener listener : listenersToRemove) {
			listeners.remove(listener);
		}

		for (GraphicElementClipboardEntryListener listener : listenersToAdd) {
			listeners.add(listener);
		}

		listenersToRemove.clear();
		listenersToAdd.clear();
	}

	/**
	 * Constructor.
	 * 
	 * @param shapes
	 *            Shapes contained inside this clipboard entry
	 */
	public GraphicElementClipboardEntry(Set<GraphicShape> shapes) {
		this.shapes = new HashSet<GraphicShape>();
		this.shapes.addAll(shapes);
	}

	/**
	 * Registers a listener for this entry.
	 * 
	 * @param listener
	 *            Listener to register
	 */
	public void registerEntryListener(GraphicElementClipboardEntryListener listener) {
		if (inCriticalSection) {
			listenersToAdd.add(listener);
		} else {
			listeners.add(listener);
		}
	}

	/**
	 * Unregisters a previously registered listener for this entry.
	 * 
	 * @param listener
	 *            Listener to unregister
	 */
	public void unregisterEntryListener(GraphicElementClipboardEntryListener listener) {
		if (inCriticalSection) {
			listenersToRemove.add(listener);
		} else {
			listeners.remove(listener);
		}
	}

	/**
	 * Notifies the clipboard entry that it has been pasted into a document.
	 */
	public void notifyPasted() {
		pastedCount++;
		enterCriticalSection();
		for (GraphicElementClipboardEntryListener listener : listeners) {
			listener.onEntryPasted();
		}
		exitCriticalSection();
	}

	/**
	 * Notifies the clipboard entry that it has been evicted from the clipboard,
	 * as a result of another entry being copied or cut.
	 */
	public void notifyEvicted() {
		enterCriticalSection();
		for (GraphicElementClipboardEntryListener listener : listeners) {
			listener.onEntryEvicted();
		}
		exitCriticalSection();
	}

	/**
	 * Gets the shapes in this clipboard entry.
	 * 
	 * @return Set of shapes
	 */
	public Set<GraphicShape> getShapes() {
		return shapes;
	}

	/**
	 * Gets the number of times this clipboard entry has been pasted.
	 * 
	 * @return Number representing the count of times the entry has been pasted
	 */
	public int getPastedCount() {
		return pastedCount;
	}
}
