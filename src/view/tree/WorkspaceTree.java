package view.tree;

import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

import controller.TreeListener;
import gerudok_observer.GObserver;
import gerudok_observer.GObserverNotification;
import model.Model;
import model.Project;
import model.tree.GNode;
import model.tree.GTreeModel;

public class WorkspaceTree extends JTree implements GObserver{
	
	Model model;
	
	public WorkspaceTree(Model model) {
		this.model = model;
		this.model.addObserver(this);
		addTreeSelectionListener(new TreeListener(model));
	}

	protected void setExpandedState(TreePath path, boolean state) {
		Object o = path.getLastPathComponent();
		if(o instanceof Project && !((Project)o).isOpened()) {
			
		} else
			super.setExpandedState(path, state);
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
