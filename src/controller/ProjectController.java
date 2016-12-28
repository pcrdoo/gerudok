/***********************************************************************
 * Module:  ProjectController.java
 * Author:  Ognjen
 * Purpose: Defines the Class ProjectController
 ***********************************************************************/

package controller;

import model.Model;
import view.DocumentView;
import view.ProjectView;
import java.util.*;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import command.Invoker;
import command.TreeSelectCommand;

/** @pdOid bf767906-23cd-405d-a24d-dd73d7851411 */
public class ProjectController {
	private Model model;
	private ProjectView projectView;
	
	public ProjectController(Model model, ProjectView view) {
		this.model = model;
		this.projectView = view;
		projectView.attachFrameListener(new FrameListener());
		projectView.attachTabChangeListener(new TabChangeListener());
	}
	      
	class TabChangeListener implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent e) {
	          if(projectView.isDocumentSelectionFromTree()) {
					projectView.setDocumentSelectionFromTree(false);
				} else {
		          JTabbedPane sourceTabbedPane = (JTabbedPane) e.getSource();
		          DocumentView documentView = (DocumentView)sourceTabbedPane.getSelectedComponent();
		           if(documentView != null) {
		        	   TreeSelectCommand command = new TreeSelectCommand(model, documentView.getDocument());
					   Invoker.getInstance().executeCommand(command);
		           }
				}
		}
		
	}
	
	class FrameListener implements InternalFrameListener {

		@Override
		public void internalFrameActivated(InternalFrameEvent e) {
			// TODO: Naci lepsi nacin da se ovo popravi nakon sto se refaktorisu komande.
			if(projectView.isProjectSelectionFromTree()) {
				projectView.setProjectSelectionFromTree(false);
			} else {
				Invoker.getInstance().executeCommand(new TreeSelectCommand(model, projectView.getProject()));
			}
		}

		@Override
		public void internalFrameClosed(InternalFrameEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void internalFrameClosing(InternalFrameEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void internalFrameDeactivated(InternalFrameEvent e) {
			// TODO Auto-generated method stub
			
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
			// TODO Auto-generated method stub
			
		}
	}
  
}
