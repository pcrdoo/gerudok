/***********************************************************************
 * Module:  SoltView.java
 * Author:  Ognjen
 * Purpose: Defines the Class SoltView
 ***********************************************************************/

package view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import controller.SlotController;
import model.Model;
import model.Slot;

/**
 * The graphical representation of a slot.
 * 
 * @author Nikola Jovanovic
 *
 */
@SuppressWarnings("serial")
public class SlotView extends ElementContainerView {
	/**
	 * The main model.
	 */
	private Model model;

	/**
	 * The border that contains the slot title.
	 */
	private TitledBorder border;
	/**
	 * The corresponding controller.
	 */
	private SlotController slotController;

	/**
	 * Constructor that forwards a reference to the main model and the slot to
	 * be visualized.
	 * 
	 * @param model
	 *            the main model
	 * @param slot
	 *            the slot to be visualized
	 */
	public SlotView(Model model, Slot slot) {
		super(model, slot, true);

		this.model = model;
		this.model.addObserver(this);
		this.setSlot(slot);

		setAlignmentY(CENTER_ALIGNMENT);
		setAlignmentX(CENTER_ALIGNMENT);
		this.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.2f));
		EmptyBorder innerBorder = new EmptyBorder(3, 3, 3, 3);
		border = BorderFactory.createTitledBorder(innerBorder, slot.getName());
		this.setBorder(border);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.ElementContainerView#onRenameNotification(java.lang.Object)
	 */
	@Override
	public void onRenameNotification(Object obj) {
		border.setTitle(this.getSlot().getName());
		repaint();
	}

	/**
	 * Retrieves the slot that this view is based on.
	 * 
	 * @return the slot that this view is based on
	 */
	public Slot getSlot() {
		return (Slot) getElementContainer();
	}

	/**
	 * Sets the slot that this view is based on.
	 * 
	 * @param slot
	 *            the slot that this view is based on
	 */
	public void setSlot(Slot slot) {
		this.elementContainer = slot;
	}

	/**
	 * Retrieves the corresponding controller.
	 * 
	 * @return the corresponding controller
	 */
	public SlotController getSlotController() {
		return slotController;
	}

	/**
	 * Attaches the controller.
	 * 
	 * @param slotController
	 *            the controller to attach
	 */
	public void setSlotController(SlotController slotController) {
		this.slotController = slotController;
	}

}