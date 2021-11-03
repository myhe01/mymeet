/*
 * This class converts interests from either an ArrayList of Strings to an integer that
 * is stored in the database, or from an integer stored in the database to an ArrayList
 * of Strings
 */

import java.util.ArrayList;

public class Interests {
	//Examples of the two methods contained bellow are the strings test1, test2, test3, test4 etc that will be replaced with actual interests
	
	public int stringToInt(ArrayList<String> interestString) {
		int interestInt = 1;
		for (int i = 0; i < interestString.size(); i++) {
			if (interestString.get(i).equals("test1")) {
				interestInt*=2;
			}
		}
		for (int i = 0; i < interestString.size(); i++) {
			if (interestString.get(i).equals("test2")) {
				interestInt*=3;
			}
		}
		for (int i = 0; i < interestString.size(); i++) {
			if (interestString.get(i).equals("test3")) {
				interestInt*=5;
			}
		}
		for (int i = 0; i < interestString.size(); i++) {
			if (interestString.get(i).equals("test4")) {
				interestInt*=7;
			}
		}
		//TODO May expand this with as many strings as necessary as long as you use prime numbers
		return interestInt;
	}
	public ArrayList<String> intToString(int interestInt){
		ArrayList<String> interestString = new ArrayList<String>();
		if (interestInt % 2 == 0) {
			interestString.add("test1");
		}
		if (interestInt % 3 == 0) {
			interestString.add("test2");
		}
		if (interestInt % 5 == 0) {
			interestString.add("test3");
		}
		if (interestInt % 7 == 0) {
			interestString.add("test4");
		}
		//TODO must expand here similar to the previous method to keep internal consistancy
		return interestString;
	}
}