package logged;

import java.awt.EventQueue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLXML;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ImageIcon;

public class DatabaseEntry extends JFrame {

	private JPanel contentPane;
	private JTextField textf;
	private JTextField textn;
	private JTextField mob;
	private JTextField eid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DatabaseEntry frame = new DatabaseEntry();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DatabaseEntry() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 273, 297);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setForeground(Color.WHITE);
		lblFirstName.setBounds(15, 57, 84, 14);
		contentPane.add(lblFirstName);
		
		textf = new JTextField();
		textf.setBounds(101, 54, 128, 20);
		contentPane.add(textf);
		textf.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setForeground(Color.WHITE);
		lblLastName.setBounds(15, 103, 84, 14);
		contentPane.add(lblLastName);
		
		JLabel lblDatabase = new JLabel("Database");
		lblDatabase.setForeground(Color.WHITE);
		lblDatabase.setHorizontalAlignment(SwingConstants.CENTER);
		lblDatabase.setFont(new Font("Times New Roman", Font.BOLD, 26));
		lblDatabase.setBounds(10, 11, 237, 32);
		contentPane.add(lblDatabase);
		
		textn = new JTextField();
		textn.setBounds(101, 100, 128, 20);
		contentPane.add(textn);
		textn.setColumns(10);
		
		JButton btnEnter = new JButton("Enter");
		btnEnter.setBackground(new Color(0, 128, 128));
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textf.getText().equals("")&&textn.getText().equals("")&&mob.getText().equals("")&&eid.getText().equals(""))
					JOptionPane.showMessageDialog(null, "Please Enter The Required Deatils");
				else if(textf.getText().equals(""))
					JOptionPane.showMessageDialog(null, "Please Enter First Name");
				else if(textn.getText().equals(""))
					JOptionPane.showMessageDialog(null, "Please Enter Last Name");
				else if(mob.getText().equals(""))
					JOptionPane.showMessageDialog(null, "Please Enter Mobile Number");
				else if(eid.getText().equals(""))
					JOptionPane.showMessageDialog(null, "Please Enter Email Id");
				else {
				
				try {					
					String var1=textf.getText();
					String var2=textn.getText();
					String var3=mob.getText();
					String var4=eid.getText();
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/loginuser","root","root");
					
					String queryCheck = "SELECT * from dbTable WHERE FirstName = '"+var1+"'";
					PreparedStatement ps = conn.prepareStatement(queryCheck);
					//ps.setString(1, var1,var2);
					ResultSet rs2 = ps.executeQuery();
					if(rs2.next())
						JOptionPane.showMessageDialog(null, "Name Already Exists");
					else {
					
					Statement stmt=conn.createStatement();
					stmt.executeUpdate("INSERT INTO DbTable (FirstName, LastName, MobileNo, EmailId)VALUES('"+var1+"','"+var2+"','"+var3+"','"+var4+"')");
					
					textf.setText("");
					textn.setText("");
					mob.setText("");
					eid.setText("");
					JOptionPane.showMessageDialog(null, "Data Entered...");				
					
					}
					}catch(Exception e) {
				e.printStackTrace();
				}
				}		
			}
			});
		btnEnter.setBounds(15, 224, 89, 23);
		contentPane.add(btnEnter);
		
		JButton btnClose = new JButton("Close");
		btnClose.setBackground(new Color(255, 99, 71));
		btnClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});
		btnClose.setBounds(140, 224, 89, 23);
		contentPane.add(btnClose);
		
		JLabel lblMobileNo = new JLabel("Mobile No");
		lblMobileNo.setForeground(Color.WHITE);
		lblMobileNo.setBounds(15, 146, 64, 14);
		contentPane.add(lblMobileNo);
		
		mob = new JTextField();
		mob.setBounds(101, 143, 128, 20);
		contentPane.add(mob);
		mob.setColumns(10);
		
		JLabel lblEmailId = new JLabel("Email Id");
		lblEmailId.setForeground(Color.WHITE);
		lblEmailId.setBounds(15, 190, 69, 14);
		contentPane.add(lblEmailId);
		
		eid = new JTextField();
		eid.setBounds(101, 187, 128, 20);
		contentPane.add(eid);
		eid.setColumns(10);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("D:\\Mozilla downloads\\db.png"));
		label.setBounds(0, 0, 257, 258);
		contentPane.add(label);
	}
}
