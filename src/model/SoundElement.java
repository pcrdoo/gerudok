/***********************************************************************
 * Module:  SoundElement.java
 * Author:  Ognjen
 * Purpose: Defines the Class SoundElement
 ***********************************************************************/

package model;

import java.io.Serializable;
import java.util.*;

/** @pdOid 62bddb0a-4443-46fc-b7ea-df3e874b35a4 */
public class SoundElement extends Element implements Serializable {
	public SoundElement() {
		super();
		this.type = ElementType.SOUND;
	}
	
	public SoundElement(String name) {
		super(name);
		this.type = ElementType.SOUND;
	}
}