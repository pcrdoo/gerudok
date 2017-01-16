package command;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JFileChooser;

import constants.Constants;
import files.WorkspaceFile;
import model.Model;
import model.Workspace;
import model.tree.GNode;
import view.MainView;

public class NewWorkspaceCommand extends Command {

	public NewWorkspaceCommand(Model model) {
		this.model = model;
	}
	
	@Override
	public void doCommand() {
		JFileChooser jfc = new JFileChooser();
        jfc.setFileFilter(new WorkspaceFile());
        
        Workspace workspace = Workspace.getInstance();
        if(jfc.showSaveDialog(MainView.getInstance())==JFileChooser.APPROVE_OPTION) {
            while (workspace.getChildCount() > 0) {
            	Invoker.getInstance().executeCommand(new DeleteCommand(model, (GNode) workspace.getChildAt(0)));
            }
            File workspaceFile = jfc.getSelectedFile();
			String o = workspaceFile.getPath();
			if (!o.endsWith(Constants.WORKSPACE_EXT)) {
				o += Constants.WORKSPACE_EXT;
				workspaceFile.delete();
				workspaceFile = new File(o);
			}
        	workspace.setWorkspaceFile(workspaceFile);
        }
	}

}
