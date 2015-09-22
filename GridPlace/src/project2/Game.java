package project2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Game extends JPanel{
    HandlerClass handler = new HandlerClass();
    ArrayList<Organism> organisms = new ArrayList<Organism>();
    ArrayList<Food> foods = new ArrayList<Food>();
    ArrayList<Player> playerParts = new ArrayList<Player>();
    ArrayList<Integer> movements = new ArrayList<Integer>();
    ArrayList<Objects> allObj = new ArrayList<Objects>();

    static boolean lClickD = false, rClickD = false, mClickD = false;
    static int clicks = 0;
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(Color.black);
		int width = 1000;
	    int height = 800;
		for(int x = 0; x <= width; x+=5)
  	    {
			g.drawLine(x,0,x,height);
  	    }
		for(int y = 0; y <= height; y+=5)
  	    {
			g.drawLine(0,y,width,y);
  	    }
		for(Iterator<Objects> iterator = allObj.iterator(); iterator.hasNext();){
		    Objects obj = iterator.next();
		    g2d.setColor(obj.getColor());
//		    if(obj instanceof Predator)
//		    	g2d.fillRect(obj.getX(),obj.getY(), obj.getSize(),obj.getSize());
		    g2d.fillRect(obj.getX(),obj.getY(), 5,5);
			//obj.operate(allObj);
		//	obj.operate(allObj,obj);
			obj.operate(playerParts);
			
			if(obj.isActive == false || obj.getX() <0 || obj.getX() > 1000 || obj.getY() < 0 || obj.getY() > 800)
				iterator.remove();
		}
	}
	static JTextField text = new JTextField();

    static JFrame frame = new JFrame("Snake");
	  public static void frame() throws InterruptedException{
		    Game game = new Game();
	        Font f = new Font("Engravers MT", Font.BOLD, 23);
	        text.setEditable(false);
	        text.setBackground(Color.GRAY);
	        text.setFont(f);
	        text.setText("Under Construction");
	        frame.add(text, BorderLayout.SOUTH);
	        frame.add(game);
	        frame.setResizable(false);
	        frame.setSize(1000, 800);
	        frame.setLocation(300, 10);
	        frame.getContentPane().setBackground(Color.BLACK);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setVisible(true);
	        frame.setFocusable(true);
	        frame.addMouseListener(game.handler);
	        frame.addMouseMotionListener(game.handler);
	        frame.addKeyListener(game.handler);
	        while(true){
	        	game.repaint();
	        	Thread.sleep(25);
	        }
	    }
	    public static void main(String[] args) throws InterruptedException{
	        frame();
	        
	    }
	    private class HandlerClass extends KeyAdapter implements MouseListener, MouseMotionListener,KeyListener {
	    	
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				int keyCode = e.getKeyCode();
				
	            if (keyCode == e.VK_LEFT)
	            {
	                playerParts.get(0).setMoveDir(1);	                
	            }
	            if (keyCode == e.VK_RIGHT)
	            {
	            	playerParts.get(0).setMoveDir(2);
//	            	for(int i = 1; i<playerParts.size();i++)
//	            		playerParts.get(i).setMoveDir(playerParts.get(i-1).getMoveDir());
	            }
	            if (keyCode == e.VK_UP)
	            {
	            	playerParts.get(0).setMoveDir(3);
//	            	for(int i = 1; i<playerParts.size();i++)
//	            		playerParts.get(i).setMoveDir(playerParts.get(i-1).getMoveDir());
	            }
	            if (keyCode == e.VK_DOWN)
	            {
	            	playerParts.get(0).setMoveDir(4);
//	            	for(int i = 1; i<playerParts.size();i++)
//	                	playerParts.get(i).setMoveDir(playerParts.get(i-1).getMoveDir());
	            }
//	            for(int i = 1; i<playerParts.size();i++)
//                	playerParts.get(i).setMoveDir(playerParts.get(i-1).getMoveDir());
			}
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
			}
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			public void mouseClicked(MouseEvent event){

			}
			public void mousePressed(MouseEvent event){
				int x = event.getX()-5;
				int y = event.getY()-32;
				if(event.getButton() == MouseEvent.BUTTON1){
					lClickD = true;
					Player p1 = new Player((x-(x%5)),(y-(y%5)));
					Player p2 = new Player((p1.getX()-5),(p1.getY()));
					Player p3 = new Player((p1.getX()-10),(p1.getY()));
					allObj.add(p1);
					playerParts.add(p1);
					allObj.add(p2);
					playerParts.add(p2);
					allObj.add(p3);
					playerParts.add(p3);
				}
				if(event.getButton() == MouseEvent.BUTTON3){
					rClickD = true;
					allObj.add(new Food((x-(x%5)),(y-(y%5))));

				}

			}
			public  void mouseReleased(MouseEvent event){
				if(event.getButton() == MouseEvent.BUTTON1)
					lClickD = false;
				if(event.getButton() == MouseEvent.BUTTON3)
					rClickD = false;
			}
			public void mouseEntered (MouseEvent event){
				
			}
			public void mouseExited(MouseEvent event){
				
			}
			//These are mouse motion events
			public void mouseDragged(MouseEvent event){
				int x = event.getX()-5;
				int y = event.getY()-32;
				if(lClickD){
					allObj.add(new Box((x-(x%5)),(y-(y%5))));
				}
				else if(rClickD){
					allObj.add(new Food((x-(x%5)),(y-(y%5))));
				}
			}
			public void mouseMoved(MouseEvent event){
				
				
			}
			
			 	
		}
	
}