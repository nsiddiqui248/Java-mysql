package logged;

import java.sql.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.ImageIcon;

public class Login_Page extends JFrame {

	private JPanel contentPane;
	private JTextField user;
	private JPasswordField pass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login_Page frame = new Login_Page();
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
	public Login_Page() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 264, 311);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(119, 136, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLoginPage = new JLabel("Login Page");
		lblLoginPage.setForeground(new Color(255, 255, 255));
		lblLoginPage.setBounds(10, 11, 234, 31);
		lblLoginPage.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoginPage.setFont(new Font("Times New Roman", Font.BOLD, 26));
		contentPane.add(lblLoginPage);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setForeground(new Color(245, 245, 220));
		lblUsername.setBounds(20, 53, 159, 14);
		contentPane.add(lblUsername);
		
		user = new JTextField();
		//user.setOpaque(true);
		user.setBounds(20, 69, 201, 25);
		contentPane.add(user);
		user.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(new Color(245, 245, 245));
		lblPassword.setBounds(20, 116, 141, 14);
		contentPane.add(lblPassword);
		
		pass = new JPasswordField();
		pass.setBounds(20, 134, 201, 25);
		contentPane.add(pass);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//register();
			}
		});
		btnLogin.setForeground(new Color(0, 0, 0));
		btnLogin.setBackground(new Color(51, 153, 153));
		btnLogin.setBounds(20, 181, 89, 23);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					//Class.forName("com.mysql.jdbc.Driver");
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/loginuser", "root", "root");
					Statement stmt=conn.createStatement();
					String sql="Select * from User where UserName='"+user.getText()+"'and Password='"+pass.getText()+"'";
					ResultSet rs=stmt.executeQuery(sql);
					if(rs.next()) {
						JOptionPane.showMessageDialog(null, "Login Successful...");
						dispose();
						DatabaseEntry de=new DatabaseEntry();
						de.setVisible(true);
					}
					else
						JOptionPane.showMessageDialog(null, "Wrong Username or Password...");
					conn.close();
				}catch (Exception e) {
					System.out.println(e);
				}
				
			}
		});
		contentPane.add(btnLogin);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBackground(new Color(255, 102, 0));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCancel.setBounds(132, 181, 89, 23);
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});
		contentPane.add(btnCancel);
		
		JLabel lblNewUserRegister = new JLabel("New User? Register here");
		lblNewUserRegister.setForeground(new Color(255, 255, 255));
		lblNewUserRegister.setBounds(20, 215, 172, 14);
		contentPane.add(lblNewUserRegister);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.setBackground(new Color(153, 204, 153));
		btnRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
				Register_Page rp=new Register_Page();
				rp.setVisible(true);
			}
		});
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		btnRegister.setBounds(20, 238, 201, 23);
		contentPane.add(btnRegister);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("D:\\Mozilla downloads\\lp.jpg"));
		label.setBounds(0, 0, 248, 272);
		contentPane.add(label);
	}
}
