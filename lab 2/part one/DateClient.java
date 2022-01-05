/**
 * Client program requesting current date from server.
 *
 * Figure 3.28
 *
 * @author Silberschatz, Gagne and Galvin
 * Operating System Concepts  - Tenth Edition
 */ 

import java.net.*;
import java.io.*;

public class DateClient
{
	public static void main(String[] args)  {
		if (args.length != 1) {
			System.err.println("Usage: java DateClient <IP name>");
			System.exit(0);
		}
		
		DNSLookUp dnslookup = new DNSLookUp();
		int portNumber = 6013;
		try {
			
			// this could be changed to an IP name or address other than the localhost
			String ipAddress = dnslookup.addressLookup(args[0]);

			if ( ipAddress.length() > 15 && ipAddress.substring(0, 15) != "Unknown host: ") {
				System.out.println(ipAddress);
				System.exit(0);
			}
			else  {
				System.out.println("Connecting to " + args[0] + " at port " + portNumber);
				Socket sock = new Socket(ipAddress, portNumber);
				InputStream in = sock.getInputStream();
				BufferedReader bin = new BufferedReader(new InputStreamReader(in));

				String line;
				while( (line = bin.readLine()) != null)
					System.out.println(line);
				sock.close();
			}
		}
		catch (IOException ioe) {
				System.err.println(ioe);
		}
	}
}
