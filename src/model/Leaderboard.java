/* file: Leaderboard.java
 * author: Amon Guinan
 * 
 * This file implements the Leaderboard object,
 * an object that stores WordleAccount objects in a sorted arrayList,
 * in order 
 */

package model;

import java.util.ArrayList;

public class Leaderboard {
	private ArrayList<WordleAccount> contents;
	public Leaderboard() {
		contents = new ArrayList<WordleAccount>();
	}
	
	public Leaderboard(ArrayList<WordleAccount> accounts) {
		//contents = (ArrayList<WordleAccount>) accounts.clone();
		//likely not necessary...
		contents = accounts;
		contents.sort(null); 
		//vanilla ArrayList uses a version of mergesort.
	}
	
	public void addUser(WordleAccount user) {
		contents.add(user);
		contents.sort(null);
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<WordleAccount> getUsers() {
		return (ArrayList<WordleAccount>) contents.clone();
	}
}
