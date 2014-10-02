package FinalProject;

import java.io.*;
import java.util.*;
/*NEW: 0/1 MAZE, WITH ?s=CHANGES MAP
why are 1s and 0s still stored as chars? because ?s are included, stupid
Also, maybe add capability to use something else beside 1s and 0s
Java is considered "row major", meaning that it does rows first. 
This is because a 2D array is an "array of arrays".
Array[number of arrays][how many elements in each of those arrays]
*/

class Map{
	private MapUnit[][] mapArray;
	String defaultFilePath="FinalProject/defaultMap.biMap.txt";
	File defaultMap=new File(defaultFilePath);
	private Hashtable<Byte, Character> gameRepresentations = new Hashtable<Byte, Character>();
	private int mapSize=10, rowSize=0, columnSize=0;
	Map(File file){ //Loading a custom map
		readFile(file);
	}
							//Print values: open, then occupied, player, mixup
	Map(char... printValues){ //This is for custom representations, or the default no arg constructor
		if(printValues.length == 4) 
			setGameRepresentations(printValues); 
		else
			setGameRepresentations();
		mapArray = new MapUnit[mapSize][mapSize];//THE ISSUE: Every printValue, even if default, must be repassed
		readFile(defaultMap);
	}

	Map(int mapSize){ //DON'T USE RITE NOW
	//This will be worked on later..as of right now, no point?
		this.mapSize=mapSize;//bc our default map has a certain size
		mapArray = new MapUnit[mapSize][mapSize];
		for(int i=0; i<mapArray.length; i++){
			for(int b=0; b<mapArray[i].length; b++){
				mapArray[i][b] = new MapUnit((byte)0);
			}
		}
	}	
	
	public MapUnit getMapUnit(int x, int y) {
		return mapArray[x][y];
	}

	public void preserve(int x, int y){
		this.getMapUnit(x, y).preserve();
	}

	public char getGameRep(byte b){ //make sure byte sent is correct, is static so the standared getGameRep can be ref'd from wherever
	//might wanna look at unstatickin later
		return (gameRepresentations.get(new Byte(b)));
	}

	public void update(int x, int y, byte status){ //Status is the new status to set the updated point.
		//this.mapArray[x][y].update(status, gameRepresentations.get(new Byte(status)));
		this.mapArray[x][y].update(status);
	}

	public String toString(){
		String sToReturn="";
		if(this.mapArray.equals(null))
			return "ERROR"; //TODO: throw exception
		for(int m=0; m<mapSize; m++){
			for(int n=0; n<mapSize; n++){
				sToReturn += getGameRep(mapArray[m][n].getStatus()); 
			}														
			//System.out.println("Printin a " + sToReturn);	
			sToReturn += "\n";
		}
		return sToReturn;
	}

	public MapUnit[][] getMap(){return mapArray;}

	private void readFile(File file){
		int row=0, column=0;
		try(Scanner s = new Scanner(file); Scanner sc = new Scanner(file)){
			get_size:{
				rowSize=s.next().length();
				columnSize++;//This is bc the readLine reads one column/row or whatever.
				while(s.hasNextLine()){
					s.next();
					columnSize++;
				}
			}
			if(columnSize == rowSize){ //If the map is square
				this.mapSize = columnSize;
				mapArray = new MapUnit[mapSize][mapSize];
				while(sc.hasNextLine()){
					String line=sc.next();
					for(column=0; column<rowSize; column++){
						//if(checkRepresentationValidity(mapArray[row][column].getStatus()))
						//	throw new UnknownSymbolInMapException(mapArray[row][column].getRep());
						//mapArray[row][column] = new MapUnit(line.charAt(column));	
						//System.out.println("Reading a : " + line.charAt(column));
						switch(line.charAt(column)){
							case '0': mapArray[row][column] = new MapUnit((byte)0);  break;
							case '1': mapArray[row][column] = new MapUnit((byte)1);  break;
							case '?': mapArray[row][column] = new MapUnit((byte)-1); break;
							case '@': mapArray[row][column] = new MapUnit((byte)2);  break; //not needed for now, auto placement at top corner
							default: System.out.println("HAIII ERRRRROOOOOOOOOR");
						}
					}
					//System.out.println();
					row++;
				}
					
			}
		
		else
			throw new NonSquareMapException(rowSize, columnSize);
		
		s.close();//These are here b/c the finally clause doesn't work with rec's init'd in a try w/ rec's (b/c the scope is only in the try block)
		sc.close();
		}
		catch(FileNotFoundException exc){//Add retry capabiliity later
			System.out.println("File not found.");
		}
		catch(IOException exc){
			exc.printStackTrace();
		}
		catch(NonSquareMapException exc){
			System.out.println(exc);
		}
		/*catch(UnknownSymbolInMapException exc){
			System.out.println(exc);
		}*/
	}
	private boolean checkRepresentationValidity(byte toBeChecked){//Later add checking for only 0/1s and such.
		Set <Byte> keys = gameRepresentations.keySet();
		for(byte c: keys){
			if(toBeChecked!=gameRepresentations.get(c))
				return false; //CURRENTLY NOT WORKING, WILL COME BACK TO IT LATER TO FIX FOR CUSTOM MAPS
				//return true;
		}
		return false;
	}
	public void flip(){
		//System.out.println("\nFlipping map!");
		for(int i=0; i<mapSize; i++){
			for(int j=0; j<mapSize; j++){
				mapArray[i][j].flip();
			}
		}
	}
	private void setGameRepresentations(char... representationValues){ //Sets game representation
		if(representationValues.length>0){ //Checking if it exists or not,
			gameRepresentations.put(new Byte((byte)0),representationValues[0]);//Not if correct length, 
			gameRepresentations.put(new Byte((byte)1), representationValues[1]);//since the constructor has a condition to make sure the length is correct
			gameRepresentations.put(new Byte((byte)2), representationValues[2]);
			gameRepresentations.put(new Byte((byte)-1), representationValues[3]);			
		}
		else{
			gameRepresentations.put(new Byte((byte)0), '0');
			gameRepresentations.put(new Byte((byte)1), '1');
			gameRepresentations.put(new Byte((byte)2), '@');
			gameRepresentations.put(new Byte((byte)-1), '?');
		}
	}
	public int getMapSize(){
		return this.mapSize;
	}
}