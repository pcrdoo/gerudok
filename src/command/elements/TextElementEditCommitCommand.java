package command.elements;

import command.Command;
import model.Model;
import model.elements.TextElement;

/**
 * Command which commits the new state of a text element after a confirmed edit
 * operation.
 * 
 * @author geomaster
 *
 */
public class TextElementEditCommitCommand extends Command {
	/**
	 * Text element whose contents to change.
	 */
	private TextElement element;
	/**
	 * New contents of the text element.
	 */
	private String newHtml;

	/**
	 * Constructor.
	 * 
	 * @param model
	 *            Model
	 * @param element
	 *            Element whose contents to revert
	 * @param newHtml
	 *            New contents of the text element
	 */
	public TextElementEditCommitCommand(Model model, TextElement element, String newHtml) {
		this.newHtml = newHtml;
		this.element = element;
		this.model = model;
	}

	/**
	 * Sets the contents of the text element to the new value.
	 */
	@Override
	public void doCommand() {
		element.setHtml(newHtml);
	}
}
