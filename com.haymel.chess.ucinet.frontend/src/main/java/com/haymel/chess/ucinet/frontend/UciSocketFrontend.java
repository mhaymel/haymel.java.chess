/***************************************************
 * (c) Markus Heumel
 *
 * @date 10.11.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.ucinet.frontend;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import com.haymel.chess.ucinet.StreamToStream;

public class UciSocketFrontend {
	
	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
		
		int port = 4711;
		String host = "localhost";
		
		try(Socket socket = new Socket(host, port)) {
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
