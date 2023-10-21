package view;

import java.awt.GridLayout;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import controller.GuiController;

public class CustomerDashboard {
	private JFrame frame;
	private JDesktopPane desktop;
	private JMenuBar menuBar;
	
	
	private JMenu cart, account;
	private JMenuItem viewProfile, updateProfile, deleteProfile, Logout,
						viewCart;
	
	private JTree treeView;
	
	private DefaultMutableTreeNode dashBoard, home, veiwEquipment, pastTransaction, message; 
	
	private GuiController guiController;
	
	public CustomerDashboard(GuiController gui, JFrame Frame) {
		this.guiController = gui;
		this.frame = Frame;
		
		this.initializeComponents();
		//addMenuItemsToPopup();
		this.addMenuItemsToMenu();
		this.addMenusToMenuBar();
		this.createTreeStructure();
		this.addTreeNodesToTree();
		this.addComponentsToWindow();
		this.setWindowProperties();
		this.registerListeners();
	}
	
	public void initializeComponents() {
		frame.setLayout(new GridLayout(1,1));
		
		desktop = new JDesktopPane();
		menuBar = new JMenuBar();
		
		cart = new JMenu("Cart");
		cart.setMnemonic('A');
		
		account = new JMenu("Account");
		account.setMnemonic('X');
		
		viewProfile = new JMenuItem("View Profile");
		updateProfile = new JMenuItem("Update Profile");
		deleteProfile = new JMenuItem("Delete Profile");
		Logout = new JMenuItem("Logout");
		viewCart  = new JMenuItem("View Cart");
		
		
		
		dashBoard = new DefaultMutableTreeNode("DashBoard");
	    home = new DefaultMutableTreeNode("Home");  
	    veiwEquipment = new DefaultMutableTreeNode("View Equiments");
	    pastTransaction = new DefaultMutableTreeNode("View Past Transaction");
	    message = new DefaultMutableTreeNode("Message");
	}
	
	public void addMenuItemsToMenu() {
		account.add(viewProfile);
		account.add(updateProfile);
		account.add(deleteProfile);
		account.addSeparator(); // separates items
		account.add(Logout);
		
		cart.add(viewCart);
	}
	
	public void addMenusToMenuBar() {
		menuBar.add(account);
		menuBar.add(cart);
	}
	
	public void createTreeStructure() {
		dashBoard.add(home);
		dashBoard.add(veiwEquipment);
		dashBoard.add(pastTransaction);
		dashBoard.add(message);
	}
	
	public void addTreeNodesToTree() {
		treeView = new JTree(dashBoard);
		
		// Remove default JTree icons
        DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) treeView.getCellRenderer();
        renderer.setLeafIcon(null);
        renderer.setClosedIcon(null);
        renderer.setOpenIcon(null);
	}
	
	public void addComponentsToWindow(){
		frame.add(desktop);
		frame.add(treeView);
	}
	
	public void setWindowProperties() {
		frame.setJMenuBar(menuBar);
		frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
	}
	
	public void registerListeners() {
		treeView.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				 DefaultMutableTreeNode node = (DefaultMutableTreeNode) treeView.getLastSelectedPathComponent(); //gets the node that was selected
				 String nodeName = node.toString();
				 if(nodeName.equals("Home")) {
					 
				 }
			}
		});
	}
	
}
