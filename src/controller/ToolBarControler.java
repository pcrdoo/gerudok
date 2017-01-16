package controller;

import model.GeRuDocument;
import model.Model;
import model.Project;
import model.Slot;
import model.tree.GNode;
import view.MainView;
import view.MenuBarView;
import view.ToolBarView;
import view.tree.GPopupMenu;
import view.tree.SelectProjectDialog;
import view.tree.TreeView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import command.AddNewChildCommand;
import command.AddNewLinkChildCommand;
import command.CloseProjectCommand;
import command.DeleteCommand;
import command.Invoker;
import command.LoadProjectCommand;
import command.NewWorkspaceCommand;
import command.OpenProjectCommand;
import command.RenameCommand;
import command.SaveAsProjectCommand;
import command.SaveProjectCommand;
import command.SaveWorkspaceCommand;
import command.SwitchWorkspaceCommand;
/**
 * Controller for the <code>ToolBarView</code>.
 * 
 * @author Igor Bakovic
 *
 */
public class ToolBarControler {
	
	/**
	 * The main model.
	 */
	public Model model;
	
	/**
	 * The view to bind to.
	 */
	public ToolBarView toolBarView;

	/**
	    * @param model
	    * 		the main model
	    * @param ToolBarView
	    * 		view to bind to
	    * 		
	    */
	public ToolBarControler(Model model, ToolBarView toolBarView) {
		super();
		this.model = model;
		this.toolBarView = toolBarView;
	}

	/**
	 * @return ActionListener which executes <code>SaveProjectCommand</code>.
	 * 
	 * @author Igor Bakovic
	 */
	public ActionListener getSaveActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Invoker.getInstance().executeCommand(new SaveProjectCommand(model));
			}
		};
	}

	/**
	 * @return ActionListener which executes <code>SaveAsProjectCommand</code>.
	 * 
	 * @author Igor Bakovic
	 */
	public ActionListener getSaveAsActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Invoker.getInstance().executeCommand(new SaveAsProjectCommand(model));
			}
		};
	}

	/**
	 * @return ActionListener which executes <code>LoadProjectCommand</code>.
	 * 
	 * @author Igor Bakovic
	 */
	public ActionListener getLoadActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Invoker.getInstance().executeCommand(new LoadProjectCommand(model));
			}
		};
	}

	/**
	 * @return ActionListener which executes <code>AddNewChildCommand</code>.
	 * 
	 * @author Igor Bakovic
	 */
	public ActionListener getAddActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Object obj = model.getSelectedObject();
				Invoker.getInstance().executeCommand(new AddNewChildCommand(model, (GNode) obj));
			}
		};
	}

	/**
	 * @return ActionListener which executes <code>OpenProjectCommand</code>.
	 * 
	 * @author Igor Bakovic
	 */
	public ActionListener getOpenProjectActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Object obj = model.getSelectedObject();
				if (obj instanceof Project) {
					Invoker.getInstance().executeCommand(new OpenProjectCommand(model, (Project) obj));
				}
			}
		};
	}

	/**
	 * @return ActionListener which executes <code>CloseProjectCommand</code>.
	 * 
	 * @author Igor Bakovic
	 */
	public ActionListener getCloseProjectActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Object obj = model.getSelectedObject();
				if (obj instanceof Project) {
					Invoker.getInstance().executeCommand(new CloseProjectCommand(model, (Project) obj));
				}
			}
		};
	}
	
	/**
	 * @return ActionListener which executes <code>RenameCommand</code>.
	 * 
	 * @author Igor Bakovic
	 */
	public ActionListener getRenameActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Object obj = model.getSelectedObject();
				Invoker.getInstance().executeCommand(new RenameCommand(model, (GNode) obj));
			}
		};
	}

	/**
	 * @return ActionListener which executes <code>DeleteCommand</code>.
	 * 
	 * @author Igor Bakovic
	 */
	public ActionListener getDeleteActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Object obj = model.getSelectedObject();
				Invoker.getInstance().executeCommand(new DeleteCommand(model, (GNode) obj));
			}
		};
	}

	/**
	 * @return ActionListener which executes <code>AddNewLinkChildCommand</code>.
	 * 
	 * @author Igor Bakovic
	 */
	public ActionListener getShareActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Object obj = model.getSelectedObject();
				if (obj instanceof GeRuDocument) {
					SelectProjectDialog sd = new SelectProjectDialog((GNode) obj, model);
					sd.show();
				}
			}
		};
	}

	/**
	 * @return ActionListener which executes <code>NewWorkspaceCommand</code>.
	 * 
	 * @author Igor Bakovic
	 */
	public ActionListener getNewWorkspaceListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Invoker.getInstance().executeCommand(new NewWorkspaceCommand(model));
			}
		};
	}
	
	/**
	 * @return ActionListener which executes <code>SwitchWorkspaceCommand</code>.
	 * 
	 * @author Igor Bakovic
	 */
	public ActionListener getSwitchWorkspaceListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Invoker.getInstance().executeCommand(new SwitchWorkspaceCommand(model));
			}
		};
	}
	
	/**
	 * 
	 * @return ActionListener which executes cascade function from <code>DesktopView</code>.
	 * 
	 * @author Igor Bakovic
	 */
	public ActionListener getCascadeActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				MainView.getInstance().getDesktopView().cascade();
			}
		};
	}

	/**
	 * 
	 * @return ActionListener which executes tile horizontally function from <code>DesktopView</code>.
	 * 
	 * @author Igor Bakovic
	 */
	public ActionListener getTileHorizontalyActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				MainView.getInstance().getDesktopView().tileHorizontally();
			}
		};
	}

	/**
	 * 
	 * @return ActionListener which executes tile vertically function from <code>DesktopView</code>.
	 * 
	 * @author Igor Bakovic
	 */
	public ActionListener getTileVerticalyActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				MainView.getInstance().getDesktopView().tileVertically();
			}
		};
	}
	
	/**
	 * @return ActionListener which executes <code>SaveWorkspaceCommand</code>.
	 * 
	 * @author Igor Bakovic
	 */
	public ActionListener getSaveWorkspaceListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Invoker.getInstance().executeCommand(new SaveWorkspaceCommand(model));
			}
		};
	}

}