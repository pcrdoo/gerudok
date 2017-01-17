package command;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import files.ProjectFile;
import model.Model;
import model.Project;
import model.Workspace;
import model.tree.GNode;
import view.MainView;

/**
 * A command that imports existing project into workspace.
 * 
 * @author Igor Bakovic
 *
 */

public class LoadProjectCommand extends Command {

	/**
	 * The string reference to location of project to be imported.
	 */
	private String loadFilePath;

	/**
	 * @param model
	 *            the main model
	 */
	public LoadProjectCommand(Model model) {
		this.model = model;
		this.loadFilePath = null;
	}

	/**
	 * @param model
	 *            the main model
	 * @param loadFilePath
	 *            location of project
	 */
	public LoadProjectCommand(Model model, String loadFilePath) {
		this.model = model;
		this.loadFilePath = loadFilePath;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see command.Command#doCommand()
	 */
	@Override
	public void doCommand() {
		if (loadFilePath == null) {
			JFileChooser jfc = new JFileChooser();
			jfc.setFileFilter(new ProjectFile());

			if (jfc.showOpenDialog(MainView.getInstance()) == JFileChooser.APPROVE_OPTION) {
				loadProject(jfc.getSelectedFile());
			}
		} else {
			File file = new File(loadFilePath);
			if (file.exists()) {
				loadProject(file);
			}
		}

	}

	/**
	 * @param file
	 *            the project to be imported
	 * 
	 */
	private void loadProject(File file) {
		/**
		 * Checks if the project allredy exist in the worksace. In that case
		 * show message: "Project already opened!"
		 * 
		 * @author Igor Bakovic
		 */
		for (GNode p : Workspace.getInstance().getChildren()) {
			if (file.getAbsolutePath().equals(((Project) p).getProjectFile().getAbsolutePath())) {
				JOptionPane.showMessageDialog(MainView.getInstance(), "Project already opened!");
				return;
			}
		}

		/**
		 * Import project into workspace
		 * 
		 * @author Igor Bakovic
		 */
		try {
			ObjectInputStream os = new ObjectInputStream(new FileInputStream(file));

			Project p = null;

			try {
				p = (Project) os.readObject();
				p.initObserverList();
				p.setProjectFile(file);
			} catch (ClassNotFoundException ee) {
				JOptionPane.showMessageDialog(MainView.getInstance(), "Erorr in file.");
			}

			Workspace.getInstance().addChild(p);
			model.getTreeModel().reload();
			Invoker.getInstance().executeCommand(new TreeSelectCommand(model, p));

			os.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
