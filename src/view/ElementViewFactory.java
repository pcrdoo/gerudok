package view;

import model.Model;
import model.elements.GraphicElement;
import model.elements.TextElement;
import model.Element;
import model.ElementType;
import view.elements.GraphicElementEditDialog;
import view.elements.GraphicElementView;
import view.elements.TextElementEditDialog;
import view.elements.TextElementView;

import javax.swing.JDialog;

/**
 * Factory for element views, based on type.
 * 
 * @author geomaster
 *
 */
public class ElementViewFactory {
	/**
	 * Creates an element view for a particular element.
	 * 
	 * @param model
	 *            Model
	 * @param element
	 *            Element to create view for
	 * @return Element view
	 * @throws Exception
	 *             If the type of the element is unknown
	 */
	public ElementView create(Model model, Element element) throws Exception {
		ElementType type = element.getType();
		switch (type) {
		case GRAPHIC:
			return new GraphicElementView(model, (GraphicElement) element);
		case TEXT:
			return new TextElementView(model, (TextElement) element);
		default:
			throw new Exception("Unsupported element type");
		}
	}

	/**
	 * Creates an edit dialog for a particular element.
	 * 
	 * @param model
	 *            Model
	 * @param element
	 *            Element to create edit dialog for
	 * @return Element view
	 * @throws Exception
	 *             If the type of the element is unknown
	 */
	public JDialog createEditDialog(Model model, Element element) throws Exception {
		ElementType type = element.getType();
		switch (type) {
		case GRAPHIC:
			return new GraphicElementEditDialog(model, (GraphicElement) element);
		case TEXT:
			return new TextElementEditDialog(model, (TextElement) element);
		default:
			throw new Exception("Unsupported element type");
		}
	}
}
