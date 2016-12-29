package view;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;

import controller.TextElementEditDialogController;
import model.Workspace;
import model.TextElement;
import model.Model;

public class TextElementEditDialog extends JDialog {
	TextElementEditDialogController controller;
	Model model;
	TextElement element;
	JButton btnOk;
	JButton btnCancel;
	JTextArea textArea;
	
	public TextElementEditDialog(Model model, TextElement element) {
		this.model = model;
		this.element = element;
		
		this.setLayout(new BorderLayout());
		add(new JLabel("Enter the text for the text element. HTML is allowed."), BorderLayout.NORTH);
		
	    textArea = new JTextArea(element.getHtml());
	    add(textArea, BorderLayout.CENTER);
		
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
	
	public TextElement getTextElement() {
		return element;
	}
	
	public void addOkListener(ActionListener l) {
		btnOk.addActionListener(l);
	}

	public void addCancelListener(ActionListener l) {
		btnCancel.addActionListener(l);
	}
	
	public String getHtml() {
		return textArea.getText();
	}
}
