
package view;

import java.awt.Component;
import java.awt.Point;
import java.beans.PropertyVetoException;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeListener;
import javax.swing.event.InternalFrameListener;

import constants.Constants;
import controller.ProjectController;
import gerudok_observer.GNotification;
import gerudok_observer.GObserver;
import model.GeRuDocument;
import model.GeRuDocumentLink;
import model.Model;
import model.Page;
import model.Project;
import model.tree.GLink;
import model.tree.GNode;

/**
 * The graphical representation of a project.
 * 
 * @author Nikola Jovanovic
 *
 */
@SuppressWarnings("serial")
public class ProjectView extends JInternalFrame implements GObserver {
	/**
	 * The project that this view is based on.
	 */
	private Project project;
	/**
	 * The main model.
	 */
	private Model model;
	/**
	 * The tabbed pane that holds documents.
	 */
	private JTabbedPane documentTabs;
	/**
	 * The corresponding controller.
	 */
	private ProjectController projectController;

	/**
	 * Constructor that forwards a reference to the main model and the project
	 * to be visualized.
	 * 
	 * @param model
	 *            the main model
	 * @param project
	 *            the project to be visualized
	 * @param position
	 *            the expected position of this view
	 */
	public ProjectView(Model model, Project project, Point position) {
		super(project.getName(), true, false, true, true);
		this.model = model;
		this.model.addObserver(this);
		this.setProject(project);
		this.project.addObserver(this);
		setSize(Constants.INTERNAL_FRAME_SIZE);
		setLocation(position);
		setVisible(true);

		// Adds the tabbed pane.
		documentTabs = new JTabbedPane();
		documentTabs.setAlignmentX(0.0f);
		add(documentTabs);

		// Attaches the listeners.
		projectController = new ProjectController(model, this);
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
			GeRuDocument document = null;
			if (obj instanceof GeRuDocument) {
				document = (GeRuDocument) obj;
			} else if (obj instanceof GeRuDocumentLink) {
				document = (GeRuDocument) ((GLink) obj).getOriginal();
			}
			if (document != null) {
				addNewChildView(document);
			}
		} else if (notification == GNotification.DELETE) {
			GeRuDocument document = (GeRuDocument) (obj instanceof GeRuDocument ? obj : ((GLink) obj).getOriginal());
			GeRuDocumentView documentView = findDocumentTab(document);
			try {
				documentTabs.remove(documentView);
				repaint();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		} else if (notification == GNotification.PROJECT_CLOSE) {
			try {
				this.setIcon(true);
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
		} else if (notification == GNotification.PROJECT_OPEN) {
			try {
				this.setIcon(false);
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
		} else if (notification == GNotification.GNODE_RENAME) {
			this.setTitle(this.getProject().getName());
		}
	}

	/**
	 * Creates and attaches a new child view based on the received document.
	 * 
	 * @param document
	 *            the document to visualize
	 */
	public void addNewChildView(GeRuDocument document) {
		GeRuDocumentView documentView = new GeRuDocumentView(model, document);
		documentTabs.addTab(documentView.getDocument().getName(), documentView);
		repaint();
		for (GNode child : document.getChildren()) {
			Page page = (Page) child;
			documentView.addNewChildView(page);
		}
	}

	/**
	 * Updates the selected document after the selection changed in the tree.
	 * Passes the selection array to the relevant child element afterwards.
	 * 
	 * @param path
	 *            array of nodes that represents the current tree selection
	 * @param idx
	 *            the index in the path array this view is interested in
	 */
	public void updateSelection(Object[] path, int idx) {
		if (path.length > idx) {
			GeRuDocumentView documentView = findDocumentTab(
					(GeRuDocument) (path[idx] instanceof GeRuDocument ? path[idx] : ((GLink) path[idx]).getOriginal()));
			if (documentView == null)
				return;
			try {
				if (documentTabs.getSelectedComponent() != documentView) {
					documentTabs.setSelectedComponent(documentView);
				}
				documentView.updateSelection(path, idx + 1);
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Finds a child tab that represents a document.
	 * 
	 * @param document
	 *            the document to look for
	 * @return the requested tab
	 */
	private GeRuDocumentView findDocumentTab(GeRuDocument document) {
		int totalTabs = documentTabs.getTabCount();
		for (int i = 0; i < totalTabs; i++) {
			Component tab = documentTabs.getComponentAt(i);
			if (tab instanceof GeRuDocumentView) {
				GeRuDocumentView documentView = (GeRuDocumentView) tab;
				if (documentView.getDocument() == document) {
					return documentView;
				}
			}
		}
		return null;
	}

	/**
	 * Attaches an InternalFrameListener.
	 * 
	 * @param frameListener
	 *            the listener to attach
	 */
	public void attachFrameListener(InternalFrameListener frameListener) {
		this.addInternalFrameListener(frameListener);
	}

	/**
	 * Attaches a ChangeListener.
	 * 
	 * @param tabChangeListener
	 *            the listener to attach
	 */
	public void attachTabChangeListener(ChangeListener tabChangeListener) {
		this.documentTabs.addChangeListener(tabChangeListener);
	}

	/**
	 * Retrieves the project that this view is based on.
	 * 
	 * @return the project that this view is based on
	 */
	public Project getProject() {
		return project;
	}

	/**
	 * Sets the project that this view is based on.
	 * 
	 * @param project
	 *            the project that this view is based on
	 */
	public void setProject(Project project) {
		this.project = project;
	}

	/**
	 * Retrieves the corresponding controller.
	 * 
	 * @return the corresponding controller
	 */
	public ProjectController ProjectController() {
		return projectController;
	}

	/**
	 * Attaches the controller.
	 * 
	 * @param projectController
	 *            the controller to attach
	 */
	public void setProjectController(ProjectController projectController) {
		this.projectController = projectController;
	}

}