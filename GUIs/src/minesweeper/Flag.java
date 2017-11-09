package minesweeper;

public class Flag {
	int posX, posY;
	public Flag(int x, int y){
		posX = x;
		posY = y;
	}
	public int getX(){
		return posX;
	}
	public int getY(){
		return posY;
	}
}
