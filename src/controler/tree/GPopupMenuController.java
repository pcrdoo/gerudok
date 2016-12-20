package controler.tree;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import model.Model;
import model.tree.GNode;
import view.tree.GPopupMenu;

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
	}
	
	class AddNewListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
//			System.out.println(model);
//			System.out.println(model.getTreeModel());
//			System.out.println(view);
//			System.out.println(view.getSelectedNode());
			view.getSelectedNode().addNewChild();
			model.getTreeModel().reload();
		}
	}
	
	class DeleteListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			//TODO ovo dole ne radi
			view.getSelectedNode().removeFromParent();
			//model.getTreeModel().se
			model.getTreeModel().reload();
		}
	}
	
	class RenameListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	class SwitchWorkspaceListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
}
