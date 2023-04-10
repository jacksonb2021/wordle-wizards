/*
 * WordleConsole.java
 * Author: Amon Guinan
 * 
 */

package view_controller;

import model.Wordle;
import model.WordleAccount;

import java.util.Scanner;

public class WordleConsole {

	public static void main(String[] args) {
		Wordle wordle = new Wordle();
//        System.out.println("Play wordle, enter 'd' for daily word, or 'r' for random word");
//        Scanner s = new Scanner(System.in);

		// implement random word assignment.
		// would probably need to modify Wordle class
		// specifically, publicize getRandomWord() and setWord()
//        String word;
//        if(s.next().strip().equals("d")){
//        	
//        } else if (!s.next().strip().equals("d")){

//        }

		printWelcome();
		// wordle.setDailyWord("brick");
		gameLoop(wordle);

	}

	private static void printWelcome() {
		System.out.println("Welcome to Wordle!");
		System.out.println("Guide:CORRECT: 1, INCORRECT: 0, CONTAINS: 2");

	}

	private static void gameLoop(Wordle game) {
		int counter = 0;
		String guess;
		int results[];
		Scanner s = new Scanner(System.in);
		WordleAccount account = null;
		System.out.println("Login (l) or create account (c)");
		if (s.nextLine().strip().equals("l")) {
			System.out.println("Login:");
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
			System.out.println("Create account:");
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
		if (wordChoice.equals("r")) {
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

			results = game.guess(guess, isDaily);
			System.out.println(game.guessToString(results) + '\n');
			counter++;

			// if guess is correct
			if (!game.guessToString(results).matches(".*(Yellow|Gray).*")) {
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
		System.out.println("Game over. You lose.\nThe word was " + game.getWord(isDaily));
		account.updateScore(counter + 1);
		System.out.println(account.getScoreString());
		game.updateAccount(account);
		game.save();

	}

}
