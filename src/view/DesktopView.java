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
import javax.swing.tree.TreePath;

@SuppressWarnings("serial")
public class DesktopView extends JDesktopPane implements GObserver{
  
   //private ArrayList<ProjectView> projectViews;
   private DesktopController desktopController;
   private Workspace workspace;
   private Model model;
   
   public DesktopView(Model model) {
	   this.model = model;
	   this.model.addObserver(this);
	   this.workspace = this.model.getWorkspace();
	   this.workspace.addObserver(this);
	   this.setBackground(Color.CYAN);
	   this.add(new JLabel("RADNI PROSTOR"));
	   setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
	   
	   // Listener
	   
   }
   
   public void cascade() {
	   // TODO: pozvati ovu funkciju bar nekad
	   int sz = getAllFrames().length;
	   for(int i = 0; i < sz; i++) {
		   getAllFrames()[i].setLocation(20 + i * 30, 20 + i * 30);
		   getAllFrames()[i].setSize(500, 400);
		   try {
			  getAllFrames()[i].setSelected(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
	   }
   }
   
   public void tileVertically() {
	   // TODO: pozvati ovu funkciju bar nekad
	   Dimension d = getSize();
	   int sz = getAllFrames().length;
	   int unitHeight = (int)(d.getHeight() / sz);
	   for(int i = 0; i < sz; i++) {
		   getAllFrames()[i].setLocation(0, i * unitHeight);
		   getAllFrames()[i].setSize((int)d.getWidth(), unitHeight);
	   }
   }
   
   public void tileHorizontally() {
	   // TODO: pozvati ovu funkciju bar nekad
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
			ProjectView projectView = new ProjectView(model, (Project)obj, p);
			add(projectView);
            repaint();
			try {
				projectView.setSelected(true);
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
		}else if(notification == GObserverNotification.DELETE) {
			ProjectView projectView = findProjectView((Project)obj);
			try {
				remove(projectView);
				repaint();
			} catch(NullPointerException e) {
				e.printStackTrace();
			}
        } else if(notification == GObserverNotification.DESKTOP_SELECT) {
        	Object[] path = ((TreePath)obj).getPath();
        	updateSelection(path, 1);
        }
	}

	private void updateSelection(Object[] path, int idx) {
		// I am a workspace, I contain projects, idx = 1
		if(path.length > idx) {
			ProjectView projectView = findProjectView((Project)path[idx]);
			try {
				projectView.setSelected(true);
				projectView.updateSelection(path, idx + 1);
			} catch(NullPointerException e) {
				e.printStackTrace();
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
		}
	}
	
	private ProjectView findProjectView(Project project) {
        for(JInternalFrame internalFrame : getAllFrames()) {
            ProjectView projectView = (ProjectView)internalFrame;
            if(projectView.getProject() == project) {
                return projectView;
            }
        }
        return null;
	}
   
   
}