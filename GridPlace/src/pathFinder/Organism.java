package pathFinder;

public class Organism extends Cube {

	public Organism(int x, int y, boolean bool) {
		super(x, y, bool);
		// TODO Auto-generated constructor stub
	}

	public void leftMotor(int i) {
		x-= i;
	}

	public void rightMotor(int i) {
		x+=i;
	}

	public void topMotor(int i) {
		y-=i;
	}

	public void bottomMotor(int i) {
		y+=i;
	}

}
