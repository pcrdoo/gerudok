/***********************************************************************
 * Module:  TextElement.java
 * Author:  Ognjen
 * Purpose: Defines the Class TextElement
 ***********************************************************************/

package model;

import java.util.*;

import gerudok_observer.GObserverNotification;

/** @pdOid 7be85156-09fc-4a5e-8135-a23816e01bde */
public class TextElement extends Element {
	public String html;
	
	public TextElement() {
		super();
		this.type = ElementType.TEXT;
		this.html = "Lorem ipsum dolor sit amet";
	}
	
	public TextElement(String name) {
		super(name);
		this.type = ElementType.TEXT;
		this.html = "Lorem ipsum dolor sit amet";
	}
	
	public String getHtml() {
		return html;
	}
	
	public void setHtml(String html) {
		this.html = html;
		this.notifyObservers(GObserverNotification.ELEMENT_EDIT, this);
	}
}