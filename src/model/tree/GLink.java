package model.tree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import gerudok_observer.GNotification;

/**
 * Represents a node in the WorkspaceTree that does not contain any data, but is
 * a hyperlink of a GNode.
 * 
 * @author Ognjen Djuricic
 *
 */
public class GLink extends GNode implements Serializable {

	/**
	 * The GNode that this link represents.
	 */
	private GNode original;

	/**
	 * Constructor that sets the original GNode.
	 * 
	 * @param original
	 *            The GNode to witch the link links to.
	 */
	public GLink(GNode original) {
		this.original = original;
	}

	/**
	 * Gets the original GNode.
	 * 
	 * @return The origilan GNode.
	 */
	public GNode getOriginal() {
		return this.original;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.tree.GNode#children()
	 */
	@Override
	public Enumeration children() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.tree.GNode#getAllowsChildren()
	 */
	@Override
	public boolean getAllowsChildren() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.tree.GNode#getChildAt(int)
	 */
	@Override
	public TreeNode getChildAt(int index) {
		return this.original.getChildAt(index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.tree.GNode#getChildCount()
	 */
	@Override
	public int getChildCount() {
		return this.original.getChildCount();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.tree.GNode#getIndex(javax.swing.tree.TreeNode)
	 */
	@Override
	public int getIndex(TreeNode arg0) {
		return this.original.getChildren().indexOf(arg0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.tree.GNode#getParent()
	 */
	@Override
	public TreeNode getParent() {
		return this.parent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.tree.GNode#isLeaf()
	 */
	@Override
	public boolean isLeaf() {
		return this.original.isLeaf();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.tree.GNode#insert(javax.swing.tree.MutableTreeNode, int)
	 */
	@Override
	public void insert(MutableTreeNode child, int index) {
		this.original.insert(child, index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.tree.GNode#remove(int)
	 */
	@Override
	public void remove(int index) {
		this.original.remove(index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.tree.GNode#remove(javax.swing.tree.MutableTreeNode)
	 */
	@Override
	public void remove(MutableTreeNode child) {
		this.original.remove(child);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.tree.GNode#removeFromParent()
	 */
	@Override
	public void removeFromParent() {
		this.parent.children.remove(this);
		this.parent.observerList.notifyObservers(GNotification.DELETE, this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.tree.GNode#setParent(javax.swing.tree.MutableTreeNode)
	 */
	@Override
	public void setParent(MutableTreeNode parent) {
		this.parent = (GNode) parent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.tree.GNode#setUserObject(java.lang.Object)
	 */
	@Override
	public void setUserObject(Object arg0) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.tree.GNode#toString()
	 */
	@Override
	public String toString() {
		return this.original.getName();
	}

}
