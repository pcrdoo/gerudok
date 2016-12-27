
package model;

import java.util.*;

import javax.swing.tree.TreePath;

import gerudok_observer.GObservable;
import gerudok_observer.GObserver;
import gerudok_observer.GObserverList;
import gerudok_observer.GObserverNotification;
import model.tree.GNode;
import model.tree.GTreeModel;

public class Model implements GObservable{
	private GTreeModel treeModel;
	private GObserverList observerList;
	
	public Model() {
		this.observerList = new GObserverList();
	}
	
	public void setTreeModel(GTreeModel treeModel) {
		this.treeModel = treeModel;
	}
	
	public GTreeModel getTreeModel() {
		return this.treeModel;
	}
	
	public Workspace getWorkspace() {
		return Workspace.getInstance();
	}
	
	// Desktop -> Tree
	public void doTreeSelection(GNode node) {
		//TODO pitati Davida da presudi dal ovo da bude genericka funkcija
		this.observerList.notifyObservers(GObserverNotification.TREE_SELECT, node);
	}
	
	public void doTreeRename(GNode node) {
		this.observerList.notifyObservers(GObserverNotification.TREE_RENAME, node);
	}

	@Override
	public void addObserver(GObserver obs) {
		this.observerList.addObserver(obs);
	}

	// Tree -> Desktop
	public void doDesktopSelection(TreePath path) {
		this.observerList.notifyObservers(GObserverNotification.DESKTOP_SELECT, path);
	}
	
}