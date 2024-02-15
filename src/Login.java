import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login implements ActionListener {

	//for LOGIN as user
	
	private int loginAttempts = 0;
	private JFrame frame1;
	private JPanel panel1;
	private JLabel vetaLabel, userLabel, passLabel, secretkey;
	private JPasswordField passText, secretText, rpassText, rpassText2;
	private JTextField userText;
	private JButton loginButton, registerButton;
	
	//For REGISTER
	private JLabel regLabel, ruserLabel, rpassLabel, rpassLabel2;
	private JTextField ruserText;
	private JButton submitButton, backButton;
	
	//For AdminvsUser
	private JButton adminButton, userButton;
	
	private JCheckBox showPassCheckbox;

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
	
	public Login() {
		adminVsUser();
	}
	
	//ADMIN or USER
	public void adminVsUser() {
		 frame1 = new JFrame("VETA ADMIN OR USER");
		 frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 frame1.setSize(1200,700);
		 frame1.setLocationRelativeTo(null);
		 frame1.setVisible(true);
		 
		 JPanel panel3 = new JPanel();
		 panel3.setBounds(0,0,1200,700);
		 panel3.setLayout(null);
		 panel3.setBackground(Color.decode("#1e1f22"));
		 frame1.add(panel3);
		 
		 panel1 = new JPanel();
		 
		 panel1.setBounds(0,0,120,100);
		 panel1.setBackground(Color.red);
		 frame1.add(panel1);

		 adminButton = medicalButton("ADMIN", "");
		 adminButton.setBounds(250,250,270,80);
		 adminButton.setFont(new Font("SansSerif Bold", Font.PLAIN, 50));
		 panel3.add(adminButton);
		 adminButton.addActionListener(this);
		 adminButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame1.setVisible(false);
				loginasAdmin();
			}
		});
		 
		 userButton = medicalButton("USER","");
		 userButton.setBounds(700,250,270,80);
		 userButton.setFont(new Font("SansSerif Bold", Font.PLAIN, 50));
		 panel3.add(userButton);
		 userButton.addActionListener(this);
		 userButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame1.setVisible(false);
				loginasUser();
			}
		});
	}
	
	//LOGIN AS USER
	public void loginasUser() {
		
		
		 frame1 = new JFrame("VETA Login as USER");
		 frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 frame1.setSize(1200,700);
		 frame1.setLayout(null);
		 frame1.setLocationRelativeTo(null);
		 
		 panel1 = new JPanel();
		 panel1.setLayout(null);
		 panel1.setBounds(0,0,1200,700);
		 panel1.setBackground(Color.decode("#313338"));
		 frame1.add(panel1);
		 
		 vetaLabel = new JLabel("Welcome to VETA, User!");
		 vetaLabel.setBounds(360,50,800,50);
		 vetaLabel.setForeground(Color.decode("#FFFFFF"));
		 vetaLabel.setFont(new Font("SansSerif Bold", Font.PLAIN, 40));
		 panel1.add(vetaLabel);
		 
		 userLabel = new JLabel("USERNAME:");
		 userLabel.setBounds(370,215,200,25);
		 userLabel.setForeground(Color.decode("#FFFFFF"));
		 userLabel.setFont(new Font("SansSerif Bold", Font.PLAIN, 20));
		 panel1.add(userLabel);
		 
		 userText = new JTextField(20);
		 userText.setBounds(500,200,300,40);
		 panel1.add(userText);
		 
		 passLabel = new JLabel("PASSWORD:");
		 passLabel.setForeground(Color.decode("#FFFFFF"));
		 passLabel.setBounds(370,312,200,25);
		 passLabel.setFont(new Font("SansSerif Bold", Font.PLAIN, 20));
		 panel1.add(passLabel);
		 
		 passText = new JPasswordField(20);
		 passText.setBounds(500,300,300,40);
		 panel1.add(passText);
		 
		 showPassCheckbox = new JCheckBox("Show password");
		 showPassCheckbox.setForeground(Color.decode("#FFFFFF"));
		 showPassCheckbox.setBackground(Color.decode("#313338"));
		 showPassCheckbox.setFocusable(false);
		 showPassCheckbox.setBounds(500, 360, 120, 25);
		 showPassCheckbox.addActionListener(this);
		 showPassCheckbox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(showPassCheckbox.isSelected()) {
					passText.setEchoChar('\u0000');
				}else {
					passText.setEchoChar('•');
				}
			}
		});
		 panel1.add(showPassCheckbox);
		 
		 loginButton = medicalButton("LOGIN","");
		 loginButton.setBounds(500,400,200,40);
		 loginButton.addActionListener(this);
		 loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = userText.getText();
				String password = new String(passText.getPassword());
			    
			    if (username.trim().isEmpty() || password.trim().isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Username and password cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
		            userText.setText("");
		        	passText.setText("");
		            return;
		            }
			    
			    try {
			    	Class.forName("com.mysql.cj.jdbc.Driver");
	                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylogindb","root","burog181");
	                PreparedStatement pstmt = con.prepareStatement("SELECT * FROM customer WHERE BINARY username = ? AND BINARY password = ?");
	                pstmt.setString(1, username);
	                pstmt.setString(2, password);
	                // Execute query
	                ResultSet rs = pstmt.executeQuery();
			        if (rs.next()) {
			        	int user_id = rs.getInt("user_id"); // get user_id of logged on         
			        	JOptionPane.showMessageDialog(null, "Login Successful");
			            userText.setText("");
			        	passText.setText("");
			        	frame1.setVisible(false);//	
			        	Dashboard_Tab show_Dashboard_Tab = new Dashboard_Tab();
			        	show_Dashboard_Tab.dashboard(user_id);
			        } else {
			        	JOptionPane.showMessageDialog(null, "Invalid username or password!", "Error", JOptionPane.ERROR_MESSAGE);
			        	userText.setText("");
			        	passText.setText("");
			        }
			        rs.close();
			        pstmt.close();
			        con.close();
			    } catch(Exception ex) {
			        ex.printStackTrace();
			    }
			}
		});
		 panel1.add(loginButton);
		 
		 registerButton = new JButton("<html><u>New User? Register here.</u></html>");
		 registerButton.setBounds(970,618,200,30);
		 registerButton.setForeground(Color.decode("#FFFFFF"));
		 registerButton.addActionListener(this);
		 registerButton.setFocusable(false);
		 registerButton.setBorder(null);
		 registerButton.setBackground(Color.decode("#313338"));
		 registerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame1.setVisible(false);
				register();		
			}
		});
		 panel1.add(registerButton);
		 
		 backButton = medicalButton("BACK TO MAIN","");
		 backButton.setBounds(10,610,200,40);
		 backButton.addActionListener(this);
		 panel1.add(backButton);
		 backButton.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				frame1.setVisible(false);
				adminVsUser();
			}
		});
		 frame1.setVisible(true);
	}
	
	//LOGIN AS ADMIN
	public void loginasAdmin() {
		
		 frame1 = new JFrame("VETA Login");
		 frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 frame1.setSize(1200,700);
		 frame1.setLayout(null);
		 frame1.setLocationRelativeTo(null);
		 

		 panel1 = new JPanel();
		 panel1.setLayout(null);
		 panel1.setBounds(0,0,1200,700);
		 panel1.setBackground(Color.decode("#313338"));
		 frame1.add(panel1);
		 
		 vetaLabel = new JLabel("Welcome to VETA, Admin!");
		 vetaLabel.setForeground(Color.decode("#dbdee1"));
		 vetaLabel.setBounds(360,40,800,50);
		 vetaLabel.setFont(new Font("SansSerif Bold", Font.PLAIN, 40));
		 panel1.add(vetaLabel);
		 
		 userLabel = new JLabel("USERNAME:");
		 userLabel.setForeground(Color.decode("#dbdee1"));
		 userLabel.setBounds(370,215,200,25);
		 userLabel.setFont(new Font("SansSerif Bold", Font.PLAIN, 20));
		 panel1.add(userLabel);
		 
		 userText = new JTextField(20);
		 userText.setBounds(500,200,300,40);
		 panel1.add(userText);
		 
		 passLabel = new JLabel("PASSWORD:");
		 passLabel.setForeground(Color.decode("#dbdee1"));
		 passLabel.setBounds(370,282,200,25);
		 passLabel.setFont(new Font("SansSerif Bold", Font.PLAIN, 20));
		 panel1.add(passLabel);
		 
		 passText = new JPasswordField(20);
		 passText.setBounds(500,270,300,40);
		 panel1.add(passText);
		 
		 secretkey = new JLabel("SECRET KEY:");
		 secretkey.setForeground(Color.decode("#dbdee1"));
		 secretkey.setBounds(362,350,200,25);
		 secretkey.setFont(new Font("SansSerif Bold", Font.PLAIN, 20));
		 panel1.add(secretkey);
		 
		 secretText = new JPasswordField(20);
		 secretText.setBounds(500,340,300,40);
		 panel1.add(secretText);
		 
		 showPassCheckbox = new JCheckBox("Show password");
		 showPassCheckbox.setBackground(Color.decode("#313338"));
		 showPassCheckbox.setForeground(Color.decode("#dbdee1"));
		 showPassCheckbox.setBounds(500, 400, 120, 25);
		 showPassCheckbox.addActionListener(this);
		 showPassCheckbox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(showPassCheckbox.isSelected()) {
					passText.setEchoChar('\u0000');
					secretText.setEchoChar('\u0000');
				}else {
					passText.setEchoChar('•');
					secretText.setEchoChar('•');
				}
			}
		});
		 panel1.add(showPassCheckbox);
		 
		 loginButton = medicalButton("LOGIN","");
		 loginButton.setBounds(500,500,200,40);
		 loginButton.addActionListener(this);
		 loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				String username = userText.getText();
			    String password = passText.getText();
			    String secret_key = secretText.getText();
			    
			    if (username.trim().isEmpty() || password.trim().isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Username and password cannot be empty!");
		            userText.setText("");
		        	passText.setText("");
		        	secretText.setText("");
		            return;
		            }
			    
			    try {
			        Class.forName("com.mysql.cj.jdbc.Driver");
			        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylogindb","root","burog181");
			        PreparedStatement pstmt = con.prepareStatement("SELECT * FROM admin WHERE BINARY username = ?"); // add BINARY keyword to ensure case sensitive between user input and existing data in database
			        pstmt.setString(1, username);

			        // Execute query
			        ResultSet rs = pstmt.executeQuery();
			        if (rs.next()) {
			            String dbPassword = rs.getString("password");
			            String dbSecretKey = rs.getString("secret_key");
			            int ID = rs.getInt("admin_id");
			            if (password.equals(dbPassword) && secret_key.equals(dbSecretKey)) {
			                JOptionPane.showMessageDialog(null, "Login Successful");
			                userText.setText("");
			                passText.setText("");
			                frame1.setVisible(false);
			                Admin_Accounts at = new Admin_Accounts();
			                at.accounts(ID);
			            } else {
			                loginAttempts++;
			                if(loginAttempts == 5) {
			                    System.exit(0);
			                } else {
			                    JOptionPane.showMessageDialog(null, 5-loginAttempts+ " ATTEMPTS REMAINING \n Invalid password!" );
				                passText.setText("");
				                secretText.setText("");
			                }
			            }
			        } else {
			            JOptionPane.showMessageDialog(null, "Username does not exist!");
			            userText.setText("");
			            passText.setText("");
			            secretText.setText("");
			        }
			        rs.close();
			        pstmt.close();
			        con.close();
			    } catch(Exception ex) {
			        ex.printStackTrace();
			    }
			}
		 });
		 
		 panel1.add(loginButton);
		 
		 backButton = medicalButton("BACK TO MAIN","");
		 backButton.setBounds(10,610,200,40);
		 backButton.addActionListener(this);
		 panel1.add(backButton);
		 backButton.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame1.setVisible(false);
				adminVsUser();
			}
		});

		 frame1.setVisible(true);
	}
	
	//ACCOUNT REGISTRATIO<
	public void register() {
		 frame1 = new JFrame("VETA Register");
		 frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 frame1.setSize(1200,700);
		 frame1.setLocationRelativeTo(null);
		 frame1.setVisible(true);
		 
		 JPanel panel2 = new JPanel();
		 panel2.setBounds(0,0,1200,700);
		 panel2.setBackground(Color.decode("#313338"));
		 panel2.setLayout(null);
		 frame1.add(panel2);
		 
		 regLabel = new JLabel("Create an account!");
		 regLabel.setForeground(Color.decode("#dbdee1"));
		 regLabel.setBounds(410,30,800,100);
		 regLabel.setFont(new Font("SansSerif Bold", Font.PLAIN, 40));
		 panel2.add(regLabel);
		 
		 ruserLabel = new JLabel("USERNAME:");
		 ruserLabel.setForeground(Color.decode("#dbdee1"));
		 ruserLabel.setBounds(370,215,200,25);
		 ruserLabel.setFont(new Font("SansSerif Bold", Font.PLAIN, 20));
		 panel2.add(ruserLabel);
		 
		 ruserText = new JTextField(20);
		 ruserText.setBounds(500,200,300,40);
		 panel2.add(ruserText);
		 
		 rpassLabel = new JLabel("PASSWORD:");
		 rpassLabel.setForeground(Color.decode("#dbdee1"));
		 rpassLabel.setBounds(370,280,200,25);
		 rpassLabel.setFont(new Font("SansSerif Bold", Font.PLAIN, 20));
		 panel2.add(rpassLabel);
		 
		 rpassText = new JPasswordField(20);
		 rpassText.setBounds(500,270,300,40);
		 panel2.add(rpassText);
		 
		 rpassLabel2 = new JLabel("CONFIRM PASSWORD:");
		 rpassLabel2.setForeground(Color.decode("#dbdee1"));
		 rpassLabel2.setBounds(272,350,250,25);
		 rpassLabel2.setFont(new Font("SansSerif Bold", Font.PLAIN, 20));
		 panel2.add(rpassLabel2);
		 
		 rpassText2 = new JPasswordField(20);
		 rpassText2.setBounds(500,340,300,40);
		 panel2.add(rpassText2);
		 
		 showPassCheckbox = new JCheckBox("Show password");
		 showPassCheckbox.setFocusable(false);
		 showPassCheckbox.setForeground(Color.decode("#FFFFFF"));
		 showPassCheckbox.setBackground(Color.decode("#313338"));
		 showPassCheckbox.setBounds(500, 400, 120, 25);
		 showPassCheckbox.addActionListener(this);
		 panel2.add(showPassCheckbox);
		 showPassCheckbox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(showPassCheckbox.isSelected()) {
					rpassText.setEchoChar('\u0000');
					rpassText2.setEchoChar('\u0000');
				}else {
					rpassText.setEchoChar('•');
					rpassText2.setEchoChar('•');
				}
			}
		});

		 backButton = medicalButton("BACK TO MAIN","");
		 backButton.setBounds(10,610,200,40);
		 backButton.addActionListener(this);
		 panel2.add(backButton);
		 backButton.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				frame1.setVisible(false);
				adminVsUser();
			}
		});
		 
		 submitButton = medicalButton("SUBMIT","");
		 submitButton.setBounds(500,430,200,40);
		 submitButton.addActionListener(this);
		 submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = ruserText.getText();
				String password = rpassText.getText();
				String password2 = rpassText2.getText();
				
				if (username.trim().isEmpty() || password.trim().isEmpty() || password2.trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Username and Password is empty!", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (username.length() < 6) {
					JOptionPane.showMessageDialog(null, "Username should contain 6 characters!", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (password.length() < 8) {
					JOptionPane.showMessageDialog(null, "Password should be 8 character long", "Error", JOptionPane.ERROR_MESSAGE);
					rpassText.setText("");
	                rpassText2.setText("");
	                return;
				}
				if (!password.equals(password2)) {
	                JOptionPane.showMessageDialog(null, "Passwords do not match", "Error", JOptionPane.ERROR_MESSAGE);
	                rpassText.setText("");
	                rpassText2.setText("");
	                return;
	            }
				 try {
				        Class.forName("com.mysql.cj.jdbc.Driver");
				        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylogindb","root","burog181");
				        Statement stmt = con.createStatement();
				        String sql = "SELECT * FROM customer WHERE username='" + username + "'";
				        ResultSet rs = stmt.executeQuery(sql);
				        if (rs.next()) {
				            JOptionPane.showMessageDialog(null, "Username already exists!");
				            ruserText.setText("");
				            rpassText.setText("");
				            rpassText2.setText("");
				        } else {
				            sql = "INSERT INTO customer (username, password) VALUES ('" + username + "', '" + password + "')";
				            int rows = stmt.executeUpdate(sql);
				            
				            if(rows > 0) {
				            	sql = "SELECT LAST_INSERT_ID()";
				                rs = stmt.executeQuery(sql);
				                rs.next();
				                int user_id = rs.getInt(1);

				                // Insert a new record in the customer_information table
				                sql = "INSERT INTO customer_information (user_id) VALUES (" + user_id + ")";
				                rows = stmt.executeUpdate(sql);
				                JOptionPane.showMessageDialog(null, "Record Inserted Successfully!");
				                ruserText.setText("");
					            rpassText.setText("");
					            rpassText2.setText("");
					            frame1.setVisible(false);
					            loginasUser();
				            } else {
				                JOptionPane.showMessageDialog(null, "Failed to Insert Record!");
				                ruserText.setText("");
					            rpassText.setText("");
					            rpassText2.setText("");
				            }
				        }
				        con.close();
				        
				    } catch(Exception ex) {
				        ex.printStackTrace();
				    }
			}
		});
		 panel2.add(submitButton);
		frame1.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	}
}
