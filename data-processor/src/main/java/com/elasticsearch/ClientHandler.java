package com.elasticsearch;

import java.lang.Runnable;
import java.net.Socket;
import java.io.*;

class ClientHandler implements Runnable {
  private Socket clientSocket;
  
  ClientHandler(Socket clientSocket) {
    this.clientSocket = clientSocket;
  } 
  
  public void run() {
  // create input buffer and output buffer
  // wait for input from client and send response back to client
	  try {
		Thread.sleep(10);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  // close all streams and sockets
  }
}