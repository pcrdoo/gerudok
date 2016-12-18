/***********************************************************************
 * Module:  MainController.java
 * Author:  Ognjen
 * Purpose: Defines the Class MainController
 ***********************************************************************/

package controler;

import model.Model;
import view.MainView;
import java.util.*;

/** @pdOid 783f6e54-f635-41e8-8184-4dd834273740 */
public class MainController {
   /** @pdRoleInfo migr=no name=Model assc=association13 mult=1..1 */
   public Model model;
   /** @pdRoleInfo migr=no name=MainView assc=association3 mult=1..1 side=A */
   public MainView mainView;
   
   public MainController(Model model, MainView mainView) {
	   this.model = model;
	   this.mainView = mainView;
   }

}