/***********************************************************************
 * Module:  PageController.java
 * Author:  Ognjen
 * Purpose: Defines the Class PageController
 ***********************************************************************/

package controller;

import model.Model;
import view.PageView;
import java.util.*;

/** @pdOid 9618542f-eb74-4b88-923f-bb882f359e62 */
public class PageController {
   /** @pdRoleInfo migr=no name=Model assc=association18 mult=1..1 */
   public Model model;
   /** @pdRoleInfo migr=no name=PageView assc=association8 mult=1..1 side=A */
   public PageView pageView;

}