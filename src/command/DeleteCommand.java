package command;

import java.util.Arrays;

import model.GeRuDocument;
import model.Model;
import model.Project;
import model.Workspace;
import model.tree.GNode;

/**
 * A command that deletes a node and updates the tree accordingly.
 * 
 * @author Nikola Jovanovic
 *
 */
public class DeleteCommand extends Command {

	/**
	 * The node to be deleted.
	 */
	private GNode node;

	/**
	 * @param model
	 *            the main model
	 * @param node
	 *            the node to be deleted
	 */
	public DeleteCommand(Model model, GNode node) {
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

		System.out.println("Command " + Arrays.toString(Thread.currentThread().getStackTrace()));
		if (node instanceof Workspace) {
			return;
		}

		GNode parent = (GNode) node.getParent();
		if (node instanceof Project) {
			for (GNode child : node.getChildren()) {
				if (child instanceof GeRuDocument) {
					this.model.getFreeNodes().add(child);
					this.model.doReloadFreeNodes();
				}
			}
		}
		node.removeFromParent();
		model.getTreeModel().reload();
		Invoker.getInstance().executeCommand(new TreeSelectCommand(model, parent));
	}
}