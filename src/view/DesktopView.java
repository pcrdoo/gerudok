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
import java.awt.Dimension;
import java.awt.Point;
import java.beans.PropertyVetoException;
import java.util.*;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DesktopView extends JDesktopPane implements GObserver{
  
   //private ArrayList<ProjectView> projectViews;
   private DesktopController desktopController;
   private Workspace workspace;
   
   public DesktopView(Workspace workspace) {
	   this.workspace = workspace;
	   this.workspace.addObserver(this);
	   this.setBackground(Color.CYAN);
	   this.add(new JLabel("RADNI PROSTOR"));
	   setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
   }
   
   public void cascade() {
	   int sz = getAllFrames().length;
	   for(int i = 0; i < sz; i++) {
		   getAllFrames()[i].setLocation(20 + i * 30, 20 + i * 30);
		   getAllFrames()[i].setSize(500, 400);
		   try {
			  getAllFrames()[i].setSelected(true);
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   }
   }
   
   public void tileVertically() {
	   Dimension d = getSize();
	   int sz = getAllFrames().length;
	   int unitHeight = (int)(d.getHeight() / sz);
	   for(int i = 0; i < sz; i++) {
		   getAllFrames()[i].setLocation(0, i * unitHeight);
		   getAllFrames()[i].setSize((int)d.getWidth(), unitHeight);
	   }
   }
   
   public void tileHorizontally() {
	   Dimension d = getSize();
	   int sz = getAllFrames().length;
	   int unitWidth = (int)(d.getWidth() / sz);
	   for(int i = 0; i < sz; i++) {
		   getAllFrames()[i].setLocation(i * unitWidth, 0);
		   getAllFrames()[i].setSize(unitWidth, (int)d.getHeight());
	   }
   }

	@Override
	public void update(GObserverNotification notification, Object obj) {
		if(notification == GObserverNotification.ADD) {
			Point p = new Point(20 + getAllFrames().length * 30, 20 + getAllFrames().length * 30);
			ProjectView projectView = new ProjectView((Project)obj, p);
			//projectViews.add(projectView);
			add(projectView);
			try {
				projectView.setSelected(true);
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
			tileHorizontally();
		} else if(notification == GObserverNotification.DELETE) {
			for(JInternalFrame internalFrame : getAllFrames()) {
				ProjectView projectView = (ProjectView)internalFrame;
				if(projectView.getProject() == (Project)obj) {
					remove(projectView);
					repaint();
					break;
				}
			}
		}
	}
   
   
}