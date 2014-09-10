package FinalProject;

import java.io.*;
import java.util.Scanner;
public class Game {
	Scanner stdin = new Scanner(System.in);
	final String[] defaultText = {
		"Welcome to Binary Game!", 
		"Enter h for help!", 
		"Enter c to customize!",
		"Enter p to play!"
	};
	private Player currentPlayer;
	private Map playMap;
	Game(){
		playMap = new Map();
		currentPlayer = new Player(this.playMap);
		terminalMainMenu(defaultText);
	}
	public static void main(String args[]){
		Game currentGame = new Game();
	}
	private void play(){ //this is the default play
		playMap.update(currentPlayer.getX(), currentPlayer.getY(), (byte)2);
		while(currentPlayer.hasntWon()){
			System.out.print("\f");
			System.out.println(playMap);
			if(move(stdin.next().charAt(0))){
				playMap.update(currentPlayer.getX(), currentPlayer.getY(), (byte)2);
			}
		}
	}
	private boolean move(char direction){
		switch(direction){
			case 'w' : if(currentPlayer.move(0, -1)) return true; break; //UGLY and not sure about directions
			case 'a' : if(currentPlayer.move(-1, 0)) return true; break; 
			case 's' : if(currentPlayer.move(0, 1)) return true; break;
			case 'd' : if(currentPlayer.move(1, 0)) return true; break;
			default : return false; //Not needed for now, it's just nice to have confirmation of correct input or not.
		}
		return false; // if move is unsuccessful
	}
	private void showHelp(){
		System.out.print("\f");
		System.out.println("Your goal is to move your player from the top left corner of the map to the bottom right.");
		System.out.println("Your character can only move on the 0s.");
		System.out.println("If you walk into a ?, then all the 0s will flip to 1s...");
		System.out.println("And all the 1s will flip to 0s.");
		System.out.println("Move around by entering w, a, s, or d and then pressing enter or return.");
		System.out.println("Got it? Press enter to return to the main menu.");
		stdin.nextLine();
	}
	private void terminalMainMenu(String[] msgs){
		boolean flag = false;
		System.out.println(terminalMainMenuGraphic(msgs)+"\n");
		do{
			flag = false;
			switch(stdin.next().charAt(0)){
				case 'h':
					showHelp();
				break;
				case 'c': //Do this later
					
				break;
				case 'p':
					play();
				break;
				default:
					System.out.println(terminalMainMenuGraphic(msgs)+"\n");
					flag = true;
				break;
			}
		} while(flag);
	}
	private String terminalMainMenuGraphic(String[] msgs){
		String graphic = "\f******************************\n"; //30 stars
		graphic += terminalMMGLine(5);
		graphic += terminalMMGLine(4, msgs); //Change later
		graphic += terminalMMGLine(5);	
		graphic += "******************************";
		return graphic;
	}
	private String terminalMMGLine(int lines){
		String toReturn = "";
		for(int b=0; b<lines; b++){
			toReturn += "*";
			for(int i=0; i<28; i++)
				toReturn += " ";
			toReturn += "*";
			toReturn+="\n";
		}
		return toReturn;
	}
	private String terminalMMGLine(int lines, String[] msgs){ //msgs length must = lines
		if(msgs.length!=lines){
			//Don't do this pls
		}
		
		String toReturn = "";
		for(int b=0; b<lines; b++){
			int ct = 1;
			toReturn += "*";
			for(int i=0; i<13-msgs[b].length()/2; i++){
				toReturn += " ";
				ct++;
			}
			toReturn += msgs[b];
			ct += msgs[b].length();
			for(int x=ct; x<=28; x++)
				toReturn += " ";
			toReturn += "*";
			toReturn+="\n";
		}
		return toReturn;
	}
}
