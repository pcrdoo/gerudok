package controller.elements;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import command.Invoker;
import command.elements.GraphicElementInvoker;
import command.elements.GraphicElementEditCancelCommand;
import command.elements.GraphicElementEditCommitCommand;
import model.Model;
import model.elements.GraphicShape;
import view.elements.GraphicElementEditDialog;

/**
 * Controller for the graphic element edit dialog.
 * 
 * @author geomaster
 *
 */
public class GraphicElementEditDialogController {
	/**
	 * Model.
	 */
	public Model model;

	/**
	 * View of the dialog.
	 */
	public GraphicElementEditDialog dialog;

	/**
	 * List of shapes previously defining the graphic element. Used for
	 * implementing cancel functionality.
	 */
	private List<GraphicShape> previousShapes = new ArrayList<>();

	/**
	 * Constructor.
	 * 
	 * @param model
	 *            Model
	 * @param dialog
	 *            View
	 */
	public GraphicElementEditDialogController(Model model, GraphicElementEditDialog dialog) {
		this.model = model;
		this.dialog = dialog;
		dialog.addOkListener(new OkListener());
		dialog.addCancelListener(new CancelListener());

		for (GraphicShape s : dialog.getGraphicElement().getShapes()) {
			previousShapes.add(s.clone());
		}

		GraphicElementInvoker.getInstance().startSession();
	}

	/**
	 * Listener for the OK button click in the dialog.
	 * 
	 * @author geomaster
	 *
	 */
	class OkListener implements ActionListener {
		/**
		 * Executes the command which commits the edit.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			Invoker.getInstance()
					.executeCommand(new GraphicElementEditCommitCommand(model, dialog.getGraphicElement()));
			dialog.setVisible(false);
		}
	}

	/**
	 * Listener for the Cancel button click in the dialog.
	 * 
	 * @author geomaster
	 *
	 */
	class CancelListener implements ActionListener {
		/**
		 * Executes the command which reverts the edit.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			Invoker.getInstance().executeCommand(
					new GraphicElementEditCancelCommand(model, dialog.getGraphicElement(), previousShapes));
			GraphicElementInvoker.getInstance().abortSession();
			dialog.setVisible(false);
		}
	}
}
