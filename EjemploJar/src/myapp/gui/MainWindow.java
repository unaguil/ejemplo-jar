package myapp.gui;
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import myapp.users.User;

public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MainWindow() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(640, 480);
		
		setVisible(true);
		
		JList<User> users = new JList<User>();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.add(users);
		add(scrollPane, BorderLayout.CENTER);
		
		JPanel southPanel = new JPanel();
		JButton loadButton = new JButton("Load data");
		southPanel.add(loadButton);
		add(southPanel, BorderLayout.SOUTH);
	}

}
