package controller;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

import model.Model;
import view.DesktopView;

/**
 * Makes sure that the selection in the WorkspaceTree matches the focus of the
 * DesktopView.
 * 
 * @author Ognjen Djuricic
 *
 */
public class TreeListener implements TreeSelectionListener {
	/**
	 * Reference to the main model.
	 */
	private Model model;

	/**
	 * Constructor that sets the model.
	 * 
	 * @param model
	 *            The main model.
	 */
	public TreeListener(Model model) {
		this.model = model;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.swing.event.TreeSelectionListener#valueChanged(javax.swing.event.
	 * TreeSelectionEvent)
	 */
	@Override
	public void valueChanged(TreeSelectionEvent e) {
		TreePath path = e.getPath();
		model.updateSelection(path);
	}
}