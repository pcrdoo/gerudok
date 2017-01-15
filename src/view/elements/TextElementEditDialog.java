package view.elements;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Dialog.ModalityType;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import controller.elements.TextElementEditDialogController;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import model.Workspace;
import model.elements.TextElement;
import view.MainView;
import model.Model;

/**
 * Edit dialog for text elements.
 * 
 * @author geomaster
 *
 */
public class TextElementEditDialog extends JDialog {
	/**
	 * Controller.
	 */
	TextElementEditDialogController controller;

	/**
	 * Model.
	 */
	Model model;

	/**
	 * Element being edited.
	 */
	TextElement element;

	/**
	 * OK button.
	 */
	JButton btnOk;

	/**
	 * Cancel button.
	 */

	JButton btnCancel;

	/**
	 * Text area.
	 */
	JTextArea textArea;

	/**
	 * Constructor.
	 * 
	 * @param model
	 *            Model
	 * @param element
	 *            Element to edit
	 */
	public TextElementEditDialog(Model model, TextElement element) {
		super(MainView.getInstance(), "Editing " + element.getName(), ModalityType.APPLICATION_MODAL,
				MainView.getInstance().getGraphicsConfiguration());
		this.model = model;
		this.element = element;

		this.setLayout(new BorderLayout());
		add(new JLabel("Enter the text for the text element. HTML is allowed."), BorderLayout.NORTH);

		textArea = new JTextArea(element.getHtml());
		JScrollPane pane = new JScrollPane();
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		pane.add(textArea);
		add(pane, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
		btnOk = new JButton("Ok");
		btnCancel = new JButton("Cancel");
		buttonPanel.add(btnOk);
		buttonPanel.add(btnCancel);

		this.add(buttonPanel, BorderLayout.SOUTH);

		setPreferredSize(new Dimension(500, 400));
		pack();
		setLocationRelativeTo(null);

		this.controller = new TextElementEditDialogController(model, this);
	}

	/**
	 * Gets the text element being edited.
	 * 
	 * @return Text element
	 */
	public TextElement getTextElement() {
		return element;
	}

	/**
	 * Adds a listener for the OK button.
	 * 
	 * @param listener
	 *            Listener to add
	 */
	public void addOkListener(ActionListener listener) {
		btnOk.addActionListener(listener);
	}

	/**
	 * Adds a listener for the Cancel button.
	 * 
	 * @param listener
	 *            Listener to add
	 */
	public void addCancelListener(ActionListener listener) {
		btnCancel.addActionListener(listener);
	}

	/**
	 * Adds a key listener.
	 * 
	 * @param listener
	 *            Listener to add
	 */
	public void addKeyListener(KeyListener listener) {
		textArea.addKeyListener(listener);
	}

	/**
	 * Returns the HTML contents of the view.
	 * 
	 * @return HTML contents
	 */
	public String getHtml() {
		return textArea.getText();
	}
}
