package command;

import model.Model;
import model.Project;

/**
 * A command that closes a project.
 * 
 * @author Nikola Jovanovic
 *
 */
public class CloseProjectCommand extends Command {

	/**
	 * The project to be closed.
	 */
	private Project project;

	/**
	 * @param model
	 *            the main model
	 * @param project
	 *            the project to be closed
	 */
	public CloseProjectCommand(Model model, Project project) {
		this.model = model;
		this.project = project;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see command.Command#doCommand()
	 */
	@Override
	public void doCommand() {
		project.doProjectClose();
		project.setOpened(false);
		this.model.getTreeModel().reload();
	}

}
