package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import model.Leaderboard;
import model.Wordle;
import model.WordleAccount;
import model.WordleSerializer;

/**
 * This is a JUnit test case testing the model of Wordle, WordleAccount,
 * WordleSerializer and Leaderboard.
 * 
 * @author Jose Juan Velasquez
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WordleTest {

	/**
	 * can only run this test if the accounts.ser file is deleted. If you want the
	 * required code coverage percentage, delete all local .ser files and run with
	 * this testAccountCreator method uncommented
	 */
	@Test
	public void testAccountCreator() {
		WordleSerializer ws = new WordleSerializer();
		ws.createNewUser("jackson", "burns");
		ws.saveAccounts();
		ws.createNewUser("JJVH19", "nel");
		ws.saveAccounts();
	}

	@Test
	public void testAllCorrect() {
		Wordle wordleTest = new Wordle(true);
		wordleTest.setDailyWord("frank");
		System.out.println(wordleTest.getDailyWord() + " I ran first ");
		int[] checkedChars = wordleTest.guess("frank");
		int firstChar = checkedChars[0];
		assertEquals(firstChar, 1);
		int secondChar = checkedChars[1];
		assertEquals(secondChar, 1);
		int thirdChar = checkedChars[2];
		assertEquals(thirdChar, 1);
		int fourthChar = checkedChars[3];
		assertEquals(fourthChar, 1);
		int fifthChar = checkedChars[4];
		assertEquals(fifthChar, 1);

		System.out.println('\n' + wordleTest.guessToString(checkedChars) + '\n');
	}

	@Test
	public void testPartialCorrect() {
		Wordle wordleTest = new Wordle(true);
		// HashMap<Integer, Integer> checkedChars = new HashMap<>();
		wordleTest.setDailyWord("frank");
		System.out.println(wordleTest.getDailyWord() + " I ran second ");
		int[] checkedChars = wordleTest.guess("kranf");
		int firstChar = checkedChars[0];
		assertEquals(firstChar, 2);
		int secondChar = checkedChars[1];
		assertEquals(secondChar, 1);
		int thirdChar = checkedChars[2];
		assertEquals(thirdChar, 1);
		int fourthChar = checkedChars[3];
		assertEquals(fourthChar, 1);
		int fifthChar = checkedChars[4];
		assertEquals(fifthChar, 2);

		System.out.println('\n' + wordleTest.guessToString(checkedChars) + '\n');
	}

	@Test
	public void testPartialCorrect2() {
		Wordle wordleTest = new Wordle(true);
		// HashMap<Integer, Integer> checkedChars = new HashMap<>();
		wordleTest.setDailyWord("frank");
		System.out.println(wordleTest.getDailyWord() + " I ran third ");
		int[] checkedChars = wordleTest.guess("kfnar");
		int firstChar = checkedChars[0];
		assertEquals(firstChar, 2);
		int secondChar = checkedChars[1];
		assertEquals(secondChar, 2);
		int thirdChar = checkedChars[2];
		assertEquals(thirdChar, 2);
		int fourthChar = checkedChars[3];
		assertEquals(fourthChar, 2);
		int fifthChar = checkedChars[4];
		assertEquals(fifthChar, 2);
		System.out.println('\n' + wordleTest.guessToString(checkedChars) + '\n');
	}

	@Test
	public void testPartialIncorrect() {
		Wordle wordleTest = new Wordle(true);
		// HashMap<Integer, Integer> checkedChars = new HashMap<>();
		wordleTest.setDailyWord("frank");
		System.out.println(wordleTest.getDailyWord() + " I ran fourth ");
		int[] checkedChars = wordleTest.guess("crank");
		int firstChar = checkedChars[0];
		assertEquals(firstChar, 0);
		int secondChar = checkedChars[1];
		assertEquals(secondChar, 1);
		int thirdChar = checkedChars[2];
		assertEquals(thirdChar, 1);
		int fourthChar = checkedChars[3];
		assertEquals(fourthChar, 1);
		int fifthChar = checkedChars[4];
		assertEquals(fifthChar, 1);

		System.out.println('\n' + wordleTest.guessToString(checkedChars) + '\n');
	}

	@Test
	public void testPartialIncorrect2() {
		Wordle wordleTest = new Wordle(true);
		// HashMap<Integer, Integer> checkedChars = new HashMap<>();
		wordleTest.setDailyWord("frank");
		System.out.println(wordleTest.getDailyWord() + " I ran fifth ");
		int[] checkedChars = wordleTest.guess("cfenc");
		int firstChar = checkedChars[0];
		assertEquals(firstChar, 0);
		int secondChar = checkedChars[1];
		assertEquals(secondChar, 2);
		int thirdChar = checkedChars[2];
		assertEquals(thirdChar, 0);
		int fourthChar = checkedChars[3];
		assertEquals(fourthChar, 1);
		int fifthChar = checkedChars[4];
		assertEquals(fifthChar, 0);

		System.out.println('\n' + wordleTest.guessToString(checkedChars) + '\n');
	}

	@Test
	public void testAllIncorrect() {
		Wordle wordleTest = new Wordle(true);
		// HashMap<Integer, Integer> checkedChars = new HashMap<>();
		wordleTest.setDailyWord("frank");
		System.out.println(wordleTest.getDailyWord() + " I ran sixth ");
		int[] checkedChars = wordleTest.guess("uiope");
		int firstChar = checkedChars[0];
		assertEquals(firstChar, 0);
		int secondChar = checkedChars[1];
		assertEquals(secondChar, 0);
		int thirdChar = checkedChars[2];
		assertEquals(thirdChar, 0);
		int fourthChar = checkedChars[3];
		assertEquals(fourthChar, 0);
		int fifthChar = checkedChars[4];
		assertEquals(fifthChar, 0);

		System.out.println('\n' + wordleTest.guessToString(checkedChars) + '\n');
	}

	@Test
	public void testRepetitions2() {
		Wordle wordleTest = new Wordle(true);
		// HashMap<Integer, Integer> checkedChars = new HashMap<>();
		wordleTest.setDailyWord("krank");
		System.out.println(wordleTest.getDailyWord() + " I ran seventh ");
		int[] checkedChars = wordleTest.guess("krank");
		int firstChar = checkedChars[0];
		assertEquals(firstChar, 1);
		int secondChar = checkedChars[1];
		assertEquals(secondChar, 1);
		int thirdChar = checkedChars[2];
		assertEquals(thirdChar, 1);
		int fourthChar = checkedChars[3];
		assertEquals(fourthChar, 1);
		int fifthChar = checkedChars[4];
		assertEquals(fifthChar, 1);

		System.out.println('\n' + wordleTest.guessToString(checkedChars) + '\n');
	}

	@Test
	public void testRepetitions() {
		Wordle wordleTest = new Wordle(true);
		// HashMap<Integer, Integer> checkedChars = new HashMap<>();
		wordleTest.setDailyWord("frank");
		System.out.println(wordleTest.getDailyWord() + " I ran eighth");
		int[] checkedChars = wordleTest.guess("franf");
		int firstChar = checkedChars[0];
		assertEquals(firstChar, 1);
		int secondChar = checkedChars[1];
		assertEquals(secondChar, 1);
		int thirdChar = checkedChars[2];
		assertEquals(thirdChar, 1);
		int fourthChar = checkedChars[3];
		assertEquals(fourthChar, 1);
		int fifthChar = checkedChars[4];
		assertEquals(fifthChar, 0);

		System.out.println('\n' + wordleTest.guessToString(checkedChars) + '\n');
	}

	@Test
	public void testRepetitions3() {
		Wordle wordleTest = new Wordle(true);
		// HashMap<Integer, Integer> checkedChars = new HashMap<>();
		wordleTest.setDailyWord("ffass");
		System.out.println(wordleTest.getDailyWord() + " I ran ninth");
		int[] checkedChars = wordleTest.guess("ffass");
		int firstChar = checkedChars[0];
		assertEquals(firstChar, 1);
		int secondChar = checkedChars[1];
		assertEquals(secondChar, 1);
		int thirdChar = checkedChars[2];
		assertEquals(thirdChar, 1);
		int fourthChar = checkedChars[3];
		assertEquals(fourthChar, 1);
		int fifthChar = checkedChars[4];
		assertEquals(fifthChar, 1);

		System.out.println('\n' + wordleTest.guessToString(checkedChars) + '\n');
	}

	@Test
	public void testRepetitions4() {
		Wordle wordleTest = new Wordle(true);
		// HashMap<Integer, Integer> checkedChars = new HashMap<>();
		wordleTest.setDailyWord("ffaas");
		System.out.println(wordleTest.getDailyWord() + " I ran tenth");
		int[] checkedChars = wordleTest.guess("ffasa");
		int firstChar = checkedChars[0];
		assertEquals(firstChar, 1);
		int secondChar = checkedChars[1];
		assertEquals(secondChar, 1);
		int thirdChar = checkedChars[2];
		assertEquals(thirdChar, 1);
		int fourthChar = checkedChars[3];
		assertEquals(fourthChar, 2);
		int fifthChar = checkedChars[4];
		assertEquals(fifthChar, 2);

		System.out.println('\n' + wordleTest.guessToString(checkedChars) + '\n');
	}

	@Test
	public void testRepetitions5() {
		Wordle wordleTest = new Wordle(true);
		// HashMap<Integer, Integer> checkedChars = new HashMap<>();
		wordleTest.setDailyWord("fffff");
		System.out.println(wordleTest.getDailyWord() + " I ran eleventh");
		int[] checkedChars = wordleTest.guess("zfffz");
		int firstChar = checkedChars[0];
		assertEquals(firstChar, 0);
		int secondChar = checkedChars[1];
		assertEquals(secondChar, 1);
		int thirdChar = checkedChars[2];
		assertEquals(thirdChar, 1);
		int fourthChar = checkedChars[3];
		assertEquals(fourthChar, 1);
		int fifthChar = checkedChars[4];
		assertEquals(fifthChar, 0);

		System.out.println('\n' + wordleTest.guessToString(checkedChars) + '\n');
	}

	@Test
	public void testRepetitions6() {
		Wordle wordleTest = new Wordle(true);
		// HashMap<Integer, Integer> checkedChars = new HashMap<>();
		wordleTest.setDailyWord("fafaf");
		System.out.println(wordleTest.getDailyWord() + " I ran twelveth");
		int[] checkedChars = wordleTest.guess("afffa");
		int firstChar = checkedChars[0];
		assertEquals(firstChar, 2);
		int secondChar = checkedChars[1];
		assertEquals(secondChar, 2);
		int thirdChar = checkedChars[2];
		assertEquals(thirdChar, 1);
		int fourthChar = checkedChars[3];
		assertEquals(fourthChar, 2);
		int fifthChar = checkedChars[4];
		assertEquals(fifthChar, 2);

		System.out.println('\n' + wordleTest.guessToString(checkedChars) + '\n');
	}

	@Test
	public void testRepetitions7() {
		Wordle wordleTest = new Wordle(true);
		// HashMap<Integer, Integer> checkedChars = new HashMap<>();
		wordleTest.setDailyWord("fafaf");
		System.out.println(wordleTest.getDailyWord() + " I ran thirteenth");
		int[] checkedChars = wordleTest.guess("fefaf");
		int firstChar = checkedChars[0];
		assertEquals(firstChar, 1);
		int secondChar = checkedChars[1];
		assertEquals(secondChar, 0);
		int thirdChar = checkedChars[2];
		assertEquals(thirdChar, 1);
		int fourthChar = checkedChars[3];
		assertEquals(fourthChar, 1);
		int fifthChar = checkedChars[4];
		assertEquals(fifthChar, 1);

		System.out.println('\n' + wordleTest.guessToString(checkedChars) + '\n');
	}

	@Test
	public void testBug() {
		Wordle wordleTest = new Wordle(true);
		// HashMap<Integer, Integer> checkedChars = new HashMap<>();
		wordleTest.setDailyWord("crash");
		System.out.println(wordleTest.getDailyWord() + " I ran fourteenth");
		int[] checkedChars = wordleTest.guess("crahs");
		int firstChar = checkedChars[0];
		assertEquals(firstChar, 1);
		int secondChar = checkedChars[1];
		assertEquals(secondChar, 1);
		int thirdChar = checkedChars[2];
		assertEquals(thirdChar, 1);
		int fourthChar = checkedChars[3];
		assertEquals(fourthChar, 2);
		int fifthChar = checkedChars[4];
		assertEquals(fifthChar, 2);

		System.out.println('\n' + wordleTest.guessToString(checkedChars) + '\n');
	}

