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
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;


public class About_Tab {
	
	private JFrame frame1;
	private JPanel navPanel, mainPanel, containerPanel, mainContainerPanel, tabPanel, petDataPanel;
	private JLabel vetaLabel, tabLabel;
	
	
	
	public ImageIcon imageIcon(String name) {
		ImageIcon image = new ImageIcon(name);
		Image imagecon = image.getImage(); // Get the original image
		Image scaledImage = imagecon.getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Scale the image
		ImageIcon dbScaled = new ImageIcon(scaledImage); // Create a new ImageIcon with the scaled image
		return dbScaled;
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
		About_Tab at = new About_Tab();
		at.about(1);
	}
	
	public void about(int user_id) throws SQLException, IOException{
		
		String tabDescription = "ABOUT";
		
		frame1 = new JFrame("VETA");
		frame1.setSize(1736,964);
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
		navPanel.setPreferredSize(new Dimension(300,864));
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

		mainContainerPanel = new JPanel();
		mainContainerPanel.setBounds(20,70,1380,835);
		mainContainerPanel.setLayout(null);
		mainContainerPanel.setBackground(Color.decode("#313338"));
		containerPanel.add(mainContainerPanel);
		
		JPanel accountsPanel = new JPanel();
		accountsPanel.setBounds(10,30,1230,200);
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
		customerTable.setPreferredScrollableViewportSize(new Dimension(1220,170));
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
		
		
		JPanel shippingAccountPanel = new JPanel();
		shippingAccountPanel.setBounds(10,300,1230,200);
		shippingAccountPanel.setBackground(Color.decode("#616463"));
		mainContainerPanel.add(shippingAccountPanel);
		
		String[] columnTitle2 = {"ID","Owner", "Email", "PhoneNumber", "Pet","Gender", "Name", "Breed", "Bday", "Acc Status"};
		DefaultTableModel model2 = new DefaultTableModel();
		JTable customerShippingTable = new JTable(model2);
		JScrollPane spane2 = new JScrollPane(customerShippingTable);
		customerShippingTable.setOpaque(true);
		customerShippingTable.setBackground(Color.decode("#f2f2f2"));
		customerShippingTable.setRowHeight(20);
		customerShippingTable.setEnabled(false);
		customerShippingTable.setPreferredScrollableViewportSize(new Dimension(1220,170));
		customerShippingTable.getTableHeader().setBackground(new Color(30,30,30));
		customerShippingTable.getTableHeader().setForeground(new Color(200,200,200));

		for(int i = 0; i <= columnTitle2.length-1; i++) {
		    model2.addColumn(columnTitle2[i]);
		}

		//Create a custom cell renderer for the first row
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
		for (int i = 0; i < customerShippingTable.getColumnCount(); i++) {
		    customerShippingTable.getColumnModel().getColumn(i).setCellRenderer(customRenderer2);
		}

		// Calculate the preferred width of each column
		for (int column = 0; column < customerShippingTable.getColumnCount(); column++) {
		    int maxWidth = 0;
		    TableColumn tableColumn = customerShippingTable.getColumnModel().getColumn(column);
		    for (int row = 0; row < customerShippingTable.getRowCount(); row++) {
		        TableCellRenderer cellRenderer = customerShippingTable.getCellRenderer(row, column);
		        Object value = customerShippingTable.getValueAt(row, column);
		        Component cellComponent = cellRenderer.getTableCellRendererComponent(customerShippingTable, value, false, false, row, column);
		        maxWidth = Math.max(maxWidth, cellComponent.getPreferredSize().width);
		    }
		    tableColumn.setPreferredWidth(maxWidth);
		}
		shippingAccountPanel.add(new JScrollPane(customerShippingTable));

		
		
		JPanel medicalRecordPanel = new JPanel();
		medicalRecordPanel.setBounds(10,570,1230,200);
		medicalRecordPanel.setBackground(Color.decode("#616463"));
		mainContainerPanel.add(medicalRecordPanel);

		String[] columnTitle3 = {"ID", "Owner", "Breed", "Type", "Birthdate", "Height (cm)", "Weight (kg)", "Vaccine Date", "Next Appointment", "Remarks"};
		DefaultTableModel model3 = new DefaultTableModel();
		JTable medicalRecordTable = new JTable(model3);
		JScrollPane spane3 = new JScrollPane(medicalRecordTable);
		medicalRecordTable.setOpaque(true);
		medicalRecordTable.setBackground(Color.decode("#f2f2f2"));
		medicalRecordTable.setRowHeight(20);
		medicalRecordTable.setEnabled(false);
		medicalRecordTable.setPreferredScrollableViewportSize(new Dimension(1220,170));
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
		
		JButton aboutButton = myButton("ABOUT","D:\\3rd Year BSCPE (2nd SEMESTER)\\Software Design\\ICONS VETA\\icons8-about-50.png");
		buttonPanel.add(aboutButton);
		aboutButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame1.setVisible(false);
				try {
					about(user_id);
				} catch (SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
//		frame1.pack();
		frame1.setVisible(true);
	}
	
//	public static void main(String[] args) {
public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
