package command.elements;

import model.elements.GraphicElement;
import view.elements.GraphicCanvasView;
import clipboard.GraphicElementClipboardManager;
import clipboard.GraphicElementClipboardEntry;

/**
 * Command to copy the current selection to the clipboard.
 * 
 * @author geomaster
 *
 */
public class GraphicElementCopyCommand extends GraphicElementCommand {
	/**
	 * Editor view.
	 */
	private GraphicCanvasView view;

	/**
	 * Constructor.
	 * 
	 * @param element
	 *            Element to act upon
	 * @param view
	 *            Editor view whose selection to copy
	 */
	public GraphicElementCopyCommand(GraphicElement element, GraphicCanvasView view) {
		super(element);
		this.view = view;
	}

	/**
	 * Returns false, as a copy event is not undoable.
	 */
	@Override
	public boolean isUndoable() {
		return false;
	}

	/**
	 * Gets the command name.
	 * 
	 * @return Command name
	 */
	@Override
	protected String getCommandName() {
		return "copy shape(s)";
	}

	/**
	 * Copies the current selection in the editor view to the clipboard.
	 */
	@Override
	public void doCommand() {
		GraphicElementClipboardManager.getInstance().toClipboard(new GraphicElementClipboardEntry(view.getSelection()));
	}

	/**
	 * Does nothing.
	 */
	@Override
	public void undoCommand() {
	}
}
