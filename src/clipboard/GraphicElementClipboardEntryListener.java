package clipboard;

/**
 * Listener interface that is registered with a GraphicElementClipboardEntry and
 * is notified when the entry is pasted into a document or evicted from the
 * clipboard.
 * 
 * @author geomaster
 *
 */
public interface GraphicElementClipboardEntryListener {
	/**
	 * Called when the entry is evicted from the clipboard, usually as a result
	 * of another entry being copied or cut.
	 */
	public void onEntryEvicted();

	/**
	 * Called when the entry is pasted into a document.
	 */
	public void onEntryPasted();
}
