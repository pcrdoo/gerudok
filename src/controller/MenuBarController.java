/***********************************************************************
 * Module:  MenuBarController.java
 * Author:  Ognjen
 * Purpose: Defines the Class MenuBarController
 ***********************************************************************/

package controller;

import model.Model;
import view.MenuBarView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import command.LoadProjectCommand;
import command.SaveAsProjectCommand;
import command.SaveProjectCommand;

/** @pdOid 1189e529-58ea-4e27-92c3-a945264c24e8 */
public class MenuBarController {
   /** @pdRoleInfo migr=no name=Model assc=association11 mult=1..1 */
   public Model model;
   /** @pdRoleInfo migr=no name=MenuBarView assc=association2 mult=1..1 side=A */
   public MenuBarView menuBarView;
   
	public MenuBarController(Model model, MenuBarView menuBarView) {
		super();
		this.model = model;
		this.menuBarView = menuBarView;
	}

	public ActionListener getSaveActionListener() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SaveProjectCommand cmd = new SaveProjectCommand();
				cmd.execute();
			}
		};
	}
	
	public ActionListener getSaveAsActionListener() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SaveAsProjectCommand cmd = new SaveAsProjectCommand();
				cmd.execute();
			}
		};
	}
	
	public ActionListener getLoadActionListener() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				LoadProjectCommand cmd = new LoadProjectCommand(model);
				cmd.execute();
			}
		};
	}
   
}