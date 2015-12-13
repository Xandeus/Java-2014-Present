package space;

import java.awt.Image;

public class BackgroundStar {
	int size;
	float posX;
	float posY;
	Image image;
	public BackgroundStar(int x, int y, int s,Image i){
		posX = x;
		posY = y;
		size = s;
		image = i;
	}
	public float getX(){
		return posX;
	}
	public float getY(){
		return posY;
	}
	public int getSize(){
		return size;
	}
	public void setX(int x){
		posX = x;
	}
	public void setY(int y){
		posY = y;
	}
	public void incrementX(float x){
		posX +=x;
	}
	public void incrementY(float y){
		posY +=y;
	}
	public Image getImage(){
		return image;
	}
}
