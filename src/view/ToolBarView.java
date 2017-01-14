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
	   
	   JButton btnNew = new JButton();
	   btnNew.setToolTipText("New");
	   btnNew.setIcon(new ImageIcon("src/res/new_toolbar_icon.png"));
	   add(btnNew);
	   
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
	   
	   JButton btnUndo = new JButton();
	   btnUndo.setToolTipText("Undo");
	   btnUndo.setIcon(new ImageIcon("src/res/undo_toolbar_icon.png"));
	   add(btnUndo);

	   JButton btnRedo = new JButton();
	   btnRedo.setToolTipText("Redo");
	   btnRedo.setIcon(new ImageIcon("src/res/redo_toolbar_icon.png"));
	   add(btnRedo);
	   
	   addSeparator();
	   
	   JButton btnCut = new JButton();
	   btnCut.setToolTipText("Cut");
	   btnCut.setIcon(new ImageIcon("src/res/cut_toolbar_icon.png"));
	   add(btnCut);
	   
	   JButton btnCopy = new JButton();
	   btnCopy.setToolTipText("Copy");
	   btnCopy.setIcon(new ImageIcon("src/res/copy_toolbar_icon.png"));
	   add(btnCopy);
	   
	   JButton btnPaste = new JButton();
	   btnPaste.setToolTipText("Paste");
	   btnPaste.setIcon(new ImageIcon("src/res/paste_toolbar_icon.png"));
	   add(btnPaste);
	   
	   addSeparator();

	   JButton btnShare = new JButton();
	   btnShare.setToolTipText("Share");
	   btnShare.setIcon(new ImageIcon("src/res/share_toolbar_icon.png"));
	   add(btnShare);
	   
	   JButton btnDelete = new JButton();
	   btnDelete.setToolTipText("Delete");
	   btnDelete.setIcon(new ImageIcon("src/res/delete_toolbar_icon.png"));
	   add(btnDelete);
	   
	   setBackground(new Color(255,255,204));
   }
}

