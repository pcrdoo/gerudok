/***********************************************************************
 * Module:  Slot.java
 * Author:  Ognjen
 * Purpose: Defines the Class Slot
 ***********************************************************************/

package model;

import java.util.*;

import javax.swing.tree.MutableTreeNode;

/** @pdOid b2e2161e-269a-4e75-b842-89f0af4ac234 */
public class Slot extends ElementContainer {
	public Slot() {
		super();
	}
	
	@Override
	public void remove(int index) {
		super.remove(index);
		if (children.size() == 0) {
			this.type = null;
		}
	}

	@Override
	public void remove(MutableTreeNode child) {
		super.remove(child);
		if (children.size() == 0) {
			this.type = null;
		}
	}
	
	public Slot(String name) {
		super(name);
	}
}