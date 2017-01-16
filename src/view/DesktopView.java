
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.beans.PropertyVetoException;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.tree.TreePath;

import constants.Constants;
import controller.DesktopController;
import gerudok_observer.GNotification;
import gerudok_observer.GObserver;
import model.GeRuDocument;
import model.Model;
import model.Project;
import model.Workspace;
import model.tree.GNode;

/**
 * Desktop area of the application.
 * 
 * @author Nikola Jovanovic
 *
 */
@SuppressWarnings("serial")
public class DesktopView extends JDesktopPane implements GObserver {

	/**
	 * The currently active workspace.
	 */
	private Workspace workspace;
	/**
	 * The main model.
	 */
	private Model model;

	/**
	 * The corresponding controller.
	 */
	private DesktopController desktopController;

	/**
	 * Constructor that forwards a reference to the main model.
	 * 
	 * @param model
	 *            the main model
	 */
	public DesktopView(Model model) {
		this.model = model;
		this.model.addObserver(this);
		this.workspace = Workspace.getInstance();
		this.workspace.addObserver(this);
		this.setBackground(Color.CYAN);
		this.add(new JLabel("Radni prostor"));
		setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);

		// Attaches the listeners.
		setDesktopController(new DesktopController(model, this));
	}

	/**
	 * Cascades the internal panels that represent projects.
	 */
	public void cascade() {
		// TODO (Igor Bakovic): Call this method from the tool bar
		int sz = getAllFrames().length;
		for (int i = 0; i < sz; i++) {
			getAllFrames()[i].setLocation(20 + i * 30, 20 + i * 30);
			getAllFrames()[i].setSize(500, 400);
			try {
				getAllFrames()[i].setSelected(true);
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Tiles the internal panels that represent projects vertically.
	 */
	public void tileVertically() {
		// TODO (Igor Bakovic): Call this method from the tool bar
		Dimension d = getSize();
		int sz = getAllFrames().length;
		int unitHeight = (int) (d.getHeight() / sz);
		for (int i = 0; i < sz; i++) {
			getAllFrames()[i].setLocation(0, i * unitHeight);
			getAllFrames()[i].setSize((int) d.getWidth(), unitHeight);
		}
	}

	/**
	 * Tiles the internal panels that represent projects horizontally.
	 */
	public void tileHorizontally() {
		// TODO (Igor Bakovic): Call this method from the tool bar
		Dimension d = getSize();
		int sz = getAllFrames().length;
		int unitWidth = (int) (d.getWidth() / sz);
		for (int i = 0; i < sz; i++) {
			getAllFrames()[i].setLocation(i * unitWidth, 0);
			getAllFrames()[i].setSize(unitWidth, (int) d.getHeight());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gerudok_observer.GObserver#update(gerudok_observer.GNotification,
	 * java.lang.Object)
	 */
	@Override
	public void update(GNotification notification, Object obj) {
		if (notification == GNotification.ADD) {
			if (obj instanceof Project) {
				Project project = (Project) obj;
				addNewChildView(project);
			}
		} else if (notification == GNotification.DELETE) {

			ProjectView projectView = findProjectView((Project) obj);
			try {
				try {
					projectView.setIcon(false);
					remove(projectView);
				} catch (PropertyVetoException e) {
					e.printStackTrace();
				}
				repaint();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		} else if (notification == GNotification.DESKTOP_SELECT) {
			TreePath treePath = (TreePath) obj;
			Object[] path = treePath.getPath();
			updateSelection(path, 1);
			if (treePath.getLastPathComponent() instanceof GNode) {
				model.doTreeSelection((GNode) treePath.getLastPathComponent());
			}
		}
	}

	/**
	 * Creates and attaches a new child view based on the received project.
	 * 
	 * @param project
	 *            the project to visualize
	 */
	public void addNewChildView(Project project) {
		Point p = new Point(10 + getAllFrames().length * Constants.CASCADE_OFFSET,
				10 + getAllFrames().length * Constants.CASCADE_OFFSET);
		ProjectView projectView = new ProjectView(model, project, p);
		this.add(projectView);
		repaint();
		try {
			projectView.setSelected(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		for (GNode child : project.getChildren()) {
			GeRuDocument document = (GeRuDocument) child;
			projectView.addNewChildView(document);
		}
	}

	/**
	 * Updates the selected project after the selection changed in the tree.
	 * Passes the selection array to the relevant child element afterwards.
	 * 
	 * @param path
	 *            array of nodes that represents the current tree selection
	 * @param idx
	 *            the index in the path array this view is interested in
	 */
	private void updateSelection(Object[] path, int idx) {
		if (path.length > idx) {
			ProjectView projectView = findProjectView((Project) path[idx]);
			if (projectView == null) {
				return;
			}
			try {
				if (this.getSelectedFrame() != projectView) {
					System.out.println("PROJECT ACTIVATED");
					projectView.setSelected(true);
				}
				projectView.updateSelection(path, idx + 1);
			} catch (NullPointerException e) {
				e.printStackTrace();
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Finds a child view that represents a project.
	 * 
	 * @param project
	 *            the project to look for
	 * @return the requested view
	 */
	public ProjectView findProjectView(Project project) {
		for (JInternalFrame internalFrame : getAllFrames()) {
			ProjectView projectView = (ProjectView) internalFrame;
			if (projectView.getProject() == project) {
				return projectView;
			}
		}
		return null;
	}

	/**
	 * @return the corresponding controller
	 */
	public DesktopController getDesktopController() {
		return desktopController;
	}

	/**
	 * @param desktopController
	 *            the controller to be attached
	 */
	public void setDesktopController(DesktopController desktopController) {
		this.desktopController = desktopController;
	}

}