package model;

import java.io.Serializable;

/**
 * This class represents a Wordle account, which stores a username, password,
 * and score. It can retreive all three of these values, and update the score.
 * 
 * @author Jackson Burns, Amon Guinan
 */
@SuppressWarnings("serial")
public class WordleAccount implements Serializable{
	private final String username;
	private final String password;
	private int[] score;

	public WordleAccount(String username, String password) {
		this.username = username;
		this.password = password;
		score = new int[7];
		for (int i = 0; i < score.length; i++) {
			score[i] = 0;
		}
	}

	/**
	 * This method returns the username of the account
	 * 
	 * @return - the username as a String of the account
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * This method returns the password of the account
	 * 
	 * @return - password as a String of the account
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * This method updates the score of the account by incrementing the index of the
	 * score array
	 * 
	 * @param score - the int index of the score to update
	 */
	public void updateScore(int score) {
		this.score[score - 1]++;
	}

	/**
	 * This method returns the score of the account
	 * 
	 * @return - a int array of the score of the account, where each index is the
	 *         number of guesses it took the user to guess a word
	 */
	public int[] getScore() {
		return score;
	}

	public String getScoreString() {
		String s = "";
		s+= "Total Games: " + getTotalGames()+'\n';
		for (int i = 0; i < score.length; i++) {
			if (i == 6) {
				s += "more than 6: " + score[i] + " (" + percent(i) + "%)" + '\n';
			} else {
				s += (i + 1) + ": " + score[i] + " (" + percent(i) + "%)" + '\n';
			}
		}
		return s;
	}

	/**
	 * This method returns the average number of guesses it took the user to guess a word
	 * @return - a float of the average guesses per word
	 */
	public float getAverage() {
		float total = 0;
		for (int i = 0; i < score.length; i++) {
			total += score[i] * (i + 1);
		}
		return total / getTotalGames();
	}

	/**
	 * this method returns the percent of games that took i guesses
	 * @param i - the number of guesses
	 * @return - the percent of games that took i guesses
	 */
	public int percent(int i) {
		return (int) ((double) score[i] / getTotalGames() * 100);
	}

	public int getTotalGames() {
		int total = 0;
		for (int i = 0; i < score.length; i++) {
			total += score[i];
		}
		return total;
	}

//
//	/**
//	 *
//	 * @return
//	 */
//	private int totalGuesses() {
//		int retVal = 0;
//		for(int i = 0; i < score.length; i++) {
//			retVal += score[i];
//		}
//		return retVal;
//	}
//	public int totalScore() {
//		return totalGuesses() / getTotalGames();
//	}
//
//
//	/**
//	 * 	Assumes score is from a game with a 5 letter word.
//	 * 	Need to record word length and implement general scoring function
//	 * 	in order to fairly compare scores from different word length games.
//	 * @param other the object to be compared.
//	 * @return
//	 */
//	@Override
//	public int compareTo(WordleAccount other) {
//		//>0 if this is larger than other.
//		//0 if equal
//		//<0 if other larger.
//		return this.totalScore() - other.totalScore();
//	}
}
