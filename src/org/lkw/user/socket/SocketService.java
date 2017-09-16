package org.lkw.user.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketService {
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		Socket socket = null;
		try {
			//创建 serverSocket 并绑定端口
			serverSocket = new ServerSocket(9999);
			System.out.println("******** 服务已启动  ********");
			while (true) {
				//等待客户端请求
				socket = serverSocket.accept();
				ServiceThread thread = new ServiceThread(socket);
				thread.start();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
			
	}

}
