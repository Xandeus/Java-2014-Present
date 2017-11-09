package virus;


import java.awt.Color;
import java.util.ArrayList;

public class Organism {
	public static int numDead = 0;
	public static Virus virus = new Virus(1,0.01);
	int posX,posY;
	int visionStrength = 10;
	double health;
	double damage;
	int gender;
	int movementSpeed = 2;
	boolean isAlive = true;
	boolean isSick = false;
	boolean isParent = false;
	Brain brain = new Brain();
	Color color;
	Organism(int startX, int startY,Color color,int sick,int gender){
		health = 10;
		damage = health * .25;
		posX = startX;
		posY = startY;
		this.gender = gender;
		if(sick == 0){
			isSick = true;
		}
		//0 is a male 1 is female
		if(gender == 0){
			this.color = Color.GREEN;
		}
		else if(gender == 1){
			this.color = Color.PINK;
		}
	}
	public void leftMotor(){
		if(posX>10)
			posX-=movementSpeed;
	}
	public void rightMotor(){
		if(posX<990)
			posX+=movementSpeed;
	}
	public void bottomMotor(){
		if(posY<590)
			posY+=movementSpeed;
	}
	public void topMotor(){
		if(posY>10)
			posY-=movementSpeed;
	}
	public void setColor(Color color){
		this.color = color;
	}
	public void randAction() {
		int randMotor = (int)(Math.random()*10);
		
		switch(randMotor){
			case 0:
				leftMotor();
				break;
			case 1:
				rightMotor();
				break;
			case 2:
				bottomMotor();
				break;
			case 3 :
				topMotor();
				break;
				
		}
		if(isSick){
			health-=virus.getDamage();
		}
	}
	public void setSick() {
		// TODO Auto-generated method stub
		this.color = Color.RED;
		isSick = true;
	}
	public void setDead() {
		// TODO Auto-generated method stub
		isAlive = false;
	}
	public void setParent(boolean parent) {
		// TODO Auto-generated method stub
		isParent = parent;
	}
	public boolean getParent() {
		// TODO Auto-generated method stub
		return isParent;
	}
	public int getGender(){
		return gender;
	}
	public boolean collided(int posX, int posY,int radius){
		if(posX > (this.posX - radius) && posX < (this.posX + 5 + radius))
			if(posY >(this.posY- radius) && posY< (this.posY + 5 + radius))
				return true;
			 
		return false;
	}
}
