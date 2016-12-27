package view.tree;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Model;
import model.Workspace;
import model.tree.GLink;
import model.tree.GNode;

public class SelectDialog extends JDialog implements ListSelectionListener{
	
	Model model;
	GNode selected;
	GNode shared;
	JButton btnOK;
	
	public SelectDialog(GNode shared, Model model) {
		super();
		
		
		this.model = model;
		this.shared = shared;
		
		this.setLayout(new BorderLayout());
		
		JLabel lbl = new JLabel("test");
		this.add(lbl, BorderLayout.NORTH);
		
		DefaultListModel listModel = new DefaultListModel();
	    JList list = new JList(listModel);
	    
	    
	    
	    this.add(list, BorderLayout.CENTER);
	    
	    list.addListSelectionListener(this);
	    
	    //only for projects
	    for(GNode node : Workspace.getInstance().getChildren()) {
	    	if(node != shared.getParent())
	    		listModel.addElement(node);
	    }
		
	    btnOK = new JButton("OK");
	    btnOK.setEnabled(false);
	    btnOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cclose();
			}
		});
	    
	    this.add(btnOK, BorderLayout.SOUTH);
	    
		setPreferredSize(new Dimension(500, 400));
		pack();
		setLocationRelativeTo(null);
	}
	
	private void cclose() {
		this.model.doTreeSelection(this.selected.addNewLinkChild(this.shared));
		this.dispose();
	}
	
	public GNode getSelected() {
		return this.selected;
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		btnOK.setEnabled(true);
		System.out.println(((JList)arg0.getSource()).getSelectedValue());
		this.selected = (GNode)((JList)arg0.getSource()).getSelectedValue();
	}
}
