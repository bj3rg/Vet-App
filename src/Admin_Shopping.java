
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
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.print.attribute.AttributeSet;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class Admin_Shopping {
	private String s;
	private JFrame frame1;
	private JPanel navPanel, mainPanel, containerPanel, mainContainerPanel, tabPanel, petDataPanel;
	private JLabel vetaLabel, tabLabel;
	
	public static void main(String[] args) {
		Admin_Shopping ad = new Admin_Shopping();
		try {
			ad.admin_shopping(1);
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public JLabel shoppingLabel(String title) {
		JLabel label = new JLabel(title);
		label.setFont(new Font("SansSerif BOLD", Font.PLAIN, 16));
		label.setForeground(Color.decode("#dbdee1"));
		return label;
	}

	private JButton shoppingButton(String title, String name) {
		
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
	
	public JLabel shopLabel(String title) {
		JLabel label = new JLabel(title);
		label.setFont(new Font("SansSerif", Font.PLAIN, 18));
		label.setForeground(Color.decode("#dbdee1"));
		return label;
	}

	public void admin_shopping(int user_id) throws SQLException, IOException{
		
		String tabDescription = "SHOPPING";
		
		frame1 = new JFrame("VETA");
		frame1.setSize(1536,864);
		frame1.setResizable(false);
		frame1.setLayout(new BorderLayout());
		frame1.setLocationRelativeTo(null);
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.setVisible(true);
		
		mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBackground(Color.decode("#404249"));;
		frame1.getContentPane().add(mainPanel,BorderLayout.CENTER);
		frame1.add(mainPanel,BorderLayout.CENTER);
	
		navPanel = new JPanel(new BorderLayout());
		navPanel.setPreferredSize(new Dimension(200,764));
		navPanel.setLayout(new BorderLayout());
		navPanel.setBackground(Color.decode("#1e1f22"));
		mainPanel.add(navPanel,BorderLayout.WEST);
		
		containerPanel = new JPanel();
		containerPanel.setLayout(null);
		containerPanel.setPreferredSize(new Dimension(1236,82));
		containerPanel.setBackground(Color.decode("#404249"));
		mainPanel.add(containerPanel,BorderLayout.CENTER);
		
		//LEFT PANEL for nav
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
		tabPanel.setBounds(480,0,280,50);
		tabPanel.setBackground(Color.decode("#616463"));
		containerPanel.add(tabPanel, BorderLayout.NORTH);
		
		tabLabel = new JLabel(tabDescription);
		tabLabel.setForeground(Color.decode("#ffffff"));
		tabLabel.setFont(new Font("SansSerif Bold", Font.PLAIN, 30));
		tabPanel.add(tabLabel);
		
		// main container panel for shopping tab
		JPanel mainContainerPanel = new JPanel();
		mainContainerPanel.setBounds(20,70,1280,735);
		mainContainerPanel.setLayout(new BorderLayout());
		mainContainerPanel.setBackground(Color.decode("#313338"));
		containerPanel.add(mainContainerPanel);
		
		JPanel topPanel = new JPanel();
		topPanel.setBackground(Color.decode("#313338"));
		topPanel.setPreferredSize(new Dimension(100,30));
		topPanel.setLayout(null);
		
		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(Color.decode("#313338"));
		leftPanel.setPreferredSize(new Dimension(20,80));
		
		//Panel where add items found
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(null);
		rightPanel.setBackground(Color.decode("#313338"));
		rightPanel.setPreferredSize(new Dimension(370,80));
		
		JLabel addItem = shopLabel("INSERT ITEM");
		addItem.setBounds(110,5,150,30);
		addItem.setFont(new Font("SansSerif Bold",Font.PLAIN, 18));
		rightPanel.add(addItem);
		
		JLabel itemName = shopLabel("Name:");
		itemName.setBounds(10,45,150,30);
		rightPanel.add(itemName);

		JTextField nameField = new JTextField();
		nameField.setBounds(135,45,200,30);
		rightPanel.add(nameField);
		
		JLabel itemPurpose = shopLabel("Purpose:");
		itemPurpose.setBounds(10,85,150,30);
		rightPanel.add(itemPurpose);

		JTextField purpField = new JTextField();
		purpField.setBounds(135,85,200,30);
		rightPanel.add(purpField);
		
		JLabel itemDescription = shopLabel("Description");
		itemDescription.setBounds(10,125,150,30);
		rightPanel.add(itemDescription);
		
		JTextField descField = new JTextField();
		descField.setBounds(135,125,200,30);
		rightPanel.add(descField);
		
		JLabel itemApplied = shopLabel("Pet type:");
		itemApplied.setBounds(10,165,150,30);
		rightPanel.add(itemApplied);
		
		String[] petType = {"Cats", "Dogs", "Cats & Dogs"};
		JComboBox<String> pet_type = new JComboBox<String>(petType);
		pet_type.setBounds(135, 165, 200, 30);
		pet_type.setSelectedIndex(-1);
		rightPanel.add(pet_type);
		
		JLabel itemQuantity = shopLabel("Quantity:");
		itemQuantity.setBounds(10,205,150,30);
		rightPanel.add(itemQuantity);
		
		String[] itemQuanArray = {"5", "10", "15", "20", "25", "30"};
		JComboBox<String> quantityItem = new JComboBox<String>(itemQuanArray);
		quantityItem.setBounds(135, 205, 200, 30);
		quantityItem.setSelectedIndex(-1);
		rightPanel.add(quantityItem);
		
		JLabel itemPrice= shopLabel("Price:");
		itemPrice.setBounds(10,245,150,30);
		rightPanel.add( itemPrice);
		
		JTextField priceField = new JTextField();
		priceField.setBounds(135,245,200,30);
		rightPanel.add(priceField);
		
		String[] itemTax= {".15", ".16", ".17",".18", ".19",".20"};
		JComboBox<String> taxForItem = new JComboBox<String>(itemTax);
		taxForItem.setBounds(135, 285, 200, 30);
		taxForItem.setSelectedIndex(-1);
		rightPanel.add(taxForItem);
		
		JLabel taxLabel= shopLabel("Markup:");
		taxLabel.setBounds(10,285,150,30);
		rightPanel.add(taxLabel);
		
		JLabel itemPic= shopLabel("Image:");
		itemPic.setBounds(10,325,150,30);
		rightPanel.add( itemPic);
		
		JButton browsePhoto = new JButton("BROWSE");
		browsePhoto.setBounds(135,325,200,30);
		browsePhoto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser  fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				FileNameExtensionFilter filter = new FileNameExtensionFilter("*.IMAGE", "jpg", "gif", "png", "jpeg");
				fileChooser.addChoosableFileFilter(filter);
				int result = fileChooser.showSaveDialog(null);
				if(result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					String path = selectedFile.getAbsolutePath();
					ImageIcon imageIcon = new ImageIcon(path);
					Image img_cat = imageIcon.getImage().getScaledInstance(230,270,Image.SCALE_SMOOTH);
					browsePhoto.setIcon(new ImageIcon(img_cat));
					s = path;
				}else if (result == JFileChooser.CANCEL_OPTION) {
					System.out.println("No data inserted!");
				}
			}
		});
		rightPanel.add(browsePhoto);
		
		//button for adding item in shopping_item
		JButton addButton = shoppingButton("ADD ITEM","D:\\3rd Year BSCPE (2nd SEMESTER)\\Software Design\\ICONS VETA\\icons8-add-100.png");
		addButton.setBounds(135,365,200,30);
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String item_name = nameField.getText();
				String item_description = descField.getText();
				String item_applied = (String) pet_type.getSelectedItem();
				String item_purpose = purpField.getText();
				String tax_Item = (String) taxForItem.getSelectedItem();
				String quanCombo = (String) quantityItem.getSelectedItem();
				
				String item_price = priceField.getText();
				float price = 0;
				if (browsePhoto.getIcon()==null|| item_name.isEmpty() || item_description.isEmpty() || quantityItem.getSelectedItem()==null || pet_type.getSelectedItem()==null|| item_purpose.isEmpty() || taxForItem.getSelectedItem()==null) {
				    JOptionPane.showMessageDialog(null, "Please enter valid input values.");
				    return;
				}		
				try {
				    //item price
					price = Float.parseFloat(item_price);
				    
				} catch (NumberFormatException e1) {
				    // Handle the error, for example:
					priceField.setText("");
				    JOptionPane.showMessageDialog(null, "Invalid input for price!");
				    return; // Exit the method or show an error message to the user
				}	
				int quantity = Integer.parseInt(quanCombo);
				//item tax
			    float taxToFloat = Float.parseFloat(tax_Item);   
			    float itemTaxValue = price-(price*taxToFloat);	    
			    //tax per item
			    float taxPerItem=  price - itemTaxValue;	    
			    // price + tax value
			    float totalPrice = taxPerItem + price;
				try {
					InputStream is = new FileInputStream(new File(s));//allows blob image to be save in db
				    Class.forName("com.mysql.cj.jdbc.Driver");
				    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylogindb","root","burog181");
				    PreparedStatement pstmt = con.prepareStatement("INSERT INTO shopping_item SET item_name=?, item_description=?, item_applied=?, item_purpose=?, quantity=?, price=?, image=?, item_tax_percent=?, item_tax_value=?");
				    pstmt.setString(1, item_name);
				    pstmt.setString(2, item_description);
				    pstmt.setString(3, item_applied);
				    pstmt.setString(4, item_purpose);
				    pstmt.setInt(5, quantity);
				    pstmt.setFloat(6, totalPrice);
				    pstmt.setBlob(7, is);
				    pstmt.setFloat(8, taxToFloat);
				    pstmt.setFloat(9, taxPerItem);
				    int rowsInserted = pstmt.executeUpdate();
				    if(rowsInserted > 0) {
				        JOptionPane.showMessageDialog(null, "Item succesfully added to shopping item list!");
				    	System.out.println("A new item was inserted successfully!");
				    	nameField.setText("");
						purpField.setText("");
						pet_type.setSelectedIndex(-1);
						quantityItem.setSelectedIndex(-1);
						taxForItem.setSelectedIndex(-1);
						priceField.setText("");
						descField.setText("");
						browsePhoto.setIcon(null);
				        
				    }
				} catch (ClassNotFoundException | SQLException | FileNotFoundException e1) {
				    e1.printStackTrace();
				    JOptionPane.showMessageDialog(null, "Enter valid inputs!");
				    
				}
			}
		});
		rightPanel.add(addButton);
		
		//clears the input items
		JButton clearButton = shoppingButton("CLEAR","D:\\3rd Year BSCPE (2nd SEMESTER)\\Software Design\\ICONS VETA\\icons8-clear-symbol-100.png");
		clearButton.setBounds(10,365,100,30);
		clearButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				nameField.setText("");
				purpField.setText("");
				pet_type.setSelectedIndex(-1);
				quantityItem.setSelectedIndex(-1);
				taxForItem.setSelectedIndex(-1);
				priceField.setText("");
				descField.setText("");
				browsePhoto.setIcon(null);
			}
		});
		rightPanel.add(clearButton);
		
		JLabel updateItem = shopLabel("ADD PRODUCT STOCKS");
		updateItem.setBounds(60,400,250,30);
		updateItem.setFont(new Font("SansSerif Bold",Font.PLAIN, 18));
		rightPanel.add(updateItem);
		
		JLabel itemID = shopLabel("Product ID:");
		itemID.setBounds(10,430,150,30);
		rightPanel.add(itemID);

		JTextField idField = new JTextField();
		idField.setBounds(135,430,200,30);
		rightPanel.add(idField);
		
		JLabel itemStocks= shopLabel("Stocks:");
		itemStocks.setBounds(10,470,150,30);
		rightPanel.add(itemStocks);

		JTextField stocksField = new JTextField();
		stocksField.setBounds(135,470,200,30);
		rightPanel.add(stocksField);
		
		JButton updateButton = shoppingButton("ADD STOCKS","D:\\3rd Year BSCPE (2nd SEMESTER)\\Software Design\\ICONS VETA\\icons8-add-100.png");
		updateButton.setBounds(135,510,200,30);
		updateButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String item_id = idField.getText();
				String item_stocks = stocksField.getText();

				if(item_id.isEmpty() || !item_id.matches("\\d+") || item_stocks.isEmpty() || !item_stocks.matches("\\d+")) {
				    JOptionPane.showMessageDialog(null, "Please enter valid numeric input values for item ID and item stock.");
				    idField.setText("");
			    	stocksField.setText("");
				    return;
				}
				int itemStocks= Integer.parseInt(item_stocks);
				int itemID = Integer.parseInt(item_id);
				//item tax

				try {
				    Class.forName("com.mysql.cj.jdbc.Driver");
				    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylogindb","root","burog181");
				    String sql = "SELECT * FROM shopping_item WHERE item_id=?";
				    PreparedStatement pstmt = con.prepareStatement(sql);
				    pstmt.setInt(1, itemID);
				    ResultSet rs = pstmt.executeQuery();
				    if(rs.next()) {
				    	int item_current_quantity = rs.getInt("quantity");
				    	String item_name = rs.getString("item_name");
				    	int updated_stocks= item_current_quantity+itemStocks;
				    	sql = "UPDATE shopping_item SET quantity=? WHERE item_id=?";
				    	pstmt = con.prepareStatement(sql);
				    	pstmt.setInt(1, updated_stocks);
				    	pstmt.setInt(2, itemID);
				    	pstmt.executeUpdate();
				    	JOptionPane.showMessageDialog(null, itemStocks+" stocks added to "+item_name+".");
				    	frame1.setVisible(false);
				    	admin_shopping(user_id);
				    }else {
				    	JOptionPane.showMessageDialog(null, "Item not found in shopping items");
				    	idField.setText("");
				    	stocksField.setText("");
				    }
				} catch (ClassNotFoundException | SQLException | IOException e1) {
				    e1.printStackTrace();
				    JOptionPane.showMessageDialog(null, "Enter valid inputs!");
				    
				}
			}
		});
		rightPanel.add(updateButton);
		
		//clears the input items
		JButton clearButton2 = shoppingButton("CLEAR","D:\\3rd Year BSCPE (2nd SEMESTER)\\Software Design\\ICONS VETA\\icons8-clear-symbol-100.png");
		clearButton2.setBounds(10,510,100,30);
		clearButton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				idField.setText("");
				stocksField.setText("");
			}
		});
		rightPanel.add(clearButton2);
		
		JLabel shippingLabel = shopLabel("UPDATE SHIPPING STATUS");
		shippingLabel.setBounds(60,540,250,30);
		shippingLabel.setFont(new Font("SansSerif Bold",Font.PLAIN, 18));
		rightPanel.add(shippingLabel);
		
		JLabel userID = shopLabel("Buyer ID:");
		userID.setBounds(10,570,150,30);
		rightPanel.add(userID);

		JTextField idField2 = new JTextField();
		idField2.setBounds(135,570,200,30);
		rightPanel.add(idField2);
		
		JLabel statusChange = shopLabel("Order ID:");
		statusChange.setBounds(10,610,150,30);
		rightPanel.add(statusChange);
		
		JTextField idField22 = new JTextField();
		idField22.setBounds(135,610,200,30);
		rightPanel.add(idField22);
		
		JButton statusButton = shoppingButton("UPDATE STATUS","D:\\3rd Year BSCPE (2nd SEMESTER)\\Software Design\\ICONS VETA\\icons8-update-100.png");
		statusButton.setBounds(135,650,200,30);
		statusButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				String s_order_id = idField22.getText();
				String s_user_id = idField2.getText();
				
				if(s_order_id.isEmpty() || s_user_id.isEmpty()) {
				    return;
				}
				int user_id2 = Integer.parseInt(s_user_id);
				int order_id = Integer.parseInt(s_order_id);
				try {
				    Class.forName("com.mysql.cj.jdbc.Driver");
				    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylogindb","root","burog181");
				    String sql = "SELECT * FROM billing WHERE user_id=? AND order_status=?";
				    PreparedStatement pstmt = con.prepareStatement(sql);
				    pstmt.setInt(1, user_id2);
				    pstmt.setString(2, "For Shipping");
				    ResultSet rs = pstmt.executeQuery();
				    if(rs.next()) {
				    	String status = "Shipped";
				    	String retStatus = rs.getString("order_status");
				    	if(retStatus.equals("Shipped")) {
				    		JOptionPane.showMessageDialog(null, "Order Already shipped");
				    	}else {
				    		String user_name = rs.getString("user_name");
					    	sql = "UPDATE billing SET order_status=? WHERE user_id=? AND billing_id=?";
					    	pstmt = con.prepareStatement(sql);
					    	pstmt.setString(1, status);
					    	pstmt.setInt(2, user_id2);
					    	pstmt.setInt(3, order_id);
					    	pstmt.executeUpdate();
					    	JOptionPane.showMessageDialog(null, "User "+user_id2 +", "+user_name+ " order status changed to Shipped.");
					    	frame1.setVisible(false);
					    	admin_shopping(user_id);
				    	}
				    }else {
				    	JOptionPane.showMessageDialog(null, "No data found for this user!");
				    	idField2.setText("");
				    	idField22.setText("");
				    }
				} catch (ClassNotFoundException | SQLException | IOException e1) {
				    e1.printStackTrace();
				    JOptionPane.showMessageDialog(null, "Enter valid inputs!");
				    
				}
			}
		});
		rightPanel.add(statusButton);
		
		//clears the input items
		JButton clearButton3 = shoppingButton("CLEAR","D:\\3rd Year BSCPE (2nd SEMESTER)\\Software Design\\ICONS VETA\\icons8-clear-symbol-100.png");
		clearButton3.setBounds(10,650,100,30);
		clearButton3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				idField2.setText("");
		    	idField22.setText("");
			}
		});
		rightPanel.add(clearButton3);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(Color.decode("#313338"));
		centerPanel.setLayout(null);
		
		JLabel shoppingLabel = shoppingLabel("Products Listed on Shop".toUpperCase());
		shoppingLabel.setBounds(350,0,250,30);
		centerPanel.add(shoppingLabel);

		JLabel boughtLabel = shoppingLabel("Items Inventory".toUpperCase());
		boughtLabel.setBounds(380,220,250,30);
		centerPanel.add(boughtLabel);

		JLabel billingLabel = shoppingLabel("Customer Orders".toUpperCase());
		billingLabel.setBounds(375,470,250,30);
		centerPanel.add(billingLabel);

		JPanel shopping_item_panel = new JPanel();
		shopping_item_panel.setBounds(20,30, 850,190);
		shopping_item_panel.setBackground(Color.decode("#4e4f50"));
		centerPanel.add(shopping_item_panel);
		
		String[] columnTitle1 = {"ID","Name", "Stocks Available","Margin %", "Margin Value", "Price"};
		DefaultTableModel model = new DefaultTableModel();
		JTable shoppingTable = new JTable(model);
		JScrollPane spane = new JScrollPane(shoppingTable);
		shoppingTable.setOpaque(true);
		shoppingTable.setBackground(Color.decode("#f2f2f2"));
		shoppingTable.setRowHeight(20);
		shoppingTable.setEnabled(false);
		shoppingTable.setPreferredScrollableViewportSize(new Dimension(830,170));
		shoppingTable.getTableHeader().setBackground(new Color(30,30,30));
		shoppingTable.getTableHeader().setForeground(new Color(200,200,200));
		
		for(int i = 0; i <= columnTitle1.length-1; i++) {
			model.addColumn(columnTitle1[i]);
		}
		//Change color of the table cell by odd and even
