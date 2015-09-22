package AI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.util.*;
 public class PathFinder extends JPanel{
	
	 ArrayList<Organism> organisms = new ArrayList();
	 ArrayList<Food> foods = new ArrayList();
	 int foodX = (int)(Math.random() * 250);
	 int foodY = (int)(Math.random() * 250);
	 int numBirths = 0;
	 int space = 250;
     //Add in original organisms in organism arraylist
	 public void createOrganisms(){
		 for(int i = 0;i<1000;i++){
			 //Organism(position X, position Y, color, sick, gender)
	 			organisms.add(new Organism((int)(Math.random() * space),(int)(Math.random() * space),new Color((int)(Math.random() *254)+1,(int)(Math.random() *254)+1,(int)(Math.random() *254)+1),1,(int)(Math.random()*2)));
	 		}
	 }
	 //Create da foods square
//	 public void createFood(){
//		 for(int x=foodX;x<foodX+50;x+=5){
//			 for(int y = foodY;y<foodY +50;y+=5){
//	 			foods.add(new Food(x,y));
//			 }
//		 }
//	 }
	 @Override
     public void paint(Graphics g) {
 		super.paint(g);
 		Graphics2D g2d = (Graphics2D) g;
 		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
 				RenderingHints.VALUE_ANTIALIAS_ON);
 		
 		g2d.setColor(new Color(0,0,255));
 		//Cycles through every organism object in the arraylist
 		for(int i =0; i<organisms.size();i++){
 			if(organisms.get(i).health<0){
 				//Removes dead organisms from the array list
 				Organism.numDead ++;
 				organisms.remove(i);
				System.out.println(Organism.numDead + " have died.");
 			}
 			//Sets the color of the organism
	 		g2d.setColor(organisms.get(i).color);
	 		g2d.fillRect(organisms.get(i).posX,organisms.get(i).posY, 5, 5);
 			//Randomly move organims
 			organisms.get(i).randAction();
 			for(int x =0; x<organisms.size();x++){
 				//Checks if two organims collided
 				if(organisms.get(i).collided(organisms.get(x).posX, organisms.get(x).posY, 4)){
					if((organisms.get(i).isSick || organisms.get(x).isSick) && !(organisms.get(i).isSick && organisms.get(i).isSick)){
		 				organisms.get(x).setSick();
		 				organisms.get(i).setSick();
	 				}
 				}
 				if(organisms.get(i).posX == organisms.get(x).posX && organisms.get(i).posY == organisms.get(x).posY && i!=x){
 					//If one of two organisms are sick make the other sick

 					//If neither organim is sick and havent aleady had children create another organim that is blue
 					if(!(organisms.get(x).getParent() || organisms.get(i).getParent()) && !organisms.get(i).isSick && !organisms.get(x).isSick  && organisms.get(x).getGender() != organisms.get(i).getGender()){
 						organisms.get(x).setColor(Color.BLUE);
	 					organisms.add(new Organism(organisms.get(i).posX+5,organisms.get(i).posY+5,Color.BLUE,(int)(Math.random()*100),(int)(Math.random() * 1)));
	 					organisms.get(x).setParent(true);
	 					organisms.get(i).setParent(true);
	 					numBirths++;
	 					System.out.println("Births: " + numBirths);
 					}
 						
 				}
 			}
 		}
 		//Draw the foods
 		for(int i = 0;i<foods.size();i++){
 			g2d.setColor(new Color(0,255,0));
 			g2d.fillRect(foods.get(i).posX, foods.get(i).posY, 5, 5);
 		}

 	}

	 public void mouseClicked(MouseEvent e) {

	     int x=e.getX();
	     int y=e.getY();
	     System.out.println(x+","+y);//these co-ords are relative to the component
	 }
	 public void MouseEventDemo() {
	         
	        //Register for mouse events on blankArea and the panel.
	        
	    }
	 public class AL extends KeyAdapter {

	        @Override
	        public void keyPressed(KeyEvent event) {
	            int keyCode = event.getKeyCode();
	            if (keyCode == event.VK_LEFT)
	            {
	            	System.out.println("LEFT");
	            }
	            if (keyCode == event.VK_RIGHT)
	            {
	            	System.out.println("RIGHT");
	            }
	            if (keyCode == event.VK_UP)
	            {
	            	System.out.println("UP");
	            }
	            if (keyCode == event.VK_DOWN)
	            {
	            	System.out.println("DOWN");
	            }
	        }

	        @Override
	        public void keyReleased(KeyEvent event) {
	        }
	    }
	 //Gui window shenanigans
      public static void main(String[] args) throws InterruptedException{
  		JFrame frame = new JFrame("AI");
  		PathFinder game = new PathFinder();
 //       addKeyListener(new AL());

  		frame.add(game);
  		frame.setSize(1000, 600);
  		frame.setVisible(true);
  		frame.setResizable(false);
  		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 		game.createOrganisms();
 		//game.createFood();
 		
 		
  		while (true) {
  			game.repaint();
  			//refresh rate
  			Thread.sleep(1);
  		}
  	}
	
  }