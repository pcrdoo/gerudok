/***********************************************************************
 * Module:  Page.java
 * Author:  Ognjen
 * Purpose: Defines the Class Page
 ***********************************************************************/

package model;

import java.util.*;

/** @pdOid a8ab91cf-d20a-4024-a842-190ce1e68bfa */
public class Page {
   /** @pdRoleInfo migr=no name=Slot assc=association5 coll=java.util.Collection impl=java.util.HashSet mult=0..* type=Composition */
   public java.util.Collection<Slot> slots;
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<Slot> getSlots() {
      if (slots == null)
         slots = new java.util.HashSet<Slot>();
      return slots;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorSlots() {
      if (slots == null)
         slots = new java.util.HashSet<Slot>();
      return slots.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newSlots */
   public void setSlots(java.util.Collection<Slot> newSlots) {
      removeAllSlots();
      for (java.util.Iterator iter = newSlots.iterator(); iter.hasNext();)
         addSlots((Slot)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newSlot */
   public void addSlots(Slot newSlot) {
      if (newSlot == null)
         return;
      if (this.slots == null)
         this.slots = new java.util.HashSet<Slot>();
      if (!this.slots.contains(newSlot))
         this.slots.add(newSlot);
   }
   
   /** @pdGenerated default remove
     * @param oldSlot */
   public void removeSlots(Slot oldSlot) {
      if (oldSlot == null)
         return;
      if (this.slots != null)
         if (this.slots.contains(oldSlot))
            this.slots.remove(oldSlot);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllSlots() {
      if (slots != null)
         slots.clear();
   }

}