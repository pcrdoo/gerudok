/***********************************************************************
 * Module:  Page.java
 * Author:  Ognjen
 * Purpose: Defines the Class Page
 ***********************************************************************/

package model;

import java.awt.Color;
import java.io.Serializable;
import java.util.*;

import model.tree.GNode;

/** @pdOid a8ab91cf-d20a-4024-a842-190ce1e68bfa */
public class Page extends GNode implements Serializable{

	private int newChildCount = 0;
	
	private Color color;
	
	public Page(String name) {
		super(name);
		this.newChildCount = 0;
		Random r = new Random();
		color = new Color(r.nextFloat(), r.nextFloat(), r.nextFloat());
	}
	
	public GNode addNewChild() {
		Slot child = new Slot("Slot"+newChildCount);
		newChildCount++;
		this.add(child);
		return child;
	}
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}