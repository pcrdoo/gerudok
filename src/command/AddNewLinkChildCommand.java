package command;

import model.Model;
import model.tree.GNode;

public class AddNewLinkChildCommand implements Command {
	
	private Model model;
	private GNode node;
	private GNode shared;
	
	public AddNewLinkChildCommand(Model model, GNode node, GNode shared) {
		this.model = model;
		this.node = node;
		this.shared = shared;
	}
	@Override
	public void execute() {
		GNode child = node.addNewLinkChild(shared);
		Invoker.getInstance().executeCommand(new TreeSelectCommand(model, child));
	}

}
