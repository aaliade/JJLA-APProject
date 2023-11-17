package views;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import controller.CustomerClientController;

public class Login {
	private JFrame frame;
	private JLabel usernametext, userPassword, title, errorText;
	private JTextField usernameField, userPasswordField;
	private JButton signUpBtn, loginBtn;
	private JPanel[] panels;

	private CustomerClientController controller; 

	private String password, username;

	private static final Logger logger = LogManager.getLogger(Login.class);


	public Login(CustomerClientController controller) {
		this.controller = controller;
		initializeComponents();
		addComponentsToPanel();
		addToPanelToFrame();
		setWindowProperties();
		registerListeners();

		logger.info("Login Page created");
	}

	public void initializeComponents() {
		frame = new JFrame("Grizzly’s Entertainment Equipment Rental");
		frame.setLayout(new GridLayout(5,1));

		usernametext = new JLabel("Username: ");
		userPassword = new JLabel("Password: ");
		title = new JLabel("Welcome, Login To Your Account.");
		errorText = new JLabel("Error: wrong password/User ID. \r\n"
				+ "Please Try Again");

		//set error text to be invisible
		errorText.setVisible(false);

		usernameField = new JTextField();
		userPasswordField = new JTextField();


		signUpBtn = new JButton("Sign Up");
		loginBtn = new JButton("Login");

		panels = new JPanel[5];

		for(int i=0;i<panels.length;i++) {
			panels[i] = new JPanel();
		}

		panels[0].setLayout(new GridLayout(1,1));
		panels[1].setLayout(new GridLayout(1,2));
		panels[2].setLayout(new GridLayout(1,2));
		panels[3].setLayout(new GridLayout(1,1));
		panels[4].setLayout(new GridLayout(1,2));

		logger.info("Login Components initialized");
	}


	public void addComponentsToPanel() {
		panels[0].add(title);

		panels[1].add(usernametext);
		panels[1].add(usernameField);

		panels[2].add(userPassword);
		panels[2].add(userPasswordField);

		panels[3].add(errorText);

		panels[4].add(signUpBtn);
		panels[4].add(loginBtn);

		logger.info("Compenents added to Panel");
	}

	public void addToPanelToFrame() {
		for(int i=0;i<panels.length;i++) {
			frame.add(panels[i]);
		}
		logger.info("Panel added to Frame");
	}

	public void setWindowProperties() {
		frame.setSize(400,400);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		logger.info("Window Prperties set");
	}

	public boolean getLoginDetails() {
		this.password = userPasswordField.getText();
		this.username = usernameField.getText();

		if(this.password.isEmpty() || this.username.isEmpty()) {
			return false;
		}else {
			return true;
		}

	}

	public void registerListeners() {
		signUpBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.signUpUser(frame); //passes frame object to function
			}

		});

		loginBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(getLoginDetails()){ //calls to function
					//if it finds the employee
					if(controller.SearchCustomer(username)) {
						//if the password is correct
						if(controller.checkPassword(password)) {
							controller.loginEmployee(frame);
							JOptionPane.showMessageDialog(null, "Login Successful", "Login Successful",JOptionPane.INFORMATION_MESSAGE);
						}else {
							JOptionPane.showMessageDialog(null, "Password Incorrect Please Try Again", "Incorrect Password",JOptionPane.ERROR_MESSAGE);
						}
					} else {//No user is found
						JOptionPane.showMessageDialog(null, "User Not Found", "User Not Found",JOptionPane.ERROR_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(null, "Please Enter Both Username and Password", "Not properly filled out",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		logger.info("Login Page Listeners initialized");
	}
}