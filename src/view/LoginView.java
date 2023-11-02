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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import controller.GuiController;

public class LoginView {

	private JFrame frame;
	private JLabel userID, userPassword, title;
	private JTextField userIdField, userPasswordField;
	private JButton signUpBtn, loginBtn;
	private JPanel[] panels;
	private GuiController guiController;
	
	private static final Logger logger = LogManager.getLogger(LoginView.class);
	
//	private UtilDateModel model; 
//	private JDatePanelImpl datePanel;
//	private JDatePickerImpl datePicker; 
	
	
	public LoginView(GuiController gui) {
		this.guiController = gui;
		initializeComponents();
		addComponentsToPanel();
		addToPanelToFrame();
		setWindowProperties();
		registerListeners();
		logger.info("Login Page created");
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
		
		
//		Properties p = new Properties();
//		p.put("text.today", "Today");
//		p.put("text.month", "Month");
//		p.put("text.year", "Year");
		
//		model = new UtilDateModel();
//		datePanel = new JDatePanelImpl(model,p);
//		datePicker = new JDatePickerImpl(datePanel, null);
		logger.info("Login Components initialized");
	}
	
	
	public void addComponentsToPanel() {
		panels[0].add(title);
		
		panels[1].add(userID);
		panels[1].add(userIdField);
		
		panels[2].add(userPassword);
		panels[2].add(userPasswordField);
		
		panels[3].add(signUpBtn);
		panels[3].add(loginBtn);
		
//		panels[4].add(datePicker);
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
		
		/*
		datePicker.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String date = String.valueOf(model.getDay());
				System.out.print(date);
			}
		});*/
		logger.info("Login Page Listeners initialized");
	}
}
