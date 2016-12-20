/***********************************************************************
 * Module:  SoltView.java
 * Author:  Ognjen
 * Purpose: Defines the Class SoltView
 ***********************************************************************/

package view;

import controler.SlotController;
import model.Slot;
import java.util.*;

/** @pdOid 9d5d961c-8484-4b1f-9204-c2daa677e3c3 */
public class SlotView {
   /** @pdRoleInfo migr=no name=ElementView assc=association8 coll=java.util.Collection impl=java.util.HashSet mult=0..* type=Composition */
   public java.util.Collection<ElementView> elementView;
   /** @pdRoleInfo migr=no name=SoltController assc=association9 mult=1..1 type=Composition */
   public SlotController soltController;
   /** @pdRoleInfo migr=no name=Slot assc=association18 mult=1..1 */
   public Slot slot;
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<ElementView> getElementView() {
      if (elementView == null)
         elementView = new java.util.HashSet<ElementView>();
      return elementView;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorElementView() {
      if (elementView == null)
         elementView = new java.util.HashSet<ElementView>();
      return elementView.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newElementView */
   public void setElementView(java.util.Collection<ElementView> newElementView) {
      removeAllElementView();
      for (java.util.Iterator iter = newElementView.iterator(); iter.hasNext();)
         addElementView((ElementView)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newElementView */
   public void addElementView(ElementView newElementView) {
      if (newElementView == null)
         return;
      if (this.elementView == null)
         this.elementView = new java.util.HashSet<ElementView>();
      if (!this.elementView.contains(newElementView))
         this.elementView.add(newElementView);
   }
   
   /** @pdGenerated default remove
     * @param oldElementView */
   public void removeElementView(ElementView oldElementView) {
      if (oldElementView == null)
         return;
      if (this.elementView != null)
         if (this.elementView.contains(oldElementView))
            this.elementView.remove(oldElementView);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllElementView() {
      if (elementView != null)
         elementView.clear();
   }

}