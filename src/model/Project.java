/***********************************************************************
 * Module:  Project.java
 * Author:  Ognjen
 * Purpose: Defines the Class Project
 ***********************************************************************/

package model;

import java.util.*;

import model.tree.GNode;

/** @pdOid 715d801c-8af9-4037-9476-707272b9ea66 */
public class Project extends GNode{
   
   public Project(String name) {
	   super(name);
   }
   
   public void addNewChild() {
		Document child = new Document("Document"+this.getNewChildCount());
		this.add(child);
   }
}