import javax.swing.JFrame;
public class ClientTest {
	public static void main(String args[]){
		Client client;
		
		client = new Client("10.9.33.55");
		client.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		client.startRunning();
	}
}
