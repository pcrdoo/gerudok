package controller.tree;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import command.AddNewChildCommand;
import command.AddNewChildElementCommand;
import command.CloseProjectCommand;
import command.DeleteCommand;
import command.Invoker;
import command.LoadProjectCommand;
import command.OpenProjectCommand;
import command.RenameCommand;
import command.SaveAsProjectCommand;
import command.SaveProjectCommand;
import command.SwitchWorkspaceCommand;
import command.ElementEditInitCommand;
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

public class GPopupMenuController {
	
	Model model;
	GPopupMenu view;
	
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
	
	class AddNewListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Invoker.getInstance().executeCommand(new AddNewChildCommand(model, view.getSelectedNode()));
		}
	}
	
	class AddNewGraphicElementListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Invoker.getInstance().executeCommand(new AddNewChildElementCommand(model, (ElementContainer)view.getSelectedNode(), ElementType.GRAPHIC));
		}
	}
	
	class AddNewTextElementListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Invoker.getInstance().executeCommand(new AddNewChildElementCommand(model, (ElementContainer)view.getSelectedNode(), ElementType.TEXT));
		}
	}
	
	class AddNewSoundElementListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Invoker.getInstance().executeCommand(new AddNewChildElementCommand(model, (ElementContainer)view.getSelectedNode(), ElementType.SOUND));
		}
	}
	
	
	class DeleteListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Invoker.getInstance().executeCommand(new DeleteCommand(model, view.getSelectedNode()));
		}
	}
	
	class RenameListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Invoker.getInstance().executeCommand(new RenameCommand(model, view.getSelectedNode()));
		}
	}
	
	class SwitchWorkspaceListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Invoker.getInstance().executeCommand(new SwitchWorkspaceCommand(model, view.getSelectedNode()));
		}
	}
	
	class OpenCloseListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Project project = (Project)view.getSelectedNode();
			if(project.isOpened()) {
				Invoker.getInstance().executeCommand(new CloseProjectCommand(model, project));
			} else {
				Invoker.getInstance().executeCommand(new OpenProjectCommand(model, project));
			}
			// model.getTreeModel().reload();
		}
	}
	
	class ElementEditListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Invoker.getInstance().executeCommand(new ElementEditInitCommand(model, (Element) view.getSelectedNode()));

		}		
	}
	
	class ShareListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			SelectProjectDialog sd = new SelectProjectDialog(view.getSelectedNode(), model);
			sd.show();
		}
	}
	
	class AddFromFreeListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			SelectDocumentDialog sd = new SelectDocumentDialog(view.getSelectedNode(), model);
			sd.show();
		}
	}
	
	class SaveProjectListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Invoker.getInstance().executeCommand(new SaveProjectCommand());
		}
	}
	
	class SaveAsProjectListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Invoker.getInstance().executeCommand(new SaveAsProjectCommand());
		}
	}
	
	class ImportProjectListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Invoker.getInstance().executeCommand(new LoadProjectCommand(model));
		}
	}
}
