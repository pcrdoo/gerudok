package view.elements;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Window;
import java.awt.GraphicsConfiguration;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import controller.elements.GraphicElementEditDialogController;
import model.Model;
import model.elements.GraphicElement;
import model.elements.GraphicShape;
import state.GraphicElementStateManager;
import view.MainView;

import java.util.*;

public class GraphicElementEditDialog extends JDialog {
	GraphicElementEditDialogController controller;
	List<GraphicShape> shapes;
	Model model;
	GraphicElement element;
	GraphicCanvasView canvas;
	JButton btnOk;
	JButton btnCancel;
	
	public GraphicElementEditDialog(Model model, GraphicElement element) {
		super(MainView.getInstance(), "Editing " + element.getName(), ModalityType.APPLICATION_MODAL, MainView.getInstance().getGraphicsConfiguration());
		this.model = model;
		this.element = element;
		
		this.setLayout(new BorderLayout());
		
	    canvas = new GraphicCanvasView(element);

		GraphicElementStateManager stateManager = new GraphicElementStateManager();
		canvas.addMouseListener(stateManager);
		canvas.addMouseMotionListener(stateManager);
		add(new GraphicElementToolBarView(model, element, stateManager, canvas), BorderLayout.NORTH);
	    add(canvas, BorderLayout.CENTER);
		
	    JPanel buttonPanel = new JPanel();
	    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
	    btnOk = new JButton("Ok");
	    btnCancel = new JButton("Cancel");
	    buttonPanel.add(btnOk);
	    buttonPanel.add(btnCancel);
	    
	    this.add(buttonPanel, BorderLayout.SOUTH);
	    
		setPreferredSize(new Dimension(460, 220));
		pack();
		setLocationRelativeTo(null);
		
		setTitle("Editing " + element.getName());
		this.controller = new GraphicElementEditDialogController(model, this);
	}
	
	public List<GraphicShape> getShapes() {
		return shapes;
	}
	
	public GraphicElement getGraphicElement() {
		return element;
	}
	
	public void setGraphicElement(GraphicElement element) {
		this.element = element;
	}
	
	public GraphicCanvasView getGraphicCanvasView() {
		return canvas;
	}
	
	public void addOkListener(ActionListener l) {
		btnOk.addActionListener(l);
	}

	public void addCancelListener(ActionListener l) {
		btnCancel.addActionListener(l);
	}
	
	public void addMouseListener(MouseListener l) {
		canvas.addMouseListener(l);
	}
}
