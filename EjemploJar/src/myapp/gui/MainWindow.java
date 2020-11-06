package myapp.gui;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import myapp.database.DBException;
import myapp.database.DBManager;
import myapp.users.User;

public class MainWindow extends JFrame implements WindowListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DBManager dbManager;
	private DefaultListModel<User> usersListModel = new DefaultListModel<User>(); 
	
	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(this);
		setIconImage(new ImageIcon("icons/user.jpg").getImage());
		
		try (BufferedReader reader = new BufferedReader(new FileReader("res/title.txt"))) {
			setTitle(reader.readLine());
		} catch (IOException e) {
			System.out.println("Error loading application title");
		}
		
		setSize(640, 480);
		
		JList<User> usersJList = new JList<User>(usersListModel);
		JScrollPane scrollPane = new JScrollPane(usersJList);
		add(scrollPane, BorderLayout.CENTER);
		
		JButton loadButton = new JButton("Load data");
		loadButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					List<User> users = dbManager.getAllUsers();

					for (User u : users) {
						usersListModel.addElement(u);
					}
					
				} catch (DBException e) {
					System.out.println("Could not obtain user list. " + e.getMessage());
				}
			}
		});
		
		JPanel southPanel = new JPanel();
		southPanel.add(loadButton);
		add(southPanel, BorderLayout.SOUTH);
		
		dbManager = new DBManager();
		try {
			dbManager.connect("myapp.db");
		} catch (DBException e) {
			System.out.println("Could not connect to database. " + e.getMessage());
		}
				
		setVisible(true);
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		try {
			dbManager.disconnect();
		} catch (DBException e) {
			System.out.println("Error disconnecting from database");
		}
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
