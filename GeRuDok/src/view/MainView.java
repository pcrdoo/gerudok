/***********************************************************************
 * Module:  MainView.java
 * Author:  Ognjen
 * Purpose: Defines the Class MainView
 ***********************************************************************/

package view;

import controler.MainController;
import model.Model;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainView extends JFrame implements Observer {
	
	private ToolBarView toolBarView;
	private MenuBarView menuBarView;
	private DesktopView desktopView;
	private TreeView treeView;
	private MainController mainController;
	private Model model;
	
	// Singleton pattern implementation.
	public static MainView instance = null;

	private MainView() {
	}

	public static MainView getInstance() {
		if (instance == null) {
			instance = new MainView();
			instance.initialize();
		}
		return instance;
	}
	
	private void initialize() {
		// Retrieves the model and subscribes to changes.
		this.model = new Model();
		this.model.addObserver(this);
		
		// Sets global layout properties.
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("GeRuDok");//TODO maybe smthing
		
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    this.setSize((int) screenSize.getWidth(), (int) screenSize.getHeight());
		this.setUndecorated(true);		
	    this.setLocationRelativeTo(null);

		this.setResizable(true);
		// Adds three main panels.
		this.setLayout(new BorderLayout());
		
		//TODO
		//ResourceBundle bundle = ResourceBundle.getBundle("strings.MessageResources", Locale.getDefault());
		
		// Add menu bar
		this.menuBarView = new MenuBarView(this.model);
		this.setJMenuBar(menuBarView);
		
		// Add tool bar
		this.toolBarView = new ToolBarView(this.model);
		this.add(this.toolBarView);
		
		// Add Tree view
		this.treeView = new TreeView(this.model);
		this.add(this.treeView);
		
		// Add Desktop view
		this.desktopView = new DesktopView(this.model);
		this.add(this.desktopView);
		
		this.mainController = new MainController(this.model, this);
	}
	
	@Override
	public void update(Observable o, Object arg) {
	// TODO Auto-generated method stub
	}
}