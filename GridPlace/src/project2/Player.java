package project2;

import java.awt.Color;
import java.util.ArrayList;

public class Player extends Objects {
	static Color color = Color.RED;
	private int mDir = 1;
	private int delay = 0;
	public Player(int x, int y) {
		super(x, y, color);
		// TODO Auto-generated constructor stub
	}

	public void operate(ArrayList<Player> array){
		
		move();
	
		for(int i = 1; i<array.size();i++)
			array.get(i).setMoveDir(array.get(i-1).getMoveDir());
		

	}
	public void setMoveDir(int x){
		if((mDir == 1 && x != 2) || (mDir == 2 && x != 1) || (mDir == 3 && x != 4) || (mDir == 4 && x != 3))
			mDir = x;
	}
	public void move(){
		if(mDir == 1){
			setX(getX()-5);
		}
		else if(mDir == 2)
			setX(getX()+5);
		else if(mDir == 3)
			setY(getY()-5);
		else if(mDir == 4)
			setY(getY()+5);
	}
	public int getMoveDir(){
		return mDir;
	}

}
