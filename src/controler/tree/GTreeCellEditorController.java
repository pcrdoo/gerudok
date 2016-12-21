package controler.tree;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JLabel;

import model.tree.GNode;
import view.tree.GTreeCellEditor;

public class GTreeCellEditorController {
	
	GTreeCellEditor view;
	
	public GTreeCellEditorController(GTreeCellEditor view) {
		this.view = view;
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
