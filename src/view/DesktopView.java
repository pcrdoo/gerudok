/***********************************************************************
 * Module:  DesktopView.java
 * Author:  Ognjen
 * Purpose: Defines the Class DesktopView
 ***********************************************************************/

package view;

import gerudok_observer.GObserver;
import gerudok_observer.GNotification;
import model.GeRuDocument;
import model.Model;
import model.Page;
import model.Project;
import model.Workspace;
import model.tree.GNode;

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

import constants.Constants;
import controller.DesktopController;

@SuppressWarnings("serial")
public class DesktopView extends JDesktopPane implements GObserver {

	// private ArrayList<ProjectView> projectViews;
	private DesktopController desktopController;
	private Workspace workspace;
	private Model model;

	public DesktopView(Model model) {
		this.model = model;
		this.model.addObserver(this);
		this.workspace = Workspace.getInstance();
		this.workspace.addObserver(this);
		this.setBackground(Color.CYAN);
		this.add(new JLabel("RADNI PROSTOR"));
		setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
		// Listener

	}

	public void cascade() {
		// TODO: pozvati ovu funkciju bar nekad
		int sz = getAllFrames().length;
		for (int i = 0; i < sz; i++) {
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
		int unitHeight = (int) (d.getHeight() / sz);
		for (int i = 0; i < sz; i++) {
			getAllFrames()[i].setLocation(0, i * unitHeight);
			getAllFrames()[i].setSize((int) d.getWidth(), unitHeight);
		}
	}

	public void tileHorizontally() {
		// TODO: pozvati ovu funkciju bar nekad
		Dimension d = getSize();
		int sz = getAllFrames().length;
		int unitWidth = (int) (d.getWidth() / sz);
		for (int i = 0; i < sz; i++) {
			getAllFrames()[i].setLocation(i * unitWidth, 0);
			getAllFrames()[i].setSize(unitWidth, (int) d.getHeight());
		}
	}

	@Override
	public void update(GNotification notification, Object obj) {
		if (notification == GNotification.ADD) {
			if(obj instanceof Project) {
				Project project = (Project) obj;
				addNewChildView(project);
			}
		} else if (notification == GNotification.DELETE) {
			
			ProjectView projectView = findProjectView((Project) obj);
			try {
				try {
					projectView.setIcon(false);
					remove(projectView);
				} catch (PropertyVetoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				repaint();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		} else if (notification == GNotification.DESKTOP_SELECT) {
			Object[] path = ((TreePath) obj).getPath();
			updateSelection(path, 1);
		}
	}
	
	public void addNewChildView(Project project) {
		Point p = new Point(10 + getAllFrames().length * Constants.CASCADE_OFFSET, 10 + getAllFrames().length * Constants.CASCADE_OFFSET); 
		ProjectView projectView = new ProjectView(model, project, p);
		this.add(projectView);
		repaint();
		try {
			projectView.setSelected(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		for(GNode child : project.getChildren()) {
			GeRuDocument document = (GeRuDocument)child;
			projectView.addNewChildView(document);
		}
	}

	private void updateSelection(Object[] path, int idx) {
		// I am a workspace, I contain projects, idx = 1
		if (path.length > idx) {
			ProjectView projectView = findProjectView((Project) path[idx]);
			if (projectView == null) {
				return;
			}
			try {
				projectView.setProjectSelectionFromTree(true);
				projectView.setSelected(true);
				projectView.updateSelection(path, idx + 1);
			} catch (NullPointerException e) {
				e.printStackTrace();
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
		}
	}

	public ProjectView findProjectView(Project project) {
		for (JInternalFrame internalFrame : getAllFrames()) {
			ProjectView projectView = (ProjectView) internalFrame;
			if (projectView.getProject() == project) {
				return projectView;
			}
		}
		return null;
	}

}