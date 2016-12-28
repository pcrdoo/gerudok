package view.tree;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.tree.SelectDialogController;
import model.Model;
import model.Workspace;
import model.tree.GLink;
import model.tree.GNode;

public class SelectDialog extends JDialog{
	
	Model model;
	GNode selected;
	GNode shared;
	JButton btnOK;
	JList list;
	SelectDialogController controller;
	
	public SelectDialog(GNode shared, Model model) {
		super();
		this.model = model;
		this.shared = shared;
		
		this.setLayout(new BorderLayout());
		
		JLabel lbl = new JLabel("Select the Project to share the Document with:");
		this.add(lbl, BorderLayout.NORTH);
		
		DefaultListModel listModel = new DefaultListModel();
	    list = new JList(listModel);
	    
	    
	    
	    this.add(list, BorderLayout.CENTER);
	    
	    //only for projects
	    for(GNode node : Workspace.getInstance().getChildren()) {
	    	if(node != shared.getParent())
	    		listModel.addElement(node);
	    }
		
	    btnOK = new JButton("OK");
	    btnOK.setEnabled(false);
	    
	    this.add(btnOK, BorderLayout.SOUTH);
	    
		setPreferredSize(new Dimension(500, 400));
		pack();
		setLocationRelativeTo(null);
		
		this.controller = new SelectDialogController(model, this);
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
	
	public GNode getShared() {
		return this.shared;
	}
}
