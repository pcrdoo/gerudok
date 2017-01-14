/***********************************************************************
 * Module:  TreeView.java
 * Author:  Ognjen
 * Purpose: Defines the Class TreeView
 ***********************************************************************/

package view.tree;

import model.Model;
import model.Project;
import model.Workspace;
import model.tree.GNode;
import model.tree.GTreeModel;

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
import javax.swing.tree.DefaultTreeSelectionModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import constants.Constants;
import controller.tree.TreeController;
import gerudok_observer.GNotification;
import gerudok_observer.GObserver;

public class TreeView extends JPanel implements GObserver{

	private TreeController treeController;
	private Model model;
	private WorkspaceTree tree;
	private GTreeModel treeModel;
	private DefaultListModel<GNode> freeNodesListModel;

	public TreeView(Model model) {
		this.model = model;
		this.model.addObserver(this);
		this.initialize();
	}

	private void initialize() {

		// TODO mock ovo srediti
		GNode root = Workspace.getInstance();

		// MOCK
		// for(int i =0;i<5;i++) {
		// GNode ngn = new GNode("mock_child_" + i);
		// root.add(ngn);
		// }
		//

		this.setPreferredSize(Constants.TREE_SIZE);

		this.treeModel = new GTreeModel();
		this.treeModel.setRoot(root);

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
		this.add(new JScrollPane(tree), BorderLayout.NORTH);

		freeNodesListModel = new DefaultListModel<>();
		JList freeNodesList = new JList<GNode>(freeNodesListModel);
		
		JScrollPane freeNodesScrollPane = new JScrollPane(freeNodesList);
		
		freeNodesScrollPane.setSize(new Dimension(100, 240));
		freeNodesScrollPane.setMaximumSize(new Dimension(100, 240));
		freeNodesScrollPane.setMinimumSize(new Dimension(100, 240));
		freeNodesScrollPane.setPreferredSize(new Dimension(100, 240));
		
		this.reloadFreeNodesList();
		
		this.add(new JLabel("Free GeRuDocuments:"), BorderLayout.CENTER);
		this.add(freeNodesScrollPane, BorderLayout.SOUTH);

		this.model.setTreeModel(this.treeModel);
		this.treeController = new TreeController(this.model, this);
	}

	public void reloadFreeNodesList() {
		this.freeNodesListModel.removeAllElements();
		for (GNode node : this.model.getFreeNodes()) {
			this.freeNodesListModel.addElement(node);
		}
	}

	public void addTreeListener(MouseListener l) {
		this.tree.addMouseListener(l);
	}

	public WorkspaceTree getTree() {
		return this.tree;
	}

	public Project getSelectedProject() {
		Object o = tree.getLastSelectedPathComponent();
		if (o instanceof Project) {
			return (Project) o;
		}
		return null;
	}

	@Override
	public void update(GNotification notification, Object obj) {
		
		if(notification == GNotification.FREE_NODES_CHANGED) {
			this.reloadFreeNodesList();
		}
	}
}