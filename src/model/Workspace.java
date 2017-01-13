/***********************************************************************
 * Module:  Workspace.java
 * Author:  Ognjen
 * Purpose: Defines the Class Workspace
 ***********************************************************************/

package model;

import java.util.*;

import gerudok_observer.GObserver;
import gerudok_observer.GNotification;
import gerudok_observer.GObserverList;
import model.tree.GNode;

/** @pdOid 9ad5960e-3097-4f1b-971a-2008a1df723c */
public class Workspace extends GNode {

	static int newChildCount = 0;
	
	
	private static Workspace instance = null;
	
	private static int getNewChildCount() {
		return newChildCount++;
	}
	
	private Workspace() {
		
		// TODO
		// TEST
		super("workspace 1");
	}
	
	public static Workspace getInstance() {
		if(instance == null) {
			instance = new Workspace();
			instance.setName("workspace 1");
		}
		return instance;
	}
	
	public GNode addNewChild() {
		Project child = new Project("Project"+getNewChildCount());
		this.add(child);
		return child;
	}
	   
	
}