//		 Create a custom cell renderer for the first row
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
		for (int i = 0; i < shoppingTable.getColumnCount(); i++) {
		    shoppingTable.getColumnModel().getColumn(i).setCellRenderer(customRenderer);
		}
		// Calculate the preferred width of each column
		for (int column = 0; column < shoppingTable.getColumnCount(); column++) {
		    int maxWidth = 0;
		    TableColumn tableColumn = shoppingTable.getColumnModel().getColumn(column);
		    for (int row = 0; row < shoppingTable.getRowCount(); row++) {
		        TableCellRenderer cellRenderer = shoppingTable.getCellRenderer(row, column);
		        Object value = shoppingTable.getValueAt(row, column);
		        Component cellComponent = cellRenderer.getTableCellRendererComponent(shoppingTable, value, false, false, row, column);
		        maxWidth = Math.max(maxWidth, cellComponent.getPreferredSize().width);
		    }
		    tableColumn.setPreferredWidth(maxWidth);
		}
		shopping_item_panel.add(new JScrollPane(shoppingTable));
		
		JPanel bought_item_panel = new JPanel();
		bought_item_panel.setBounds(20,250, 850,190);
		bought_item_panel.setBackground(Color.decode("#4e4f50"));
		centerPanel.add(bought_item_panel);
		
		String[] columnTitle2 = {"Buyer ID","Item ID","Name", "Quantity","Bought Date",  "Margin","Price" };
		DefaultTableModel model2 = new DefaultTableModel();
		JTable boughtTable = new JTable(model2);
		JScrollPane spane2 = new JScrollPane(boughtTable);
		boughtTable.setOpaque(true);
		boughtTable.setBackground(Color.decode("#f2f2f2"));
		boughtTable.setRowHeight(20);
		boughtTable.setEnabled(false);
		boughtTable.setPreferredScrollableViewportSize(new Dimension(830,170));
		boughtTable.getTableHeader().setBackground(new Color(30,30,30));
		boughtTable.getTableHeader().setForeground(new Color(200,200,200));
		
		for(int i = 0; i <= columnTitle2.length-1; i++) {
			model2.addColumn(columnTitle2[i]);
		}
		//Change color of the table cell by odd and even
