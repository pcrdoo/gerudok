package command.elements;
import model.Model;

import model.elements.GraphicElement;

import command.Command;

public class GraphicElementEditCommitCommand extends Command {
	private GraphicElement element;
	
	public GraphicElementEditCommitCommand(Model model, GraphicElement element) {
		this.model = model;
		this.element = element;
	}
	
	@Override
	public void doCommand() {
		element.notifyShapesChanged();
	}
}
