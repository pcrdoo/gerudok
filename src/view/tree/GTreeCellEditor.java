package view.tree;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;

import model.tree.GNode;

public class GTreeCellEditor extends DefaultTreeCellEditor implements ActionListener{
	
	private Object stavka=null;
	private JTextField edit=null; 

	public GTreeCellEditor(JTree arg0, DefaultTreeCellRenderer arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}
	
	public Component getTreeCellEditorComponent(JTree arg0, Object value, boolean isSelected, boolean expanded, boolean leaf, int row) {
		super.getTreeCellEditorComponent(arg0, value, isSelected, expanded, leaf, row);
		
		stavka=value;
		
		edit=new JTextField(value.toString());
		edit.addActionListener(this);
		return edit;
	}
	
	public boolean isCellEditable(EventObject e) {
		if (e instanceof MouseEvent)
			if (((MouseEvent)e).getClickCount()==3){
				return true;
				}
		return false;
	}
	
	public void actionPerformed(ActionEvent e){
		if (stavka instanceof GNode){
		((GNode)stavka).setName(e.getActionCommand());
		}else{
		((GNode)stavka).setName(e.getActionCommand());
		}
	}
}
