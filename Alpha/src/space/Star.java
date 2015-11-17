package space;

import java.awt.Color;

public class Star implements CelestialBody{
	int radius;
	int wLocX, wLocY;
	Color color = new Color((int)(Math.random()*256),(int)(Math.random()*256),0);
	public Star(){
		radius = ((int)(Math.random()*51)+50);
	}
	public Star(int r){
		radius = r;
	}
	public int getWindowLocX(){
		return wLocX;
	}
	public int getWindowLocY(){
		return wLocY;
	}
	public int getLocation() {
		// TODO Auto-generated method stub
		return 0;
	}
	public Color getColor(){
		return color;
	}
	public int getRadius(){
		return radius;
	}
	public int getMass() {
		// TODO Auto-generated method stub
		return 0;
	}
	public int getDensity() {
		// TODO Auto-generated method stub
		return 0;
	}
	public int getVolume() {
		// TODO Auto-generated method stub
		return 0;
	}
	//Sets gui window location x
	public void setWindowLocX(int x){
		wLocX = x;
	}
	public void setWindowLocY(int y){
		wLocY = y;
	}

}
