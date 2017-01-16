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

public class MenuBarView extends JMenuBar{
	
   public MenuBarController menuBarController;
   public Model model;
   
   public MenuBarView(Model model) {
	   this.model = model;
	   menuBarController = new MenuBarController(model, this);
	   
	   JMenu fileMenu = new JMenu("File"); 
	   
	   JMenuItem newWorkspaceItem = new JMenuItem("New workspace");
	   newWorkspaceItem.addActionListener(menuBarController.getNewWorkspaceListener());
	   JMenuItem switchWorkspaceItem = new JMenuItem("Switch workspace");
	   switchWorkspaceItem.addActionListener(menuBarController.getSwitchWorkspaceListener());
	   JMenuItem saveWorkspaceItem = new JMenuItem("Save workspace");
	   saveWorkspaceItem.addActionListener(menuBarController.getSaveWorkspaceListener());
	   JMenuItem openItem = new JMenuItem("Open");
	   openItem.addActionListener(menuBarController.getLoadActionListener());
	   JMenuItem saveItem = new JMenuItem("Save");
	   saveItem.addActionListener(menuBarController.getSaveActionListener());
	   JMenuItem saveAsItem = new JMenuItem("Save as");
	   saveAsItem.addActionListener(menuBarController.getSaveAsActionListener());
	   
	   fileMenu.add(newWorkspaceItem);
	   fileMenu.add(switchWorkspaceItem);
	   fileMenu.add(saveWorkspaceItem);
	   fileMenu.addSeparator();
	   fileMenu.add(openItem);
	   fileMenu.addSeparator();
	   fileMenu.add(saveItem);
	   fileMenu.add(saveAsItem);
	   fileMenu.addSeparator();
	   
	   JMenu editMenu = new JMenu("Edit");
	   JMenuItem addNewChildItem = new JMenuItem("Add new");
	   addNewChildItem.addActionListener(menuBarController.getAddActionListener());
	   JMenuItem openSelectedProjectItem = new JMenuItem("Open project");
	   openSelectedProjectItem.addActionListener(menuBarController.getOpenProjectActionListener());
	   JMenuItem closeSelectedProjectItem = new JMenuItem("Close project");
	   closeSelectedProjectItem.addActionListener(menuBarController.getCloseProjectActionListener());
	   JMenuItem deleteItem = new JMenuItem("Delete");
	   deleteItem.addActionListener(menuBarController.getDeleteActionListener());
	   JMenuItem renameItem = new JMenuItem("Rename");
	   renameItem.addActionListener(menuBarController.getRenameActionListener());
	   JMenuItem shareItem = new JMenuItem("Share");
	   shareItem.addActionListener(menuBarController.getShareActionListener());
	   
	   editMenu.add(addNewChildItem);
	   editMenu.addSeparator();
	   editMenu.add(openSelectedProjectItem);
	   editMenu.add(closeSelectedProjectItem);
	   editMenu.addSeparator();
	   editMenu.add(shareItem);
	   editMenu.add(deleteItem);
	   editMenu.add(renameItem);
	   
	   JMenu aboutMenu = new JMenu("About Us");
	   
	   JMenuItem PCRItem = new JMenuItem("About PCR");
	   
	   PCRItem.addActionListener(menuBarController.getAboutActionListener());
	   
	   aboutMenu.add(PCRItem);
	   
	   this.add(fileMenu);
	   this.add(editMenu);
	   this.add(aboutMenu);	   
   }

}