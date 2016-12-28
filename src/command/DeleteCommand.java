package command;

import model.Document;
import model.Model;
import model.tree.GNode;

public class DeleteCommand implements Command {
	private Model model;
	private GNode node;
	
	public DeleteCommand(Model model, GNode node) {
		this.model = model;
		this.node = node;
	}
	
	@Override
	public void execute() {
		GNode parent = (GNode) node.getParent();
		if(node instanceof Document) {
			model.addFreeNode(node);
		}
		node.removeFromParent();
		Invoker.getInstance().executeCommand(new TreeSelectCommand(model, parent));
	}
}