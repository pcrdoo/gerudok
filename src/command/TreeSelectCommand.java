package command;

import model.Model;
import model.tree.GNode;

/**
 * @author Random
 *
 */
public class TreeSelectCommand extends Command {

	/**
	 * 
	 */
	private GNode node;
	
	/**
	 * @param model
	 * @param node
	 */
	public TreeSelectCommand(Model model, GNode node) {
		this.model = model;
		this.node = node;
	}
	
	/* (non-Javadoc)
	 * @see command.Command#doCommand()
	 */
	@Override
	public void doCommand() {
		model.doTreeSelection(node);
	}

}
