package tests;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Test;

import model.Wordle;

public class WordleTest {

	@Test
	public void testAllCorrect() {
		Wordle wordleTest = new Wordle();
		System.out.println(wordleTest.getDailyWord() + " I ran first");
		wordleTest.setDailyWord("frank");
		int[] checkedChars= wordleTest.guess("frank");
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
		System.out.println(wordleTest.getDailyWord() + " I ran second");
		//HashMap<Integer, Integer> checkedChars = new HashMap<>();
		wordleTest.setDailyWord("frank");
		int[]checkedChars = wordleTest.guess("kranf");
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
		System.out.println(wordleTest.getDailyWord() + " I ran third");
		//HashMap<Integer, Integer> checkedChars = new HashMap<>();
		wordleTest.setDailyWord("frank");
		int[]checkedChars = wordleTest.guess("kfnar");
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
		System.out.println(wordleTest.getDailyWord() + " I ran fourth");
		//HashMap<Integer, Integer> checkedChars = new HashMap<>();
		wordleTest.setDailyWord("frank");
		int[]checkedChars = wordleTest.guess("crank");
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
		System.out.println(wordleTest.getDailyWord() + " I ran fifth");
		//HashMap<Integer, Integer> checkedChars = new HashMap<>();
		wordleTest.setDailyWord("frank");
		int[]checkedChars = wordleTest.guess("cfenc");
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
		System.out.println(wordleTest.getDailyWord() + " I ran sixth");
		//HashMap<Integer, Integer> checkedChars = new HashMap<>();
		wordleTest.setDailyWord("frank");
		int[]checkedChars = wordleTest.guess("uiope");
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
		System.out.println(wordleTest.getDailyWord() + " I ran seventh");
		//HashMap<Integer, Integer> checkedChars = new HashMap<>();
		wordleTest.setDailyWord("krank");
		int[]checkedChars = wordleTest.guess("krank");
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
		System.out.println(wordleTest.getDailyWord() + " I ran eighth");
		//HashMap<Integer, Integer> checkedChars = new HashMap<>();
		wordleTest.setDailyWord("frank");
		int[]checkedChars = wordleTest.guess("franf");
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
		System.out.println(wordleTest.getDailyWord() + " I ran ninth");
		//HashMap<Integer, Integer> checkedChars = new HashMap<>();
		wordleTest.setDailyWord("ffass");
		int[]checkedChars = wordleTest.guess("ffass");
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
		System.out.println(wordleTest.getDailyWord() + " I ran tenth");
		//HashMap<Integer, Integer> checkedChars = new HashMap<>();
		wordleTest.setDailyWord("ffaas");
		int[]checkedChars = wordleTest.guess("ffasa");
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
		System.out.println(wordleTest.getDailyWord() + " I ran eleventh");
		//HashMap<Integer, Integer> checkedChars = new HashMap<>();
		wordleTest.setDailyWord("fffff");
		int[]checkedChars = wordleTest.guess("zfffz");
		int firstChar = checkedChars[0];
		assertEquals(firstChar, 0);
		int secondChar = checkedChars[1];
		assertEquals(secondChar, 1);
		int thirdChar = checkedChars[2];
		assertEquals(thirdChar, 1);
		int fourthChar = checkedChars[3];
		assertEquals(fourthChar, 1);
		int fifthChar = checkedChars[4];
		assertEquals(fifthChar,  0);

		System.out.println('\n' + wordleTest.guessToString(checkedChars) + '\n');
	}

	@Test
	public void testRepetitions6() {
		Wordle wordleTest = new Wordle();
		System.out.println(wordleTest.getDailyWord() + " I ran twelveth");
		//HashMap<Integer, Integer> checkedChars = new HashMap<>();
		wordleTest.setDailyWord("fafaf");
		int[]checkedChars = wordleTest.guess("afffa");
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
		System.out.println(wordleTest.getDailyWord() + " I ran thirteenth");
		//HashMap<Integer, Integer> checkedChars = new HashMap<>();
		wordleTest.setDailyWord("fafaf");
		int[]checkedChars = wordleTest.guess("fefaf");
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
}
