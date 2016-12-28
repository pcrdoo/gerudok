package command;

import gerudok_observer.GObserverNotification;
import model.Document;
import model.Model;
import model.tree.GNode;

public class AddNewChildCommand extends Command {

	private GNode node;
	
	public AddNewChildCommand(Model model, GNode node) {
		this.model = model;
		this.node = node;
	}
	
	@Override
	public void execute() {
		GNode child = this.node.addNewChild();
		Invoker.getInstance().executeCommand(new TreeSelectCommand(model, child));
	}

}