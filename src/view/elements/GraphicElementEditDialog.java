package view.elements;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import controller.elements.GraphicElementEditDialogController;
import model.Model;
import model.elements.GraphicElement;
import state.GraphicElementStateManager;
import view.MainView;

/**
 * Dialog for editing a graphic element.
 * 
 * @author geomaster
 *
 */
public class GraphicElementEditDialog extends JDialog {
	/**
	 * Version UID for serialization.
	 */
	final static long serialVersionUID = 1;
	
	/**
	 * Controller.
	 */
	GraphicElementEditDialogController controller;

	/**
	 * Model.
	 */
	Model model;

	/**
	 * Graphic element being edited.
	 */
	GraphicElement element;

	/**
	 * Editor view.
	 */
	GraphicCanvasView canvas;

	/**
	 * OK button.
	 */
	JButton btnOk;

	/**
	 * Cancel button.
	 */
	JButton btnCancel;

	/**
	 * Constructor.
	 * 
	 * @param model
	 *            Model
	 * @param element
	 *            Element to edit
	 */
	public GraphicElementEditDialog(Model model, GraphicElement element) {
		super(MainView.getInstance(), "Editing " + element.getName(), ModalityType.APPLICATION_MODAL,
				MainView.getInstance().getGraphicsConfiguration());
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

	/**
	 * Gets the graphic element being edited.
	 * 
	 * @return Graphic element
	 */
	public GraphicElement getGraphicElement() {
		return element;
	}

	/**
	 * Sets the graphic element being edited.
	 * 
	 * @param element
	 *            New element to edit
	 */
	public void setGraphicElement(GraphicElement element) {
		this.element = element;
	}

	/**
	 * Gets the graphic canvas (editor) view.
	 * 
	 * @return Graphic canvas view
	 */
	public GraphicCanvasView getGraphicCanvasView() {
		return canvas;
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
	 * Adds a mouse listener.
	 * 
	 * @param listener
	 *            Listener to add
	 */
	@Override
	public void addMouseListener(MouseListener listener) {
		canvas.addMouseListener(listener);
	}
}
