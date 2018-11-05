package com.elasticsearch;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerSocketInitializer {

	// Setting up a Multithreaded Server in Java
	private static final int PORT_NUMBER = 5000;
	private static ServerSocket serverSocket;
	private static ClientHandler clientHandler;
	private static Thread thread;

	public static void initialize() throws IOException {
		serverSocket = new ServerSocket(PORT_NUMBER);

		while (true) {
			clientHandler = new ClientHandler(serverSocket.accept());
			thread = new Thread(clientHandler);
			System.out.println("started at port : " + PORT_NUMBER);
			thread.start();
			
		}
	}

	protected void finalize() throws IOException {
		serverSocket.close();
	}

}
