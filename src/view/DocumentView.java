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
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeListener;
import javax.swing.event.InternalFrameListener;

import constants.Constants;
import controller.DocumentController;

import controller.DocumentController;

@SuppressWarnings("serial")
public class DocumentView extends JPanel implements GObserver {
	private DocumentController documentController;
	private Document document;
	private Model model;
	private JPanel pageArea;

	public DocumentView(Model model, Document document) {
		this.model = model;
		this.model.addObserver(this);
		this.setDocument(document);
		this.document.addObserver(this);

		// Unutrasnji za stranice
		pageArea = new JPanel();
		pageArea.setLayout(new BoxLayout(pageArea, BoxLayout.PAGE_AXIS));
		pageArea.setBackground(Color.LIGHT_GRAY);
		pageArea.add(Box.createRigidArea(new Dimension(0, Constants.DOCUMENT_TOP_MARGIN)));

		// Dodaj skrol bar
		this.setLayout(new BorderLayout());
		JScrollPane scrollBar = new JScrollPane(pageArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollBar.setBorder(BorderFactory.createEmptyBorder());
		scrollBar.getVerticalScrollBar().setUnitIncrement(20);
		this.add(scrollBar);
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public Document getDocument() {
		return this.document;
	}

	// DOKUMENT SVA ONA SRANJA ?!

	@Override
	public void update(GObserverNotification notification, Object obj) {
		if (notification == GObserverNotification.ADD) {
			PageView pageView = new PageView(model, (Page) obj);
			pageArea.add(pageView);
			repaint();
		} else if (notification == GObserverNotification.DELETE) {
			System.out.println("Brisi dokument");
			PageView pageView = findPage((Page) obj);
			try {
				System.out.println(pageArea.getComponents().length);
				pageArea.remove(pageView);
				System.out.println(pageArea.getComponents().length);
				repaint();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
		// Projekti i Dokumenti se renamuju iz ProjectView
		// a Pagevi iz PageView, pa ne treba ovde rename
	}

	public void updateSelection(Object[] path, int i) {
		// TODO Auto-generated method stub

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

	/*
	 * public void updateSelection(Object[] path, int idx) { if (path.length >
	 * idx) { Component tab = findDocumentTab((Document)path[idx]); if(tab ==
	 * null) return; try { documentTabs.setSelectedComponent(tab);
	 * ((DocumentView)tab).updateSelection(path, idx + 1); } catch
	 * (NullPointerException e) { e.printStackTrace(); } } }
	 * 
	 * 
	 * public void attachFrameListener(InternalFrameListener frameListener) {
	 * this.addInternalFrameListener(frameListener); }
	 * 
	 * public void attachTabChangeListener(ChangeListener tabChangeListener) {
	 * this.documentTabs.addChangeListener(tabChangeListener); }
	 */

}