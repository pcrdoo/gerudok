package command;

import model.Model;

public abstract class Command {
	protected Model model;
	public abstract void doCommand();
}
