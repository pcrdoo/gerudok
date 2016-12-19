/***********************************************************************
 * Module:  ProjectView.java
 * Author:  Ognjen
 * Purpose: Defines the Class ProjectView
 ***********************************************************************/

package view;

import controler.ProjectController;
import model.Project;
import java.util.*;

/** @pdOid 8b47cb46-c2c0-4d7d-be99-85faf493aa42 */
public class ProjectView {
   /** @pdRoleInfo migr=no name=DocumentView assc=association5 coll=java.util.Collection impl=java.util.HashSet mult=0..* type=Composition */
   public java.util.Collection<DocumentView> documentView;
   /** @pdRoleInfo migr=no name=ProjectController assc=association6 mult=1..1 type=Composition */
   public ProjectController projectController;
   /** @pdRoleInfo migr=no name=Project assc=association15 mult=1..1 */
   public Project project;
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<DocumentView> getDocumentView() {
      if (documentView == null)
         documentView = new java.util.HashSet<DocumentView>();
      return documentView;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorDocumentView() {
      if (documentView == null)
         documentView = new java.util.HashSet<DocumentView>();
      return documentView.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newDocumentView */
   public void setDocumentView(java.util.Collection<DocumentView> newDocumentView) {
      removeAllDocumentView();
      for (java.util.Iterator iter = newDocumentView.iterator(); iter.hasNext();)
         addDocumentView((DocumentView)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newDocumentView */
   public void addDocumentView(DocumentView newDocumentView) {
      if (newDocumentView == null)
         return;
      if (this.documentView == null)
         this.documentView = new java.util.HashSet<DocumentView>();
      if (!this.documentView.contains(newDocumentView))
         this.documentView.add(newDocumentView);
   }
   
   /** @pdGenerated default remove
     * @param oldDocumentView */
   public void removeDocumentView(DocumentView oldDocumentView) {
      if (oldDocumentView == null)
         return;
      if (this.documentView != null)
         if (this.documentView.contains(oldDocumentView))
            this.documentView.remove(oldDocumentView);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllDocumentView() {
      if (documentView != null)
         documentView.clear();
   }

}