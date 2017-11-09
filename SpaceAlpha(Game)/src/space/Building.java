package space;

public interface Building {
	public String getType();
	public void function();
	public int getX();
	public int getY();
	//returns place where building resides
	public CelestialBody getLocation();
}
