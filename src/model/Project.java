/***********************************************************************
 * Module:  Project.java
 * Author:  Ognjen
 * Purpose: Defines the Class Project
 ***********************************************************************/

package model;

import java.util.*;

import gerudok_observer.GObserverList;
import gerudok_observer.GNotification;
import model.tree.GNode;

/** @pdOid 715d801c-8af9-4037-9476-707272b9ea66 */
public class Project extends GNode{

	static int newChildCount = 0;
	
	private static int getNewChildCount() {
		return newChildCount++;
	}
	
	private boolean opened;
	
	public Project(String name) {
		super(name);
		this.opened = true;
	}

	public GNode addNewChild() {
		GeRuDocument child = new GeRuDocument("Document"+getNewChildCount());
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
}