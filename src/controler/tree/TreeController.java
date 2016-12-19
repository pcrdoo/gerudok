/***********************************************************************
 * Module:  TreeController.java
 * Author:  Ognjen
 * Purpose: Defines the Class TreeController
 ***********************************************************************/

package controler.tree;

import model.Model;
import model.tree.GNode;
import view.tree.GPopupMenu;
import view.tree.TreeView;
import view.tree.WorkspaceTree;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;

import javax.swing.SwingUtilities;
import javax.swing.tree.TreePath;

public class TreeController {
	
   public Model model;
   public TreeView treeView;
   
   public TreeController(Model model, TreeView treeView) {
	   this.model = model;
	   this.treeView = treeView;
	   
	   this.treeView.addTreeListener(new RightClickListener());
   }
   
   class RightClickListener implements MouseListener {
	   @Override
	   public void mouseClicked(MouseEvent e) {
		   if(SwingUtilities.isRightMouseButton(e)) {
			   //TODO ne znam odakle da pristupim treeu?
			   
			   WorkspaceTree tree = treeView.getTree();
			   TreePath path = tree.getPathForLocation(e.getX(), e.getY());
			   
			   if(path == null)
				   return;
			   
			   tree.setSelectionPath(path);
			   
			   //TODO open context menu for node
			   System.out.println(tree.getLastSelectedPathComponent());
			   GPopupMenu pm = new GPopupMenu(model, (GNode)tree.getLastSelectedPathComponent());
			   pm.show(e.getComponent(), e.getX(), e.getY());
		   }
	   }
	   
	   @Override
	   public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
	   }
		
		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
   }
}