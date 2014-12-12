package luma.jira.rtx.agent;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

import rtx.RTXSvrApi;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class RTXAgent {
	public static void main(String[] args) {
		// init rtx server api
		RTXSvrApi rtx = new RTXSvrApi();  
		if(!rtx.Init()) {
			System.out.println("failed to init RTX server api");
			return;
		}
		
		// start waiting for client
		ServerSocket servSocket = null;
		try {
			servSocket = new ServerSocket(20141);
			while (true) {
				Socket clientSocket = servSocket.accept();
				startClientThread(clientSocket, rtx);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			rtx.UnInit();
		}
	}
	
	private static void startClientThread(final Socket clientSocket, final RTXSvrApi rtx) {
		Thread t = new Thread() {
			@Override
			public void run() {
				int read = 0;
				byte[] buf = new byte[65535];
				SocketAddress clientAddress = clientSocket.getRemoteSocketAddress();
				System.out.println("Handling client at " + clientAddress);

				try {
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
						read -= 4 + length;
						
						// create json
						try {
	                        JSONObject json = JSONObject.fromObject(receivedData);
	                        String msg = json.optString("msg");
	                        JSONArray receiversArray = json.optJSONArray("receivers");
	                        String receivers = receiversArray.join(",");
                        	int ret = rtx.sendNotify(receivers, "Jira通知", msg, "0", "0");
                        	if(ret != 0) {
                        		System.out.println(String.format("发消息给%s失败: %s", receivers, msg));
                        	}
                        } catch (Exception e) {
                        	System.out.println(String.format("parse json error for %s", receivedData));
                        }
					}
					clientSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						if(clientSocket != null) {
							clientSocket.close();
						}
					} catch (IOException e) {
					}
				}
			}
		};
		t.setDaemon(true);
		t.start();
	}
}
