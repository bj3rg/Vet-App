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
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import com.toedter.calendar.JDateChooser;

import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.*;  

public class Admin_Appointment {
	
	private JFrame frame1;
	private JPanel navPanel, mainPanel, containerPanel, mainContainerPanel, tabPanel, petDataPanel;
	private JLabel vetaLabel, tabLabel;
	private ArrayList<Object> lists;
	
	public ImageIcon imageIcon(String name) {
		ImageIcon image = new ImageIcon(name);
		Image imagecon = image.getImage(); // Get the original image
		Image scaledImage = imagecon.getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Scale the image
		ImageIcon dbScaled = new ImageIcon(scaledImage); // Create a new ImageIcon with the scaled image
		return dbScaled;
	}
	//method for buttons
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

	public JLabel adminAppointmentLabel(String title) {
		JLabel label = new JLabel(title);
		label.setFont(new Font("SansSerif", Font.PLAIN, 18));
		label.setForeground(Color.decode("#dbdee1"));
		return label;
	}
	
	public static void main(String[] args) throws SQLException, IOException {
		Admin_Appointment at = new Admin_Appointment();
		at.adminAppointment(1);
	}
	
	public void adminAppointment(int user_id) throws SQLException, IOException{
		
		String tabDescription = "APPOINTMENTS";
		
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
		tabPanel.setBounds(480,0,280,50);
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

		JLabel table1Label = adminAppointmentLabel("APPOINTMENT SCHEDULE SLOT");
		table1Label.setBounds(290,15,350,30);
		mainContainerPanel.add(table1Label);

		JLabel table3Label = adminAppointmentLabel("CUSTOMER BOOKED APPOINTMENT DETAILS");
		table3Label.setBounds(430,280,450,30);
		mainContainerPanel.add(table3Label);
		
		//Schedule slot
		JPanel schedulePanel = new JPanel();
		schedulePanel.setBounds(25,45,730,210);
		schedulePanel.setBackground(Color.decode("#616463"));
		mainContainerPanel.add(schedulePanel);

		String[] columnTitle3 = {"Appointment ID","Date", "Time", "Status"};
		DefaultTableModel model1 = new DefaultTableModel();
		JTable scheduleSlotTable = new JTable(model1);
		JScrollPane spane3 = new JScrollPane(scheduleSlotTable);
		scheduleSlotTable.setOpaque(true);
		scheduleSlotTable.setBackground(Color.decode("#f2f2f2"));
		scheduleSlotTable.setRowHeight(20);
		scheduleSlotTable.setEnabled(false);
		scheduleSlotTable.setPreferredScrollableViewportSize(new Dimension(710,180));
		scheduleSlotTable.getTableHeader().setBackground(new Color(30,30,30));
		scheduleSlotTable.getTableHeader().setForeground(new Color(200,200,200));

		for(int i = 0; i <= columnTitle3.length-1; i++) {
		    model1.addColumn(columnTitle3[i]);
		}

		//Create a custom cell renderer for the first row
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
		        } else {
		            c3.setBackground(Color.decode("#555555"));
		        }
		        return c3;
		    }
		};

		// Set the cell renderer for all cells in the first row
		for (int i = 0; i < scheduleSlotTable.getColumnCount(); i++) {
		    scheduleSlotTable.getColumnModel().getColumn(i).setCellRenderer(customRenderer3);
		}

		// Calculate the preferred width of each column
		for (int column = 0; column < scheduleSlotTable.getColumnCount(); column++) {
		    int maxWidth = 0;
		    TableColumn tableColumn = scheduleSlotTable.getColumnModel().getColumn(column);
		    for (int row = 0; row < scheduleSlotTable.getRowCount(); row++) {
		        TableCellRenderer cellRenderer = scheduleSlotTable.getCellRenderer(row, column);
		        Object value = scheduleSlotTable.getValueAt(row, column);
		        Component cellComponent = cellRenderer.getTableCellRendererComponent(scheduleSlotTable, value, false, false, row, column);
		        maxWidth = Math.max(maxWidth, cellComponent.getPreferredSize().width);
		    }
		    tableColumn.setPreferredWidth(maxWidth);
		}
		schedulePanel.add(new JScrollPane(scheduleSlotTable));


		// Change adoption status and appointment----------------------------------------
		JLabel acc_statusLabel2 = adminAppointmentLabel("CHANGE STATUS");
		acc_statusLabel2.setBounds(810, 30, 300, 30);
		mainContainerPanel.add(acc_statusLabel2);

		// Adjusted bounds for idLabel
		JLabel idLabel2 = adminAppointmentLabel("User ID:");
		idLabel2.setBounds(770, 110, 150, 30);
		mainContainerPanel.add(idLabel2);

		// Adjusted bounds for idField
		JTextField idField2 = new JTextField();
		idField2.setBounds(890, 110, 120, 30);
		mainContainerPanel.add(idField2);
		
		JLabel tranIDLabel = adminAppointmentLabel("Appntmnt ID:");
		tranIDLabel.setBounds(770,70, 150, 30);
		mainContainerPanel.add(tranIDLabel);

		// Field for appointment and adoption id 
		// Adjusted bounds for idField
		JTextField tranField = new JTextField();
		tranField.setBounds(890, 70, 120, 30);
		mainContainerPanel.add(tranField);
	
	
		// Adjusted bounds for idLabel
		JLabel statusLabel2 = adminAppointmentLabel("Set Status:");
		statusLabel2.setBounds(770, 150, 150, 30);
		mainContainerPanel.add(statusLabel2);
		
		// Adjusted bounds for comboTable
		String statusList[] = {"Approved","Cancelled"};
		JComboBox<String> comboStatus = new JComboBox<String>(statusList);
		comboStatus.setBounds(890, 150, 120, 30);
		comboStatus.setSelectedIndex(-1);
		mainContainerPanel.add(comboStatus);
		
		// Adjusted bounds for idLabel
		JLabel statusLabel3 = adminAppointmentLabel("Choose Table:");
		statusLabel3.setBounds(770, 190, 150, 30);
		mainContainerPanel.add(statusLabel3);
		
		// Adjusted bounds for comboTable
		String tableList2[] = {"Appointment Table-2"};
		JComboBox<String> comboTable2 = new JComboBox<String>(tableList2);
		comboTable2.setBounds(890, 190, 120, 30);
		comboTable2.setSelectedIndex(-1);
		mainContainerPanel.add(comboTable2);
		
		//button for updating status of appointment booked by user
		JButton accStatusButton2 = appointmentButton("UPDATE", "D:\\3rd Year BSCPE (2nd SEMESTER)\\Software Design\\ICONS VETA\\icons8-update-100.png");
		accStatusButton2.setBounds(890, 230, 120, 30);
		accStatusButton2.addActionListener(new ActionListener() {

			@SuppressWarnings("resource")
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String s_user_id = idField2.getText();
				String s_app_add_id = tranField.getText();
				String status= (String) comboStatus.getSelectedItem();
				String table = (String) comboTable2.getSelectedItem();
				if(s_user_id.isEmpty()|| s_app_add_id.isEmpty() || status==null|| table==null || !s_user_id.matches("\\d+") || !s_app_add_id.matches("\\d+")) {
					idField2.setText("");
					tranField.setText("");
					comboStatus.setSelectedIndex(-1);
					comboTable2.setSelectedIndex(-1);
					JOptionPane.showMessageDialog(null, "Invalid input!");
					return;
				}
				int user_id = Integer.parseInt(s_user_id);
				int app_id = Integer.parseInt(s_app_add_id);
				
				if(comboTable2.getSelectedIndex()==0) {
					if(comboStatus.getSelectedIndex()==0) {
						try {
							Class.forName("com.mysql.cj.jdbc.Driver");
							Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylogindb","root","burog181");
							String sql = "SELECT * from booked_appointment WHERE user_id=? AND id=?";
							PreparedStatement pstmt = con.prepareStatement(sql);
							pstmt.setInt(1, user_id);
							pstmt.setInt(2, app_id);
							ResultSet rs = pstmt.executeQuery();
							if (rs.next()) {
								String newStatus = rs.getString("status");
								if(newStatus.equals("Pending-Approval")) {
									sql = "UPDATE booked_appointment SET status=? WHERE user_id=? AND id=? ";
									pstmt = con.prepareStatement(sql);
									pstmt.setString(1, status);
									pstmt.setInt(2, user_id);
									pstmt.setInt(3, app_id);
									pstmt.executeUpdate();
									frame1.setVisible(false);
									adminAppointment(1);
								}else if(newStatus.equals("Approved")){
									JOptionPane.showMessageDialog(null, "Appointment is already approved.");
									idField2.setText("");
									tranField.setText("");
									comboStatus.setSelectedIndex(-1);
									comboTable2.setSelectedIndex(-1);
								}else {
									JOptionPane.showMessageDialog(null, "Invalid Combination");
									idField2.setText("");
									tranField.setText("");
									comboStatus.setSelectedIndex(-1);
									comboTable2.setSelectedIndex(-1);
								}
							}else {
								JOptionPane.showMessageDialog(null, "No data found for this user.");
								idField2.setText("");
								tranField.setText("");
								comboStatus.setSelectedIndex(-1);
								comboTable2.setSelectedIndex(-1);
							}
						} catch (ClassNotFoundException | SQLException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else {
						try {
							Class.forName("com.mysql.cj.jdbc.Driver");
							Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylogindb","root","burog181");
							String sql = "SELECT * from booked_appointment WHERE user_id=? AND id=?";
							PreparedStatement pstmt = con.prepareStatement(sql);
							pstmt.setInt(1, user_id);
							pstmt.setInt(2, app_id);
							ResultSet rs = pstmt.executeQuery();
							if (rs.next()) {
								String newStatus = rs.getString("status");
								if(newStatus.equals("Pending-Cancel")) {
									sql="Update booked_appointment set status=? WHERE user_id=? AND id=?";
									pstmt = con.prepareStatement(sql);
									pstmt.setString(1, status);
									pstmt.setInt(2, user_id);
									pstmt.setInt(3, app_id);
									pstmt.executeUpdate();
									JOptionPane.showMessageDialog(null, "Appointment is cancelled!");
									frame1.setVisible(false);
									adminAppointment(1);
								}else if(newStatus.equals("Pending-Approval")){
									sql="Update booked_appointment set status=? WHERE user_id=? AND id=?";
									pstmt = con.prepareStatement(sql);
									pstmt.setString(1, status);
									pstmt.setInt(2, user_id);
									pstmt.setInt(3, app_id);
									pstmt.executeUpdate();
									JOptionPane.showMessageDialog(null, "Appointment is cancelled!");
									frame1.setVisible(false);
									adminAppointment(1);
								}else {
									JOptionPane.showMessageDialog(null, "Appointment is already approved.");
									idField2.setText("");
									tranField.setText("");
									comboStatus.setSelectedIndex(-1);
									comboTable2.setSelectedIndex(-1);
									
								}
							}else {
								JOptionPane.showMessageDialog(null, "No data found for this user.");
								idField2.setText("");
								tranField.setText("");
								comboStatus.setSelectedIndex(-1);
								comboTable2.setSelectedIndex(-1);
							}
						} catch (ClassNotFoundException | SQLException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}else {
					idField2.setText("");
					tranField.setText("");
					comboStatus.setSelectedIndex(-1);
					comboTable2.setSelectedIndex(-1);
				}
				
			}
		    // ActionListener code...
		});
		mainContainerPanel.add(accStatusButton2);
		
		JButton clearButton2 = appointmentButton("CLEAR", "D:\\3rd Year BSCPE (2nd SEMESTER)\\Software Design\\ICONS VETA\\icons8-clear-symbol-100.png");
		clearButton2.setBounds(770, 230, 110, 30);
		clearButton2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				tranField.setText("");
				comboStatus.setSelectedIndex(-1);
				comboTable2.setSelectedIndex(-1);
			}
		    // ActionListener code...
		});
		mainContainerPanel.add(clearButton2);
		
		
		//-----------------------------------------------------------------------------
		
		//ADD APPOINTMENTS SLOT
		// Adjusted bounds for statusLabel
		JLabel statusLabel = adminAppointmentLabel("ADD APPOINTMENT SLOT:");
		statusLabel.setBounds(1025, 30, 250, 30);
		mainContainerPanel.add(statusLabel);
		
		JLabel timeLabel = adminAppointmentLabel("Choose Time:");
		timeLabel.setBounds(1020,90, 130, 30);
		mainContainerPanel.add(timeLabel);
		
		JPanel cbPanel = new JPanel();
		cbPanel.setBackground(Color.decode("#313338"));
		cbPanel.setBounds(1130,60,170,80);
		cbPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		mainContainerPanel.add(cbPanel);

		String cbArray[] = {"7:00:00", "8:00:00", "9:00:00", "10:00:00", "11:00:00"};
		JCheckBox[] checkboxesArray = new JCheckBox[5];
		ArrayList<Object> allChecked = new ArrayList<>();
		for (int i = 0; i < checkboxesArray.length; i++) {
		    checkboxesArray[i] = new JCheckBox(cbArray[i]);
		    checkboxesArray[i].setBackground(Color.decode("#313338"));
		    checkboxesArray[i].setForeground(Color.decode("#dbdee1"));
		    checkboxesArray[i].setFocusable(true);
		    cbPanel.add(checkboxesArray[i]);
		    
		    int index = i;
		    checkboxesArray[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    JCheckBox checkbox = (JCheckBox) e.getSource();
                    if (checkbox.isSelected()) {
                    	allChecked.add(checkbox.getText());
                    }
                    
                }
            });
		}
		
		
		JLabel dateLabel = adminAppointmentLabel("Choose Date:");
		dateLabel.setBounds(1020, 150, 130, 30);
		mainContainerPanel.add(dateLabel);
		
		//date schedule appointment chooser
		JDateChooser dateChooser2 = new JDateChooser();
		Calendar calendar2 = Calendar.getInstance();
		dateChooser2.setMinSelectableDate(calendar2.getTime());
	    calendar2.add(Calendar.YEAR, 1);
	    dateChooser2.setMaxSelectableDate(calendar2.getTime());
		dateChooser2.setLocale(Locale.US);
		dateChooser2.setBounds(1140,150,120,30);
		mainContainerPanel.add(dateChooser2);
		
		//button for adding schedule slot for appointment
		JButton addSchedule = appointmentButton("ADD", "D:\\3rd Year BSCPE (2nd SEMESTER)\\Software Design\\ICONS VETA\\icons8-add-100.png");
		addSchedule.setBounds(1140, 230, 120, 30);
		addSchedule.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				java.util.Date date = dateChooser2.getDate();
				if(date==null) {
					return;
				}
				
				SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
				String s_date = formatDate.format(date);
				int x = allChecked.toArray().length;
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylogindb","root","burog181");
						String sql = "SELECT * from schedule_slot WHERE date=?";
						PreparedStatement pstmt = con.prepareStatement(sql);
						pstmt.setString(1, s_date);
						ResultSet rs = pstmt.executeQuery();
						if(rs.next()) {
							for (int i =0; i< checkboxesArray.length; i++) {
								checkboxesArray[i].setSelected(false);
							}
							dateChooser2.setDate(null);
							JOptionPane.showMessageDialog(null, "You have already added an appointment on this day! "+ "("+s_date+")");
						}else {
							if(x==0) {
								JOptionPane.showMessageDialog(null, "Choose among the listed time!");
							}else {
								for (int i =0;i<allChecked.toArray().length; i++) {
									sql = "INSERT INTO schedule_slot SET date=?, time=?, status=?";
									pstmt = con.prepareStatement(sql);
									pstmt.setString(1, s_date);
									pstmt.setString(2, allChecked.toArray()[i].toString() );
									pstmt.setString(3, "Available");
									pstmt.executeUpdate();
									for (int j =0; j< checkboxesArray.length; j++) {
										checkboxesArray[j].setSelected(false);
									}
									dateChooser2.setDate(null);
								}
								JOptionPane.showMessageDialog(null, "Appointments succesfully added!");
								frame1.setVisible(false);
								adminAppointment(1);
							}
							allChecked.clear();
						}
					} catch (ClassNotFoundException | SQLException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				
			}
		    // ActionListener code...
		});
		mainContainerPanel.add(addSchedule);
		
		JButton clearButton3 = appointmentButton("CLEAR", "D:\\3rd Year BSCPE (2nd SEMESTER)\\Software Design\\ICONS VETA\\icons8-clear-symbol-100.png");
		clearButton3.setBounds(1020, 230, 110, 30);
		clearButton3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				for (int i =0; i< checkboxesArray.length; i++) {
					checkboxesArray[i].setSelected(false);
				}
				dateChooser2.setDate(null);
			}
		    // ActionListener code...
		});
		mainContainerPanel.add(clearButton3);
		
		//booked appointments
		JPanel bookedPanel = new JPanel();
		bookedPanel.setBounds(25,310,1230,400);
		bookedPanel.setBackground(Color.decode("#616463"));
		mainContainerPanel.add(bookedPanel);
			
		String[] columnTitle1 = {"Appointment ID","User ID", "Name", "Email", "Phone Number","Reason", "Pet", "Date", "Time", "Status"};
		DefaultTableModel model3 = new DefaultTableModel();
		JTable bookedAppointmentTable = new JTable(model3);
		JScrollPane spane = new JScrollPane(bookedAppointmentTable);
		bookedAppointmentTable.setOpaque(true);
		bookedAppointmentTable.setBackground(Color.decode("#f2f2f2"));
		bookedAppointmentTable.setRowHeight(20);
		bookedAppointmentTable.setEnabled(false);
		bookedAppointmentTable.setPreferredScrollableViewportSize(new Dimension(1210,370));
		bookedAppointmentTable.getTableHeader().setBackground(new Color(30,30,30));
		bookedAppointmentTable.getTableHeader().setForeground(new Color(200,200,200));

		for(int i = 0; i <= columnTitle1.length-1; i++) {
		    model3.addColumn(columnTitle1[i]);
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
		for (int i = 0; i < bookedAppointmentTable.getColumnCount(); i++) {
		    bookedAppointmentTable.getColumnModel().getColumn(i).setCellRenderer(customRenderer);
		}

		// Calculate the preferred width of each column
		for (int column = 0; column < bookedAppointmentTable.getColumnCount(); column++) {
		    int maxWidth = 0;
		    TableColumn tableColumn = bookedAppointmentTable.getColumnModel().getColumn(column);
		    for (int row = 0; row < bookedAppointmentTable.getRowCount(); row++) {
		        TableCellRenderer cellRenderer = bookedAppointmentTable.getCellRenderer(row, column);
		        Object value = bookedAppointmentTable.getValueAt(row, column);
		        Component cellComponent = cellRenderer.getTableCellRendererComponent(bookedAppointmentTable, value, false, false, row, column);
		        maxWidth = Math.max(maxWidth, cellComponent.getPreferredSize().width);
		    }
		    tableColumn.setPreferredWidth(maxWidth);
		}
		bookedPanel.add(new JScrollPane(bookedAppointmentTable));

		try {
			//user_id, pet_owner, pet_type, pet_gender, pet_name, pet_breed, pet_bday, email, phonenumber, owner_status
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylogindb","root","burog181");
						String sql = "SELECT * FROM schedule_slot ORDER BY " +
							      "CASE " +
							      "WHEN status IS NULL THEN 1 " +
							      "WHEN status = 'Booked' THEN 2 " +
							      "ELSE 3 " +
							      "END";
						PreparedStatement pstmt = con.prepareStatement(sql);
						ResultSet rs = pstmt.executeQuery();
						while(rs.next()) {
							int appointment_id = rs.getInt("appointment_id");
							String s_appointment_id = String.valueOf(appointment_id);
							String date = rs.getString("date");
							String time = rs.getString("time");
							String status=rs.getString("status");
							String values[]= {s_appointment_id,date,time,status};
							model1.addRow(values);
						}

					
						sql = "SELECT * FROM booked_appointment ORDER BY " +
							      "CASE " +
							      "WHEN status = 'Approved' THEN 1 " +
							      "WHEN status = 'Pending-Approval' THEN 2 " +
							      "WHEN status = 'Pending-Cancel' THEN 3 " +
							      "ELSE 4 " +
							      "END";
						pstmt= con.prepareStatement(sql);
						rs = pstmt.executeQuery();
						while(rs.next()) {
							int id = rs.getInt("id");
							int i_user_id = rs.getInt("user_id");
							String appointment_id = String.valueOf(id);
							String s_user_id = String.valueOf(i_user_id);
							String date = rs.getString("date");
							String time = rs.getString("time");
							String user_name = rs.getString("user_name");
							String user_email = rs.getString("user_email");
							String user_phone = rs.getString("user_phone");
							String reason = rs.getString("reason");
							String status = rs.getString("status");

							String pet = rs.getString("pet_type");
							String values[]= {appointment_id,s_user_id,user_name,user_email, user_phone,reason,pet,date,time,status};
							model3.addRow(values);
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
