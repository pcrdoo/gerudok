/***********************************************************************
 * Module:  ProjectView.java
 * Author:  Ognjen
 * Purpose: Defines the Class ProjectView
 ***********************************************************************/

package view;

import controler.ProjectController;
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
import javax.swing.event.InternalFrameListener;

public class ProjectView extends JInternalFrame implements GObserver {
	private ProjectController projectController;
	private Project project;
	private JTabbedPane documentTabs;
	private Model model;
	
	public ProjectView(Model model, Project project, Point p) {
		super(project.getName(), true, false, true, true);
		this.model = model;
		this.setProject(project);
		this.project.addObserver(this);
		setSize(500, 400);
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
			Component tab = findDocumentTab((Document) obj);
			try {
				documentTabs.remove(tab);
				repaint();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
	}

	public void updateSelection(Object[] path, int idx) {
		if (path.length > idx) {
			Component tab = findDocumentTab((Document)path[idx]);
			try {
				documentTabs.setSelectedComponent(tab);
				((DocumentView)tab).updateSelection(path, idx + 1);
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
	}

	private Component findDocumentTab(Document document) {
		int totalTabs = documentTabs.getTabCount();
		for (int i = 0; i < totalTabs; i++) {
			Component tab = documentTabs.getComponentAt(i);
			DocumentView documentView = (DocumentView) tab;
			if (documentView.getDocument() == document) {
				return tab;
			}
			// other stuff
		}
		return null;
	}

	public void attachFrameListener(InternalFrameListener frameListener) {
		this.addInternalFrameListener(frameListener);
	}
}