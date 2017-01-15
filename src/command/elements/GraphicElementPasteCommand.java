package command.elements;
import model.elements.GraphicElement;
import view.elements.GraphicCanvasView;
import clipboard.GraphicElementClipboardManager;
import clipboard.GraphicElementClipboardEntry;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.elements.GraphicShape;
import clipboard.ClipboardException;
import clipboard.SerializationDeserializationException;

public class GraphicElementPasteCommand extends GraphicElementCommand {
	private final static int CASCADE_PIXELS_PER_PASTE = 10;
	private GraphicCanvasView view;
	
	public GraphicElementPasteCommand(GraphicElement element, GraphicCanvasView view) {
		super(element);
		this.view = view;
	}
	
	@Override
	public boolean isUndoable() {
		return false;
	}
	
	@Override
	public void doCommand() {
		try {
			GraphicElementClipboardEntry e = GraphicElementClipboardManager.getInstance().fromClipboard();
			e.onPasted();
			
			ArrayList<GraphicElementAddShapeCommand> list = new ArrayList<>();
			for (GraphicShape s: e.getShapes()) {
				int offset = CASCADE_PIXELS_PER_PASTE * e.getPastedCount();
				GraphicShape newShape = s.clone();
				newShape.translate(offset, offset);
				list.add(new GraphicElementAddShapeCommand(element, newShape));
			}
			GraphicElementCompositeCommand command = new GraphicElementCompositeCommand(element, list);

			GraphicElementInvoker.getInstance().executeCommand(command);
			view.deselectAll();
			for (GraphicElementAddShapeCommand cmd: list) {
				view.select(cmd.getShape());
			}
		} catch (SerializationDeserializationException e) {
			JOptionPane.showMessageDialog(null, "Nije moguće nalepiti ono što je trenutno na clipboard-u. Proverite da li je prethodno isečen ili kopiran sadržaj iz GeRuDok-a.");
			e.printStackTrace();
		} catch (ClipboardException e) {
			JOptionPane.showMessageDialog(null, "Greška prilikom pristupanja clipboard-u.");
			e.printStackTrace();
		}
	}
	
	@Override
	public void undoCommand() { }
}
