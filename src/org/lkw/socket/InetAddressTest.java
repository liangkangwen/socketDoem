package org.lkw.socket;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

public class InetAddressTest {
	
	public static void main(String[] args) throws UnknownHostException {
		InetAddress address = InetAddress.getLocalHost();
		String hostName = address.getHostName();
		System.out.println(hostName + address.getHostAddress());
		System.out.println(Arrays.toString(address.getAddress()));
	}
}
