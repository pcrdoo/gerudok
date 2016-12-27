package model.tree;

import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

public class GLink extends GNode {
	
	private GNode original;
	private GNode parent;
	
	public GLink(GNode original) {
		this.original = original;
	}
	
	public GNode getOriginal() {
		return this.original;
	}

	@Override
	public Enumeration children() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getAllowsChildren() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TreeNode getChildAt(int index) {
		return this.original.getChildAt(index);
	}

	@Override
	public int getChildCount() {
		return this.original.getChildCount();
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
		return this.original.isLeaf();
	}

	@Override
	public void insert(MutableTreeNode child, int index) {
		this.original.insert(child, index);
	}

	@Override
	public void remove(int index) {
		this.original.remove(index);
	}

	@Override
	public void remove(MutableTreeNode child) {
		this.original.remove(child);
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
		return this.original.getName();
	}

}
