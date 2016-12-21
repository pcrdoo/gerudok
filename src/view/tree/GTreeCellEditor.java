package view.tree;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;

import controler.tree.GTreeCellEditorController;
import model.tree.GNode;

public class GTreeCellEditor extends DefaultTreeCellEditor{
	
	private GNode node;
	private JTextField tfName;
	private GTreeCellEditorController controller;

	public GTreeCellEditor(JTree arg0, DefaultTreeCellRenderer arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
		this.tfName = new JTextField("test ogi oig test");
		this.controller = new GTreeCellEditorController(this);
	}
	
	public Component getTreeCellEditorComponent(JTree arg0, Object value, boolean isSelected, boolean expanded, boolean leaf, int row) {
		super.getTreeCellEditorComponent(arg0, value, isSelected, expanded, leaf, row);
		
		this.node = (GNode)value;
		this.tfName.setText(value.toString());
		return this.tfName;
	}
	
	public GNode getNode() {
		return this.node;
	}
	
	public String getNameText() {
		return this.tfName.getText();
	}
	
	public void setNameFocusListener(FocusListener l) {
		System.out.println("aa");
		this.tfName.addFocusListener(l);
	}
	
	public boolean isCellEditable(EventObject e) {
		if (e instanceof MouseEvent)
			if (((MouseEvent)e).getClickCount() == 3){
				return true;
				}
		return false;
	}
}
