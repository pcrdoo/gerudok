/***********************************************************************
 * Module:  ProjectController.java
 * Author:  Ognjen
 * Purpose: Defines the Class ProjectController
 ***********************************************************************/

package controler;

import model.Model;
import view.ProjectView;
import java.util.*;

/** @pdOid bf767906-23cd-405d-a24d-dd73d7851411 */
public class ProjectController {
   /** @pdRoleInfo migr=no name=Model assc=association16 mult=1..1 */
   public Model model;
   /** @pdRoleInfo migr=no name=ProjectView assc=association6 mult=1..1 side=A */
   public ProjectView projectView;

}