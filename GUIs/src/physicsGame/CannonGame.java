package physicsGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;



public class CannonGame extends JPanel{
	HandlerClass handler = new HandlerClass();
	static boolean lClickD = false, rClickD = false, mClickD = false;
	static int clicks = 0;
	PhysicsObject rocket = new PhysicsObject(100,100);
	Rectangle rect = new Rectangle();
	int mouseX,mouseY ;
	int centerX;
	int centerY;
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		AffineTransform transform = g2d.getTransform();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(Color.black);
		int width = 1400;
		int height = 800;
		g2d.fillRect(rocket.getX(), rocket.getY(), 10, 10);
		
		
	}

	public Color randColor() {
		return new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
	}


	static JFrame frame = new JFrame("Cannon");

	public static void frame() throws InterruptedException {
		CannonGame game = new CannonGame();
		Font f = new Font("Engravers MT", Font.BOLD, 23);
		frame.add(game);
		frame.setResizable(false);
		frame.setSize(1400, 800);
		frame.setLocation(300, 10);
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.addMouseListener(game.handler);
		frame.addMouseMotionListener(game.handler);

		while (true) {
			game.physics();
			game.repaint();
			Thread.sleep(20);
		}
	}
	public void physics(){
		rocket.move();
		if((rocket.getY()+rocket.getVY()) > 500){
			rocket.setVY(0);
		}
		else{
			rocket.setAY(9.8f);	
		}
	}
	public static void main(String[] args) throws InterruptedException {
		frame();

	}

	private class HandlerClass implements MouseListener, MouseMotionListener {
		public void mouseClicked(MouseEvent event) {

		}

		public void mousePressed(MouseEvent event) {
			int x = event.getX() - 5;
			int y = event.getY() - 32;
			
			if (event.getButton() == MouseEvent.BUTTON3) {

			}
			if (event.getButton() == MouseEvent.BUTTON1) {
				rocket.setVY(-50);
			}

		}

		public void mouseReleased(MouseEvent event) {
			if (event.getButton() == MouseEvent.BUTTON1) {
			}
			if (event.getButton() == MouseEvent.BUTTON3) {

			}
		}

		public void mouseEntered(MouseEvent event) {

		}

		public void mouseExited(MouseEvent event) {

		}

		// These are mouse motion events
		public void mouseDragged(MouseEvent event) {
			int x = event.getX() - 5;
			int y = event.getY() - 32;
			if (lClickD) {

			} else if (rClickD) {
			} else if (mClickD) {
			}
		}

		public void mouseMoved(MouseEvent event) {
			mouseX = event.getX();
			mouseY = Math.abs(event.getY()-1032);

		}
	}
}
