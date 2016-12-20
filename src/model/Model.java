
package model;

import java.util.*;

import model.tree.GTreeModel;

public class Model extends Observable {
	private Workspace workspace;
	private GTreeModel treeModel;
	
	public void setTreeModel(GTreeModel treeModel) {
		this.treeModel = treeModel;
	}
	
	public GTreeModel getTreeModel() {
		return this.treeModel;
	}
	
	public Workspace getWorkspace() {
		return Workspace.getInstance();
	}
	
}