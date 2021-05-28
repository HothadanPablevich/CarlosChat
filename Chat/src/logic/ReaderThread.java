package logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JTextPane;



public class ReaderThread extends Thread {
	BufferedReader bufferInput;
	PrintStream output;
	ArrayList<PrintStream> clientBuffersOut= new  ArrayList<PrintStream>();
	 int index;
	public ReaderThread(BufferedReader buffer, PrintStream output,ArrayList<PrintStream> allOuts, int index){
		this.bufferInput = buffer;
		this.output = output;
		this.clientBuffersOut = allOuts;
		this.index= index;
	}
	@Override
	public void run(){
		while(true) {
			try {
				String message="";
				message=bufferInput.readLine();
				//int indexControl = clientBuffersIn.indexOf(bufferInput);
				for(int i =0; i< clientBuffersOut.size(); i++) {
					clientBuffersOut.get(i).println(message);
					
					
				}
				System.out.println(message +" /////Client message " + index);
				//Scanner tec = new Scanner(System.in);
				//message= tec.nextLine();
				//output.println(message);
				//output.flush();
				//output.flush();
				//output.println(message);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				break;
			}
		}
	}

}
