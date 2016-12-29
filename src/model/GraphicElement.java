/***********************************************************************
 * Module:  GraphicElement.java
 * Author:  Ognjen
 * Purpose: Defines the Class GraphicElement
 ***********************************************************************/

package model;

import java.util.*;

/** @pdOid b3cc2eff-d82f-456e-ab73-1921e0ba2467 */
public class GraphicElement extends Element {
	public GraphicElement() {
		super();
		this.type = ElementType.GRAPHIC;
	}
	
	public GraphicElement(String name) {
		super(name);
		this.type = ElementType.GRAPHIC;
	}
}