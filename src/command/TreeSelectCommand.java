package command;

import model.Model;
import model.tree.GNode;

public class TreeSelectCommand implements Command {

	private Model model;
	private GNode node;
	
	public TreeSelectCommand(Model model, GNode node) {
		this.model = model;
		this.node = node;
	}
	
	@Override
	public void execute() {
		model.getTreeModel().reload();
		model.doTreeSelection(node);
	}

}
