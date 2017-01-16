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

public class ToolBarView extends JToolBar {
   public ToolBarControler toolBarControler;
   public Model model;
   
   public ToolBarView(Model model) {
	   this.model = model;
	   toolBarControler = new ToolBarControler(model, this);
	   
	   JButton btnAdd = new JButton();
	   btnAdd.setToolTipText("Add");
	   btnAdd.setIcon(new ImageIcon("src/res/new_toolbar_icon.png"));
	   btnAdd.addActionListener(toolBarControler.getAddActionListener());
	   add(btnAdd);
	   
	   addSeparator();
	   
	   JButton btnNewWorkspace = new JButton();
	   btnNewWorkspace.setToolTipText("New workspace");
	   btnNewWorkspace.setIcon(new ImageIcon("src/res/new_workspace_toolbar_icon.png"));
	   btnNewWorkspace.addActionListener(toolBarControler.getNewWorkspaceListener());
	   add(btnNewWorkspace);
	   
	   JButton btnSwitchWorkspace = new JButton();
	   btnSwitchWorkspace.setToolTipText("Switch workspace");
	   btnSwitchWorkspace.setIcon(new ImageIcon("src/res/switch_workspace_toolbar_icon.png"));
	   btnSwitchWorkspace.addActionListener(toolBarControler.getSwitchWorkspaceListener());
	   add(btnSwitchWorkspace);
	   
	   JButton btnSaveWorkspace = new JButton();
	   btnSaveWorkspace.setToolTipText("Save workspace");
	   btnSaveWorkspace.setIcon(new ImageIcon("src/res/save_workspace_toolbar_icon.png"));
	   btnSaveWorkspace.addActionListener(toolBarControler.getSaveWorkspaceListener());
	   add(btnSaveWorkspace);
	   
	   addSeparator();
	   
	   JButton btnOpen = new JButton();
	   btnOpen.setToolTipText("Import project");
	   btnOpen.setIcon(new ImageIcon("src/res/open_toolbar_icon.png"));
	   btnOpen.addActionListener(toolBarControler.getLoadActionListener());
	   add(btnOpen);
	   
	   JButton btnSave = new JButton();
	   btnSave.setToolTipText("Save selected project");
	   btnSave.setIcon(new ImageIcon("src/res/save_toolbar_icon.png"));
	   btnSave.addActionListener(toolBarControler.getSaveActionListener());
	   add(btnSave);
	   
	   JButton btnSaveAs = new JButton();
	   btnSaveAs.setToolTipText("Save selected project as");
	   btnSaveAs.setIcon(new ImageIcon("src/res/save_as_toolbar_icon.png"));
	   btnSaveAs.addActionListener(toolBarControler.getSaveAsActionListener());
	   add(btnSaveAs);
	   
	   addSeparator();
	   
	   JButton btnOpenProject = new JButton();
	   btnOpenProject.setToolTipText("Open selected project");
	   btnOpenProject.setIcon(new ImageIcon("src/res/maximize_toolbar_icon.png"));
	   btnOpenProject.addActionListener(toolBarControler.getOpenProjectActionListener());
	   add(btnOpenProject);
	   
	   JButton btnCloseProject = new JButton();
	   btnCloseProject.setToolTipText("Close selected project");
	   btnCloseProject.setIcon(new ImageIcon("src/res/minimize_toolbar_icon.png"));
	   btnCloseProject.addActionListener(toolBarControler.getCloseProjectActionListener());
	   add(btnCloseProject);

	   addSeparator();
	   
	   JButton btnDelete = new JButton();
	   btnDelete.setToolTipText("Delete");
	   btnDelete.setIcon(new ImageIcon("src/res/delete_toolbar_icon.png"));
	   btnDelete.addActionListener(toolBarControler.getDeleteActionListener());
	   add(btnDelete);
		   
	   JButton btnRename = new JButton();
	   btnRename.setToolTipText("Rename");
	   btnRename.setIcon(new ImageIcon("src/res/rename_toolbar_icon.png"));
	   btnRename.addActionListener(toolBarControler.getRenameActionListener());
	   add(btnRename);
	   
	   JButton btnShare = new JButton();
	   btnShare.setToolTipText("Share");
	   btnShare.setIcon(new ImageIcon("src/res/share_toolbar_icon.png"));
	   btnShare.addActionListener(toolBarControler.getShareActionListener());
	   add(btnShare);
	   
	   addSeparator();
	   
	   JButton btnCascade = new JButton();
	   btnCascade.setToolTipText("Cascade");
	   btnCascade.setIcon(new ImageIcon("src/res/share_toolbar_icon.png"));
	   btnCascade.addActionListener(toolBarControler.getCascadeActionListener());
	   add(btnCascade);
		   
	   JButton btnTileHorizontaly = new JButton();
	   btnTileHorizontaly.setToolTipText("Tile horizontaly");
	   btnTileHorizontaly.setIcon(new ImageIcon("src/res/share_toolbar_icon.png"));
	   btnTileHorizontaly.addActionListener(toolBarControler.getTileHorizontalyActionListener());
	   add(btnTileHorizontaly);
	   
	   JButton btnTileVerticaly = new JButton();
	   btnTileVerticaly.setToolTipText("Tile verticaly");
	   btnTileVerticaly.setIcon(new ImageIcon("src/res/share_toolbar_icon.png"));
	   btnTileVerticaly.addActionListener(toolBarControler.getTileVerticalyActionListener());
	   add(btnTileVerticaly);
	   
	   setBackground(new Color(255,255,204));
   }
}

