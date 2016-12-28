package command;

import model.Model;
import model.tree.GNode;

public class RenameCommand implements Command {
	
	private Model model;
	private GNode node;
	
	public RenameCommand(Model model, GNode node) {
		this.model = model;
		this.node = node;
	}
	
	@Override
	public void execute() {
		model.doTreeRename(node);
	}

}
