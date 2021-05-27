package logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
/** ServerExample class, to create a simple example to connect two computer via a 
 * TCPIP connection
 *  */
public class ServerLogic {

    /*We keep the port in a constant*/
    private final static int PORT = 3503;
    private static  ServerSocket serverSocket;
    private ArrayList<Socket> clientSockets= new  ArrayList<Socket>();
    private ArrayList<BufferedReader> clientBuffersIn= new  ArrayList<BufferedReader>();
    private ArrayList<BufferedReader> clientBuffersOut= new  ArrayList<BufferedReader>();
    private ArrayList<Thread> clientThread= new  ArrayList<Thread>();
    
    public static ServerSocket getServerSocket() {
		return serverSocket;
	}
	public static void setServerSocket(ServerSocket serverSocket) {
		ServerLogic.serverSocket = serverSocket;
	}
	public ArrayList<Socket> getClientSockets() {
		return clientSockets;
	}
	public void setClientSockets(ArrayList<Socket> clientSockets) {
		this.clientSockets = clientSockets;
	}
	public ArrayList<BufferedReader> getClientBuffersIn() {
		return clientBuffersIn;
	}
	public void setClientBuffersIn(ArrayList<BufferedReader> clientBuffersIn) {
		this.clientBuffersIn = clientBuffersIn;
	}
	public ArrayList<BufferedReader> getClientBuffersOut() {
		return clientBuffersOut;
	}
	public void setClientBuffersOut(ArrayList<BufferedReader> clientBuffersOut) {
		this.clientBuffersOut = clientBuffersOut;
	}
	public ArrayList<Thread> getClientThread() {
		return clientThread;
	}
	public void setClientThread(ArrayList<Thread> clientThread) {
		this.clientThread = clientThread;
	}
	public ServerLogic (ServerSocket server) {
    	this.setServerSocket(server);
    }
    public static void main(String[] args) {
        
        try {
        	Scanner tec = new Scanner(System.in);
            //Server Socket to wait for network requests
        	ServerLogic server = new ServerLogic(new ServerSocket(PORT));
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
