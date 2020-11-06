package myapp;

import javax.swing.SwingUtilities;

import myapp.gui.MainWindow;

public class MyApp {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new MainWindow();
			}
		});
	}

}
