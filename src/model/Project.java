/***********************************************************************
 * Module:  Project.java
 * Author:  Ognjen
 * Purpose: Defines the Class Project
 ***********************************************************************/

package model;

import java.util.*;

import gerudok_observer.GObserverList;
import gerudok_observer.GObserverNotification;
import model.tree.GNode;

/** @pdOid 715d801c-8af9-4037-9476-707272b9ea66 */
public class Project extends GNode{
	
	private boolean opened;
	
	public Project(String name) {
		super(name);
		this.opened = true;
	}

	public GNode addNewChild() {
		Document child = new Document("Document"+this.getNewChildCount());
		this.add(child);
		return child;
	}
	
	public GNode addNewLinkChild(GNode node) {
		DocumentLink child = new DocumentLink(node);
		node.addLink(child);
		this.add(child);
		child.setParent(this);
		return child;
	}

	public void doProjectClose() {
		this.observerList.notifyObservers(GObserverNotification.PROJECT_CLOSE, null);
	}
	
	public void doProjectOpen() {
		this.observerList.notifyObservers(GObserverNotification.PROJECT_OPEN, null);
	}

	public boolean isOpened() {
		return this.opened;
	}

	public void setOpened(boolean opened) {
		this.opened = opened;
	}
}