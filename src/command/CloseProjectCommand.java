package command;

import model.Model;
import model.Project;

public class CloseProjectCommand implements Command {
	
	private Model model;
	private Project project;
	
	public CloseProjectCommand(Model model, Project project) {
		this.model = model;
		this.project = project;
	}
	
	@Override
	public void execute() {
		project.doProjectClose();
		project.setOpened(false);
	}

}
