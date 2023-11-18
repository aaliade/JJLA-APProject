package views;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import controller.EmployeeClientController;

public class SignUp {

	private EmployeeClientController controller;
	private JFrame frame;
	private JTextField firstNameField, lastNameField, phoneField, emailField,
						userNameField, passwordField, confirmPasswordField,
						employeeRoleField,  addressField;
	private JLabel titleLabel, firstNameLabel, lastNameLabel,
					phoneNumberLabel, emailLabel, userNameLabel, passwordLabel, 
					confirmPasswordLabel, employeeRole, hireDate, addressLabel;
	private JButton goBackBtn, submitBtn, clearBtn;
	private JPanel[] panels;
	UtilDateModel model;
	JDatePanelImpl datePanel;
	JDatePickerImpl datePicker;
	private String firstName, lastName, phoneNumber, email, userName, password, confirmPassword, empRole, address;
	
	private static final Logger logger = LogManager.getLogger(SignUp.class);
	
	
	public SignUp(EmployeeClientController controller, JFrame Frame) {
		this.frame = Frame;
		this.controller = controller;
		initializeComponents();
		setLayout();
		addComponentsToPanel();
		addToPanelToFrame();
		setWindowProperties();
		registerListeners();
		logger.info("Sign Up Page created");
	}
	
	public void initializeComponents() {
		titleLabel = new JLabel("Sign Up");
		firstNameLabel = new JLabel("First Name: ");
		lastNameLabel = new JLabel("Last Name: ");
		phoneNumberLabel = new JLabel("Phone Number: ");
		emailLabel = new JLabel("Email: ");
		userNameLabel = new JLabel("Username: ");
		passwordLabel = new JLabel("Password: ");
		confirmPasswordLabel = new JLabel("Confirm Password: ");
		employeeRole = new JLabel("Employee Role: ");
		hireDate = new JLabel("Please Select Your Hire Date");
		addressLabel = new JLabel("Address: ");
		
		firstNameField = new JTextField();
		lastNameField = new JTextField();
		phoneField = new JTextField();
		emailField = new JTextField();
		userNameField = new JTextField();
		passwordField = new JTextField();
		confirmPasswordField = new JTextField();
		employeeRoleField = new JTextField();
		addressField = new JTextField();
	
		
		goBackBtn = new JButton("Go Back");
		submitBtn = new JButton("Submit");
		clearBtn = new JButton("Clear");
		
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");

		model = new UtilDateModel();
		datePanel = new JDatePanelImpl(model,p);
		datePicker = new JDatePickerImpl(datePanel, null);
		
		panels = new JPanel[12];
		for(int i=0;i<panels.length;i++) {
			panels[i] = new JPanel();
		}
		
		logger.info("Sign Up Components initialized");
	}
	
	public void setLayout() {
		frame.setLayout(new GridLayout(12,1)); 
		
		panels[0].setLayout(new GridLayout(1,1));
		
		panels[1].setLayout(new GridLayout(1,2));
		panels[2].setLayout(new GridLayout(1,2));
		panels[3].setLayout(new GridLayout(1,2));
		panels[4].setLayout(new GridLayout(1,2));
		panels[5].setLayout(new GridLayout(1,2));
		panels[6].setLayout(new GridLayout(1,2));
		panels[7].setLayout(new GridLayout(1,2));
		panels[8].setLayout(new GridLayout(1,2));
		panels[9].setLayout(new GridLayout(1,2));
		
		panels[10].setLayout(new GridLayout(1,2));
		
		panels[11].setLayout(new GridLayout(1,3));
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
		
		panels[8].add(employeeRole);
		panels[8].add(employeeRoleField);
		
		panels[9].add(addressLabel);
		panels[9].add(addressField);
		
		panels[10].add(hireDate);
		panels[10].add(datePicker);
		
		panels[11].add(goBackBtn);
		panels[11].add(clearBtn);
		panels[11].add(submitBtn);
		logger.info("Components added to Panel");
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
		
		datePicker.addActionListener(new ActionListener() {
			@Override 
			public void actionPerformed(ActionEvent e) {
				int day = model.getDay();
				int month = model.getMonth();
				int year = model.getYear();

				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String dateString = year + "-" + month + "-" + day; // Format: yyyy-MM-dd
				hireDate.setText(dateString);
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
				employeeRoleField.setText("");
				addressField.setText("");
			}
		});
		
		submitBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int IDNumber = GenerateRandomID();

				//get the value of the fields from the window
				GetFields();

				//if all the fields are filled out and the password is similar
				if(CheckFields() && CheckPassword()) {
					if(controller.CreateEmployeeObject(IDNumber, empRole, model.getDay(), model.getMonth(), model.getYear(), userName, password, firstName, lastName, phoneNumber, email, address, "Employee")) {
						JOptionPane.showMessageDialog(frame, "User Successfully added to database", "Sign Up Complete", JOptionPane.INFORMATION_MESSAGE);
						controller.goBackToLoginPage(frame);
					}
				}
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

	
	public void GetFields() {
		this.firstName = firstNameField.getText();				
		this.lastName = lastNameField.getText();
		this.phoneNumber = phoneField.getText();
		this.email = emailField.getText();
		this.userName = userNameField.getText();
		this.password = passwordField.getText();
		this.confirmPassword = confirmPasswordField.getText();
		this.empRole = employeeRoleField.getText();
		this.address = addressField.getText();
	}
	
	public boolean CheckFields() {
		//Check if the fields are empty
		if(this.firstName.isEmpty() || this.lastName.isEmpty() || this.phoneNumber.isEmpty() ||
				this.email.isEmpty() || this.userName.isEmpty() || this.password.isEmpty() || 
				this.confirmPassword.isEmpty() || this.empRole.isEmpty() || this.address.isEmpty()) {
			JOptionPane.showMessageDialog(frame, "Please Fill Out All Fields", "Incomplete Registration", JOptionPane.ERROR_MESSAGE);
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
}
