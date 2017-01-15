
package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import constants.Constants;
import controller.PageController;
import gerudok_observer.GNotification;
import gerudok_observer.GObserver;
import model.Model;
import model.Page;
import model.Slot;

/**
 * The graphical representation of a page.
 * 
 * @author Nikola Jovanovic
 *
 */
@SuppressWarnings("serial")
public class PageView extends JPanel implements GObserver {

	/**
	 * The page that this view is based on.
	 */
	private Page page;
	/**
	 * The main model.
	 */
	private Model model;
	/**
	 * The main content area that holds the slots.
	 */
	private JPanel content;

	/**
	 * Part of the page border.
	 */
	private EmptyBorder emptyBorder;
	/**
	 * Part of the page border.
	 */
	private EmptyBorder innerTitledBorder;
	/**
	 * Part of the page border.
	 */
	private TitledBorder titledBorder;
	/**
	 * Full page border.
	 */
	private CompoundBorder compoundBorder;
	/**
	 * The corresponding controller.
	 */
	private PageController pageController;

	/**
	 * Constructor that forwards a reference to the main model and the page to
	 * be visualized.
	 * 
	 * @param model
	 *            the main model
	 * @param page
	 *            the page to be visualized
	 */
	public PageView(Model model, Page page) {
		this.model = model;
		this.model.addObserver(this);
		this.setPage(page);
		this.page.addObserver(this);

		// Fixes the layout.
		setMaximumSize(Constants.PAGE_SIZE);
		setPreferredSize(Constants.PAGE_SIZE);
		setMinimumSize(Constants.PAGE_SIZE);
		setBackground(Color.LIGHT_GRAY);
		setAlignmentY(CENTER_ALIGNMENT);
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		// Creates a border.
		emptyBorder = new EmptyBorder(Constants.PAGE_TOP_MARGIN, 0, Constants.PAGE_BOTTOM_MARGIN, 0);
		innerTitledBorder = new EmptyBorder(0, 0, 0, 0);
		titledBorder = BorderFactory.createTitledBorder(innerTitledBorder, this.getPage().getName());
		titledBorder.setTitlePosition(TitledBorder.BELOW_BOTTOM);
		titledBorder.setTitleJustification(TitledBorder.CENTER);
		compoundBorder = new CompoundBorder(emptyBorder, titledBorder);
		this.setBorder(compoundBorder);

		// Attaches the content panel.
		content = new JPanel();
		content.setBackground(page.getColor());
		add(content);

		// Attaches the listeners.
		setPageController(new PageController(model, this));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gerudok_observer.GObserver#update(gerudok_observer.GNotification,
	 * java.lang.Object)
	 */
	@Override
	public void update(GNotification notification, Object obj) {
		if (notification == GNotification.ADD) {
			if (obj instanceof Slot) {
				Slot slot = (Slot) obj;
				addNewChildView(slot);
				validate();
				repaint();
			}
		} else if (notification == GNotification.DELETE) {
			SlotView slotView = findSlot((Slot) obj);
			try {
				content.remove(slotView);
				adjustSlotSizes();
				validate();
				repaint();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		} else if (notification == GNotification.GNODE_RENAME) {
			titledBorder.setTitle(this.getPage().getName());
			repaint();
		}
	}

	/**
	 * Creates and attaches a new child view based on the received slot.
	 * 
	 * @param slot
	 *            the slot to visualize
	 */
	public SlotView addNewChildView(Slot slot) {
		SlotView slotView = new SlotView(model, slot);
		content.add(slotView);
		adjustSlotSizes();
		return slotView;
	}

	/**
	 * Adjusts sizes of slots in this page based on their number.
	 */
	private void adjustSlotSizes() {

		int factor = Math.max(4, content.getComponents().length);
		int availableHeight = content.getHeight() - factor * 5;

		for (Component c : content.getComponents()) {
			if (c instanceof SlotView) {
				c.setPreferredSize(new Dimension(content.getWidth(), availableHeight / factor));
			}
		}
	}

	/**
	 * Finds a child view that represents a slot.
	 * 
	 * @param slot
	 *            the slot to look for
	 * @return the requested view
	 */
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

	/**
	 * Retrieves the page that this view is based on.
	 * 
	 * @return the page that this view is based on
	 */
	public Page getPage() {
		return page;
	}

	/**
	 * * Sets the page that this view is based on.
	 * 
	 * @param page
	 *            the page that this view is based on
	 */
	public void setPage(Page page) {
		this.page = page;
	}

	/**
	 * Retrieves the corresponding controller.
	 * 
	 * @return the corresponding controller
	 */
	public PageController getPageController() {
		return pageController;
	}

	/**
	 * Attaches the controller.
	 * 
	 * @param pageController
	 *            the controller to attach
	 */
	public void setPageController(PageController pageController) {
		this.pageController = pageController;
	}
}