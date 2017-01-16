package view;

import model.Model;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;
import controller.ToolBarController;

/**
 * The tool bar of the application.
 * 
 * @author Igor Bakovic
 *
 */
public class ToolBarView extends JToolBar {
	/**
	 * Version UID for serialization.
	 */
	final static long serialVersionUID = 1;

	/**
	 * The corresponding controller.
	 */
	public ToolBarController toolBarControler;

	/**
	 * The main model.
	 */
	public Model model;

	/**
	 * Constructor.
	 * 
	 * @param model
	 *            Model
	 */
	public ToolBarView(Model model) {
		this.model = model;
		toolBarControler = new ToolBarController(model, this);

		// Create toolbar element and attach corresponding listener
		JButton btnAdd = new JButton();
		btnAdd.setToolTipText("Add");
		btnAdd.setIcon(new ImageIcon("src/res/new_toolbar_icon.png"));
		btnAdd.addActionListener(toolBarControler.getAddActionListener());
		add(btnAdd);

		// Create toolbar element and attach corresponding listener
		JButton btnDelete = new JButton();
		btnDelete.setToolTipText("Delete");
		btnDelete.setIcon(new ImageIcon("src/res/delete_toolbar_icon.png"));
		btnDelete.addActionListener(toolBarControler.getDeleteActionListener());
		add(btnDelete);

		// Create toolbar element and attach corresponding listener
		JButton btnRename = new JButton();
		btnRename.setToolTipText("Rename");
		btnRename.setIcon(new ImageIcon("src/res/rename_toolbar_icon.png"));
		btnRename.addActionListener(toolBarControler.getRenameActionListener());
		add(btnRename);

		addSeparator();

		// Create toolbar element and attach corresponding listener
		JButton btnNewWorkspace = new JButton();
		btnNewWorkspace.setToolTipText("New workspace");
		btnNewWorkspace.setIcon(new ImageIcon("src/res/new_workspace_toolbar_icon.png"));
		btnNewWorkspace.addActionListener(toolBarControler.getNewWorkspaceListener());
		add(btnNewWorkspace);

		// Create toolbar element and attach corresponding listener
		JButton btnSwitchWorkspace = new JButton();
		btnSwitchWorkspace.setToolTipText("Switch workspace");
		btnSwitchWorkspace.setIcon(new ImageIcon("src/res/switch_workspace_toolbar_icon.png"));
		btnSwitchWorkspace.addActionListener(toolBarControler.getSwitchWorkspaceListener());
		add(btnSwitchWorkspace);

		// Create toolbar element and attach corresponding listener
		JButton btnSaveWorkspace = new JButton();
		btnSaveWorkspace.setToolTipText("Save workspace");
		btnSaveWorkspace.setIcon(new ImageIcon("src/res/save_workspace_icon.png"));
		btnSaveWorkspace.addActionListener(toolBarControler.getSaveWorkspaceListener());
		add(btnSaveWorkspace);

		addSeparator();

		// Create toolbar element and attach corresponding listener
		JButton btnOpen = new JButton();
		btnOpen.setToolTipText("Import project");
		btnOpen.setIcon(new ImageIcon("src/res/open_toolbar_icon.png"));
		btnOpen.addActionListener(toolBarControler.getLoadActionListener());
		add(btnOpen);

		// Create toolbar element and attach corresponding listener
		JButton btnSave = new JButton();
		btnSave.setToolTipText("Save selected project");
		btnSave.setIcon(new ImageIcon("src/res/save_toolbar_icon.png"));
		btnSave.addActionListener(toolBarControler.getSaveActionListener());
		add(btnSave);

		// Create toolbar element and attach corresponding listener
		JButton btnSaveAs = new JButton();
		btnSaveAs.setToolTipText("Save selected project as");
		btnSaveAs.setIcon(new ImageIcon("src/res/save_as_toolbar_icon.png"));
		btnSaveAs.addActionListener(toolBarControler.getSaveAsActionListener());
		add(btnSaveAs);

		addSeparator();

		// Create toolbar element and attach corresponding listener
		JButton btnOpenProject = new JButton();
		btnOpenProject.setToolTipText("Open selected project");
		btnOpenProject.setIcon(new ImageIcon("src/res/project_icon.png"));
		btnOpenProject.addActionListener(toolBarControler.getOpenProjectActionListener());
		add(btnOpenProject);

		// Create toolbar element and attach corresponding listener
		JButton btnCloseProject = new JButton();
		btnCloseProject.setToolTipText("Close selected project");
		btnCloseProject.setIcon(new ImageIcon("src/res/project_closed_icon.png"));
		btnCloseProject.addActionListener(toolBarControler.getCloseProjectActionListener());
		add(btnCloseProject);

		addSeparator();

		JButton btnShare = new JButton();
		btnShare.setToolTipText("Share selected GeRuDocument");
		btnShare.setIcon(new ImageIcon("src/res/share_toolbar_icon.png"));
		btnShare.addActionListener(toolBarControler.getShareActionListener());
		add(btnShare);

		addSeparator();

		// Create toolbar element and attach corresponding listener
		JButton btnCascade = new JButton();
		btnCascade.setToolTipText("Cascade");
		btnCascade.setIcon(new ImageIcon("src/res/cascade_icon.png"));
		btnCascade.addActionListener(toolBarControler.getCascadeActionListener());
		add(btnCascade);

		// Create toolbar element and attach corresponding listener
		JButton btnTileHorizontally = new JButton();
		btnTileHorizontally.setToolTipText("Tile horizontally");
		btnTileHorizontally.setIcon(new ImageIcon("src/res/tile_horizontally_icon.png"));
		btnTileHorizontally.addActionListener(toolBarControler.getTileHorizontallyActionListener());
		add(btnTileHorizontally);

		// Create toolbar element and attach corresponding listener
		JButton btnTileVertically = new JButton();
		btnTileVertically.setToolTipText("Tile vertically");
		btnTileVertically.setIcon(new ImageIcon("src/res/tile_vertically_icon.png"));
		btnTileVertically.addActionListener(toolBarControler.getTileVerticallyActionListener());
		add(btnTileVertically);

		setBackground(new Color(255, 255, 204));
	}
}
