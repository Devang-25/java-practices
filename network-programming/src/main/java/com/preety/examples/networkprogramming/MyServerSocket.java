package com.preety.examples.networkprogramming;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServerSocket {
	/*
	 * Sockets are the end points of connections between two hosts and can be used
	 * to send and receive data. There are two kinds of sockets: server sockets and
	 * client sockets. A server socket waits for requests from clients. A client
	 * socket can be used to send and receive data.
	 * 
	 * A server socket listens at a specific port. A port is positive integer less
	 * than or equal to 65565. The port number is necessary to distinguish different
	 * server applications running on the same host. Ports 1 through 1023 are
	 * reserved for administrative purposes (e.g. 21 for FTP, 23 for Telnet, 25 for
	 * e-mail, and 80 for HTTP).
	 * 
	 * A server socket is an instance of the ServerSocket class and can be created
	 * by one of these constructors: ServerSocket(int port) ServerSocket(int port,
	 * int backlog) ! ! port: port number at which the server will be listening for
	 * requests from clients. backlog: the maximum length of the queue of clients
	 * waiting to be processed (default is 50).
	 * 
	 * Socket accept() Waits for a connection request. The thread that executes the
	 * method will be blocked until a request is received, at which time the method
	 * return a client socket.
	 * 
	 * void close() Stops waiting for requests from clients.
	 */

	void listen(int port) {
		try {
			ServerSocket s = new ServerSocket(port);
			while (true) {
				Socket incoming = s.accept();
				
				// handle a client
				incoming.close();
				break;
			}
			s.close();

		} catch (IOException e) {
			// handle exception
			System.out.println(e.getMessage());
		}
	}

}
