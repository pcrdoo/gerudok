package command.elements;
import model.Model;
import model.elements.GraphicElement;
import model.elements.GraphicShape;

import java.util.*;

import command.Command;

public class GraphicElementEditCancelCommand extends Command {
	private GraphicElement element;
	private List<GraphicShape> oldShapes;
	
	public GraphicElementEditCancelCommand(Model model, GraphicElement element, List<GraphicShape> oldShapes) {
		this.oldShapes = oldShapes;
		this.element = element;
		this.model = model;
	}
	
	@Override
	public void doCommand() {
		element.setShapes(oldShapes);
	}
}
