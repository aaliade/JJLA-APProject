package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



import controller.GuiController;

public class LoginView {

	private JFrame frame;
	private JLabel userID, userPassword, title;
	private JTextField userIdField, userPasswordField;
	private JButton signUpBtn, loginBtn;
	private JPanel[] panels;
	private GuiController guiController; 
	
	public LoginView(GuiController gui) {
		this.guiController = gui;
		initializeComponents();
		addComponentsToPanel();
		addToPanelToFrame();
		setWindowProperties();
		registerListeners();
	}
	
	public void initializeComponents() {
		frame = new JFrame("Grizzly’s Entertainment Equipment Rental");
		frame.setLayout(new GridLayout(4,1));
		
		userID = new JLabel("User ID: ");
		userPassword = new JLabel("Password: ");
		title = new JLabel("Welcome, Login To Your Account.");
		
		userIdField = new JTextField();
		userPasswordField = new JTextField();
		
		
		signUpBtn = new JButton("Sign Up");
		loginBtn = new JButton("Login");
		
		panels = new JPanel[4];
		
		for(int i=0;i<panels.length;i++) {
			panels[i] = new JPanel();
		}
		
		panels[0].setLayout(new GridLayout(1,1));
		panels[1].setLayout(new GridLayout(1,2));
		panels[2].setLayout(new GridLayout(1,2));
		panels[3].setLayout(new GridLayout(1,2));
		
	}
	
	
	public void addComponentsToPanel() {
		panels[0].add(title);
		
		panels[1].add(userID);
		panels[1].add(userIdField);
		
		panels[2].add(userPassword);
		panels[2].add(userPasswordField);
		
		panels[3].add(signUpBtn);
		panels[3].add(loginBtn);
		

	}
	
	public void addToPanelToFrame() {
		for(int i=0;i<panels.length;i++) {
			frame.add(panels[i]);
		}
	}
	
	public void setWindowProperties() {
		frame.setSize(400,400);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
	}
	
	public void registerListeners() {
		signUpBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				guiController.signUpUser(frame); //passes frame object to function
			}
			
		});
		
		loginBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				guiController.loginUser(frame);
			}
		});
	}
}
