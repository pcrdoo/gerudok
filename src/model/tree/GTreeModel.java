package model.tree;

import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

import model.Workspace;

public class GTreeModel extends DefaultTreeModel{

	public GTreeModel() {
		super(Workspace.getInstance());
		// TODO
	}
}
