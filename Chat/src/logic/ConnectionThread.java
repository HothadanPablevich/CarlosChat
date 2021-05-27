package logic;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class ConnectionThread extends Thread{
	private ServerLogic server;
	private ArrayList<Socket> clientSockets = new ArrayList<Socket>();
	public ServerLogic getServer() {
		return server;
	}
	public void setServer(ServerLogic server) {
		this.server = server;
	}
	ConnectionThread(ServerLogic server){
		this.setServer(server);
	}
	public void run() {
		try {
			clientSockets.add(server.getServerSocket().accept());
			System.out.println(clientSockets.size()+" Clients connected");
			server.setClientSockets(clientSockets);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
