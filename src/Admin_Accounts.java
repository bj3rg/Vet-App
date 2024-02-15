import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import com.mysql.cj.jdbc.Blob;

import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;


public class Admin_Accounts {
	
	private JFrame frame1;
	private JPanel navPanel, mainPanel, containerPanel, mainContainerPanel, tabPanel, petDataPanel;
	private JLabel vetaLabel, tabLabel;
	private String concat;
	
	
	public ImageIcon imageIcon(String name) {
		ImageIcon image = new ImageIcon(name);
		Image imagecon = image.getImage(); // Get the original image
		Image scaledImage = imagecon.getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Scale the image
		ImageIcon dbScaled = new ImageIcon(scaledImage); // Create a new ImageIcon with the scaled image
		return dbScaled;
	}
	//method for buttons
	private JButton myButton(String title,String name) {
		
		ImageIcon image = new ImageIcon(name);
		Image imagecon = image.getImage(); // Get the original image
		Image scaledImage = imagecon.getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Scale the image
		ImageIcon dbScaled = new ImageIcon(scaledImage); // Create a new ImageIcon with the scaled image
		
		JButton  button = new JButton(title);
		button.setIcon(dbScaled);
		button.setPreferredSize(new Dimension(200,30));
		button.setFocusable(false);
		button.setFont(new Font("SansSerif", Font.PLAIN, 20));
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setBackground(Color.decode("#1e1f22"));
		button.setBorder(null);
		button.setForeground(Color.decode("#dbdee1"));
		
		button.addMouseListener(new MouseAdapter() {
			
		public void mouseEntered(MouseEvent e) {
			button.setBackground(Color.decode("#36373d"));
			button.setForeground(Color.decode("#FFFFFF"));
		}
		public void mouseExited(MouseEvent e) {
			button.setBackground(Color.decode("#1e1f22"));
			button.setForeground(Color.decode("#dbdee1"));
		}
		public void mousePressed(MouseEvent e) {
			button.setBackground(Color.decode("#a1a3a5")); 
		}
		});
		return button;
	}

