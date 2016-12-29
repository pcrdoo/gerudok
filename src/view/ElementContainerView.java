package view;
import java.util.List;
import model.Element;
import model.Model;
import model.ElementContainer;
import gerudok_observer.GObserver;
import gerudok_observer.GObserverNotification;

import javax.swing.BoxLayout;
import model.tree.GNode;
import javax.swing.JPanel;
import java.awt.Component;

public class ElementContainerView extends JPanel implements GObserver {
	public Model model;
	public ElementContainer elementContainer;
	public ElementViewFactory elementViewFactory;
	
	public ElementContainerView(Model model, ElementContainer elementContainer) {
		super();
		this.elementContainer = elementContainer;
		this.elementContainer.addObserver(this);
		this.model = model;
		this.elementViewFactory = new ElementViewFactory();
		
		setAlignmentY(TOP_ALIGNMENT);
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		for (GNode child: elementContainer.getChildren()) {
			if (child instanceof Element) {
				addElement((Element) child);
			}
		}
	}
	
	public ElementContainer getElementContainer() {
		return elementContainer;
	}
	
	private void addElement(Element e) {
		try {
			ElementView view = elementViewFactory.create(model, e);
			add(view);
			repaint();
		} catch (Exception ex) {
			System.out.println("DavisException: Unknown element type! (maybe we should just no-op here though?");
			ex.printStackTrace();
		}		
	}
	
	@Override
	public void update(GObserverNotification notification, Object obj) {
		if (notification == GObserverNotification.ADD) {
			addElement((Element) obj);
		} else if (notification == GObserverNotification.DELETE) {
			ElementView view = findView((Element) obj);
			if (view != null) {
				remove(view);
				repaint();
			}
		} else if (notification == GObserverNotification.GNODE_RENAME) {
			onRenameNotification(obj);
		} else if (notification == GObserverNotification.ELEMENT_EDIT) {
			onEditNotification(obj);
		}
	}
	
	public void onRenameNotification(Object obj)
	{
	}
	
	public void onEditNotification(Object obj)
	{
	}
	
	private ElementView findView(Element e)
	{
		for (Component c: getComponents()) {
			if (c instanceof ElementView) {
				if (((ElementView) c).getElement() == e) {
					return (ElementView) c;
				}
			}
		}
		
		return null;
	}
}
