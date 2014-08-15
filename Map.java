package FinalProject;

import java.io.*;

/*For now, maps will be based of 1.0.0's original 10x10 map.
In the future, add capability to read map from text file.
*/
class Map{
	private MapUnit[][] map;
	/*Map(File file){
		To be added later-reads in a txt file provided by the user
		(checks for incorrect size/errors)
		and creates a map off that (custom size?)
	}*/
	Map(){
		map=new MapUnit[10][10];
	}
	Map(int mapSize){

	}
}