package model;

import java.io.Serializable;

import model.elements.GraphicElement;
import model.elements.TextElement;

public class ElementFactory implements Serializable{
	public Element create(ElementType type, String name) throws Exception {
		switch (type) {
		case GRAPHIC: return new GraphicElement(name);
		case TEXT:    return new TextElement(name);
		default:      throw new Exception("Unsupported element type");
		}
	}
	
	public String createName(ElementType type, int index) throws Exception {
		String prefix = "";
		switch (type) {
		case GRAPHIC: prefix = "Graphic"; break; 
		case TEXT:    prefix = "Text"; break;
		}		
		
		return prefix + "Element" + index;
	}
}
