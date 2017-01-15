/***********************************************************************
 * Module:  MenuBarController.java
 * Author:  Ognjen
 * Purpose: Defines the Class MenuBarController
 ***********************************************************************/

package controller;

import model.Model;
import view.AboutDialog;
import view.MainView;
import view.MenuBarView;
import view.tree.SelectProjectDialog;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import command.AddNewChildCommand;
import command.CloseProjectCommand;
import command.DeleteCommand;
import command.Invoker;
import command.LoadProjectCommand;
import command.OpenProjectCommand;
import command.RenameCommand;
import command.SaveAsProjectCommand;
import command.SaveProjectCommand;

/** @pdOid 1189e529-58ea-4e27-92c3-a945264c24e8 */
public class MenuBarController {
   /** @pdRoleInfo migr=no name=Model assc=association11 mult=1..1 */
   public Model model;
   /** @pdRoleInfo migr=no name=MenuBarView assc=association2 mult=1..1 side=A */
   public MenuBarView menuBarView;
   public AboutDialog aboutDialog;
   
	public MenuBarController(Model model, MenuBarView menuBarView) {
		super();
		this.model = model;
		this.menuBarView = menuBarView;
		this.aboutDialog = new AboutDialog(null);
	}

	public ActionListener getSaveActionListener() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Invoker.getInstance().executeCommand(new SaveProjectCommand(model));
			}
		};
	}
	
	public ActionListener getSaveAsActionListener() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Invoker.getInstance().executeCommand(new SaveAsProjectCommand(model));
			}
		};
	}
	
	public ActionListener getLoadActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Invoker.getInstance().executeCommand(new LoadProjectCommand(model));
			}
		};
	}
	
	public ActionListener getAboutActionListener() {
		return new ActionListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				aboutDialog.show();
			}
		};
	}

	public ActionListener getAddActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Invoker.getInstance().executeCommand(new AddNewChildCommand(model, MainView.getInstance().getTreeView().getSelectedNode()));
			}
		};
	}
	
	public ActionListener getOpenProjectActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Invoker.getInstance().executeCommand(new OpenProjectCommand(model, MainView.getInstance().getTreeView().getSelectedProject()));
			}
		};
	}

	public ActionListener getCloseProjectActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Invoker.getInstance().executeCommand(new CloseProjectCommand(model, MainView.getInstance().getTreeView().getSelectedProject()));
			}
		};
	}
	
	public ActionListener getRenameActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Invoker.getInstance().executeCommand(new RenameCommand(model, MainView.getInstance().getTreeView().getSelectedNode()));
			}
		};
	}
	
	public ActionListener getDeleteActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Invoker.getInstance().executeCommand(new DeleteCommand(model, MainView.getInstance().getTreeView().getSelectedNode()));
			}
		};
	}
	
	public ActionListener getShareActionListener() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				SelectProjectDialog sd = new SelectProjectDialog(MainView.getInstance().getTreeView().getSelectedNode(), model);
				sd.show();
			}
		};
	}
}