
package controller;

import model.Model;
import view.PageView;

/**
 * Controller for pages.
 * 
 * @author Nikola Jovanovic
 *
 */
public class PageController {
	/**
	 * The main model.
	 */
	public Model model;
	/**
	 * The view to bind to.
	 */
	public PageView pageView;

	/**
	 * @param model
	 *            the main model
	 * @param pageView
	 *            the view to bind to
	 */
	public PageController(Model model, PageView pageView) {
		this.model = model;
		this.pageView = pageView;
	}

}