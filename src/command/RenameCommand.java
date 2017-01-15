package command;

import model.Model;
import model.tree.GNode;

public class RenameCommand extends Command {
	
	private GNode node;
	
	public RenameCommand(Model model, GNode node) {
		this.model = model;
		this.node = node;
	}
	
	@Override
	public void doCommand() {
		model.doTreeRename(node);
	}

}
