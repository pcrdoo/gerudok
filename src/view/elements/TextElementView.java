package view.elements;

import model.ElementContainer;
import model.Model;
import model.elements.TextElement;
import view.ElementView;

import javax.swing.JTextPane;
import javax.swing.UIManager;
import java.awt.Dimension;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.Color;

public class TextElementView extends ElementView {
	private TextElement textElement;
	private JTextPane display;
	
	public TextElementView(Model model, TextElement textElement) {
		super(model, textElement);
		
		this.textElement = textElement;
		display = new JTextPane();
		//display.setMinimumSize(new Dimension(500, 30));
		//display.setWrapStyleWord(true);
		//display.setLineWrap(true);
		display.setContentType("text/html");
		display.setOpaque(false);
		display.setEditable(false);
		display.setFocusable(false);
		display.setBackground(UIManager.getColor("Label.background"));
		display.setFont(UIManager.getFont("Label.font"));
		display.setBorder(new EmptyBorder(5, 5, 5, 5));
		display.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.3f));
		display.setOpaque(false);
	    
		add(display);
		for (MouseListener l: deferredListeners) {
			display.addMouseListener(l);
		}
		setHtml(textElement.getHtml());
	}
	
	public void onEditNotification(Object obj) {
		setHtml(textElement.getHtml());
	}
	
	private void setHtml(String html) {
		display.setText(html.replace("\n", "<br>"));
	}
	
	public TextElement getTextElement() {
		return (TextElement) getElementContainer();
	}

}
