package view;

import model.Model;
import model.Element;
import model.ElementType;
import model.TextElement;
import model.GraphicElement;
import javax.swing.JDialog;


public class ElementViewFactory {
	public ElementView create(Model model, Element element) throws Exception {
		ElementType type = element.getType();
		switch (type) {
		case GRAPHIC: return new GraphicElementView(model, (GraphicElement) element);
		case TEXT:    return new TextElementView(model, (TextElement) element);
		case SOUND:   //return new SoundElement(name);
		default:      throw new Exception("Unsupported element type");
		}
	}
	
	public JDialog createEditDialog(Model model, Element element) throws Exception {
		ElementType type = element.getType();
		switch (type) {
		case GRAPHIC: return new GraphicElementEditDialog(model, (GraphicElement) element);
		case TEXT:    return new TextElementEditDialog(model, (TextElement) element);
		case SOUND:   //return new SoundElement(name);
		default:      throw new Exception("Unsupported element type");
		}		
	}
}
