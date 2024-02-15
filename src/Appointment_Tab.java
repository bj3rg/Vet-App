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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import com.toedter.calendar.JDateChooser;

public class Appointment_Tab {
	
	private JFrame frame1;
	private JPanel navPanel, mainPanel, containerPanel, petDetailPanel, tabPanel, petDataPanel;
	private JLabel vetaLabel, tabLabel;
	
	private JButton appointmentButton(String title, String name) {
		
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
		addButton.setFont(new Font("SansSerif BOLD", Font.PLAIN, 15));
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

	public JLabel appointmentLabels(String title) {
		JLabel label = new JLabel(title);
		label.setFont(new Font("SansSerif", Font.PLAIN, 18));
		label.setForeground(Color.decode("#dbdee1"));
		return label;
	}
	
	public void appointment(int user_id) throws SQLException, IOException{
		
		String tabDescription = "APPOINTMENT";
		
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

		petDetailPanel = new JPanel();
		petDetailPanel.setBounds(33,70,1180,735);
		petDetailPanel.setLayout(null);
		petDetailPanel.setBackground(Color.decode("#313338"));
		containerPanel.add(petDetailPanel);
		
		JLabel availableLabel = new JLabel("AVAILABLE APPOINTMENT SCHEDULES");
		availableLabel.setBounds(14,5,390,30);
		availableLabel.setFont(new Font("SansSerif BOLD",Font.PLAIN,18));
		availableLabel.setForeground(Color.decode("#dbdee1"));
		petDetailPanel.add(availableLabel);
		
		JLabel yourLabel = new JLabel("YOUR APPOINTMENT SCHEDULE HISTORY");
		yourLabel.setBounds(590,5,390,30);
		yourLabel.setFont(new Font("SansSerif BOLD",Font.PLAIN,18));
		yourLabel.setForeground(Color.decode("#dbdee1"));
		petDetailPanel.add(yourLabel);
		
		JLabel bookedLabel = new JLabel("BOOKED APPOINTMENT SCHEDULES");
		bookedLabel.setBounds(27,375,390,30);
		bookedLabel.setFont(new Font("SansSerif BOLD",Font.PLAIN,18));
		bookedLabel.setForeground(Color.decode("#dbdee1"));
		petDetailPanel.add(bookedLabel);
		
		JLabel chooseLabel = new JLabel("BOOK AN APPOINTMENT");
		chooseLabel.setBounds(485,375,390,30);
		chooseLabel.setFont(new Font("SansSerif BOLD",Font.PLAIN,18));
		chooseLabel.setForeground(Color.decode("#dbdee1"));
		petDetailPanel.add(chooseLabel);
		
		JLabel cancelLabel = new JLabel("REQUEST CANCELLATION");
		cancelLabel.setBounds(825,375,390,30);
		cancelLabel.setFont(new Font("SansSerif BOLD",Font.PLAIN,18));
		cancelLabel.setForeground(Color.decode("#dbdee1"));
		petDetailPanel.add(cancelLabel);
		
		ArrayList<String> dateList = new ArrayList<>();
		ArrayList<String> timeList = new ArrayList<>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylogindb","root","burog181");
			String sql = "SELECT time, date FROM schedule_slot WHERE status=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "Available");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
			     String time = rs.getString("time");
			     String date = rs.getString("date");
			     // Search if date/time is there,, if yes == don't add, if no add the current date/time on hold
			     if(dateList.contains(date)) {
			    	 System.out.println("Date is currently contained inside");
			     }else {
			    	 dateList.add(date);
			     }
			     
			     if(timeList.contains(time)) {
			    	 System.out.println("Time is currently contained inside");
			     }else {
					timeList.add(time);
				}
			    
			}
			
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		JLabel chooseDate = appointmentLabels("Choose Date:");
		chooseDate.setBounds(470,420,200,20);
		petDetailPanel.add(chooseDate);
		 
		
		JComboBox<Object> comboDate = new JComboBox<Object>(dateList.toArray());
		comboDate.setBounds(470,450,250,30);
		comboDate.setSelectedIndex(-1);
		petDetailPanel.add(comboDate);
		
		JLabel chooseTime = appointmentLabels("Choose Time:");
		chooseTime.setBounds(470,500,200,20);
		petDetailPanel.add(chooseTime);
		
	
		JComboBox<Object> comboTime = new JComboBox<Object>(timeList.toArray());
		comboTime.setBounds(470,530,250,30);
		comboTime.setSelectedIndex(-1);
		petDetailPanel.add(comboTime);
		
		JLabel chooseReason = appointmentLabels("Reason:");
		chooseReason.setBounds(470,580,200,20);
		petDetailPanel.add(chooseReason);
		
		Object[] reasonList = {"Pet Checkup","Pet Operation", "Pet Flees", "Pet Rabies", "Sick"};
		
		JComboBox<Object> comboReason = new JComboBox<>(reasonList);
		comboReason.setBounds(470,610,250,30);
		comboReason.setSelectedIndex(-1);
		petDetailPanel.add(comboReason);
		
		JLabel requestCancel = appointmentLabels("Appointment ID Number:");
		requestCancel.setBounds(820,420,250,20);
		petDetailPanel.add(requestCancel);
		
		JTextField requestField = new JTextField();
		requestField.setBounds(820, 450, 250, 30);
		petDetailPanel.add(requestField);

		// available schedule //schedule slot
		JPanel ftCon = new JPanel();
		ftCon.setBackground(Color.decode("#313338"));
		ftCon.setBounds(50,40,300,320);
		petDetailPanel.add(ftCon);
		//table for schedule slot
		String[] columnTitle = {"Time","Date","Status"};
		DefaultTableModel model = new DefaultTableModel();
		JTable table = new JTable(model);
		table.setBackground(Color.decode("#f2f2f2"));
		table.setRowHeight(20);
		table.setEnabled(false);
		table.setPreferredScrollableViewportSize(new Dimension(270,250));
		table.getTableHeader().setBackground(new Color(30,30,30));
		table.getTableHeader().setForeground(new Color(200,200,200));
		
		model.addColumn(columnTitle[0]);
		model.addColumn(columnTitle[1]);
		model.addColumn(columnTitle[2]);
		
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
		for (int i = 0; i < table.getColumnCount(); i++) {
		    table.getColumnModel().getColumn(i).setCellRenderer(customRenderer);
		}
		ftCon.add(new JScrollPane(table));
		
	
		
		JPanel ftCon2 = new JPanel();
		ftCon2.setBackground(Color.decode("#313338"));
		ftCon2.setBounds(50,420,300,320);
		petDetailPanel.add(ftCon2);
		
		DefaultTableModel model2 = new DefaultTableModel();
		JTable table2 = new JTable(model2);
		
		table2.setRowHeight(20);
		table2.setEnabled(false);
		table2.setPreferredScrollableViewportSize(new Dimension(270,250));
		table2.getTableHeader().setBackground(new Color(30,30,30));
		table2.getTableHeader().setForeground(new Color(200,200,200));
		table2.setBackground(Color.decode("#f2f2f2"));
		model2.addColumn(columnTitle[0]);
		model2.addColumn(columnTitle[1]);
		model2.addColumn(columnTitle[2]);
		
		// Create a custom cell renderer for the first row
		DefaultTableCellRenderer customRenderer2 = new DefaultTableCellRenderer() {
		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
		    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		        Component c2 = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		        if (row %2 == 0) { // check if this is the first row
		            c2.setBackground(Color.decode("#2f2f2f")); // set the background color
		            c2.setForeground(Color.white);
		        }else {
		        	c2.setBackground(Color.decode("#555555"));
		        }
		        return c2;
		    }
		};

