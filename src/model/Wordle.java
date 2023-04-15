package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Wordle {
	private String dailyWord;
	private boolean loggedIn;
	private final HashMap<Integer, ArrayList<String>> map;
	private String guessedWord;
	private String randomWord;
	WordleSerializer ws;

	public Wordle() {
		ws = new WordleSerializer();
		map = ws.getMap();
		dailyWord = randomWord(5, true);
		setRandomWord(5);
	}

	public WordleAccount login(String username, String password) {
		for (WordleAccount account : ws.getAccounts()) {
			if (account.getUsername().equals(username) && account.getPassword().equals(password)) {
				//loggedIn=true;
				return account;
			}
		}
		return null;
	}

	public boolean isLoggedIn(){
		return loggedIn;
	}
	public boolean createAccount(String username, String password) {
		return ws.createNewUser(username, password);

	}

	public void updateAccount(WordleAccount account) {
		ws.update(account);
	}

	public String getWord(boolean daily) {
		if (daily) {
			return dailyWord;
		} else {
			return randomWord;
		}
	}

	public void save() {
		ws.saveAccounts();
	}

	/**
	 * This function compares the word passed into to the dailyWord variable, set in
	 * the constructor, and returns an array and then WRONG (0), CORRECT (1) and
	 * CONTAINS (2) as the potential values.
	 * 
	 * @param word The word to check
	 * @return An array with index corresponding to word 0 for wrong, 1 for correct
	 *         position, 2 for in the string, wrong position.
	 */
	public int[] guess(String word, boolean daily) {
		// Completed toString method and can print an indication of the tiles
		// Also now it checks for repeated characters
		String toBeGuessed;
		if (daily) {
			toBeGuessed = dailyWord;
		} else {
			toBeGuessed = randomWord;
		}
		guessedWord = word;
		int[] checkedChars = new int[word.length()];
		final int WRONG = 0;
		final int CORRECT = 1;
		final int CONTAINS = 2;

		for (int index = 0; index < word.length(); index++) {
			char guessChar = word.charAt(index);
			char correctChar = toBeGuessed.charAt(index);
			if (guessChar == correctChar) {
				checkedChars[index] = CORRECT;
			} else if (toBeGuessed.indexOf(guessChar) != -1) {
				boolean verifyRepeat = checkForRepeats(word, guessChar, toBeGuessed);
				if (!verifyRepeat) {
					checkedChars[index] = CONTAINS;
				} else {
					checkedChars[index] = WRONG;
				}
			} else {
				checkedChars[index] = WRONG;
			}

		}
		return checkedChars;
	}

	private boolean checkForRepeats(String word, char guessChar, String toBeGuessed) {
		int verifyRepeatGuess = 0;
		int verifyRepeatDailyWord = 0;
		for (int character = 0; character < word.length(); character++) {
			if (word.charAt(character) == guessChar) {
				verifyRepeatGuess++;
			}
			if (toBeGuessed.charAt(character) == guessChar) {
				verifyRepeatDailyWord++;
			}
		}
		if (verifyRepeatGuess == verifyRepeatDailyWord) {
			return false;
		}
		return true;

	}

	private String randomWord(int length, boolean daily) {
		Random rand;
		if (daily) {
			rand = new Random(LocalDate.now().hashCode());
		} else {
			rand = new Random();
		}
		int len = map.get(length).size();
		int randomIndex = rand.nextInt(len + 1);
		return map.get(length).get(randomIndex);
	}

	public void setRandomWord(int length) {
		randomWord = randomWord(length, false);
	}

	public String getDailyWord() {
		return dailyWord;
	}

	public String guessToString(int[] checkedChars) {
		String guess = "";
		for (int i = 0; i < checkedChars.length; i++) {
			if (checkedChars[i] == 1) {
				guess += guessedWord.charAt(i);// + "(Green)";
			} else if (checkedChars[i] == 2) {
				guess += guessedWord.charAt(i);// + "(Yellow)";
			} else if (checkedChars[i] == 0) {
				guess += guessedWord.charAt(i);// + "(Gray)";
			}
			guess += " ";
		}
		return guess;
	}

	// Only used for testing
	public void setDailyWord(String test) {
		dailyWord = test;
	}

}
