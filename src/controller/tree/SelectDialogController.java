package controller.tree;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Model;
import view.tree.SelectDialog;

public class SelectDialogController {
	
	Model model;
	SelectDialog view;
	
	public SelectDialogController(Model model, SelectDialog view) {
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
			model.doTreeSelection(view.getSelected().addNewLinkChild(view.getShared()));
			view.dispose();
		}
	}
	
	class DoubleClickListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount() >= 2) {
				model.doTreeSelection(view.getSelected().addNewLinkChild(view.getShared()));
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
