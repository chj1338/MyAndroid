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
			println("���� ���۵� : " + port);
			
			while(true) {
				Socket socket = server.accept();
				println("Ŭ���̾�Ʈ�� �����߽��ϴ�.");
				
				ObjectInputStream instream = new ObjectInputStream(socket.getInputStream());
				Object input = instream.readObject();
				println("Ŭ���̾�Ʈ�κ��� ���� ������ : " + input);
				
				String output = input + " from Server";				
				ObjectOutputStream outstream = new ObjectOutputStream(socket.getOutputStream());
				outstream.writeObject(output);
				outstream.flush();				
				println("Ŭ���̾�Ʈ�� ���� ������" + output);
				
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
