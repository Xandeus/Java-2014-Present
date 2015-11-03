package Fractals;

import java.awt.Color;

public class Shape {
	int x,y,r,dX,dY;
	boolean isActive = false;
	Color color = new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));
	public Shape(int x, int y, int r){
		this.x = x;
		this.y = y;
		this.r = r;
		
	}
	public Shape(int x, int y, int dX,int dY){
		this.x = x;
		this.y = y;
		this.dX = dX;
		this.dY = dY;
		
	}
	public Shape(int x, int y, int dX,int dY,Color color){
		this.x = x;
		this.y = y;
		this.dX = dX;
		this.dY = dY;
		this.color = color;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int getR(){
		return r;
	}
	public int getDX(){
		return dX;
	}
	public int getDY(){
		return dY;
	}
	public void setX(int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}
	public void setR(int r){
		this.r = r;
	}
	public void setDX(int dx){
		dX = dx;
	}
	public void setDY(int dy){
		dY = dy;
	}
	public void change(int x, int y, int r){
		this.x+=x;
		this.y+=y;
		this.r+=r;
	}
	public boolean isActive(){
		return isActive;
	}
	public Color getColor(){
		return color;
	}
	public void setColor(Color color){
		this.color = color;
	}
	public void changeState(){
		isActive = !isActive;
	}
}
