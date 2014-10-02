package FinalProject;

import java.util.Scanner; //REMOVE IF NOT NEEDED

class Player {
	private int x, y; //Player's x and y positions.
	Map currentMap;
	Player(Map m){
		this.currentMap = m;
		x = 0; 
		y = 0;
	}
	private boolean canHazMove(int lr, int ud){
		if(((x + lr < currentMap.getMapSize())
			&& (x + lr >= 0))
			
			&& //LAWD HELP ME THIS IS UGLY
			
			((y + ud < currentMap.getMapSize())
			&& (y + ud >= 0))){

			if(currentMap.getMapUnit(x+lr, y+ud).getStatus() != 1){ //If 
				if(currentMap.getMapUnit(x+lr, y+ud).getStatus() == -1){
					//System.out.println("\nMoving to a ?");
					currentMap.flip();
					return true;
				}
				else{
					//System.out.println("\nMoving to a 0!");
					return true;
				}
			}
			else
				return false;
		}
		else
			return false;
	}
	public boolean move(int leftright, int updown){//Returns true if successful movement
		if(canHazMove(leftright, updown)){

			currentMap.getMapUnit(x, y).preserve();


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
		if(x == 9 && y == 9)
			return false;
		else
			return true;
	}
}