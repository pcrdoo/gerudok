/***********************************************************************
 * Module:  Document.java
 * Author:  Ognjen
 * Purpose: Defines the Class Document
 ***********************************************************************/

package model;

import java.util.*;

/** @pdOid 2daa0c29-7b61-4d73-9235-343a3f9ce163 */
public class Document {
   /** @pdRoleInfo migr=no name=Page assc=association4 coll=java.util.Collection impl=java.util.HashSet mult=0..* type=Aggregation */
   public java.util.Collection<Page> pages;
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<Page> getPages() {
      if (pages == null)
         pages = new java.util.HashSet<Page>();
      return pages;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorPages() {
      if (pages == null)
         pages = new java.util.HashSet<Page>();
      return pages.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newPages */
   public void setPages(java.util.Collection<Page> newPages) {
      removeAllPages();
      for (java.util.Iterator iter = newPages.iterator(); iter.hasNext();)
         addPages((Page)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newPage */
   public void addPages(Page newPage) {
      if (newPage == null)
         return;
      if (this.pages == null)
         this.pages = new java.util.HashSet<Page>();
      if (!this.pages.contains(newPage))
         this.pages.add(newPage);
   }
   
   /** @pdGenerated default remove
     * @param oldPage */
   public void removePages(Page oldPage) {
      if (oldPage == null)
         return;
      if (this.pages != null)
         if (this.pages.contains(oldPage))
            this.pages.remove(oldPage);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllPages() {
      if (pages != null)
         pages.clear();
   }

}