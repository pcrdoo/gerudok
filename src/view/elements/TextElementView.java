package view.elements;

import model.Model;
import model.elements.TextElement;
import view.ElementView;

import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseListener;
import java.awt.Color;

/**
 * Text element view.
 * 
 * @author geomaster
 *
 */
public class TextElementView extends ElementView {
	/**
	 * Version UID for serialization.
	 */
	final static long serialVersionUID = 1;
	
	/**
	 * Element being displayed.
	 */
	private TextElement textElement;

	/**
	 * JTextPane used to display the text.
	 */
	private JTextPane display;

	/**
	 * Constructor.
	 * 
	 * @param model
	 *            Model
	 * @param textElement
	 *            Element to display
	 */
	public TextElementView(Model model, TextElement textElement) {
		super(model, textElement);

		this.textElement = textElement;
		display = new JTextPane();
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
		for (MouseListener l : deferredListeners) {
			display.addMouseListener(l);
		}
		setHtml(textElement.getHtml());
	}

	/**
	 * Called when the displayed element changes.
	 * 
	 * @param obj
	 *            Custom event data
	 */
	@Override
	public void onEditNotification(Object obj) {
		setHtml(textElement.getHtml());
	}

	/**
	 * Set the HTML displayed
	 * 
	 * @param html
	 *            HTML contents
	 */
	private void setHtml(String html) {
		display.setText(html.replace("\n", "<br>"));
	}

	/**
	 * Gets the text element being displayed
	 * 
	 * @return Text element
	 */
	public TextElement getTextElement() {
		return (TextElement) getElementContainer();
	}

}
