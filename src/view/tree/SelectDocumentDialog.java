package view.tree;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.event.ListSelectionListener;

import controller.tree.SelectDocumentDialogController;
import model.Model;
import model.tree.GNode;
import view.MainView;

/**
 * The dialog that is used for selecting GeRuDocuments for the free ones and
 * adding them to Projects.
 * 
 * @author Ognjen Djuricic
 *
 */
public class SelectDocumentDialog extends JDialog {
	/**
	 * Version UID for serialization.
	 */
	final static long serialVersionUID = 1;

	/**
	 * Reference to the main model.
	 */
	Model model;
	/**
	 * The project to witch to add the documents to.
	 */
	GNode parent;
	/**
	 * Button for finalizing the process.
	 */
	JButton btnOK;
	/**
	 * List of the free GeRuDocuments.
	 */
	JList<GNode> list;
	/**
	 * Instance of the controller for this view.
	 */
	SelectDocumentDialogController controller;

	/**
	 * Constructor that creates everything.
	 * 
	 * @param parent
	 *            The parent project.
	 * @param model
	 *            The main model.
	 */
	public SelectDocumentDialog(GNode parent, Model model) {
		super(MainView.getInstance(), "Free Document Selection", ModalityType.APPLICATION_MODAL,
				MainView.getInstance().getGraphicsConfiguration());
		this.model = model;
		this.parent = parent;

		this.setLayout(new BorderLayout());

		JLabel lbl = new JLabel("Select the free Document to add to the Project:");
		this.add(lbl, BorderLayout.NORTH);

		DefaultListModel<GNode> listModel = new DefaultListModel<>();
		list = new JList<GNode>(listModel);

		this.add(list, BorderLayout.CENTER);

		for (GNode node : this.model.getFreeNodes()) {
			listModel.addElement(node);
		}

		btnOK = new JButton("OK");
		btnOK.setEnabled(false);

		this.add(btnOK, BorderLayout.SOUTH);

		setPreferredSize(new Dimension(500, 400));
		pack();
		setLocationRelativeTo(null);

		this.controller = new SelectDocumentDialogController(model, this);
	}

	/**
	 * Enables the OK button if anything is selected.
	 */
	public void enableBtnOK() {
		this.btnOK.setEnabled(this.list.getSelectedValuesList().size() != 0);
	}

	/**
	 * Gets the selected GeRuDocuments.
	 * 
	 * @return The selected GeRuDocuments.
	 */
	public List<GNode> getSelected() {
		return this.list.getSelectedValuesList();
	}

	/**
	 * Gets the parent project.
	 * 
	 * @return The parent project node.
	 */
	public GNode getParentNode() {
		return this.parent;
	}

	/**
	 * Adds a listener to the corresponding menu item(option).
	 * 
	 * @param l
	 *            The listener to be added.
	 */
	public void addSelectionChangedListener(ListSelectionListener l) {
		this.list.addListSelectionListener(l);
	}

	/**
	 * Adds a listener to the corresponding menu item(option).
	 * 
	 * @param l
	 *            The listener to be added.
	 */
	public void addBtnOKListener(ActionListener l) {
		this.btnOK.addActionListener(l);
	}

	/**
	 * Adds a listener to the corresponding menu item(option).
	 * 
	 * @param l
	 *            The listener to be added.
	 */
	public void addDoubleClickListener(MouseListener l) {
		this.list.addMouseListener(l);
	}
}
