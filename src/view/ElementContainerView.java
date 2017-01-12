package view;
import java.util.List;
import model.Element;
import model.Model;
import model.ElementContainer;
import gerudok_observer.GObserver;
import gerudok_observer.GObserverNotification;

import java.util.*;
import javax.swing.BoxLayout;
import javax.swing.Box;
import model.tree.GNode;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Component;

public class ElementContainerView extends JPanel implements GObserver {
	public Model model;
	public ElementContainer elementContainer;
	public ElementViewFactory elementViewFactory;
	private Set<ElementView> views = new HashSet<>();
	private boolean isRoot;
	
	public ElementContainerView(Model model, ElementContainer elementContainer, boolean isRoot) {
		super();
		this.elementContainer = elementContainer;
		this.elementContainer.addObserver(this);
		this.model = model;
		this.elementViewFactory = new ElementViewFactory();
		
		setAlignmentY(TOP_ALIGNMENT);
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		for (GNode child: elementContainer.getChildren()) {
			if (child instanceof Element) {
				addElement((Element) child, !isRoot);
			}
		}
		
		this.isRoot = isRoot;
	}
	
	public ElementContainer getElementContainer() {
		return elementContainer;
	}
	
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
	
	@Override
	public void update(GObserverNotification notification, Object obj) {
		if (notification == GObserverNotification.ADD) {
			addElement((Element) obj, !isRoot);
		} else if (notification == GObserverNotification.DELETE) {
			ElementView view = findView((Element) obj);
			if (view != null) {
				view.getParent().remove(view);
				revalidate();
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
		for (ElementView v: views) {
			if (v.getElement() == e) {
				return v;
			}
		}
		
		return null;
	}
}
