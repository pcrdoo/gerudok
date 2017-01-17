/***********************************************************************
 * Module:  ElementController.java
 * Author:  Ognjen
 * Purpose: Defines the Class ElementController
 ***********************************************************************/

package controller;

import model.Model;
import view.ElementView;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import command.ElementEditInitCommand;
import command.Invoker;

/**
 * Controller for an element view.
 * 
 * @author geomaster
 *
 */
public class ElementController {
	/**
	 * Model.
	 */
	public Model model;

	/**
	 * Element view.
	 */
	public ElementView elementView;

	/**
	 * Constructor.
	 * 
	 * @param model
	 *            Model
	 * @param elementView
	 *            Element view
	 */
	public ElementController(Model model, ElementView elementView) {
		this.model = model;
		this.elementView = elementView;
		elementView.addDoubleClickListener(new DoubleClickListener());
	}

	/**
	 * Listener for double click events on the element.
	 * 
	 * @author geomaster
	 *
	 */
	public class DoubleClickListener implements MouseListener {
		/**
		 * Called when the mouse is clicked.
		 * 
		 * @param e
		 *            Mouse event
		 */
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() >= 2) {
				Invoker.getInstance().executeCommand(new ElementEditInitCommand(model, elementView.getElement()));
			}
		}

		/**
		 * Called when the mouse has entered the element.
		 * 
		 * @param e
		 *            Mouse event
		 */
		@Override
		public void mouseEntered(MouseEvent e) {
		}

		/**
		 * Called when the mouse has exited the element.
		 * 
		 * @param e
		 *            Mouse event
		 */
		@Override
		public void mouseExited(MouseEvent e) {
		}

		/**
		 * Called when the mouse button is held over the element.
		 * 
		 * @param e
		 *            Mouse event
		 */
		@Override
		public void mousePressed(MouseEvent e) {
		}

		/**
		 * Called when the mouse is released over the element.
		 * 
		 * @param e
		 *            Mouse event
		 */
		@Override
		public void mouseReleased(MouseEvent e) {
		}
	}
}