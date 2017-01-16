package controller.elements;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import command.Invoker;
import command.elements.TextElementEditCommitCommand;
import command.elements.TextElementEditCancelCommand;
import model.Model;
import view.elements.TextElementEditDialog;

/**
 * Controller for the text element edit dialog.
 * 
 * @author geomaster
 */
public class TextElementEditDialogController {
	/**
	 * Model.
	 */
	public Model model;

	/**
	 * Edit dialog.
	 */
	public TextElementEditDialog dialog;

	/**
	 * Old contents of the text element.
	 */
	private String oldHtml;

	/**
	 * Constructor.
	 * 
	 * @param model
	 *            Model
	 * @param dialog
	 *            View
	 */
	public TextElementEditDialogController(Model model, TextElementEditDialog dialog) {
		this.model = model;
		this.dialog = dialog;
		this.oldHtml = dialog.getTextElement().getHtml();
		dialog.addOkListener(new OkListener());
		dialog.addCancelListener(new CancelListener());
		dialog.addKeyListener(new TextChangeListener());
	}

	/**
	 * Listener for the OK button in the dialog.
	 * 
	 * @author geomaster
	 *
	 */
	class OkListener implements ActionListener {
		/**
		 * Runs the edit commit command via the invoker.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			Invoker.getInstance()
					.executeCommand(new TextElementEditCommitCommand(model, dialog.getTextElement(), dialog.getHtml()));
			dialog.setVisible(false);
		}
	}

	/**
	 * Listener for the Cancel button in the dialog.
	 * 
	 * @author geomaster
	 *
	 */
	class CancelListener implements ActionListener {
		/**
		 * Runs the edit cancel command via the invoker.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			Invoker.getInstance()
					.executeCommand(new TextElementEditCancelCommand(model, dialog.getTextElement(), oldHtml));
			dialog.setVisible(false);
		}
	}

	/**
	 * Key listener for the text being changed.
	 * 
	 * @author geomaster
	 *
	 */
	class TextChangeListener implements KeyListener {
		@Override
		/**
		 * Called when a key is typed. Updates the dialog with the new text.
		 * 
		 * @param e
		 *            Key event
		 */
		public void keyTyped(KeyEvent e) {
			dialog.getTextElement().setHtml(dialog.getHtml() + (e.isActionKey() ? "" : e.getKeyChar()));
		}

		/**
		 * Does nothing.
		 */
		@Override
		public void keyPressed(KeyEvent e) {
		}

		/**
		 * Does nothing.
		 */
		@Override
		public void keyReleased(KeyEvent e) {
		}
	}
}
