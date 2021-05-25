package logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.Scanner;
/** ServerExample class, to create a simple example to connect two computer via a 
 * TCPIP connection
 *  */
public class ServerLogic {

    /*We keep the port in a constant*/
    private final static int PORT = 3503;

    public static void main(String[] args) {
        
        try {
        	Scanner tec = new Scanner(System.in);
            //Server Socket to wait for network requests
            ServerSocket server = new ServerSocket(PORT);
            System.out.println("Server started");    
              
            //Client Socket
            Socket client;
            System.out.println("Server waiting for a client...");  
            client = server.accept();
            //setSoLinger closes the socket giving 10mS to receive the remaining data
            client.setSoLinger (true, 10);
            //an input reader to read from the socket
            BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
            ReaderThread reader= new ReaderThread(input);
            reader.start();
            String line ="";
            PrintStream output = new PrintStream(client.getOutputStream());
            while(!line.equalsIgnoreCase("quit")){
            //to print data out                
            // now we read a line from the keyboard
            System.out.println("Server -> type a sentence to send to the client:");
            line= tec.nextLine();
            System.out.println("Server -> read line (keyboard): " + line);
            output.flush();//empty contents 
            output.println(line);       
            System.out.println("Server -> send the line to the client");
            }
            //close connection
            client.close();
            server.close();
               
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
  
}
