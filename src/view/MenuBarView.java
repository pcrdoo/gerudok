/***********************************************************************
 * Module:  MenuBarView.java
 * Author:  Ognjen
 * Purpose: Defines the Class MenuBarView
 ***********************************************************************/

package view;

import model.Model;
import java.util.*;

import javax.swing.JMenuBar;

import controller.MenuBarController;

/** @pdOid 90d49140-4eb5-4916-b044-c9f452fdd3a2 */
public class MenuBarView extends JMenuBar{
   /** @pdRoleInfo migr=no name=MenuBarController assc=association2 mult=1..1 type=Composition */
   public MenuBarController menuBarController;
   /** @pdRoleInfo migr=no name=Model assc=association10 mult=1..1 */
   public Model model;
   
   public MenuBarView(Model model) {
	   this.model = model;
   }

}