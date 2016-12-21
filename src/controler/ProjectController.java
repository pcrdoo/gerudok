/***********************************************************************
 * Module:  ProjectController.java
 * Author:  Ognjen
 * Purpose: Defines the Class ProjectController
 ***********************************************************************/

package controler;

import model.Model;
import view.DocumentView;
import view.ProjectView;
import java.util.*;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

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
	          JTabbedPane sourceTabbedPane = (JTabbedPane) e.getSource();
	          DocumentView documentView = (DocumentView)sourceTabbedPane.getSelectedComponent();
	          System.out.println("iz desktopa selektovan dokument");
	          model.doTreeSelection(documentView.getDocument());
		}
		
	}
	
	class FrameListener implements InternalFrameListener {

		@Override
		public void internalFrameActivated(InternalFrameEvent e) {
			model.doTreeSelection(projectView.getProject());
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