package command;

import model.Model;
import model.ElementContainer;
import model.ElementType;
import model.Element;
import model.tree.GNode;

public class AddNewChildElementCommand extends Command {
	private ElementContainer node;
	private ElementType childType;
	
	public AddNewChildElementCommand(Model model, ElementContainer node, ElementType type) {
		this.model = model;
		this.node = node;
		this.childType = type;
	}
	
	@Override
	public void doCommand() {
		try {
			Element child = (Element) node.addNewChild(childType);
			model.getTreeModel().reload();
			Invoker.getInstance().executeCommand(new TreeSelectCommand(model, child));
			Invoker.getInstance().executeCommand(new ElementEditInitCommand(model, child));
		} catch (Exception e) {
			System.out.println("DavisException: violated data type invariant; contact your local Davis for help and guidance");
			e.printStackTrace();
		}
	}
}
