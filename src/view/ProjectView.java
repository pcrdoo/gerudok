/***********************************************************************
 * Module:  ProjectView.java
 * Author:  Ognjen
 * Purpose: Defines the Class ProjectView
 ***********************************************************************/

package view;

import gerudok_observer.GObserver;
import gerudok_observer.GNotification;
import model.GeRuDocument;
import model.GeRuDocumentLink;
import model.Model;
import model.Page;
import model.Project;
import model.Slot;
import model.tree.GLink;
import model.tree.GNode;

import java.awt.Component;
import java.awt.Point;
import java.beans.PropertyVetoException;
import java.util.*;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeListener;
import javax.swing.event.InternalFrameListener;

import constants.Constants;
import controller.ProjectController;

public class ProjectView extends JInternalFrame implements GObserver {
	private ProjectController projectController;
	private Project project;
	private JTabbedPane documentTabs;
	private Model model;
	private boolean projectSelectionFromTree, documentSelectionFromTree;

	public ProjectView(Model model, Project project, Point p) {
		super(project.getName(), true, false, true, true);
		this.model = model;
		this.model.addObserver(this);
		this.setProject(project);
		this.project.addObserver(this);
		setSize(Constants.INTERNAL_FRAME_SIZE);
		setLocation(p);
		setVisible(true);

		// Tabbed Pane
		documentTabs = new JTabbedPane();
		documentTabs.setAlignmentX(0.0f);
		add(documentTabs);

		// Listener
		projectController = new ProjectController(model, this);
		projectSelectionFromTree = documentSelectionFromTree = false;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (notification == GNotification.PROJECT_OPEN) {
			try {
				this.setIcon(false);
			} catch (PropertyVetoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (notification == GNotification.GNODE_RENAME) {
			this.setTitle(this.getProject().getName());
		}
	}

	public void addNewChildView(GeRuDocument document) {
		GeRuDocumentView documentView = new GeRuDocumentView(model, document);
		documentTabs.addTab(documentView.getDocument().getName(), documentView);
		repaint();
		for (GNode child : document.getChildren()) {
			Page page = (Page) child;
			documentView.addNewChildView(page);
		}
	}

	public void updateSelection(Object[] path, int idx) {
		if (path.length > idx) {
			GeRuDocumentView documentView = findDocumentTab(
					(GeRuDocument) (path[idx] instanceof GeRuDocument ? path[idx] : ((GLink) path[idx]).getOriginal()));
			if (documentView == null)
				return;
			try {
				documentSelectionFromTree = true;
				documentTabs.setSelectedComponent(documentView);
				documentView.updateSelection(path, idx + 1);
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
	}

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
			// other stuff
		}
		return null;
	}

	public void attachFrameListener(InternalFrameListener frameListener) {
		this.addInternalFrameListener(frameListener);
	}

	public void attachTabChangeListener(ChangeListener tabChangeListener) {
		this.documentTabs.addChangeListener(tabChangeListener);
	}

	public boolean isProjectSelectionFromTree() {
		return projectSelectionFromTree;
	}

	public void setProjectSelectionFromTree(boolean projectSelectionFromTree) {
		this.projectSelectionFromTree = projectSelectionFromTree;
	}

	public boolean isDocumentSelectionFromTree() {
		return documentSelectionFromTree;
	}

	public void setDocumentSelectionFromTree(boolean documentSelectionFromTree) {
		this.documentSelectionFromTree = documentSelectionFromTree;
	}
}