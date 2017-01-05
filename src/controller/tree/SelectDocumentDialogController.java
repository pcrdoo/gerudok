package controller.tree;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import command.Invoker;
import command.TreeSelectCommand;
import model.Model;
import model.tree.GNode;
import view.tree.SelectDocumentDialog;

public class SelectDocumentDialogController {

	Model model;
	SelectDocumentDialog view;
	
	public SelectDocumentDialogController(Model model, SelectDocumentDialog view) {
		this.model = model;
		this.view = view;
		
		this.view.addSelectionChangedListener(new SelectionChangedListener());
		this.view.addBtnOKListener(new BtnOKListener());
		this.view.addDoubleClickListener(new DoubleClickListener());
	}
	
	class SelectionChangedListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			view.enableBtnOK();
		}
	}
	
	class BtnOKListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			// TODO mozda u komandu
			GNode selected = view.getSelected();
			model.getFreeNodes().remove(selected);
			view.getParentNode().add(selected);
			model.getTreeModel().reload();
			Invoker.getInstance().executeCommand(new TreeSelectCommand(model, selected));
//			AddNewLinkChildCommand command = new AddNewLinkChildCommand(model, view.getSelected(), view.getShared());
//			Invoker.getInstance().executeCommand(command);
			view.dispose();
		}
	}
	
	class DoubleClickListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount() >= 2) {
				// TODO mozda u komandu
				GNode selected = view.getSelected();
				model.getFreeNodes().remove(selected);
				view.getParentNode().add(selected);
				model.getTreeModel().reload();
				Invoker.getInstance().executeCommand(new TreeSelectCommand(model, selected));
//				AddNewLinkChildCommand command = new AddNewLinkChildCommand(model, view.getSelected(), view.getShared());
//				Invoker.getInstance().executeCommand(command);
				view.dispose();
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
