package view.tree;

import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import model.tree.GNode;
import model.tree.GTreeModel;

public class WorkspaceTree extends JTree implements TreeSelectionListener{

	public WorkspaceTree() {
		addTreeSelectionListener(this);
	}
	
	@Override
	public void valueChanged(TreeSelectionEvent e) {
		//System.out.println(e.getPath());
		//this.setSelectionPath(e.getPath());
	}
	
	public void addGNode(GNode node){
		((GTreeModel)getModel()).addGNode(node);
		SwingUtilities.updateComponentTreeUI(this);
	}
	
	public void refresh() {
		SwingUtilities.updateComponentTreeUI(this);
	}

}
