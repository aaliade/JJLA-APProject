package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Vector;

import javax.swing.ImageIcon;
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
import controller.CustomerClientController;

public class DashBoard extends Decorations {
	private JFrame frame;
	private JMenuBar menuBar;

<<<<<<< HEAD
	private ImageIcon image, open, close, leaf;
=======
//	ImageIcon image = null;
//	URL imageUrl = getClass().getResource("CustomerClient\\images\\logo.png");
>>>>>>> branch 'main' of https://github.com/aaliade/JJLA-APProject.git
	
	private static final Logger logger = LogManager.getLogger(DashBoard.class);

	private JMenu account;
	private JMenuItem viewProfile, updateProfile, deleteProfile, Logout;

	private JTree treeView;
	// Main TreeNode
	private DefaultMutableTreeNode dashBoardNode;
	// SubTree Nodes Level 2
	private DefaultMutableTreeNode homeNode, veiwEquipmentNode, pastTransactionNode, messageNode;
	// SubTree Nodes Level 3
	private DefaultMutableTreeNode inboxNode, composeNode, recieptsNode, invoiceNode;

	private JPanel dashBoardPanel, viewPanel;

	private JTable equipmentTable;

	private JLabel welcomeLabel;
	private JLabel logoLabel;

	private CustomerClientController Dashboardcontroller;

	public DashBoard(CustomerClientController controller, JFrame Frame) {
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
		menuBar.setBorder(bevel);

		// Menu Items and Mnemonic for each
		account = new JMenu("Account");
		account.setMnemonic('X');
		account.setBorder(bevel);

		// Menu Items For MenuBar
		viewProfile = new JMenuItem("View Profile");
		updateProfile = new JMenuItem("Update Profile");
		deleteProfile = new JMenuItem("Delete Profile");
		Logout = new JMenuItem("Logout");
		viewProfile.setBorder(bevel);
		updateProfile.setBorder(bevel);
		deleteProfile.setBorder(bevel);
		Logout.setBorder(bevel);

		// Two view Panels in the window
		dashBoardPanel = new JPanel();
		viewPanel = new JPanel(new GridLayout(1,1));
        viewPanel.setBackground(cyan);
        

		// Nodes for Jtree
		dashBoardNode = new DefaultMutableTreeNode("DashBoard");
		homeNode = new DefaultMutableTreeNode("Home");
		veiwEquipmentNode = new DefaultMutableTreeNode("Equipment");
		pastTransactionNode = new DefaultMutableTreeNode("Transactions");
		messageNode = new DefaultMutableTreeNode("Message");
		

		inboxNode = new DefaultMutableTreeNode("Inbox");
		composeNode = new DefaultMutableTreeNode("Compose");
		recieptsNode = new DefaultMutableTreeNode("Receipts");

		veiwEquipmentNode = new DefaultMutableTreeNode("Equipment");
		pastTransactionNode = new DefaultMutableTreeNode("Transactions");
		messageNode = new DefaultMutableTreeNode("Message");
		inboxNode = new DefaultMutableTreeNode("Inbox");
		composeNode = new DefaultMutableTreeNode("Compose");
		recieptsNode = new DefaultMutableTreeNode("Reciepts");

		invoiceNode = new DefaultMutableTreeNode("Invoice");

		// Welcome Label
//		image = new ImageIcon(imageUrl);
//		logoLabel = new JLabel(image);
//		logoLabel.setBorder(bevel);
		
		welcomeLabel = new JLabel(
						"<html> Welcome to Grizzly's Entertainment!<br><br>We are a stage equipment business that offers the rental "
						+ "of equipment for events requiring: <br><br>Staging, Lighting, Power, and Sound.</html>",
						SwingConstants.CENTER);
		welcomeLabel.setVerticalAlignment(JLabel.CENTER);
		welcomeLabel.setFont(verdana);
		welcomeLabel.setPreferredSize(new Dimension(600, 600));
		welcomeLabel.setForeground(brown);
		welcomeLabel.setBorder(bevel);

		logger.info("Customer Dashboard components initialized");

		equipmentTable = new JTable();
		logger.info("Customer Dashboard components initialized");
	}

