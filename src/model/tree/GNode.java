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
	protected GObserverList observerList;
	private List<GLink> links;
	
	public GNode() {
		this.parent = null;
		this.children = new ArrayList<>();
		this.observerList = new GObserverList();
		this.links = new ArrayList<>();
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
	
	public GNode addNewLinkChild(GNode node) {
		return null;
	}
	
	public void addLink(GLink link) {
		this.links.add(link);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		this.observerList.notifyObservers(GObserverNotification.GNODE_RENAME, this);
	}
	
	public List<GNode> getChildren() {
		return this.children;
	}
	
	public void notifyObservers(GObserverNotification notification, Object obj) {
		observerList.notifyObservers(notification, obj);
	}
	
	@Override
	public Enumeration children() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getAllowsChildren() {
		// TODO Auto-generated method stub
		return true;
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
		return this.children.indexOf(arg0);
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
		for(GLink link : this.links) {
			link.removeFromParent();
		}
		this.parent.remove(this);
		this.parent.observerList.notifyObservers(GObserverNotification.DELETE, this);
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
}
