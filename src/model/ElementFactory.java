package model;

import java.io.Serializable;

import model.elements.GraphicElement;
import model.elements.TextElement;

/**
 * Factory for creating specific elements, based on type.
 * 
 * @author geomaster
 *
 */
public class ElementFactory implements Serializable {
	/**
	 * Version UID for serialization.
	 */
	final static long serialVersionUID = 1;

	/**
	 * Creates an element of a given type.
	 * 
	 * @param type
	 *            Type of the element to create
	 * @param name
	 *            Name of the element
	 * @return Newly created element
	 * @throws Exception
	 *             If the type is unknown
	 */
	public Element create(ElementType type, String name) throws Exception {
		switch (type) {
		case GRAPHIC:
			return new GraphicElement(name);
		case TEXT:
			return new TextElement(name);
		default:
			throw new Exception("Unsupported element type");
		}
	}

	/**
	 * Creates a name for an element of a given type.
	 * 
	 * @param type
	 *            Type of the element whose name to create
	 * @param index
	 *            Index of the element whose name to create
	 * @return Newly created element
	 * @throws Exception
	 *             If the type is unknown
	 */
	public String createName(ElementType type, int index) throws Exception {
		String prefix = "";
		switch (type) {
		case GRAPHIC:
			prefix = "Graphic";
			break;
		case TEXT:
			prefix = "Text";
			break;
		}

		return prefix + "Element" + index;
	}
}
