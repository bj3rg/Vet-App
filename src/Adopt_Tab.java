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
import java.awt.color.ColorSpace;
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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.toedter.calendar.JDateChooser;

public class Adopt_Tab {
	
	private JFrame frame1;
	private JPanel navPanel, mainPanel, containerPanel, petDetailPanel, tabPanel, petDataPanel;
	private JLabel vetaLabel, tabLabel;
	private String s;
	public String verified = "Verified";
	public String in_scheduling = "In Scheduling";
	
//	public static void main(String[] args) {
//		Adopt_Tab ad = new Adopt_Tab();
//		ad.petInsertInfo(2);
//	}

	
	private JPanel petAdoptAvailable(String pet_id,String pet_age, String pet_gender, Blob pet_image, String pet_description, String pet_status) throws SQLException, IOException {
		
		String concat = "PET ID:" +" "+pet_id+" - "+pet_gender;
		String gen_status = pet_gender +" - " + "("+pet_status+")"; 
		//  String pet_age, String pet_gender, Image pet_image
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 100,10));
		panel.setPreferredSize(new Dimension(200,270));
		panel.setBackground(Color.decode("#1e1f22"));
		
		Border border = BorderFactory.createLineBorder(Color.decode("#1e1f22"), 5);
		JLabel imageContainer = new JLabel("");
		InputStream inputStream = pet_image.getBinaryStream();
        byte[] imageBytes = inputStream.readAllBytes();
        ImageIcon imageIcon = new ImageIcon(imageBytes);
        Image img_cat = imageIcon.getImage().getScaledInstance(130,170,Image.SCALE_SMOOTH);
		imageContainer.setBackground(Color.red);
		imageContainer.setBorder(border);
		imageContainer.setIcon(new ImageIcon(img_cat));
		imageContainer.setPreferredSize(new Dimension(130,150));
		panel.add(imageContainer);
		
		JLabel age = new JLabel(concat);
		age.setForeground(Color.white);
		panel.add(age);
		
		JLabel gender = new JLabel(gen_status);
		gender.setForeground(Color.white);
		panel.add(gender);
		
		JButton viewDetail = adoptButton("VIEW FULL DETAIL","");
		viewDetail.setFont(new Font("SansSerif BOLD", Font.PLAIN,14));
		viewDetail.setPreferredSize(new Dimension(140,25));
		viewDetail.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFrame detailFrame = new JFrame("Pet Full Detail");
				detailFrame.setSize(800,800);
				detailFrame.setLocationRelativeTo(null);
				detailFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				detailFrame.setVisible(true);
				
				JPanel detailPanel = new JPanel();
				detailPanel.setBackground(Color.decode("#171717"));
				detailPanel.setLayout(null);
				detailFrame.add(detailPanel);
				
				JPanel imagePanel = new JPanel();
				imagePanel.setBounds(250,70,300,400);
				imagePanel.setBackground(Color.decode("#171717"));
				detailPanel.add(imagePanel);
				
				JLabel showpetImage = adoptLabel("");
				InputStream inputStream;
				try {
					inputStream = pet_image.getBinaryStream();
					byte[] imageBytes = inputStream.readAllBytes();
				} catch (SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	            ImageIcon imageIcon = new ImageIcon(imageBytes);
	            Image img_cat = imageIcon.getImage().getScaledInstance(300,400,Image.SCALE_SMOOTH);
				showpetImage.setIcon(new ImageIcon(img_cat));
			
				imagePanel.add(showpetImage);
				
				JPanel detailContainer = new JPanel(new FlowLayout(FlowLayout.CENTER, 90,40));
				detailContainer.setBounds(240,450,300,300);
				detailContainer.setBackground(Color.decode("#171717"));
				detailPanel.add(detailContainer);
				
				
				String ageConcat = "Pet Age: "+pet_age;
				JLabel showAge = adoptLabel(ageConcat);
				detailContainer.add(showAge);
				
				String genderConcat ="Pet Gender: "+pet_gender;
				JLabel showGender = adoptLabel(genderConcat);
				detailContainer.add(showGender);
				
	
				JLabel showDescription = adoptLabel(pet_description);
				detailContainer.add(showDescription);
			}
		});
		panel.add(viewDetail);
		return panel;	
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
		return label;
	}
	
	private JTextField petTextFields() {
		JTextField textField = new JTextField();
		textField.setSize(200,200);
		textField.setFont(new Font("SansSerif",Font.PLAIN,15));
		return textField;
	}
	
	public void cancel_adoption(int user_id) {
		frame1 = new JFrame();
		frame1.setSize(500,300);
		frame1.setLocationRelativeTo(null);
		frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		tabPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,90,30));
		tabPanel.setBackground(Color.decode("#2f2f2f"));
		frame1.add(tabPanel);
		ArrayList<Object> myPetAdopt = new ArrayList<>();
		try {
			String scheduling = "In Briefing";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylogindb","root","burog181");
			String sql = "SELECT * FROM cat_for_adoption WHERE cat_owner_id=? AND cat_status=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, user_id);
			pstmt.setString(2, scheduling);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
					int id = rs.getInt("cat_id");
					String idString = String.valueOf(id);
					String age = rs.getString("cat_age");
					String gender = rs.getString("cat_gender");
					String allConcat = "Cat ID: "+ idString + " -"+ " Age: "+age+" -"+ " Gender: "+gender;
				    myPetAdopt.add(allConcat);
			}
			sql = "SELECT * FROM dog_for_adoption WHERE dog_owner_id=? AND dog_status=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, user_id);
			pstmt.setString(2, scheduling);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				System.out.println("SAD dog");
					int id = rs.getInt("dog_id");
					String idString = String.valueOf(id);
					String age = rs.getString("dog_age");
					String gender = rs.getString("dog_gender");
					String allConcat = "Dog ID: "+ idString + " -"+ " Age: "+age+" -"+ " Gender: "+gender;
				    myPetAdopt.add(allConcat);

			}
			
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		JLabel chooseLabel = adoptLabel("Choose pet:");
		tabPanel.add(chooseLabel);
		
		JComboBox<Object> comboPet = new JComboBox<Object>(myPetAdopt.toArray());
		comboPet.setPreferredSize(new Dimension(350,30));
		comboPet.setSelectedIndex(-1);
		tabPanel.add(comboPet);
		
		JButton submitButton = adoptButton("SUBMIT CANCELLATION","SAD");
		submitButton.setPreferredSize(new Dimension(200,30));
		submitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String selectedItem = (String) comboPet.getSelectedItem();
				
				if(comboPet.getSelectedItem()==null) {
					return;
				}
				int selectedId = Integer.parseInt(selectedItem.split(" ")[2]); // split sentence
				String pet = (selectedItem.split(" ")[0]); // split words
				System.out.println(pet);
				if(pet.equals("Cat")) {
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylogindb","root","burog181");
						String sql = "SELECT * FROM cat_for_adoption WHERE cat_owner_id=? AND cat_id=?";
						PreparedStatement pstmt = con.prepareStatement(sql);
						pstmt.setInt(1, user_id);
						pstmt.setInt(2, selectedId);
						ResultSet rs = pstmt.executeQuery();
						if(rs.next()) {
							String status = "For Adoption";
							System.out.println("291");
							sql = "UPDATE cat_for_adoption SET cat_owner_id =NULL, cat_new_owner=NULL, cat_status=? WHERE cat_id=?";
						    pstmt = con.prepareStatement(sql);
						    pstmt.setString(1, status);
						    pstmt.setInt(2, selectedId);
						    pstmt.executeUpdate();
							sql = "SELECT * From adopted_pet WHERE owner_id=? AND pet_id=? AND pet_type=?";
						    pstmt = con.prepareStatement(sql);
						    pstmt.setInt(1, user_id);
						    pstmt.setInt(2, selectedId);
						    pstmt.setString(3, "Cat");
						    rs = pstmt.executeQuery();
					    if (rs.next()) {
					    	int adoption_id = rs.getInt("adoption_id");
					    	System.out.println("357");
					    	sql ="DELETE from adopted_pet WHERE adoption_id=?";
					    	pstmt =con.prepareStatement(sql);
					    	pstmt.setInt(1, adoption_id);
					    	pstmt.executeUpdate();
					    }else {
					    	JOptionPane.showMessageDialog(null, "There is an error");
					    }
						}
						JOptionPane.showMessageDialog(null, "Pet adopt cancellation submitted succesfully!");
						frame1.setVisible(false);
						Adopt_Tab at = new Adopt_Tab();
						at.adopt(user_id);
					} catch (ClassNotFoundException | SQLException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			
				}else {
					try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylogindb","root","burog181");
					String sql = "SELECT * FROM dog_for_adoption WHERE dog_owner_id=? AND dog_id=?";
					PreparedStatement pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, user_id);
					pstmt.setInt(2, selectedId);
					ResultSet rs = pstmt.executeQuery();
					if(rs.next()) {
						String status = "For Adoption";
						sql = "UPDATE dog_for_adoption SET dog_owner_id=NULL, dog_new_owner=NULL, dog_status=? WHERE dog_id=?";
					    pstmt = con.prepareStatement(sql);
					    pstmt.setString(1, status);
					    pstmt.setInt(2, selectedId);
					    pstmt.executeUpdate();
					    sql = "SELECT * From adopted_pet WHERE owner_id=? AND pet_id=?";
					    pstmt = con.prepareStatement(sql);
					    pstmt.setInt(1, user_id);
					    pstmt.setInt(2, selectedId);
					    rs = pstmt.executeQuery();
					    if (rs.next()) {
					    	int adoption_id = rs.getInt("adoption_id");
					    	System.out.println("357");
					    	sql ="DELETE from adopted_pet WHERE adoption_id=?";
					    	pstmt =con.prepareStatement(sql);
					    	pstmt.setInt(1, adoption_id);
					    	pstmt.executeUpdate();
					    }else {
					    	JOptionPane.showMessageDialog(null, "There is an error");
					    }
					}
					JOptionPane.showMessageDialog(null, "Pet adopt cancellation submitted succesfully!");
				}catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				}
			}
			}
		});
		tabPanel.add(submitButton);
		
		frame1.setVisible(true);
		
		
	}
	
 public void petInsertAdopt(int user_id) {
		
		JFrame petData = new JFrame("Pet Data");
		petData.setSize(600,790);
		
		petData.setLayout(null);
		petData.setLocationRelativeTo(null);
		petData.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		JPanel petPanel = new JPanel();
		petPanel.setBounds(20,20,540,710);
		petPanel.setLayout(null);
		petPanel.setBackground(Color.decode("#1e1f22"));
		petData.add(petPanel);
		
		// panel for showing image in insert photo
		Border border = BorderFactory.createLineBorder(Color.decode("#111111"), 5);
		JPanel petImagePanel = new JPanel();
		petImagePanel.setBackground(Color.decode("#dbdee1"));
		petImagePanel.setBorder(border);
		petImagePanel.setBounds(155,10,230,270);
		petPanel.add(petImagePanel);
		
		//Label that gets the image
		JLabel petImage = adoptLabel("");
		petImage.setPreferredSize(new Dimension(215,255));
		petImagePanel.add(petImage);
		
		JPanel midPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,20,30));
		midPanel.setBounds(125,270,300,420);
		midPanel.setBackground(Color.decode("#1e1f22"));
		petPanel.add(midPanel);
		
		JLabel petType = adoptLabel("INSERT PHOTO:");
		midPanel.add(petType);
		
		JButton browsePhoto = adoptButton("BROWSE","");
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
					petImage.setIcon(new ImageIcon(img_cat));;
					s = path;
				}else if (result == JFileChooser.CANCEL_OPTION) {
					System.out.println("No data inserted!");
				}
			}
		});
		midPanel.add(browsePhoto);
		
		JLabel pet_Type = adoptLabel("PET TYPE:");
		midPanel.add(pet_Type);
		
		String myPetChoice[] = {"Cat","Dog"};
		JComboBox<String> comboType = new JComboBox<>(myPetChoice);
		comboType.setPreferredSize(new Dimension(120,30));
		comboType.setSelectedIndex(-1);
		midPanel.add(comboType);
		
		
		JLabel petAge = adoptLabel("PET AGE:");
		midPanel.add(petAge);
		
