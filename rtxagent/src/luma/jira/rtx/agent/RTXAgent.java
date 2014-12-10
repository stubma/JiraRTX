package luma.jira.rtx.agent;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class RTXAgent {
	public static void main(String[] args) {
		// Create a server socket to accept client connection request
		ServerSocket servSocket = null;
		int read = 0;
		byte[] buf = new byte[65535];

		try {
			servSocket = new ServerSocket(20141);
			while (true) {
				Socket clientSocket = servSocket.accept();
				SocketAddress clientAddress = clientSocket.getRemoteSocketAddress();
				System.out.println("Handling client at " + clientAddress);

				InputStream in = clientSocket.getInputStream();

				// receive until client close connection, indicate by -l return
				for(int i = 0; i != -1; i = in.read(buf, read, buf.length - read)) {
					// add read
					read += i;
					if(read < 4)
						continue;
					
					// read length
					int length = ((buf[0] << 24) & 0xff000000) | ((buf[1] << 16) & 0xff0000) | ((buf[2] << 8) & 0xff00) | (buf[3] & 0xff);
					
					// check data
					if(length > read - 4)
						continue;
					
					// get body
					String receivedData = new String(buf, 4, length, "utf-8");
					System.out.println(receivedData);
					
					// compact
					for(int j = 4 + length; j < read; j++) {
						buf[j - 4 - length] = buf[j];
					}
				}
				clientSocket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
