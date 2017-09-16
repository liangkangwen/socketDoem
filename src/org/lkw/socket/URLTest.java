package org.lkw.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class URLTest {

	public static void main(String[] args) {
		try {
			URL baidu = new URL("Https://www.baidu.com");
			URL url = new URL(baidu, "/index.html?wd=java");
			String host = url.getHost();
			int port = url.getPort();

			System.out.println(host + " " + port + "  " + url.getFile() + " " + url.getProtocol());
			System.out.println(url.getQuery());
			InputStream openStream = url.openStream();
			InputStreamReader isByteInputStream  = new InputStreamReader(openStream,"gb2312");
			BufferedReader br = new BufferedReader(isByteInputStream);
			String data = br.readLine();
			while (data != null) {
				System.out.println(data);
				data = br.readLine();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
