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

public class SignUp {

	private CustomerClientController controller;


	private JFrame frame;

	private JTextField firstNameField, lastNameField, phoneField, emailField,
	userNameField, passwordField, confirmPasswordField, addressField;

	private JLabel titleLabel, firstNameLabel, lastNameLabel,
	phoneNumberLabel, emailLabel, userNameLabel, passwordLabel, 
	confirmPasswordLabel, addressLabel;

	private JButton goBackBtn, submitBtn, clearBtn;

	private JPanel[] panels;

	private String firstName, lastName, phoneNumber, email, userName, password, confirmPassword, address;

	private static final Logger logger = LogManager.getLogger(SignUp.class);

	public SignUp(CustomerClientController controller, JFrame Frame) {
		this.frame = Frame;
		this.controller = controller;
		initializeComponents();
		addComponentsToPanel();
		addToPanelToFrame();
		setWindowProperties();
		registerListeners();
		logger.info("Sign Up Page created");
	}

	public void initializeComponents() {

		frame.setLayout(new GridLayout(10,1)); 

		titleLabel = new JLabel("Sign Up");
		firstNameLabel = new JLabel("First Name: ");
		lastNameLabel = new JLabel("Last Name: ");
		phoneNumberLabel = new JLabel("Phone Number: ");
		emailLabel = new JLabel("Email: ");
		userNameLabel = new JLabel("Username: ");
		passwordLabel = new JLabel("Password: ");
		confirmPasswordLabel = new JLabel("Confirm Password: ");
		addressLabel  = new JLabel("Address: ");

		firstNameField = new JTextField();
		lastNameField = new JTextField();
		phoneField = new JTextField();
		emailField = new JTextField();
		userNameField = new JTextField();
		passwordField = new JTextField();
		confirmPasswordField = new JTextField();
		addressField = new JTextField();

		goBackBtn = new JButton("Go Back");
		submitBtn = new JButton("Submit");
		clearBtn = new JButton("Clear");

		panels = new JPanel[10];
		for(int i=0;i<panels.length;i++) {
			panels[i] = new JPanel();
		}

		panels[0].setLayout(new GridLayout(1,1));
		panels[1].setLayout(new GridLayout(1,2));
		panels[2].setLayout(new GridLayout(1,2));
		panels[3].setLayout(new GridLayout(1,2));
		panels[4].setLayout(new GridLayout(1,2));
		panels[5].setLayout(new GridLayout(1,2));
		panels[6].setLayout(new GridLayout(1,2));
		panels[7].setLayout(new GridLayout(1,2));
		panels[8].setLayout(new GridLayout(1,2));
		panels[9].setLayout(new GridLayout(1,3));


		logger.info("Sign Up Components initialized");
	}

	public void addComponentsToPanel() {
		panels[0].add(titleLabel);

		panels[1].add(firstNameLabel);
		panels[1].add(firstNameField);

		panels[2].add(lastNameLabel);
		panels[2].add(lastNameField);

		panels[3].add(phoneNumberLabel);
		panels[3].add(phoneField);

		panels[4].add(emailLabel);
		panels[4].add(emailField);

		panels[5].add(userNameLabel);
		panels[5].add(userNameField);

		panels[6].add(addressLabel);
		panels[6].add(addressField);

		panels[7].add(passwordLabel);
		panels[7].add(passwordField);

		panels[8].add(confirmPasswordLabel);
		panels[8].add(confirmPasswordField);

		panels[9].add(goBackBtn);
		panels[9].add(clearBtn);
		panels[9].add(submitBtn);
		logger.info("Compenets added to Panel");
	}

	public void addToPanelToFrame() {
		for(int i=0;i<panels.length;i++) {
			frame.add(panels[i]);
		}
		logger.info("Panel added to Frame");
	}

	public void setWindowProperties() {
		frame.setSize(400,600);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		logger.info("Window Properties set");
	}

	public boolean GetFields() {
		this.firstName = firstNameField.getText();				
		this.lastName = lastNameField.getText();
		this.phoneNumber = phoneField.getText();
		this.email = emailField.getText();
		this.userName = userNameField.getText();
		this.password = passwordField.getText();
		this.confirmPassword = confirmPasswordField.getText();
		this.address = addressField.getText();

		if(this.firstName.isEmpty() || this.lastName.isEmpty() || this.phoneNumber.isEmpty() ||
				this.email.isEmpty() || this.userName.isEmpty() || this.password.isEmpty() || 
				this.confirmPassword.isEmpty() || this.address.isEmpty()) {
			return false;
		}else {
			return true;
		}
	}

	public boolean CheckPassword() {
		//Check if the passwords are the same
		if(password.equals(confirmPassword)) {
			JOptionPane.showMessageDialog(frame, "Password correctly matched", "Field Empty",JOptionPane.INFORMATION_MESSAGE);
			return true;
		}else {
			JOptionPane.showMessageDialog(frame, "Password not matched", "Field Empty",JOptionPane.ERROR_MESSAGE);
			return false;
		} 

	}

	public int GenerateRandomID() {
		int min = 1000;  
		int max = 10000;  
		int IDNum = (int)(Math.random()*(max-min+1)+min);
		return IDNum;
	}

	private void registerListeners() {
		submitBtn.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				int IDNumber = GenerateRandomID();
				if(GetFields() && CheckPassword()) {
					//Check the Database first to see if there is any instance of the user
					if(controller.FindCustomer(userName)) { //Check if user exists to avoid duplicate records of a primary key in database
						JOptionPane.showMessageDialog(frame, "A user with this username already exists", "Registration Failed",JOptionPane.ERROR_MESSAGE);
					}else {
						if(controller.CreateCustomerObject(userName, password, firstName, lastName, phoneNumber,address,email,"Customer", IDNumber, 0.0f)) {
							JOptionPane.showMessageDialog(frame, "You have been Successfully Registered.\r\n"
									+ "\r\n"
									+ "A User ID has been generated: '" + IDNumber + "'\r\n"
									+ "\r\n"
									+ "This should be used to login. ", "Registration Complete",JOptionPane.INFORMATION_MESSAGE);
							controller.goBackToLoginPage(frame);
						}
					}
				}else {
					JOptionPane.showMessageDialog(null, "Please Ensure All Fields Are Properly Filled Out.", "Field Empty",JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		clearBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Clear Fields
				firstNameField.setText("");
				lastNameField.setText("");
				phoneField.setText("");
				emailField.setText("");
				userNameField.setText("");
				passwordField.setText("");
				confirmPasswordField.setText("");
				addressField.setText("");
			}
		});

		goBackBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.goBackToLoginPage(frame);
			}
		});
		logger.info("Sign Up Page Listeners initialized");
	}

}
