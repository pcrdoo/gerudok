/***********************************************************************
 * Module:  ProjectController.java
 * Author:  Ognjen
 * Purpose: Defines the Class ProjectController
 ***********************************************************************/

package controler;

import model.Model;
import view.ProjectView;
import java.util.*;

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
			// TODO Auto-generated method stub
			
		}

		@Override
		public void internalFrameIconified(InternalFrameEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void internalFrameOpened(InternalFrameEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
  
}