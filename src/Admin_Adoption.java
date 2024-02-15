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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.ComboPopup;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import com.mysql.cj.jdbc.Blob;
import com.mysql.cj.x.protobuf.MysqlxDatatypes.Any;
import com.toedter.calendar.JDateChooser;

import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;


public class Admin_Adoption {
	
	private JFrame frame1;
	private JPanel navPanel, mainPanel, containerPanel, mainContainerPanel, tabPanel, petDataPanel;
	private JLabel vetaLabel, tabLabel;
	private String s;
	
	
	public ImageIcon imageIcon(String name) {
		ImageIcon image = new ImageIcon(name);
		Image imagecon = image.getImage(); // Get the original image
		Image scaledImage = imagecon.getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Scale the image
		ImageIcon dbScaled = new ImageIcon(scaledImage); // Create a new ImageIcon with the scaled image
		return dbScaled;
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
	
	public JLabel adoptLabel(String title) {
		JLabel label = new JLabel(title);
		label.setFont(new Font("SansSerif", Font.PLAIN, 18));
		label.setForeground(Color.decode("#dbdee1"));
		mainContainerPanel.add(label);
		return label;
	}
	
	public static void main(String[] args) throws SQLException, IOException {
		Admin_Appointment at = new Admin_Appointment();
		at.adminAppointment(1);
	}
	
	public void adopt(int user_id) throws SQLException, IOException{
		
		String tabDescription = "ADOPTION";
		
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
		mainContainerPanel.setBounds(20,70,1280,745);
		mainContainerPanel.setLayout(null);
		mainContainerPanel.setBackground(Color.decode("#313338"));
		containerPanel.add(mainContainerPanel);
		
		JLabel fTableLabel = adoptLabel("PET ADOPTION QUEUE");
		fTableLabel.setBounds(530,40,250,30);
		mainContainerPanel.add(fTableLabel);
		
		JLabel fTableLabel2 = adoptLabel("PETS LISTED FOR ADOPTION");
		fTableLabel2.setBounds(280,200,300,30);
		mainContainerPanel.add(fTableLabel2);
		
		JPanel accountsPanel = new JPanel();
		accountsPanel.setBounds(20,70,1230,130);
		accountsPanel.setBackground(Color.decode("#616463"));
		mainContainerPanel.add(accountsPanel);
		
		String[] columnTitle1 = {"Adoption ID", "Pet ID","Type", "Owner ID", "Owner Name", "Date Appt", "Time Appt", "Status"};
		DefaultTableModel model = new DefaultTableModel();
		JTable customerTable = new JTable(model);
		JScrollPane spane = new JScrollPane(customerTable);
		customerTable.setOpaque(true);
		customerTable.setBackground(Color.decode("#f2f2f2"));
		customerTable.setRowHeight(20);
		customerTable.setEnabled(false);
		customerTable.setPreferredScrollableViewportSize(new Dimension(1220,100));
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
		
		// For updating all status
		JLabel acc_statusLabel = adoptLabel("SET APPOINTMENT");
		acc_statusLabel.setBounds(80,380,180,30);
		mainContainerPanel.add(acc_statusLabel);
		
		JLabel idLabel = adoptLabel("Adoption ID:");
		idLabel.setBounds(40,410,150,30);
		mainContainerPanel.add(idLabel);
		
		JTextField idField = new JTextField();
		idField.setBounds(170,410,120,30);
		mainContainerPanel.add(idField);
		
		JLabel chooseLabel = adoptLabel("Select Date:");
		chooseLabel.setBounds(40,450,150,30);
		mainContainerPanel.add(chooseLabel);
		
		JDateChooser dateChooser = new JDateChooser();
		Calendar calendar = Calendar.getInstance();
		dateChooser.setMinSelectableDate(calendar.getTime());
	    calendar.add(Calendar.YEAR, 1);
	    dateChooser.setMaxSelectableDate(calendar.getTime());
		dateChooser.setLocale(Locale.US);
		dateChooser.setBounds(170,450,120,30);
		mainContainerPanel.add(dateChooser);
		
		JLabel statusLabel = adoptLabel("Select Time:");
		statusLabel.setBounds(40,490,150,30);
		mainContainerPanel.add(statusLabel);
		
		String tableList[] = {"7:00:00", "8:00:00", "9:00:00", "10:00:00", "11:00:00"};
		JComboBox<String> comboTime = new JComboBox<String>(tableList);
		comboTime.setBounds(170,490,120,30);
		comboTime.setSelectedIndex(-1);
		mainContainerPanel.add(comboTime);
	
		//verify pet history
		JButton accStatusButton = accountButton("APPLY", "D:\\3rd Year BSCPE (2nd SEMESTER)\\Software Design\\ICONS VETA\\icons8-change-100.png");
		accStatusButton.setBounds(170,530,120,30);
		accStatusButton.addActionListener( new ActionListener() {

			@SuppressWarnings("resource")
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String get_id = idField.getText();
				String get_time = (String) comboTime.getSelectedItem();
				Date date = dateChooser.getDate();
				if(get_id.isEmpty() || !get_id.matches("\\d+") || get_time==null || dateChooser.getDate()==null){
				    System.out.println("EMPTY");
				    comboTime.setSelectedIndex(-1);
				    idField.setText("");
					return;
				} 
				SimpleDateFormat formateDate = new SimpleDateFormat("yyyy-MM-dd");
				String s_date = formateDate.format(date);
				int id = Integer.parseInt(get_id);
				
						try {
							Class.forName("com.mysql.cj.jdbc.Driver");
							Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylogindb","root","burog181");
							String sql = "SELECT * FROM adopted_pet WHERE adoption_id=?";
							PreparedStatement pstmt = con.prepareStatement(sql);
							pstmt.setInt(1, id);
							ResultSet rs = pstmt.executeQuery();
							if(rs.next()) {
								String type = rs.getString("pet_type");
								String status = rs.getString("status");
								String new_id = rs.getString("pet_id");
								if(status.equals("In Scheduling")) {
									String newStatus = "For Meet-up";
									sql = "Update adopted_pet SET date_appointment=?, time_appointment=?, status=? WHERE adoption_id=?";
									pstmt = con.prepareStatement(sql);
									pstmt.setString(1, s_date);
									pstmt.setString(2, get_time);
									pstmt.setString(3, newStatus);
									pstmt.setInt(4, id);
									pstmt.executeUpdate();
									if(type.equals("Cat")) {
										int cat_id = Integer.parseInt(new_id);
										sql = "Update cat_for_adoption SET cat_status=? WHERE cat_id=?";
										pstmt = con.prepareStatement(sql);
										pstmt.setString(1, "For Meet-up");
										pstmt.setInt(2, cat_id);
										pstmt.executeUpdate();
									}else {
										int dog_id = Integer.parseInt(new_id);
										sql = "Update dog_for_adoption SET dog_status=? WHERE dog_id=?";
										pstmt = con.prepareStatement(sql);
										pstmt.setString(1, "For Meet-up");
										pstmt.setInt(2, dog_id);
										pstmt.executeUpdate();
									}
									
									
									JOptionPane.showMessageDialog(null, "Adoption appointment succesfully updated!");
									frame1.setVisible(false);
									adopt(1);
								}else {
									JOptionPane.showMessageDialog(null, "Action can't be done.");
									idField.setText("");
									comboTime.setSelectedIndex(-1);
									dateChooser.setDate(null);
								}
							}else {
								JOptionPane.showMessageDialog(null, "No data found for this user.");
								idField.setText("");
								comboTime.setSelectedIndex(-1);
								dateChooser.setDate(null);
							}
						} catch (ClassNotFoundException | SQLException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}		
			}
		});
		mainContainerPanel.add(accStatusButton);
		
		JButton clearButton = accountButton("CLEAR", "D:\\3rd Year BSCPE (2nd SEMESTER)\\Software Design\\ICONS VETA\\icons8-clear-symbol-100.png");
		clearButton.setBounds(40,530,120,30);
		clearButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				idField.setText("");
				comboTime.setSelectedIndex(-1);
				dateChooser.setDate(null);

			}
		});
		mainContainerPanel.add(clearButton);
				
		
		//For choosing image
		JLabel imageLabel = adoptLabel("IMAGE VIEWER");
		imageLabel.setBounds(380,380,150,30);
		mainContainerPanel.add(imageLabel);
		
		JLabel idLabel2 = adoptLabel("Enter Pet ID:");
		idLabel2.setBounds(320,410,150,30);
		mainContainerPanel.add(idLabel2);
		
		JTextField idField2 = new JTextField();
		idField2.setBounds(450,410,120,30);
		mainContainerPanel.add(idField2);
		
		JLabel chooseLabel2 = adoptLabel("Pet Type:");
		chooseLabel2.setBounds(320,450,150,30);
		mainContainerPanel.add(chooseLabel2);
		
		String tablePet[] = {"Cat", "Dog"};
		JComboBox<String> comboPet = new JComboBox<String>(tablePet);
		comboPet.setBounds(450,450,120,30);
		comboPet.setSelectedIndex(-1);
		mainContainerPanel.add(comboPet);
		
		JLabel detailLabel = adoptLabel("");
		detailLabel.setBounds(305,705,450,30);
		
		// IMAGE CONTAINER PANEL
		mainContainerPanel.add(detailLabel);
		Border border = BorderFactory.createLineBorder(Color.decode("#616463"), 5);
		JPanel imagePanel = new JPanel();
		imagePanel.setBorder(border);
		imagePanel.setBounds(870,210,380,510);
		imagePanel.setBackground(Color.decode("#4e4f50"));
		mainContainerPanel.add(imagePanel);
		//IMAGE CONTAINER LABEL
		JLabel imageContainerLabel = new JLabel("");
		imageContainerLabel.setPreferredSize(new Dimension(360,490));
		imagePanel.add(imageContainerLabel);
		//-----------------------------------------
		
		JButton searchImageBtn = accountButton("SEARCH", "D:\\3rd Year BSCPE (2nd SEMESTER)\\Software Design\\ICONS VETA\\icons8-search-100.png");
		searchImageBtn.setBounds(450,490,120,30);
		searchImageBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String get_id = idField2.getText();
				String pet_type = (String) comboPet.getSelectedItem();
				String concat=null;
				if(get_id.isEmpty() || !get_id.matches("\\d+") ||pet_type == null){
					idField2.setText("");
					comboPet.setSelectedIndex(-1);
					return;
				} 
				int id = Integer.parseInt(get_id);
				if(comboPet.getSelectedIndex()==0) {
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylogindb","root","burog181");
						String sql = "SELECT * FROM cat_for_adoption WHERE cat_id=?";
						PreparedStatement pstmt = con.prepareStatement(sql);
						pstmt.setInt(1, id);
						ResultSet rs = pstmt.executeQuery();
						if(rs.next()) {
							Blob img = (Blob) rs.getBlob("cat_image");
							InputStream inputStream = img.getBinaryStream();
					        byte[] imageBytes = inputStream.readAllBytes();
					        ImageIcon imageIcon = new ImageIcon(imageBytes);
					        Image scaledImage = imageIcon.getImage().getScaledInstance(360,490,Image.SCALE_SMOOTH);
					        imageContainerLabel.setIcon(new ImageIcon(scaledImage));
							idField2.setText("");
							comboPet.setSelectedIndex(-1);
							concat = "Cat Image| Cat ID: "+get_id;
							detailLabel.setText(concat);
						}else {
							idField2.setText("");
							comboPet.setSelectedIndex(-1);
							JOptionPane.showMessageDialog(null, "Cat not found!");
						}
					} catch (ClassNotFoundException | SQLException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else {
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylogindb","root","burog181");
						String sql = "SELECT * FROM dog_for_adoption WHERE dog_id=?";
						PreparedStatement pstmt = con.prepareStatement(sql);
						pstmt.setInt(1, id);
						ResultSet rs = pstmt.executeQuery();
						if(rs.next()) {
							Blob img = (Blob) rs.getBlob("dog_image");
							InputStream inputStream = img.getBinaryStream();
					        byte[] imageBytes = inputStream.readAllBytes();
					        ImageIcon imageIcon = new ImageIcon(imageBytes);
					        Image scaledImage = imageIcon.getImage().getScaledInstance(360,490,Image.SCALE_SMOOTH);
					        imageContainerLabel.setIcon(new ImageIcon(scaledImage));
							idField2.setText("");
							comboPet.setSelectedIndex(-1);
							concat = "Dog Image | Dog ID: "+get_id;
							detailLabel.setText(concat);
						}else {
							idField2.setText("");
							comboPet.setSelectedIndex(-1);
							JOptionPane.showMessageDialog(null, "Dog not found!");
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
		clearButton2.setBounds(320,490,120,30);
		clearButton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				idField2.setText("");
				imageContainerLabel.setIcon(null);
				detailLabel.setText("");
			}
		});
		mainContainerPanel.add(clearButton2);
		//--------------------------------------------------
		
		JLabel imageLabel2 = adoptLabel("APPROVE ADDED PET");
		imageLabel2.setBounds(210,580,200,30);
		mainContainerPanel.add(imageLabel2);
		
		JLabel idLabel21 = adoptLabel("Enter Pet ID:");
		idLabel21.setBounds(40,630,150,30);
		mainContainerPanel.add(idLabel21);
		
		JTextField idField22 = new JTextField();
		idField22.setBounds(170,630,120,30);
		mainContainerPanel.add(idField22);
		
		JLabel chooseLabel22 = adoptLabel("Pet Type:");
		chooseLabel22.setBounds(320,630,150,30);
		mainContainerPanel.add(chooseLabel22);
		
		JComboBox<String> comboPet2 = new JComboBox<String>(tablePet);
		comboPet2.setBounds(450,630,120,30);
		comboPet2.setSelectedIndex(-1);
		mainContainerPanel.add(comboPet2);
		
		JLabel chooseLabel23 = adoptLabel("Choose Status:");
		chooseLabel23.setBounds(320,670,150,30);
		mainContainerPanel.add(chooseLabel23);
		
		String status[]= {"For Adoption", "Adopted"};
		JComboBox<String> comboAdd = new JComboBox<String>(status);
		comboAdd.setBounds(450,670,120,30);
		comboAdd.setSelectedIndex(-1);
		mainContainerPanel.add(comboAdd);
		
		//verify pet history
		JButton accStatusButton3 = accountButton("APPROVE", "D:\\3rd Year BSCPE (2nd SEMESTER)\\Software Design\\ICONS VETA\\icons8-change-100.png");
		accStatusButton3.setBounds(170,670,120,30);
		accStatusButton3.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String get_id = idField22.getText();
				String get_type= (String) comboPet2.getSelectedItem();
				String get_status = (String) comboAdd.getSelectedItem();
				if(get_id.isEmpty() || !get_id.matches("\\d+") || get_type==null || get_status == null){
				    System.out.println("EMPTY");
				    comboPet2.setSelectedIndex(-1);
				    comboAdd.setSelectedIndex(-1);
				    idField22.setText("");
					return;
				} 
				int id = Integer.parseInt(get_id);
				//cat
				if(comboPet2.getSelectedIndex()==0) {
					//For adoption
					if(comboAdd.getSelectedIndex()==0) {
						try {
							Class.forName("com.mysql.cj.jdbc.Driver");
							Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylogindb","root","burog181");
							String sql = "SELECT * FROM cat_for_adoption WHERE cat_id=?";
							PreparedStatement pstmt = con.prepareStatement(sql);
							pstmt.setInt(1, id);
							ResultSet rs = pstmt.executeQuery();
							if(rs.next()) {
								get_status = "For Adoption";
								String briefing_status= "In Briefing";
								String retrieved_status = rs.getString("cat_status");
								if(retrieved_status.equals(get_status)) {
									comboPet2.setSelectedIndex(-1);
								    comboAdd.setSelectedIndex(-1);
								    idField22.setText("");
									JOptionPane.showMessageDialog(null, "Pet is already under for adoption!");
								}else if(retrieved_status.equals(briefing_status)){
									comboPet2.setSelectedIndex(-1);
								    comboAdd.setSelectedIndex(-1);
								    idField22.setText("");
									JOptionPane.showMessageDialog(null, "Pet is taken and now at briefing stage!");
								}else {
									sql = "UPDATE cat_for_adoption SET cat_status=? WHERE cat_id=?";
									pstmt = con.prepareStatement(sql);
									pstmt.setString(1, get_status);
									pstmt.setInt(2, id);
									pstmt.executeUpdate();
									comboPet2.setSelectedIndex(-1);
								    comboAdd.setSelectedIndex(-1);
								    idField22.setText("");
									JOptionPane.showMessageDialog(null, "Cat "+id+ " is now ready for adoption!");
									frame1.setVisible(false);
									adopt(1);
								}
							}else {
								comboPet2.setSelectedIndex(-1);
							    comboAdd.setSelectedIndex(-1);
							    idField22.setText("");
								JOptionPane.showMessageDialog(null, "Cat not found!");
							}
						} catch (ClassNotFoundException | SQLException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}	
						//ADopted
					}else {
						try {
							Class.forName("com.mysql.cj.jdbc.Driver");
							Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylogindb","root","burog181");
							String sql = "SELECT * from adopted_pet WHERE pet_id=? AND pet_type=?";
							PreparedStatement pstmt = con.prepareStatement(sql);
							pstmt.setInt(1, id);
							pstmt.setString(2, get_type);
							ResultSet rs = pstmt.executeQuery();
							if (rs.next()) {
								String newStatus = rs.getString("status");
								if(newStatus.equals("For Meet-up")) {
									sql = "UPDATE adopted_pet set status=? WHERE pet_id=? AND pet_type=?";
									pstmt = con.prepareStatement(sql);
									pstmt.setString(1, get_status);
									pstmt.setInt(2, id);
									pstmt.setString(3, get_type);
									pstmt.executeUpdate();
									
									sql = "UPDATE cat_for_adoption SET cat_status=? WHERE cat_id=?";
									pstmt = con.prepareStatement(sql);
									pstmt.setString(1, "Adopted");
									pstmt.setInt(2, id);
									pstmt.executeUpdate();

									idField2.setText("");
									comboPet2.setSelectedIndex(-1);
								    comboAdd.setSelectedIndex(-1);
								    idField22.setText("");
								    JOptionPane.showMessageDialog(null, "Succesfully updated!");
								}else if(newStatus.equals("Adopted")){
									JOptionPane.showMessageDialog(null, "The process has already been completed.");
									idField2.setText("");
									comboPet2.setSelectedIndex(-1);
								    comboAdd.setSelectedIndex(-1);
								    idField22.setText("");
								}else {
									JOptionPane.showMessageDialog(null, "An appointment visit should be done first.");
									idField2.setText("");
									comboPet2.setSelectedIndex(-1);
								    comboAdd.setSelectedIndex(-1);
								    idField22.setText("");
								}
							}else {
								JOptionPane.showMessageDialog(null, "No data found for this user.");
								idField2.setText("");
								comboPet2.setSelectedIndex(-1);
							    comboAdd.setSelectedIndex(-1);
							    idField22.setText("");
							}
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					//dog
				}else{
					if(comboAdd.getSelectedIndex()==0) {
						try {
						    Class.forName("com.mysql.cj.jdbc.Driver");
						    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylogindb","root","burog181");
						    String sql = "SELECT * FROM dog_for_adoption WHERE dog_id=?";
						    PreparedStatement pstmt = con.prepareStatement(sql);
						    pstmt.setInt(1, id);
						    ResultSet rs = pstmt.executeQuery();
						    if(rs.next()) {
						    	get_status = "For Adoption";
						    	String briefing_status= "In Briefing";
						        String retrieved_status = rs.getString("dog_status");
						        if(retrieved_status.equals(get_status)) {
						        	comboPet2.setSelectedIndex(-1);
								    comboAdd.setSelectedIndex(-1);
								    idField22.setText("");
						        	JOptionPane.showMessageDialog(null, "User pet record is already verified");
						        }else if(retrieved_status.equals(briefing_status)){
						        	comboPet2.setSelectedIndex(-1);
								    comboAdd.setSelectedIndex(-1);
								    idField22.setText("");
						        	JOptionPane.showMessageDialog(null, "Pet is taken and now at briefing stage!");
						        }else {
						        	sql = "UPDATE dog_for_adoption SET dog_status=? WHERE dog_id=?";
						            pstmt = con.prepareStatement(sql);
						            pstmt.setString(1, get_status);
						            pstmt.setInt(2, id);
						            pstmt.executeUpdate();
						            
						            
						            comboPet2.setSelectedIndex(-1);
								    comboAdd.setSelectedIndex(-1);
								    idField22.setText("");
						            JOptionPane.showMessageDialog(null, "Dog "+id+ " is now ready for adoption!");
						            frame1.setVisible(false);
									adopt(1);
						        }
						    }else {
						    	comboPet2.setSelectedIndex(-1);
							    comboAdd.setSelectedIndex(-1);
							    idField22.setText("");
						        JOptionPane.showMessageDialog(null, "Dog not found!");
						    }
						} catch (ClassNotFoundException | SQLException | IOException e1) {
						    e1.printStackTrace();
						}
					}else {
						try {
							Class.forName("com.mysql.cj.jdbc.Driver");
							Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylogindb","root","burog181");
							String sql = "SELECT * from adopted_pet WHERE pet_id=? AND pet_type=?";
							PreparedStatement pstmt = con.prepareStatement(sql);
							pstmt.setInt(1, id);
							pstmt.setString(2, get_type);
							ResultSet rs = pstmt.executeQuery();
							if (rs.next()) {
								String newStatus = rs.getString("status");
								if(newStatus.equals("For Meet-up")) {
									sql = "UPDATE adopted_pet set status=? WHERE pet_id=? AND pet_type=?";
									pstmt = con.prepareStatement(sql);
									pstmt.setString(1, get_status);
									pstmt.setInt(1, id);
									pstmt.setString(2, get_type);
									pstmt.executeUpdate();
									
									sql = "UPDATE dog_for_adoption SET status=? WHERE dog_id=?";
									pstmt = con.prepareStatement(sql);
									pstmt.setString(1, "Adopted");
									pstmt.setInt(2, id);
									pstmt.executeUpdate();
									idField2.setText("");
									comboPet2.setSelectedIndex(-1);
								    comboAdd.setSelectedIndex(-1);
								    idField22.setText("");
								    JOptionPane.showMessageDialog(null, "Succesfully updated!");
								}else if(newStatus.equals("Adopted")){
									JOptionPane.showMessageDialog(null, "The process has already been completed.");
									idField2.setText("");
									comboPet2.setSelectedIndex(-1);
								    comboAdd.setSelectedIndex(-1);
								    idField22.setText("");
								}else {
									JOptionPane.showMessageDialog(null, "An appointment visit should be done first.");
									idField2.setText("");
									comboPet2.setSelectedIndex(-1);
								    comboAdd.setSelectedIndex(-1);
								    idField22.setText("");
								}
							}else {
								JOptionPane.showMessageDialog(null, "No data found for this user.");
								idField2.setText("");
								comboPet2.setSelectedIndex(-1);
							    comboAdd.setSelectedIndex(-1);
							    idField22.setText("");
							}
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
						
			}
		});
		mainContainerPanel.add(accStatusButton3);
		
		JButton clearButton21 = accountButton("CLEAR", "D:\\3rd Year BSCPE (2nd SEMESTER)\\Software Design\\ICONS VETA\\icons8-clear-symbol-100.png");
		clearButton21.setBounds(40,670,120,30);
		clearButton21.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				idField.setText("");
				comboTime.setSelectedIndex(-1);
				dateChooser.setDate(null);

			}
		});
		mainContainerPanel.add(clearButton21);
				
		
		
		//------------------------add record in medical history of pets------------------------
		JLabel addLabel = adoptLabel("ADD PET FOR ADOPTION");
		addLabel.setBounds(605,380,300,30);
		mainContainerPanel.add(addLabel);
		
		JLabel idLabel9 = adoptLabel("Pet Type:");
		idLabel9.setBounds(590,410,150,30);
		mainContainerPanel.add(idLabel9);
		
		String petType[]= {"Cat","Dog"};
		JComboBox<String> comboAddPet = new JComboBox<String>(petType);
		comboAddPet.setBounds(720,410,120,30);
		comboAddPet.setSelectedIndex(-1);
		mainContainerPanel.add(comboAddPet);
		
		JLabel genderLabel = adoptLabel("Gender:");
		genderLabel.setBounds(590,450,150,30);
		mainContainerPanel.add(genderLabel);
		
		JTextField genderField = new JTextField();
		genderField.setBounds(720,450,120,30);
		mainContainerPanel.add(genderField);
		
		JLabel ageLabel = adoptLabel("Age:");
		ageLabel.setBounds(590,490,150,30);
		mainContainerPanel.add(ageLabel);
		
		JTextField ageField = new JTextField();
		ageField.setBounds(720,490,120,30);
		mainContainerPanel.add(ageField);
		
		JLabel descLabel = adoptLabel("Description:");
		descLabel.setBounds(590,530,150,30);
		mainContainerPanel.add(descLabel);
		
		JTextField descField = new JTextField();
		descField.setBounds(720,530,120,30);
		mainContainerPanel.add(descField);
		
		JLabel addimageLabel = adoptLabel("Choose Image:");
		addimageLabel.setBounds(590,570,150,30);
		mainContainerPanel.add(addimageLabel);
		
		JButton browsePhoto = accountButton("BROWSE","");
		browsePhoto.setBounds(720,570,120,30);
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
					Image img_cat = imageIcon.getImage().getScaledInstance(360,490,Image.SCALE_SMOOTH);
					imageContainerLabel.setIcon(new ImageIcon(img_cat));
					s = path;
				}else if (result == JFileChooser.CANCEL_OPTION) {
					System.out.println("No data inserted!");
				}
			}
		});
		mainContainerPanel.add(browsePhoto);
		
		// button to add pet in adoption
		JButton accStatusButton2 = accountButton("ADD", "D:\\3rd Year BSCPE nd SEMESTER)\\Software Design\\ICO(2NS VETA\\icons8-change-100.png");
		accStatusButton2.setBounds(720,610,120,30);
		accStatusButton2.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String pet_type = (String) comboAddPet.getSelectedItem();
				String gender = genderField.getText();
				String get_desc = descField.getText();
				String age = ageField.getText();
				
				if(pet_type == null || gender.isBlank() || get_desc.isBlank() || age.isEmpty() || s==null ){
				   JOptionPane.showMessageDialog(null, "Complete the data needed!");
					return;
				} 
				if(comboAddPet.getSelectedIndex()==0) {
					try {
						String status = "For Adoption";
						InputStream is = new FileInputStream(new File(s));
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylogindb","root","burog181");
						String sql = "INSERT INTO cat_for_adoption SET cat_age=?, cat_gender=?, cat_description=?, cat_image=?, cat_status=?";
						PreparedStatement pstmt = con.prepareStatement(sql);
						pstmt.setString(1, age);
						pstmt.setString(2, gender);
						pstmt.setString(3, get_desc);
						pstmt.setBlob(4, is);
						pstmt.setString(5, status);
						pstmt.executeUpdate();	
						comboAddPet.setSelectedIndex(-1);
						genderField.setText("");
						descField.setText("");
						ageField.setText("");
						
					} catch (ClassNotFoundException | SQLException | FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}		
				}else {
					try {
						String status = "For Adoption";
						InputStream is = new FileInputStream(new File(s));
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylogindb","root","burog181");
						String sql = "INSERT INTO dog_for_adoption SET dog_age=?, dog_gender=?, dog_description=?, dog_image=?, dog_status=?";
						PreparedStatement pstmt = con.prepareStatement(sql);
						pstmt.setString(1, age);
						pstmt.setString(2, gender);
						pstmt.setString(3, get_desc);
						pstmt.setBlob(4, is);
						pstmt.setString(5, status);
						pstmt.executeUpdate();	
						comboAddPet.setSelectedIndex(-1);
						genderField.setText("");
						descField.setText("");
						ageField.setText("");
					} catch (ClassNotFoundException | SQLException | FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}		
				}
				
						
			}
		});
		mainContainerPanel.add(accStatusButton2);
		
		JButton clearButton211 = accountButton("CLEAR", "D:\\3rd Year BSCPE (2nd SEMESTER)\\Software Design\\ICONS VETA\\icons8-clear-symbol-100.png");
		clearButton211.setBounds(590,610,120,30);
		clearButton211.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				comboAddPet.setSelectedIndex(-1);
				genderField.setText("");
				descField.setText("");
				ageField.setText("");
			}
		});
		mainContainerPanel.add(clearButton211);
		
