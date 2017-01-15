package command;

import model.Model;
import model.tree.GNode;

public class SwitchWorkspaceCommand extends Command {

	private GNode node;
	
	public SwitchWorkspaceCommand(Model model, GNode node) {
		this.model = model;
		this.node = node;
	}
	
	@Override
	public void doCommand() {
		// TODO(David): Implementirati komandu.
	}
}
