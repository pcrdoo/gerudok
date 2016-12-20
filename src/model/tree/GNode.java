package model.tree;

import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import gerudok_observer.GObserver;
import gerudok_observer.GObserverNotification;
import gerudok_observer.ObserverList;

public class GNode implements MutableTreeNode{
	
	private String name;
	private GNode parent;
	private ArrayList<GNode> children;
	private int newChildCount;
	protected ObserverList observerList;
	
	public GNode() {
		this.setName(null);
		this.parent = null;
		this.children = new ArrayList<>();
		this.newChildCount = 0;
	}
	
	public GNode(String name) {
		this.setName(name);
		this.parent = null;
		this.children = new ArrayList<>();
	}

	public void addObserver(GObserver observer) {
		observerList.addObserver(observer);
	}
	
	public void add(GNode child) {
		child.parent = this;
		this.children.add(child);
		observerList.notifyObservers(GObserverNotification.ADD, child);
	}
	
	public void addNewChild() {
//		GNode child = new GNode("test1");
//		child.parent = this;
//		this.children.add(child);
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
	}

	protected int getNewChildCount() {
		return this.newChildCount++;
	}
}
