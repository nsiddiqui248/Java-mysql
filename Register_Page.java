package logged;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import java.util.regex.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextPane;
import java.awt.Color;

public class Register_Page extends JFrame {

	private JPanel contentPane1;
	private JTextField textr;
	private JTextField passr;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register_Page frame = new Register_Page();
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
	public Register_Page() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 281, 300);
		contentPane1 = new JPanel();
		contentPane1.setBackground(new Color(204, 255, 153));
		contentPane1.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane1);
		contentPane1.setLayout(null);
		
		JLabel lblRegisterForm = new JLabel("Register form");
		lblRegisterForm.setBounds(10, 11, 263, 31);
		lblRegisterForm.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegisterForm.setFont(new Font("Times New Roman", Font.BOLD, 26));
		contentPane1.add(lblRegisterForm);
		
		textr = new JTextField();
		textr.setBounds(102, 66, 147, 31);
		contentPane1.add(textr);
		textr.setColumns(10);
		
		passr = new JTextField();
		passr.setBounds(102, 122, 147, 31);
		contentPane1.add(passr);
		passr.setColumns(10);
		
		
		JButton btnRegister = new JButton("Register");
		btnRegister.setBackground(new Color(102, 153, 204));
		btnRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String ln=passr.getText();
				String fn=textr.getText();
				
				if(fn.isEmpty()&&ln.isEmpty())
					JOptionPane.showMessageDialog(null, "Please Enter Username & Password");
				else if(fn.isEmpty())
					JOptionPane.showMessageDialog(null,"fn");
				else if(ln.isEmpty())
					JOptionPane.showMessageDialog(null, "Please Enter Password");
				else {
				
				try {
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/loginuser","root","root");
					PreparedStatement ps=conn.prepareStatement("INSERT INTO User (UserName, Password)VALUES('"+fn+"','"+ln+"')");
					ps.executeUpdate();
					
				}catch(Exception e) {
					System.out.println(e);
				}
					System.out.println("else");
					JOptionPane.showMessageDialog(null, "Registered Sucessfully...");
				Login_Page lp=new Login_Page();
				lp.setVisible(true);
				dispose();
				}}
		});
		btnRegister.setBounds(10, 189, 89, 23);
		contentPane1.add(btnRegister);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(10, 74, 82, 14);
		contentPane1.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 130, 82, 14);
		contentPane1.add(lblPassword);
		
		JLabel lblAlreadyRegisteredGo = new JLabel("Already Registered? Go Back to Login Page");
		lblAlreadyRegisteredGo.setCursor(getCursor());
		lblAlreadyRegisteredGo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblAlreadyRegisteredGo.setForeground(Color.RED);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblAlreadyRegisteredGo.setForeground(Color.BLACK);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				Login_Page lp=new Login_Page();
				lp.setVisible(true);
			}
		});
		lblAlreadyRegisteredGo.setBounds(10, 223, 239, 14);
		contentPane1.add(lblAlreadyRegisteredGo);
	}
}
