package view;

import model.Model;
import model.elements.GraphicElement;
import model.elements.TextElement;
import model.Element;
import model.ElementType;
import view.elements.GraphicElementEditDialog;
import view.elements.GraphicElementView;
import view.elements.TextElementEditDialog;
import view.elements.TextElementView;

import javax.swing.JDialog;


public class ElementViewFactory {
	public ElementView create(Model model, Element element) throws Exception {
		ElementType type = element.getType();
		switch (type) {
		case GRAPHIC: return new GraphicElementView(model, (GraphicElement) element);
		case TEXT:    return new TextElementView(model, (TextElement) element);
		default:      throw new Exception("Unsupported element type");
		}
	}
	
	public JDialog createEditDialog(Model model, Element element) throws Exception {
		ElementType type = element.getType();
		switch (type) {
		case GRAPHIC: return new GraphicElementEditDialog(model, (GraphicElement) element);
		case TEXT:    return new TextElementEditDialog(model, (TextElement) element);
		default:      throw new Exception("Unsupported element type");
		}		
	}
}
