package FinalProject;

import java.util.Scanner; //REMOVE IF NOT NEEDED

class Player {
	private int x, y; //Player's x and y positions.
	Map currentMap;
	Player(Map m){
		this.currentMap = m;
		x = 0; 
		y = 9;
	}
	public boolean move(int leftright, int updown){
		if(((x + leftright < currentMap.getMapSize())
			&& (x + leftright > 0))
			&& //LAWD HELP ME THIS IS UGLY
			((y + updown < currentMap.getMapSize())
			&& (y + updown > 0))){

			x += leftright;
			y += updown;
			return true;
		}
		else
			return false;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public boolean hasntWon(){
		if(x == 9 && y ==0)
			return false;
		else
			return true;
	}
}