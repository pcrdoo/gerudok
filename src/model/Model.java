
package model;

import java.util.*;

import javax.swing.tree.TreePath;

import gerudok_observer.GObservable;
import gerudok_observer.GObserver;
import gerudok_observer.GObserverList;
import gerudok_observer.GNotification;
import model.tree.GNode;
import model.tree.GTreeModel;

public class Model implements GObservable{
	private GTreeModel treeModel;
	private GObserverList observerList;
	private List<GNode> freeNodes;
	
	public Model() {
		this.observerList = new GObserverList();
		this.freeNodes = new ArrayList<>();
	}
	
	public void addFreeNode(GNode node) {
		this.freeNodes.add(node);
	}
	
	public List<GNode> getFreeNodes() {
		return this.freeNodes;
	}
	
	public void setTreeModel(GTreeModel treeModel) {
		this.treeModel = treeModel;
	}
	
	public GTreeModel getTreeModel() {
		return this.treeModel;
	}
	
	// Desktop -> Tree
	public void doTreeSelection(GNode node) {
		//TODO pitati Davida da presudi dal ovo da bude genericka funkcija
		this.observerList.notifyObservers(GNotification.TREE_SELECT, node);
	}
	
	public void doTreeRename(GNode node) {
		this.observerList.notifyObservers(GNotification.TREE_RENAME, node);
	}

	// Tree -> Desktop
	public void doDesktopSelection(TreePath path) {
		this.observerList.notifyObservers(GNotification.DESKTOP_SELECT, path);
	}
	
	@Override
	public void addObserver(GObserver obs) {
		this.observerList.addObserver(obs);
	}
	
}