package pathFinder;

public class Cube {
	int x,y;
	boolean isDestination;
	public Cube(int x, int y,boolean isDestination){
		this.x = x;
		this.y = y;
		this.isDestination = isDestination;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public boolean isDestination(){
		return isDestination;
	}
	public void setX(int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}
	public void setDestination(boolean isDestination){
		this.isDestination = isDestination;
	}
	
}
