/***********************************************************************
 * Module:  Document.java
 * Author:  Ognjen
 * Purpose: Defines the Class Document
 ***********************************************************************/

package model;

import java.util.*;

import model.tree.GNode;

/** @pdOid 2daa0c29-7b61-4d73-9235-343a3f9ce163 */
public class Document extends GNode{
	
	public Document(String name) {
		super(name);
	}
   
   public GNode addNewChild() {
		Page child = new Page("Page"+this.getNewChildCount());
		this.add(child);
		return child;
   }

}