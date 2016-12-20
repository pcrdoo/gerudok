/***********************************************************************
 * Module:  DesktopView.java
 * Author:  Ognjen
 * Purpose: Defines the Class DesktopView
 ***********************************************************************/

package view;

import controler.DesktopController;
import gerudok_observer.GObserver;
import gerudok_observer.GObserverNotification;
import model.Model;
import model.Project;
import model.Workspace;

import java.awt.Color;
import java.util.*;

import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DesktopView extends JDesktopPane implements GObserver{
  
   private ArrayList<ProjectView> projectViews;
   private DesktopController desktopController;
   private Workspace workspace;
   
   public DesktopView(Workspace workspace) {
	   this.workspace = workspace;
	   this.workspace.addObserver(this);
	   this.setBackground(Color.CYAN);
	   this.add(new JLabel("RADNI PROSTOR"));
	   setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
	   projectViews = new ArrayList<>();
   }

	@Override
	public void update(GObserverNotification notification, Object obj) {
		if(notification == GObserverNotification.ADD) {
			ProjectView projectView = new ProjectView((Project)obj);
			projectViews.add(projectView);
			add(projectView);
			System.out.println("DODATO");
		}
	}
   
   
}