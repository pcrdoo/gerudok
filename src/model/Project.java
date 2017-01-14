/***********************************************************************
 * Module:  Project.java
 * Author:  Ognjen
 * Purpose: Defines the Class Project
 ***********************************************************************/

package model;

import java.io.File;
import java.io.Serializable;
import java.util.*;

import gerudok_observer.GObserverList;
import gerudok_observer.GNotification;
import model.tree.GNode;

/** @pdOid 715d801c-8af9-4037-9476-707272b9ea66 */
public class Project extends GNode implements Serializable{

	private int newChildCount;
	private boolean opened;
	private File projectFile;
	
	public Project(String name) {
		super(name);
		this.opened = true;
		this.projectFile = null;
		this.newChildCount = 0;
	}

	public GNode addNewChild() {
		GeRuDocument child = new GeRuDocument("Document"+newChildCount);
		newChildCount++;
		this.add(child);
		return child;
	}
	
	@Override
	public GNode addNewLinkChild(GNode node) {
		GeRuDocumentLink child = new GeRuDocumentLink(node);
		node.addLink(child);
		this.add(child);
		child.setParent(this);
		return child;
	}

	public void doProjectClose() {
		this.observerList.notifyObservers(GNotification.PROJECT_CLOSE, null);
	}
	
	public void doProjectOpen() {
		this.observerList.notifyObservers(GNotification.PROJECT_OPEN, null);
	}

	public boolean isOpened() {
		return this.opened;
	}

	public void setOpened(boolean opened) {
		this.opened = opened;
	}

	public File getProjectFile() {
		return projectFile;
	}

	public void setProjectFile(File projectFile) {
		this.projectFile = projectFile;
	}
}