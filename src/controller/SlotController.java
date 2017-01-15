package controller;

import model.Model;
import view.SlotView;

/**
 * Controller for slots.
 * 
 * @author Nikola Jovanovic
 *
 */
public class SlotController {

	/**
	 * The main model.
	 */
	public Model model;
	/**
	 * The view to bind to.
	 */
	public SlotView slotView;

	/**
	 * @param model
	 *            the main model
	 * @param slotView
	 *            the view to bind to
	 */
	public SlotController(Model model, SlotView slotView) {
		this.model = model;
		this.slotView = slotView;
	}
}