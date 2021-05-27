package interface_;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

import logic.*;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Scanner;
import java.awt.event.ActionEvent;

public class InterfaceChat extends JFrame {

	private JPanel contentPane;
	private JTextField Mensaje;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceChat frame = new InterfaceChat(null);
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
	public InterfaceChat(ClientLogic cli) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel LabelName = new JLabel(cli.getName());
		LabelName.setFont(new Font("Times New Roman", Font.BOLD, 12));
		LabelName.setHorizontalAlignment(SwingConstants.CENTER);
		LabelName.setBounds(100, 10, 90, 15);
		contentPane.add(LabelName);
		
		JTextPane TextChat = new JTextPane();
		TextChat.setBounds(10, 30, 415, 240);
		contentPane.add(TextChat);
		
		Mensaje = new JTextField();
		Mensaje.setBounds(10, 280, 415, 20);
		contentPane.add(Mensaje);
		Mensaje.setColumns(10);
		
		try {            
            System.out.println("Client -> Start");  
            //To read from the server      
            BufferedReader input = new BufferedReader( new InputStreamReader(cli.getSocket().getInputStream()));   
            ReaderThread reader= new ReaderThread(input);
            reader.start();
		} catch (IOException ex) {
	         System.err.println("Client -> " + ex.getMessage());
	       }
		
    	
		JButton SendButton = new JButton("Send");
		SendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean exit=false;//bandera para controlar ciclo del programa
		    	//Socket para la comunicacion cliente servidor        
		        try {             
		            //To read from the server      
		            BufferedReader input = new BufferedReader( new InputStreamReader(cli.getSocket().getInputStream()));   
		            
		            //to write to the server
		            PrintStream output = new PrintStream(cli.getSocket().getOutputStream());
		            ReaderThread reader= new ReaderThread(input, output);
		            reader.start();
		            //To read from the user (keyboard)           
		            Scanner tec = new Scanner(System.in);
		            //send the line to the server
		            String line="";
		            	System.out.println("Client mensaje: ");                
		                line = Mensaje.getText();
		            	 output.println(line);
		            	 TextChat.setText(TextChat.getText() + "\n" + "Client_" + cli.getName() + ": " + Mensaje.getText());
		            	 Mensaje.setText("");
		            //read the answer and print it
		            //String st = input.readLine();
		            //if( st != null ) System.out.println("Client -> received message: " + st );    
		            //System.out.println("Client -> End of the program");    
		                 
		                              
		       }catch (IOException ex) {
			       System.err.println("Client -> " + ex.getMessage());
			   }
			}
		});
		SendButton.setBounds(430, 280, 90, 25);
		contentPane.add(SendButton);
		
		JButton Button_Disconnect = new JButton("Disconnect");
		Button_Disconnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cli.getSocket().close();
					System.exit(0);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		Button_Disconnect.setFont(new Font("Tahoma", Font.PLAIN, 8));
		Button_Disconnect.setBounds(435, 30, 90, 25);
		contentPane.add(Button_Disconnect);
	}
}