//      pet gender age for adoption
		JTextField petAgeField = petTextFields();
		petAgeField.setPreferredSize(new Dimension(120,30));
		midPanel.add(petAgeField);
		
//      pet gender label for adoption
		JLabel petGender = adoptLabel("PET GENDER:");
		midPanel.add(petGender);

//		pet gender field for adoption
		JTextField petGenderField = petTextFields();
		petGenderField.setPreferredSize(new Dimension(120,30));
		midPanel.add(petGenderField);
		
//		pet additional label for adoption
		JLabel petInfo = adoptLabel("ADDITIONAL DESCRIPTION:");
		midPanel.add(petInfo);
		
//		pet info field for adoption
		JTextField petInfoField = petTextFields();
		petInfoField.setPreferredSize(new Dimension(200,30));
		midPanel.add(petInfoField);

		
		JButton editButton = adoptButton("SUBMIT","");
		editButton.setPreferredSize(new Dimension(200,30));
		editButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
					String pet_age = petAgeField.getText();
					String pet_gender = petGenderField.getText();
					String pet_description = petInfoField.getText();
					String pet_type = (String) comboType.getSelectedItem();
					
					System.out.println(pet_age+pet_gender+pet_description);
					
					if(petImage.getIcon()==null || pet_age.isEmpty() || pet_gender.isEmpty()|| pet_description.isEmpty() || comboType.getSelectedItem()==null ) {
						JOptionPane.showMessageDialog(null, "Insert data required!");
						return;
					}
					if(pet_type.equals("Cat")) {
						try {
							String status = "Request-Add";
						    Class.forName("com.mysql.cj.jdbc.Driver");
						    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylogindb","root","burog181");
						    String sql = "INSERT INTO cat_for_adoption SET cat_age=?, cat_gender=?, cat_description=?, cat_image=?, cat_status=?";
						    InputStream is = new FileInputStream(new File(s));
						    PreparedStatement pstmt = con.prepareStatement(sql);
						    pstmt.setString(1,pet_age);
						    pstmt.setString(2, pet_gender);
						    pstmt.setString(3, pet_description);
						    pstmt.setBlob(4, is);
						    pstmt.setString(5, status);
						    pstmt.executeUpdate();
						    JOptionPane.showMessageDialog(null, "Pet cat submitted succesfully. Thank you!");
						    con.close();
						    petData.setVisible(false);
						} catch (ClassNotFoundException | SQLException | FileNotFoundException e1) {
						    e1.printStackTrace();
						}
					}else {
						try {
							String status = "Request-Add";
						    Class.forName("com.mysql.cj.jdbc.Driver");
						    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylogindb","root","burog181");
						    String sql = "INSERT INTO dog_for_adoption SET dog_age=?, dog_gender=?, dog_description=?, dog_image=?, dog_status=?";
						    InputStream is = new FileInputStream(new File(s));
						    PreparedStatement pstmt = con.prepareStatement(sql);
						    pstmt.setString(1,pet_age);
						    pstmt.setString(2, pet_gender);
						    pstmt.setString(3, pet_description);
						    pstmt.setBlob(4, is);
						    pstmt.setString(5, status);
						    pstmt.executeUpdate();
						    JOptionPane.showMessageDialog(null, "Pet dog submitted succesfully. Thank you!");
						    con.close();
						    petData.setVisible(false);
						} catch (ClassNotFoundException | SQLException | FileNotFoundException e1) {
						    e1.printStackTrace();
						}
					}
					
			}
		});
		midPanel.add(editButton);
		petData.setVisible(true);
	}
	
	public void adopt(int user_id) throws SQLException, IOException{
		
		String tabDescription = "ADOPT";
		
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

		JPanel adoptDetailPanel = new JPanel();
		adoptDetailPanel.setBounds(33,70,1180,735);
		adoptDetailPanel.setLayout(new BorderLayout());
		adoptDetailPanel.setBackground(Color.decode("#313338"));
		containerPanel.add(adoptDetailPanel);
		
		JPanel topPanel = new JPanel();
		topPanel.setBackground(Color.decode("#313338"));
		topPanel.setPreferredSize(new Dimension(200,90));
		topPanel.setLayout(null);
		
		JLabel adoptDesc=adoptLabel("ADOPT A PET NOW");
		adoptDesc.setBounds(500,5,300,30);
		adoptDesc.setFont(new Font("SansSerif BOLD",Font.PLAIN,18));
		topPanel.add(adoptDesc);
		
		JLabel catLabel=adoptLabel("CAT");
		catLabel.setBounds(310,55,300,30);
		catLabel.setFont(new Font("SansSerif BOLD",Font.PLAIN,25));
		topPanel.add(catLabel);
		
		JLabel dogLabel=adoptLabel("DOG");
		dogLabel.setBounds(800,55,300,30);
		dogLabel.setFont(new Font("SansSerif BOLD",Font.PLAIN,25));
		topPanel.add(dogLabel);
		
		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(Color.decode("#313338"));
		leftPanel.setPreferredSize(new Dimension(100,80));
		
		JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0,20));
		rightPanel.setBackground(Color.decode("#313338"));
		rightPanel.setPreferredSize(new Dimension(100,80));
		
		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(Color.decode("#313338"));
		centerPanel.setLayout(new BorderLayout());
		
		JPanel catPanel = new JPanel();
		catPanel.setBackground(Color.decode("#4e4f50"));
		catPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

		try {
		    Class.forName("com.mysql.cj.jdbc.Driver");
		    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylogindb","root","burog181");
		    String sql = "SELECT * FROM cat_for_adoption";
		    PreparedStatement pstmt = con.prepareStatement(sql);
		    ResultSet rs = pstmt.executeQuery();
		    while(rs.next()) {
		        int intcat_id = rs.getInt("cat_id");
		        String cat_status = rs.getString("cat_status");
		        String cat_id = String.valueOf(intcat_id);
		        String cat_age =  rs.getString("cat_age");
		        String cat_gender = rs.getString("cat_gender");
		        String cat_description = rs.getString("cat_description");
		        Blob images = rs.getBlob("cat_image");
		        if (images != null) {
		        	System.out.println("image is not nutt");
		          if(cat_status.equals("For Adoption")) {
		        	  catPanel.add(petAdoptAvailable(cat_id, cat_age, cat_gender, images,cat_description, cat_status));
		          }else {
		          }
		        } else {
		            System.out.println("NO DATA FOUND");
		        }
		    }
		} catch (ClassNotFoundException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
		
//		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		JScrollPane scrollPane = new JScrollPane(catPanel);
		catPanel.setPreferredSize(new Dimension(400,1500));
		scrollPane.setPreferredSize(new Dimension(470,600));
		centerPanel.add(scrollPane, BorderLayout.WEST);
		
		
		JPanel dogPanel = new JPanel();
		dogPanel.setBackground(Color.decode("#4e4f50"));
		dogPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
		
		try {
		    Class.forName("com.mysql.cj.jdbc.Driver");
		    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylogindb","root","burog181");
		    String sql = "SELECT * FROM dog_for_adoption";
		    PreparedStatement pstmt = con.prepareStatement(sql);
		    ResultSet rs = pstmt.executeQuery();
		    while(rs.next()) {
		        int intdog_id = rs.getInt("dog_id");
		        String dog_status = rs.getString("dog_status");
		        String dog_id = String.valueOf(intdog_id);
		        String dog_age =  rs.getString("dog_age");
		        String dog_gender = rs.getString("dog_gender");
		        String dog_description = rs.getString("dog_description");
		        Blob images = rs.getBlob("dog_image");
		        if (images != null) {
		        	System.out.println(dog_status);
		          if(dog_status.equals("For Adoption")) {
		        	  dogPanel.add(petAdoptAvailable(dog_id, dog_age, dog_gender, images, dog_description, dog_status));
		          }else {
		        	  System.out.println("NOT AVAILABLE");
		          }
		        } else {
		            System.out.println("NO DATA FOUND");
		        }
		    }
		} catch (ClassNotFoundException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
	
		JScrollPane scrollPane1 = new JScrollPane(dogPanel);
		dogPanel.setPreferredSize(new Dimension(400,1500));
		scrollPane1.setPreferredSize(new Dimension(470,600));
		centerPanel.add(scrollPane1, BorderLayout.EAST);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBackground(Color.decode("#313338"));
		bottomPanel.setLayout(null);
		bottomPanel.setPreferredSize(new Dimension(100,100));
		
		JLabel addLabel = adoptLabel("Add pets for adoption?");
		addLabel.setFont(new Font("SansSerif BOLD",Font.PLAIN,15));
		addLabel.setBounds(120,0,500,30);
		bottomPanel.add(addLabel);
		
		JButton addPet = adoptButton("Add Pet","D:\\3rd Year BSCPE (2nd SEMESTER)\\Software Design\\ICONS VETA\\icons8-add-100.png");
		addPet.setBounds(135,40,120,30);
		addPet.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				petInsertAdopt(user_id);
			}
		});
		bottomPanel.add(addPet);
		
		JLabel cancelLabel = adoptLabel("Cancel chosen pet to adopt.");
		cancelLabel.setFont(new Font("SansSerif BOLD",Font.PLAIN,15));
		cancelLabel.setBounds(340,0,500,30);
		bottomPanel.add(cancelLabel);
		
		JButton cancelPet = adoptButton("Cancel Adoption","D:\\3rd Year BSCPE (2nd SEMESTER)\\Software Design\\ICONS VETA\\icons8-pet-100.png");
		cancelPet.setBounds(350,40,180,30);
		cancelPet.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cancel_adoption(user_id);
			}
		});
		bottomPanel.add(cancelPet);
		
		//label to ask if found pet to adopt
		JLabel petIDLabel = adoptLabel("Found a pet to adopt? Enter Pet ID below.");
		petIDLabel.setFont(new Font("SansSerif BOLD",Font.PLAIN,15));
		petIDLabel.setBounds(690,0,500,30);
		bottomPanel.add(petIDLabel);
		
		//label for entering cat id
		JLabel catIDLabel = adoptLabel("Enter cat ID:");
		catIDLabel.setFont(new Font("SansSerif BOLD",Font.PLAIN,15));
		catIDLabel.setBounds(610,30,500,30);
		bottomPanel.add(catIDLabel);
		
		//cat request enter id field
		JTextField catrequestTextField = new JTextField();
		catrequestTextField.setPreferredSize(new Dimension(70,30));
		catrequestTextField.setBounds(710,30,100,25);
		bottomPanel.add(catrequestTextField);
		
		//button to request pet in the list
		JButton catrequestPet = adoptButton("Adopt Cat","D:\\3rd Year BSCPE (2nd SEMESTER)\\Software Design\\ICONS VETA\\icons8-pet-100.png");
		catrequestPet.setBounds(650,65,120,25);
		catrequestPet.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String getCatID = catrequestTextField.getText();
				
				//check if empty
				if(getCatID.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Enter the ID of cat you want to adopt.");
					catrequestTextField.setText("");
					return;
				}
				//check if field is alphabet input
				if(!getCatID.matches("\\d+")) {
					JOptionPane.showMessageDialog(null, "Numbers only!");
					catrequestTextField.setText("");
					return;
				}
				
				
				
				try {
					//cat id
					int catID = Integer.parseInt(getCatID);
			    	
					Class.forName("com.mysql.cj.jdbc.Driver");
	                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylogindb","root","burog181");
	               String sql = "SELECT * from customer_information WHERE user_id=? AND owner_status=?";
	                PreparedStatement pstmt = con.prepareStatement(sql);
	                // Execute query
	                // if owner is verified
	                pstmt.setInt(1, user_id);
	                pstmt.setString(2, verified);
	                ResultSet rs = pstmt.executeQuery();
	                if(rs.next()) {
	                	//get owner name
	                	String owner_name = rs.getString("pet_owner");
	                	sql = "SELECT * FROM adopted_pet WHERE owner_id=? AND status=?";
	                	pstmt = con.prepareStatement(sql);
	                	pstmt.setInt(1, user_id);
	                	pstmt.setString(2, "In Scheduling");
	                	rs = pstmt.executeQuery();
	                	// if pet is in scheduling user cant adopt
	                	if(rs.next()) {
	                		JOptionPane.showMessageDialog(null, "You can't adopt another pet. Finish your latest queue first!");
	                	// check if for adoption
	                	}else {
		                    String status = "For Adoption";
		                    String sql2 = "SELECT * FROM cat_for_adoption WHERE cat_id=? AND cat_status=?";
		                    pstmt = con.prepareStatement(sql2);
		                    pstmt.setInt(1, catID);
		                    pstmt.setString(2, status);
		                    rs = pstmt.executeQuery();
		                    if(rs.next()) {
		                    	status = "In Briefing";
		                        sql2 = "SELECT * FROM cat_for_adoption WHERE cat_owner_id=? AND cat_status=?";
		                        pstmt = con.prepareStatement(sql2);
		                        pstmt.setInt(1, user_id);
		                        pstmt.setString(2, status);
		                        rs = pstmt.executeQuery();
		                        if(rs.next()) {
		                            JOptionPane.showMessageDialog(null, "Wait for your previous chosen pet to be approved!");
		                        } else {
		                            status = "In Briefing";
		                            sql = "UPDATE cat_for_adoption SET cat_status=?, cat_owner_id=?, cat_new_owner=? WHERE cat_id=?";
		                            pstmt = con.prepareStatement(sql);
		                            pstmt.setString(1, status);
		                            pstmt.setInt(2, user_id);
		                            pstmt.setString(3, owner_name);
		                            pstmt.setInt(4, catID);
		                            pstmt.executeUpdate();
		                            JOptionPane.showMessageDialog(null, "Successful");
		                            catrequestTextField.setText("");
		                                
		                            sql = "INSERT INTO adopted_pet SET pet_id=?, owner_id=?, owner_name=?, status=?, pet_type=?";
		                            pstmt = con.prepareStatement(sql);
		                            pstmt.setInt(1, catID);
		                            pstmt.setInt(2, user_id);
		                            pstmt.setString(3, owner_name);
		                            pstmt.setString(4, in_scheduling);
		                            pstmt.setString(5, "Cat");
		                            pstmt.executeUpdate();
		                            
		                            JOptionPane.showMessageDialog(null, "Wait for appointment schedule for pet adoption. Visit dashboard tab.");
		                        }
		                    } else {
		                        JOptionPane.showMessageDialog(null, "Try another pet.");
		                    }
	                	}
	                }else {
	                	JOptionPane.showMessageDialog(null, "Update pet information at dashboard tab!");
	                }
	                frame1.setVisible(false);
	                adopt(user_id);
			    } catch(Exception ex) {
			        ex.printStackTrace();
			    }		
			}
		});
		bottomPanel.add(catrequestPet);
		
		JLabel dogIDLabel = adoptLabel("Enter dog ID:");
		dogIDLabel.setFont(new Font("SansSerif BOLD",Font.PLAIN,15));
		dogIDLabel.setBounds(875,30,500,30);
		bottomPanel.add(dogIDLabel);
		
		JTextField dogrequestTextField = new JTextField();
		dogrequestTextField.setPreferredSize(new Dimension(70,30));
		dogrequestTextField.setBounds(975,30,100,25);
		bottomPanel.add(dogrequestTextField);
		
		JButton dogrequestPet = adoptButton("Adopt Dog","D:\\3rd Year BSCPE (2nd SEMESTER)\\Software Design\\ICONS VETA\\icons8-pet-100.png");
		dogrequestPet.setBounds(920,65,120,25);
		dogrequestPet.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String getDogID = dogrequestTextField.getText();
				
				
				if(getDogID.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Enter the ID of dog you want to adopt.");
					dogrequestTextField.setText("");
					return;
				}
				//check if input is string
				if(!getDogID.matches("\\d+")) {
					JOptionPane.showMessageDialog(null, "Numbers only!");
					dogrequestTextField.setText("");
					return;
				}
				
				try {
					int dogID = Integer.parseInt(getDogID);
					Class.forName("com.mysql.cj.jdbc.Driver");
	                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylogindb","root","burog181");
	                String sql = "SELECT * from customer_information WHERE user_id=? AND owner_status=?";
	                PreparedStatement pstmt = con.prepareStatement(sql);
	                // Execute query
	                pstmt.setInt(1, user_id);
	                pstmt.setString(2, verified);
	                ResultSet rs = pstmt.executeQuery();
	                if(rs.next()) {
	                	 String owner_name = rs.getString("pet_owner");
	                	sql = "SELECT * FROM adopted_pet WHERE owner_id=? AND status=?";
	                	pstmt = con.prepareStatement(sql);
	                	pstmt.setInt(1, user_id);
	                	pstmt.setString(2, "In Scheduling");
	                	rs = pstmt.executeQuery();
	                	if(rs.next()) {
	                		JOptionPane.showMessageDialog(null, "You can't adopt another pet. Finish your latest queue first!");
	                	}else {
		                    String status = "For Adoption";
		                    String sql2 = "SELECT * FROM dog_for_adoption WHERE dog_id=? AND dog_status=?";
		                    pstmt = con.prepareStatement(sql2);
		                    pstmt.setInt(1, dogID);
		                    pstmt.setString(2, status);
		                    rs = pstmt.executeQuery();
		                    if(rs.next()) {
		                        sql2 = "SELECT * FROM dog_for_adoption WHERE dog_owner_id=? AND dog_status=?";
		                        status = "In Briefing";
		                        pstmt = con.prepareStatement(sql2);
		                        pstmt.setInt(1, user_id);
		                        pstmt.setString(2, status);
		                        rs = pstmt.executeQuery();
		                        if(rs.next()) {
		                            JOptionPane.showMessageDialog(null, "Wait for your previous chosen pet to be approved!");
		                        } else {
		                            status = "In Briefing";
		                            sql = "UPDATE dog_for_adoption SET dog_status=?, dog_owner_id=?, dog_new_owner=? WHERE dog_id=?";
		                            pstmt = con.prepareStatement(sql);
		                            pstmt.setString(1, status);
		                            pstmt.setInt(2, user_id);
		                            pstmt.setString(3, owner_name);
		                            pstmt.setInt(4, dogID);
		                            pstmt.executeUpdate();
		                            JOptionPane.showMessageDialog(null, "Successful");
		                            catrequestTextField.setText("");
		                            
		                            sql = "INSERT INTO adopted_pet SET pet_id=?, owner_id=?, owner_name=?, status=?, pet_type=?";
		                            pstmt = con.prepareStatement(sql);
		                            pstmt.setInt(1,dogID);
		                            pstmt.setInt(2, user_id);
		                            pstmt.setString(3, owner_name);
		                            pstmt.setString(4, in_scheduling);
		                            pstmt.setString(5, "Dog");
		                            pstmt.executeUpdate();
		                            
		                            JOptionPane.showMessageDialog(null, "Wait for appointment schedule for pet adoption. Visit dashboard tab.");
		                        }
		                    } else {
		                        JOptionPane.showMessageDialog(null, "Try another pet.");
		                   }
	                	}
	                } else {
	                	JOptionPane.showMessageDialog(null, "Update pet information at dashboard tab!");
	                }             
	                frame1.setVisible(false);
	                adopt(user_id);
			    } catch(Exception ex) {
			        ex.printStackTrace();
			    }		
			}
		});
		bottomPanel.add(dogrequestPet);
		
		adoptDetailPanel.add(topPanel, BorderLayout.NORTH);
		adoptDetailPanel.add(leftPanel, BorderLayout.WEST);
		adoptDetailPanel.add(rightPanel, BorderLayout.EAST);
		adoptDetailPanel.add(centerPanel, BorderLayout.CENTER);
		adoptDetailPanel.add(bottomPanel, BorderLayout.SOUTH);
		
		
		
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
				try {
					adopt(user_id);
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
