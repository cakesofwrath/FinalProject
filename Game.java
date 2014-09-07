package FinalProject;

import java.io.*;
import java.util.Scanner;
public class Game{
	Scanner stdin = new Scanner(System.in);
	final String[] defaultText = {
		"Welcome to Binary Game!", 
		"Enter h for help!", 
		"Enter c to customize!",
		"Enter p to play!"
	};
	Game(){
		terminalMainMenu(defaultText);
	}
	public static void main(String args[]){
		Game currentGame = new Game();
	}
	private void play(){ //this is the default play
		char input;
		System.out.print("\f");
		Map playMap = new Map();
		System.out.println(playMap);
		input=stdin.next().charAt(0);
	}
	private void terminalMainMenu(String[] msgs){
		boolean flag = false;
		System.out.println(terminalMainMenuGraphic(msgs)+"\n");
		do{
			flag = false;
			switch(stdin.next().charAt(0)){
				case 'h':
					play();
				break;
				case 'c':
					
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