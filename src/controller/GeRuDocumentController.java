/***********************************************************************
 * Module:  DocumentController.java
 * Author:  Ognjen
 * Purpose: Defines the Class ProjectController
 ***********************************************************************/

package controller;

import model.Model;
import view.GeRuDocumentView;
import view.PageView;
import view.ProjectView;

import java.awt.Component;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.util.*;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import command.Invoker;
import command.TreeSelectCommand;

/** @pdOid bf767906-23cd-405d-a24d-dd73d7851411 */
public class GeRuDocumentController {
	private Model model;
	private GeRuDocumentView documentView;

	public GeRuDocumentController(Model model, GeRuDocumentView view) {
		this.model = model;
		this.documentView = view;
		documentView.attachViewPortChangeListener(new ViewPortChangeListener());
	}

	class ViewPortChangeListener implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent e) {
			Rectangle visibleRect = documentView.getPageArea().getVisibleRect();
			Point2D.Double visibleRectCenter = new Point2D.Double(visibleRect.getCenterX(), visibleRect.getCenterY());
			PageView closestChild = null;
			double closestChildDistance = visibleRect.getHeight() * visibleRect.getWidth();
			for (Component comp : documentView.getPageArea().getComponents()) {
				if (comp instanceof PageView) {
					PageView child = (PageView) comp;
					Rectangle childBounds = child.getBounds();
					Point2D.Double childCenter = new Point2D.Double(childBounds.getCenterX(), childBounds.getCenterY());
					double currentDistance = childCenter.distance(visibleRectCenter);
					// looking for a page with center closest to visibleRect center
					if (currentDistance < closestChildDistance) {
						closestChild = child;
						closestChildDistance = currentDistance;
					}
					child.repaint();
				}
			}
			if (closestChild != null && closestChild.getPage() != documentView.getSelectedPage()) {
				System.out.println("ScrollCandidate " + closestChild.getPage().getName());
				if(documentView.getSelectedPage() != null)
				System.out.println("Mine Old " + documentView.getSelectedPage().getName());
				documentView.setSelectedPage(closestChild.getPage());
				Invoker.getInstance().executeCommand(new TreeSelectCommand(model, closestChild.getPage()));
			}
		}
	}
}
