package view;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import controller.GuiController;

public class CustomerDashboard {
	private JFrame frame;
	private JDesktopPane desktop;
	private JMenuBar menuBar;
	
	private JMenu cart, account;
	private JLabel currentTab;
	
	private GuiController guiController;
	
	public CustomerDashboard(GuiController gui, JFrame Frame) {
		this.guiController = gui;
		this.frame = Frame;
		
		initializeComponents();
		//addMenuItemsToPopup();
		//addMenuItemsToMenu();
		addMenusToMenuBar();
		addComponentsToWindow();
		registerListeners();
		setWindowProperties();
	}
	
	public void initializeComponents() {
		desktop = new JDesktopPane();
		menuBar = new JMenuBar();
		
		cart = new JMenu("Cart");
		cart.setMnemonic('A');
		
		account = new JMenu("Account");
		account.setMnemonic('X');
		
		currentTab = new JLabel("Dashboard");
	}
	
	public void addMenusToMenuBar() {
		//menuBar.add(file);
		//menuBar.add(edit);
	}
	
	public void addComponentsToWindow(){
		frame.add(desktop);
	}
	
	public void setWindowProperties() {
		frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
	}
	
	public void registerListeners() {
		
	}
	
}
