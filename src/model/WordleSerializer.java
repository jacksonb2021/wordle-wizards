package model;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class WordleSerializer {
    private final String wordsPath = "WordleWords.txt";
    private final String serializedPath = "database.ser";
    //private ArrayList<String>[] words;
    private HashMap<Integer, ArrayList<String>> words;
    public WordleSerializer(){
        //words = new ArrayList<ArrayList<String>>();
        //words = new ArrayList[16];
        words = new HashMap<Integer, ArrayList<String>>();

        //TODO: load words from file
        try{
            FileInputStream rawBytes = new FileInputStream(serializedPath); // Read the .ser file just created
            ObjectInputStream inFile = new ObjectInputStream(rawBytes);
            //words = (ArrayList<String>[]) inFile.readObject();
            words = (HashMap<Integer, ArrayList<String>>) inFile.readObject();
            inFile.close();
        } catch (IOException | ClassNotFoundException e) {
            load();
            save();
        }
    }


    public void save(){

    }

    public void load(){

        Scanner s=null;
        try {
            s = new Scanner(new File(wordsPath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //int largest = 0;
        while(s.hasNext()){
            String word = s.next().strip();
            if(words.containsKey(word.length())){
                words.get(word.length()).add(word);
            } else {
                ArrayList<String> temp = new ArrayList<String>();
                temp.add(word);
                words.put(word.length(), temp);
            }
            //words[word.length()-1].add(word);
            //largest = Math.max(largest, word.length());
            //words.get()

        }
        System.out.println(words);

        //System.out.println(largest);

    }

    public String pickDailyWord(){
        return null;
    }


}
