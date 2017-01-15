package command.elements;

import java.util.ArrayList;

import model.elements.GraphicShape;
import clipboard.GraphicElementClipboardEntry;
import clipboard.GraphicElementClipboardManager;
import clipboard.GraphicElementClipboardEntryListener;
import model.elements.GraphicElement;
import view.elements.GraphicCanvasView;

public class GraphicElementCutCommand extends GraphicElementCommand implements GraphicElementClipboardEntryListener {
	private GraphicCanvasView view;
	private GraphicElementClipboardEntry entry = null;
	private boolean wasPasted = false;
	

	public GraphicElementCutCommand(GraphicElement element, GraphicCanvasView view) {
		super(element);
		this.view = view;
	}
	
	@Override
	public void doCommand() {
		entry = new GraphicElementClipboardEntry(view.getSelection());
		entry.registerEntryListener(this);
		GraphicElementClipboardManager.getInstance().toClipboard(entry);
		view.cutSelection();
	}
	
	@Override
	public boolean isUndoable() {
		return !wasPasted;
	}
	
	@Override
	public boolean isRedoable() {
		return true;
	}
	
	@Override
	public void undoCommand() {
		view.clearCutShapes();
		entry.unregisterEntryListener(this);
	}
	
	public void onEntryEvicted()
	{
		view.clearCutShapes();
	}
	
	public void onEntryPasted()
	{
		ArrayList<GraphicElementRemoveShapeCommand> list = new ArrayList<>();
		for (GraphicShape s: view.getSelection()) {
			list.add(new GraphicElementRemoveShapeCommand(element, s));
		}
		GraphicElementInvoker.getInstance().executeCommand(new GraphicElementCompositeCommand(element, list));
		view.clearCutShapes();
		
		entry.unregisterEntryListener(this);
		wasPasted = true;
	}
}