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
import java.awt.Color;
import javax.swing.border.BevelBorder;

public class InterfaceChat extends JFrame {

	private JPanel contentPane;
	private JTextField mensaje;
	private JTextPane TextChat = new JTextPane();
	BufferedReader input;
    PrintStream output ;
    ReaderThread reader;
    WriterThread writer;
    

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
		//To read from the server      
		try {
			input = new BufferedReader( new InputStreamReader(cli.getSocket().getInputStream()));
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}   
	    try {
			output = new PrintStream(cli.getSocket().getOutputStream());
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	    //reader= new ReaderThread(input, output);
		//reader.start();
		writer = new WriterThread(input, output, TextChat);
		writer.start();
		///
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 725, 449);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel LabelName = new JLabel(cli.getName());
		LabelName.setForeground(Color.GREEN);
		LabelName.setFont(new Font("Times New Roman", Font.BOLD, 17));
		LabelName.setHorizontalAlignment(SwingConstants.CENTER);
		LabelName.setBounds(100, 10, 131, 20);
		contentPane.add(LabelName);
		
		TextChat.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.GREEN, Color.GREEN, Color.GREEN, Color.GREEN));
		TextChat.setEditable(false);
		TextChat.setBackground(Color.BLACK);
		TextChat.setForeground(Color.GREEN);
		TextChat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		TextChat.setBounds(10, 36, 548, 321);
		contentPane.add(TextChat);
		
		mensaje = new JTextField();
		mensaje.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLUE, Color.BLUE, Color.BLUE, Color.BLUE));
		mensaje.setBounds(10, 368, 548, 31);
		contentPane.add(mensaje);
		mensaje.setColumns(10);
		
    	
		JButton SendButton = new JButton("Send");
		SendButton.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLUE, Color.BLUE, Color.BLUE, Color.BLUE));
		SendButton.setForeground(Color.GREEN);
		SendButton.setBackground(Color.BLACK);
		SendButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		SendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean exit=false;//bandera para controlar ciclo del programa
		    	//Socket para la comunicacion cliente servidor              
		            //To read from the user (keyboard)           
		            //Scanner tec = new Scanner(System.in);
		            //send the line to the server
		            String line="";
		            	System.out.println("Client mensaje: ");                
		                 line = cli.getName() +": " + mensaje.getText();
		            	 output.println(line);
		            	 //TextChat.setText(TextChat.getText() + "\n" + cli.getName() + ": " + mensaje.getText());
		            	 mensaje.setText("");
		            //read the answer and print it
		            //String st = input.readLine();
		            //if( st != null ) System.out.println("Client -> received message: " + st );    
		            //System.out.println("Client -> End of the program");    

			}
		});
		SendButton.setBounds(568, 368, 131, 31);
		contentPane.add(SendButton);
		
		JButton Button_Disconnect = new JButton("Disconnect");
		Button_Disconnect.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.RED, Color.RED, Color.RED, Color.RED));
		Button_Disconnect.setBackground(Color.BLACK);
		Button_Disconnect.setForeground(Color.RED);
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
		Button_Disconnect.setFont(new Font("Tahoma", Font.PLAIN, 13));
		Button_Disconnect.setBounds(573, 38, 126, 40);
		contentPane.add(Button_Disconnect);
	}
}
