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

/**
 * Abstract view for elements. All views displaying specific elements should
 * subclass it.
 * 
 * @author geomaster
 *
 */
public abstract class ElementView extends ElementContainerView {
	/**
	 * Version UID for serialization.
	 */
	final static long serialVersionUID = 1;

	/**
	 * Listeners to be added when the construction is finished (added by
	 * superclass controllers and thus cannot be effectively added before our
	 * own constructor runs, hence the deferring).
	 */
	protected ArrayList<MouseListener> deferredListeners = new ArrayList<>();

	/**
	 * Constructor.
	 * 
	 * @param model
	 *            Model
	 * @param element
	 *            Element to display
	 */
	public ElementView(Model model, Element element) {
		super(model, element, false);
		new ElementController(model, this);
	}

	/**
	 * Gets the element being displayed.
	 * 
	 * @return Element
	 */
	public Element getElement() {
		return (Element) getElementContainer();
	}

	/**
	 * Adds a double click listener.
	 * 
	 * @param listener
	 *            Listener to add
	 */
	public void addDoubleClickListener(MouseListener listener) {
		deferredListeners.add(listener);
	}
}