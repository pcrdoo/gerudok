package command;

import model.Element;
import model.Model;
import view.ElementViewFactory;

/**
 * Command which initializes editing of an element, by showing its edit dialog.
 * 
 * @author geomaster
 *
 */
public class ElementEditInitCommand extends Command {
	/**
	 * Element whose editing was initialized.
	 */
	private Element element;

	/**
	 * Factory for creating edit dialogs.
	 */
	private ElementViewFactory factory;

	/**
	 * Constructor.
	 * 
	 * @param model
	 *            Model
	 * @param element
	 *            Element whose editing to initialize
	 */
	public ElementEditInitCommand(Model model, Element element) {
		this.model = model;
		this.element = element;
		factory = new ElementViewFactory();
	}

	/**
	 * Opens an edit dialog for the element.
	 */
	@Override
	public void doCommand() {
		try {
			factory.createEditDialog(model, element).setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
