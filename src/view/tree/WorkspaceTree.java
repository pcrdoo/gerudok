package view.tree;

import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import controler.TreeListener;
import gerudok_observer.GObserver;
import gerudok_observer.GObserverNotification;
import model.Model;
import model.tree.GNode;
import model.tree.GTreeModel;

public class WorkspaceTree extends JTree implements GObserver{
	
	Model model;
	
	public WorkspaceTree(Model model) {
		this.model = model;
		this.model.addObserver(this);
		addTreeSelectionListener(new TreeListener(model));
	}
	
	public void addGNode(GNode node){
		((GTreeModel)getModel()).addGNode(node);
		SwingUtilities.updateComponentTreeUI(this);
	}
	
	public void refresh() {
		SwingUtilities.updateComponentTreeUI(this);
	}

	@Override
	public void update(GObserverNotification notification, Object obj) {
		switch(notification) {
		case TREE_SELECT:
			this.setExpandedState(((GNode)obj).getPath(), true);
			this.setSelectionPath(((GNode)obj).getPath());
		}
	}

}
