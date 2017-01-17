package model;

import java.io.Serializable;

import model.tree.GNode;

/**
 * Represents a node in the WorkspaceTree that contains and manipulates Pages.
 * 
 * @author Ognjen Djuricic
 *
 */
public class GeRuDocument extends GNode implements Serializable {
	/**
	 * Version UID for serialization.
	 */
	final static long serialVersionUID = 1;

	/**
	 * Used for generating names for new children.
	 */
	private static int newChildCount = 0;

	/**
	 * Constructor that sets the name.
	 * 
	 * @param name
	 *            The GeRuDocument name.
	 */
	public GeRuDocument(String name) {
		super(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.tree.GNode#addNewChild()
	 */
	@Override
	public GNode addNewChild() {
		Page child = new Page("Page" + newChildCount);
		newChildCount++;
		this.addChild(child);
		return child;
	}
}