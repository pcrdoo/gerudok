/***********************************************************************
 * Module:  DocumentView.java
 * Author:  Ognjen
 * Purpose: Defines the Class DocumentView
 ***********************************************************************/

package view;

import controler.DocumentController;
import model.Document;
import java.util.*;

/** @pdOid dbcfced6-0a07-4c15-b8bd-1cb841965818 */
public class DocumentView {
   /** @pdRoleInfo migr=no name=PageView assc=association6 coll=java.util.Collection impl=java.util.HashSet mult=0..* type=Composition */
   public java.util.Collection<PageView> pageView;
   /** @pdRoleInfo migr=no name=DocumentController assc=association7 mult=1..1 type=Composition */
   public DocumentController documentController;
   /** @pdRoleInfo migr=no name=Document assc=association16 mult=1..1 */
   public Document document;
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<PageView> getPageView() {
      if (pageView == null)
         pageView = new java.util.HashSet<PageView>();
      return pageView;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorPageView() {
      if (pageView == null)
         pageView = new java.util.HashSet<PageView>();
      return pageView.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newPageView */
   public void setPageView(java.util.Collection<PageView> newPageView) {
      removeAllPageView();
      for (java.util.Iterator iter = newPageView.iterator(); iter.hasNext();)
         addPageView((PageView)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newPageView */
   public void addPageView(PageView newPageView) {
      if (newPageView == null)
         return;
      if (this.pageView == null)
         this.pageView = new java.util.HashSet<PageView>();
      if (!this.pageView.contains(newPageView))
         this.pageView.add(newPageView);
   }
   
   /** @pdGenerated default remove
     * @param oldPageView */
   public void removePageView(PageView oldPageView) {
      if (oldPageView == null)
         return;
      if (this.pageView != null)
         if (this.pageView.contains(oldPageView))
            this.pageView.remove(oldPageView);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllPageView() {
      if (pageView != null)
         pageView.clear();
   }

}