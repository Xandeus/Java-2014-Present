package chess;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;

public abstract class GamePiece {
	int x, y;
	int pX,pY;
	Image image;
	boolean isWhite;
	String name;
	public GamePiece(int x, int y,boolean isWhite,int posX, int posY){
		this.x = x;
		this.y = y;
		pX = posX;
		pY = posY;
		
		this.isWhite = isWhite;
	}
	public GamePiece(int x, int y,int posX, int posY){
		this.x = x;
		this.y = y;
		pX = posX;
		pY = posY;
		
	}
	public abstract boolean isMoveValid(GamePiece desiredMove, GamePiece[][] pieces);
	public abstract ArrayList<GamePiece> findValidMoves(GamePiece[][] pieces);
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
	public Image getImage(){
		return image;
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
	public void setImage(Image image){
		this.image = image;
	}
	public boolean isWhite(){
		return isWhite;
	}

	
}