	private JButton accountButton(String title, String name) {
		
		ImageIcon image = new ImageIcon(name);
		Image imagecon = image.getImage(); // Get the original image
		Image scaledImage = imagecon.getScaledInstance(20, 20, Image.SCALE_SMOOTH); // Scale the image
		ImageIcon dbScaled = new ImageIcon(scaledImage); // Create a new ImageIcon with the scaled ima
		
		JButton addButton = new JButton(title);
		addButton.setForeground(Color.decode("#dbdee1"));
		addButton.setBorder(null);
		addButton.setFocusable(false);
		addButton.setIcon(dbScaled);
		addButton.setBackground(Color.decode("#404249"));
		addButton.setFont(new Font("SansSerif BOLD", Font.PLAIN, 17));

		addButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
			addButton.setBackground(Color.decode("#5c6aff"));
			addButton.setForeground(Color.decode("#FFFFFF"));
		}
		public void mouseExited(MouseEvent e) {
			addButton.setBackground(Color.decode("#404249"));
			addButton.setForeground(Color.decode("#dbdee1"));
		}
		public void mousePressed(MouseEvent e) {
			addButton.setBackground(Color.decode("#5c6aff")); 
		}
		});
		return addButton;
	}
	
	public JLabel adminAccLabel(String title) {
		JLabel label = new JLabel(title);
		label.setFont(new Font("SansSerif", Font.PLAIN, 18));
		label.setForeground(Color.decode("#dbdee1"));
		return label;
	}
	
	public static void main(String[] args) throws SQLException, IOException {
		Admin_Accounts at = new Admin_Accounts();
		at.accounts(1);
	}
	
	public void accounts(int user_id) throws SQLException, IOException{
		
		String tabDescription = "ACCOUNTS";
		
		frame1 = new JFrame("VETA");
		frame1.setSize(1536,864);
		frame1.setResizable(false);
		frame1.setLayout(new BorderLayout());
		frame1.setLocationRelativeTo(null);
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.setVisible(true);
		
		//panel same size of frame
		mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBackground(Color.decode("#404249"));;
		frame1.getContentPane().add(mainPanel,BorderLayout.CENTER);
		frame1.add(mainPanel,BorderLayout.CENTER);
	
		//left side panel for navs
		navPanel = new JPanel(new BorderLayout());
		navPanel.setPreferredSize(new Dimension(200,864));
		navPanel.setLayout(new BorderLayout());
		navPanel.setBackground(Color.decode("#1e1f22"));
		mainPanel.add(navPanel,BorderLayout.WEST);
		
		// big gray that holds all sub container panel
		containerPanel = new JPanel();
		containerPanel.setLayout(null);
		containerPanel.setPreferredSize(new Dimension(1236,82));
		containerPanel.setBackground(Color.decode("#404249"));
		mainPanel.add(containerPanel,BorderLayout.CENTER);
		
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel(new BorderLayout());
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0,30));
		JPanel panel4 = new JPanel();
		JPanel panel5 = new JPanel();
		panel1.setBackground(Color.decode("#1e1f22"));
		panel2.setBackground(Color.decode("#1e1f12"));
		buttonPanel.setBackground(Color.decode("#1e1f22"));
		panel4.setBackground(Color.decode("#1e1f22"));
		panel5.setBackground(Color.decode("#1e1f22"));
		
		panel1.setPreferredSize(new Dimension(20,50));
		panel2.setPreferredSize(new Dimension(100,170));
		buttonPanel.setPreferredSize(new Dimension(100,200));
		buttonPanel.setBackground(Color.decode("#1e1f22"));
		panel4.setPreferredSize(new Dimension(100,200));
		panel5.setPreferredSize(new Dimension(20,50));
		
		navPanel.add(panel1, BorderLayout.WEST);
		navPanel.add(panel2, BorderLayout.NORTH);
		navPanel.add(buttonPanel, BorderLayout.CENTER);
		navPanel.add(panel4, BorderLayout.SOUTH);
		navPanel.add(panel5, BorderLayout.EAST);
		
		JPanel split1 = new JPanel();
		split1.setBackground(Color.decode("#1e1f22"));
		split1.setPreferredSize(new Dimension(50,100));
		JPanel split2 = new JPanel(new FlowLayout());
		split2.setBackground(Color.decode("#1e1f22"));
		split2.setPreferredSize(new Dimension(50,70));
		panel2.add(split1, BorderLayout.NORTH);
		panel2.add(split2,BorderLayout.SOUTH);
		
		vetaLabel = new JLabel("VETA");
		vetaLabel.setBounds(80,100,185,50);
		vetaLabel.setFont(new Font("SansSerif Bold", Font.PLAIN, 50));
		vetaLabel.setForeground(Color.decode("#ffffff"));
		split2.add(vetaLabel);
		
		tabPanel = new JPanel() {

			protected void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g.create();
				int width= getWidth();
				int height = getHeight();
				Shape shape = new RoundRectangle2D.Double(0,0,width,height,20,20);
				g2.setClip(shape);
				super.paintComponent(g2);
				g2.dispose();
			}
		}; 
		tabPanel.setBounds(510,0,280,50);
		tabPanel.setBackground(Color.decode("#616463"));
		containerPanel.add(tabPanel, BorderLayout.NORTH);
		
		tabLabel = new JLabel(tabDescription);
		tabLabel.setForeground(Color.decode("#ffffff"));
		tabLabel.setFont(new Font("SansSerif Bold", Font.PLAIN, 30));
		tabPanel.add(tabLabel);

		mainContainerPanel = new JPanel();
		mainContainerPanel.setBounds(20,70,1280,735);
		mainContainerPanel.setLayout(null);
		mainContainerPanel.setBackground(Color.decode("#313338"));
		containerPanel.add(mainContainerPanel);
		
		JLabel table1Label = adminAccLabel("USER ACCOUNT - PET IDENTIFICATION");
		table1Label.setBounds(460,0,350,30);
		mainContainerPanel.add(table1Label);

		JLabel table2Label = adminAccLabel("USER ACCOUNT - VERIFICATION");
		table2Label.setBounds(140,220,350,30);
		mainContainerPanel.add(table2Label);
		
		Border border = BorderFactory.createLineBorder(Color.decode("#616463"), 5);
		JPanel imagePanel = new JPanel();
		imagePanel.setBorder(border);
		imagePanel.setBounds(855,210,380,510);
		imagePanel.setBackground(Color.decode("#4e4f50"));
		mainContainerPanel.add(imagePanel);
		
		JLabel imageContainerLabel = new JLabel("");
		imageContainerLabel.setPreferredSize(new Dimension(360,490));
		imagePanel.add(imageContainerLabel);
		
		
		// For updating all status
		JLabel acc_statusLabel = adminAccLabel("Change status");
		acc_statusLabel.setBounds(620,220,150,30);
		mainContainerPanel.add(acc_statusLabel);
		
		JLabel idLabel = adminAccLabel("Enter User ID:");
		idLabel.setBounds(560,260,150,30);
		mainContainerPanel.add(idLabel);
		
		JTextField idField = new JTextField();
		idField.setBounds(690,260,120,30);
		mainContainerPanel.add(idField);
		
		JLabel chooseLabel = adminAccLabel("Choose Table:");
		chooseLabel.setBounds(560,300,150,30);
		mainContainerPanel.add(chooseLabel);
		
		String tableList[] = {"Pet Identity Table-1", "User-Verify Table-2"};
		JComboBox<String> comboTable = new JComboBox<String>(tableList);
		comboTable.setBounds(690,300,120,30);
		comboTable.setSelectedIndex(-1);
		mainContainerPanel.add(comboTable);
		
		JLabel statusLabel = adminAccLabel("Choose Status:");
		statusLabel.setBounds(560,340,150,30);
		mainContainerPanel.add(statusLabel);
		
		String statusList[] = {"Verified"};
		JComboBox<String> comboStatus = new JComboBox<String>(statusList);
		comboStatus.setBounds(690,340,120,30);
		comboStatus.setSelectedIndex(-1);
		mainContainerPanel.add(comboStatus);
		
		JButton accStatusButton = accountButton("APPLY", "D:\\3rd Year BSCPE (2nd SEMESTER)\\Software Design\\ICONS VETA\\icons8-change-100.png");
		accStatusButton.setBounds(690,390,120,30);
		accStatusButton.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String get_id = idField.getText();
				String get_table = (String) comboTable.getSelectedItem();
				String get_status = (String) comboStatus.getSelectedItem();
				
				if(get_id.isEmpty() || !get_id.matches("\\d+") || get_status==null || get_status==null){
				    System.out.println("EMPTY");
				    comboStatus.setSelectedIndex(-1);
				    comboTable.setSelectedIndex(-1);
				    idField.setText("");
					return;
				} 
				int id = Integer.parseInt(get_id);
				
				if(comboStatus.getSelectedIndex()==0) {
					if(comboTable.getSelectedIndex()==0) {
						try {
							Class.forName("com.mysql.cj.jdbc.Driver");
							Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylogindb","root","burog181");
							String sql = "SELECT * FROM customer_information WHERE user_id=?";
							PreparedStatement pstmt = con.prepareStatement(sql);
							pstmt.setInt(1, id);
							ResultSet rs = pstmt.executeQuery();
							if(rs.next()) {
								String retrieved_status = rs.getString("owner_status");
								if(retrieved_status.equals(get_status)) {
									 comboStatus.setSelectedIndex(-1);
									    comboTable.setSelectedIndex(-1);
									    idField.setText("");
									JOptionPane.showMessageDialog(null, "User is already verified!");
								} else {
									sql = "UPDATE customer_information SET owner_status=? WHERE user_id=?";
									pstmt = con.prepareStatement(sql);
									pstmt.setString(1, get_status);
									pstmt.setInt(2, id);
									pstmt.executeUpdate();
									comboStatus.setSelectedIndex(-1);
								    comboTable.setSelectedIndex(-1);
								    idField.setText("");
									JOptionPane.showMessageDialog(null, "User "+id+ " is now verified!");
									frame1.setVisible(false);
									accounts(1);
								}
							}else {
								comboStatus.setSelectedIndex(-1);
							    comboTable.setSelectedIndex(-1);
							    idField.setText("");
								JOptionPane.showMessageDialog(null, "User not found!");
							}
						} catch (ClassNotFoundException | SQLException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}else if(comboTable.getSelectedIndex()==1){
						try {
							Class.forName("com.mysql.cj.jdbc.Driver");
							Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylogindb","root","burog181");
							String sql = "SELECT * FROM customer_shipping_verification WHERE customer_id=?";
							PreparedStatement pstmt = con.prepareStatement(sql);
							pstmt.setInt(1, id);
							ResultSet rs = pstmt.executeQuery();
							if(rs.next()) {
								String retrieved_status = rs.getString("verification_status");
								if(retrieved_status.equals(get_status)) {
									 comboStatus.setSelectedIndex(-1);
									    comboTable.setSelectedIndex(-1);
									    idField.setText("");
									JOptionPane.showMessageDialog(null, "User is already verified!");
								}else {
									sql = "UPDATE customer_shipping_verification SET verification_status=? WHERE customer_id=?";
									pstmt = con.prepareStatement(sql);
									pstmt.setString(1, get_status);
									pstmt.setInt(2, id);
									pstmt.executeUpdate();
									comboStatus.setSelectedIndex(-1);
								    comboTable.setSelectedIndex(-1);
								    idField.setText("");
									JOptionPane.showMessageDialog(null, "User "+id+ " shipping verification is now verified! They can now buy items!");
									frame1.setVisible(false);
									accounts(1);
								}
							}else {
								comboStatus.setSelectedIndex(-1);
							    comboTable.setSelectedIndex(-1);
							    idField.setText("");
								JOptionPane.showMessageDialog(null, "User not found!");
							}
						} catch (ClassNotFoundException | SQLException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}else {
						comboStatus.setSelectedIndex(-1);
					    comboTable.setSelectedIndex(-1);
					    idField.setText("");
						JOptionPane.showMessageDialog(null, "Invalid Combination");
					}
				}			
			}
		});
		mainContainerPanel.add(accStatusButton);
		
		JButton clearButton = accountButton("CLEAR", "D:\\3rd Year BSCPE (2nd SEMESTER)\\Software Design\\ICONS VETA\\icons8-clear-symbol-100.png");
		clearButton.setBounds(560,390,120,30);
		clearButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				idField.setText("");
				comboStatus.setSelectedIndex(-1);
				comboTable.setSelectedIndex(-1);
			}
		});
		mainContainerPanel.add(clearButton);
		
		//For choosing image
		JLabel imageLabel = adminAccLabel("Image Viewer");
		imageLabel.setBounds(610,470,150,30);
		mainContainerPanel.add(imageLabel);
		
		JLabel idLabel2 = adminAccLabel("Enter User ID:");
		idLabel2.setBounds(560,510,150,30);
		mainContainerPanel.add(idLabel2);
		
		JTextField idField2 = new JTextField();
		idField2.setBounds(690,510,120,30);
		mainContainerPanel.add(idField2);
		
		JLabel chooseLabel2 = adminAccLabel("Choose Image:");
		chooseLabel2.setBounds(560,550,150,30);
		mainContainerPanel.add(chooseLabel2);
		
		String tableList2[] = {"Pet Identity img", "User Valid ID img"};
		JComboBox<String> comboTable2 = new JComboBox<String>(tableList2);
		comboTable2.setBounds(690,550,120,30);
		comboTable2.setSelectedIndex(-1);
		mainContainerPanel.add(comboTable2);
		
		JLabel detailLabel = adminAccLabel("");
		detailLabel.setBounds(560,675,450,30);
		
		mainContainerPanel.add(detailLabel);
		
		//image viewer button
		JButton searchImageBtn = accountButton("SEARCH", "D:\\3rd Year BSCPE (2nd SEMESTER)\\Software Design\\ICONS VETA\\icons8-search-100.png");
		searchImageBtn.setBounds(690,600,120,30);
		searchImageBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String get_id = idField2.getText();
				String get_table = (String) comboTable2.getSelectedItem();
				
				if(get_id.isEmpty() || !get_id.matches("\\d+") || get_table==null){
					comboTable2.setSelectedIndex(-1);
					idField2.setText("");
					return;
				} 
				int id = Integer.parseInt(get_id);
				
				if(comboTable2.getSelectedIndex()==0) {
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylogindb","root","burog181");
						String sql = "SELECT * FROM customer_information WHERE user_id=?";
						PreparedStatement pstmt = con.prepareStatement(sql);
						pstmt.setInt(1, id);
						ResultSet rs = pstmt.executeQuery();
						if(rs.next()) {
							Blob img = (Blob) rs.getBlob("pet_image");
							InputStream inputStream = img.getBinaryStream();
					        byte[] imageBytes = inputStream.readAllBytes();
					        ImageIcon imageIcon = new ImageIcon(imageBytes);
					        Image scaledImage = imageIcon.getImage().getScaledInstance(360,490,Image.SCALE_SMOOTH);
					        imageContainerLabel.setIcon(new ImageIcon(scaledImage));
					        comboTable2.setSelectedIndex(-1);
							idField2.setText("");
							concat = "Pet Image | User ID: "+get_id;
							detailLabel.setText(concat);
						}else {
							comboTable2.setSelectedIndex(-1);
							idField2.setText("");
							JOptionPane.showMessageDialog(null, "User not found!");
						}
					} catch (ClassNotFoundException | SQLException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else{
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylogindb","root","burog181");
						String sql = "SELECT * FROM customer_shipping_verification WHERE customer_id=?";
						PreparedStatement pstmt = con.prepareStatement(sql);
						pstmt.setInt(1, id);
						ResultSet rs = pstmt.executeQuery();
						if(rs.next()) {
							Blob img = (Blob) rs.getBlob("customer_valid_id");
							InputStream inputStream = img.getBinaryStream();
					        byte[] imageBytes = inputStream.readAllBytes();
					        ImageIcon imageIcon = new ImageIcon(imageBytes);
					        Image scaledImage = imageIcon.getImage().getScaledInstance(360,490,Image.SCALE_SMOOTH);
					        imageContainerLabel.setIcon(new ImageIcon(scaledImage));
					        comboTable2.setSelectedIndex(-1);
							idField2.setText("");
							concat = "User Valid ID Image | User ID: "+get_id;
							detailLabel.setText(concat);
						}else {
							comboTable2.setSelectedIndex(-1);
							idField2.setText("");
							JOptionPane.showMessageDialog(null, "User not found!");
						}
					} catch (ClassNotFoundException | SQLException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		mainContainerPanel.add(searchImageBtn);
		
		JButton clearButton2 = accountButton("CLEAR", "D:\\3rd Year BSCPE (2nd SEMESTER)\\Software Design\\ICONS VETA\\icons8-clear-symbol-100.png");
		clearButton2.setBounds(560,600,120,30);
		clearButton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				idField.setText("");
				comboStatus.setSelectedIndex(-1);
				comboTable.setSelectedIndex(-1);
				imageContainerLabel.setIcon(null);
				detailLabel.setText("");
			}
		});
		mainContainerPanel.add(clearButton2);

		JPanel accountsPanel = new JPanel();
		accountsPanel.setBounds(25,30,1230,170);
		accountsPanel.setBackground(Color.decode("#616463"));
		mainContainerPanel.add(accountsPanel);
		
		String[] columnTitle1 = {"ID","Owner", "Email", "PhoneNumber", "Pet","Gender", "Name", "Breed", "Bday", "Account Status"};
		DefaultTableModel model = new DefaultTableModel();
		JTable customerTable = new JTable(model);
		JScrollPane spane = new JScrollPane(customerTable);
		customerTable.setOpaque(true);
		customerTable.setBackground(Color.decode("#f2f2f2"));
		customerTable.setRowHeight(20);
		customerTable.setEnabled(false);
		customerTable.setPreferredScrollableViewportSize(new Dimension(1210,140));
		customerTable.getTableHeader().setBackground(new Color(30,30,30));
		customerTable.getTableHeader().setForeground(new Color(200,200,200));

		for(int i = 0; i <= columnTitle1.length-1; i++) {
		    model.addColumn(columnTitle1[i]);
		}

		//Create a custom cell renderer for the first row
		DefaultTableCellRenderer customRenderer = new DefaultTableCellRenderer() {
		    /**
		     *
		     */
		    private static final long serialVersionUID = 1L;

		    @Override
		    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		        Component c3 = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		        if (row %2 == 0) { // check if this is the first row
		            c3.setBackground(Color.decode("#2f2f2f")); // set the background color
		            c3.setForeground(Color.white);
		        }else {
		            c3.setBackground(Color.decode("#555555"));
		        }
		        return c3;
		    }
		};

		// Set the cell renderer for all cells in the first row
		for (int i = 0; i < customerTable.getColumnCount(); i++) {
		    customerTable.getColumnModel().getColumn(i).setCellRenderer(customRenderer);
		}

		// Calculate the preferred width of each column
		for (int column = 0; column < customerTable.getColumnCount(); column++) {
		    int maxWidth = 0;
		    TableColumn tableColumn = customerTable.getColumnModel().getColumn(column);
		    for (int row = 0; row < customerTable.getRowCount(); row++) {
		        TableCellRenderer cellRenderer = customerTable.getCellRenderer(row, column);
		        Object value = customerTable.getValueAt(row, column);
		        Component cellComponent = cellRenderer.getTableCellRendererComponent(customerTable, value, false, false, row, column);
		        maxWidth = Math.max(maxWidth, cellComponent.getPreferredSize().width);
		    }
		    tableColumn.setPreferredWidth(maxWidth);
		}
		accountsPanel.add(new JScrollPane(customerTable));
		
		JPanel shippingAccountPanel = new JPanel();
		shippingAccountPanel.setBounds(20,250,500,470);
		shippingAccountPanel.setBackground(Color.decode("#616463"));
		mainContainerPanel.add(shippingAccountPanel);
		
		String[] columnTitle2 = {"ID","Name", "Address", "Verification Status"};
		DefaultTableModel model2 = new DefaultTableModel();
		JTable customerShippingTable = new JTable(model2);
		JScrollPane spane2 = new JScrollPane(customerShippingTable);
		customerShippingTable.setOpaque(true);
		customerShippingTable.setBackground(Color.decode("#f2f2f2"));
		customerShippingTable.setRowHeight(20);
		customerShippingTable.setEnabled(false);
		customerShippingTable.setPreferredScrollableViewportSize(new Dimension(480,440));
		customerShippingTable.getTableHeader().setBackground(new Color(30,30,30));
		customerShippingTable.getTableHeader().setForeground(new Color(200,200,200));

		for(int i = 0; i <= columnTitle2.length-1; i++) {
		    model2.addColumn(columnTitle2[i]);
		}

		//Create a custom cell renderer for the first row
		DefaultTableCellRenderer customRenderer2 = new DefaultTableCellRenderer() {
		    /**
		     *
		     */
		    private static final long serialVersionUID = 1L;

		    @Override
		    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		        Component c3 = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		        if (row %2 == 0) { // check if this is the first row
		            c3.setBackground(Color.decode("#2f2f2f")); // set the background color
		            c3.setForeground(Color.white);
		        }else {
		            c3.setBackground(Color.decode("#555555"));
		        }
		        return c3;
		    }
		};

		// Set the cell renderer for all cells in the first row
		for (int i = 0; i < customerShippingTable.getColumnCount(); i++) {
		    customerShippingTable.getColumnModel().getColumn(i).setCellRenderer(customRenderer2);
		}

		// Calculate the preferred width of each column
		for (int column = 0; column < customerShippingTable.getColumnCount(); column++) {
		    int maxWidth = 0;
		    TableColumn tableColumn = customerShippingTable.getColumnModel().getColumn(column);
		    for (int row = 0; row < customerShippingTable.getRowCount(); row++) {
		        TableCellRenderer cellRenderer = customerShippingTable.getCellRenderer(row, column);
		        Object value = customerShippingTable.getValueAt(row, column);
		        Component cellComponent = cellRenderer.getTableCellRendererComponent(customerShippingTable, value, false, false, row, column);
		        maxWidth = Math.max(maxWidth, cellComponent.getPreferredSize().width);
		    }
		    tableColumn.setPreferredWidth(maxWidth);
		}
		shippingAccountPanel.add(new JScrollPane(customerShippingTable));

		// Display values on table via database
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylogindb","root","burog181");
			String sql = "SELECT * FROM customer_information";
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int i_user_id = rs.getInt("user_id");
				String owner_name = rs.getString("pet_owner");
				String s_user_id = String.valueOf(i_user_id);
				String pet_type = rs.getString("pet_type");
				String pet_gender = rs.getString("pet_gender");
				String pet_name = rs.getString("pet_name");
				String pet_breed = rs.getString("pet_breed");
				String pet_bday = rs.getString("pet_bday");
				String email = rs.getString("owner_email");
				String phoneNumber =rs.getString("owner_phonenumber");
				String status = rs.getString("owner_status");
				String values[]= {s_user_id, owner_name, email, phoneNumber, pet_type, pet_gender, pet_name, pet_breed, pet_bday, status};
				model.addRow(values);
			}
			
			sql = "SELECT * from customer_shipping_verification";
			pstmt= con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int i_user_id = rs.getInt("customer_id");
				String s_user_id = String.valueOf(i_user_id);
				String user_name = rs.getString("customer_name");
				String user_address = rs.getString("customer_address");
				String user_status= rs.getString("verification_status");

				String values[]= {s_user_id,user_name,user_address,user_status};
				model2.addRow(values);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JButton accountsButton = myButton("ACCOUNTS", "");
		buttonPanel.add(accountsButton);
		accountsButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame1.setVisible(false);
				Admin_Accounts ac = new Admin_Accounts();
				try {
					ac.accounts(user_id);
				} catch (SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JButton appButton = myButton("APPOINTMENTS", "");
		buttonPanel.add(appButton);
		appButton.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				frame1.setVisible(false);
		
				Admin_Appointment nt = new Admin_Appointment();
				try {
					nt.adminAppointment(user_id);
				} catch (SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		
		JButton shoppingButton = myButton("INVENTORY", "");
		buttonPanel.add(shoppingButton);
		shoppingButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame1.setVisible(false);
				Admin_Shopping nt = new Admin_Shopping();
				try {
					nt.admin_shopping(user_id);
				} catch (SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		JButton adoptionButton = myButton("ADOPTION", "");
		buttonPanel.add(adoptionButton);
		adoptionButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame1.setVisible(false);
				Admin_Adoption ad = new Admin_Adoption();
				try {
					ad.adopt(1);
				} catch (SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		JButton medicalButton = myButton("MEDICAL", "");
		buttonPanel.add(medicalButton);
		medicalButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame1.setVisible(false);
				Admin_AddRecord aar = new Admin_AddRecord();
				try {
					aar.record(user_id);
				} catch (SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		
		frame1.setVisible(true);
	}
	
//	public static void main(String[] args) {
public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
