package view.tree;

import java.awt.Component;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;

import controller.tree.GTreeCellEditorController;
import model.Model;
import model.tree.GLink;
import model.tree.GNode;

/**
 * The class used to edit(rename) GNodes.
 * 
 * @author Ognjen Djuricic
 *
 */
public class GTreeCellEditor extends DefaultTreeCellEditor {
	/**
	 * The node to be edited.
	 */
	private GNode node;
	/**
	 * Text field for the new name.
	 */
	private JTextField tfName;

	/**
	 * Constructor that inits everything.
	 * 
	 * @param tree
	 *            The tree to witch the editor belongs to.
	 * @param cellRenderer
	 *            The cell renderer of the tree.
	 * @param model
	 *            The main model.
	 */
	public GTreeCellEditor(JTree tree, DefaultTreeCellRenderer cellRenderer, Model model) {
		super(tree, cellRenderer);
		this.tfName = new JTextField();
		new GTreeCellEditorController(this, model);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.swing.tree.DefaultTreeCellEditor#getTreeCellEditorComponent(javax.
	 * swing.JTree, java.lang.Object, boolean, boolean, boolean, int)
	 */
	@Override
	public Component getTreeCellEditorComponent(JTree arg0, Object value, boolean isSelected, boolean expanded,
			boolean leaf, int row) {
		super.getTreeCellEditorComponent(arg0, value, isSelected, expanded, leaf, row);

		this.node = (GNode) value;
		this.tfName.setText(value.toString());
		return this.tfName;
	}

	/**
	 * Gets the node.
	 * 
	 * @return The node to be edited.
	 */
	public GNode getNode() {
		return this.node;
	}

	/**
	 * Gets the new name text.
	 * 
	 * @return The name text.
	 */
	public String getNameText() {
		return this.tfName.getText();
	}

	/**
	 * Adds a listener to the corresponding menu item(option).
	 * 
	 * @param l
	 *            The listener to be added.
	 */
	public void setNameFocusListener(FocusListener l) {
		this.tfName.addFocusListener(l);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.tree.DefaultTreeCellEditor#isCellEditable(java.util.
	 * EventObject)
	 */
	@Override
	public boolean isCellEditable(EventObject e) {
		if (e == null)
			return true;

		if (((WorkspaceTree) e.getSource()).getLastSelectedPathComponent() instanceof GLink) {
			return false;
		}

		if (e instanceof MouseEvent)
			if (((MouseEvent) e).getClickCount() == 3) {
				return true;
			}

		return false;
	}
}
