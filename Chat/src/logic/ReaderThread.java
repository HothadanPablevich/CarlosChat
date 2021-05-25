package logic;

import java.io.BufferedReader;
import java.io.IOException;



public class ReaderThread extends Thread {
	BufferedReader bufferinput;
	public ReaderThread(BufferedReader buffer){
		this.bufferinput=buffer;
	}
	@Override
	public void run(){
		while(true) {
			try {
				System.out.println(bufferinput.readLine());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				break;
			}
		}
	}

}
