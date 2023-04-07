package model;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class WordleSerializer {
    private final String wordsPath = "WordleWords.txt";
    private final String wordsDatabasePath = "database.ser";
    private final String accountsDatabasePath = "accounts.ser";
    private HashMap<Integer, ArrayList<String>> words;
    private ArrayList<WordleAccount> accounts;

    public WordleSerializer(){

        words = new HashMap<>();
        accounts = new ArrayList<>();

        try{
            FileInputStream rawBytes = new FileInputStream(wordsDatabasePath); // Read the .ser file just created
            ObjectInputStream inFile = new ObjectInputStream(rawBytes);
            //words = (ArrayList<String>[]) inFile.readObject();
            words = (HashMap<Integer, ArrayList<String>>) inFile.readObject();
            inFile.close();
            System.out.println("Loaded words");
        } catch (IOException | ClassNotFoundException e) {
            loadMap();
            saveMap();
            System.out.println("words not found, creating new database");
        }
        try{
            FileInputStream rawBytes = new FileInputStream(accountsDatabasePath); // Read the .ser file just created
            ObjectInputStream inFile = new ObjectInputStream(rawBytes);
            accounts = (ArrayList<WordleAccount>) inFile.readObject();
            inFile.close();
            System.out.println("Loaded accounts");
        } catch (IOException | ClassNotFoundException e) {

            saveAccounts();
            System.out.println("accounts not found, creating new database");
        }

    }

    public ArrayList<WordleAccount> getAccounts(){
        return accounts;
    }

    private void saveAccounts(){
        try {
            FileOutputStream bytesToDisk = new FileOutputStream(accountsDatabasePath);
            ObjectOutputStream outFile = new ObjectOutputStream(bytesToDisk);
            outFile.writeObject(accounts);
            outFile.close(); // Always close the output file!
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }
    private void saveMap(){
        try {
            FileOutputStream bytesToDisk = new FileOutputStream(wordsDatabasePath);
            ObjectOutputStream outFile = new ObjectOutputStream(bytesToDisk);
            outFile.writeObject(words);
            outFile.close(); // Always close the output file!
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

    private void loadMap(){

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


        }


    }


    public HashMap<Integer,ArrayList<String>> getMap(){
        return words;
    }


    public WordleAccount verifyLogin(String userName, String password) {
        if (userName.length() != 0 && password.length() != 0) {
            for (WordleAccount acct : accounts) {
                if (acct.getUsername().equals(userName.trim()) && acct.getPassword().equals(password.trim())) {
                    return acct;
                }
            }

            return null;
        }

        return null;
    }

    public void update(WordleAccount acct) {
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getUsername().equals(acct.getUsername())) {
                accounts.set(i, acct);
            }
        }

    }
    public boolean createNewUser(String newUser, String newPwd) {
        boolean containsAcct = false;
        for (WordleAccount acct : accounts) {
            if (acct.getUsername().equals(newUser)) {
                containsAcct = true;
            }
        }
        if (!containsAcct) {
            accounts.add(new WordleAccount(newUser, newPwd));

            return true;
        }

        return false;
    }


}
