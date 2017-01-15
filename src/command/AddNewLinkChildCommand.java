package command;

import model.Model;
import model.tree.GNode;

/**
 * A command that adds a new link child to a node and updates the tree
 * accordingly.
 * 
 * @author Nikola Jovanovic
 *
 */
public class AddNewLinkChildCommand extends Command {

	/**
	 * The parent node.
	 */
	private GNode node;
	/**
	 * The node that the new GLink node points to.
	 */
	private GNode shared;

	/**
	 * @param model
	 *            the main model
	 * @param node
	 *            the parent node
	 * @param shared
	 *            the node that the new link node points to
	 */
	public AddNewLinkChildCommand(Model model, GNode node, GNode shared) {
		this.model = model;
		this.node = node;
		this.shared = shared;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see command.Command#doCommand()
	 */
	@Override
	public void doCommand() {
		GNode child = node.addNewLinkChild(shared);
		model.getTreeModel().reload();
		Invoker.getInstance().executeCommand(new TreeSelectCommand(model, child));
	}

}
