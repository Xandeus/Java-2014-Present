package physicsGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
	ArrayList<CannonBall> shots = new ArrayList<CannonBall>();
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
		centerX = (width / 2)-500;
		centerY = (height / 2)+100;
		double angle = (Math.atan2((centerY) - mouseY, (centerX) - mouseX) - Math.PI / 2);
		g2d.rotate(-angle, centerX,centerY);
		g2d.fillRect(centerX, centerY, 5,20); // draw your rectangle
		g2d.setTransform(transform);
		g2d.setColor(Color.DARK_GRAY);
		g2d.fillRect(0, 510, width, 10);
		g2d.setColor(Color.RED);
		for(CannonBall b : shots){
			g2d.fillOval(b.getX(), b.getY(), 5, 5);
		}
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
			game.repaint();
			game.physics();
			Thread.sleep(25);
		}
	}
	public void physics(){
		for(CannonBall b : shots){
			b.move();
			if((b.getY()+b.getVY())>510){
				b.setVX(0);
				b.setVY(0);
			}
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
			if (event.getButton() == MouseEvent.BUTTON1) {
				shots.add(new CannonBall(centerX,centerY,-(centerX-mouseX)*.1f,(centerY-mouseY)*.1f,0,9.8f));
			}
			if (event.getButton() == MouseEvent.BUTTON3) {

			}
			if (event.getButton() == MouseEvent.BUTTON2) {

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
