/* file: Leaderboard.java
 * author: Amon Guinan
 * 
 * This file implements the Leaderboard object,
 * an object that stores WordleAccount objects in a sorted arrayList,
 * in order of score.
 */

package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * 
 * @author Amon Guinan The Leaderboard class implements an object capable of
 *         storing WordleAccount objects using a sorted ArrayList.
 */
public class Leaderboard {

	private ArrayList<WordleAccount> contents;

	/**
	 * Initializes the an empty arraylist that contains WordleAccounts.
	 */
	public Leaderboard() {
		contents = new ArrayList<WordleAccount>();
	}

	/**
	 * @param accounts Like above constructor, but also accepts input ArrayList for
	 *                 contents.
	 */
	public Leaderboard(ArrayList<WordleAccount> accounts) {
		contents = accounts;
		contents.sort(Collections.reverseOrder());
		// vanilla ArrayList uses a version of mergesort.
	}

	/**
	 * 
	 * @param user Adds a WordleAccount to the arraylist.
	 */
	public void addUser(WordleAccount user) {
		for (WordleAccount u : contents) {
			if (u.getUsername().equals(user.getUsername())) {
				contents.remove(u);
				contents.add(user);
				return;
			}
		}
		contents.add(user);
		contents.sort(Collections.reverseOrder());
	}

	/**
	 * 
	 * @return a shallow copy of the arraylist that contains the user accounts.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<WordleAccount> getUsers() {
		return (ArrayList<WordleAccount>) contents.clone();
	}

	/**
	 * @return a String representation of the contents of the leaderboard.
	 */
	public String toString() {
		String out = "";
		for (WordleAccount u : contents) {
			out += u.getTotalGames() + ": " + u.getUsername() + "\n";
		}
		return out;
	}
}
