package command;

import model.Model;
import model.Project;

public class OpenProjectCommand extends Command {
	
	private Project project;
	
	public OpenProjectCommand(Model model, Project project) {
		this.model = model;
		this.project = project;
	}
	
	@Override
	public void execute() {
		project.doProjectClose();
		project.setOpened(true);
		this.model.getTreeModel().reload();
	}

}
