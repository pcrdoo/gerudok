package model.tree;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import gerudok_observer.GObservable;
import gerudok_observer.GObserver;
import gerudok_observer.GObserverNotification;
import gerudok_observer.GObserverList;
import model.Project;

public class GNode implements MutableTreeNode, GObservable {
	
	private String name;
	protected GNode parent;
	protected ArrayList<GNode> children;
	private int newChildCount;
	protected GObserverList observerList;
	
	public GNode() {
		this.parent = null;
		this.children = new ArrayList<>();
		this.newChildCount = 0;
		this.observerList = new GObserverList();
		this.setName(null);
	}
	
	public GNode(String name) {
		this();
		this.setName(name);
	}

	@Override
	public void addObserver(GObserver observer) {
		observerList.addObserver(observer);
	}
	
	public void add(GNode child) {
		child.parent = this;
		this.children.add(child);
		observerList.notifyObservers(GObserverNotification.ADD, child);
	}
	
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
	
	public GNode addNewChild() {
		return null;
	}

	@Override
	public Enumeration children() {
		// TODO Auto-generated method stub
		return null;
		//return (Enumeration)this.children;
	}

	@Override
	public boolean getAllowsChildren() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TreeNode getChildAt(int index) {
		return this.children.get(index);
	}

	@Override
	public int getChildCount() {
		return this.children.size();
	}

	@Override
	public int getIndex(TreeNode arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public TreeNode getParent() {
		return this.parent;
	}

	@Override
	public boolean isLeaf() {
		return this.children.size() == 0;
	}

	@Override
	public void insert(MutableTreeNode child, int index) {
		this.children.add(index, (GNode)child);
	}

	@Override
	public void remove(int index) {
		this.children.remove(index);
	}

	@Override
	public void remove(MutableTreeNode child) {
		this.children.remove((GNode)child);
	}

	@Override
	public void removeFromParent() {
		this.parent.children.remove(this);
		this.parent.observerList.notifyObservers(GObserverNotification.DELETE, this);
		System.out.println("Obrisi mene " + this.getName());
	}

	@Override
	public void setParent(MutableTreeNode parent) {
		this.parent = (GNode)parent;
	}

	@Override
	public void setUserObject(Object arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String toString() {
		return this.name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		this.observerList.notifyObservers(GObserverNotification.GNODE_RENAME, this);
	}

	protected int getNewChildCount() {
		return this.newChildCount++;
	}
	
}
