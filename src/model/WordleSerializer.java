package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Wordle serializer stores the words arraylist and the accounts arraylist in a
 * .ser file. It also loads the words and accounts from the .ser file if it
 * exists. Finally, it can verify if a user exists, and it can add a new user to
 * the database
 *
 * @author Jackson Burns
 */
public class WordleSerializer {
	private final String wordsDatabasePath = "database.ser";
	private final String accountsDatabasePath = "accounts.ser";
	private ArrayList<String> words;
	private ArrayList<WordleAccount> accounts;

	/**
	 * this constructor creates the words arraylist, and the accounts arraylist,
	 * then it loads the databases. if they dont exist, it creates them.
	 */
	@SuppressWarnings("unchecked")
	public WordleSerializer() {

		words = new ArrayList<>();
		accounts = new ArrayList<>();
		// load or create the words dataabase
		try {
			FileInputStream rawBytes = new FileInputStream(wordsDatabasePath); // Read the .ser file just created
			ObjectInputStream inFile = new ObjectInputStream(rawBytes);
			// words = (ArrayList<String>[]) inFile.readObject();
			words = (ArrayList<String>) inFile.readObject();
			inFile.close();
			System.out.println("Loaded words");
		} catch (IOException | ClassNotFoundException e) {
			loadList();
			saveList();
			System.out.println("words not found, creating new database");
		}
		// load or create the accounts database
		try {
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

	/**
	 * This method returns an ArrayList of wordle accounts
	 * 
	 * @return - the arraylist of wordle accounts
	 */
	public ArrayList<WordleAccount> getAccounts() {
		return accounts;
	}

	/**
	 * This method saves the accounts arraylist to the accounts.ser file
	 */
	public void saveAccounts() {
		try {
			FileOutputStream bytesToDisk = new FileOutputStream(accountsDatabasePath);
			ObjectOutputStream outFile = new ObjectOutputStream(bytesToDisk);
			outFile.writeObject(accounts);
			outFile.close(); // Always close the output file!
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	/**
	 * This method saves the words arraylist to the database.ser file
	 */
	private void saveList() {
		try {
			FileOutputStream bytesToDisk = new FileOutputStream(wordsDatabasePath);
			ObjectOutputStream outFile = new ObjectOutputStream(bytesToDisk);
			outFile.writeObject(words);
			outFile.close(); // Always close the output file!
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	/**
	 * This method loads the words from the WordleWords.txt file into the words
	 * arraylist
	 */
	private void loadList() {

		Scanner s = null;
		try {
			s = new Scanner(new File("WordleWords.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// int largest = 0;
		while (s.hasNext()) {
			String word = s.next().strip();
			if (word.length() == 5) {
				words.add(word);
			}
		}
	}

	/**
	 * This method verifies if a user exists in the accounts arraylist. if they do,
	 * it returns the account. otherwise, it returns null
	 * 
	 * @param userName - the username to verify
	 * @param password - the password to verify
	 * @return - the account if it exists, null otherwise
	 */
	public WordleAccount verifyLogin(String userName, String password) {
		if (userName.length() != 0 && password.length() != 0) {
			for (WordleAccount acct : accounts) {
				if (acct.getUsername().equals(userName.trim()) && acct.getPassword().equals(password.trim())) {
					return acct;
				}
			}
		}
		return null;
	}

	/**
	 * This method updates the account in the accounts arraylist, if the score
	 * changes
	 * 
	 * @param acct - the account to update
	 */
	public void update(WordleAccount acct) {
		for (int i = 0; i < accounts.size(); i++) {
			if (accounts.get(i).getUsername().equals(acct.getUsername())) {
				accounts.set(i, acct);
			}
		}

	}

	/**
	 * This method creates a new user in the accounts arraylist, if it doesnt exist
	 * 
	 * @param newUser - the username of the new user
	 * @param newPwd  - the password of the new user
	 * @return - true if the user was created, false if the user already exists
	 */
	public boolean createNewUser(String newUser, String newPwd) {
		boolean containsAcct = false;
		for (WordleAccount acct : accounts) {
			if (acct.getUsername().equals(newUser)) {
				containsAcct = true;
			}
		}
		if (!containsAcct) {
			accounts.add(new WordleAccount(newUser, newPwd));
		}
		return containsAcct;
	}

	public ArrayList<String> getList() {
		return words;
	}

}