//		 Create a custom cell renderer for the first row
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
		for (int i = 0; i < boughtTable.getColumnCount(); i++) {
		    boughtTable.getColumnModel().getColumn(i).setCellRenderer(customRenderer2);
		}
		// Calculate the preferred width of each column
		for (int column = 0; column < boughtTable.getColumnCount(); column++) {
		    int maxWidth = 0;
		    TableColumn tableColumn = boughtTable.getColumnModel().getColumn(column);
		    for (int row = 0; row < boughtTable.getRowCount(); row++) {
		        TableCellRenderer cellRenderer = boughtTable.getCellRenderer(row, column);
		        Object value = boughtTable.getValueAt(row, column);
		        Component cellComponent = cellRenderer.getTableCellRendererComponent(boughtTable, value, false, false, row, column);
		        maxWidth = Math.max(maxWidth, cellComponent.getPreferredSize().width);
		    }
		    tableColumn.setPreferredWidth(maxWidth);
		}
		bought_item_panel.add(new JScrollPane(boughtTable));
		
		JPanel billing_panel = new JPanel();
		billing_panel.setBounds(20,500, 850,190);
		billing_panel.setBackground(Color.decode("#4e4f50"));
		centerPanel.add(billing_panel);
		
		String[] columnTitle3 = {"Order ID","Buyer ID", "Buyer","Address", "Phone number", "Order Date", "Total", "Status"};
		DefaultTableModel model3 = new DefaultTableModel();
		JTable billingTable = new JTable(model3);
		JScrollPane spane3 = new JScrollPane(billingTable);
		billingTable.setOpaque(true);
		billingTable.setBackground(Color.decode("#f2f2f2"));
		billingTable.setRowHeight(20);
		billingTable.setEnabled(false);
		billingTable.setPreferredScrollableViewportSize(new Dimension(830,170));
		billingTable.getTableHeader().setBackground(new Color(30,30,30));
		billingTable.getTableHeader().setForeground(new Color(200,200,200));
		
		for(int i = 0; i <= columnTitle3.length-1; i++) {
			model3.addColumn(columnTitle3[i]);
		}
		//Change color of the table cell by odd and even
