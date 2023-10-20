package controller;

import javax.swing.JFrame;

import models.Employee;
import models.User;
import view.LoginView;
import view.SignUpView;

public class GuiController {

	private LoginView loginView;
	private SignUpView signupView;
	
	private User user;
	private Employee employee;
	
	public GuiController() {
		this.loginView = new LoginView(this);
		
	}
	
	public void signUpUser(JFrame frame) {
		this.signupView = new SignUpView(this,frame);
	}
	
	public static void main(String args[]) {
		new GuiController();
	}
	
}
