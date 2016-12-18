/***********************************************************************
 * Module:  DesktopView.java
 * Author:  Ognjen
 * Purpose: Defines the Class DesktopView
 ***********************************************************************/

package view;

import controler.DesktopController;
import model.Model;
import java.util.*;

import javax.swing.JDesktopPane;

/** @pdOid 1f8d9318-ae76-41ca-a08e-b8dddac09e76 */
public class DesktopView extends JDesktopPane{
   /** @pdRoleInfo migr=no name=ProjectView assc=association9 coll=java.util.Collection impl=java.util.HashSet mult=0..* type=Composition */
   public java.util.Collection<ProjectView> projectView;
   /** @pdRoleInfo migr=no name=DesktopController assc=association5 mult=1..1 type=Composition */
   public DesktopController desktopController;
   /** @pdRoleInfo migr=no name=Model assc=association14 mult=1..1 */
   public Model model;
   
   public DesktopView(Model model) {
	   this.model = model;
   }
   /** @pdGenerated default getter */
   public java.util.Collection<ProjectView> getProjectView() {
      if (projectView == null)
         projectView = new java.util.HashSet<ProjectView>();
      return projectView;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorProjectView() {
      if (projectView == null)
         projectView = new java.util.HashSet<ProjectView>();
      return projectView.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newProjectView */
   public void setProjectView(java.util.Collection<ProjectView> newProjectView) {
      removeAllProjectView();
      for (java.util.Iterator iter = newProjectView.iterator(); iter.hasNext();)
         addProjectView((ProjectView)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newProjectView */
   public void addProjectView(ProjectView newProjectView) {
      if (newProjectView == null)
         return;
      if (this.projectView == null)
         this.projectView = new java.util.HashSet<ProjectView>();
      if (!this.projectView.contains(newProjectView))
         this.projectView.add(newProjectView);
   }
   
   /** @pdGenerated default remove
     * @param oldProjectView */
   public void removeProjectView(ProjectView oldProjectView) {
      if (oldProjectView == null)
         return;
      if (this.projectView != null)
         if (this.projectView.contains(oldProjectView))
            this.projectView.remove(oldProjectView);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllProjectView() {
      if (projectView != null)
         projectView.clear();
   }

}