package command;

import model.GeRuDocument;
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
				if(child instanceof GeRuDocument) {
					this.model.getFreeNodes().add(child);
					this.model.doReloadFreeNodes();
				}
			}
		}
		node.removeFromParent();
		Invoker.getInstance().executeCommand(new TreeSelectCommand(model, parent));
	}
}