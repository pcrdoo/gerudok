/***********************************************************************
 * Module:  PageView.java
 * Author:  Ognjen
 * Purpose: Defines the Class ProjectView
 ***********************************************************************/

package view;

import gerudok_observer.GObserver;
import gerudok_observer.GObserverNotification;
import model.Model;
import model.Page;
import model.Slot;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import constants.Constants;
import controller.PageController;

@SuppressWarnings("serial")
public class PageView extends JPanel implements GObserver {
	private PageController pageController;
	private Page page;
	private Model model;
	private JPanel content;
	
	private EmptyBorder emptyBorder;
	private EmptyBorder innerTitledBorder;
	private TitledBorder titledBorder;
	private CompoundBorder compoundBorder;

	public PageView(Model model, Page page) {
		this.model = model;
		this.model.addObserver(this);
		this.setPage(page);
		this.page.addObserver(this);

		setMaximumSize(Constants.PAGE_SIZE);
		setPreferredSize(Constants.PAGE_SIZE);
		setMinimumSize(Constants.PAGE_SIZE);
		setBackground(Color.LIGHT_GRAY);
		setAlignmentY(CENTER_ALIGNMENT);
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		
		// Border
		emptyBorder = new EmptyBorder(Constants.PAGE_TOP_MARGIN, 0, Constants.PAGE_BOTTOM_MARGIN, 0);
	    innerTitledBorder = new EmptyBorder(0, 0, 0, 0);
		titledBorder = BorderFactory.createTitledBorder(innerTitledBorder, this.getPage().getName());
		titledBorder.setTitlePosition(TitledBorder.BELOW_BOTTOM);
		titledBorder.setTitleJustification(TitledBorder.CENTER);
		compoundBorder = new CompoundBorder(emptyBorder, titledBorder);
		this.setBorder(compoundBorder);
		
		// Content
		content = new JPanel();
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		content.setBackground(page.getColor());
		add(content);
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	@Override
	public void update(GObserverNotification notification, Object obj) {
		if (notification == GObserverNotification.ADD) {
			SlotView slotView = new SlotView(model, (Slot) obj);
			content.add(slotView);
			adjustSlotSizes();
			repaint();
		} else if (notification == GObserverNotification.DELETE) {
			SlotView slotView = findSlot((Slot) obj);
			try {
				content.remove(slotView);
				adjustSlotSizes();
				repaint();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		} else if (notification == GObserverNotification.GNODE_RENAME) {
			titledBorder.setTitle(this.getPage().getName());
			repaint();
		}
	}
	
	private void adjustSlotSizes() {
		for(Component c : content.getComponents()) {
			if (c instanceof SlotView) {
				int factor = Math.max(4, content.getComponents().length);
				// TODO: Racunati ove dimenzije kako treba umesto ovog nabadanja.
				c.setPreferredSize(new Dimension(Constants.PAGE_WIDTH - 15, Constants.PAGE_HEIGHT / factor - 12));
			} 
		}
	}
	
	private SlotView findSlot(Slot slot) {
		for (Component c : content.getComponents()) {
			if (c instanceof SlotView) {
				SlotView slotView = (SlotView) c;
				if (slotView.getSlot() == slot) {
					return slotView;
				}
			}
		}
		return null;
	}
}