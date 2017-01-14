/***********************************************************************
 * Module:  ToolBarControler.java
 * Author:  Ognjen
 * Purpose: Defines the Class ToolBarControler
 ***********************************************************************/

package controller;

import model.Model;
import view.MenuBarView;
import view.ToolBarView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import command.LoadProjectCommand;
import command.SaveAsProjectCommand;
import command.SaveProjectCommand;

/** @pdOid 88245b1f-d41c-42ae-be15-48a0717c1585 */
public class ToolBarControler {
   /** @pdRoleInfo migr=no name=Model assc=association12 mult=1..1 */
   public Model model;
   /** @pdRoleInfo migr=no name=ToolBarView assc=association1 mult=1..1 side=A */
   public ToolBarView toolBarView;
   
   public ToolBarControler(Model model, ToolBarView ToolBarView) {
		super();
		this.model = model;
		this.toolBarView = toolBarView;
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
				LoadProjectCommand cmd = new LoadProjectCommand();
				cmd.execute();
			}
		};
	}
  
}