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

public class GPopupMenu extends JPopupMenu {
	
	Model model;
	GPopupMenuController controller;
	GNode node;
	JMenuItem addNew;
	JMenu addNewElement;
	JMenuItem edit;
	JMenuItem addNewTextElement;
	JMenuItem addNewGraphicElement;
	JMenuItem addNewSoundElement;
	JMenuItem delete;
	JMenuItem rename;
	JMenuItem switchWorkspace;
	JMenuItem openClose;
	JMenuItem share;
	JMenuItem addFromFree;
	JMenuItem saveProject;
	JMenuItem saveAsProject;
	JMenuItem importProject;
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
		
		this.addNewElement = new JMenu("Add");
		this.addNewTextElement = new JMenuItem("Text");
		this.addNewGraphicElement = new JMenuItem("Graphics");
		this.addNewSoundElement = new JMenuItem("Sound");
		
		this.addNewElement.add(this.addNewTextElement);
		this.addNewElement.add(this.addNewGraphicElement);
		this.addNewElement.add(this.addNewSoundElement);
		
		this.edit = new JMenuItem("Edit");
		
		this.delete = new JMenuItem("Delete");
		this.rename = new JMenuItem("Rename");
		this.switchWorkspace = new JMenuItem("Switch Workspace");
		this.openClose = new JMenuItem(this.node instanceof Project && ((Project)this.node).isOpened() ? "Close" : "Open");
		this.share = new JMenuItem("Share with...");
		this.addFromFree = new JMenuItem("Add from free documents...");
		this.addFromFree.setEnabled(model.hasFreeNodes());
		this.saveProject = new JMenuItem("Save");
		this.saveAsProject = new JMenuItem("Save As");
		this.importProject = new JMenuItem("Import Project");
		
		this.menuItems = new HashMap<Class, List<JMenuItem>>(){{

			put(Workspace.class, Arrays.asList(addNew, importProject, rename, switchWorkspace));
			put(Project.class, node.getClass() == Project.class && ((Project)node).isOpened() ? Arrays.asList(addNew, addFromFree, delete, rename, openClose, saveProject, saveAsProject) : Arrays.asList(delete, openClose));
			put(GeRuDocument.class, Arrays.asList(addNew, share, delete, rename));
			put(GeRuDocumentLink.class, Arrays.asList(delete));
			put(Page.class, Arrays.asList(addNew, delete, rename));
			put(Slot.class, Arrays.asList(delete, rename));
			put(Element.class, Arrays.asList(edit, delete, rename));
			
			}};
		
		if(this.menuItems.containsKey(node.getClass())) {
			if (node instanceof ElementContainer) {
				this.add(addNewElement);
				if (((ElementContainer)node).getType() != null) {
					addNewElement.setEnabled(false);
				}
			}
			for(JMenuItem item : this.menuItems.get(node.getClass()))
				this.add(item);
		} else {
			if (node instanceof Element) {
				// hak oko ogijevog haka
				this.add(addNew);
				for (JMenuItem item : this.menuItems.get(Element.class)) {
					this.add(item);
				}
			} else {
				System.err.println("OgiException: Unknown node type in tree");
			}
		}
	}
	
	public GNode getSelectedNode() {
		return this.node;
	}
	
	public void setAddNewListener(ActionListener l) {
		this.addNew.addActionListener(l);
	}

	public void setAddNewTextElementListener(ActionListener l) {
		this.addNewTextElement.addActionListener(l);
	}
	
	public void setAddNewSoundElementListener(ActionListener l) {
		this.addNewSoundElement.addActionListener(l);
	}
	
	public void setAddNewGraphicElementListener(ActionListener l) {
		this.addNewGraphicElement.addActionListener(l);
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
	
	public void setOpenCloseListener(ActionListener l) {
		this.openClose.addActionListener(l);
	}
	
	public void setShareListener(ActionListener l) {
		this.share.addActionListener(l);
	}
	
	public void setElementEditListener(ActionListener l) {
		this.edit.addActionListener(l);
	}
	
	public void setAddFromFreeListener(ActionListener l) {
		this.addFromFree.addActionListener(l);
	}
	
	public void setSaveProjectListener(ActionListener l) {
		this.saveProject.addActionListener(l);
	}
	
	public void setSaveAsProjectListener(ActionListener l) {
		this.saveAsProject.addActionListener(l);
	}
	
	public void setImportProjectListener(ActionListener l) {
		this.importProject.addActionListener(l);
	}
}
