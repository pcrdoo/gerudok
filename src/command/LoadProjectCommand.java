package command;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import files.ProjectFile;
import model.Element;
import model.GeRuDocument;
import model.Model;
import model.Project;
import model.Workspace;
import model.tree.GNode;
import view.GeRuDocumentView;
import view.MainView;
import view.ProjectView;

public class LoadProjectCommand extends Command {
	
	private String loadFilePath;
	
	public LoadProjectCommand(Model model) {
		this.model = model;
		this.loadFilePath = null;
	}
	
	public LoadProjectCommand(Model model, String loadFilePath) {
		this.model = model;
		this.loadFilePath = loadFilePath;
	}
	
	@Override
	public void doCommand() {
		if (loadFilePath == null) {
			JFileChooser jfc = new JFileChooser();
	        jfc.setFileFilter(new ProjectFile());
	        
	        if(jfc.showOpenDialog(MainView.getInstance())==JFileChooser.APPROVE_OPTION) {
	            loadProject(jfc.getSelectedFile());
	        }
		} else {
			File file = new File(loadFilePath);
			if (file.exists()) {
				loadProject(file);
			}
		}
		
	}
	
	private void loadProject(File file) {
		// Check if this project exists
		for (GNode p : Workspace.getInstance().getChildren()) {
			if (file.getAbsolutePath().equals(((Project) p).getProjectFile().getAbsolutePath())) {
				JOptionPane.showMessageDialog(MainView.getInstance(), "Project already opened!");
				return;
			}
		}
		
		try {
            ObjectInputStream os = new ObjectInputStream(new FileInputStream(file));

            Project p=null;

            try {
                p = (Project) os.readObject();
                p.initObserverList();
                p.setProjectFile(file);
            } catch (ClassNotFoundException ee) {
                JOptionPane.showMessageDialog(MainView.getInstance(),"Erorr in file.");
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


