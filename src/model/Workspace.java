/***********************************************************************
 * Module:  Workspace.java
 * Author:  Ognjen
 * Purpose: Defines the Class Workspace
 ***********************************************************************/

package model;

import java.util.*;

import model.tree.GNode;

/** @pdOid 9ad5960e-3097-4f1b-971a-2008a1df723c */
public class Workspace extends GNode {
	
	private static Workspace instance = null;
	
	public Workspace() {
		
		// TODO
		// TEST
		super("workspace 1");
	}
	
	public static Workspace getInstance() {
		if(instance == null) {
			instance = new Workspace();
		}
		
		return instance;
	}
	
	public void addNewChild() {
		Project child = new Project("Project"+this.getNewChildCount());
		this.add(child);
	}
}