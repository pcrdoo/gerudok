/***********************************************************************
 * Module:  Page.java
 * Author:  Ognjen
 * Purpose: Defines the Class Page
 ***********************************************************************/

package model;

import java.util.*;

import model.tree.GNode;

/** @pdOid a8ab91cf-d20a-4024-a842-190ce1e68bfa */
public class Page extends GNode{
	
	public Page(String name) {
		super(name);
	}
	
	public void addNewChild() {
		Slot child = new Slot("Slot"+this.getNewChildCount());;
		this.add(child);
	}

}