package view;

import model.Element;
import model.Model;
import model.ElementContainer;
import gerudok_observer.GObserver;
import gerudok_observer.GNotification;

import java.util.*;
import javax.swing.BoxLayout;
import javax.swing.Box;
import model.tree.GNode;
import javax.swing.JPanel;

import java.awt.Component;

/**
 * Generic view for element containers. Element containers can be slots and
 * elements (as elements can be recursive).
 * 
 * @author geomaster
 *
 */
public class ElementContainerView extends JPanel implements GObserver {
	/**
	 * Version UID for serialization.
	 */
	final static long serialVersionUID = 1;

	/**
	 * Model.
	 */
	public Model model;

	/**
	 * Element container being displayed.
	 */
	public ElementContainer elementContainer;

	/**
	 * Factory for specific element views.
	 */
	public ElementViewFactory elementViewFactory;

	/**
	 * Set of child element views.
	 */
	private Set<ElementView> views = new HashSet<>();

	/**
	 * Whether this elements is the root (only slots are). Used to control
	 * indentation of the element.
	 */
	private boolean isRoot;

	/**
	 * Constructor.
	 * 
	 * @param model
	 *            Model
	 * @param elementContainer
	 *            Element container to display
	 * @param isRoot
	 *            Whether this element container is a root element container
	 *            (i.e. slot)
	 */
	public ElementContainerView(Model model, ElementContainer elementContainer, boolean isRoot) {
		super();
		this.elementContainer = elementContainer;
		this.elementContainer.addObserver(this);
		this.model = model;
		this.elementViewFactory = new ElementViewFactory();

		setAlignmentY(TOP_ALIGNMENT);
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		for (GNode child : elementContainer.getChildren()) {
			if (child instanceof Element) {
				addElement((Element) child, !isRoot);
			}
		}

		this.isRoot = isRoot;
	}

	/**
	 * Gets the element container being displayed.
	 * 
	 * @return Element container
	 */
	public ElementContainer getElementContainer() {
		return elementContainer;
	}

	/**
	 * Adds an element to the container.
	 * 
	 * @param e
	 *            Element to add
	 * @param indent
	 *            Whether to indent the element
	 */
	private void addElement(Element e, boolean indent) {
		try {
			JPanel viewContainer = new JPanel();
			viewContainer.setLayout(new BoxLayout(viewContainer, BoxLayout.LINE_AXIS));

			ElementView view = elementViewFactory.create(model, e);
			if (indent) {
				Component strut = Box.createHorizontalStrut(20);
				viewContainer.add(strut);
			}
			viewContainer.add(view);
			views.add(view);
			add(viewContainer);
			repaint();
		} catch (Exception ex) {
			System.out.println("DavisException: Unknown element type! (maybe we should just no-op here though?");
			ex.printStackTrace();
		}
	}

	/**
	 * Handles updates to the tree. Adds and deletes children, and calls
	 * onRenameNotification and onEditNotification (which should be implemented
	 * by derived classes).
	 * 
	 * @param notification
	 *            Notification type
	 * @param obj
	 *            Custom data
	 */
	@Override
	public void update(GNotification notification, Object obj) {
		if (notification == GNotification.ADD) {
			addElement((Element) obj, !isRoot);
		} else if (notification == GNotification.DELETE) {
			ElementView view = findView((Element) obj);
			if (view != null) {
				view.getParent().remove(view);
				revalidate();
				repaint();
			}
		} else if (notification == GNotification.GNODE_RENAME) {
			onRenameNotification(obj);
		} else if (notification == GNotification.ELEMENT_EDIT) {
			onEditNotification(obj);
		}
	}

	/**
	 * Called when the node is renamed.
	 * 
	 * @param obj
	 *            Custom data
	 */
	public void onRenameNotification(Object obj) {
	}

	/**
	 * Called when an immediate child element is edited.
	 * 
	 * @param obj
	 *            Custom data
	 */
	public void onEditNotification(Object obj) {
	}

	/**
	 * Finds a view displaying a particular element.
	 * 
	 * @param e
	 *            Element whose view to find
	 * @return ElementView displaying the element, or null if not found
	 */
	private ElementView findView(Element e) {
		for (ElementView v : views) {
			if (v.getElement() == e) {
				return v;
			}
		}

		return null;
	}
}
