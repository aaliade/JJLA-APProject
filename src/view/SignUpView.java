package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import controller.GuiController;

public class SignUpView {

	private GuiController guiController;
	private JFrame frame;
	private JTextField firstNameField, lastNameField, phoneField, emailField,
						userNameField, passwordField, confirmPasswordField;
	private JLabel titleLabel, firstNameLabel, lastNameLabel,
					phoneNumberLabel, emailLabel, userNameLabel, passwordLabel, 
					confirmPasswordLabel;
	private JRadioButton rbtnCustomer, rbtnEmployee;
	private JButton goBackBtn, submitBtn, clearBtn;
	private JPanel[] panels;
	
	private static final Logger logger = LogManager.getLogger(SignUpView.class);
	
	
	public SignUpView(GuiController gui, JFrame Frame) {
		this.frame = Frame;
		this.guiController = gui;
		initializeComponents();
		addComponentsToPanel();
		addToPanelToFrame();
		setWindowProperties();
		registerListeners();
		logger.info("Sign Up Page created");
	}
	
	public void initializeComponents() {
		
		frame.setLayout(new GridLayout(11,1)); 
		
		titleLabel = new JLabel("Sign Up");
		firstNameLabel = new JLabel("First Name: ");
		lastNameLabel = new JLabel("Last Name: ");
		phoneNumberLabel = new JLabel("Phone Number: ");
		emailLabel = new JLabel("Email: ");
		userNameLabel = new JLabel("Username: ");
		passwordLabel = new JLabel("Password: ");
		confirmPasswordLabel = new JLabel("Confirm Password: ");
		
		firstNameField = new JTextField();
		lastNameField = new JTextField();
		phoneField = new JTextField();
		emailField = new JTextField();
		userNameField = new JTextField();
		passwordField = new JTextField();
		confirmPasswordField = new JTextField();
		
		rbtnCustomer = new JRadioButton("Customer");
		rbtnEmployee = new JRadioButton("Employee");
		
		goBackBtn = new JButton("Go Back");
		submitBtn = new JButton("Submit");
		clearBtn = new JButton("Clear");
		
		panels = new JPanel[11];
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
		panels[8].setLayout(new GridLayout(1,1));
		panels[9].setLayout(new GridLayout(1,1));
		panels[10].setLayout(new GridLayout(1,3));
		
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
		
		panels[6].add(passwordLabel);
		panels[6].add(passwordField);
		
		panels[7].add(confirmPasswordLabel);
		panels[7].add(confirmPasswordField);
		
		panels[8].add(rbtnCustomer);
		panels[9].add(rbtnEmployee);
		
		panels[10].add(goBackBtn);
		panels[10].add(clearBtn);
		panels[10].add(submitBtn);
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
	 
	private void registerListeners() {
		rbtnCustomer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rbtnEmployee.setEnabled(false);
			}
		});
		
		
		rbtnEmployee.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rbtnCustomer.setEnabled(false);
				
			}
		}); 
		
		submitBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(rbtnCustomer.isSelected()) {
					
					//Check if all fields are filled out
		
					String firstName, lastName, phoneNumber, email, userName, password, confirmPassword;
					
					firstName = firstNameField.getText();				
					lastName = lastNameField.getText();
					phoneNumber = phoneField.getText();
					email = emailField.getText();
					userName = userNameField.getText();
					password = passwordField.getText();
					confirmPassword = confirmPasswordField.getText();
					
					
					//Check Database first
					
					//Check if the fields are empty
					if(firstName.isEmpty() || lastName.isEmpty() || phoneNumber.isEmpty() ||
							email.isEmpty() || userName.isEmpty() || password.isEmpty() || 
							confirmPassword.isEmpty()) {
						JOptionPane.showMessageDialog(frame, "Please Ensure All Fields Are Properly Filled Out.", "Field Empty",JOptionPane.ERROR_MESSAGE);
					}
					
					//Check if the passwords are the same
					if(password.equals(confirmPassword)) {
						JOptionPane.showMessageDialog(frame, "Password correct", "Field Empty",JOptionPane.ERROR_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(frame, "Password incorrect", "Field Empty",JOptionPane.ERROR_MESSAGE);
					} 
					
					//Check Database first
					
					//Add to data to database
					
					//Display Message if registration is successful
					/*JOptionPane.showMessageDialog(frame, "You have been Successfully Registered.\r\n"
							+ "\r\n"
							+ "A User ID has been generated: n43rvntg\r\n"
							+ "\r\n"
							+ "This should be used to login. ", "Registration Complete",JOptionPane.INFORMATION_MESSAGE);*/
					
					//Display Message if registration is failed
					//JOptionPane.showMessageDialog(frame, "Please Ensure All Fields Are Properly Filled Out.", "Registration Failed",JOptionPane.ERROR_MESSAGE);
				}
				
				if(rbtnEmployee.isSelected()) {
					JOptionPane.showMessageDialog(null, "Note: A code was sent by the employer please check your email for the code.");
					String code = JOptionPane.showInputDialog(null, "Please Enter Registration Code:", 
			                "Employee Registration", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		clearBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//Enable buttons
				rbtnCustomer.setEnabled(true);
				rbtnEmployee.setEnabled(true);
				
				//Unselect Buttons
				rbtnCustomer.setSelected(false);
				rbtnEmployee.setSelected(false);
				
				//Clear Fields
				firstNameField.setText("");
				lastNameField.setText("");
				phoneField.setText("");
				emailField.setText("");
				userNameField.setText("");
				passwordField.setText("");
				confirmPasswordField.setText("");
			}
		});
		
		goBackBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				guiController.goBackToLoginPage(frame);
			}
		});
		logger.info("Sign Up Page Listeners initialized");
	}
	
}
