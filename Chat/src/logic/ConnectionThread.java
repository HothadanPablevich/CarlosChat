package logic;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class ConnectionThread extends Thread{
	private ServerLogic server;
	private ArrayList<Socket> clientSockets = new ArrayList<Socket>();
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
			while(!kill) {
			clientSockets.add(server.getServerSocket().accept());
			System.out.println(clientSockets.size()+" Clients connected");
			server.setClientSockets(clientSockets);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
