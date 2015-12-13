import java.io.Serializable;

public class Player implements Serializable{
	private int posX;
	private int posY;
	private int xVel,yVel;
	private String name;
	private String message = "Hello";
	private float tick = 0;
	public Player(int x, int y){
		posX = x;
		posY = y;
		xVel = 0;
		yVel = 0;
	}
	public int getX(){
		return posX;
	}
	public int getY(){
		return posY;
	}
	public String getName(){
		return name;
	}
	public String getMessage(){
		return message;
	}
	public int getXVel(){
		return xVel;
	}
	public int getYVel(){
		return yVel;
	}
	public void setX(int x){
		posX = x;
	}
	public void setY(int y){
		posY = y;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setMessage(String message){
		this.message = message;
	}
	public void setXVel(int x){
		xVel = x;
	}
	public void setYVel(int y){
		yVel = y;
	}	
	public void move(){
		tick+=.1f;
		if(tick>=1){
			posX += xVel;
			posY += yVel;
			tick = 0;
		}
	}
}
