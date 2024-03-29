package machinelearning;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EcosystemGUI extends JPanel {
	HandlerClass handler = new HandlerClass();
	Random rand = new Random();
	static Point target = new Point();
	static boolean lClickD = false, rClickD = false, mClickD = false;
	static boolean generate = false;
	static boolean explode = false;
	static boolean rave = false;
	static boolean attraction = true;
	static int clicks = 0;

	static float t;
	PVector vTarget = new PVector(500, 400);
	ArrayList<Organism> organisms = new ArrayList<Organism>();
	ArrayList<Organism> dupe = new ArrayList<Organism>();
	int mouseX = 0, mouseY = 0;

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(Color.black);
		int width = 1000;
		int height = 800;
		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);
		for (Organism or : organisms) {
			g.setColor(or.getColor());
			if (rave) {
				// or.setDiameter(or.getDiameter()+rand.nextInt(21)-10);
				// if(or.getDiameter()==0){
				// or.setDiameter(or.getDiameter()+1);
				// }
				g.setColor(randColor());
			}
			g.fillOval((int) or.getLocation().x, (int) or.getLocation().y, or.getDiameter(), or.getDiameter());
		}
	}

	public Color randColor() {
		return new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
	}

	static JTextField text = new JTextField();

	static JFrame frame = new JFrame("Machine Learning: Ecosystem");

	public static void frame() throws InterruptedException {
		EcosystemGUI game = new EcosystemGUI();
		frame.add(game);
		frame.setResizable(false);
		frame.setSize(1000, 800);
		frame.setLocation(300, 10);
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.addMouseListener(game.handler);
		frame.addMouseMotionListener(game.handler);
		frame.addKeyListener(game.handler);

		while (true) {
			game.repaint();
			if (!generate) {
				game.generate();
			}
			game.update();

			Thread.sleep(10);
		}
	}

	public void generate() {
		for (int i = 0; i < 2000; i++) {
			organisms.add(new Organism((int) (Math.random() * 1000), (int) (Math.random() * 800),
					(int) (Math.random() * 2) + 1));
		}
		generate = true;
	}

	public void update() {
		PVector mouse = new PVector(mouseX, mouseY);
		
		for (Organism organism : organisms) {
			PVector dir1 = PVector.sub(mouse, organism.location);
			PVector dir2 = PVector.sub(mouse, organism.location);
			// dir1.add(dir2);
			if (attraction && dir1.mag() < 250) {
				dir1.normalize();
				dir1.mult(.5f);
				t = organism.topspeed;
				organism.acceleration = dir1;
				organism.velocity.add(organism.acceleration);
				organism.velocity.limit(organism.topspeed);
			}
			else{
				organism.velocity.add(new PVector(organism.velocity.x*-.005f,organism.velocity.y*-.005f));
			}
			organism.location.add(organism.velocity);
			organism.setTopSpeed(t);
			if (explode) {
				organism.acceleration.mult(-4f);
				organism.velocity.add(organism.acceleration);
			}
			checkCollision(organism);
		}
		// dupe.clear();
		// for (Organism o : organisms) {
		// dupe.add(new
		// Organism((int)o.location.x,(int)o.location.y,o.diameter));
		// }
		// for (Organism organism : organisms) {
		// PVector dir1 = new PVector(0,0);
		// for (Organism o : dupe) {
		// if (organism != o) {
		// PVector dir = PVector.sub(o.location, organism.location);
		// PVector dir2 = new PVector(0,0);
		// //if(dir.mag()<500){
		// dir.mult(o.diameter);
		// dir1.add(dir);
		// //}
		// }
		// }
		// t = organism.topspeed;
		// dir1.normalize();
		// dir1.mult(.5f);
		// organism.acceleration = dir1;
		// organism.velocity.add(organism.acceleration);
		// organism.velocity.limit(organism.topspeed);
		// organism.location.add(organism.velocity);
		// organism.setTopSpeed(t);
		// //checkCollision(organism);
		// }

		// explode = false;

	}

	public void checkCollision(Organism org) {
		if (org.location.x < 0 || org.location.x > 1000 - org.getDiameter()) {
			org.velocity.multX(-1f);
		}
		if (org.location.y < 0 || org.location.y > 800 - org.getDiameter()) {
			org.velocity.multY(-1f);
		}

	}

	public static void main(String[] args) throws InterruptedException {
		frame();

	}

	private class HandlerClass implements MouseListener, MouseMotionListener, KeyListener {
		public void mouseClicked(MouseEvent event) {

		}

		public void mousePressed(MouseEvent event) {
			int x = event.getX() - 5;
			int y = event.getY() - 32;
			if (event.getButton() == MouseEvent.BUTTON1) {
				// explode = !explode;\
				lClickD = true;
				organisms.add(new Organism(x, y, 40));
			}
			if (event.getButton() == MouseEvent.BUTTON3) {

			}
			if (event.getButton() == MouseEvent.BUTTON2) {
				mClickD = true;
				organisms.add(new Organism(x, y, 15));

			}

		}

		public void mouseReleased(MouseEvent event) {
			if (event.getButton() == MouseEvent.BUTTON1) {
				lClickD = false;
			}
			if (event.getButton() == MouseEvent.BUTTON3)
				rClickD = false;
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
				organisms.add(new Organism(x, y, (int) (Math.random() * 100) + 1));

			} else if (rClickD) {
			} else if (mClickD) {
			}
		}

		public void mouseMoved(MouseEvent event) {
			mouseX = event.getX() - 5;
			mouseY = event.getY() - 32;
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			int keyCode = e.getKeyCode();
			if (keyCode == e.VK_SPACE) {
				rave = !rave;
			}
			if (keyCode == e.VK_C) {
				attraction = !attraction;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}
	}
}