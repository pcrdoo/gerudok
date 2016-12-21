package view.tree;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import controller.tree.GPopupMenuController;
import model.Document;
import model.Element;
import model.Model;
import model.Page;
import model.Project;
import model.Slot;
import model.Workspace;
import model.tree.GNode;

public class GPopupMenu extends JPopupMenu {
	
	Model model;
	GPopupMenuController controller;
	GNode node;
	JMenuItem addNew;
	JMenuItem delete;
	JMenuItem rename;
	JMenuItem switchWorkspace;
	JMenuItem close;
	HashMap<Class, List<JMenuItem>> menuItems;
	
	public GPopupMenu(Model model, GNode node) {
		super();
		this.model = model;
		this.node = node;
		
		this.initialize();
		this.controller = new GPopupMenuController(this.model, this);
	}
	
	private void initialize() {
		//TODO strings from res
		this.addNew = new JMenuItem("Add");
		this.delete = new JMenuItem("Delete");
		this.rename = new JMenuItem("Rename");
		this.switchWorkspace = new JMenuItem("Switch Workspace");
		this.close = new JMenuItem(this.node instanceof Project && ((Project)this.node).isOpened() ? "Close" : "Open");
		
		this.menuItems = new HashMap<Class, List<JMenuItem>>(){{

			put(Workspace.class, Arrays.asList(addNew, rename, switchWorkspace));
			put(Project.class, node.getClass() == Project.class && ((Project)node).isOpened() ? Arrays.asList(addNew, delete, rename, close) : Arrays.asList(delete, close));
			put(Document.class, Arrays.asList(addNew, delete, rename));
			put(Page.class, Arrays.asList(addNew, delete, rename));
			put(Slot.class, Arrays.asList(addNew, delete, rename));
			put(Element.class, Arrays.asList(addNew, delete, rename));
			
			}};
		
		if(this.menuItems.containsKey(node.getClass())) {
			for(JMenuItem item : this.menuItems.get(node.getClass()))
				this.add(item);
		} else {
			System.err.println("OgiException: Unknown node type in tree");
		}
	}
	
	public GNode getSelectedNode() {
		return this.node;
	}
	
	public void setAddNewListener(ActionListener l) {
		this.addNew.addActionListener(l);
	}
	
	public void setDeleteListener(ActionListener l) {
		this.delete.addActionListener(l);
	}
	
	public void setRenameListener(ActionListener l) {
		this.rename.addActionListener(l);
	}

	public void setSwitchWorkspaceListener(ActionListener l) {
		this.switchWorkspace.addActionListener(l);
	}
	
	public void setCloseListener(ActionListener l) {
		this.close.addActionListener(l);
	}
}
