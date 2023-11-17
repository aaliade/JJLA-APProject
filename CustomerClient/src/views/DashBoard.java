package views;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.TableView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import controller.CustomerClientController;


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
	private DefaultMutableTreeNode stagingNode, lightingNode, powerNode, soundNode, inboxNode, composeNode, recieptsNode, 
	invoiceNode;

	private JPanel dashBoardPanel, viewPanel;

	private JTable equipmentTable;

	private JLabel welcomeLabel;


	private CustomerClientController Dashboardcontroller;

	public DashBoard(CustomerClientController controller, JFrame Frame) {
		this.Dashboardcontroller = controller;
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
	    veiwEquipmentNode = new DefaultMutableTreeNode("Equipment");
	    pastTransactionNode = new DefaultMutableTreeNode("Transactions");
	    messageNode = new DefaultMutableTreeNode("Message");
	    
	    stagingNode = new DefaultMutableTreeNode("Staging");
	    lightingNode = new DefaultMutableTreeNode("Light");
	    powerNode = new DefaultMutableTreeNode("Power");
	    soundNode = new DefaultMutableTreeNode("Sound");
	    inboxNode = new DefaultMutableTreeNode("Inbox");
	    composeNode = new DefaultMutableTreeNode("Compose");
	    recieptsNode = new DefaultMutableTreeNode("Receipts");

		homeNode = new DefaultMutableTreeNode("Home");  
		veiwEquipmentNode = new DefaultMutableTreeNode("Equipment");
		pastTransactionNode = new DefaultMutableTreeNode("Transactions");
		messageNode = new DefaultMutableTreeNode("Message");

		stagingNode = new DefaultMutableTreeNode("Staging");
		lightingNode = new DefaultMutableTreeNode("Light");
		powerNode = new DefaultMutableTreeNode("Power");
		soundNode = new DefaultMutableTreeNode("Sound");
		inboxNode = new DefaultMutableTreeNode("Inbox");
		composeNode = new DefaultMutableTreeNode("Compose");
		recieptsNode = new DefaultMutableTreeNode("Reciepts");

		invoiceNode = new DefaultMutableTreeNode("Invoice");

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
	    
	    logger.info("Customer Dashboard components initialized");


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

		pastTransactionNode.add(invoiceNode);
		pastTransactionNode.add(recieptsNode);

		dashBoardNode.add(homeNode);
		dashBoardNode.add(veiwEquipmentNode);
		dashBoardNode.add(pastTransactionNode);
		dashBoardNode.add(messageNode);
		logger.info("Tree Structure created");
	}

	public void addTreeNodesToTree() {
		treeView = new JTree(dashBoardNode);
		treeView.setSize(100,400);

		// Remove default JTree icons
		DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) treeView.getCellRenderer();
		renderer.setLeafIcon(null);
		renderer.setClosedIcon(null);
		renderer.setOpenIcon(null);
		logger.info("Nodes added to Tree");
	}

	public void addComponentsToWindow(){
		JSplitPane paneSplit = new JSplitPane(SwingConstants.VERTICAL, treeView, viewPanel); 
		paneSplit.setDividerLocation(200);
		frame.add(paneSplit);
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
				if(nodeName.equals("Equipment")) {
					System.out.println("Equipment");
					clearPanel(viewPanel);

					Dashboardcontroller.getEquipmentsFromDatabase();

					if(Dashboardcontroller.getCurrentEquipmentCount()>0) {
						System.out.print(Dashboardcontroller.getCurrentEquipmentCount());
						equipmentTable = new JTable();
						String column[]={"Name","Category","Rental Rate", "Description"}; 
						// Create an empty table model with column names
						DefaultTableModel tableModel = new DefaultTableModel(0, 0);
						//add model to table
						tableModel.setColumnIdentifiers(column);
						equipmentTable.setModel(tableModel);
						equipmentTable.setShowGrid(false);
						equipmentTable.setBounds(30, 40, 200, 300);
						
						Vector<Object> row = new Vector<>();
						for(int i=0;i<Dashboardcontroller.getCurrentEquipmentCount();i++) {
							 row = Dashboardcontroller.updateEquipmentViewPanel(i);
							 tableModel.addRow(row);
						}
						viewPanel.add(equipmentTable);
					}

					//Add Something to panel
					updatePanel(viewPanel);
				}
				if(nodeName.equals("Light")) {
					System.out.println("Light");
					clearPanel(viewPanel);
					//Add Something to panel

					updatePanel(viewPanel);
				}
				if(nodeName.equals("Sound")) {
					System.out.println("Sound");
					clearPanel(viewPanel);
					//Add Something to panel

					updatePanel(viewPanel);
				}
				if(nodeName.equals("Power")) {
					System.out.println("Power");
					clearPanel(viewPanel);
					//Add Something to panel

					updatePanel(viewPanel);
				}
				if(nodeName.equals("Staging")) {
					System.out.println("Staging");
					clearPanel(viewPanel);
					//Add Something to panel

					updatePanel(viewPanel);
				}
				if(nodeName.equals("Transactions")) {
					System.out.println("Transactions");
					clearPanel(viewPanel);
					//Add Something to panel

					updatePanel(viewPanel);
				}
				if(nodeName.equals("Invoice")) {
					System.out.println("Invoice");
					clearPanel(viewPanel);
					//Add Something to panel

					updatePanel(viewPanel);
				}
				if(nodeName.equals("Reciepts")) {
					System.out.println("Reciepts");
					clearPanel(viewPanel);
					//Add Something to panel

					updatePanel(viewPanel);
				}
				if(nodeName.equals("Message")) {
					System.out.println("Message");
					clearPanel(viewPanel);
					//Add Something to panel

					updatePanel(viewPanel);
				} 
				if(nodeName.equals("Inbox")) {
					System.out.println("Message");
					clearPanel(viewPanel);
					//Add Something to panel

					updatePanel(viewPanel);
				}
				if(nodeName.equals("Compose")) {
					System.out.println("Message");
					clearPanel(viewPanel);
					//Add Something to panel

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
	}

	public void updatePanel(JPanel panel) {
		panel.revalidate();
		panel.repaint(); 
		logger.info("Customer Dashboard Listeners initialized");
	}
}