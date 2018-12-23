package com.preety.examples.networkprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientSockets {
	/*
	 * A client socket is an instance of the Socket class and can be obtained in two
	 * ways: (1) On the server side as return value of the accept() method. (2) On
	 * the client side by using the constructor
	 * 
	 * Socket(String host, int port) ! ! ! ! host: the address of the host port: the
	 * port number
	 * 
	 * Communication is handled on both sides by Socket objects.
	 * 
	 * Methods of Socket
	 * getInputStream() Returns an InputStream object for receiving data
	 * getOutputStream() Returns and OutputStream object for sending data close()
	 * Closes the socket connection
	 */
	
	void requestAndRecieveData(String host, int port) {
		try {
			Socket s= new Socket(host, port);
			BufferedReader in= new BufferedReader(new InputStreamReader(s.getInputStream()));
			OutputStreamWriter streamWriter= new OutputStreamWriter(s.getOutputStream());
			PrintWriter out= new PrintWriter(streamWriter);
			//send and recieve data;
			System.out.println("streamWriter: " + streamWriter.toString());
			System.out.println("out: " + out.toString());
			
			in.close();
			out.close();
			s.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
