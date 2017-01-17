package view.tree;

import javax.swing.JTree;
import javax.swing.tree.TreePath;

import controller.TreeListener;
import gerudok_observer.GObserver;
import gerudok_observer.GNotification;
import model.Model;
import model.Project;
import model.tree.GNode;

/**
 * The graphic representation of all the GNodes and their hierarchy.
 * 
 * @author Ognjen Djuricic
 *
 */
public class WorkspaceTree extends JTree implements GObserver {
	/**
	 * Version UID for serialization.
	 */
	final static long serialVersionUID = 1;

	/**
	 * Reference to the main model.
	 */
	Model model;

	/**
	 * Constructor that sets the model.
	 * 
	 * @param model
	 *            The main model.
	 */
	public WorkspaceTree(Model model) {
		this.model = model;
		this.model.addObserver(this);
		addTreeSelectionListener(new TreeListener(model));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JTree#setExpandedState(javax.swing.tree.TreePath,
	 * boolean)
	 */
	@Override
	protected void setExpandedState(TreePath path, boolean state) {
		Object o = path.getLastPathComponent();
		if (o instanceof Project && !((Project) o).isOpened()) {

		} else
			super.setExpandedState(path, state);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gerudok_observer.GObserver#update(gerudok_observer.GNotification,
	 * java.lang.Object)
	 */
	@Override
	public void update(GNotification notification, Object obj) {
		switch (notification) {
		case TREE_SELECT:
			TreePath path = ((GNode) obj).getPath();
			if (path != model.getSelectedPath()) {
				this.setSelectionPath(path);
			}
			break;
		case TREE_RENAME:
			this.startEditingAtPath(((GNode) obj).getPath());
			break;

		default:
			break;
		}
	}
}
