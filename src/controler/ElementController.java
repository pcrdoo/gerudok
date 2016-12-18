/***********************************************************************
 * Module:  ElementController.java
 * Author:  Ognjen
 * Purpose: Defines the Class ElementController
 ***********************************************************************/

package controler;

import model.Model;
import view.ElementView;
import java.util.*;

/** @pdOid a5685a25-1c1b-492d-9034-be45e4f21053 */
public class ElementController {
   /** @pdRoleInfo migr=no name=Model assc=association20 mult=1..1 */
   public Model model;
   /** @pdRoleInfo migr=no name=ElementView assc=association10 mult=1..1 side=A */
   public ElementView elementView;

}