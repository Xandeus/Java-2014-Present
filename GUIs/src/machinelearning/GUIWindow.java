package machinelearning;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class GUIWindow extends JPanel {
	HandlerClass handler = new HandlerClass();
	Random rand = new Random();
	static Point target = new Point();
	static boolean lClickD = false, rClickD = false, mClickD = false;
	static int clicks = 0;
	static int squareSize = 20;
	static int totalPopulation = 1000;
	static Pathfinder[] population = new Pathfinder[totalPopulation];
	static boolean temp = true;
	static boolean fPath = false;
	static ArrayList<Pathfinder> matingPool = new ArrayList<Pathfinder>();
	static int count = 0;
	float mutationRate = .51f;

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(Color.black);
		int width = 1000;
		int height = 800;
		for (int x = 0; x <= width; x += squareSize) {
			g.drawLine(x, 0, x, height);
		}
		for (int y = 0; y <= height; y += squareSize) {
			g.drawLine(0, y, width, y);
		}
		g.fillRect(100, 500, squareSize, squareSize);
		if (target != null) {
			g.setColor(Color.green);
			g.fillRect(target.x, target.y, squareSize, squareSize);
		}
		if (population[0] != null) {
			for (Pathfinder pf : population) {
				g.setColor(pf.getColor());
				g.fillRect(pf.posX, pf.posY, squareSize, squareSize);
				pf.moveDisplay(pf.movements[count], squareSize);
			}
		}
	}

	public Color randColor() {
		return new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
	}

	static JTextField text = new JTextField();

	static JFrame frame = new JFrame("Machine Learning");

	public static void frame() throws InterruptedException {
		GUIWindow game = new GUIWindow();
		frame.add(game);
		frame.setResizable(false);
		frame.setSize(1000, 800);
		frame.setLocation(300, 10);
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.addMouseListener(game.handler);
		frame.addMouseMotionListener(game.handler);

		while (true) {
			if (population[0] != null) {
				if (count < population[0].movements.length - 1) {
					count++;
				} else {
					count = 0;
					for (Pathfinder p : population) {
						p.resetPos();
					}
					game.evolve();

				}
			}
			game.repaint();
			Thread.sleep(25);
		}
	}

	public static void main(String[] args) throws InterruptedException {
		frame();

	}

	public void evolve() {
		matingPool.clear();
		Pathfinder f = population[0];
		float total = 0;
		for (Pathfinder p : population) {
			// System.out.println(p.fitness);
			p.fitness(target, squareSize);
			total+=p.fitness;
			
		}
		float average = total/population.length;
		System.out.println("Average fitness " + average);
//		// if(f.fitness>.95f){
//		// fPath = true;
//		// }
//		matingPool.add(f);
		System.out.println("Highest fitness " + f.fitness);
		for (int i = 0; i < population.length; i++) {
			if(population[i].fitness >= average)
				matingPool.add(population[i]);
			
		}
		if(matingPool.size() == 0){
			matingPool.add(population[0]);
		}
		for (int i = 0; i < population.length; i++) {
			int a = rand.nextInt(matingPool.size());
			int b = rand.nextInt(matingPool.size());
			Pathfinder partnerA = matingPool.get(a);
			Pathfinder partnerB = matingPool.get(b);
			Pathfinder child = matingPool.get(a);
			child.mutate(mutationRate);
			population[i].movements = child.movements;
		}
	}

	private class HandlerClass implements MouseListener, MouseMotionListener {
		public void mouseClicked(MouseEvent event) {

		}

		public void mousePressed(MouseEvent event) {
			int x = event.getX() - 5;
			int y = event.getY() - 32;
			if (event.getButton() == MouseEvent.BUTTON1) {
				lClickD = true;
				temp = false;
				// Pathfinder p = new Pathfinder((x - (x % squareSize)), (y - (y
				// % squareSize)));
				// System.out.println(p.fitness(target, squareSize));
			}
			if (event.getButton() == MouseEvent.BUTTON3) {
				rClickD = true;
				target.setLocation((x - (x % squareSize)), (y - (y % squareSize)));
				for (int i = 0; i < population.length; i++) {
					population[i] = new Pathfinder();
				}
			}
			if (event.getButton() == MouseEvent.BUTTON2) {
				mClickD = true;

			}

		}

		public void mouseReleased(MouseEvent event) {
			if (event.getButton() == MouseEvent.BUTTON1)
				lClickD = false;
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
			} else if (rClickD) {
			} else if (mClickD) {
			}
		}

		public void mouseMoved(MouseEvent event) {

		}
	}

}