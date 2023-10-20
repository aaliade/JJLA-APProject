package controller;

import javax.swing.JFrame;

import models.Employee;
import models.User;
import view.CustomerDashboard;
import view.LoginView;
import view.SignUpView;

public class GuiController {

	private LoginView loginView;
	private SignUpView signupView;
	private CustomerDashboard customerDashboard;
	
	private User user;
	private Employee employee;
	
	public GuiController() {
		this.loginView = new LoginView(this);
	}
	
	public void signUpUser(JFrame frame) {
		clearFrame(frame);
		this.signupView = new SignUpView(this,frame);
	}
	
	public void loginUser(JFrame frame) {
		clearFrame(frame);
		this.customerDashboard = new CustomerDashboard(this,frame);
	}
	
	public void clearFrame(JFrame frame) {
		frame.getContentPane().removeAll(); //Clear Frame
		frame.repaint();
	}
	
	public static void main(String args[]) {
		new GuiController();
	}
	
}
