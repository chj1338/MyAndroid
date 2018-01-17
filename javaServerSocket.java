package org.tacademy.socket;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class javaServerSocket {

	public static void main(String[] args) {
		
		int port = 7001;
		
		try {
			ServerSocket server = new ServerSocket(port);
			println("서버 시작됨 : " + port);
			
			while(true) {
				Socket socket = server.accept();
				println("클라이언트가 접속했습니다.");
				
				ObjectInputStream instream = new ObjectInputStream(socket.getInputStream());
				Object input = instream.readObject();
				println("클라이언트로부터 받은 데이터 : " + input);
				
				String output = input + " from Server";				
				ObjectOutputStream outstream = new ObjectOutputStream(socket.getOutputStream());
				outstream.writeObject(output);
				outstream.flush();				
				println("클라이언트로 보낸 데이터" + output);
				
				instream.close();
				outstream.close();
				socket.close();
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void println(String data) {
		System.out.println(data);
	}

}
