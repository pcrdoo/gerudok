package controller.tree;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import command.AddNewChildCommand;
import command.CloseProjectCommand;
import command.DeleteCommand;
import command.Invoker;
import command.OpenProjectCommand;
import command.RenameCommand;
import command.SwitchWorkspaceCommand;
import command.TreeSelectCommand;
import model.Document;
import model.Model;
import model.Project;
import model.tree.GNode;
import view.tree.GPopupMenu;
import view.tree.SelectDialog;

public class GPopupMenuController {
	
	Model model;
	GPopupMenu view;
	
	public GPopupMenuController(Model model, GPopupMenu view) {
		this.model = model;
		this.view = view;

		this.view.setAddNewListener(new AddNewListener());
		this.view.setDeleteListener(new DeleteListener());
		this.view.setRenameListener(new RenameListener());
		this.view.setSwitchWorkspaceListener(new SwitchWorkspaceListener());
		this.view.setOpenCloseListener(new OpenCloseListener());
		this.view.setShareListener(new ShareListener());
	}
	
	class AddNewListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Invoker.getInstance().executeCommand(new AddNewChildCommand(model, view.getSelectedNode()));
		}
	}
	
	class DeleteListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Invoker.getInstance().executeCommand(new DeleteCommand(model, view.getSelectedNode()));
		}
	}
	
	class RenameListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Invoker.getInstance().executeCommand(new RenameCommand(model, view.getSelectedNode()));
		}
	}
	
	class SwitchWorkspaceListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Invoker.getInstance().executeCommand(new SwitchWorkspaceCommand(model, view.getSelectedNode()));
		}
	}
	
	class OpenCloseListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Project project = (Project)view.getSelectedNode();
			if(project.isOpened()) {
				Invoker.getInstance().executeCommand(new CloseProjectCommand(model, project));
			} else {
				Invoker.getInstance().executeCommand(new OpenProjectCommand(model, project));
			}
			// model.getTreeModel().reload();
		}
	}
	
	class ShareListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			SelectDialog sd = new SelectDialog(view.getSelectedNode(), model);
			sd.show();
		}
	}
}
