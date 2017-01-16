package model;

import java.io.Serializable;
import model.tree.GNode;

/**
 * Abstract element container. Element containers can have elements as children.
 * Slot and Element are element containers.
 * 
 * @author geomaster
 *
 */
public class ElementContainer extends GNode implements Serializable {
	/**
	 * Version UID for serialization.
	 */
	final static long serialVersionUID = 1;
	
	/**
	 * Element type.
	 */
	protected ElementType type;

	/**
	 * Factory for child elements.
	 */
	private ElementFactory elementFactory;

	/**
	 * Number of created children. Used to derive the next sequential ID when
	 * auto-generating names.
	 */
	static int newChildCount = 0;

	/**
	 * Gets the global number of added children.
	 * 
	 * @return New child count
	 */
	private static int getNewChildCount() {
		return newChildCount++;
	}

	/**
	 * Default constructor.
	 */
	public ElementContainer() {
		super();
		this.type = null;
		this.elementFactory = new ElementFactory();
	}

	/**
	 * Constructor.
	 * 
	 * @param name
	 *            Name of the element container
	 */
	public ElementContainer(String name) {
		super(name);
		this.type = null;
		this.elementFactory = new ElementFactory();
	}

	/**
	 * Gets the type of the elements in this container.
	 * 
	 * @return Element type
	 */
	public ElementType getType() {
		return type;
	}

	/**
	 * Adds a new child. The type is assumed to be the same as the current type
	 * of all elements (as upon adding a single typed child the whole container
	 * type locks into that type). If no child is present, this method will
	 * scream.
	 * 
	 * @return Node representing the newly added child
	 */
	@Override
	public GNode addNewChild() {
		// Pretend only the happy path exists
		try {
			return addNewChild(type);
		} catch (Exception e) {
			System.out.println("DavisException: this should never happen, if's don't work");
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Adds a new child with an explicit type.
	 * 
	 * @param childType
	 *            Type of the new child
	 * @return Node representing the newly added child
	 * @throws Exception
	 *             If the type is incompatible with the existing type
	 */
	public GNode addNewChild(ElementType childType) throws Exception {
		if (type != null && childType != type) {
			// The slot is already typed, cannot add an incompatible element
			throw new Exception("Element of invalid type added to element container");
		}

		Element child = elementFactory.create(childType, elementFactory.createName(childType, getNewChildCount()));
		this.addChild(child);
		this.type = childType;

		return child;
	}
}
