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
	ArrayList<Socket> clientSockets = new ArrayList<Socket>();
	 ArrayList<BufferedReader> clientBuffersIn= new  ArrayList<BufferedReader>();
    ArrayList<PrintStream> clientBuffersOut= new  ArrayList<PrintStream>();
   ArrayList<ReaderThread> clientThread= new  ArrayList<ReaderThread>();
   Socket client;
	
	private boolean kill= false;
	public ConnectionThread() {
		
	}
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
	public ArrayList<Socket> getClientSockets() {
		return clientSockets;
	}
	public ArrayList<BufferedReader> getClientBuffersIn() {
		return clientBuffersIn;
	}
	public ArrayList<PrintStream> getClientBuffersOut() {
		return clientBuffersOut;
	}
	public ArrayList<ReaderThread> getClientThread() {
		return clientThread;
	}
	public void setClientSockets(ArrayList<Socket> clientSockets) {
		this.clientSockets = clientSockets;
	}
	public void setClientBuffersIn(ArrayList<BufferedReader> clientBuffersIn) {
		this.clientBuffersIn = clientBuffersIn;
	}
	public void setClientBuffersOut(ArrayList<PrintStream> clientBuffersOut) {
		this.clientBuffersOut = clientBuffersOut;
	}
	public void setClientThread(ArrayList<ReaderThread> clientThread) {
		this.clientThread = clientThread;
	}
	////Methods////
	public void kill() {
		this.setKill(true);
	}
	public void killAllFromClient() {
		
	}
	public void run() {
		try {
			ConnectionThread conn= new ConnectionThread();
			while(!kill) {
			
			client = server.getServerSocket().accept();
			clientSockets.add(client);
			int indexControl = clientSockets.indexOf(client);
			BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
			clientBuffersIn.add(input);
			PrintStream output = new PrintStream(client.getOutputStream());
			//output.println(222222);
			clientBuffersOut.add(output);
			ReaderThread reader = new ReaderThread(input, conn, clientBuffersOut, client, indexControl);
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
