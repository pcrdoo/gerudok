/***********************************************************************
 * Module:  DocumentController.java
 * Author:  Ognjen
 * Purpose: Defines the Class ProjectController
 ***********************************************************************/

package controller;

import model.Model;
import view.DocumentView;
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

/** @pdOid bf767906-23cd-405d-a24d-dd73d7851411 */
public class DocumentController {
	private Model model;
	private DocumentView documentView;

	public DocumentController(Model model, DocumentView view) {
		this.model = model;
		this.documentView = view;
		documentView.attachViewPortChangeListener(new ViewPortChangeListener());
	}

	class ViewPortChangeListener implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent e) {
			if (documentView.isPageSelectionFromTree()) {
				documentView.setPageSelectionFromTree(false);
				return;
			}
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
					// pre je bilo samo ako se seku pravougaonici ali for style
					// points
					// trazimo stranicu ciji je centar najblizi centru
					// visibleRect-a
					if (currentDistance < closestChildDistance) {
						closestChild = child;
						closestChildDistance = currentDistance;
					}
				}
			}
			if (closestChild != null && closestChild != documentView.getSelectedPage()) {
				// U desktopu je implementirano: "ako je doslo od drveta
				// ne vracaj mu"
				// sto radi za slucaj projekata i dokumenata. U slucaju
				// stranica da bi
				// Desktop -> Tree cross selection radio neophodno je da
				// stablo
				// implementira "ako je doslo od desktopa ne vracaj mu",
				// jer trenutno
				// mu vrati pa je scroll prakticno nemoguc.
				// UPDATE: ovo cemo takodje da radimo iz dokumenta da se
				// ne narusi
				// polimorfizam stabla. Ovo ima potencijal da se polomi
				// zbog nekog
				// race conditiona, ako krenu da se desavaju cudne
				// stvari naci lepsi nacin.

				documentView.setTreePageSelectionFromDesktop(true);
				model.doTreeSelection(closestChild.getPage());
			}
		}
	}

	private Point getRectangleCenter(Rectangle rect) {
		return new Point(rect.x + rect.width / 2, rect.y + rect.height / 2);
	}
}
