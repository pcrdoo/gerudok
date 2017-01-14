package model.tree;

import java.io.Serializable;

import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

import model.Workspace;

public class GTreeModel extends DefaultTreeModel implements Serializable{

	public GTreeModel() {
		super(Workspace.getInstance());
		// TODO
	}
}
