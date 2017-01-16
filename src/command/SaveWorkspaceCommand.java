package command;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import constants.Constants;
import files.ProjectFile;
import files.WorkspaceFile;
import model.Model;
import model.Project;
import model.Workspace;
import model.tree.GNode;
import view.MainView;
/**
 * A command that save workspace.
 * 
 * @author Igor Bakovic
 *
 */
public class SaveWorkspaceCommand extends Command {

	/**
	 * @param model
	 * 		the main model
	 */
	public SaveWorkspaceCommand(Model model) {
		this.model = model;
	}
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see command.Command#doCommand()
	 */
	@Override
	public void doCommand() {
		Workspace workspace = Workspace.getInstance();
		JFileChooser jfc = new JFileChooser();
		jfc.setFileFilter(new WorkspaceFile());
		jfc.setAcceptAllFileFilterUsed(false);
		if (workspace != null) {
			File workspaceFile = workspace.getWorkspaceFile();
			if (workspaceFile == null) {
				if (jfc.showSaveDialog(MainView.getInstance()) == JFileChooser.APPROVE_OPTION)
				{
					workspaceFile = jfc.getSelectedFile();
					String o = workspaceFile.getPath();
					if (!o.endsWith(Constants.WORKSPACE_EXT)) {
						o += Constants.WORKSPACE_EXT;
						workspaceFile.delete();
						workspaceFile = new File(o);
					}
				} else {
					return;
				}
			}
			
			// Save all projects
			for (GNode p : workspace.getChildren()) {
				if (((Project) p).getProjectFile() == null) {
					JOptionPane.showMessageDialog(MainView.getInstance(), "Unsaved project " + p.getName() + ". You must save it first.");
				}
				Invoker.getInstance().executeCommand(new SaveProjectCommand(model, (Project) p));
			}
			
			BufferedWriter os;
			try {
				os = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(workspaceFile)));
				for (GNode p : workspace.getChildren()) {
					if (((Project) p).getProjectFile() != null) {
						os.write(((Project) p).getProjectFile().getAbsolutePath());
					}
					os.newLine();
				}
				workspace.setWorkspaceFile(workspaceFile);
				os.close();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
	}
}
