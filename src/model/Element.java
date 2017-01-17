/***********************************************************************
 * Module:  Element.java
 * Author:  Ognjen
 * Purpose: Defines the Class Element
 ***********************************************************************/

package model;

import java.io.Serializable;

/**
 * Abstract element.
 * 
 * @author geomaster
 *
 */
public abstract class Element extends ElementContainer implements Serializable {
	/**
	 * Version UID for serialization.
	 */
	final static long serialVersionUID = 1;

	/**
	 * Default constructor.
	 */
	public Element() {
		super();
	}

	/**
	 * Constructor.
	 * 
	 * @param name
	 *            Name of the element
	 */
	public Element(String name) {
		super(name);
	}
}