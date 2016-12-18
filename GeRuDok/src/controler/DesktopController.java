/***********************************************************************
 * Module:  DesktopController.java
 * Author:  Ognjen
 * Purpose: Defines the Class DesktopController
 ***********************************************************************/

package controler;

import model.Model;
import view.DesktopView;
import java.util.*;

/** @pdOid 74a00726-f117-44b6-b1d3-d04b394308c2 */
public class DesktopController {
   /** @pdRoleInfo migr=no name=Model assc=association15 mult=1..1 */
   public Model model;
   /** @pdRoleInfo migr=no name=DesktopView assc=association5 mult=1..1 side=A */
   public DesktopView desktopView;

}