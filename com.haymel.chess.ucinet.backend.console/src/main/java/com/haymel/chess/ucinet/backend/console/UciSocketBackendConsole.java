package com.haymel.chess.ucinet.backend.console;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.haymel.chess.ucinet.StreamToStream;

public class UciSocketBackendConsole {

	public static void main(String[] args) throws IOException, InterruptedException {
		System.out.println("Server started");
		
		int port = 4711;

		try(ServerSocket serverSocket = new ServerSocket(port)) {
			System.out.println("waiting for frontend ... ");

			try (Socket socket = serverSocket.accept()) {
				System.out.println("frontend connected");
				InputStream in = socket.getInputStream();
				OutputStream out = socket.getOutputStream();
				
				Thread t1 = new Thread(new StreamToStream(System.in, out));
				Thread t2 = new Thread(new StreamToStream(in, System.out));
				
				t1.start();
				t2.start();
				
				t1.join();
				t2.join();
			}
			
		}
	}

}