//	@Test
//	public void getAccounts() {
//		WordleSerializer ws = new WordleSerializer();
//		System.out.println(ws.getAccounts());
//	}

	@Test
	public void update() {
		WordleSerializer ws = new WordleSerializer();
		Wordle wordleTest = new Wordle(true);
		ws.createNewUser("jackson", "burns");
		WordleAccount jackson = ws.getAccounts().get(0);
		jackson.updateScore(5);
		wordleTest.updateAccount(jackson);
		// ws.update(jackson);
		assertEquals(ws.getAccounts().get(0).getScore()[4], 1);
		assertEquals(ws.getAccounts().get(0).getScore()[3], 0);
		jackson.updateScore(5);
		assertEquals(ws.getAccounts().get(0).getScore()[4], 2);
		System.out.println(jackson.getScoreString());
		jackson.updateScore(7);
		assertEquals(ws.getAccounts().get(0).getScore()[6], 1);
		System.out.println(jackson.getScoreString());

		WordleAccount wordleAcct = new WordleAccount("JJVH19", "nel");
		System.out.println(wordleAcct.getScoreString());

	}

	@Test
	public void testUser() {
		WordleSerializer ws = new WordleSerializer();
		ws.createNewUser("jackson", "burns");
		assertNotNull(ws.verifyLogin("jackson", "burns"));
		assertNull(ws.verifyLogin("jackson", "burns2"));
	}

	@Test
	public void testLogin() {
		Wordle wordleTest = new Wordle(true);
		wordleTest.createAccount("JJVH19", "nel");
		assertNotNull(wordleTest.login("JJVH19", "nel"));
		assertNull(wordleTest.login("JJVH18", "nel"));
		assertNull(wordleTest.login("JJVH19", "nl"));
	}

	@Test
	public void testGettingWord() {
		Wordle wordleTest = new Wordle(true);
		wordleTest.setDailyWord("crash");
		assertEquals(wordleTest.getWord(true), "crash");
		assertNotEquals(wordleTest.getWord(false), "crash");
	}

	@Test
	public void testSaveMap() {
		Wordle wordleTest = new Wordle(true);
		wordleTest.save();
	}

	@Test
	public void testAverageGuesses() {
		WordleSerializer ws = new WordleSerializer();
		ArrayList<WordleAccount> accounts = ws.getAccounts();
		for (int i = 0; i < accounts.size(); i++) {
			System.out.println("name: " + accounts.get(i).getUsername() + "\naverage: " + accounts.get(i).getAverage());
		}

	}

	@Test
	public void testRealWords() {
		Wordle wordleTest = new Wordle(true);
		assertFalse(wordleTest.isWord("word"));
		assertFalse(wordleTest.isWord("w0rds"));
		assertFalse(wordleTest.isWord("!@#$%"));
		assertFalse(wordleTest.isWord("W0Rd5"));

	}

	@Test
	public void testLeaderboards() {
		Wordle wordleTest = new Wordle(true);
		WordleAccount accountTest = new WordleAccount("JJVH19", "nel");
		Leaderboard leaderboardTest = new Leaderboard();
		WordleAccount accountTest2 = new WordleAccount("jackson", "burns");
		wordleTest.login("jackson", "burns");
		assertNotNull(wordleTest.login("jackson", "burns"));
		wordleTest.login("JJVH19", "nel");
		assertNotNull(wordleTest.login("JJVH19", "nel"));
		wordleTest.setDailyWord("corgi");
		// attempt 1
		int[] checkedChars = wordleTest.guess("crank");
		int firstChar = checkedChars[0];
		assertEquals(firstChar, 1);
		int secondChar = checkedChars[1];
		assertEquals(secondChar, 2);
		int thirdChar = checkedChars[2];
		assertEquals(thirdChar, 0);
		int fourthChar = checkedChars[3];
		assertEquals(fourthChar, 0);
		int fifthChar = checkedChars[4];
		assertEquals(fifthChar, 0);

		// attempt 2
		checkedChars = wordleTest.guess("cores");
		firstChar = checkedChars[0];
		assertEquals(firstChar, 1);
		secondChar = checkedChars[1];
		assertEquals(secondChar, 1);
		thirdChar = checkedChars[2];
		assertEquals(thirdChar, 1);
		fourthChar = checkedChars[3];
		assertEquals(fourthChar, 0);
		fifthChar = checkedChars[4];
		assertEquals(fifthChar, 0);

		// attempt 3
		checkedChars = wordleTest.guess("corgi");
		firstChar = checkedChars[0];
		assertEquals(firstChar, 1);
		secondChar = checkedChars[1];
		assertEquals(secondChar, 1);
		thirdChar = checkedChars[2];
		assertEquals(thirdChar, 1);
		fourthChar = checkedChars[3];
		assertEquals(fourthChar, 1);
		fifthChar = checkedChars[4];
		assertEquals(fifthChar, 1);

		accountTest.updateScore(3 + 1);
		wordleTest.updateAccount(accountTest);
		wordleTest.save();
		leaderboardTest.addUser(accountTest);
		ArrayList<WordleAccount> arr = leaderboardTest.getUsers();
		assertEquals(arr.size(), 1);

		System.out.println(leaderboardTest.toString());
		System.out.println(accountTest.totalScore());

		accountTest2.updateScore(2 + 1);
		wordleTest.updateAccount(accountTest2);
		wordleTest.save();
		leaderboardTest.addUser(accountTest2);
		arr = leaderboardTest.getUsers();
		assertEquals(arr.size(), 2);

		Leaderboard leaderboardTest2 = new Leaderboard(arr);

		System.out.println(leaderboardTest2.toString());
		System.out.println(accountTest2.totalScore());

		assertEquals(accountTest.compareTo(accountTest2), 0);
		assertEquals(accountTest.compareTo(accountTest), 0);

	}

	@Test
	public void testDates() {
		LocalDate dateTest = LocalDate.now();
		WordleAccount accountTest = new WordleAccount("JJVH19", "nel");
		Wordle wordleTest = new Wordle(true);
		Wordle wordleTestSameDay = new Wordle(true);
		Leaderboard leaderboardTest = new Leaderboard();
		accountTest.updateScore(3 + 1);
		wordleTest.updateAccount(accountTest);
		wordleTest.save();
		leaderboardTest.addUser(accountTest);
		ArrayList<WordleAccount> arr = leaderboardTest.getUsers();
		assertEquals(arr.size(), 1);

		System.out.println(leaderboardTest.toString());
		System.out.println(accountTest.totalScore());

		wordleTest.setDailyWord("corgi");
		String firstAttempt = wordleTest.getWord(true);
		assertEquals(firstAttempt, "corgi");
		String secondAttempt = wordleTestSameDay.getWord(true);
		assertNotEquals(secondAttempt, "corgi");
		assertNotEquals(firstAttempt, secondAttempt);

		wordleTestSameDay = new Wordle(true, LocalDate.now().plusDays(1));
		secondAttempt = wordleTestSameDay.getWord(true);
		assertNotEquals(secondAttempt, "corgi");
		assertNotEquals(firstAttempt, secondAttempt);

		accountTest.setLastPlayed(dateTest);
		assertEquals(accountTest.getLastPlayed(), dateTest);
		assertNotEquals(accountTest.getLastPlayed(), dateTest.plusDays(1));

		ArrayList<WordleAccount> accounts = wordleTest.getAccounts();
		assertEquals(accounts.size(), 2);
		Wordle wordleTest2 = new Wordle(false);
		assertNotEquals(wordleTest.getWord(true), wordleTest2.getWord(false));
	}

	@Test
	public void testRepetitionsBug() {
		Wordle wordleTest = new Wordle(true);
		// HashMap<Integer, Integer> checkedChars = new HashMap<>();
		wordleTest.setDailyWord("cover");
		int[] checkedChars = wordleTest.guess("carry");
		int firstChar = checkedChars[0];
		assertEquals(firstChar, 1);
		int secondChar = checkedChars[1];
		assertEquals(secondChar, 0);
		int thirdChar = checkedChars[2];
		assertEquals(thirdChar, 2);
		int fourthChar = checkedChars[3];
		assertEquals(fourthChar, 0);
		int fifthChar = checkedChars[4];
		assertEquals(fifthChar, 0);

		System.out.println('\n' + wordleTest.guessToString(checkedChars) + '\n');
	}

}
