package command;

import model.Model;
import model.Project;

public class OpenProjectCommand implements Command {
	
	private Model model;
	private Project project;
	
	public OpenProjectCommand(Model model, Project project) {
		this.model = model;
		this.project = project;
	}
	
	@Override
	public void execute() {
		project.doProjectClose();
		project.setOpened(true);
	}

}
