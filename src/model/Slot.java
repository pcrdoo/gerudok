package model;

import java.io.Serializable;

import javax.swing.tree.MutableTreeNode;

/**
 * Represents a node in the WorkspaceTree that contains a single Element.
 * 
 * @author Ognjen Djuricic
 *
 */
public class Slot extends ElementContainer implements Serializable {

	/**
	 * Default constructor.
	 */
	public Slot() {
		super();
	}

	/**
	 * Constructor that sets the name.
	 * 
	 * @param name
	 *            The slot name.
	 */
	public Slot(String name) {
		super(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.tree.GNode#remove(int)
	 */
	@Override
	public void remove(int index) {
		super.remove(index);
		if (children.size() == 0) {
			this.type = null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.tree.GNode#remove(javax.swing.tree.MutableTreeNode)
	 */
	@Override
	public void remove(MutableTreeNode child) {
		super.remove(child);
		if (children.size() == 0) {
			this.type = null;
		}
	}
}