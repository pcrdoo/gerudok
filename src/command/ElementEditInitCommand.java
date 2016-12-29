package command;
import model.Element;
import model.Model;
import view.ElementViewFactory;

public class ElementEditInitCommand extends Command {
	private Element element;
	private ElementViewFactory factory;
	
	public ElementEditInitCommand(Model model, Element element) {
		this.model = model;
		this.element = element;
		factory = new ElementViewFactory();
	}
	
	@Override
	public void execute() {
		try {
			factory.createEditDialog(model, element).show();
		} catch (Exception e) {
			System.out.println("DavisException: something's not implemented, sorry");
			e.printStackTrace();
		}
	}
}
