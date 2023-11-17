package views;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import controller.EmployeeClientController;


public class DashBoard {
	private JFrame frame;
	private JMenuBar menuBar;
	
	private static final Logger logger = LogManager.getLogger(DashBoard.class);
	
	private JMenu cart, account;
	private JMenuItem viewProfile, updateProfile, deleteProfile, Logout, viewCart;
	
	private JTree treeView;
	//Main TreeNode
	private DefaultMutableTreeNode dashBoardNode;
	//SubTree Nodes Level 2
	private DefaultMutableTreeNode homeNode, veiwEquipmentNode, pastTransactionNode, messageNode; 
	//SubTree Nodes Level 3
	private DefaultMutableTreeNode stagingNode, lightingNode, powerNode, soundNode, inboxNode, composeNode;
	
	private JPanel dashBoardPanel, viewPanel;
	
	private JLabel welcomeLabel;
	
	
	private EmployeeClientController controller;
	
	public DashBoard(EmployeeClientController controller, JFrame Frame) {
		this.controller = controller;
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
		logger.info("Customer Dashboard created");
	}
	
	public void initializeComponents() {
		//frame.setLayout(new GridLayout(1,2));
		
		menuBar = new JMenuBar();
		
		//Menu Items and Mnemonic for each
		cart = new JMenu("Cart");
		cart.setMnemonic('A');
		account = new JMenu("Account");
		account.setMnemonic('X');
		
		//Menu Items For MenuBar
		viewProfile = new JMenuItem("View Profile");
		updateProfile = new JMenuItem("Update Profile");
		deleteProfile = new JMenuItem("Delete Profile");
		Logout = new JMenuItem("Logout");
		viewCart  = new JMenuItem("View Cart");
		
		//Two view Panels in the window
		dashBoardPanel = new JPanel();
		viewPanel = new JPanel();
		
		//Nodes for Jtree
		dashBoardNode = new DefaultMutableTreeNode("DashBoard");
	    homeNode = new DefaultMutableTreeNode("Home");  
	    veiwEquipmentNode = new DefaultMutableTreeNode("View Equiments");
	    pastTransactionNode = new DefaultMutableTreeNode("View Past Transaction");
	    messageNode = new DefaultMutableTreeNode("Message");
	    
	    stagingNode = new DefaultMutableTreeNode("Staging");
	    lightingNode = new DefaultMutableTreeNode("Light");
	    powerNode = new DefaultMutableTreeNode("Power");
	    soundNode = new DefaultMutableTreeNode("Sound");
	    inboxNode = new DefaultMutableTreeNode("Inbox");
	    composeNode = new DefaultMutableTreeNode("Compose");
	    
	    //Welcome Label
	    welcomeLabel = new JLabel("<html>Welcome to Grizzly's Entertainment<br><br>We are a stage equipment business that offers the rental "
	    		+ "of equipment for events requiring: <br><br>Staging, Lighting, Power, and Sound.</html>", SwingConstants.CENTER);
	    welcomeLabel.setVerticalAlignment(JLabel.TOP);
	    welcomeLabel.setFont(new Font("Verdana", Font.BOLD, 15));
	    welcomeLabel.setPreferredSize(new Dimension(600, 600));
	    welcomeLabel.setForeground(new Color(120, 90, 40));
	    welcomeLabel.setBackground(new Color(100, 20, 70));
	    
	    
//		Properties p = new Properties();
//		p.put("text.today", "Today");
//		p.put("text.month", "Month");
//		p.put("text.year", "Year");
		
//		model = new UtilDateModel();
//		datePanel = new JDatePanelImpl(model,p);
//		datePicker = new JDatePickerImpl(datePanel, null);
	    
	    //This should be in the add components to panel method
//		panels[4].add(datePicker);
		dashBoardNode = new DefaultMutableTreeNode("DashBoard");
	    homeNode = new DefaultMutableTreeNode("Home");  
	    veiwEquipmentNode = new DefaultMutableTreeNode("View Equiments");
	    pastTransactionNode = new DefaultMutableTreeNode("View Past Transaction");
	    messageNode = new DefaultMutableTreeNode("Message");
	    logger.info("Customer Dashboard components initialized");
	}
	
	public void addMenuItemsToMenu() {
		account.add(viewProfile);
		account.add(updateProfile);
		account.add(deleteProfile);
		account.addSeparator(); // separates items
		account.add(Logout);
		
		cart.add(viewCart);
		logger.info("Items added to Account and Cart Menus");
	}
	
	public void addMenusToMenuBar() {
		menuBar.add(account);
		menuBar.add(cart);
		logger.info("Account and Cart Menus added to Menu Bar");
	}
	
	public void createTreeStructure() {
		//Creating First Level
		dashBoardNode.add(homeNode);
		dashBoardNode.add(veiwEquipmentNode);
		dashBoardNode.add(pastTransactionNode);
		dashBoardNode.add(messageNode);
		
		//Creating Second Level
		veiwEquipmentNode.add(lightingNode);
		veiwEquipmentNode.add(soundNode);
		veiwEquipmentNode.add(powerNode);
		veiwEquipmentNode.add(stagingNode);
		
		messageNode.add(composeNode);
		messageNode.add(inboxNode);
		
		dashBoardNode.add(homeNode);
		dashBoardNode.add(veiwEquipmentNode);
		dashBoardNode.add(pastTransactionNode);
		dashBoardNode.add(messageNode);
		logger.info("Tree Structure created");
	}
	
	public void addTreeNodesToTree() {
		treeView = new JTree(dashBoardNode);
		
		treeView.setSize(100,100);
		
		// Remove default JTree icons
        DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) treeView.getCellRenderer();
        renderer.setLeafIcon(null);
        renderer.setClosedIcon(null);
        renderer.setOpenIcon(null);
        logger.info("Nodes added to Tree");
	}
	
	public void addComponentsToWindow(){
		JSplitPane paneSplit = new JSplitPane(SwingConstants.VERTICAL, treeView, viewPanel); 
		//frame.add(treeView);
		//frame.add(viewPanel);
		frame.add(paneSplit);
		// frame.add(treeView);
		logger.info("Components added to Window");
	}
	
	public void setWindowProperties() {
		frame.setJMenuBar(menuBar);
		frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		logger.info("Window Properties set");
	}
	
	public void registerListeners() {
		treeView.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				 DefaultMutableTreeNode node = (DefaultMutableTreeNode) treeView.getLastSelectedPathComponent(); //gets the node that was selected
				 String nodeName = node.toString();
				 
				 //If home panel is selected
				 if(nodeName.equals("Home")) {
					 clearPanel(viewPanel);
					 viewPanel.add(welcomeLabel);
					 updatePanel(viewPanel);
				 }
				 
				 //cant compare with white spaces
				 /*if(nodeName.trim().replaceAll("\\s+", " ").equals("ViewEquipments")) {
					 System.out.println("View Equipments");
					 clearPanel(viewPanel);
				 }*/

				 if(nodeName == "View Equipments") {
					 System.out.println("Message");
					 clearPanel(viewPanel);

					 updatePanel(viewPanel);
				 }

				 if(nodeName.equals("Message")) {
					 System.out.println("Message");
					 clearPanel(viewPanel);
					 
					 updatePanel(viewPanel);
				 } 
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
	}
	
	
	public void clearPanel(JPanel panel) {
		panel.removeAll();
		//add your elements
	}
	
	public void updatePanel(JPanel panel) {
		panel.revalidate();
		panel.repaint(); 
		logger.info("Customer Dashboard Listeners initialized");
	}
	
}