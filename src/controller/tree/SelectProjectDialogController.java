package controller.tree;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import command.AddNewLinkChildCommand;
import command.Invoker;
import model.Model;
import model.tree.GNode;
import view.tree.SelectProjectDialog;

public class SelectProjectDialogController {
	
	Model model;
	SelectProjectDialog view;
	
	public SelectProjectDialogController(Model model, SelectProjectDialog view) {
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
			
			for(GNode node : view.getSelected()) {
				AddNewLinkChildCommand command = new AddNewLinkChildCommand(model, node, view.getShared());
				Invoker.getInstance().executeCommand(command);
			}
			view.dispose();
		}
	}
	
	class DoubleClickListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount() >= 2) {
				AddNewLinkChildCommand command = new AddNewLinkChildCommand(model, view.getSelected().get(0), view.getShared());
				Invoker.getInstance().executeCommand(command);
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
