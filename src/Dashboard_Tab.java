import java.awt.BorderLayout;
import java.awt.Color;
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
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.text.SimpleDateFormat;

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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.toedter.calendar.JDateChooser;

public class Dashboard_Tab implements ActionListener{
	
	private JFrame frame1, petData;
	private JPanel navPanel, mainPanel, containerPanel, medicalPanel, petDetailPanel, historyPanel, tabPanel, petDataPanel;
	private JLabel vetaLabel, tabLabel;
	private JButton updateButton;
	private JButton editButton;
	private JDateChooser dateChooser;
	private String s; //image path
	private String s1;
	private String s2;
	public String notVerified = "For Verification";
	public String verified = "Verified";
	public String meet_status= "For Meet-up";
	public String approved_status = "Adopted";
	public String briefing_status = "In Briefing";
	public String order_status = "For Shipping";
	public String in_scheduling = "In Scheduling";
	
	private JLabel petDetail(String title) {
		JLabel petOwner = new JLabel(title);
		petOwner.setFont(new Font("SansSerif Bold", Font.PLAIN,15));
		petOwner.setPreferredSize(new Dimension(200,30));
		petOwner.setForeground(Color.decode("#dbdee1"));
		return petOwner;
	}
	
	private JLabel medicalLabel(String title) {
		JLabel label = new JLabel(title);
		label.setFont(new Font("SansSerif Bold", Font.PLAIN,18));
		label.setForeground(Color.decode("#dbdee1"));
		medicalPanel.add(label);
		return label;
	}
	
	private JButton medicalButton(String title, String name) {
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
// method for setting icon size into particular size
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

	private JLabel petLabels(String title) {
		JLabel label = new JLabel(title);
		label.setFont(new Font("SansSerif Bold", Font.PLAIN, 18));
		label.setForeground(Color.decode("#dbdee1"));
		petDataPanel.add(label);
		return label;
	}
	
	private JLabel petShowData(String title) {
		JLabel label = new JLabel(title);
		label.setFont(new Font("SansSerif", Font.PLAIN, 15));
		label.setPreferredSize(new Dimension(400,30));
		label.setForeground(Color.decode("#dbdee1"));
		return label;
	}
	
	private JTextField petTextFields() {
		JTextField textField = new JTextField();
		textField.setSize(200,200);
		textField.setFont(new Font("SansSerif",Font.PLAIN,15));
		petDataPanel.add(textField);
		return textField;
	}

	private JLabel verifyLabel(String title) {
		JLabel label = new JLabel(title);
		label.setFont(new Font("SansSerif", Font.PLAIN, 18));
		label.setForeground(Color.decode("#dbdee1"));
		return label;
	}
	
	
	private JPanel addPetHistory(String date, String description, String clinic, Blob record_image ) throws SQLException, IOException {
		
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 100,10));
		panel.setPreferredSize(new Dimension(200,270));
		panel.setBackground(Color.decode("#1e1f22"));
		
		Border border = BorderFactory.createLineBorder(Color.decode("#1e1f22"), 5);
		JLabel imageContainer = new JLabel("");
		InputStream inputStream = record_image.getBinaryStream();
        byte[] imageBytes = inputStream.readAllBytes();
        ImageIcon imageIcon = new ImageIcon(imageBytes);
        Image img_record = imageIcon.getImage().getScaledInstance(130,170,Image.SCALE_SMOOTH);
		imageContainer.setBackground(Color.red);
		imageContainer.setBorder(border);
		imageContainer.setIcon(new ImageIcon(img_record));
		imageContainer.setPreferredSize(new Dimension(130,170));
		panel.add(imageContainer);
		
		JLabel clinic_name = new JLabel(clinic);
		clinic_name.setForeground(Color.white);
		panel.add(clinic_name);
		
		JLabel record_date = new JLabel(date);
		record_date.setForeground(Color.white);
		panel.add(record_date);
		
		// view full detail of medical record of pet
		JButton viewDetail = medicalButton("VIEW FULL DETAIL","");
		viewDetail.setFont(new Font("SansSerif BOLD", Font.PLAIN,14));
		viewDetail.setPreferredSize(new Dimension(140,25));
		viewDetail.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFrame detailFrame = new JFrame("Pet Medical Record Detail");
				detailFrame.setSize(600,980);
				detailFrame.setLocationRelativeTo(null);
				detailFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				detailFrame.setVisible(true);
				
				JPanel detailPanel = new JPanel();
				detailPanel.setBackground(Color.decode("#313338"));
				detailPanel.setLayout(null);
				detailFrame.add(detailPanel);
				
				JPanel imagePanel = new JPanel();
				imagePanel.setBounds(20,20,550,750);
				imagePanel.setBackground(Color.decode("#313338"));
				detailPanel.add(imagePanel);
				
