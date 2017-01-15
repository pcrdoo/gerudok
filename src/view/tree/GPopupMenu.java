package view.tree;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import controller.tree.GPopupMenuController;
import model.GeRuDocument;
import model.GeRuDocumentLink;
import model.Element;
import model.ElementContainer;
import model.Model;
import model.Page;
import model.Project;
import model.Slot;
import model.Workspace;
import model.tree.GNode;

/**
 * The popup menu that contains operation to be performed on GNodes.
 * 
 * @author Ognjen Djuricic
 *
 */
public class GPopupMenu extends JPopupMenu {

	/**
	 * Reference to the main model.
	 */
	Model model;
	/**
	 * Instance of the controller for this view.
	 */
	GPopupMenuController controller;
	/**
	 * The node on witch the operations can be performed on.
	 */
	GNode node;
	/**
	 * The add new child option.
	 */
	JMenuItem addNew;
	/**
	 * The add new element option.
	 */
	JMenu addNewElement;
	/**
	 * The edit element option.
	 */
	JMenuItem edit;
	/**
	 * The add new text element option.
	 */
	JMenuItem addNewTextElement;
	/**
	 * The add new graphic element option.
	 */
	JMenuItem addNewGraphicElement;
	/**
	 * The delete the node option.
	 */
	JMenuItem delete;
	/**
	 * The rename the node option.
	 */
	JMenuItem rename;
	/**
	 * The switch workspace option.
	 */
	JMenuItem switchWorkspace;
	/**
	 * The open project option.
	 */
	JMenuItem openClose;
	/**
	 * The create a link of node option.
	 */
	JMenuItem share;
	/**
	 * The add child from free nodes option.
	 */
	JMenuItem addFromFree;
	/**
	 * The save project its file option.
	 */
	JMenuItem saveProject;
	/**
	 * The save project to new file option.
	 */
	JMenuItem saveAsProject;
	/**
	 * The import project form file option.
	 */
	JMenuItem importProject;
	/**
	 * The information witch options are for witch class.
	 */
	HashMap<Class, List<JMenuItem>> menuItems;

	/**
	 * Constructor that sets the selected node.
	 * 
	 * @param model
	 *            The main model.
	 * @param node
	 *            The selected node.
	 */
	public GPopupMenu(Model model, GNode node) {
		super();
		this.model = model;
		this.node = node;

		this.initialize();
		this.controller = new GPopupMenuController(this.model, this);
	}

	/**
	 * Creates and set the text of all the options.
	 */
	private void initialize() {
		this.addNew = new JMenuItem("Add");

		this.addNewElement = new JMenu("Add");
		this.addNewTextElement = new JMenuItem("Text");
		this.addNewGraphicElement = new JMenuItem("Graphics");

		this.addNewElement.add(this.addNewTextElement);
		this.addNewElement.add(this.addNewGraphicElement);

		this.edit = new JMenuItem("Edit");

		this.delete = new JMenuItem("Delete");
		this.rename = new JMenuItem("Rename");
		this.switchWorkspace = new JMenuItem("Switch Workspace");
		this.openClose = new JMenuItem(
				this.node instanceof Project && ((Project) this.node).isOpened() ? "Close" : "Open");
		this.share = new JMenuItem("Share with...");
		this.addFromFree = new JMenuItem("Add from free documents...");
		this.addFromFree.setEnabled(model.hasFreeNodes());
		this.saveProject = new JMenuItem("Save");
		this.saveAsProject = new JMenuItem("Save As");
		this.importProject = new JMenuItem("Import Project");

		this.menuItems = new HashMap<Class, List<JMenuItem>>() {
			{

				put(Workspace.class, Arrays.asList(addNew, importProject, rename, switchWorkspace));
				put(Project.class,
						node.getClass() == Project.class && ((Project) node).isOpened() ? Arrays.asList(addNew,
								addFromFree, delete, rename, openClose, saveProject, saveAsProject)
								: Arrays.asList(delete, openClose));
				put(GeRuDocument.class, Arrays.asList(addNew, share, delete, rename));
				put(GeRuDocumentLink.class, Arrays.asList(delete));
				put(Page.class, Arrays.asList(addNew, delete, rename));
				put(Slot.class, Arrays.asList(delete, rename));
				put(Element.class, Arrays.asList(edit, delete, rename));

			}
		};

		if (this.menuItems.containsKey(node.getClass())) {
			if (node instanceof ElementContainer) {
				this.add(addNewElement);
				if (((ElementContainer) node).getType() != null) {
					addNewElement.setEnabled(false);
				}
			}
			for (JMenuItem item : this.menuItems.get(node.getClass()))
				this.add(item);
		} else {
			if (node instanceof Element) {
				this.add(addNew);
				for (JMenuItem item : this.menuItems.get(Element.class)) {
					this.add(item);
				}
			} else {
				System.err.println("OgiException: Unknown node type in tree");
			}
		}
	}

