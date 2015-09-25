import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class Client extends JFrame {

	private JTextField userText;
	private JTextArea chatWindow;
	private DrawPane mainFrame;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private String message = "";
	private String serverIP;
	private Socket connection;
	private boolean connected = false;
	public Player client = new Player(0,0);
	public Player server = new Player(0,0);
	int clientX = 0, clientY = 0;
	int serverX = 0, serverY = 0;

	// constructor
	public Client(String host) {
		super("Client");
		System.out.println("CLIENT");
		serverIP = host;
		userText = new JTextField();
		userText.setEditable(false);
		userText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				//sendMessage(event.getActionCommand());
				userText.setText("");
			}
		});
		add(userText, BorderLayout.NORTH);
		chatWindow = new JTextArea();
		setContentPane(new DrawPane());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame = new DrawPane();
		addKeyListener(new AL());
		setSize(400, 400);
		setVisible(true);

	}

	public class AL extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent event) {
			int keyCode = event.getKeyCode();
			if (keyCode == event.VK_LEFT)
            {
            	client.moveX(-client.getMovementspeed());
            	sendMessage(0);
            	
            }
            if (keyCode == event.VK_RIGHT)
            {
            	client.moveX(client.getMovementspeed());
            	sendMessage(1);
            }
            if (keyCode == event.VK_UP)
            {
            	client.moveY(-client.getMovementspeed());
            	sendMessage(2);
            }
            if (keyCode == event.VK_DOWN)
            {
            	client.moveY(client.getMovementspeed());
            	sendMessage(3);
            }
			repaint();
		}

		@Override
		public void keyReleased(KeyEvent event) {
		}
	}

	// create a component that you can actually draw on.
	class DrawPane extends JPanel {
		public void paintComponent(Graphics g) {
			// draw on g here e.g.
			g.setColor(Color.RED);
			g.fillRect((int)server.getX(), (int)server.getY(), 10, 10);

			g.setColor(Color.BLUE);
			g.fillRect((int)client.getX(), (int)client.getY(), 10, 10);
		}
	}

	// Connect to server
	public void startRunning() {
		try {
			connectToServer();
			setupStreams();
			whileChatting();
		} catch (EOFException eofException) {
			showMessage("\nClient terminated connection");
		} catch (IOException ioException) {
			ioException.printStackTrace();
		} finally {
			closeStreams();
		}
	}

	// Connect to server
	private void connectToServer() throws IOException {
		showMessage("Attempting connection... \n");
		connection = new Socket(InetAddress.getByName(serverIP), 6789);
		connected = true;
		showMessage("Connected to: " + connection.getInetAddress().getHostName());
	}

	// Setup streams
	private void setupStreams() throws IOException {
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		input = new ObjectInputStream(connection.getInputStream());
		showMessage("\n Streams setup");
	}

	// While chatting with server
	private void whileChatting() throws IOException {
		// client = new Player(0,0);
		int num;
		ableToType(true);
		do {
			try {
				num = input.readInt();
				showMessage("\n" + message);
				if(num == 0)
					server.moveX(-server.getMovementspeed());
				if(num == 1)
					server.moveX(server.getMovementspeed());
				if(num == 2)
					server.moveY(-server.getMovementspeed());
				if(num == 3)
					server.moveY(server.getMovementspeed());
				repaint();
			} catch (IOException ioException) {
				showMessage("\n Object not found");
			}

		} while (!message.equals("Server - END"));
	}

	// Close client
	private void closeStreams() {
		showMessage("\n Closing streams");
		ableToType(false);
		try {
			output.close();
			input.close();
			connection.close();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	// Send messages to server
//	private void sendMessage(String message) {
//		try {
//			output.writeObject("CLIENT - " + message);
//			output.flush();
//			showMessage("\nCLIENT - " + message);
//		} catch (IOException ioException) {
//			chatWindow.append("\n Error while sending message");
//		}
//	}

	private void sendMessage(int num) {
		try {
			output.writeInt(num);
			output.flush();
			showMessage("\nCLIENT - " + message);
		} catch (IOException ioException) {
			chatWindow.append("\n Error while sending message");
		}
	}
//	private void sendMessage(Player player) {
//		try {
//			output.writeObject(player);
//			output.flush();
//			showMessage("\nCLIENT - " + message);
//		} catch (IOException ioException) {
//			chatWindow.append("\n Error while sending message");
//		}
//	}

	// Show message
	private void showMessage(final String message) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// chatWindow.append(message);
			}
		});
	}

	// Change users ability to type
	private void ableToType(final boolean tof) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// userText.setEditable(tof);
			}
		});
	}
}
