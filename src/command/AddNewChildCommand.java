package command;

import gerudok_observer.GObserverNotification;
import model.GeRuDocument;
import model.Model;
import model.Element;
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
		if (child instanceof Element) {
			Invoker.getInstance().executeCommand(new ElementEditInitCommand(model, (Element) child));
		}
	}

}