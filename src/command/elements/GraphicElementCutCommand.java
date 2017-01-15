package command.elements;

import java.util.ArrayList;

import model.elements.GraphicShape;
import clipboard.GraphicElementClipboardEntry;
import clipboard.GraphicElementClipboardManager;
import clipboard.GraphicElementClipboardEntryListener;
import model.elements.GraphicElement;
import view.elements.GraphicCanvasView;

/**
 * Command which cuts the current selection to the clipboard.
 * 
 * @author geomaster
 *
 */
public class GraphicElementCutCommand extends GraphicElementCommand implements GraphicElementClipboardEntryListener {
	/**
	 * Editor view.
	 */
	private GraphicCanvasView view;

	/**
	 * Entry that was added to the clipboard.
	 */
	private GraphicElementClipboardEntry entry = null;

	/**
	 * Whether the cut content was pasted somewhere.
	 */
	private boolean wasPasted = false;

	/**
	 * Constructor.
	 * 
	 * @param element
	 *            Element to act upon
	 * @param view
	 *            Editor view whose selection to cut
	 */
	public GraphicElementCutCommand(GraphicElement element, GraphicCanvasView view) {
		super(element);
		this.view = view;
	}

	/**
	 * Copies the selected shapes to the clipboard, and makes them translucent
	 * in the editor view to signify their state.
	 */
	@Override
	public void doCommand() {
		entry = new GraphicElementClipboardEntry(view.getSelection());
		entry.registerEntryListener(this);
		GraphicElementClipboardManager.getInstance().toClipboard(entry);
		view.cutSelection();
	}

	/**
	 * Gets the command name.
	 * 
	 * @return Command name
	 */
	@Override
	public String getCommandName() {
		return "cut shape(s)";
	}

	/**
	 * Returns whether the command is undoable. The cut command itself is
	 * undoable if the content hasn't been pasted i.e. the shapes are still in
	 * 'limbo'.
	 */
	@Override
	public boolean isUndoable() {
		return !wasPasted;
	}

	/**
	 * Returns true, as the user can always re-cut specific elements.
	 */
	@Override
	public boolean isRedoable() {
		return true;
	}

	/**
	 * Undoes the cutting command by making the shapes non-translucent in the
	 * editor view.
	 */
	@Override
	public void undoCommand() {
		view.clearCutShapes();
		entry.unregisterEntryListener(this);
	}

	/**
	 * Called when the entry is evicted from the clipboard. If this happens, the
	 * shapes are made non-translucent in the editor view, as this means the cut
	 * 'n' paste operation was aborted.
	 */
	public void onEntryEvicted() {
		view.clearCutShapes();
	}

	/**
	 * Called when the entry is pasted from the clipboard. When this happens,
	 * the cut 'n' paste operation was finished successfully, and the shapes are
	 * physically removed from the graphic element altogether.
	 */
	public void onEntryPasted() {
		ArrayList<GraphicElementRemoveShapeCommand> list = new ArrayList<>();
		for (GraphicShape s : view.getSelection()) {
			list.add(new GraphicElementRemoveShapeCommand(element, s));
		}
		GraphicElementInvoker.getInstance().executeCommand(new GraphicElementCompositeCommand(element, list));
		view.clearCutShapes();

		entry.unregisterEntryListener(this);
		wasPasted = true;
	}
}