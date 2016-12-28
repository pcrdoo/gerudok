package command;

import model.Model;
import model.tree.GNode;

public class SwitchWorkspaceCommand implements Command {
	private Model model;
	private GNode node;
	
	public SwitchWorkspaceCommand(Model model, GNode node) {
		this.model = model;
		this.node = node;
	}
	
	@Override
	public void execute() {
		// TODO(David): Implementirati komandu.
	}
}
