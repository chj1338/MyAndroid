package org.tacademy.socket;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class javaSocketClient {

	public static void main(String[] args) {
		String host = "localhost";
		int port = 7001;
		
		try {
			Socket socket = new Socket(host, port);
			println("서버에 연결하였습니다. : " + host + ":" + port);
			
			String output = " hellow!!!";				
			ObjectOutputStream outstream = new ObjectOutputStream(socket.getOutputStream());
			outstream.writeObject(output);
			outstream.flush();				
			println("서버로 보낸 데이터" + output);
			
			ObjectInputStream instream = new ObjectInputStream(socket.getInputStream());
			Object input = instream.readObject();
			println("서버로부터 받은 데이터 : " + input);
			
			outstream.close();
			instream.close();
			socket.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void println(String data) {
		System.out.println(data);
	}

}
