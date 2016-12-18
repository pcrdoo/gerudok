/***********************************************************************
 * Module:  Workspace.java
 * Author:  Ognjen
 * Purpose: Defines the Class Workspace
 ***********************************************************************/

package model;

import java.util.*;

/** @pdOid 9ad5960e-3097-4f1b-971a-2008a1df723c */
public class Workspace {
   /** @pdRoleInfo migr=no name=Project assc=association2 coll=java.util.Collection impl=java.util.HashSet mult=0..* type=Aggregation */
   public java.util.Collection<Project> projects;
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<Project> getProjects() {
      if (projects == null)
         projects = new java.util.HashSet<Project>();
      return projects;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorProjects() {
      if (projects == null)
         projects = new java.util.HashSet<Project>();
      return projects.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newProjects */
   public void setProjects(java.util.Collection<Project> newProjects) {
      removeAllProjects();
      for (java.util.Iterator iter = newProjects.iterator(); iter.hasNext();)
         addProjects((Project)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newProject */
   public void addProjects(Project newProject) {
      if (newProject == null)
         return;
      if (this.projects == null)
         this.projects = new java.util.HashSet<Project>();
      if (!this.projects.contains(newProject))
         this.projects.add(newProject);
   }
   
   /** @pdGenerated default remove
     * @param oldProject */
   public void removeProjects(Project oldProject) {
      if (oldProject == null)
         return;
      if (this.projects != null)
         if (this.projects.contains(oldProject))
            this.projects.remove(oldProject);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllProjects() {
      if (projects != null)
         projects.clear();
   }

}