package model.tree;

import java.awt.Component;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import gerudok_observer.GObservable;
import gerudok_observer.GObserver;
import gerudok_observer.GNotification;
import gerudok_observer.GObserverList;
import model.Project;

/**
 * Represents a node in the WorkspaceTree with all needed functionalities.
 * 
 * @author Ognjen Djuricic
 *
 */
public class GNode implements MutableTreeNode, GObservable, Serializable {

	/**
	 * Name of the node.
	 */
	private String name;
	/**
	 * Parent of the node in the tree.
	 */
	protected GNode parent;
	/**
	 * Children of the node in the tree.
	 */
	protected ArrayList<GNode> children;
	/**
	 * GObservers that react to node changes. Usually the nodes graphic
	 * representation.
	 */
	protected transient GObserverList observerList;
	/**
	 * Hyperlinks of the node.
	 */
	private List<GLink> links;

	/**
	 * Default constructor.
	 */
	public GNode() {
		this.parent = null;
		this.children = new ArrayList<>();
		this.observerList = new GObserverList();
		this.links = new ArrayList<>();
		this.setName(null);
	}

	/**
	 * Construction that sets the name.
	 * 
	 * @param name
	 *            Name of the GNode.
	 */
	public GNode(String name) {
		this();
		this.setName(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gerudok_observer.GObservable#addObserver(gerudok_observer.GObserver)
	 */
	@Override
	public void addObserver(GObserver observer) {
		observerList.addObserver(observer);
	}

	/**
	 * Adds a GNode to the children of the node.
	 * 
	 * @param child
	 *            GNode to be added.
	 */
	public void addChild(GNode child) {
		child.parent = this;
		this.children.add(child);
		observerList.notifyObservers(GNotification.ADD, child);
	}

	/**
	 * Gets TreePath to the node.
	 * 
	 * @return TreePath to the node.
	 */
	public TreePath getPath() {

		TreeNode node = this;

		List<Object> nodes = new ArrayList<Object>();
		nodes.add(node);
		node = node.getParent();

		while (node != null) {
			nodes.add(0, node);
			node = node.getParent();
		}

		return nodes.isEmpty() ? null : new TreePath(nodes.toArray());
	}

	/**
	 * Creates and adds a new child GNode.
	 * 
	 * @return The added child GNode.
	 */
	public GNode addNewChild() {
		return null;
	}

	/**
	 * Creates and adds a new child GLink.
	 * 
	 * @param node
	 *            A GNode to use for GLink creation.
	 * @return The added GLink.
	 */
	public GNode addNewLinkChild(GNode node) {
		return null;
	}

	/**
	 * Adds a GLink to links.
	 * 
	 * @param link
	 *            GLink to be added.
	 */
	public void addLink(GLink link) {
		this.links.add(link);
	}

	/**
	 * Gets the name of the node.
	 * 
	 * @return The name of the node.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the node.
	 * 
	 * @param name
	 *            The new name of the node.
	 */
	public void setName(String name) {
		this.name = name;
		this.observerList.notifyObservers(GNotification.GNODE_RENAME, this);
	}

	/**
	 * Gets the children of the node.
	 * 
	 * @return The children of the node.
	 */
	public List<GNode> getChildren() {
		return this.children;
	}

	/**
	 * Sends a GNotificatin and an Object to the observers of the node.
	 * 
	 * @param notification
	 *            Notification type.
	 * @param obj
	 *            Data relevant to notification.
	 */
	public void notifyObservers(GNotification notification, Object obj) {
		observerList.notifyObservers(notification, obj);
	}

	/**
	 * Deletes all of the node's Hyperlinks. Also its children's.
	 */
	private void removeLinks() {

		for (GLink link : this.links) {
			link.removeFromParent();
		}
		for (GNode child : this.children) {
			child.removeLinks();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.tree.TreeNode#children()
	 */
	@Override
	public Enumeration children() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.tree.TreeNode#getAllowsChildren()
	 */
	@Override
	public boolean getAllowsChildren() {
		// TODO Auto-generated method stub
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.tree.TreeNode#getChildAt(int)
	 */
	@Override
	public TreeNode getChildAt(int index) {
		return this.children.get(index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.tree.TreeNode#getChildCount()
	 */
	@Override
	public int getChildCount() {
		return this.children.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.tree.TreeNode#getIndex(javax.swing.tree.TreeNode)
	 */
	@Override
	public int getIndex(TreeNode arg0) {
		return this.children.indexOf(arg0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.tree.TreeNode#getParent()
	 */
	@Override
	public TreeNode getParent() {
		return this.parent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.tree.TreeNode#isLeaf()
	 */
	@Override
	public boolean isLeaf() {
		return this.children.size() == 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.swing.tree.MutableTreeNode#insert(javax.swing.tree.MutableTreeNode,
	 * int)
	 */
	@Override
	public void insert(MutableTreeNode child, int index) {
		this.children.add(index, (GNode) child);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.tree.MutableTreeNode#remove(int)
	 */
	@Override
	public void remove(int index) {
		this.children.remove(index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.swing.tree.MutableTreeNode#remove(javax.swing.tree.MutableTreeNode)
	 */
	@Override
	public void remove(MutableTreeNode child) {
		this.children.remove((GNode) child);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.tree.MutableTreeNode#removeFromParent()
	 */
	@Override
	public void removeFromParent() {
		this.removeLinks();
		this.parent.remove(this);
		this.parent.observerList.notifyObservers(GNotification.DELETE, this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.tree.MutableTreeNode#setParent(javax.swing.tree.
	 * MutableTreeNode)
	 */
	@Override
	public void setParent(MutableTreeNode parent) {
		this.parent = (GNode) parent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.tree.MutableTreeNode#setUserObject(java.lang.Object)
	 */
	@Override
	public void setUserObject(Object arg0) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.name;
	}

	/**
	 * Initializes the observerList. Also for the children of the node.
	 * 
	 * @author Bakovic
	 */
	public void initObserverList() {
		this.observerList = new GObserverList();
		for (GNode child : getChildren())
			child.initObserverList();
	}
}