				JLabel showpetImage = verifyLabel("");
				InputStream inputStream;
				try {
					inputStream = record_image.getBinaryStream();
					byte[] imageBytes = inputStream.readAllBytes();
				} catch (SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	            ImageIcon imageIcon = new ImageIcon(imageBytes);
	            Image img_record = imageIcon.getImage().getScaledInstance(520,740,Image.SCALE_SMOOTH);
				showpetImage.setIcon(new ImageIcon(img_record));
				imagePanel.add(showpetImage);
				
				JPanel detailContainer = new JPanel(new FlowLayout(FlowLayout.CENTER, 30,30));
				detailContainer.setBounds(140,750,300,170);
				detailContainer.setBackground(Color.decode("#313338"));
				detailPanel.add(detailContainer);
				
				String ageConcat = "Clinic Name: "+clinic;
				JLabel showAge = verifyLabel(ageConcat);
				detailContainer.add(showAge);
				
				String genderConcat ="Visit Reason: "+description;
				JLabel showGender = verifyLabel(genderConcat);
				detailContainer.add(showGender);
				
	
				JLabel showDescription = verifyLabel("Record issued: "+date);
				detailContainer.add(showDescription);
			}
		});
		panel.add(viewDetail);
		return panel;	
	}
	
	
	// panel for viewing medical history of user's pet
	public void viewHistory(int user_id) {
		JFrame frame1 = new JFrame("Pet Medical Records");
		frame1.setSize(700,450);
		frame1.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame1.setLayout(null);
		frame1.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10,10,665,390);
		panel.setBackground(Color.decode("#313338"));
		panel.setLayout(null);
		frame1.add(panel);
		
		JLabel allRecords = verifyLabel("PET MEDICAL HISTORY RECORDS");
		allRecords.setBounds(200,0,350,30);
		allRecords.setFont(new Font("SansSerif Bold", Font.PLAIN, 18));
		panel.add(allRecords);
		
		JPanel recordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20,20));
		recordPanel.setBounds(35,35,600,325);
		recordPanel.setBackground(Color.decode("#4e4f50"));
		panel.add(recordPanel);
		// create a scroll pane and add the recordPanel to it
		JScrollPane scrollPane = new JScrollPane(recordPanel);

		// set the size and position of the scroll pane
		scrollPane.setBounds(35, 35, 600, 325);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

		// add the scroll pane to the main panel
		panel.add(scrollPane);

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylogindb","root","burog181");
			String sql = "SELECT * From customer_pet_medhistory WHERE user_id=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, user_id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
			    String history_status = rs.getString("status");
			    if (history_status != null) { // Add null check
			        String clinic_name = rs.getString("clinic_name");
			        String record_description = rs.getString("description");
			        java.sql.Date date = rs.getDate("date_issued");
			        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			        String formattedDate = dateFormat.format(date);
			        Blob record_image = rs.getBlob("record_image");
			        if(history_status.equals(verified)) {
			            recordPanel.add(addPetHistory(formattedDate, record_description, clinic_name, record_image));
			        } else {
			            System.out.println("Not verified");
			        }
			    }
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		frame1.setVisible(true);
	}
	
	//submit account verification for shipping purposes when user wants to buy an item
	public void accountVerification(int user_id) {
			
			JFrame verifyFrame = new JFrame("Pet Data");
			verifyFrame.setSize(600,720);
			
			verifyFrame.setLayout(null);
			verifyFrame.setLocationRelativeTo(null);
			verifyFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			
			JPanel verifyPanel = new JPanel();
			verifyPanel.setBounds(20,20,540,640);
			verifyPanel.setLayout(null);
			verifyPanel.setBackground(Color.decode("#313338"));
			verifyFrame.add(verifyPanel);
			
			// panel for showing image in insert photo
			Border border = BorderFactory.createLineBorder(Color.decode("#111111"), 5);
			JPanel userImagePanel = new JPanel();
			userImagePanel.setBackground(Color.decode("#dbdee1"));
			userImagePanel.setBorder(border);
			userImagePanel.setBounds(35,10,230,270);
			verifyPanel.add(userImagePanel);
			
			Border border2 = BorderFactory.createLineBorder(Color.decode("#111111"), 5);
			JPanel userValidIdPanel = new JPanel();
			userValidIdPanel.setBackground(Color.decode("#dbdee1"));
			userValidIdPanel.setBorder(border);
			userValidIdPanel.setBounds(280,10,230,270);
			verifyPanel.add(userValidIdPanel);
			
			//Label that gets the image
			JLabel yourImage = verifyLabel("");
			yourImage.setPreferredSize(new Dimension(215,255));
			userImagePanel.add(yourImage);
			
			JLabel ValidImage = verifyLabel("");
			ValidImage.setPreferredSize(new Dimension(215,255));
			userValidIdPanel.add(ValidImage);			

			JLabel petType = verifyLabel("INSERT YOUR PHOTO");
			petType.setBounds(50,280,200,30);
			verifyPanel.add(petType);
			
			JLabel insertImageLabel = verifyLabel("INSERT YOUR PHOTO");
			insertImageLabel.setBounds(50,280,200,30);
			verifyPanel.add(insertImageLabel);
			
			JLabel insertValidLabel = verifyLabel("INSERT VALID ID PHOTO");
			insertValidLabel.setBounds(287,280,250,30);
			verifyPanel.add(insertValidLabel);
			
			JButton browseYourPhoto = medicalButton("BROWSE","");
			browseYourPhoto.setBounds(90,310,110,25);
			browseYourPhoto.addActionListener(new ActionListener() {
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
						Image yourPhoto = imageIcon.getImage().getScaledInstance(230,270,Image.SCALE_SMOOTH);
						yourImage.setIcon(new ImageIcon(yourPhoto));;
						s1 = path;
					}else if (result == JFileChooser.CANCEL_OPTION) {
						System.out.println("No data inserted!");
					}
				}
			});
			verifyPanel.add(browseYourPhoto);
			
			JButton browseValidPhoto = medicalButton("BROWSE","");
			browseValidPhoto.setBounds(335,310,110,25);
			browseValidPhoto.addActionListener(new ActionListener() {
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
						Image validPhoto = imageIcon.getImage().getScaledInstance(230,270,Image.SCALE_SMOOTH);
						ValidImage.setIcon(new ImageIcon(validPhoto));;
						s2 = path;
					}else if (result == JFileChooser.CANCEL_OPTION) {
						System.out.println("No data inserted!");
					}
				}
			});
			verifyPanel.add(browseValidPhoto);
			
			JPanel midPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,20,30));
			midPanel.setBounds(65,340,390,420);
			midPanel.setBackground(Color.decode("#313338"));
			verifyPanel.add(midPanel);
			
			JLabel your_name = verifyLabel("YOUR NAME:");
			midPanel.add(your_name);

