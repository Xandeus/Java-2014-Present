import java.io.Serializable;

public class Player implements Serializable{
	private int posX;
	private int posY;
	private int moveSpeed;
	public Player(int x, int y){
		posX = x;
		posY = y;
		moveSpeed = 8;
	}
	public int getX(){
		return posX;
	}
	public int getY(){
		return posY;
	}
	public void setX(int x){
		posX = x;
	}
	public void setY(int y){
		posY = y;
	}
	public void moveX(float x){
		posX += (int)x;
	}
	public void moveY(float y){
		posY += (int)y;
	}
	public int getMovementspeed(){
		return moveSpeed;
	}
}
