package space;

import java.awt.Color;

public class Planet implements CelestialBody {
	Color color = new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256));
	static String[] resources = {"Iron","Copper","Gold","Silicon","Fuel",};
	String[] planetResources = new String[10];
	int radius;
	int density = (int)(Math.random()*1000), volume = (int)(Math.random()*1000), mass = (int)(Math.random()*1000);
	int wLocX, wLocY;
	int resourceTotal = (int)(Math.random()*2000)+500; 
	//Change to better system
	int atmoSphere = (int)(Math.random()*2);
	String pType;
	public Planet() {
		if((int)(Math.random()*2)==0){
			pType = "Terrestrial";
			radius = ((int) (Math.random() * 11) + 5);
			planetResources[0] = resources[(int)(Math.random()*5)];
		}
		else{
			pType = "Gas";
			radius = ((int) (Math.random() * 11) + 15);
			planetResources[0] ="N/A";
		}
	}

	public Planet(int r) {
		radius = r;
	}

	public int getWindowLocX() {
		return wLocX;
	}

	public int getWindowLocY() {
		return wLocY;
	}

	public int getLocation() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Color getColor() {
		return color;
	}
	public String getType(){
		return pType + " planet";
	}
	public String getResources(){
		return planetResources[0] ;//resource;
	}
	public int getResourceTotal(){
		return resourceTotal;
	}
	public boolean hasAtmosphere(){
		if(atmoSphere==0)
			return true;
		return false;
	}
	public int getRadius() {
		return radius;
	}

	public int getMass() {
		// TODO Auto-generated method stub
		return mass;
	}

	public int getDensity() {
		// TODO Auto-generated method stub
		return density;
	}

	public int getVolume() {
		// TODO Auto-generated method stub
		return (int)((4.0/3.0)*Math.PI*Math.pow(radius, 3));
	}

	// Sets gui window location x
	public void setWindowLocX(int x) {
		wLocX = x;
	}

	public void setWindowLocY(int y) {
		wLocY = y;
	}
}
