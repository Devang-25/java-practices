package com.preety.examples.networkprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientHandler extends Thread{
	private Socket incoming;
	
	public ClientHandler(Socket socket) {
		this.incoming= socket;
	}
	@Override
	public void run() {
		try {

			BufferedReader in= new BufferedReader(new InputStreamReader(incoming.getInputStream()));
			PrintWriter out= new PrintWriter(new OutputStreamWriter(incoming.getOutputStream()));
			out.println("Hello, this is ServerSocket response in java");
			out.println("Enter BYE to exit");
			out.flush();
			
			while(true) {
				String str= in.readLine();
				if(str==null) {
					break; // client closed connection
				}
				out.println("ECHO: from " + Thread.currentThread() + ": "+ str);
				out.flush();
				if(str.trim().equals("BYE")) break;
			}
			in.close();
			out.close();
			incoming.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