//		 Create a custom cell renderer for the first row
		DefaultTableCellRenderer customRenderer3 = new DefaultTableCellRenderer() {
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
		for (int i = 0; i < billingTable.getColumnCount(); i++) {
		    billingTable.getColumnModel().getColumn(i).setCellRenderer(customRenderer3);
		}
		// Calculate the preferred width of each column
		for (int column = 0; column < billingTable.getColumnCount(); column++) {
		    int maxWidth = 0;
		    TableColumn tableColumn = billingTable.getColumnModel().getColumn(column);
		    for (int row = 0; row < billingTable.getRowCount(); row++) {
		        TableCellRenderer cellRenderer = billingTable.getCellRenderer(row, column);
		        Object value = billingTable.getValueAt(row, column);
		        Component cellComponent = cellRenderer.getTableCellRendererComponent(billingTable, value, false, false, row, column);
		        maxWidth = Math.max(maxWidth, cellComponent.getPreferredSize().width);
		    }
		    tableColumn.setPreferredWidth(maxWidth);
		}
		billing_panel.add(new JScrollPane(billingTable));
		
		JPanel showItemPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20,30));
		showItemPanel.setBackground(Color.decode("#4e4f50"));
		centerPanel.add(showItemPanel);
	
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBackground(Color.decode("#313338"));
		bottomPanel.setLayout(null);
		bottomPanel.setPreferredSize(new Dimension(100,20));
		
		mainContainerPanel.add(topPanel, BorderLayout.NORTH);
		mainContainerPanel.add(leftPanel, BorderLayout.WEST);
		mainContainerPanel.add(rightPanel, BorderLayout.EAST);
		mainContainerPanel.add(centerPanel, BorderLayout.CENTER);
		mainContainerPanel.add(bottomPanel, BorderLayout.SOUTH);
		
		float total_inventory = 0;
		float total_revenue = 0;
		
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylogindb","root","burog181");
			String sql = "SELECT item_id, item_name, quantity, price, item_tax_percent, item_tax_value FROM shopping_item";
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				
				int i_item_id = rs.getInt("item_id");
				int i_quantity = rs.getInt("quantity");
				float f_price = rs.getFloat("price");
				float f_item_tax_percent = rs.getFloat("item_tax_percent");
				float f_item_tax_value = rs.getFloat("item_tax_value");
				String item_name = rs.getString("item_name");
				String item_id = String.valueOf(i_item_id);
				String item_quantity = String.valueOf(i_quantity);
				String price = String.valueOf(f_price);
				String tax_percent = String.valueOf(f_item_tax_percent);
				String tax_value = String.valueOf(f_item_tax_value);
				String values[]= {item_id , item_name, item_quantity, tax_percent, tax_value, price};
				model.addRow(values);
			}
			
			sql = "SELECT * from shopped_item_history";
			pstmt= con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int i_item_id = rs.getInt("item_id");
				int i_quantity = rs.getInt("item_quantity");
				float f_item_tax = rs.getFloat("item_tax");
				int i_buyer_id= rs.getInt("buyer_id");
				float f_price = rs.getFloat("item_price");
				String item_name = rs.getString("item_name");
				String bought_date = rs.getString("item_bought_date");
				String item_id = String.valueOf(i_item_id);
				String bought_quantity = String.valueOf(i_quantity);
				String tax = String.valueOf(f_item_tax);
				String buyer_id = String.valueOf(i_buyer_id);
				String price = String.valueOf(f_price);
				String values[]= {buyer_id, item_id, item_name, bought_quantity, bought_date, tax, price};
				model2.addRow(values);
				total_inventory = total_inventory+f_price;
				total_revenue = total_revenue + f_item_tax;
			}
			
			sql = "SELECT * from billing";
			pstmt= con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int i_order_id = rs.getInt("billing_id");
				int i_user_id= rs.getInt("user_id");
				float total_price = rs.getFloat("order_total");
				
				String user_pnumber = rs.getString("user_phonenumber");
				String user_address = rs.getString("user_address");
				String user_name = rs.getString("user_name");
				String status = rs.getString("order_status");
				String order_date = rs.getString("order_date");
				String order_id = String.valueOf(i_order_id);
				String buyer_id = String.valueOf(i_user_id);
				String price = String.valueOf(total_price);
				String values[]= {order_id, buyer_id, user_name, user_address, user_pnumber, order_date, price, status };
				model3.addRow(values);
			}
			
			System.out.println(total_inventory);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		JLabel shoppingLabel = shoppingLabel("Products Listed on Shop");
//		shoppingLabel.setBounds(350,0,250,30);
//		centerPanel.add(shoppingLabel);
		
		JLabel totalboughtLabel = shoppingLabel("Total Sales Revenue: Php." + total_inventory);
		totalboughtLabel.setBounds(150,445,450,30);
		centerPanel.add(totalboughtLabel);
		
		JLabel revenueLabel = shoppingLabel("Total Profit: Php." + total_revenue);
		revenueLabel.setBounds(550,445,450,30);
		centerPanel.add(revenueLabel);
		
		
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
				// TODO Auto-generated method stub'
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

