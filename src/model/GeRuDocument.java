/***********************************************************************
 * Module:  Document.java
 * Author:  Ognjen
 * Purpose: Defines the Class Document
 ***********************************************************************/

package model;

import java.io.Serializable;
import java.util.*;

import model.tree.GNode;

public class GeRuDocument extends GNode implements Serializable{

	private int newChildCount = 0;
	
	public GeRuDocument(String name) {
		super(name);
		this.newChildCount = 0;
	}
   
	public GNode addNewChild() {
		Page child = new Page("Page"+newChildCount);
		newChildCount++;
		this.add(child);
		return child;
	}
}