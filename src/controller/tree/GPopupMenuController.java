package controller.tree;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import command.AddNewChildCommand;
import command.AddNewChildElementCommand;
import command.CloseProjectCommand;
import command.DeleteCommand;
import command.ElementEditInitCommand;
import command.Invoker;
import command.LoadProjectCommand;
import command.OpenProjectCommand;
import command.RenameCommand;
import command.SaveAsProjectCommand;
import command.SaveProjectCommand;
import command.SwitchWorkspaceCommand;
import command.TreeSelectCommand;
import model.GeRuDocument;
import model.Model;
import model.Project;
import model.ElementContainer;
import model.ElementType;
import model.Element;
import model.tree.GNode;
import view.tree.GPopupMenu;
import view.tree.SelectDocumentDialog;
import view.tree.SelectProjectDialog;

/**
 * Controller for the GPopupMenuDialog, contains all its operations.
 * 
 * @author Ognjen Djuricic
 *
 */
public class GPopupMenuController {

	/**
	 * Reference to the main model.
	 */
	Model model;
	/**
	 * Instance of the view for this controller.
	 */
	GPopupMenu view;

	/**
	 * Creates everything and sets view listeners.
	 * 
	 * @param model
	 *            The main model.
	 * @param view
	 *            The view for this controller.
	 */
	public GPopupMenuController(Model model, GPopupMenu view) {
		this.model = model;
		this.view = view;

		this.view.setAddNewListener(new AddNewListener());
		this.view.setAddNewGraphicElementListener(new AddNewGraphicElementListener());
		this.view.setAddNewTextElementListener(new AddNewTextElementListener());
		this.view.setAddNewSoundElementListener(new AddNewSoundElementListener());
		this.view.setDeleteListener(new DeleteListener());
		this.view.setRenameListener(new RenameListener());
		this.view.setSwitchWorkspaceListener(new SwitchWorkspaceListener());
		this.view.setOpenCloseListener(new OpenCloseListener());
		this.view.setShareListener(new ShareListener());
		this.view.setElementEditListener(new ElementEditListener());
		this.view.setAddFromFreeListener(new AddFromFreeListener());
		this.view.setSaveProjectListener(new SaveProjectListener());
		this.view.setSaveAsProjectListener(new SaveAsProjectListener());
		this.view.setImportProjectListener(new ImportProjectListener());
	}

	/**
	 * Executes the AddNewChildCommand.
	 * 
	 * @author Ognjen Djuricic
	 *
	 */
	class AddNewListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Invoker.getInstance().executeCommand(new AddNewChildCommand(model, view.getSelectedNode()));
		}
	}

	/**
	 * Executes the AddNewChildElementCommand.
	 * 
	 * @author Ognjen Djuricic
	 *
	 */
	class AddNewGraphicElementListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Invoker.getInstance().executeCommand(new AddNewChildElementCommand(model,
					(ElementContainer) view.getSelectedNode(), ElementType.GRAPHIC));
		}
	}

	/**
	 * Executes the AddNewChildElementCommand.
	 * 
	 * @author Ognjen Djuricic
	 *
	 */
	class AddNewTextElementListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Invoker.getInstance().executeCommand(
					new AddNewChildElementCommand(model, (ElementContainer) view.getSelectedNode(), ElementType.TEXT));
		}
	}

	/**
	 * Executes the AddNewChildElementCommand.
	 * 
	 * @author Ognjen Djuricic
	 *
	 */
	class AddNewSoundElementListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Invoker.getInstance().executeCommand(
					new AddNewChildElementCommand(model, (ElementContainer) view.getSelectedNode(), ElementType.SOUND));
		}
	}

	/**
	 * Executes the DeleteCommand.
	 * 
	 * @author Ognjen Djuricic
	 *
	 */
	class DeleteListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Invoker.getInstance().executeCommand(new DeleteCommand(model, view.getSelectedNode()));
		}
	}

	/**
	 * Executes the RenameCommand.
	 * 
	 * @author Ognjen Djuricic
	 *
	 */
	class RenameListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Invoker.getInstance().executeCommand(new RenameCommand(model, view.getSelectedNode()));
		}
	}

	/**
	 * Executes the SwitchWorkspaceCommand.
	 * 
	 * @author Ognjen Djuricic
	 *
	 */
	class SwitchWorkspaceListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Invoker.getInstance().executeCommand(new SwitchWorkspaceCommand(model, view.getSelectedNode()));
		}
	}

	/**
	 * Executes the OpenProjectCommand or CloseProjectCommand.
	 * 
	 * @author Ognjen Djuricic
	 *
	 */
	class OpenCloseListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Project project = (Project) view.getSelectedNode();
			if (project.isOpened()) {
				Invoker.getInstance().executeCommand(new CloseProjectCommand(model, project));
			} else {
				Invoker.getInstance().executeCommand(new OpenProjectCommand(model, project));
			}
			// model.getTreeModel().reload();
		}
	}

	/**
	 * Executes the ElementEditInitCommand.
	 * 
	 * @author Ognjen Djuricic
	 *
	 */
	class ElementEditListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Invoker.getInstance().executeCommand(new ElementEditInitCommand(model, (Element) view.getSelectedNode()));

		}
	}

	/**
	 * Creates and shows the SelectProjectDialog.
	 * 
	 * @author Ognjen Djuricic
	 *
	 */
	class ShareListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			SelectProjectDialog sd = new SelectProjectDialog(view.getSelectedNode(), model);
			sd.show();
		}
	}

	/**
	 * Creates and shows the SelectDocumentDialog.
	 * 
	 * @author Ognjen Djuricic
	 *
	 */
	class AddFromFreeListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			SelectDocumentDialog sd = new SelectDocumentDialog(view.getSelectedNode(), model);
			sd.show();
		}
	}

	/**
	 * Executes the SaveProjectCommand.
	 * 
	 * @author Ognjen Djuricic
	 *
	 */
	class SaveProjectListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Invoker.getInstance().executeCommand(new SaveProjectCommand(model));
		}
	}

	/**
	 * Executes the SaveAsProjectCommand.
	 * 
	 * @author Ognjen Djuricic
	 *
	 */
	class SaveAsProjectListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Invoker.getInstance().executeCommand(new SaveAsProjectCommand(model));
		}
	}

	/**
	 * Executes the LoadProjectCommand.
	 * 
	 * @author Ognjen Djuricic
	 *
	 */
	class ImportProjectListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Invoker.getInstance().executeCommand(new LoadProjectCommand(model));
		}
	}
}
