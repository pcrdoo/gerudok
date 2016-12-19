package view.tree;

import java.awt.Color;
import java.awt.Component;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;

import model.tree.GNode;
import view.MainView;

public class GTreeCellRendered extends DefaultTreeCellRenderer{

	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean arg6) {
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, arg6);
		
		Icon icon = new ImageIcon(this.getClass().getResource("/res/folder_icon_mock.jpg"));
		this.setIcon(icon);
		this.setText(value.toString());
		
		return this;
	}
}
