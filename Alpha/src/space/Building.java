package space;

public interface Building {
	public String getType();
	public void function();
	//returns place where building resides
	public CelestialBody getLocation();
}
