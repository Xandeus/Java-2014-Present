package space;

import java.awt.Point;
import java.util.ArrayList;

public abstract class Character {
	float posX, posY;
	int width, height;
	ArrayList<Point> lasers = new ArrayList<Point>();
	CelestialBody currentBody;
	public Character(int x, int y,int w,int h,CelestialBody b){
		posX = x;
		posY = y;
		width = w;
		height = h;
		currentBody = b;
	}
	public float getX(){
		return posX;
	}
	public float getY(){
		return posY;
	}
	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}
	public ArrayList<Point> getLasers(){
		return lasers;
	}
	public void setWidth(int w){
		width = w;
	}
	public void setHeight(int h){
		height = h;
	}
	public CelestialBody getCurrentBody(){
		return currentBody;
	}
	public void setPos(int x, int y){
		posX = x;
		posY = y;
	}
	public void setPosX(int x){
		posX = x;
	}
	public void setPosY(int y){
		posY = y;
	}
	public void setBody(CelestialBody b){
		currentBody = b;
	}
}
