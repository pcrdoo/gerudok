package command;

import model.Model;
import model.tree.GNode;

public class AddNewLinkChildCommand extends Command {
	
	private GNode node;
	private GNode shared;
	
	public AddNewLinkChildCommand(Model model, GNode node, GNode shared) {
		this.model = model;
		this.node = node;
		this.shared = shared;
	}
	@Override
	public void doCommand() {
		GNode child = node.addNewLinkChild(shared);
		Invoker.getInstance().executeCommand(new TreeSelectCommand(model, child));
	}

}
