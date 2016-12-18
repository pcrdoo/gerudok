/***********************************************************************
 * Module:  TreeController.java
 * Author:  Ognjen
 * Purpose: Defines the Class TreeController
 ***********************************************************************/

package controler;

import model.Model;
import view.TreeView;
import java.util.*;

public class TreeController {
	
   public Model model;
   public TreeView treeView;
   
   public TreeController(Model model, TreeView treeView) {
	   this.model = model;
	   this.treeView = treeView;
   }

}