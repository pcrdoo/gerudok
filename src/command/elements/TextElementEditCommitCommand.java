package command.elements;
import command.Command;
import model.Model;
import model.elements.TextElement;

public class TextElementEditCommitCommand extends Command {
	private TextElement element;
	private String newHtml;
	
	public TextElementEditCommitCommand(Model model, TextElement element, String newHtml) {
		this.newHtml = newHtml;
		this.element = element;
		this.model = model;
	}
	
	@Override
	public void doCommand() {
		element.setHtml(newHtml);
	}
}
