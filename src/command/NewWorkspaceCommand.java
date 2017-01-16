package command;

import java.io.File;
import javax.swing.JFileChooser;

import constants.Constants;
import files.WorkspaceFile;
import model.Model;
import model.Workspace;
import model.tree.GNode;
import view.MainView;

/**
 * A command that create new workspace.
 * 
 * @author Igor Bakovic
 *
 */

public class NewWorkspaceCommand extends Command {

	/**
	 * @param model
	 * 		the main model
	 */
	public NewWorkspaceCommand(Model model) {
		this.model = model;
	}
	
	/** 
	 * 	Create empty workspace in new or existing file with GeRuDok workspace extension (grdw).  
	 * 
	 *  @author Igor Bakovic
	 */
	@Override
	public void doCommand() {
		JFileChooser jfc = new JFileChooser();
        jfc.setFileFilter(new WorkspaceFile());
        jfc.setAcceptAllFileFilterUsed(false);
        
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
