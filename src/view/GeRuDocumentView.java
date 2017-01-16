
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeListener;

import controller.GeRuDocumentController;
import gerudok_observer.GNotification;
import gerudok_observer.GObserver;
import model.GeRuDocument;
import model.Model;
import model.Page;
import model.Slot;
import model.tree.GNode;

/**
 * The graphical representation of a GeRuDocument.
 * 
 * @author Nikola Jovanovic
 *
 */
@SuppressWarnings("serial")
public class GeRuDocumentView extends JPanel implements GObserver {

	/**
	 * The document that this view is based on.
	 */
	private GeRuDocument document;
	/**
	 * The main model.
	 */
	private Model model;
	/**
	 * The area of the Panel that shows the actual pages.
	 */
	private JPanel pageArea;
	/**
	 * Maintains the page currently in focus.
	 */
	private Page selectedPage;

	/**
	 * The scroll bar that allows scrolling through pages.
	 */
	private JScrollPane scrollBar;

	/**
	 * The corresponding controller.
	 */
	private GeRuDocumentController documentController;

	/**
	 * Constructor that forwards a reference to the main model and the document
	 * to be visualized.
	 * 
	 * @param model
	 *            the main model
	 * @param document
	 *            the document to be visualized
	 */
	public GeRuDocumentView(Model model, GeRuDocument document) {
		this.model = model;
		this.model.addObserver(this);
		this.setDocument(document);
		this.document.addObserver(this);

		// Creates the page area.
		pageArea = new JPanel();
		pageArea.setLayout(new BoxLayout(pageArea, BoxLayout.PAGE_AXIS));
		pageArea.setBackground(Color.LIGHT_GRAY);
		selectedPage = null;

		// Adds the scroll bar.
		this.setLayout(new BorderLayout());
		scrollBar = new JScrollPane(pageArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollBar.setBorder(BorderFactory.createEmptyBorder());
		scrollBar.getVerticalScrollBar().setUnitIncrement(20);
		this.add(scrollBar);

		// Attaches the listeners.
		setDocumentController(new GeRuDocumentController(model, this));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gerudok_observer.GObserver#update(gerudok_observer.GNotification,
	 * java.lang.Object)
	 */
	@Override
	public void update(GNotification notification, Object obj) {
		if (notification == GNotification.ADD) {
			if (obj instanceof Page) {
				Page page = (Page) obj;
				addNewChildView(page);
			}
		} else if (notification == GNotification.DELETE) {
			PageView pageView = findPage((Page) obj);
			try {
				pageArea.remove(pageView);
				repaint();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		} else if (notification == GNotification.GNODE_RENAME) {
			JTabbedPane documentTabs = (JTabbedPane) SwingUtilities.getAncestorOfClass(JTabbedPane.class, this);
			for (int i = 0; i < documentTabs.getTabCount(); i++) {
				if (documentTabs.getComponentAt(i) == this) {
					documentTabs.setTitleAt(i, this.getDocument().getName());
				}
			}
		}
	}

	/**
	 * Creates and attaches a new child view based on the received page.
	 * 
	 * @param page
	 *            the page to visualize
	 */
	public void addNewChildView(Page page) {
		PageView pageView = new PageView(model, page);
		pageArea.add(pageView);
		pageArea.scrollRectToVisible(pageView.getBounds());
		repaint();
		for (GNode child : page.getChildren()) {
			Slot slot = (Slot) child;
			pageView.addNewChildView(slot);
		}
	}

	/**
	 * Updates the selected page after the selection changed in the tree. Passes
	 * the selection array to the relevant child element afterwards.
	 * 
	 * @param path
	 *            array of nodes that represents the current tree selection
	 * @param idx
	 *            the index in the path array this view is interested in
	 */
	public void updateSelection(Object[] path, int idx) {
		if (path.length > idx) {
			PageView pageView = findPage((Page) path[idx]);
			if (pageView == null) {
				return;
			}
			if (pageView.getPage() != selectedPage) {
				selectedPage = pageView.getPage();
				try {
					pageArea.scrollRectToVisible(pageView.getBounds());
				} catch (NullPointerException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Finds a child view that represents a page.
	 * 
	 * @param page
	 *            the page to look for
	 * @return the requested view
	 */
	private PageView findPage(Page page) {
		for (Component c : pageArea.getComponents()) {
			if (c instanceof PageView) {
				PageView pageView = (PageView) c;
				if (pageView.getPage() == page) {
					return pageView;
				}
			}
		}
		return null;
	}

	/**
	 * Attaches a listener.
	 * 
	 * @param viewPortChangeListener
	 *            the listener to attach
	 */
	public void attachViewPortChangeListener(ChangeListener viewPortChangeListener) {
		this.scrollBar.getViewport().addChangeListener(viewPortChangeListener);
	}

	/**
	 * Retrieves the page area panel.
	 * 
	 * @return the page area panel
	 */
	public JPanel getPageArea() {
		return pageArea;
	}

	/**
	 * Retrieves the currently selected page.
	 * 
	 * @return selected page
	 */
	public Page getSelectedPage() {
		return selectedPage;
	}

	/**
	 * Sets the currently selected page.
	 * 
	 * @param selectedPage
	 *            currently selected page
	 */
	public void setSelectedPage(Page selectedPage) {
		this.selectedPage = selectedPage;
	}

	/**
	 * Retrieves the document that this view is based on.
	 * 
	 * @return the document that this view is based on
	 */
	public GeRuDocument getDocument() {
		return this.document;
	}

	/**
	 * Sets the document that this view is based on.
	 * 
	 * @param document
	 *            the document that this view is based on
	 */
	public void setDocument(GeRuDocument document) {
		this.document = document;
	}

	/**
	 * Retrieves the corresponding controller.
	 * 
	 * @return the corresponding controller
	 */
	public GeRuDocumentController getDocumentController() {
		return documentController;
	}

	/**
	 * Attaches the controller.
	 * 
	 * @param documentController
	 *            the controller to attach
	 */
	public void setDocumentController(GeRuDocumentController documentController) {
		this.documentController = documentController;
	}

}