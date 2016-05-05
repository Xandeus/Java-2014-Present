package androidNetworking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class OServer {
	public static void main(String[] args) {
		System.out.println("Server Started");
		OServer server = new OServer();
		server.start();
	}

	public void start() {
		try {
			ServerSocket SRVSOCK = new ServerSocket(6789);
			Socket SOCK = SRVSOCK.accept();
			InputStreamReader ir = new InputStreamReader(SOCK.getInputStream());
			BufferedReader bf = new BufferedReader(ir);

			String MESSAGE = bf.readLine();
			System.out.println(MESSAGE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
