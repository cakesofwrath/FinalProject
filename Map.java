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
	private Hashtable<Character, Byte> gameRepresentations = new Hashtable<Character, Byte>();
	private int mapSize=10, rowSize=0, columnSize=0, row=0, column=0;
	Map(File file){ //Loading a custom map
		readFile(file);
	}						//Print values: open, then occupied, player, mixup
	Map(char... printValues){ //This is for custom representations, or the default no arg constructor
		if(printValues.length == 4) 
			setGameRepresentations(printValues); 
		else
			setGameRepresentations();
		mapArray = new MapUnit[mapSize][mapSize];//THE ISSUE: Every printValue, even if default, must be repassed
		readFile(defaultMap);
	}
	Map(int mapSize){ //This will be worked on later..as of right now, no point?
		this.mapSize=mapSize;//bc our default map has a certain size
		mapArray = new MapUnit[mapSize][mapSize];
		for(int i=0; i<mapArray.length; i++){
			for(int b=0; b<mapArray[i].length; b++){
				mapArray[i][b] = new MapUnit('0');
			}
		}
	}	
	
	public String toString(){
		String sToReturn="";
		if(this.mapArray.equals(null))
			return "ERROR"; //TODO: throw exception
		for(int m=0; m<this.mapArray.length; m++){
			for(int n=0; n<this.mapArray[m].length; n++){
				
				sToReturn += (this.mapArray[m][n]);
			}
			sToReturn += "\n";
		}
		return sToReturn;
	}
	public MapUnit[][] getMap(){return mapArray;}
	private void readFile(File file){
		try(Scanner s = new Scanner(file); Scanner sc = new Scanner(file)){

			size:{
				rowSize=s.next().length();
				columnSize++;//This is bc the readLine reads one column/row or whatever.
				while(s.hasNextLine()){
					s.next();
					columnSize++;
				}
			}
			if(columnSize == rowSize){
				this.mapSize = columnSize;
				mapArray = new MapUnit[mapSize][mapSize];
				int initMapUnit;
				while(sc.hasNextLine()){
					String line=sc.next();
					for(column=0; column<rowSize; column++){
						mapArray[row][column]=new MapUnit(line.charAt(column));
						if(checkRepresentationValidity(mapArray[row][column].getRep()))
							throw new UnknownSymbolInMapException(mapArray[row][column].getRep());
					}
					row++;
				}
			}
			else{
				throw new NonSquareMapException(rowSize, columnSize);
			}
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
		catch(UnknownSymbolInMapException exc){
			System.out.println(exc);
		}
	}
	private boolean checkRepresentationValidity(char toBeChecked){//Later add checking for only 0/1s and such.
		Set <Character> keys = gameRepresentations.keySet();
		for(char c: keys){
			if(toBeChecked!=c)
				return false; //CURRENTLY NOT WORKING, WILL COME BACK TO IT LATER TO FIX FOR CUSTOM MAPS
				//return true;
		}
		return false;
	}
	private void setGameRepresentations(char... representationValues){ //Sets game representation
		if(representationValues.length>0){ //Checking if it exists or not,
			gameRepresentations.put(representationValues[0], new Byte((byte)0));//Not if correct length, 
			gameRepresentations.put(representationValues[1], new Byte((byte)1));//since the constructor has a condition to make sure the length is correct
			gameRepresentations.put(representationValues[2], new Byte((byte)2));
			gameRepresentations.put(representationValues[3], new Byte((byte)-1));			
		}
		else{
			gameRepresentations.put('0', new Byte((byte)0));
			gameRepresentations.put('1', new Byte((byte)1));
			gameRepresentations.put('$', new Byte((byte)2));
			gameRepresentations.put('?', new Byte((byte)-1));
		}
	}
	public int getMapSize(){
		return this.mapSize;
	}
}