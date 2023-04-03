package model;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class WordleSerializer {
    private final String wordsPath = "WordleWords.txt";
    private final String serializedPath = "database.ser";
    private ArrayList<ArrayList<String>> words;
    public WordleSerializer(){
        words = new ArrayList<ArrayList<String>>();
        //TODO: load words from file
        try{
            FileInputStream rawBytes = new FileInputStream(serializedPath); // Read the .ser file just created
            ObjectInputStream inFile = new ObjectInputStream(rawBytes);
            words = (ArrayList<ArrayList<String>>) inFile.readObject();
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
        while(s.hasNext()){
            String word = s.next().strip();

        }

    }

    public String pickDailyWord(){
        return null;
    }


}
