package org.lkw.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class ServerThread extends Thread {
	
	Socket socket = null;
	InputStream inputStream = null;
	InputStreamReader isr = null;
	BufferedReader br = null;
	@Override
	public void run() {

		try {
			inputStream = socket.getInputStream();
			//将字节流转换成字符流
			isr = new InputStreamReader(inputStream);
			//为字符流添加缓存
			br = new BufferedReader(isr);
			String data = br.readLine();
			while (data != null) {
				System.out.println(data);
				data =  br.readLine();
			}
			OutputStream os = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os);
			InetAddress inetAddress = socket.getInetAddress();
			pw.write("服务端说：欢迎你！" + inetAddress.getHostAddress());
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
				
				try {
					if (br != null) {
						br.close();
					}
					if (isr != null) {
						isr.close();
					}
					if (inputStream != null) {
						inputStream.close();
					}
					if (socket != null) {
						socket.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		
	}

	public ServerThread(Socket socket) {
		this.socket = socket;
	}
}
