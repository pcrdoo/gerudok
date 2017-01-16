package command;

import model.Model;
import model.ElementContainer;
import model.ElementType;
import model.Element;

/**
 * Command which adds a child element to an element container.
 * 
 * @author geomaster
 *
 */
public class AddNewChildElementCommand extends Command {
	/**
	 * Element container to add the child to.
	 */
	private ElementContainer node;

	/**
	 * Type of the child to add.
	 */
	private ElementType childType;

	/**
	 * Constructor.
	 * 
	 * @param model
	 *            Model
	 * @param node
	 *            Element container to add a child to
	 * @param type
	 *            Type of the child to add
	 */
	public AddNewChildElementCommand(Model model, ElementContainer node, ElementType type) {
		this.model = model;
		this.node = node;
		this.childType = type;
	}

	/**
	 * Adds a child to the element.
	 */
	@Override
	public void doCommand() {
		try {
			Element child = (Element) node.addNewChild(childType);
			model.getTreeModel().reload();
			Invoker.getInstance().executeCommand(new TreeSelectCommand(model, child));
			Invoker.getInstance().executeCommand(new ElementEditInitCommand(model, child));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
