package model;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.tree.MutableTreeNode;

import gerudok_observer.GObserverList;
import model.tree.GNode;

public class ElementContainer extends GNode implements Serializable{
	protected ElementType type;
	private ElementFactory elementFactory;

	static int newChildCount = 0;
	
	private static int getNewChildCount() {
		return newChildCount++;
	}
	
	public ElementContainer() {
		super();
		this.type = null;
		this.elementFactory = new ElementFactory();
	}
	
	public ElementContainer(String name) {
		super(name);
		this.type = null;
		this.elementFactory = new ElementFactory();
	}
	
	public ElementType getType() {
		return type;
	}
	

	
	@Override
	public GNode addNewChild() {
		// Pretend only the happy path exists
		try {
			return addNewChild(type);
		} catch (Exception e) {
			System.out.println("DavisException: this should never happen, if's don't work");
			e.printStackTrace();
			return null;
		}
	}
	
	public GNode addNewChild(ElementType childType) throws Exception {
		if (type != null && childType != type)
		{
			// The slot is already typed, cannot add an incompatible element
			throw new Exception("Element of invalid type added to element container");
		}
		
		Element child = elementFactory.create(childType, elementFactory.createName(childType, getNewChildCount()));
		this.add(child);
		this.type = childType;
		
		return child;
	}
}
