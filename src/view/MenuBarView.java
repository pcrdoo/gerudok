/***********************************************************************
 * Module:  MenuBarView.java
 * Author:  Ognjen
 * Purpose: Defines the Class MenuBarView
 ***********************************************************************/

package view;

import model.Model;
import java.util.*;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controller.MenuBarController;
import controller.ProjectController;

/** @pdOid 90d49140-4eb5-4916-b044-c9f452fdd3a2 */
public class MenuBarView extends JMenuBar{
   /** @pdRoleInfo migr=no name=MenuBarController assc=association2 mult=1..1 type=Composition */
   public MenuBarController menuBarController;
   /** @pdRoleInfo migr=no name=Model assc=association10 mult=1..1 */
   public Model model;
   
   public MenuBarView(Model model) {
	   this.model = model;
	   menuBarController = new MenuBarController(model, this);
	   
	   JMenu fileMenu = new JMenu("File"); 
	   
	   JMenuItem newWorkspaceItem = new JMenuItem("New workspace");
	   JMenuItem switchWorkspaceItem = new JMenuItem("Switch workspace");
	   JMenuItem openItem = new JMenuItem("Open");
	   openItem.addActionListener(menuBarController.getLoadActionListener());
	   JMenuItem saveItem = new JMenuItem("Save");
	   saveItem.addActionListener(menuBarController.getSaveActionListener());
	   JMenuItem saveAsItem = new JMenuItem("Save as");
	   saveAsItem.addActionListener(menuBarController.getSaveAsActionListener());
	   JMenuItem exitItem = new JMenuItem("Exit GeRuDok");
	   
	   fileMenu.add(newWorkspaceItem);
	   fileMenu.add(switchWorkspaceItem);
	   fileMenu.addSeparator();
	   fileMenu.add(openItem);
	   fileMenu.addSeparator();
	   fileMenu.add(saveItem);
	   fileMenu.add(saveAsItem);
	   fileMenu.addSeparator();
	   fileMenu.add(exitItem);
	   
	   JMenu editMenu = new JMenu("Edit");
	   
	   JMenuItem cutItem = new JMenuItem("Cut");
	   JMenuItem copyItem = new JMenuItem("Copy");
	   JMenuItem pasteItem = new JMenuItem("Paste");
	   JMenuItem undoItem = new JMenuItem("Undo");
	   JMenuItem redoItem = new JMenuItem("Redu");
	   JMenuItem deleteItem = new JMenuItem("Delete");
	   JMenuItem renameItem = new JMenuItem("Rename");
	   
	   editMenu.add(cutItem);
	   editMenu.add(copyItem);
	   editMenu.add(pasteItem);
	   editMenu.addSeparator();
	   editMenu.add(undoItem);
	   editMenu.add(redoItem);
	   editMenu.addSeparator();
	   editMenu.add(deleteItem);
	   editMenu.add(renameItem);
	   
	   JMenu aboutMenu = new JMenu("About Us");
	   
	   JMenuItem PCRItem = new JMenuItem("About PCR");
	   
	   aboutMenu.add(PCRItem);
	   
	   this.add(fileMenu);
	   this.add(editMenu);
	   this.add(aboutMenu);	   
   }

}