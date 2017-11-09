package ecosystem;
import java.awt.Color;
import java.util.ArrayList;


public class Predator extends Objects{
	int sightDist=5;
	int size = 5;
	static Color color = Color.BLACK;
	String eye;
	int dis;
	Objects sightedObj = null;
	public Predator(int x, int y) {
		super(x, y,color);
		// TODO Auto-generated constructor stub
	}
	public void operate(ArrayList<Objects> array){
		eye = "";
		sightedObj = null;
		dis = 1000;
		for(Objects obj : array){
			look(obj.getX(),obj.getY(),obj);
			if(obj instanceof Organism && (obj.getX() == getX()) && (obj.getY() == getY())){
				obj.deactivate();
				size+=5;
				energy+=5;
			}
		}
		if (eye.equals("r"))
			rightEye(sightedObj);
		else if (eye.equals("l"))
			leftEye(sightedObj);
		else if (eye.equals("t"))
			topEye(sightedObj);
		else if (eye.equals("b"))
			bottomEye(sightedObj);
		else{
			System.out.println("Move random");
			randMove(5);
		}
		energy-=5;
		if(energy <= 0){
			deactivate();
		}
	}
	public int getSize(){
		return size;
	}
	public void rightMotor(int x){
		setX(getX()+x);
	}
	public void leftMotor(int x){
		setX(getX()-x);
	}
	public void topMotor(int x){
		setY(getY()-x);
	}
	public void bottomMotor(int x){
		setY(getY()+x);
	}
	public void rightEye(Objects obj){
		
		if(obj instanceof Organism){
			rightMotor(10);
		}
		else if(obj instanceof Predator){
			if(obj.getSize() < size)
				rightMotor(10);
			else
				leftMotor(5);
		}
		else
			randMove(5);
	}
	public void leftEye(Objects obj){

		if(obj instanceof Organism){
			leftMotor(10);
		}
		else if(obj instanceof Predator){
			if(obj.getSize() < size)
				leftMotor(10);
			else
				rightMotor(5);
		}
		else
			randMove(5);
	}
	public void topEye(Objects obj){

	
		if (obj instanceof Organism) {
			topMotor(10);
		}
		else if(obj instanceof Predator){
			if(obj.getSize() < size)
				topMotor(10);
			else
				bottomMotor(5);
		}
		else
			randMove(5);
		
	}
	public void bottomEye(Objects obj){

		if (obj instanceof Organism) {
			bottomMotor(10);
		}
		else if(obj instanceof Predator){
			if(obj.getSize() < size)
				bottomMotor(10);
			else
				topMotor(5);
		}
		else
			randMove(5);
		
	}
	public void look(int x, int y,Objects obj){
		if (this != obj) {
			// Right Eye
			if ((x >= (getX() + 5)) && (x < (getX() + 5 + (5 * sightDist)))) {
				if (y == getY()) {
					if ((x - (getX()+5)) < dis) {
						dis = x - getX()+5;
						eye = "r";
						sightedObj = obj;
					}
				}
			}
			// Left eye
			if ((x < (getX())) && (x >= (getX() - (5 * sightDist)))) {
				if (y == getY()) {
					if ((getX() - x) < dis) {
						dis = getX() - x;
						eye = "l";
						sightedObj = obj;
					}
				}
			}
			// Top Eye
			if ((y < (getY())) && (y >= (getY() - (5 * sightDist)))) {
				if (x == getX()) {
					if ((getY() - y) < dis) {
						dis = getY() - y;
						eye = "t";
						sightedObj = obj;
					}
				}
			}
			//Bottom
			if ((y >= (getY() + 5)) && (y < (getY() + 5 + (5 * sightDist)))) {
				if (x == getX()) {
					if ((y-(getY()+5) ) < dis) {
						dis = y-(getY()+5);
						eye = "b";
						sightedObj = obj;
					}
				}
			}
			
		}

	}

	public void randMove(int x){
		int rand = (int)(Math.random()*5);
		switch(rand){
		case 0 :
			rightMotor(x);
			break;
		case 2 :
			leftMotor(x);
			break;
		case 3 :
			topMotor(x);
			break;
		case 4 :
			bottomMotor(x);
			break;
		}
	}

}
