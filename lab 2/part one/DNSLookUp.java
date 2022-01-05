/**
 * A simple program demonstrating DNS lookup
 *
 * Usage:
 *	java DNSLookUp <IP Name>
 */

import java.io.*;
import java.net.*;

public class DNSLookUp
{
	public static String addressLookup(String domainName) {


		InetAddress hostAddress;

		try {
			hostAddress = InetAddress.getByName(domainName);
			return hostAddress.getHostAddress();
		}
		catch (UnknownHostException uhe) {
			return "Unknown host: " + domainName;
		}
	}
}

