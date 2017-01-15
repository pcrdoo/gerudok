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

/**
 * Command that pastes the current clipboard contents to a graphic element.
 * 
 * @author geomaster
 *
 */
public class GraphicElementPasteCommand extends GraphicElementCommand {
	/**
	 * How many pixels in each direction to translate successive pastes of the
	 * same element.
	 */
	private final static int CASCADE_PIXELS_PER_PASTE = 10;

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
	 *            Editor view to use
	 */
	public GraphicElementPasteCommand(GraphicElement element, GraphicCanvasView view) {
		super(element);
		this.view = view;
	}

	/**
	 * Returns false, as the paste command itself executes other undoable
	 * commands, but is itself a meta-command.
	 */
	@Override
	public boolean isUndoable() {
		return false;
	}

	/**
	 * Retrieves shapes from the clipboard, adds them to the element and selects
	 * them in the editor view.
	 */
	@Override
	public void doCommand() {
		try {
			GraphicElementClipboardEntry e = GraphicElementClipboardManager.getInstance().fromClipboard();

			ArrayList<GraphicElementAddShapeCommand> list = new ArrayList<>();
			for (GraphicShape s : e.getShapes()) {
				int offset = CASCADE_PIXELS_PER_PASTE * e.getPastedCount();
				GraphicShape newShape = s.clone();
				newShape.translate(offset, offset);
				list.add(new GraphicElementAddShapeCommand(element, newShape));
			}
			GraphicElementCompositeCommand command = new GraphicElementCompositeCommand(element, list);

			GraphicElementInvoker.getInstance().executeCommand(command);
			view.deselectAll();
			for (GraphicElementAddShapeCommand cmd : list) {
				view.select(cmd.getShape());
			}
			System.out.println("Added " + list.size() + " shapes");
		} catch (SerializationDeserializationException e) {
			JOptionPane.showMessageDialog(null,
					"Nije moguće nalepiti ono što je trenutno na clipboard-u. Proverite da li je prethodno isečen ili kopiran sadržaj iz GeRuDok-a.");
			e.printStackTrace();
		} catch (ClipboardException e) {
			JOptionPane.showMessageDialog(null, "Greška prilikom pristupanja clipboard-u.");
			e.printStackTrace();
		}
	}

	/**
	 * Gets the command name.
	 * 
	 * @return Command name
	 */
	@Override
	public String getCommandName() {
		return "paste shape(s)";
	}

	/**
	 * Does nothing.
	 */
	@Override
	public void undoCommand() {
	}
}
