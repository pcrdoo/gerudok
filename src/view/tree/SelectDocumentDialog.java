package view.tree;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.event.ListSelectionListener;

import controller.tree.SelectDocumentDialogController;
import controller.tree.SelectProjectDialogController;
import model.Model;
import model.Workspace;
import model.tree.GNode;

public class SelectDocumentDialog extends JDialog{
	Model model;
	GNode selected;
	GNode parent;
	JButton btnOK;
	JList<GNode> list;
	SelectDocumentDialogController controller;
	
	public SelectDocumentDialog(GNode parent, Model model) {
		super();
		this.model = model;
		this.parent = parent;
		
		this.setLayout(new BorderLayout());
		
		JLabel lbl = new JLabel("Select the free Document to add to the Project:");
		this.add(lbl, BorderLayout.NORTH);
		
		DefaultListModel<GNode> listModel = new DefaultListModel<>();
	    list = new JList<GNode>(listModel);
	    
	    this.add(list, BorderLayout.CENTER);
	    
	    for(GNode node : this.model.getFreeNodes()) {
	    	listModel.addElement(node);
	    }
		
	    btnOK = new JButton("OK");
	    btnOK.setEnabled(false);
	    
	    this.add(btnOK, BorderLayout.SOUTH);
	    
		setPreferredSize(new Dimension(500, 400));
		pack();
		setLocationRelativeTo(null);
		
		this.controller = new SelectDocumentDialogController(model, this);
	}
	
	public void addSelectionChangedListener(ListSelectionListener l) {
		this.list.addListSelectionListener(l);
	}
	
	public void addBtnOKListener(ActionListener l) {
		this.btnOK.addActionListener(l);
	}
	
	public void addDoubleClickListener(MouseListener l) {
		this.list.addMouseListener(l);
	}
	
	public void enableBtnOK() {
		this.btnOK.setEnabled(this.list.getSelectedValue() != null);
	}
	
	public GNode getSelected() {
		return (GNode) this.list.getSelectedValue();
	}
	
	public GNode getParentNode() {
		return this.parent;
	}
}
