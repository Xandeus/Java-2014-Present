package Fractals;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Fractals extends JPanel {
	HandlerClass handler = new HandlerClass();
	static JFrame frame = new JFrame("Fractals");
	int count = 0;
	boolean zoom = false;
	boolean rave = false;
	ArrayList<Shape> circles = new ArrayList<Shape>();
	public static void frame() throws InterruptedException {
		Fractals game = new Fractals();
		Font f = new Font("Engravers MT", Font.BOLD, 23);
		frame.add(game);
		frame.setResizable(false);
		frame.setSize(1000, 800);
		frame.setLocation(300, 10);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setFocusable(true);
		frame.addMouseListener(game.handler);
		frame.addMouseMotionListener(game.handler);
		frame.addKeyListener(game.handler);

		while (true) {
			game.repaint();
			Thread.sleep(1);
		}
	}
	public Color randColor(){
		return new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));
	}
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(Color.black);
		g.fillRect(0, 0, 1000, 800);
		if(count < circles.size() && circles.size() >0){
			circles.get(count).changeState();;
			count++;
		}
		for(Shape c : circles){
			if(c.isActive){
				if(!rave)
					g.setColor(c.getColor());
				else
					g.setColor(randColor());
//				g.drawLine(c.getX(), c.getY(), c.getX(), c.getY()-c.getR());
//				g.drawLine(c.getX(), c.getY()-c.getR(), c.getX()-c.getR(), c.getY()-2*c.getR());
//				g.drawLine(c.getX(), c.getY()-c.getR(), c.getX()-c.getR(), c.getY()-2*c.getR());
				//Draw triangles
//				g.drawLine(c.getX(), c.getY(), c.getX()+c.getR(), c.getY());
//				g.drawLine(c.getX(), c.getY(), c.getX()+c.getR()/2, c.getY()-c.getR());
//				g.drawLine(c.getX()+c.getR()/2, c.getY()-c.getR(), c.getX()+c.getR(), c.getY());
				g.drawOval(c.getX(), c.getY(), c.getR(), c.getR());
				if(zoom){
					c.change(-1,-1,2);
				}
			}
		}
		
		
		
	}

	public void drawShape(int x, int y, int r) {
		circles.add(new Shape(x,y,r));
		if (r>10) {
			

			//Some asshole's triangle
//			drawShape(x,y,r/2);
//			drawShape(x+r/2,y,r/2);
//			drawShape(x+r/4,y-r/2,r/2);

			//Squarse
			drawShape(x-r/2,y+r/4,r/2);
			drawShape(x+r/4,y-r/2,r/2);
			
			drawShape(x+r,y+r/4,r/2);
			drawShape(x+r/4,y+r,r/2);
		}

	}

	public static void main(String[] args) throws InterruptedException {
		frame();

	}

	private class HandlerClass implements MouseListener, MouseMotionListener, KeyListener{
		public void mouseClicked(MouseEvent event) {

		}

		public void mousePressed(MouseEvent event) {
			int x = event.getX() - 5;
			int y = event.getY() - 32;
			if (event.getButton() == MouseEvent.BUTTON1) {
				drawShape(x,y,400);
			}
			if (event.getButton() == MouseEvent.BUTTON2) {

			}

		}

		public void mouseReleased(MouseEvent event) {

		}

		public void mouseEntered(MouseEvent event) {

		}

		public void mouseExited(MouseEvent event) {

		}

		// These are mouse motion events
		public void mouseDragged(MouseEvent event) {
			int x = event.getX() - 5;
			int y = event.getY() - 32;

		}

		public void mouseMoved(MouseEvent event) {

		}

		@Override
		public void keyPressed(KeyEvent event) {
			// TODO Auto-generated method stub
			int keyCode = event.getKeyCode();
			if(keyCode == event.VK_C){
				count = 0;
				circles.clear();
			}
			if(keyCode == event.VK_R){
				rave = !rave;
			}
			if(keyCode == event.VK_Z){
				zoom = !zoom;
			}
		}

		@Override
		public void keyReleased(KeyEvent event) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent event) {
			// TODO Auto-generated method stub
			
		}
	}
}
