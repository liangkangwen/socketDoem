package org.lkw.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	public static void main(String[] args) {
		
		try {
			Socket socket  = new Socket("localhost", 8888);
			OutputStream os = socket.getOutputStream();
			//将字节流转化成打印流
			PrintWriter pw = new PrintWriter(os);
			pw.write("客户端说：用户名/,密码/456");
			pw.flush();
			//关闭输出流
			socket.shutdownOutput();
			//接送服务端返回的信息
			InputStream is = socket.getInputStream();
			//将字节流转成字符流
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String data = br.readLine();
			while (data != null) {
				System.out.println(data);
				data =  br.readLine();
			}
			socket.shutdownInput();
			br.close();
			isr.close();
			is.close();
			pw.close();
			os.close();
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
