/***********************************************************************
 * Module:  ElementController.java
 * Author:  Ognjen
 * Purpose: Defines the Class ElementController
 ***********************************************************************/

package controller;

import command.ElementEditInitCommand;
import model.Model;
import view.ElementView;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;

import command.AddNewLinkChildCommand;
import command.Invoker;

/** @pdOid a5685a25-1c1b-492d-9034-be45e4f21053 */
public class ElementController {
   /** @pdRoleInfo migr=no name=Model assc=association20 mult=1..1 */
   public Model model;
   /** @pdRoleInfo migr=no name=ElementView assc=association10 mult=1..1 side=A */
   public ElementView elementView;
   
   public ElementController(Model model, ElementView elementView)
   {
	   this.model = model;
	   this.elementView = elementView;
	   elementView.addDoubleClickListener(new DoubleClickListener());
   }
   
	class DoubleClickListener implements MouseListener {
	
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