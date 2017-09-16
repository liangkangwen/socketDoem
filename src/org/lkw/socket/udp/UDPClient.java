package org.lkw.socket.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
	public static void main(String[] args) {
		try {
			InetAddress address = InetAddress.getByName("localhost");
			int port = 9999;
			byte[] data = "我是客户端，你好！".getBytes();
			//定义数据报
			DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
			DatagramSocket socket = new DatagramSocket();
			socket.send(packet);
			
			//接收服务器反馈信息
			byte[] dataClient = new byte[1024];
			//定义用于接收的数据报
			DatagramPacket packetClient = new DatagramPacket(dataClient, dataClient.length);
			socket.receive(packetClient);
			System.out.println("我是客户端，服务器说：" + new String(packetClient.getData()));
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
