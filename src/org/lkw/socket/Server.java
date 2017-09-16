package org.lkw.socket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		Socket socket = null;
		try {
			int conut = 0;
			//创建 serverSocket 并绑定端口
			serverSocket = new ServerSocket(8888);
			while (true) {
				//等待客户端请求
				socket = serverSocket.accept();
				ServerThread thread = new ServerThread(socket);
				thread.start();
				conut++;
				InetAddress inetAddress = socket.getInetAddress();
				System.out.println("当前IP: " + inetAddress.getHostAddress() + "您是第" + conut + "个访客");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
			
	}
}
