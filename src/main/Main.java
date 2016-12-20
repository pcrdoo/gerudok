package main;

import javax.swing.UIManager;

import view.MainView;

public class Main {

	public static void main(String[] args) {
		MainView mainView = MainView.getInstance();
		mainView.setVisible(true);
		UIManager.LookAndFeelInfo[] lafInfo = UIManager.getInstalledLookAndFeels();
		for(UIManager.LookAndFeelInfo kita : lafInfo) {
			System.out.println(kita);
		}
	}

}
