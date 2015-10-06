package Chess;

import java.awt.Color;

public abstract class GamePiece {
	int x, y;
	int pX,pY;
	Color color;
	String name;
	public GamePiece(int x, int y,Color color,int posX, int posY){
		this.x = x;
		this.y = y;
		pX = posX;
		pY = posY;
		
		this.color = color;
	}
	
	public abstract void move(int x, int y);
	public abstract boolean isMoveValid(GamePiece desiredMove, GamePiece[][] pieces);
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int getPosX(){
		return pX;
	}
	public int getPosY(){
		return pY;
	}
	public String getName(){
		return name;
	}
	public void setPosX(int x){
		pX = x;
	}
	public void setPosY(int y) {
		// TODO Auto-generated method stub
		pY = y;
	}
	public void setName(String name){
		this.name = name;
	}
	public Color getColor(){
		return color;
	}

	
}
