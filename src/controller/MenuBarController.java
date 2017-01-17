package controller;

import model.Model;
import view.AboutDialog;
import view.MainView;
import view.MenuBarView;
import view.tree.SelectProjectDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import command.AddNewChildCommand;
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
 * Controller for the <code>MenuBarView</code>.
 * 
 * @author Igor Bakovic
 *
 */
public class MenuBarController {

	/**
	 * The main model.
	 */
	public Model model;

	/**
	 * The view to bind to.
	 */
	public MenuBarView menuBarView;

	/**
	 * AboutDialog view.
	 */
	public AboutDialog aboutDialog;

	/**
	 * @param model
	 *            the main model
	 * @param menuBarView
	 *            view to bind to
	 * 
	 */
	public MenuBarController(Model model, MenuBarView menuBarView) {
		super();
		this.model = model;
		this.menuBarView = menuBarView;
		this.aboutDialog = new AboutDialog(null);
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
	 * @return ActionListener which show <code>AboutDialog</code>.
	 * 
	 * @author Igor Bakovic
	 */
	public ActionListener getAboutActionListener() {
		return new ActionListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				aboutDialog.show();
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
				Invoker.getInstance().executeCommand(
						new AddNewChildCommand(model, MainView.getInstance().getTreeView().getSelectedNode()));
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
				Invoker.getInstance().executeCommand(
						new OpenProjectCommand(model, MainView.getInstance().getTreeView().getSelectedProject()));
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
				Invoker.getInstance().executeCommand(
						new CloseProjectCommand(model, MainView.getInstance().getTreeView().getSelectedProject()));
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
				Invoker.getInstance().executeCommand(
						new RenameCommand(model, MainView.getInstance().getTreeView().getSelectedNode()));
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
				Invoker.getInstance().executeCommand(
						new DeleteCommand(model, MainView.getInstance().getTreeView().getSelectedNode()));
			}
		};
	}

	/**
	 * @return ActionListener which executes
	 *         <code>AddNewLinkChildCommand</code>.
	 * 
	 * @author Igor Bakovic
	 */
	public ActionListener getShareActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				SelectProjectDialog sd = new SelectProjectDialog(MainView.getInstance().getTreeView().getSelectedNode(),
						model);
				sd.setVisible(true);
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
	 * @return ActionListener which executes
	 *         <code>SwitchWorkspaceCommand</code>.
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
}