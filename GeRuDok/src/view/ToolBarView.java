/***********************************************************************
 * Module:  ToolBarView.java
 * Author:  Ognjen
 * Purpose: Defines the Class ToolBarView
 ***********************************************************************/

package view;

import controler.ToolBarControler;
import model.Model;
import java.util.*;

import javax.swing.JToolBar;

/** @pdOid 89b60961-0c68-4ad1-8eb4-5a8b8a8dec28 */
public class ToolBarView extends JToolBar {
   /** @pdRoleInfo migr=no name=ToolBarControler assc=association1 mult=1..1 type=Composition */
   public ToolBarControler toolBarControler;
   /** @pdRoleInfo migr=no name=Model assc=association11 mult=1..1 */
   public Model model;
   
   public ToolBarView(Model model) {
	   this.model = model;
   }

}