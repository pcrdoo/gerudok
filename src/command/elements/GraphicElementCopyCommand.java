package command.elements;
import model.elements.GraphicElement;
import view.elements.GraphicCanvasView;
import clipboard.GraphicElementClipboardManager;
import clipboard.GraphicElementClipboardEntry;

public class GraphicElementCopyCommand extends GraphicElementCommand {
	private GraphicCanvasView view;
	
	public GraphicElementCopyCommand(GraphicElement element, GraphicCanvasView view) {
		super(element);
		this.view = view;
	}
	
	@Override
	public boolean isUndoable() {
		return false;
	}
	
	@Override
	public void doCommand() {
		GraphicElementClipboardManager.getInstance().toClipboard(new GraphicElementClipboardEntry(view.getSelection()));
	}
	
	@Override
	public void undoCommand() { }
}
