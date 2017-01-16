package view.tree;

import model.Model;
import model.Project;
import model.Workspace;
import model.tree.GNode;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultTreeSelectionModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import constants.Constants;
import controller.tree.TreeController;
import gerudok_observer.GNotification;
import gerudok_observer.GObserver;

/**
 * The graphic representation of the WorkspaceTree and freeNodesList.
 * 
 * @author Ognjen Djuricic
 *
 */
public class TreeView extends JPanel implements GObserver {

	/**
	 * Reference to the main model.
	 */
	private Model model;
	/**
	 * Instance of the controller for this view.
	 */
	private TreeController treeController;
	/**
	 * The graphic representation of all the GNodes.
	 */
	private WorkspaceTree tree;
	/**
	 * Model for the list of free nodes.
	 */
	private DefaultListModel<GNode> freeNodesListModel;

	/**
	 * Constructor that sets the model.
	 * 
	 * @param model
	 *            The main model.
	 */
	public TreeView(Model model) {
		this.model = model;
		this.model.addObserver(this);
		this.initialize();
	}

	/**
	 * Creates everything.
	 */
	private void initialize() {

		GNode root = Workspace.getInstance();
		this.setPreferredSize(Constants.TREE_SIZE);

		DefaultTreeModel treeModel = new DefaultTreeModel(Workspace.getInstance());
		treeModel.setRoot(root);

		this.tree = new WorkspaceTree(this.model);
		this.tree.setModel(treeModel);

		GTreeCellRendered gtcr = new GTreeCellRendered();
		this.tree.setCellRenderer(gtcr);
		this.tree.setCellEditor(new GTreeCellEditor(this.tree, gtcr, this.model));
		this.tree.setEditable(true);
		this.tree.setShowsRootHandles(true);

		TreeSelectionModel selectionModel = new DefaultTreeSelectionModel();
		selectionModel.setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		this.tree.setSelectionModel(selectionModel);

		this.setLayout(new BorderLayout());
		this.setMinimumSize(new Dimension(1000, 1000));
		
		int height = Constants.TREE_VIEW_HEIGHT;
		
		JScrollPane treeScrollPane  = new JScrollPane(tree);
		
		treeScrollPane.setSize(new Dimension(50, (int)Math.round(Constants.TREE_PANE_FACTOR * height)));
		treeScrollPane.setMaximumSize(new Dimension(50, (int)Math.round(Constants.TREE_PANE_FACTOR * height)));
		treeScrollPane.setMinimumSize(new Dimension(50, (int)Math.round(Constants.TREE_PANE_FACTOR * height)));
		treeScrollPane.setPreferredSize(new Dimension(50, (int)Math.round(Constants.TREE_PANE_FACTOR * height)));
		
		this.add(treeScrollPane, BorderLayout.NORTH);

		freeNodesListModel = new DefaultListModel<>();
		JList freeNodesList = new JList<GNode>(freeNodesListModel);

		JScrollPane freeNodesScrollPane = new JScrollPane(freeNodesList);

		freeNodesScrollPane.setSize(new Dimension(100,  (int)Math.round(Constants.FREE_DOC_FACTOR * height)));
		freeNodesScrollPane.setMaximumSize(new Dimension(100,  (int)Math.round(Constants.FREE_DOC_FACTOR * height)));
		freeNodesScrollPane.setMinimumSize(new Dimension(100, (int)Math.round(Constants.FREE_DOC_FACTOR * height)));
		freeNodesScrollPane.setPreferredSize(new Dimension(100,  (int)Math.round(Constants.FREE_DOC_FACTOR * height)));

		this.reloadFreeNodesList();

		this.add(new JLabel("Free GeRuDocuments:"), BorderLayout.CENTER);
		this.add(freeNodesScrollPane, BorderLayout.SOUTH);

		this.model.setTreeModel(treeModel);
		this.treeController = new TreeController(this.model, this);
	}

	/**
	 * Repopulates the list of free nodes.
	 */
	public void reloadFreeNodesList() {
		this.freeNodesListModel.removeAllElements();
		for (GNode node : this.model.getFreeNodes()) {
			this.freeNodesListModel.addElement(node);
		}
	}

	/**
	 * Gets the WorkspaceTree.
	 * 
	 * @return The WorkspaceTree.
	 */
	public WorkspaceTree getTree() {
		return this.tree;
	}

	/**
	 * Gets selected project if there is one.
	 * 
	 * @return The selected project.
	 */
	public Project getSelectedProject() {
		Object o = tree.getLastSelectedPathComponent();
		if (o instanceof Project) {
			return (Project) o;
		}
		return null;
	}
	
	/**
	 * Gets selected node.
	 * 
	 * @return The selected node.
	 */
	public GNode getSelectedNode() {
		Object o = tree.getLastSelectedPathComponent();
		if (o instanceof GNode) {
			return (GNode) o;
		}
		return null;
	}

	/**
	 * Adds a listener to the corresponding menu item(option).
	 * 
	 * @param l
	 *            The listener to be added.
	 */
	public void addTreeListener(MouseListener l) {
		this.tree.addMouseListener(l);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gerudok_observer.GObserver#update(gerudok_observer.GNotification,
	 * java.lang.Object)
	 */
	@Override
	public void update(GNotification notification, Object obj) {

		if (notification == GNotification.FREE_NODES_CHANGED) {
			this.reloadFreeNodesList();
		}
	}
}