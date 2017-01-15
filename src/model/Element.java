/***********************************************************************
 * Module:  Element.java
 * Author:  Ognjen
 * Purpose: Defines the Class Element
 ***********************************************************************/

package model;

import java.io.Serializable;
import java.util.*;

/** @pdOid 328723e1-05ca-41dc-b721-a3bcaff3e342 */
public abstract class Element extends ElementContainer implements Serializable {
	public Element() {
		super();
	}

	public Element(String name) {
		super(name);
	}
}