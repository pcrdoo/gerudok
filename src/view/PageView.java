/***********************************************************************
 * Module:  PageView.java
 * Author:  Ognjen
 * Purpose: Defines the Class PageView
 ***********************************************************************/

package view;

import model.Page;
import java.util.*;

import controller.PageController;

/** @pdOid 5edaadb8-f0ba-42a2-8a9c-9fb37d8b725a */
public class PageView {
   /** @pdRoleInfo migr=no name=SoltView assc=association7 coll=java.util.Collection impl=java.util.HashSet mult=0..* type=Composition */
   public java.util.Collection<SlotView> soltView;
   /** @pdRoleInfo migr=no name=PageController assc=association8 mult=1..1 type=Composition */
   public PageController pageController;
   /** @pdRoleInfo migr=no name=Page assc=association17 mult=1..1 */
   public Page page;
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<SlotView> getSoltView() {
      if (soltView == null)
         soltView = new java.util.HashSet<SlotView>();
      return soltView;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorSoltView() {
      if (soltView == null)
         soltView = new java.util.HashSet<SlotView>();
      return soltView.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newSoltView */
   public void setSoltView(java.util.Collection<SlotView> newSoltView) {
      removeAllSoltView();
      for (java.util.Iterator iter = newSoltView.iterator(); iter.hasNext();)
         addSoltView((SlotView)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newSoltView */
   public void addSoltView(SlotView newSoltView) {
      if (newSoltView == null)
         return;
      if (this.soltView == null)
         this.soltView = new java.util.HashSet<SlotView>();
      if (!this.soltView.contains(newSoltView))
         this.soltView.add(newSoltView);
   }
   
   /** @pdGenerated default remove
     * @param oldSoltView */
   public void removeSoltView(SlotView oldSoltView) {
      if (oldSoltView == null)
         return;
      if (this.soltView != null)
         if (this.soltView.contains(oldSoltView))
            this.soltView.remove(oldSoltView);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllSoltView() {
      if (soltView != null)
         soltView.clear();
   }

}