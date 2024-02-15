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
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import com.mysql.cj.jdbc.Blob;
import com.toedter.calendar.JDateChooser;

import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;


public class Admin_AddRecord {
	
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
	
	public JLabel recordLabel(String title) {
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
	
	public void record(int user_id) throws SQLException, IOException{
		
		String tabDescription = "PET RECORD";
		
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
		
		JLabel fTableLabel = recordLabel("PET IDENTIFICATION");
		fTableLabel.setBounds(530,40,250,30);
		mainContainerPanel.add(fTableLabel);
		
		JLabel fTableLabel2 = recordLabel("PETS MEDICAL RECORD HISTORY");
		fTableLabel2.setBounds(260,220,350,30);
		mainContainerPanel.add(fTableLabel2);
		
		JPanel accountsPanel = new JPanel();
		accountsPanel.setBounds(20,70,1230,130);
		accountsPanel.setBackground(Color.decode("#616463"));
		mainContainerPanel.add(accountsPanel);
		
		String[] columnTitle1 = {"ID","Owner", "Email", "PhoneNumber", "Pet","Gender", "Name", "Breed", "Bday", "Acc Status"};
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
				JLabel acc_statusLabel = recordLabel("CHANGE STATUS");
				acc_statusLabel.setBounds(100,470,180,30);
				mainContainerPanel.add(acc_statusLabel);
				
				JLabel idLabel = recordLabel("Enter User ID:");
				idLabel.setBounds(40,510,150,30);
				mainContainerPanel.add(idLabel);
				
				JTextField idField = new JTextField();
				idField.setBounds(170,510,120,30);
				mainContainerPanel.add(idField);
				
				JLabel chooseLabel = recordLabel("Medical ID:");
				chooseLabel.setBounds(40,550,150,30);
				mainContainerPanel.add(chooseLabel);
				
				JTextField hisidField = new JTextField();
				hisidField.setBounds(170,550,120,30);
				mainContainerPanel.add(hisidField);
				
				JLabel statusLabel = recordLabel("Choose Status:");
				statusLabel.setBounds(40,590,150,30);
				mainContainerPanel.add(statusLabel);
				
				String statusList[] = {"Verified","Disapproved"};
				JComboBox<String> comboStatus = new JComboBox<String>(statusList);
				comboStatus.setBounds(170,590,120,30);
				comboStatus.setSelectedIndex(-1);
				mainContainerPanel.add(comboStatus);
				//change status button
				JButton accStatusButton = accountButton("APPLY", "D:\\3rd Year BSCPE (2nd SEMESTER)\\Software Design\\ICONS VETA\\icons8-change-100.png");
				accStatusButton.setBounds(170,630,120,30);
				accStatusButton.addActionListener( new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						String get_id = idField.getText();
						String get_med_id = hisidField.getText();
						String get_status = (String) comboStatus.getSelectedItem();
						
						if(get_id.isEmpty() || !get_id.matches("\\d+") || get_status==null || get_status==null){
						    System.out.println("EMPTY");
						    comboStatus.setSelectedIndex(-1);
						    hisidField.setText("");
						    idField.setText("");
							return;
						} 
						int medID = Integer.parseInt(get_med_id);
						int id = Integer.parseInt(get_id);
						
								try {
									Class.forName("com.mysql.cj.jdbc.Driver");
									Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylogindb","root","burog181");
									String sql = "SELECT * FROM customer_pet_medhistory WHERE user_id=?";
									PreparedStatement pstmt = con.prepareStatement(sql);
									pstmt.setInt(1, id);
									ResultSet rs = pstmt.executeQuery();
									if(rs.next()) {
										String retrieved_status = rs.getString("status");						
										if(retrieved_status.equals(get_status)) {
											comboStatus.setSelectedIndex(-1);
											hisidField.setText("");
										    idField.setText("");
											JOptionPane.showMessageDialog(null, "User pet record is already verified");
										}else {
											sql = "UPDATE customer_pet_medhistory SET status=? WHERE user_id=? AND medical_id=?";
											pstmt = con.prepareStatement(sql);
											pstmt.setString(1, get_status);
											pstmt.setInt(2, id);
											pstmt.setInt(3, medID);
											pstmt.executeUpdate();
											comboStatus.setSelectedIndex(-1);
											hisidField.setText("");
										    idField.setText("");
											JOptionPane.showMessageDialog(null, "User "+id+ " pet's medical record is approved! It will now display on their records!");
											frame1.setVisible(false);
										}
									}else {
										comboStatus.setSelectedIndex(-1);
										hisidField.setText("");
									    idField.setText("");
										JOptionPane.showMessageDialog(null, "User not found!");
									}
								} catch (ClassNotFoundException | SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}		
					}
				});
				mainContainerPanel.add(accStatusButton);
				
