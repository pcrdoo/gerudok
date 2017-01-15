package controller.tree;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JLabel;

import model.Model;
import model.tree.GNode;
import view.tree.GTreeCellEditor;

/**
 * Controller for the GTreeCellEditor.
 * 
 * @author Ognjen Djuricic
 *
 */
public class GTreeCellEditorController {

	/**
	 * Reference to the main model.
	 */
	Model model;
	/**
	 * Instance of the view for this controller.
	 */
	GTreeCellEditor view;

	/**
	 * Creates everything and sets view listeners.
	 * 
	 * @param view
	 *            The view for this controller.
	 * @param model
	 *            The main model.
	 */
	public GTreeCellEditorController(GTreeCellEditor view, Model model) {
		this.view = view;
		this.model = model;
		this.view.setNameFocusListener(new NameFocusListener());
	}

	/**
	 * Sets the node name when focus is lost.
	 * 
	 * @author Ognjen Djuricic
	 *
	 */
	class NameFocusListener implements FocusListener {

		@Override
		public void focusGained(FocusEvent arg0) {
		}

		@Override
		public void focusLost(FocusEvent e) {
			view.getNode().setName(view.getNameText());
		}
	}
}
