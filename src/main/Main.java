package main;

import view.MainView;

/**
 * Main class, application entry point.
 * 
 * @author Nikola Jovanovic
 *
 */
public class Main {

	/**
	 * @param args
	 *            command line arguments
	 */
	public static void main(String[] args) {
		MainView mainView = MainView.getInstance();
		mainView.setVisible(true);
	}
}
