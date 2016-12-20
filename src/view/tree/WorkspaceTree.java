package view.tree;

import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import gerudok_observer.GObserver;
import gerudok_observer.GObserverNotification;
import model.Model;
import model.tree.GNode;
import model.tree.GTreeModel;

public class WorkspaceTree extends JTree implements TreeSelectionListener, GObserver{
	
	Model model;
	
	public WorkspaceTree(Model model) {
		addTreeSelectionListener(this);
		this.model = model;
		this.model.addObserver(this);
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

	@Override
	public void update(GObserverNotification notification, Object obj) {
		switch(notification) {
		case SELECT_NODE:
			//this.setExpandedState(path, state);
		}
	}

}
