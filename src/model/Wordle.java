package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

/**
 * The main game object for this entire project, represents the current game
 * state of Wordle. The class allows the user to amke guesses, log in, play with
 * a random word, and informs them how they got their guess wrong. Only allows
 * one daily wordle game a day epr user, though.
 *
 * @author Jackson Burns, Duke Speed, Jose Juan Velasquez
 */
public class Wordle {
	private String dailyWord;
	private LocalDate currentDate;
	private final ArrayList<String> list;
	private String guessedWord;
	private String randomWord;
	private String toBeGuessed;
	private boolean dailyChoice;
	WordleSerializer ws;

	public Wordle(boolean daily) {
		this(daily, LocalDate.MIN);
	}

	public Wordle(boolean daily, LocalDate dateLastPlayed) {

		ws = new WordleSerializer();
		list = ws.getList();
		dailyWord = randomWord(true);
		setRandomWord(5);
		dailyChoice = daily;
		currentDate = dateLastPlayed;

	}

	/**
	 * this function takes a word, and tests to see if the arraylist contains the
	 * word, returning a boolean if it does
	 * 
	 * @param word - a string of the word to check
	 * @return - boolean if the word is a valid word
	 */
	public boolean isWord(String word) {
		return list.contains(word);
	}

	/**
	 * this method takes a username and password, checks to see if they are in the
	 * serialized accounts arraylist, and returns the WorleAccount object if it is.
	 * if not, it returns null
	 * 
	 * @param username - String of the username
	 * @param password - String of the password
	 * @return - WordleAccount object of the user requested
	 */
	public WordleAccount login(String username, String password) {
		for (WordleAccount account : ws.getAccounts()) {
			if (account.getUsername().equals(username) && account.getPassword().equals(password)) {
				return account;
			}
		}
		return null;
	}

	/**
	 * this function creates an account and serializes it in the ArrayList of
	 * WordleAccounts
	 * 
	 * @param username - String of the username of the account
	 * @param password - String of the password of the account
	 * @return - The WordleAccount object created. if it is created already, it will
	 *         return the existing account
	 */
	public boolean createAccount(String username, String password) {
		return ws.createNewUser(username, password);
	}

	/**
	 * This method is called when saving a new score. It updates the WordleAccount
	 * object to be serialized with the new modified object
	 * 
	 * @param account - WordleAccount to be changed
	 */
	public void updateAccount(WordleAccount account) {
		ws.update(account);
	}

	/**
	 * this function gets the word, and takes a parameter if it wants the daily
	 * word. it will return the daily word if the day is different than the stored
	 * date
	 * 
	 * @param daily returns the dailyWord if true, random otherwise.
	 * @return the correct guess
	 */
	public String getWord(boolean daily) {
		boolean isDifferentDay = !(currentDate.equals(LocalDate.now()));
		if (daily && isDifferentDay) {
			return dailyWord;
		} else {
			return randomWord;
		}
	}

	/**
	 * this function calls the saveAccounts function from the WordleSerializer,
	 * which serializes the ArrayList of WordleAccounts
	 */
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
	public int[] guess(String word) {

		int[] checkedChars = new int[word.length()];

		final int WRONG = 0;
		final int CORRECT = 1;
		final int CONTAINS = 2;

		if (dailyChoice) {
			toBeGuessed = dailyWord;
		} else {
			toBeGuessed = randomWord;
		}
		guessedWord = word;
		String remainingLetters = toBeGuessed;
		String ch = "_";
		for (int index = 0; index < word.length(); index++) {
			char guessChar = word.charAt(index);
			char correctChar = toBeGuessed.charAt(index);
			if (guessChar == correctChar) {
				checkedChars[index] = CORRECT;
				remainingLetters = remainingLetters.replaceFirst(guessChar + "", ch);
			} else {
				checkedChars[index] = WRONG;
			}
		}
		for (int index = 0; index < 5; index++) {
			char guessChar = word.charAt(index);
			char correctChar = toBeGuessed.charAt(index);
			if ((remainingLetters.contains(guessChar + "") && guessChar != correctChar)) {
				remainingLetters = remainingLetters.replaceFirst(guessChar + "", ch);
				checkedChars[index] = CONTAINS;
			}
		}
		return checkedChars;
	}

	/**
	 * Picks a word from the Wordle dictionary at random, making sure it is the
	 * correct length.
	 * 
	 * @param daily bases the word on a date-based seed if true
	 * @return a random string from the Wordle dictionary
	 */
	private String randomWord(boolean daily) {
		Random rand;
		if (daily) {
			rand = new Random(LocalDate.now().hashCode());
		} else {
			rand = new Random();
		}
		int len = list.size();
		int randomIndex = rand.nextInt(len + 1);
		return list.get(randomIndex);
	}

	/**
	 * Generates a random word as long as length.
	 * 
	 * @param length how long the random word should be
	 */
	public void setRandomWord(int length) {
		randomWord = randomWord(false);
	}

	/**
	 * The daily word is generated by generating from a seed based of the current
	 * day.
	 * 
	 * @return the current daily word
	 */
	public String getDailyWord() {
		return dailyWord;
	}

	/**
	 * Converts the current guess into string, denoting which chars are wrong.
	 * 
	 * @param checkedChars array of 0,1,2 denoting if the character is
	 *                     wrong,correct, or in the wrong index
	 * @return a string telling the user which chars are wrong/right/in the wrong
	 *         place
	 */
	public String guessToString(int[] checkedChars) {
		String guess = "";
		for (int i = 0; i < checkedChars.length; i++) {
			if (checkedChars[i] == 1) {
				guess += guessedWord.charAt(i) + "(Green)";
			} else if (checkedChars[i] == 2) {
				guess += guessedWord.charAt(i) + "(Yellow)";
			} else if (checkedChars[i] == 0) {
				guess += guessedWord.charAt(i) + "(Gray)";
			}
			guess += " ";
		}
		return guess;
	}

	/**
	 * Hard codes a daily word for testing purposes
	 * 
	 * @param the word we want to set as the Daily Word
	 * @return the modified daily word
	 */
	public void setDailyWord(String test) {
		dailyWord = test;
	}

	/**
	 * Gets the array containing all the accounts
	 * 
	 * @return an ArrayList containing all the registered accounts
	 */
	public ArrayList<WordleAccount> getAccounts() {
		return ws.getAccounts();
	}
}
