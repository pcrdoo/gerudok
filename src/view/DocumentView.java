/***********************************************************************
 * Module:  DocumentView.java
 * Author:  Ognjen
 * Purpose: Defines the Class ProjectView
 ***********************************************************************/

package view;

import gerudok_observer.GObserver;
import gerudok_observer.GObserverNotification;
import model.Document;
import model.Model;
import model.Page;
import model.Project;
import model.tree.GNode;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.beans.PropertyVetoException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeListener;
import javax.swing.event.InternalFrameListener;

import constants.Constants;
import controller.DocumentController;
import controller.ProjectController;
import controller.DocumentController;

@SuppressWarnings("serial")
public class DocumentView extends JPanel implements GObserver {
	private DocumentController documentController;
	private Document document;
	private Model model;
	private JPanel pageArea;
	private boolean pageSelectionFromTree;
	private boolean treePageSelectionFromDesktop;
	private PageView selectedPage;

	private JScrollPane scrollBar;

	public DocumentView(Model model, Document document) {
		this.model = model;
		this.model.addObserver(this);
		this.setDocument(document);
		this.document.addObserver(this);

		// Unutrasnji za stranice
		pageArea = new JPanel();
		pageArea.setLayout(new BoxLayout(pageArea, BoxLayout.PAGE_AXIS));
		pageArea.setBackground(Color.LIGHT_GRAY);
		pageSelectionFromTree = false;
		selectedPage = null;

		// Dodaj skrol bar
		this.setLayout(new BorderLayout());
		scrollBar = new JScrollPane(pageArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollBar.setBorder(BorderFactory.createEmptyBorder());
		scrollBar.getVerticalScrollBar().setUnitIncrement(20);
		this.add(scrollBar);
		
		// Listener
		documentController = new DocumentController(model, this);
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public Document getDocument() {
		return this.document;
	}

	@Override
	public void update(GObserverNotification notification, Object obj) {
		if (notification == GObserverNotification.ADD) {
			PageView pageView = new PageView(model, (Page) obj);
			pageArea.add(pageView);
			pageArea.scrollRectToVisible(pageView.getBounds());
			repaint();
		} else if (notification == GObserverNotification.DELETE) {
			PageView pageView = findPage((Page) obj);
			try {
				pageArea.remove(pageView);
				repaint();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		} else if (notification == GObserverNotification.GNODE_RENAME) {
			JTabbedPane documentTabs = (JTabbedPane)SwingUtilities.getAncestorOfClass(JTabbedPane.class, this);
			for(int i = 0; i < documentTabs.getTabCount(); i++) {
			    if(documentTabs.getComponentAt(i) == this) {
			    	documentTabs.setTitleAt(i, this.getDocument().getName());
			    }
			}
		}
	}

	public void updateSelection(Object[] path, int idx) {
		// selektovanje stranica
		// dno, update selection ne ide na nivo slotova
		if (path.length > idx) {
			PageView pageView = findPage((Page) path[idx]);
			selectedPage = pageView;
			if(treePageSelectionFromDesktop) {
				treePageSelectionFromDesktop = false;
				return;
			}
			selectedPage = pageView;
			if (pageView == null) {
				return;
			}
			try {
				// TODO: Tree -> Model selekcija dokumenata - popraviti
				pageSelectionFromTree = true;
				pageArea.scrollRectToVisible(pageView.getBounds());
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
	}

	private PageView findPage(Page page) {
		for (Component c : pageArea.getComponents()) {
			if (c instanceof PageView) {
				PageView pageView = (PageView) c;
				if (pageView.getPage() == page) {
					return pageView;
				}
			}
		}
		return null;
	}
	
	public void attachViewPortChangeListener(ChangeListener viewPortChangeListener) {
		this.scrollBar.getViewport().addChangeListener(viewPortChangeListener);
	}
	
	
	public JPanel getPageArea() {
		return pageArea;
	}
	

	public boolean isPageSelectionFromTree() {
		return pageSelectionFromTree;
	}

	public void setPageSelectionFromTree(boolean pageSelectionFromTree) {
		this.pageSelectionFromTree = pageSelectionFromTree;
	}
	
	public boolean isTreePageSelectionFromDesktop() {
		return treePageSelectionFromDesktop;
	}

	public void setTreePageSelectionFromDesktop(boolean treePageSelectionFromDesktop) {
		this.treePageSelectionFromDesktop = treePageSelectionFromDesktop;
	}

	public PageView getSelectedPage() {
		return selectedPage;
	}
}