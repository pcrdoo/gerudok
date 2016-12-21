/***********************************************************************
 * Module:  MenuBarController.java
 * Author:  Ognjen
 * Purpose: Defines the Class MenuBarController
 ***********************************************************************/

package controller;

import model.Model;
import view.MenuBarView;
import java.util.*;

/** @pdOid 1189e529-58ea-4e27-92c3-a945264c24e8 */
public class MenuBarController {
   /** @pdRoleInfo migr=no name=Model assc=association11 mult=1..1 */
   public Model model;
   /** @pdRoleInfo migr=no name=MenuBarView assc=association2 mult=1..1 side=A */
   public MenuBarView menuBarView;

}