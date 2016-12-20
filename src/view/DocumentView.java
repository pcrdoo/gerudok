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
import model.tree.GNode;

import java.awt.Color;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DocumentView extends JPanel implements GObserver {
   private DocumentController documentController;
   private Document document;
   
   public DocumentView(Document document) {
	   this.setDocument(document);
	   this.document.addObserver(this);
	   this.setBackground(Color.GREEN);
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


}