	/**
	 * Gets the selected node.
	 * 
	 * @return The selected node.
	 */
	public GNode getSelectedNode() {
		return this.node;
	}

	/**
	 * Adds a listener to the corresponding menu item(option).
	 * 
	 * @param l
	 *            The listener to be added.
	 */
	public void setAddNewListener(ActionListener l) {
		this.addNew.addActionListener(l);
	}

	/**
	 * Adds a listener to the corresponding menu item(option).
	 * 
	 * @param l
	 *            The listener to be added.
	 */
	public void setAddNewTextElementListener(ActionListener l) {
		this.addNewTextElement.addActionListener(l);
	}

	/**
	 * Adds a listener to the corresponding menu item(option).
	 * 
	 * @param l
	 *            The listener to be added.
	 */
	public void setAddNewGraphicElementListener(ActionListener l) {
		this.addNewGraphicElement.addActionListener(l);
	}

	/**
	 * Adds a listener to the corresponding menu item(option).
	 * 
	 * @param l
	 *            The listener to be added.
	 */
	public void setDeleteListener(ActionListener l) {
		this.delete.addActionListener(l);
	}

	/**
	 * Adds a listener to the corresponding menu item(option).
	 * 
	 * @param l
	 *            The listener to be added.
	 */
	public void setRenameListener(ActionListener l) {
		this.rename.addActionListener(l);
	}

	/**
	 * Adds a listener to the corresponding menu item(option).
	 * 
	 * @param l
	 *            The listener to be added.
	 */
	public void setSwitchWorkspaceListener(ActionListener l) {
		this.switchWorkspace.addActionListener(l);
	}

	/**
	 * Adds a listener to the corresponding menu item(option).
	 * 
	 * @param l
	 *            The listener to be added.
	 */
	public void setOpenCloseListener(ActionListener l) {
		this.openClose.addActionListener(l);
	}

	/**
	 * Adds a listener to the corresponding menu item(option).
	 * 
	 * @param l
	 *            The listener to be added.
	 */
	public void setShareListener(ActionListener l) {
		this.share.addActionListener(l);
	}

	/**
	 * Adds a listener to the corresponding menu item(option).
	 * 
	 * @param l
	 *            The listener to be added.
	 */
	public void setElementEditListener(ActionListener l) {
		this.edit.addActionListener(l);
	}

	/**
	 * Adds a listener to the corresponding menu item(option).
	 * 
	 * @param l
	 *            The listener to be added.
	 */
	public void setAddFromFreeListener(ActionListener l) {
		this.addFromFree.addActionListener(l);
	}

	/**
	 * Adds a listener to the corresponding menu item(option).
	 * 
	 * @param l
	 *            The listener to be added.
	 */
	public void setSaveProjectListener(ActionListener l) {
		this.saveProject.addActionListener(l);
	}

	/**
	 * Adds a listener to the corresponding menu item(option).
	 * 
	 * @param l
	 *            The listener to be added.
	 */
	public void setSaveAsProjectListener(ActionListener l) {
		this.saveAsProject.addActionListener(l);
	}

	/**
	 * Adds a listener to the corresponding menu item(option).
	 * 
	 * @param l
	 *            The listener to be added.
	 */
	public void setImportProjectListener(ActionListener l) {
		this.importProject.addActionListener(l);
	}
}
