/***********************************************************************
 * Module:  ElementView.java
 * Author:  Ognjen
 * Purpose: Defines the Class ElementView
 ***********************************************************************/

package view;

import controler.ElementController;
import model.Element;
import java.util.*;

/** @pdOid 7de39cf0-3021-4c7e-a79b-bd085f44937d */
public class ElementView {
   /** @pdRoleInfo migr=no name=ElementController assc=association10 mult=1..1 type=Composition */
   public ElementController elementController;
   /** @pdRoleInfo migr=no name=Element assc=association19 mult=1..1 */
   public Element element;

}