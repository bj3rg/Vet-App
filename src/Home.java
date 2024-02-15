import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import javax.print.attribute.standard.JobPriority;
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
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import java.sql.*;
import java.text.SimpleDateFormat;
public class Home{
	private JPanel panel2;
	private String s;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JLabel petLabels(String title) {
		JLabel label = new JLabel(title);
		label.setFont(new Font("SansSerif Bold", Font.PLAIN, 18));
		return label;
	}
	
	private JTextField petTextFields() {
		JTextField textField = new JTextField();
		textField.setSize(200,200);
		textField.setFont(new Font("SansSerif",Font.PLAIN,15));
		return textField;
	}
	private JButton medicalButton(String title) {
		
		JButton addButton = new JButton(title);
		addButton.setForeground(Color.decode("#dbdee1"));
		addButton.setBorder(null);
		addButton.setFocusable(false);
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

	public static void main(String[] args) {
		Home h = new Home();
		h.showCalendar();
	}
	
	
	public void showCalendar() {
//		
//        JPanel  panel = new JPanel();
//        panel.setBounds(20,20,300,500);
//        panel.setBackground(Color.white);
//        
//        JCalendar calendar = new JCalendar();
//		calendar.setSelectableDateRange(new Date(), null);
//        calendar.setTodayButtonVisible(true);
//        calendar.setWeekOfYearVisible(true);
//        calendar.setLocale(Locale.US);
//        DefaultTableModel model = new DefaultTableModel();
//        model.addColumn("EVENT");
//        model.addColumn("TIME");
//        frame.add(panel);
//        panel.add(calendar);
////		frame.getContentPane().add(calendar, BorderLayout.CENTER);
//		frame.setSize(1000,1500);
////		frame.setLayout(new BorderLayout());
//		frame.setVisible(true);
		JFrame frame = new JFrame("Appointment");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1500,1500);
		frame.setLayout(new BorderLayout());
		
		JPanel  panel = new JPanel(new BorderLayout());
		panel.setBackground(Color.white);
		panel.setLayout(new BorderLayout());
		frame.add(panel, BorderLayout.CENTER);
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER,120,20));
		
		panel1.setBackground(Color.yellow);
		panel2.setBackground(Color.pink);

		
		panel1.setPreferredSize(new Dimension(600,300));
		panel2.setPreferredSize(new Dimension(600,300));
		
		panel.add(panel1, BorderLayout.WEST);
		panel.add(panel2, BorderLayout.EAST);

		JLabel lbl7 = petLabels("IMAGE");
		panel1.add(lbl7);
		
		JLabel lbl1 = petLabels("AGE");
		panel2.add(lbl1);
		
		JTextField txt1 = petTextFields();
		txt1.setPreferredSize(new Dimension(200,30));
		panel2.add(txt1);
		
		
		JLabel lbl2 = petLabels("GENDER");
		panel2.add(lbl2);
		
		JTextField txt2 = petTextFields();
		txt2.setPreferredSize(new Dimension(200,30));
		panel2.add(txt2);
		
		JLabel lbl3 = petLabels("DESCRIPTION");
		panel2.add(lbl3);
		
		JTextField txt3 = petTextFields();
		txt3.setPreferredSize(new Dimension(200,30));
		panel2.add(txt3);
		
		JLabel lbl5 = petLabels("STATUS");
		panel2.add(lbl5);
		
		String myArray[] = {"For-Adoption", "In-Briefing", "Adopted"};
		JComboBox<String> comboBreed = new JComboBox<>(myArray);
		comboBreed.setPreferredSize(new Dimension(200,30));
		comboBreed.setSelectedIndex(-1);
		panel2.add(comboBreed);
		
		JLabel lbl4 = petLabels("IMAGE");
		panel2.add(lbl4);
	
		JButton browsePhoto = new JButton("BROWSE");
		browsePhoto.setPreferredSize(new Dimension(200,30));
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
					Image img_cat = imaIcon.getImage().getScaledInstance(400,570,Image.SCALE_SMOOTH);
					lbl7.setIcon(new ImageIcon(img_cat));;
					s = path;
				}else if (result == JFileChooser.CANCEL_OPTION) {
					System.out.println("No data inserted!");
				}
			}
		});
		panel2.add(browsePhoto);
		
		JLabel lbl9 = petLabels("IMAGE");
		panel2.add(lbl9);
		
		String myPet[] = {"Dog", "Cat"};
		JComboBox<String> comboType = new JComboBox<>(myPet);
		comboType.setPreferredSize(new Dimension(200,30));
		comboType.setSelectedIndex(-1);
		panel2.add(comboType);
		
		JButton button1 = medicalButton("ADD");
		button1.setPreferredSize(new Dimension(200,30));
		button1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			//txt1 = age
				//txt2 =gender
				//txt3 = description
				//browsephoto imagegetter
				String pet_age = txt1.getText();
				String pet_gender = txt2.getText();
				String pet_description = txt3.getText();
				String pet_status = (String) comboBreed.getSelectedItem();
				String pet_type = (String) comboType.getSelectedItem();
				
				
				if(pet_type.equals("Cat")) {
					try {
					    Class.forName("com.mysql.cj.jdbc.Driver");
					    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylogindb","root","burog181");
					    String sql = "INSERT INTO cat_for_adoption (cat_age, cat_gender, cat_description, cat_image, cat_status) VALUES (?,?,?,?,?)";
					    InputStream is = new FileInputStream(new File(s));
					    PreparedStatement pstmt = con.prepareStatement(sql);
					    pstmt.setString(1, pet_age);
					    pstmt.setString(2, pet_gender);
					    pstmt.setString(3, pet_description);
					    pstmt.setBlob(4, is);
					    pstmt.setString(5, pet_status);

					    int rows = pstmt.executeUpdate();
					    JOptionPane.showMessageDialog(null, "Successfully inserted");
					    con.close();
					} catch (ClassNotFoundException | SQLException | FileNotFoundException e1) {
					    e1.printStackTrace();
					}
				}else {
					try {
					    Class.forName("com.mysql.cj.jdbc.Driver");
					    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mylogindb","root","burog181");
					    String sql = "INSERT INTO dog_for_adoption (dog_age, dog_gender, dog_description, dog_image, dog_status) VALUES (?,?,?,?,?)";
					    InputStream is = new FileInputStream(new File(s));
					    PreparedStatement pstmt = con.prepareStatement(sql);
					    pstmt.setString(1, pet_age);
					    pstmt.setString(2, pet_gender);
					    pstmt.setString(3, pet_description);
					    pstmt.setBlob(4, is);
					    pstmt.setString(5, pet_status);

					    int rows = pstmt.executeUpdate();
					    JOptionPane.showMessageDialog(null, "Successfully inserted");
					    con.close();
					} catch (ClassNotFoundException | SQLException | FileNotFoundException e1) {
					    e1.printStackTrace();
					}
				}
				
		
			}
		});
		panel2.add(button1);
		
	
		
		frame.setVisible(true);
		
		
		
	}

	
}