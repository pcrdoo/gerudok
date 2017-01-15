package model;

import java.io.File;
import java.io.Serializable;

import gerudok_observer.GNotification;
import model.tree.GNode;

/**
 * Represents a node in the WorkspaceTree that contains and manipulates
 * GeRuDocuments. Can be opened or closed, and saved.
 * 
 * @author Ognjen Djuricic
 *
 */
public class Project extends GNode implements Serializable {

	/**
	 * Used for generating names for new children.
	 */
	private static int newChildCount = 0;
	/**
	 * The state of the project.
	 */
	private boolean opened;
	/**
	 * The file of the project.
	 */
	private File projectFile;

	/**
	 * Constructor that sets the name.
	 * 
	 * @param name
	 *            The name of the project.
	 */
	public Project(String name) {
		super(name);
		this.opened = true;
		this.projectFile = null;
		this.newChildCount = 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.tree.GNode#addNewChild()
	 */
	public GNode addNewChild() {
		GeRuDocument child = new GeRuDocument("GeRuDocument" + newChildCount);
		newChildCount++;
		this.addChild(child);
		return child;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.tree.GNode#addNewLinkChild(model.tree.GNode)
	 */
	@Override
	public GNode addNewLinkChild(GNode node) {
		GeRuDocumentLink child = new GeRuDocumentLink(node);
		node.addLink(child);
		this.addChild(child);
		child.setParent(this);
		return child;
	}

	/**
	 * Notifies the ProjectView that the project is opened.
	 */
	public void doProjectOpen() {
		this.observerList.notifyObservers(GNotification.PROJECT_OPEN, null);
	}

	/**
	 * Notifies the ProjectView that the project is closed.
	 */
	public void doProjectClose() {
		this.observerList.notifyObservers(GNotification.PROJECT_CLOSE, null);
	}

	/**
	 * Gets the project state.
	 * 
	 * @return The project state.
	 */
	public boolean isOpened() {
		return this.opened;
	}

	/**
	 * Sets the project state.
	 * 
	 * @param opened
	 *            The new state.
	 */
	public void setOpened(boolean opened) {
		this.opened = opened;
	}

	/**
	 * Gets the project file.
	 * 
	 * @return The project file.
	 */
	public File getProjectFile() {
		return projectFile;
	}

	/**
	 * Sets the project file.
	 * 
	 * @param projectFile
	 *            The new file.
	 */
	public void setProjectFile(File projectFile) {
		this.projectFile = projectFile;
	}
}