				JButton clearButton = accountButton("CLEAR", "D:\\3rd Year BSCPE (2nd SEMESTER)\\Software Design\\ICONS VETA\\icons8-clear-symbol-100.png");
				clearButton.setBounds(40,630,120,30);
				clearButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						idField.setText("");
						comboStatus.setSelectedIndex(-1);
						hisidField.setText("");
					}
				});
				mainContainerPanel.add(clearButton);
				
		
		//For choosing image
		JLabel imageLabel = recordLabel("IMAGE VIEWER");
		imageLabel.setBounds(380,470,150,30);
		mainContainerPanel.add(imageLabel);
		
		JLabel idLabel2 = recordLabel("Enter User ID:");
		idLabel2.setBounds(320,510,150,30);
		mainContainerPanel.add(idLabel2);
		
		JTextField idField2 = new JTextField();
		idField2.setBounds(450,510,120,30);
		mainContainerPanel.add(idField2);
		
		JLabel chooseLabel2 = recordLabel("Medical ID:");
		chooseLabel2.setBounds(320,550,150,30);
		mainContainerPanel.add(chooseLabel2);
		
		JTextField idField3 = new JTextField();
		idField3.setBounds(450,550,120,30);
		mainContainerPanel.add(idField3);
		
		JLabel detailLabel = recordLabel("");
		detailLabel.setBounds(305,705,450,30);
		
		// IMAGE CONTAINER PANEL
		mainContainerPanel.add(detailLabel);
		Border border = BorderFactory.createLineBorder(Color.decode("#616463"), 5);
		JPanel imagePanel = new JPanel();
		imagePanel.setBorder(border);
		imagePanel.setBounds(865,210,380,510);
		imagePanel.setBackground(Color.decode("#4e4f50"));
		mainContainerPanel.add(imagePanel);
		//IMAGE CONTAINER LABEL
		JLabel imageContainerLabel = new JLabel("");
		imageContainerLabel.setPreferredSize(new Dimension(360,490));
		imagePanel.add(imageContainerLabel);
		//-----------------------------------------
		
		JButton searchImageBtn = accountButton("SEARCH", "D:\\3rd Year BSCPE (2nd SEMESTER)\\Software Design\\ICONS VETA\\icons8-search-100.png");
		searchImageBtn.setBounds(450,600,120,30);
		searchImageBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String get_id = idField2.getText();
				String med_id = idField3.getText();
				String concat=null;
				if(get_id.isEmpty() || !get_id.matches("\\d+") || med_id.isEmpty() || !med_id.matches("\\d+")){
					idField2.setText("");
					idField3.setText("");
					return;
				} 
				int id = Integer.parseInt(get_id);
				int medID = Integer.parseInt(med_id);
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylogindb","root","burog181");
						String sql = "SELECT * FROM customer_pet_medhistory WHERE user_id=? AND medical_id=?";
						PreparedStatement pstmt = con.prepareStatement(sql);
						pstmt.setInt(1, id);
						pstmt.setInt(2, medID);
						ResultSet rs = pstmt.executeQuery();
						if(rs.next()) {
							Blob img = (Blob) rs.getBlob("record_image");
							InputStream inputStream = img.getBinaryStream();
					        byte[] imageBytes = inputStream.readAllBytes();
					        ImageIcon imageIcon = new ImageIcon(imageBytes);
					        Image scaledImage = imageIcon.getImage().getScaledInstance(360,490,Image.SCALE_SMOOTH);
					        imageContainerLabel.setIcon(new ImageIcon(scaledImage));
							idField2.setText("");
							idField3.setText("");
							concat = "Medical History Image | User ID: "+get_id;
							detailLabel.setText(concat);
						}else {
							idField2.setText("");
							idField3.setText("");
							JOptionPane.showMessageDialog(null, "User not found!");
						}
					} catch (ClassNotFoundException | SQLException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
		});
		mainContainerPanel.add(searchImageBtn);
		
		JButton clearButton2 = accountButton("CLEAR", "D:\\3rd Year BSCPE (2nd SEMESTER)\\Software Design\\ICONS VETA\\icons8-clear-symbol-100.png");
		clearButton2.setBounds(320,600,120,30);
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
		
		//------------------------add record in medical history of pets------------------------
		JLabel addLabel = recordLabel("ADD RECORD");
		addLabel.setBounds(650,470,150,30);
		mainContainerPanel.add(addLabel);
		
		JLabel idLabel9 = recordLabel("Enter User ID:");
		idLabel9.setBounds(590,510,150,30);
		mainContainerPanel.add(idLabel9);
		
		JTextField addidField = new JTextField();
		addidField.setBounds(720,510,120,30);
		mainContainerPanel.add(addidField);
		
		JLabel descLabel = recordLabel("Description:");
		descLabel.setBounds(590,550,150,30);
		mainContainerPanel.add(descLabel);
		
		JTextField descField = new JTextField();
		descField.setBounds(720,550,120,30);
		mainContainerPanel.add(descField);
		
		JLabel addimageLabel = recordLabel("Choose Image:");
		addimageLabel.setBounds(590,590,150,30);
		mainContainerPanel.add(addimageLabel);
		
		JButton browsePhoto = accountButton("BROWSE","");
		browsePhoto.setBounds(720,590,120,30);
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
		
		JLabel adddateLabel = recordLabel("Date Issued:");
		adddateLabel.setBounds(590,630,150,30);
		mainContainerPanel.add(adddateLabel);
		
		JDateChooser dateChooser = new JDateChooser();
		Calendar calendar = Calendar.getInstance();
		dateChooser.setMinSelectableDate(calendar.getTime());
	    calendar.add(Calendar.YEAR, 1);
	    dateChooser.setMaxSelectableDate(calendar.getTime());
		dateChooser.setLocale(Locale.US);
		dateChooser.setBounds(720,630,120,30);
		mainContainerPanel.add(dateChooser);
		
		JButton accStatusButton2 = accountButton("ADD", "D:\\3rd Year BSCPE nd SEMESTER)\\Software Design\\ICO(2NS VETA\\icons8-change-100.png");
		accStatusButton2.setBounds(720,670,120,30);
		accStatusButton2.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String get_id = addidField.getText();
				String get_desc = descField.getText();
				Date date = dateChooser.getDate();
				if(get_id.isEmpty() || !get_id.matches("\\d+") || get_desc.isEmpty() || date==null || s==null){
				    System.out.println("EMPTY");
				   addidField.setText("");
				   descField.setText("");
				   dateChooser.setDate(null);
					return;
				} 
				SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
				String s_date = dateformat.format(date);
				int id = Integer.parseInt(get_id);
				
						try {
							InputStream is = new FileInputStream(new File(s));
							Class.forName("com.mysql.cj.jdbc.Driver");
							Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylogindb","root","burog181");
							String sql = "INSERT INTO customer_pet_medhistory SET clinic_name=?, description=?, date_issued=?, record_image=?, status=?, user_id=?";
							PreparedStatement pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "Veterinary-3202 Clinic");
							pstmt.setString(2, get_desc);
							pstmt.setString(3, s_date);
							pstmt.setBlob(4, is);
							pstmt.setString(5, "Verified");
							pstmt.setInt(6, id);
							pstmt.executeUpdate();	
						} catch (ClassNotFoundException | SQLException | FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}		
			}
		});
		mainContainerPanel.add(accStatusButton2);
		
		JButton clearButton21 = accountButton("CLEAR", "D:\\3rd Year BSCPE (2nd SEMESTER)\\Software Design\\ICONS VETA\\icons8-clear-symbol-100.png");
		clearButton21.setBounds(590,670,120,30);
		clearButton21.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			
			}
		});
		mainContainerPanel.add(clearButton21);
		
		
	
		
