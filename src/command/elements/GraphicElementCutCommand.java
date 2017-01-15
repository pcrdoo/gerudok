package command.elements;

import java.util.ArrayList;

import model.elements.GraphicShape;
import clipboard.GraphicElementClipboardEntry;
import clipboard.GraphicElementClipboardManager;
import model.elements.GraphicElement;
import view.elements.GraphicCanvasView;

public class GraphicElementCutCommand extends GraphicElementCommand{
	private GraphicCanvasView view;
	
	public GraphicElementCutCommand(GraphicElement element, GraphicCanvasView view) {
		super(element);
		this.view = view;
	}
	
	@Override
	public boolean isUndoable() {
		return false;
	}
	
	@Override
	public void doCommand() {
		ArrayList<GraphicElementCommand> list = new ArrayList<>();
		list.add(new GraphicElementCopyCommand(element, view));
		for (GraphicShape s: view.getSelection()) {
			list.add(new GraphicElementRemoveShapeCommand(element, s));
		}
		GraphicElementInvoker.getInstance().executeCommand(new GraphicElementCompositeCommand(element, list));
	}
	
	@Override
	public void undoCommand() { }
}