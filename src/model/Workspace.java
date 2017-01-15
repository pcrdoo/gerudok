package model;

import java.io.Serializable;
import java.util.*;

import gerudok_observer.GObserver;
import gerudok_observer.GNotification;
import gerudok_observer.GObserverList;
import model.tree.GNode;

/**
 * Represents the root node in the WorkspaceTree that contains and manipulates
 * Projects. There is never more then one Workspace.
 * 
 * @author Ognjen Djuricic
 *
 */
public class Workspace extends GNode implements Serializable {

	/**
	 * Used for generating names for new children.
	 */
	private static int newChildCount = 0;
	/**
	 * The single Workspace object.
	 */
	private static Workspace instance = null;

	/**
	 * Gets the newChildCount.
	 * 
	 * @return The newChildCount.
	 */
	private static int getNewChildCount() {
		return newChildCount++;
	}

	/**
	 * Default constructor.
	 */
	private Workspace() {

		// TODO Bakovic
		// TEST
		super("Workspace1");
	}

	/**
	 * Creates and gets the single instance of the Workspace.
	 * 
	 * @return The single Workspace object.
	 */
	public static Workspace getInstance() {
		if (instance == null) {
			instance = new Workspace();
			instance.setName("workspace 1");
		}
		return instance;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.tree.GNode#addNewChild()
	 */
	public GNode addNewChild() {
		Project child = new Project("Project" + getNewChildCount());
		this.addChild(child);
		return child;
	}
}