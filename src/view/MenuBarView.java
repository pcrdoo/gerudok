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

/** @pdOid 90d49140-4eb5-4916-b044-c9f452fdd3a2 */
public class MenuBarView extends JMenuBar{
   /** @pdRoleInfo migr=no name=MenuBarController assc=association2 mult=1..1 type=Composition */
   public MenuBarController menuBarController;
   /** @pdRoleInfo migr=no name=Model assc=association10 mult=1..1 */
   public Model model;
   
   public MenuBarView(Model model) {
	   this.model = model;
	   
	   JMenu fileMenu = new JMenu("File"); 
	   
	   JMenuItem newWorkspaceItem = new JMenuItem("New workspace");
	   JMenuItem closeItem = new JMenuItem("Close");
	   JMenuItem closeAllItem = new JMenuItem("Close all");
	   JMenuItem openItem = new JMenuItem("Open");
	   JMenuItem saveItem = new JMenuItem("Save");
	   JMenuItem saveAsItem = new JMenuItem("Save as");
	   JMenuItem exitItem = new JMenuItem("Exit GeRuDok");
	   
	   fileMenu.add(newWorkspaceItem);
	   fileMenu.addSeparator();
	   fileMenu.add(closeItem);
	   fileMenu.add(closeAllItem);
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
	   
	   editMenu.add(cutItem);
	   editMenu.add(copyItem);
	   editMenu.add(pasteItem);
	   editMenu.addSeparator();
	   editMenu.add(undoItem);
	   editMenu.add(redoItem);
	   editMenu.addSeparator();
	   editMenu.add(deleteItem);
	   
	   JMenu windowMenu = new JMenu("Window");
	   
	   JMenuItem minimizeItem = new JMenuItem("Minimize");
	   JMenuItem maximizeItem = new JMenuItem("Maximize");
	   
	   JMenu lookAndFeelMenu = new JMenu("Look And Feel");
	   
	   JMenuItem LnF1 = new JMenuItem("LnF1");
	   JMenuItem LnF2 = new JMenuItem("LnF2");
	   JMenuItem LnF3 = new JMenuItem("LnF3");
	   
	   lookAndFeelMenu.add(LnF1);
	   lookAndFeelMenu.add(LnF2);
	   lookAndFeelMenu.add(LnF3);
	   
	   windowMenu.add(minimizeItem);
	   windowMenu.add(maximizeItem);
	   windowMenu.addSeparator();
	   windowMenu.add(lookAndFeelMenu);
	   
	   JMenu aboutMenu = new JMenu("About Us");
	   
	   JMenuItem PCRItem = new JMenuItem("About PCR");
	   
	   aboutMenu.add(PCRItem);
	   
	   this.add(fileMenu);
	   this.add(editMenu);
	   this.add(windowMenu);
	   this.add(aboutMenu);	   
   }

}