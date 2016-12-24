package controller;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

import model.Model;
import view.DesktopView;

public class TreeListener implements TreeSelectionListener {
	   private Model model;
	   
	   public TreeListener(Model model) {
		   this.model = model;
	   }
	   
		@Override
		public void valueChanged(TreeSelectionEvent e) {
			TreePath path = e.getPath(); // Strasno...
			System.out.println(path);
			model.doDesktopSelection(path);
		}
}