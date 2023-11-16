package views;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import controller.EmployeeClientController;

public class SignUp {
	private EmployeeClientController controller;
	private JFrame frame;
	private JTextField firstNameField, lastNameField, phoneField, emailField,
	userNameField, passwordField, confirmPasswordField, addressField, ;
	
	private JLabel titleLabel, firstNameLabel, lastNameLabel,
	phoneNumberLabel, emailLabel, userNameLabel, passwordLabel, 
	confirmPasswordLabel, addressLabel;
	
	private JButton goBackBtn, submitBtn, clearBtn;
	private JPanel[] panels;

	private String firstName, lastName, phoneNumber, email, userName, password, confirmPassword, address;

	private static final Logger logger = LogManager.getLogger(SignUp.class);
	
	
}
