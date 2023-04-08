package view_controller;

import model.Wordle;

import java.util.Scanner;

public class WordleConsole {


    public static void main(String[] args) {
        Wordle wordle = new Wordle();
        System.out.println("Play wordle, enter 'd' for daily word, or 'r' for random word");
        Scanner s = new Scanner(System.in);
        if(s.next().strip().equals("d")){

        }
    }
    
    //test commit branch
}
