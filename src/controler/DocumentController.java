/***********************************************************************
 * Module:  DocumentController.java
 * Author:  Ognjen
 * Purpose: Defines the Class DocumentController
 ***********************************************************************/

package controler;

import model.Model;
import view.DocumentView;
import java.util.*;

/** @pdOid 930f8441-ac77-40dd-8c94-bcc27b8317ff */
public class DocumentController {
   /** @pdRoleInfo migr=no name=Model assc=association17 mult=1..1 */
   public Model model;
   /** @pdRoleInfo migr=no name=DocumentView assc=association7 mult=1..1 side=A */
   public DocumentView documentView;

}