	public void addMenuItemsToMenu() {
		account.add(viewProfile);
		account.add(updateProfile);
		account.add(deleteProfile);
		account.addSeparator(); // separates items
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
		dashBoardNode.add(veiwEquipmentNode);
		dashBoardNode.add(pastTransactionNode);
		dashBoardNode.add(messageNode);

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
		treeView.setSize(100, 400);
		treeView.setBackground(coral);
		treeView.setBorder(bevel);

		// Remove default JTree icons
		DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) treeView.getCellRenderer();
		open = new ImageIcon(getClass().getResource("plus.png"));
		close = new ImageIcon(getClass().getResource("minus.png"));
		leaf = new ImageIcon(getClass().getResource("leaf.png")); 
		
		renderer.setLeafIcon(leaf);
		renderer.setClosedIcon(close);
		renderer.setOpenIcon(open);
		renderer.setBorder(bevel);
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

		updateProfile.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				clearPanel(viewPanel);
				JPanel[] panels = new JPanel[9];
				JPanel mainPanel = new JPanel();
				mainPanel.setLayout(new GridLayout(9, 1));

				for (int i = 0; i < panels.length; i++) {
					panels[i] = new JPanel();
				}

				panels[0].setLayout(new GridLayout(1, 1));
				panels[1].setLayout(new GridLayout(1, 2));
				panels[2].setLayout(new GridLayout(1, 2));
				panels[3].setLayout(new GridLayout(1, 2));
				panels[4].setLayout(new GridLayout(1, 2));
				panels[5].setLayout(new GridLayout(1, 2));
				panels[6].setLayout(new GridLayout(1, 2));
				panels[7].setLayout(new GridLayout(1, 2));
				panels[8].setLayout(new GridLayout(1, 2));

				JButton cancelBtn, updateBtn;

				JLabel addressLabel, emailLabel, firstNameLabel, lastNameLabel, passwordLabel, phoneLabel,
				usernameLabel, instructionLabel;
				JTextField addressField, emailField, firstNameField, lastNameField, passwordField, phoneField,
				usernameField;

				cancelBtn = new JButton("Cancel");
				updateBtn = new JButton("Update");

				instructionLabel = new JLabel("Only Enter Data In The Fields You Wish To Update");
				addressLabel = new JLabel("Address: ");
				emailLabel = new JLabel("Email: ");
				firstNameLabel = new JLabel("First Name: ");
				lastNameLabel = new JLabel("Last Name: ");
				passwordLabel = new JLabel("Password: ");
				phoneLabel = new JLabel("Phone: ");
				usernameLabel = new JLabel("Username: ");

				addressField = new JTextField();
				emailField = new JTextField();
				firstNameField = new JTextField();
				lastNameField = new JTextField();
				passwordField = new JTextField();
				phoneField = new JTextField();
				usernameField = new JTextField();

				panels[0].add(instructionLabel);

				panels[1].add(usernameLabel);
				panels[1].add(usernameField);

				panels[2].add(firstNameLabel);
				panels[2].add(firstNameField);

				panels[3].add(lastNameLabel);
				panels[3].add(lastNameField);

				panels[4].add(passwordLabel);
				panels[4].add(passwordField);

				panels[5].add(emailLabel);
				panels[5].add(emailField);

				panels[6].add(phoneLabel);
				panels[6].add(phoneField);

				panels[7].add(addressLabel);
				panels[7].add(addressField);

				panels[8].add(cancelBtn);
				panels[8].add(updateBtn);

				for (int i = 0; i < panels.length; i++) {
					mainPanel.add(panels[i]);
				}

				viewPanel.add(mainPanel);
				updatePanel(viewPanel);

				cancelBtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
					}
				});

				updateBtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						int result = JOptionPane.showConfirmDialog(frame,
								"Are you sure you want to proceed with this update?", "Confirmation",
								JOptionPane.YES_NO_OPTION);
						if (result == JOptionPane.YES_OPTION) {
							Dashboardcontroller.UpdateCustomerObject(addressField.getText(), emailField.getText(),
									firstNameField.getText(), lastNameField.getText(), passwordField.getText(),
									phoneField.getText(), usernameField.getText());
						} else if (result == JOptionPane.NO_OPTION) {

						} else if (result == JOptionPane.CLOSED_OPTION) {

						}
					}
				});
			}

		});

		Logout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Dashboardcontroller.closeConnection();
				Dashboardcontroller.goBackToLoginPage(frame);
			}
		});

		deleteProfile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(frame,
						"Are you sure you want to proceed with deleting your profile?", "Confirmation",
						JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					Dashboardcontroller.DeleteUser();
					if (Dashboardcontroller.GetDeleteStatus()) {
						// TODO Auto-generated method stub
						Dashboardcontroller.closeConnection();
						Dashboardcontroller.goBackToLoginPage(frame);
					}
				}
			}
		});

		viewProfile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearPanel(viewPanel);
				JPanel pan = new JPanel();
				pan.setLayout(new GridLayout(9, 1));

				JLabel custIDLabel, accountBalanceLabel, addressLabel, emailLabel, firstNameLabel, lastNameLabel,
				passwordLabel, phoneLabel, userTypeLabel, usernameLabel;

				custIDLabel = new JLabel();
				accountBalanceLabel = new JLabel();
				addressLabel = new JLabel();
				emailLabel = new JLabel();
				firstNameLabel = new JLabel();
				lastNameLabel = new JLabel();
				passwordLabel = new JLabel();
				phoneLabel = new JLabel();
				userTypeLabel = new JLabel();
				usernameLabel = new JLabel();

				Dashboardcontroller.setCustomerInfo(custIDLabel, accountBalanceLabel, addressLabel, emailLabel,
						firstNameLabel, lastNameLabel, phoneLabel, userTypeLabel, usernameLabel);

				pan.add(userTypeLabel);
				pan.add(usernameLabel);
				pan.add(custIDLabel);
				pan.add(accountBalanceLabel);
				pan.add(firstNameLabel);
				pan.add(lastNameLabel);
				pan.add(emailLabel);
				pan.add(phoneLabel);
				pan.add(addressLabel);
				viewPanel.add(pan);
				// Add Something to panel
				updatePanel(viewPanel);
			}
		});

		treeView.addTreeSelectionListener(new TreeSelectionListener() {
			@SuppressWarnings("rawtypes")
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) treeView.getLastSelectedPathComponent();
				String nodeName = node.toString();

				if (nodeName.equals("Home")) {
					clearPanel(viewPanel);
					viewPanel.add(logoLabel);
					viewPanel.add(welcomeLabel);
					updatePanel(viewPanel);
				} else if (nodeName.equals("Equipment")) {
					System.out.println("Equipment"); 
					clearPanel(viewPanel);
					Dashboardcontroller.getEquipmentsFromDatabase();
					if (Dashboardcontroller.getCurrentEquipmentCount() > 0) {
						JPanel panelView = new JPanel(new GridLayout(2, 1));
						String[] catgegories = { "Lighting", "Stage", "Sound", "Power" };

						@SuppressWarnings("unchecked")
						JLabel selectCategory = new JLabel("Select Category:");
						JButton sort = new JButton("Sort");
						JComboBox categoryList = new JComboBox(catgegories);
						
						selectCategory.setBorder(bevel);
						categoryList.setBorder(bevel);

						JPanel split = new JPanel(new GridLayout(1, 3));

						split.setBorder(low);
						split.add(selectCategory);
						split.add(categoryList);
						split.add(sort);

						panelView.add(split);

						JButton[] button = new JButton[Dashboardcontroller.getCurrentEquipmentCount()];
						for (int i = 0; i < button.length; i++) {
							button[i] = new JButton("Book Item");
						}
						System.out.print(Dashboardcontroller.getCurrentEquipmentCount());
						String[] columnNames = { "ID", "Name", "Category", "Rental Rate", "Description" };

						// Create an empty table model with column names
						DefaultTableModel tableModel = new DefaultTableModel(0, 0);
						tableModel.setColumnIdentifiers(columnNames);

						JTable equipmentTable = new JTable(tableModel);

						// Set cell spacing
						Dimension dim = new Dimension(20, 1);
						equipmentTable.setIntercellSpacing(dim);

						// Set up the table header with column names
						JTableHeader tableHeader = equipmentTable.getTableHeader();
						tableHeader.setFont(new Font("Arial", Font.BOLD, 70)); // Adjust font as needed

						// Create a TableRowSorter for the JTable
						TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(tableModel);
						equipmentTable.setRowSorter(rowSorter);

						Vector<Object> row = new Vector<>();
						for (int i = 0; i < Dashboardcontroller.getCurrentEquipmentCount(); i++) {
							row = Dashboardcontroller.updateEquipmentViewPanel(i);
							tableModel.addRow(row);
						}

						ListSelectionModel selectionModel = equipmentTable.getSelectionModel();
						selectionModel.addListSelectionListener(new ListSelectionListener() {
							@Override
							public void valueChanged(ListSelectionEvent e) {
								if (!e.getValueIsAdjusting()) {
									int selectedRow = equipmentTable.getSelectedRow();
									if (selectedRow != -1) {
										// Get values from the clicked row
										String ID = (String) equipmentTable.getValueAt(selectedRow, 0);
										String Name = (String) equipmentTable.getValueAt(selectedRow, 1);
										String Category = (String) equipmentTable.getValueAt(selectedRow, 2);
										Double RentalRate = (Double) equipmentTable.getValueAt(selectedRow, 3);
										String Description = (String) equipmentTable.getValueAt(selectedRow, 4);
										ItemInformation(ID, Name, Category,  RentalRate, Description);
									}
								}
							}
						});

						categoryList.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub

							}
						});

						sort.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								String categoryName = (String) categoryList.getSelectedItem();
								rowSorter.setRowFilter(RowFilter.regexFilter(categoryName));
							}

						});

						panelView.add(equipmentTable);
						viewPanel.add(panelView);
					}
					// Add Something to panel
					updatePanel(viewPanel);
				} else if (nodeName.equals("Invoice")) {
					System.out.println("Invoice");
					Dashboardcontroller.getInvoiceFromDatabase();
					if (Dashboardcontroller.getCurrentInvoiceCount() > 0) {
						JPanel panelView = new JPanel(new GridLayout(1, 1));
						String[] columns = { "Invoice Number", "Rent Date","Return Date","Cost"};
						
						// Create an empty table model with column names
						DefaultTableModel tableModel = new DefaultTableModel(0, 0);
						tableModel.setColumnIdentifiers(columns);

						JTable invoiceTable = new JTable(tableModel);

						// Set cell spacing
						Dimension dim = new Dimension(20, 1);
						invoiceTable.setIntercellSpacing(dim);
						
						Vector<Object> row = new Vector<>();
						for (int i = 0; i < Dashboardcontroller.getCurrentInvoiceCount(); i++) {
							row = Dashboardcontroller.updateInvoiceViewPanel(i);
							tableModel.addRow(row);
						}

						ListSelectionModel selectionModel = invoiceTable.getSelectionModel();
						selectionModel.addListSelectionListener(new ListSelectionListener() {
							@Override
							public void valueChanged(ListSelectionEvent e) {
								if (!e.getValueIsAdjusting()) {
									int selectedRow = invoiceTable.getSelectedRow();
									if (selectedRow != -1) {
										// Get values from the clicked row
										String InvoiceNumber = (String) invoiceTable.getValueAt(selectedRow, 0);
										String RentDate = (String) invoiceTable.getValueAt(selectedRow, 1);
										String ReturnDate = (String) invoiceTable.getValueAt(selectedRow, 2);
										String Cost = (String) invoiceTable.getValueAt(selectedRow, 3);
										InvoiceInformation(InvoiceNumber, RentDate, ReturnDate, Cost);
									}
								}
							}
						});
						panelView.add(invoiceTable);
						viewPanel.add(panelView);
					}
					updatePanel(viewPanel);
				} else if (nodeName.equals("Reciepts")) {
					clearPanel(viewPanel);
					Dashboardcontroller.getReceiptFromDatabase();
					if (Dashboardcontroller.getCurrentReceiptsCount() > 0) {
						JPanel panelView = new JPanel(new GridLayout(1, 1));
					
						System.out.print(Dashboardcontroller.getCurrentReceiptsCount());
						String[] columnNames = { "receiptNum", "payType", "payDate", "payAmt"};

						// Create an empty table model with column names
						DefaultTableModel tableModel = new DefaultTableModel(0, 0);
						tableModel.setColumnIdentifiers(columnNames);

						JTable receiptTable = new JTable(tableModel);

						// Set cell spacing
						Dimension dim = new Dimension(20, 1);
						receiptTable.setIntercellSpacing(dim);

						// Set up the table header with column names
						JTableHeader tableHeader = receiptTable.getTableHeader();
						tableHeader.setFont(new Font("Arial", Font.BOLD, 70)); // Adjust font as needed

						Vector<Object> row = new Vector<>();
						for (int i = 0; i < Dashboardcontroller.getCurrentReceiptsCount(); i++) {
							row = Dashboardcontroller.updateReceiptViewPanel(i);
							tableModel.addRow(row);
						}

						panelView.add(receiptTable);
						viewPanel.add(panelView);
					}
					// Add Something to panel
					updatePanel(viewPanel);
				} else if (nodeName.equals("Inbox")) {
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
				} else if (nodeName.equals("Compose")) {
					System.out.println("Message");
					clearPanel(viewPanel);
					JTextArea textarea = new JTextArea();
					textarea.setBounds(10,30, 200,200); 
					JButton button = new JButton("Send");
					JPanel panel = new JPanel(new GridLayout(2,1));
					
					panel.add(textarea);
					panel.add(button);
					
					viewPanel.add(panel);
					
					button.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							String content = textarea.getText();
							Dashboardcontroller.sendMessage(Integer.toString(GenerateMessageID()), content);
						}
						
					});
					// Add Something to panel

					updatePanel(viewPanel);
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
				Dashboardcontroller.sendMessage(Integer.toString(GenerateMessageID()), Replycontent);
			}
		});

		internalFrame.setSize(100, 100); 
		internalFrame.setVisible(true);

		internalFrame.add(panel);
		frame.add(internalFrame);
	}
	
	public void InvoiceInformation(String InvoiceNumber, String RentDate, String ReturnDate,String Cost ) {
		JInternalFrame internalFrame = new JInternalFrame("Compose A Message", true, true,true);
		JButton button = new JButton("Reply");
		JTextArea textarea = new JTextArea();
		textarea.setBounds(10,30, 200,200);

		JPanel panel = new JPanel(new GridLayout(4,1));
		
		panel.add(new JLabel("Invoice Number: " + InvoiceNumber));
		panel.add(new JLabel("Cost: " + Cost));
		panel.add(new JLabel("Rent Date: " + RentDate));
		panel.add(new JLabel("Return Date: " + ReturnDate));
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String Replycontent = textarea.getText();
				Dashboardcontroller.sendMessage(Integer.toString(GenerateMessageID()), Replycontent);
			}
		});

		internalFrame.setSize(100, 100); 
		internalFrame.setVisible(true);

		internalFrame.add(panel);
		frame.add(internalFrame);
	}

	public void ItemInformation(String ID, String Name,String Category, Double RentalRate,String Description ) {
		JInternalFrame internalFrame = new JInternalFrame("Item Information", true, true,true);
		JButton button = new JButton("Send Request");

		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");

		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model,p);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, null);
		datePicker.setBackground(coral);

		JPanel panel = new JPanel(new GridLayout(7,1));
		JPanel Datepanel = new JPanel(new GridLayout(1,2));
		JLabel pickDate = new JLabel("Pick a date you would want the equipment for: ");
		
		panel.setBackground(cyan);
		panel.setBorder(bevel);
		Datepanel.setBackground(cyan);
		Datepanel.setBorder(bevel);
		pickDate.setBackground(cyan);
		pickDate.setBorder(bevel);

		datePicker.addActionListener(new ActionListener() {
			@Override 
			public void actionPerformed(ActionEvent e) {
				int day = model.getDay();
				int month = model.getMonth();
				int year = model.getYear();

				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String dateString = year + "-" + month + "-" + day; // Format: yyyy-MM-dd
				pickDate.setText(dateString);
			} 
		});

		Datepanel.add(pickDate);
		Datepanel.add(datePicker);
		JLabel id =  new JLabel("Equipment ID: "+ ID);
		JLabel name = new JLabel("Equipment Name: "+ Name);
		JLabel category = new JLabel("Equipment Category: "+ Category);
		JLabel rate = new JLabel("Rental Rate: "+ Double.toString(RentalRate));
		JLabel description = new JLabel("Description: "+ Description);
		id.setBorder(bevel);
		name.setBorder(bevel);
		category.setBorder(bevel);
		rate.setBorder(bevel);
		description.setBorder(bevel);
		
		panel.add(Datepanel);
		panel.add(id);
		panel.add(name);
		panel.add(category);
		panel.add(rate);
		panel.add(description);
		panel.add(button);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int day = model.getDay();
				int month = model.getMonth();
				int year = model.getYear();

				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String dateString = year + "-" + month + "-" + day; // Format: yyyy-MM-dd

				Date hireDate = null;
				try {
					hireDate = dateFormat.parse(dateString);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				String content = ID + " " + dateString;
				Dashboardcontroller.sendMessage(Integer.toString(GenerateMessageID()), content);
			}
		});

		internalFrame.setSize(100, 100); 
		internalFrame.setVisible(true);

		internalFrame.add(panel);
		frame.add(internalFrame);
	}

	
	public int GenerateMessageID() {
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