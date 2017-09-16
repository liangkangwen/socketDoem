package org.lkw.socket.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {

	public static void main(String[] args) {
		
		try {
			//创建datagramSocket,指定端口号
			DatagramSocket socket = new DatagramSocket(9999);
			System.out.println("***服务器已启动，等待客户端响应***");
			int count = 0;
			while (true) {
				byte[] data = new byte[1024];
				//定义用于接收的数据报
				DatagramPacket packet = new DatagramPacket(data, data.length);
				//等待接收的数据，接收前会阻塞
				socket.receive(packet);
				UDPServerThread thread = new UDPServerThread(socket,packet);
				thread.start();
				System.out.println(count++);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
