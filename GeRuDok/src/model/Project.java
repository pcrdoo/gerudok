/***********************************************************************
 * Module:  Project.java
 * Author:  Ognjen
 * Purpose: Defines the Class Project
 ***********************************************************************/

package model;

import java.util.*;

/** @pdOid 715d801c-8af9-4037-9476-707272b9ea66 */
public class Project {
   /** @pdRoleInfo migr=no name=Document assc=association3 coll=java.util.Collection impl=java.util.HashSet mult=0..* type=Aggregation */
   public java.util.Collection<Document> documents;
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<Document> getDocuments() {
      if (documents == null)
         documents = new java.util.HashSet<Document>();
      return documents;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorDocuments() {
      if (documents == null)
         documents = new java.util.HashSet<Document>();
      return documents.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newDocuments */
   public void setDocuments(java.util.Collection<Document> newDocuments) {
      removeAllDocuments();
      for (java.util.Iterator iter = newDocuments.iterator(); iter.hasNext();)
         addDocuments((Document)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newDocument */
   public void addDocuments(Document newDocument) {
      if (newDocument == null)
         return;
      if (this.documents == null)
         this.documents = new java.util.HashSet<Document>();
      if (!this.documents.contains(newDocument))
         this.documents.add(newDocument);
   }
   
   /** @pdGenerated default remove
     * @param oldDocument */
   public void removeDocuments(Document oldDocument) {
      if (oldDocument == null)
         return;
      if (this.documents != null)
         if (this.documents.contains(oldDocument))
            this.documents.remove(oldDocument);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllDocuments() {
      if (documents != null)
         documents.clear();
   }

}