package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import javax.swing.text.TableView;
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

	private JMenu account;
	private JMenuItem Logout;

	private JTree treeView;
	// Main TreeNode
	private DefaultMutableTreeNode dashBoardNode;
	// SubTree Nodes Level 2
	private DefaultMutableTreeNode homeNode, addEquipmentNode,EquipmentNode, scheduleEventNode, inboxNode, veiwEquipmentNode, createReceipt, createInvoice;

	private JPanel dashBoardPanel, viewPanel;

	private JLabel welcomeLabel;

	private EmployeeClientController Dashboardcontroller;

	public DashBoard(EmployeeClientController controller, JFrame Frame) {
		this.Dashboardcontroller = controller;
		this.frame = Frame;

		this.initializeComponents();
		// addMenuItemsToPopup();
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
		frame.setLayout(new GridLayout(1, 1));
		menuBar = new JMenuBar();

		// Menu Items and Mnemonic for each
		account = new JMenu("Account");
		account.setMnemonic('X');

		// Menu Items For MenuBar
		Logout = new JMenuItem("Logout");

		// Two view Panels in the window
		dashBoardPanel = new JPanel();
		viewPanel = new JPanel(new GridLayout(1,1));

		// Nodes for Jtree
		dashBoardNode = new DefaultMutableTreeNode("DashBoard");
		homeNode = new DefaultMutableTreeNode("Home");
		EquipmentNode = new DefaultMutableTreeNode("Equipment");
		addEquipmentNode= new DefaultMutableTreeNode("Add");
		veiwEquipmentNode = new DefaultMutableTreeNode("View");
		scheduleEventNode = new DefaultMutableTreeNode("Schedule");
		createReceipt= new DefaultMutableTreeNode("Create Receipt");
		createInvoice = new DefaultMutableTreeNode("Create Invoice");
		inboxNode = new DefaultMutableTreeNode("Inbox");

		// Welcome Label
		welcomeLabel = new JLabel(
				"<html>Welcome to Grizzly's Entertainment<br><br>We are a stage equipment business that offers the rental "
						+ "of equipment for events requiring: <br><br>Staging, Lighting, Power, and Sound.</html>",
						SwingConstants.CENTER);
		welcomeLabel.setVerticalAlignment(JLabel.TOP);
		welcomeLabel.setFont(new Font("Verdana", Font.BOLD, 15));
		welcomeLabel.setPreferredSize(new Dimension(600, 600));
		welcomeLabel.setForeground(new Color(120, 90, 40));
		welcomeLabel.setBackground(new Color(100, 20, 70));

		logger.info("Customer Dashboard components initialized");
	}

	public void addMenuItemsToMenu() {
		account.add(Logout);

		logger.info("Items added to Account and Cart Menus");
	}

	public void addMenusToMenuBar() {
		menuBar.add(account);
		logger.info("Account and Cart Menus added to Menu Bar");
	}

	public void createTreeStructure() {
		// Creating First Level
		dashBoardNode.add(homeNode);
		dashBoardNode.add(EquipmentNode);
		
		
		dashBoardNode.add(inboxNode);
		dashBoardNode.add(scheduleEventNode);
		dashBoardNode.add(createInvoice);
		dashBoardNode.add(createReceipt);
		
		
		EquipmentNode.add(veiwEquipmentNode);
		EquipmentNode.add(addEquipmentNode);
		
		logger.info("Tree Structure created");
	}

	public void addTreeNodesToTree() {
		treeView = new JTree(dashBoardNode);
		treeView.setSize(100, 400);

		// Remove default JTree icons
		DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) treeView.getCellRenderer();
		renderer.setLeafIcon(null);
		renderer.setClosedIcon(null);
		renderer.setOpenIcon(null);
		logger.info("Nodes added to Tree");
	}

	public void addComponentsToWindow() {
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
			@SuppressWarnings("rawtypes")
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) treeView.getLastSelectedPathComponent();
				String nodeName = node.toString();

				if (nodeName.equals("Home")) {
					clearPanel(viewPanel);
					System.out.println("Home");
					viewPanel.add(welcomeLabel);
					updatePanel(viewPanel);
				}else if (nodeName.equals("Add")) {
					clearPanel(viewPanel);
					System.out.println("Add");
					//viewPanel.add(welcomeLabel);
					updatePanel(viewPanel);
				}else if (nodeName.equals("View")) {
					clearPanel(viewPanel);
					System.out.println("View");
					//viewPanel.add(welcomeLabel);
					updatePanel(viewPanel);
				}else if (nodeName.equals("Schedule")) {
					clearPanel(viewPanel);
					JPanel panel = new JPanel(new GridLayout(6,1));
					JPanel Datepanel = new JPanel(new GridLayout(1,2));
					JPanel EventNamepanel = new JPanel(new GridLayout(1,2));
					JPanel EventLocationpanel = new JPanel(new GridLayout(1,2));
					String ID = Integer.toString(GenerateID());
					
					JLabel label = new JLabel("Enter Event Details");
					
					JLabel IDLabel = new JLabel("Generated ID Code For Event:   " + ID);
					JLabel eventNameLabel = new JLabel("Event Name: ");
					JTextField eventNameField = new JTextField();
					JLabel location = new JLabel("Location: ");
					JTextField locationField = new JTextField();
					JLabel date = new JLabel("Select The Event Date: ");
					JButton submit = new JButton("Submit");
					
					Properties p = new Properties();
					p.put("text.today", "Today");
					p.put("text.month", "Month");
					p.put("text.year", "Year");
					
					UtilDateModel model = new UtilDateModel();
					JDatePanelImpl datePanel = new JDatePanelImpl(model,p);
					JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, null);
					
					datePicker.addActionListener(new ActionListener() {
						@Override 
						public void actionPerformed(ActionEvent e) {
							String dateString = model.getYear() + "-" + model.getMonth() + "-" + model.getDay(); // Format: yyyy-MM-dd
						} 
					});
					
					EventNamepanel.add(eventNameLabel);
					EventNamepanel.add(eventNameField);
					
					EventLocationpanel.add(location);
					EventLocationpanel.add(locationField);
					
					Datepanel.add(date);
					Datepanel.add(datePicker);
					
					panel.add(label);
					panel.add(IDLabel);
					panel.add(Datepanel);
					panel.add(EventNamepanel);
					panel.add(EventLocationpanel);
					panel.add(submit);
					
					submit.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							int result = JOptionPane.showConfirmDialog(frame,
									"Are you sure you want to proceed with adding this event?", "Confirmation",
									JOptionPane.YES_NO_OPTION);
							if (result == JOptionPane.YES_OPTION) {
								String Name =  eventNameField.getText();
								String location =  locationField.getText();
								Dashboardcontroller.CreateEventObject(ID, Name, model.getDay(), model.getMonth(), model.getYear(), location);
							}							
						}
					});
					viewPanel.add(panel); 
					updatePanel(viewPanel);
				}else if (nodeName.equals("Create Receipt")) {
					clearPanel(viewPanel);
					System.out.println("Create Receipt");
					//viewPanel.add(welcomeLabel);
					updatePanel(viewPanel);
				}else if (nodeName.equals("Create Invoice")) {
					clearPanel(viewPanel);
					System.out.println("Create Invoice");
					//viewPanel.add(welcomeLabel);
					updatePanel(viewPanel);
				}else if (nodeName.equals("Inbox")) {
					clearPanel(viewPanel);
					System.out.println("Inbox");
					Dashboardcontroller.getMessagesFromDatabase();
					if (Dashboardcontroller.getCurrentMessageCount() > 0) {
						JPanel panelView = new JPanel(new GridLayout(2, 1));
						String[] columns = { "Sender Name", "Content"};
						
						// Create an empty table model with column names
						DefaultTableModel tableModel = new DefaultTableModel(0, 0);
						tableModel.setColumnIdentifiers(columns);

						JTable messageTable = new JTable(tableModel);

						// Set cell spacing
						Dimension dim = new Dimension(20, 1);
						messageTable.setIntercellSpacing(dim);

						// Set up the table header with column names
						JTableHeader tableHeader = messageTable.getTableHeader();
						tableHeader.setFont(new Font("Arial", Font.BOLD, 70)); // Adjust font as needed
						
						Vector<Object> row = new Vector<>();
						for (int i = 0; i < Dashboardcontroller.getCurrentMessageCount(); i++) {
							row = Dashboardcontroller.updateMessageViewPanel(i);
							tableModel.addRow(row);
						}

						ListSelectionModel selectionModel = messageTable.getSelectionModel();
						selectionModel.addListSelectionListener(new ListSelectionListener() {
							@Override
							public void valueChanged(ListSelectionEvent e) {
								if (!e.getValueIsAdjusting()) {
									int selectedRow = messageTable.getSelectedRow();
									if (selectedRow != -1) {
										// Get values from the clicked row
										String Sender = (String) messageTable.getValueAt(selectedRow, 0);
										String Content = (String) messageTable.getValueAt(selectedRow, 1);
										MessageInformation(Sender, Content);
									}
								}
							}
						});
						viewPanel.add(messageTable);
						updatePanel(viewPanel);
					}
				}
			}
		});
	}
	
	public void MessageInformation(String from,String content ) {
		JInternalFrame internalFrame = new JInternalFrame("Compose A Message", true, true,true);
		JButton button = new JButton("Reply");
		JTextArea textarea = new JTextArea();
		textarea.setBounds(10,30, 200,200);

		JPanel panel = new JPanel(new GridLayout(4,1));
		panel.add(new JLabel("From: " + from));
		panel.add(new JLabel(content));
		panel.add(textarea);
		panel.add(button);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String Replycontent = textarea.getText();
				Dashboardcontroller.sendMessage(Integer.toString(GenerateID()), Replycontent, from);
			}
		});

		internalFrame.setSize(100, 100); 
		internalFrame.setVisible(true);

		internalFrame.add(panel);
		frame.add(internalFrame);
	}
	
	public int GenerateID() {
		int min = 1000;  
		int max = 10000;  
		int MessageID = (int)(Math.random()*(max-min+1)+min);
		return MessageID;
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