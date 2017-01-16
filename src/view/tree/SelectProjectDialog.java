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

import controller.tree.SelectProjectDialogController;
import model.Model;
import model.Workspace;
import model.tree.GNode;
import view.MainView;

/**
 * The dialog that is used for selecting projects to witch to add a hyperlink of
 * a GeRuDocument.
 * 
 * @author Ognjen Djuricic
 *
 */
public class SelectProjectDialog extends JDialog {
	/**
	 * Version UID for serialization.
	 */
	final static long serialVersionUID = 1;

	/**
	 * Reference to the main model.
	 */
	Model model;
	/**
	 * The GeRuDocument to be shared with projects.
	 */
	GNode shared;
	/**
	 * Button for finalizing the process.
	 */
	JButton btnOK;
	/**
	 * List of all projects.
	 */
	JList<GNode> list;
	/**
	 * Instance of the controller for this view.
	 */
	SelectProjectDialogController controller;

	/**
	 * Constructor that creates everything.
	 * 
	 * @param shared The shared document.
	 * @param model The main model.
	 */
	public SelectProjectDialog(GNode shared, Model model) {
		super(MainView.getInstance(), "Project Selection", ModalityType.APPLICATION_MODAL, MainView.getInstance().getGraphicsConfiguration());		this.model = model;
		this.shared = shared;

		this.setLayout(new BorderLayout());

		JLabel lbl = new JLabel("Select the Project to share the Document with:");
		this.add(lbl, BorderLayout.NORTH);

		DefaultListModel<GNode> listModel = new DefaultListModel<>();
		list = new JList<GNode>(listModel);

		this.add(list, BorderLayout.CENTER);

		// only for projects
		for (GNode node : Workspace.getInstance().getChildren()) {
			if (node != shared.getParent())
				listModel.addElement(node);
		}

		btnOK = new JButton("OK");
		btnOK.setEnabled(false);

		this.add(btnOK, BorderLayout.SOUTH);

		setPreferredSize(new Dimension(500, 400));
		pack();
		setLocationRelativeTo(null);

		this.controller = new SelectProjectDialogController(model, this);
	}

	/**
	 * Enables the OK button if anything is selected.
	 */
	public void enableBtnOK() {
		this.btnOK.setEnabled(this.list.getSelectedValuesList().size() != 0);
	}

	/**
	 * Gets the selected Projects.
	 * 
	 * @return The selected Projects.
	 */
	public List<GNode> getSelected() {
		return this.list.getSelectedValuesList();
	}

	/**
	 * Gets the shared GeRuDocument.
	 * 
	 * @return The shared GeRuDocument.
	 */
	public GNode getShared() {
		return this.shared;
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
