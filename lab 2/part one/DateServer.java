/**
 * Time-of-day server listening to port 6013.
 *
 * Figure 3.27
 *
 * @author Silberschatz, Gagne, and Galvin. 
 * Operating System Concepts  - Tenth Edition
 * Copyright John Wiley & Sons - 2018.
 */
 
import java.net.*;
import java.io.*;

public class DateServer
{
	public static void main(String[] args)  {
		int portNumber = 6013;
		try {
			System.out.println("Starting Date Server, Binding ServerSocket to port " + portNumber + "...");
			ServerSocket sock = new ServerSocket(portNumber);

			// now listen for connections
			while (true) {
				Socket client = sock.accept();
				// we have a connection
				
				PrintWriter pout = new PrintWriter(client.getOutputStream(), true);
				// write the Date to the socket
				pout.println(new java.util.Date().toString());

				// close the socket and resume listening for more connections
				client.close();
			}
		}
		catch (IOException ioe) {
				System.err.println(ioe);
		}
	}
}
