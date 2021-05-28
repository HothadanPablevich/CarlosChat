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
    private final static int PORT = 3509;
    private static  ServerSocket serverSocket;
    private ArrayList<Socket> clientSockets= new  ArrayList<Socket>();
    private ArrayList<BufferedReader> clientBuffersIn= new  ArrayList<BufferedReader>();
    private ArrayList<PrintStream> clientBuffersOut= new  ArrayList<PrintStream>();
    private ArrayList<ReaderThread> clientThread= new  ArrayList<ReaderThread>();
    
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
	public ArrayList<PrintStream> getClientBuffersOut() {
		return clientBuffersOut;
	}
	public void setClientBuffersOut(ArrayList<PrintStream> clientBuffersOut) {
		this.clientBuffersOut = clientBuffersOut;
	}
	public ArrayList<ReaderThread> getClientThread() {
		return clientThread;
	}
	public void setClientThread(ArrayList<ReaderThread> clientThread) {
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
            ConnectionThread conn = new ConnectionThread(server);
            conn.start();
            //Client Socket
            /*Socket client;
            System.out.println("Server waiting for a client...");  
            client = server.getServerSocket().accept();
            //setSoLinger closes the socket giving 10mS to receive the remaining data
            client.setSoLinger (true, 10);
            //an input reader to read from the socket
            BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintStream output = new PrintStream(client.getOutputStream());
            ReaderThread reader= new ReaderThread(input,output);
            
            reader.start();*/
            String line ="";
            
            while(true){
            	
            //to print data out                
            // now we read a line from the input
            //System.out.println("Server -> type a sentence to send to the client:");
            //line=input.readLine();
           // System.out.println("Server -> read line (keyboard): " + line);
            //output.flush();//empty contents 
           // output.println(line);       
            //System.out.println("Server -> send the line to the client");
            }
            //close connection
            //server.getClientSockets().get(0).close();
            //server.getServerSocket().close();
               
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
  
}
