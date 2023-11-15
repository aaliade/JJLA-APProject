package view;

import javax.swing.JFrame;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import controller.GuiController;

public class EmployeeDashboard {

	private static final Logger logger = LogManager.getLogger(EmployeeDashboard.class);
	private JFrame frame;
	
private GuiController guiController;
	
	public EmployeeDashboard(GuiController gui, JFrame Frame) {
		this.guiController = gui;
		this.frame = Frame;
		
		
		logger.info("Employee Dashboard created");
	}
	
}
