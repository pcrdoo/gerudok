/***********************************************************************
 * Module:  ToolBarView.java
 * Author:  Ognjen
 * Purpose: Defines the Class ToolBarView
 ***********************************************************************/

package view;

import controler.ToolBarControler;
import model.Model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.*;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;

/** @pdOid 89b60961-0c68-4ad1-8eb4-5a8b8a8dec28 */
public class ToolBarView extends JPanel {
   /** @pdRoleInfo migr=no name=ToolBarControler assc=association1 mult=1..1 type=Composition */
   public ToolBarControler toolBarControler;
   /** @pdRoleInfo migr=no name=Model assc=association11 mult=1..1 */
   public Model model;
   
   public ToolBarView(Model model) {
	   this.model = model;
	   this.setBackground(Color.RED);
	   this.add(new JLabel("TOOLBAR"));
   }

}