package controller.tree;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

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
		this.view.setCloseListener(new CloseListener());
		this.view.setShareListener(new ShareListener());
	}
	
	class AddNewListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			GNode newNode = view.getSelectedNode().addNewChild();
			model.getTreeModel().reload();
			model.doTreeSelection(newNode);
		}
	}
	
	class DeleteListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			GNode selected = view.getSelectedNode();
			GNode parent = (GNode) selected.getParent();
			
			if(selected instanceof Document) {
				model.addFreeNode(selected);
			}
			
			selected.removeFromParent();
			model.getTreeModel().reload();
			model.doTreeSelection(parent);
			
			//System.out.println(model.getFreeNodes());
		}
	}
	
	class RenameListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			model.doTreeRename(view.getSelectedNode());
		}
	}
	
	class SwitchWorkspaceListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	class CloseListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(((Project)view.getSelectedNode()).isOpened()) {
				((Project)view.getSelectedNode()).doProjectClose();
				((Project)view.getSelectedNode()).setOpened(false);
			} else {
				((Project)view.getSelectedNode()).doProjectOpen();
				((Project)view.getSelectedNode()).setOpened(true);
			}
			model.getTreeModel().reload();
		}
	}
	
	class ShareListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			SelectDialog sd = new SelectDialog(view.getSelectedNode(), model);
			sd.show();
			model.getTreeModel().reload();
		}
	}
}
