package physicsGame;

public class CannonBall {
	private int x, y;
	private float vx,vy,ax,ay;
	public CannonBall(int x, int y,float vx,float vy, float ax, float ay){
		this.x = x; 
		this.y = y;
		this.vx = vx; 
		this.vy = vy;
		this.ax = ax; 
		this.ay = ay;
	}
	public CannonBall(int x, int y,float ax, float ay){
		this.x = x; 
		this.y = y;
		this.ax = ax; 
		this.ay = ay;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}public float getVX(){
		return vx;
	}
	public float getVY(){
		return vy;
	}
	public float getAX(){
		return ax;
	}
	public float getAY(){
		return ay;
	}
	public void setX(int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}public void setVX(float v){
		vx = v;
	}
	public void setVY(float v){
		vy = v;
	}
	public void setAX(float a){
		ax = a;
	}
	public void setAY(float a){
		ay = a;
	}
	public void move(){
		x+= vx;
		y+= vy;
		vx+=ax;
		vy+=ay;
	}
}
