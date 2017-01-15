package controller;

import model.Model;
import view.DesktopView;

/**
 * Controller for the desktop.
 * 
 * @author Nikola Jovanovic
 *
 */
/**
 * @author Random
 *
 */
public class DesktopController {

	/**
	 * The main model.
	 */
	public Model model;
	/**
	 * The view to bind to.
	 */
	public DesktopView desktopView;

	/**
	 * @param model
	 *            the main model
	 * @param desktopView
	 *            the view to bind to
	 */
	public DesktopController(Model model, DesktopView desktopView) {
		this.model = model;
		this.desktopView = desktopView;

	}

}