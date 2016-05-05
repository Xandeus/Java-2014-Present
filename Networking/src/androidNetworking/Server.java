package androidNetworking;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import org.omg.PortableServer.ServantRetentionPolicyOperations;

public class Server extends JFrame {
	private JTextField userText;
	private JTextArea chatWindow;
	private DrawPane mainFrame;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private ServerSocket server;
	private Socket connection;
	private boolean pConnected = false;

	public Server() {
		super("Android To Computer - SERVER");		
		mainFrame = new DrawPane();
		chatWindow = new JTextArea();
		add(chatWindow, BorderLayout.SOUTH);
		chatWindow.setPreferredSize(new Dimension(400, 50));
		addKeyListener(new AL());
		addMouseListener(new MouseAL());
		setSize(800, 600);
		setAlwaysOnTop(true);
		setLocation(100, 150);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(mainFrame);
	//	createNPCS(10);
	}


	public class MouseAL implements MouseListener {
		public void mousePressed(MouseEvent e) {
		}

		public void mouseReleased(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}

		public void mouseClicked(MouseEvent e) {

			System.out.println("Clicked at X: " + e.getX() + " Y: " + e.getY());
		}

	}

	public class AL extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent event) {
			int keyCode = event.getKeyCode();
			if (keyCode == event.VK_LEFT) {
				
			}
			if (keyCode == event.VK_RIGHT) {
		
			}
			if (keyCode == event.VK_UP) {
			
			}
			if (keyCode == event.VK_DOWN) {
				
			}
		}

		@Override
		public void keyReleased(KeyEvent event) {
			int keyCode = event.getKeyCode();
		
		}

	}

	// create a component that you can actually draw on.
	class DrawPane extends JPanel {
		public void paintComponent(Graphics g) {
			// draw on g here e.g.
			

			gameLoop();
		}
	}

	public void gameLoop() {
		repaint();
		if (pConnected) {
		
		}
	}

	// Set up server
	public void startRunning() {
		try {
			server = new ServerSocket(6789, 100);
			while (true) {
				try {
					waitForConnection();
					setupStreams();
					whileChatting();
				} catch (EOFException eofException) {
					System.out.println("\n Server ended connection");
				} finally {
					closeStreams();
				}
			}
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	// Wait for connection, then display info
	private void waitForConnection() throws IOException {
		System.out.println("Waiting for connection... \n");
		connection = server.accept();
		System.out.println(" Now connected to " + connection.getInetAddress().getHostName());

	}

	// Get stream to send and receive data
	private void setupStreams() throws IOException {
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		input = new ObjectInputStream(connection.getInputStream());
		System.out.println("\nStreams setup");
	}

	// During the chat conversation
	private void whileChatting() throws IOException {
		String message = "You are connected";
		Object temp;
		pConnected = true;
		System.out.println("You are connected");
		do {
			try {
				temp = input.readObject();
				System.out.println((String)temp);
			} catch (ClassNotFoundException classNotFoundException) {
				System.out.println("\n error");
			}
		} while (!message.equals("CLIENT - END"));
	}

	private void closeStreams() {
		System.out.println("\n Closing connections");
		ableToType(false);
		try {
			output.close();
			input.close();
			connection.close();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	// send message to client
	private void sendMessage(String message) {
		try {
			output.writeObject("SERVER - " + message);
			output.flush();
			System.out.println("\nSERVER - " + message);
		} catch (IOException ioException) {
			chatWindow.append("\n Cannot send message");
		}
	}

	private void sendMessage(int num) {
		try {
			output.writeInt(num);
			output.flush();
		} catch (IOException ioException) {
			// chatWindow.append("\n Error while sending message");
		}
	}


	private void sendMessage(ArrayList array) {
		try {
			output.reset();
			output.writeObject(array);
			output.flush();
		} catch (IOException ioException) {
			// chatWindow.append("\n Error while sending message");
		}
	}

	// Update chat window
	private void ShowMessage(final String text) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// chatWindow.append(text);
			}
		});
	}

	// Change user ability to type
	private void ableToType(final boolean tof) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// userText.setEditable(tof);
			}
		});
	}
}

