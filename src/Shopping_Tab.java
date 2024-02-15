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
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class Shopping_Tab {
	
	private JFrame frame1;
	private JPanel navPanel, mainPanel, containerPanel, mainContainerPanel, tabPanel, verifyFramePanel;
	private JLabel vetaLabel, tabLabel;
	private String s1;
	private String s2;
	public String order_status = "For Shipping";
//	public static void main(String[] args) {
//		Shopping_Tab nt = new Shopping_Tab();
//		nt.viewHistory(6);
//	}

	public JLabel adoptLabel(String title) {
		JLabel label = new JLabel(title);
		label.setFont(new Font("SansSerif", Font.PLAIN, 18));
		label.setForeground(Color.decode("#dbdee1"));
		return label;
	}
	
	private JButton adoptButton(String title, String name) {
		
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
	
	private JPanel shoppingItems(String item_name,String item_purpose, String item_description, String pet_applied, int price, int quantity, Blob image, int user_id) throws SQLException, IOException {
		 
		//  String pet_age, String pet_gender, Image pet_image
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 100,10));
		panel.setPreferredSize(new Dimension(230,385));
		panel.setBackground(Color.decode("#1e1f22"));
		
		Border border = BorderFactory.createLineBorder(Color.decode("#1e1f22"), 5);
		JLabel imageContainer = new JLabel("");
		InputStream inputStream = image.getBinaryStream();
        byte[] imageBytes = inputStream.readAllBytes();
        ImageIcon imageIcon = new ImageIcon(imageBytes);
        Image img = imageIcon.getImage().getScaledInstance(130,170,Image.SCALE_SMOOTH);
		imageContainer.setBackground(Color.red);
		imageContainer.setBorder(border);
		imageContainer.setIcon(new ImageIcon(img));
		imageContainer.setPreferredSize(new Dimension(130,150));
		panel.add(imageContainer);
		
		JLabel itemName = new JLabel(item_name);
		itemName.setForeground(Color.white);
		panel.add(itemName);
		
		JLabel itemPurpose = new JLabel(item_purpose);
		itemPurpose.setForeground(Color.white);
		panel.add(itemPurpose);
		
		
		String priceString = String.valueOf(price);
		String priceConcat ="Php. "+priceString;
		JLabel showPrice =new JLabel(priceConcat);
		showPrice.setFont(new Font("SansSerif BOLD",Font.PLAIN,18));
		showPrice.setForeground(Color.white);
		panel.add(showPrice);
		
		String quantityString = String.valueOf(quantity);
		String quanConcat ="Items Left: " +quantityString;
		JLabel showQuan =new JLabel(quanConcat);
		showQuan.setForeground(Color.white);
		panel.add(showQuan);
		
		String itemQuantity[]= {"1","2","3","4","5"};
		JComboBox<String> comboQuantity = new JComboBox<String>(itemQuantity);
		comboQuantity.setSelectedIndex(-1);
		comboQuantity.setPreferredSize(new Dimension(150,25));
		panel.add(comboQuantity);

		JButton addToCart = adoptButton("ADD TO CART","");
		addToCart.setPreferredSize(new Dimension(140,25));
		addToCart.setFont(new Font("SansSerif BOLD", Font.PLAIN,14));
		addToCart.addActionListener(new ActionListener() {
			
			@SuppressWarnings("resource")
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String getQuantity = (String) comboQuantity.getSelectedItem();
				if(comboQuantity.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "Pick product quantity");
					return;
				}
				int quantity = Integer.parseInt(getQuantity);
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylogindb","root","burog181");
					PreparedStatement pstmt = con.prepareStatement("SELECT * FROM shopping_item WHERE item_name=?");
					pstmt.setString(1, item_name);
					ResultSet rs = pstmt.executeQuery();
					if(rs.next()) {
						int itemCurrentQuantity = rs.getInt("quantity");
						int item_id = rs.getInt("item_id");
						float price = rs.getFloat("price");
						float firstSubtotal = quantity*price;
						if(itemCurrentQuantity > quantity) {
							String sql = "SELECT * FROM customer_shopping_cart WHERE item_name=? AND user_id=?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, item_name);
							pstmt.setInt(2, user_id);
							rs = pstmt.executeQuery();
							if(rs.next()) {
								int recentQuantity = rs.getInt("item_quantity");
								int updatedQuantity = recentQuantity+quantity;
								float updatedSubtotal = updatedQuantity*price;
								
								sql = "UPDATE customer_shopping_cart SET item_quantity=?, item_subtotal=? WHERE user_id=? AND item_id=?";
								pstmt = con.prepareStatement(sql);
								pstmt.setInt(1, updatedQuantity);
								pstmt.setFloat(2, updatedSubtotal);
								pstmt.setInt(3, user_id);
								pstmt.setInt(4, item_id);
								
								pstmt.executeUpdate();
								JOptionPane.showMessageDialog(null, "Cart updated succesfully!");
								comboQuantity.setSelectedIndex(-1);
							}else {
								sql = "INSERT INTO customer_shopping_cart SET user_id=?, item_id=?, item_name=?, item_quantity=?, item_price=?, item_subtotal=?";
								pstmt = con.prepareStatement(sql);
								pstmt.setInt(1, user_id );
								pstmt.setInt(2, item_id);
								pstmt.setString(3, item_name);
								pstmt.setInt(4, quantity);
								pstmt.setFloat(5, price);
								pstmt.setFloat(6, firstSubtotal);
								pstmt.executeUpdate();
								JOptionPane.showMessageDialog(null, "Item added to cart succesfully!");
								comboQuantity.setSelectedIndex(-1);
							}
	
						}
						frame1.setVisible(false);
						shopping(user_id);
					}
				
				} catch (ClassNotFoundException | SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		panel.add(addToCart);
		
		JButton viewDetail = adoptButton("VIEW DETAIL","");
		viewDetail.setFont(new Font("SansSerif BOLD", Font.PLAIN,14));
		viewDetail.setPreferredSize(new Dimension(140,25));
		viewDetail.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFrame detailFrame = new JFrame("Item Detail");
				detailFrame.setSize(800,800);
				detailFrame.setLocationRelativeTo(null);
				detailFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				detailFrame.setVisible(true);
				detailFrame.setResizable(false);
				
				JPanel detailPanel = new JPanel();
				detailPanel.setBackground(Color.decode("#1e1f22"));
				detailPanel.setLayout(null);
				detailFrame.add(detailPanel);
				
				JPanel imagePanel = new JPanel();
				imagePanel.setBounds(250,50,300,400);
				imagePanel.setBackground(Color.decode("#1e1f22"));
				detailPanel.add(imagePanel);
				
				JLabel showpetImage = adoptLabel("");
				InputStream inputStream;
				try {
					inputStream = image.getBinaryStream();
					byte[] imageBytes = inputStream.readAllBytes();
				} catch (SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	            ImageIcon imageIcon = new ImageIcon(imageBytes);
	            Image img = imageIcon.getImage().getScaledInstance(300,400,Image.SCALE_SMOOTH);
				showpetImage.setIcon(new ImageIcon(img));
				imagePanel.add(showpetImage);
				
				JPanel detailContainer = new JPanel();
				detailContainer.setLayout(null);
				detailContainer.setBounds(90,450,600,600);
				detailContainer.setBackground(Color.decode("#1e1f22"));
				detailPanel.add(detailContainer);
				
				String nameConcat = "Item Name: "+item_name;
				JLabel showItemName = adoptLabel(nameConcat);
				showItemName.setBounds(20,5,550,60);
				detailContainer.add(showItemName);
				
				String purposeConcat ="Item Purpose: "+item_purpose;
				JLabel showPurpose = adoptLabel(purposeConcat);
				showPurpose.setBounds(20,50,550,60);
				detailContainer.add(showPurpose);
				
				String descriptionConcat ="Description: "+item_description;
				JLabel showDescription = adoptLabel(descriptionConcat);
				showDescription.setBounds(20,95,550,60);
				detailContainer.add(showDescription);
				
				String appliedConcat ="For: "+pet_applied;
				JLabel showApplied = adoptLabel(appliedConcat);
				showApplied.setBounds(20,140,550,60);
				detailContainer.add(showApplied);
				
				String quantityString = String.valueOf(quantity);
				String priceString = String.valueOf(price);
				String priceConcat ="Item Price: "+priceString+ " | Items Left: " +quantityString;
				JLabel showPrice = adoptLabel(priceConcat);
				showPrice.setBounds(20,185,550,60);
				detailContainer.add(showPrice);
			}
		});
		panel.add(viewDetail);
		return panel;	
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
	
	public void viewHistory(int user_id) {
		JFrame frame1 = new JFrame("Pet Medical Records");
		frame1.setSize(700,350);
		frame1.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame1.setLayout(null);
		frame1.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10,10,665,290);
		panel.setBackground(Color.decode("#313338"));
		panel.setLayout(null);
		frame1.add(panel);
		
		JLabel allRecords = shopLabel("ORDER HISTORY");
		allRecords.setBounds(250,0,350,30);
		allRecords.setFont(new Font("SansSerif Bold", Font.PLAIN, 18));
		panel.add(allRecords);
		
		JPanel recordPanel = new JPanel();
		recordPanel.setBounds(35,35,600,225);
		recordPanel.setBackground(Color.decode("#4e4f50"));
		panel.add(recordPanel);
		// create a scroll pane and add the recordPanel to it
		JScrollPane scrollPane = new JScrollPane(recordPanel);
		// set the size and position of the scroll pane
		scrollPane.setBounds(35, 35, 600, 225);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		// add the scroll pane to the main panel
		panel.add(scrollPane);
		
		String orderHeaders[] = {"Order ID","Order Date","Order Status","Total Price"};
		DefaultTableModel model = new DefaultTableModel();
		JTable ordersTable = new JTable(model);
		ordersTable.setBackground(Color.decode("#f2f2f2"));
		ordersTable.setRowHeight(20);
		ordersTable.setEnabled(false);
		ordersTable.setPreferredScrollableViewportSize(new Dimension(580,325));
		ordersTable.getTableHeader().setBackground(new Color(30,30,30));
		ordersTable.getTableHeader().setForeground(new Color(200,200,200));	
		
		model.addColumn(orderHeaders[0]);
		model.addColumn(orderHeaders[1]);
		model.addColumn(orderHeaders[2]);
		model.addColumn(orderHeaders[3]);
		
		// Create a custom cell renderer for the first row
				DefaultTableCellRenderer customRenderer = new DefaultTableCellRenderer() {
				    @Override
				    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
				        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				        if (row %2 == 0) { // check if this is the first row
				            c.setBackground(Color.decode("#2f2f2f")); // set the background color
				            c.setForeground(Color.white);
				        }else {
				        	c.setBackground(Color.decode("#555555"));
				        }
				        return c;
				    }
				};

				// Set the cell renderer for all cells in the first row
				for (int i = 0; i < ordersTable.getColumnCount(); i++) {
				    ordersTable.getColumnModel().getColumn(i).setCellRenderer(customRenderer);
				}
				recordPanel.add(new JScrollPane(ordersTable));
				
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylogindb","root","burog181");
			String sql = "SELECT * From billing WHERE user_id=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, user_id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int get_billing_id = rs.getInt("billing_id");
				float get_order_total = rs.getFloat("order_total");
				String billing_id = String.valueOf(get_billing_id);
				String order_total = String.valueOf(get_order_total);
				String date = rs.getString("order_date");
				String order_status = rs.getString("order_status");
				String[] order_history_list = {billing_id,date,order_status,order_total};
				model.addRow(order_history_list);
			}

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		frame1.setVisible(true);
	}
	
	public void shopping(int user_id) throws SQLException, IOException{
		
		 Date currentDate = new Date();
	     SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	     String formattedDate = dateFormat.format(currentDate);
		
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
		navPanel.setPreferredSize(new Dimension(300,864));
		navPanel.setLayout(new BorderLayout());
		navPanel.setBackground(Color.decode("#1e1f22"));
		mainPanel.add(navPanel,BorderLayout.WEST);
		
		containerPanel = new JPanel();
		containerPanel.setLayout(null);
		containerPanel.setPreferredSize(new Dimension(1236,82));
		containerPanel.setBackground(Color.decode("#404249"));
		mainPanel.add(containerPanel,BorderLayout.EAST);
		
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
		
		panel1.setPreferredSize(new Dimension(50,50));
		panel2.setPreferredSize(new Dimension(100,170));
		buttonPanel.setPreferredSize(new Dimension(100,200));
		buttonPanel.setBackground(Color.decode("#1e1f22"));
		panel4.setPreferredSize(new Dimension(100,200));
		panel5.setPreferredSize(new Dimension(50,50));
		
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

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

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
		mainContainerPanel.setBounds(33,70,1180,735);
		mainContainerPanel.setLayout(new BorderLayout());
		mainContainerPanel.setBackground(Color.decode("#313338"));
		containerPanel.add(mainContainerPanel);
		
		JPanel topPanel = new JPanel();
		topPanel.setBackground(Color.decode("#313338"));
		topPanel.setPreferredSize(new Dimension(200,70));
		topPanel.setLayout(null);
		
		JLabel shopNow = shopLabel("SHOP YOUR PET NEEDS HERE!");
		shopNow.setFont(new Font("SansSerif Bold", Font.PLAIN, 18));
		shopNow.setBounds(450,18,550,30);
		topPanel.add(shopNow);
		
		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(Color.decode("#313338"));
		leftPanel.setPreferredSize(new Dimension(20,80));
		
		JPanel rightPanel = new JPanel();
		rightPanel.setBackground(Color.decode("#313338"));
		rightPanel.setPreferredSize(new Dimension(20,20));
		rightPanel.setLayout(null);		
		
		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(Color.decode("#313338"));
		centerPanel.setLayout(new BorderLayout());
		
		JPanel showItemPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 30,30));
		showItemPanel.setBackground(Color.decode("#4e4f50"));
		centerPanel.add(showItemPanel);
		
		JScrollPane scrollPane = new JScrollPane(showItemPanel);
		showItemPanel.setPreferredSize(new Dimension(2000,100));
		scrollPane.setPreferredSize(new Dimension(1255,600));
		centerPanel.add(scrollPane, BorderLayout.WEST);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylogindb","root","burog181");
			PreparedStatement pstmt = con.prepareStatement("SELECT item_name, item_purpose, item_description, item_applied, quantity, price, image FROM shopping_item");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String item_name = rs.getString("item_name");
				String item_purpose = rs.getString("item_purpose");
				String item_description = rs.getString("item_description");
				String item_applied = rs.getString("item_applied");
				int quantity = rs.getInt("quantity");
				int price = rs.getInt("price");
				Blob image = rs.getBlob("image");
				showItemPanel.add(shoppingItems(item_name, item_purpose, item_description, item_applied, price, quantity, image, user_id));
			}
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBackground(Color.decode("#313338"));
		bottomPanel.setLayout(null);
		bottomPanel.setPreferredSize(new Dimension(100,200));
		
		JLabel cartLabel = shopLabel("ITEMS ON CART");
		cartLabel.setBounds(305,5,150,30);
		cartLabel.setFont(new Font("SansSerif BOLD",Font.PLAIN,18));
		cartLabel.setForeground(Color.decode("#dbdee1"));
		bottomPanel.add(cartLabel);
		
		JPanel cartPanel = new JPanel();
		cartPanel.setBounds(20,30,720,140);
		cartPanel.setBackground(Color.decode("#313338"));
		bottomPanel.add(cartPanel);
		
		String columnName[]= {"ITEM NO.","ITEM ID","ITEM NAME", "QUANTITY", "PRICE", "SUBTOTAL"};
		DefaultTableModel model = new DefaultTableModel();
		JTable cart_table = new JTable(model);
		cart_table.setBackground(Color.decode("#f2f2f2"));
		cart_table.setRowHeight(20);
		cart_table.setEnabled(false);
		cart_table.setPreferredScrollableViewportSize(new Dimension(700,100));
		cart_table.getTableHeader().setBackground(new Color(30,30,30));
		cart_table.getTableHeader().setForeground(new Color(200,200,200));
		
		model.addColumn(columnName[0]);
		model.addColumn(columnName[1]);
		model.addColumn(columnName[2]);
		model.addColumn(columnName[3]);
		model.addColumn(columnName[4]);
		model.addColumn(columnName[5]);
		
		float total = 0;
		try {
			int i = 1;
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylogindb","root","burog181");
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM customer_shopping_cart WHERE user_id=? ");
			pstmt.setInt(1, user_id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int item_id = rs.getInt("item_id");
				int quantity = rs.getInt("item_quantity");
				float price = rs.getFloat("item_price");
				float subtotal = rs.getFloat("item_subtotal");
				String show_itemID = String.valueOf(item_id);
				String item_name = rs.getString("item_name");
				String show_quantity = String.valueOf(quantity);
				String show_price = String.valueOf(price);
				String show_subtotal  = String.valueOf(subtotal);
				
				Object [] cartRow = {i, show_itemID, item_name, show_quantity, show_price, show_subtotal};
				model.addRow(cartRow);
				i++;
				//calculates total price of items in cart
				total+= subtotal;
			}
			
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cartPanel.add(new JScrollPane(cart_table));

		// Create a custom cell renderer for the first row
		DefaultTableCellRenderer customRenderer = new DefaultTableCellRenderer() {
		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
		    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		        if (row %2 == 0) { // check if this is the first row
		            c.setBackground(Color.decode("#2f2f2f")); // set the background color
		            c.setForeground(Color.white);
		        }else {
		        	c.setBackground(Color.decode("#555555"));
		        }
		        return c;
		    }
		};

		// Set the cell renderer for all cells in the first row
		for (int i = 0; i < cart_table.getColumnCount(); i++) {
		    cart_table.getColumnModel().getColumn(i).setCellRenderer(customRenderer);
		}
		cartPanel.add(new JScrollPane(cart_table));
		
		JLabel carttotalLabel = shopLabel("Cart Total: ");
		carttotalLabel.setBounds(525,170,150,30);
		carttotalLabel.setFont(new Font("SansSerif BOLD",Font.PLAIN,18));
		carttotalLabel.setForeground(Color.decode("#dbdee1"));
		bottomPanel.add(carttotalLabel);
		
		String overAllTotal = String.valueOf(total);
		JLabel cart_total = shopLabel("Php. "+overAllTotal);
		cart_total.setBounds(625,170,150,30);
		cart_total.setFont(new Font("SansSerif",Font.PLAIN,18));
		cart_total.setForeground(Color.decode("#dbdee1"));
		bottomPanel.add(cart_total);
		
		JLabel decreaseLabel = shopLabel("Decrease Item Quantity");
		decreaseLabel.setFont(new Font("SansSerif BOLD",Font.PLAIN,18));
		decreaseLabel.setBounds(790,5,250,30);
		bottomPanel.add(decreaseLabel);
		
		JLabel enterIDLabel = shopLabel("Enter item ID: ");
		enterIDLabel.setFont(new Font("SansSerif BOLD",Font.PLAIN,15));
		enterIDLabel.setBounds(790,35,250,30);
		bottomPanel.add(enterIDLabel);
		
		JTextField itemIDField = new JTextField();
		itemIDField.setBounds(790,63,200,25);
		bottomPanel.add(itemIDField);
		
		JLabel enterQtyLabel = shopLabel("Enter Quantity: ");
		enterQtyLabel.setFont(new Font("SansSerif BOLD",Font.PLAIN,15));
		enterQtyLabel.setBounds(790,85,250,30);
		bottomPanel.add(enterQtyLabel);
		
		JTextField itemQtyField = new JTextField();
		itemQtyField.setBounds(790,113,200,25);
		bottomPanel.add(itemQtyField);
		
		// deduct item
		JButton applyBtn = adoptButton("APPLY", "D:\\3rd Year BSCPE (2nd SEMESTER)\\Software Design\\ICONS VETA\\icons8-change-100.png");
		applyBtn.setBounds(830,155,120,25);
		applyBtn.addActionListener(new ActionListener() {
			
			@SuppressWarnings("resource")
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			
				String stringQuan = itemQtyField.getText();
				String stringItem = itemIDField.getText();
				
				if(stringQuan.isEmpty() || stringItem.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Insert a valid input!");
					return;
				}
				if(!stringItem.matches("\\d+")||!stringQuan.matches("\\d+")) {
					JOptionPane.showMessageDialog(null, "Numbers only!");
					itemQtyField.setText("");
					itemIDField.setText("");
					return;
				}
				int item_id = Integer.parseInt(stringItem);
				int item_quantity = Integer.parseInt(stringQuan);
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylogindb","root","burog181");
					String sql = "SELECT * FROM customer_shopping_cart WHERE user_id=? AND item_id=?";
					PreparedStatement pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, user_id);
					pstmt.setInt(2, item_id);
					ResultSet rs = pstmt.executeQuery();
					if(rs.next()) {
						float price = rs.getInt("item_price");
						int currentQuantity = rs.getInt("item_quantity");
						if(item_quantity >=  currentQuantity) {
							sql = "DELETE FROM customer_shopping_cart WHERE item_id=?";
							pstmt = con.prepareStatement(sql);
							pstmt.setInt(1, item_id);
							pstmt.executeUpdate();
							JOptionPane.showMessageDialog(null, "Cart items updated!");
						}else if(item_quantity < currentQuantity){
							int updateQuantity = currentQuantity - item_quantity;
							float newPrice = updateQuantity*price;
							sql = "UPDATE customer_shopping_cart SET item_quantity=?, item_subtotal=? WHERE item_id=?";
							pstmt = con.prepareStatement(sql);
							pstmt.setInt(1, updateQuantity);
							pstmt.setFloat(2, newPrice);
							pstmt.setInt(3, item_id);
							pstmt.executeUpdate();
							JOptionPane.showMessageDialog(null, "Cart items updated!");
						}else {
							System.out.println("THERE IS AN ERROR");
						}
						
					}else {
						JOptionPane.showMessageDialog(null,"Failed to apply changes!");
					}
					frame1.setVisible(false);
					Shopping_Tab st = new Shopping_Tab();
					st.shopping(user_id);
				} catch (ClassNotFoundException | SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		bottomPanel.add(applyBtn);
		
		
		JButton viewOrders = adoptButton("VIEW ORDER HISTORY", "");
		viewOrders.setBounds(300,170,200,25);
		viewOrders.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				viewHistory(user_id);
			}
		});
		bottomPanel.add(viewOrders);
		
		JLabel courierLabel = shopLabel("Choose courier ");
		courierLabel.setFont(new Font("SansSerif BOLD",Font.PLAIN,15));
		courierLabel.setBounds(1020,35,250,30);
		bottomPanel.add(courierLabel);
		
		String courierList[] = {"LBC","J&T","NinjaVan"};
		//checkout all cart items
		JComboBox<String> comboCourier = new JComboBox<String>(courierList);
		comboCourier.setBounds(1020,65,140,25);
		comboCourier.setSelectedIndex(-1);
		bottomPanel.add(comboCourier);
		
		JButton checkOut = adoptButton("CHECK OUT", "D:\\3rd Year BSCPE (2nd SEMESTER)\\Software Design\\ICONS VETA\\icons8-checkout-100.png");
		checkOut.setBounds(1020,105,140,50);
		checkOut.addActionListener(new ActionListener() {
			
			@SuppressWarnings("resource")
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				
				
				float order_total = 0;
				String courier_pick = (String) comboCourier.getSelectedItem();
				String customerName =null;
				String customerAddress = null;
				String customerPhoneNumber = null;
				if(courier_pick==null) {
					return;
				}
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylogindb","root","burog181");
					String sql = "SELECT * From customer_shipping_verification WHERE customer_id=?";
					PreparedStatement pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, user_id);
					ResultSet rs = pstmt.executeQuery();
					if(rs.next()) {
						customerName = rs.getString("customer_name");
						customerAddress = rs.getString("customer_address");
						customerPhoneNumber = rs.getString("customer_phoneNumber");
					}
					
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
				    Class.forName("com.mysql.cj.jdbc.Driver");
				    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylogindb","root","burog181");
				    String sql = "SELECT * From customer_shipping_verification WHERE customer_id=? AND verification_status=?";
					PreparedStatement pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, user_id);
					pstmt.setString(2, "Verified");
					ResultSet rs = pstmt.executeQuery();
				    if(rs.next()) {
				    	sql = "SELECT * FROM customer_shopping_cart WHERE user_id=?";
					    PreparedStatement pstmt1 = con.prepareStatement(sql);
					    pstmt1.setInt(1, user_id);
					    ResultSet rs1 = pstmt1.executeQuery();
					    while (rs1.next()) {
					        String item_name = rs1.getString("item_name");
					        int item_quantity = rs1.getInt("item_quantity"); //to be saved in history
					        int item_id = rs1.getInt("item_id");
					        float subtotal_item = rs1.getFloat("item_subtotal");
					        
					        sql = "SELECT * FROM shopping_item WHERE item_name=? AND item_id=?";
					        PreparedStatement pstmt2 = con.prepareStatement(sql);
					        pstmt2.setString(1, item_name);
					        pstmt2.setInt(2, item_id);
					        ResultSet rs2 = pstmt2.executeQuery();
					        if (rs2.next()) {
					        	float tax_value = rs2.getFloat("item_tax_value");
					            int shop_item_quantity = rs2.getInt("quantity");
					            float price = rs2.getFloat("price");
					            float taxAndquantity = item_quantity * tax_value;
					            if (shop_item_quantity > item_quantity) {
					                int total_quantity = shop_item_quantity - item_quantity;
					                sql = "UPDATE shopping_item SET quantity=? WHERE item_id=? AND item_name=?";
					                pstmt2 = con.prepareStatement(sql);
					                pstmt2.setInt(1, total_quantity);
					                pstmt2.setInt(2, item_id);
					                pstmt2.setString(3, item_name);
					                pstmt2.executeUpdate();
					                sql = "INSERT INTO shopped_item_history SET item_id=?, item_name=?, item_quantity=?, item_price=?, item_tax=?, item_bought_date=?, buyer_id=?";
					                pstmt2 = con.prepareStatement(sql);
					                pstmt2.setInt(1, item_id);
					                pstmt2.setString(2, item_name);
					                pstmt2.setInt(3, item_quantity);
					                pstmt2.setFloat(4, subtotal_item);
					                pstmt2.setFloat(5, taxAndquantity);
					                pstmt2.setString(6, formattedDate);
					                pstmt2.setInt(7, user_id);
					                pstmt2.executeUpdate();
					                order_total = order_total+subtotal_item;
					                
					                sql = "DELETE FROM customer_shopping_cart WHERE user_id=? AND item_id=?";
					                pstmt2 = con.prepareStatement(sql);
					                pstmt2.setInt(1, user_id);
					                pstmt2.setInt(2, item_id);
					                pstmt2.executeUpdate();
					                frame1.setVisible(false);
					                shopping(user_id);
					             
					            } else {
					                JOptionPane.showMessageDialog(null, "There is an error in purchasing");
					            }
					        } else {
					            System.out.println("Pass");
					        }
					    }
				    }else {
				    	JOptionPane.showMessageDialog(null,"Please submit account verification in dashboard tab first!");
				    }
					LocalDate today = LocalDate.now();
				    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				    String formattedDate = today.format(formatter);
				    float shipping_fee = 0;
				    if(order_total!= 0) {
				    	if(courier_pick.equals("LBC")) {
				    		shipping_fee = 80;
				    	}else if(courier_pick.equals("NinjaVan")) {
				    		shipping_fee = 90;
				    	}else {
				    		shipping_fee = 85;
				    	}
				    	order_total = order_total+shipping_fee;
					    sql = "INSERT INTO billing set user_id=?, user_name=?, user_address=?, user_phonenumber=?, order_date=?, order_total=?, order_status=? ";
						pstmt = con.prepareStatement(sql);
						pstmt.setInt(1, user_id);
						pstmt.setString(2, customerName);
						pstmt.setString(3, customerAddress);
						pstmt.setString(4, customerPhoneNumber);
						pstmt.setString(5, formattedDate );
						pstmt.setFloat(6, order_total);
						pstmt.setString(7, order_status);
						pstmt.executeUpdate();
						JOptionPane.showMessageDialog(null, "Prepare php."+order_total+" in the next two(2) days. Thank you!");
						comboCourier.setSelectedIndex(-1);
				    }else {
				    	JOptionPane.showMessageDialog(null, "No orders found!");
				    	comboCourier.setSelectedIndex(-1);
				    }
				    
				} catch (ClassNotFoundException | SQLException | IOException e1) {
				    // TODO Auto-generated catch block
				    e1.printStackTrace();
				}

			}
		});
		bottomPanel.add(checkOut);
		
		JButton clearCartBtn = adoptButton("CLEAR CART", "D:\\3rd Year BSCPE (2nd SEMESTER)\\Software Design\\ICONS VETA\\icons8-clear-shopping-cart-100.png");
		clearCartBtn.setBounds(30,170,150,25);
		clearCartBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylogindb","root","burog181");
					PreparedStatement pstmt = con.prepareStatement("DELETE FROM customer_shopping_cart WHERE user_id=?");
					pstmt.setInt(1, user_id);
					pstmt.executeUpdate();
					JOptionPane.showMessageDialog(null, "Cart items deleted!");
					frame1.setVisible(false);
					Shopping_Tab st = new Shopping_Tab();
					st.shopping(user_id);
				} catch (ClassNotFoundException | SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		bottomPanel.add(clearCartBtn);
		
		mainContainerPanel.add(topPanel, BorderLayout.NORTH);
		mainContainerPanel.add(leftPanel, BorderLayout.WEST);
		mainContainerPanel.add(rightPanel, BorderLayout.EAST);
		mainContainerPanel.add(centerPanel, BorderLayout.CENTER);
		mainContainerPanel.add(bottomPanel, BorderLayout.SOUTH);
		
		JButton dbButton = myButton("DASHBOARD", "D:\\3rd Year BSCPE (2nd SEMESTER)\\Software Design\\ICONS VETA\\icons8-dashboard-layout-96.png");
		buttonPanel.add(dbButton);
		dbButton.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				frame1.setVisible(false);
				try {
					Dashboard_Tab dt = new Dashboard_Tab();
					dt.dashboard(user_id);
				} catch (SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
	
		JButton appointmentButton = myButton("APPOINTMENT", "D:\\3rd Year BSCPE (2nd SEMESTER)\\Software Design\\ICONS VETA\\icons8-appointment-time-64.png");
		buttonPanel.add(appointmentButton);
		appointmentButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame1.setVisible(false);
				Appointment_Tab showTab = new Appointment_Tab();
				try {
					showTab.appointment(user_id);
				} catch (SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JButton adoptionButton = myButton("ADOPT", "D:\\3rd Year BSCPE (2nd SEMESTER)\\Software Design\\ICONS VETA\\icons8-pet-100.png");
		buttonPanel.add(adoptionButton);
		adoptionButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame1.setVisible(false);
				Adopt_Tab showAdopt_Tab = new Adopt_Tab();
				try {
					showAdopt_Tab.adopt(user_id);
				} catch (SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		JButton shoppingButton = myButton("SHOP NOW","D:\\3rd Year BSCPE (2nd SEMESTER)\\Software Design\\ICONS VETA\\icons8-shopping-basket-96.png");
		buttonPanel.add(shoppingButton);
		shoppingButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame1.setVisible(false);
				try {
					shopping(user_id);
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
