package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Wordle {
    private final String dailyWord;
    private final HashMap<Integer, ArrayList<String>> map;

    public Wordle(){
        WordleSerializer ws = new WordleSerializer();
        map = ws.getMap();
        dailyWord = randomWord(5,true);



    }



    public String guess(String word){
        //Todo guess the word, return a string with a space for wrong characters, underscore for wrong location?
        // compare to original string??
        return null;
    }




    private String randomWord(int length, boolean daily){
        Random rand;
        if (daily) {
            rand = new Random(LocalDate.now().hashCode());
        }
        else{
            rand = new Random();
        }
        int len = map.get(length).size();
        int randomIndex = rand.nextInt(len+1);
        return map.get(length).get(randomIndex);
    }

    public String getDailyWord(){
        return dailyWord;
    }



}
