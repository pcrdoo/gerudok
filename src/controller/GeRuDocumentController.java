/***********************************************************************
 * Module:  DocumentController.java
 * Author:  Ognjen
 * Purpose: Defines the Class ProjectController
 ***********************************************************************/

package controller;

import java.awt.Component;
import java.awt.Rectangle;
import java.awt.geom.Point2D;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import command.Invoker;
import command.TreeSelectCommand;
import model.Model;
import view.GeRuDocumentView;
import view.PageView;

/**
 * Controller for GeRuDocuments.
 * 
 * @author Nikola Jovanovic
 *
 */
public class GeRuDocumentController {
	/**
	 * The main model.
	 */
	private Model model;
	/**
	 * The view to bind to.
	 */
	private GeRuDocumentView documentView;

	/**
	 * @param model
	 *            The main model.
	 * @param view
	 *            The view to bind to.
	 */
	public GeRuDocumentController(Model model, GeRuDocumentView view) {
		this.model = model;
		this.documentView = view;
		documentView.attachViewPortChangeListener(new ViewPortChangeListener());
	}

	/**
	 * A listener that tracks the current viewport and determines currently
	 * selected page.
	 * 
	 * @author Nikola Jovanovic
	 *
	 */
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
					// looking for a page with center closest to
					// visibleRect center
					if (currentDistance < closestChildDistance) {
						closestChild = child;
						closestChildDistance = currentDistance;
					}
					child.repaint();
				}
			}
			if (closestChild != null && closestChild.getPage() != documentView.getSelectedPage()) {
				documentView.setSelectedPage(closestChild.getPage());
				Invoker.getInstance().executeCommand(new TreeSelectCommand(model, closestChild.getPage()));
			}
		}
	}
}
