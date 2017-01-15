package model;

import java.io.Serializable;
import java.util.*;

import model.tree.GNode;

/**
 * Represents a node in the WorkspaceTree that contains and manipulates Pages.
 * 
 * @author Ognjen Djuricic
 *
 */
public class GeRuDocument extends GNode implements Serializable {

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
		this.newChildCount = 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.tree.GNode#addNewChild()
	 */
	public GNode addNewChild() {
		Page child = new Page("Page" + newChildCount);
		newChildCount++;
		this.addChild(child);
		return child;
	}
}