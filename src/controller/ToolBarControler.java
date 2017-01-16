/***********************************************************************
 * Module:  ToolBarControler.java
 * Author:  Ognjen
 * Purpose: Defines the Class ToolBarControler
 ***********************************************************************/

package controller;

import model.GeRuDocument;
import model.Model;
import model.Project;
import model.Slot;
import model.tree.GNode;
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
import command.SaveWorkspaceCommand;
import command.SwitchWorkspaceCommand;

public class ToolBarControler {
	public Model model;
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
				Object obj = model.getSelectedObject();
				Invoker.getInstance().executeCommand(new AddNewChildCommand(model, (GNode) obj));
			}
		};
	}

	public ActionListener getOpenProjectActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Object obj = model.getSelectedObject();
				if (obj instanceof Project) {
					Invoker.getInstance().executeCommand(new OpenProjectCommand(model, (Project) obj));
				}
			}
		};
	}

	public ActionListener getCloseProjectActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Object obj = model.getSelectedObject();
				if (obj instanceof Project) {
					Invoker.getInstance().executeCommand(new CloseProjectCommand(model, (Project) obj));
				}
			}
		};
	}

	public ActionListener getRenameActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Object obj = model.getSelectedObject();
				Invoker.getInstance().executeCommand(new RenameCommand(model, (GNode) obj));
			}
		};
	}

	public ActionListener getDeleteActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Object obj = model.getSelectedObject();
				Invoker.getInstance().executeCommand(new DeleteCommand(model, (GNode) obj));
			}
		};
	}

	public ActionListener getShareActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Object obj = model.getSelectedObject();
				if (obj instanceof GeRuDocument) {
					SelectProjectDialog sd = new SelectProjectDialog((GNode) obj, model);
					sd.show();
				}
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
				Invoker.getInstance().executeCommand(new SwitchWorkspaceCommand(model));
			}
		};
	}

	public ActionListener getCascadeActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				MainView.getInstance().getDesktopView().cascade();
			}
		};
	}

	public ActionListener getTileHorizontalyActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				MainView.getInstance().getDesktopView().tileHorizontally();
			}
		};
	}

	public ActionListener getTileVerticalyActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				MainView.getInstance().getDesktopView().tileVertically();
			}
		};
	}

	public ActionListener getSaveWorkspaceListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Invoker.getInstance().executeCommand(new SaveWorkspaceCommand(model));
			}
		};
	}

}