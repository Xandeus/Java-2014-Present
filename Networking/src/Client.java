import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
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
	private boolean pConnected = false;
	private Player pClient = new Player(780, 0);
	private Player pServer;
	private GameBall gameBall;

	// Creates GUI assigns IP from host
	public Client(String host) {
		super("DONG - CLIENT");
		serverIP = host;
		mainFrame = new DrawPane();
		chatWindow = new JTextArea();
		addKeyListener(new AL());
		addMouseListener(new MouseAL());
		setPreferredSize(new Dimension(800,600));
		setAlwaysOnTop(true);
		setLocation(700, 150);
		pack();
		setVisible(true);
		setResizable(false);
		setBackground(Color.black);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(mainFrame);

	}

	// Listens for mouse actions
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

	// Listens for keyboard actions
	public class AL extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent event) {

			int keyCode = event.getKeyCode();
			if (keyCode == event.VK_UP) {
				pClient.setYVel(-1);
				//pClient.setXVel(0);
			}
			if (keyCode == event.VK_DOWN) {
				pClient.setYVel(1);
				//pClient.setXVel(0);
			}
			repaint();
		}

		@Override
		public void keyReleased(KeyEvent event) {
			pClient.setXVel(0);
			pClient.setYVel(0);
		}
	}

	// create a component that you can actually draw on.
	class DrawPane extends JPanel {
		public void paintComponent(Graphics g) {
			// draw on g here e.g.
			g.setColor(Color.BLUE);
			g.fillRect(pClient.getX(), pClient.getY(), pClient.getWidth(), pClient.getHeight());
			if (pServer != null) {
				g.setColor(Color.RED);
				g.fillRect(pServer.getX(), pServer.getY(), pServer.getWidth(), pServer.getHeight());
			}
			if (pConnected) {
		
				sendMessage(pClient);
			}
			gameLoop();
		}
	}
	public void gameLoop(){
		pClient.move();
		repaint();
		if(pConnected){
			sendMessage(pClient);
		}
	}

	// Start the client and attempt to connect to server
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
		pConnected = true;
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
		pConnected = true;
		Object temp;
		do {
			try {
				temp = input.readObject();
				if (temp instanceof Player)
					pServer = (Player) temp;
				if (temp instanceof GameBall)
					gameBall = (GameBall) temp;

			} catch (ClassNotFoundException classNotFoundException) {
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

	// Send strings to server
	private void sendMessage(String message) {
		try {
			output.writeObject("CLIENT - " + message);
			output.flush();
			showMessage("\nCLIENT - " + message);
		} catch (IOException ioException) {
			chatWindow.append("\n Error while sending message");
		}
	}

	// Send ints to the server
	private void sendMessage(int num) {
		try {
			output.writeInt(num);
			output.flush();
			showMessage("\nCLIENT - " + message);
		} catch (IOException ioException) {
			chatWindow.append("\n Error while sending message");
		}
	}

	// Send player objects to server
	private void sendMessage(Player player) {
		try {
			output.reset();
			output.writeObject(player);
			output.flush();
			showMessage("\nCLIENT - " + message);
		} catch (IOException ioException) {
			chatWindow.append("\n Error while sending message");
		}
	}

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
