
package model;

import java.io.Serializable;
import java.util.*;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import gerudok_observer.GObservable;
import gerudok_observer.GObserver;
import gerudok_observer.GObserverList;
import gerudok_observer.GNotification;
import model.tree.GNode;

/**
 * The main model class in the MVC architecture. Used to propagate and store
 * data.
 * 
 * @author Ognjen Djuricic
 *
 */
public class Model implements GObservable, Serializable {
	/**
	 * The model of the WorkspaceTree. Used to refresh the tree.
	 */
	private DefaultTreeModel treeModel;
	/**
	 * GObservers that react to any data change.
	 */
	private GObserverList observerList;
	/**
	 * GNodes that were removed from the WorkspaceTree, but were not deleted.
	 * They are stored here while they wait to be put back in the tree.
	 */
	private List<GNode> freeNodes;

	/**
	 * Default constructor.
	 */
	public Model() {
		this.observerList = new GObserverList();
		this.freeNodes = new ArrayList<>();
	}

	/**
	 * Gets the free nodes.
	 * 
	 * @return The free nodes.
	 */
	public List<GNode> getFreeNodes() {
		return this.freeNodes;
	}

	/**
	 * Checks if there are any free nodes.
	 * 
	 * @return Are there any free nodes.
	 */
	public boolean hasFreeNodes() {
		return !this.freeNodes.isEmpty();
	}

	/**
	 * Sets the treeModel.
	 * 
	 * @param treeModel
	 *            The treeModel.
	 */
	public void setTreeModel(DefaultTreeModel treeModel) {
		this.treeModel = treeModel;
	}

	/**
	 * Gets the treeModel.
	 * 
	 * @return The treeModel.
	 */
	public DefaultTreeModel getTreeModel() {
		return this.treeModel;
	}

	/**
	 * Notifies the WorkspaceTree to select a node.
	 * 
	 * @param node
	 *            The GNode to be selected.
	 */
	public void doTreeSelection(GNode node) {
		// TODO pitati Davida da presudi dal ovo da bude genericka funkcija
		this.observerList.notifyObservers(GNotification.TREE_SELECT, node);
	}

	/**
	 * Notifies the WorkspaceTree to rename a node.
	 * 
	 * @param node
	 *            The GNode to be renamed.
	 */
	public void doTreeRename(GNode node) {
		this.observerList.notifyObservers(GNotification.TREE_RENAME, node);
	}

	/**
	 * Notifies the DesktopView to put in focus some of its components.
	 * 
	 * @param path
	 *            The TreePath that contains the information about the
	 *            components.
	 */
	public void doDesktopSelection(TreePath path) {
		this.observerList.notifyObservers(GNotification.DESKTOP_SELECT, path);
	}

	/**
	 * Notifies the TreeView to refresh the free node representation.
	 */
	public void doReloadFreeNodes() {
		this.observerList.notifyObservers(GNotification.FREE_NODES_CHANGED, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gerudok_observer.GObservable#addObserver(gerudok_observer.GObserver)
	 */
	@Override
	public void addObserver(GObserver obs) {
		this.observerList.addObserver(obs);
	}
}