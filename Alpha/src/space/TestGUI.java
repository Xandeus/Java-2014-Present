package space;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TestGUI extends JPanel {
	static Image infoTab;
	Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);
	Cursor defCursor = Cursor.getDefaultCursor();
	Cursor currentCursor;

	HandlerClass handler = new HandlerClass();
	ArrayList<CelestialBody> bodies = new ArrayList<CelestialBody>();
	ArrayList<ArrayList<CelestialBody>> systems = new ArrayList<ArrayList<CelestialBody>>();
	ArrayList<Point> systemLocations = new ArrayList<Point>();
	ArrayList<Building> buildings = new ArrayList<Building>();
	Random rand = new Random();
	public final int INDIVIDUALV = 0, SYSTEMV = 1, SECTORV = 2;
	public int view = SECTORV;
	int mX, mY;
	int highlightX, highlightY;
	int highlightR;
	int numSystems = 5;
	int systemPoint;
	int sysPointSize = 5;
	static int wWidth = 1200, wHeight = 800;
	boolean temp = false;
	boolean systemH = false;
	boolean infoTabActive = false;
	boolean isBuilding = false;
	static boolean leftMouseDown = false, rightMouseDown = false;
	Point infoTabLoc = new Point();
	CelestialBody body;
	CelestialBody selectedBody;

	public static void main(String[] args) throws InterruptedException {
		frame();
	}

	static JFrame frame = new JFrame("SPAAACE");

	public static void frame() throws InterruptedException {
		try {
			infoTab = ImageIO.read(new File("res/glassPanel_cornerTL.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TestGUI game = new TestGUI();
		Font logFont = new Font("Engravers MT", Font.PLAIN, 11);
		frame.add(game);
		frame.pack();
		frame.setResizable(false);
		frame.setSize(wWidth, wHeight);
		frame.setLocation(300, 10);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.addMouseListener(game.handler);
		frame.addMouseMotionListener(game.handler);
		frame.addKeyListener(game.handler);
	}

	@Override
	public void paint(Graphics g) {

		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setFont(new Font("Display", Font.BOLD, 10));
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, frame.getWidth(), frame.getHeight());
		switch (view) {
		case INDIVIDUALV:
			g2d.setColor(body.getColor());
			g2d.fillOval(50, 25, wHeight - 50, wHeight - 50);
			g2d.drawImage(infoTab, wHeight + 10, 30, 300, 500, null);
			g.setColor(Color.white);
			g2d.drawString("Avaliable Constructions", wHeight + 100, 80);
			if (body.getType().equalsIgnoreCase("Terrestrial planet"))
				g2d.drawString("Mining Facility", wHeight + 50, 90);
			else
				g2d.drawString("You cannot construct anything here!", wHeight + 50, 90);
			for (Building b : buildings) {
				if(body == b.getLocation()){
					g2d.setColor(Color.black);
					g2d.drawRect(b.getX(), b.getY(), 20, 20);
					System.out.println(b.getX());
				}
			}
			break;
		case SYSTEMV:
			// Draw background stars
			// for(int i = 0;i<1000;i++){
			// g2d.setColor(new Color(0,0,(int)(Math.random()*256)));
			// g2d.drawRect((int)(Math.random()*1200), (int)(Math.random()*800),
			// 2, 2);
			// }
			// Draw planets and stars from systems
			for (CelestialBody b : bodies) {
				int c = b.getRadius() * 2;
				g2d.setColor(b.getColor());
				b.setWindowLocX(bodies.indexOf(b) * 200);
				b.setWindowLocY(500);
				g2d.fillOval(b.getWindowLocX(), b.getWindowLocY(), c, c);
				if (b.hasAtmosphere()) {
					g2d.setColor(new Color(15, 100, 255, 50));
					g2d.fillOval(b.getWindowLocX() - 5, b.getWindowLocY() - 5, c + 10, c + 10);
				}
				for (Building bu : buildings) {
					if (bu.getLocation() == b) {
						g2d.setColor(Color.black);
						g2d.fillRect(b.getWindowLocX() + (b.getRadius()), b.getWindowLocY() + (b.getRadius()), 5, 5);
					}
				}
			}

			break;
		case SECTORV:
			for (Point p : systemLocations) {
				g2d.setColor(Color.white);
				g2d.fillRect(p.x, p.y, sysPointSize, sysPointSize);
			}
			break;
		}
		g2d.setColor(Color.green);
		switch (view) {
		case SYSTEMV:
			g2d.drawOval(highlightX, highlightY, highlightR, highlightR);
			break;
		case SECTORV:
			g2d.drawRect(highlightX, highlightY, highlightR, highlightR);
			break;
		}
		if (infoTabActive) {
			g2d.setColor(Color.green);
			g2d.drawImage(infoTab, infoTabLoc.x, infoTabLoc.y, 100, 150, null);
			g2d.setColor(Color.black);
			g2d.drawString(body.getType(), infoTabLoc.x + 5, infoTabLoc.y + 30);
			g2d.drawString("Mass: " + body.getMass(), infoTabLoc.x + 5, infoTabLoc.y + 40);
			g2d.drawString("Volume: " + body.getVolume(), infoTabLoc.x + 5, infoTabLoc.y + 50);
			g2d.drawString("Density: " + body.getDensity(), infoTabLoc.x + 5, infoTabLoc.y + 60);
			g2d.drawString("Resources: " + body.getResources(), infoTabLoc.x + 5, infoTabLoc.y + +70);
			g2d.drawString(body.getResourceTotal() + "", infoTabLoc.x + 5, infoTabLoc.y + 80);
		}
		if (isBuilding) {
			g2d.setColor(Color.black);
			g2d.drawRect(mX, mY, 20, 20);
		}
		g2d.setColor(Color.gray);
		g2d.fillRect(0, 0, wWidth, 25);
	}

	public ArrayList<CelestialBody> generateSolarSystem() {
		ArrayList<CelestialBody> b = new ArrayList<CelestialBody>();
		b.add(new Star());
		for (int i = 0; i < 5; i++) {
			b.add(new Planet());
		}
		systems.add(b);
		return b;
	}

	public void generateSystems() {
		for (int i = 0; i < numSystems; i++) {
			systemLocations
					.add(new Point((rand.nextInt((frame.getWidth()) - 11)), rand.nextInt((frame.getHeight()) - 11)));
			generateSolarSystem();
		}
	}

	// Type differentiates between highlight collision and mouse click collision
	public boolean checkBodyCollision(int x, int y, int type) {
		for (CelestialBody b : bodies) {
			if (mX > b.getWindowLocX() && mX < b.getWindowLocX() + b.getRadius() * 2 && mY > b.getWindowLocY()
					&& mY < b.getWindowLocY() + b.getRadius() * 2) {
				highlightX = b.getWindowLocX();
				highlightY = b.getWindowLocY();
				highlightR = b.getRadius() * 2;
				if (type == 1)
					body = b;
				currentCursor = new Cursor(Cursor.HAND_CURSOR);
				repaint();
				return true;
			}
		}
		repaint();
		highlightX = 0;
		highlightY = 0;
		highlightR = 0;
		return false;
	}

	public void constructBuilding(CelestialBody b,int x,int y, int type) {
		buildings.add(new MiningFacility(b, x, y));
	}

	public boolean checkSystemCollision(int x, int y) {
		for (Point p : systemLocations) {
			if (mX > p.x && mX < p.x + sysPointSize && mY > p.y && mY < p.y + sysPointSize) {
				highlightX = p.x;
				highlightY = p.y;
				highlightR = sysPointSize;
				repaint();
				systemH = true;
				systemPoint = systemLocations.indexOf(p);
				return true;
			}
		}
		repaint();
		highlightX = 0;
		highlightY = 0;
		highlightR = 0;
		systemH = false;
		bodies = null;
		return false;
	}

	private class HandlerClass implements MouseListener, MouseMotionListener, KeyListener {

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			if (leftMouseDown)
				System.out.println("Left button down");
			else if (rightMouseDown) {
				System.out.println("Right button down");
			}
		}

		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			mX = e.getX() - 3;
			mY = e.getY() - 30;
			frame.setCursor(currentCursor);
			switch (view) {
			case SYSTEMV:
				if (checkBodyCollision(mX, mY, 0))
					currentCursor = handCursor;
				else
					currentCursor = defCursor;
				break;

			case SECTORV:
				if (checkSystemCollision(mX, mY))
					currentCursor = handCursor;
				else
					currentCursor = defCursor;
				break;
			default:
				currentCursor = defCursor;
				break;
			}
			frame.setCursor(currentCursor);
			repaint();
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			mX = e.getX() - 3;
			mY = e.getY() - 30;
			selectedBody = body;
			if (infoTabActive && checkBodyCollision(mX, mY, 1) && (selectedBody == body)) {
				view = INDIVIDUALV;
			}
			infoTabActive = false;
			if (view == SECTORV && checkSystemCollision(mX, mY)) {
				bodies = systems.get(systemPoint);
				view = SYSTEMV;
			} else if (!isBuilding && mX > 600 && view == INDIVIDUALV) {
				isBuilding = true;
			}
			else if(isBuilding){
				constructBuilding(body,mX,mY, 0);
				isBuilding = false;
			}
			// Changing info tab display
			else if (view == SYSTEMV && checkBodyCollision(mX, mY, 1)) {
				infoTabActive = true;
				infoTabLoc.setLocation(body.getWindowLocX() + body.getRadius() * 2 + 5, body.getWindowLocY());
			}
			if (e.getButton() == e.BUTTON3) {
				if (view < SECTORV)
					view++;
				infoTabActive = false;
				isBuilding = false;
			}
			repaint();
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

			if (temp == false) {
				generateSystems();
				repaint();
				temp = true;
			}
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			if (e.getButton() == e.BUTTON1)
				leftMouseDown = true;
			else if (e.getButton() == e.BUTTON3) {
				rightMouseDown = true;
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			if (e.getButton() == e.BUTTON1)
				leftMouseDown = false;
			else if (e.getButton() == e.BUTTON3) {
				rightMouseDown = false;
			}
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			if (e.getKeyCode() == e.VK_BACK_SPACE) {
				if (view == SYSTEMV) {
					view = SECTORV;
					repaint();
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
