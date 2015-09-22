package project2;
import java.awt.Color;
import java.util.ArrayList;


public abstract class Objects {
	int x,y;
	Color color;
	boolean isActive = true;
	public int size;
	public Objects(int x, int y,Color color){
		this.x = x;
		this.y = y;
		this.color = color;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public void setX(int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}
	public void deactivate(){
		isActive = false;
	}
	public int getSize(){
		return size;
	}
	public Color getColor(){
		return color;
	}
	public void setColor(Color color){
		this.color = color;
	}
	public void operate(){
		
	}
//	public void operate(ArrayList<Objects> array){
//		
//	}
	public void operate(ArrayList<Objects> array,Objects obj){
		
	}
//	public void operate(ArrayList<Player> array,Objects obj){
//		
//	}
	public int getMoveDir() {
		// TODO Auto-generated method stub
		return 0;
	}
	public void setMoveDir(Object moveDir) {
		// TODO Auto-generated method stub
		
	}
	public void operate(ArrayList<Player> playerParts) {
		// TODO Auto-generated method stub
		
	}

}
