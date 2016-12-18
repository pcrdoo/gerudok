/***********************************************************************
 * Module:  ToolBarControler.java
 * Author:  Ognjen
 * Purpose: Defines the Class ToolBarControler
 ***********************************************************************/

package controler;

import model.Model;
import view.ToolBarView;
import java.util.*;

/** @pdOid 88245b1f-d41c-42ae-be15-48a0717c1585 */
public class ToolBarControler {
   /** @pdRoleInfo migr=no name=Model assc=association12 mult=1..1 */
   public Model model;
   /** @pdRoleInfo migr=no name=ToolBarView assc=association1 mult=1..1 side=A */
   public ToolBarView toolBarView;

}