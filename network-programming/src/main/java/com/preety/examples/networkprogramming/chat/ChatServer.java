package com.preety.examples.networkprogramming.chat;

import java.io.IOException;
import java.net.ServerSocket;

public class ChatServer {
	/*
	 * Development of a chat server that handles multiple clients simultaneously
	 * broadcasts a message received from a client to all other active clients. We
	 * need to keep track of active clients.
	 */
	
	public ChatServer(int port) throws IOException {
		ServerSocket server= new ServerSocket(port);
		while(true) {
			new ChatHandler(server.accept()).start();
		}
	}
	
	
	public static void main(String[] args) {
		if(args.length ==0) {
			throw new RuntimeException("Syntax: java ChatServer <port>");
		}
		try {
			new ChatServer(Integer.parseInt(args[0]));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
