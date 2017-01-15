/***********************************************************************
 * Module:  ToolBarControler.java
 * Author:  Ognjen
 * Purpose: Defines the Class ToolBarControler
 ***********************************************************************/

package controller;

import model.Model;
import model.Slot;
import view.MainView;
import view.MenuBarView;
import view.ToolBarView;
import view.tree.GPopupMenu;
import view.tree.SelectProjectDialog;
import view.tree.TreeView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import command.AddNewChildCommand;
import command.AddNewLinkChildCommand;
import command.CloseProjectCommand;
import command.DeleteCommand;
import command.Invoker;
import command.LoadProjectCommand;
import command.NewWorkspaceCommand;
import command.OpenProjectCommand;
import command.RenameCommand;
import command.SaveAsProjectCommand;
import command.SaveProjectCommand;
import command.SwitchWorkspaceCommand;

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

	public ActionListener getNewWorkspaceListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Invoker.getInstance().executeCommand(new NewWorkspaceCommand(model));
			}
		};
	}

	public ActionListener getSwitchWorkspaceListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("123");
				Invoker.getInstance().executeCommand(new SwitchWorkspaceCommand(model));
			}
		};
	}
}