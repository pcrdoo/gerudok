package view.tree;

import java.awt.Color;
import java.awt.Component;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;

import model.GeRuDocument;
import model.GeRuDocumentLink;
import model.Element;
import model.Page;
import model.Project;
import model.Slot;
import model.Workspace;
import model.tree.GNode;
import view.MainView;

public class GTreeCellRendered extends DefaultTreeCellRenderer{
	
	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean arg6) {

		String iconPath;
		if(value instanceof Workspace) {
			iconPath = "/res/workspace_icon.png";
		} else if(value instanceof Project) {
			iconPath = ((Project)value).isOpened() ? "/res/project_icon.png" : "/res/project_closed_icon.png";
		} else if(value instanceof GeRuDocument) {
			iconPath = "/res/document_icon.png";
		} else if(value instanceof GeRuDocumentLink) {
			iconPath = "/res/document_link_icon.png";
		} else if(value instanceof Page) {
			iconPath = "/res/page_icon.png";
		} else if(value instanceof Slot) {
			iconPath = "/res/slot_icon.png";
		} else if(value instanceof Element) {
			iconPath = "/res/element_icon.png";
		} else
			iconPath = "/res/undefined_icon.png";
		
		
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, arg6);

		Icon icon = new ImageIcon(this.getClass().getResource(iconPath));
		
		this.setIcon(icon);
		this.setText(value.toString());
		
		return this;
	}
}
