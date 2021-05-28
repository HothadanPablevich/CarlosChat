package logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;

public class ConnectionThread extends Thread{
	private ServerLogic server;
	
	private boolean kill= false;
	public boolean isKill() {
		return kill;
	}
	public void setKill(boolean kill) {
		this.kill = kill;
	}
	public ServerLogic getServer() {
		return server;
	}
	public void setServer(ServerLogic server) {
		this.server = server;
	}
	ConnectionThread(ServerLogic server){
		this.setServer(server);
	}
	////Methods////
	public void kill() {
		this.setKill(true);
	}
	public void run() {
		try {
			ArrayList<Socket> clientSockets = new ArrayList<Socket>();
			 ArrayList<BufferedReader> clientBuffersIn= new  ArrayList<BufferedReader>();
		     ArrayList<PrintStream> clientBuffersOut= new  ArrayList<PrintStream>();
		    ArrayList<ReaderThread> clientThread= new  ArrayList<ReaderThread>();
		    Socket client;
			while(!kill) {
			
			client = server.getServerSocket().accept();
			clientSockets.add(client);
			int indexControl = clientSockets.indexOf(client);
			BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
			clientBuffersIn.add(input);
			PrintStream output = new PrintStream(client.getOutputStream());
			//output.println(222222);
			clientBuffersOut.add(output);
			ReaderThread reader = new ReaderThread(input, output, clientBuffersOut, indexControl);
			clientThread.add(reader);
			clientThread.get(indexControl).start();
			//reader.start();
			System.out.println(clientSockets.size()+" Clients connected");
			server.setClientSockets(clientSockets);
			server.setClientBuffersIn(clientBuffersIn);
			server.setClientBuffersOut(clientBuffersOut);
			server.setClientThread(clientThread);
			
			
			
			
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
