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
import model.Project;

import java.awt.Component;
import java.awt.Point;
import java.beans.PropertyVetoException;
import java.util.*;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;

public class ProjectView extends JInternalFrame implements GObserver {
   private ProjectController projectController;
   private Project project;
   private JTabbedPane documentTabs;
   
   public ProjectView(Project project, Point p) {
	   super(project.getName(), true, false, true, true);
	   this.setProject(project);
	   this.project.addObserver(this);
	   setSize(500, 400);
	   setLocation(p);
	   setVisible(true);
	   
	   // Tabbed Pane
	   documentTabs = new JTabbedPane();
	   documentTabs.setAlignmentX(0.0f);
   }


	public Project getProject() {
		return project;
	}
	
	public void setProject(Project project) {
		this.project = project;
	}

	@Override
	public void update(GObserverNotification notification, Object obj) {
		if(notification == GObserverNotification.ADD) {
			DocumentView documentView = new DocumentView((Document)obj);
			documentTabs.addTab(documentView.getDocument().getName(), documentView);
			documentTabs.repaint();
		} else if(notification == GObserverNotification.DELETE) {
			int totalTabs = documentTabs.getTabCount();
			for(int i = 0; i < totalTabs; i++)
			{
			   Component tab = documentTabs.getTabComponentAt(i);
			   DocumentView documentView = (DocumentView)tab;
			   if(documentView.getDocument() == (Document)obj) {
				   documentTabs.remove(tab);
				   repaint();
				   break;
			   }
			   //other stuff
			}
		}
	}

}