package FinalProject;

import java.io.*;
public class testMap{
	public static void main(String args[]){
		final String defaultFilePath = "/Users/admin/code/JavaExercises/FinalProject/allZero.biMap.txt";
		final File defaultMap = new File(defaultFilePath);
		Map m1 = new Map(3);
		System.out.println(m1);
		Map mF = new Map(defaultMap);
		System.out.println(mF);

	}
}