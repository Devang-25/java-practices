package com.preety.examples.networkprogramming.chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class ChatHandler extends Thread {
	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;
	private static Set<ChatHandler> handlers = new HashSet<ChatHandler>();

	public ChatHandler(Socket incoming) throws IOException {
		super();
		this.socket = incoming;
		in = new DataInputStream(socket.getInputStream());
		out = new DataOutputStream(socket.getOutputStream());
		handlers.add(this);
	}

	public void run() {
		String name = "";
		try {
			name = in.readUTF();
			System.out.println("new client " + name + "from address " + socket.getInetAddress());

			broadcast(name + " entered");
			while (true) {
				broadcast(name + ": " + in.readUTF());
			}
		} catch (IOException e) {
			System.out.println("Connection to the user is lost");
		} finally {
			handlers.remove(this);
			try {
				broadcast(name + " left");
				in.close();
				out.close();
				socket.close();
			} catch (IOException e) {
				System.out.println("Error while closing: " + e.getMessage());
			}

		}
	}

	/*
	 * Note that this method needs to be synchronized because it will be invoked by
	 * all the threads that are handling clients.
	 */
	static synchronized void broadcast(String message) throws IOException {
		for (ChatHandler handler : handlers) {

			handler.out.writeUTF(message);
			handler.out.flush();
		}
	}
}
