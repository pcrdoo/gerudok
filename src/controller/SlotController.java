/***********************************************************************
 * Module:  SoltController.java
 * Author:  Ognjen
 * Purpose: Defines the Class SoltController
 ***********************************************************************/

package controller;

import model.Model;
import view.SlotView;
import java.util.*;

/** @pdOid e203d912-f17d-486a-9db6-e04ec5c8cf01 */
public class SlotController {
   /** @pdRoleInfo migr=no name=Model assc=association19 mult=1..1 */
   public Model model;
   /** @pdRoleInfo migr=no name=SoltView assc=association9 mult=1..1 side=A */
   public SlotView soltView;

}