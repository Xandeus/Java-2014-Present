import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class Server extends JFrame{
	private JTextField userText;
	private JTextArea chatWindow;
	private DrawPane mainFrame;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private ServerSocket server;
	private Socket connection;
	public  Player pServer = new Player(0,0),pClient = new Player(0,0);
	private int serverX =0,serverY=0;
	private int clientX =0,clientY=0;
	public Server(){
		super("Instant Messenger");
		userText = new JTextField();
		userText.setEditable(false);
		userText.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent event){
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
		setSize(400,400);
		setVisible(true);
		
	}
	public class AL extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent event) {
            int keyCode = event.getKeyCode();
            if (keyCode == event.VK_LEFT)
            {
            	pServer.moveX(-pServer.getMovementspeed());
            	sendMessage(0);
            	
            }
            if (keyCode == event.VK_RIGHT)
            {
            	pServer.moveX(pServer.getMovementspeed());
            	sendMessage(1);
            }
            if (keyCode == event.VK_UP)
            {
            	pServer.moveY(-pServer.getMovementspeed());
            	sendMessage(2);
            }
            if (keyCode == event.VK_DOWN)
            {
            	pServer.moveY(pServer.getMovementspeed());
            	sendMessage(3);
            }
            
			System.out.println("Client " + pClient.getX());
            repaint();
            
        }

        @Override
        public void keyReleased(KeyEvent event) {
        }
}
	  //create a component that you can actually draw on.
    class DrawPane extends JPanel{
      public void paintComponent(Graphics g){
        //draw on g here e.g.
    	 g.setColor(Color.RED);
         g.fillRect((int)pServer.getX(), (int)pServer.getY(), 10, 10);
         g.setColor(Color.BLUE);
         g.fillRect((int)pClient.getX(), (int)pClient.getY(), 10, 10);
       }
   }
	//Set up server
	public void startRunning(){
		try{
			server = new ServerSocket(6789,100);
			while(true){
				try{
					waitForConnection();
					setupStreams();
					whileChatting();
				}catch(EOFException eofException){
					showMessage("\n Server ended connection");
				}finally{
					closeStreams();
				}
			}
		}catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
	//Wait for connection, then display info
	private void waitForConnection() throws IOException{
		showMessage("Waiting for connection... \n");
		connection = server.accept();
		showMessage(" Now connected to " + connection.getInetAddress().getHostName());
		
	}
	//Get stream to send and receive data
	private void setupStreams() throws IOException{
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		input = new ObjectInputStream(connection.getInputStream());
		showMessage("\nStreams setup");
	}
	//During the chat conversation
	private void whileChatting() throws IOException{
		String message = "You are connected";
		int num;

		ableToType(true);
		do{
			try{
				num = input.readInt();
				showMessage("\n" + message);
				if(num == 0)
					pClient.moveX(-pClient.getMovementspeed());
				if(num == 1)
					pClient.moveX(pClient.getMovementspeed());
				if(num == 2)
					pClient.moveY(-pClient.getMovementspeed());
				if(num == 3)
					pClient.moveY(pClient.getMovementspeed());
				showMessage("\n" + message);
				repaint();
			}catch(IOException ioException){
				showMessage("\n error");
			}
		}while(!message.equals("CLIENT - END"));
	}
	private void closeStreams(){
		showMessage("\n Closing connections");
		ableToType(false);
		try{
			output.close();
			input.close();
			connection.close();
		}catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
	//send message to client
//	private void sendMessage(String message){
//		try {
//			output.writeObject("SERVER - " + message);
//			
//			output.flush();
//			showMessage("\nSERVER - " + message);
//		}catch(IOException ioException){
//			chatWindow.append("\n Cannot send message");
//		}
//	}
	private void sendMessage(int num){
		try {
			output.writeInt(num);
			output.flush();
		} catch (IOException ioException) {
			chatWindow.append("\n Error while sending message");
		}
	}
	private void sendMessage(Player player){
		try {
			output.writeObject(player);
			output.flush();
		} catch (IOException ioException) {
			chatWindow.append("\n Error while sending message");
		}
	}
	//Update chat window
	private void showMessage(final String text){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				System.out.println(text);
				//chatWindow.append(text);
			}
		});
	}
	
	//Change user ability to type
	private void ableToType(final boolean tof) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
			//	userText.setEditable(tof);
			}
		});
	}
}