//	      pet gender age for adoption
			JTextField nameField = new JTextField();
			nameField.setPreferredSize(new Dimension(170,30));
			midPanel.add(nameField);
			
//	      pet gender label for adoption
			JLabel addressLabel = verifyLabel("YOUR ADDRESS:");
			midPanel.add(addressLabel);

//			pet gender field for adoption
			JTextField addressField = new JTextField();
			addressField.setPreferredSize(new Dimension(170,30));
			midPanel.add(addressField);
			
			JLabel numberLabel = verifyLabel("CONTACT NO:");
			midPanel.add(numberLabel);

//			pet gender field for adoption
			JTextField numberField = new JTextField();
			numberField.setPreferredSize(new Dimension(170,30));
			midPanel.add(numberField);
				
			JButton editButton = medicalButton("SUBMIT","");
			editButton.setPreferredSize(new Dimension(200,30));
			editButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					
						String customer_name = nameField.getText();
						String customer_address = addressField.getText();
						String customer_number = numberField.getText();
						if( s1 == null || s2 == null || yourImage.getIcon()==null || ValidImage.getIcon()==null| customer_address.isEmpty() || customer_name.isEmpty()) {
							JOptionPane.showMessageDialog(null, "Insert data required!");
							return;
						}
							
						try {
							InputStream userPhoto = new FileInputStream(new File(s1));
							InputStream userValidIDPhoto = new FileInputStream(new File(s2));
							Class.forName("com.mysql.cj.jdbc.Driver");
							Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylogindb","root","burog181");
							String sql = "SELECT * FROM customer_shipping_verification WHERE customer_id=?";
							PreparedStatement pstmt = con.prepareStatement(sql);
							pstmt.setInt(1, user_id);
							ResultSet rs = pstmt.executeQuery();
							if(rs.next()) {
								JOptionPane.showMessageDialog(null, "You have already submitted your data for verification!");
							}else {
								sql = "INSERT INTO customer_shipping_verification SET customer_id=?, customer_name=?, customer_address=?, customer_phoneNumber=?, customer_picture=?, customer_valid_id=?, verification_status=?";
								pstmt = con.prepareStatement(sql);
								pstmt.setInt(1, user_id);
								pstmt.setString(2, customer_name);
								pstmt.setString(3, customer_address);
								pstmt.setString(4, customer_number);
								pstmt.setBlob(5, userPhoto);
								pstmt.setBlob(6, userValidIDPhoto);
								pstmt.setString(7, "Under-verification");
								pstmt.executeUpdate();
								JOptionPane.showMessageDialog(null, "Account submitted succesfully for verification!");
							}
						} catch (ClassNotFoundException | SQLException | FileNotFoundException e1) {
							e1.printStackTrace();
						}
						verifyFrame.setVisible(false);
				}
			});
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylogindb","root","burog181");
				String sql = "SELECT * FROM customer_shipping_verification WHERE customer_id=?";
				PreparedStatement pstmt = con.prepareStatement(sql);	
				pstmt.setInt(1, user_id);
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()) {
					JOptionPane.showMessageDialog(null, "You have already submitted your data for verification!");
				}else {
					verifyFrame.setVisible(true);
				}
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
			
			midPanel.add(editButton);
			
		}
	
	// choose what type of pet the owner has
	public void petType(int user_id) {
		JFrame frame1 = new JFrame("Pet Type");
		frame1.setSize(300,300);
		frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame1.setLayout(null);
		frame1.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10,10,265,240);
		panel.setBackground(Color.decode("#313338"));
		panel.setLayout(null);
		frame1.add(panel);
		
		JLabel label = new JLabel("Choose what pet you own.");
		label.setForeground(Color.decode("#dbdee1"));
		label.setFont(new Font("SansSerif Bold",Font.PLAIN,17));
		label.setBounds(25,30,300,20);
		panel.add(label);
		
		String[] petTypeItems = {"Cat", "Dog"};;
		JComboBox<String> comboType = new JComboBox<>(petTypeItems);
		comboType.setBounds(80,80,100,30);
		comboType.setSelectedIndex(-1);
		panel.add(comboType);
		
		JButton button = medicalButton("Submit", "");
		button.setBounds(90,170,80,30);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				String pet_type = (String) comboType.getSelectedItem();
				
				if(pet_type == null) {
					JOptionPane.showMessageDialog(null, "INSERT AN ITEM");
					return;
				}else {
					System.out.println("PASSED!");
				}
				String items[];
				if(pet_type.equals("Cat")) {
					items = new String[] {"American Shorthair", "Bengal", "British Shorthair", "Exotic Shorthair", "Himalayan", "Persian", "Puspin", "Siamese"};
				}else {
					items = new String[] {"Aspin", "Bichon Frise", "Chihuahua", "Doberman Pinscher", "German Shepherd", "Labrador Retriever", "Pomeranian", "Poodle", "Rottweiler", "Shih Tzu", "Siberian Husky"};
				}
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylogindb","root","burog181");
					String sql = "UPDATE customer_information SET pet_type = ? WHERE user_id=?";
					PreparedStatement pstmt = con.prepareStatement(sql);
					pstmt.setString(1, pet_type);
					pstmt.setInt(2, user_id);
					pstmt.executeUpdate();
				    frame1.setVisible(false);
				    petInsertInfo(user_id, items);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel.add(button);
		frame1.setVisible(true);
	}
		 
	// insert pet identification or pet information including gender, breed, bday
	public void petInsertInfo(int retrieved_id, String myArray[]) {
		
		petData = new JFrame("Pet Data");
		petData.setSize(500,800);
		petData.setVisible(true);
		petData.setLayout(null);
		petData.setLocationRelativeTo(null);
		petData.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		petDataPanel = new JPanel();
		petDataPanel.setBounds(0,0,500,800);
		petDataPanel.setLayout(null);
		petDataPanel.setBackground(Color.decode("#313338"));
		petData.add(petDataPanel);
		
		JLabel petType = petLabels("INSERT PET PHOTO:");
		petType.setBounds(40,80,200,20);
		
		JLabel petName = petLabels("PET NAME:");
		petName.setBounds(40,150,150,20);
		
		JLabel petOwner = petLabels("PET OWNER:");
		petOwner.setBounds(40,220,150,20);
		
		JLabel petBreed = petLabels("PET BREED:");
		petBreed.setBounds(40,290,170,20);
		
		JLabel petGender = petLabels("PET GENDER:");
		petGender.setBounds(40,360,170,20);
		
		JLabel petBirthdate = petLabels("PET BIRTHDATE:");
		petBirthdate.setBounds(40,430,170,20);
		
		JLabel emailLabel = petLabels("EMAIL:");
		emailLabel.setBounds(40,500,170,20);
		
		JLabel photoShow = petLabels("ENTER YOUR PET DATA");
		photoShow.setBounds(110,0,300,100);
		
		JLabel phoneNumberLabel = petLabels("PHONE NUMBER:");
		phoneNumberLabel.setBounds(40,570,170,20);
		
		JButton browsePhoto = medicalButton("BROWSE", "");
		browsePhoto.setBounds(265,75,90,30);
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
					ImageIcon imaIcon = new ImageIcon(path);
					browsePhoto.setIcon(imaIcon);;
					s = path;
				}else if (result == JFileChooser.CANCEL_OPTION) {
					System.out.println("No data inserted!");
				}
			}
		});
		petDataPanel.add(browsePhoto);
		
		//ALL FIELDS OR DATA INPUT RECEIVER
		JTextField petNameField = petTextFields();
		petNameField.setBounds(225,145, 180,30);
		
		JTextField petOwnerField = petTextFields();
		petOwnerField.setBounds(225,215,180,30);

		JComboBox<String> comboBreed = new JComboBox<>(myArray);
		comboBreed.setBounds(225,285,180,30);
		comboBreed.setSelectedIndex(-1);
		petDataPanel.add(comboBreed);
		
		dateChooser= new JDateChooser();
		dateChooser.setMaxSelectableDate(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000));
		dateChooser.setDateFormatString("dd/MM/yyyy");
		dateChooser.setLocale(Locale.US);
		dateChooser.setBounds(225,425,180,30);
		petDataPanel.add(dateChooser);
		
		JTextField emailField = petTextFields();
		emailField.setBounds(225,495, 180,30);
		
		JTextField phoneNumberField = petTextFields();
		phoneNumberField.setBounds(225,565,180,30);
		
		String[] genderItems = {"Male", "Female"};;
		JComboBox<String> comboGender = new JComboBox<>(genderItems);
		comboGender.setBounds(225,355,180,30);
		comboGender.setSelectedIndex(-1);
		petDataPanel.add(comboGender);
		
		editButton = medicalButton("EDIT", "");
		editButton.setBounds(170,660,100,40);
		editButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					
				} catch (Exception e2) {
					// TODO: handle exception
				}
					int user_id = retrieved_id; //ID from user who login
			
					Calendar calendar = dateChooser.getCalendar();
				// TODO Auto-generated method stub
					String pet_owner = petOwnerField.getText();
					String pet_gender = (String) comboGender.getSelectedItem();
					String pet_name = petNameField.getText();
					String pet_breed = (String) comboBreed.getSelectedItem();
					String email = emailField.getText();
					String phonenumber = phoneNumberField.getText();
					
					if(browsePhoto.getIcon()==null) {
						JOptionPane.showMessageDialog(null, "Insert a photo of your pet!", "Error", JOptionPane.ERROR_MESSAGE);
						return;
						
					}
					
					if (pet_owner.isEmpty() || pet_gender == null || pet_name.isEmpty() || pet_breed == null || calendar== null || email.isEmpty() || phoneNumberField.getText().isEmpty()) {
					    JOptionPane.showMessageDialog(null, "Please fill in all required fields.", "Error", JOptionPane.ERROR_MESSAGE);
					    return;
					}
					
					if (phonenumber.length()!=11) {
						JOptionPane.showMessageDialog(null, "Check your Phone Number input. Max of 11 digits only!", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
			
					try {
						Date selectedDate = calendar.getTime();
						java.sql.Date pet_bday = new java.sql.Date(selectedDate.getTime());
						
					    Class.forName("com.mysql.cj.jdbc.Driver");
					    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylogindb","root","burog181");
					    String sql = "UPDATE customer_information SET pet_owner=?, pet_gender=?, pet_name=?, pet_breed=?, pet_bday=?, owner_email=?, owner_phonenumber=?, pet_image=?, owner_status=? WHERE user_id=?";
					    InputStream is = new FileInputStream(new File(s));
					    PreparedStatement pstmt = con.prepareStatement(sql);
					    pstmt.setString(1, pet_owner);
					    pstmt.setString(2, pet_gender);
					    pstmt.setString(3, pet_name);
					    pstmt.setString(4, pet_breed);
					    pstmt.setDate(5, pet_bday);	
					    pstmt.setString(6, email);
					    pstmt.setString(7, phonenumber);
					    pstmt.setBlob(8, is);
					    pstmt.setString(9, notVerified);
					    pstmt.setInt(10, user_id);
					    pstmt.executeUpdate();
					    JOptionPane.showMessageDialog(null, "Pet Data Updated Successfully");
					    petData.setVisible(false);
					    frame1.setVisible(false);
						try {
							dashboard(user_id);
						} catch (SQLException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					    
					    
					    con.close();
					} catch (ClassNotFoundException | SQLException | FileNotFoundException e1) {
					    e1.printStackTrace();
					}
			}
		});
		petDataPanel.add(editButton);
	}
	
	// dashboard overall interface
	public void dashboard(int user_id) throws SQLException, IOException{
		
		String tabDescription = "DASHBOARD";
		
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
	
		//panel that holds all navigation buttons
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
		
		//designing position of navigation buttons usign panels
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel(new BorderLayout());
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0,30));
		JPanel panel4 = new JPanel();
		JPanel panel5 = new JPanel();
		panel1.setBackground(Color.decode("#1e1f22"));
		panel2.setBackground(Color.decode("#1e1"));
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
		
		// shows which tab we are on. panel that shows current tab
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
		
		// panel for medical record adding. making the panel curved
		medicalPanel = new JPanel(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g.create();
				int width= getWidth();
				int height = getHeight();
				Shape shape = new RoundRectangle2D.Double(0,0,width,height,40,40);
				g2.setClip(shape);
				super.paintComponent(g2);
				g2.dispose();
			}
		};
		medicalPanel.setBounds(33,425,580,380);
		medicalPanel.setLayout(null);
		medicalPanel.setBackground(Color.decode("#313338"));
		containerPanel.add(medicalPanel);
		
		JLabel medicalImageRecord = new JLabel("MEDICAL RECORD SAVER");
		medicalImageRecord.setFont(new Font("SansSerif Bold", Font.PLAIN, 18));
		medicalImageRecord.setForeground(Color.decode("#dbdee1"));
		medicalImageRecord.setBounds(170,0,400,50);
		medicalPanel.add(medicalImageRecord);
		
		JPanel displayer = new JPanel(new BorderLayout());
		JLabel imageDisplay = new JLabel();
		displayer.setBackground(Color.decode("#404249"));
		displayer.setBounds(15,45,230,290);
		displayer.setBorder(BorderFactory.createLineBorder(Color.decode("#1e1f22"), 3));
		displayer.add(imageDisplay, BorderLayout.CENTER);
		medicalPanel.add(displayer);
		
		// browse photo for medical record of pet
		JButton browseButton = medicalButton("Browse","D:\\3rd Year BSCPE (2nd SEMESTER)\\Software Design\\ICONS VETA\\icons8-browse-page-100.png");
		browseButton.setBounds(70,340,100,30);
		browseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser  fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				FileNameExtensionFilter filter = new FileNameExtensionFilter("*.IMAGE", "jpg", "gif", "png","jpeg");
				fileChooser.addChoosableFileFilter(filter);
				int result = fileChooser.showSaveDialog(null);
				if(result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					String path = selectedFile.getAbsolutePath();
					ImageIcon imaIcon = new ImageIcon(path);
					Image img = imaIcon.getImage().getScaledInstance(230,290,Image.SCALE_SMOOTH);
					imageDisplay.setIcon(new ImageIcon(img));;
					s = path;
				}else if (result == JFileChooser.CANCEL_OPTION) {
					System.out.println("No data inserted!");
				}
			}
		});
		medicalPanel.add(browseButton);
		
		// button for viewing all records of pet's medical history
		JButton viewButton = medicalButton("View Records","D:\\3rd Year BSCPE (2nd SEMESTER)\\Software Design\\ICONS VETA\\icons8-view-100.png");
		viewButton.setBounds(280,340,140,30);
		viewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				viewHistory(user_id);
			}
		});
		medicalPanel.add(viewButton);
		
		JLabel clinic = medicalLabel("Clinic Name:");
		clinic.setBounds(275,70,150,20);
		JTextField clinicTextField = new JTextField();
		medicalPanel.add(clinicTextField);
		clinicTextField.setBounds(275,100,270,30);

		JLabel description =  medicalLabel("Description:");
		description.setBounds(275,150,150,20);
		JTextField descriptionTextField = new JTextField();
		medicalPanel.add(descriptionTextField);
		descriptionTextField.setBounds(275,180,270,30);
		
		JLabel dateClinic =  medicalLabel("Date Issued:");
		dateClinic.setBounds(275,230,150,20);
		
		// field / date chooser when did the medical record been issued
		dateChooser= new JDateChooser();
		dateChooser.setMaxSelectableDate(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000));
		dateChooser.setDateFormatString("dd/MM/yyyy");
		dateChooser.setLocale(Locale.US);
		dateChooser.setBounds(275,260,270,30);
		medicalPanel.add(dateChooser);
		
		historyPanel = new JPanel(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g.create();
				int width= getWidth();
				int height = getHeight();
				Shape shape = new RoundRectangle2D.Double(0,0,width,height,40,40);
				g2.setClip(shape);
				super.paintComponent(g2);
				g2.dispose();
			}
		};
		historyPanel.setBounds(633,425,580,380);
		historyPanel.setLayout(null);
		historyPanel.setBackground(Color.decode("#313338"));
		containerPanel.add(historyPanel);
		
		// ALl status tabs
		JLabel yourStatus = petShowData("TRANSACTIONS STATUS");
		yourStatus.setFont(new Font("SansSerif Bold", Font.PLAIN,18));
		yourStatus.setBounds(180,5,450,40);
		historyPanel.add(yourStatus);
		// appointment
		JLabel yourAppointment = petShowData("Appointment Status:");
		yourAppointment.setFont(new Font("SansSerif Bold", Font.PLAIN,20));
		yourAppointment.setBounds(20,50,450,40);
		historyPanel.add(yourAppointment);
		//adoption
		JLabel yourAdopt = petShowData("Adoption Status:");
		yourAdopt.setFont(new Font("SansSerif Bold", Font.PLAIN,20));
		yourAdopt.setBounds(20,130,450,40);
		historyPanel.add(yourAdopt);
		//orders
		JLabel yourOrder = petShowData("Order Status:");
		yourOrder.setFont(new Font("SansSerif Bold", Font.PLAIN,20));
		yourOrder.setBounds(20,210,450,40);
		historyPanel.add(yourOrder);
		//verification
		JLabel yourVerification = petShowData("Verification Status:");
		yourVerification.setFont(new Font("SansSerif Bold", Font.PLAIN,20));
		yourVerification.setBounds(20,290,450,40);
		historyPanel.add(yourVerification);
		
		String conCat = null;
		String verifiedUser = null;
		String meet_up_status= null;
		String order = null;
		try {
			System.out.println("SAD");
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylogindb","root","burog181");
			String sql = "SELECT * FROM booked_appointment WHERE user_id=? AND status=? ORDER BY id DESC LIMIT 1";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, user_id);
			pstmt.setString(2, "Approved");
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				String date = rs.getString("date");
				String time = rs.getString("time");
				conCat= "Appointment on "+date+ " at "+time+"am is approved.";
			}else {
				conCat= "No data found for approved appointment.";
			}
			
			sql = "SELECT * from customer_shipping_verification WHERE customer_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, user_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String verificationStatus= rs.getString("verification_status");
				if (verificationStatus.equals("Verified")){
					verifiedUser = "Verified User";
				}else {
					verifiedUser = "User not Verified.";
				}
			}else {
				verifiedUser = "No data found for verification.";
			}
			
			sql = "SELECT * from adopted_pet WHERE owner_id=? ORDER BY adoption_id DESC LIMIT 1";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, user_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				 String briefing_day = rs.getString("date_appointment");
				 String adoption_status = rs.getString("status");
				 String time = rs.getString("time_appointment");
				 if(adoption_status.equals(meet_status)) {
					 meet_up_status = "Visit the clinic on " + briefing_day + " at " + time + "am for briefing.";
				 }else if(adoption_status.equals(approved_status)) {
					meet_up_status = "You have succesfully adopted the pet. Thank you!";
				 }else if(adoption_status.equals(in_scheduling)) {
					 meet_up_status = "Wait for a schedule of visit for pet adoption";
				 }else {
					System.out.println("There is an error");
				}
			}else {
				meet_up_status = "No data found for adopting a pet.";
			}
			
			sql = "SELECT * from billing WHERE user_id=? AND order_status=? ORDER BY billing_id DESC LIMIT 1";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, user_id);
			pstmt.setString(2, order_status);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				order = "Wait for your order to arrive";
			}else {
				order = "No data found for online orders.";
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Show all status labels
		JLabel showAppointment = petShowData(conCat);
		showAppointment.setFont(new Font("SansSerif",Font.PLAIN,18));
		showAppointment.setBounds(70,90,500,40);
		historyPanel.add(showAppointment);
		
		JLabel showAdoption = petShowData(meet_up_status);
		showAdoption.setFont(new Font("SansSerif",Font.PLAIN,18));
		showAdoption.setBounds(70,170,500,40);
		historyPanel.add(showAdoption);
		
		JLabel showOrder = petShowData(order);
		showOrder.setFont(new Font("SansSerif",Font.PLAIN,18));
		showOrder.setBounds(70,250,500,40);
		historyPanel.add(showOrder);
		
		JLabel showVerification = petShowData(verifiedUser);
		showVerification.setFont(new Font("SansSerif",Font.PLAIN,18));
		showVerification.setBounds(70,330,500,40);
		historyPanel.add(showVerification);
		
		//add medical record button
		JButton addButton = medicalButton(" Add","D:\\3rd Year BSCPE (2nd SEMESTER)\\Software Design\\ICONS VETA\\icons8-add-100.png");
		addButton.setBounds(440,340,100,30);
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String clinic_name = clinicTextField.getText();
				String description = descriptionTextField.getText();
				Calendar calendar = dateChooser.getCalendar();
				
				if(s == null||  browseButton.getIcon()==null || calendar == null|| clinicTextField.getText().isEmpty() || descriptionTextField.getText().isEmpty() ) {
					JOptionPane.showMessageDialog(null, "Please Insert Required Data!");
					return;
				}
				
				try {
					Date selectedDate = calendar.getTime();
					java.sql.Date date_issued = new java.sql.Date(selectedDate.getTime());
					 InputStream is = new FileInputStream(new File(s));
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylogindb","root","burog181");
					String sql = "INSERT INTO customer_pet_medhistory (user_id, clinic_name, description, date_issued, record_image, status) VALUES (?,?,?,?,?,?)";
					PreparedStatement pstmt = con.prepareStatement(sql);
				    pstmt.setInt(1, user_id);
				    pstmt.setString(2, clinic_name);
				    pstmt.setString(3, description);
				    pstmt.setDate(4, date_issued);
				    pstmt.setBlob(5, is);
				    pstmt.setString(6, "For Verification");
				    pstmt.executeUpdate();
				    clinicTextField.setText("");
				    descriptionTextField.setText("");
				    dateChooser.setDate(null);
				    imageDisplay.setIcon(null);
				    JOptionPane.showMessageDialog(null, "Record Inserted Successfully!");
				    con.close();
				    
				} catch (ClassNotFoundException | SQLException | FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		medicalPanel.add(addButton);
		
		
		//Panel for pet details
		petDetailPanel = new JPanel();
		petDetailPanel.setBounds(33,70,1180,335);
		petDetailPanel.setLayout(new BorderLayout());
		petDetailPanel.setBackground(Color.decode("#313338"));
		containerPanel.add(petDetailPanel);
		
		JPanel additional = new JPanel();
		additional.setBackground(Color.decode("#313338"));
		additional.setPreferredSize(new Dimension(100,35));
		petDetailPanel.add(additional,BorderLayout.NORTH);
		
		JPanel bottomAdditional = new JPanel();
		bottomAdditional.setBackground(Color.decode("#313338"));
		bottomAdditional.setPreferredSize(new Dimension(100,35));
		petDetailPanel.add(bottomAdditional,BorderLayout.SOUTH);
		
		JPanel pdSepator1 = new JPanel();
		pdSepator1.setLayout(new BorderLayout());
		
		JPanel pdSepator2 = new JPanel();
		pdSepator2.setLayout(new BorderLayout());
		JPanel pdSepator3 = new JPanel(new FlowLayout(FlowLayout.LEFT, 15,30));
		JPanel pdSepator4 = new JPanel(new FlowLayout(FlowLayout.LEFT, 50,15));
		JPanel pdSepator5 = new JPanel(new FlowLayout(FlowLayout.LEFT,20,18));
		JPanel pdSepator6 = new JPanel(new FlowLayout(FlowLayout.LEFT, 20,18));
		
		pdSepator1.setBackground(Color.decode("#1e1f22"));
		pdSepator2.setBackground(Color.decode("#1e23d2"));
		
		pdSepator1.setPreferredSize(new Dimension(590,100));
		pdSepator2.setPreferredSize(new Dimension(590,100));
		
		pdSepator3.setBackground(Color.decode("#313338"));
		pdSepator4.setBackground(Color.decode("#313338"));
		
		pdSepator3.setPreferredSize(new Dimension(295,100));
		pdSepator4.setPreferredSize(new Dimension(295,100));
		
		pdSepator5.setBackground(Color.decode("#313338"));
		pdSepator6.setBackground(Color.decode("#313338"));
		
		pdSepator5.setPreferredSize(new Dimension(315,100));
		pdSepator6.setPreferredSize(new Dimension(275,100));
		
		petDetailPanel.add(pdSepator1, BorderLayout.WEST);
		petDetailPanel.add(pdSepator2, BorderLayout.EAST);
		
		pdSepator1.add(pdSepator3, BorderLayout.EAST);
		pdSepator1.add(pdSepator4, BorderLayout.WEST);
		pdSepator2.add(pdSepator5, BorderLayout.EAST);
		pdSepator2.add(pdSepator6, BorderLayout.WEST);
		
		
		JLabel petIdentification = petDetail("PET IDENTIFICATION");
		petIdentification.setFont(new Font("SansSerif Bold", Font.PLAIN, 18));
		additional.add(petIdentification);
		
		JLabel petOwner = petDetail("OWNER:");
		pdSepator6.add(petOwner);
		JLabel petGender = petDetail("GENDER:");
		pdSepator6.add(petGender);
		JLabel petBirthdate = petDetail("BIRTHDATE:");
		pdSepator6.add(petBirthdate);
		JLabel userEmail = petDetail("OWNER EMAIL:");
		pdSepator6.add(userEmail);
		JLabel userCPN = petDetail("OWNER CP NO:");
		pdSepator6.add(userCPN);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylogindb","root","burog181");
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM customer_information WHERE user_id = ?");
			pstmt.setInt(1, user_id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				String pet_name =  rs.getString("pet_name");
				String pet_breed = rs.getString("pet_breed");
				String pet_type = rs.getString("pet_type");
				String pet_owner = rs.getString("pet_owner");
				String pet_gender =rs.getString("pet_gender");
				String pet_bday = rs.getString("pet_bday");
				String email = rs.getString("owner_email");
				String phonenumber = rs.getString("owner_phonenumber");
				//for pet image
				Blob images = rs.getBlob("pet_image");

				if(images == null) {
					JOptionPane.showMessageDialog(null, "Welcome to VETA!");
					System.out.println("NO DATA FOUND");
				}else {
					Border border = BorderFactory.createLineBorder(Color.decode("#1e1f22"), 5);
					InputStream inputStream = images.getBinaryStream();
			        byte[] imageBytes = inputStream.readAllBytes();
			        ImageIcon imageIcon = new ImageIcon(imageBytes);
			        Image img = imageIcon.getImage().getScaledInstance(300,300,Image.SCALE_SMOOTH);
			        JLabel but = new JLabel("");
			        but.setBackground(Color.decode("#404249"));
					but.setBorder(border);
					but.setPreferredSize(new Dimension(240,240));
					but.setIcon(new ImageIcon(img));
					pdSepator4.add(but);
				}
		        
				String concatBT;
				if(pet_breed != null || pet_type!=null) {
					concatBT = pet_breed+" - "+pet_type;
				}else {
					concatBT ="";
				}
				JLabel fillerlabel = petShowData("");
				pdSepator3.add(fillerlabel);
						
				JLabel showName = petShowData(pet_name);
				showName.setFont(new Font("SansSerif BOLD",Font.ITALIC,25));
				pdSepator3.add(showName);

				JLabel showBreed_Type = petShowData(concatBT);
				pdSepator3.add(showBreed_Type);
				
				JLabel showOwner = petShowData(pet_owner);
				pdSepator5.add(showOwner);
				
				JLabel showGender = petShowData(pet_gender);
				pdSepator5.add(showGender);
				
				JLabel showBday = petShowData(pet_bday);
				pdSepator5.add(showBday);
			
				JLabel showEmail = petShowData(email);
				pdSepator5.add(showEmail);
				
				JLabel showPhoneNumber = petShowData(phonenumber);
				pdSepator5.add(showPhoneNumber);
				
				
			}else {
				System.out.println("PASS");
            }
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//-----------------------------------------------------------
		
		JButton verifyBtn = medicalButton("VERIFY ACCOUNT","D:\\3rd Year BSCPE (2nd SEMESTER)\\Software Design\\ICONS VETA\\icons8-verify-100.png");
		verifyBtn.setPreferredSize(new Dimension(170,25));
		verifyBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Dashboard_Tab dt = new Dashboard_Tab();
				dt.accountVerification(user_id);
			}
		});
		bottomAdditional.add(verifyBtn);
		
		updateButton = medicalButton("UPDATE PET INFO","D:\\3rd Year BSCPE (2nd SEMESTER)\\Software Design\\ICONS VETA\\icons8-update-file-96.png");	
		updateButton.setPreferredSize(new Dimension(170,25));
		updateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// TODO Auto-generated method stub
				petType(user_id);
				return;
			}
		});
		bottomAdditional.add(updateButton);	
		
		JButton dbButton = myButton("DASHBOARD", "D:\\3rd Year BSCPE (2nd SEMESTER)\\Software Design\\ICONS VETA\\icons8-dashboard-layout-96.png");
		buttonPanel.add(dbButton);
		dbButton.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				frame1.setVisible(false);
		
				try {
					dashboard(user_id);
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
				Shopping_Tab showShopping_Tab = new Shopping_Tab();
				try {
					showShopping_Tab.shopping(user_id);
				} catch (SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		frame1.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
