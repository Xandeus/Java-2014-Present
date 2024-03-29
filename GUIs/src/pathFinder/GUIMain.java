package pathFinder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.print.attribute.standard.Destination;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUIMain extends JPanel {
	private static int width = 600, height = 600;
	private static int border = 50;
	private static boolean lClick = false;
	private int state = 0;
	private final int CREATINGMAZE = 0, ORGANISM = 1, DESTINATION = 2;
	private static String currentState = "Place maze";
	ArrayList<Cube> cubes = new ArrayList<Cube>();
	ArrayList<Integer> moves = new ArrayList<Integer>();

	Cube dest = new Cube(-10,-10,true);
	Organism org = new Organism(-10,-10,false);
	HandlerClass handler = new HandlerClass();

	public static void main(String[] args) throws InterruptedException {
		frame();
	}

	static JTextField text = new JTextField();
	static JFrame frame = new JFrame("Pathfinder");

	public static void frame() throws InterruptedException {
		GUIMain game = new GUIMain();
		Font f = new Font("Engravers MT", Font.BOLD, 23);
		text.setEditable(false);
		text.setBackground(Color.GRAY);
		text.setFont(f);
		text.setText(currentState);
		frame.add(text, BorderLayout.SOUTH);
		frame.add(game);
		frame.setResizable(false);
		frame.setSize(1000, 800);
		frame.setLocation(300, 10);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.addMouseListener(game.handler);
		frame.addMouseMotionListener(game.handler);

		while (true) {
			game.repaint();
			Thread.sleep(25);
		}
	}//1 is right 2 is down 3 is left 4 is up
	public ArrayList<Integer> findPath(ArrayList<Integer> a,int x, int y){
		if(x == dest.getX() && y == dest.getY())
			return a;
		else{
			for(Integer i : a){
				
			}
		}
		return a;
			
	}
	@Override
	public void paint(Graphics g) {

		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		int width = 1000;
		int height = 800;
		g.setColor(Color.black);
		for (int x = 0; x <= width; x += 5) {
			g.drawLine(x, 0, x, height);
		}
		for (int y = 0; y <= height; y += 5) {
			g.drawLine(0, y, width, y);
		}
		for (Cube c : cubes) {
			g.fillRect(c.getX(), c.getY(), 5, 5);
		}
		g.setColor(Color.RED);
		g.fillRect(dest.getX(), dest.getY(), 5, 5);
		g.setColor(Color.GREEN);
		g.fillRect(org.getX(), org.getY(), 5, 5);


	}
	
	private class HandlerClass implements MouseListener, MouseMotionListener {
		public void mouseClicked(MouseEvent event) {
			int x1 = event.getX() - 5;
			int y1 = event.getY() - 32;
			x1 = (x1 - (x1 % 5));
			y1 = (y1 - (y1 % 5));
			if (event.getButton() == MouseEvent.BUTTON3) {
				state++;
				if (state > 2)
					state = 0;
				switch (state) {
				case CREATINGMAZE:
					currentState = "Place maze";
					break;
				case ORGANISM:
					currentState = "Place player";
					break;
				case DESTINATION:
					currentState = "Place destination";
					break;
				}
				text.setText(currentState);
			}
			if (event.getButton() == MouseEvent.BUTTON1) {
				switch (state) {
				case ORGANISM:
					org.setX(x1);
					org.setY(y1);
					break;
				case DESTINATION:
					dest.setX(x1);
					dest.setY(y1);
					break;
				}
			}
		}

		public void mousePressed(MouseEvent event) {
			if (event.getButton() == MouseEvent.BUTTON1)
				lClick = true;
		}

		@Override
		public void mouseDragged(MouseEvent event) {
			// TODO Auto-generated method stub
			int x1 = event.getX() - 5;
			int y1 = event.getY() - 32;
			x1 = (x1 - (x1 % 5));
			y1 = (y1 - (y1 % 5));
			if (lClick) {
				if (state == CREATINGMAZE) {
					for (Cube c : cubes)
						if (x1 == c.getX() && y1 == c.getY())
							return;
					cubes.add(new Cube(x1, y1, false));
					repaint();
				}
			}
		}

		@Override
		public void mouseMoved(MouseEvent event) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent event) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent event) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent event) {
			// TODO Auto-generated method stub
			if (event.getButton() == MouseEvent.BUTTON1)
				lClick = false;
		}
	}
}
