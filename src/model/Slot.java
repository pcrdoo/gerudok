/***********************************************************************
 * Module:  Slot.java
 * Author:  Ognjen
 * Purpose: Defines the Class Slot
 ***********************************************************************/

package model;

import java.util.*;

/** @pdOid b2e2161e-269a-4e75-b842-89f0af4ac234 */
public class Slot {
   /** @pdRoleInfo migr=no name=Element assc=association6 coll=java.util.Collection impl=java.util.HashSet mult=0..* type=Composition */
   public java.util.Collection<Element> elements;
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<Element> getElements() {
      if (elements == null)
         elements = new java.util.HashSet<Element>();
      return elements;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorElements() {
      if (elements == null)
         elements = new java.util.HashSet<Element>();
      return elements.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newElements */
   public void setElements(java.util.Collection<Element> newElements) {
      removeAllElements();
      for (java.util.Iterator iter = newElements.iterator(); iter.hasNext();)
         addElements((Element)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newElement */
   public void addElements(Element newElement) {
      if (newElement == null)
         return;
      if (this.elements == null)
         this.elements = new java.util.HashSet<Element>();
      if (!this.elements.contains(newElement))
         this.elements.add(newElement);
   }
   
   /** @pdGenerated default remove
     * @param oldElement */
   public void removeElements(Element oldElement) {
      if (oldElement == null)
         return;
      if (this.elements != null)
         if (this.elements.contains(oldElement))
            this.elements.remove(oldElement);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllElements() {
      if (elements != null)
         elements.clear();
   }

}