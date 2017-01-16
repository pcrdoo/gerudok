package model;

import java.awt.Color;
import java.io.Serializable;
import java.util.Random;

import model.tree.GNode;

/**
 * Represents a node in the WorkspaceTree that contains and manipulates Slots.
 * 
 * @author Ognjen Djuricic
 *
 */
public class Page extends GNode implements Serializable {
	/**
	 * Version UID for serialization.
	 */
	final static long serialVersionUID = 1;

	/**
	 * Used for generating names for new children.
	 */
	private static int newChildCount = 0;

	/**
	 * The color of the page's graphic representation.
	 */
	private Color color;

	/**
	 * Constructor that sets the name.
	 * 
	 * @param name
	 *            The name of the page.
	 */
	public Page(String name) {
		super(name);
		Page.newChildCount = 0;
		Random r = new Random();
		color = new Color(r.nextFloat(), r.nextFloat(), r.nextFloat());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.tree.GNode#addNewChild()
	 */
	@Override
	public GNode addNewChild() {
		Slot child = new Slot("Slot" + newChildCount);
		newChildCount++;
		this.addChild(child);
		return child;
	}

	/**
	 * Gets the page color.
	 * 
	 * @return The page color.
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Sets the page color.
	 * 
	 * @param color
	 *            The new page color.
	 */
	public void setColor(Color color) {
		this.color = color;
	}
}