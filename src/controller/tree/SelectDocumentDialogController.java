package controller.tree;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import command.Invoker;
import command.TreeSelectCommand;
import model.Model;
import model.tree.GNode;
import view.tree.SelectDocumentDialog;

/**
 * Controller for the SelectDocumentDialog.
 * 
 * @author Ognjen Djuricic
 *
 */
public class SelectDocumentDialogController {

	/**
	 * Reference to the main model.
	 */
	Model model;
	/**
	 * Instance of the view for this controller.
	 */
	SelectDocumentDialog view;

	/**
	 * Creates everything and sets view listeners.
	 * 
	 * @param model
	 *            The main model.
	 * @param view
	 *            The view for this controller.
	 */
	public SelectDocumentDialogController(Model model, SelectDocumentDialog view) {
		this.model = model;
		this.view = view;

		this.view.addSelectionChangedListener(new SelectionChangedListener());
		this.view.addBtnOKListener(new BtnOKListener());
		this.view.addDoubleClickListener(new DoubleClickListener());
	}

	/**
	 * Enables OK button.
	 * 
	 * @author Ognjen Djuricic
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
	 * @author Ognjen Djuricic
	 *
	 */
	class BtnOKListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			List<GNode> selected = view.getSelected();

			for (GNode node : selected) {
				model.getFreeNodes().remove(node);
				model.doReloadFreeNodes();
				view.getParentNode().addChild(node);
				model.getTreeModel().reload();
			}

			Invoker.getInstance().executeCommand(new TreeSelectCommand(model, selected.get(0)));
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
				GNode selected = view.getSelected().get(0);
				model.getFreeNodes().remove(selected);
				model.doReloadFreeNodes();
				view.getParentNode().addChild(selected);
				model.getTreeModel().reload();
				Invoker.getInstance().executeCommand(new TreeSelectCommand(model, selected));
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
