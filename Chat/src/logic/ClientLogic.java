package logic;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
/** ClientExample class, to create a simple example to connect two computer via a 
 * TCPIP connection
 *  */
public class ClientLogic {
    /*Port 5002*/
	private Socket socket;
	
	private String name;
    
    private static boolean connection = true;
    
    public static boolean isConnection() {
		return connection;
	}

	public ClientLogic(String name, int port, String server) {
    	setName(name);
    	try {
			socket = new Socket(server,port);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	@Override
	public String toString() {
		return "ClientLogic [socket=" + socket + ", name=" + name + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public void setConnection(boolean connection) {
		ClientLogic.connection = connection;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	
	public static void main(String[] args) {
    	boolean exit=false;//bandera para controlar ciclo del programa
    	//Socket para la comunicacion cliente servidor        
        try {            
            System.out.println("Client -> Start");  
            ClientLogic cli = new ClientLogic("Carlos", 3502, "192.168.31.11");
            //To read from the server      
            BufferedReader input = new BufferedReader( new InputStreamReader(cli.getSocket().getInputStream()));   
            ReaderThread reader= new ReaderThread(input);
            reader.start();
            //to write to the server
            PrintStream output = new PrintStream(cli.getSocket().getOutputStream());                
            //To read from the user (keyboard)           
            Scanner tec = new Scanner(System.in);                            
            //send the line to the server
            String line="";
            while(!line.equalsIgnoreCase("quit")){
            	System.out.println("Client -> Write a sentence to send:");                
                line = tec.nextLine();
            	 output.println(line);
            }
           
            //read the answer and print it
            //String st = input.readLine();
            //if( st != null ) System.out.println("Client -> received message: " + st );    
            System.out.println("Client -> End of the program");    
                 
            cli.getSocket().close();
                                    
       } catch (IOException ex) {
         System.err.println("Client -> " + ex.getMessage());
       }
    }
}