package command.elements;

import command.Command;
import model.Model;
import model.elements.TextElement;

/**
 * Command which reverts the state of a text element to the one before an edit
 * operation.
 * 
 * @author geomaster
 *
 */
public class TextElementEditCancelCommand extends Command {
	/**
	 * Text element whose contents to revert.
	 */
	private TextElement element;

	/**
	 * Old contents of the text element.
	 */
	private String oldHtml;

	/**
	 * Constructor.
	 * 
	 * @param model
	 *            Model
	 * @param element
	 *            Element whose contents to revert
	 * @param oldHtml
	 *            Old contents of the text element
	 */
	public TextElementEditCancelCommand(Model model, TextElement element, String oldHtml) {
		this.oldHtml = oldHtml;
		this.element = element;
		this.model = model;
	}

	/**
	 * Sets the contents of the text element to the old value.
	 */
	@Override
	public void doCommand() {
		element.setHtml(oldHtml);
	}
}
