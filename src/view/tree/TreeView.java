/***********************************************************************
 * Module:  TreeView.java
 * Author:  Ognjen
 * Purpose: Defines the Class TreeView
 ***********************************************************************/

package view.tree;

import model.Model;
import model.Project;
import model.Workspace;
import model.tree.GNode;
import model.tree.GTreeModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultTreeSelectionModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import constants.Constants;
import controller.tree.TreeController;

public class TreeView extends JPanel{
	
   private TreeController treeController;
   private Model model;
   private WorkspaceTree tree;
   private GTreeModel treeModel;
   
   public TreeView(Model model) {
	   this.model = model;
	   this.initialize();
   }
   
   private void initialize() {
	   
	   // TODO mock ovo srediti
	   GNode root = Workspace.getInstance();
	   
	   //MOCK
//	   for(int i =0;i<5;i++) {
//		   GNode ngn = new GNode("mock_child_" + i); 
//		   root.add(ngn);
//	   }
	   //
	   
	   this.setPreferredSize(Constants.TREE_SIZE);
	   
	   this.treeModel = new GTreeModel();
	   this.treeModel.setRoot(root);
	   
	   this.tree = new WorkspaceTree(this.model);
	   this.tree.setModel(treeModel);
	   
	   GTreeCellRendered gtcr = new GTreeCellRendered();
	   this.tree.setCellRenderer(gtcr);
	   this.tree.setCellEditor(new GTreeCellEditor(this.tree, gtcr, this.model));
	   this.tree.setEditable(true);
	   this.tree.setShowsRootHandles(true);
	   
	   TreeSelectionModel selectionModel = new DefaultTreeSelectionModel();
	   selectionModel.setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
	   this.tree.setSelectionModel(selectionModel);
	   
	   //MOCK
	   this.setBackground(Color.BLACK);
	   
	   this.setLayout(new BorderLayout());
	   this.setMinimumSize(new Dimension(1000, 1000));
	   this.add(new JScrollPane(tree), BorderLayout.CENTER);
	   
	   this.model.setTreeModel(this.treeModel);
	   this.treeController = new TreeController(this.model, this);
   }
   
   public void addTreeListener(MouseListener l) {
	   this.tree.addMouseListener(l);
   }
   
   public WorkspaceTree getTree() {
	   return this.tree;
   }
   
   public Project getSelectedProject() {
	    Object o = tree.getLastSelectedPathComponent();
		if(o instanceof Project) {
			return (Project) o;
		}
		return null;
   }

}