package command;
import model.TextElement;
import model.Model;

public class TextElementEditCommitCommand extends Command {
	private TextElement element;
	private String newHtml;
	
	public TextElementEditCommitCommand(Model model, TextElement element, String newHtml) {
		this.newHtml = newHtml;
		this.element = element;
		this.model = model;
	}
	
	@Override
	public void execute() {
		element.setHtml(newHtml);
	}
}
