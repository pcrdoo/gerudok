package command;

import model.Model;
import model.tree.GNode;

public class TreeSelectCommand extends Command {

	private GNode node;
	
	public TreeSelectCommand(Model model, GNode node) {
		this.model = model;
		this.node = node;
	}
	
	@Override
	public void doCommand() {
		model.getTreeModel().reload();
		model.doTreeSelection(node);
	}

}
