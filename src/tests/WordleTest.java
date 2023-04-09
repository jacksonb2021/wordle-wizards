package tests;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import model.Wordle;
import model.WordleAccount;
import model.WordleSerializer;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WordleTest {

	/**
	 * can only run this test if the accounts.ser file is deleted. If you want the
	 * required code coverage percentage, delete all local .ser files and run with
	 * this testAccountCreator method uncommented
	 */
//	@Test
//	public void testAccountCreator() {
//		WordleSerializer ws = new WordleSerializer();
//		ws.createNewUser("jackson", "burns");
//		ws.saveAccounts();
//		ws.createNewUser("JJVH19", "nel");
//		ws.saveAccounts();
//	}

	@Test
	public void testAllCorrect() {
		Wordle wordleTest = new Wordle();
		wordleTest.setDailyWord("frank");
		System.out.println(wordleTest.getDailyWord() + " I ran first ");
		int[] checkedChars = wordleTest.guess("frank", true);
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
		Wordle wordleTest = new Wordle();
		// HashMap<Integer, Integer> checkedChars = new HashMap<>();
		wordleTest.setDailyWord("frank");
		System.out.println(wordleTest.getDailyWord() + " I ran second ");
		int[] checkedChars = wordleTest.guess("kranf", true);
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
		Wordle wordleTest = new Wordle();
		// HashMap<Integer, Integer> checkedChars = new HashMap<>();
		wordleTest.setDailyWord("frank");
		System.out.println(wordleTest.getDailyWord() + " I ran third ");
		int[] checkedChars = wordleTest.guess("kfnar", true);
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
		Wordle wordleTest = new Wordle();
		// HashMap<Integer, Integer> checkedChars = new HashMap<>();
		wordleTest.setDailyWord("frank");
		System.out.println(wordleTest.getDailyWord() + " I ran fourth ");
		int[] checkedChars = wordleTest.guess("crank", true);
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
		Wordle wordleTest = new Wordle();
		// HashMap<Integer, Integer> checkedChars = new HashMap<>();
		wordleTest.setDailyWord("frank");
		System.out.println(wordleTest.getDailyWord() + " I ran fifth ");
		int[] checkedChars = wordleTest.guess("cfenc", true);
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
		Wordle wordleTest = new Wordle();
		// HashMap<Integer, Integer> checkedChars = new HashMap<>();
		wordleTest.setDailyWord("frank");
		System.out.println(wordleTest.getDailyWord() + " I ran sixth ");
		int[] checkedChars = wordleTest.guess("uiope", true);
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
		Wordle wordleTest = new Wordle();
		// HashMap<Integer, Integer> checkedChars = new HashMap<>();
		wordleTest.setDailyWord("krank");
		System.out.println(wordleTest.getDailyWord() + " I ran seventh ");
		int[] checkedChars = wordleTest.guess("krank", true);
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
		Wordle wordleTest = new Wordle();
		// HashMap<Integer, Integer> checkedChars = new HashMap<>();
		wordleTest.setDailyWord("frank");
		System.out.println(wordleTest.getDailyWord() + " I ran eighth");
		int[] checkedChars = wordleTest.guess("franf", true);
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
		Wordle wordleTest = new Wordle();
		// HashMap<Integer, Integer> checkedChars = new HashMap<>();
		wordleTest.setDailyWord("ffass");
		System.out.println(wordleTest.getDailyWord() + " I ran ninth");
		int[] checkedChars = wordleTest.guess("ffass", true);
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
		Wordle wordleTest = new Wordle();
		// HashMap<Integer, Integer> checkedChars = new HashMap<>();
		wordleTest.setDailyWord("ffaas");
		System.out.println(wordleTest.getDailyWord() + " I ran tenth");
		int[] checkedChars = wordleTest.guess("ffasa", true);
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
		Wordle wordleTest = new Wordle();
		// HashMap<Integer, Integer> checkedChars = new HashMap<>();
		wordleTest.setDailyWord("fffff");
		System.out.println(wordleTest.getDailyWord() + " I ran eleventh");
		int[] checkedChars = wordleTest.guess("zfffz", true);
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
		Wordle wordleTest = new Wordle();
		// HashMap<Integer, Integer> checkedChars = new HashMap<>();
		wordleTest.setDailyWord("fafaf");
		System.out.println(wordleTest.getDailyWord() + " I ran twelveth");
		int[] checkedChars = wordleTest.guess("afffa", true);
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
		Wordle wordleTest = new Wordle();
		// HashMap<Integer, Integer> checkedChars = new HashMap<>();
		wordleTest.setDailyWord("fafaf");
		System.out.println(wordleTest.getDailyWord() + " I ran thirteenth");
		int[] checkedChars = wordleTest.guess("fefaf", true);
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
		Wordle wordleTest = new Wordle();
		// HashMap<Integer, Integer> checkedChars = new HashMap<>();
		wordleTest.setDailyWord("crash");
		System.out.println(wordleTest.getDailyWord() + " I ran fourteenth");
		int[] checkedChars = wordleTest.guess("crahs", true);
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
		Wordle wordleTest = new Wordle();
		ws.createNewUser("jackson","burns");
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
		ws.createNewUser("jackson","burns");
		assertNotNull(ws.verifyLogin("jackson", "burns"));
		assertNull(ws.verifyLogin("jackson", "burns2"));
	}

//	@Test
//	public void getMap() {
//		WordleSerializer ws = new WordleSerializer();
//		System.out.println(ws.getMap());
//	}

	@Test
	public void testLogin() {
		Wordle wordleTest = new Wordle();
		wordleTest.createAccount("JJVH19", "nel");
		assertNotNull(wordleTest.login("JJVH19", "nel"));
		assertNull(wordleTest.login("JJVH18", "nel"));
		assertNull(wordleTest.login("JJVH19", "nl"));
	}

	@Test
	public void testGettingWord() {
		Wordle wordleTest = new Wordle();
		wordleTest.setDailyWord("crash");
		assertEquals(wordleTest.getWord(true), "crash");
		assertNotEquals(wordleTest.getWord(false), "crash");
	}

	@Test
	public void testSaveMap() {
		Wordle wordleTest = new Wordle();
		wordleTest.save();
	}
}
