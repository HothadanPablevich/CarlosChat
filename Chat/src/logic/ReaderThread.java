package logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;



public class ReaderThread extends Thread {
	BufferedReader bufferinput;
	PrintStream clientBuffersOut;
	public ReaderThread(BufferedReader buffer, PrintStream output){
		this.bufferinput = buffer;
		this.clientBuffersOut = output;
	}
	@Override
	public void run(){
		while(true) {
			try {
				System.out.println(bufferinput.readLine());
				clientBuffersOut.print(bufferinput.readLine());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				break;
			}
		}
	}

}
