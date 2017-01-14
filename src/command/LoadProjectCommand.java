package command;

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
	
	public LoadProjectCommand(Model model) {
		this.model = model;
	}
	
	@Override
	public void execute() {
		JFileChooser jfc = new JFileChooser();
        jfc.setFileFilter(new ProjectFile());

        if(jfc.showOpenDialog(MainView.getInstance())==JFileChooser.APPROVE_OPTION) {
            try {
                ObjectInputStream os = new ObjectInputStream(new FileInputStream(jfc.getSelectedFile()));

                Project p=null;

                try {
                    p = (Project) os.readObject();
                    p.initObserverList();
                    p.setProjectFile(jfc.getSelectedFile());
                } catch (ClassNotFoundException ee) {
                    JOptionPane.showMessageDialog(MainView.getInstance(),"Erorr in file.");
                }

                Workspace.getInstance().addLoadedProject(p);
                Invoker.getInstance().executeCommand(new TreeSelectCommand(model, p));

                os.close();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
	}
}
