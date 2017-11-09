package project2;
import java.awt.Color;
import java.util.ArrayList;


public class Organism extends Objects{
	int sightDist=5;
	static Color color = Color.BLUE;
	String eye;
	int dis;
	Objects sightedObj = null;
	public Organism(int x, int y){
		super(x,y,color);
	}
	public void operate(ArrayList<Player> array){
		eye = "";
		sightedObj = null;
		dis = 1000;
		for(Objects obj : array){
			look(obj.getX(),obj.getY(),obj);
			if(obj instanceof Food && (obj.getX() == getX()) && (obj.getY() == getY()))
				obj.deactivate();
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
		
		if(obj instanceof Food)
			rightMotor(5);
		
	}
	public void leftEye(Objects obj){

		if(obj instanceof Food)
			leftMotor(5);
		else if(obj instanceof Organism){
			rightMotor(5);
		}
	}
	public void topEye(Objects obj){

		if (obj instanceof Food)
			topMotor(5);
		else if (obj instanceof Organism) {
			bottomMotor(5);
		}
		
	}
	public void bottomEye(Objects obj){

		if (obj instanceof Food)
			bottomMotor(5);
		else if (obj instanceof Organism ) {
			topMotor(5);
		}
		
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
