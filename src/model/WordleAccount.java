package model;

import java.io.Serializable;

/**
 * This class represents a Wordle account, which stores a username, password, and score.
 * It can retreive all three of these values, and update the score.
 * @author Jackson Burns
 */
public class WordleAccount implements Serializable {
    private final String username;
    private final String password;
    private int[]score;

    public WordleAccount(String username, String password) {
        this.username = username;
        this.password = password;
        score = new int[6];
        for(int i=0;i<score.length;i++){
            score[i]=0;
        }
    }

    /**
     * This method returns the username of the account
     * @return - the username as a String of the account
     */
    public String getUsername(){
        return username;
    }

    /**
     * This method returns the password of the account
     * @return -  password as a String of the account
     */
    public String getPassword(){
        return password;
    }

    /**
     * This method updates the score of the account by incrementing the index of the score array
     * @param score - the int index of the score to update
     */
    public void updateScore(int score){
        this.score[score-1]++;
    }

    /**
     * This method returns the score of the account
     * @return - a int array of the score of the account, where each index is the number of
     * guesses it took the user to guess a word
     */
    public int[] getScore() {
        return score;
    }
}
