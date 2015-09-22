package AI;

public class Virus {
	int virulence;
	double  damage;
	public Virus(int virulence,double damage){
		this.virulence = virulence;
		this.damage = damage;
	}
	public int getVirulence(){
		return virulence;
	}
	public double getDamage(){
		return damage;
	}
}
