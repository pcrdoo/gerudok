package command;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;

import constants.Constants;
import files.ProjectFile;
import model.Model;
import model.Project;
import view.MainView;

public class SaveAsProjectCommand extends Command {
	
	public SaveAsProjectCommand(Model model) {
		this.model = model;
	}  
	
	@Override
	public void doCommand() {
		Object object = model.getSelectedObject();
		Project project = (object instanceof Project) ? (Project)object : null;
		JFileChooser jfc = new JFileChooser();
		jfc.setFileFilter(new ProjectFile());
		if (project != null) {
			File projectFile;
			if (jfc.showSaveDialog(MainView.getInstance()) == JFileChooser.APPROVE_OPTION)
			{
				projectFile = jfc.getSelectedFile();
				String o = projectFile.getPath();
				if (!o.endsWith(Constants.PROJECT_EXT)) {
					o += Constants.PROJECT_EXT;
					projectFile.delete();
					projectFile = new File(o);
				}
			} else {
				return;
			}
			ObjectOutputStream os;
			try {
				os = new ObjectOutputStream(new FileOutputStream(projectFile));
				os.writeObject(project);
				project.setProjectFile(projectFile);
				os.close();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
	}
}
