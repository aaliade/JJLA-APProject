package controller;

import javax.swing.JFrame;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import models.Employee;
import models.User;
import view.CustomerDashboard;
import view.LoginView;
import view.SignUpView;

public class GuiController {

	private LoginView loginView;
	private SignUpView signupView;
	private CustomerDashboard customerDashboard;
	
	private static final Logger logger = LogManager.getLogger(GuiController.class);
	
	private User user;
	private Employee employee;
	
	public GuiController() {
		this.loginView = new LoginView(this);
		logger.info("Controller initialized, Login page displayed");
	}
	
	public void signUpUser(JFrame frame) {
		clearFrame(frame);
		this.signupView = new SignUpView(this,frame);
		logger.info("Sign up page displayed");
	}
	
	public void loginUser(JFrame frame) {
		clearFrame(frame);
		this.customerDashboard = new CustomerDashboard(this,frame);
		logger.info("Customer logged in, Dashboard displayed");
	}
	
	public void goBackToLoginPage(JFrame frame) {
		frame.dispose(); //terminates frame
		this.loginView = new LoginView(this);
		logger.info("Returned to Login page");
	}
	
	public void clearFrame(JFrame frame) {
		frame.getContentPane().removeAll(); //Clear Frame
		frame.repaint();
		logger.info("Page cleared");
	}
	
	public static void main(String args[]) {
		new GuiController();
	}
	
}
