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

/**
 * Graphic element that can hold zero or more shapes.
 * 
 * @author geomaster
 *
 */
public class GraphicElement extends Element implements Serializable {
	/**
	 * Version UID for serialization.
	 */
	final static long serialVersionUID = 1;

	/**
	 * Shapes contained in the element.
	 */
	private List<GraphicShape> shapes;

	/**
	 * Default constructor.
	 */
	public GraphicElement() {
		super();
		this.shapes = new ArrayList<>();
		this.type = ElementType.GRAPHIC;
	}

	/**
	 * Constructor.
	 * 
	 * @param name
	 *            Name of the element
	 */
	public GraphicElement(String name) {
		super(name);
		this.shapes = new ArrayList<>();
		this.type = ElementType.GRAPHIC;
	}

	/**
	 * Gets the list of shapes contained in the element.
	 * 
	 * @return List of shapes
	 */
	public List<GraphicShape> getShapes() {
		return shapes;
	}

	/**
	 * Notifies the element that its shape list has changed, or that one or more
	 * of the shapes it contains has changed.
	 */
	public void notifyShapesChanged() {
		this.notifyObservers(GNotification.ELEMENT_EDIT, this);
	}

	/**
	 * Sets the list of the shapes contained in the element.
	 * 
	 * @param shapes
	 *            List of shapes
	 */
	public void setShapes(List<GraphicShape> shapes) {
		this.shapes = shapes;
		notifyShapesChanged();
	}
}