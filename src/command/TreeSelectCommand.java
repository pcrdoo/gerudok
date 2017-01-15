package command;

import model.Model;
import model.tree.GNode;

/**
 * A command that propagates a selection event to the tree.
 * 
 * @author Nikola Jovanovic
 *
 */
public class TreeSelectCommand extends Command {

	/**
	 * Newly selected node.
	 */
	private GNode node;

	/**
	 * @param model
	 *            the main model
	 * @param node
	 *            newly selected node
	 */
	public TreeSelectCommand(Model model, GNode node) {
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
		model.doTreeSelection(node);
	}

}
