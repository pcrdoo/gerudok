package controller.tree;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JLabel;

import model.Model;
import model.tree.GNode;
import view.tree.GTreeCellEditor;

public class GTreeCellEditorController {
	
	GTreeCellEditor view;
	Model model;
	
	public GTreeCellEditorController(GTreeCellEditor view, Model model) {
		this.view = view;
		this.model = model;
		this.view.setNameFocusListener(new NameFocusListener());
	}
	
	class NameFocusListener implements FocusListener {

		@Override
		public void focusGained(FocusEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void focusLost(FocusEvent e) {
			view.getNode().setName(view.getNameText());
		}
	}
}
