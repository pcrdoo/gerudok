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
import java.util.*;

import command.AddNewLinkChildCommand;
import command.ElementEditInitCommand;
import command.Invoker;

public class ElementController {
   public Model model;
   public ElementView elementView;
   
   public ElementController(Model model, ElementView elementView)
   {
	   this.model = model;
	   this.elementView = elementView;
	   elementView.addDoubleClickListener(new DoubleClickListener());
   }
   
   public class DoubleClickListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount() >= 2) {
				Invoker.getInstance().executeCommand(new ElementEditInitCommand(model, elementView.getElement()));
			}
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {}
		
		@Override
		public void mouseExited(MouseEvent e) {}
		
		@Override
		public void mousePressed(MouseEvent e) {}
		
		@Override
		public void mouseReleased(MouseEvent e) {}
	}
}