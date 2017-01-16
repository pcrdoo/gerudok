package controller.tree;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import command.AddNewLinkChildCommand;
import command.Invoker;
import model.Model;
import model.tree.GNode;
import view.tree.SelectProjectDialog;

/**
 * Controller for the SelectProjectDialog.
 * 
 * @author Ognjen Djuricic
 *
 */
public class SelectProjectDialogController {

	/**
	 * Reference to the main model.
	 */
	Model model;
	/**
	 * Instance of the view for this controller.
	 */
	SelectProjectDialog view;

	/**
	 * Creates everything and sets view listeners.
	 * 
	 * @param model
	 *            The main model.
	 * @param view
	 *            The view for this controller.
	 */
	public SelectProjectDialogController(Model model, SelectProjectDialog view) {
		this.model = model;
		this.view = view;

		this.view.addSelectionChangedListener(new SelectionChangedListener());
		this.view.addBtnOKListener(new BtnOKListener());
		this.view.addDoubleClickListener(new DoubleClickListener());
	}

	/**
	 * Enables OK button.
	 * 
	 * @author Ognjen
	 *
	 */
	class SelectionChangedListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			view.enableBtnOK();
		}
	}

	/**
	 * Finalizes the process and closes the view.
	 * 
	 * @author Ognjen
	 *
	 */
	class BtnOKListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			for (GNode node : view.getSelected()) {
				AddNewLinkChildCommand command = new AddNewLinkChildCommand(model, node, view.getShared());
				Invoker.getInstance().executeCommand(command);
			}
			view.dispose();
		}
	}

	/**
	 * Finalizes the process and closes the view.
	 * 
	 * @author Ognjen
	 *
	 */
	class DoubleClickListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() >= 2) {
				AddNewLinkChildCommand command = new AddNewLinkChildCommand(model, view.getSelected().get(0),
						view.getShared());
				Invoker.getInstance().executeCommand(command);
				view.dispose();
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}
	}
}
