package command;

import model.Element;
import model.Model;
import model.tree.GNode;

/**
 * A command that adds a new child to a node and updates the tree accordingly.
 * 
 * @author Nikola Jovanovic
 *
 */
public class AddNewChildCommand extends Command {

	/**
	 * The parent node.
	 */
	private GNode node;

	/**
	 * @param model
	 *            the main model
	 * @param node
	 *            the parent node
	 */
	public AddNewChildCommand(Model model, GNode node) {
		this.model = model;
		this.node = node;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see command.Command#doCommand()
	 */
	@Override
	public void doCommand() {
		GNode child = this.node.addNewChild();
		model.getTreeModel().reload();
		Invoker.getInstance().executeCommand(new TreeSelectCommand(model, child));
		if (child instanceof Element) {
			Invoker.getInstance().executeCommand(new ElementEditInitCommand(model, (Element) child));
		}
	}

}