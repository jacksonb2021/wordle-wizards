/*
 * WordleConsole.java
 * Author: Amon Guinan
 * 
 */

package view_controller;

import model.Wordle;
import model.WordleAccount;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * Command line interface for Wordle, allowing the user to play entirely based
 * on a keybaord. Contains the entire game loop of wordle, indicating where/when
 * guesses are correct or in the wrong place. Also allows the user to look back
 * on past Wordle scores.
 *
 * @author Amon Guinon, Jackson Burns, Duke Speed, Jose Juan Velasquez
 */
public class WordleConsole {

	public static void main(String[] args) {
		Wordle wordle = new Wordle(true);
		printWelcome();
		gameLoop(wordle);

	}

	/**
	 * Prints out a welcome message.
	 */
	private static void printWelcome() {
		System.out.println("Welcome to Wordle!");
		System.out.println("Guide:CORRECT: 1, INCORRECT: 0, CONTAINS: 2");

	}

	/**
	 * Provides the main game loop of the console GUI. Allows the user to login, and
	 * create accounts if needed. Asks the user for input, uses that input to change
	 * the game state represented in our Wordle object. Checks and sanitizes said
	 * inputs to prevent crashing/unintended bugs.
	 * 
	 * @param game the Wordle object to manipulate
	 */
	private static void gameLoop(Wordle game) {
		int counter = 0;
		String guess;
		int results[];
		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
		WordleAccount account = null;
		System.out.println("Login (l) or create account (c)");
		if (s.nextLine().strip().equals("l")) {
			System.out.println("Login:\n");
			System.out.println("Enter username:");
			String username = s.nextLine().strip();
			System.out.println("Enter password:");
			String password = s.nextLine().strip();
			account = game.login(username, password);
			if (account == null) {
				System.out.println("Invalid username or password, exiting");
				return;
			}
		} else {
			System.out.println("Create account:\n");
			System.out.println("Enter username:");
			String username = s.nextLine().strip();
			System.out.println("Enter password:");
			String password = s.nextLine().strip();
			game.createAccount(username, password);
			account = game.login(username, password);
			System.out.println("Account created");
			game.save();
		}
		System.out.println("Daily word (d) or random word (r)");
		String wordChoice = s.nextLine().strip();
		boolean isDaily;
		if (account.getLastPlayed().equals(LocalDate.now())) {
			System.out.println("You've already played one game of Wordle today, setting to practice.");
			isDaily = false;
		} else if (wordChoice.equals("r")) {
			isDaily = false;
		} else if (wordChoice.equals("d")) {
			isDaily = true;
		} else {
			System.out.println("Invalid input. Defaulting to daily word.");
			isDaily = true;
		}
		// while the guess contains wrong or out of place letters
		while (counter < 6) {
			// implement no-more-guesses loss condition here.

			System.out.println("Enter guess number " + (counter + 1) + ":, or 'q' to quit");
			guess = s.nextLine().strip();
			if (guess.equals("q")) {
				System.out.println("Game over.\n the word was " + game.getWord(isDaily) + "\n");
				break;
			}
			if (guess.length() != 5) {
				System.out.println("Guess must be 5 letters long. (longer words implemented in gui)");
				continue;
			}

			results = game.guess(guess);
			System.out.println(game.guessToString(results) + '\n');
			counter++;

			// if guess is correct
			boolean win = true;
			for (int i = 0; i < results.length; i++) {
				if (results[i] != 1) {
					win = false;
				}
			}
			account.setLastPlayed(LocalDate.now());
			if (win) {
				System.out.println("Game over. You win!");
				System.out.println("You guessed the word in " + counter + " guesses.");
				System.out.println("The word was " + game.getWord(isDaily));
				account.updateScore(counter);
				System.out.println(account.getScoreString());
				game.updateAccount(account);
				game.save();
				return;
			}
		}
		s.close();
		System.out.println("Game over. You lose.\nThe word was " + game.getWord(isDaily));
		account.updateScore(counter + 1);
		System.out.println(account.getScoreString());
		game.updateAccount(account);
		game.save();

	}

}