//--------------------------------------------------------------------
		JPanel medicalRecordPanel = new JPanel();
		medicalRecordPanel.setBounds(20,250,830,200);
		medicalRecordPanel.setBackground(Color.decode("#616463"));
		mainContainerPanel.add(medicalRecordPanel);

		String[] columnTitle3 = {"Medical ID","User ID", "Clinic Name", "Description", "Date Issued", "Status"};
		DefaultTableModel model3 = new DefaultTableModel();
		JTable medicalRecordTable = new JTable(model3);
		JScrollPane spane3 = new JScrollPane(medicalRecordTable);
		medicalRecordTable.setOpaque(true);
		medicalRecordTable.setBackground(Color.decode("#f2f2f2"));
		medicalRecordTable.setRowHeight(20);
		medicalRecordTable.setEnabled(false);
		medicalRecordTable.setPreferredScrollableViewportSize(new Dimension(800,170));
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
		
		JButton showAllUser = accountButton("SHOW ALL USER", "");
		showAllUser.setBounds(170,15,150,30);
		showAllUser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylogindb","root","burog181");
					String sql = "SELECT * FROM customer_pet_medhistory";
					PreparedStatement pstmt = con.prepareStatement(sql);
					ResultSet rs = pstmt.executeQuery();
					while(rs.next()) {
						int i_user_id = rs.getInt("user_id");
						String s_user_id = String.valueOf(i_user_id);
						int i_med_id = rs.getInt("medical_id");
						String s_med_id = String.valueOf(i_med_id);
						String clinic_name = rs.getString("clinic_name");
						String description = rs.getString("description");
						String date= rs.getString("date_issued");
						String status= rs.getString("status");
						String values[]= {s_med_id, s_user_id,clinic_name,description,date,status };
						model3.addRow(values);
					}
					
					
					sql = "SELECT * FROM customer_information";
					pstmt = con.prepareStatement(sql);
					rs = pstmt.executeQuery();
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
				String getID = searchField.getText();
				if(getID.isEmpty() || !getID.matches("\\d+")) {
					searchField.setText("");
					JOptionPane.showMessageDialog(null, "No data found for this user");
					return;
				}
				int id = Integer.parseInt(getID);
			
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylogindb","root","burog181");
						String sql = "SELECT * FROM customer_pet_medhistory WHERE user_id=?";
						PreparedStatement pstmt = con.prepareStatement(sql);
						pstmt.setInt(1, id);
						ResultSet rs = pstmt.executeQuery();
						while(rs.next()) {
							int i_user_id = rs.getInt("user_id");
							String s_user_id = String.valueOf(i_user_id);
							int i_med_id = rs.getInt("medical_id");
							String s_med_id = String.valueOf(i_med_id);
							String clinic_name = rs.getString("clinic_name");
							String description = rs.getString("description");
							String date= rs.getString("date_issued");
							String status= rs.getString("status");
							String values[]= {s_med_id, s_user_id,clinic_name,description,date,status };
							model3.addRow(values);
						}
						
						
						sql = "SELECT * FROM customer_information WHERE user_id=?";
						pstmt = con.prepareStatement(sql);
						pstmt.setInt(1, id);
						rs = pstmt.executeQuery();
						if(rs.next()) {
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
						}else {
							JOptionPane.showMessageDialog(null, "User not found!");
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
