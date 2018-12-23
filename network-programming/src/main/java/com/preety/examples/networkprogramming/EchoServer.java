package com.preety.examples.networkprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	
	public static void main(String args[]) {
		ServerSocket server;
		try {
			server = new ServerSocket(8008);
			Socket incoming= server.accept();
			BufferedReader in= new BufferedReader(new InputStreamReader(incoming.getInputStream()));
			PrintWriter out= new PrintWriter(new OutputStreamWriter(incoming.getOutputStream()));
			out.println("Hello, this is response from ServerSocket to Socket client in java");
			out.println("Enter BYE to exit");
			out.flush();
			
			while(true) {
				String str= in.readLine();
				if(str==null) {
					break; // client closed connection
				}
				out.println("ECHO: "+ str);
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