//--------------------------------------------------------------------
		JPanel medicalRecordPanel = new JPanel();
		medicalRecordPanel.setBounds(20,230,830,150);
		medicalRecordPanel.setBackground(Color.decode("#616463"));
		mainContainerPanel.add(medicalRecordPanel);

		String[] columnTitle3 = {"Pet ID", "Type", "Age", "Gender", "Owner", "Owner ID", "Status"};
		DefaultTableModel model3 = new DefaultTableModel();
		JTable medicalRecordTable = new JTable(model3);
		JScrollPane spane3 = new JScrollPane(medicalRecordTable);
		medicalRecordTable.setOpaque(true);
		medicalRecordTable.setBackground(Color.decode("#f2f2f2"));
		medicalRecordTable.setRowHeight(20);
		medicalRecordTable.setEnabled(false);
		medicalRecordTable.setPreferredScrollableViewportSize(new Dimension(810,120));
		medicalRecordTable.getTableHeader().setBackground(new Color(30,30,30));
		medicalRecordTable.getTableHeader().setForeground(new Color(200,200,200));

		for(int i = 0; i <= columnTitle3.length-1; i++) {
		    model3.addColumn(columnTitle3[i]);
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
		for (int i = 0; i < medicalRecordTable.getColumnCount(); i++) {
		    medicalRecordTable.getColumnModel().getColumn(i).setCellRenderer(customRenderer3);
		}

		// Calculate the preferred width of each column
		for (int column = 0; column < medicalRecordTable.getColumnCount(); column++) {
		    int maxWidth = 0;
		    TableColumn tableColumn = medicalRecordTable.getColumnModel().getColumn(column);
		    for (int row = 0; row < medicalRecordTable.getRowCount(); row++) {
		        TableCellRenderer cellRenderer = medicalRecordTable.getCellRenderer(row, column);
		        Object value = medicalRecordTable.getValueAt(row, column);
		        Component cellComponent = cellRenderer.getTableCellRendererComponent(medicalRecordTable, value, false, false, row, column);
		        maxWidth = Math.max(maxWidth, cellComponent.getPreferredSize().width);
		    }
		    tableColumn.setPreferredWidth(maxWidth);
		}
		medicalRecordPanel.add(new JScrollPane(medicalRecordTable));
		
		
		// Show field
		JTextField searchField = new JTextField();
		searchField.setBounds(980,15,130,30);
		mainContainerPanel.add(searchField);
		
		JButton clearDisplayBtn = accountButton("CLEAR TABLE", "");
		clearDisplayBtn.setBounds(20,15,130,30);
		clearDisplayBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				model.setRowCount(0);
				model3.setRowCount(0);
			}
		});
		mainContainerPanel.add(clearDisplayBtn);
		
		JButton showAllUser = accountButton("SHOW ALL", "");
		showAllUser.setBounds(170,15,130,30);
		showAllUser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-gene rated method stub
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylogindb","root","burog181");
					String sql = "SELECT * FROM cat_for_adoption ORDER BY CASE cat_status " +
					        "WHEN 'For Meet-up' THEN 1 " +
					        "WHEN 'In Briefing' THEN 2 " +
					        "WHEN 'For Adoption' THEN 3 " +
					        "ELSE 4 " +
					        "END";
					PreparedStatement pstmt= con.prepareStatement(sql);
					ResultSet rs = pstmt.executeQuery();
					while(rs.next()) {
						String type = "Cat";
						String cat_id = rs.getString("cat_id");
						String age = rs.getString("cat_age");
						String gender = rs.getString("cat_gender");
						String owner = rs.getString("cat_new_owner");
						String owner_id = rs.getString("cat_owner_id");
						String status= rs.getString("cat_status");
						String values[]= {cat_id,type, age,gender,owner,owner_id,status};
						model3.addRow(values);
					}
					
					sql = "SELECT * from dog_for_adoption ORDER BY CASE dog_status "+
							"WHEN 'For Meet-up' THEN 1 " +
					        "WHEN 'In Briefing' THEN 2 " +
					        "WHEN 'For Adoption' THEN 3 " +
					        "ELSE 4 " +
					        "END";
					pstmt= con.prepareStatement(sql);
					rs = pstmt.executeQuery();
					while(rs.next()) {
					    String type = "Dog";
					    String dog_id = rs.getString("dog_id");
					    String age = rs.getString("dog_age");
					    String gender = rs.getString("dog_gender");
					    String owner = rs.getString("dog_new_owner");
					    String owner_id = rs.getString("dog_owner_id");
					    String status= rs.getString("dog_status");
					    String values[]= {dog_id,type, age,gender,owner,owner_id,status};
					    model3.addRow(values);
					}
					
					
					sql = "SELECT * FROM adopted_pet ORDER BY CASE status " +
						      "WHEN 'In Scheduling' THEN 1 " +
						      "WHEN 'For Meet-up' THEN 2 " +
						      "WHEN 'Adopted' THEN 3 " +
						      "ELSE 4 " +
						      "END";
						pstmt = con.prepareStatement(sql);
						rs = pstmt.executeQuery();
					pstmt = con.prepareStatement(sql);
					rs = pstmt.executeQuery();
					while(rs.next()) {
						int i_adoption_id = rs.getInt("adoption_id");
						int i_pet_id = rs.getInt("pet_id");
						int i_owner_id = rs.getInt("owner_id");
						String adoption_id = String.valueOf(i_adoption_id);
						String pet_id = String.valueOf(i_pet_id);
						String owner_id = String.valueOf(i_owner_id);
						String pet_type = rs.getString("pet_type");
						String owner_name = rs.getString("owner_name");
						String date_appointment = rs.getString("date_appointment");
						String time_appointment = rs.getString("time_appointment");
						String status = rs.getString("status");
						
						String values[]= {adoption_id, pet_id, pet_type,owner_id, owner_name, date_appointment, time_appointment,status};
						model.addRow(values);
								
					}
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mainContainerPanel.add(showAllUser);
			
		JButton searchButton = accountButton("SEARCH", "");
		searchButton.setBounds(1120,15,130,30);
		searchButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String s_id = searchField.getText();
				if(s_id.isBlank() || !s_id.matches("\\d+")) {
					searchField.setText("");
					return;
				}
				int id = Integer.parseInt(s_id);
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylogindb","root","burog181");
					String sql = "SELECT * FROM cat_for_adoption WHERE cat_id=? ORDER BY CASE cat_status " +
					        "WHEN 'In Briefing' THEN 1 " +
					        "WHEN 'For Adoption' THEN 2 " +
					        "ELSE 3 " +
					        "END ";
					PreparedStatement pstmt= con.prepareStatement(sql);
					pstmt.setInt(1, id);
					ResultSet rs = pstmt.executeQuery();
					while(rs.next()) {
						String type = "Cat";
						String cat_id = rs.getString("cat_id");
						String age = rs.getString("cat_age");
						String gender = rs.getString("cat_gender");
						String owner = rs.getString("cat_new_owner");
						String owner_id = rs.getString("cat_owner_id");
						String status= rs.getString("cat_status");
						String values[]= {cat_id,type, age,gender,owner,owner_id,status};
						model3.addRow(values);
					}
					
					sql = "SELECT * FROM dog_for_adoption WHERE dog_id=? ORDER BY CASE dog_status " +
					        "WHEN 'In Briefing' THEN 1 " +
					        "WHEN 'For Adoption' THEN 2 " +
					        "ELSE 3 " +
					        "END";

					pstmt= con.prepareStatement(sql);
					pstmt.setInt(1, id);
					rs = pstmt.executeQuery();
					while(rs.next()) {
					    String type = "Dog";
					    String dog_id = rs.getString("dog_id");
					    String age = rs.getString("dog_age");
					    String gender = rs.getString("dog_gender");
					    String owner = rs.getString("dog_new_owner");
					    String owner_id = rs.getString("dog_owner_id");
					    String status= rs.getString("dog_status");
					    String values[]= {dog_id,type, age,gender,owner,owner_id,status};
					    model3.addRow(values);
					}
					
					
					sql = "SELECT * FROM adopted_pet WHERE pet_id=? ORDER BY CASE status " +
						      "WHEN 'In Scheduling' THEN 1 " +
						      "WHEN 'For Meet-up' THEN 2 " +
						      "WHEN 'Adopted' THEN 3 " +
						      "ELSE 4 " +
						      "END";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, id);
					rs = pstmt.executeQuery();
					while(rs.next()) {
						int i_adoption_id = rs.getInt("adoption_id");
						int i_pet_id = rs.getInt("pet_id");
						int i_owner_id = rs.getInt("owner_id");
						String adoption_id = String.valueOf(i_adoption_id);
						String pet_id = String.valueOf(i_pet_id);
						String owner_id = String.valueOf(i_owner_id);
						String pet_type = rs.getString("pet_type");
						String owner_name = rs.getString("owner_name");
						String date_appointment = rs.getString("date_appointment");
						String time_appointment = rs.getString("time_appointment");
						String status = rs.getString("status");
						
						String values[]= {adoption_id, pet_id, pet_type,owner_id, owner_name, date_appointment, time_appointment,status};
						model.addRow(values);
								
					}
					searchField.setText("");
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					
			}
		});
		mainContainerPanel.add(searchButton);
				
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
					ad.adopt(user_id);
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

		
	}

//	public static void main(String[] args) {
public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
