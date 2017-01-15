/***********************************************************************
 * Module:  ToolBarView.java
 * Author:  Ognjen
 * Purpose: Defines the Class ToolBarView
 ***********************************************************************/

package view;

import model.Model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.File;
import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import controller.MenuBarController;
import controller.ToolBarControler;

/** @pdOid 89b60961-0c68-4ad1-8eb4-5a8b8a8dec28 */
public class ToolBarView extends JToolBar {
   /** @pdRoleInfo migr=no name=ToolBarControler assc=association1 mult=1..1 type=Composition */
   public ToolBarControler toolBarControler;
   /** @pdRoleInfo migr=no name=Model assc=association11 mult=1..1 */
   public Model model;
   
   public ToolBarView(Model model) {
	   this.model = model;
	   toolBarControler = new ToolBarControler(model, this);
	   
	// TODO
	   JButton btnAdd = new JButton();
	   btnAdd.setToolTipText("Add");
	   btnAdd.setIcon(new ImageIcon("src/res/new_toolbar_icon.png"));
	   add(btnAdd);
	   
	   addSeparator();
	   
	   JButton btnNewWorkspace = new JButton();
	   btnNewWorkspace.setToolTipText("New workspace");
	   btnNewWorkspace.setIcon(new ImageIcon("src/res/new_workspace_toolbar_icon.png"));
	   add(btnNewWorkspace);
	   
	   JButton btnSwitchWorkspace = new JButton();
	   btnSwitchWorkspace.setToolTipText("Switch workspace");
	   btnSwitchWorkspace.setIcon(new ImageIcon("src/res/switch_workspace_toolbar_icon.png"));
	   add(btnSwitchWorkspace);
	   
	   addSeparator();
	   
	   JButton btnOpen = new JButton();
	   btnOpen.setToolTipText("Open");
	   btnOpen.setIcon(new ImageIcon("src/res/open_toolbar_icon.png"));
	   btnOpen.addActionListener(toolBarControler.getLoadActionListener());
	   add(btnOpen);
	   
	   JButton btnSave = new JButton();
	   btnSave.setToolTipText("Save");
	   btnSave.setIcon(new ImageIcon("src/res/save_toolbar_icon.png"));
	   btnSave.addActionListener(toolBarControler.getSaveActionListener());
	   add(btnSave);
	   
	   JButton btnSaveAs = new JButton();
	   btnSaveAs.setToolTipText("Save As");
	   btnSaveAs.setIcon(new ImageIcon("src/res/save_as_toolbar_icon.png"));
	   btnSaveAs.addActionListener(toolBarControler.getSaveAsActionListener());
	   add(btnSaveAs);
	   
	   addSeparator();
	   
	// TODO
	   JButton btnOpenProject = new JButton();
	   btnOpenProject.setToolTipText("Open selected project");
	   btnOpenProject.setIcon(new ImageIcon("src/res/maximize_toolbar_icon.png"));
	   add(btnOpenProject);
	   
	// TODO
	   JButton btnCloseProject = new JButton();
	   btnCloseProject.setToolTipText("Close selected project");
	   btnCloseProject.setIcon(new ImageIcon("src/res/minimize_toolbar_icon.png"));
	   add(btnCloseProject);

	   addSeparator();
	   
	// TODO
	   JButton btnDelete = new JButton();
	   btnDelete.setToolTipText("Delete");
	   btnDelete.setIcon(new ImageIcon("src/res/delete_toolbar_icon.png"));
	   add(btnDelete);
		   
	// TODO
	   JButton btnRename = new JButton();
	   btnRename.setToolTipText("Rename");
	   btnRename.setIcon(new ImageIcon("src/res/rename_toolbar_icon.png"));
	   add(btnRename);
	   
	   // TODO
	   JButton btnShare = new JButton();
	   btnShare.setToolTipText("Share");
	   btnShare.setIcon(new ImageIcon("src/res/share_toolbar_icon.png"));
	   add(btnShare);
	   
	   setBackground(new Color(255,255,204));
   }
}

