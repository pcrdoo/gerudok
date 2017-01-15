package command;

import model.Model;
import model.Project;

/**
 * A command that opens a project.
 * 
 * @author Nikola Jovanovic
 *
 */
public class OpenProjectCommand extends Command {

	/**
	 * The project to be opened.
	 */
	private Project project;

	/**
	 * @param model
	 *            the main model
	 * @param project
	 *            the project to be opened
	 */
	public OpenProjectCommand(Model model, Project project) {
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
		project.doProjectOpen();
		project.setOpened(true);
		this.model.getTreeModel().reload();
	}

}
