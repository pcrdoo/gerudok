/***********************************************************************
 * Module:  Project.java
 * Author:  Ognjen
 * Purpose: Defines the Class Project
 ***********************************************************************/

package model;

import java.util.*;

import gerudok_observer.GObserverList;
import model.tree.GNode;

/** @pdOid 715d801c-8af9-4037-9476-707272b9ea66 */
public class Project extends GNode{
   
   public Project(String name) {
	   super(name);
   }
   
   public GNode addNewChild() {
		Document child = new Document("Document"+this.getNewChildCount());
		this.add(child);
		return child;
   }

   public void setOpened(boolean b) {
		// TODO Auto-generated method stub
	
	}
}