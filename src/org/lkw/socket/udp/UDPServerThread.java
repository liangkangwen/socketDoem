package org.lkw.socket.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServerThread extends Thread {
	DatagramSocket socket = null;
	DatagramPacket packet = null;

	@Override
	public void run() {
		try {
			
			byte[] dataClient = packet.getData();
			System.out.println("我是服务器，客户端说：" + new String(dataClient));     
			
			//给客户端一个响应
			InetAddress address = packet.getAddress();
			int port = packet.getPort();
			byte[] message = "服务器已收到！".getBytes();
			DatagramPacket datagramPacket = new DatagramPacket(message, message.length,address,port);
			socket.send(datagramPacket);
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
	
	public UDPServerThread(DatagramSocket socket, DatagramPacket packet) {
		this.socket = socket;
		this.packet = packet;
	}

}
