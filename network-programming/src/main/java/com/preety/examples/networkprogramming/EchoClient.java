package com.preety.examples.networkprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class EchoClient {
	

	public static void main(String[] args) {
		String host= args.length > 0 ? args[0] : "localhost";
		
		try {
			Socket socket= new Socket(host, 8009);
			BufferedReader in= new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out= new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			// send data to server
			for(int i=0; i<10; i++) {
				out.println("sending line " + i);
				out.println("line " + i);
				out.flush();
			}
			out.println("BYE");
			out.flush();
			
			//receive data from server
			while(true) {
				String str= in.readLine();
				if(str==null) break;
				System.out.println("received line " + str);
				
			}
			in.close();
			out.close();
			socket.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
