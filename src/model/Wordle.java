package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;

public class Wordle {
	private String dailyWord;
	private final HashMap<Integer, ArrayList<String>> map;
	private String guessedWord;

	public Wordle() {
		WordleSerializer ws = new WordleSerializer();
		map = ws.getMap();
		dailyWord = randomWord(5, true);
	}

	/**
	 * This function compares the word passed into to the dailyWord variable, set in
	 * the constructor, and returns a map of character indices for keys, and then
	 * WRONG (0), CORRECT (1) and CONTAINS (2) as the potential values.
	 * 
	 * @param word The word to check
	 * @return A map with indices for keys, 0 for wrong, 1 for correct position, 2
	 *         for in the string, wrong position.
	 */
	public HashMap<Integer, Integer> guess(String word) {
		// Completed toString method and can print an indication of the tiles
		// Also now it checks for repeated characters
		guessedWord = word;
		HashMap<Integer, Integer> checkedChars = new HashMap<>();
		final int WRONG = 0;
		final int CORRECT = 1;
		final int CONTAINS = 2;

		for (int index = 0; index < word.length(); index++) {
			char guessChar = word.charAt(index);
			char correctChar = dailyWord.charAt(index);
			if (guessChar == correctChar) {
				checkedChars.put(index, CORRECT);
			} else if (dailyWord.indexOf(guessChar) != -1) {
				boolean verifyRepeat = checkForRepeats(word, guessChar);
				if (!verifyRepeat) {
					checkedChars.put(index, CONTAINS);
				} else {
					checkedChars.put(index, WRONG);
				}
			} else {
				checkedChars.put(index, WRONG);
			}

		}
		return checkedChars;
	}

	private boolean checkForRepeats(String word, char guessChar) {
		int verifyRepeatGuess = 0;
		int verifyRepeatDailyWord = 0;
		for (int character = 0; character < word.length(); character++) {
			if (word.charAt(character) == guessChar) {
				verifyRepeatGuess++;
			}
			if (dailyWord.charAt(character) == guessChar) {
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

	public String getDailyWord() {
		return dailyWord;
	}

	public String guessToString(HashMap<Integer, Integer> checkedChars) {
		String guess = "";
		for (Entry<Integer, Integer> entry : checkedChars.entrySet()) {
			if (entry.getValue() == 1) {
				guess += guessedWord.charAt(entry.getKey()) + "(Green)";
			} else if (entry.getValue() == 2) {
				guess += guessedWord.charAt(entry.getKey()) + "(Yellow)";
			} else if (entry.getValue() == 0) {
				guess += guessedWord.charAt(entry.getKey()) + "(Gray)";
			}
			guess += " ";
		}
		return guess;
	}

	//Only used for testing
	public void setDailyWord(String test) {
		dailyWord = test;
	}

}
