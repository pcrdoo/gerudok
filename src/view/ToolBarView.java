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
/**
 * The tool bar of the application.
 * 
 * @author Igor Bakovic
 *
 */
public class ToolBarView extends JToolBar {

	/**
	 * The corresponding controller.
	 */
   public ToolBarControler toolBarControler;
   
   /**
	 * The main model.
	 */
   public Model model;
   
   public ToolBarView(Model model) {
	   this.model = model;
	   toolBarControler = new ToolBarControler(model, this);
	   
	   //Create toolbar element and attach corresponding listener
	   JButton btnAdd = new JButton();
	   btnAdd.setToolTipText("Add");
	   btnAdd.setIcon(new ImageIcon("src/res/new_toolbar_icon.png"));
	   btnAdd.addActionListener(toolBarControler.getAddActionListener());
	   add(btnAdd);
	   
	   //Create toolbar element and attach corresponding listener
	   JButton btnDelete = new JButton();
	   btnDelete.setToolTipText("Delete");
	   btnDelete.setIcon(new ImageIcon("src/res/delete_toolbar_icon.png"));
	   btnDelete.addActionListener(toolBarControler.getDeleteActionListener());
	   add(btnDelete);
		  
	   //Create toolbar element and attach corresponding listener
	   JButton btnRename = new JButton();
	   btnRename.setToolTipText("Rename");
	   btnRename.setIcon(new ImageIcon("src/res/rename_toolbar_icon.png"));
	   btnRename.addActionListener(toolBarControler.getRenameActionListener());
	   add(btnRename);
	   
	   addSeparator();
	   
	   //Create toolbar element and attach corresponding listener
	   JButton btnNewWorkspace = new JButton();
	   btnNewWorkspace.setToolTipText("New workspace");
	   btnNewWorkspace.setIcon(new ImageIcon("src/res/rename_toolbar_icon.png"));
	   btnNewWorkspace.addActionListener(toolBarControler.getNewWorkspaceListener());
	   add(btnNewWorkspace);
	   
	   //Create toolbar element and attach corresponding listener
	   JButton btnSwitchWorkspace = new JButton();
	   btnSwitchWorkspace.setToolTipText("Switch workspace");
	   btnSwitchWorkspace.setIcon(new ImageIcon("src/res/switch_workspace_toolbar_icon.png"));
	   btnSwitchWorkspace.addActionListener(toolBarControler.getSwitchWorkspaceListener());
	   add(btnSwitchWorkspace);
	   
	   //Create toolbar element and attach corresponding listener
	   JButton btnSaveWorkspace = new JButton();
	   btnSaveWorkspace.setToolTipText("Save workspace");
	   btnSaveWorkspace.setIcon(new ImageIcon("src/res/switch_workspace_toolbar_icon.png"));
	   btnSaveWorkspace.addActionListener(toolBarControler.getSaveWorkspaceListener());
	   add(btnSaveWorkspace);
	   
	   addSeparator();
	   
	   //Create toolbar element and attach corresponding listener
	   JButton btnOpen = new JButton();
	   btnOpen.setToolTipText("Import project");
	   btnOpen.setIcon(new ImageIcon("src/res/open_toolbar_icon.png"));
	   btnOpen.addActionListener(toolBarControler.getLoadActionListener());
	   add(btnOpen);
	   
	   //Create toolbar element and attach corresponding listener
	   JButton btnSave = new JButton();
	   btnSave.setToolTipText("Save selected project");
	   btnSave.setIcon(new ImageIcon("src/res/save_toolbar_icon.png"));
	   btnSave.addActionListener(toolBarControler.getSaveActionListener());
	   add(btnSave);
	   
	   //Create toolbar element and attach corresponding listener
	   JButton btnSaveAs = new JButton();
	   btnSaveAs.setToolTipText("Save selected project as");
	   btnSaveAs.setIcon(new ImageIcon("src/res/save_as_toolbar_icon.png"));
	   btnSaveAs.addActionListener(toolBarControler.getSaveAsActionListener());
	   add(btnSaveAs);
	   
	   addSeparator();
	  
	   //Create toolbar element and attach corresponding listener
	   JButton btnOpenProject = new JButton();
	   btnOpenProject.setToolTipText("Open selected project");
	   btnOpenProject.setIcon(new ImageIcon("src/res/maximize_toolbar_icon.png"));
	   btnOpenProject.addActionListener(toolBarControler.getOpenProjectActionListener());
	   add(btnOpenProject);
	   
	  //Create toolbar element and attach corresponding listener
	   JButton btnCloseProject = new JButton();
	   btnCloseProject.setToolTipText("Close selected project");
	   btnCloseProject.setIcon(new ImageIcon("src/res/minimize_toolbar_icon.png"));
	   btnCloseProject.addActionListener(toolBarControler.getCloseProjectActionListener());
	   add(btnCloseProject);

	   addSeparator();
	   
	   JButton btnShare = new JButton();
	   btnShare.setToolTipText("Share selected GeRuDocument");
	   btnShare.setIcon(new ImageIcon("src/res/share_toolbar_icon.png"));
	   btnShare.addActionListener(toolBarControler.getShareActionListener());
	   add(btnShare);
	    
	   addSeparator();
	  
	   //Create toolbar element and attach corresponding listener
	   JButton btnCascade = new JButton();
	   btnCascade.setToolTipText("Cascade");
	   btnCascade.setIcon(new ImageIcon("src/res/share_toolbar_icon.png"));
	   btnCascade.addActionListener(toolBarControler.getCascadeActionListener());
	   add(btnCascade);
		 
	   //Create toolbar element and attach corresponding listener
	   JButton btnTileHorizontaly = new JButton();
	   btnTileHorizontaly.setToolTipText("Tile horizontally");
	   btnTileHorizontaly.setIcon(new ImageIcon("src/res/share_toolbar_icon.png"));
	   btnTileHorizontaly.addActionListener(toolBarControler.getTileHorizontalyActionListener());
	   add(btnTileHorizontaly);
	   
	   //Create toolbar element and attach corresponding listener
	   JButton btnTileVerticaly = new JButton();
	   btnTileVerticaly.setToolTipText("Tile vertically");
	   btnTileVerticaly.setIcon(new ImageIcon("src/res/share_toolbar_icon.png"));
	   btnTileVerticaly.addActionListener(toolBarControler.getTileVerticalyActionListener());
	   add(btnTileVerticaly);
	   
	   setBackground(new Color(255,255,204));
   }
}

