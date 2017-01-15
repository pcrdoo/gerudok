package command.elements;
import command.Command;
import model.Model;
import model.elements.TextElement;

public class TextElementEditCancelCommand extends Command {
	private TextElement element;
	private String oldHtml;
	
	public TextElementEditCancelCommand(Model model, TextElement element, String oldHtml) {
		this.oldHtml = oldHtml;
		this.element = element;
		this.model = model;
	}
	
	@Override
	public void doCommand() {
		element.setHtml(oldHtml);
	}
}
