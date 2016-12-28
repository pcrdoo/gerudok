/***********************************************************************
 * Module:  SoltView.java
 * Author:  Ognjen
 * Purpose: Defines the Class SoltView
 ***********************************************************************/

package view;

import model.Document;
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
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import constants.Constants;
import controller.DocumentController;
import controller.SlotController;
import gerudok_observer.GObserver;
import gerudok_observer.GObserverNotification;

public class SlotView extends JPanel implements GObserver {
	private SlotController slotController;
	private Slot slot;

	private Model model;
	
	private TitledBorder border;

	public SlotView(Model model, Slot obj) {
		this.model = model;
		this.model.addObserver(this);
		this.setSlot(obj);
		this.slot.addObserver(this);

		setAlignmentY(CENTER_ALIGNMENT);
		this.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.8f));
	    EmptyBorder innerBorder = new EmptyBorder(3, 3, 3, 3);
		border = BorderFactory.createTitledBorder(innerBorder, this.getSlot().getName());
		this.setBorder(border);
	}

	public Slot getSlot() {
		return slot;
	}

	public void setSlot(Slot slot) {
		this.slot = slot;
	}
	
	@Override
	public void update(GObserverNotification notification, Object obj) {
		if (notification == GObserverNotification.ADD) {
			// Add new element
		} else if (notification == GObserverNotification.DELETE) {
			// Delete an element
		} else if (notification == GObserverNotification.GNODE_RENAME) {
			System.out.println("RENAMED");
			border.setTitle(this.getSlot().getName());
			repaint();
		}
	}
}