package clipboard;

import model.elements.GraphicShape;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.io.IOException;
import java.util.Set;

import clipboard.serialize.GraphicElementClipboardDeserializer;
import clipboard.serialize.GraphicElementClipboardSerializer;

/**
 * Manager of the clipboard and clipboard entries pertaining to the graphic
 * element.
 * 
 * @author geomaster
 *
 */
public class GraphicElementClipboardManager {
	/**
	 * Singleton instance.
	 */
	private static GraphicElementClipboardManager instance = null;

	/**
	 * Serializer for clipboard entries.
	 */
	private GraphicElementClipboardSerializer serializer = new GraphicElementClipboardSerializer();

	/**
	 * Deserializer for clipboard entries.
	 */
	private GraphicElementClipboardDeserializer deserializer = new GraphicElementClipboardDeserializer();

	/**
	 * Serialized string representing the last retrieved clipboard entry.
	 */
	private String lastEntryString = null;

	/**
	 * Last retrieved clipboard entry.
	 */
	private GraphicElementClipboardEntry lastEntry = null;

	/**
	 * Constructor - do not use. This is a singleton.
	 */
	private GraphicElementClipboardManager() {

	}

	/**
	 * Gets the singleton instance of the clipboard manager.
	 * 
	 * @return Singleton instance
	 */
	public static GraphicElementClipboardManager getInstance() {
		if (instance == null) {
			instance = new GraphicElementClipboardManager();
		}
		return instance;
	}

	/**
	 * Puts a clipboard entry in the clipboard.
	 * 
	 * @param entry
	 *            Clipboard entry to put
	 */
	public void toClipboard(GraphicElementClipboardEntry entry) {
		if (lastEntry != null) {
			lastEntry.notifyEvicted();
		}

		lastEntry = entry;
		lastEntryString = serializer.serialize(entry.getShapes());
		StringSelection str = new StringSelection(lastEntryString);
		Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
		cb.setContents(str, str);
	}

	/**
	 * Returns a clipboard entry from the clipboard.
	 * 
	 * @return Clipboard entry in the clipboard, or null if there's none
	 * @throws SerializationDeserializationException
	 *             If there was an error deserializing the clipboard contents.
	 * @throws ClipboardException
	 *             If there was an error accessing clipboard contents.
	 */
	public GraphicElementClipboardEntry fromClipboard()
			throws SerializationDeserializationException, ClipboardException {
		Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable t = cb.getContents(null);
		if (t.isDataFlavorSupported(DataFlavor.stringFlavor)) {
			try {
				Object o = t.getTransferData(DataFlavor.stringFlavor);
				String s = (String) o;
				if (s != null) {
					if (s.equals(lastEntryString)) {
						lastEntry.notifyPasted();
						return lastEntry;
					}

					Set<GraphicShape> shapes = deserializer.deserialize(s);
					GraphicElementClipboardEntry e = new GraphicElementClipboardEntry(shapes);
					lastEntry = e;
					lastEntryString = s;

					return e;
				} else {
					return null;
				}
			} catch (UnsupportedFlavorException e) {
				throw new ClipboardException("This clipboard is not supported");
			} catch (IOException e) {
				throw new ClipboardException("I/O error while accessing the clipboard");
			}
		} else {
			return null;
		}
	}
}
