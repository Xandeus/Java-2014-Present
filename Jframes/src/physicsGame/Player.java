package physicsGame;

public class Player {
	private float posX,posY;
	private int width,height;
	private float dX=0,dY=0;
	private boolean colliding = false;
	public Player(int x, int y,int w, int h){
		posX = x;
		posY = y;
		width = w;
		height = h;
	}
	public void updatePos(){
		posX += dX;
		posY += dY;
	}
	public void setX(int x){
		posX = x;
	}
	public void setY(int y){
		posY = y;
	}
	public void setdX(float x){
		dX = x;
	}
	public void setdY(float y){
		dY = y;
	}
	public void setColliding(boolean value){
		colliding = value;
	}
	public boolean isColliding(){
		return colliding;
	}
	public void incX(float x){
		dX += x;
	}
	public void incY(float y){
		dY += y;
	}
	public int getX(){
		return (int)(posX);
	}
	public int getY(){
		return (int)(posY);
	}
	public int getHeight(){
		return height;
	}
	public int getWidth(){
		return width;
	}
	public float getdX(){
		return dX;
	}
	public float getdY(){
		return dY;
	}
}
