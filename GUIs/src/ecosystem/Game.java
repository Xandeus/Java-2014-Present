package ecosystem;
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
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Game extends JPanel{
    HandlerClass handler = new HandlerClass();
    ArrayList<Organism> organisms = new ArrayList<Organism>();
    ArrayList<Food> foods = new ArrayList<Food>();
    ArrayList<Objects> allObj = new ArrayList<Objects>();
    ArrayList<Objects> births = new ArrayList<Objects>();
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
		    if(obj instanceof Predator)
		    	g2d.fillRect(obj.getX(),obj.getY(), obj.getSize(),obj.getSize());
		    g2d.fillRect(obj.getX(),obj.getY(), 5,5);
			obj.operate(allObj);
			//obj.setColor(randColor());
			if(obj.isActive == false)
				iterator.remove();
			if(obj.energy > 200){
				obj.energy-=100;
				births.add(obj);
			}
		}
		for(Object b : births){
			allObj.add((Objects) new Object());
		}
		births.clear();
	}
	public Color randColor(){
		return new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));
	}
	static JTextField text = new JTextField();

    static JFrame frame = new JFrame("Neural Network");
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
	        frame.addMouseListener(game.handler);
	        frame.addMouseMotionListener(game.handler);

	        while(true){
	        	game.repaint();
	        	Thread.sleep(25);
	        }
	    }
	    public static void main(String[] args) throws InterruptedException{
	        frame();
	        
	    }
	    private class HandlerClass implements MouseListener, MouseMotionListener{
			public void mouseClicked(MouseEvent event){

			}
			public void mousePressed(MouseEvent event){
				int x = event.getX()-5;
				int y = event.getY()-32;
				if(event.getButton() == MouseEvent.BUTTON1){
					lClickD = true;
					allObj.add(new Organism((x-(x%5)),(y-(y%5))));

				}
				if(event.getButton() == MouseEvent.BUTTON3){
					rClickD = true;
					allObj.add(new Food((x-(x%5)),(y-(y%5))));

				}
				if(event.getButton() == MouseEvent.BUTTON2){
					mClickD = true;
					allObj.add(new Predator((x-(x%5)),(y-(y%5))));

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
					allObj.add(new Organism((x-(x%5)),(y-(y%5))));
				}
				else if(rClickD){
					allObj.add(new Food((x-(x%5)),(y-(y%5))));
				}
				else if(mClickD){
					allObj.add(new Predator((x-(x%5)),(y-(y%5))));
				}
			}
			public void mouseMoved(MouseEvent event){
				
				
			}
		}
	
}