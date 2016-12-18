/***********************************************************************
 * Module:  TreeView.java
 * Author:  Ognjen
 * Purpose: Defines the Class TreeView
 ***********************************************************************/

package view;

import controler.TreeController;
import model.Model;
import java.util.*;

import javax.swing.JTree;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

public class TreeView extends JTree{
	
   private TreeController treeController;
   private Model model;
   
   public TreeView(Model model) {
	   this.model = model;
	   this.initialize();
   }
   
   private void initialize() {
	   
	   //MOCK
	   //MutableTreeNode aa;
	   //this.setModel(treeModel);
	   
	   
	   this.treeController = new TreeController(this.model, this);
   }

}