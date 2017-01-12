package command;
import model.GraphicElement;
import model.GraphicShape;
import model.Model;
import java.util.*;

public class GraphicElementEditCommitCommand extends Command {
	private GraphicElement element;
	private List<GraphicShape> newShapes;
	
	public GraphicElementEditCommitCommand(Model model, GraphicElement element, List<GraphicShape> newShapes) {
		this.newShapes = newShapes;
		this.element = element;
		this.model = model;
	}
	
	@Override
	public void execute() {
		element.setShapes(newShapes);
	}
}
