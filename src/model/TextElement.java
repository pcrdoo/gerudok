/***********************************************************************
 * Module:  TextElement.java
 * Author:  Ognjen
 * Purpose: Defines the Class TextElement
 ***********************************************************************/

package model;

import java.io.Serializable;
import java.util.*;

import gerudok_observer.GNotification;

/** @pdOid 7be85156-09fc-4a5e-8135-a23816e01bde */
public class TextElement extends Element implements Serializable {
	public String html;
	
	public TextElement() {
		super();
		this.type = ElementType.TEXT;
		this.html = "";
	}
	
	public TextElement(String name) {
		super(name);
		this.type = ElementType.TEXT;
		this.html = "";
	}
	
	public String getHtml() {
		return html;
	}
	
	public void setHtml(String html) {
		this.html = html;
		this.notifyObservers(GNotification.ELEMENT_EDIT, this);
	}
}