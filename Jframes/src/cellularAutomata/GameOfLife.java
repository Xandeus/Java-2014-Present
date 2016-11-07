package cellularAutomata;

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
import javax.swing.JTextField;

public class GameOfLife extends JPanel {
	HandlerClass handler = new HandlerClass();
	Cell[][] cells;
	ArrayList<Cell> aliveCells = new ArrayList<Cell>();
	ArrayList<Cell> deadCells = new ArrayList<Cell>();
	ArrayList<Cell> brightenCells = new ArrayList<Cell>();
	static boolean lClickD = false, rClickD = false, mClickD = false;
	static boolean gameStarted = false;
	static boolean pauseGame = false;
	static boolean done = false;
	static int clicks = 0;
	static int updateCount = 100000;
	int blockSize = 2;
	int bx;
	int by;
	static int width = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
	static int height = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, width, height);
		if (gameStarted) {
			for (Cell[] x : cells) {
				for (Cell c : x) {
					if (c.isAlive()) {
						g.setColor(c.getColor());
						g.fillRect(c.getX(), c.getY(), blockSize, blockSize);
					}
				}
			}
		}
	}

	public Color randColor() {
		return new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
	}

	static JTextField text = new JTextField();

	static JFrame frame = new JFrame("Conway's Game Of Life");

	public static void frame() throws InterruptedException {
		GameOfLife game = new GameOfLife();
		Font f = new Font("Engravers MT", Font.BOLD, 23);
		text.setEditable(false);
		text.setBackground(Color.GRAY);
		text.setFont(f);
		frame.add(game);
		frame.setResizable(false);
		frame.setUndecorated(true);
		frame.setSize(width, height);
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.addMouseListener(game.handler);
		frame.addMouseMotionListener(game.handler);
		frame.addKeyListener(game.handler);
		while (true) {
			game.repaint();
			if (gameStarted && !pauseGame) {
				game.checkCells();
				game.updateCells();
				if(!done && updateCount > 10000){
					game.randAlive();
					updateCount = 0;
					done=true;
				}
				updateCount++;
			}
			Thread.sleep(35);
		}
	}
	public void randAlive(){
		for (int x = cells.length/6;x<cells.length-(cells.length/6);x++) {
			for (int y = cells[0].length/6;y<cells[0].length-(cells[0].length/6);y++) {
				if((int)(Math.random()*2)<1){
					cells[x][y].setAlive(true);
				}
			}
		}
	}
	public void checkCells() {
		for (Cell[] x : cells) {
			for (Cell c : x) {
				int n = getAliveNeighbours(c.getPosX(), c.getPosY());
				if (c.isAlive() && (n < 2 || n > 3)) {
					deadCells.add(c);
				}
				else if (n == 3 && !c.isAlive())
					aliveCells.add(c);

			}
		}
	}

	public void updateCells() {
		for (Cell c : aliveCells) {
			c.setAlive(true);
		}
		for (Cell c : deadCells) {
			c.setAlive(false);
		}
		
		aliveCells.clear();
		deadCells.clear();
	}

	public int getAliveNeighbours(int px, int py) {
		int count = 0;
		for (int x = px - 1; x <= px + 1; x++) {
			for (int y = py - 1; y <= py + 1; y++) {
				if (x > 0 && x < cells.length && y > 0 && y < cells[0].length) {
					if (cells[x][y].isAlive() && !(px == x && py == y)) {
						count++;
					}
				}

			}
		}
		return count;
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
				bx = x - (x % blockSize);
				by = y - (y % blockSize);

				cells[bx / blockSize][by / blockSize].setAlive(true);

				// getAliveNeighbours(bx/5, by/5);
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
			if (!gameStarted) {
				cells = new Cell[width / blockSize][height / blockSize];
				for (int x = 0; x < width / blockSize; x++) {
					for (int y = 0; y < height / blockSize; y++) {
						cells[x][y] = new Cell(x * blockSize, y * blockSize, x, y);
					}
				}
			}
			gameStarted = true;

		}

		public void mouseExited(MouseEvent event) {

		}

		// These are mouse motion events
		public void mouseDragged(MouseEvent event) {
			int x = event.getX() - 5;
			int y = event.getY() - 32;
			bx = x - (x % blockSize);
			by = y - (y % blockSize);
			cells[bx / blockSize][by / blockSize].setAlive(true);

		}

		public void mouseMoved(MouseEvent event) {

		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			int keyCode = e.getKeyCode();

			if (keyCode == e.VK_SPACE) {
				pauseGame = !pauseGame;
			}
			if (keyCode == e.VK_C) {
				for (Cell[] x : cells) {
					for (Cell c : x) {
						c.setAlive(false);
					}
				}
			}

		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}
	}

}
