/***********************************************************************
 * Module:  GraphicElement.java
 * Author:  Ognjen
 * Purpose: Defines the Class GraphicElement
 ***********************************************************************/

package model.elements;

import java.io.Serializable;
import java.util.*;

import gerudok_observer.GNotification;
import model.Element;
import model.ElementType;

/** @pdOid b3cc2eff-d82f-456e-ab73-1921e0ba2467 */
public class GraphicElement extends Element implements Serializable{
	private List<GraphicShape> shapes;
	
	public GraphicElement() {
		super();
		this.shapes = new ArrayList<>();
		this.type = ElementType.GRAPHIC;
	}
	
	public GraphicElement(String name) {
		super(name);
		this.shapes = new ArrayList<>();
		this.type = ElementType.GRAPHIC;
	}

	public List<GraphicShape> getShapes() {
		return shapes;
	}
	
	public void notifyShapesChanged()
	{
		this.notifyObservers(GNotification.ELEMENT_EDIT, this);
	}
	
	public void setShapes(List<GraphicShape> shapes) {
		this.shapes = shapes;
		notifyShapesChanged();
	}
}