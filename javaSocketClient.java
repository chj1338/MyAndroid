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
			println("������ �����Ͽ����ϴ�. : " + host + ":" + port);
			
			String output = " hellow!!!";				
			ObjectOutputStream outstream = new ObjectOutputStream(socket.getOutputStream());
			outstream.writeObject(output);
			outstream.flush();				
			println("������ ���� ������" + output);
			
			ObjectInputStream instream = new ObjectInputStream(socket.getInputStream());
			Object input = instream.readObject();
			println("�����κ��� ���� ������ : " + input);
			
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
