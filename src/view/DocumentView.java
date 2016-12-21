/***********************************************************************
 * Module:  DocumentView.java
 * Author:  Ognjen
 * Purpose: Defines the Class ProjectView
 ***********************************************************************/

package view;

import controler.DocumentController;
import gerudok_observer.GObserver;
import gerudok_observer.GObserverNotification;
import model.Document;
import model.Model;
import model.tree.GNode;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.beans.PropertyVetoException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class DocumentView extends JPanel implements GObserver {
   private DocumentController documentController;
   private Document document;
   private Model model;
   
   public DocumentView(Model model, Document document) {
	   this.model = model;
	   this.model.addObserver(this);
	   this.setDocument(document);
	   this.document.addObserver(this);
	   this.setBackground(Color.GREEN);

	   JScrollPane pane = new JScrollPane();
	   pane.setBackground(Color.YELLOW);
	   pane.setPreferredSize(new Dimension(100, 100));
	   pane.setMinimumSize(new Dimension(100, 100));
	   pane.setMaximumSize(new Dimension(100, 100));
	   
	   JPanel div = new JPanel();
	   div.setPreferredSize(new Dimension(100, 100));
	   div.setMinimumSize(new Dimension(100, 100));
	   div.setMaximumSize(new Dimension(100, 100));
	   
	   for(int i = 0; i < 3; i++) {
		   JPanel nov = new JPanel();
		   nov.setBackground(Color.BLUE);
		   nov.setPreferredSize(new Dimension(100, 100));
		   nov.setMinimumSize(new Dimension(100, 100));
		   nov.setMaximumSize(new Dimension(100, 100));
		   JLabel labela = new JLabel("TEKST" + i);
		   nov.add(labela);
		   div.add(nov);
	   }
	   
	   
	   
	   pane.add(div);
	   this.add(pane);
   }

	public void setDocument(Document document) {
		this.document = document;
	}
	
	public Document getDocument() {
		return this.document;
	}

	@Override
	public void update(GObserverNotification notification, Object obj) {
		// TODO
	}

	public void updateSelection(Object[] path, int idx) {
		System.out.println("UPDATE TO PAGE: TODO"); // na isti nacin
	}


}