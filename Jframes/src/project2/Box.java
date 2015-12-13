package project2;

import java.awt.Color;
import java.util.ArrayList;

public class Box extends Objects{
	static Color color = Color.BLACK;
	private int dirX  = 5;
	private int dirY = 5;

	public Box(int x, int y) {
		super(x, y, color);
		// TODO Auto-generated constructor stub
	}
	public void operate(ArrayList<Objects> array,Objects object){
		setX(getX() + dirX);
		setY(getY()+ dirY);
		if((getX()<= 0 || getX() >=1000))
			dirX*=-1;
		if((getY()<= 0 || getY() >=800))
			dirY*=-1;
		else
			for(Objects obj : array){
				if(getX() == obj.getX() && getY() == obj.getY() && obj!=object)
					dirX*=-1;
			}
		
	}
}
