package view.tree;

import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import controler.tree.GPopupMenuController;
import model.Model;
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
	
	public GPopupMenu(Model model, GNode node) {
		super();
		this.model = model;
		this.node = node;
		this.initialize();
		
		if(node instanceof Workspace) {
			this.initWorkSpace();
		} else if(node instanceof GNode) {
			this.initOther();
		} else {
			//TODO
			System.err.println("OgiException: Unknown node type in tree");
		}
		
		this.controller = new GPopupMenuController(this.model, this);
	}
	
	private void initialize() {
		//TODO strings from res
		this.addNew = new JMenuItem("Add");
		this.delete = new JMenuItem("Delete");
		this.rename = new JMenuItem("Rename");
		this.switchWorkspace = new JMenuItem("Switch Workspace");
	}
	
	private void initOther() {
		this.add(this.addNew);
		this.add(this.delete);
		this.add(this.rename);
	}
	
	private void initWorkSpace() {
		this.add(this.addNew);
		this.add(this.switchWorkspace);
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
}
