
package controller;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import command.Invoker;
import command.TreeSelectCommand;
import model.Model;
import view.GeRuDocumentView;
import view.ProjectView;

/**
 * Controller for projects.
 * 
 * @author Nikola Jovanovic
 *
 */
public class ProjectController {
	/**
	 * The main model.
	 */
	private Model model;
	/**
	 * The view to bind to.
	 */
	private ProjectView projectView;

	/**
	 * @param model
	 *            the main model
	 * @param view
	 *            the view to bind to
	 */
	public ProjectController(Model model, ProjectView view) {
		this.model = model;
		this.projectView = view;
		projectView.attachFrameListener(new FrameListener());
		projectView.attachTabChangeListener(new TabChangeListener());
	}

	/**
	 * A listener that tracks tab changes and maintains currently selected
	 * document.
	 * 
	 * @author Nikola Jovanovic
	 *
	 */
	class TabChangeListener implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent e) {
			JTabbedPane sourceTabbedPane = (JTabbedPane) e.getSource();
			GeRuDocumentView documentView = (GeRuDocumentView) sourceTabbedPane.getSelectedComponent();
			if (documentView != null) {
				TreeSelectCommand command = new TreeSelectCommand(model, documentView.getDocument());
				Invoker.getInstance().executeCommand(command);
			}
		}

	}

	/**
	 * A listener that tracks project events and deals with project selection,
	 * opening and closing.
	 * 
	 * @author Nikola Jovanovic
	 *
	 */
	class FrameListener implements InternalFrameListener {

		@Override
		public void internalFrameActivated(InternalFrameEvent e) {
			Invoker.getInstance().executeCommand(new TreeSelectCommand(model, projectView.getProject()));
		}

		@Override
		public void internalFrameClosed(InternalFrameEvent e) {
		}

		@Override
		public void internalFrameClosing(InternalFrameEvent e) {
		}

		@Override
		public void internalFrameDeactivated(InternalFrameEvent e) {
		}

		@Override
		public void internalFrameDeiconified(InternalFrameEvent e) {
			projectView.getProject().setOpened(true);
			model.getTreeModel().reload();

		}

		@Override
		public void internalFrameIconified(InternalFrameEvent e) {
			projectView.getProject().setOpened(false);
			model.getTreeModel().reload();
		}

		@Override
		public void internalFrameOpened(InternalFrameEvent e) {
		}
	}

}
