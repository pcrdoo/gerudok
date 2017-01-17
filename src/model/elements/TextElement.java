/***********************************************************************
 * Module:  TextElement.java
 * Author:  Ognjen
 * Purpose: Defines the Class TextElement
 ***********************************************************************/

package model.elements;

import java.io.Serializable;
import gerudok_observer.GNotification;
import model.Element;
import model.ElementType;

/**
 * Text element that can hold HTML-formatted text.
 * 
 * @author geomaster
 *
 */
public class TextElement extends Element implements Serializable {
	/**
	 * Version UID for serialization.
	 */
	final static long serialVersionUID = 1;

	/**
	 * HTML contents of the element.
	 */
	public String html;

	/**
	 * Default constructor.
	 */
	public TextElement() {
		super();
		this.type = ElementType.TEXT;
		this.html = "";
	}

	/**
	 * Constructor.
	 * 
	 * @param name
	 *            Name of the element
	 */
	public TextElement(String name) {
		super(name);
		this.type = ElementType.TEXT;
		this.html = "";
	}

	/**
	 * Gets the HTML contents of the element.
	 * 
	 * @return HTML contents
	 */
	public String getHtml() {
		return html;
	}

	/**
	 * Sets the HTML contents of the element.
	 * 
	 * @param html
	 *            New HTML contents
	 */
	public void setHtml(String html) {
		this.html = html;
		this.notifyObservers(GNotification.ELEMENT_EDIT, this);
	}
}