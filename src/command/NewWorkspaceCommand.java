package command;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JFileChooser;

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
        if(jfc.showOpenDialog(MainView.getInstance())==JFileChooser.APPROVE_OPTION) {
            while (workspace.getChildCount() > 0) {
            	Invoker.getInstance().executeCommand(new DeleteCommand(model, (GNode) workspace.getChildAt(0)));
            }

        	workspace.setWorkspaceFile(jfc.getSelectedFile());
        }
	}

}
