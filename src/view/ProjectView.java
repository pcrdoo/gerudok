/***********************************************************************
 * Module:  ProjectView.java
 * Author:  Ognjen
 * Purpose: Defines the Class ProjectView
 ***********************************************************************/

package view;

import gerudok_observer.GObserver;
import gerudok_observer.GObserverNotification;
import model.Document;
import model.Model;
import model.Project;

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
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@Override
	public void update(GObserverNotification notification, Object obj) {
		if (notification == GObserverNotification.ADD) {
			DocumentView documentView = new DocumentView(model, (Document) obj);
			documentTabs.addTab(documentView.getDocument().getName(), documentView);
			repaint();
		} else if (notification == GObserverNotification.DELETE) {
			DocumentView documentView = findDocumentTab((Document) obj);
			try {
				documentTabs.remove(documentView);
				repaint();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		} else if (notification == GObserverNotification.PROJECT_CLOSE) {
			try {
				this.setIcon(true);
			} catch (PropertyVetoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (notification == GObserverNotification.PROJECT_OPEN) {
			try {
				this.setIcon(false);
			} catch (PropertyVetoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (notification == GObserverNotification.GNODE_RENAME) {
				this.setTitle(this.getProject().getName());
		}
	}

	public void updateSelection(Object[] path, int idx) {
		if (path.length > idx) {
			DocumentView documentView = findDocumentTab((Document) path[idx]);
			if (documentView == null)
				return;
			try {
				documentTabs.setSelectedComponent(documentView);
				documentView.updateSelection(path, idx + 1);
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
	}

	private DocumentView findDocumentTab(Document document) {
		System.out.println("trazimo ime " + document.getName());
		int totalTabs = documentTabs.getTabCount();
		for (int i = 0; i < totalTabs; i++) {
			Component tab = documentTabs.getComponentAt(i);
			if (tab instanceof DocumentView) {
				DocumentView documentView = (DocumentView) tab;
				System.out.println("trenutno ime " + documentView.getDocument().getName());
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
}