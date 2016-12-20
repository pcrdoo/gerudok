/***********************************************************************
 * Module:  ProjectView.java
 * Author:  Ognjen
 * Purpose: Defines the Class ProjectView
 ***********************************************************************/

package view;

import controler.ProjectController;
import model.Project;

import java.util.*;

import javax.swing.JInternalFrame;

public class ProjectView extends JInternalFrame {
   private ArrayList<DocumentView> documentView;
   private ProjectController projectController;
   private Project project;
   
   public ProjectView(Project project) {
	   super("Project #X", true, false, true, true);
	   this.project = project;
	   setSize(400, 400);
	   setLocation(20, 20);
	   setVisible(true);
   }

}