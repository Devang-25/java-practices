package com.preety.examples.networkprogramming;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * An echo server that handles multiple clients simultaneously
 * Use a separate thread for each client.
 */
public class MultiEchoServer {

	public static void main(String[] args) {
		ServerSocket server;
		try {
			server = new ServerSocket(8009);

			while (true) {
				Socket incoming = server.accept();
				new ClientHandler(incoming).start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
