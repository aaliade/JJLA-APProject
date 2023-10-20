package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginView {

	private JFrame frame;
	private JLabel userID, userPassword, title;
	private JTextField userIdField, userPasswordField;
	private JButton signUpBtn, loginBtn;
	private JPanel panel1, panel2, panel3, panel4;
	
	
	public void LoginView() {
		
	}
	
	public void initializeComponents() {
		frame = new JFrame("Grizzly’s Entertainment Equipment Rental");
		frame.setLayout(new GridLayout(4,1));
		
		userID = new JLabel("User ID: ");
		userPassword = new JLabel("Password: ");
		title = new JLabel("Grizzly’s Entertainment Equipment Rental");
		
		userIdField = new JTextField();
		userPasswordField = new JTextField();
		
		
		signUpBtn = new JButton("Sign Up");
		loginBtn = new JButton("Login");
		
		panel1 = new JPanel(new GridLayout(1,1));
		panel2 = new JPanel(new GridLayout(1,2));
		panel3 = new JPanel(new GridLayout(1,2));
		panel4 = new JPanel(new GridLayout(1,2));
		
	}
	
	
	public void addComponentsToPanel() {
		panel1.add(title);
		
		panel2.add(userID);
		panel2.add(userIdField);
		
		panel3.add(userPassword);
		panel3.add(userPasswordField);
		
		panel4.add(signUpBtn);
		panel4.add(loginBtn);
	}
	
	public void addToPanelToFrame() {
		frame.add(panel1);
		frame.add(panel2);
		frame.add(panel3);
		frame.add(panel4);
	}
	
	public void setWindowProperties() {
		frame.setSize(400,400);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
	}
	
	private void registerListeners() {
		signUpBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
			
		});
		loginBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	}
}
