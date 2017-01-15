
package controller;

import model.Model;
import view.MainView;

/**
 * Controller for the main view.
 * 
 * @author Nikola Jovanovic
 *
 */
public class MainController {

	/**
	 * The main model.
	 */
	public Model model;
	/**
	 * The view to bind to.
	 */
	public MainView mainView;

	/**
	 * @param model
	 *            the main model
	 * @param mainView
	 *            the view to bind to
	 */
	public MainController(Model model, MainView mainView) {
		this.model = model;
		this.mainView = mainView;
	}
}