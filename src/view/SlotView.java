/***********************************************************************
 * Module:  SoltView.java
 * Author:  Ognjen
 * Purpose: Defines the Class SoltView
 ***********************************************************************/

package view;

import model.GeRuDocument;
import model.Model;
import model.Page;
import model.Slot;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.util.*;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import constants.Constants;
import controller.GeRuDocumentController;
import controller.SlotController;
import gerudok_observer.GObserver;
import gerudok_observer.GObserverNotification;

public class SlotView extends ElementContainerView {
	private SlotController slotController;

	private Model model;
	
	private TitledBorder border;

	public SlotView(Model model, Slot obj) {
		super(model, obj, true);

		setAlignmentY(CENTER_ALIGNMENT);
		setAlignmentX(CENTER_ALIGNMENT);
		this.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.2f));
	    EmptyBorder innerBorder = new EmptyBorder(3, 3, 3, 3);
		border = BorderFactory.createTitledBorder(innerBorder, obj.getName());
		this.setBorder(border);
	}
	
	public Slot getSlot() {
		return (Slot) getElementContainer();
	}
	
	public void onRenameNotification(Object obj)
	{
		border.setTitle(this.getSlot().getName());
		repaint();
	}
		
}