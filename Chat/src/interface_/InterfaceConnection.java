package interface_;


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

import logic.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;

public class InterfaceConnection extends JFrame {

	private JPanel contentPane;
	private JTextField textField_IP_Direcction;
	private JTextField textField_Port_Number;
	private JTextField textField_Username;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceConnection frame = new InterfaceConnection();
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
	public InterfaceConnection() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 270, 375);
		contentPane = new JPanel();
		contentPane.setForeground(Color.GREEN);
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_Title1 = new JLabel("CONNECTION");
		lbl_Title1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Title1.setForeground(Color.GREEN);
		lbl_Title1.setFont(new Font("Times New Roman", Font.BOLD, 28));
		lbl_Title1.setBounds(10, 30, 235, 25);
		contentPane.add(lbl_Title1);
		
		JLabel lbl_Title2 = new JLabel("PARAMETERS");
		lbl_Title2.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Title2.setForeground(Color.GREEN);
		lbl_Title2.setFont(new Font("Times New Roman", Font.BOLD, 28));
		lbl_Title2.setBounds(10, 70, 235, 25);
		contentPane.add(lbl_Title2);
		
		JLabel lbl_IP_Direction = new JLabel("IP Direction:");
		lbl_IP_Direction.setForeground(Color.GREEN);
		lbl_IP_Direction.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lbl_IP_Direction.setBounds(10, 130, 85, 15);
		contentPane.add(lbl_IP_Direction);
		
		textField_IP_Direcction = new JTextField();
		textField_IP_Direcction.setToolTipText("");
		textField_IP_Direcction.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.RED, new Color(0, 0, 255), new Color(0, 0, 255), new Color(0, 0, 255)));
		textField_IP_Direcction.setBounds(100, 130, 130, 20);
		contentPane.add(textField_IP_Direcction);
		textField_IP_Direcction.setColumns(10);
		
		JLabel lbl_Port_Number = new JLabel("Port Number:");
		lbl_Port_Number.setForeground(Color.GREEN);
		lbl_Port_Number.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lbl_Port_Number.setBounds(10, 195, 85, 15);
		contentPane.add(lbl_Port_Number);
		
		textField_Port_Number = new JTextField();
		textField_Port_Number.setColumns(10);
		textField_Port_Number.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.RED, new Color(0, 0, 255), new Color(0, 0, 255), new Color(0, 0, 255)));
		textField_Port_Number.setBounds(100, 195, 130, 20);
		contentPane.add(textField_Port_Number);
		
		JLabel lbl_Username = new JLabel("Username:");
		lbl_Username.setForeground(Color.GREEN);
		lbl_Username.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lbl_Username.setBounds(10, 260, 85, 15);
		contentPane.add(lbl_Username);
		
		textField_Username = new JTextField();
		textField_Username.setColumns(10);
		textField_Username.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.RED, new Color(0, 0, 255), new Color(0, 0, 255), new Color(0, 0, 255)));
		textField_Username.setBounds(100, 260, 130, 20);
		contentPane.add(textField_Username);
		
		JButton btn_Connection = new JButton("CONNECT");
		btn_Connection.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btn_Connection.setBounds(85, 300, 100, 25);
		contentPane.add(btn_Connection);
		
		//Valores para probar
		textField_IP_Direcction.setText("localhost");
		textField_Port_Number.setText("3501");
		textField_Username.setText("Paco");
		
		btn_Connection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int port = Integer.parseInt(textField_Port_Number.getText());
				System.out.println(port);
				ClientLogic cli = new ClientLogic(textField_Username.getText(),port, textField_IP_Direcction.getText());
				InterfaceChat fc = new InterfaceChat(cli);
				fc.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				fc.setVisible(true);
				dispose();
				//System.out.println(cli.toString());
			}
		});
	}
}
