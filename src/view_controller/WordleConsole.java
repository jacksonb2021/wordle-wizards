/*
 * WordleConsole.java
 * Author: Amon Guinan
 * 
 */

package view_controller;

import model.Wordle;

import java.util.Scanner;

public class WordleConsole {

	
    public static void main(String[] args) {
        Wordle wordle = new Wordle();
//        System.out.println("Play wordle, enter 'd' for daily word, or 'r' for random word");
//        Scanner s = new Scanner(System.in);
        
        //implement random word assignment.
    	//would probably need to modify Wordle class
    	//specifically, publicize getRandomWord() and setWord()
//        String word;
//        if(s.next().strip().equals("d")){
//        	
//        } else if (!s.next().strip().equals("d")){
        
//        }
        
        printWelcome();
        //wordle.setDailyWord("brick");
        gameLoop(wordle);
        
        
    }
    
    private static void printWelcome() {
    	System.out.println("Welcome to Wordle!");
        System.out.println("Guide:CORRECT: 1, INCORRECT: 0, CONTAINS: 2");
      
    }
    
    private static void gameLoop(Wordle game) {
    	String guess;
    	int results[];
    	Scanner s = new Scanner(System.in);
    	//while the guess contains wrong or out of place letters
    	while(true) {
    		//implement no-more-guesses loss condition here.
    		
    		System.out.println("Enter guess:");
    		guess = s.nextLine().strip();
    		results = game.guess(guess);
    		System.out.println(game.guessToString(results));
    		
    		//if guess is correct
    		if(!game.guessToString(results).matches(".*(Yellow|Gray).*")) { 
    			System.out.println("Game over. You win!");
    			break;
    		}
    	}
    }
    
}
