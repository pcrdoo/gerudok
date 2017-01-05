package command;

import model.Document;
import model.Model;
import model.Project;
import model.tree.GNode;

public class DeleteCommand extends Command {

	private GNode node;
	
	public DeleteCommand(Model model, GNode node) {
		this.model = model;
		this.node = node;
	}
	
	@Override
	public void execute() {
		GNode parent = (GNode) node.getParent();
		if(node instanceof Project) {
			for(GNode child : node.getChildren()) {
				this.model.addFreeNode(child);
			}
		}
		node.removeFromParent();
		Invoker.getInstance().executeCommand(new TreeSelectCommand(model, parent));
	}
}