package command;

import model.Model;
import model.tree.GNode;

/**
 * A command that changes the name of a node and updates the tree accordingly.
 * 
 * @author Nikola Jovanovic
 *
 */
public class RenameCommand extends Command {

	/**
	 * The node to be renamed.
	 */
	private GNode node;

	/**
	 * @param model
	 *            the main model
	 * @param node
	 *            the node to be renamed
	 */
	public RenameCommand(Model model, GNode node) {
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
		model.doTreeRename(node);
	}

}
