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
		int recvMsgSize = 0;
		byte[] receivBuf = new byte[65535];

		try {
			servSocket = new ServerSocket(20141);
			while (true) {
				Socket clientSocket = servSocket.accept();
				SocketAddress clientAddress = clientSocket.getRemoteSocketAddress();
				System.out.println("Handling client at " + clientAddress);

				InputStream in = clientSocket.getInputStream();

				// receive until client close connection, indicate by -l return
				while ((recvMsgSize = in.read(receivBuf)) != -1) {
					String receivedData = new String(receivBuf, 0, recvMsgSize, "utf-8");
					System.out.println(receivedData);
				}
				clientSocket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