		// Set the cell renderer for all cells in the first row
		for (int i = 0; i < table.getColumnCount(); i++) {
		    table2.getColumnModel().getColumn(i).setCellRenderer(customRenderer2);
		    
		}
		ftCon2.add(new JScrollPane(table2));

		//---------------------Panel number 3 for USER APPOINTMENT HISTORY--------------------------------------//
		JPanel ftCon3 = new JPanel();
		ftCon3.setBackground(Color.decode("#313338"));
		ftCon3.setBounds(390,40,780,320);
		petDetailPanel.add(ftCon3);
		
		String[] columnTitle2 = {"ID","Name", "Phone Number","Email Account", "Reason", "Schedule", "Status"};
		DefaultTableModel model3 = new DefaultTableModel();
		JTable table3 = new JTable(model3);
		JScrollPane spane = new JScrollPane(table3);
		table3.setOpaque(true);
		table3.setBackground(Color.decode("#f2f2f2"));
		table3.setRowHeight(20);
		table3.setEnabled(false);
		table3.setPreferredScrollableViewportSize(new Dimension(770,250));
		table3.getTableHeader().setBackground(new Color(30,30,30));
		table3.getTableHeader().setForeground(new Color(200,200,200));
		
		for(int i = 0; i <= columnTitle2.length-1; i++) {
			model3.addColumn(columnTitle2[i]);
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
		for (int i = 0; i < table3.getColumnCount(); i++) {
		    table3.getColumnModel().getColumn(i).setCellRenderer(customRenderer);
		}
		// Calculate the preferred width of each column
		for (int column = 0; column < table3.getColumnCount(); column++) {
		    int maxWidth = 0;
		    TableColumn tableColumn = table3.getColumnModel().getColumn(column);
		    for (int row = 0; row < table3.getRowCount(); row++) {
		        TableCellRenderer cellRenderer = table3.getCellRenderer(row, column);
		        Object value = table3.getValueAt(row, column);
		        Component cellComponent = cellRenderer.getTableCellRendererComponent(table3, value, false, false, row, column);
		        maxWidth = Math.max(maxWidth, cellComponent.getPreferredSize().width);
		    }
		    tableColumn.setPreferredWidth(maxWidth);
		}
		ftCon3.add(new JScrollPane(table3));
		
		// Show data in tables, user history of appointments
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylogindb","root","burog181");
			
			//Go into table to check if value exists
			String sql = "SELECT time, date, status FROM schedule_slot";
			PreparedStatement pstmt  = con.prepareStatement(sql);
			
			//Go into booked_appointment table to check if value exists	
			String sql2 = "Select * from booked_appointment WHERE user_id=?";
			PreparedStatement pstmt2 = con.prepareStatement(sql2);
			pstmt2.setInt(1, user_id);
			
			ResultSet rs = pstmt.executeQuery();
			ResultSet rs2= pstmt2.executeQuery();
			
			while(rs.next()) {
				//get value of time and date from 
			     String time = rs.getString("time");
			     String date = rs.getString("date");
				 String status = rs.getString("status");
				 //check if status is booked or not.
				 if(status.equals("Available")) {
					 Object[] row = {time, date, status};
					 model.addRow(row);
				 	
				 } else if(status.equals("Booked")) {
					Object[] row = {time, date, status};
					 model2.addRow(row);
				 }else {
					 System.out.println("ERROR");
				 }
			}
			rs.close();
			pstmt.close();
			while(rs2.next()) {
				
				 int id = rs2.getInt("id");
				 String stringID = String.valueOf(id);
				 //Gets date from booked_appointment table sql
				 Date date_booked = rs2.getDate("date");
				 String dateAsString = date_booked.toString(); //convert date to string
				
				 //Gets time from booked_appointment table sql
				 Time time_booked = rs2.getTime("time");
				 String timeAsString = time_booked.toString(); // convert time to string
				 
				 String concat = dateAsString + " - " + timeAsString;
				 String phonenumber = rs2.getString("user_phone");
				 String user_name = rs2.getString("user_name");
				 String user_email = rs2.getString("user_email");
				 String reason = rs2.getString("reason");
				 String appointment_status = rs2.getString("status");
				 if(appointment_status.equals("CANCELLED")) {
					 System.out.println("This row is cancelled");
				 }else if(appointment_status.equals(appointment_status)){
					 Object[] bookedArray = {id, user_name, phonenumber, user_email,reason, concat, appointment_status};
					 model3.addRow(bookedArray);
				 }else {
					 System.out.println("sad");
				 }
				 
			}
			con.close();
			

		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//button to add appointment
		JButton addButton = appointmentButton("ADD", "D:\\3rd Year BSCPE (2nd SEMESTER)\\Software Design\\ICONS VETA\\icons8-add-100.png");
		addButton.setBounds(550,670,100,30);
		addButton.setHorizontalAlignment(SwingConstants.CENTER);
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//Convert combo box selected item into STRING
				String status = "Booked";
				String reason = (String) comboReason.getSelectedItem();
				String time = (String) comboTime.getSelectedItem();
				String s_date = (String) comboDate.getSelectedItem();
				
				if (reason == null || reason.isBlank() || time == null || time.isBlank() || s_date == null || s_date.isBlank()) {
				    // No data found, return or show error message
					JOptionPane.showMessageDialog(null, "Insert a data");
				    return;
				}
				//Converts string date into DATE sql

				
				try {
					
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylogindb","root","burog181");
					String sql = "SELECT * FROM customer_information WHERE user_id=? AND owner_status=?";
					PreparedStatement pstmt = con.prepareStatement(sql);

					pstmt.setInt(1, user_id);
					pstmt.setString(2, "Verified");
					ResultSet rs = pstmt.executeQuery();
					
					if(rs.next()) {
						//Database connection
						String pending = "Pending-Approval";
						sql = "SELECT * FROM booked_appointment WHERE user_id=? AND status=?";
						pstmt = con.prepareStatement(sql);
						pstmt.setInt(1, user_id);
						pstmt.setString(2, pending);
						rs = pstmt.executeQuery();
						System.out.println("618");
						if(rs.next()) {
							JOptionPane.showMessageDialog(null, "You have appointment in queue for approval");
						}else {
							sql = "SELECT * FROM schedule_slot WHERE time=? AND date=? AND status=?";
							pstmt = con.prepareStatement(sql);
							String available = "Available";
							pstmt.setString(1, time);
							pstmt.setString(2, s_date);
							pstmt.setString(3, available);
							rs = pstmt.executeQuery();
							if(rs.next()) {
								sql = "UPDATE schedule_slot SET status=? WHERE date=? AND time=?";
								pstmt = con.prepareStatement(sql);
								pstmt.setString(1, status);
								pstmt.setString(2, s_date);
								pstmt.setString(3, time);
								pstmt.executeUpdate();
								
								String sql2 = "Select * from customer_information WHERE user_id=?";
								PreparedStatement pstmt2 = con.prepareStatement(sql2);
								pstmt2.setInt(1, user_id);
								ResultSet rs2 = pstmt2.executeQuery();
								if(rs2.next()) {
									String pending2 = "Pending-Approval";
									String owner = rs2.getString("pet_owner");
									String pet_type = rs2.getString("pet_type");
									String phone_number = rs2.getString("owner_phonenumber");
									String email = rs2.getString("owner_email");
									sql2 = "INSERT INTO booked_appointment (date, time, user_id, user_name, user_email,user_phone, reason, status, pet_type) VALUES (?,?,?,?,?,?,?,?,?)";
									pstmt2 =con.prepareStatement(sql2);
									pstmt2.setString(1, s_date);
									pstmt2.setString(2, time);
									pstmt2.setInt(3, user_id);
									pstmt2.setString(4, owner);
									pstmt2.setString(5, email);
									pstmt2.setString(6,phone_number);
									System.out.println(reason);
									pstmt2.setString(7, reason);
									pstmt2.setString(8, pending2);
									pstmt2.setString(9, pet_type);
									pstmt2.executeUpdate();
									JOptionPane.showMessageDialog(null, "Appointment added succesfully! Please wait for approval. Thank you!");
								}else {
									System.out.println("Sad");
								}
							}
						}
					}else {
						JOptionPane.showMessageDialog(null, "Update your pet information first at dashboard tab!");
					}
					
					con.close();
					comboDate.setSelectedIndex(-1);
					comboReason.setSelectedIndex(-1);
					comboTime.setSelectedIndex(-1);
					frame1.setVisible(false);
					Appointment_Tab at = new Appointment_Tab();
					at.appointment(user_id);
				} catch (ClassNotFoundException | SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		petDetailPanel.add(addButton);
		
		//button to cancel appointment
		JButton cancelButton = appointmentButton("SUBMIT", "D:\\3rd Year BSCPE (2nd SEMESTER)\\Software Design\\ICONS VETA\\icons8-remove-90.png");
		cancelButton.setBounds(900,670,100,30);
		cancelButton.setHorizontalAlignment(SwingConstants.CENTER);
		cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String idString = requestField.getText();
//				|| !description.matches("\\d+")
				if(!idString.matches("\\d+")) {
					JOptionPane.showMessageDialog(null, "Numbers only!");
					requestField.setText("");
					return;
				}
				
				
				if(requestField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Appointment ID number cannot be empty!");
					return;
				}
				int id_cancel = Integer.parseInt(idString);
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylogindb","root","burog181");
					String sql = "SELECT * FROM booked_appointment WHERE id=?";
					PreparedStatement pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, id_cancel);
					ResultSet rs = pstmt.executeQuery();
					if(rs.next()) {
						String status_booked = rs.getString("status");
						if(status_booked.equals("Pending-Approval")) {
							sql = "UPDATE booked_appointment SET status=? WHERE id=?";
							pstmt=con.prepareStatement(sql);
							String statusNew = "Pending-Cancel";
							pstmt.setString(1, statusNew);
							pstmt.setInt(2, id_cancel);
							pstmt.executeUpdate();
							JOptionPane.showMessageDialog(null, "Appointment cancellation sent succesfully!");
						}else {
							JOptionPane.showMessageDialog(null, "Appointment cannot be cancelled.");
							requestField.setText("");
						}
						
					}else {
						JOptionPane.showMessageDialog(null, "Failed to cancel");
						requestField.setText("");
					}
					con.close();
					rs.close();
					frame1.setVisible(false);
					Appointment_Tab at = new Appointment_Tab();
					at.appointment(user_id);
				
				} catch (ClassNotFoundException | SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		petDetailPanel.add(cancelButton);
		
		
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
				try {
					appointment(user_id);
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
				Adopt_Tab at = new Adopt_Tab();
				try {
					at.adopt(user_id);
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
				Shopping_Tab st = new Shopping_Tab();
				try {
					st.shopping(user_id);
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
