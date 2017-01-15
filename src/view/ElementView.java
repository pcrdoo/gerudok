/***********************************************************************
 * Module:  ElementView.java
 * Author:  Ognjen
 * Purpose: Defines the Class ElementView
 ***********************************************************************/

package view;

import model.Element;
import model.Model;

import java.awt.event.MouseListener;
import java.util.ArrayList;

import controller.ElementController;

/** @pdOid 7de39cf0-3021-4c7e-a79b-bd085f44937d */
public abstract class ElementView extends ElementContainerView {
	private ElementController controller;
	protected ArrayList<MouseListener> deferredListeners = new ArrayList<>();
	
	public ElementView(Model model, Element element) {
		super(model, element, false);
		controller = new ElementController(model, this);
	}
	
	public Element getElement() {
		return (Element) getElementContainer();
	}
	
	public void addDoubleClickListener(MouseListener l) {
		deferredListeners.add(l);
	}
}