
package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import constants.Constants;
import controller.MainController;
import model.Model;
import view.tree.TreeView;

/**
 * The main view of the application.
 * 
 * @author Nikola Jovanovic
 *
 */
@SuppressWarnings("serial")
public class MainView extends JFrame {

	/**
	 * Singleton instance.
	 */
	public static MainView instance = null;
	/**
	 * The main model.
	 */
	private Model model;
	/**
	 * The tool bar view.
	 */
	private ToolBarView toolBarView;
	/**
	 * The menu bar view.
	 */
	private MenuBarView menuBarView;
	/**
	 * The view that shows the main desktop area.
	 */
	private DesktopView desktopView;
	/**
	 * The view that shows the tree.
	 */
	private TreeView treeView;
	/**
	 * The corresponding controller.
	 */
	private MainController mainController;

	/**
	 * Inaccessible constructor that prevents instantiation.
	 */
	private MainView() {
	}

	/**
	 * @return returns MainView instance
	 */
	public static MainView getInstance() {
		if (instance == null) {
			instance = new MainView();
			instance.initialize();
		}
		return instance;
	}

	/**
	 * Initializes the main view.
	 */
	private void initialize() {

		// Retrieves the main model.
		this.model = new Model();

		// Sets global layout properties.
		setLookAndFeel();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("GeRuDok");
		this.setSize(Constants.WINDOW_SIZE);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());

		// Adds the menu bar.
		this.menuBarView = new MenuBarView(this.model);
		this.setJMenuBar(menuBarView);

		// Adds the tool bar.
		this.toolBarView = new ToolBarView(this.model);
		this.add(this.toolBarView, BorderLayout.PAGE_START);

		// Adds the tree view.
		this.treeView = new TreeView(this.model);
		this.add(this.treeView, BorderLayout.LINE_START);

		// Adds the desktop view.
		this.desktopView = new DesktopView(this.model);
		this.add(this.desktopView, BorderLayout.CENTER);

		// Attaches the listeners.
		this.setMainController(new MainController(this.model, this));

	}

	/**
	 * Sets application look and feel.
	 */
	private void setLookAndFeel() {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					SwingUtilities.updateComponentTreeUI(this);
					pack();
					break;
				}
			}
		} catch (Exception e) {
			// If Nimbus is not available, you can set the GUI to
			// another look and feel.
		}
	}

	/**
	 * @return the tree view
	 */
	public TreeView getTreeView() {
		return treeView;
	}

	/**
	 * @return the desktop view
	 */
	public DesktopView getDesktopView() {
		return desktopView;
	}

	/**
	 * Retrieves the corresponding controller.
	 * 
	 * @return the corresponding controller
	 */
	public MainController getMainController() {
		return mainController;
	}

	/**
	 * Attaches the controller.
	 * 
	 * @param mainController
	 *            the controller to attach
	 */
	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}

}